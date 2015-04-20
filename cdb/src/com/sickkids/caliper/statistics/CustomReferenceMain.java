package com.sickkids.caliper.statistics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.StatUtils;

import edu.emory.mathcs.backport.java.util.Collections;


public class CustomReferenceMain
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		TDistribution t=new TDistribution(3);//n=sample#-1
		System.out.println("value="+t.getDegreesOfFreedom()+" "+t.inverseCumulativeProbability(0.975));//0.975=(1-((1-0.95)/2)
		//n=3 in 0.975=>3.18244
		TDistribution t2=new TDistribution(4);
		System.out.println("value="+t2.getDegreesOfFreedom()+" "+t2.inverseCumulativeProbability(0.975));
		/*
		double[] data={0.2, 0.1, 0.9, 0.7, 0.3, 0.4, 1.2, 1.7, 1.8, 1.9, -2.0, -1.5, 2.1, 3.2, 0.77, 0.89,
					   0.2, 0.1, 0.9, 0.7, 0.3, 0.4, 1.2, 1.7, 1.8, 1.9, -2.0, -1.5, 2.1, 3.2, 0.77, 0.89,
					   0.2, 0.1, 0.9, 0.7, 0.3, 0.4, 1.2, 1.7, 1.8, 1.9, -2.0, -1.5, 2.1, 3.2, 0.77, 0.89,
					   0.2, 0.1, 0.9, 0.7, 0.3, 0.4, 1.2, 1.7, 1.8, 1.9, -2.0, -1.5, 2.1, 3.2, 0.77, 0.89,
					   0.2, 0.1, 0.9, 0.7, 0.3, 0.4, 1.2, 1.7, 1.8, 1.9, -2.0, -1.5, 2.1, 3.2, 0.77, 0.89,
						};
		*/
		double[] data=
			{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 7, 8, 8, 8, 9, 9, 9, 9, 9, 10, 10, 11, 12, 12, 13, 13, 13, 13, 13, 13, 13, 13, 14, 16, 16, 16, 17, 17, 17, 18, 18, 18.5, 19, 19, 19, 20, 20, 20, 20, 21, 21, 21, 22, 22, 23, 23, 23, 24, 24, 24, 25, 25, 25, 27, 28, 30, 31, 31, 32, 34, 34, 40, 40, 41, 42, 53, 54, 54};
		//for(double d : data) System.out.println("Number = " + d);
		//System.out.println("\n\n");
		//Arrays.sort(data);
		//for(double d : data) System.out.println("Number = " + d);
		
		
		/*
		System.out.println("data="+data);
		System.out.println("list="+list);
		*/
		//System.out.println("median="+StatUtils.percentile(data, 50));
		
		long start = System.nanoTime();
		
		CStatistics stat=new CStatistics();
		double[] dataTemp=new double[data.length], wi=new double[data.length];
		System.out.println("\n\nFinal Value="+stat.robustMethod(data, 0.95, -100000, dataTemp, wi));
		/*
		try
		{
			stat.calculateCIs(data, 25);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		System.out.println("----------------1--------------");
		double[] temp=stat.withoutOutliers(data);
		//System.err.println("remove outlier 1="+temp.length);
		stat.printArray(data);
		System.out.println("----------------2--------------\n\n");
		stat.printArray(temp);
		
		temp=stat.withoutOutliers(temp);
		System.out.println("----------------3--------------\n\n");
		stat.printArray(temp);
		
		temp=stat.logTransformation(temp);
		System.out.println("----------------4--------------\n\n");
		stat.printArray(temp);
		
		
		
		long end = System.nanoTime();
		long microseconds = (end - start) / 1000;
		
		System.out.println("Time taken (microseconds)="+microseconds);
	}

}
