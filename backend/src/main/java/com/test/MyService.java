package com.test;

public class MyService implements MyServiceMXBean {
    private String status = "Idle";

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void performOperation() {
        status = "Operation Performed!";
        System.out.println("Operation has been executed.");
    }

    @Override
    public void resetStatus() {
        status = "Idle";
        System.out.println("Status has been reset.");
    }
}
