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
