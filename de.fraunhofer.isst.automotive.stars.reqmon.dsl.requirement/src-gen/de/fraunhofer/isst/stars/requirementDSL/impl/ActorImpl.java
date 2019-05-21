/**
 * generated by Xtext 2.16.0
 */
package de.fraunhofer.isst.stars.requirementDSL.impl;

import de.fraunhofer.isst.stars.requirementDSL.Actor;
import de.fraunhofer.isst.stars.requirementDSL.PreNominative;
import de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Actor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.ActorImpl#getPreNominative <em>Pre Nominative</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.ActorImpl#getActor <em>Actor</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActorImpl extends MinimalEObjectImpl.Container implements Actor
{
  /**
   * The cached value of the '{@link #getPreNominative() <em>Pre Nominative</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPreNominative()
   * @generated
   * @ordered
   */
  protected PreNominative preNominative;

  /**
   * The default value of the '{@link #getActor() <em>Actor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getActor()
   * @generated
   * @ordered
   */
  protected static final String ACTOR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getActor() <em>Actor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getActor()
   * @generated
   * @ordered
   */
  protected String actor = ACTOR_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ActorImpl()
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
    return RequirementDSLPackage.Literals.ACTOR;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PreNominative getPreNominative()
  {
    return preNominative;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPreNominative(PreNominative newPreNominative, NotificationChain msgs)
  {
    PreNominative oldPreNominative = preNominative;
    preNominative = newPreNominative;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.ACTOR__PRE_NOMINATIVE, oldPreNominative, newPreNominative);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPreNominative(PreNominative newPreNominative)
  {
    if (newPreNominative != preNominative)
    {
      NotificationChain msgs = null;
      if (preNominative != null)
        msgs = ((InternalEObject)preNominative).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.ACTOR__PRE_NOMINATIVE, null, msgs);
      if (newPreNominative != null)
        msgs = ((InternalEObject)newPreNominative).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.ACTOR__PRE_NOMINATIVE, null, msgs);
      msgs = basicSetPreNominative(newPreNominative, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.ACTOR__PRE_NOMINATIVE, newPreNominative, newPreNominative));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getActor()
  {
    return actor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setActor(String newActor)
  {
    String oldActor = actor;
    actor = newActor;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.ACTOR__ACTOR, oldActor, actor));
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
      case RequirementDSLPackage.ACTOR__PRE_NOMINATIVE:
        return basicSetPreNominative(null, msgs);
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
      case RequirementDSLPackage.ACTOR__PRE_NOMINATIVE:
        return getPreNominative();
      case RequirementDSLPackage.ACTOR__ACTOR:
        return getActor();
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
      case RequirementDSLPackage.ACTOR__PRE_NOMINATIVE:
        setPreNominative((PreNominative)newValue);
        return;
      case RequirementDSLPackage.ACTOR__ACTOR:
        setActor((String)newValue);
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
      case RequirementDSLPackage.ACTOR__PRE_NOMINATIVE:
        setPreNominative((PreNominative)null);
        return;
      case RequirementDSLPackage.ACTOR__ACTOR:
        setActor(ACTOR_EDEFAULT);
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
      case RequirementDSLPackage.ACTOR__PRE_NOMINATIVE:
        return preNominative != null;
      case RequirementDSLPackage.ACTOR__ACTOR:
        return ACTOR_EDEFAULT == null ? actor != null : !ACTOR_EDEFAULT.equals(actor);
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
    result.append(" (actor: ");
    result.append(actor);
    result.append(')');
    return result.toString();
  }

} //ActorImpl
