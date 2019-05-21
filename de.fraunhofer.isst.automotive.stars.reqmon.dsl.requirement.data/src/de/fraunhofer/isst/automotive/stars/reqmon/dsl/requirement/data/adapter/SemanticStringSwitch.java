/**
 * 
 */
package de.fraunhofer.isst.automotive.stars.reqmon.dsl.requirement.data.adapter;

import java.util.StringJoiner;

import de.fraunhofer.isst.stars.requirementDSL.ConstraintOrdinators;
import de.fraunhofer.isst.stars.requirementDSL.FloatValue;
import de.fraunhofer.isst.stars.requirementDSL.IntValue;
import de.fraunhofer.isst.stars.requirementDSL.IntervallConstraints;
import de.fraunhofer.isst.stars.requirementDSL.Predicate;
import de.fraunhofer.isst.stars.requirementDSL.PredicateObject;
import de.fraunhofer.isst.stars.requirementDSL.SingleValueConstraints;
import de.fraunhofer.isst.stars.requirementDSL.UnitConstraints;
import de.fraunhofer.isst.stars.requirementDSL.Value;
import de.fraunhofer.isst.stars.requirementDSL.util.RequirementDSLSwitch;

/**
 * @author mmauritz
 *
 */
public class SemanticStringSwitch extends RequirementDSLSwitch<String> {

	@Override
	public String caseValue(Value object) {
		if (object instanceof IntValue) {
			return caseIntValue((IntValue) object);
		}
		if (object instanceof FloatValue) {
			return caseFloatValue((FloatValue) object);
		}
		return super.caseValue(object);//Should never happen
	}
	
	@Override
	public String caseIntValue(IntValue object) {
		StringJoiner objTxt = new StringJoiner(" ");
		objTxt.add(String.valueOf(object.getValue()));
		if(!object.getUnit().isEmpty()) {
			objTxt.add(object.getUnit());
		}
		if(!object.getObject().isEmpty()) {
			for (String str : object.getObject()) {
				objTxt.add(str);
			}
		}
		return objTxt.toString();
	}
	
	@Override
	public String caseFloatValue(FloatValue object) {
		StringJoiner objTxt = new StringJoiner(" ");
		objTxt.add(String.valueOf(object.getValue()));
		if(!object.getUnit().isEmpty()) {
			objTxt.add(object.getUnit());
		}
		if(!object.getObject().isEmpty()) {
			for (String str : object.getObject()) {
				objTxt.add(str);
			}
		}
		return objTxt.toString();
	}
	
	@Override
	public String caseSingleValueConstraints(SingleValueConstraints object) {
		return caseValue(object.getValue());
	}
	
	@Override
	public String caseIntervallConstraints(IntervallConstraints object) {
		StringBuilder objTxt = new StringBuilder();
		objTxt.append("[");
		objTxt.append(caseValue(object.getLower()));
		objTxt.append(",");
		objTxt.append(caseValue(object.getHigher()));
		objTxt.append("]");
		return objTxt.toString();
	}
	
	@Override
	public String caseConstraintOrdinators(ConstraintOrdinators object) {
		StringJoiner objTxt = new StringJoiner(" ");
		if(object.getStuffing()!=null && !object.getStuffing().isEmpty()) {
			objTxt.add(object.getStuffing());
		}
		//adverbial is a must
		if(object.getAdverbial()!=null && !object.getAdverbial().isEmpty()) {
			objTxt.add(object.getAdverbial());
		}
		if(object.getComperator()!=null && !object.getComperator().isEmpty()) {
			objTxt.add(object.getComperator());
		}
		return objTxt.toString();
	}
	
	@Override
	public String caseUnitConstraints(UnitConstraints object) {
		if(object instanceof SingleValueConstraints) {
			return caseSingleValueConstraints((SingleValueConstraints) object);
		}
		if(object instanceof IntervallConstraints) {
			return caseIntervallConstraints((IntervallConstraints) object);
			
		}
		return super.caseUnitConstraints(object);//Should never happen
	}	
	
	@Override
	public String casePredicateObject(PredicateObject object) {
		StringJoiner objTxt = new StringJoiner(" ");
		if (object!=null && object.getObject()!=null && !(object.getObject().isEmpty())) {
			for (String text : object.getObject()) {
				// the text object lists all words for the real meaning these word have to be
				// concatenated
				objTxt.add(text);
			}
		}
		return objTxt.toString();
	}
	
	@Override
	public String casePredicate(Predicate object) {
		StringJoiner objTxt = new StringJoiner(" ");
		if (object.getPredicates()!=null && !(object.getPredicates().isEmpty())){
			//GET THE ACTOR for Text
			for (String text : object.getPredicates()) {
				// the text object lists all words for the real meaning these word have to be
				// concatenated
				objTxt.add(text);
			}
		}	
		if(object.getObject()!=null) {
			objTxt.add(casePredicateObject(object.getObject()));
		}
		return objTxt.toString();
	}
	
}
