/**
 * 
 */
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.importer;

import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.data.SemanticTextElement;


/**
 * @author mmauritz
 *
 */
public interface IDslAnalyzer {

    /**
     * Takes the EMF-Model of the AST anad analyzes it.
     * 
     * @param model
     * @return 
     */

    public Set<SemanticTextElement> analyze(EObject model);

}
