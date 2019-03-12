/**
 * generated by Xtext 2.16.0
 */
package de.fraunhofer.isst.stars.requirementDSL.impl;

import de.fraunhofer.isst.stars.requirementDSL.IntervallConstraints;
import de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage;
import de.fraunhofer.isst.stars.requirementDSL.Value;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Intervall Constraints</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.IntervallConstraintsImpl#getLower <em>Lower</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.IntervallConstraintsImpl#getHigher <em>Higher</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IntervallConstraintsImpl extends UnitConstraintsImpl implements IntervallConstraints
{
  /**
   * The cached value of the '{@link #getLower() <em>Lower</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLower()
   * @generated
   * @ordered
   */
  protected Value lower;

  /**
   * The cached value of the '{@link #getHigher() <em>Higher</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHigher()
   * @generated
   * @ordered
   */
  protected Value higher;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IntervallConstraintsImpl()
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
    return RequirementDSLPackage.Literals.INTERVALL_CONSTRAINTS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Value getLower()
  {
    return lower;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLower(Value newLower, NotificationChain msgs)
  {
    Value oldLower = lower;
    lower = newLower;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.INTERVALL_CONSTRAINTS__LOWER, oldLower, newLower);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLower(Value newLower)
  {
    if (newLower != lower)
    {
      NotificationChain msgs = null;
      if (lower != null)
        msgs = ((InternalEObject)lower).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.INTERVALL_CONSTRAINTS__LOWER, null, msgs);
      if (newLower != null)
        msgs = ((InternalEObject)newLower).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.INTERVALL_CONSTRAINTS__LOWER, null, msgs);
      msgs = basicSetLower(newLower, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.INTERVALL_CONSTRAINTS__LOWER, newLower, newLower));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Value getHigher()
  {
    return higher;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetHigher(Value newHigher, NotificationChain msgs)
  {
    Value oldHigher = higher;
    higher = newHigher;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.INTERVALL_CONSTRAINTS__HIGHER, oldHigher, newHigher);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHigher(Value newHigher)
  {
    if (newHigher != higher)
    {
      NotificationChain msgs = null;
      if (higher != null)
        msgs = ((InternalEObject)higher).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.INTERVALL_CONSTRAINTS__HIGHER, null, msgs);
      if (newHigher != null)
        msgs = ((InternalEObject)newHigher).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.INTERVALL_CONSTRAINTS__HIGHER, null, msgs);
      msgs = basicSetHigher(newHigher, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.INTERVALL_CONSTRAINTS__HIGHER, newHigher, newHigher));
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
      case RequirementDSLPackage.INTERVALL_CONSTRAINTS__LOWER:
        return basicSetLower(null, msgs);
      case RequirementDSLPackage.INTERVALL_CONSTRAINTS__HIGHER:
        return basicSetHigher(null, msgs);
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
      case RequirementDSLPackage.INTERVALL_CONSTRAINTS__LOWER:
        return getLower();
      case RequirementDSLPackage.INTERVALL_CONSTRAINTS__HIGHER:
        return getHigher();
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
      case RequirementDSLPackage.INTERVALL_CONSTRAINTS__LOWER:
        setLower((Value)newValue);
        return;
      case RequirementDSLPackage.INTERVALL_CONSTRAINTS__HIGHER:
        setHigher((Value)newValue);
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
      case RequirementDSLPackage.INTERVALL_CONSTRAINTS__LOWER:
        setLower((Value)null);
        return;
      case RequirementDSLPackage.INTERVALL_CONSTRAINTS__HIGHER:
        setHigher((Value)null);
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
      case RequirementDSLPackage.INTERVALL_CONSTRAINTS__LOWER:
        return lower != null;
      case RequirementDSLPackage.INTERVALL_CONSTRAINTS__HIGHER:
        return higher != null;
    }
    return super.eIsSet(featureID);
  }

} //IntervallConstraintsImpl
