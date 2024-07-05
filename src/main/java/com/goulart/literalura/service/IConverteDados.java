package com.goulart.literalura.service;

public interface IConverteDados {
    <tipo> tipo obterDados(String json, Class<tipo> classe);
}
