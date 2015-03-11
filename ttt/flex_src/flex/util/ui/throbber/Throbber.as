package flex.util.ui.throbber {
	import flash.events.Event;
	import flash.events.TimerEvent;
	import flash.utils.Timer;
	import flash.utils.getTimer;

	import mx.events.FlexEvent;

	import spark.components.supportClasses.SkinnableComponent;

	/**
	 *  Alpha level of the background for this component. Valid values range from 0.0 to 1.0.
	 *  @default 1.0
	 */
	[Style(name = "backgroundAlpha", type = "Number", inherit = "no", theme = "spark")]

	/**
	 *  Background color of a component.
	 *  @default 0xFFFFFF
	 */
	[Style(name = "backgroundColor", type = "uint", format = "Color", inherit = "no", theme = "spark")]

	/** The alpha value for the throbber. */
	[Style(name = "lineAlpha", type = "Number")]
	/** The thickness of the throbber lines (depends on the skin) */
	[Style(name = "lineThickness", type = "Number")]
	/** The main color */
	[Style(name = "color", type = "uint", format = "Color", inherit = "yes")]
	/** The highlight color */
	[Style(name = "highlightColor", type = "uint", format = "Color", inherit = "yes")]
	/** The highlight colors for the throbber (depends on the skin) */
	[Style(name = "highlightColors", type = "Array", arrayType = "uint", format = "Color")]


	/** Fired when the throbber starts animating. */
	[Event(name = "throbberStarted", type = "flash.events.Event")]
	/** Fired when the throbber stops animating. */
	[Event(name = "throbberStopped", type = "flash.events.Event")]

	/**
	 * A skinnable throbber component.
	 * The animation is divided into steps, every time the timer goes off the <b>currentStep> counter is increased
	 * and rolls over when it reaches the <b>maxSteps</b> value.
	 *
	 * The speed of the throbber animation is controlled by the <b>delay</b> property (in milliseconds).
	 * Set the delay to smaller values to speed up the animation.
	 *
	 * To start the throbber, either call the <b>start()</b> function or set <b>running</b> to true.
	 * To stop the throbber either call the <b>stop()</b> function or set <b>running</b> to false.
	 *
	 * The animation can be automatically started when the control is created by setting the <b>autoStart</b> property to true.
	 *
	 * The animation is automatically stopped then the throbber is hidden (setting <b>visible</b> to false) or removed from its parent.
	 * It is also stopped when the throbber is removed from the stage (because the parent is removed any parent is removed).
	 *
	 * The throbber can also be hidden when it is not running/animating by setting the <b>hideWhenStopped</b> property to true.
	 *
	 * There are currently two skins for the throbber - the <b>ThrobberCirclesSkin</b> and the <b>ThrobberLinesSkin</b>.
	 *
	 * @author ccallendar
	 * @date September 10th, 2010
	 */
	public class Throbber extends SkinnableComponent {

		public static const THROBBER_STARTED:String = "throbberStarted";

		public static const THROBBER_STOPPED:String = "throbberStopped";

		private var timer:Timer;

		private var _currentStep:int = -1;

		private var _maxSteps:int = 12;

		private var _autoStart:Boolean = false;

		private var _maxDuration:Number = NaN;

		private var startTime:int;

		private var _hideWhenStopped:Boolean = false;

		public function Throbber() {
			super();

			timer = new Timer(100);
			timer.addEventListener(TimerEvent.TIMER, timerHandler);

			addEventListener(FlexEvent.CREATION_COMPLETE, created);
			addEventListener(FlexEvent.SHOW, throbberAdded);
			addEventListener(FlexEvent.HIDE, throbberRemoved);
			addEventListener(FlexEvent.ADD, throbberAdded);
			addEventListener(FlexEvent.REMOVE, throbberRemoved);
			// This is needed to detect when the throbber's parent or ancestor is removed
			addEventListener(Event.REMOVED_FROM_STAGE, throbberRemoved);
		}

		[Inspectable(name = "Timer Delay", category = "Throbber", defaultValue = "100")]
		[Bindable("delayChanged")]
		/** The time delay in milliseconds, the default value is 100ms. */
		public function get delay():Number {
			return timer.delay;
		}

		public function set delay(value:Number):void {
			if (value != delay) {
				timer.delay = value;
				dispatchEvent(new Event("delayChanged"));
			}
		}

		[Inspectable(name = "Maximum timer duration (in milliseconds)", category = "Throbber", defaultValue = "NaN")]
		[Bindable("maxDurationChanged")]

		/**
		 * Gets or sets the maximum duration that the throbber timer can run for before stopping.
		 * The run time is reset after the timer has stopped.
		 * Default value is NaN.
		 */
		public function get maxDuration():Number {
			return _maxDuration;
		}

		public function set maxDuration(value:Number):void {
			if (value != _maxDuration) {
				_maxDuration = value;
				dispatchEvent(new Event("maxDurationChanged"));
			}
		}

		[Inspectable(name = "Auto start throbber", category = "Throbber", defaultValue = "false")]
		[Bindable("autoStartChanged")]
		/** If true then the throbber will start animating immediately after being created, defaults to false. */
		public function get autoStart():Boolean {
			return _autoStart;
		}

		public function set autoStart(value:Boolean):void {
			if (value != _autoStart) {
				_autoStart = value;
				dispatchEvent(new Event("autoStartChanged"));
			}
		}

		[Bindable("maxStepsChanged")]
		/** Defines how many steps there are in the throbber cycle. */
		public function get maxSteps():int {
			return _maxSteps;
		}

		public function set maxSteps(value:int):void {
			if (value != _maxSteps) {
				_maxSteps = value;
				dispatchEvent(new Event("maxStepsChanged"));
			}
		}

		[Bindable("currentStepChanged")]
		/** Returns the current step in the cycle. */
		public function get currentStep():int {
			return _currentStep;
		}

		public function set currentStep(value:int):void {
			if (value != _currentStep) {
				_currentStep = value;
				dispatchEvent(new Event("currentStepChanged"));
			}
		}

		/**
		 * Set to true if you want to hide the throbber (visible="false") when it is not animating.
		 */
		[Inspectable(name = "Hide Throbber When Stopped", category = "Throbber", defaultValue = "false")]
		[Bindable("hideWhenStoppedChanged")]
		/** If true then the throbber is hidden when it is not animating, defaults to false. */
		public function get hideWhenStopped():Boolean {
			return _hideWhenStopped;
		}

		public function set hideWhenStopped(value:Boolean):void {
			if (value != _hideWhenStopped) {
				_hideWhenStopped = value;
				if (value && !running && visible) {
					visible = false;
				}
				dispatchEvent(new Event("hideWhenStoppedChanged"));
			}
		}

		private var _dropShadowVisible:Boolean = true;

		[Inspectable(name = "Throbber Drop Shadow", category = "Throbber", defaultValue = "true")]
		[Bindable("dropShadowVisibleChanged")]
		/** Whether the default drop shadow on the throbber is visible (true by default). */
		public function get dropShadowVisible():Boolean {
			return _dropShadowVisible;
		}

		public function set dropShadowVisible(value:Boolean):void {
			if (value != _dropShadowVisible) {
				_dropShadowVisible = value;
				dispatchEvent(new Event("dropShadowVisibleChanged"));
			}
		}


		private function created(event:FlexEvent):void {
			if (autoStart) {
				start();
			}
		}

		private function throbberAdded(event:Event):void {
			if (autoStart && !running) {
				running = true;
			}
		}

		private function throbberRemoved(event:Event):void {
			if (running) {
				running = false;
			}
		}

		private function timerHandler(event:TimerEvent):void {
			nextStep();

			// check running time, stop if gone on too long
			if (!isNaN(maxDuration) && (maxDuration > 0)) {
				var time:int = (getTimer() - startTime);
				if (time > maxDuration) {
					stop();
				}
			}
		}

		[Inspectable(name = "Throbber Running", category = "Throbber", defaultValue = "false")]
		[Bindable("runningChanged")]
		public function get running():Boolean {
			return timer.running;
		}

		public function set running(value:Boolean):void {
			if (value != running) {
				if (value) {
					timer.start();
					startTime = getTimer();
					dispatchEvent(new Event(THROBBER_STARTED));
					if (!visible) {
						visible = true;
					}
				} else {
					timer.stop();
					dispatchEvent(new Event(THROBBER_STOPPED));
					if (hideWhenStopped && visible) {
						visible = false;
					}
				}
				dispatchEvent(new Event("runningChanged"));
			}
		}

		public function start():void {
			running = true;
		}

		public function stop():void {
			running = false;
		}

		public function reset():void {
			currentStep = -1;
		}

		public function nextStep():void {
			currentStep = (currentStep + 1) % maxSteps;
			if (skin) {
				skin.invalidateDisplayList();
			}
		}


	}
}
