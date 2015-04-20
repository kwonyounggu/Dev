package com.sickkids.caliper.common;

public class SecureCodeGenerator
{

	private static String validChars ="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    
    private int _IDlength;
    
    public SecureCodeGenerator(int IDlength) 
    {
        _IDlength = IDlength;
    }       
    public String generate() 
    {
        String resultID = "";
        int maxIndex = validChars.length();
        java.util.Random rnd = new java.util.Random();
        
        for ( int i = 0 ; i < _IDlength ; i++ ) 
        {
            int rndPos = Math.abs(rnd.nextInt() % maxIndex);   
            resultID += validChars.charAt(rndPos);
        }
        return resultID;
    }
}
