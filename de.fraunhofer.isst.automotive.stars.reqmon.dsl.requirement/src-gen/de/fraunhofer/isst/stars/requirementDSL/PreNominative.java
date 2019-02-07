/**
 * generated by Xtext 2.14.0
 */
package de.fraunhofer.isst.stars.requirementDSL;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pre Nominative</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PreNominative#getDeterminer <em>Determiner</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.PreNominative#getArticle <em>Article</em>}</li>
 * </ul>
 *
 * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPreNominative()
 * @model
 * @generated
 */
public interface PreNominative extends Actor, de.fraunhofer.isst.stars.requirementDSL.Object
{
  /**
   * Returns the value of the '<em><b>Determiner</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Determiner</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Determiner</em>' attribute.
   * @see #setDeterminer(String)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPreNominative_Determiner()
   * @model
   * @generated
   */
  String getDeterminer();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.PreNominative#getDeterminer <em>Determiner</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Determiner</em>' attribute.
   * @see #getDeterminer()
   * @generated
   */
  void setDeterminer(String value);

  /**
   * Returns the value of the '<em><b>Article</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Article</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Article</em>' attribute.
   * @see #setArticle(String)
   * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getPreNominative_Article()
   * @model
   * @generated
   */
  String getArticle();

  /**
   * Sets the value of the '{@link de.fraunhofer.isst.stars.requirementDSL.PreNominative#getArticle <em>Article</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Article</em>' attribute.
   * @see #getArticle()
   * @generated
   */
  void setArticle(String value);

} // PreNominative