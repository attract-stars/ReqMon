/**
 * generated by Xtext 2.14.0
 */
package de.fraunhofer.isst.stars.requirementDSL.impl;

import de.fraunhofer.isst.stars.requirementDSL.Actors;
import de.fraunhofer.isst.stars.requirementDSL.ExistencePreface;
import de.fraunhofer.isst.stars.requirementDSL.Modifier;
import de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage;
import de.fraunhofer.isst.stars.requirementDSL.relativeClause;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Existence Preface</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.ExistencePrefaceImpl#getActors <em>Actors</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.ExistencePrefaceImpl#getRelativeClause <em>Relative Clause</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.ExistencePrefaceImpl#getModifier <em>Modifier</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExistencePrefaceImpl extends ExistenceSentenceImpl implements ExistencePreface
{
  /**
   * The cached value of the '{@link #getActors() <em>Actors</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getActors()
   * @generated
   * @ordered
   */
  protected Actors actors;

  /**
   * The cached value of the '{@link #getRelativeClause() <em>Relative Clause</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRelativeClause()
   * @generated
   * @ordered
   */
  protected relativeClause relativeClause;

  /**
   * The default value of the '{@link #getModifier() <em>Modifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModifier()
   * @generated
   * @ordered
   */
  protected static final Modifier MODIFIER_EDEFAULT = Modifier.GLOBALLY;

  /**
   * The cached value of the '{@link #getModifier() <em>Modifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModifier()
   * @generated
   * @ordered
   */
  protected Modifier modifier = MODIFIER_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ExistencePrefaceImpl()
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
    return RequirementDSLPackage.Literals.EXISTENCE_PREFACE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Actors getActors()
  {
    return actors;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetActors(Actors newActors, NotificationChain msgs)
  {
    Actors oldActors = actors;
    actors = newActors;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.EXISTENCE_PREFACE__ACTORS, oldActors, newActors);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setActors(Actors newActors)
  {
    if (newActors != actors)
    {
      NotificationChain msgs = null;
      if (actors != null)
        msgs = ((InternalEObject)actors).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.EXISTENCE_PREFACE__ACTORS, null, msgs);
      if (newActors != null)
        msgs = ((InternalEObject)newActors).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.EXISTENCE_PREFACE__ACTORS, null, msgs);
      msgs = basicSetActors(newActors, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.EXISTENCE_PREFACE__ACTORS, newActors, newActors));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public relativeClause getRelativeClause()
  {
    return relativeClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRelativeClause(relativeClause newRelativeClause, NotificationChain msgs)
  {
    relativeClause oldRelativeClause = relativeClause;
    relativeClause = newRelativeClause;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.EXISTENCE_PREFACE__RELATIVE_CLAUSE, oldRelativeClause, newRelativeClause);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRelativeClause(relativeClause newRelativeClause)
  {
    if (newRelativeClause != relativeClause)
    {
      NotificationChain msgs = null;
      if (relativeClause != null)
        msgs = ((InternalEObject)relativeClause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.EXISTENCE_PREFACE__RELATIVE_CLAUSE, null, msgs);
      if (newRelativeClause != null)
        msgs = ((InternalEObject)newRelativeClause).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.EXISTENCE_PREFACE__RELATIVE_CLAUSE, null, msgs);
      msgs = basicSetRelativeClause(newRelativeClause, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.EXISTENCE_PREFACE__RELATIVE_CLAUSE, newRelativeClause, newRelativeClause));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Modifier getModifier()
  {
    return modifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setModifier(Modifier newModifier)
  {
    Modifier oldModifier = modifier;
    modifier = newModifier == null ? MODIFIER_EDEFAULT : newModifier;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.EXISTENCE_PREFACE__MODIFIER, oldModifier, modifier));
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
      case RequirementDSLPackage.EXISTENCE_PREFACE__ACTORS:
        return basicSetActors(null, msgs);
      case RequirementDSLPackage.EXISTENCE_PREFACE__RELATIVE_CLAUSE:
        return basicSetRelativeClause(null, msgs);
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
      case RequirementDSLPackage.EXISTENCE_PREFACE__ACTORS:
        return getActors();
      case RequirementDSLPackage.EXISTENCE_PREFACE__RELATIVE_CLAUSE:
        return getRelativeClause();
      case RequirementDSLPackage.EXISTENCE_PREFACE__MODIFIER:
        return getModifier();
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
      case RequirementDSLPackage.EXISTENCE_PREFACE__ACTORS:
        setActors((Actors)newValue);
        return;
      case RequirementDSLPackage.EXISTENCE_PREFACE__RELATIVE_CLAUSE:
        setRelativeClause((relativeClause)newValue);
        return;
      case RequirementDSLPackage.EXISTENCE_PREFACE__MODIFIER:
        setModifier((Modifier)newValue);
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
      case RequirementDSLPackage.EXISTENCE_PREFACE__ACTORS:
        setActors((Actors)null);
        return;
      case RequirementDSLPackage.EXISTENCE_PREFACE__RELATIVE_CLAUSE:
        setRelativeClause((relativeClause)null);
        return;
      case RequirementDSLPackage.EXISTENCE_PREFACE__MODIFIER:
        setModifier(MODIFIER_EDEFAULT);
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
      case RequirementDSLPackage.EXISTENCE_PREFACE__ACTORS:
        return actors != null;
      case RequirementDSLPackage.EXISTENCE_PREFACE__RELATIVE_CLAUSE:
        return relativeClause != null;
      case RequirementDSLPackage.EXISTENCE_PREFACE__MODIFIER:
        return modifier != MODIFIER_EDEFAULT;
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
    result.append(" (modifier: ");
    result.append(modifier);
    result.append(')');
    return result.toString();
  }

} //ExistencePrefaceImpl
