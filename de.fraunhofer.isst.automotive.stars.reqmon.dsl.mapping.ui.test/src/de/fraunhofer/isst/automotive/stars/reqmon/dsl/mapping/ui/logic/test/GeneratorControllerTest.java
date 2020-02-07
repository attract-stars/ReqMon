/*******************************************************************************
 * Copyright (C) 2020 Fraunhofer ISST
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic.GeneratorController;

public class GeneratorControllerTest {
	
	private GeneratorController gc;
	
	@Before
	public void generatorControllerTestSetup() {
		gc = new GeneratorController();
		
		/** create generator list */
		List<String> generators = new ArrayList<String>();
		List<String> labels = new ArrayList<String>();
		generators.add("g1");
		generators.add("g2");
		generators.add("g3");
		generators.add("g4");
		labels.add("gen1");
		labels.add("gen2");
		labels.add("gen3");
		labels.add("gen4");
		gc.setGenerators(generators);
		gc.setGenerateLabels(labels);
	}
	
	@Test
	public void deleteExistingGeneratorShouldReturnTrue() {
		/** test the delete of 'g2' */
		assertEquals("Delete of g2 must be successful", true, gc.deleteGenerator("g2"));
		assertEquals("After delete of g2 the generators size must be 3", 3, gc.getGenerators().size());
		assertEquals("After delete of g2 the generatorLabels size must be 3", 3, gc.getGenerateLabels().size());
		assertEquals("After delete of g2 the generators must not contain g2", false, gc.getGenerators().contains("g2"));
		assertEquals("After delete of g2 the generatorLabels must not contain g2", false, gc.getGenerateLabels().contains("g2"));
	}
	
	public void deleteNotExistingGeneratorShouldReturnFalse() {
		/** test the delete of 'g6' */
		assertEquals("Delete of g6 must not be successful", false, gc.deleteGenerator("g6"));
		assertEquals("After delete of g6 the generators size must be 4", 4, gc.getGenerators().size());
		assertEquals("After delete of g6 the generatorLabels size must be 4", 4, gc.getGenerateLabels().size());
	}
	
	public void getCorrectLabelToTheGivenName() {
		assertEquals("Get gen3 for g3","gen3", gc.getLabel("g3"));
	}
	
	public void getFirstLabelIfTheGivenNameIsWrong() {
		assertEquals("Get gen1 for g","gen1", gc.getLabel("g"));
	}

}
