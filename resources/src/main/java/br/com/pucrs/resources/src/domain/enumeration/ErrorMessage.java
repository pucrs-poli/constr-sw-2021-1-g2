package br.com.pucrs.resources.src.domain.enumeration;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorMessage {

    //TODO adicionar todos os erros aqui

    INTERNAL_ERROR("Internal error occurred. Please contact our support.");

    private final String value;
}
