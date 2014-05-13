package com.umeca.infrastructure;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.umeca.model.ResponseMessage;

public class PojoValidator {
	public static <T> ResponseMessage validate(T pojo){
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<T>> violations = validator.validate(pojo);

		for (ConstraintViolation<T> constraintViolation : violations) {
            String message = constraintViolation.getMessage();
            return new ResponseMessage(true, message);
        }
		
		return null;
	}

    public static <T> String validateConcat(T pojo){
        String cadMsg = new String("");
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(pojo);

        for (ConstraintViolation<T> constraintViolation : violations) {
            String message = constraintViolation.getMessage();
            cadMsg = cadMsg + message;
        }

        if(cadMsg.trim().equals(""))
            return null;
        else
            return cadMsg;
    }
}
