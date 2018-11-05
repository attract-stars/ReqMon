/**
 * generated by Xtext 2.14.0
 */
package de.fraunhofer.isst.stars.requirementDSL;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Main Clause</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.MainClause#getModifier <em>Modifier</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.MainClause#getClauses <em>Clauses</em>}</li>
 * </ul>
 *
 * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getMainClause()
 * @model
 * @generated
 */
public interface MainClause extends EObject
{
  /**
   * Returns the value of the '<em><b>Modifier</b></em>' attribute.
   * The literals are from the enumeration {@link de.fraunhofer.isst.stars.requirementDSL.Modifier}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modifier</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modifier</em>' attribute.
   * @see de.fraunhofer.isst.stars.requirementDSL.Modifier
   * @see #setModifier(Modifier)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getMainClause_Modifier()
   * @model
   * @generated
   */
  Modifier getModifier();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.MainClause#getModifier <em>Modifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Modifier</em>' attribute.
   * @see de.fraunhofer.isst.stars.requirementDSL.Modifier
   * @see #getModifier()
   * @generated
   */
  void setModifier(Modifier value);

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
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getMainClause_Clauses()
   * @model containment="true"
   * @generated
   */
  EList<Clause> getClauses();

} // MainClause
