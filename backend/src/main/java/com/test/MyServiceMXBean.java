package com.test;

import javax.management.MXBean;

@MXBean
public interface MyServiceMXBean {
    String getStatus();
    void performOperation();
    void resetStatus();
}
