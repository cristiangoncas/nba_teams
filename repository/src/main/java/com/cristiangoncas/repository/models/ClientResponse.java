package com.cristiangoncas.repository.models;

public class ClientResponse {

    private boolean success;
    private String message;

    public ClientResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}