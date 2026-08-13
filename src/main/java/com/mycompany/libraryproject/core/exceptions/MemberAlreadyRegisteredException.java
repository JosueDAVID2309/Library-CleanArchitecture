package com.mycompany.libraryproject.core.exceptions;

public class MemberAlreadyRegisteredException extends RuntimeException {

    public MemberAlreadyRegisteredException() {
        super("This member has already registered");
    }
}
