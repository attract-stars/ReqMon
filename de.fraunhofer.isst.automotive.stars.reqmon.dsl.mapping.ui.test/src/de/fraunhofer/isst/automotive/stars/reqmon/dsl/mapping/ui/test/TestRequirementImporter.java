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


public class TestRequirementImporter implements IRequirementImporter {
	
	private List<IRequirementElement> requirements;
	
	private String pathname;

	
	public TestRequirementImporter() {
		requirements = new ArrayList<IRequirementElement>();
	}
	
	public String getPath() {
		if (pathname == null) {
			pathname = "";
		}
		return pathname;
	}

	public void setPath(String path) {
		this.pathname = path;
	}
	
	public String[] getFilterExt() {
		String[] filterExt = { "*.txt", "*.doc", ".rtf", "*.*" };
		return filterExt;
	}
	
	@Override
	public List<IRequirementElement> getRequirements() {
		return requirements;
	}
	
	@Override
	public void execute(IRequirementController rc) {
		readFile();
		//rc.updateList();
	}

	private void readFile() {
		Reader reader = null;
		requirements = new ArrayList<IRequirementElement>();
		
		try {
			if (pathname == null) {
				System.out.println("The path is empty!");
				return;
			}
			reader = new FileReader(new File(pathname));
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
			System.out.println("The file does not exist or can not be read: " + pathname);
		} catch (IOException io) {
			System.out.println("The character of the file can not be read: " + pathname);
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
				reqElem.setElementType("object");
			}
			else if (last.contains("relation")) {
				reqElem.setElementType("relation");
			}
			else if (last.contains("function")) {
				reqElem.setElementType("function");
			}
			else {
				reqElem.setElementType("unknown");
			}
			String sub = line.substring(0, line.length()-last.length()-2);
			reqElem.setElementName(sub);
			requirements.add(reqElem);
		}
	}

	

}
