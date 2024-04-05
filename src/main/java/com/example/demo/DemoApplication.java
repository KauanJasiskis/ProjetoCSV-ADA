package com.example.demo;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public DemoApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {
        List<Produto> produtosNoCSV = lerCsv();
        insert(produtosNoCSV);
        List<Produto> produtosNoBanco = select();
        System.out.println("Total de categorias " + calcularCategorias(produtosNoBanco));
        Map<String, Long> quantidadePorCategoria = calcularQuantiadePorCategoria(produtosNoBanco);
        quantidadePorCategoria.forEach((categoria, quantidade) -> System.out.println(categoria + " : " + quantidade));
        List<Produto> produtosEmBaixoEstoque = verificaSeProdutoTemBaixoEstoque(produtosNoBanco);
        System.out.println("Produtos em baixo estoque: ");
        produtosEmBaixoEstoque.forEach(produto -> System.out.println(produto.getNome()));
        BigDecimal valorMedio = calcularValorMedio(produtosNoBanco);
        System.out.println("Valor medio dos protudos no banco: R$" + valorMedio);
    }



    private List<Produto> lerCsv() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\ProjetoCSV\\DEMOCSV\\produtos.csv"));
        String linha;
        br.readLine();
        List<Produto> produtos = new ArrayList<>();
        while ((linha = br.readLine()) != null) {
            String[] valores = linha.split(",");
            produtos.add(
                    new Produto(
                            valores[0],
                            Integer.parseInt(valores[1]),
                            valores[2],
                            new BigDecimal(valores[3])
                    )
            );
        }
        return produtos;
    }

    private void insert(List<Produto> produtos) {
        String sql = "INSERT INTO produtos (nome, quantidade, categoria, preco) VALUES (?, ?, ?, ?)";
        produtos.forEach(produto -> {
            jdbcTemplate.update(sql, produto.getNome(), produto.getQuantidade(), produto.getCategoria(), produto.getPreco());
        });
    }

    private Integer calcularCategorias(List<Produto> produtos) {
        HashSet<String> categoriasJaVistas = new HashSet<>();
        produtos.stream()
                .map(Produto::getCategoria)
                .forEach(categoriasJaVistas::add);
        return categoriasJaVistas.size();
    }

    private Map<String, Long> calcularQuantiadePorCategoria(List<Produto> produtos) {
        return produtos.stream().collect(Collectors.groupingBy(Produto::getCategoria, Collectors.counting()));
    }
    private List<Produto> select() {
        String sql = "SELECT * FROM produtos";
        RowMapper<Produto> rowMapper = ((rs, rowNum) -> new Produto(
                rs.getInt("ID_do_produto"),
                rs.getString("nome"),
                rs.getInt("quantidade"),
                rs.getString("categoria"),
                rs.getBigDecimal("preco")
        ));
        return jdbcTemplate.query(sql, rowMapper);
    }

    private BigDecimal calcularValorMedio(List<Produto> produtos) {
        BigDecimal precoTotal = produtos.stream().map(Produto::getPreco).reduce(BigDecimal.ZERO, BigDecimal::add);
        Long quantidade = produtos.stream().mapToLong(Produto::getQuantidade).sum();
        return precoTotal.divide(BigDecimal.valueOf(quantidade), 2, RoundingMode.HALF_UP);
    }

    private List<Produto> verificaSeProdutoTemBaixoEstoque(List<Produto> produtos) {
        return produtos.stream().filter(produto -> produto.getQuantidade() < 3).toList();
    }
}



