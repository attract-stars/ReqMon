package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.analyzer;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.importer.IDslAnalyzer;
import de.fraunhofer.isst.stars.requirementDSL.ConditionalClause;
import de.fraunhofer.isst.stars.requirementDSL.Model;
import de.fraunhofer.isst.stars.requirementDSL.Requirement;
import de.fraunhofer.isst.stars.requirementDSL.RequirementText;

/**
 * @author mmauritz
 *
 */
public class RequirementDslAstAnalyzer implements IDslAnalyzer {

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.IAstAnalyzer#
     * analyze(de.fraunhofer.isst.stars.requirementDSL.Model)
     */
    @Override
    public void analyze(EObject model) {
    System.out.println("Analyzing Requirement DSL");
   	TreeIterator<EObject> modelIterator = model.eAllContents();
	// TODO We might have to implement a Specific Adapter for the Work on the
	// Leaves-> Leaves seems the only think interesting for consolidating the Data
	// Model objects
	// Whats with relations that are defined over a particular set of other objects
	// / types /etc.
	if (modelIterator.hasNext()) {
	    EObject obj = modelIterator.next();
	    System.out.println(obj.toString());
	}
	// EList<EStructuralFeature> features =
	// object.eClass().getEAllStructuralFeatures();// gives only Attributes and
	// // References
	// for (EStructuralFeature feat : features) {
	// if(object.eIsSet(feat)) {
	//
	// } else {
	// Object featObj = object.eGet(feat);
	// if(featObj instanceof EObject) {
	//
	// }
	// }
	//
	//
	// }
	
	// throw new UnknownError("Language is not known for Analysis!");
    }

    protected void analyzeRequirements(org.eclipse.emf.common.util.EList<Requirement> requirements) {
	for (Requirement req : requirements) {
	    analyzeRequirementText(req.getText());
	}
    }

    /**
     * @param text
     */
    protected void analyzeRequirementText(RequirementText text) {
	analzyeCondClauses(text.getCondClauses());
    }

    /**
     * @param condClauses
     */
    protected void analzyeCondClauses(EList<ConditionalClause> condClauses) {
	for (ConditionalClause clause : condClauses) {
	    analyzeConditionalClause(clause);
	}
	
    }

    /**
     * @param clause
     */
    private void analyzeConditionalClause(ConditionalClause clause) {
	// TODO Auto-generated method stub

    }

}
