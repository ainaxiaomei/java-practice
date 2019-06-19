package org.practice.spring.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * 基于spring验证接口的验证器
 * @author vergil
 *
 */
public class FooValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Foo.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "name", "name can not be null !");
		
		Foo foo = (Foo) target;
		
		if(foo.getAge() <= 0){
			errors.reject(" age is illegel !");
		}
		
	}

}
