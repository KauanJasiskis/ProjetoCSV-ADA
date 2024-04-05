package com.example.demo.repository;

import com.example.demo.banco.BancoArquivo;
import com.example.demo.modelo.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public class RepositorioArquivoProdutos implements RepositorioArquivoGeral<Produto> {
    private final BancoArquivo<Produto> bancoArquivo;
    public RepositorioArquivoProdutos(BancoArquivo<Produto> bancoArquivo){
        this.bancoArquivo = bancoArquivo;
    }
    @Override
    public void insertNoArquivo(List<Produto> produtos) {
        bancoArquivo.insertNoArquivo(produtos);
    }

    @Override
    public List<Produto> selectNoArquivo() {
        return bancoArquivo.selectNoArquivo();
    }
}
