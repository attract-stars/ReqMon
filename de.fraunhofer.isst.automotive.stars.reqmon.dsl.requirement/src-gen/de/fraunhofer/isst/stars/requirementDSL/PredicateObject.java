/**
 * generated by Xtext 2.14.0
 */
package de.fraunhofer.isst.stars.requirementDSL;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Predicate Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PredicateObject#getArticle <em>Article</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PredicateObject#getObject <em>Object</em>}</li>
 * </ul>
 *
 * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPredicateObject()
 * @model
 * @generated
 */
public interface PredicateObject extends EObject
{
  /**
   * Returns the value of the '<em><b>Article</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Article</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Article</em>' containment reference.
   * @see #setArticle(PreNominative)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPredicateObject_Article()
   * @model containment="true"
   * @generated
   */
  PreNominative getArticle();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.PredicateObject#getArticle <em>Article</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Article</em>' containment reference.
   * @see #getArticle()
   * @generated
   */
  void setArticle(PreNominative value);

  /**
   * Returns the value of the '<em><b>Object</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Object</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Object</em>' attribute list.
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPredicateObject_Object()
   * @model unique="false"
   * @generated
   */
  EList<String> getObject();

} // PredicateObject