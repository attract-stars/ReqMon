/**
 * generated by Xtext 2.16.0
 */
package de.fraunhofer.isst.stars.requirementDSL.impl;

import de.fraunhofer.isst.stars.requirementDSL.AuxNeg;
import de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Aux Neg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.AuxNegImpl#getAuxiliarVerb <em>Auxiliar Verb</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.AuxNegImpl#getNegation <em>Negation</em>}</li>
 *   <li>{@link de.fraunhofer.isst.stars.requirementDSL.impl.AuxNegImpl#getAuxiliarVerbNeg <em>Auxiliar Verb Neg</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AuxNegImpl extends MinimalEObjectImpl.Container implements AuxNeg
{
  /**
   * The default value of the '{@link #getAuxiliarVerb() <em>Auxiliar Verb</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAuxiliarVerb()
   * @generated
   * @ordered
   */
  protected static final String AUXILIAR_VERB_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAuxiliarVerb() <em>Auxiliar Verb</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAuxiliarVerb()
   * @generated
   * @ordered
   */
  protected String auxiliarVerb = AUXILIAR_VERB_EDEFAULT;

  /**
   * The default value of the '{@link #getNegation() <em>Negation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNegation()
   * @generated
   * @ordered
   */
  protected static final String NEGATION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNegation() <em>Negation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNegation()
   * @generated
   * @ordered
   */
  protected String negation = NEGATION_EDEFAULT;

  /**
   * The default value of the '{@link #getAuxiliarVerbNeg() <em>Auxiliar Verb Neg</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAuxiliarVerbNeg()
   * @generated
   * @ordered
   */
  protected static final String AUXILIAR_VERB_NEG_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAuxiliarVerbNeg() <em>Auxiliar Verb Neg</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAuxiliarVerbNeg()
   * @generated
   * @ordered
   */
  protected String auxiliarVerbNeg = AUXILIAR_VERB_NEG_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AuxNegImpl()
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
    return RequirementDSLPackage.Literals.AUX_NEG;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAuxiliarVerb()
  {
    return auxiliarVerb;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAuxiliarVerb(String newAuxiliarVerb)
  {
    String oldAuxiliarVerb = auxiliarVerb;
    auxiliarVerb = newAuxiliarVerb;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.AUX_NEG__AUXILIAR_VERB, oldAuxiliarVerb, auxiliarVerb));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNegation()
  {
    return negation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNegation(String newNegation)
  {
    String oldNegation = negation;
    negation = newNegation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.AUX_NEG__NEGATION, oldNegation, negation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAuxiliarVerbNeg()
  {
    return auxiliarVerbNeg;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAuxiliarVerbNeg(String newAuxiliarVerbNeg)
  {
    String oldAuxiliarVerbNeg = auxiliarVerbNeg;
    auxiliarVerbNeg = newAuxiliarVerbNeg;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RequirementDSLPackage.AUX_NEG__AUXILIAR_VERB_NEG, oldAuxiliarVerbNeg, auxiliarVerbNeg));
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
      case RequirementDSLPackage.AUX_NEG__AUXILIAR_VERB:
        return getAuxiliarVerb();
      case RequirementDSLPackage.AUX_NEG__NEGATION:
        return getNegation();
      case RequirementDSLPackage.AUX_NEG__AUXILIAR_VERB_NEG:
        return getAuxiliarVerbNeg();
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
      case RequirementDSLPackage.AUX_NEG__AUXILIAR_VERB:
        setAuxiliarVerb((String)newValue);
        return;
      case RequirementDSLPackage.AUX_NEG__NEGATION:
        setNegation((String)newValue);
        return;
      case RequirementDSLPackage.AUX_NEG__AUXILIAR_VERB_NEG:
        setAuxiliarVerbNeg((String)newValue);
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
      case RequirementDSLPackage.AUX_NEG__AUXILIAR_VERB:
        setAuxiliarVerb(AUXILIAR_VERB_EDEFAULT);
        return;
      case RequirementDSLPackage.AUX_NEG__NEGATION:
        setNegation(NEGATION_EDEFAULT);
        return;
      case RequirementDSLPackage.AUX_NEG__AUXILIAR_VERB_NEG:
        setAuxiliarVerbNeg(AUXILIAR_VERB_NEG_EDEFAULT);
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
      case RequirementDSLPackage.AUX_NEG__AUXILIAR_VERB:
        return AUXILIAR_VERB_EDEFAULT == null ? auxiliarVerb != null : !AUXILIAR_VERB_EDEFAULT.equals(auxiliarVerb);
      case RequirementDSLPackage.AUX_NEG__NEGATION:
        return NEGATION_EDEFAULT == null ? negation != null : !NEGATION_EDEFAULT.equals(negation);
      case RequirementDSLPackage.AUX_NEG__AUXILIAR_VERB_NEG:
        return AUXILIAR_VERB_NEG_EDEFAULT == null ? auxiliarVerbNeg != null : !AUXILIAR_VERB_NEG_EDEFAULT.equals(auxiliarVerbNeg);
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
    result.append(" (auxiliarVerb: ");
    result.append(auxiliarVerb);
    result.append(", negation: ");
    result.append(negation);
    result.append(", auxiliarVerbNeg: ");
    result.append(auxiliarVerbNeg);
    result.append(')');
    return result.toString();
  }

} //AuxNegImpl
