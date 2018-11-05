/*
 * generated by Xtext 2.14.0
 */
grammar DebugInternalRequirementDSL;

// Rule Model
ruleModel:
	ruleRequirement
	*
;

// Rule Requirement
ruleRequirement:
	'Req'?
	ruleReqID
	?
	(
		':'
		    |
		'.'
	)
	ruleRequirementText
	(
		'.'
		    |
		';'
	)
;

// Rule RequirementText
ruleRequirementText:
	(
		ruleConditionalClause
		','?
		'then'
	)?
	ruleMainClause
	(
		','?
		ruleConditionalClause
	)?
;

// Rule ConditionalClause
ruleConditionalClause:
	ruleClauseOrdinator
	ruleClauses
;

// Rule MainClause
ruleMainClause:
	ruleModifier
	?
	ruleClause
;

// Rule Clauses
ruleClauses:
	ruleClause
	(
		ruleConjunction
		ruleClause
	)*
;

// Rule Clause
ruleClause:
	(
		ruleModalitySentence
		    |
		rulePredicateSentence
		    |
		ruleExistenceSentence
		    |
		rulePropertySentence
	)
;

// Rule ModalitySentence
ruleModalitySentence:
	ruleActors
	ruleModality
	ruleNegation
	?
	ruleAuxiliaryVerb
	?
	rulePredicate
	ruleConstraints
	*
;

// Rule PredicateSentence
rulePredicateSentence:
	(
		ruleActors
		rulePredicate
		ruleConstraints
		*
		    |
		ruleActors
		ruleAuxiliaryVerb
		ruleNegation
		?
		ruleAuxiliaryVerb
		?
		rulePredicate
		ruleConstraints
		*
		    |
		ruleActors
		ruleAuxiliaryVerb
		ruleNegation
		?
		rulePredicateObject
		?
		ruleConstraints
		*
	)
;

// Rule ExistenceSentence
ruleExistenceSentence:
	ruleExistencePreface
	ruleActors
	','
	rulerelativeClause
	','
;

// Rule PropertySentence
rulePropertySentence:
	(
		ruleProperty
		ruleModality
		ruleNegation
		?
		ruleAuxiliaryVerb
		?
		rulePredicate
		ruleConstraints
		*
		    |
		ruleProperty
		ruleModality
		ruleNegation
		?
		ruleAuxiliaryVerb
		rulePredicateObject
		?
		ruleConstraints
		*
		    |
		ruleProperty
		ruleAuxiliaryVerb
		ruleNegation
		?
		(
			(
				rulePredicate
				    |
				rulePredicateObject
			)
			ruleConstraints
			*
			    |
			ruleConstraints
			+
		)
		    |
		ruleProperty
		(
			ruleWORD
			    |
			RULE_STRING
		)
		rulePredicateObject
		?
		ruleConstraints
		*
	)
;

// Rule Property
ruleProperty:
	(
		ruleQuantification
		    |
		ruleArticles
		    |
		ruleRefArticles
	)?
	(
		ruleWORD
		+
		    |
		RULE_STRING
	)
	RULE_PROPERTY_TERM
	(
		ruleWORD
		+
		    |
		RULE_STRING
	)
;

// Rule relativeClause
rulerelativeClause:
	rulerelativeSentence
	(
		ruleConjunction
		ruleConditionalClause
	)*
;

// Rule relativeSentence
rulerelativeSentence:
	(
		ruleRelativePronounsSubject
		ruleModality
		ruleNegation
		?
		rulePredicate
		ruleConstraints
		*
		    |
		ruleRelativePronounsSubject
		(
			ruleWORD
			ruleNegation
		)?
		rulePredicate
		ruleConstraints
		*
		    |
		ruleRelativePronounsObject
		(
			ruleModalitySentence
			    |
			rulePredicateSentence
		)
	)
;

// Rule Actors
ruleActors:
	ruleActor
	(
		ruleConjunction
		ruleActor
	)*
;

// Rule Actor
ruleActor:
	rulePreNominative?
	(
		ruleWORD
		    |
		RULE_STRING
	)
;

// Rule Predicate
rulePredicate:
	(
		ruleWORD
		+
		    |
		RULE_STRING
		    |
		ruleWORD
		+
		rulePredicateObject
	)
;

// Rule PredicateObject
rulePredicateObject:
	rulePreNominative
	(
		ruleWORD
		+
		    |
		RULE_STRING
	)
;

// Rule ExistencePreface
ruleExistencePreface:
	'there'
	ruleModifier
	?
	(
		ruleWORD+
		    |
		RULE_STRING
	)
;

// Rule Object
ruleObject:
	rulePreNominative?
	(
		ruleWORD
		+
		    |
		RULE_STRING
	)
;

// Rule PreNominative
rulePreNominative:
	(
		ruleQuantification
		    |
		ruleArticles
		    |
		ruleRefArticles
	)
;

// Rule Adverbial
ruleAdverbial:
	(
		ruleSizeAdverbial
		    |
		rulePositionAdverbial
		    |
		ruleComparisonAdverbial
	)
;

// Rule Constraints
ruleConstraints:
	(
		ruleTimeConstraint
		    |
		ruleConstraint
	)
;

// Rule Constraint
ruleConstraint:
	ruleConstraintOrdinators
	(
		ruleObjectConstraint
		    |
		ruleUnitConstraints
		    |
		ruleSetConstraint
	)
;

// Rule ConstraintOrdinators
ruleConstraintOrdinators:
	ruleStuffWord
	?
	ruleAdverbial
	ruleComperators
	?
;

// Rule SetConstraint
ruleSetConstraint:
	(
		ruleObjectSet
		    |
		ruleValueSet
	)
;

// Rule TimeConstraint
ruleTimeConstraint:
	ruleConstraintOrdinators
	RULE_INT
	ruleTimeUnits
;

// Rule ObjectConstraint
ruleObjectConstraint:
	ruleObject
;

// Rule UnitConstraints
ruleUnitConstraints:
	(
		ruleSingleValueConstraints
		    |
		ruleIntervallConstraints
	)
;

// Rule IntervallConstraints
ruleIntervallConstraints:
	'['
	ruleValue
	','
	ruleValue
	']'
;

// Rule SingleValueConstraints
ruleSingleValueConstraints:
	ruleValue
;

// Rule ValueSet
ruleValueSet:
	'{'
	ruleValue
	(
		';'
		ruleValue
	)*
	'}'
;

// Rule ObjectSet
ruleObjectSet:
	'{'
	ruleObject
	(
		';'
		ruleObject
	)*
	'}'
;

// Rule Value
ruleValue:
	(
		ruleIntValue
		    |
		ruleFloatValue
	)
;

// Rule IntValue
ruleIntValue:
	RULE_INT
	ruleUnit
	?
;

// Rule FloatValue
ruleFloatValue:
	RULE_FLOAT
	ruleUnit
	?
;

// Rule ReqID
ruleReqID:
	(
		RULE_ID
		    |
		RULE_INT
	)
	(
		'.'
		    |
		RULE_INT
	)*
;

// Rule WORD
ruleWORD:
	RULE_ID
	(
		'-'
		RULE_ID
	)*
;

// Rule AuxiliaryVerb
ruleAuxiliaryVerb:
	(
		'is'
		    |
		'be'
		    |
		'been'
		    |
		'has'
		    |
		'do'
		    |
		'does'
	)
;

// Rule Conjunction
ruleConjunction:
	(
		'and'
		    |
		'or'
	)
;

// Rule Comperators
ruleComperators:
	(
		'than'
		    |
		'as'
		    |
		'to'
		    |
		'of'
	)
;

// Rule SizeAdverbial
ruleSizeAdverbial:
	(
		'higher'
		    |
		'less'
		    |
		'more'
		    |
		'larger'
		    |
		'smaller'
		    |
		'as_long_as'
	)
;

// Rule PositionAdverbial
rulePositionAdverbial:
	(
		'between'
		    |
		'next'
		    |
		'on'
		    |
		'above'
		    |
		'below'
		    |
		'in'
		    |
		'within'
		    |
		'in_front_of'
		    |
		'behind'
		    |
		'out'
		    |
		'under'
	)
;

// Rule ComparisonAdverbial
ruleComparisonAdverbial:
	(
		'equal'
		    |
		'faster'
		    |
		'slower'
		    |
		'better'
		    |
		'by'
		    |
		'to'
	)
;

// Rule Quantification
ruleQuantification:
	(
		'all'
		    |
		'every'
		    |
		'each'
		    |
		'whole'
		    |
		'any'
		    |
		'several'
		    |
		'either'
		'All'
		    |
		'Every'
		    |
		'Each'
		    |
		'Whole'
		    |
		'Any'
		    |
		'Several'
		    |
		'Either'
	)
;

// Rule Negation
ruleNegation:
	(
		'not'
		    |
		'donot'
		    |
		'doesnot'
		    |
		'doesn\'t'
		    |
		'don\'t'
	)
;

// Rule Articles
ruleArticles:
	(
		'the'
		    |
		'a'
		    |
		'an'
		    |
		'The'
		    |
		'A'
		    |
		'An'
	)
;

// Rule RefArticles
ruleRefArticles:
	(
		'that'
		    |
		'this'
		    |
		'That'
		    |
		'This'
	)
;

// Rule StuffWord
ruleStuffWord:
	'with'
;

// Rule RelativePronounsSubject
ruleRelativePronounsSubject:
	(
		'which'
		    |
		'who'
		    |
		'that'
	)
;

// Rule RelativePronounsObject
ruleRelativePronounsObject:
	(
		'whose'
		    |
		'whom'
	)
;

// Rule Unit
ruleUnit:
	(
		ruleLengthUnits
		    |
		rulePresureUnits
		    |
		ruleHeatUnits
		    |
		ruleMassUnits
		    |
		ruleVelcoityUnits
		    |
		ruleCuvature
	)
;

// Rule Cuvature
ruleCuvature:
	(
		'rad/m'
		    |
		'\u00B0'
		    |
		'rad'
		    |
		'\u00B0/m'
	)
;

// Rule VelcoityUnits
ruleVelcoityUnits:
	(
		'm/s'
		    |
		'knots'
		    |
		'km/h'
		    |
		'm/min'
	)
;

// Rule MassUnits
ruleMassUnits:
	(
		'kg'
		    |
		'g'
		    |
		'mg'
		    |
		't'
	)
;

// Rule HeatUnits
ruleHeatUnits:
	(
		'C'
		    |
		'F'
	)
;

// Rule PresureUnits
rulePresureUnits:
	(
		'bar'
		    |
		'Pa'
		    |
		'hPa'
	)
;

// Rule LengthUnits
ruleLengthUnits:
	(
		'm'
		    |
		'f'
		    |
		'km'
		    |
		'cm'
		    |
		'mm'
		    |
		'nm'
	)
;

// Rule TimeUnits
ruleTimeUnits:
	(
		'ns'
		    |
		'ms'
		    |
		's'
		    |
		'sec'
		    |
		'second'
		    |
		'seconds'
		    |
		'minute'
		    |
		'minutes'
		    |
		'min'
		    |
		'hour'
		    |
		'hours'
		    |
		'h'
		    |
		'day'
		    |
		'days'
		    |
		'd'
		    |
		'month'
		    |
		'months'
		    |
		'mon'
		    |
		'year'
		    |
		'years'
		    |
		'y'
	)
;

// Rule Modality
ruleModality:
	(
		'shall'
		    |
		'should'
		    |
		'will'
		    |
		'would'
		    |
		'can'
		    |
		'could'
		    |
		'must'
	)
;

// Rule Modifier
ruleModifier:
	(
		'Globally'
		    |
		'globally'
		    |
		'Always'
		    |
		'always'
		    |
		'Sometimes'
		    |
		'sometimes'
		    |
		'Eventually'
		    |
		'eventually'
	)
;

// Rule ClauseOrdinator
ruleClauseOrdinator:
	(
		'if'
		    |
		'after'
		    |
		'once'
		    |
		'when'
		    |
		'whenever'
		    |
		'while'
		    |
		'before'
		    |
		'until'
		    |
		'If'
		    |
		'After'
		    |
		'Once'
		    |
		'When'
		    |
		'Whenever'
		    |
		'While'
		    |
		'Before'
		    |
		'Until'
	)
;

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'-'|'0'..'9')*;

RULE_WS_HYPHEN : ' - ' {skip();};

RULE_PROPERTY_TERM : ('\'s'|'`s'|'\u00B4s');

RULE_FLOAT : RULE_INT '.' RULE_INT;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/' {skip();};

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')? {skip();};

RULE_WS : (' '|'\t'|'\r'|'\n')+ {skip();};

RULE_ANY_OTHER : .;
