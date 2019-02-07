/**
 * generated by Xtext 2.14.0
 */
package de.fraunhofer.isst.stars.requirementDSL;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Sentence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getProperty <em>Property</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getModelity <em>Modelity</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#isNegation <em>Negation</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getAuxiliarVerb <em>Auxiliar Verb</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getPredicate <em>Predicate</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getObject <em>Object</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getPredicateWord <em>Predicate Word</em>}</li>
 * </ul>
 *
 * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPropertySentence()
 * @model
 * @generated
 */
public interface PropertySentence extends Clause
{
  /**
   * Returns the value of the '<em><b>Property</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Property</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Property</em>' containment reference.
   * @see #setProperty(Property)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPropertySentence_Property()
   * @model containment="true"
   * @generated
   */
  Property getProperty();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getProperty <em>Property</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Property</em>' containment reference.
   * @see #getProperty()
   * @generated
   */
  void setProperty(Property value);

  /**
   * Returns the value of the '<em><b>Modelity</b></em>' attribute.
   * The literals are from the enumeration {@link de.fraunhofer.isst.stars.requirementDSL.Modality}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modelity</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modelity</em>' attribute.
   * @see de.fraunhofer.isst.stars.requirementDSL.Modality
   * @see #setModelity(Modality)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPropertySentence_Modelity()
   * @model
   * @generated
   */
  Modality getModelity();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getModelity <em>Modelity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Modelity</em>' attribute.
   * @see de.fraunhofer.isst.stars.requirementDSL.Modality
   * @see #getModelity()
   * @generated
   */
  void setModelity(Modality value);

  /**
   * Returns the value of the '<em><b>Negation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Negation</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Negation</em>' attribute.
   * @see #setNegation(boolean)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPropertySentence_Negation()
   * @model
   * @generated
   */
  boolean isNegation();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#isNegation <em>Negation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Negation</em>' attribute.
   * @see #isNegation()
   * @generated
   */
  void setNegation(boolean value);

  /**
   * Returns the value of the '<em><b>Auxiliar Verb</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Auxiliar Verb</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Auxiliar Verb</em>' attribute.
   * @see #setAuxiliarVerb(String)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPropertySentence_AuxiliarVerb()
   * @model
   * @generated
   */
  String getAuxiliarVerb();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getAuxiliarVerb <em>Auxiliar Verb</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Auxiliar Verb</em>' attribute.
   * @see #getAuxiliarVerb()
   * @generated
   */
  void setAuxiliarVerb(String value);

  /**
   * Returns the value of the '<em><b>Predicate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Predicate</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Predicate</em>' containment reference.
   * @see #setPredicate(Predicate)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPropertySentence_Predicate()
   * @model containment="true"
   * @generated
   */
  Predicate getPredicate();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getPredicate <em>Predicate</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Predicate</em>' containment reference.
   * @see #getPredicate()
   * @generated
   */
  void setPredicate(Predicate value);

  /**
   * Returns the value of the '<em><b>Constraints</b></em>' containment reference list.
   * The list contents are of type {@link de.fraunhofer.isst.stars.requirementDSL.Constraints}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Constraints</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Constraints</em>' containment reference list.
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPropertySentence_Constraints()
   * @model containment="true"
   * @generated
   */
  EList<Constraints> getConstraints();

  /**
   * Returns the value of the '<em><b>Object</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Object</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Object</em>' containment reference.
   * @see #setObject(PredicateObject)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPropertySentence_Object()
   * @model containment="true"
   * @generated
   */
  PredicateObject getObject();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getObject <em>Object</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Object</em>' containment reference.
   * @see #getObject()
   * @generated
   */
  void setObject(PredicateObject value);

  /**
   * Returns the value of the '<em><b>Predicate Word</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Predicate Word</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Predicate Word</em>' attribute.
   * @see #setPredicateWord(String)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPropertySentence_PredicateWord()
   * @model
   * @generated
   */
  String getPredicateWord();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getPredicateWord <em>Predicate Word</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Predicate Word</em>' attribute.
   * @see #getPredicateWord()
   * @generated
   */
  void setPredicateWord(String value);

} // PropertySentence