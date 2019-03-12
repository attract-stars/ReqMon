/**
 * generated by Xtext 2.16.0
 */
package de.fraunhofer.isst.stars.requirementDSL;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Float Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.FloatValue#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getFloatValue()
 * @model
 * @generated
 */
public interface FloatValue extends Value
{
  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see #setValue(float)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getFloatValue_Value()
   * @model
   * @generated
   */
  float getValue();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.FloatValue#getValue <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #getValue()
   * @generated
   */
  void setValue(float value);

} // FloatValue
