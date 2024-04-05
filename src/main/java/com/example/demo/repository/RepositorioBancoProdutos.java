package com.example.demo.repository;

import com.example.demo.banco.Banco;
import com.example.demo.modelo.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioBancoProdutos implements RepositorioGeral<Produto> {
    private final Banco<Produto> banco;

    public RepositorioBancoProdutos(Banco<Produto> banco) {
        this.banco = banco;
    }

    @Override
    public void insertNoBanco(List<Produto> itens) {
        banco.insertNoBanco(itens);
    }

    @Override
    public List<Produto> selectNoBanco() {
        return banco.selectNoBanco();
    }
}


