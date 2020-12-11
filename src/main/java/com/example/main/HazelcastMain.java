package com.example.main;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class HazelcastMain {

	public static void main(String[] args) {
		Config config = new Config();
		HazelcastInstance hz = Hazelcast.newHazelcastInstance(config);
		
		IMap<String, String> map = hz.getMap("customers");
		String value = map.get("SomeKey");
		System.out.println("value = " + value);
		
		IMap<Integer, String> mapCustomers = hz.getMap("customers");
		mapCustomers.put(0, "Mikaelo");
		mapCustomers.put(1, "Joe");
		mapCustomers.put(2, "Morgan");
		for(int i = 0; i < mapCustomers.size(); i++) {
			String c = mapCustomers.get(i);
			System.out.println("Key = " + i + " value = " + c);
		}
		System.out.println("Size: "  + mapCustomers.size());
		//System.out.println();
		
		Map<String, String> employees = hz.getMap("employees");
		 if(employees.containsKey("1"))
		    {
			    employees.put("6", "emp6");
		
		    }else
	    employees.put("1", "emp1");
	    employees.put("2", "emp2");
	    employees.put("3", "emp3");
	    employees.put("4", "emp4");
	    employees.put("5", "emp5");
	    
	    System.out.println("Total number of employees " + employees.size());
	    
	    Set<String> persons = hz.getSet("persons");
	    persons.addAll(employees.values());
	    persons.add("tom");
	    persons.add("john");
	    persons.add("jobin");
	    
	    List<String> countries = hz.getList("persons");
	    persons.addAll(employees.keySet());
	    persons.add("tom");
	    persons.add("john");
	    persons.add("tom"); // duplicate entry
	    
	    BlockingQueue<String> arrivals = hz.getQueue("arrivals");
	    while (true) {
	    	String arrival = null;
			try {
				arrival = arrivals.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    System.err.println("New arrival from: " + arrival);
	     }
	    
	}

}
