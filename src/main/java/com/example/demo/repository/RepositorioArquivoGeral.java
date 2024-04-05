package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioArquivoGeral<T> {
    void insertNoArquivo(List<T> itens);

    List<T> selectNoArquivo();
}

