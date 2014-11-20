package com.dcu.redmonj9.whichprep.test;

import org.junit.Test;

import com.dcu.redmonj9.whichprep.prepositions.Dictionary;
import com.dcu.redmonj9.whichprep.prepositions.PrepScrubber;

import junit.framework.TestCase;

public class PrepScrubberTest extends TestCase {
	PrepScrubber ps = new PrepScrubber();
	Dictionary dict = new Dictionary();
	
	@Test
	public void testContains(){
		
		String result = ps.containsPrep(dict.getDictionaryItem(0), dict.getPrepositions());
		assertEquals("in", result);
	}
	
	@Test
	public void testRemove(){
		String result = ps.removePrep(dict.getDictionaryItem(0), ps.containsPrep(dict.getDictionaryItem(0), dict.getPrepositions()));
		assertEquals("He will see them ____ October", result);
	}
	
	
}
