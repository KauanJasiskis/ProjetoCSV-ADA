package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RepositorioGeral<T> {
    void insertNoBanco(List<T> itens);
    List<T> selectNoBanco();
}
