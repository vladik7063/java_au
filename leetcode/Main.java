package com.company;

import java.io.IOException;

public class Main {
    public static void main(final String[] args) throws Throwable {
        try {
            LeetCode File = new LeetCode(args[0]);
            File.writeToMDFile(args[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(String.format("Invalid number of parameters\n%d of 2 arguments recieved", args.length));
        }
    }
}