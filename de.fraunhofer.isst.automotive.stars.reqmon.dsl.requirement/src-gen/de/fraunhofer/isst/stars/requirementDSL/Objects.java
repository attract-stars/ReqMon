/**
 * generated by Xtext 2.14.0
 */
package de.fraunhofer.isst.stars.requirementDSL;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Objects</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.Objects#getObjects <em>Objects</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.Objects#getConjunction <em>Conjunction</em>}</li>
 * </ul>
 *
 * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getObjects()
 * @model
 * @generated
 */
public interface Objects extends EObject
{
  /**
   * Returns the value of the '<em><b>Objects</b></em>' containment reference list.
   * The list contents are of type {@link de.fraunhofer.isst.stars.requirementDSL.Object}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Objects</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Objects</em>' containment reference list.
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getObjects_Objects()
   * @model containment="true"
   * @generated
   */
  EList<de.fraunhofer.isst.stars.requirementDSL.Object> getObjects();

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
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getObjects_Conjunction()
   * @model unique="false"
   * @generated
   */
  EList<String> getConjunction();

} // Objects