/**
 * generated by Xtext 2.14.0
 */
package de.fraunhofer.isst.stars.requirementDSL;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Object Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.ObjectSet#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getObjectSet()
 * @model
 * @generated
 */
public interface ObjectSet extends EObject
{
  /**
   * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
   * The list contents are of type {@link de.fraunhofer.isst.stars.requirementDSL.Object}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Elements</em>' containment reference list.
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getObjectSet_Elements()
   * @model containment="true"
   * @generated
   */
  EList<de.fraunhofer.isst.stars.requirementDSL.Object> getElements();

} // ObjectSet