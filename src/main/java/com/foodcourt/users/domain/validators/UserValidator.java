package com.foodcourt.users.domain.validators;

import com.foodcourt.users.domain.constants.Constants;
import com.foodcourt.users.domain.exception.DomainException;
import com.foodcourt.users.domain.model.User;

import java.time.LocalDate;
import java.time.Period;

public class UserValidator {

    public static User validateEmail(User user){
        if (!user.getEmail().matches(Constants.EMAIL_PATTERN)){
            throw new DomainException(Constants.INVALID_EMAIL);
        }
        return user;
    }

    public static User validatePhone(User user){
        if (!user.getPhoneNumber().matches(Constants.PHONE_NUMBER_PATTERN)){
            throw new DomainException(Constants.INVALID_PHONE_NUMBER);
        }
        return user;
    }

    public static User validateIDNumber(User user){
        if (!user.getIdNumber().matches(Constants.ID_NUMBER_PATTERN)){
             throw new DomainException(Constants.INVALID_ID_NUMBER);
        }
        return user;
    }

    public static User validateAge(User userToCreate){
        LocalDate today = LocalDate.now();
        int age = Period.between(userToCreate.getBirthDate(), today).getYears();
        if (age < Constants.MIN_AGE){
            throw new DomainException(Constants.INVALID_AGE);
        }
        return userToCreate;
    }
}
