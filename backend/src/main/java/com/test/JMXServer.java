package com.test;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class JMXServer {
    public static void main(String[] args) {
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            MyService myService = new MyService();
            ObjectName objectName = new ObjectName("com.example:type=MyService");

            mBeanServer.registerMBean(myService, objectName);

            System.out.println("MXBean registered successfully. Keep the application running to access JMX.");

            Thread.sleep(Long.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
