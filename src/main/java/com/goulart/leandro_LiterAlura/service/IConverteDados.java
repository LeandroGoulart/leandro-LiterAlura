package com.goulart.leandro_LiterAlura.service;

public interface IConversorDeDados {
    <T> T getData(String json, Class<T> classe);
}