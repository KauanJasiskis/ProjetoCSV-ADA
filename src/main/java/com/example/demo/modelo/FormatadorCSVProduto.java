package com.example.demo.modelo;

public class FormatadorCSVProduto implements FormatadorProduto {
    @Override
    public String formatar(Produto produto) {
        return produto.getNome() + "," + produto.getQuantidade() + "," + produto.getCategoria() + "," + produto.getPreco();
    }
}