package com.sickkids.caliper.effects
{
import spark.effects.interpolation.NumberInterpolator;

public class IntegerInterpolator extends NumberInterpolator
{
    private static var theInstance:IntegerInterpolator;

    
    public function IntegerInterpolator()
    {
        super();
    }
    
    public static function getInstance():IntegerInterpolator
    {
        if (!theInstance)
            theInstance = new IntegerInterpolator();
        return theInstance;
    }
    
    override public function interpolate(fraction:Number, startValue:Object, endValue:Object):Object
    {
        return Math.round(super.interpolate(fraction, startValue, endValue) as Number);
    }
}
}