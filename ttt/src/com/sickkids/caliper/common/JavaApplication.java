package com.sickkids.caliper.common;

import java.sql.Timestamp;
import java.util.Calendar;


public class JavaApplication
{

	public static boolean doWork(String a, StringBuilder  out)
	{
		out.append("from inside");
		return true;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		/*
		double oneMonth=1 * 24 * 60 * 60 * 1000 * 30.0;
		// TODO Auto-generated method stub
		System.out.println(FileCurrentStatus.PENDING_REVIEW.name()+" "+FileCurrentStatus.PENDING_REVIEW.toString());
		
		long offset = Timestamp.valueOf("2012-01-01 00:00:00").getTime();
		long end = (long)(offset+oneMonth);
		long diff = end - offset + 1;
		//Timestamp rand = new Timestamp(offset + (long)(Math.random() * diff));
		*/
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DATE, 1); //For tomorrow
		
		double oneMonth=1 * 24 * 60 * 60 * 1000 * 30.0;
		//Timestamp rand = new Timestamp(cal1.getTimeInMillis() + (long)(Math.random() * oneMonth));
		//for(int i=0;i<300;i++)
			//System.out.println("offset="+new Timestamp(cal1.getTimeInMillis())+", start date="+new Timestamp(cal1.getTimeInMillis() + (long)(Math.random() * oneMonth)));
			//System.out.println(SPIREServletHelper.getSPIREStartDate());
		
		/*
		//SpireScheduledTask st=new SpireScheduledTask();
		
		String a="this is a";
		StringBuilder out=new StringBuilder("");
		System.out.println("doWork("+a+", "+out+")");
		
		System.out.println(doWork(a,out)+" out="+out);
		
		*/
		/*
		for(int i=4; i<=18;i+=2)
			System.out.println("full reports="+i+", random list="+Randomizer.randomNumbers(i));
		
		String file_name="../../html/newsletter.htm";
		System.out.println("file="+"\n\n");
		try
		{
			System.out.println(SPIREServletHelper.readHtmlFile(file_name));
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

}
