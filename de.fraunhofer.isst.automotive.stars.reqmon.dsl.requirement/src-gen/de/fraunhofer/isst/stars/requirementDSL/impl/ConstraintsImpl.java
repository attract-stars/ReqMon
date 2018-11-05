/**
 * generated by Xtext 2.14.0
 */
package de.fraunhofer.isst.stars.requirementDSL.impl;

import de.fraunhofer.isst.stars.requirementDSL.Constraint;
import de.fraunhofer.isst.stars.requirementDSL.Constraints;
import de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage;
import de.fraunhofer.isst.stars.requirementDSL.TimeConstraint;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constraints</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.ConstraintsImpl#getTimeConstraint <em>Time Constraint</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.ConstraintsImpl#getConstraint <em>Constraint</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConstraintsImpl extends MinimalEObjectImpl.Container implements Constraints
{
  /**
   * The cached value of the '{@link #getTimeConstraint() <em>Time Constraint</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTimeConstraint()
   * @generated
   * @ordered
   */
  protected TimeConstraint timeConstraint;

  /**
   * The cached value of the '{@link #getConstraint() <em>Constraint</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstraint()
   * @generated
   * @ordered
   */
  protected Constraint constraint;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ConstraintsImpl()
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
    return RequirementDSLPackage.Literals.CONSTRAINTS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TimeConstraint getTimeConstraint()
  {
    return timeConstraint;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTimeConstraint(TimeConstraint newTimeConstraint, NotificationChain msgs)
  {
    TimeConstraint oldTimeConstraint = timeConstraint;
    timeConstraint = newTimeConstraint;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.CONSTRAINTS__TIME_CONSTRAINT, oldTimeConstraint, newTimeConstraint);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTimeConstraint(TimeConstraint newTimeConstraint)
  {
    if (newTimeConstraint != timeConstraint)
    {
      NotificationChain msgs = null;
      if (timeConstraint != null)
        msgs = ((InternalEObject)timeConstraint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.CONSTRAINTS__TIME_CONSTRAINT, null, msgs);
      if (newTimeConstraint != null)
        msgs = ((InternalEObject)newTimeConstraint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.CONSTRAINTS__TIME_CONSTRAINT, null, msgs);
      msgs = basicSetTimeConstraint(newTimeConstraint, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.CONSTRAINTS__TIME_CONSTRAINT, newTimeConstraint, newTimeConstraint));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Constraint getConstraint()
  {
    return constraint;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConstraint(Constraint newConstraint, NotificationChain msgs)
  {
    Constraint oldConstraint = constraint;
    constraint = newConstraint;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.CONSTRAINTS__CONSTRAINT, oldConstraint, newConstraint);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConstraint(Constraint newConstraint)
  {
    if (newConstraint != constraint)
    {
      NotificationChain msgs = null;
      if (constraint != null)
        msgs = ((InternalEObject)constraint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.CONSTRAINTS__CONSTRAINT, null, msgs);
      if (newConstraint != null)
        msgs = ((InternalEObject)newConstraint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.CONSTRAINTS__CONSTRAINT, null, msgs);
      msgs = basicSetConstraint(newConstraint, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.CONSTRAINTS__CONSTRAINT, newConstraint, newConstraint));
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
      case RequirementDSLPackage.CONSTRAINTS__TIME_CONSTRAINT:
        return basicSetTimeConstraint(null, msgs);
      case RequirementDSLPackage.CONSTRAINTS__CONSTRAINT:
        return basicSetConstraint(null, msgs);
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
      case RequirementDSLPackage.CONSTRAINTS__TIME_CONSTRAINT:
        return getTimeConstraint();
      case RequirementDSLPackage.CONSTRAINTS__CONSTRAINT:
        return getConstraint();
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
      case RequirementDSLPackage.CONSTRAINTS__TIME_CONSTRAINT:
        setTimeConstraint((TimeConstraint)newValue);
        return;
      case RequirementDSLPackage.CONSTRAINTS__CONSTRAINT:
        setConstraint((Constraint)newValue);
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
      case RequirementDSLPackage.CONSTRAINTS__TIME_CONSTRAINT:
        setTimeConstraint((TimeConstraint)null);
        return;
      case RequirementDSLPackage.CONSTRAINTS__CONSTRAINT:
        setConstraint((Constraint)null);
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
      case RequirementDSLPackage.CONSTRAINTS__TIME_CONSTRAINT:
        return timeConstraint != null;
      case RequirementDSLPackage.CONSTRAINTS__CONSTRAINT:
        return constraint != null;
    }
    return super.eIsSet(featureID);
  }

} //ConstraintsImpl
