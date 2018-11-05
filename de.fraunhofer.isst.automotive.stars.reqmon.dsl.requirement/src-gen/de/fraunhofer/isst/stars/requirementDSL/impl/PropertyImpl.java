/**
 * generated by Xtext 2.14.0
 */
package de.fraunhofer.isst.stars.requirementDSL.impl;

import de.fraunhofer.isst.stars.requirementDSL.Property;
import de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.PropertyImpl#getQuantifier <em>Quantifier</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.PropertyImpl#getArticle <em>Article</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.PropertyImpl#getObject <em>Object</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.PropertyImpl#getProperty <em>Property</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PropertyImpl extends MinimalEObjectImpl.Container implements Property
{
  /**
   * The default value of the '{@link #getQuantifier() <em>Quantifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getQuantifier()
   * @generated
   * @ordered
   */
  protected static final String QUANTIFIER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getQuantifier() <em>Quantifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getQuantifier()
   * @generated
   * @ordered
   */
  protected String quantifier = QUANTIFIER_EDEFAULT;

  /**
   * The default value of the '{@link #getArticle() <em>Article</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArticle()
   * @generated
   * @ordered
   */
  protected static final String ARTICLE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getArticle() <em>Article</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArticle()
   * @generated
   * @ordered
   */
  protected String article = ARTICLE_EDEFAULT;

  /**
   * The cached value of the '{@link #getObject() <em>Object</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getObject()
   * @generated
   * @ordered
   */
  protected EList<String> object;

  /**
   * The cached value of the '{@link #getProperty() <em>Property</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProperty()
   * @generated
   * @ordered
   */
  protected EList<String> property;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PropertyImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return RequirementDSLPackage.Literals.PROPERTY;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getQuantifier()
  {
    return quantifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setQuantifier(String newQuantifier)
  {
    String oldQuantifier = quantifier;
    quantifier = newQuantifier;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.PROPERTY__QUANTIFIER, oldQuantifier, quantifier));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getArticle()
  {
    return article;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setArticle(String newArticle)
  {
    String oldArticle = article;
    article = newArticle;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.PROPERTY__ARTICLE, oldArticle, article));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getObject()
  {
    if (object == null)
    {
      object = new EDataTypeEList<String>(String.class, this, RequirementDSLPackage.PROPERTY__OBJECT);
    }
    return object;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getProperty()
  {
    if (property == null)
    {
      property = new EDataTypeEList<String>(String.class, this, RequirementDSLPackage.PROPERTY__PROPERTY);
    }
    return property;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case RequirementDSLPackage.PROPERTY__QUANTIFIER:
        return getQuantifier();
      case RequirementDSLPackage.PROPERTY__ARTICLE:
        return getArticle();
      case RequirementDSLPackage.PROPERTY__OBJECT:
        return getObject();
      case RequirementDSLPackage.PROPERTY__PROPERTY:
        return getProperty();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case RequirementDSLPackage.PROPERTY__QUANTIFIER:
        setQuantifier((String)newValue);
        return;
      case RequirementDSLPackage.PROPERTY__ARTICLE:
        setArticle((String)newValue);
        return;
      case RequirementDSLPackage.PROPERTY__OBJECT:
        getObject().clear();
        getObject().addAll((Collection<? extends String>)newValue);
        return;
      case RequirementDSLPackage.PROPERTY__PROPERTY:
        getProperty().clear();
        getProperty().addAll((Collection<? extends String>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case RequirementDSLPackage.PROPERTY__QUANTIFIER:
        setQuantifier(QUANTIFIER_EDEFAULT);
        return;
      case RequirementDSLPackage.PROPERTY__ARTICLE:
        setArticle(ARTICLE_EDEFAULT);
        return;
      case RequirementDSLPackage.PROPERTY__OBJECT:
        getObject().clear();
        return;
      case RequirementDSLPackage.PROPERTY__PROPERTY:
        getProperty().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case RequirementDSLPackage.PROPERTY__QUANTIFIER:
        return QUANTIFIER_EDEFAULT == null ? quantifier != null : !QUANTIFIER_EDEFAULT.equals(quantifier);
      case RequirementDSLPackage.PROPERTY__ARTICLE:
        return ARTICLE_EDEFAULT == null ? article != null : !ARTICLE_EDEFAULT.equals(article);
      case RequirementDSLPackage.PROPERTY__OBJECT:
        return object != null && !object.isEmpty();
      case RequirementDSLPackage.PROPERTY__PROPERTY:
        return property != null && !property.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (quantifier: ");
    result.append(quantifier);
    result.append(", article: ");
    result.append(article);
    result.append(", object: ");
    result.append(object);
    result.append(", property: ");
    result.append(property);
    result.append(')');
    return result.toString();
  }

} //PropertyImpl
