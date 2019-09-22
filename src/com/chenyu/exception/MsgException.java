package com.chenyu.exception;

/**
 * @author xinYing
 */
public class MsgException extends RuntimeException {
    public MsgException(){

    }
    public MsgException(String name){
        super(name);
    }
}
