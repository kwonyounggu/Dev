package com.sickkids.caliper.view.ttt
{
	//import com.realeyes.osmfplayer.app.CsrPlayer;
	//import com.webmonster.app.model.AppModel;
	
	import flash.events.Event;
	import flash.media.Video;
	
	import mx.core.UIComponent;

	public class VideoContainer extends UIComponent
	{
		private var _video:Video=null;
        //private var model:AppModel = AppModel.getInstance();
        //private var _csrplayer:CsrPlayer=null;
        //private var _pdfplayer:PdfCanvas=null;
        private var _constrainProportion=false;
        
		public function VideoContainer()
		{
			super();
			addEventListener(Event.RESIZE, resizeHandler);			
		}
		/*
		override protected function createChildren():void
        {
            super.createChildren();
            if(_video!=null) model.statusText+="_video is not null...\n";
            else model.statusText+="_video is null...\n";
        }
        override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void 
        {

            super.updateDisplayList(unscaledWidth, unscaledHeight);
            if(_video!=null) model.statusText+="_video is not null in updateDisplayList()...\n";
            else model.statusText+="_video is null in updateDisplayList()...\n";
        }*/
        public function set constrainProportion(cP:Boolean):void
        {
        	_constrainProportion=cP;
        }
		public function get video():Video
		{
			return _video;
		}
        public function set video(video:Video):void
        {
            if (_video != null)
            {                  
                removeAllChilren();
            }

			_video = video;

           	if (_video != null)
            {	
	            _video.width = width;
                _video.height = height;
                if(_constrainProportion)
                	video.scaleX < _video.scaleY ? _video.scaleY = _video.scaleX : _video.scaleX = _video.scaleY;
                addChild(_video);
                
                //the following is to adjust the given video to the size of the UIComponent
               _video.x = (this.width - _video.width) * .5;
               _video.y = (this.height - _video.height) * .5;
            }
        }
		/*
        public function set csrplayer(csrplayer:CsrPlayer):void
        {
            if (_csrplayer != null)
            {    
            	removeAllChilren();
            }			
			_csrplayer = csrplayer;

           	if (_csrplayer != null)
            {	
	            _csrplayer.width = width;
                _csrplayer.height = height;
                addChild(_csrplayer);
            }
        }
        public function set pdfplayer(pdfplayer:PdfCanvas):void
        {
            if (_pdfplayer != null)
            {                  
                removeAllChilren();
            }

			_pdfplayer = pdfplayer;

           	if (_pdfplayer != null)
            {	trace("INFO: set pdfplayer() is called with w="+width+", h="+height+" before");
	            _pdfplayer.width = width;
                _pdfplayer.height = height;
                addChild(_pdfplayer);
                trace("INFO: set pdfplayer() is called with w="+width+", h="+height+" after");
            }
        }*/
        /************************************************************************************************************************************
        * note: numChildren is defined on 'DisplayObjectContainer' so you need to have a class of that type or subclass. 
        * You can't for instance do this for a 'DisplayObject', 
        * but it will work for 'Sprite' : help.adobe.com/en_US/FlashPlatform/reference/actionscript/3/… – Simon_Weaver Mar 20 '11 at 20:58
        * ***********************************************************************************************************************************/
		public function removeAllChilren():void
		{	
			while (this.numChildren > 0) 
			{	//model.statusText+="before removeAllChildren() is called"+this.numChildren+"\n";
				this.removeChildAt(0);
				//model.statusText+="after removeAllChildren() is called"+this.numChildren+"\n";
			}
			_video=null;
			/*
			_csrplayer=null;
			_pdfplayer=null;
			*/
		}
        private function resizeHandler(event:Event):void
        {	trace("resizeHandler width="+width+", height="+height);
            if (_video != null)
            {
               _video.width = width;
               _video.height = height;
               if(_constrainProportion)
               		_video.scaleX < _video.scaleY ? _video.scaleY = _video.scaleX : _video.scaleX = _video.scaleY;
               //the following is to adjust the given video to the size of the UIComponent
               _video.x = (this.width - _video.width) * .5;
               _video.y = (this.height - _video.height) * .5;
            }
			/*
            else if (_csrplayer != null)
            {
               _csrplayer.width = width;
               _csrplayer.height = height;
            }
            else if (_pdfplayer != null)
            {
               _pdfplayer.width = width;
               _pdfplayer.height = height;
            }
			*/
        }
        
        private function scaleProportionalByWidth ( newWidth:Number, proportions:Number ) : void 
        {
		    _video.width = newWidth;
		    _video.height = newWidth / proportions;
		}

		private function scaleProportionalByHeight ( newHeight : Number, proportions:Number ) : void 
		{
		    _video.height = newHeight;
		    _video.width = newHeight * proportions;
		}
		
		/*
		private function scaleVideo():void
		{
				var scale:Number = this.width / this.height > AppModel.V_WIDTH / AppModel.V_HEIGHT ? this.height / AppModel.V_HEIGHT : this.width / AppModel.V_WIDTH;
		    _video.width *= scale;
		    _video.height *= scale;
		    _video.x = (this.width - _video.width) * .5;
		    _video.y = (this.height - _video.height) * .5;
		}
		*/
	
	}
}