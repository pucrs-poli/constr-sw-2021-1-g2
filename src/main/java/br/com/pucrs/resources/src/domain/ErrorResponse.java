package br.com.pucrs.resources.src.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ErrorResponse<T> implements Serializable {

    private int statusCode;
    private T data;
}

