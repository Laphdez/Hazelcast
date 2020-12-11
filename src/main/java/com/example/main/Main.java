package com.example.main;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.hazelcast.config.Config;
import com.hazelcast.core.Cluster;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.Member;

public class Main {
	
	public static void main(String[] args){
	    
		// creates a new HazelcastInstance (a new node in a cluster)
		Config config = new Config();
	    HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
	    
	    // returns the Cluster that this HazelcastInstance is part of
	    Cluster cluster = instance.getCluster();
	    // get all devices, that are in the cluster
	    Set<Member> setMembers = cluster.getMembers();

	    // get ExecutorService that works on cluster instance
	    ExecutorService mService = instance.getExecutorService("exec");
	    
	    for (int i = 0; i < setMembers.size(); i++) {
	        // send a task for each member on service of HazelcastInstance
	        final Future<String> future = mService.submit(new ClusterWorkingTask());
	        
	        String response = null;
	        
	        try {
	            // wait for response
	            response = future.get();
	            System.out.println(response);  // each member return: Hello World!
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } catch (ExecutionException e) {
	            e.printStackTrace();
	        }
	    }
	}
	 
}
