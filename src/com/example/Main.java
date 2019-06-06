package com.example;

public class Main {

    public static void main(String[] args) {
        // Run Jobs
        String seq = runJobs(args);

        // Print Response
        System.out.println(seq);
    }

    public static String runJobs(String[] jobs) {
        String seq = "Sequence: ";

        return jobs.length == 0 ? "" : seq;
    }

}
