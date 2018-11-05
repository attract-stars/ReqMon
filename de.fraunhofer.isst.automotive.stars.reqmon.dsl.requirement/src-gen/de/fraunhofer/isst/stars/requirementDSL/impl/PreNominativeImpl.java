/**
 * generated by Xtext 2.14.0
 */
package de.fraunhofer.isst.stars.requirementDSL.impl;

import de.fraunhofer.isst.stars.requirementDSL.PreNominative;
import de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pre Nominative</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.PreNominativeImpl#getObject <em>Object</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.PreNominativeImpl#getDeterminer <em>Determiner</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.PreNominativeImpl#getArticle <em>Article</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PreNominativeImpl extends ActorImpl implements PreNominative
{
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
   * The default value of the '{@link #getDeterminer() <em>Determiner</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDeterminer()
   * @generated
   * @ordered
   */
  protected static final String DETERMINER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDeterminer() <em>Determiner</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDeterminer()
   * @generated
   * @ordered
   */
  protected String determiner = DETERMINER_EDEFAULT;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PreNominativeImpl()
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
    return RequirementDSLPackage.Literals.PRE_NOMINATIVE;
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
      object = new EDataTypeEList<String>(String.class, this, RequirementDSLPackage.PRE_NOMINATIVE__OBJECT);
    }
    return object;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDeterminer()
  {
    return determiner;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDeterminer(String newDeterminer)
  {
    String oldDeterminer = determiner;
    determiner = newDeterminer;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.PRE_NOMINATIVE__DETERMINER, oldDeterminer, determiner));
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
      eNotify(new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.PRE_NOMINATIVE__ARTICLE, oldArticle, article));
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
      case RequirementDSLPackage.PRE_NOMINATIVE__OBJECT:
        return getObject();
      case RequirementDSLPackage.PRE_NOMINATIVE__DETERMINER:
        return getDeterminer();
      case RequirementDSLPackage.PRE_NOMINATIVE__ARTICLE:
        return getArticle();
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
      case RequirementDSLPackage.PRE_NOMINATIVE__OBJECT:
        getObject().clear();
        getObject().addAll((Collection<? extends String>)newValue);
        return;
      case RequirementDSLPackage.PRE_NOMINATIVE__DETERMINER:
        setDeterminer((String)newValue);
        return;
      case RequirementDSLPackage.PRE_NOMINATIVE__ARTICLE:
        setArticle((String)newValue);
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
      case RequirementDSLPackage.PRE_NOMINATIVE__OBJECT:
        getObject().clear();
        return;
      case RequirementDSLPackage.PRE_NOMINATIVE__DETERMINER:
        setDeterminer(DETERMINER_EDEFAULT);
        return;
      case RequirementDSLPackage.PRE_NOMINATIVE__ARTICLE:
        setArticle(ARTICLE_EDEFAULT);
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
      case RequirementDSLPackage.PRE_NOMINATIVE__OBJECT:
        return object != null && !object.isEmpty();
      case RequirementDSLPackage.PRE_NOMINATIVE__DETERMINER:
        return DETERMINER_EDEFAULT == null ? determiner != null : !DETERMINER_EDEFAULT.equals(determiner);
      case RequirementDSLPackage.PRE_NOMINATIVE__ARTICLE:
        return ARTICLE_EDEFAULT == null ? article != null : !ARTICLE_EDEFAULT.equals(article);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
  {
    if (baseClass == de.fraunhofer.isst.stars.requirementDSL.Object.class)
    {
      switch (derivedFeatureID)
      {
        case RequirementDSLPackage.PRE_NOMINATIVE__OBJECT: return RequirementDSLPackage.OBJECT__OBJECT;
        default: return -1;
      }
    }
    return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
  {
    if (baseClass == de.fraunhofer.isst.stars.requirementDSL.Object.class)
    {
      switch (baseFeatureID)
      {
        case RequirementDSLPackage.OBJECT__OBJECT: return RequirementDSLPackage.PRE_NOMINATIVE__OBJECT;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
    result.append(" (object: ");
    result.append(object);
    result.append(", determiner: ");
    result.append(determiner);
    result.append(", article: ");
    result.append(article);
    result.append(')');
    return result.toString();
  }

} //PreNominativeImpl
