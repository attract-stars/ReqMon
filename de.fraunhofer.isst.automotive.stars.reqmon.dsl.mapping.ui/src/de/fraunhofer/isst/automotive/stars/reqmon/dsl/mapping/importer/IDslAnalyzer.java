/**
 * 
 */
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.mapping.importer;

import org.eclipse.emf.ecore.EObject;


/**
 * @author mmauritz
 *
 */
public interface IDslAnalyzer {

    /**
     * Takes the EMF-Model of the AST anad analyzes it.
     * 
     * @param model
     */
    // TODO WHAT IS THE RETURN TYPE??
    public void analyze(EObject model);

}
