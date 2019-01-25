package com.maslke.spring.validator;

import com.maslke.spring.pojo.Person;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * validator usage
 */
public class PersonValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
    }
}
