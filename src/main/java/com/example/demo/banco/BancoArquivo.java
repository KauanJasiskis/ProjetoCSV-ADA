package com.example.demo.banco;

import java.util.List;

public interface BancoArquivo<T>{
    void insertNoArquivo(List<T> itens);

    List<T> selectNoArquivo();


}
