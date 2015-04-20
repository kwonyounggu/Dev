package com.sickkids.caliper.skins
{
	import flash.display.Bitmap;
	import flash.display.BitmapData;
	import flash.events.Event;
	import flash.events.FocusEvent;
	import flash.events.MouseEvent;
	import flash.events.SoftKeyboardEvent;
	import flash.geom.Matrix;
	import flash.geom.Rectangle;
	import flash.text.StageText;
	
	import mx.core.mx_internal;
	import mx.events.FlexEvent;
	import mx.events.ResizeEvent;
	
	import spark.components.TextInput;
	import spark.components.supportClasses.StyleableTextField;
	import spark.skins.mobile.supportClasses.StageTextSkinBase;
	
	use namespace mx_internal;
	/**
	 *  ActionScript-based skin for TextInput controls in mobile applications that uses a
	 *  StyleableStageText class for the text input. 
	 * 
	 *  @see spark.components.TextInput
	 *  @see spark.components.supportClasses.StyleableStageText
	 * 
	 *  @langversion 3.0
	 *  @playerversion AIR 3.0 
	 *  @productversion Flex 4.6
	 */
	public class ExtendedStageTextInputSkin extends StageTextSkinBase
	{
		//--------------------------------------------------------------------------
		//
		//  Constructor
		//
		//--------------------------------------------------------------------------
		
		/**
		 *  Constructor.
		 *  
		 *  @langversion 3.0
		 *  @playerversion AIR 3.0
		 *  @productversion Flex 4.6
		 */
		public function ExtendedStageTextInputSkin()
		{
			super();
			multiline = false;
			
			//these may not be needed on the device, only emulator
			//addEventListener(FocusEvent.FOCUS_IN,onFocusIn); //unfreeze
			//addEventListener(FocusEvent.FOCUS_OUT, onFocusOut);  //freeze
//			
			//these may not be needed on the emulator, only device
			addEventListener(MouseEvent.CLICK, onTap);
			addEventListener(SoftKeyboardEvent.SOFT_KEYBOARD_ACTIVATE,onSoftKeyboardActivate); //unfreeze
			addEventListener(SoftKeyboardEvent.SOFT_KEYBOARD_DEACTIVATE,onSoftKeyboardDeActivate);//freeze
			 
			addEventListener(ResizeEvent.RESIZE, invalidateScreenshot);
			addEventListener(FlexEvent.CREATION_COMPLETE, invalidateScreenshot);
		}   
		
		protected function onTap(event:MouseEvent):void
		{
			this.releaseScreenshot();	
		}
		
		protected var softKeyboardVisible:Boolean=false;
		protected var screenshotInvalid:Boolean=false;
		
		
		protected function invalidateScreenshot(event:Event):void
		{
			if(!softKeyboardVisible){
				screenshotInvalid=true;
				invalidateProperties();
			}
		}
		
		protected override function commitProperties():void{
			super.commitProperties();
			if(screenshotInvalid){
				screenshotInvalid=false;
				captureScreenshot();
			}
		}
		
		protected function onSoftKeyboardDeActivate(event:SoftKeyboardEvent):void
		{
			softKeyboardVisible=false;
			this.captureScreenshot();	
		}
		protected function onSoftKeyboardActivate(event:SoftKeyboardEvent):void
		{
			softKeyboardVisible=true;
			this.releaseScreenshot();	
		}
		
		protected function onFocusOut(event:FocusEvent):void
		{
			softKeyboardVisible=false;
			this.captureScreenshot();
		}
		
		protected function onFocusIn(event:FocusEvent):void
		{
			softKeyboardVisible=true;
			this.releaseScreenshot();
		}
		
		//--------------------------------------------------------------------------
		//
		//  Variables
		//
		//--------------------------------------------------------------------------
		
		private var _hostComponent:TextInput;
		/** 
		 *  @copy spark.skins.spark.ApplicationSkin#hostComponent
		 */
		public function get hostComponent():TextInput
		{
			return _hostComponent;
		}
		
		/**
		 * @private
		 */
		public function set hostComponent(value:TextInput):void
		{
			_hostComponent = value;
			if(value){
				value.addEventListener("textChanged", invalidateScreenshot);
				//value.addEventListener(Event.ADDED_TO_STAGE,onTextChanged);
			}
		}
		
		// SkinnableComponent will populate
		
		//--------------------------------------------------------------------------
		//
		//  Overridden methods
		//
		//--------------------------------------------------------------------------
		
		/**
		 *  @private
		 */
		override protected function layoutContents(unscaledWidth:Number, 
												   unscaledHeight:Number):void
		{
			// base class handles border position & size
			super.layoutContents(unscaledWidth, unscaledHeight);
			
			// position & size the text
			var paddingLeft:Number = getStyle("paddingLeft");
			var paddingRight:Number = getStyle("paddingRight");
			var paddingTop:Number = getStyle("paddingTop");
			var paddingBottom:Number = getStyle("paddingBottom");
			
			var unscaledTextWidth:Number = Math.max(0, unscaledWidth - paddingLeft - paddingRight);
			var unscaledTextHeight:Number = Math.max(0, unscaledHeight - paddingTop - paddingBottom);
			
			// default vertical positioning is centered
			var textHeight:Number = getElementPreferredHeight(textDisplay);
			var textY:Number = Math.round(0.5 * (unscaledTextHeight - textHeight)) + paddingTop;
			
			if (textDisplay)
			{
				textDisplay.commitStyles();
				setElementSize(textDisplay, unscaledTextWidth, unscaledTextHeight);
				setElementPosition(textDisplay, paddingLeft, textY);
			}
			
			if (promptDisplay)
			{
				if (promptDisplay is StyleableTextField)
					StyleableTextField(promptDisplay).commitStyles();
				
				var promptHeight:Number = getElementPreferredHeight(promptDisplay);
				var promptY:Number = Math.round(0.5 * (unscaledTextHeight - promptHeight)) + paddingTop;
				
				setElementSize(promptDisplay, unscaledTextWidth, promptHeight);
				setElementPosition(promptDisplay, paddingLeft, promptY);
			}
		} 
		//// Additional functions ////
		public var snapshot:Bitmap;
		public function captureScreenshot():void
		{
			if(this.textDisplay.width>0 && this.textDisplay.height>0 && textDisplay && textDisplay.getStageText())
			{
				releaseScreenshot();
				var st:StageText=textDisplay.getStageText();
				var viewPortRectangle:Rectangle = new Rectangle(this.textDisplay.x,this.textDisplay.y,this.textDisplay.width,this.textDisplay.height);
				var bmd:BitmapData = new BitmapData(this.textDisplay.width, this.textDisplay.height);
				try{
					st.drawViewPortToBitmapData(bmd);	
				}catch(e:Error){
					//some times this is throwing 2004, not quite sure why.
				}
				
				bmd.draw(border, new Matrix(1, 0, 0, 1, this.x - viewPortRectangle.x, this.y - viewPortRectangle.y));
				this.snapshot = new Bitmap(bmd);
				this.snapshot.x = viewPortRectangle.x - this.x;
				this.snapshot.y = viewPortRectangle.y - this.y;
				this.addChild(this.snapshot);
				textDisplay.visible = false;
			}
			
		}
		
		public function releaseScreenshot():void
		{
			if (this.snapshot != null && this.contains(this.snapshot) && textDisplay)
			{
				this.removeChild(this.snapshot);
				this.snapshot = null;
				textDisplay.visible = true;
			}
		}
		
		/**
		 *  @private
		 */
		override protected function measure():void
		{
			super.measure();
			measureTextComponent(hostComponent);
		}
	}
}


