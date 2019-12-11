package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.definitions.Pin;

public class PinTest {
	
	@Test
	public void nameChangeTest() {
		Pin pin = new Pin("two words", true);
		assertEquals("twoWords", pin.getPinObjectName());
	}

}
