package com.example;

public class JobDependencyException extends Exception {
    public JobDependencyException() {
        super("Error. Job cannot Depend on itself.");
    }

    public JobDependencyException(String s) {
        super(s);
    }
}
