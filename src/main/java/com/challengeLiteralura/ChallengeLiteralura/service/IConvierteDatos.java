package com.challengeLiteralura.ChallengeLiteralura.service;

public interface IConvierteDatos {

    <T> T obtenerDatos (String json, Class<T> clase);
}
