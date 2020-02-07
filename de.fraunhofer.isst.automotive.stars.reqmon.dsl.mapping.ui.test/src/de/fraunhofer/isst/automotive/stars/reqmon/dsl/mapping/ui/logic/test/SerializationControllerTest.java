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
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.logic.SerializationController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.model.SaveModel;

public class SerializationControllerTest {
	
	private SaveModel model;
	
	@Before
	public void serializationControllerTestSetup() {
		model = new SaveModel();
		
		ArrayList<IRequirementElement> reqList = new ArrayList<IRequirementElement>();
		for (int i = 0; i < 3; i++) {
			TestReqElem elem = new TestReqElem(RequirementType.OBJECT, "obj_" + i);
			reqList.add(elem);
		}
		for (int i = 0; i < 3; i++) {
			TestReqElem elem = new TestReqElem(RequirementType.FUNCTION, "func_" + i);
			reqList.add(elem);
		}
		for (int i = 0; i < 3; i++) {
			TestReqElem elem = new TestReqElem(RequirementType.RELATION, "rel_" + i);
			reqList.add(elem);
		}
		model.setReqList(reqList);
		
		ArrayList<String> inputs = new ArrayList<String>();
		for (int i = 0; i < 9; i++) {
			String in = "bla_" + (i*5);
			inputs.add(in);
		}
		model.setEditorContentList(inputs);
		
		model.setReqPath("Requirementpath");
		model.setSysPath("Systempath");
	}
	
	@Test
	public void filenameTest() {
		System.out.println("\nfilenameTest:");
		
		String filename;
		String serFilename;
		
		filename = "filenameTest";
		SerializationController.getInstance().setFilename(filename);
		serFilename = SerializationController.getInstance().getFilename();
		assertEquals(filename + ".ser", serFilename);
		System.out.println("serFileName: " + serFilename);
		System.out.println("serFilepath: " + SerializationController.getInstance().getAbsolutSerFilePath());
		
		filename = "filenameTest2.fhf.fgj";
		SerializationController.getInstance().setFilename(filename);
		serFilename = SerializationController.getInstance().getFilename();
		assertEquals("filenameTest2.ser", serFilename);
		System.out.println("serFileName: " + serFilename);
		System.out.println("serFilepath: " + SerializationController.getInstance().getAbsolutSerFilePath());
		
		filename = null;
		SerializationController.getInstance().setFilename(filename);
		serFilename = SerializationController.getInstance().getFilename();
		assertEquals(null, serFilename);
		System.out.println("serFileName: " + serFilename);
		System.out.println("serFilepath: " + SerializationController.getInstance().getAbsolutSerFilePath());
		
	}
	
	@Test
	public void serializationControllerTest() {
		System.out.println("\nserializationControllerTest:");
		
		String filename = "example.x";
		SerializationController.getInstance().setFilename(filename);
		SerializationController.getInstance().save(model);
		SaveModel loaded = SerializationController.getInstance().load();
		assertEquals(model.getReqPath(), loaded.getReqPath());
		assertEquals(model.getSysPath(), loaded.getSysPath());
		for (int i = 0; i < model.getEditorContentList().size(); i++) {
			assertEquals(model.getEditorContentList().get(i), loaded.getEditorContentList().get(i));
		}
		for (int i = 0; i < model.getReqList().size(); i++) {
			assertEquals(model.getReqList().get(i).getElementName(), loaded.getReqList().get(i).getElementName());
			assertEquals(model.getReqList().get(i).getElementType(), loaded.getReqList().get(i).getElementType());
		}
		assertEquals(true, loaded.getEditorContentList().size() == loaded.getReqList().size());
	}
	
	@Test
	public void serializationWithoutFilenameTest() {
		System.out.println("\nserializationWithoutFilenameTest:");
		
		String filename = null;
		SerializationController.getInstance().setFilename(filename);
		SerializationController.getInstance().save(model);
		SaveModel loaded = SerializationController.getInstance().load();
		assertEquals(null, loaded);
	}
	
	@Test
	public void changeModelTest() {
		System.out.println("\nchangeModelTest:");
		
		String filename = "example.x";
		SerializationController.getInstance().setFilename(filename);
		SerializationController.getInstance().save(model);
		
		model.setReqPath("otherReqPath");
		model.setSysPath("otherSysPath");
		
		assertEquals("otherReqPath", model.getReqPath());
		assertEquals("otherSysPath", model.getSysPath());
		
		ArrayList<IRequirementElement> reqs = new ArrayList<IRequirementElement>();
		reqs.add(new TestReqElem(RequirementType.OBJECT, "one"));
		reqs.add(new TestReqElem(RequirementType.FUNCTION, "two"));
		reqs.add(new TestReqElem(RequirementType.RELATION, "three"));
		model.setReqList(reqs);
		
		for(int i = 0; i < model.getReqList().size(); i++) {
			assertEquals(reqs.get(i).getElementName(), model.getReqList().get(i).getElementName());
			assertEquals(reqs.get(i).getElementType(), model.getReqList().get(i).getElementType());
		}
		
		ArrayList<String> contents = new ArrayList<String>();
		contents.add("a");
		contents.add("b");
		contents.add("c");
		model.setEditorContentList(contents);
		
		for(int i = 0; i < model.getEditorContentList().size(); i++) {
			assertEquals(contents.get(i), model.getEditorContentList().get(i));
		}
		
		SerializationController.getInstance().save(model);
		SaveModel loaded = SerializationController.getInstance().load();
		
		assertEquals(model.getReqPath(), loaded.getReqPath());
		assertEquals(model.getSysPath(), loaded.getSysPath());
		for (int i = 0; i < model.getEditorContentList().size(); i++) {
			assertEquals(model.getEditorContentList().get(i), loaded.getEditorContentList().get(i));
		}
		for (int i = 0; i < model.getReqList().size(); i++) {
			assertEquals(model.getReqList().get(i).getElementName(), loaded.getReqList().get(i).getElementName());
			assertEquals(model.getReqList().get(i).getElementType(), loaded.getReqList().get(i).getElementType());
		}
		assertEquals(true, loaded.getEditorContentList().size() == loaded.getReqList().size());
	}
	
	
	@Test
	public void changeOneEditorContentTest() {
		System.out.println("\nchangeOneEditorContentTest:");
		
		String filename = "example.x";
		SerializationController.getInstance().setFilename(filename);
		SerializationController.getInstance().save(model);
		
		List<String> contents = model.getEditorContentList();
		contents.set(0, "changed");
		model.setEditorContentList(contents);
		assertEquals("changed", model.getEditorContentList().get(0));
		
		SerializationController.getInstance().save(model);
		SaveModel loaded = SerializationController.getInstance().load();
		
		assertEquals(model.getReqPath(), loaded.getReqPath());
		assertEquals(model.getSysPath(), loaded.getSysPath());
		for (int i = 0; i < model.getEditorContentList().size(); i++) {
			assertEquals(model.getEditorContentList().get(i), loaded.getEditorContentList().get(i));
		}
		for (int i = 0; i < model.getReqList().size(); i++) {
			assertEquals(model.getReqList().get(i).getElementName(), loaded.getReqList().get(i).getElementName());
			assertEquals(model.getReqList().get(i).getElementType(), loaded.getReqList().get(i).getElementType());
		}
		assertEquals(true, loaded.getEditorContentList().size() == loaded.getReqList().size());
	}
	
	@Test
	public void changeEditorContentSizeTest() {
		System.out.println("\nchangeEditorContentSizeTest:");
		
		String filename = "example.x";
		SerializationController.getInstance().setFilename(filename);
		SerializationController.getInstance().save(model);
		
		List<String> contents = model.getEditorContentList();
		int oldSize = contents.size();
		contents.add("new");
		model.setEditorContentList(contents);
		int newSize = model.getEditorContentList().size();
		assertEquals(oldSize, newSize);
		
		SerializationController.getInstance().save(model);
		SaveModel loaded = SerializationController.getInstance().load();
		
		assertEquals(model.getReqPath(), loaded.getReqPath());
		assertEquals(model.getSysPath(), loaded.getSysPath());
		for (int i = 0; i < model.getEditorContentList().size(); i++) {
			assertEquals(model.getEditorContentList().get(i), loaded.getEditorContentList().get(i));
		}
		for (int i = 0; i < model.getReqList().size(); i++) {
			assertEquals(model.getReqList().get(i).getElementName(), loaded.getReqList().get(i).getElementName());
			assertEquals(model.getReqList().get(i).getElementType(), loaded.getReqList().get(i).getElementType());
		}
		assertEquals(true, loaded.getEditorContentList().size() == loaded.getReqList().size());
	}
	
	@Test
	public void changeReqsSizeTest() {
		System.out.println("\nchangeReqsSizeTest:");
		
		String filename = "example.x";
		SerializationController.getInstance().setFilename(filename);
		SerializationController.getInstance().save(model);
		SerializationController.getInstance().load();
		
		List<IRequirementElement> reqs = model.getReqList();
		int oldContentSize = model.getEditorContentList().size();
		int oldSize = reqs.size();
		reqs.add(new TestReqElem(RequirementType.FUNCTION, "new"));
		model.setReqList(reqs);
		int newContentSize = model.getEditorContentList().size();
		int newSize = model.getReqList().size();
		assertEquals(oldSize + 1, newSize);
		// the contents list size also changes, because the requirements and the contents are saved in MappingObjects
		// and for each requirement a new MappingObject is created. So if the content after that is not set it will be null.
		assertEquals(oldContentSize + 1, newContentSize);
		
		SerializationController.getInstance().save(model);
		SaveModel loaded = SerializationController.getInstance().load();
		
		assertEquals(model.getReqPath(), loaded.getReqPath());
		assertEquals(model.getSysPath(), loaded.getSysPath());
		for (int i = 0; i < model.getEditorContentList().size(); i++) {
			assertEquals(model.getEditorContentList().get(i), loaded.getEditorContentList().get(i));
		}
		for (int i = 0; i < model.getReqList().size(); i++) {
			assertEquals(model.getReqList().get(i).getElementName(), loaded.getReqList().get(i).getElementName());
			assertEquals(model.getReqList().get(i).getElementType(), loaded.getReqList().get(i).getElementType());
		}
		assertEquals(true, loaded.getEditorContentList().size() == loaded.getReqList().size());
	}
	
	
	public static class TestReqElem implements IRequirementElement {

		private RequirementType type;
		private String name;
		
		public TestReqElem(RequirementType type, String name) {
			this.type = type;
			this.name = name;
		}
		
		@Override
		public void setElementType(RequirementType type) {
			this.type = type;
		}
		
		@Override
		public void setElementName(String name) {
			this.name = name;
		}
		
		@Override
		public RequirementType getElementType() {
			return this.type;
		}
		
		@Override
		public String getElementName() {
			return this.name;
		}

		@Override
		public Comparator<? super IRequirementElement> getElementTypeComparator() {
			return new Comparator<IRequirementElement>() {

				@Override
				public int compare(IRequirementElement o1, IRequirementElement o2) {
					if (o1.getElementType().equals(o2.getElementType())) {
						return 0;
					}
					else if (o1.getElementType().equals(RequirementType.OBJECT)) {
						return -1;
					}
					else if (o1.getElementType().equals(RequirementType.FUNCTION) && 
							o2.getElementType().equals(RequirementType.RELATION)) {
						return -1;
					}
					return 1;
				}
			};
		}
		
	}

}
