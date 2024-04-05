package com.example.demo.banco;

import com.example.demo.modelo.Produto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BancoProdutos  implements Banco<Produto> {
    private final JdbcTemplate jdbcTemplate;

    public BancoProdutos(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertNoBanco(List<Produto> produtos) {
        String sql = "INSERT INTO produtos (nome, quantidade, categoria, preco) VALUES (?, ?, ?, ?)";
        produtos.forEach(produto -> {
            jdbcTemplate.update(sql, produto.getNome(), produto.getQuantidade(), produto.getCategoria(), produto.getPreco());
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
}
