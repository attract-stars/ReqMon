/**
 * generated by Xtext 2.17.1
 */
package de.fraunhofer.isst.stars.requirementDSL.impl;

import de.fraunhofer.isst.stars.requirementDSL.Constraints;
import de.fraunhofer.isst.stars.requirementDSL.Relation;
import de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage;
import de.fraunhofer.isst.stars.requirementDSL.SentenceEnding;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sentence Ending</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.SentenceEndingImpl#getConst <em>Const</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.SentenceEndingImpl#getRela <em>Rela</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SentenceEndingImpl extends MinimalEObjectImpl.Container implements SentenceEnding
{
  /**
   * The cached value of the '{@link #getConst() <em>Const</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConst()
   * @generated
   * @ordered
   */
  protected EList<Constraints> const_;

  /**
   * The cached value of the '{@link #getRela() <em>Rela</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRela()
   * @generated
   * @ordered
   */
  protected Relation rela;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SentenceEndingImpl()
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
    return RequirementDSLPackage.Literals.SENTENCE_ENDING;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<Constraints> getConst()
  {
    if (const_ == null)
    {
      const_ = new EObjectContainmentEList<Constraints>(Constraints.class, this, RequirementDSLPackage.SENTENCE_ENDING__CONST);
    }
    return const_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Relation getRela()
  {
    return rela;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRela(Relation newRela, NotificationChain msgs)
  {
    Relation oldRela = rela;
    rela = newRela;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.SENTENCE_ENDING__RELA, oldRela, newRela);
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
  public void setRela(Relation newRela)
  {
    if (newRela != rela)
    {
      NotificationChain msgs = null;
      if (rela != null)
        msgs = ((InternalEObject)rela).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.SENTENCE_ENDING__RELA, null, msgs);
      if (newRela != null)
        msgs = ((InternalEObject)newRela).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.SENTENCE_ENDING__RELA, null, msgs);
      msgs = basicSetRela(newRela, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.SENTENCE_ENDING__RELA, newRela, newRela));
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
      case RequirementDSLPackage.SENTENCE_ENDING__CONST:
        return ((InternalEList<?>)getConst()).basicRemove(otherEnd, msgs);
      case RequirementDSLPackage.SENTENCE_ENDING__RELA:
        return basicSetRela(null, msgs);
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
      case RequirementDSLPackage.SENTENCE_ENDING__CONST:
        return getConst();
      case RequirementDSLPackage.SENTENCE_ENDING__RELA:
        return getRela();
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
      case RequirementDSLPackage.SENTENCE_ENDING__CONST:
        getConst().clear();
        getConst().addAll((Collection<? extends Constraints>)newValue);
        return;
      case RequirementDSLPackage.SENTENCE_ENDING__RELA:
        setRela((Relation)newValue);
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
      case RequirementDSLPackage.SENTENCE_ENDING__CONST:
        getConst().clear();
        return;
      case RequirementDSLPackage.SENTENCE_ENDING__RELA:
        setRela((Relation)null);
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
      case RequirementDSLPackage.SENTENCE_ENDING__CONST:
        return const_ != null && !const_.isEmpty();
      case RequirementDSLPackage.SENTENCE_ENDING__RELA:
        return rela != null;
    }
    return super.eIsSet(featureID);
  }

} //SentenceEndingImpl
