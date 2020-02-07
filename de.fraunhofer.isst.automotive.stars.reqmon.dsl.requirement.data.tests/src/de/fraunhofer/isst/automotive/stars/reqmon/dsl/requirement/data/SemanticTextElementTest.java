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
/**
 * 
 */
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType;

/**
 * @author mmauritz
 *
 */
class SemanticTextElementTest {

	/**
	 * Test method for
	 * {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.SemanticTextElement#Constructor}.
	 */
	@Test
	void testConstructor() {
		SemanticTextElement st1 = new SemanticTextElement();
		assertNull(st1.getElementName(), "Must be Null due to Standard Constructor");
		assertNull(st1.getElementType(), "Must be Null due to Standard Constructor");
		assertNull(st1.getText(), "Must be Null due to Standard Constructor");
		assertNull(st1.getType(), "Must be Null due to Standard Constructor");
		String name = "NAME";
		SemanticTextElement st2 = new SemanticTextElement(name, RequirementType.OBJECT);
		assertEquals("Should equal 'NAME'", name, st2.getElementName());
		assertEquals("Should equal 'OBJECT'", RequirementType.OBJECT, st2.getElementType());
		assertEquals("Should equal 'NAME'", name, st2.getText());
		assertEquals("Should equal 'OBJECT'", RequirementType.OBJECT, st2.getType());
	}

	/**
	 * Test method for
	 * {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.SemanticTextElement#getText()}.
	 */
	@Test
	void testGetText() {
		String name = "NAME";
		SemanticTextElement st1 = new SemanticTextElement(name, RequirementType.OBJECT);
		String name2 = "NAME2";
		SemanticTextElement st2 = new SemanticTextElement(name2, RequirementType.RELATION);
		String name3 = "NAME3";
		SemanticTextElement st3 = new SemanticTextElement(name3, RequirementType.FUNCTION);
		assertEquals("Output should be 'NAME'", name, st1.getText());
		assertEquals("Output should be 'NAME2'", name2, st2.getText());
		assertEquals("Output should be 'NAME3'", name3, st3.getText());
	}

	/**
	 * Test method for
	 * {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.SemanticTextElement#setText(java.lang.String)}.
	 */
	@Test
	void testSetText() {
		SemanticTextElement st = new SemanticTextElement();
		assertNull(st.getText());
		String name = "NAME";
		st.setText(name);
		assertEquals("Output should be 'NAME'", name, st.getText());
		String name2 = "Person";
		st = new SemanticTextElement(name2, RequirementType.RELATION);
		assertEquals("Output should be 'Person'", name2, st.getText());
		st.setText(name);
		assertEquals("Output should be 'NAME'", name, st.getText());
	}

	/**
	 * Test method for
	 * {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.SemanticTextElement#getType()}.
	 */
	@Test
	void testGetType() {
		SemanticTextElement st = new SemanticTextElement();
		assertNull(st.getType());
		st = new SemanticTextElement("Name", RequirementType.OBJECT);
		assertEquals("Type should be 'Object'", RequirementType.OBJECT, st.getType());
		st = new SemanticTextElement("Name", RequirementType.RELATION);
		assertEquals("Type should be 'Relation'", RequirementType.RELATION, st.getType());
		st = new SemanticTextElement("Name", RequirementType.FUNCTION);
		assertEquals("Type should be 'Function'", RequirementType.FUNCTION, st.getType());
	}

	/**
	 * Test method for
	 * {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.SemanticTextElement#setType(de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType)}.
	 */
	@Test
	void testSetType() {
		SemanticTextElement st = new SemanticTextElement();
		assertNull(st.getType());
		st.setType(RequirementType.OBJECT);
		assertEquals("Type should be 'Object'", RequirementType.OBJECT, st.getType());
		st.setType(RequirementType.RELATION);
		assertEquals("Type should be 'Relation'", RequirementType.RELATION, st.getType());
		st.setType(RequirementType.FUNCTION);
		assertEquals("Type should be 'Function'", RequirementType.FUNCTION, st.getType());
		st.setType(null);
		assertNull(st.getType());
	}

	/**
	 * Test method for
	 * {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.SemanticTextElement#toString()}.
	 */
	@Test
	void testToString() {
		SemanticTextElement st = new SemanticTextElement("Name", RequirementType.OBJECT);
		List<String> expectedLines = Arrays.asList(".*(Name).*", ".*(OBJECT).*");
		List<String> actual = Arrays.asList(st.toString(), st.toString());
		assertLinesMatch(expectedLines, actual);
		st = new SemanticTextElement("Name", RequirementType.RELATION);
		expectedLines = Arrays.asList(".*(Name).*", ".*(RELATION).*");
		actual = Arrays.asList(st.toString(), st.toString());
		assertLinesMatch(expectedLines, actual);
		st = new SemanticTextElement("Name", RequirementType.FUNCTION);
		expectedLines = Arrays.asList(".*(Name).*", ".*(FUNCTION).*");
		actual = Arrays.asList(st.toString(), st.toString());
		assertLinesMatch(expectedLines, actual);
	}

	/**
	 * Test method for
	 * {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.SemanticTextElement#equals(java.lang.Object)}.
	 */
	@Test
	void testEqualsObject() {
		SemanticTextElement st1 = new SemanticTextElement("Name", RequirementType.OBJECT);
		SemanticTextElement st2 = new SemanticTextElement("Name", RequirementType.OBJECT);
		SemanticTextElement st3 = new SemanticTextElement("Person", RequirementType.OBJECT);
		SemanticTextElement st4 = new SemanticTextElement("Name", RequirementType.FUNCTION);
		assertEquals("Object intended to be identical", st1, st1);
		assertEquals("Object intended to be identical", st1, st2);
		assertNotEquals("Object intended to be different", st1, st3);
		assertNotEquals("Object intended to be different", st1, st4);
		assertNotEquals("Object intended to be different", st2, st4);
		assertNotEquals("Object intended to be different", st3, st4);
	}

	/**
	 * Test method for
	 * {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.SemanticTextElement#getElementName()}.
	 */
	@Test
	void testGetElementName() {
		String name = "NAME";
		SemanticTextElement st1 = new SemanticTextElement(name, RequirementType.OBJECT);
		String name2 = "NAME2";
		SemanticTextElement st2 = new SemanticTextElement(name2, RequirementType.RELATION);
		String name3 = "NAME3";
		SemanticTextElement st3 = new SemanticTextElement(name3, RequirementType.FUNCTION);
		assertEquals("Output should be 'NAME'", name, st1.getElementName());
		assertEquals("Output should be 'NAME2'", name2, st2.getElementName());
		assertEquals("Output should be 'NAME3'", name3, st3.getElementName());
	}

	/**
	 * Test method for
	 * {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.SemanticTextElement#getElementType()}.
	 */
	@Test
	void testGetElementType() {
		SemanticTextElement st = new SemanticTextElement();
		assertNull(st.getElementType());
		st = new SemanticTextElement("Name", RequirementType.OBJECT);
		assertEquals("Type should be 'Object'", RequirementType.OBJECT, st.getElementType());
		st = new SemanticTextElement("Name", RequirementType.RELATION);
		assertEquals("Type should be 'Relation'", RequirementType.RELATION, st.getElementType());
		st = new SemanticTextElement("Name", RequirementType.FUNCTION);
		assertEquals("Type should be 'Function'", RequirementType.FUNCTION, st.getElementType());
	}

	/**
	 * Test method for
	 * {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.SemanticTextElement#setElementName(java.lang.String)}.
	 */
	@Test
	void testSetElementName() {
		SemanticTextElement st = new SemanticTextElement();
		assertNull(st.getElementName());
		String name = "NAME";
		st.setElementName(name);
		assertEquals("Output should be 'NAME'", name, st.getElementName());
		String name2 = "Person";
		st = new SemanticTextElement(name2, RequirementType.RELATION);
		assertEquals("Output should be 'Person'", name2, st.getElementName());
		st.setElementName(name);
		assertEquals("Output should be 'NAME'", name, st.getElementName());
	}

	/**
	 * Test method for
	 * {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.SemanticTextElement#setElementType(de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType)}.
	 */
	@Test
	void testSetElementType() {
		SemanticTextElement st = new SemanticTextElement();
		assertNull(st.getElementType());
		st.setElementType(RequirementType.OBJECT);
		assertEquals("Type should be 'Object'", RequirementType.OBJECT, st.getElementType());
		st.setElementType(RequirementType.RELATION);
		assertEquals("Type should be 'Relation'", RequirementType.RELATION, st.getElementType());
		st.setElementType(RequirementType.FUNCTION);
		assertEquals("Type should be 'Function'", RequirementType.FUNCTION, st.getElementType());
		st.setElementType(null);
		assertNull(st.getType());
	}

	/**
	 * Test method for
	 * {@link de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.SemanticTextElement#getElementTypeComparator()}.
	 */
	@Test
	void testGetElementTypeComparator() {
		SemanticTextElement st1 = new SemanticTextElement("Name", RequirementType.OBJECT);
		SemanticTextElement st2 = new SemanticTextElement("Name2", RequirementType.OBJECT);
		SemanticTextElement st3 = new SemanticTextElement("A", RequirementType.FUNCTION);
		SemanticTextElement st4 = new SemanticTextElement("A", RequirementType.RELATION);
		Comparator<? super IRequirementElement> st1Comparator = st1.getElementTypeComparator();
		assertEquals(0, st1Comparator.compare(st1, st1));
		assertEquals(0, st1Comparator.compare(st1, st2));
		assertEquals(0, st1Comparator.compare(st2, st1));
		assertEquals(-1, st1Comparator.compare(st1, st3));
		assertEquals(-1, st1Comparator.compare(st1, st4));
		assertEquals(-1, st1Comparator.compare(st2, st3));
		assertEquals(-1, st1Comparator.compare(st2, st4));
		assertEquals(1, st1Comparator.compare(st3, st2));
		assertEquals(1, st1Comparator.compare(st4, st2));
		assertEquals(-1, st1Comparator.compare(st3, st4));
		assertEquals(1, st1Comparator.compare(st4, st3));
		List<SemanticTextElement> expected = Arrays.asList(st1, st2, st3, st4);
		List<SemanticTextElement> actual1 = Arrays.asList(st3, st1, st2, st4);
		List<SemanticTextElement> actual2 = Arrays.asList(st3, st4, st1, st2);
		List<SemanticTextElement> actual3 = Arrays.asList(st4, st1, st3, st2);
		List<SemanticTextElement> actual4 = Arrays.asList(st2, st4, st1, st3); // Das dürfte ein fehler sein weiö st2
																				// vor st1 kommt
		actual1.sort(st1Comparator);
		assertEquals(expected, actual1);
		actual2.sort(st1Comparator);
		assertEquals(expected, actual2);
		actual3.sort(st1Comparator);
		assertEquals(expected, actual3);
		actual4.sort(st1Comparator);
		List<SemanticTextElement> expected2 = Arrays.asList(st2, st1, st3, st4);
		assertEquals(expected2, actual4);

	}

}
