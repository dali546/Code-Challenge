package com.example;

import java.util.*;

public class Main {

    public static void main(String[] args) throws JobDependencyException {
        // Run Jobs

        Job[] jobs = Arrays.stream(args).map(Job::new).toArray(Job[]::new);

        String seq = runJobs(jobs);

        // Print Response
        System.out.println(seq);
    }

    public static String runJobs(Job[] jobs) throws JobDependencyException, JobDependencyLoopException {
        StringBuilder seq = new StringBuilder();

        if (validateJobListIsFinite(jobs)) {
            for (Job job : jobs) {
                seq.append(runJob(job));
            }
            return jobs.length == 0 ? "" : seq.toString();
        } else throw new JobDependencyLoopException();
    }

    private static String runJob(Job job) throws JobDependencyException {
        StringBuilder response = new StringBuilder();

        if (job.getDependentOn() != null) {
            if (job.getJob().equals(job.getDependentOn().getJob())) { // String compare instead of memory object compare. (safe)
                throw new JobDependencyException();
            }
            response.append(runJob(job.getDependentOn()));
        }
        response.append(job.getJob());

        return response.toString();
    }

    private static boolean validateJobListIsFinite(Job[] jobs) {

        Set<String> chain;
        for (Job job : jobs) {
            chain = new TreeSet<>();
            chain.add(job.getJob());
            if (checkJobDependentsIsInChain(job, chain)) { // If duplicate found exit and return false
                return false;
            }
        }
        return true;
    }

    private static boolean checkJobDependentsIsInChain(Job job, Set<String> chain) {
        if (job.getDependentOn() == null) {
            return false; // Not dependent on anything... therefore no loop
        } else {
            if (chain.contains(job.getDependentOn().getJob())) {
                return true;
            } else {
                chain.add(job.getDependentOn().getJob());
                return checkJobDependentsIsInChain(job.getDependentOn(), chain);
            }
        }
    }

}
