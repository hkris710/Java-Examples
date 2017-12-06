package com.manager;
import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
	static class Employee{
		Employee manager = null;
		String name = null;
		
		/**
		 * 
		 * @param manager
		 * @param name
		 */
		Employee(Employee manager, String name){
			this.manager = manager;
			this.name  = name;
		}
		
		Employee(String name){
			this.name = name;
		}
		
		public Employee getManager() {
			return manager;
		}
		
		public String getName() {
			return name;
		}
		
		public boolean hasManager() {
			return manager != null;
		}
		
		public void setManager(Employee e) {
			manager = e;
		}
		
	}
	
	public static void main(String args[]) {
		Scanner s  = new Scanner(System.in);
		HashMap<String,Employee> allEmployees = new HashMap<String,Employee>();
		// assume input is valid, no checking done
		String input = s.nextLine();
		String[] splitInput = input.split(",");
		// Process all except last two.
		for (int i = 0; i < splitInput.length-2; i++) {
			// Split manager to employee.
			String[] splitPairs = splitInput[i].split("->");
			//Create manger employee if not already there.
 			if (!allEmployees.containsKey(splitPairs[0])) {
				allEmployees.put(splitPairs[0], new Employee(splitPairs[0]));
			}
			// Update or create employee.
			if (allEmployees.containsKey(splitPairs[1])) {
				allEmployees.get(splitPairs[1]).setManager(allEmployees.get(splitPairs[0]));
			} else {
				allEmployees.put(splitPairs[1], new Employee(allEmployees.get(splitPairs[0]), splitPairs[1]));
			}
		}
		Employee check = allEmployees.get(splitInput[splitInput.length-2]);
		Employee check2 = allEmployees.get(splitInput[splitInput.length-1]);
		// Gather all managers of first employee up the chain. 
		HashSet<String> checkManagers = new HashSet<String>();
		String finalOut = null;
		while(check.hasManager()) {
			checkManagers.add(check.getManager().getName());
			check = check.getManager();
		}
		// Check manager chain of second employee.
		while(finalOut == null) {
			if (checkManagers.contains(check2.getManager().getName())) {
				finalOut = check2.getManager().getName();
			}
			check2 = check2.getManager();
		}
		System.out.println(finalOut);
		s.close();
	}
}
