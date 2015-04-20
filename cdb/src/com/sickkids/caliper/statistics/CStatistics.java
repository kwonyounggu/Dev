package com.sickkids.caliper.statistics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.moment.Skewness;
import org.apache.commons.math3.stat.inference.TestUtils;

import com.sickkids.caliper.dao.CustomizedRefIntervalInfoBean;
import com.sickkids.caliper.exceptions.DAOException;

import edu.emory.mathcs.backport.java.util.Arrays;

public class CStatistics
{
	public static int LOOPLIMT=500;//Change it to 500 later
	private Log log = LogFactory.getLog(CStatistics.class);
	
	public RefInterval robustMethodWithComments(double[] data, double refConf)
	{
		System.out.println("before sorting:");
		printArray(data);
		Arrays.sort(data);
		System.out.println("after sorting:");
		printArray(data);
		int n=data.length;
		double median=StatUtils.percentile(data, 50);
		System.out.println("median:"+median);
		double tbi=median;
		double tbiNew=10000;
		float c=3.7f;
		double[] dataTemp=new double[data.length];
		for(int i=0;i<data.length;i++) dataTemp[i]=Math.abs(data[i]-median);
		System.out.println("before sorting dataTemp:");
		printArray(dataTemp);
		Arrays.sort(dataTemp);
		System.out.println("after sorting dataTemp:");
		printArray(dataTemp);
		double mad=StatUtils.percentile(dataTemp, 50);
		System.out.println("mad:"+mad);
		mad/=0.6745;
		System.out.println("new mad:"+mad);
		double[] ui=dataTemp, wi=new double[data.length];
		//System.out.println("with the ui pointer=>");
		//printArray(ui);
		
		long count=0L;
		double numerator=0.0d;
		double denominator=0.0d;
		
		do
		{	count++;
			numerator=0.0d;
			denominator=0.0d;
			
			for(int i=0;i<ui.length;i++) 
			{
				ui[i]=(data[i]-tbi)/(c*mad);
				if(ui[i]<-1 || ui[i]>1) ui[i]=1;
				wi[i]=(1-ui[i]*ui[i])*(1-ui[i]*ui[i]);
				numerator+=(data[i]*wi[i]);
				denominator+=wi[i];
			}
			tbiNew=numerator/denominator;
			if(Math.abs(tbiNew-tbi)<0.00001) break;
			tbi=tbiNew;			
		} while(true);
		System.out.println("tbi:"+tbi+" after loop="+count);
		
		double sbi205=calculateS(data, ui, 205.6, median, mad);
		double sbi37=calculateS(data, ui, 3.7, median, mad);
		sbi37=calculateS(data, ui, 3.7, tbi, sbi37);
		
		TDistribution t=new TDistribution(data.length-1);//n=sample#-1
		double margin=t.inverseCumulativeProbability(0.975)*Math.sqrt(sbi205*sbi205 + sbi37*sbi37);
		RefInterval refInterval=new RefInterval();
		refInterval.setLowerLimit(tbi-margin);
		refInterval.setUpperLimit(tbi+margin);
		return refInterval;
	}
	public RefInterval robustMethod(double[] data, double refConf, double tStatistics, double[] dataTemp, double[] wi)
	{
		Arrays.sort(data);
		double median=StatUtils.percentile(data, 50);
		
		double tbi=median;
		double tbiNew=10000;
		float c=3.7f;

		for(int i=0;i<data.length;i++) dataTemp[i]=Math.abs(data[i]-median);
		
		Arrays.sort(dataTemp);
		double mad=StatUtils.percentile(dataTemp, 50);

		mad/=0.6745;

		double[] ui=dataTemp;
		
		double numerator=0.0d;
		double denominator=0.0d;
		
		int count=0;
		//do
		//{	
		//The following only loops 1000 times in order to prevent crashing from infinite loop and creating a memory leak.
		for(int z=0;z<1000;z++)
		{
			numerator=0.0d;
			denominator=0.0d;
			
			for(int i=0;i<ui.length;i++) 
			{
				ui[i]=(data[i]-tbi)/(c*mad);
				if(ui[i]<-1 || ui[i]>1) ui[i]=1;
				wi[i]=(1-ui[i]*ui[i])*(1-ui[i]*ui[i]);
				numerator+=(data[i]*wi[i]);
				denominator+=wi[i];
			}
			tbiNew=numerator/denominator;
			if(count>10000 || Math.abs(tbiNew-tbi)<0.000001) break;
			tbi=tbiNew;		
			
			if(++count==10000) log.info("current loop counts on "+count);
		}
		//} while(true);

		double sbi205=calculateS(data, ui, 205.6, median, mad);
		double sbi37=calculateS(data, ui, 3.7, median, mad);
		double st37=calculateSt(data, ui, 3.7, tbi, sbi37);

		
		if(tStatistics<=-100000)
		{
			TDistribution t=new TDistribution(data.length-1);//n=sample#-1
			tStatistics=t.inverseCumulativeProbability(0.975);
			
			//log.info("tStatistics value is "+tStatistics+" in robustMethod of CStatistics.java");
		}
		double margin=tStatistics*Math.sqrt(sbi205*sbi205 + st37*st37);

		RefInterval refInterval=new RefInterval();
		refInterval.setLowerLimit(tbi-margin);
		refInterval.setUpperLimit(tbi+margin);
		
		return refInterval;
	}
	public double calculateS(double[] data, double[] ui, double c, double m, double e)
	{
		double numerator=0.0d;
		double denominator=0.0d;
		
		for(int i=0;i<ui.length;i++)
		{
			ui[i]=(data[i]-m)/(c*e);
			if(ui[i]>-1 && ui[i]<1)
			{
				numerator+=Math.pow((1-ui[i]*ui[i]), 4)*ui[i]*ui[i];
				denominator+=(1-ui[i]*ui[i])*(1-5*ui[i]*ui[i]);
			}
		}
		
		return c*e*Math.sqrt((ui.length*numerator)/(denominator*Math.max(1, (-1+denominator))));
	}
	public double calculateSt(double[] data, double[] ui, double c, double m, double e)
	{
		double numerator=0.0d;
		double denominator=0.0d;
		
		for(int i=0;i<ui.length;i++)
		{
			ui[i]=(data[i]-m)/(c*e);
			if(ui[i]>-1 && ui[i]<1)
			{
				numerator+=Math.pow((1-ui[i]*ui[i]), 4)*ui[i]*ui[i];
				denominator+=(1-ui[i]*ui[i])*(1-5*ui[i]*ui[i]);
			}
		}
		
		return c*e*Math.sqrt((numerator)/(denominator*Math.max(1, (-1+denominator))));
	}
	public void printArray(double[] data)
	{
		for(int i=0;i<data.length;i++)
			System.out.println("data["+i+"]="+data[i]);
	}
	public CustomizedRefIntervalInfoBean calculateCIs(double[] data, int randLimit) throws Exception
	{
		double[] randomData=new double[randLimit], dataTemp=new double[randLimit], wi=new double[randLimit];
		if(data.length<randLimit) throw new Exception("data length is shorter than randLimit, "+randLimit);
		
		double[] upperData=new double[LOOPLIMT];
		double[] lowerData=new double[LOOPLIMT];
		
		RefInterval ref=null;
		CustomizedRefIntervalInfoBean cb=null;
		try
		{
			TDistribution t=new TDistribution(randLimit-1);
			double tStatistics=t.inverseCumulativeProbability(0.975);
			RandomDataGenerator rg=new RandomDataGenerator();
			//long memoryNow=0L;
			for(int i=0;i<LOOPLIMT;i++)
			{
				for(int j=0;j<randLimit;j++) randomData[j]=data[rg.nextInt(0, data.length-1)];
				//for(int j=0;j<randLimit;j++) randomData[j]=data[randInt(0, data.length-1)];
				ref=robustMethod(randomData, 0.95, tStatistics, dataTemp, wi);
				upperData[i]=ref.getUpperLimit();
				lowerData[i]=ref.getLowerLimit();
				//System.gc();//remove ui and wi from the memory
				//memoryNow = Runtime.getRuntime().freeMemory();
				//log.info("loop = "+i+" with the remaining memory, "+memoryNow);
			}
			
			Arrays.sort(upperData);
			Arrays.sort(lowerData);
			
			cb=new CustomizedRefIntervalInfoBean();
			cb.setLowerCI5(StatUtils.percentile(lowerData, 5));
			cb.setLowerCI95(StatUtils.percentile(lowerData, 95));
			cb.setUpperCI5(StatUtils.percentile(upperData, 5));
			cb.setUpperCI95(StatUtils.percentile(upperData, 95));

		}
		catch(Exception e)
		{
			log.error(e);
		}
		return cb;
	}
	/*********** Log Transformation *************/
	public double[] logTransformation(double[] data)
	{
		for(int i=0;i<data.length;i++)
			data[i]=Math.log10(data[i]);
		
		return data;
	}
	/*********** Remove Outliers *************/
	public double[] withoutOutliers(double[] data)
	{
		Arrays.sort(data);

		//double q1=getQ1(data);
		//double q3=getQ3(data);
		
		double q1=StatUtils.percentile(data, 25);
		double q3=StatUtils.percentile(data, 75);

		double interQuartileRange=q3-q1;
		double upperOutlierThreshold=q3+(interQuartileRange*1.5);
		double lowerOutlierThreshold=q1-(interQuartileRange*1.5);
		
		double upperFaroutThreshold=q3+(interQuartileRange*2.0);
		double lowerFaroutThreshold=q1-(interQuartileRange*2.0);
		
		double minRegularValue=Double.POSITIVE_INFINITY;
		double maxRegularValue=Double.NEGATIVE_INFINITY;
		double minOutlier=Double.POSITIVE_INFINITY;
		double maxOutlier=Double.NEGATIVE_INFINITY;
		
		List<Double> outliers=new ArrayList<Double>();
		List<Double> remainingData=new ArrayList<Double>();
		
		for(int i=0;i<data.length;i++)
		{
			if(data[i]>upperOutlierThreshold)
			{
				outliers.add(Double.valueOf(data[i]));
				if(data[i]>maxOutlier && data[i]<=upperFaroutThreshold) maxOutlier=data[i];
			}
			else if(data[i]<lowerOutlierThreshold)
			{
				outliers.add(Double.valueOf(data[i]));
				if(data[i]<minOutlier && data[i]>=lowerFaroutThreshold) minOutlier=data[i];
			}
			else
			{
				minRegularValue=Math.min(minRegularValue, data[i]);
				maxRegularValue=Math.max(maxRegularValue, data[i]);
				
				remainingData.add(Double.valueOf(data[i]));
			}
			minOutlier=Math.min(minOutlier, minRegularValue);
			maxOutlier=Math.max(maxOutlier, maxRegularValue);
					
		}
		
		double[] retData=new double[remainingData.size()];
		for(int i=0;i<remainingData.size();i++) 
		{
			retData[i]=remainingData.get(i).doubleValue();
			if(retData[i]<=0) log.error("There exists a value less than equal to zero whose data effects BOX-COX transformation.");
		}

		return retData;
	}
	//public double pValue(double[] data)
	//{
	//	Collection<double[]> c=new ArrayList<double[]>();
	//	c.add(data);
	//	return TestUtils.oneWayAnovaPValue(c);
	//}
	public double pValue(Collection<double[]> c)
	{
		return TestUtils.oneWayAnovaPValue(c);
	}
	public double getQ1(double[] data)
	{
		double result=0.0d;
		if(data.length>0)
		{
			if(data.length%2==1)
				if(data.length>1) result=StatUtils.percentile(data, 0, data.length/2, 50);
				else result=StatUtils.percentile(data, 0, 0, 50);
			else
				result=StatUtils.percentile(data, 0, data.length/2-1, 50);
		}
		
		return result;
	}
	public double getQ3(double[] data)
	{
		double result=0.0d;
		if(data.length>0)
		{
			if(data.length%2==1)
				if(data.length>1) result=StatUtils.percentile(data, data.length/2, data.length-1, 50);
				else result=StatUtils.percentile(data, 0, 0, 50);
			else
				result=StatUtils.percentile(data, data.length/2, data.length-1, 50);
		}
		
		return result;
	}
	/**
	 * Returns a pseudo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimum value
	 * @param max Maximum value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public int randInt(int min, int max) 
	{

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    //System.out.println("randomNum="+randomNum);
	    return randomNum;
	}
	public double[] convertDd(Double[] d)
	{
		double[] data=new double[d.length];
		for(int i=0;i<d.length;i++) data[i]=d[i].doubleValue();
		
		return data;
	}
	public Double[] convert_dD(double[] d)
	{
		Double[] data=new Double[d.length];
		for(int i=0;i<d.length;i++) data[i]=Double.valueOf(d[i]);
		
		return data;
	}
	public CustomizedRefIntervalInfoBean nonParametric(double[] data, List<RankBean> rankTable, int originalSize)
	{
		log.info("nonParametric("+data.length+", "+rankTable.size()+") is called");
		
		CustomizedRefIntervalInfoBean cb=new CustomizedRefIntervalInfoBean();
		cb.setSamples(originalSize);
		cb.setSamplesWithLT(data.length);
		
		Skewness pValue=new Skewness();
		Arrays.sort(data);
			
		int a=(int) Math.ceil(cb.getSamplesWithLT()*0.025);
		int b=(int) Math.ceil(cb.getSamplesWithLT()*0.975);
		
		log.info("a="+a+", b="+b);
		cb.setLowerLimit(data[a]);
		cb.setUpperLimit(data[b]);
		
		for(RankBean rb : rankTable)
			if(rb.inBetween(cb.getSamplesWithLT()))
			{	
				log.info("data.length="+data.length+" size="+cb.getSamplesWithLT()+" lower="+rb.getLower()+", upper="+rb.getUpper()+" ciUpper="+(cb.getSamplesWithLT()-rb.getUpper())+" to "+(cb.getSamplesWithLT()-rb.getLower()));
				cb.setLowerCI5(data[rb.getLower()]);
				cb.setLowerCI95(data[rb.getUpper()]);
				cb.setUpperCI5(data[cb.getSamplesWithLT()-rb.getUpper()]);
				cb.setUpperCI95(data[cb.getSamplesWithLT()-rb.getLower()]);
				
				//here all mode (%) is used to protect out of index bound, it can be removed but crashed due to the earlier formula given from Yungi
				//The 2nd one give from her still gives a crash (eg: data.length=171 size=171 lower=1, upper=9 ciUpper=163 to 171)
				//cb.setLowerCI5(data[rb.getLower()%data.length]);//here all mode (%) is used to protect out of index bound, it can be removed but crashed due to the earlier formula given from Yungi
				//cb.setLowerCI95(data[rb.getUpper()%data.length]);
				//cb.setUpperCI5(data[(cb.getSamplesWithLT()+1-rb.getUpper())%data.length]);
				//cb.setUpperCI95(data[(cb.getSamplesWithLT()+1-rb.getLower())%data.length]);
				
				break;
			}
		
		cb.setpValue1(pValue.evaluate(data));
		cb.setpValue2(cb.getpValue1());//no transformation so values are equal
		return cb;
	}
	
	public CustomizedRefIntervalInfoBean robustBoxCox(double[] data, int originalSize) throws DAOException
	{
		CustomizedRefIntervalInfoBean cb=null;
		Skewness pValue=new Skewness();
		try
		{
			RefInterval ref=robustMethod(data, 0.95, -100000, new double[data.length], new double[data.length]);
			cb=calculateCIs(data, 25);//25: random number of samples
			cb.setSamplesWithLT(data.length);
			cb.setSamples(originalSize);
			cb.setLowerLimit(ref.getLowerLimit());
			cb.setUpperLimit(ref.getUpperLimit());
			
			cb.setpValue1(pValue.evaluate(data));	//skewness before transformation			
			
			BoxCox bc=new BoxCox(convert_dD(data));
			Double[] dataTransformed=bc.getTransformedData();
			data=convertDd(dataTransformed);
			
			cb.setpValue2(pValue.evaluate(data));	//skewness after transformation	
			ref=robustMethod(data, 0.95, -100000, new double[data.length], new double[data.length]);
			
			cb.setLowerLimitWithLT(bc.reverseTransform(ref.getLowerLimit()));//by BoxCox
			cb.setUpperLimitWithLT(bc.reverseTransform(ref.getUpperLimit()));//by BoxCox
			
			CustomizedRefIntervalInfoBean cb2=calculateCIs(data, 25);
			
			cb.setLowerCI5WithLT(bc.reverseTransform(cb2.getLowerCI5()));//by BoxCox
			cb.setLowerCI95WithLT(bc.reverseTransform(cb2.getLowerCI95()));//by BoxCox
			cb.setUpperCI5WithLT(bc.reverseTransform(cb2.getUpperCI5()));//by BoxCox
			cb.setUpperCI95WithLT(bc.reverseTransform(cb2.getUpperCI95()));	//by BoxCox	
		}
		catch(Exception e)
		{
			log.fatal(e);
			throw new DAOException(e);
		}
		
		return cb;
	}
}
