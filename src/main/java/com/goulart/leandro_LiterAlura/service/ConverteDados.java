package com.goulart.leandro_LiterAlura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goulart.leandro_LiterAlura.model.DadosAutor;
import com.goulart.leandro_LiterAlura.model.DadosLivro;

public class ConversorDeDados implements IConversorDeDados {
    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T getData(String json, Class<T> classe) {
        T resultado = null;
        try {
            if (classe == DadosLivro.class) {
                JsonNode node = mapper.readTree(json);
                var s = node.get("results").get(0);
                resultado = mapper.treeToValue(s, classe);
            }else if (classe == DadosAutor.class) {
                JsonNode node = mapper.readTree(json);
                var s = node.get("results").get(0).get("authors").get(0);
                resultado = mapper.treeToValue(s, classe);
            }else {
                resultado = mapper.readValue(json, classe);
            }
        }catch (JsonProcessingException e){
            e.getStackTrace();
            throw new RuntimeException(e);
        }
        return resultado;
    }
}
