package br.com.application.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequest implements Serializable {

    private final String username;
    private final String password;
    private final String email;
}
