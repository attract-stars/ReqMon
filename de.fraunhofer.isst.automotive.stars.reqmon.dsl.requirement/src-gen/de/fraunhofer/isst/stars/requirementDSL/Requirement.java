/**
 * generated by Xtext 2.17.1
 */
package de.fraunhofer.isst.stars.requirementDSL;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Requirement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.Requirement#getReqID <em>Req ID</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.Requirement#getText <em>Text</em>}</li>
 * </ul>
 *
 * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getRequirement()
 * @model
 * @generated
 */
public interface Requirement extends EObject
{
  /**
   * Returns the value of the '<em><b>Req ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Req ID</em>' attribute.
   * @see #setReqID(String)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getRequirement_ReqID()
   * @model
   * @generated
   */
  String getReqID();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.Requirement#getReqID <em>Req ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Req ID</em>' attribute.
   * @see #getReqID()
   * @generated
   */
  void setReqID(String value);

  /**
   * Returns the value of the '<em><b>Text</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Text</em>' containment reference.
   * @see #setText(RequirementText)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getRequirement_Text()
   * @model containment="true"
   * @generated
   */
  RequirementText getText();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.Requirement#getText <em>Text</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Text</em>' containment reference.
   * @see #getText()
   * @generated
   */
  void setText(RequirementText value);

} // Requirement
