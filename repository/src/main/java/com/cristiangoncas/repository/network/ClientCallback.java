package com.cristiangoncas.repository.network;

public interface ClientCallback<T> {
    void response(T response);
}
