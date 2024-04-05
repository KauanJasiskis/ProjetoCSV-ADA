package com.example.demo.banco;

import java.io.IOException;
import java.util.List;

public interface Banco<T> {
    void insertNoBanco(List<T> itens);

    List<T> selectNoBanco();

}
