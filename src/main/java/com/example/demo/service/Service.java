package com.example.demo.service;

import com.example.demo.modelo.Produto;
import com.example.demo.repository.RepositorioArquivoGeral;
import com.example.demo.repository.RepositorioBancoProdutos;
import com.example.demo.repository.RepositorioGeral;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class Service {
    private final RepositorioArquivoGeral<Produto> repositorioArquivoGeral;
    private final RepositorioGeral<Produto> repositorioGeral;

    public Service(RepositorioArquivoGeral<Produto> repositorioArquivoGeral, RepositorioGeral<Produto> repositorioGeral) {
        this.repositorioArquivoGeral = repositorioArquivoGeral;
        this.repositorioGeral = repositorioGeral;
    }

    public void salvarProdutosNoBanco(List<Produto> produtos) {
        repositorioGeral.insertNoBanco(produtos);

    }

    public List<Produto> resgatarProdutosNoBanco() {
        return repositorioGeral.selectNoBanco();
    }

    public void SalvarProdutosNoArquivo(List<Produto> produtos) {
        repositorioArquivoGeral.insertNoArquivo(produtos);
    }

    public List<Produto> resgatarProdutosNoArquivo() {
        return repositorioArquivoGeral.selectNoArquivo();
    }

   public Integer calcularCategorias(List<Produto> produtos) {
        HashSet<String> categoriasJaVistas = new HashSet<>();
        produtos.stream()
                .map(Produto::getCategoria)
                .forEach(categoriasJaVistas::add);
        return categoriasJaVistas.size();
    }

    public Map<String, Long> calcularQuantiadePorCategoria(List<Produto> produtos) {
        return produtos.stream().collect(Collectors.groupingBy(Produto::getCategoria, Collectors.counting()));
    }

    public  BigDecimal calcularValorMedio(List<Produto> produtos) {
        BigDecimal precoTotal = produtos.stream().map(produto -> produto.getPreco()
                .multiply(BigDecimal.valueOf(produto.getQuantidade())))
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        Long quantidade = produtos.stream().mapToLong(Produto::getQuantidade).sum();
        return precoTotal.divide(BigDecimal.valueOf(quantidade), 2, RoundingMode.HALF_UP);
    }

    public List<Produto> verificaSeProdutoTemBaixoEstoque(List<Produto> produtos) {
        return produtos.stream().filter(produto -> produto.getQuantidade() < 3).toList();
    }
}



