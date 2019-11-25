package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.data.analytics.repository;

import java.util.HashMap;
import org.eclipse.emf.common.util.URI;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.data.analytics.IRequirementElementMapping;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IRequirementElement;

/**
 * The singelton Repository to save all references between syntactic elements and semantic elements specific for each file.
 * @author mmauritz
 *
 */
public class RequirementElementMappingRepository extends HashMap<URI, IRequirementElementMapping<?, ? extends IRequirementElement>>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6710192667242070413L;
	
	private static RequirementElementMappingRepository INSTANCE = null;
	
	private RequirementElementMappingRepository() {
		
	}
	
	public static RequirementElementMappingRepository getInstance() {
	    if (RequirementElementMappingRepository.INSTANCE== null) {
	    	RequirementElementMappingRepository.INSTANCE= new RequirementElementMappingRepository();
	    }
	    return RequirementElementMappingRepository.INSTANCE;
	}


}