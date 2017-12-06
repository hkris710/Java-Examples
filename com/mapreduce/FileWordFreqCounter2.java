package com.mapreduce;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.ArrayList;

public class FileWordFreqCounter2 {
	
	static class Value {
		
		int value = 0;
		
		public void increment() {
			value++;
		}
		
		public void add(Value other) {
			value += other.value;
		}
		
		public int getValue() {
			return value;
		}
	}
	
	static class WordCountRunnable implements Runnable{
		
		HashMap<String, Value> wordCountMap = new HashMap<String, Value>();
		String filePath;
		
		public WordCountRunnable(String filePath) {
			this.filePath = filePath;
		}
		
		public void run() {
			System.out.println("*** Processing file: " +this.filePath);
	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            String line = null;
	            while ((line = br.readLine()) != null) {
	               String[] words = line.split(" ");
	               for (int i = 0; i < words.length; i++) {
	            	   String word = words[i];//.toLowerCase();
	            	   //word = word.replaceAll("[^a-z]" , "");
	            	   if (wordCountMap.containsKey(word))
	            		   wordCountMap.get(word).increment();
	            	   else
	            		   wordCountMap.put(word, new Value());
	               }
	            }
	        }
	        catch(Exception ex) {
	            ex.printStackTrace();
	        }
		}
		
		public HashMap<String, Value> getMap(){
			return wordCountMap;
		}
	}

    public static void main(String[] args) {
    	ArrayList<WordCountRunnable> listOfR = new ArrayList<WordCountRunnable>();
    	ArrayList<Thread> listOfT = new ArrayList<Thread>();
        for(String filePath : args) {
        	WordCountRunnable wcr = new WordCountRunnable(filePath);
            listOfR.add(wcr);
            Thread t = new Thread(wcr);
        	t.start();
        	listOfT.add(t);
        }
        
        for (Thread t : listOfT) {
        	try {
        		t.join();  // Blocks the main thread.
        	} catch (InterruptedException e) {
        		e.printStackTrace();
        	}
        }
        
    	HashMap<String, Value> totalMap = combineMaps(listOfR);
    	int max = 0;
    	String stringOfMax = "";
    	for (String s : totalMap.keySet()) {
    		if (totalMap.get(s).getValue() > max) {
    			max = totalMap.get(s).getValue();
    			stringOfMax = s;
    		}
    	}
    	
    	System.out.printf("The most frequent word is: %s with a frequency of %d", stringOfMax, totalMap.get(stringOfMax).getValue());

    }
    
    public static HashMap<String, Value> combineMaps(ArrayList<WordCountRunnable> arr){
    	HashMap<String, Value> totalMap = new HashMap<String, Value>();
    	for (WordCountRunnable wcr : arr) {
    		for (String s : wcr.getMap().keySet()) {
    			Value other = wcr.getMap().get(s);
    			if(totalMap.containsKey(s)) 
    				totalMap.get(s).add(other);
    			else
    				totalMap.put(s, other);
    		}
    	}
    	return totalMap;
    }    
}

