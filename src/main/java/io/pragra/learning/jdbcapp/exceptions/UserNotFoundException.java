package io.pragra.learning.jdbcapp.exceptions;

public class UserNotFoundException  extends RuntimeException{
    public UserNotFoundException(String msg){
        super(msg);
    }
}
