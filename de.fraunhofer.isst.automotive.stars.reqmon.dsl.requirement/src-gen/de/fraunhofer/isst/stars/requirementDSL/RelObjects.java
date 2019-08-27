/**
 * generated by Xtext 2.17.1
 */
package de.fraunhofer.isst.stars.requirementDSL;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rel Objects</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.RelObjects#getObject <em>Object</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.RelObjects#getProperty <em>Property</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.RelObjects#getRelConj <em>Rel Conj</em>}</li>
 * </ul>
 *
 * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getRelObjects()
 * @model
 * @generated
 */
public interface RelObjects extends EObject
{
  /**
   * Returns the value of the '<em><b>Object</b></em>' containment reference list.
   * The list contents are of type {@link de.fraunhofer.isst.stars.requirementDSL.Object}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Object</em>' containment reference list.
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getRelObjects_Object()
   * @model containment="true"
   * @generated
   */
  EList<de.fraunhofer.isst.stars.requirementDSL.Object> getObject();

  /**
   * Returns the value of the '<em><b>Property</b></em>' containment reference list.
   * The list contents are of type {@link de.fraunhofer.isst.stars.requirementDSL.Property}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Property</em>' containment reference list.
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getRelObjects_Property()
   * @model containment="true"
   * @generated
   */
  EList<Property> getProperty();

  /**
   * Returns the value of the '<em><b>Rel Conj</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rel Conj</em>' attribute list.
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getRelObjects_RelConj()
   * @model unique="false"
   * @generated
   */
  EList<String> getRelConj();

} // RelObjects