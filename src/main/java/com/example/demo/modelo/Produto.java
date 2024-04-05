package com.example.demo.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    private Integer id;
    private String nome;
    private Integer quantidade;
    private String categoria;

    private BigDecimal preco;

    public Produto(String nome, Integer quantidade, String categoria, BigDecimal preco) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.preco = preco;

    }

    public String formatar(FormatadorProduto formatador){
        return formatador.formatar(this);
    }

}
