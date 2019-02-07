/**
 * generated by Xtext 2.14.0
 */
package de.fraunhofer.isst.stars.requirementDSL;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>relative Sentence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.relativeSentence#getPronoun <em>Pronoun</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.relativeSentence#getModelity <em>Modelity</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.relativeSentence#isNegation <em>Negation</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.relativeSentence#getPredicate <em>Predicate</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.relativeSentence#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.relativeSentence#getAuxiliar <em>Auxiliar</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.relativeSentence#getClause <em>Clause</em>}</li>
 * </ul>
 *
 * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getrelativeSentence()
 * @model
 * @generated
 */
public interface relativeSentence extends EObject
{
  /**
   * Returns the value of the '<em><b>Pronoun</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pronoun</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pronoun</em>' attribute.
   * @see #setPronoun(String)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getrelativeSentence_Pronoun()
   * @model
   * @generated
   */
  String getPronoun();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.relativeSentence#getPronoun <em>Pronoun</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pronoun</em>' attribute.
   * @see #getPronoun()
   * @generated
   */
  void setPronoun(String value);

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
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getrelativeSentence_Modelity()
   * @model
   * @generated
   */
  Modality getModelity();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.relativeSentence#getModelity <em>Modelity</em>}' attribute.
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
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getrelativeSentence_Negation()
   * @model
   * @generated
   */
  boolean isNegation();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.relativeSentence#isNegation <em>Negation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Negation</em>' attribute.
   * @see #isNegation()
   * @generated
   */
  void setNegation(boolean value);

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
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getrelativeSentence_Predicate()
   * @model containment="true"
   * @generated
   */
  Predicate getPredicate();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.relativeSentence#getPredicate <em>Predicate</em>}' containment reference.
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
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getrelativeSentence_Constraints()
   * @model containment="true"
   * @generated
   */
  EList<Constraints> getConstraints();

  /**
   * Returns the value of the '<em><b>Auxiliar</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Auxiliar</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Auxiliar</em>' attribute.
   * @see #setAuxiliar(String)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getrelativeSentence_Auxiliar()
   * @model
   * @generated
   */
  String getAuxiliar();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.relativeSentence#getAuxiliar <em>Auxiliar</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Auxiliar</em>' attribute.
   * @see #getAuxiliar()
   * @generated
   */
  void setAuxiliar(String value);

  /**
   * Returns the value of the '<em><b>Clause</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Clause</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Clause</em>' containment reference.
   * @see #setClause(Clause)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getrelativeSentence_Clause()
   * @model containment="true"
   * @generated
   */
  Clause getClause();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.relativeSentence#getClause <em>Clause</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Clause</em>' containment reference.
   * @see #getClause()
   * @generated
   */
  void setClause(Clause value);

} // relativeSentence