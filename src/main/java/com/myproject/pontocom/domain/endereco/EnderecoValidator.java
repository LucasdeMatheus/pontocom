package com.myproject.pontocom.domain.endereco;

import com.myproject.pontocom.domain.contato.Contato;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class EnderecoValidator {
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    public static Set<ConstraintViolation<Endereco>> validate(Endereco endereco) {
        return validator.validate(endereco);
    }
}
