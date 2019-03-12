/**
 * generated by Xtext 2.16.0
 */
package de.fraunhofer.isst.stars.requirementDSL;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Sentence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getActors <em>Actors</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getProperty <em>Property</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getRela <em>Rela</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getModality <em>Modality</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#isNegation <em>Negation</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getAuxiliarVerb <em>Auxiliar Verb</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getPredObj <em>Pred Obj</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getEnding <em>Ending</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getAuxNeg <em>Aux Neg</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getConstraints <em>Constraints</em>}</li>
 * </ul>
 *
 * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPropertySentence()
 * @model
 * @generated
 */
public interface PropertySentence extends Clause
{
  /**
   * Returns the value of the '<em><b>Actors</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Actors</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Actors</em>' containment reference.
   * @see #setActors(Actors)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPropertySentence_Actors()
   * @model containment="true"
   * @generated
   */
  Actors getActors();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getActors <em>Actors</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Actors</em>' containment reference.
   * @see #getActors()
   * @generated
   */
  void setActors(Actors value);

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
   * Returns the value of the '<em><b>Rela</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rela</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rela</em>' containment reference.
   * @see #setRela(Relation)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPropertySentence_Rela()
   * @model containment="true"
   * @generated
   */
  Relation getRela();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getRela <em>Rela</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rela</em>' containment reference.
   * @see #getRela()
   * @generated
   */
  void setRela(Relation value);

  /**
   * Returns the value of the '<em><b>Modality</b></em>' attribute.
   * The literals are from the enumeration {@link de.fraunhofer.isst.stars.requirementDSL.Modality}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modality</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modality</em>' attribute.
   * @see de.fraunhofer.isst.stars.requirementDSL.Modality
   * @see #setModality(Modality)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPropertySentence_Modality()
   * @model
   * @generated
   */
  Modality getModality();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getModality <em>Modality</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Modality</em>' attribute.
   * @see de.fraunhofer.isst.stars.requirementDSL.Modality
   * @see #getModality()
   * @generated
   */
  void setModality(Modality value);

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
   * Returns the value of the '<em><b>Pred Obj</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pred Obj</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pred Obj</em>' containment reference.
   * @see #setPredObj(PredOrObject)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPropertySentence_PredObj()
   * @model containment="true"
   * @generated
   */
  PredOrObject getPredObj();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getPredObj <em>Pred Obj</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pred Obj</em>' containment reference.
   * @see #getPredObj()
   * @generated
   */
  void setPredObj(PredOrObject value);

  /**
   * Returns the value of the '<em><b>Ending</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ending</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ending</em>' containment reference.
   * @see #setEnding(SentenceEnding)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPropertySentence_Ending()
   * @model containment="true"
   * @generated
   */
  SentenceEnding getEnding();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getEnding <em>Ending</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ending</em>' containment reference.
   * @see #getEnding()
   * @generated
   */
  void setEnding(SentenceEnding value);

  /**
   * Returns the value of the '<em><b>Aux Neg</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Aux Neg</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Aux Neg</em>' containment reference.
   * @see #setAuxNeg(AuxNeg)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPropertySentence_AuxNeg()
   * @model containment="true"
   * @generated
   */
  AuxNeg getAuxNeg();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getAuxNeg <em>Aux Neg</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Aux Neg</em>' containment reference.
   * @see #getAuxNeg()
   * @generated
   */
  void setAuxNeg(AuxNeg value);

  /**
   * Returns the value of the '<em><b>Constraints</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Constraints</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Constraints</em>' containment reference.
   * @see #setConstraints(Constraints)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPropertySentence_Constraints()
   * @model containment="true"
   * @generated
   */
  Constraints getConstraints();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.PropertySentence#getConstraints <em>Constraints</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Constraints</em>' containment reference.
   * @see #getConstraints()
   * @generated
   */
  void setConstraints(Constraints value);

} // PropertySentence
