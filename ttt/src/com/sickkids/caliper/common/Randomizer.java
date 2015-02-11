package com.sickkids.caliper.common;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Randomizer
{
	public static synchronized int generate(int min,int max)
    {
		return min + (int)(Math.random() * ((max - min) + 1));
    }
	public static synchronized TreeSet<Integer> randomNumbers(int how_many_numbers)
	{
		Set<Integer> set = new HashSet<Integer>(how_many_numbers);
	
		//The following statement is to force the first entry always in the full entry.
		//Reqeusted by Dr. Chris on Dec-26-2014
		set.add(Integer.valueOf(1));
		
	    while(set.size()< how_many_numbers) 
	    {
	        while (set.add(generate(1, 18)) != true);
	    }
	    assert set.size() == how_many_numbers;
	    //System.out.println(set+" in Randomizer.java");
	    
	    return new TreeSet(set);
	}
}
