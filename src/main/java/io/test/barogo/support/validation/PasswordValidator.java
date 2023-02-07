package io.test.barogo.support.validation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (passwordCheck(value)) {
            return true;
        }
        return false;
    }

    private boolean passwordCheck(String password) {
        if (password == null || password.equals("")) {
            return false;
        }
        boolean checked = false;
        try {
            int checkNum = 0;
            String numberRedExp = "^(?=.*[0-9])(?=.\\S+$).{12,30}$";
            String lowerRedExp = "^(?=.*[a-z])(?=.\\S+$).{12,30}$";
            String upperRedExp = "^(?=.*[A-Z])(?=.\\S+$).{12,30}$";
            String specialRedExp = "^(?=.*\\W)(?=.\\S+$).{12,30}$";
            if (password.matches(numberRedExp)) {
                checkNum++;
            }
            if (password.matches(lowerRedExp)) {
                checkNum++;
            }
            if (password.matches(upperRedExp)) {
                checkNum++;
            }
            if (password.matches(specialRedExp)) {
                checkNum++;
            }
            if (checkNum >= 3) {
                checked = true;
            }
        } catch (Exception e) {
            return false;
        }
        return checked;
    }
}
