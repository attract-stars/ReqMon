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
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementController;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementImporter;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType;

/**
 * This class implements the IRequirementImporter.
 * It reads a text file with the names and types of the requirement elements and 
 * creates for each name an IRequirementElement.
 * 
 * @author sgraf
 *
 */
public class TestRequirementImporter implements IRequirementImporter {
	
	private List<IRequirementElement> requirements;
	
	
	public TestRequirementImporter() {
		requirements = new ArrayList<IRequirementElement>();
	}
	
	@Override
	public void execute(IRequirementController rc, String path) {
		readFile(path);
		rc.updateList(this.requirements);
	}
	

	private void readFile(String path) {
		Reader reader = null;
		requirements = new ArrayList<IRequirementElement>();
		
		try {
			if (path == null) {
				System.out.println("The path is empty!");
				return;
			}
			reader = new FileReader(new File(path));
			int c = reader.read();
			String line = "";
			int num = 0;
			
			while (c != -1) {
				if ((char) c == '\n') {
					addElements(line, num);
					num++;
					line = "";
				}
				else {
					if (c != 13 && c != 194) {
						line += (char) c;
					}
				}
				c = reader.read();
			}
			addElements(line, num);
			
		} catch (FileNotFoundException e) {
			System.out.println("The file does not exist or can not be read: " + path);
		} catch (IOException io) {
			System.out.println("The character of the file can not be read: " + path);
		} finally {
			  try { 
				  reader.close();
			  } catch ( Exception e ) { 
				  System.out.println("Unknown Exception!");
			  }
		}
	}
	
	private void addElements(String line, int num) {
		if (line.equals("") || !line.contains("-")) {
		}
		else {
			TestRequirementElement reqElem = new TestRequirementElement();
			String[] words = line.split("-");
			String last = words[words.length - 1];
			if(last.contains("object")) {
				reqElem.setElementType(RequirementType.OBJECT);
			}
			else if (last.contains("relation")) {
				reqElem.setElementType(RequirementType.RELATION);
			}
			else if (last.contains("function")) {
				reqElem.setElementType(RequirementType.FUNCTION);
			}
			
			String sub = line.substring(0, line.length()-last.length()-2);
			reqElem.setElementName(sub);
			requirements.add(reqElem);
		}
	}

	

}
