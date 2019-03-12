/**
 * generated by Xtext 2.16.0
 */
package de.fraunhofer.isst.stars.requirementDSL.impl;

import de.fraunhofer.isst.stars.requirementDSL.ConditionalClause;
import de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage;
import de.fraunhofer.isst.stars.requirementDSL.relativeClause;
import de.fraunhofer.isst.stars.requirementDSL.relativeSentence;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>relative Clause</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.relativeClauseImpl#getSentence <em>Sentence</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.relativeClauseImpl#getConjunction <em>Conjunction</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.relativeClauseImpl#getCondClauses <em>Cond Clauses</em>}</li>
 * </ul>
 *
 * @generated
 */
public class relativeClauseImpl extends MinimalEObjectImpl.Container implements relativeClause
{
  /**
   * The cached value of the '{@link #getSentence() <em>Sentence</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSentence()
   * @generated
   * @ordered
   */
  protected relativeSentence sentence;

  /**
   * The cached value of the '{@link #getConjunction() <em>Conjunction</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConjunction()
   * @generated
   * @ordered
   */
  protected EList<String> conjunction;

  /**
   * The cached value of the '{@link #getCondClauses() <em>Cond Clauses</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCondClauses()
   * @generated
   * @ordered
   */
  protected EList<ConditionalClause> condClauses;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected relativeClauseImpl()
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
    return RequirementDSLPackage.Literals.RELATIVE_CLAUSE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public relativeSentence getSentence()
  {
    return sentence;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSentence(relativeSentence newSentence, NotificationChain msgs)
  {
    relativeSentence oldSentence = sentence;
    sentence = newSentence;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.RELATIVE_CLAUSE__SENTENCE, oldSentence, newSentence);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSentence(relativeSentence newSentence)
  {
    if (newSentence != sentence)
    {
      NotificationChain msgs = null;
      if (sentence != null)
        msgs = ((InternalEObject)sentence).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.RELATIVE_CLAUSE__SENTENCE, null, msgs);
      if (newSentence != null)
        msgs = ((InternalEObject)newSentence).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RequirementDSLPackage.RELATIVE_CLAUSE__SENTENCE, null, msgs);
      msgs = basicSetSentence(newSentence, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.RELATIVE_CLAUSE__SENTENCE, newSentence, newSentence));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getConjunction()
  {
    if (conjunction == null)
    {
      conjunction = new EDataTypeEList<String>(String.class, this, RequirementDSLPackage.RELATIVE_CLAUSE__CONJUNCTION);
    }
    return conjunction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ConditionalClause> getCondClauses()
  {
    if (condClauses == null)
    {
      condClauses = new EObjectContainmentEList<ConditionalClause>(ConditionalClause.class, this, RequirementDSLPackage.RELATIVE_CLAUSE__COND_CLAUSES);
    }
    return condClauses;
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
      case RequirementDSLPackage.RELATIVE_CLAUSE__SENTENCE:
        return basicSetSentence(null, msgs);
      case RequirementDSLPackage.RELATIVE_CLAUSE__COND_CLAUSES:
        return ((InternalEList<?>)getCondClauses()).basicRemove(otherEnd, msgs);
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
      case RequirementDSLPackage.RELATIVE_CLAUSE__SENTENCE:
        return getSentence();
      case RequirementDSLPackage.RELATIVE_CLAUSE__CONJUNCTION:
        return getConjunction();
      case RequirementDSLPackage.RELATIVE_CLAUSE__COND_CLAUSES:
        return getCondClauses();
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
      case RequirementDSLPackage.RELATIVE_CLAUSE__SENTENCE:
        setSentence((relativeSentence)newValue);
        return;
      case RequirementDSLPackage.RELATIVE_CLAUSE__CONJUNCTION:
        getConjunction().clear();
        getConjunction().addAll((Collection<? extends String>)newValue);
        return;
      case RequirementDSLPackage.RELATIVE_CLAUSE__COND_CLAUSES:
        getCondClauses().clear();
        getCondClauses().addAll((Collection<? extends ConditionalClause>)newValue);
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
      case RequirementDSLPackage.RELATIVE_CLAUSE__SENTENCE:
        setSentence((relativeSentence)null);
        return;
      case RequirementDSLPackage.RELATIVE_CLAUSE__CONJUNCTION:
        getConjunction().clear();
        return;
      case RequirementDSLPackage.RELATIVE_CLAUSE__COND_CLAUSES:
        getCondClauses().clear();
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
      case RequirementDSLPackage.RELATIVE_CLAUSE__SENTENCE:
        return sentence != null;
      case RequirementDSLPackage.RELATIVE_CLAUSE__CONJUNCTION:
        return conjunction != null && !conjunction.isEmpty();
      case RequirementDSLPackage.RELATIVE_CLAUSE__COND_CLAUSES:
        return condClauses != null && !condClauses.isEmpty();
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
    result.append(" (conjunction: ");
    result.append(conjunction);
    result.append(')');
    return result.toString();
  }

} //relativeClauseImpl
