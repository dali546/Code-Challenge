package com.example;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws JobDependencyException {
        // Run Jobs

        Job[] jobs = Arrays.stream(args).map(Job::new).toArray(Job[]::new);

        String seq = runJobs(jobs);

        // Print Response
        System.out.println(seq);
    }

    public static String runJobs(Job[] jobs) throws JobDependencyException {
        StringBuilder seq = new StringBuilder();

        for (Job job : jobs) {
            seq.append(runJob(job));
        }

        return jobs.length == 0 ? "" : seq.toString();
    }

    private static String runJob(Job job) throws JobDependencyException {
        StringBuilder response = new StringBuilder();

        if (job.getDependentOn() != null) {
            if (job.getJob().equals(job.getDependentOn().getJob())){
                throw new JobDependencyException("Error. Job cannot Depend on itself.");
            }
            response.append(runJob(job.getDependentOn()));
        }
        response.append(job.getJob());
        return response.toString();
    }

}
