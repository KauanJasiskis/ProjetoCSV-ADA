package com.example.demo;

import com.example.demo.modelo.Produto;
import com.example.demo.service.Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {


    private final Service service;

    public DemoApplication(Service service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {
        //Se atente quando for rodar o programa
        // para o  arquivos csv 'produtosDoBanco' ter um linha em branco
        // a baixo do ultimo dado informado para o programa funcionar corretamente
        List<Produto> produtosNoCSV = service.resgatarProdutosNoArquivo();
        service.salvarProdutosNoBanco(produtosNoCSV);
        List<Produto> produtosNoBanco = service.resgatarProdutosNoBanco();
       service.SalvarProdutosNoArquivo(produtosNoBanco);
        System.out.println("Total de categorias " + service.calcularCategorias(produtosNoBanco));
        Map<String, Long> quantidadePorCategoria = service.calcularQuantiadePorCategoria(produtosNoBanco);
        quantidadePorCategoria.forEach((categoria, quantidade) -> System.out.println(categoria + " : " + quantidade));
        List<Produto> produtosEmBaixoEstoque = service.verificaSeProdutoTemBaixoEstoque(produtosNoBanco);
        System.out.println("Produtos em baixo estoque: ");
        produtosEmBaixoEstoque.forEach(produto -> System.out.println(produto.getNome()));
        BigDecimal valorMedio = service.calcularValorMedio(produtosNoBanco);
        System.out.println("Valor medio dos protudos no banco: R$" + valorMedio);
    }
}






