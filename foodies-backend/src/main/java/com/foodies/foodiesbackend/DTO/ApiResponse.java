package com.foodies.foodiesbackend.DTO;

public class ApiResponse {

    private int status;
    private int id;
    private String message;
    private Object result;

    public ApiResponse(int status, String message, Object result){
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public ApiResponse(int status, int id, String message, Object result){
        this.status = status;
        this.id = id;
        this.message = message;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {return message;}

}