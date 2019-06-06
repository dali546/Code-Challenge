package com.example;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Run Jobs

        Job[] jobs = Arrays.stream(args).map(Job::new).toArray(Job[]::new);

        String seq = runJobs(jobs);

        // Print Response
        System.out.println(seq);
    }

    public static String runJobs(Job[] jobs) {
        StringBuilder seq = new StringBuilder();

        for (Job job : jobs) {
            seq.append(runJob(job));
        }

        return jobs.length == 0 ? "" : seq.toString();
    }

    private static String runJob(Job job) {
        StringBuilder response = new StringBuilder();
        if (job.getDependentOn() != null) {
            response.append(runJob(job.getDependentOn()));
        }
        response.append(job.getJob());
        return response.toString();
    }

}
