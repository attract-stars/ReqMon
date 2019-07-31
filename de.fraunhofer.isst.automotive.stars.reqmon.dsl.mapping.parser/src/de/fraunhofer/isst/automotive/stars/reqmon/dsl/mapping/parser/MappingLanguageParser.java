package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.parser;


import org.eclipse.emf.common.util.URI;
import com.google.inject.Injector;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.scoping.MappingImportURIGlobalScopeProvider;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.language.ui.internal.LanguageActivator;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.ui.definitions.IMappingParser;

/**
 * This class implements the IMappingParser. It creates an injector for the mapping language: 
 * "DE_FRAUNHOFER_ISST_AUTOMOTIVE_STARS_REQMON_DSL_MAPPING_LANGUAGE_MAPPING".
 * 
 * @author sgraf
 *
 */
public class MappingLanguageParser implements IMappingParser {
	
	@Override
	public Injector getDslInjector(URI systemModelUri) {
		Injector injector = LanguageActivator.getInstance() 
				.getInjector(LanguageActivator.DE_FRAUNHOFER_ISST_AUTOMOTIVE_STARS_REQMON_DSL_MAPPING_LANGUAGE_MAPPING);
		
		MappingImportURIGlobalScopeProvider mappingProvider = injector.getInstance(MappingImportURIGlobalScopeProvider.class);
		
		if (mappingProvider != null) {
			System.out.println("ModelUri: " + systemModelUri);
			mappingProvider.setModelUri(systemModelUri);
		}
		return injector;
	}

}
