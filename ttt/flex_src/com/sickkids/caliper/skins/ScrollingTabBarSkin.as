package com.sickkids.caliper.skins
{
	import mx.core.InteractionMode;
	
	import spark.components.Scroller;
	import spark.layouts.HorizontalLayout;
	import spark.skins.mobile.TabbedViewNavigatorTabBarSkin;

	/**
	 *  A custom TabbedViewNavigatorTabBarSkin skin that enables horizontal scrolling in the tab bar.
	 */
	public class ScrollingTabBarSkin extends TabbedViewNavigatorTabBarSkin
	{
		public var scroller:Scroller;
		
		/**
		 *  Override createChildren() to create a Scroller and add the DataGroup
		 *  as its viewport.
		 */
		override protected function createChildren():void
		{
			super.createChildren();
			
			// use a standard HorizontalLayout instead of a specialized layout
			var tabLayout:HorizontalLayout = new HorizontalLayout();
			tabLayout.useVirtualLayout = false;
			tabLayout.gap = 0;
			
			dataGroup.layout = tabLayout;
			
			scroller = new Scroller();
			scroller.setStyle('interactionMode', InteractionMode.TOUCH);
			scroller.viewport = dataGroup;
			addChild(scroller);
		}
		
		/** 
		 * Size and position the Scroller
		 */
		override protected function layoutContents(unscaledWidth:Number, unscaledHeight:Number):void
		{
			setElementPosition(scroller, 0, 0);
			setElementSize(scroller, unscaledWidth, unscaledHeight);
		}
	}
}