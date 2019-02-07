/**
 * generated by Xtext 2.14.0
 */
package de.fraunhofer.isst.stars.requirementDSL;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Predicate Sentence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PredicateSentence#getActors <em>Actors</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PredicateSentence#getPredicate <em>Predicate</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PredicateSentence#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PredicateSentence#getAuxiliarVerb <em>Auxiliar Verb</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PredicateSentence#isNegation <em>Negation</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PredicateSentence#getObject <em>Object</em>}</li>
 * </ul>
 *
 * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPredicateSentence()
 * @model
 * @generated
 */
public interface PredicateSentence extends Clause
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
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPredicateSentence_Actors()
   * @model containment="true"
   * @generated
   */
  Actors getActors();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.PredicateSentence#getActors <em>Actors</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Actors</em>' containment reference.
   * @see #getActors()
   * @generated
   */
  void setActors(Actors value);

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
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPredicateSentence_Predicate()
   * @model containment="true"
   * @generated
   */
  Predicate getPredicate();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.PredicateSentence#getPredicate <em>Predicate</em>}' containment reference.
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
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPredicateSentence_Constraints()
   * @model containment="true"
   * @generated
   */
  EList<Constraints> getConstraints();

  /**
   * Returns the value of the '<em><b>Auxiliar Verb</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Auxiliar Verb</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Auxiliar Verb</em>' attribute list.
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPredicateSentence_AuxiliarVerb()
   * @model unique="false"
   * @generated
   */
  EList<String> getAuxiliarVerb();

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
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPredicateSentence_Negation()
   * @model
   * @generated
   */
  boolean isNegation();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.PredicateSentence#isNegation <em>Negation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Negation</em>' attribute.
   * @see #isNegation()
   * @generated
   */
  void setNegation(boolean value);

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
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPredicateSentence_Object()
   * @model containment="true"
   * @generated
   */
  PredicateObject getObject();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.PredicateSentence#getObject <em>Object</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Object</em>' containment reference.
   * @see #getObject()
   * @generated
   */
  void setObject(PredicateObject value);

} // PredicateSentence