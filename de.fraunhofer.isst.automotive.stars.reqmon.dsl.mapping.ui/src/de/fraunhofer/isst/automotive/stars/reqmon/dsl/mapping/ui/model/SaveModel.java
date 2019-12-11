package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingModel;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.editor.RequirementType;

/**
 * This class is used for the serialization of the Mapping Language Editor data: 
 * the requirement path, the system model path, the requirement list and the embedded editor content list.
 * Both lists are combined in the MappingObject list.
 * 
 * @author sgraf
 *
 */
public class SaveModel implements Serializable, IMappingModel {
	
	/**
	 * Generated SerialVersionUID.
	 */
	private static final long serialVersionUID = -943340977918585830L;
	private String reqPath;
	private String sysPath;
	private List<MappingObject> mappingObjects;
	private String genLabel;
	private int generatorIndex;
	private String projectName;
	
	// Exclude this fields from serialization
	private transient List<Resource> mappingResourceList;
	private transient List<? extends IRequirementElement> requirements;
	private transient EObject systemModel;
	

	public SaveModel() {
		reqPath = "";
		sysPath = "";
		mappingObjects = new ArrayList<MappingObject>();
		
		mappingResourceList = new ArrayList<Resource>();
		requirements = new ArrayList<IRequirementElement>();
	}
	
	@Override
	public List<Resource> getMappingResourceList() {
		return mappingResourceList;
	}

	@Override
	public void setMappingResourceList(List<Resource> mappingResourceList) {
		this.mappingResourceList = mappingResourceList;
	}

	@Override
	public List<? extends IRequirementElement> getRequirementList() {
		return requirements;
	}

	@Override
	public void setRequirementList(List<? extends IRequirementElement> requirementList) {
		this.requirements = requirementList;
		
	}

	@Override
	public EObject getSystemModel() {
		return systemModel;
	}

	@Override
	public void setSystemModel(EObject systemModel) {
		this.systemModel = systemModel;
	}
	
	/**
	 * Returns the requirement list.
	 * @return the requirement list
	 */
	public List<IRequirementElement> getReqList() {
		// Collect the requirements from the MappingObject list
		List <IRequirementElement> reqList = new ArrayList<IRequirementElement>();
		if (mappingObjects == null) {
			System.err.println("The MappingObject list is null!");
			return null;
		}
		for (MappingObject mapObj : mappingObjects) {
			reqList.add(getReqElement(mapObj));
		}
		return reqList;
	}
	
	/**
	 * Set the requirement list.
	 * @param reqList the requirement list
	 */
	public void setReqList(List<? extends IRequirementElement> reqList) {
		/* create for each requirement element a new MappingObject,
		 * add it to the MappingObject list
		 * and pass the requirements on to the MappingObject list */
		this.mappingObjects = new ArrayList<MappingObject>();
		for (IRequirementElement req : reqList) {
			MappingObject mapObj = new MappingObject();
			mappingObjects.add(mapObj);
			setReqElement(req, mapObj);
		}
	}
	
	/**
	 * Pass the requirement element name and type on to the MappingObject.
	 * @param reqElem the requirement element
	 * @param mapObj MappingObject
	 */
	private void setReqElement(IRequirementElement reqElem, MappingObject mapObj) {
		mapObj.setReqElemName(reqElem.getElementName());
		mapObj.setReqElemType(reqElem.getElementType());
	}
	
	/**
	 * Return the requirement name and type as an IRequirementElement from the given MappingObject.
	 * @param mapObj the MappingObject
	 * @return the requirement element
	 */
	private IRequirementElement getReqElement(MappingObject mapObj) {
		// create a new requirement element with the name and type that are saved in the given MappingObject
		IRequirementElement elem = new IRequirementElement() {
			
			private RequirementType type;
			private String name;
			
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
		};
		
		elem.setElementName(mapObj.getReqElemName());
		elem.setElementType(mapObj.getReqElemType());
		return elem;
	}
	
	/**
	 * Sets the embedded editor content list if the size of the list is equal with the size of the requirement list.
	 * @param contents the embedded editor content list
	 */
	public void setEditorContentList(List<String> contents) {
		if (mappingObjects.size() != contents.size()) {
			System.err.println("Error! Can not save editor inputs because different list sizes!");
			System.err.println("Mapping Size: " + mappingObjects.size() + ", Input size: " + contents.size());
			return;
		}
		// pass the embedded editor contents on to the MappingObject list
		for (int i = 0; i < contents.size(); i++) {
			setEditorInput(contents.get(i), mappingObjects.get(i));
		}
	}
	
	/**
	 * Pass the embedded editor content on to the MappingObject.
	 * @param text the embedded editor content
	 * @param mapObj the MappingObject
	 */
	private void setEditorInput(String text, MappingObject mapObj) {
		mapObj.setMappingText(text);
	}
	
	/**
	 * Returns the embedded editor content list.
	 * @return the embedded editor content list
	 */
	public List<String> getEditorContentList() {
		// create  a new embedded editor content list and add all contents from the MappingObject list
		ArrayList<String> contents = new ArrayList<String>();
		if (mappingObjects == null) {
			System.err.println("The MappingObject list is null!");
			return null;
		}
		for (MappingObject elem : mappingObjects) {
			contents.add(getEditorContent(elem));
		}
		return contents;
	}
	
	/**
	 * Returns the embedded editor content that is saved in the given MappingObject.
	 * @param mapObj the MappingObject
	 * @return the embedded editor content
	 */
	private String getEditorContent(MappingObject mapObj) {
		return mapObj.getMappingText();
	}
	
	/**
	 * Returns the requirements path.
	 * @return the requirements path
	 */
	public String getReqPath() {
		return reqPath;
	}
	
	/**
	 * Sets the requirements path.
	 * @param reqPath the requirements path
	 */
	public void setReqPath(String reqPath) {
		this.reqPath = reqPath;
	}
	
	/**
	 * Returns the system model path.
	 * @return the system model path
	 */
	public String getSysPath() {
		return sysPath;
	}
	
	/**
	 * Sets the system model path.
	 * @param sysPath the system model path
	 */
	public void setSysPath(String sysPath) {
		this.sysPath = sysPath;
	}
	
	/**
	 * Returns the requirements and embedded editor contents as MappingObject list.
	 * @return the MappingObject list
	 */
	public List<MappingObject> getMappingObjects() {
		return mappingObjects;
	}
	
	/**
	 * Sets the MappingObject list.
	 * @param mappings the MappingObject list.
	 */
	public void setMappingObjects(List<MappingObject> mappingObjects) {
		this.mappingObjects = mappingObjects;
	}

	public String getGenLabel() {
		return genLabel;
	}

	public void setGenLabel(String genLabel) {
		this.genLabel = genLabel;
	}

	public int getGeneratorIndex() {
		return generatorIndex;
	}

	public void setGeneratorIndex(int generatorIndex) {
		this.generatorIndex = generatorIndex;
	}

	@Override
	public String getProjectName() {
		return this.projectName;
	}

	@Override
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	
	

}
