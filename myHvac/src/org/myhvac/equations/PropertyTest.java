package org.myhvac.equations;

public class PropertyTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		R410aProperties r410a=new R410aProperties();
		
		for(int t=10; t<20; t+=2)
		{
			double c=r410a.fToC((double)t);
			double kjKg=r410a.getHl(c);
			double btuLb=r410a.kjKgToBtuLb(kjKg);
			System.out.println("t["+t+"/"+c+"]="+btuLb+"/"+kjKg);
		}

	}

}
