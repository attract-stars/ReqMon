/**
 * generated by Xtext 2.17.1
 */
package de.fraunhofer.isst.stars.requirementDSL;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Modality</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see de.fraunhofer.isst.stars.requirementDSL.RequirementDSLPackage#getModality()
 * @model
 * @generated
 */
public enum Modality implements Enumerator
{
  /**
   * The '<em><b>SHALL</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SHALL_VALUE
   * @generated
   * @ordered
   */
  SHALL(0, "SHALL", "shall"),

  /**
   * The '<em><b>SHOULD</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SHOULD_VALUE
   * @generated
   * @ordered
   */
  SHOULD(1, "SHOULD", "should"),

  /**
   * The '<em><b>WILL</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #WILL_VALUE
   * @generated
   * @ordered
   */
  WILL(2, "WILL", "will"),

  /**
   * The '<em><b>WOULD</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #WOULD_VALUE
   * @generated
   * @ordered
   */
  WOULD(3, "WOULD", "would"),

  /**
   * The '<em><b>CAN</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #CAN_VALUE
   * @generated
   * @ordered
   */
  CAN(4, "CAN", "can"),

  /**
   * The '<em><b>COULD</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #COULD_VALUE
   * @generated
   * @ordered
   */
  COULD(5, "COULD", "could"),

  /**
   * The '<em><b>MUST</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #MUST_VALUE
   * @generated
   * @ordered
   */
  MUST(6, "MUST", "must");

  /**
   * The '<em><b>SHALL</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SHALL
   * @model literal="shall"
   * @generated
   * @ordered
   */
  public static final int SHALL_VALUE = 0;

  /**
   * The '<em><b>SHOULD</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SHOULD
   * @model literal="should"
   * @generated
   * @ordered
   */
  public static final int SHOULD_VALUE = 1;

  /**
   * The '<em><b>WILL</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #WILL
   * @model literal="will"
   * @generated
   * @ordered
   */
  public static final int WILL_VALUE = 2;

  /**
   * The '<em><b>WOULD</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #WOULD
   * @model literal="would"
   * @generated
   * @ordered
   */
  public static final int WOULD_VALUE = 3;

  /**
   * The '<em><b>CAN</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #CAN
   * @model literal="can"
   * @generated
   * @ordered
   */
  public static final int CAN_VALUE = 4;

  /**
   * The '<em><b>COULD</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #COULD
   * @model literal="could"
   * @generated
   * @ordered
   */
  public static final int COULD_VALUE = 5;

  /**
   * The '<em><b>MUST</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #MUST
   * @model literal="must"
   * @generated
   * @ordered
   */
  public static final int MUST_VALUE = 6;

  /**
   * An array of all the '<em><b>Modality</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final Modality[] VALUES_ARRAY =
    new Modality[]
    {
      SHALL,
      SHOULD,
      WILL,
      WOULD,
      CAN,
      COULD,
      MUST,
    };

  /**
   * A public read-only list of all the '<em><b>Modality</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<Modality> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Modality</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static Modality get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      Modality result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Modality</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static Modality getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      Modality result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Modality</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static Modality get(int value)
  {
    switch (value)
    {
      case SHALL_VALUE: return SHALL;
      case SHOULD_VALUE: return SHOULD;
      case WILL_VALUE: return WILL;
      case WOULD_VALUE: return WOULD;
      case CAN_VALUE: return CAN;
      case COULD_VALUE: return COULD;
      case MUST_VALUE: return MUST;
    }
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final int value;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String name;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String literal;

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private Modality(int value, String name, String literal)
  {
    this.value = value;
    this.name = name;
    this.literal = literal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getLiteral()
  {
    return literal;
  }

  /**
   * Returns the literal value of the enumerator, which is its string representation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    return literal;
  }
  
} //Modality
