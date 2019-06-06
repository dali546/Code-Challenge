package com.example;

public class Job {

    private String job;
    private Job dependentOn;

    public Job(String job) {
        this(job, null);
    }

    public Job(String job, Job dependentOn) {
        this.job = job;
        this.dependentOn = dependentOn;
    }

    public String getJob() {
        return job;
    }

    public Job getDependentOn() {
        return dependentOn;
    }
}
