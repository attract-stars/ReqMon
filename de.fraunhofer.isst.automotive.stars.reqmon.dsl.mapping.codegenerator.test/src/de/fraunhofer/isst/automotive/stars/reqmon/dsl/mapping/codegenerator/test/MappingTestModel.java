package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.codegenerator.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementElement;

public class MappingTestModel implements IMappingModel {
	
	private Document doc;
	
	public MappingTestModel() {
		//readSystemXMLFile();
	}

	@Override
	public List<Resource> getMappingResourceList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMappingResourceList(List<Resource> mappingResourceList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IRequirementElement> getRequirementList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRequirementList(List<? extends IRequirementElement> requirementList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EObject getSystemModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSystemModel(EObject systemModel) {
		// TODO Auto-generated method stub
		
	}
	
	
	private void readSystemXMLFile() {
		try {

			File fXmlFile = new File("C:/Users/sgraf/eclipse-workspace/de.fraunhofer.isst.automative.stars.reqmon.dsl.mapping.codegenerator.test/src/de/fraunhofer/isst/automative/stars/reqmon/dsl/mapping/codegenerator/test/adtf.sysdef");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
					
			doc.getDocumentElement().normalize();

			System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
			
			NodeList nList = doc.getElementsByTagName("data_structure");
			
			System.out.println("----------------------------");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
						
				System.out.println("\nCurrent Element : " + nNode.getNodeName());
				
				getClassNames(nNode);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getClassNames(Node nNode) {
		NodeList nList = doc.getElementsByTagName("class");
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nClass = nList.item(temp);
					
			if (nClass.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nClass;
				System.out.println("Class Name : " + eElement.getElementsByTagName("name").item(0).getTextContent());

			/*	System.out.println("Staff id : " + eElement.getAttribute("id"));
				System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
				System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
				System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
				System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());*/

			}
			
			
		}
	}
	
	public List<String> getAllAtributesOf(String objectName) {
		List<String> attributes = new ArrayList<String>();
		switch(objectName) {
		case "tCategorization":
			attributes.add("c tEgoVehicleCategory ego");
			attributes.add("l tLaneCategory lanesCategory");
			break;
		case "tEgoVehicleCategory":
			//attributes.add("e velocity TO_LOW TO_HIGH PREFFERED OK");
			attributes.add("a tFloat egoVelocity");
			//attributes.add("e offset TO_LOW TO_HIGH PREFFERED OK");
			attributes.add("a tFloat egoOffset");
			break;
		}
		
		return attributes;
	}

	public String getSystemAttribute(String name) {
		String attr = "";
		switch(name) {
		case "tEgoVehicleCategory" : 
			attr = "i tObject ego";
			break;
		case "tLaneCategory" :
			attr = "l tLane lanes";
		}
		return attr;
	}

	public String getCorrespondingAttribute(String monAttr) {
		String attr = "";
		switch(monAttr) {
		case "egoVelocity" :
			attr = "ego velocity";
			break;
		case "egoOffset" :
			attr = "ego offset";
			break;
		}
		return attr;
	}

	public List<String> isSignal(String attr) {
		List<String> bounds = new ArrayList<String>();
		switch(attr) {
		case "velocity" :
			bounds.add("EGO_VELOCITY_MIN");
			bounds.add("EGO_VELOCITY_MAX");
			bounds.add("EGO_VELOCITY_PREFFERED");
			break;
		case "offset" :
			bounds.add("EGO_OFFSET_MIN");
			bounds.add("EGO_OFFSET_MAX");
			bounds.add("EGO_OFFSET_PREFFERED");
			break;
		}
		return bounds;
	}

}
