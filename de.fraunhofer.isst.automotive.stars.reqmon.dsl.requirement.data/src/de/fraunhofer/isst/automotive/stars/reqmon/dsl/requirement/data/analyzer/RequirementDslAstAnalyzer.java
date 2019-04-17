package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.analyzer;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.importer.IDslAnalyzer;
import de.fraunhofer.isst.stars.requirementDSL.Actor;
import de.fraunhofer.isst.stars.requirementDSL.ConditionalClause;
import de.fraunhofer.isst.stars.requirementDSL.Model;
import de.fraunhofer.isst.stars.requirementDSL.Object;
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
	// Leaves-> Leaves seems the only think interesting for consolidating the Data -> No also prior objects like constraints are interesting
	// Model objects
	// Whats with relations that are defined over a particular set of other objects
	// / types /etc.
	while (modelIterator.hasNext()) {
	    EObject obj = modelIterator.next();
	    if(obj instanceof Requirement) {
	    	System.out.println(obj.toString());
	    }
	    if(obj instanceof Object) {
	    	System.out.println(obj.toString());
	    }
	    if(obj instanceof Actor) {
	    	System.out.println(obj.toString());
	    }
	}
	
	//TODO WAS IST HIER EIGENTLICH DER RÜCKGABEWERT?
//	Wir brauchen einmal die Elemente für das Mapping(Editor) -> Die annotieren wir hier ja sowieso
//	Wir brauchen den Lookup für die einzelnen Elemente / Typen / Entitäten um sie in der Code Analyse nutzen zu können
//	->Brauchen wir ein Lookup Repository?
	
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
