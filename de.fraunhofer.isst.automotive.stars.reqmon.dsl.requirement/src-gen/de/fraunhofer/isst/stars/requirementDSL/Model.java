/**
 * generated by Xtext 2.14.0
 */
package de.fraunhofer.isst.stars.requirementDSL;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.Model#getRequirements <em>Requirements</em>}</li>
 * </ul>
 *
 * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getModel()
 * @model
 * @generated
 */
public interface Model extends EObject
{
  /**
   * Returns the value of the '<em><b>Requirements</b></em>' containment reference list.
   * The list contents are of type {@link de.fraunhofer.isst.stars.requirementDSL.Requirement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Requirements</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Requirements</em>' containment reference list.
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getModel_Requirements()
   * @model containment="true"
   * @generated
   */
  EList<Requirement> getRequirements();

} // Model
