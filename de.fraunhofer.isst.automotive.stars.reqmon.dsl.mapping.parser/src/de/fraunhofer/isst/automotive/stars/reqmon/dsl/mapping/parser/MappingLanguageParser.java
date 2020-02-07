/*******************************************************************************
 * Copyright (C) 2020 Fraunhofer ISST
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
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
