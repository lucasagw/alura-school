package br.com.alura.aluraschool.util;

public interface IConvertsData {

    <T> T getData(String json, Class<T> clazz);
}
