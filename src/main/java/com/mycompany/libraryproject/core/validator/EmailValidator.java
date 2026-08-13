package com.mycompany.libraryproject.core.validator;

public class EmailValidator {

    public EmailValidator() {
    }

    public static boolean validate(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }

        return email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }
}
