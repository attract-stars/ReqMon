/**
 * generated by Xtext 2.17.1
 */
package de.fraunhofer.isst.stars.requirementDSL.impl;

import de.fraunhofer.isst.stars.requirementDSL.PredOrObject;
import de.fraunhofer.isst.stars.requirementDSL.Predicate;
import de.fraunhofer.isst.stars.requirementDSL.PredicateObject;
import de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pred Or Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.PredOrObjectImpl#getPredicate <em>Predicate</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.PredOrObjectImpl#getPredObj <em>Pred Obj</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PredOrObjectImpl extends MinimalEObjectImpl.Container implements PredOrObject
{
  /**
   * The cached value of the '{@link #getPredicate() <em>Predicate</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPredicate()
   * @generated
   * @ordered
   */
  protected Predicate predicate;

  /**
   * The cached value of the '{@link #getPredObj() <em>Pred Obj</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPredObj()
   * @generated
   * @ordered
   */
  protected PredicateObject predObj;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PredOrObjectImpl()
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
    return RequirementDSLPackage.Literals.PRED_OR_OBJECT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Predicate getPredicate()
  {
    return predicate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPredicate(Predicate newPredicate, NotificationChain msgs)
  {
    Predicate oldPredicate = predicate;
    predicate = newPredicate;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.PRED_OR_OBJECT__PREDICATE, oldPredicate, newPredicate);
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
  public void setPredicate(Predicate newPredicate)
  {
    if (newPredicate != predicate)
    {
      NotificationChain msgs = null;
      if (predicate != null)
        msgs = ((InternalEObject)predicate).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.PRED_OR_OBJECT__PREDICATE, null, msgs);
      if (newPredicate != null)
        msgs = ((InternalEObject)newPredicate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.PRED_OR_OBJECT__PREDICATE, null, msgs);
      msgs = basicSetPredicate(newPredicate, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.PRED_OR_OBJECT__PREDICATE, newPredicate, newPredicate));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public PredicateObject getPredObj()
  {
    return predObj;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPredObj(PredicateObject newPredObj, NotificationChain msgs)
  {
    PredicateObject oldPredObj = predObj;
    predObj = newPredObj;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.PRED_OR_OBJECT__PRED_OBJ, oldPredObj, newPredObj);
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
  public void setPredObj(PredicateObject newPredObj)
  {
    if (newPredObj != predObj)
    {
      NotificationChain msgs = null;
      if (predObj != null)
        msgs = ((InternalEObject)predObj).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.PRED_OR_OBJECT__PRED_OBJ, null, msgs);
      if (newPredObj != null)
        msgs = ((InternalEObject)newPredObj).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.PRED_OR_OBJECT__PRED_OBJ, null, msgs);
      msgs = basicSetPredObj(newPredObj, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.PRED_OR_OBJECT__PRED_OBJ, newPredObj, newPredObj));
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
      case RequirementDSLPackage.PRED_OR_OBJECT__PREDICATE:
        return basicSetPredicate(null, msgs);
      case RequirementDSLPackage.PRED_OR_OBJECT__PRED_OBJ:
        return basicSetPredObj(null, msgs);
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
      case RequirementDSLPackage.PRED_OR_OBJECT__PREDICATE:
        return getPredicate();
      case RequirementDSLPackage.PRED_OR_OBJECT__PRED_OBJ:
        return getPredObj();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case RequirementDSLPackage.PRED_OR_OBJECT__PREDICATE:
        setPredicate((Predicate)newValue);
        return;
      case RequirementDSLPackage.PRED_OR_OBJECT__PRED_OBJ:
        setPredObj((PredicateObject)newValue);
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
      case RequirementDSLPackage.PRED_OR_OBJECT__PREDICATE:
        setPredicate((Predicate)null);
        return;
      case RequirementDSLPackage.PRED_OR_OBJECT__PRED_OBJ:
        setPredObj((PredicateObject)null);
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
      case RequirementDSLPackage.PRED_OR_OBJECT__PREDICATE:
        return predicate != null;
      case RequirementDSLPackage.PRED_OR_OBJECT__PRED_OBJ:
        return predObj != null;
    }
    return super.eIsSet(featureID);
  }

} //PredOrObjectImpl