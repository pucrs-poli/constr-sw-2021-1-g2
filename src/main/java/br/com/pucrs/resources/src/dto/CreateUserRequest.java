package br.com.pucrs.resources.src.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class CreateUserRequest implements Serializable {
    private String userName;

    private String userPassword;
}