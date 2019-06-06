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
        return Arrays.stream(jobs) // For Each Job
                .allMatch(job ->   // Make sure no job has a chain of looping dependents
                        isJobDependencyChainAValidChain(job, ofSet(job))// Initialise the chain-set with root Job
                );
    }

    private static boolean isJobDependencyChainAValidChain(Job job, HashSet<String> chain) {
        // Returns true if a valid chain -- no loop
        if (job.getDependentOn() != null) {

            if (chain.contains(job.getDependentOn().getJob()))
                return false; // If chain has dependent already, return false.

            // If Chain Does NOT contain the dependent
            chain.add(job.getDependentOn().getJob()); // Add the dependent to the chain
            return isJobDependencyChainAValidChain(job.getDependentOn(), chain); // Repeat
        }
        return true; // null dependent. Not dependent on anything... therefore no loop
    }

    private static HashSet<String> ofSet(Job job) {
        HashSet<String> set = new HashSet<>();
        set.add(job.getJob());
        return set;
    }
}
