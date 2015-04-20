package org.myhvac.equations;

public class R410aProperties
{
	private double A2=Double.parseDouble("-0.172178E+00");
	private double A3=Double.parseDouble(" 2.381558E-04");
	private double A4=Double.parseDouble("-4.329207E-07");
	private double A5=Double.parseDouble("-6.241072E-10");
	
	private double B2=Double.parseDouble(" 1.646288E-04");
	private double B3=Double.parseDouble("-1.462803E-08");
	private double B4=Double.parseDouble("0.0");
	private double B5=Double.parseDouble(" 1.380469E-12");
	
	private double C2=Double.parseDouble("-6.293665E+00");
	private double C3=Double.parseDouble(" 1.532461E-02");
	private double C4=Double.parseDouble("0.0");
	private double C5=Double.parseDouble(" 1.6041250E-07");
	
	private double k=5.75;
	private double R=Double.parseDouble(" 1.145502E-01");
	private double b=Double.parseDouble(" 4.355134E-04");
	private double V=0.00205;// m^3/kg
			
	private double Pc=4926.06;// kPa;
	private double Tc=345.28;// K;
	private double Dc=488.90;// kg/m3;
	private double Wt=72.584;
	
	private double Al=Double.parseDouble(" 2.211749E+02");
	private double Bl=Double.parseDouble("-5.149668E+02");
	private double Cl=Double.parseDouble("-6.316250E+02");
	private double Dl=Double.parseDouble("-2.622749E+02");
	private double El=Double.parseDouble(" 1.052000E+03");
	private double Fl=Double.parseDouble(" 1.596000E+03");
	private double X0l=Double.parseDouble("5.541498E-01");
	//private double X0l=0.2086902;
	
	private double Ag=Double.parseDouble(" 0.15305823E+00");
	private double Bg=Double.parseDouble(" 2.1153533E-03");
	private double Cg=Double.parseDouble("-9.848184E-07");
	private double Dg=Double.parseDouble(" 6.4937810E-11");
	
	
	public double cToKelvin(double c)
	{
		return c+274.15;
	}
	public double kjKgToBtuLb(double kjKg)
	{
		return (kjKg -  141.1) *  0.429923;// 0.429923
	}
	public double cToF(double c)
	{
		return c*1.8 + 32;
	}
	public double fToC(double f)
	{
		return (f-32)/1.8;
	}
	public double XinHl(double TempInK)
	{
		double Tr=TempInK/Tc;
		return Math.pow((1-Tr), (double)1/3) - X0l;
	}
	public double getHl(double tempInC)
	{
		System.out.print("getHl("+tempInC+"), ");
		double Tk=cToKelvin(tempInC);
		double X=XinHl(Tk);
		return Al + Bl*X + Cl*X*X + Dl*X*X*X + El*X*X*X*X +Fl*X*X*X*X*X;
	}
	public double getP2(double Tk, double Ai, double Bi, double Ci, int i)
	{
		return (Ai+Bi*Tk+Ci*Math.exp(-k*Tk/Tc))/Math.pow((V-b), i);
	}
	public double getP1(double Tk)
	{
		return (R*Tk)/(V-b);
	}
	public double getP(double Tk)
	{
		return getP1(Tk)+getP2(Tk, A2, B2, C2, 2)+getP2(Tk, A3, B3, C3, 3)+getP2(Tk, A4, B4, C4, 4)+getP2(Tk, A5, B5, C5, 5);
	}
	//P?
	public double getHg(double tempInC)
	{
		System.out.print("getHl("+tempInC+"), ");
		double Tk=cToKelvin(tempInC);
		//Ag*Tk+(Bg*Tk*Tk/2)+(Cg*Tk*Tk*Tk/3)+(Dg*Tk*Tk*Tk*Tk/4)+getP()
		return 0;
	}
}
