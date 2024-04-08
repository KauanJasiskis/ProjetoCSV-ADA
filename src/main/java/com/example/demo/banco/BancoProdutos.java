package com.example.demo.banco;

import com.example.demo.modelo.Produto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BancoProdutos implements Banco<Produto> {
    private final JdbcTemplate jdbcTemplate;

    public BancoProdutos(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertNoBanco(List<Produto> produtos) {
        String sql = "INSERT INTO produtos (nome, quantidade, categoria, preco) VALUES (?, ?, ?, ?)";
        produtos.forEach(produto -> {
            if (!existeProdutoComMesmoNomeEPreco(produto)) {
                jdbcTemplate.update(sql, produto.getNome(), produto.getQuantidade(), produto.getCategoria(), produto.getPreco());
            } else {
                System.out.println("Produto: " + produto.getNome() + " Com o preco: " + produto.getPreco() + " Ja existe no banco, ignorando insert");
            }

        });
    }


    @Override
    public List<Produto> selectNoBanco() {
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

    private boolean existeProdutoComMesmoNomeEPreco(Produto produto) {
        String sql = "SELECT COUNT(*) FROM produtos WHERE nome = ? AND preco = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, produto.getNome(), produto.getPreco());
        return count > 0;
    }

}
