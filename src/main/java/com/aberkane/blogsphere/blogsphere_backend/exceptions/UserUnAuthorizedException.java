package com.aberkane.blogsphere.blogsphere_backend.exceptions;

public class UserUnAuthorizedException extends RuntimeException{
    public UserUnAuthorizedException(String message){super(message);}
}
