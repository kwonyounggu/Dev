package com.sickkids.caliper.common
{
	import mx.collections.ArrayCollection;
	import mx.collections.IList;

	public class Utils
	{
		public function Utils()
		{
		}
		
		public static function arrayCollectionClone(a: ArrayCollection):ArrayCollection
		{
			var b:ArrayCollection=new ArrayCollection;

			for(var i:int=0;i<a.source.length;i++)
			{ 
				b.addItem(a.source[i]);
			}
			
			return b;
		}
	}
}