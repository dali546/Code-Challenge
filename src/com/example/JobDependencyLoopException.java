package com.example;

public class JobDependencyLoopException extends JobDependencyException {
    public JobDependencyLoopException(){
        super("Error. Job list has a infinite loop.");
    }
}
