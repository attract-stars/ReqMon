package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.analyzer;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.data.SemanticTextElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.data.analytics.repository.impl.TextElementRepository;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.importer.IDslAnalyzer;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.RequirementTextElement;
import de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.RequirementTextElementMapping;
import de.fraunhofer.isst.stars.requirementDSL.Actor;
import de.fraunhofer.isst.stars.requirementDSL.ConditionalClause;
import de.fraunhofer.isst.stars.requirementDSL.Object;
import de.fraunhofer.isst.stars.requirementDSL.Requirement;
import de.fraunhofer.isst.stars.requirementDSL.RequirementText;

/**
 * @author mmauritz
 *
 */
//TODO THIS CLASS NEEDS AN LOOKUP FOR EXISTING ELEMENTS
public class RequirementDslAstAnalyzer implements IDslAnalyzer {
	
		
	class DslTextElement {
		EClassifier type;
		String text;
		
		public DslTextElement(EClassifier eType, String text) {
			this.type=eType;
			this.text=text;
		}
		
		/**
		 * @return the type
		 */
		public EClassifier getType() {
			return type;
		}

		/**
		 * @return the text
		 */
		public String getText() {
			return text;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((text == null) ? 0 : text.hashCode());
			result = prime * result + ((type == null) ? 0 : type.hashCode());
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(java.lang.Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			DslTextElement other = (DslTextElement) obj;
//			if (!getOuterType().equals(other.getOuterType()))
//				return false;
			if (text == null) {
				if (other.text != null)
					return false;
			} else if (!text.equals(other.text))
				return false;
			if (type == null) {
				if (other.type != null)
					return false;
			} else if (!type.equals(other.type))
				return false;
			return true;
		}

//		private RequirementDslAstAnalyzer getOuterType() {
//			return RequirementDslAstAnalyzer.this;
//		}		
		
	}
	
	
	
	public RequirementDslAstAnalyzer() {
		
	}

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.IAstAnalyzer#
     * analyze(de.fraunhofer.isst.stars.requirementDSL.Model)
     */
    @Override
    public Set<SemanticTextElement> analyze(EObject model) {
    System.out.println("Analyzing Requirement DSL");
   	TreeIterator<EObject> modelIterator = model.eAllContents();   	
   	
   	//lets lookup if an analysis of the resource exists
   	TextElementRepository eleRepo = TextElementRepository.getInstance();
   	if( eleRepo.containsKey(model.eResource())) {
   		System.out.println("Resource has been analyzed. Recovering previous results");
   		Collection<SemanticTextElement> values = (Collection<SemanticTextElement>) eleRepo.get(model.eResource()).values();
  		return new HashSet<SemanticTextElement>(values);
   	}
   	
   	//STARTING THE ANALYSIS
   	System.out.println("STARTING: Analysis of AST");
   	Set<SemanticTextElement> semElements = new HashSet<SemanticTextElement>();//output for the Mapping View
   	RequirementTextElementMapping mapping = new RequirementTextElementMapping();// THe mapping from syn to sem save in the repository
   	HashMap<DslTextElement, RequirementTextElement> lookup = new HashMap<DslTextElement,RequirementTextElement>();//local lookup to find existing sem elements
   	
   	
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

	//Saving Mapping from syntactic elements to semantic elements in Repository
	eleRepo.put(model.eResource(), mapping);
	return semElements;
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
