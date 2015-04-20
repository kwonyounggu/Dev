package com.sickkids.caliper.skins
{
    import spark.skins.mobile.TabbedViewNavigatorTabBarSkin;
    
    public class MyTabBarSkin extends TabbedViewNavigatorTabBarSkin
    {
        public function MyTabBarSkin()
        {
            super();
        }
        
        override protected function drawBackground(unscaledWidth:Number, unscaledHeight:Number):void
        {
            // backgroundAlpha style is not supported by ButtonBar
            // TabbedViewNavigatorSkin sets a hard-coded value to support
            // overlayControls
            var backgroundAlphaValue:* = getStyle("backgroundAlpha");
            var backgroundAlpha:Number = (backgroundAlphaValue === undefined)
                ? 1 : getStyle("backgroundAlpha");
            
            graphics.beginFill(0x00FF00, backgroundAlpha);
            graphics.drawRect(0, 0, unscaledWidth, unscaledHeight);
            graphics.endFill();
        }
    }
}