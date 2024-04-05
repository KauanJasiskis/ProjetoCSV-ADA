package com.example.demo.banco;

import com.example.demo.modelo.FormatadorCSVProduto;
import com.example.demo.modelo.FormatadorProduto;
import com.example.demo.modelo.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Component
public class BancoCSVProdutos implements BancoArquivo<Produto> {

    @Value("${caminho.arquivo.csv1}")
    private String arquivoCSVOriginal;
    @Value("${caminho.arquivo.csv2}")
    private String arquivoCSVNovo;
    @Override
    public void insertNoArquivo(List<Produto> produtos) {
        try {
            FileWriter writer = new FileWriter(arquivoCSVNovo, true);
            FormatadorProduto formatadorProduto = new FormatadorCSVProduto();
            for (Produto produto : produtos) {
                writer.append(produto.formatar(formatadorProduto)).append("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Produto> selectNoArquivo() {
        List<Produto> produtos = null;
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSVOriginal))) {
            String linha;
            br.readLine();
            produtos = new ArrayList<>();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return produtos;
    }
}

