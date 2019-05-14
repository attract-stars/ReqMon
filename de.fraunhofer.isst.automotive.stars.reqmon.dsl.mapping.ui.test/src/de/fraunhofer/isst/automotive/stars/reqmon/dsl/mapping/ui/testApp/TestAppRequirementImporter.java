package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.testApp;

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
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.MappingPage;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType;

/**
 * This is a test class for the MappingApplication.
 * It implements the IRequirementImporter. It reads a text file with the names and types of the requirement elements and 
 * creates for each name an IRequirementElement.
 * 
 * @author sgraf
 *
 */
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
		requirements.add(new TestAppRequirementElement("An Object ...", RequirementType.OBJECT));
		requirements.add(new TestAppRequirementElement("A Relation ...", RequirementType.RELATION));
		requirements.add(new TestAppRequirementElement("A Function ...", RequirementType.FUNCTION));
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
	public void execute(IRequirementController rc, String path) {
		readFile(path);
		mp.updateList();
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
					//System.out.println("line with c: " + c + " " + line);
				}
				c = reader.read();
				//System.out.println("Next c: " + c);
			}
			addElements(line, num);
			
		} catch (FileNotFoundException e) {
			System.out.println("The file does not exist or can not be read: " + path);
		} catch (IOException io) {
			System.out.println("The character of the file can not be read: " + path);
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
			//System.out.println("line: " + line + ", sub: " + nameList + ", types: " + type);
			/*if (num == 20) {
				System.out.println(num);
				return;
			}*/
		}
	}

	

}
