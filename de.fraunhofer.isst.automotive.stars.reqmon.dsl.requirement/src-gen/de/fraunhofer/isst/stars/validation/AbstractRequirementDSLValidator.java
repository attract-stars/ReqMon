/*
 * generated by Xtext 2.16.0
 */
package de.fraunhofer.isst.stars.validation;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;

public abstract class AbstractRequirementDSLValidator extends AbstractDeclarativeValidator {
	
	@Override
	protected List<EPackage> getEPackages() {
		List<EPackage> result = new ArrayList<EPackage>();
		result.add(de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage.eINSTANCE);
		return result;
	}
}
