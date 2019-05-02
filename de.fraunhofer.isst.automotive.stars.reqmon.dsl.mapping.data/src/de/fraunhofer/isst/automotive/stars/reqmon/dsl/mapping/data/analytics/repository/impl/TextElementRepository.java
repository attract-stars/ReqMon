package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.data.analytics.repository.impl;

import java.util.HashMap;

import org.eclipse.core.resources.IFile;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.data.SemanticTextElementMapping;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.data.analytics.repository.ITextElementRepository;

/**
 * The singelton Repository to save all references between syntactic elements and semantic elements specific for each file.
 * @author mmauritz
 *
 */
public class TextElementRepository extends HashMap<IFile, SemanticTextElementMapping<?, ?>> implements ITextElementRepository<IFile, SemanticTextElementMapping<?, ?>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6710192667242070413L;
	
	private static TextElementRepository INSTANCE = null;
	
	private TextElementRepository() {
		
	}
	
	public static TextElementRepository getInstance () {
	    if (TextElementRepository.INSTANCE== null) {
	    	TextElementRepository.INSTANCE= new TextElementRepository();
	    }
	    return TextElementRepository.INSTANCE;
	  }


}