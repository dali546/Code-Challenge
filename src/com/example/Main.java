package com.example;

public class Main {

    public static void main(String[] args) {
        // Run Jobs
        String seq = runJobs(args);

        // Print Response
        System.out.println(seq);
    }

    public static String runJobs(String[] jobs) {
        StringBuilder seq = new StringBuilder();

        for (String job : jobs) {
            seq.append(runJob(job));
        }

        return jobs.length == 0 ? "" : seq.toString();
    }

    private static String runJob(String job) {
        return job;
    }

}
