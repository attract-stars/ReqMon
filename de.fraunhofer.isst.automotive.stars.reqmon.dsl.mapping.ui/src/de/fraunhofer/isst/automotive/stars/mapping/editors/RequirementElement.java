package de.fraunhofer.isst.automotive.stars.mapping.editors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class RequirementElement {
	
	private List<String> nameList;
	private List<String> type;
	private String pathname;
	
	public RequirementElement() {
		this.nameList = new ArrayList<String>();
		this.type = new ArrayList<String>();
	}
	
	public void createSampleElements() {
		//for (int i = 0; i < 10; i++) {
			nameList.add("An Object ...");
			nameList.add("A Relation ...");
			nameList.add("A Function ...");
			type.add("object");
			type.add("relation");
			type.add("function");
		//}
		
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
	
	public String getElement(int i) {
		if (i < nameList.size()) {
			return nameList.get(i);
		}
		return null;
	}
	
	public String getType(int i) {
		if (i < type.size()) {
			return type.get(i);
		}
		return null;
	}
	
	public int getElementSize() {
		return nameList.size();
	}
	
	public void readFile() {
		Reader reader = null;
		nameList = new ArrayList<String>();
		type = new ArrayList<String>();
		
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
				  System.out.println("closed!");
			  } catch ( Exception e ) { 
				  System.out.println("Unknown Exception!");
			  }
		}
	}
	
	private void addElements(String line, int num) {
		if (/*num < 40 ||*/ line.equals("") || !line.contains("-")) {
		}
		else {
			System.out.println("num: " + num);
			String[] words = line.split("-");
			String last = words[words.length - 1];
			if(last.contains("object")) {
				type.add("object");
			}
			else if (last.contains("relation")) {
				type.add("relation");
			}
			else if (last.contains("function")) {
				type.add("function");
			}
			else {
				type.add("unknown");
			}
			String sub = line.substring(0, line.length()-last.length()-2);
			nameList.add(sub);
			System.out.println("line: " + line + ", sub: " + nameList + ", types: " + type);
			/*if (num == 20) {
				System.out.println(num);
				return;
			}*/
		}
	}

}
