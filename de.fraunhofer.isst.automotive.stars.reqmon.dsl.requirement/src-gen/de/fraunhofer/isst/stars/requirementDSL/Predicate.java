/**
 * generated by Xtext 2.17.1
 */
package de.fraunhofer.isst.stars.requirementDSL;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Predicate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.Predicate#getPredicates <em>Predicates</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.Predicate#getObject <em>Object</em>}</li>
 * </ul>
 *
 * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPredicate()
 * @model
 * @generated
 */
public interface Predicate extends EObject
{
  /**
   * Returns the value of the '<em><b>Predicates</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Predicates</em>' attribute list.
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPredicate_Predicates()
   * @model unique="false"
   * @generated
   */
  EList<String> getPredicates();

  /**
   * Returns the value of the '<em><b>Object</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Object</em>' containment reference.
   * @see #setObject(PredicateObject)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPredicate_Object()
   * @model containment="true"
   * @generated
   */
  PredicateObject getObject();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.Predicate#getObject <em>Object</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Object</em>' containment reference.
   * @see #getObject()
   * @generated
   */
  void setObject(PredicateObject value);

} // Predicate
