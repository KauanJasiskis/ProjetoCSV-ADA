package com.example.demo.config;

import com.example.demo.banco.Banco;
import com.example.demo.banco.BancoArquivo;
import com.example.demo.banco.BancoCSVProdutos;
import com.example.demo.banco.BancoProdutos;
import com.example.demo.modelo.Produto;
import com.example.demo.repository.RepositorioArquivoGeral;
import com.example.demo.repository.RepositorioArquivoProdutos;
import com.example.demo.repository.RepositorioBancoProdutos;
import com.example.demo.repository.RepositorioGeral;

import com.example.demo.service.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConfiguracaoBeans{
    @Bean
    public BancoProdutos bancoProdutos(){
        return new BancoProdutos(new JdbcTemplate());
    }
    @Bean
    public BancoCSVProdutos bancoCSVProdutos(){
        return new BancoCSVProdutos();
    }
    @Bean
    public RepositorioBancoProdutos repositorioBancoProdutos(Banco<Produto> banco) {
        return new RepositorioBancoProdutos(banco);
    }

    @Bean
    public RepositorioArquivoProdutos repositorioArquivoProdutos(BancoArquivo<Produto> bancoArquivo) {
        return new RepositorioArquivoProdutos(bancoArquivo);
    }

    @Bean
    public Service productService(RepositorioArquivoGeral<Produto> repositorioArquivoGeral,RepositorioGeral<Produto> repositorioGeral) {
        return new Service(repositorioArquivoGeral,repositorioGeral);
    }
}



