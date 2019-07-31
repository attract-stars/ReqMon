/**
 * generated by Xtext 2.17.1
 */
package de.fraunhofer.isst.stars.requirementDSL;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Modality Sentence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.ModalitySentence#getBegin <em>Begin</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.ModalitySentence#getActors <em>Actors</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.ModalitySentence#getModelity <em>Modelity</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.ModalitySentence#isNegation <em>Negation</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.ModalitySentence#getAuxiliarVerb <em>Auxiliar Verb</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.ModalitySentence#getPredicate <em>Predicate</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.ModalitySentence#getEnding <em>Ending</em>}</li>
 * </ul>
 *
 * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getModalitySentence()
 * @model
 * @generated
 */
public interface ModalitySentence extends Clause
{
  /**
   * Returns the value of the '<em><b>Begin</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Begin</em>' containment reference.
   * @see #setBegin(SentenceBegin)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getModalitySentence_Begin()
   * @model containment="true"
   * @generated
   */
  SentenceBegin getBegin();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.ModalitySentence#getBegin <em>Begin</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Begin</em>' containment reference.
   * @see #getBegin()
   * @generated
   */
  void setBegin(SentenceBegin value);

  /**
   * Returns the value of the '<em><b>Actors</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Actors</em>' containment reference.
   * @see #setActors(Actors)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getModalitySentence_Actors()
   * @model containment="true"
   * @generated
   */
  Actors getActors();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.ModalitySentence#getActors <em>Actors</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Actors</em>' containment reference.
   * @see #getActors()
   * @generated
   */
  void setActors(Actors value);

  /**
   * Returns the value of the '<em><b>Modelity</b></em>' attribute.
   * The literals are from the enumeration {@link de.fraunhofer.isst.stars.requirementDSL.Modality}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modelity</em>' attribute.
   * @see de.fraunhofer.isst.stars.requirementDSL.Modality
   * @see #setModelity(Modality)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getModalitySentence_Modelity()
   * @model
   * @generated
   */
  Modality getModelity();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.ModalitySentence#getModelity <em>Modelity</em>}' attribute.
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
   * <!-- end-user-doc -->
   * @return the value of the '<em>Negation</em>' attribute.
   * @see #setNegation(boolean)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getModalitySentence_Negation()
   * @model
   * @generated
   */
  boolean isNegation();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.ModalitySentence#isNegation <em>Negation</em>}' attribute.
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
   * <!-- end-user-doc -->
   * @return the value of the '<em>Auxiliar Verb</em>' attribute.
   * @see #setAuxiliarVerb(String)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getModalitySentence_AuxiliarVerb()
   * @model
   * @generated
   */
  String getAuxiliarVerb();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.ModalitySentence#getAuxiliarVerb <em>Auxiliar Verb</em>}' attribute.
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
   * <!-- end-user-doc -->
   * @return the value of the '<em>Predicate</em>' containment reference.
   * @see #setPredicate(Predicate)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getModalitySentence_Predicate()
   * @model containment="true"
   * @generated
   */
  Predicate getPredicate();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.ModalitySentence#getPredicate <em>Predicate</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Predicate</em>' containment reference.
   * @see #getPredicate()
   * @generated
   */
  void setPredicate(Predicate value);

  /**
   * Returns the value of the '<em><b>Ending</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ending</em>' containment reference.
   * @see #setEnding(SentenceEnding)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getModalitySentence_Ending()
   * @model containment="true"
   * @generated
   */
  SentenceEnding getEnding();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.ModalitySentence#getEnding <em>Ending</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ending</em>' containment reference.
   * @see #getEnding()
   * @generated
   */
  void setEnding(SentenceEnding value);

} // ModalitySentence
