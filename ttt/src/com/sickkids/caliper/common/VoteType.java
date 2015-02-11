package com.sickkids.caliper.common;

public enum VoteType
{
	AGREE, DISAGREE, ABSTAIN, CONFLICT;
	
	@Override public String toString() 
	{
		//only capitalize the first letter
		String s = super.toString();
		return s.substring(0, 1) + s.substring(1).toLowerCase();
	}
}
