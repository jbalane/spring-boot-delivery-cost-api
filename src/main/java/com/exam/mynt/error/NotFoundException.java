package com.exam.mynt.error;

public class NotFoundException extends RuntimeException{

    private Long id;
    private String name;

    public NotFoundException(Long id) {
        super("Unable to find " + id);
    }

    public NotFoundException(String name) {
        super("Unable to find " + name);
    }
}
