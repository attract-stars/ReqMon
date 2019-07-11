/**
 * generated by Xtext 2.17.1
 */
package de.fraunhofer.isst.stars.requirementDSL.impl;

import de.fraunhofer.isst.stars.requirementDSL.PreNominative;
import de.fraunhofer.isst.stars.requirementDSL.PredicateObject;
import de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Predicate Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.PredicateObjectImpl#getArticle <em>Article</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.PredicateObjectImpl#getObject <em>Object</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PredicateObjectImpl extends MinimalEObjectImpl.Container implements PredicateObject
{
  /**
   * The cached value of the '{@link #getArticle() <em>Article</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArticle()
   * @generated
   * @ordered
   */
  protected PreNominative article;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PredicateObjectImpl()
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
    return RequirementDSLPackage.Literals.PREDICATE_OBJECT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public PreNominative getArticle()
  {
    return article;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetArticle(PreNominative newArticle, NotificationChain msgs)
  {
    PreNominative oldArticle = article;
    article = newArticle;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.PREDICATE_OBJECT__ARTICLE, oldArticle, newArticle);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setArticle(PreNominative newArticle)
  {
    if (newArticle != article)
    {
      NotificationChain msgs = null;
      if (article != null)
        msgs = ((InternalEObject)article).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.PREDICATE_OBJECT__ARTICLE, null, msgs);
      if (newArticle != null)
        msgs = ((InternalEObject)newArticle).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.PREDICATE_OBJECT__ARTICLE, null, msgs);
      msgs = basicSetArticle(newArticle, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.PREDICATE_OBJECT__ARTICLE, newArticle, newArticle));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<String> getObject()
  {
    if (object == null)
    {
      object = new EDataTypeEList<String>(String.class, this, RequirementDSLPackage.PREDICATE_OBJECT__OBJECT);
    }
    return object;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case RequirementDSLPackage.PREDICATE_OBJECT__ARTICLE:
        return basicSetArticle(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case RequirementDSLPackage.PREDICATE_OBJECT__ARTICLE:
        return getArticle();
      case RequirementDSLPackage.PREDICATE_OBJECT__OBJECT:
        return getObject();
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
      case RequirementDSLPackage.PREDICATE_OBJECT__ARTICLE:
        setArticle((PreNominative)newValue);
        return;
      case RequirementDSLPackage.PREDICATE_OBJECT__OBJECT:
        getObject().clear();
        getObject().addAll((Collection<? extends String>)newValue);
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
      case RequirementDSLPackage.PREDICATE_OBJECT__ARTICLE:
        setArticle((PreNominative)null);
        return;
      case RequirementDSLPackage.PREDICATE_OBJECT__OBJECT:
        getObject().clear();
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
      case RequirementDSLPackage.PREDICATE_OBJECT__ARTICLE:
        return article != null;
      case RequirementDSLPackage.PREDICATE_OBJECT__OBJECT:
        return object != null && !object.isEmpty();
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
    result.append(" (object: ");
    result.append(object);
    result.append(')');
    return result.toString();
  }

} //PredicateObjectImpl
