package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data;

import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.data.analytics.IRequirementElementMapping;

/**
 * 
 * @author mmauritz
 *
 */
public class RequirementTextElementMapping extends HashMap<EObject, SemanticTextElement> implements IRequirementElementMapping<EObject, SemanticTextElement>{

	private boolean hasDirtySource;
	
	public RequirementTextElementMapping() {
		this.hasDirtySource=false;
	}
	
	@Override
	public boolean hasDirtySource() {
		return hasDirtySource;
	}

	@Override
	public void setDirtySource(boolean dirty) {
		this.hasDirtySource = dirty;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = -3103601961030381516L;

	

}
