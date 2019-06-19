package org.practice.spring.validate;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * friend验证器,验证foo的friend必须以A开头
 * @author vergil
 *
 */
public class FriendConstraintValidator implements ConstraintValidator<FriendConstraint,List<String>>{

	@Override
	public void initialize(FriendConstraint constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(List<String> value, ConstraintValidatorContext context) {
		for(String f : value) {
			if(!f.startsWith("A")) {
				return false;
			}
		}
		
		return true;
	}


}
