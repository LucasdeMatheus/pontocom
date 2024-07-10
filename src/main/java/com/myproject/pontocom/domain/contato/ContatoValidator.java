package com.myproject.pontocom.domain.contato;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class ContatoValidator {

    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    public static Set<ConstraintViolation<Contato>> validate(Contato contato) {
        return validator.validate(contato);
    }
}
