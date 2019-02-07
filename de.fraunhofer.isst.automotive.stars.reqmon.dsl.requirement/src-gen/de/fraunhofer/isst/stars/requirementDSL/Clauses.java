/**
 * generated by Xtext 2.14.0
 */
package de.fraunhofer.isst.stars.requirementDSL;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Clauses</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.Clauses#getClauses <em>Clauses</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.Clauses#getConjunction <em>Conjunction</em>}</li>
 * </ul>
 *
 * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getClauses()
 * @model
 * @generated
 */
public interface Clauses extends EObject
{
  /**
   * Returns the value of the '<em><b>Clauses</b></em>' containment reference list.
   * The list contents are of type {@link de.fraunhofer.isst.stars.requirementDSL.Clause}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Clauses</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Clauses</em>' containment reference list.
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getClauses_Clauses()
   * @model containment="true"
   * @generated
   */
  EList<Clause> getClauses();

  /**
   * Returns the value of the '<em><b>Conjunction</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Conjunction</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Conjunction</em>' attribute list.
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getClauses_Conjunction()
   * @model unique="false"
   * @generated
   */
  EList<String> getConjunction();

} // Clauses