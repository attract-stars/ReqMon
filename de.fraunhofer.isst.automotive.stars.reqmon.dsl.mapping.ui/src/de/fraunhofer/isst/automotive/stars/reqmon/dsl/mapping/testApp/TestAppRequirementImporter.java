package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.testApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.definitions.IRequirementElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.definitions.IRequirementImporter;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.editor.MappingPage;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.logic.RequirementController;

public class TestAppRequirementImporter implements IRequirementImporter {
	
	private List<IRequirementElement> requirements;
	private String pathname;
	private MappingPage mp;
	
	public TestAppRequirementImporter(MappingPage mp) {
		this.mp = mp;
		requirements = new ArrayList<IRequirementElement>();
		createSampleElements();
	}
	
	public void createSampleElements() {
		requirements.add(new TestAppRequirementElement("An Object ...", "object"));
		requirements.add(new TestAppRequirementElement("A Relation ...", "realtion"));
		requirements.add(new TestAppRequirementElement("A Function ...", "function"));
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
	
	
	public List<IRequirementElement> getRequirements() {
		return requirements;
	}

	
	@Override
	public void execute(RequirementController rc) {
		readFile();
		mp.updateList();
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
					//System.out.println("line with c: " + c + " " + line);
				}
				c = reader.read();
				//System.out.println("Next c: " + c);
			}
			addElements(line, num);
			
		} catch (FileNotFoundException e) {
			System.out.println("The file does not exist or can not be read: " + pathname);
		} catch (IOException io) {
			System.out.println("The character of the file can not be read: " + pathname);
		} finally {
			  try { 
				  reader.close();
				  //System.out.println("closed!");
			  } catch ( Exception e ) { 
				  System.out.println("Unknown Exception!");
			  }
		}
	}
	
	private void addElements(String line, int num) {
		if (/*num < 40 ||*/ line.equals("") || !line.contains("-")) {
		}
		else {
			//System.out.println("num: " + num);
			TestAppRequirementElement reqElem = new TestAppRequirementElement();
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
			//System.out.println("line: " + line + ", sub: " + nameList + ", types: " + type);
			/*if (num == 20) {
				System.out.println(num);
				return;
			}*/
		}
	}

	

}
