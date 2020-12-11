package com.example.main;

import java.io.Serializable;
import java.util.concurrent.Callable;

public class ClusterWorkingTask implements Callable<String>, Serializable {
    
	@Override
    public String call() throws Exception {
        // send Hello World! as result of execution
        return "Hello World!";
    }
	
}
