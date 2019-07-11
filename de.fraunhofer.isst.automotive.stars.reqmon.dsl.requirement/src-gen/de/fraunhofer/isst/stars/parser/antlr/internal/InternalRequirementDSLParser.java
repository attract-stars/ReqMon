package de.fraunhofer.isst.stars.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import de.fraunhofer.isst.stars.services.RequirementDSLGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalRequirementDSLParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_PROPERTY_TERM", "RULE_STRING", "RULE_INT", "RULE_ID", "RULE_WS_HYPHEN", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'Req'", "':'", "'.'", "';'", "','", "'then'", "'there'", "'There'", "'exist'", "'exists'", "'['", "']'", "'{'", "'}'", "'-'", "'relation'", "'is'", "'are'", "'be'", "'been'", "'has'", "'have'", "'do'", "'does'", "'and'", "'or'", "'and_to'", "'or_to'", "'than'", "'as'", "'to'", "'of'", "'higher'", "'less'", "'more'", "'larger'", "'smaller'", "'as_long_as'", "'between'", "'next'", "'on'", "'above'", "'below'", "'in'", "'within'", "'in_front_of'", "'behind'", "'out'", "'under'", "'equal'", "'faster'", "'slower'", "'better'", "'by'", "'all'", "'every'", "'each'", "'whole'", "'any'", "'several'", "'either'", "'All'", "'Every'", "'Each'", "'Whole'", "'Any'", "'Several'", "'Either'", "'not'", "'doesn\\u00B4t'", "'don\\u00B4t'", "'isn\\u00B4t'", "'aren\\u00B4t'", "'the'", "'a'", "'an'", "'The'", "'A'", "'An'", "'that'", "'this'", "'That'", "'This'", "'with'", "'which'", "'who'", "'whose'", "'whom'", "'rad/m'", "'\\u00B0'", "'rad'", "'\\u00B0/m'", "'m/s'", "'knots'", "'km/h'", "'m/min'", "'kg'", "'g'", "'mg'", "'t'", "'C'", "'F'", "'bar'", "'Pa'", "'hPa'", "'m'", "'f'", "'km'", "'cm'", "'mm'", "'nm'", "'ns'", "'ms'", "'s'", "'sec'", "'second'", "'seconds'", "'minute'", "'minutes'", "'min'", "'hour'", "'hours'", "'h'", "'day'", "'days'", "'d'", "'month'", "'months'", "'mon'", "'year'", "'years'", "'y'", "'shall'", "'should'", "'will'", "'would'", "'can'", "'could'", "'must'", "'Globally'", "'globally'", "'Always'", "'always'", "'Sometimes'", "'sometimes'", "'Eventually'", "'eventually'", "'if'", "'after'", "'once'", "'when'", "'whenever'", "'while'", "'before'", "'until'", "'If'", "'After'", "'Once'", "'When'", "'Whenever'", "'While'", "'Before'", "'Until'"
    };
    public static final int T__144=144;
    public static final int T__143=143;
    public static final int T__146=146;
    public static final int T__50=50;
    public static final int T__145=145;
    public static final int T__140=140;
    public static final int T__142=142;
    public static final int T__141=141;
    public static final int RULE_PROPERTY_TERM=4;
    public static final int T__59=59;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__137=137;
    public static final int T__52=52;
    public static final int T__136=136;
    public static final int T__53=53;
    public static final int T__139=139;
    public static final int T__54=54;
    public static final int T__138=138;
    public static final int T__133=133;
    public static final int T__132=132;
    public static final int T__60=60;
    public static final int T__135=135;
    public static final int T__61=61;
    public static final int T__134=134;
    public static final int RULE_ID=7;
    public static final int T__131=131;
    public static final int T__130=130;
    public static final int RULE_INT=6;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=9;
    public static final int T__67=67;
    public static final int T__129=129;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__62=62;
    public static final int T__126=126;
    public static final int T__63=63;
    public static final int T__125=125;
    public static final int T__64=64;
    public static final int T__128=128;
    public static final int T__65=65;
    public static final int T__127=127;
    public static final int T__166=166;
    public static final int T__165=165;
    public static final int T__168=168;
    public static final int T__167=167;
    public static final int T__162=162;
    public static final int T__161=161;
    public static final int T__164=164;
    public static final int T__163=163;
    public static final int T__160=160;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__159=159;
    public static final int T__30=30;
    public static final int T__158=158;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__155=155;
    public static final int T__154=154;
    public static final int T__157=157;
    public static final int T__156=156;
    public static final int T__151=151;
    public static final int T__150=150;
    public static final int T__153=153;
    public static final int T__152=152;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__148=148;
    public static final int T__41=41;
    public static final int T__147=147;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__149=149;
    public static final int T__91=91;
    public static final int T__100=100;
    public static final int T__92=92;
    public static final int T__93=93;
    public static final int T__102=102;
    public static final int T__94=94;
    public static final int T__101=101;
    public static final int T__90=90;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__99=99;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__95=95;
    public static final int T__96=96;
    public static final int T__97=97;
    public static final int T__98=98;
    public static final int T__173=173;
    public static final int T__172=172;
    public static final int T__175=175;
    public static final int T__174=174;
    public static final int T__171=171;
    public static final int T__170=170;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int RULE_WS_HYPHEN=8;
    public static final int T__169=169;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__122=122;
    public static final int T__70=70;
    public static final int T__121=121;
    public static final int T__71=71;
    public static final int T__124=124;
    public static final int T__72=72;
    public static final int T__123=123;
    public static final int T__120=120;
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=10;
    public static final int T__77=77;
    public static final int T__119=119;
    public static final int T__78=78;
    public static final int T__118=118;
    public static final int T__79=79;
    public static final int T__73=73;
    public static final int T__115=115;
    public static final int EOF=-1;
    public static final int T__74=74;
    public static final int T__114=114;
    public static final int T__75=75;
    public static final int T__117=117;
    public static final int T__76=76;
    public static final int T__116=116;
    public static final int T__80=80;
    public static final int T__111=111;
    public static final int T__81=81;
    public static final int T__110=110;
    public static final int T__82=82;
    public static final int T__113=113;
    public static final int T__83=83;
    public static final int T__112=112;
    public static final int RULE_WS=11;
    public static final int RULE_ANY_OTHER=12;
    public static final int T__88=88;
    public static final int T__108=108;
    public static final int T__89=89;
    public static final int T__107=107;
    public static final int T__109=109;
    public static final int T__84=84;
    public static final int T__104=104;
    public static final int T__85=85;
    public static final int T__103=103;
    public static final int T__86=86;
    public static final int T__106=106;
    public static final int T__87=87;
    public static final int T__105=105;

    // delegates
    // delegators


        public InternalRequirementDSLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalRequirementDSLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalRequirementDSLParser.tokenNames; }
    public String getGrammarFileName() { return "InternalRequirementDSL.g"; }



     	private RequirementDSLGrammarAccess grammarAccess;

        public InternalRequirementDSLParser(TokenStream input, RequirementDSLGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Model";
       	}

       	@Override
       	protected RequirementDSLGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleModel"
    // InternalRequirementDSL.g:65:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // InternalRequirementDSL.g:65:46: (iv_ruleModel= ruleModel EOF )
            // InternalRequirementDSL.g:66:2: iv_ruleModel= ruleModel EOF
            {
             newCompositeNode(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModel=ruleModel();

            state._fsp--;

             current =iv_ruleModel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalRequirementDSL.g:72:1: ruleModel returns [EObject current=null] : ( (lv_requirements_0_0= ruleRequirement ) )* ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        EObject lv_requirements_0_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:78:2: ( ( (lv_requirements_0_0= ruleRequirement ) )* )
            // InternalRequirementDSL.g:79:2: ( (lv_requirements_0_0= ruleRequirement ) )*
            {
            // InternalRequirementDSL.g:79:2: ( (lv_requirements_0_0= ruleRequirement ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==13) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalRequirementDSL.g:80:3: (lv_requirements_0_0= ruleRequirement )
            	    {
            	    // InternalRequirementDSL.g:80:3: (lv_requirements_0_0= ruleRequirement )
            	    // InternalRequirementDSL.g:81:4: lv_requirements_0_0= ruleRequirement
            	    {

            	    				newCompositeNode(grammarAccess.getModelAccess().getRequirementsRequirementParserRuleCall_0());
            	    			
            	    pushFollow(FOLLOW_3);
            	    lv_requirements_0_0=ruleRequirement();

            	    state._fsp--;


            	    				if (current==null) {
            	    					current = createModelElementForParent(grammarAccess.getModelRule());
            	    				}
            	    				add(
            	    					current,
            	    					"requirements",
            	    					lv_requirements_0_0,
            	    					"de.fraunhofer.isst.stars.RequirementDSL.Requirement");
            	    				afterParserOrEnumRuleCall();
            	    			

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleRequirement"
    // InternalRequirementDSL.g:101:1: entryRuleRequirement returns [EObject current=null] : iv_ruleRequirement= ruleRequirement EOF ;
    public final EObject entryRuleRequirement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRequirement = null;


        try {
            // InternalRequirementDSL.g:101:52: (iv_ruleRequirement= ruleRequirement EOF )
            // InternalRequirementDSL.g:102:2: iv_ruleRequirement= ruleRequirement EOF
            {
             newCompositeNode(grammarAccess.getRequirementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRequirement=ruleRequirement();

            state._fsp--;

             current =iv_ruleRequirement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRequirement"


    // $ANTLR start "ruleRequirement"
    // InternalRequirementDSL.g:108:1: ruleRequirement returns [EObject current=null] : (otherlv_0= 'Req' ( (lv_reqID_1_0= ruleReqID ) )? otherlv_2= ':' ( (lv_text_3_0= ruleRequirementText ) ) (otherlv_4= '.' | otherlv_5= ';' ) ) ;
    public final EObject ruleRequirement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_reqID_1_0 = null;

        EObject lv_text_3_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:114:2: ( (otherlv_0= 'Req' ( (lv_reqID_1_0= ruleReqID ) )? otherlv_2= ':' ( (lv_text_3_0= ruleRequirementText ) ) (otherlv_4= '.' | otherlv_5= ';' ) ) )
            // InternalRequirementDSL.g:115:2: (otherlv_0= 'Req' ( (lv_reqID_1_0= ruleReqID ) )? otherlv_2= ':' ( (lv_text_3_0= ruleRequirementText ) ) (otherlv_4= '.' | otherlv_5= ';' ) )
            {
            // InternalRequirementDSL.g:115:2: (otherlv_0= 'Req' ( (lv_reqID_1_0= ruleReqID ) )? otherlv_2= ':' ( (lv_text_3_0= ruleRequirementText ) ) (otherlv_4= '.' | otherlv_5= ';' ) )
            // InternalRequirementDSL.g:116:3: otherlv_0= 'Req' ( (lv_reqID_1_0= ruleReqID ) )? otherlv_2= ':' ( (lv_text_3_0= ruleRequirementText ) ) (otherlv_4= '.' | otherlv_5= ';' )
            {
            otherlv_0=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getRequirementAccess().getReqKeyword_0());
            		
            // InternalRequirementDSL.g:120:3: ( (lv_reqID_1_0= ruleReqID ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( ((LA2_0>=RULE_INT && LA2_0<=RULE_ID)) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalRequirementDSL.g:121:4: (lv_reqID_1_0= ruleReqID )
                    {
                    // InternalRequirementDSL.g:121:4: (lv_reqID_1_0= ruleReqID )
                    // InternalRequirementDSL.g:122:5: lv_reqID_1_0= ruleReqID
                    {

                    					newCompositeNode(grammarAccess.getRequirementAccess().getReqIDReqIDParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_5);
                    lv_reqID_1_0=ruleReqID();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getRequirementRule());
                    					}
                    					set(
                    						current,
                    						"reqID",
                    						lv_reqID_1_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.ReqID");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,14,FOLLOW_6); 

            			newLeafNode(otherlv_2, grammarAccess.getRequirementAccess().getColonKeyword_2());
            		
            // InternalRequirementDSL.g:143:3: ( (lv_text_3_0= ruleRequirementText ) )
            // InternalRequirementDSL.g:144:4: (lv_text_3_0= ruleRequirementText )
            {
            // InternalRequirementDSL.g:144:4: (lv_text_3_0= ruleRequirementText )
            // InternalRequirementDSL.g:145:5: lv_text_3_0= ruleRequirementText
            {

            					newCompositeNode(grammarAccess.getRequirementAccess().getTextRequirementTextParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_7);
            lv_text_3_0=ruleRequirementText();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRequirementRule());
            					}
            					set(
            						current,
            						"text",
            						lv_text_3_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.RequirementText");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:162:3: (otherlv_4= '.' | otherlv_5= ';' )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==15) ) {
                alt3=1;
            }
            else if ( (LA3_0==16) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalRequirementDSL.g:163:4: otherlv_4= '.'
                    {
                    otherlv_4=(Token)match(input,15,FOLLOW_2); 

                    				newLeafNode(otherlv_4, grammarAccess.getRequirementAccess().getFullStopKeyword_4_0());
                    			

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:168:4: otherlv_5= ';'
                    {
                    otherlv_5=(Token)match(input,16,FOLLOW_2); 

                    				newLeafNode(otherlv_5, grammarAccess.getRequirementAccess().getSemicolonKeyword_4_1());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRequirement"


    // $ANTLR start "entryRuleRequirementText"
    // InternalRequirementDSL.g:177:1: entryRuleRequirementText returns [EObject current=null] : iv_ruleRequirementText= ruleRequirementText EOF ;
    public final EObject entryRuleRequirementText() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRequirementText = null;


        try {
            // InternalRequirementDSL.g:177:56: (iv_ruleRequirementText= ruleRequirementText EOF )
            // InternalRequirementDSL.g:178:2: iv_ruleRequirementText= ruleRequirementText EOF
            {
             newCompositeNode(grammarAccess.getRequirementTextRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRequirementText=ruleRequirementText();

            state._fsp--;

             current =iv_ruleRequirementText; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRequirementText"


    // $ANTLR start "ruleRequirementText"
    // InternalRequirementDSL.g:184:1: ruleRequirementText returns [EObject current=null] : ( ( ( (lv_condClauses_0_0= ruleConditionalClause ) ) (otherlv_1= ',' )? otherlv_2= 'then' )? ( (lv_mainclauses_3_0= ruleMainClause ) ) ( (otherlv_4= ',' )? ( (lv_condClauses_5_0= ruleConditionalClause ) ) )? ) ;
    public final EObject ruleRequirementText() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_condClauses_0_0 = null;

        EObject lv_mainclauses_3_0 = null;

        EObject lv_condClauses_5_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:190:2: ( ( ( ( (lv_condClauses_0_0= ruleConditionalClause ) ) (otherlv_1= ',' )? otherlv_2= 'then' )? ( (lv_mainclauses_3_0= ruleMainClause ) ) ( (otherlv_4= ',' )? ( (lv_condClauses_5_0= ruleConditionalClause ) ) )? ) )
            // InternalRequirementDSL.g:191:2: ( ( ( (lv_condClauses_0_0= ruleConditionalClause ) ) (otherlv_1= ',' )? otherlv_2= 'then' )? ( (lv_mainclauses_3_0= ruleMainClause ) ) ( (otherlv_4= ',' )? ( (lv_condClauses_5_0= ruleConditionalClause ) ) )? )
            {
            // InternalRequirementDSL.g:191:2: ( ( ( (lv_condClauses_0_0= ruleConditionalClause ) ) (otherlv_1= ',' )? otherlv_2= 'then' )? ( (lv_mainclauses_3_0= ruleMainClause ) ) ( (otherlv_4= ',' )? ( (lv_condClauses_5_0= ruleConditionalClause ) ) )? )
            // InternalRequirementDSL.g:192:3: ( ( (lv_condClauses_0_0= ruleConditionalClause ) ) (otherlv_1= ',' )? otherlv_2= 'then' )? ( (lv_mainclauses_3_0= ruleMainClause ) ) ( (otherlv_4= ',' )? ( (lv_condClauses_5_0= ruleConditionalClause ) ) )?
            {
            // InternalRequirementDSL.g:192:3: ( ( (lv_condClauses_0_0= ruleConditionalClause ) ) (otherlv_1= ',' )? otherlv_2= 'then' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( ((LA5_0>=160 && LA5_0<=175)) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalRequirementDSL.g:193:4: ( (lv_condClauses_0_0= ruleConditionalClause ) ) (otherlv_1= ',' )? otherlv_2= 'then'
                    {
                    // InternalRequirementDSL.g:193:4: ( (lv_condClauses_0_0= ruleConditionalClause ) )
                    // InternalRequirementDSL.g:194:5: (lv_condClauses_0_0= ruleConditionalClause )
                    {
                    // InternalRequirementDSL.g:194:5: (lv_condClauses_0_0= ruleConditionalClause )
                    // InternalRequirementDSL.g:195:6: lv_condClauses_0_0= ruleConditionalClause
                    {

                    						newCompositeNode(grammarAccess.getRequirementTextAccess().getCondClausesConditionalClauseParserRuleCall_0_0_0());
                    					
                    pushFollow(FOLLOW_8);
                    lv_condClauses_0_0=ruleConditionalClause();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getRequirementTextRule());
                    						}
                    						add(
                    							current,
                    							"condClauses",
                    							lv_condClauses_0_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.ConditionalClause");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:212:4: (otherlv_1= ',' )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==17) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // InternalRequirementDSL.g:213:5: otherlv_1= ','
                            {
                            otherlv_1=(Token)match(input,17,FOLLOW_9); 

                            					newLeafNode(otherlv_1, grammarAccess.getRequirementTextAccess().getCommaKeyword_0_1());
                            				

                            }
                            break;

                    }

                    otherlv_2=(Token)match(input,18,FOLLOW_6); 

                    				newLeafNode(otherlv_2, grammarAccess.getRequirementTextAccess().getThenKeyword_0_2());
                    			

                    }
                    break;

            }

            // InternalRequirementDSL.g:223:3: ( (lv_mainclauses_3_0= ruleMainClause ) )
            // InternalRequirementDSL.g:224:4: (lv_mainclauses_3_0= ruleMainClause )
            {
            // InternalRequirementDSL.g:224:4: (lv_mainclauses_3_0= ruleMainClause )
            // InternalRequirementDSL.g:225:5: lv_mainclauses_3_0= ruleMainClause
            {

            					newCompositeNode(grammarAccess.getRequirementTextAccess().getMainclausesMainClauseParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_10);
            lv_mainclauses_3_0=ruleMainClause();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRequirementTextRule());
            					}
            					set(
            						current,
            						"mainclauses",
            						lv_mainclauses_3_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.MainClause");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:242:3: ( (otherlv_4= ',' )? ( (lv_condClauses_5_0= ruleConditionalClause ) ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==17||(LA7_0>=160 && LA7_0<=175)) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalRequirementDSL.g:243:4: (otherlv_4= ',' )? ( (lv_condClauses_5_0= ruleConditionalClause ) )
                    {
                    // InternalRequirementDSL.g:243:4: (otherlv_4= ',' )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==17) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // InternalRequirementDSL.g:244:5: otherlv_4= ','
                            {
                            otherlv_4=(Token)match(input,17,FOLLOW_11); 

                            					newLeafNode(otherlv_4, grammarAccess.getRequirementTextAccess().getCommaKeyword_2_0());
                            				

                            }
                            break;

                    }

                    // InternalRequirementDSL.g:249:4: ( (lv_condClauses_5_0= ruleConditionalClause ) )
                    // InternalRequirementDSL.g:250:5: (lv_condClauses_5_0= ruleConditionalClause )
                    {
                    // InternalRequirementDSL.g:250:5: (lv_condClauses_5_0= ruleConditionalClause )
                    // InternalRequirementDSL.g:251:6: lv_condClauses_5_0= ruleConditionalClause
                    {

                    						newCompositeNode(grammarAccess.getRequirementTextAccess().getCondClausesConditionalClauseParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_condClauses_5_0=ruleConditionalClause();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getRequirementTextRule());
                    						}
                    						add(
                    							current,
                    							"condClauses",
                    							lv_condClauses_5_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.ConditionalClause");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRequirementText"


    // $ANTLR start "entryRuleConditionalClause"
    // InternalRequirementDSL.g:273:1: entryRuleConditionalClause returns [EObject current=null] : iv_ruleConditionalClause= ruleConditionalClause EOF ;
    public final EObject entryRuleConditionalClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConditionalClause = null;


        try {
            // InternalRequirementDSL.g:273:58: (iv_ruleConditionalClause= ruleConditionalClause EOF )
            // InternalRequirementDSL.g:274:2: iv_ruleConditionalClause= ruleConditionalClause EOF
            {
             newCompositeNode(grammarAccess.getConditionalClauseRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConditionalClause=ruleConditionalClause();

            state._fsp--;

             current =iv_ruleConditionalClause; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConditionalClause"


    // $ANTLR start "ruleConditionalClause"
    // InternalRequirementDSL.g:280:1: ruleConditionalClause returns [EObject current=null] : ( ( (lv_ordinator_0_0= ruleClauseOrdinator ) ) ( (lv_clauses_1_0= ruleClauses ) ) ) ;
    public final EObject ruleConditionalClause() throws RecognitionException {
        EObject current = null;

        Enumerator lv_ordinator_0_0 = null;

        EObject lv_clauses_1_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:286:2: ( ( ( (lv_ordinator_0_0= ruleClauseOrdinator ) ) ( (lv_clauses_1_0= ruleClauses ) ) ) )
            // InternalRequirementDSL.g:287:2: ( ( (lv_ordinator_0_0= ruleClauseOrdinator ) ) ( (lv_clauses_1_0= ruleClauses ) ) )
            {
            // InternalRequirementDSL.g:287:2: ( ( (lv_ordinator_0_0= ruleClauseOrdinator ) ) ( (lv_clauses_1_0= ruleClauses ) ) )
            // InternalRequirementDSL.g:288:3: ( (lv_ordinator_0_0= ruleClauseOrdinator ) ) ( (lv_clauses_1_0= ruleClauses ) )
            {
            // InternalRequirementDSL.g:288:3: ( (lv_ordinator_0_0= ruleClauseOrdinator ) )
            // InternalRequirementDSL.g:289:4: (lv_ordinator_0_0= ruleClauseOrdinator )
            {
            // InternalRequirementDSL.g:289:4: (lv_ordinator_0_0= ruleClauseOrdinator )
            // InternalRequirementDSL.g:290:5: lv_ordinator_0_0= ruleClauseOrdinator
            {

            					newCompositeNode(grammarAccess.getConditionalClauseAccess().getOrdinatorClauseOrdinatorEnumRuleCall_0_0());
            				
            pushFollow(FOLLOW_6);
            lv_ordinator_0_0=ruleClauseOrdinator();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConditionalClauseRule());
            					}
            					set(
            						current,
            						"ordinator",
            						lv_ordinator_0_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.ClauseOrdinator");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:307:3: ( (lv_clauses_1_0= ruleClauses ) )
            // InternalRequirementDSL.g:308:4: (lv_clauses_1_0= ruleClauses )
            {
            // InternalRequirementDSL.g:308:4: (lv_clauses_1_0= ruleClauses )
            // InternalRequirementDSL.g:309:5: lv_clauses_1_0= ruleClauses
            {

            					newCompositeNode(grammarAccess.getConditionalClauseAccess().getClausesClausesParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_clauses_1_0=ruleClauses();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConditionalClauseRule());
            					}
            					set(
            						current,
            						"clauses",
            						lv_clauses_1_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.Clauses");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConditionalClause"


    // $ANTLR start "entryRuleMainClause"
    // InternalRequirementDSL.g:330:1: entryRuleMainClause returns [EObject current=null] : iv_ruleMainClause= ruleMainClause EOF ;
    public final EObject entryRuleMainClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMainClause = null;


        try {
            // InternalRequirementDSL.g:330:51: (iv_ruleMainClause= ruleMainClause EOF )
            // InternalRequirementDSL.g:331:2: iv_ruleMainClause= ruleMainClause EOF
            {
             newCompositeNode(grammarAccess.getMainClauseRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMainClause=ruleMainClause();

            state._fsp--;

             current =iv_ruleMainClause; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMainClause"


    // $ANTLR start "ruleMainClause"
    // InternalRequirementDSL.g:337:1: ruleMainClause returns [EObject current=null] : ( ( (lv_modifier_0_0= ruleModifier ) )? ( (lv_clauses_1_0= ruleClauses ) ) ) ;
    public final EObject ruleMainClause() throws RecognitionException {
        EObject current = null;

        Enumerator lv_modifier_0_0 = null;

        EObject lv_clauses_1_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:343:2: ( ( ( (lv_modifier_0_0= ruleModifier ) )? ( (lv_clauses_1_0= ruleClauses ) ) ) )
            // InternalRequirementDSL.g:344:2: ( ( (lv_modifier_0_0= ruleModifier ) )? ( (lv_clauses_1_0= ruleClauses ) ) )
            {
            // InternalRequirementDSL.g:344:2: ( ( (lv_modifier_0_0= ruleModifier ) )? ( (lv_clauses_1_0= ruleClauses ) ) )
            // InternalRequirementDSL.g:345:3: ( (lv_modifier_0_0= ruleModifier ) )? ( (lv_clauses_1_0= ruleClauses ) )
            {
            // InternalRequirementDSL.g:345:3: ( (lv_modifier_0_0= ruleModifier ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( ((LA8_0>=152 && LA8_0<=159)) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalRequirementDSL.g:346:4: (lv_modifier_0_0= ruleModifier )
                    {
                    // InternalRequirementDSL.g:346:4: (lv_modifier_0_0= ruleModifier )
                    // InternalRequirementDSL.g:347:5: lv_modifier_0_0= ruleModifier
                    {

                    					newCompositeNode(grammarAccess.getMainClauseAccess().getModifierModifierEnumRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_6);
                    lv_modifier_0_0=ruleModifier();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getMainClauseRule());
                    					}
                    					set(
                    						current,
                    						"modifier",
                    						lv_modifier_0_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.Modifier");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalRequirementDSL.g:364:3: ( (lv_clauses_1_0= ruleClauses ) )
            // InternalRequirementDSL.g:365:4: (lv_clauses_1_0= ruleClauses )
            {
            // InternalRequirementDSL.g:365:4: (lv_clauses_1_0= ruleClauses )
            // InternalRequirementDSL.g:366:5: lv_clauses_1_0= ruleClauses
            {

            					newCompositeNode(grammarAccess.getMainClauseAccess().getClausesClausesParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_clauses_1_0=ruleClauses();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMainClauseRule());
            					}
            					add(
            						current,
            						"clauses",
            						lv_clauses_1_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.Clauses");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMainClause"


    // $ANTLR start "entryRuleClauses"
    // InternalRequirementDSL.g:387:1: entryRuleClauses returns [EObject current=null] : iv_ruleClauses= ruleClauses EOF ;
    public final EObject entryRuleClauses() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClauses = null;


        try {
            // InternalRequirementDSL.g:387:48: (iv_ruleClauses= ruleClauses EOF )
            // InternalRequirementDSL.g:388:2: iv_ruleClauses= ruleClauses EOF
            {
             newCompositeNode(grammarAccess.getClausesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleClauses=ruleClauses();

            state._fsp--;

             current =iv_ruleClauses; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleClauses"


    // $ANTLR start "ruleClauses"
    // InternalRequirementDSL.g:394:1: ruleClauses returns [EObject current=null] : ( ( (lv_clauses_0_0= ruleClause ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_clauses_2_0= ruleClause ) ) )* ) ;
    public final EObject ruleClauses() throws RecognitionException {
        EObject current = null;

        EObject lv_clauses_0_0 = null;

        AntlrDatatypeRuleToken lv_conjunction_1_0 = null;

        EObject lv_clauses_2_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:400:2: ( ( ( (lv_clauses_0_0= ruleClause ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_clauses_2_0= ruleClause ) ) )* ) )
            // InternalRequirementDSL.g:401:2: ( ( (lv_clauses_0_0= ruleClause ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_clauses_2_0= ruleClause ) ) )* )
            {
            // InternalRequirementDSL.g:401:2: ( ( (lv_clauses_0_0= ruleClause ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_clauses_2_0= ruleClause ) ) )* )
            // InternalRequirementDSL.g:402:3: ( (lv_clauses_0_0= ruleClause ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_clauses_2_0= ruleClause ) ) )*
            {
            // InternalRequirementDSL.g:402:3: ( (lv_clauses_0_0= ruleClause ) )
            // InternalRequirementDSL.g:403:4: (lv_clauses_0_0= ruleClause )
            {
            // InternalRequirementDSL.g:403:4: (lv_clauses_0_0= ruleClause )
            // InternalRequirementDSL.g:404:5: lv_clauses_0_0= ruleClause
            {

            					newCompositeNode(grammarAccess.getClausesAccess().getClausesClauseParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_12);
            lv_clauses_0_0=ruleClause();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getClausesRule());
            					}
            					add(
            						current,
            						"clauses",
            						lv_clauses_0_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.Clause");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:421:3: ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_clauses_2_0= ruleClause ) ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==37) ) {
                    int LA9_2 = input.LA(2);

                    if ( (LA9_2==RULE_STRING||LA9_2==RULE_ID||(LA9_2>=19 && LA9_2<=20)||(LA9_2>=51 && LA9_2<=61)||(LA9_2>=67 && LA9_2<=80)||(LA9_2>=86 && LA9_2<=95)) ) {
                        alt9=1;
                    }


                }
                else if ( (LA9_0==38) ) {
                    int LA9_3 = input.LA(2);

                    if ( (LA9_3==RULE_STRING||LA9_3==RULE_ID||(LA9_3>=19 && LA9_3<=20)||(LA9_3>=51 && LA9_3<=61)||(LA9_3>=67 && LA9_3<=80)||(LA9_3>=86 && LA9_3<=95)) ) {
                        alt9=1;
                    }


                }


                switch (alt9) {
            	case 1 :
            	    // InternalRequirementDSL.g:422:4: ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_clauses_2_0= ruleClause ) )
            	    {
            	    // InternalRequirementDSL.g:422:4: ( (lv_conjunction_1_0= ruleConjunction ) )
            	    // InternalRequirementDSL.g:423:5: (lv_conjunction_1_0= ruleConjunction )
            	    {
            	    // InternalRequirementDSL.g:423:5: (lv_conjunction_1_0= ruleConjunction )
            	    // InternalRequirementDSL.g:424:6: lv_conjunction_1_0= ruleConjunction
            	    {

            	    						newCompositeNode(grammarAccess.getClausesAccess().getConjunctionConjunctionParserRuleCall_1_0_0());
            	    					
            	    pushFollow(FOLLOW_6);
            	    lv_conjunction_1_0=ruleConjunction();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getClausesRule());
            	    						}
            	    						add(
            	    							current,
            	    							"conjunction",
            	    							lv_conjunction_1_0,
            	    							"de.fraunhofer.isst.stars.RequirementDSL.Conjunction");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    // InternalRequirementDSL.g:441:4: ( (lv_clauses_2_0= ruleClause ) )
            	    // InternalRequirementDSL.g:442:5: (lv_clauses_2_0= ruleClause )
            	    {
            	    // InternalRequirementDSL.g:442:5: (lv_clauses_2_0= ruleClause )
            	    // InternalRequirementDSL.g:443:6: lv_clauses_2_0= ruleClause
            	    {

            	    						newCompositeNode(grammarAccess.getClausesAccess().getClausesClauseParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_12);
            	    lv_clauses_2_0=ruleClause();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getClausesRule());
            	    						}
            	    						add(
            	    							current,
            	    							"clauses",
            	    							lv_clauses_2_0,
            	    							"de.fraunhofer.isst.stars.RequirementDSL.Clause");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleClauses"


    // $ANTLR start "entryRuleClause"
    // InternalRequirementDSL.g:465:1: entryRuleClause returns [EObject current=null] : iv_ruleClause= ruleClause EOF ;
    public final EObject entryRuleClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClause = null;


        try {
            // InternalRequirementDSL.g:465:47: (iv_ruleClause= ruleClause EOF )
            // InternalRequirementDSL.g:466:2: iv_ruleClause= ruleClause EOF
            {
             newCompositeNode(grammarAccess.getClauseRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleClause=ruleClause();

            state._fsp--;

             current =iv_ruleClause; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleClause"


    // $ANTLR start "ruleClause"
    // InternalRequirementDSL.g:472:1: ruleClause returns [EObject current=null] : (this_ModalitySentence_0= ruleModalitySentence | this_PredicateSentence_1= rulePredicateSentence | this_ExistenceSentence_2= ruleExistenceSentence | this_PropertySentence_3= rulePropertySentence ) ;
    public final EObject ruleClause() throws RecognitionException {
        EObject current = null;

        EObject this_ModalitySentence_0 = null;

        EObject this_PredicateSentence_1 = null;

        EObject this_ExistenceSentence_2 = null;

        EObject this_PropertySentence_3 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:478:2: ( (this_ModalitySentence_0= ruleModalitySentence | this_PredicateSentence_1= rulePredicateSentence | this_ExistenceSentence_2= ruleExistenceSentence | this_PropertySentence_3= rulePropertySentence ) )
            // InternalRequirementDSL.g:479:2: (this_ModalitySentence_0= ruleModalitySentence | this_PredicateSentence_1= rulePredicateSentence | this_ExistenceSentence_2= ruleExistenceSentence | this_PropertySentence_3= rulePropertySentence )
            {
            // InternalRequirementDSL.g:479:2: (this_ModalitySentence_0= ruleModalitySentence | this_PredicateSentence_1= rulePredicateSentence | this_ExistenceSentence_2= ruleExistenceSentence | this_PropertySentence_3= rulePropertySentence )
            int alt10=4;
            alt10 = dfa10.predict(input);
            switch (alt10) {
                case 1 :
                    // InternalRequirementDSL.g:480:3: this_ModalitySentence_0= ruleModalitySentence
                    {

                    			newCompositeNode(grammarAccess.getClauseAccess().getModalitySentenceParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_ModalitySentence_0=ruleModalitySentence();

                    state._fsp--;


                    			current = this_ModalitySentence_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:489:3: this_PredicateSentence_1= rulePredicateSentence
                    {

                    			newCompositeNode(grammarAccess.getClauseAccess().getPredicateSentenceParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_PredicateSentence_1=rulePredicateSentence();

                    state._fsp--;


                    			current = this_PredicateSentence_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:498:3: this_ExistenceSentence_2= ruleExistenceSentence
                    {

                    			newCompositeNode(grammarAccess.getClauseAccess().getExistenceSentenceParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_ExistenceSentence_2=ruleExistenceSentence();

                    state._fsp--;


                    			current = this_ExistenceSentence_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:507:3: this_PropertySentence_3= rulePropertySentence
                    {

                    			newCompositeNode(grammarAccess.getClauseAccess().getPropertySentenceParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_PropertySentence_3=rulePropertySentence();

                    state._fsp--;


                    			current = this_PropertySentence_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleClause"


    // $ANTLR start "entryRuleModalitySentence"
    // InternalRequirementDSL.g:519:1: entryRuleModalitySentence returns [EObject current=null] : iv_ruleModalitySentence= ruleModalitySentence EOF ;
    public final EObject entryRuleModalitySentence() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModalitySentence = null;


        try {
            // InternalRequirementDSL.g:519:57: (iv_ruleModalitySentence= ruleModalitySentence EOF )
            // InternalRequirementDSL.g:520:2: iv_ruleModalitySentence= ruleModalitySentence EOF
            {
             newCompositeNode(grammarAccess.getModalitySentenceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModalitySentence=ruleModalitySentence();

            state._fsp--;

             current =iv_ruleModalitySentence; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModalitySentence"


    // $ANTLR start "ruleModalitySentence"
    // InternalRequirementDSL.g:526:1: ruleModalitySentence returns [EObject current=null] : ( ( (lv_begin_0_0= ruleSentenceBegin ) )? ( (lv_actors_1_0= ruleActors ) ) ( (lv_modelity_2_0= ruleModality ) ) ( (lv_negation_3_0= ruleNegation ) )? ( (lv_auxiliarVerb_4_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_5_0= rulePredicate ) ) ( (lv_ending_6_0= ruleSentenceEnding ) )? ) ;
    public final EObject ruleModalitySentence() throws RecognitionException {
        EObject current = null;

        EObject lv_begin_0_0 = null;

        EObject lv_actors_1_0 = null;

        Enumerator lv_modelity_2_0 = null;

        AntlrDatatypeRuleToken lv_negation_3_0 = null;

        AntlrDatatypeRuleToken lv_auxiliarVerb_4_0 = null;

        EObject lv_predicate_5_0 = null;

        EObject lv_ending_6_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:532:2: ( ( ( (lv_begin_0_0= ruleSentenceBegin ) )? ( (lv_actors_1_0= ruleActors ) ) ( (lv_modelity_2_0= ruleModality ) ) ( (lv_negation_3_0= ruleNegation ) )? ( (lv_auxiliarVerb_4_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_5_0= rulePredicate ) ) ( (lv_ending_6_0= ruleSentenceEnding ) )? ) )
            // InternalRequirementDSL.g:533:2: ( ( (lv_begin_0_0= ruleSentenceBegin ) )? ( (lv_actors_1_0= ruleActors ) ) ( (lv_modelity_2_0= ruleModality ) ) ( (lv_negation_3_0= ruleNegation ) )? ( (lv_auxiliarVerb_4_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_5_0= rulePredicate ) ) ( (lv_ending_6_0= ruleSentenceEnding ) )? )
            {
            // InternalRequirementDSL.g:533:2: ( ( (lv_begin_0_0= ruleSentenceBegin ) )? ( (lv_actors_1_0= ruleActors ) ) ( (lv_modelity_2_0= ruleModality ) ) ( (lv_negation_3_0= ruleNegation ) )? ( (lv_auxiliarVerb_4_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_5_0= rulePredicate ) ) ( (lv_ending_6_0= ruleSentenceEnding ) )? )
            // InternalRequirementDSL.g:534:3: ( (lv_begin_0_0= ruleSentenceBegin ) )? ( (lv_actors_1_0= ruleActors ) ) ( (lv_modelity_2_0= ruleModality ) ) ( (lv_negation_3_0= ruleNegation ) )? ( (lv_auxiliarVerb_4_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_5_0= rulePredicate ) ) ( (lv_ending_6_0= ruleSentenceEnding ) )?
            {
            // InternalRequirementDSL.g:534:3: ( (lv_begin_0_0= ruleSentenceBegin ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( ((LA11_0>=51 && LA11_0<=61)) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalRequirementDSL.g:535:4: (lv_begin_0_0= ruleSentenceBegin )
                    {
                    // InternalRequirementDSL.g:535:4: (lv_begin_0_0= ruleSentenceBegin )
                    // InternalRequirementDSL.g:536:5: lv_begin_0_0= ruleSentenceBegin
                    {

                    					newCompositeNode(grammarAccess.getModalitySentenceAccess().getBeginSentenceBeginParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_13);
                    lv_begin_0_0=ruleSentenceBegin();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getModalitySentenceRule());
                    					}
                    					set(
                    						current,
                    						"begin",
                    						lv_begin_0_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.SentenceBegin");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalRequirementDSL.g:553:3: ( (lv_actors_1_0= ruleActors ) )
            // InternalRequirementDSL.g:554:4: (lv_actors_1_0= ruleActors )
            {
            // InternalRequirementDSL.g:554:4: (lv_actors_1_0= ruleActors )
            // InternalRequirementDSL.g:555:5: lv_actors_1_0= ruleActors
            {

            					newCompositeNode(grammarAccess.getModalitySentenceAccess().getActorsActorsParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_14);
            lv_actors_1_0=ruleActors();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getModalitySentenceRule());
            					}
            					set(
            						current,
            						"actors",
            						lv_actors_1_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.Actors");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:572:3: ( (lv_modelity_2_0= ruleModality ) )
            // InternalRequirementDSL.g:573:4: (lv_modelity_2_0= ruleModality )
            {
            // InternalRequirementDSL.g:573:4: (lv_modelity_2_0= ruleModality )
            // InternalRequirementDSL.g:574:5: lv_modelity_2_0= ruleModality
            {

            					newCompositeNode(grammarAccess.getModalitySentenceAccess().getModelityModalityEnumRuleCall_2_0());
            				
            pushFollow(FOLLOW_15);
            lv_modelity_2_0=ruleModality();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getModalitySentenceRule());
            					}
            					set(
            						current,
            						"modelity",
            						lv_modelity_2_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.Modality");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:591:3: ( (lv_negation_3_0= ruleNegation ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==81) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalRequirementDSL.g:592:4: (lv_negation_3_0= ruleNegation )
                    {
                    // InternalRequirementDSL.g:592:4: (lv_negation_3_0= ruleNegation )
                    // InternalRequirementDSL.g:593:5: lv_negation_3_0= ruleNegation
                    {

                    					newCompositeNode(grammarAccess.getModalitySentenceAccess().getNegationNegationParserRuleCall_3_0());
                    				
                    pushFollow(FOLLOW_15);
                    lv_negation_3_0=ruleNegation();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getModalitySentenceRule());
                    					}
                    					set(
                    						current,
                    						"negation",
                    						true,
                    						"de.fraunhofer.isst.stars.RequirementDSL.Negation");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalRequirementDSL.g:610:3: ( (lv_auxiliarVerb_4_0= ruleAuxiliaryVerb ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( ((LA13_0>=29 && LA13_0<=36)) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalRequirementDSL.g:611:4: (lv_auxiliarVerb_4_0= ruleAuxiliaryVerb )
                    {
                    // InternalRequirementDSL.g:611:4: (lv_auxiliarVerb_4_0= ruleAuxiliaryVerb )
                    // InternalRequirementDSL.g:612:5: lv_auxiliarVerb_4_0= ruleAuxiliaryVerb
                    {

                    					newCompositeNode(grammarAccess.getModalitySentenceAccess().getAuxiliarVerbAuxiliaryVerbParserRuleCall_4_0());
                    				
                    pushFollow(FOLLOW_15);
                    lv_auxiliarVerb_4_0=ruleAuxiliaryVerb();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getModalitySentenceRule());
                    					}
                    					set(
                    						current,
                    						"auxiliarVerb",
                    						lv_auxiliarVerb_4_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.AuxiliaryVerb");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalRequirementDSL.g:629:3: ( (lv_predicate_5_0= rulePredicate ) )
            // InternalRequirementDSL.g:630:4: (lv_predicate_5_0= rulePredicate )
            {
            // InternalRequirementDSL.g:630:4: (lv_predicate_5_0= rulePredicate )
            // InternalRequirementDSL.g:631:5: lv_predicate_5_0= rulePredicate
            {

            					newCompositeNode(grammarAccess.getModalitySentenceAccess().getPredicatePredicateParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_16);
            lv_predicate_5_0=rulePredicate();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getModalitySentenceRule());
            					}
            					set(
            						current,
            						"predicate",
            						lv_predicate_5_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.Predicate");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:648:3: ( (lv_ending_6_0= ruleSentenceEnding ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==43||(LA14_0>=45 && LA14_0<=66)||LA14_0==96) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalRequirementDSL.g:649:4: (lv_ending_6_0= ruleSentenceEnding )
                    {
                    // InternalRequirementDSL.g:649:4: (lv_ending_6_0= ruleSentenceEnding )
                    // InternalRequirementDSL.g:650:5: lv_ending_6_0= ruleSentenceEnding
                    {

                    					newCompositeNode(grammarAccess.getModalitySentenceAccess().getEndingSentenceEndingParserRuleCall_6_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_ending_6_0=ruleSentenceEnding();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getModalitySentenceRule());
                    					}
                    					set(
                    						current,
                    						"ending",
                    						lv_ending_6_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.SentenceEnding");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModalitySentence"


    // $ANTLR start "entryRulePredicateSentence"
    // InternalRequirementDSL.g:671:1: entryRulePredicateSentence returns [EObject current=null] : iv_rulePredicateSentence= rulePredicateSentence EOF ;
    public final EObject entryRulePredicateSentence() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePredicateSentence = null;


        try {
            // InternalRequirementDSL.g:671:58: (iv_rulePredicateSentence= rulePredicateSentence EOF )
            // InternalRequirementDSL.g:672:2: iv_rulePredicateSentence= rulePredicateSentence EOF
            {
             newCompositeNode(grammarAccess.getPredicateSentenceRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePredicateSentence=rulePredicateSentence();

            state._fsp--;

             current =iv_rulePredicateSentence; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePredicateSentence"


    // $ANTLR start "rulePredicateSentence"
    // InternalRequirementDSL.g:678:1: rulePredicateSentence returns [EObject current=null] : ( ( ( (lv_begin_0_0= ruleSentenceBegin ) )? ( (lv_actors_1_0= ruleActors ) ) ( (lv_auxNeg_2_0= ruleAuxNeg ) ) ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )? ( (lv_preds_4_0= rulePreds ) )? ( (lv_ending_5_0= ruleSentenceEnding ) )? ) | ( ( (lv_begin_6_0= ruleSentenceBegin ) )? ( (lv_actors_7_0= ruleActors ) ) ( (lv_preds_8_0= rulePreds ) ) ( (lv_ending_9_0= ruleSentenceEnding ) )? ) ) ;
    public final EObject rulePredicateSentence() throws RecognitionException {
        EObject current = null;

        EObject lv_begin_0_0 = null;

        EObject lv_actors_1_0 = null;

        EObject lv_auxNeg_2_0 = null;

        AntlrDatatypeRuleToken lv_auxiliarVerb_3_0 = null;

        EObject lv_preds_4_0 = null;

        EObject lv_ending_5_0 = null;

        EObject lv_begin_6_0 = null;

        EObject lv_actors_7_0 = null;

        EObject lv_preds_8_0 = null;

        EObject lv_ending_9_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:684:2: ( ( ( ( (lv_begin_0_0= ruleSentenceBegin ) )? ( (lv_actors_1_0= ruleActors ) ) ( (lv_auxNeg_2_0= ruleAuxNeg ) ) ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )? ( (lv_preds_4_0= rulePreds ) )? ( (lv_ending_5_0= ruleSentenceEnding ) )? ) | ( ( (lv_begin_6_0= ruleSentenceBegin ) )? ( (lv_actors_7_0= ruleActors ) ) ( (lv_preds_8_0= rulePreds ) ) ( (lv_ending_9_0= ruleSentenceEnding ) )? ) ) )
            // InternalRequirementDSL.g:685:2: ( ( ( (lv_begin_0_0= ruleSentenceBegin ) )? ( (lv_actors_1_0= ruleActors ) ) ( (lv_auxNeg_2_0= ruleAuxNeg ) ) ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )? ( (lv_preds_4_0= rulePreds ) )? ( (lv_ending_5_0= ruleSentenceEnding ) )? ) | ( ( (lv_begin_6_0= ruleSentenceBegin ) )? ( (lv_actors_7_0= ruleActors ) ) ( (lv_preds_8_0= rulePreds ) ) ( (lv_ending_9_0= ruleSentenceEnding ) )? ) )
            {
            // InternalRequirementDSL.g:685:2: ( ( ( (lv_begin_0_0= ruleSentenceBegin ) )? ( (lv_actors_1_0= ruleActors ) ) ( (lv_auxNeg_2_0= ruleAuxNeg ) ) ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )? ( (lv_preds_4_0= rulePreds ) )? ( (lv_ending_5_0= ruleSentenceEnding ) )? ) | ( ( (lv_begin_6_0= ruleSentenceBegin ) )? ( (lv_actors_7_0= ruleActors ) ) ( (lv_preds_8_0= rulePreds ) ) ( (lv_ending_9_0= ruleSentenceEnding ) )? ) )
            int alt21=2;
            alt21 = dfa21.predict(input);
            switch (alt21) {
                case 1 :
                    // InternalRequirementDSL.g:686:3: ( ( (lv_begin_0_0= ruleSentenceBegin ) )? ( (lv_actors_1_0= ruleActors ) ) ( (lv_auxNeg_2_0= ruleAuxNeg ) ) ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )? ( (lv_preds_4_0= rulePreds ) )? ( (lv_ending_5_0= ruleSentenceEnding ) )? )
                    {
                    // InternalRequirementDSL.g:686:3: ( ( (lv_begin_0_0= ruleSentenceBegin ) )? ( (lv_actors_1_0= ruleActors ) ) ( (lv_auxNeg_2_0= ruleAuxNeg ) ) ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )? ( (lv_preds_4_0= rulePreds ) )? ( (lv_ending_5_0= ruleSentenceEnding ) )? )
                    // InternalRequirementDSL.g:687:4: ( (lv_begin_0_0= ruleSentenceBegin ) )? ( (lv_actors_1_0= ruleActors ) ) ( (lv_auxNeg_2_0= ruleAuxNeg ) ) ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )? ( (lv_preds_4_0= rulePreds ) )? ( (lv_ending_5_0= ruleSentenceEnding ) )?
                    {
                    // InternalRequirementDSL.g:687:4: ( (lv_begin_0_0= ruleSentenceBegin ) )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( ((LA15_0>=51 && LA15_0<=61)) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // InternalRequirementDSL.g:688:5: (lv_begin_0_0= ruleSentenceBegin )
                            {
                            // InternalRequirementDSL.g:688:5: (lv_begin_0_0= ruleSentenceBegin )
                            // InternalRequirementDSL.g:689:6: lv_begin_0_0= ruleSentenceBegin
                            {

                            						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getBeginSentenceBeginParserRuleCall_0_0_0());
                            					
                            pushFollow(FOLLOW_13);
                            lv_begin_0_0=ruleSentenceBegin();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                            						}
                            						set(
                            							current,
                            							"begin",
                            							lv_begin_0_0,
                            							"de.fraunhofer.isst.stars.RequirementDSL.SentenceBegin");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }

                    // InternalRequirementDSL.g:706:4: ( (lv_actors_1_0= ruleActors ) )
                    // InternalRequirementDSL.g:707:5: (lv_actors_1_0= ruleActors )
                    {
                    // InternalRequirementDSL.g:707:5: (lv_actors_1_0= ruleActors )
                    // InternalRequirementDSL.g:708:6: lv_actors_1_0= ruleActors
                    {

                    						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getActorsActorsParserRuleCall_0_1_0());
                    					
                    pushFollow(FOLLOW_17);
                    lv_actors_1_0=ruleActors();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                    						}
                    						set(
                    							current,
                    							"actors",
                    							lv_actors_1_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Actors");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:725:4: ( (lv_auxNeg_2_0= ruleAuxNeg ) )
                    // InternalRequirementDSL.g:726:5: (lv_auxNeg_2_0= ruleAuxNeg )
                    {
                    // InternalRequirementDSL.g:726:5: (lv_auxNeg_2_0= ruleAuxNeg )
                    // InternalRequirementDSL.g:727:6: lv_auxNeg_2_0= ruleAuxNeg
                    {

                    						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getAuxNegAuxNegParserRuleCall_0_2_0());
                    					
                    pushFollow(FOLLOW_18);
                    lv_auxNeg_2_0=ruleAuxNeg();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                    						}
                    						add(
                    							current,
                    							"auxNeg",
                    							lv_auxNeg_2_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.AuxNeg");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:744:4: ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( ((LA16_0>=29 && LA16_0<=36)) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // InternalRequirementDSL.g:745:5: (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb )
                            {
                            // InternalRequirementDSL.g:745:5: (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb )
                            // InternalRequirementDSL.g:746:6: lv_auxiliarVerb_3_0= ruleAuxiliaryVerb
                            {

                            						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getAuxiliarVerbAuxiliaryVerbParserRuleCall_0_3_0());
                            					
                            pushFollow(FOLLOW_18);
                            lv_auxiliarVerb_3_0=ruleAuxiliaryVerb();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                            						}
                            						add(
                            							current,
                            							"auxiliarVerb",
                            							lv_auxiliarVerb_3_0,
                            							"de.fraunhofer.isst.stars.RequirementDSL.AuxiliaryVerb");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }

                    // InternalRequirementDSL.g:763:4: ( (lv_preds_4_0= rulePreds ) )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==RULE_STRING||LA17_0==RULE_ID||(LA17_0>=67 && LA17_0<=80)||(LA17_0>=86 && LA17_0<=95)) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // InternalRequirementDSL.g:764:5: (lv_preds_4_0= rulePreds )
                            {
                            // InternalRequirementDSL.g:764:5: (lv_preds_4_0= rulePreds )
                            // InternalRequirementDSL.g:765:6: lv_preds_4_0= rulePreds
                            {

                            						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getPredsPredsParserRuleCall_0_4_0());
                            					
                            pushFollow(FOLLOW_16);
                            lv_preds_4_0=rulePreds();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                            						}
                            						set(
                            							current,
                            							"preds",
                            							lv_preds_4_0,
                            							"de.fraunhofer.isst.stars.RequirementDSL.Preds");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }

                    // InternalRequirementDSL.g:782:4: ( (lv_ending_5_0= ruleSentenceEnding ) )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0==43||(LA18_0>=45 && LA18_0<=66)||LA18_0==96) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // InternalRequirementDSL.g:783:5: (lv_ending_5_0= ruleSentenceEnding )
                            {
                            // InternalRequirementDSL.g:783:5: (lv_ending_5_0= ruleSentenceEnding )
                            // InternalRequirementDSL.g:784:6: lv_ending_5_0= ruleSentenceEnding
                            {

                            						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getEndingSentenceEndingParserRuleCall_0_5_0());
                            					
                            pushFollow(FOLLOW_2);
                            lv_ending_5_0=ruleSentenceEnding();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                            						}
                            						set(
                            							current,
                            							"ending",
                            							lv_ending_5_0,
                            							"de.fraunhofer.isst.stars.RequirementDSL.SentenceEnding");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:803:3: ( ( (lv_begin_6_0= ruleSentenceBegin ) )? ( (lv_actors_7_0= ruleActors ) ) ( (lv_preds_8_0= rulePreds ) ) ( (lv_ending_9_0= ruleSentenceEnding ) )? )
                    {
                    // InternalRequirementDSL.g:803:3: ( ( (lv_begin_6_0= ruleSentenceBegin ) )? ( (lv_actors_7_0= ruleActors ) ) ( (lv_preds_8_0= rulePreds ) ) ( (lv_ending_9_0= ruleSentenceEnding ) )? )
                    // InternalRequirementDSL.g:804:4: ( (lv_begin_6_0= ruleSentenceBegin ) )? ( (lv_actors_7_0= ruleActors ) ) ( (lv_preds_8_0= rulePreds ) ) ( (lv_ending_9_0= ruleSentenceEnding ) )?
                    {
                    // InternalRequirementDSL.g:804:4: ( (lv_begin_6_0= ruleSentenceBegin ) )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( ((LA19_0>=51 && LA19_0<=61)) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // InternalRequirementDSL.g:805:5: (lv_begin_6_0= ruleSentenceBegin )
                            {
                            // InternalRequirementDSL.g:805:5: (lv_begin_6_0= ruleSentenceBegin )
                            // InternalRequirementDSL.g:806:6: lv_begin_6_0= ruleSentenceBegin
                            {

                            						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getBeginSentenceBeginParserRuleCall_1_0_0());
                            					
                            pushFollow(FOLLOW_13);
                            lv_begin_6_0=ruleSentenceBegin();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                            						}
                            						set(
                            							current,
                            							"begin",
                            							lv_begin_6_0,
                            							"de.fraunhofer.isst.stars.RequirementDSL.SentenceBegin");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }

                    // InternalRequirementDSL.g:823:4: ( (lv_actors_7_0= ruleActors ) )
                    // InternalRequirementDSL.g:824:5: (lv_actors_7_0= ruleActors )
                    {
                    // InternalRequirementDSL.g:824:5: (lv_actors_7_0= ruleActors )
                    // InternalRequirementDSL.g:825:6: lv_actors_7_0= ruleActors
                    {

                    						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getActorsActorsParserRuleCall_1_1_0());
                    					
                    pushFollow(FOLLOW_19);
                    lv_actors_7_0=ruleActors();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                    						}
                    						set(
                    							current,
                    							"actors",
                    							lv_actors_7_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Actors");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:842:4: ( (lv_preds_8_0= rulePreds ) )
                    // InternalRequirementDSL.g:843:5: (lv_preds_8_0= rulePreds )
                    {
                    // InternalRequirementDSL.g:843:5: (lv_preds_8_0= rulePreds )
                    // InternalRequirementDSL.g:844:6: lv_preds_8_0= rulePreds
                    {

                    						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getPredsPredsParserRuleCall_1_2_0());
                    					
                    pushFollow(FOLLOW_16);
                    lv_preds_8_0=rulePreds();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                    						}
                    						set(
                    							current,
                    							"preds",
                    							lv_preds_8_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Preds");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:861:4: ( (lv_ending_9_0= ruleSentenceEnding ) )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==43||(LA20_0>=45 && LA20_0<=66)||LA20_0==96) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // InternalRequirementDSL.g:862:5: (lv_ending_9_0= ruleSentenceEnding )
                            {
                            // InternalRequirementDSL.g:862:5: (lv_ending_9_0= ruleSentenceEnding )
                            // InternalRequirementDSL.g:863:6: lv_ending_9_0= ruleSentenceEnding
                            {

                            						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getEndingSentenceEndingParserRuleCall_1_3_0());
                            					
                            pushFollow(FOLLOW_2);
                            lv_ending_9_0=ruleSentenceEnding();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                            						}
                            						set(
                            							current,
                            							"ending",
                            							lv_ending_9_0,
                            							"de.fraunhofer.isst.stars.RequirementDSL.SentenceEnding");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePredicateSentence"


    // $ANTLR start "entryRuleExistenceSentence"
    // InternalRequirementDSL.g:885:1: entryRuleExistenceSentence returns [EObject current=null] : iv_ruleExistenceSentence= ruleExistenceSentence EOF ;
    public final EObject entryRuleExistenceSentence() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExistenceSentence = null;


        try {
            // InternalRequirementDSL.g:885:58: (iv_ruleExistenceSentence= ruleExistenceSentence EOF )
            // InternalRequirementDSL.g:886:2: iv_ruleExistenceSentence= ruleExistenceSentence EOF
            {
             newCompositeNode(grammarAccess.getExistenceSentenceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExistenceSentence=ruleExistenceSentence();

            state._fsp--;

             current =iv_ruleExistenceSentence; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExistenceSentence"


    // $ANTLR start "ruleExistenceSentence"
    // InternalRequirementDSL.g:892:1: ruleExistenceSentence returns [EObject current=null] : (this_ExistencePreface_0= ruleExistencePreface ( (lv_actors_1_0= ruleActors ) ) otherlv_2= ',' ( (lv_relativeClause_3_0= rulerelativeClause ) ) otherlv_4= ',' ) ;
    public final EObject ruleExistenceSentence() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_ExistencePreface_0 = null;

        EObject lv_actors_1_0 = null;

        EObject lv_relativeClause_3_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:898:2: ( (this_ExistencePreface_0= ruleExistencePreface ( (lv_actors_1_0= ruleActors ) ) otherlv_2= ',' ( (lv_relativeClause_3_0= rulerelativeClause ) ) otherlv_4= ',' ) )
            // InternalRequirementDSL.g:899:2: (this_ExistencePreface_0= ruleExistencePreface ( (lv_actors_1_0= ruleActors ) ) otherlv_2= ',' ( (lv_relativeClause_3_0= rulerelativeClause ) ) otherlv_4= ',' )
            {
            // InternalRequirementDSL.g:899:2: (this_ExistencePreface_0= ruleExistencePreface ( (lv_actors_1_0= ruleActors ) ) otherlv_2= ',' ( (lv_relativeClause_3_0= rulerelativeClause ) ) otherlv_4= ',' )
            // InternalRequirementDSL.g:900:3: this_ExistencePreface_0= ruleExistencePreface ( (lv_actors_1_0= ruleActors ) ) otherlv_2= ',' ( (lv_relativeClause_3_0= rulerelativeClause ) ) otherlv_4= ','
            {

            			newCompositeNode(grammarAccess.getExistenceSentenceAccess().getExistencePrefaceParserRuleCall_0());
            		
            pushFollow(FOLLOW_13);
            this_ExistencePreface_0=ruleExistencePreface();

            state._fsp--;


            			current = this_ExistencePreface_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalRequirementDSL.g:908:3: ( (lv_actors_1_0= ruleActors ) )
            // InternalRequirementDSL.g:909:4: (lv_actors_1_0= ruleActors )
            {
            // InternalRequirementDSL.g:909:4: (lv_actors_1_0= ruleActors )
            // InternalRequirementDSL.g:910:5: lv_actors_1_0= ruleActors
            {

            					newCompositeNode(grammarAccess.getExistenceSentenceAccess().getActorsActorsParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_20);
            lv_actors_1_0=ruleActors();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getExistenceSentenceRule());
            					}
            					set(
            						current,
            						"actors",
            						lv_actors_1_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.Actors");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,17,FOLLOW_21); 

            			newLeafNode(otherlv_2, grammarAccess.getExistenceSentenceAccess().getCommaKeyword_2());
            		
            // InternalRequirementDSL.g:931:3: ( (lv_relativeClause_3_0= rulerelativeClause ) )
            // InternalRequirementDSL.g:932:4: (lv_relativeClause_3_0= rulerelativeClause )
            {
            // InternalRequirementDSL.g:932:4: (lv_relativeClause_3_0= rulerelativeClause )
            // InternalRequirementDSL.g:933:5: lv_relativeClause_3_0= rulerelativeClause
            {

            					newCompositeNode(grammarAccess.getExistenceSentenceAccess().getRelativeClauseRelativeClauseParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_20);
            lv_relativeClause_3_0=rulerelativeClause();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getExistenceSentenceRule());
            					}
            					set(
            						current,
            						"relativeClause",
            						lv_relativeClause_3_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.relativeClause");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getExistenceSentenceAccess().getCommaKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExistenceSentence"


    // $ANTLR start "entryRulePropertySentence"
    // InternalRequirementDSL.g:958:1: entryRulePropertySentence returns [EObject current=null] : iv_rulePropertySentence= rulePropertySentence EOF ;
    public final EObject entryRulePropertySentence() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertySentence = null;


        try {
            // InternalRequirementDSL.g:958:57: (iv_rulePropertySentence= rulePropertySentence EOF )
            // InternalRequirementDSL.g:959:2: iv_rulePropertySentence= rulePropertySentence EOF
            {
             newCompositeNode(grammarAccess.getPropertySentenceRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePropertySentence=rulePropertySentence();

            state._fsp--;

             current =iv_rulePropertySentence; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePropertySentence"


    // $ANTLR start "rulePropertySentence"
    // InternalRequirementDSL.g:965:1: rulePropertySentence returns [EObject current=null] : ( ( ( (lv_actors_0_0= ruleActors ) ) ( (lv_property_1_0= ruleProperty ) ) ( (lv_rela_2_0= ruleRelation ) )? ( (lv_modality_3_0= ruleModality ) ) ( (lv_negation_4_0= ruleNegation ) )? ( (lv_auxiliarVerb_5_0= ruleAuxiliaryVerb ) )? ( (lv_predObj_6_0= rulePredOrObject ) ) ( (lv_ending_7_0= ruleSentenceEnding ) )? ) | ( ( (lv_actors_8_0= ruleActors ) ) ( (lv_property_9_0= ruleProperty ) ) ( (lv_rela_10_0= ruleRelation ) )? ( (lv_auxNeg_11_0= ruleAuxNeg ) ) ( ( (lv_predObj_12_0= rulePredOrObject ) ) | ( (lv_constraints_13_0= ruleConstraints ) ) ) ( (lv_ending_14_0= ruleSentenceEnding ) )? ) ) ;
    public final EObject rulePropertySentence() throws RecognitionException {
        EObject current = null;

        EObject lv_actors_0_0 = null;

        EObject lv_property_1_0 = null;

        EObject lv_rela_2_0 = null;

        Enumerator lv_modality_3_0 = null;

        AntlrDatatypeRuleToken lv_negation_4_0 = null;

        AntlrDatatypeRuleToken lv_auxiliarVerb_5_0 = null;

        EObject lv_predObj_6_0 = null;

        EObject lv_ending_7_0 = null;

        EObject lv_actors_8_0 = null;

        EObject lv_property_9_0 = null;

        EObject lv_rela_10_0 = null;

        EObject lv_auxNeg_11_0 = null;

        EObject lv_predObj_12_0 = null;

        EObject lv_constraints_13_0 = null;

        EObject lv_ending_14_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:971:2: ( ( ( ( (lv_actors_0_0= ruleActors ) ) ( (lv_property_1_0= ruleProperty ) ) ( (lv_rela_2_0= ruleRelation ) )? ( (lv_modality_3_0= ruleModality ) ) ( (lv_negation_4_0= ruleNegation ) )? ( (lv_auxiliarVerb_5_0= ruleAuxiliaryVerb ) )? ( (lv_predObj_6_0= rulePredOrObject ) ) ( (lv_ending_7_0= ruleSentenceEnding ) )? ) | ( ( (lv_actors_8_0= ruleActors ) ) ( (lv_property_9_0= ruleProperty ) ) ( (lv_rela_10_0= ruleRelation ) )? ( (lv_auxNeg_11_0= ruleAuxNeg ) ) ( ( (lv_predObj_12_0= rulePredOrObject ) ) | ( (lv_constraints_13_0= ruleConstraints ) ) ) ( (lv_ending_14_0= ruleSentenceEnding ) )? ) ) )
            // InternalRequirementDSL.g:972:2: ( ( ( (lv_actors_0_0= ruleActors ) ) ( (lv_property_1_0= ruleProperty ) ) ( (lv_rela_2_0= ruleRelation ) )? ( (lv_modality_3_0= ruleModality ) ) ( (lv_negation_4_0= ruleNegation ) )? ( (lv_auxiliarVerb_5_0= ruleAuxiliaryVerb ) )? ( (lv_predObj_6_0= rulePredOrObject ) ) ( (lv_ending_7_0= ruleSentenceEnding ) )? ) | ( ( (lv_actors_8_0= ruleActors ) ) ( (lv_property_9_0= ruleProperty ) ) ( (lv_rela_10_0= ruleRelation ) )? ( (lv_auxNeg_11_0= ruleAuxNeg ) ) ( ( (lv_predObj_12_0= rulePredOrObject ) ) | ( (lv_constraints_13_0= ruleConstraints ) ) ) ( (lv_ending_14_0= ruleSentenceEnding ) )? ) )
            {
            // InternalRequirementDSL.g:972:2: ( ( ( (lv_actors_0_0= ruleActors ) ) ( (lv_property_1_0= ruleProperty ) ) ( (lv_rela_2_0= ruleRelation ) )? ( (lv_modality_3_0= ruleModality ) ) ( (lv_negation_4_0= ruleNegation ) )? ( (lv_auxiliarVerb_5_0= ruleAuxiliaryVerb ) )? ( (lv_predObj_6_0= rulePredOrObject ) ) ( (lv_ending_7_0= ruleSentenceEnding ) )? ) | ( ( (lv_actors_8_0= ruleActors ) ) ( (lv_property_9_0= ruleProperty ) ) ( (lv_rela_10_0= ruleRelation ) )? ( (lv_auxNeg_11_0= ruleAuxNeg ) ) ( ( (lv_predObj_12_0= rulePredOrObject ) ) | ( (lv_constraints_13_0= ruleConstraints ) ) ) ( (lv_ending_14_0= ruleSentenceEnding ) )? ) )
            int alt29=2;
            alt29 = dfa29.predict(input);
            switch (alt29) {
                case 1 :
                    // InternalRequirementDSL.g:973:3: ( ( (lv_actors_0_0= ruleActors ) ) ( (lv_property_1_0= ruleProperty ) ) ( (lv_rela_2_0= ruleRelation ) )? ( (lv_modality_3_0= ruleModality ) ) ( (lv_negation_4_0= ruleNegation ) )? ( (lv_auxiliarVerb_5_0= ruleAuxiliaryVerb ) )? ( (lv_predObj_6_0= rulePredOrObject ) ) ( (lv_ending_7_0= ruleSentenceEnding ) )? )
                    {
                    // InternalRequirementDSL.g:973:3: ( ( (lv_actors_0_0= ruleActors ) ) ( (lv_property_1_0= ruleProperty ) ) ( (lv_rela_2_0= ruleRelation ) )? ( (lv_modality_3_0= ruleModality ) ) ( (lv_negation_4_0= ruleNegation ) )? ( (lv_auxiliarVerb_5_0= ruleAuxiliaryVerb ) )? ( (lv_predObj_6_0= rulePredOrObject ) ) ( (lv_ending_7_0= ruleSentenceEnding ) )? )
                    // InternalRequirementDSL.g:974:4: ( (lv_actors_0_0= ruleActors ) ) ( (lv_property_1_0= ruleProperty ) ) ( (lv_rela_2_0= ruleRelation ) )? ( (lv_modality_3_0= ruleModality ) ) ( (lv_negation_4_0= ruleNegation ) )? ( (lv_auxiliarVerb_5_0= ruleAuxiliaryVerb ) )? ( (lv_predObj_6_0= rulePredOrObject ) ) ( (lv_ending_7_0= ruleSentenceEnding ) )?
                    {
                    // InternalRequirementDSL.g:974:4: ( (lv_actors_0_0= ruleActors ) )
                    // InternalRequirementDSL.g:975:5: (lv_actors_0_0= ruleActors )
                    {
                    // InternalRequirementDSL.g:975:5: (lv_actors_0_0= ruleActors )
                    // InternalRequirementDSL.g:976:6: lv_actors_0_0= ruleActors
                    {

                    						newCompositeNode(grammarAccess.getPropertySentenceAccess().getActorsActorsParserRuleCall_0_0_0());
                    					
                    pushFollow(FOLLOW_22);
                    lv_actors_0_0=ruleActors();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                    						}
                    						set(
                    							current,
                    							"actors",
                    							lv_actors_0_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Actors");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:993:4: ( (lv_property_1_0= ruleProperty ) )
                    // InternalRequirementDSL.g:994:5: (lv_property_1_0= ruleProperty )
                    {
                    // InternalRequirementDSL.g:994:5: (lv_property_1_0= ruleProperty )
                    // InternalRequirementDSL.g:995:6: lv_property_1_0= ruleProperty
                    {

                    						newCompositeNode(grammarAccess.getPropertySentenceAccess().getPropertyPropertyParserRuleCall_0_1_0());
                    					
                    pushFollow(FOLLOW_23);
                    lv_property_1_0=ruleProperty();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                    						}
                    						set(
                    							current,
                    							"property",
                    							lv_property_1_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Property");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1012:4: ( (lv_rela_2_0= ruleRelation ) )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( ((LA22_0>=51 && LA22_0<=61)) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // InternalRequirementDSL.g:1013:5: (lv_rela_2_0= ruleRelation )
                            {
                            // InternalRequirementDSL.g:1013:5: (lv_rela_2_0= ruleRelation )
                            // InternalRequirementDSL.g:1014:6: lv_rela_2_0= ruleRelation
                            {

                            						newCompositeNode(grammarAccess.getPropertySentenceAccess().getRelaRelationParserRuleCall_0_2_0());
                            					
                            pushFollow(FOLLOW_14);
                            lv_rela_2_0=ruleRelation();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                            						}
                            						set(
                            							current,
                            							"rela",
                            							lv_rela_2_0,
                            							"de.fraunhofer.isst.stars.RequirementDSL.Relation");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }

                    // InternalRequirementDSL.g:1031:4: ( (lv_modality_3_0= ruleModality ) )
                    // InternalRequirementDSL.g:1032:5: (lv_modality_3_0= ruleModality )
                    {
                    // InternalRequirementDSL.g:1032:5: (lv_modality_3_0= ruleModality )
                    // InternalRequirementDSL.g:1033:6: lv_modality_3_0= ruleModality
                    {

                    						newCompositeNode(grammarAccess.getPropertySentenceAccess().getModalityModalityEnumRuleCall_0_3_0());
                    					
                    pushFollow(FOLLOW_19);
                    lv_modality_3_0=ruleModality();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                    						}
                    						set(
                    							current,
                    							"modality",
                    							lv_modality_3_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Modality");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1050:4: ( (lv_negation_4_0= ruleNegation ) )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==81) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // InternalRequirementDSL.g:1051:5: (lv_negation_4_0= ruleNegation )
                            {
                            // InternalRequirementDSL.g:1051:5: (lv_negation_4_0= ruleNegation )
                            // InternalRequirementDSL.g:1052:6: lv_negation_4_0= ruleNegation
                            {

                            						newCompositeNode(grammarAccess.getPropertySentenceAccess().getNegationNegationParserRuleCall_0_4_0());
                            					
                            pushFollow(FOLLOW_19);
                            lv_negation_4_0=ruleNegation();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                            						}
                            						set(
                            							current,
                            							"negation",
                            							true,
                            							"de.fraunhofer.isst.stars.RequirementDSL.Negation");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }

                    // InternalRequirementDSL.g:1069:4: ( (lv_auxiliarVerb_5_0= ruleAuxiliaryVerb ) )?
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( ((LA24_0>=29 && LA24_0<=36)) ) {
                        alt24=1;
                    }
                    switch (alt24) {
                        case 1 :
                            // InternalRequirementDSL.g:1070:5: (lv_auxiliarVerb_5_0= ruleAuxiliaryVerb )
                            {
                            // InternalRequirementDSL.g:1070:5: (lv_auxiliarVerb_5_0= ruleAuxiliaryVerb )
                            // InternalRequirementDSL.g:1071:6: lv_auxiliarVerb_5_0= ruleAuxiliaryVerb
                            {

                            						newCompositeNode(grammarAccess.getPropertySentenceAccess().getAuxiliarVerbAuxiliaryVerbParserRuleCall_0_5_0());
                            					
                            pushFollow(FOLLOW_19);
                            lv_auxiliarVerb_5_0=ruleAuxiliaryVerb();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                            						}
                            						set(
                            							current,
                            							"auxiliarVerb",
                            							lv_auxiliarVerb_5_0,
                            							"de.fraunhofer.isst.stars.RequirementDSL.AuxiliaryVerb");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }

                    // InternalRequirementDSL.g:1088:4: ( (lv_predObj_6_0= rulePredOrObject ) )
                    // InternalRequirementDSL.g:1089:5: (lv_predObj_6_0= rulePredOrObject )
                    {
                    // InternalRequirementDSL.g:1089:5: (lv_predObj_6_0= rulePredOrObject )
                    // InternalRequirementDSL.g:1090:6: lv_predObj_6_0= rulePredOrObject
                    {

                    						newCompositeNode(grammarAccess.getPropertySentenceAccess().getPredObjPredOrObjectParserRuleCall_0_6_0());
                    					
                    pushFollow(FOLLOW_16);
                    lv_predObj_6_0=rulePredOrObject();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                    						}
                    						set(
                    							current,
                    							"predObj",
                    							lv_predObj_6_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.PredOrObject");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1107:4: ( (lv_ending_7_0= ruleSentenceEnding ) )?
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( (LA25_0==43||(LA25_0>=45 && LA25_0<=66)||LA25_0==96) ) {
                        alt25=1;
                    }
                    switch (alt25) {
                        case 1 :
                            // InternalRequirementDSL.g:1108:5: (lv_ending_7_0= ruleSentenceEnding )
                            {
                            // InternalRequirementDSL.g:1108:5: (lv_ending_7_0= ruleSentenceEnding )
                            // InternalRequirementDSL.g:1109:6: lv_ending_7_0= ruleSentenceEnding
                            {

                            						newCompositeNode(grammarAccess.getPropertySentenceAccess().getEndingSentenceEndingParserRuleCall_0_7_0());
                            					
                            pushFollow(FOLLOW_2);
                            lv_ending_7_0=ruleSentenceEnding();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                            						}
                            						set(
                            							current,
                            							"ending",
                            							lv_ending_7_0,
                            							"de.fraunhofer.isst.stars.RequirementDSL.SentenceEnding");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:1128:3: ( ( (lv_actors_8_0= ruleActors ) ) ( (lv_property_9_0= ruleProperty ) ) ( (lv_rela_10_0= ruleRelation ) )? ( (lv_auxNeg_11_0= ruleAuxNeg ) ) ( ( (lv_predObj_12_0= rulePredOrObject ) ) | ( (lv_constraints_13_0= ruleConstraints ) ) ) ( (lv_ending_14_0= ruleSentenceEnding ) )? )
                    {
                    // InternalRequirementDSL.g:1128:3: ( ( (lv_actors_8_0= ruleActors ) ) ( (lv_property_9_0= ruleProperty ) ) ( (lv_rela_10_0= ruleRelation ) )? ( (lv_auxNeg_11_0= ruleAuxNeg ) ) ( ( (lv_predObj_12_0= rulePredOrObject ) ) | ( (lv_constraints_13_0= ruleConstraints ) ) ) ( (lv_ending_14_0= ruleSentenceEnding ) )? )
                    // InternalRequirementDSL.g:1129:4: ( (lv_actors_8_0= ruleActors ) ) ( (lv_property_9_0= ruleProperty ) ) ( (lv_rela_10_0= ruleRelation ) )? ( (lv_auxNeg_11_0= ruleAuxNeg ) ) ( ( (lv_predObj_12_0= rulePredOrObject ) ) | ( (lv_constraints_13_0= ruleConstraints ) ) ) ( (lv_ending_14_0= ruleSentenceEnding ) )?
                    {
                    // InternalRequirementDSL.g:1129:4: ( (lv_actors_8_0= ruleActors ) )
                    // InternalRequirementDSL.g:1130:5: (lv_actors_8_0= ruleActors )
                    {
                    // InternalRequirementDSL.g:1130:5: (lv_actors_8_0= ruleActors )
                    // InternalRequirementDSL.g:1131:6: lv_actors_8_0= ruleActors
                    {

                    						newCompositeNode(grammarAccess.getPropertySentenceAccess().getActorsActorsParserRuleCall_1_0_0());
                    					
                    pushFollow(FOLLOW_22);
                    lv_actors_8_0=ruleActors();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                    						}
                    						set(
                    							current,
                    							"actors",
                    							lv_actors_8_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Actors");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1148:4: ( (lv_property_9_0= ruleProperty ) )
                    // InternalRequirementDSL.g:1149:5: (lv_property_9_0= ruleProperty )
                    {
                    // InternalRequirementDSL.g:1149:5: (lv_property_9_0= ruleProperty )
                    // InternalRequirementDSL.g:1150:6: lv_property_9_0= ruleProperty
                    {

                    						newCompositeNode(grammarAccess.getPropertySentenceAccess().getPropertyPropertyParserRuleCall_1_1_0());
                    					
                    pushFollow(FOLLOW_24);
                    lv_property_9_0=ruleProperty();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                    						}
                    						set(
                    							current,
                    							"property",
                    							lv_property_9_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Property");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1167:4: ( (lv_rela_10_0= ruleRelation ) )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( ((LA26_0>=51 && LA26_0<=61)) ) {
                        alt26=1;
                    }
                    switch (alt26) {
                        case 1 :
                            // InternalRequirementDSL.g:1168:5: (lv_rela_10_0= ruleRelation )
                            {
                            // InternalRequirementDSL.g:1168:5: (lv_rela_10_0= ruleRelation )
                            // InternalRequirementDSL.g:1169:6: lv_rela_10_0= ruleRelation
                            {

                            						newCompositeNode(grammarAccess.getPropertySentenceAccess().getRelaRelationParserRuleCall_1_2_0());
                            					
                            pushFollow(FOLLOW_17);
                            lv_rela_10_0=ruleRelation();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                            						}
                            						set(
                            							current,
                            							"rela",
                            							lv_rela_10_0,
                            							"de.fraunhofer.isst.stars.RequirementDSL.Relation");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }

                    // InternalRequirementDSL.g:1186:4: ( (lv_auxNeg_11_0= ruleAuxNeg ) )
                    // InternalRequirementDSL.g:1187:5: (lv_auxNeg_11_0= ruleAuxNeg )
                    {
                    // InternalRequirementDSL.g:1187:5: (lv_auxNeg_11_0= ruleAuxNeg )
                    // InternalRequirementDSL.g:1188:6: lv_auxNeg_11_0= ruleAuxNeg
                    {

                    						newCompositeNode(grammarAccess.getPropertySentenceAccess().getAuxNegAuxNegParserRuleCall_1_3_0());
                    					
                    pushFollow(FOLLOW_25);
                    lv_auxNeg_11_0=ruleAuxNeg();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                    						}
                    						set(
                    							current,
                    							"auxNeg",
                    							lv_auxNeg_11_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.AuxNeg");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1205:4: ( ( (lv_predObj_12_0= rulePredOrObject ) ) | ( (lv_constraints_13_0= ruleConstraints ) ) )
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==RULE_STRING||LA27_0==RULE_ID||(LA27_0>=67 && LA27_0<=80)||(LA27_0>=86 && LA27_0<=95)) ) {
                        alt27=1;
                    }
                    else if ( (LA27_0==43||(LA27_0>=45 && LA27_0<=66)||LA27_0==96) ) {
                        alt27=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 27, 0, input);

                        throw nvae;
                    }
                    switch (alt27) {
                        case 1 :
                            // InternalRequirementDSL.g:1206:5: ( (lv_predObj_12_0= rulePredOrObject ) )
                            {
                            // InternalRequirementDSL.g:1206:5: ( (lv_predObj_12_0= rulePredOrObject ) )
                            // InternalRequirementDSL.g:1207:6: (lv_predObj_12_0= rulePredOrObject )
                            {
                            // InternalRequirementDSL.g:1207:6: (lv_predObj_12_0= rulePredOrObject )
                            // InternalRequirementDSL.g:1208:7: lv_predObj_12_0= rulePredOrObject
                            {

                            							newCompositeNode(grammarAccess.getPropertySentenceAccess().getPredObjPredOrObjectParserRuleCall_1_4_0_0());
                            						
                            pushFollow(FOLLOW_16);
                            lv_predObj_12_0=rulePredOrObject();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                            							}
                            							set(
                            								current,
                            								"predObj",
                            								lv_predObj_12_0,
                            								"de.fraunhofer.isst.stars.RequirementDSL.PredOrObject");
                            							afterParserOrEnumRuleCall();
                            						

                            }


                            }


                            }
                            break;
                        case 2 :
                            // InternalRequirementDSL.g:1226:5: ( (lv_constraints_13_0= ruleConstraints ) )
                            {
                            // InternalRequirementDSL.g:1226:5: ( (lv_constraints_13_0= ruleConstraints ) )
                            // InternalRequirementDSL.g:1227:6: (lv_constraints_13_0= ruleConstraints )
                            {
                            // InternalRequirementDSL.g:1227:6: (lv_constraints_13_0= ruleConstraints )
                            // InternalRequirementDSL.g:1228:7: lv_constraints_13_0= ruleConstraints
                            {

                            							newCompositeNode(grammarAccess.getPropertySentenceAccess().getConstraintsConstraintsParserRuleCall_1_4_1_0());
                            						
                            pushFollow(FOLLOW_16);
                            lv_constraints_13_0=ruleConstraints();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                            							}
                            							set(
                            								current,
                            								"constraints",
                            								lv_constraints_13_0,
                            								"de.fraunhofer.isst.stars.RequirementDSL.Constraints");
                            							afterParserOrEnumRuleCall();
                            						

                            }


                            }


                            }
                            break;

                    }

                    // InternalRequirementDSL.g:1246:4: ( (lv_ending_14_0= ruleSentenceEnding ) )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0==43||(LA28_0>=45 && LA28_0<=66)||LA28_0==96) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // InternalRequirementDSL.g:1247:5: (lv_ending_14_0= ruleSentenceEnding )
                            {
                            // InternalRequirementDSL.g:1247:5: (lv_ending_14_0= ruleSentenceEnding )
                            // InternalRequirementDSL.g:1248:6: lv_ending_14_0= ruleSentenceEnding
                            {

                            						newCompositeNode(grammarAccess.getPropertySentenceAccess().getEndingSentenceEndingParserRuleCall_1_5_0());
                            					
                            pushFollow(FOLLOW_2);
                            lv_ending_14_0=ruleSentenceEnding();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                            						}
                            						set(
                            							current,
                            							"ending",
                            							lv_ending_14_0,
                            							"de.fraunhofer.isst.stars.RequirementDSL.SentenceEnding");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePropertySentence"


    // $ANTLR start "entryRuleProperty"
    // InternalRequirementDSL.g:1270:1: entryRuleProperty returns [EObject current=null] : iv_ruleProperty= ruleProperty EOF ;
    public final EObject entryRuleProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProperty = null;


        try {
            // InternalRequirementDSL.g:1270:49: (iv_ruleProperty= ruleProperty EOF )
            // InternalRequirementDSL.g:1271:2: iv_ruleProperty= ruleProperty EOF
            {
             newCompositeNode(grammarAccess.getPropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProperty=ruleProperty();

            state._fsp--;

             current =iv_ruleProperty; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProperty"


    // $ANTLR start "ruleProperty"
    // InternalRequirementDSL.g:1277:1: ruleProperty returns [EObject current=null] : (this_PROPERTY_TERM_0= RULE_PROPERTY_TERM ( ( (lv_property_1_0= ruleWORD ) )+ | ( (lv_property_2_0= RULE_STRING ) ) ) ) ;
    public final EObject ruleProperty() throws RecognitionException {
        EObject current = null;

        Token this_PROPERTY_TERM_0=null;
        Token lv_property_2_0=null;
        AntlrDatatypeRuleToken lv_property_1_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:1283:2: ( (this_PROPERTY_TERM_0= RULE_PROPERTY_TERM ( ( (lv_property_1_0= ruleWORD ) )+ | ( (lv_property_2_0= RULE_STRING ) ) ) ) )
            // InternalRequirementDSL.g:1284:2: (this_PROPERTY_TERM_0= RULE_PROPERTY_TERM ( ( (lv_property_1_0= ruleWORD ) )+ | ( (lv_property_2_0= RULE_STRING ) ) ) )
            {
            // InternalRequirementDSL.g:1284:2: (this_PROPERTY_TERM_0= RULE_PROPERTY_TERM ( ( (lv_property_1_0= ruleWORD ) )+ | ( (lv_property_2_0= RULE_STRING ) ) ) )
            // InternalRequirementDSL.g:1285:3: this_PROPERTY_TERM_0= RULE_PROPERTY_TERM ( ( (lv_property_1_0= ruleWORD ) )+ | ( (lv_property_2_0= RULE_STRING ) ) )
            {
            this_PROPERTY_TERM_0=(Token)match(input,RULE_PROPERTY_TERM,FOLLOW_26); 

            			newLeafNode(this_PROPERTY_TERM_0, grammarAccess.getPropertyAccess().getPROPERTY_TERMTerminalRuleCall_0());
            		
            // InternalRequirementDSL.g:1289:3: ( ( (lv_property_1_0= ruleWORD ) )+ | ( (lv_property_2_0= RULE_STRING ) ) )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==RULE_ID) ) {
                alt31=1;
            }
            else if ( (LA31_0==RULE_STRING) ) {
                alt31=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // InternalRequirementDSL.g:1290:4: ( (lv_property_1_0= ruleWORD ) )+
                    {
                    // InternalRequirementDSL.g:1290:4: ( (lv_property_1_0= ruleWORD ) )+
                    int cnt30=0;
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==RULE_ID) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:1291:5: (lv_property_1_0= ruleWORD )
                    	    {
                    	    // InternalRequirementDSL.g:1291:5: (lv_property_1_0= ruleWORD )
                    	    // InternalRequirementDSL.g:1292:6: lv_property_1_0= ruleWORD
                    	    {

                    	    						newCompositeNode(grammarAccess.getPropertyAccess().getPropertyWORDParserRuleCall_1_0_0());
                    	    					
                    	    pushFollow(FOLLOW_27);
                    	    lv_property_1_0=ruleWORD();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getPropertyRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"property",
                    	    							lv_property_1_0,
                    	    							"de.fraunhofer.isst.stars.RequirementDSL.WORD");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt30 >= 1 ) break loop30;
                                EarlyExitException eee =
                                    new EarlyExitException(30, input);
                                throw eee;
                        }
                        cnt30++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:1310:4: ( (lv_property_2_0= RULE_STRING ) )
                    {
                    // InternalRequirementDSL.g:1310:4: ( (lv_property_2_0= RULE_STRING ) )
                    // InternalRequirementDSL.g:1311:5: (lv_property_2_0= RULE_STRING )
                    {
                    // InternalRequirementDSL.g:1311:5: (lv_property_2_0= RULE_STRING )
                    // InternalRequirementDSL.g:1312:6: lv_property_2_0= RULE_STRING
                    {
                    lv_property_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						newLeafNode(lv_property_2_0, grammarAccess.getPropertyAccess().getPropertySTRINGTerminalRuleCall_1_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getPropertyRule());
                    						}
                    						addWithLastConsumed(
                    							current,
                    							"property",
                    							lv_property_2_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProperty"


    // $ANTLR start "entryRuleSentenceBegin"
    // InternalRequirementDSL.g:1333:1: entryRuleSentenceBegin returns [EObject current=null] : iv_ruleSentenceBegin= ruleSentenceBegin EOF ;
    public final EObject entryRuleSentenceBegin() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSentenceBegin = null;


        try {
            // InternalRequirementDSL.g:1333:54: (iv_ruleSentenceBegin= ruleSentenceBegin EOF )
            // InternalRequirementDSL.g:1334:2: iv_ruleSentenceBegin= ruleSentenceBegin EOF
            {
             newCompositeNode(grammarAccess.getSentenceBeginRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSentenceBegin=ruleSentenceBegin();

            state._fsp--;

             current =iv_ruleSentenceBegin; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSentenceBegin"


    // $ANTLR start "ruleSentenceBegin"
    // InternalRequirementDSL.g:1340:1: ruleSentenceBegin returns [EObject current=null] : ( ( (lv_rela_0_0= ruleRelation ) ) otherlv_1= ',' ) ;
    public final EObject ruleSentenceBegin() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_rela_0_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:1346:2: ( ( ( (lv_rela_0_0= ruleRelation ) ) otherlv_1= ',' ) )
            // InternalRequirementDSL.g:1347:2: ( ( (lv_rela_0_0= ruleRelation ) ) otherlv_1= ',' )
            {
            // InternalRequirementDSL.g:1347:2: ( ( (lv_rela_0_0= ruleRelation ) ) otherlv_1= ',' )
            // InternalRequirementDSL.g:1348:3: ( (lv_rela_0_0= ruleRelation ) ) otherlv_1= ','
            {
            // InternalRequirementDSL.g:1348:3: ( (lv_rela_0_0= ruleRelation ) )
            // InternalRequirementDSL.g:1349:4: (lv_rela_0_0= ruleRelation )
            {
            // InternalRequirementDSL.g:1349:4: (lv_rela_0_0= ruleRelation )
            // InternalRequirementDSL.g:1350:5: lv_rela_0_0= ruleRelation
            {

            					newCompositeNode(grammarAccess.getSentenceBeginAccess().getRelaRelationParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_20);
            lv_rela_0_0=ruleRelation();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSentenceBeginRule());
            					}
            					set(
            						current,
            						"rela",
            						lv_rela_0_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.Relation");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getSentenceBeginAccess().getCommaKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSentenceBegin"


    // $ANTLR start "entryRuleSentenceEnding"
    // InternalRequirementDSL.g:1375:1: entryRuleSentenceEnding returns [EObject current=null] : iv_ruleSentenceEnding= ruleSentenceEnding EOF ;
    public final EObject entryRuleSentenceEnding() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSentenceEnding = null;


        try {
            // InternalRequirementDSL.g:1375:55: (iv_ruleSentenceEnding= ruleSentenceEnding EOF )
            // InternalRequirementDSL.g:1376:2: iv_ruleSentenceEnding= ruleSentenceEnding EOF
            {
             newCompositeNode(grammarAccess.getSentenceEndingRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSentenceEnding=ruleSentenceEnding();

            state._fsp--;

             current =iv_ruleSentenceEnding; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSentenceEnding"


    // $ANTLR start "ruleSentenceEnding"
    // InternalRequirementDSL.g:1382:1: ruleSentenceEnding returns [EObject current=null] : ( ( (lv_const_0_0= ruleConstraints ) )+ | ( ( (lv_const_1_0= ruleConstraints ) )* ( (lv_rela_2_0= ruleRelation ) ) ) | ( ( (lv_rela_3_0= ruleRelation ) ) ( (lv_const_4_0= ruleConstraints ) )+ ) ) ;
    public final EObject ruleSentenceEnding() throws RecognitionException {
        EObject current = null;

        EObject lv_const_0_0 = null;

        EObject lv_const_1_0 = null;

        EObject lv_rela_2_0 = null;

        EObject lv_rela_3_0 = null;

        EObject lv_const_4_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:1388:2: ( ( ( (lv_const_0_0= ruleConstraints ) )+ | ( ( (lv_const_1_0= ruleConstraints ) )* ( (lv_rela_2_0= ruleRelation ) ) ) | ( ( (lv_rela_3_0= ruleRelation ) ) ( (lv_const_4_0= ruleConstraints ) )+ ) ) )
            // InternalRequirementDSL.g:1389:2: ( ( (lv_const_0_0= ruleConstraints ) )+ | ( ( (lv_const_1_0= ruleConstraints ) )* ( (lv_rela_2_0= ruleRelation ) ) ) | ( ( (lv_rela_3_0= ruleRelation ) ) ( (lv_const_4_0= ruleConstraints ) )+ ) )
            {
            // InternalRequirementDSL.g:1389:2: ( ( (lv_const_0_0= ruleConstraints ) )+ | ( ( (lv_const_1_0= ruleConstraints ) )* ( (lv_rela_2_0= ruleRelation ) ) ) | ( ( (lv_rela_3_0= ruleRelation ) ) ( (lv_const_4_0= ruleConstraints ) )+ ) )
            int alt35=3;
            alt35 = dfa35.predict(input);
            switch (alt35) {
                case 1 :
                    // InternalRequirementDSL.g:1390:3: ( (lv_const_0_0= ruleConstraints ) )+
                    {
                    // InternalRequirementDSL.g:1390:3: ( (lv_const_0_0= ruleConstraints ) )+
                    int cnt32=0;
                    loop32:
                    do {
                        int alt32=2;
                        int LA32_0 = input.LA(1);

                        if ( (LA32_0==43||(LA32_0>=45 && LA32_0<=66)||LA32_0==96) ) {
                            alt32=1;
                        }


                        switch (alt32) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:1391:4: (lv_const_0_0= ruleConstraints )
                    	    {
                    	    // InternalRequirementDSL.g:1391:4: (lv_const_0_0= ruleConstraints )
                    	    // InternalRequirementDSL.g:1392:5: lv_const_0_0= ruleConstraints
                    	    {

                    	    					newCompositeNode(grammarAccess.getSentenceEndingAccess().getConstConstraintsParserRuleCall_0_0());
                    	    				
                    	    pushFollow(FOLLOW_16);
                    	    lv_const_0_0=ruleConstraints();

                    	    state._fsp--;


                    	    					if (current==null) {
                    	    						current = createModelElementForParent(grammarAccess.getSentenceEndingRule());
                    	    					}
                    	    					add(
                    	    						current,
                    	    						"const",
                    	    						lv_const_0_0,
                    	    						"de.fraunhofer.isst.stars.RequirementDSL.Constraints");
                    	    					afterParserOrEnumRuleCall();
                    	    				

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt32 >= 1 ) break loop32;
                                EarlyExitException eee =
                                    new EarlyExitException(32, input);
                                throw eee;
                        }
                        cnt32++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:1410:3: ( ( (lv_const_1_0= ruleConstraints ) )* ( (lv_rela_2_0= ruleRelation ) ) )
                    {
                    // InternalRequirementDSL.g:1410:3: ( ( (lv_const_1_0= ruleConstraints ) )* ( (lv_rela_2_0= ruleRelation ) ) )
                    // InternalRequirementDSL.g:1411:4: ( (lv_const_1_0= ruleConstraints ) )* ( (lv_rela_2_0= ruleRelation ) )
                    {
                    // InternalRequirementDSL.g:1411:4: ( (lv_const_1_0= ruleConstraints ) )*
                    loop33:
                    do {
                        int alt33=2;
                        alt33 = dfa33.predict(input);
                        switch (alt33) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:1412:5: (lv_const_1_0= ruleConstraints )
                    	    {
                    	    // InternalRequirementDSL.g:1412:5: (lv_const_1_0= ruleConstraints )
                    	    // InternalRequirementDSL.g:1413:6: lv_const_1_0= ruleConstraints
                    	    {

                    	    						newCompositeNode(grammarAccess.getSentenceEndingAccess().getConstConstraintsParserRuleCall_1_0_0());
                    	    					
                    	    pushFollow(FOLLOW_28);
                    	    lv_const_1_0=ruleConstraints();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getSentenceEndingRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"const",
                    	    							lv_const_1_0,
                    	    							"de.fraunhofer.isst.stars.RequirementDSL.Constraints");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop33;
                        }
                    } while (true);

                    // InternalRequirementDSL.g:1430:4: ( (lv_rela_2_0= ruleRelation ) )
                    // InternalRequirementDSL.g:1431:5: (lv_rela_2_0= ruleRelation )
                    {
                    // InternalRequirementDSL.g:1431:5: (lv_rela_2_0= ruleRelation )
                    // InternalRequirementDSL.g:1432:6: lv_rela_2_0= ruleRelation
                    {

                    						newCompositeNode(grammarAccess.getSentenceEndingAccess().getRelaRelationParserRuleCall_1_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_rela_2_0=ruleRelation();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getSentenceEndingRule());
                    						}
                    						set(
                    							current,
                    							"rela",
                    							lv_rela_2_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Relation");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:1451:3: ( ( (lv_rela_3_0= ruleRelation ) ) ( (lv_const_4_0= ruleConstraints ) )+ )
                    {
                    // InternalRequirementDSL.g:1451:3: ( ( (lv_rela_3_0= ruleRelation ) ) ( (lv_const_4_0= ruleConstraints ) )+ )
                    // InternalRequirementDSL.g:1452:4: ( (lv_rela_3_0= ruleRelation ) ) ( (lv_const_4_0= ruleConstraints ) )+
                    {
                    // InternalRequirementDSL.g:1452:4: ( (lv_rela_3_0= ruleRelation ) )
                    // InternalRequirementDSL.g:1453:5: (lv_rela_3_0= ruleRelation )
                    {
                    // InternalRequirementDSL.g:1453:5: (lv_rela_3_0= ruleRelation )
                    // InternalRequirementDSL.g:1454:6: lv_rela_3_0= ruleRelation
                    {

                    						newCompositeNode(grammarAccess.getSentenceEndingAccess().getRelaRelationParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_28);
                    lv_rela_3_0=ruleRelation();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getSentenceEndingRule());
                    						}
                    						set(
                    							current,
                    							"rela",
                    							lv_rela_3_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Relation");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1471:4: ( (lv_const_4_0= ruleConstraints ) )+
                    int cnt34=0;
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( (LA34_0==43||(LA34_0>=45 && LA34_0<=66)||LA34_0==96) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:1472:5: (lv_const_4_0= ruleConstraints )
                    	    {
                    	    // InternalRequirementDSL.g:1472:5: (lv_const_4_0= ruleConstraints )
                    	    // InternalRequirementDSL.g:1473:6: lv_const_4_0= ruleConstraints
                    	    {

                    	    						newCompositeNode(grammarAccess.getSentenceEndingAccess().getConstConstraintsParserRuleCall_2_1_0());
                    	    					
                    	    pushFollow(FOLLOW_16);
                    	    lv_const_4_0=ruleConstraints();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getSentenceEndingRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"const",
                    	    							lv_const_4_0,
                    	    							"de.fraunhofer.isst.stars.RequirementDSL.Constraints");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt34 >= 1 ) break loop34;
                                EarlyExitException eee =
                                    new EarlyExitException(34, input);
                                throw eee;
                        }
                        cnt34++;
                    } while (true);


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSentenceEnding"


    // $ANTLR start "entryRulerelativeClause"
    // InternalRequirementDSL.g:1495:1: entryRulerelativeClause returns [EObject current=null] : iv_rulerelativeClause= rulerelativeClause EOF ;
    public final EObject entryRulerelativeClause() throws RecognitionException {
        EObject current = null;

        EObject iv_rulerelativeClause = null;


        try {
            // InternalRequirementDSL.g:1495:55: (iv_rulerelativeClause= rulerelativeClause EOF )
            // InternalRequirementDSL.g:1496:2: iv_rulerelativeClause= rulerelativeClause EOF
            {
             newCompositeNode(grammarAccess.getRelativeClauseRule()); 
            pushFollow(FOLLOW_1);
            iv_rulerelativeClause=rulerelativeClause();

            state._fsp--;

             current =iv_rulerelativeClause; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulerelativeClause"


    // $ANTLR start "rulerelativeClause"
    // InternalRequirementDSL.g:1502:1: rulerelativeClause returns [EObject current=null] : ( ( (lv_sentence_0_0= rulerelativeSentence ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_condClauses_2_0= ruleConditionalClause ) ) )* ) ;
    public final EObject rulerelativeClause() throws RecognitionException {
        EObject current = null;

        EObject lv_sentence_0_0 = null;

        AntlrDatatypeRuleToken lv_conjunction_1_0 = null;

        EObject lv_condClauses_2_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:1508:2: ( ( ( (lv_sentence_0_0= rulerelativeSentence ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_condClauses_2_0= ruleConditionalClause ) ) )* ) )
            // InternalRequirementDSL.g:1509:2: ( ( (lv_sentence_0_0= rulerelativeSentence ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_condClauses_2_0= ruleConditionalClause ) ) )* )
            {
            // InternalRequirementDSL.g:1509:2: ( ( (lv_sentence_0_0= rulerelativeSentence ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_condClauses_2_0= ruleConditionalClause ) ) )* )
            // InternalRequirementDSL.g:1510:3: ( (lv_sentence_0_0= rulerelativeSentence ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_condClauses_2_0= ruleConditionalClause ) ) )*
            {
            // InternalRequirementDSL.g:1510:3: ( (lv_sentence_0_0= rulerelativeSentence ) )
            // InternalRequirementDSL.g:1511:4: (lv_sentence_0_0= rulerelativeSentence )
            {
            // InternalRequirementDSL.g:1511:4: (lv_sentence_0_0= rulerelativeSentence )
            // InternalRequirementDSL.g:1512:5: lv_sentence_0_0= rulerelativeSentence
            {

            					newCompositeNode(grammarAccess.getRelativeClauseAccess().getSentenceRelativeSentenceParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_12);
            lv_sentence_0_0=rulerelativeSentence();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRelativeClauseRule());
            					}
            					set(
            						current,
            						"sentence",
            						lv_sentence_0_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.relativeSentence");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:1529:3: ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_condClauses_2_0= ruleConditionalClause ) ) )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( ((LA36_0>=37 && LA36_0<=38)) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // InternalRequirementDSL.g:1530:4: ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_condClauses_2_0= ruleConditionalClause ) )
            	    {
            	    // InternalRequirementDSL.g:1530:4: ( (lv_conjunction_1_0= ruleConjunction ) )
            	    // InternalRequirementDSL.g:1531:5: (lv_conjunction_1_0= ruleConjunction )
            	    {
            	    // InternalRequirementDSL.g:1531:5: (lv_conjunction_1_0= ruleConjunction )
            	    // InternalRequirementDSL.g:1532:6: lv_conjunction_1_0= ruleConjunction
            	    {

            	    						newCompositeNode(grammarAccess.getRelativeClauseAccess().getConjunctionConjunctionParserRuleCall_1_0_0());
            	    					
            	    pushFollow(FOLLOW_11);
            	    lv_conjunction_1_0=ruleConjunction();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getRelativeClauseRule());
            	    						}
            	    						add(
            	    							current,
            	    							"conjunction",
            	    							lv_conjunction_1_0,
            	    							"de.fraunhofer.isst.stars.RequirementDSL.Conjunction");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    // InternalRequirementDSL.g:1549:4: ( (lv_condClauses_2_0= ruleConditionalClause ) )
            	    // InternalRequirementDSL.g:1550:5: (lv_condClauses_2_0= ruleConditionalClause )
            	    {
            	    // InternalRequirementDSL.g:1550:5: (lv_condClauses_2_0= ruleConditionalClause )
            	    // InternalRequirementDSL.g:1551:6: lv_condClauses_2_0= ruleConditionalClause
            	    {

            	    						newCompositeNode(grammarAccess.getRelativeClauseAccess().getCondClausesConditionalClauseParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_12);
            	    lv_condClauses_2_0=ruleConditionalClause();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getRelativeClauseRule());
            	    						}
            	    						add(
            	    							current,
            	    							"condClauses",
            	    							lv_condClauses_2_0,
            	    							"de.fraunhofer.isst.stars.RequirementDSL.ConditionalClause");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulerelativeClause"


    // $ANTLR start "entryRulerelativeSentence"
    // InternalRequirementDSL.g:1573:1: entryRulerelativeSentence returns [EObject current=null] : iv_rulerelativeSentence= rulerelativeSentence EOF ;
    public final EObject entryRulerelativeSentence() throws RecognitionException {
        EObject current = null;

        EObject iv_rulerelativeSentence = null;


        try {
            // InternalRequirementDSL.g:1573:57: (iv_rulerelativeSentence= rulerelativeSentence EOF )
            // InternalRequirementDSL.g:1574:2: iv_rulerelativeSentence= rulerelativeSentence EOF
            {
             newCompositeNode(grammarAccess.getRelativeSentenceRule()); 
            pushFollow(FOLLOW_1);
            iv_rulerelativeSentence=rulerelativeSentence();

            state._fsp--;

             current =iv_rulerelativeSentence; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulerelativeSentence"


    // $ANTLR start "rulerelativeSentence"
    // InternalRequirementDSL.g:1580:1: rulerelativeSentence returns [EObject current=null] : ( ( ( (lv_pronoun_0_0= ruleRelativePronounsSubject ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_predicate_3_0= rulePredicate ) ) ( (lv_constraints_4_0= ruleConstraints ) )* ) | ( ( (lv_pronoun_5_0= ruleRelativePronounsSubject ) ) ( ( (lv_auxiliar_6_0= ruleWORD ) ) ( (lv_negation_7_0= ruleNegation ) ) )? ( (lv_predicate_8_0= rulePredicate ) ) ( (lv_constraints_9_0= ruleConstraints ) )* ) | ( ( (lv_pronoun_10_0= ruleRelativePronounsObject ) ) ( ( (lv_clause_11_0= ruleModalitySentence ) ) | ( (lv_clause_12_0= rulePredicateSentence ) ) ) ) ) ;
    public final EObject rulerelativeSentence() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_pronoun_0_0 = null;

        Enumerator lv_modelity_1_0 = null;

        AntlrDatatypeRuleToken lv_negation_2_0 = null;

        EObject lv_predicate_3_0 = null;

        EObject lv_constraints_4_0 = null;

        AntlrDatatypeRuleToken lv_pronoun_5_0 = null;

        AntlrDatatypeRuleToken lv_auxiliar_6_0 = null;

        AntlrDatatypeRuleToken lv_negation_7_0 = null;

        EObject lv_predicate_8_0 = null;

        EObject lv_constraints_9_0 = null;

        AntlrDatatypeRuleToken lv_pronoun_10_0 = null;

        EObject lv_clause_11_0 = null;

        EObject lv_clause_12_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:1586:2: ( ( ( ( (lv_pronoun_0_0= ruleRelativePronounsSubject ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_predicate_3_0= rulePredicate ) ) ( (lv_constraints_4_0= ruleConstraints ) )* ) | ( ( (lv_pronoun_5_0= ruleRelativePronounsSubject ) ) ( ( (lv_auxiliar_6_0= ruleWORD ) ) ( (lv_negation_7_0= ruleNegation ) ) )? ( (lv_predicate_8_0= rulePredicate ) ) ( (lv_constraints_9_0= ruleConstraints ) )* ) | ( ( (lv_pronoun_10_0= ruleRelativePronounsObject ) ) ( ( (lv_clause_11_0= ruleModalitySentence ) ) | ( (lv_clause_12_0= rulePredicateSentence ) ) ) ) ) )
            // InternalRequirementDSL.g:1587:2: ( ( ( (lv_pronoun_0_0= ruleRelativePronounsSubject ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_predicate_3_0= rulePredicate ) ) ( (lv_constraints_4_0= ruleConstraints ) )* ) | ( ( (lv_pronoun_5_0= ruleRelativePronounsSubject ) ) ( ( (lv_auxiliar_6_0= ruleWORD ) ) ( (lv_negation_7_0= ruleNegation ) ) )? ( (lv_predicate_8_0= rulePredicate ) ) ( (lv_constraints_9_0= ruleConstraints ) )* ) | ( ( (lv_pronoun_10_0= ruleRelativePronounsObject ) ) ( ( (lv_clause_11_0= ruleModalitySentence ) ) | ( (lv_clause_12_0= rulePredicateSentence ) ) ) ) )
            {
            // InternalRequirementDSL.g:1587:2: ( ( ( (lv_pronoun_0_0= ruleRelativePronounsSubject ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_predicate_3_0= rulePredicate ) ) ( (lv_constraints_4_0= ruleConstraints ) )* ) | ( ( (lv_pronoun_5_0= ruleRelativePronounsSubject ) ) ( ( (lv_auxiliar_6_0= ruleWORD ) ) ( (lv_negation_7_0= ruleNegation ) ) )? ( (lv_predicate_8_0= rulePredicate ) ) ( (lv_constraints_9_0= ruleConstraints ) )* ) | ( ( (lv_pronoun_10_0= ruleRelativePronounsObject ) ) ( ( (lv_clause_11_0= ruleModalitySentence ) ) | ( (lv_clause_12_0= rulePredicateSentence ) ) ) ) )
            int alt42=3;
            switch ( input.LA(1) ) {
            case 97:
                {
                int LA42_1 = input.LA(2);

                if ( ((LA42_1>=145 && LA42_1<=151)) ) {
                    alt42=1;
                }
                else if ( (LA42_1==RULE_STRING||LA42_1==RULE_ID) ) {
                    alt42=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 42, 1, input);

                    throw nvae;
                }
                }
                break;
            case 98:
                {
                int LA42_2 = input.LA(2);

                if ( (LA42_2==RULE_STRING||LA42_2==RULE_ID) ) {
                    alt42=2;
                }
                else if ( ((LA42_2>=145 && LA42_2<=151)) ) {
                    alt42=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 42, 2, input);

                    throw nvae;
                }
                }
                break;
            case 92:
                {
                int LA42_3 = input.LA(2);

                if ( ((LA42_3>=145 && LA42_3<=151)) ) {
                    alt42=1;
                }
                else if ( (LA42_3==RULE_STRING||LA42_3==RULE_ID) ) {
                    alt42=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 42, 3, input);

                    throw nvae;
                }
                }
                break;
            case 99:
            case 100:
                {
                alt42=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;
            }

            switch (alt42) {
                case 1 :
                    // InternalRequirementDSL.g:1588:3: ( ( (lv_pronoun_0_0= ruleRelativePronounsSubject ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_predicate_3_0= rulePredicate ) ) ( (lv_constraints_4_0= ruleConstraints ) )* )
                    {
                    // InternalRequirementDSL.g:1588:3: ( ( (lv_pronoun_0_0= ruleRelativePronounsSubject ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_predicate_3_0= rulePredicate ) ) ( (lv_constraints_4_0= ruleConstraints ) )* )
                    // InternalRequirementDSL.g:1589:4: ( (lv_pronoun_0_0= ruleRelativePronounsSubject ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_predicate_3_0= rulePredicate ) ) ( (lv_constraints_4_0= ruleConstraints ) )*
                    {
                    // InternalRequirementDSL.g:1589:4: ( (lv_pronoun_0_0= ruleRelativePronounsSubject ) )
                    // InternalRequirementDSL.g:1590:5: (lv_pronoun_0_0= ruleRelativePronounsSubject )
                    {
                    // InternalRequirementDSL.g:1590:5: (lv_pronoun_0_0= ruleRelativePronounsSubject )
                    // InternalRequirementDSL.g:1591:6: lv_pronoun_0_0= ruleRelativePronounsSubject
                    {

                    						newCompositeNode(grammarAccess.getRelativeSentenceAccess().getPronounRelativePronounsSubjectParserRuleCall_0_0_0());
                    					
                    pushFollow(FOLLOW_14);
                    lv_pronoun_0_0=ruleRelativePronounsSubject();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getRelativeSentenceRule());
                    						}
                    						set(
                    							current,
                    							"pronoun",
                    							lv_pronoun_0_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.RelativePronounsSubject");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1608:4: ( (lv_modelity_1_0= ruleModality ) )
                    // InternalRequirementDSL.g:1609:5: (lv_modelity_1_0= ruleModality )
                    {
                    // InternalRequirementDSL.g:1609:5: (lv_modelity_1_0= ruleModality )
                    // InternalRequirementDSL.g:1610:6: lv_modelity_1_0= ruleModality
                    {

                    						newCompositeNode(grammarAccess.getRelativeSentenceAccess().getModelityModalityEnumRuleCall_0_1_0());
                    					
                    pushFollow(FOLLOW_15);
                    lv_modelity_1_0=ruleModality();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getRelativeSentenceRule());
                    						}
                    						set(
                    							current,
                    							"modelity",
                    							lv_modelity_1_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Modality");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1627:4: ( (lv_negation_2_0= ruleNegation ) )?
                    int alt37=2;
                    int LA37_0 = input.LA(1);

                    if ( (LA37_0==81) ) {
                        alt37=1;
                    }
                    switch (alt37) {
                        case 1 :
                            // InternalRequirementDSL.g:1628:5: (lv_negation_2_0= ruleNegation )
                            {
                            // InternalRequirementDSL.g:1628:5: (lv_negation_2_0= ruleNegation )
                            // InternalRequirementDSL.g:1629:6: lv_negation_2_0= ruleNegation
                            {

                            						newCompositeNode(grammarAccess.getRelativeSentenceAccess().getNegationNegationParserRuleCall_0_2_0());
                            					
                            pushFollow(FOLLOW_15);
                            lv_negation_2_0=ruleNegation();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getRelativeSentenceRule());
                            						}
                            						set(
                            							current,
                            							"negation",
                            							true,
                            							"de.fraunhofer.isst.stars.RequirementDSL.Negation");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }

                    // InternalRequirementDSL.g:1646:4: ( (lv_predicate_3_0= rulePredicate ) )
                    // InternalRequirementDSL.g:1647:5: (lv_predicate_3_0= rulePredicate )
                    {
                    // InternalRequirementDSL.g:1647:5: (lv_predicate_3_0= rulePredicate )
                    // InternalRequirementDSL.g:1648:6: lv_predicate_3_0= rulePredicate
                    {

                    						newCompositeNode(grammarAccess.getRelativeSentenceAccess().getPredicatePredicateParserRuleCall_0_3_0());
                    					
                    pushFollow(FOLLOW_16);
                    lv_predicate_3_0=rulePredicate();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getRelativeSentenceRule());
                    						}
                    						set(
                    							current,
                    							"predicate",
                    							lv_predicate_3_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Predicate");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1665:4: ( (lv_constraints_4_0= ruleConstraints ) )*
                    loop38:
                    do {
                        int alt38=2;
                        int LA38_0 = input.LA(1);

                        if ( (LA38_0==43||(LA38_0>=45 && LA38_0<=66)||LA38_0==96) ) {
                            alt38=1;
                        }


                        switch (alt38) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:1666:5: (lv_constraints_4_0= ruleConstraints )
                    	    {
                    	    // InternalRequirementDSL.g:1666:5: (lv_constraints_4_0= ruleConstraints )
                    	    // InternalRequirementDSL.g:1667:6: lv_constraints_4_0= ruleConstraints
                    	    {

                    	    						newCompositeNode(grammarAccess.getRelativeSentenceAccess().getConstraintsConstraintsParserRuleCall_0_4_0());
                    	    					
                    	    pushFollow(FOLLOW_16);
                    	    lv_constraints_4_0=ruleConstraints();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getRelativeSentenceRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"constraints",
                    	    							lv_constraints_4_0,
                    	    							"de.fraunhofer.isst.stars.RequirementDSL.Constraints");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop38;
                        }
                    } while (true);


                    }


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:1686:3: ( ( (lv_pronoun_5_0= ruleRelativePronounsSubject ) ) ( ( (lv_auxiliar_6_0= ruleWORD ) ) ( (lv_negation_7_0= ruleNegation ) ) )? ( (lv_predicate_8_0= rulePredicate ) ) ( (lv_constraints_9_0= ruleConstraints ) )* )
                    {
                    // InternalRequirementDSL.g:1686:3: ( ( (lv_pronoun_5_0= ruleRelativePronounsSubject ) ) ( ( (lv_auxiliar_6_0= ruleWORD ) ) ( (lv_negation_7_0= ruleNegation ) ) )? ( (lv_predicate_8_0= rulePredicate ) ) ( (lv_constraints_9_0= ruleConstraints ) )* )
                    // InternalRequirementDSL.g:1687:4: ( (lv_pronoun_5_0= ruleRelativePronounsSubject ) ) ( ( (lv_auxiliar_6_0= ruleWORD ) ) ( (lv_negation_7_0= ruleNegation ) ) )? ( (lv_predicate_8_0= rulePredicate ) ) ( (lv_constraints_9_0= ruleConstraints ) )*
                    {
                    // InternalRequirementDSL.g:1687:4: ( (lv_pronoun_5_0= ruleRelativePronounsSubject ) )
                    // InternalRequirementDSL.g:1688:5: (lv_pronoun_5_0= ruleRelativePronounsSubject )
                    {
                    // InternalRequirementDSL.g:1688:5: (lv_pronoun_5_0= ruleRelativePronounsSubject )
                    // InternalRequirementDSL.g:1689:6: lv_pronoun_5_0= ruleRelativePronounsSubject
                    {

                    						newCompositeNode(grammarAccess.getRelativeSentenceAccess().getPronounRelativePronounsSubjectParserRuleCall_1_0_0());
                    					
                    pushFollow(FOLLOW_15);
                    lv_pronoun_5_0=ruleRelativePronounsSubject();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getRelativeSentenceRule());
                    						}
                    						set(
                    							current,
                    							"pronoun",
                    							lv_pronoun_5_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.RelativePronounsSubject");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1706:4: ( ( (lv_auxiliar_6_0= ruleWORD ) ) ( (lv_negation_7_0= ruleNegation ) ) )?
                    int alt39=2;
                    alt39 = dfa39.predict(input);
                    switch (alt39) {
                        case 1 :
                            // InternalRequirementDSL.g:1707:5: ( (lv_auxiliar_6_0= ruleWORD ) ) ( (lv_negation_7_0= ruleNegation ) )
                            {
                            // InternalRequirementDSL.g:1707:5: ( (lv_auxiliar_6_0= ruleWORD ) )
                            // InternalRequirementDSL.g:1708:6: (lv_auxiliar_6_0= ruleWORD )
                            {
                            // InternalRequirementDSL.g:1708:6: (lv_auxiliar_6_0= ruleWORD )
                            // InternalRequirementDSL.g:1709:7: lv_auxiliar_6_0= ruleWORD
                            {

                            							newCompositeNode(grammarAccess.getRelativeSentenceAccess().getAuxiliarWORDParserRuleCall_1_1_0_0());
                            						
                            pushFollow(FOLLOW_29);
                            lv_auxiliar_6_0=ruleWORD();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getRelativeSentenceRule());
                            							}
                            							set(
                            								current,
                            								"auxiliar",
                            								lv_auxiliar_6_0,
                            								"de.fraunhofer.isst.stars.RequirementDSL.WORD");
                            							afterParserOrEnumRuleCall();
                            						

                            }


                            }

                            // InternalRequirementDSL.g:1726:5: ( (lv_negation_7_0= ruleNegation ) )
                            // InternalRequirementDSL.g:1727:6: (lv_negation_7_0= ruleNegation )
                            {
                            // InternalRequirementDSL.g:1727:6: (lv_negation_7_0= ruleNegation )
                            // InternalRequirementDSL.g:1728:7: lv_negation_7_0= ruleNegation
                            {

                            							newCompositeNode(grammarAccess.getRelativeSentenceAccess().getNegationNegationParserRuleCall_1_1_1_0());
                            						
                            pushFollow(FOLLOW_15);
                            lv_negation_7_0=ruleNegation();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getRelativeSentenceRule());
                            							}
                            							set(
                            								current,
                            								"negation",
                            								true,
                            								"de.fraunhofer.isst.stars.RequirementDSL.Negation");
                            							afterParserOrEnumRuleCall();
                            						

                            }


                            }


                            }
                            break;

                    }

                    // InternalRequirementDSL.g:1746:4: ( (lv_predicate_8_0= rulePredicate ) )
                    // InternalRequirementDSL.g:1747:5: (lv_predicate_8_0= rulePredicate )
                    {
                    // InternalRequirementDSL.g:1747:5: (lv_predicate_8_0= rulePredicate )
                    // InternalRequirementDSL.g:1748:6: lv_predicate_8_0= rulePredicate
                    {

                    						newCompositeNode(grammarAccess.getRelativeSentenceAccess().getPredicatePredicateParserRuleCall_1_2_0());
                    					
                    pushFollow(FOLLOW_16);
                    lv_predicate_8_0=rulePredicate();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getRelativeSentenceRule());
                    						}
                    						set(
                    							current,
                    							"predicate",
                    							lv_predicate_8_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Predicate");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1765:4: ( (lv_constraints_9_0= ruleConstraints ) )*
                    loop40:
                    do {
                        int alt40=2;
                        int LA40_0 = input.LA(1);

                        if ( (LA40_0==43||(LA40_0>=45 && LA40_0<=66)||LA40_0==96) ) {
                            alt40=1;
                        }


                        switch (alt40) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:1766:5: (lv_constraints_9_0= ruleConstraints )
                    	    {
                    	    // InternalRequirementDSL.g:1766:5: (lv_constraints_9_0= ruleConstraints )
                    	    // InternalRequirementDSL.g:1767:6: lv_constraints_9_0= ruleConstraints
                    	    {

                    	    						newCompositeNode(grammarAccess.getRelativeSentenceAccess().getConstraintsConstraintsParserRuleCall_1_3_0());
                    	    					
                    	    pushFollow(FOLLOW_16);
                    	    lv_constraints_9_0=ruleConstraints();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getRelativeSentenceRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"constraints",
                    	    							lv_constraints_9_0,
                    	    							"de.fraunhofer.isst.stars.RequirementDSL.Constraints");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop40;
                        }
                    } while (true);


                    }


                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:1786:3: ( ( (lv_pronoun_10_0= ruleRelativePronounsObject ) ) ( ( (lv_clause_11_0= ruleModalitySentence ) ) | ( (lv_clause_12_0= rulePredicateSentence ) ) ) )
                    {
                    // InternalRequirementDSL.g:1786:3: ( ( (lv_pronoun_10_0= ruleRelativePronounsObject ) ) ( ( (lv_clause_11_0= ruleModalitySentence ) ) | ( (lv_clause_12_0= rulePredicateSentence ) ) ) )
                    // InternalRequirementDSL.g:1787:4: ( (lv_pronoun_10_0= ruleRelativePronounsObject ) ) ( ( (lv_clause_11_0= ruleModalitySentence ) ) | ( (lv_clause_12_0= rulePredicateSentence ) ) )
                    {
                    // InternalRequirementDSL.g:1787:4: ( (lv_pronoun_10_0= ruleRelativePronounsObject ) )
                    // InternalRequirementDSL.g:1788:5: (lv_pronoun_10_0= ruleRelativePronounsObject )
                    {
                    // InternalRequirementDSL.g:1788:5: (lv_pronoun_10_0= ruleRelativePronounsObject )
                    // InternalRequirementDSL.g:1789:6: lv_pronoun_10_0= ruleRelativePronounsObject
                    {

                    						newCompositeNode(grammarAccess.getRelativeSentenceAccess().getPronounRelativePronounsObjectParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_13);
                    lv_pronoun_10_0=ruleRelativePronounsObject();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getRelativeSentenceRule());
                    						}
                    						set(
                    							current,
                    							"pronoun",
                    							lv_pronoun_10_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.RelativePronounsObject");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1806:4: ( ( (lv_clause_11_0= ruleModalitySentence ) ) | ( (lv_clause_12_0= rulePredicateSentence ) ) )
                    int alt41=2;
                    alt41 = dfa41.predict(input);
                    switch (alt41) {
                        case 1 :
                            // InternalRequirementDSL.g:1807:5: ( (lv_clause_11_0= ruleModalitySentence ) )
                            {
                            // InternalRequirementDSL.g:1807:5: ( (lv_clause_11_0= ruleModalitySentence ) )
                            // InternalRequirementDSL.g:1808:6: (lv_clause_11_0= ruleModalitySentence )
                            {
                            // InternalRequirementDSL.g:1808:6: (lv_clause_11_0= ruleModalitySentence )
                            // InternalRequirementDSL.g:1809:7: lv_clause_11_0= ruleModalitySentence
                            {

                            							newCompositeNode(grammarAccess.getRelativeSentenceAccess().getClauseModalitySentenceParserRuleCall_2_1_0_0());
                            						
                            pushFollow(FOLLOW_2);
                            lv_clause_11_0=ruleModalitySentence();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getRelativeSentenceRule());
                            							}
                            							set(
                            								current,
                            								"clause",
                            								lv_clause_11_0,
                            								"de.fraunhofer.isst.stars.RequirementDSL.ModalitySentence");
                            							afterParserOrEnumRuleCall();
                            						

                            }


                            }


                            }
                            break;
                        case 2 :
                            // InternalRequirementDSL.g:1827:5: ( (lv_clause_12_0= rulePredicateSentence ) )
                            {
                            // InternalRequirementDSL.g:1827:5: ( (lv_clause_12_0= rulePredicateSentence ) )
                            // InternalRequirementDSL.g:1828:6: (lv_clause_12_0= rulePredicateSentence )
                            {
                            // InternalRequirementDSL.g:1828:6: (lv_clause_12_0= rulePredicateSentence )
                            // InternalRequirementDSL.g:1829:7: lv_clause_12_0= rulePredicateSentence
                            {

                            							newCompositeNode(grammarAccess.getRelativeSentenceAccess().getClausePredicateSentenceParserRuleCall_2_1_1_0());
                            						
                            pushFollow(FOLLOW_2);
                            lv_clause_12_0=rulePredicateSentence();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getRelativeSentenceRule());
                            							}
                            							set(
                            								current,
                            								"clause",
                            								lv_clause_12_0,
                            								"de.fraunhofer.isst.stars.RequirementDSL.PredicateSentence");
                            							afterParserOrEnumRuleCall();
                            						

                            }


                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulerelativeSentence"


    // $ANTLR start "entryRuleActors"
    // InternalRequirementDSL.g:1852:1: entryRuleActors returns [EObject current=null] : iv_ruleActors= ruleActors EOF ;
    public final EObject entryRuleActors() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleActors = null;


        try {
            // InternalRequirementDSL.g:1852:47: (iv_ruleActors= ruleActors EOF )
            // InternalRequirementDSL.g:1853:2: iv_ruleActors= ruleActors EOF
            {
             newCompositeNode(grammarAccess.getActorsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleActors=ruleActors();

            state._fsp--;

             current =iv_ruleActors; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleActors"


    // $ANTLR start "ruleActors"
    // InternalRequirementDSL.g:1859:1: ruleActors returns [EObject current=null] : ( ( (lv_actors_0_0= ruleActor ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_actors_2_0= ruleActor ) ) )* ) ;
    public final EObject ruleActors() throws RecognitionException {
        EObject current = null;

        EObject lv_actors_0_0 = null;

        AntlrDatatypeRuleToken lv_conjunction_1_0 = null;

        EObject lv_actors_2_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:1865:2: ( ( ( (lv_actors_0_0= ruleActor ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_actors_2_0= ruleActor ) ) )* ) )
            // InternalRequirementDSL.g:1866:2: ( ( (lv_actors_0_0= ruleActor ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_actors_2_0= ruleActor ) ) )* )
            {
            // InternalRequirementDSL.g:1866:2: ( ( (lv_actors_0_0= ruleActor ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_actors_2_0= ruleActor ) ) )* )
            // InternalRequirementDSL.g:1867:3: ( (lv_actors_0_0= ruleActor ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_actors_2_0= ruleActor ) ) )*
            {
            // InternalRequirementDSL.g:1867:3: ( (lv_actors_0_0= ruleActor ) )
            // InternalRequirementDSL.g:1868:4: (lv_actors_0_0= ruleActor )
            {
            // InternalRequirementDSL.g:1868:4: (lv_actors_0_0= ruleActor )
            // InternalRequirementDSL.g:1869:5: lv_actors_0_0= ruleActor
            {

            					newCompositeNode(grammarAccess.getActorsAccess().getActorsActorParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_12);
            lv_actors_0_0=ruleActor();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getActorsRule());
            					}
            					add(
            						current,
            						"actors",
            						lv_actors_0_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.Actor");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:1886:3: ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_actors_2_0= ruleActor ) ) )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( ((LA43_0>=37 && LA43_0<=38)) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // InternalRequirementDSL.g:1887:4: ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_actors_2_0= ruleActor ) )
            	    {
            	    // InternalRequirementDSL.g:1887:4: ( (lv_conjunction_1_0= ruleConjunction ) )
            	    // InternalRequirementDSL.g:1888:5: (lv_conjunction_1_0= ruleConjunction )
            	    {
            	    // InternalRequirementDSL.g:1888:5: (lv_conjunction_1_0= ruleConjunction )
            	    // InternalRequirementDSL.g:1889:6: lv_conjunction_1_0= ruleConjunction
            	    {

            	    						newCompositeNode(grammarAccess.getActorsAccess().getConjunctionConjunctionParserRuleCall_1_0_0());
            	    					
            	    pushFollow(FOLLOW_13);
            	    lv_conjunction_1_0=ruleConjunction();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getActorsRule());
            	    						}
            	    						add(
            	    							current,
            	    							"conjunction",
            	    							lv_conjunction_1_0,
            	    							"de.fraunhofer.isst.stars.RequirementDSL.Conjunction");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    // InternalRequirementDSL.g:1906:4: ( (lv_actors_2_0= ruleActor ) )
            	    // InternalRequirementDSL.g:1907:5: (lv_actors_2_0= ruleActor )
            	    {
            	    // InternalRequirementDSL.g:1907:5: (lv_actors_2_0= ruleActor )
            	    // InternalRequirementDSL.g:1908:6: lv_actors_2_0= ruleActor
            	    {

            	    						newCompositeNode(grammarAccess.getActorsAccess().getActorsActorParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_12);
            	    lv_actors_2_0=ruleActor();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getActorsRule());
            	    						}
            	    						add(
            	    							current,
            	    							"actors",
            	    							lv_actors_2_0,
            	    							"de.fraunhofer.isst.stars.RequirementDSL.Actor");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleActors"


    // $ANTLR start "entryRuleActor"
    // InternalRequirementDSL.g:1930:1: entryRuleActor returns [EObject current=null] : iv_ruleActor= ruleActor EOF ;
    public final EObject entryRuleActor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleActor = null;


        try {
            // InternalRequirementDSL.g:1930:46: (iv_ruleActor= ruleActor EOF )
            // InternalRequirementDSL.g:1931:2: iv_ruleActor= ruleActor EOF
            {
             newCompositeNode(grammarAccess.getActorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleActor=ruleActor();

            state._fsp--;

             current =iv_ruleActor; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleActor"


    // $ANTLR start "ruleActor"
    // InternalRequirementDSL.g:1937:1: ruleActor returns [EObject current=null] : ( (this_PreNominative_0= rulePreNominative )? ( ( (lv_actor_1_0= ruleWORD ) ) | ( (lv_actor_2_0= RULE_STRING ) ) ) ) ;
    public final EObject ruleActor() throws RecognitionException {
        EObject current = null;

        Token lv_actor_2_0=null;
        EObject this_PreNominative_0 = null;

        AntlrDatatypeRuleToken lv_actor_1_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:1943:2: ( ( (this_PreNominative_0= rulePreNominative )? ( ( (lv_actor_1_0= ruleWORD ) ) | ( (lv_actor_2_0= RULE_STRING ) ) ) ) )
            // InternalRequirementDSL.g:1944:2: ( (this_PreNominative_0= rulePreNominative )? ( ( (lv_actor_1_0= ruleWORD ) ) | ( (lv_actor_2_0= RULE_STRING ) ) ) )
            {
            // InternalRequirementDSL.g:1944:2: ( (this_PreNominative_0= rulePreNominative )? ( ( (lv_actor_1_0= ruleWORD ) ) | ( (lv_actor_2_0= RULE_STRING ) ) ) )
            // InternalRequirementDSL.g:1945:3: (this_PreNominative_0= rulePreNominative )? ( ( (lv_actor_1_0= ruleWORD ) ) | ( (lv_actor_2_0= RULE_STRING ) ) )
            {
            // InternalRequirementDSL.g:1945:3: (this_PreNominative_0= rulePreNominative )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( ((LA44_0>=67 && LA44_0<=80)||(LA44_0>=86 && LA44_0<=95)) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // InternalRequirementDSL.g:1946:4: this_PreNominative_0= rulePreNominative
                    {

                    				newCompositeNode(grammarAccess.getActorAccess().getPreNominativeParserRuleCall_0());
                    			
                    pushFollow(FOLLOW_26);
                    this_PreNominative_0=rulePreNominative();

                    state._fsp--;


                    				current = this_PreNominative_0;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;

            }

            // InternalRequirementDSL.g:1955:3: ( ( (lv_actor_1_0= ruleWORD ) ) | ( (lv_actor_2_0= RULE_STRING ) ) )
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==RULE_ID) ) {
                alt45=1;
            }
            else if ( (LA45_0==RULE_STRING) ) {
                alt45=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;
            }
            switch (alt45) {
                case 1 :
                    // InternalRequirementDSL.g:1956:4: ( (lv_actor_1_0= ruleWORD ) )
                    {
                    // InternalRequirementDSL.g:1956:4: ( (lv_actor_1_0= ruleWORD ) )
                    // InternalRequirementDSL.g:1957:5: (lv_actor_1_0= ruleWORD )
                    {
                    // InternalRequirementDSL.g:1957:5: (lv_actor_1_0= ruleWORD )
                    // InternalRequirementDSL.g:1958:6: lv_actor_1_0= ruleWORD
                    {

                    						newCompositeNode(grammarAccess.getActorAccess().getActorWORDParserRuleCall_1_0_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_actor_1_0=ruleWORD();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getActorRule());
                    						}
                    						set(
                    							current,
                    							"actor",
                    							lv_actor_1_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.WORD");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:1976:4: ( (lv_actor_2_0= RULE_STRING ) )
                    {
                    // InternalRequirementDSL.g:1976:4: ( (lv_actor_2_0= RULE_STRING ) )
                    // InternalRequirementDSL.g:1977:5: (lv_actor_2_0= RULE_STRING )
                    {
                    // InternalRequirementDSL.g:1977:5: (lv_actor_2_0= RULE_STRING )
                    // InternalRequirementDSL.g:1978:6: lv_actor_2_0= RULE_STRING
                    {
                    lv_actor_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						newLeafNode(lv_actor_2_0, grammarAccess.getActorAccess().getActorSTRINGTerminalRuleCall_1_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getActorRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"actor",
                    							lv_actor_2_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleActor"


    // $ANTLR start "entryRulePreds"
    // InternalRequirementDSL.g:1999:1: entryRulePreds returns [EObject current=null] : iv_rulePreds= rulePreds EOF ;
    public final EObject entryRulePreds() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePreds = null;


        try {
            // InternalRequirementDSL.g:1999:46: (iv_rulePreds= rulePreds EOF )
            // InternalRequirementDSL.g:2000:2: iv_rulePreds= rulePreds EOF
            {
             newCompositeNode(grammarAccess.getPredsRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePreds=rulePreds();

            state._fsp--;

             current =iv_rulePreds; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePreds"


    // $ANTLR start "rulePreds"
    // InternalRequirementDSL.g:2006:1: rulePreds returns [EObject current=null] : ( ( (lv_predicate_0_0= rulePredicate ) ) | ( (lv_predObj_1_0= rulePredicateObject ) ) ) ;
    public final EObject rulePreds() throws RecognitionException {
        EObject current = null;

        EObject lv_predicate_0_0 = null;

        EObject lv_predObj_1_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2012:2: ( ( ( (lv_predicate_0_0= rulePredicate ) ) | ( (lv_predObj_1_0= rulePredicateObject ) ) ) )
            // InternalRequirementDSL.g:2013:2: ( ( (lv_predicate_0_0= rulePredicate ) ) | ( (lv_predObj_1_0= rulePredicateObject ) ) )
            {
            // InternalRequirementDSL.g:2013:2: ( ( (lv_predicate_0_0= rulePredicate ) ) | ( (lv_predObj_1_0= rulePredicateObject ) ) )
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==RULE_STRING||LA46_0==RULE_ID) ) {
                alt46=1;
            }
            else if ( ((LA46_0>=67 && LA46_0<=80)||(LA46_0>=86 && LA46_0<=95)) ) {
                alt46=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;
            }
            switch (alt46) {
                case 1 :
                    // InternalRequirementDSL.g:2014:3: ( (lv_predicate_0_0= rulePredicate ) )
                    {
                    // InternalRequirementDSL.g:2014:3: ( (lv_predicate_0_0= rulePredicate ) )
                    // InternalRequirementDSL.g:2015:4: (lv_predicate_0_0= rulePredicate )
                    {
                    // InternalRequirementDSL.g:2015:4: (lv_predicate_0_0= rulePredicate )
                    // InternalRequirementDSL.g:2016:5: lv_predicate_0_0= rulePredicate
                    {

                    					newCompositeNode(grammarAccess.getPredsAccess().getPredicatePredicateParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_predicate_0_0=rulePredicate();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getPredsRule());
                    					}
                    					set(
                    						current,
                    						"predicate",
                    						lv_predicate_0_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.Predicate");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:2034:3: ( (lv_predObj_1_0= rulePredicateObject ) )
                    {
                    // InternalRequirementDSL.g:2034:3: ( (lv_predObj_1_0= rulePredicateObject ) )
                    // InternalRequirementDSL.g:2035:4: (lv_predObj_1_0= rulePredicateObject )
                    {
                    // InternalRequirementDSL.g:2035:4: (lv_predObj_1_0= rulePredicateObject )
                    // InternalRequirementDSL.g:2036:5: lv_predObj_1_0= rulePredicateObject
                    {

                    					newCompositeNode(grammarAccess.getPredsAccess().getPredObjPredicateObjectParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_predObj_1_0=rulePredicateObject();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getPredsRule());
                    					}
                    					set(
                    						current,
                    						"predObj",
                    						lv_predObj_1_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.PredicateObject");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePreds"


    // $ANTLR start "entryRulePredicate"
    // InternalRequirementDSL.g:2057:1: entryRulePredicate returns [EObject current=null] : iv_rulePredicate= rulePredicate EOF ;
    public final EObject entryRulePredicate() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePredicate = null;


        try {
            // InternalRequirementDSL.g:2057:50: (iv_rulePredicate= rulePredicate EOF )
            // InternalRequirementDSL.g:2058:2: iv_rulePredicate= rulePredicate EOF
            {
             newCompositeNode(grammarAccess.getPredicateRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePredicate=rulePredicate();

            state._fsp--;

             current =iv_rulePredicate; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePredicate"


    // $ANTLR start "rulePredicate"
    // InternalRequirementDSL.g:2064:1: rulePredicate returns [EObject current=null] : ( ( (lv_predicates_0_0= ruleWORD ) )+ | ( (lv_predicates_1_0= RULE_STRING ) ) | ( ( (lv_predicates_2_0= ruleWORD ) )+ ( (lv_object_3_0= rulePredicateObject ) ) ) ) ;
    public final EObject rulePredicate() throws RecognitionException {
        EObject current = null;

        Token lv_predicates_1_0=null;
        AntlrDatatypeRuleToken lv_predicates_0_0 = null;

        AntlrDatatypeRuleToken lv_predicates_2_0 = null;

        EObject lv_object_3_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2070:2: ( ( ( (lv_predicates_0_0= ruleWORD ) )+ | ( (lv_predicates_1_0= RULE_STRING ) ) | ( ( (lv_predicates_2_0= ruleWORD ) )+ ( (lv_object_3_0= rulePredicateObject ) ) ) ) )
            // InternalRequirementDSL.g:2071:2: ( ( (lv_predicates_0_0= ruleWORD ) )+ | ( (lv_predicates_1_0= RULE_STRING ) ) | ( ( (lv_predicates_2_0= ruleWORD ) )+ ( (lv_object_3_0= rulePredicateObject ) ) ) )
            {
            // InternalRequirementDSL.g:2071:2: ( ( (lv_predicates_0_0= ruleWORD ) )+ | ( (lv_predicates_1_0= RULE_STRING ) ) | ( ( (lv_predicates_2_0= ruleWORD ) )+ ( (lv_object_3_0= rulePredicateObject ) ) ) )
            int alt49=3;
            alt49 = dfa49.predict(input);
            switch (alt49) {
                case 1 :
                    // InternalRequirementDSL.g:2072:3: ( (lv_predicates_0_0= ruleWORD ) )+
                    {
                    // InternalRequirementDSL.g:2072:3: ( (lv_predicates_0_0= ruleWORD ) )+
                    int cnt47=0;
                    loop47:
                    do {
                        int alt47=2;
                        int LA47_0 = input.LA(1);

                        if ( (LA47_0==RULE_ID) ) {
                            alt47=1;
                        }


                        switch (alt47) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:2073:4: (lv_predicates_0_0= ruleWORD )
                    	    {
                    	    // InternalRequirementDSL.g:2073:4: (lv_predicates_0_0= ruleWORD )
                    	    // InternalRequirementDSL.g:2074:5: lv_predicates_0_0= ruleWORD
                    	    {

                    	    					newCompositeNode(grammarAccess.getPredicateAccess().getPredicatesWORDParserRuleCall_0_0());
                    	    				
                    	    pushFollow(FOLLOW_27);
                    	    lv_predicates_0_0=ruleWORD();

                    	    state._fsp--;


                    	    					if (current==null) {
                    	    						current = createModelElementForParent(grammarAccess.getPredicateRule());
                    	    					}
                    	    					add(
                    	    						current,
                    	    						"predicates",
                    	    						lv_predicates_0_0,
                    	    						"de.fraunhofer.isst.stars.RequirementDSL.WORD");
                    	    					afterParserOrEnumRuleCall();
                    	    				

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt47 >= 1 ) break loop47;
                                EarlyExitException eee =
                                    new EarlyExitException(47, input);
                                throw eee;
                        }
                        cnt47++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:2092:3: ( (lv_predicates_1_0= RULE_STRING ) )
                    {
                    // InternalRequirementDSL.g:2092:3: ( (lv_predicates_1_0= RULE_STRING ) )
                    // InternalRequirementDSL.g:2093:4: (lv_predicates_1_0= RULE_STRING )
                    {
                    // InternalRequirementDSL.g:2093:4: (lv_predicates_1_0= RULE_STRING )
                    // InternalRequirementDSL.g:2094:5: lv_predicates_1_0= RULE_STRING
                    {
                    lv_predicates_1_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    					newLeafNode(lv_predicates_1_0, grammarAccess.getPredicateAccess().getPredicatesSTRINGTerminalRuleCall_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getPredicateRule());
                    					}
                    					addWithLastConsumed(
                    						current,
                    						"predicates",
                    						lv_predicates_1_0,
                    						"org.eclipse.xtext.common.Terminals.STRING");
                    				

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:2111:3: ( ( (lv_predicates_2_0= ruleWORD ) )+ ( (lv_object_3_0= rulePredicateObject ) ) )
                    {
                    // InternalRequirementDSL.g:2111:3: ( ( (lv_predicates_2_0= ruleWORD ) )+ ( (lv_object_3_0= rulePredicateObject ) ) )
                    // InternalRequirementDSL.g:2112:4: ( (lv_predicates_2_0= ruleWORD ) )+ ( (lv_object_3_0= rulePredicateObject ) )
                    {
                    // InternalRequirementDSL.g:2112:4: ( (lv_predicates_2_0= ruleWORD ) )+
                    int cnt48=0;
                    loop48:
                    do {
                        int alt48=2;
                        int LA48_0 = input.LA(1);

                        if ( (LA48_0==RULE_ID) ) {
                            alt48=1;
                        }


                        switch (alt48) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:2113:5: (lv_predicates_2_0= ruleWORD )
                    	    {
                    	    // InternalRequirementDSL.g:2113:5: (lv_predicates_2_0= ruleWORD )
                    	    // InternalRequirementDSL.g:2114:6: lv_predicates_2_0= ruleWORD
                    	    {

                    	    						newCompositeNode(grammarAccess.getPredicateAccess().getPredicatesWORDParserRuleCall_2_0_0());
                    	    					
                    	    pushFollow(FOLLOW_19);
                    	    lv_predicates_2_0=ruleWORD();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getPredicateRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"predicates",
                    	    							lv_predicates_2_0,
                    	    							"de.fraunhofer.isst.stars.RequirementDSL.WORD");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt48 >= 1 ) break loop48;
                                EarlyExitException eee =
                                    new EarlyExitException(48, input);
                                throw eee;
                        }
                        cnt48++;
                    } while (true);

                    // InternalRequirementDSL.g:2131:4: ( (lv_object_3_0= rulePredicateObject ) )
                    // InternalRequirementDSL.g:2132:5: (lv_object_3_0= rulePredicateObject )
                    {
                    // InternalRequirementDSL.g:2132:5: (lv_object_3_0= rulePredicateObject )
                    // InternalRequirementDSL.g:2133:6: lv_object_3_0= rulePredicateObject
                    {

                    						newCompositeNode(grammarAccess.getPredicateAccess().getObjectPredicateObjectParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_object_3_0=rulePredicateObject();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPredicateRule());
                    						}
                    						set(
                    							current,
                    							"object",
                    							lv_object_3_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.PredicateObject");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePredicate"


    // $ANTLR start "entryRulePredicateObject"
    // InternalRequirementDSL.g:2155:1: entryRulePredicateObject returns [EObject current=null] : iv_rulePredicateObject= rulePredicateObject EOF ;
    public final EObject entryRulePredicateObject() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePredicateObject = null;


        try {
            // InternalRequirementDSL.g:2155:56: (iv_rulePredicateObject= rulePredicateObject EOF )
            // InternalRequirementDSL.g:2156:2: iv_rulePredicateObject= rulePredicateObject EOF
            {
             newCompositeNode(grammarAccess.getPredicateObjectRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePredicateObject=rulePredicateObject();

            state._fsp--;

             current =iv_rulePredicateObject; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePredicateObject"


    // $ANTLR start "rulePredicateObject"
    // InternalRequirementDSL.g:2162:1: rulePredicateObject returns [EObject current=null] : ( ( (lv_article_0_0= rulePreNominative ) ) ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) ) ) ;
    public final EObject rulePredicateObject() throws RecognitionException {
        EObject current = null;

        Token lv_object_2_0=null;
        EObject lv_article_0_0 = null;

        AntlrDatatypeRuleToken lv_object_1_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2168:2: ( ( ( (lv_article_0_0= rulePreNominative ) ) ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) ) ) )
            // InternalRequirementDSL.g:2169:2: ( ( (lv_article_0_0= rulePreNominative ) ) ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) ) )
            {
            // InternalRequirementDSL.g:2169:2: ( ( (lv_article_0_0= rulePreNominative ) ) ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) ) )
            // InternalRequirementDSL.g:2170:3: ( (lv_article_0_0= rulePreNominative ) ) ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) )
            {
            // InternalRequirementDSL.g:2170:3: ( (lv_article_0_0= rulePreNominative ) )
            // InternalRequirementDSL.g:2171:4: (lv_article_0_0= rulePreNominative )
            {
            // InternalRequirementDSL.g:2171:4: (lv_article_0_0= rulePreNominative )
            // InternalRequirementDSL.g:2172:5: lv_article_0_0= rulePreNominative
            {

            					newCompositeNode(grammarAccess.getPredicateObjectAccess().getArticlePreNominativeParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_26);
            lv_article_0_0=rulePreNominative();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPredicateObjectRule());
            					}
            					set(
            						current,
            						"article",
            						lv_article_0_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.PreNominative");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:2189:3: ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) )
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==RULE_ID) ) {
                alt51=1;
            }
            else if ( (LA51_0==RULE_STRING) ) {
                alt51=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                throw nvae;
            }
            switch (alt51) {
                case 1 :
                    // InternalRequirementDSL.g:2190:4: ( (lv_object_1_0= ruleWORD ) )+
                    {
                    // InternalRequirementDSL.g:2190:4: ( (lv_object_1_0= ruleWORD ) )+
                    int cnt50=0;
                    loop50:
                    do {
                        int alt50=2;
                        int LA50_0 = input.LA(1);

                        if ( (LA50_0==RULE_ID) ) {
                            alt50=1;
                        }


                        switch (alt50) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:2191:5: (lv_object_1_0= ruleWORD )
                    	    {
                    	    // InternalRequirementDSL.g:2191:5: (lv_object_1_0= ruleWORD )
                    	    // InternalRequirementDSL.g:2192:6: lv_object_1_0= ruleWORD
                    	    {

                    	    						newCompositeNode(grammarAccess.getPredicateObjectAccess().getObjectWORDParserRuleCall_1_0_0());
                    	    					
                    	    pushFollow(FOLLOW_27);
                    	    lv_object_1_0=ruleWORD();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getPredicateObjectRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"object",
                    	    							lv_object_1_0,
                    	    							"de.fraunhofer.isst.stars.RequirementDSL.WORD");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt50 >= 1 ) break loop50;
                                EarlyExitException eee =
                                    new EarlyExitException(50, input);
                                throw eee;
                        }
                        cnt50++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:2210:4: ( (lv_object_2_0= RULE_STRING ) )
                    {
                    // InternalRequirementDSL.g:2210:4: ( (lv_object_2_0= RULE_STRING ) )
                    // InternalRequirementDSL.g:2211:5: (lv_object_2_0= RULE_STRING )
                    {
                    // InternalRequirementDSL.g:2211:5: (lv_object_2_0= RULE_STRING )
                    // InternalRequirementDSL.g:2212:6: lv_object_2_0= RULE_STRING
                    {
                    lv_object_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						newLeafNode(lv_object_2_0, grammarAccess.getPredicateObjectAccess().getObjectSTRINGTerminalRuleCall_1_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getPredicateObjectRule());
                    						}
                    						addWithLastConsumed(
                    							current,
                    							"object",
                    							lv_object_2_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePredicateObject"


    // $ANTLR start "entryRuleObject"
    // InternalRequirementDSL.g:2233:1: entryRuleObject returns [EObject current=null] : iv_ruleObject= ruleObject EOF ;
    public final EObject entryRuleObject() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleObject = null;


        try {
            // InternalRequirementDSL.g:2233:47: (iv_ruleObject= ruleObject EOF )
            // InternalRequirementDSL.g:2234:2: iv_ruleObject= ruleObject EOF
            {
             newCompositeNode(grammarAccess.getObjectRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleObject=ruleObject();

            state._fsp--;

             current =iv_ruleObject; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleObject"


    // $ANTLR start "ruleObject"
    // InternalRequirementDSL.g:2240:1: ruleObject returns [EObject current=null] : ( ( (lv_article_0_0= rulePreNominative ) )? ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) ) ) ;
    public final EObject ruleObject() throws RecognitionException {
        EObject current = null;

        Token lv_object_2_0=null;
        EObject lv_article_0_0 = null;

        AntlrDatatypeRuleToken lv_object_1_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2246:2: ( ( ( (lv_article_0_0= rulePreNominative ) )? ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) ) ) )
            // InternalRequirementDSL.g:2247:2: ( ( (lv_article_0_0= rulePreNominative ) )? ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) ) )
            {
            // InternalRequirementDSL.g:2247:2: ( ( (lv_article_0_0= rulePreNominative ) )? ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) ) )
            // InternalRequirementDSL.g:2248:3: ( (lv_article_0_0= rulePreNominative ) )? ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) )
            {
            // InternalRequirementDSL.g:2248:3: ( (lv_article_0_0= rulePreNominative ) )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( ((LA52_0>=67 && LA52_0<=80)||(LA52_0>=86 && LA52_0<=95)) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // InternalRequirementDSL.g:2249:4: (lv_article_0_0= rulePreNominative )
                    {
                    // InternalRequirementDSL.g:2249:4: (lv_article_0_0= rulePreNominative )
                    // InternalRequirementDSL.g:2250:5: lv_article_0_0= rulePreNominative
                    {

                    					newCompositeNode(grammarAccess.getObjectAccess().getArticlePreNominativeParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_26);
                    lv_article_0_0=rulePreNominative();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getObjectRule());
                    					}
                    					set(
                    						current,
                    						"article",
                    						lv_article_0_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.PreNominative");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalRequirementDSL.g:2267:3: ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) )
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==RULE_ID) ) {
                alt54=1;
            }
            else if ( (LA54_0==RULE_STRING) ) {
                alt54=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;
            }
            switch (alt54) {
                case 1 :
                    // InternalRequirementDSL.g:2268:4: ( (lv_object_1_0= ruleWORD ) )+
                    {
                    // InternalRequirementDSL.g:2268:4: ( (lv_object_1_0= ruleWORD ) )+
                    int cnt53=0;
                    loop53:
                    do {
                        int alt53=2;
                        int LA53_0 = input.LA(1);

                        if ( (LA53_0==RULE_ID) ) {
                            alt53=1;
                        }


                        switch (alt53) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:2269:5: (lv_object_1_0= ruleWORD )
                    	    {
                    	    // InternalRequirementDSL.g:2269:5: (lv_object_1_0= ruleWORD )
                    	    // InternalRequirementDSL.g:2270:6: lv_object_1_0= ruleWORD
                    	    {

                    	    						newCompositeNode(grammarAccess.getObjectAccess().getObjectWORDParserRuleCall_1_0_0());
                    	    					
                    	    pushFollow(FOLLOW_27);
                    	    lv_object_1_0=ruleWORD();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getObjectRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"object",
                    	    							lv_object_1_0,
                    	    							"de.fraunhofer.isst.stars.RequirementDSL.WORD");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt53 >= 1 ) break loop53;
                                EarlyExitException eee =
                                    new EarlyExitException(53, input);
                                throw eee;
                        }
                        cnt53++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:2288:4: ( (lv_object_2_0= RULE_STRING ) )
                    {
                    // InternalRequirementDSL.g:2288:4: ( (lv_object_2_0= RULE_STRING ) )
                    // InternalRequirementDSL.g:2289:5: (lv_object_2_0= RULE_STRING )
                    {
                    // InternalRequirementDSL.g:2289:5: (lv_object_2_0= RULE_STRING )
                    // InternalRequirementDSL.g:2290:6: lv_object_2_0= RULE_STRING
                    {
                    lv_object_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						newLeafNode(lv_object_2_0, grammarAccess.getObjectAccess().getObjectSTRINGTerminalRuleCall_1_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getObjectRule());
                    						}
                    						addWithLastConsumed(
                    							current,
                    							"object",
                    							lv_object_2_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleObject"


    // $ANTLR start "entryRulePredOrObject"
    // InternalRequirementDSL.g:2311:1: entryRulePredOrObject returns [EObject current=null] : iv_rulePredOrObject= rulePredOrObject EOF ;
    public final EObject entryRulePredOrObject() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePredOrObject = null;


        try {
            // InternalRequirementDSL.g:2311:53: (iv_rulePredOrObject= rulePredOrObject EOF )
            // InternalRequirementDSL.g:2312:2: iv_rulePredOrObject= rulePredOrObject EOF
            {
             newCompositeNode(grammarAccess.getPredOrObjectRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePredOrObject=rulePredOrObject();

            state._fsp--;

             current =iv_rulePredOrObject; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePredOrObject"


    // $ANTLR start "rulePredOrObject"
    // InternalRequirementDSL.g:2318:1: rulePredOrObject returns [EObject current=null] : ( ( (lv_predicate_0_0= rulePredicate ) ) | ( (lv_predObj_1_0= rulePredicateObject ) ) ) ;
    public final EObject rulePredOrObject() throws RecognitionException {
        EObject current = null;

        EObject lv_predicate_0_0 = null;

        EObject lv_predObj_1_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2324:2: ( ( ( (lv_predicate_0_0= rulePredicate ) ) | ( (lv_predObj_1_0= rulePredicateObject ) ) ) )
            // InternalRequirementDSL.g:2325:2: ( ( (lv_predicate_0_0= rulePredicate ) ) | ( (lv_predObj_1_0= rulePredicateObject ) ) )
            {
            // InternalRequirementDSL.g:2325:2: ( ( (lv_predicate_0_0= rulePredicate ) ) | ( (lv_predObj_1_0= rulePredicateObject ) ) )
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==RULE_STRING||LA55_0==RULE_ID) ) {
                alt55=1;
            }
            else if ( ((LA55_0>=67 && LA55_0<=80)||(LA55_0>=86 && LA55_0<=95)) ) {
                alt55=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;
            }
            switch (alt55) {
                case 1 :
                    // InternalRequirementDSL.g:2326:3: ( (lv_predicate_0_0= rulePredicate ) )
                    {
                    // InternalRequirementDSL.g:2326:3: ( (lv_predicate_0_0= rulePredicate ) )
                    // InternalRequirementDSL.g:2327:4: (lv_predicate_0_0= rulePredicate )
                    {
                    // InternalRequirementDSL.g:2327:4: (lv_predicate_0_0= rulePredicate )
                    // InternalRequirementDSL.g:2328:5: lv_predicate_0_0= rulePredicate
                    {

                    					newCompositeNode(grammarAccess.getPredOrObjectAccess().getPredicatePredicateParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_predicate_0_0=rulePredicate();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getPredOrObjectRule());
                    					}
                    					set(
                    						current,
                    						"predicate",
                    						lv_predicate_0_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.Predicate");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:2346:3: ( (lv_predObj_1_0= rulePredicateObject ) )
                    {
                    // InternalRequirementDSL.g:2346:3: ( (lv_predObj_1_0= rulePredicateObject ) )
                    // InternalRequirementDSL.g:2347:4: (lv_predObj_1_0= rulePredicateObject )
                    {
                    // InternalRequirementDSL.g:2347:4: (lv_predObj_1_0= rulePredicateObject )
                    // InternalRequirementDSL.g:2348:5: lv_predObj_1_0= rulePredicateObject
                    {

                    					newCompositeNode(grammarAccess.getPredOrObjectAccess().getPredObjPredicateObjectParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_predObj_1_0=rulePredicateObject();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getPredOrObjectRule());
                    					}
                    					set(
                    						current,
                    						"predObj",
                    						lv_predObj_1_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.PredicateObject");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePredOrObject"


    // $ANTLR start "entryRuleExistencePreface"
    // InternalRequirementDSL.g:2369:1: entryRuleExistencePreface returns [EObject current=null] : iv_ruleExistencePreface= ruleExistencePreface EOF ;
    public final EObject entryRuleExistencePreface() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExistencePreface = null;


        try {
            // InternalRequirementDSL.g:2369:57: (iv_ruleExistencePreface= ruleExistencePreface EOF )
            // InternalRequirementDSL.g:2370:2: iv_ruleExistencePreface= ruleExistencePreface EOF
            {
             newCompositeNode(grammarAccess.getExistencePrefaceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExistencePreface=ruleExistencePreface();

            state._fsp--;

             current =iv_ruleExistencePreface; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExistencePreface"


    // $ANTLR start "ruleExistencePreface"
    // InternalRequirementDSL.g:2376:1: ruleExistencePreface returns [EObject current=null] : ( () (otherlv_1= 'there' | otherlv_2= 'There' ) ( (lv_modifier_3_0= ruleModifier ) )? (otherlv_4= 'exist' | otherlv_5= 'exists' ) ) ;
    public final EObject ruleExistencePreface() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Enumerator lv_modifier_3_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2382:2: ( ( () (otherlv_1= 'there' | otherlv_2= 'There' ) ( (lv_modifier_3_0= ruleModifier ) )? (otherlv_4= 'exist' | otherlv_5= 'exists' ) ) )
            // InternalRequirementDSL.g:2383:2: ( () (otherlv_1= 'there' | otherlv_2= 'There' ) ( (lv_modifier_3_0= ruleModifier ) )? (otherlv_4= 'exist' | otherlv_5= 'exists' ) )
            {
            // InternalRequirementDSL.g:2383:2: ( () (otherlv_1= 'there' | otherlv_2= 'There' ) ( (lv_modifier_3_0= ruleModifier ) )? (otherlv_4= 'exist' | otherlv_5= 'exists' ) )
            // InternalRequirementDSL.g:2384:3: () (otherlv_1= 'there' | otherlv_2= 'There' ) ( (lv_modifier_3_0= ruleModifier ) )? (otherlv_4= 'exist' | otherlv_5= 'exists' )
            {
            // InternalRequirementDSL.g:2384:3: ()
            // InternalRequirementDSL.g:2385:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getExistencePrefaceAccess().getExistencePrefaceAction_0(),
            					current);
            			

            }

            // InternalRequirementDSL.g:2391:3: (otherlv_1= 'there' | otherlv_2= 'There' )
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==19) ) {
                alt56=1;
            }
            else if ( (LA56_0==20) ) {
                alt56=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;
            }
            switch (alt56) {
                case 1 :
                    // InternalRequirementDSL.g:2392:4: otherlv_1= 'there'
                    {
                    otherlv_1=(Token)match(input,19,FOLLOW_30); 

                    				newLeafNode(otherlv_1, grammarAccess.getExistencePrefaceAccess().getThereKeyword_1_0());
                    			

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:2397:4: otherlv_2= 'There'
                    {
                    otherlv_2=(Token)match(input,20,FOLLOW_30); 

                    				newLeafNode(otherlv_2, grammarAccess.getExistencePrefaceAccess().getThereKeyword_1_1());
                    			

                    }
                    break;

            }

            // InternalRequirementDSL.g:2402:3: ( (lv_modifier_3_0= ruleModifier ) )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( ((LA57_0>=152 && LA57_0<=159)) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // InternalRequirementDSL.g:2403:4: (lv_modifier_3_0= ruleModifier )
                    {
                    // InternalRequirementDSL.g:2403:4: (lv_modifier_3_0= ruleModifier )
                    // InternalRequirementDSL.g:2404:5: lv_modifier_3_0= ruleModifier
                    {

                    					newCompositeNode(grammarAccess.getExistencePrefaceAccess().getModifierModifierEnumRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_31);
                    lv_modifier_3_0=ruleModifier();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getExistencePrefaceRule());
                    					}
                    					set(
                    						current,
                    						"modifier",
                    						lv_modifier_3_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.Modifier");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalRequirementDSL.g:2421:3: (otherlv_4= 'exist' | otherlv_5= 'exists' )
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==21) ) {
                alt58=1;
            }
            else if ( (LA58_0==22) ) {
                alt58=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;
            }
            switch (alt58) {
                case 1 :
                    // InternalRequirementDSL.g:2422:4: otherlv_4= 'exist'
                    {
                    otherlv_4=(Token)match(input,21,FOLLOW_2); 

                    				newLeafNode(otherlv_4, grammarAccess.getExistencePrefaceAccess().getExistKeyword_3_0());
                    			

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:2427:4: otherlv_5= 'exists'
                    {
                    otherlv_5=(Token)match(input,22,FOLLOW_2); 

                    				newLeafNode(otherlv_5, grammarAccess.getExistencePrefaceAccess().getExistsKeyword_3_1());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExistencePreface"


    // $ANTLR start "entryRuleAuxNeg"
    // InternalRequirementDSL.g:2436:1: entryRuleAuxNeg returns [EObject current=null] : iv_ruleAuxNeg= ruleAuxNeg EOF ;
    public final EObject entryRuleAuxNeg() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAuxNeg = null;


        try {
            // InternalRequirementDSL.g:2436:47: (iv_ruleAuxNeg= ruleAuxNeg EOF )
            // InternalRequirementDSL.g:2437:2: iv_ruleAuxNeg= ruleAuxNeg EOF
            {
             newCompositeNode(grammarAccess.getAuxNegRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAuxNeg=ruleAuxNeg();

            state._fsp--;

             current =iv_ruleAuxNeg; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAuxNeg"


    // $ANTLR start "ruleAuxNeg"
    // InternalRequirementDSL.g:2443:1: ruleAuxNeg returns [EObject current=null] : ( ( ( (lv_auxiliarVerb_0_0= ruleAuxiliaryVerb ) ) ( (lv_negation_1_0= ruleNegation ) )? ) | ( (lv_auxiliarVerbNeg_2_0= ruleAuxiliaryVerbNegation ) ) ) ;
    public final EObject ruleAuxNeg() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_auxiliarVerb_0_0 = null;

        AntlrDatatypeRuleToken lv_negation_1_0 = null;

        AntlrDatatypeRuleToken lv_auxiliarVerbNeg_2_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2449:2: ( ( ( ( (lv_auxiliarVerb_0_0= ruleAuxiliaryVerb ) ) ( (lv_negation_1_0= ruleNegation ) )? ) | ( (lv_auxiliarVerbNeg_2_0= ruleAuxiliaryVerbNegation ) ) ) )
            // InternalRequirementDSL.g:2450:2: ( ( ( (lv_auxiliarVerb_0_0= ruleAuxiliaryVerb ) ) ( (lv_negation_1_0= ruleNegation ) )? ) | ( (lv_auxiliarVerbNeg_2_0= ruleAuxiliaryVerbNegation ) ) )
            {
            // InternalRequirementDSL.g:2450:2: ( ( ( (lv_auxiliarVerb_0_0= ruleAuxiliaryVerb ) ) ( (lv_negation_1_0= ruleNegation ) )? ) | ( (lv_auxiliarVerbNeg_2_0= ruleAuxiliaryVerbNegation ) ) )
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( ((LA60_0>=29 && LA60_0<=36)) ) {
                alt60=1;
            }
            else if ( ((LA60_0>=82 && LA60_0<=85)) ) {
                alt60=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 60, 0, input);

                throw nvae;
            }
            switch (alt60) {
                case 1 :
                    // InternalRequirementDSL.g:2451:3: ( ( (lv_auxiliarVerb_0_0= ruleAuxiliaryVerb ) ) ( (lv_negation_1_0= ruleNegation ) )? )
                    {
                    // InternalRequirementDSL.g:2451:3: ( ( (lv_auxiliarVerb_0_0= ruleAuxiliaryVerb ) ) ( (lv_negation_1_0= ruleNegation ) )? )
                    // InternalRequirementDSL.g:2452:4: ( (lv_auxiliarVerb_0_0= ruleAuxiliaryVerb ) ) ( (lv_negation_1_0= ruleNegation ) )?
                    {
                    // InternalRequirementDSL.g:2452:4: ( (lv_auxiliarVerb_0_0= ruleAuxiliaryVerb ) )
                    // InternalRequirementDSL.g:2453:5: (lv_auxiliarVerb_0_0= ruleAuxiliaryVerb )
                    {
                    // InternalRequirementDSL.g:2453:5: (lv_auxiliarVerb_0_0= ruleAuxiliaryVerb )
                    // InternalRequirementDSL.g:2454:6: lv_auxiliarVerb_0_0= ruleAuxiliaryVerb
                    {

                    						newCompositeNode(grammarAccess.getAuxNegAccess().getAuxiliarVerbAuxiliaryVerbParserRuleCall_0_0_0());
                    					
                    pushFollow(FOLLOW_32);
                    lv_auxiliarVerb_0_0=ruleAuxiliaryVerb();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getAuxNegRule());
                    						}
                    						set(
                    							current,
                    							"auxiliarVerb",
                    							lv_auxiliarVerb_0_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.AuxiliaryVerb");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:2471:4: ( (lv_negation_1_0= ruleNegation ) )?
                    int alt59=2;
                    int LA59_0 = input.LA(1);

                    if ( (LA59_0==81) ) {
                        alt59=1;
                    }
                    switch (alt59) {
                        case 1 :
                            // InternalRequirementDSL.g:2472:5: (lv_negation_1_0= ruleNegation )
                            {
                            // InternalRequirementDSL.g:2472:5: (lv_negation_1_0= ruleNegation )
                            // InternalRequirementDSL.g:2473:6: lv_negation_1_0= ruleNegation
                            {

                            						newCompositeNode(grammarAccess.getAuxNegAccess().getNegationNegationParserRuleCall_0_1_0());
                            					
                            pushFollow(FOLLOW_2);
                            lv_negation_1_0=ruleNegation();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getAuxNegRule());
                            						}
                            						set(
                            							current,
                            							"negation",
                            							lv_negation_1_0,
                            							"de.fraunhofer.isst.stars.RequirementDSL.Negation");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:2492:3: ( (lv_auxiliarVerbNeg_2_0= ruleAuxiliaryVerbNegation ) )
                    {
                    // InternalRequirementDSL.g:2492:3: ( (lv_auxiliarVerbNeg_2_0= ruleAuxiliaryVerbNegation ) )
                    // InternalRequirementDSL.g:2493:4: (lv_auxiliarVerbNeg_2_0= ruleAuxiliaryVerbNegation )
                    {
                    // InternalRequirementDSL.g:2493:4: (lv_auxiliarVerbNeg_2_0= ruleAuxiliaryVerbNegation )
                    // InternalRequirementDSL.g:2494:5: lv_auxiliarVerbNeg_2_0= ruleAuxiliaryVerbNegation
                    {

                    					newCompositeNode(grammarAccess.getAuxNegAccess().getAuxiliarVerbNegAuxiliaryVerbNegationParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_auxiliarVerbNeg_2_0=ruleAuxiliaryVerbNegation();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getAuxNegRule());
                    					}
                    					set(
                    						current,
                    						"auxiliarVerbNeg",
                    						lv_auxiliarVerbNeg_2_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.AuxiliaryVerbNegation");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAuxNeg"


    // $ANTLR start "entryRulePreNominative"
    // InternalRequirementDSL.g:2515:1: entryRulePreNominative returns [EObject current=null] : iv_rulePreNominative= rulePreNominative EOF ;
    public final EObject entryRulePreNominative() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePreNominative = null;


        try {
            // InternalRequirementDSL.g:2515:54: (iv_rulePreNominative= rulePreNominative EOF )
            // InternalRequirementDSL.g:2516:2: iv_rulePreNominative= rulePreNominative EOF
            {
             newCompositeNode(grammarAccess.getPreNominativeRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePreNominative=rulePreNominative();

            state._fsp--;

             current =iv_rulePreNominative; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePreNominative"


    // $ANTLR start "rulePreNominative"
    // InternalRequirementDSL.g:2522:1: rulePreNominative returns [EObject current=null] : ( ( (lv_determiner_0_0= ruleQuantification ) ) | ( (lv_article_1_0= ruleArticles ) ) | ( (lv_article_2_0= ruleRefArticles ) ) ) ;
    public final EObject rulePreNominative() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_determiner_0_0 = null;

        AntlrDatatypeRuleToken lv_article_1_0 = null;

        AntlrDatatypeRuleToken lv_article_2_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2528:2: ( ( ( (lv_determiner_0_0= ruleQuantification ) ) | ( (lv_article_1_0= ruleArticles ) ) | ( (lv_article_2_0= ruleRefArticles ) ) ) )
            // InternalRequirementDSL.g:2529:2: ( ( (lv_determiner_0_0= ruleQuantification ) ) | ( (lv_article_1_0= ruleArticles ) ) | ( (lv_article_2_0= ruleRefArticles ) ) )
            {
            // InternalRequirementDSL.g:2529:2: ( ( (lv_determiner_0_0= ruleQuantification ) ) | ( (lv_article_1_0= ruleArticles ) ) | ( (lv_article_2_0= ruleRefArticles ) ) )
            int alt61=3;
            switch ( input.LA(1) ) {
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
                {
                alt61=1;
                }
                break;
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
                {
                alt61=2;
                }
                break;
            case 92:
            case 93:
            case 94:
            case 95:
                {
                alt61=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;
            }

            switch (alt61) {
                case 1 :
                    // InternalRequirementDSL.g:2530:3: ( (lv_determiner_0_0= ruleQuantification ) )
                    {
                    // InternalRequirementDSL.g:2530:3: ( (lv_determiner_0_0= ruleQuantification ) )
                    // InternalRequirementDSL.g:2531:4: (lv_determiner_0_0= ruleQuantification )
                    {
                    // InternalRequirementDSL.g:2531:4: (lv_determiner_0_0= ruleQuantification )
                    // InternalRequirementDSL.g:2532:5: lv_determiner_0_0= ruleQuantification
                    {

                    					newCompositeNode(grammarAccess.getPreNominativeAccess().getDeterminerQuantificationParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_determiner_0_0=ruleQuantification();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getPreNominativeRule());
                    					}
                    					set(
                    						current,
                    						"determiner",
                    						lv_determiner_0_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.Quantification");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:2550:3: ( (lv_article_1_0= ruleArticles ) )
                    {
                    // InternalRequirementDSL.g:2550:3: ( (lv_article_1_0= ruleArticles ) )
                    // InternalRequirementDSL.g:2551:4: (lv_article_1_0= ruleArticles )
                    {
                    // InternalRequirementDSL.g:2551:4: (lv_article_1_0= ruleArticles )
                    // InternalRequirementDSL.g:2552:5: lv_article_1_0= ruleArticles
                    {

                    					newCompositeNode(grammarAccess.getPreNominativeAccess().getArticleArticlesParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_article_1_0=ruleArticles();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getPreNominativeRule());
                    					}
                    					set(
                    						current,
                    						"article",
                    						lv_article_1_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.Articles");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:2570:3: ( (lv_article_2_0= ruleRefArticles ) )
                    {
                    // InternalRequirementDSL.g:2570:3: ( (lv_article_2_0= ruleRefArticles ) )
                    // InternalRequirementDSL.g:2571:4: (lv_article_2_0= ruleRefArticles )
                    {
                    // InternalRequirementDSL.g:2571:4: (lv_article_2_0= ruleRefArticles )
                    // InternalRequirementDSL.g:2572:5: lv_article_2_0= ruleRefArticles
                    {

                    					newCompositeNode(grammarAccess.getPreNominativeAccess().getArticleRefArticlesParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_article_2_0=ruleRefArticles();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getPreNominativeRule());
                    					}
                    					set(
                    						current,
                    						"article",
                    						lv_article_2_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.RefArticles");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePreNominative"


    // $ANTLR start "entryRuleAdverbial"
    // InternalRequirementDSL.g:2593:1: entryRuleAdverbial returns [String current=null] : iv_ruleAdverbial= ruleAdverbial EOF ;
    public final String entryRuleAdverbial() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleAdverbial = null;


        try {
            // InternalRequirementDSL.g:2593:49: (iv_ruleAdverbial= ruleAdverbial EOF )
            // InternalRequirementDSL.g:2594:2: iv_ruleAdverbial= ruleAdverbial EOF
            {
             newCompositeNode(grammarAccess.getAdverbialRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAdverbial=ruleAdverbial();

            state._fsp--;

             current =iv_ruleAdverbial.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAdverbial"


    // $ANTLR start "ruleAdverbial"
    // InternalRequirementDSL.g:2600:1: ruleAdverbial returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_SizeAdverbial_0= ruleSizeAdverbial | this_PositionAdverbial_1= rulePositionAdverbial | this_ComparisonAdverbial_2= ruleComparisonAdverbial ) ;
    public final AntlrDatatypeRuleToken ruleAdverbial() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_SizeAdverbial_0 = null;

        AntlrDatatypeRuleToken this_PositionAdverbial_1 = null;

        AntlrDatatypeRuleToken this_ComparisonAdverbial_2 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2606:2: ( (this_SizeAdverbial_0= ruleSizeAdverbial | this_PositionAdverbial_1= rulePositionAdverbial | this_ComparisonAdverbial_2= ruleComparisonAdverbial ) )
            // InternalRequirementDSL.g:2607:2: (this_SizeAdverbial_0= ruleSizeAdverbial | this_PositionAdverbial_1= rulePositionAdverbial | this_ComparisonAdverbial_2= ruleComparisonAdverbial )
            {
            // InternalRequirementDSL.g:2607:2: (this_SizeAdverbial_0= ruleSizeAdverbial | this_PositionAdverbial_1= rulePositionAdverbial | this_ComparisonAdverbial_2= ruleComparisonAdverbial )
            int alt62=3;
            switch ( input.LA(1) ) {
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
                {
                alt62=1;
                }
                break;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
                {
                alt62=2;
                }
                break;
            case 43:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
                {
                alt62=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;
            }

            switch (alt62) {
                case 1 :
                    // InternalRequirementDSL.g:2608:3: this_SizeAdverbial_0= ruleSizeAdverbial
                    {

                    			newCompositeNode(grammarAccess.getAdverbialAccess().getSizeAdverbialParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_SizeAdverbial_0=ruleSizeAdverbial();

                    state._fsp--;


                    			current.merge(this_SizeAdverbial_0);
                    		

                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:2619:3: this_PositionAdverbial_1= rulePositionAdverbial
                    {

                    			newCompositeNode(grammarAccess.getAdverbialAccess().getPositionAdverbialParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_PositionAdverbial_1=rulePositionAdverbial();

                    state._fsp--;


                    			current.merge(this_PositionAdverbial_1);
                    		

                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:2630:3: this_ComparisonAdverbial_2= ruleComparisonAdverbial
                    {

                    			newCompositeNode(grammarAccess.getAdverbialAccess().getComparisonAdverbialParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_ComparisonAdverbial_2=ruleComparisonAdverbial();

                    state._fsp--;


                    			current.merge(this_ComparisonAdverbial_2);
                    		

                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAdverbial"


    // $ANTLR start "entryRuleRelation"
    // InternalRequirementDSL.g:2644:1: entryRuleRelation returns [EObject current=null] : iv_ruleRelation= ruleRelation EOF ;
    public final EObject entryRuleRelation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelation = null;


        try {
            // InternalRequirementDSL.g:2644:49: (iv_ruleRelation= ruleRelation EOF )
            // InternalRequirementDSL.g:2645:2: iv_ruleRelation= ruleRelation EOF
            {
             newCompositeNode(grammarAccess.getRelationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRelation=ruleRelation();

            state._fsp--;

             current =iv_ruleRelation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRelation"


    // $ANTLR start "ruleRelation"
    // InternalRequirementDSL.g:2651:1: ruleRelation returns [EObject current=null] : ( ( (lv_relposAdv_0_0= rulePositionAdverbial ) ) ( (lv_relDel_1_0= ruleRelationDelimiter ) ) ( (lv_relComp_2_0= ruleComperators ) ) ( (lv_relElements_3_0= ruleRelObjects ) ) ) ;
    public final EObject ruleRelation() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_relposAdv_0_0 = null;

        AntlrDatatypeRuleToken lv_relDel_1_0 = null;

        AntlrDatatypeRuleToken lv_relComp_2_0 = null;

        EObject lv_relElements_3_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2657:2: ( ( ( (lv_relposAdv_0_0= rulePositionAdverbial ) ) ( (lv_relDel_1_0= ruleRelationDelimiter ) ) ( (lv_relComp_2_0= ruleComperators ) ) ( (lv_relElements_3_0= ruleRelObjects ) ) ) )
            // InternalRequirementDSL.g:2658:2: ( ( (lv_relposAdv_0_0= rulePositionAdverbial ) ) ( (lv_relDel_1_0= ruleRelationDelimiter ) ) ( (lv_relComp_2_0= ruleComperators ) ) ( (lv_relElements_3_0= ruleRelObjects ) ) )
            {
            // InternalRequirementDSL.g:2658:2: ( ( (lv_relposAdv_0_0= rulePositionAdverbial ) ) ( (lv_relDel_1_0= ruleRelationDelimiter ) ) ( (lv_relComp_2_0= ruleComperators ) ) ( (lv_relElements_3_0= ruleRelObjects ) ) )
            // InternalRequirementDSL.g:2659:3: ( (lv_relposAdv_0_0= rulePositionAdverbial ) ) ( (lv_relDel_1_0= ruleRelationDelimiter ) ) ( (lv_relComp_2_0= ruleComperators ) ) ( (lv_relElements_3_0= ruleRelObjects ) )
            {
            // InternalRequirementDSL.g:2659:3: ( (lv_relposAdv_0_0= rulePositionAdverbial ) )
            // InternalRequirementDSL.g:2660:4: (lv_relposAdv_0_0= rulePositionAdverbial )
            {
            // InternalRequirementDSL.g:2660:4: (lv_relposAdv_0_0= rulePositionAdverbial )
            // InternalRequirementDSL.g:2661:5: lv_relposAdv_0_0= rulePositionAdverbial
            {

            					newCompositeNode(grammarAccess.getRelationAccess().getRelposAdvPositionAdverbialParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_33);
            lv_relposAdv_0_0=rulePositionAdverbial();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRelationRule());
            					}
            					set(
            						current,
            						"relposAdv",
            						lv_relposAdv_0_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.PositionAdverbial");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:2678:3: ( (lv_relDel_1_0= ruleRelationDelimiter ) )
            // InternalRequirementDSL.g:2679:4: (lv_relDel_1_0= ruleRelationDelimiter )
            {
            // InternalRequirementDSL.g:2679:4: (lv_relDel_1_0= ruleRelationDelimiter )
            // InternalRequirementDSL.g:2680:5: lv_relDel_1_0= ruleRelationDelimiter
            {

            					newCompositeNode(grammarAccess.getRelationAccess().getRelDelRelationDelimiterParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_34);
            lv_relDel_1_0=ruleRelationDelimiter();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRelationRule());
            					}
            					set(
            						current,
            						"relDel",
            						lv_relDel_1_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.RelationDelimiter");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:2697:3: ( (lv_relComp_2_0= ruleComperators ) )
            // InternalRequirementDSL.g:2698:4: (lv_relComp_2_0= ruleComperators )
            {
            // InternalRequirementDSL.g:2698:4: (lv_relComp_2_0= ruleComperators )
            // InternalRequirementDSL.g:2699:5: lv_relComp_2_0= ruleComperators
            {

            					newCompositeNode(grammarAccess.getRelationAccess().getRelCompComperatorsParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_35);
            lv_relComp_2_0=ruleComperators();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRelationRule());
            					}
            					set(
            						current,
            						"relComp",
            						lv_relComp_2_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.Comperators");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:2716:3: ( (lv_relElements_3_0= ruleRelObjects ) )
            // InternalRequirementDSL.g:2717:4: (lv_relElements_3_0= ruleRelObjects )
            {
            // InternalRequirementDSL.g:2717:4: (lv_relElements_3_0= ruleRelObjects )
            // InternalRequirementDSL.g:2718:5: lv_relElements_3_0= ruleRelObjects
            {

            					newCompositeNode(grammarAccess.getRelationAccess().getRelElementsRelObjectsParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_2);
            lv_relElements_3_0=ruleRelObjects();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRelationRule());
            					}
            					set(
            						current,
            						"relElements",
            						lv_relElements_3_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.RelObjects");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRelation"


    // $ANTLR start "entryRuleRelObjects"
    // InternalRequirementDSL.g:2739:1: entryRuleRelObjects returns [EObject current=null] : iv_ruleRelObjects= ruleRelObjects EOF ;
    public final EObject entryRuleRelObjects() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelObjects = null;


        try {
            // InternalRequirementDSL.g:2739:51: (iv_ruleRelObjects= ruleRelObjects EOF )
            // InternalRequirementDSL.g:2740:2: iv_ruleRelObjects= ruleRelObjects EOF
            {
             newCompositeNode(grammarAccess.getRelObjectsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRelObjects=ruleRelObjects();

            state._fsp--;

             current =iv_ruleRelObjects; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRelObjects"


    // $ANTLR start "ruleRelObjects"
    // InternalRequirementDSL.g:2746:1: ruleRelObjects returns [EObject current=null] : ( ( (lv_object_0_0= ruleObject ) ) ( (lv_property_1_0= ruleProperty ) )? ( ( (lv_relConj_2_0= ruleRelConjunction ) ) ( (lv_object_3_0= ruleObject ) ) ( (lv_property_4_0= ruleProperty ) )? )* ) ;
    public final EObject ruleRelObjects() throws RecognitionException {
        EObject current = null;

        EObject lv_object_0_0 = null;

        EObject lv_property_1_0 = null;

        AntlrDatatypeRuleToken lv_relConj_2_0 = null;

        EObject lv_object_3_0 = null;

        EObject lv_property_4_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2752:2: ( ( ( (lv_object_0_0= ruleObject ) ) ( (lv_property_1_0= ruleProperty ) )? ( ( (lv_relConj_2_0= ruleRelConjunction ) ) ( (lv_object_3_0= ruleObject ) ) ( (lv_property_4_0= ruleProperty ) )? )* ) )
            // InternalRequirementDSL.g:2753:2: ( ( (lv_object_0_0= ruleObject ) ) ( (lv_property_1_0= ruleProperty ) )? ( ( (lv_relConj_2_0= ruleRelConjunction ) ) ( (lv_object_3_0= ruleObject ) ) ( (lv_property_4_0= ruleProperty ) )? )* )
            {
            // InternalRequirementDSL.g:2753:2: ( ( (lv_object_0_0= ruleObject ) ) ( (lv_property_1_0= ruleProperty ) )? ( ( (lv_relConj_2_0= ruleRelConjunction ) ) ( (lv_object_3_0= ruleObject ) ) ( (lv_property_4_0= ruleProperty ) )? )* )
            // InternalRequirementDSL.g:2754:3: ( (lv_object_0_0= ruleObject ) ) ( (lv_property_1_0= ruleProperty ) )? ( ( (lv_relConj_2_0= ruleRelConjunction ) ) ( (lv_object_3_0= ruleObject ) ) ( (lv_property_4_0= ruleProperty ) )? )*
            {
            // InternalRequirementDSL.g:2754:3: ( (lv_object_0_0= ruleObject ) )
            // InternalRequirementDSL.g:2755:4: (lv_object_0_0= ruleObject )
            {
            // InternalRequirementDSL.g:2755:4: (lv_object_0_0= ruleObject )
            // InternalRequirementDSL.g:2756:5: lv_object_0_0= ruleObject
            {

            					newCompositeNode(grammarAccess.getRelObjectsAccess().getObjectObjectParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_36);
            lv_object_0_0=ruleObject();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRelObjectsRule());
            					}
            					add(
            						current,
            						"object",
            						lv_object_0_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.Object");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:2773:3: ( (lv_property_1_0= ruleProperty ) )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==RULE_PROPERTY_TERM) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // InternalRequirementDSL.g:2774:4: (lv_property_1_0= ruleProperty )
                    {
                    // InternalRequirementDSL.g:2774:4: (lv_property_1_0= ruleProperty )
                    // InternalRequirementDSL.g:2775:5: lv_property_1_0= ruleProperty
                    {

                    					newCompositeNode(grammarAccess.getRelObjectsAccess().getPropertyPropertyParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_37);
                    lv_property_1_0=ruleProperty();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getRelObjectsRule());
                    					}
                    					add(
                    						current,
                    						"property",
                    						lv_property_1_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.Property");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalRequirementDSL.g:2792:3: ( ( (lv_relConj_2_0= ruleRelConjunction ) ) ( (lv_object_3_0= ruleObject ) ) ( (lv_property_4_0= ruleProperty ) )? )*
            loop65:
            do {
                int alt65=2;
                int LA65_0 = input.LA(1);

                if ( ((LA65_0>=39 && LA65_0<=40)) ) {
                    alt65=1;
                }


                switch (alt65) {
            	case 1 :
            	    // InternalRequirementDSL.g:2793:4: ( (lv_relConj_2_0= ruleRelConjunction ) ) ( (lv_object_3_0= ruleObject ) ) ( (lv_property_4_0= ruleProperty ) )?
            	    {
            	    // InternalRequirementDSL.g:2793:4: ( (lv_relConj_2_0= ruleRelConjunction ) )
            	    // InternalRequirementDSL.g:2794:5: (lv_relConj_2_0= ruleRelConjunction )
            	    {
            	    // InternalRequirementDSL.g:2794:5: (lv_relConj_2_0= ruleRelConjunction )
            	    // InternalRequirementDSL.g:2795:6: lv_relConj_2_0= ruleRelConjunction
            	    {

            	    						newCompositeNode(grammarAccess.getRelObjectsAccess().getRelConjRelConjunctionParserRuleCall_2_0_0());
            	    					
            	    pushFollow(FOLLOW_35);
            	    lv_relConj_2_0=ruleRelConjunction();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getRelObjectsRule());
            	    						}
            	    						add(
            	    							current,
            	    							"relConj",
            	    							lv_relConj_2_0,
            	    							"de.fraunhofer.isst.stars.RequirementDSL.RelConjunction");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    // InternalRequirementDSL.g:2812:4: ( (lv_object_3_0= ruleObject ) )
            	    // InternalRequirementDSL.g:2813:5: (lv_object_3_0= ruleObject )
            	    {
            	    // InternalRequirementDSL.g:2813:5: (lv_object_3_0= ruleObject )
            	    // InternalRequirementDSL.g:2814:6: lv_object_3_0= ruleObject
            	    {

            	    						newCompositeNode(grammarAccess.getRelObjectsAccess().getObjectObjectParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_36);
            	    lv_object_3_0=ruleObject();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getRelObjectsRule());
            	    						}
            	    						add(
            	    							current,
            	    							"object",
            	    							lv_object_3_0,
            	    							"de.fraunhofer.isst.stars.RequirementDSL.Object");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    // InternalRequirementDSL.g:2831:4: ( (lv_property_4_0= ruleProperty ) )?
            	    int alt64=2;
            	    int LA64_0 = input.LA(1);

            	    if ( (LA64_0==RULE_PROPERTY_TERM) ) {
            	        alt64=1;
            	    }
            	    switch (alt64) {
            	        case 1 :
            	            // InternalRequirementDSL.g:2832:5: (lv_property_4_0= ruleProperty )
            	            {
            	            // InternalRequirementDSL.g:2832:5: (lv_property_4_0= ruleProperty )
            	            // InternalRequirementDSL.g:2833:6: lv_property_4_0= ruleProperty
            	            {

            	            						newCompositeNode(grammarAccess.getRelObjectsAccess().getPropertyPropertyParserRuleCall_2_2_0());
            	            					
            	            pushFollow(FOLLOW_37);
            	            lv_property_4_0=ruleProperty();

            	            state._fsp--;


            	            						if (current==null) {
            	            							current = createModelElementForParent(grammarAccess.getRelObjectsRule());
            	            						}
            	            						add(
            	            							current,
            	            							"property",
            	            							lv_property_4_0,
            	            							"de.fraunhofer.isst.stars.RequirementDSL.Property");
            	            						afterParserOrEnumRuleCall();
            	            					

            	            }


            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop65;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRelObjects"


    // $ANTLR start "entryRuleConstraints"
    // InternalRequirementDSL.g:2855:1: entryRuleConstraints returns [EObject current=null] : iv_ruleConstraints= ruleConstraints EOF ;
    public final EObject entryRuleConstraints() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstraints = null;


        try {
            // InternalRequirementDSL.g:2855:52: (iv_ruleConstraints= ruleConstraints EOF )
            // InternalRequirementDSL.g:2856:2: iv_ruleConstraints= ruleConstraints EOF
            {
             newCompositeNode(grammarAccess.getConstraintsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConstraints=ruleConstraints();

            state._fsp--;

             current =iv_ruleConstraints; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConstraints"


    // $ANTLR start "ruleConstraints"
    // InternalRequirementDSL.g:2862:1: ruleConstraints returns [EObject current=null] : ( ( (lv_timeConstraint_0_0= ruleTimeConstraint ) ) | ( (lv_constraint_1_0= ruleConstraint ) ) ) ;
    public final EObject ruleConstraints() throws RecognitionException {
        EObject current = null;

        EObject lv_timeConstraint_0_0 = null;

        EObject lv_constraint_1_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2868:2: ( ( ( (lv_timeConstraint_0_0= ruleTimeConstraint ) ) | ( (lv_constraint_1_0= ruleConstraint ) ) ) )
            // InternalRequirementDSL.g:2869:2: ( ( (lv_timeConstraint_0_0= ruleTimeConstraint ) ) | ( (lv_constraint_1_0= ruleConstraint ) ) )
            {
            // InternalRequirementDSL.g:2869:2: ( ( (lv_timeConstraint_0_0= ruleTimeConstraint ) ) | ( (lv_constraint_1_0= ruleConstraint ) ) )
            int alt66=2;
            alt66 = dfa66.predict(input);
            switch (alt66) {
                case 1 :
                    // InternalRequirementDSL.g:2870:3: ( (lv_timeConstraint_0_0= ruleTimeConstraint ) )
                    {
                    // InternalRequirementDSL.g:2870:3: ( (lv_timeConstraint_0_0= ruleTimeConstraint ) )
                    // InternalRequirementDSL.g:2871:4: (lv_timeConstraint_0_0= ruleTimeConstraint )
                    {
                    // InternalRequirementDSL.g:2871:4: (lv_timeConstraint_0_0= ruleTimeConstraint )
                    // InternalRequirementDSL.g:2872:5: lv_timeConstraint_0_0= ruleTimeConstraint
                    {

                    					newCompositeNode(grammarAccess.getConstraintsAccess().getTimeConstraintTimeConstraintParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_timeConstraint_0_0=ruleTimeConstraint();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getConstraintsRule());
                    					}
                    					set(
                    						current,
                    						"timeConstraint",
                    						lv_timeConstraint_0_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.TimeConstraint");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:2890:3: ( (lv_constraint_1_0= ruleConstraint ) )
                    {
                    // InternalRequirementDSL.g:2890:3: ( (lv_constraint_1_0= ruleConstraint ) )
                    // InternalRequirementDSL.g:2891:4: (lv_constraint_1_0= ruleConstraint )
                    {
                    // InternalRequirementDSL.g:2891:4: (lv_constraint_1_0= ruleConstraint )
                    // InternalRequirementDSL.g:2892:5: lv_constraint_1_0= ruleConstraint
                    {

                    					newCompositeNode(grammarAccess.getConstraintsAccess().getConstraintConstraintParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_constraint_1_0=ruleConstraint();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getConstraintsRule());
                    					}
                    					set(
                    						current,
                    						"constraint",
                    						lv_constraint_1_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.Constraint");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConstraints"


    // $ANTLR start "entryRuleConstraint"
    // InternalRequirementDSL.g:2913:1: entryRuleConstraint returns [EObject current=null] : iv_ruleConstraint= ruleConstraint EOF ;
    public final EObject entryRuleConstraint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstraint = null;


        try {
            // InternalRequirementDSL.g:2913:51: (iv_ruleConstraint= ruleConstraint EOF )
            // InternalRequirementDSL.g:2914:2: iv_ruleConstraint= ruleConstraint EOF
            {
             newCompositeNode(grammarAccess.getConstraintRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConstraint=ruleConstraint();

            state._fsp--;

             current =iv_ruleConstraint; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConstraint"


    // $ANTLR start "ruleConstraint"
    // InternalRequirementDSL.g:2920:1: ruleConstraint returns [EObject current=null] : ( ( (lv_ordinator_0_0= ruleConstraintOrdinators ) ) ( ( (lv_constraint_1_0= ruleObjectConstraint ) ) | ( (lv_constraint_2_0= ruleUnitConstraints ) ) | ( (lv_constraint_3_0= ruleSetConstraint ) ) ) ) ;
    public final EObject ruleConstraint() throws RecognitionException {
        EObject current = null;

        EObject lv_ordinator_0_0 = null;

        EObject lv_constraint_1_0 = null;

        EObject lv_constraint_2_0 = null;

        EObject lv_constraint_3_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2926:2: ( ( ( (lv_ordinator_0_0= ruleConstraintOrdinators ) ) ( ( (lv_constraint_1_0= ruleObjectConstraint ) ) | ( (lv_constraint_2_0= ruleUnitConstraints ) ) | ( (lv_constraint_3_0= ruleSetConstraint ) ) ) ) )
            // InternalRequirementDSL.g:2927:2: ( ( (lv_ordinator_0_0= ruleConstraintOrdinators ) ) ( ( (lv_constraint_1_0= ruleObjectConstraint ) ) | ( (lv_constraint_2_0= ruleUnitConstraints ) ) | ( (lv_constraint_3_0= ruleSetConstraint ) ) ) )
            {
            // InternalRequirementDSL.g:2927:2: ( ( (lv_ordinator_0_0= ruleConstraintOrdinators ) ) ( ( (lv_constraint_1_0= ruleObjectConstraint ) ) | ( (lv_constraint_2_0= ruleUnitConstraints ) ) | ( (lv_constraint_3_0= ruleSetConstraint ) ) ) )
            // InternalRequirementDSL.g:2928:3: ( (lv_ordinator_0_0= ruleConstraintOrdinators ) ) ( ( (lv_constraint_1_0= ruleObjectConstraint ) ) | ( (lv_constraint_2_0= ruleUnitConstraints ) ) | ( (lv_constraint_3_0= ruleSetConstraint ) ) )
            {
            // InternalRequirementDSL.g:2928:3: ( (lv_ordinator_0_0= ruleConstraintOrdinators ) )
            // InternalRequirementDSL.g:2929:4: (lv_ordinator_0_0= ruleConstraintOrdinators )
            {
            // InternalRequirementDSL.g:2929:4: (lv_ordinator_0_0= ruleConstraintOrdinators )
            // InternalRequirementDSL.g:2930:5: lv_ordinator_0_0= ruleConstraintOrdinators
            {

            					newCompositeNode(grammarAccess.getConstraintAccess().getOrdinatorConstraintOrdinatorsParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_38);
            lv_ordinator_0_0=ruleConstraintOrdinators();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConstraintRule());
            					}
            					set(
            						current,
            						"ordinator",
            						lv_ordinator_0_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.ConstraintOrdinators");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:2947:3: ( ( (lv_constraint_1_0= ruleObjectConstraint ) ) | ( (lv_constraint_2_0= ruleUnitConstraints ) ) | ( (lv_constraint_3_0= ruleSetConstraint ) ) )
            int alt67=3;
            switch ( input.LA(1) ) {
            case RULE_STRING:
            case RULE_ID:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
                {
                alt67=1;
                }
                break;
            case RULE_INT:
            case 23:
                {
                alt67=2;
                }
                break;
            case 25:
                {
                alt67=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 67, 0, input);

                throw nvae;
            }

            switch (alt67) {
                case 1 :
                    // InternalRequirementDSL.g:2948:4: ( (lv_constraint_1_0= ruleObjectConstraint ) )
                    {
                    // InternalRequirementDSL.g:2948:4: ( (lv_constraint_1_0= ruleObjectConstraint ) )
                    // InternalRequirementDSL.g:2949:5: (lv_constraint_1_0= ruleObjectConstraint )
                    {
                    // InternalRequirementDSL.g:2949:5: (lv_constraint_1_0= ruleObjectConstraint )
                    // InternalRequirementDSL.g:2950:6: lv_constraint_1_0= ruleObjectConstraint
                    {

                    						newCompositeNode(grammarAccess.getConstraintAccess().getConstraintObjectConstraintParserRuleCall_1_0_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_constraint_1_0=ruleObjectConstraint();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getConstraintRule());
                    						}
                    						set(
                    							current,
                    							"constraint",
                    							lv_constraint_1_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.ObjectConstraint");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:2968:4: ( (lv_constraint_2_0= ruleUnitConstraints ) )
                    {
                    // InternalRequirementDSL.g:2968:4: ( (lv_constraint_2_0= ruleUnitConstraints ) )
                    // InternalRequirementDSL.g:2969:5: (lv_constraint_2_0= ruleUnitConstraints )
                    {
                    // InternalRequirementDSL.g:2969:5: (lv_constraint_2_0= ruleUnitConstraints )
                    // InternalRequirementDSL.g:2970:6: lv_constraint_2_0= ruleUnitConstraints
                    {

                    						newCompositeNode(grammarAccess.getConstraintAccess().getConstraintUnitConstraintsParserRuleCall_1_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_constraint_2_0=ruleUnitConstraints();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getConstraintRule());
                    						}
                    						set(
                    							current,
                    							"constraint",
                    							lv_constraint_2_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.UnitConstraints");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:2988:4: ( (lv_constraint_3_0= ruleSetConstraint ) )
                    {
                    // InternalRequirementDSL.g:2988:4: ( (lv_constraint_3_0= ruleSetConstraint ) )
                    // InternalRequirementDSL.g:2989:5: (lv_constraint_3_0= ruleSetConstraint )
                    {
                    // InternalRequirementDSL.g:2989:5: (lv_constraint_3_0= ruleSetConstraint )
                    // InternalRequirementDSL.g:2990:6: lv_constraint_3_0= ruleSetConstraint
                    {

                    						newCompositeNode(grammarAccess.getConstraintAccess().getConstraintSetConstraintParserRuleCall_1_2_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_constraint_3_0=ruleSetConstraint();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getConstraintRule());
                    						}
                    						set(
                    							current,
                    							"constraint",
                    							lv_constraint_3_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.SetConstraint");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConstraint"


    // $ANTLR start "entryRuleConstraintOrdinators"
    // InternalRequirementDSL.g:3012:1: entryRuleConstraintOrdinators returns [EObject current=null] : iv_ruleConstraintOrdinators= ruleConstraintOrdinators EOF ;
    public final EObject entryRuleConstraintOrdinators() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstraintOrdinators = null;


        try {
            // InternalRequirementDSL.g:3012:61: (iv_ruleConstraintOrdinators= ruleConstraintOrdinators EOF )
            // InternalRequirementDSL.g:3013:2: iv_ruleConstraintOrdinators= ruleConstraintOrdinators EOF
            {
             newCompositeNode(grammarAccess.getConstraintOrdinatorsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConstraintOrdinators=ruleConstraintOrdinators();

            state._fsp--;

             current =iv_ruleConstraintOrdinators; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConstraintOrdinators"


    // $ANTLR start "ruleConstraintOrdinators"
    // InternalRequirementDSL.g:3019:1: ruleConstraintOrdinators returns [EObject current=null] : ( ( (lv_stuffing_0_0= ruleStuffWord ) )? ( (lv_adverbial_1_0= ruleAdverbial ) ) ( (lv_comperator_2_0= ruleComperators ) )? ) ;
    public final EObject ruleConstraintOrdinators() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_stuffing_0_0 = null;

        AntlrDatatypeRuleToken lv_adverbial_1_0 = null;

        AntlrDatatypeRuleToken lv_comperator_2_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:3025:2: ( ( ( (lv_stuffing_0_0= ruleStuffWord ) )? ( (lv_adverbial_1_0= ruleAdverbial ) ) ( (lv_comperator_2_0= ruleComperators ) )? ) )
            // InternalRequirementDSL.g:3026:2: ( ( (lv_stuffing_0_0= ruleStuffWord ) )? ( (lv_adverbial_1_0= ruleAdverbial ) ) ( (lv_comperator_2_0= ruleComperators ) )? )
            {
            // InternalRequirementDSL.g:3026:2: ( ( (lv_stuffing_0_0= ruleStuffWord ) )? ( (lv_adverbial_1_0= ruleAdverbial ) ) ( (lv_comperator_2_0= ruleComperators ) )? )
            // InternalRequirementDSL.g:3027:3: ( (lv_stuffing_0_0= ruleStuffWord ) )? ( (lv_adverbial_1_0= ruleAdverbial ) ) ( (lv_comperator_2_0= ruleComperators ) )?
            {
            // InternalRequirementDSL.g:3027:3: ( (lv_stuffing_0_0= ruleStuffWord ) )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==96) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // InternalRequirementDSL.g:3028:4: (lv_stuffing_0_0= ruleStuffWord )
                    {
                    // InternalRequirementDSL.g:3028:4: (lv_stuffing_0_0= ruleStuffWord )
                    // InternalRequirementDSL.g:3029:5: lv_stuffing_0_0= ruleStuffWord
                    {

                    					newCompositeNode(grammarAccess.getConstraintOrdinatorsAccess().getStuffingStuffWordParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_28);
                    lv_stuffing_0_0=ruleStuffWord();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getConstraintOrdinatorsRule());
                    					}
                    					set(
                    						current,
                    						"stuffing",
                    						lv_stuffing_0_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.StuffWord");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalRequirementDSL.g:3046:3: ( (lv_adverbial_1_0= ruleAdverbial ) )
            // InternalRequirementDSL.g:3047:4: (lv_adverbial_1_0= ruleAdverbial )
            {
            // InternalRequirementDSL.g:3047:4: (lv_adverbial_1_0= ruleAdverbial )
            // InternalRequirementDSL.g:3048:5: lv_adverbial_1_0= ruleAdverbial
            {

            					newCompositeNode(grammarAccess.getConstraintOrdinatorsAccess().getAdverbialAdverbialParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_39);
            lv_adverbial_1_0=ruleAdverbial();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConstraintOrdinatorsRule());
            					}
            					set(
            						current,
            						"adverbial",
            						lv_adverbial_1_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.Adverbial");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:3065:3: ( (lv_comperator_2_0= ruleComperators ) )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( ((LA69_0>=41 && LA69_0<=44)) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // InternalRequirementDSL.g:3066:4: (lv_comperator_2_0= ruleComperators )
                    {
                    // InternalRequirementDSL.g:3066:4: (lv_comperator_2_0= ruleComperators )
                    // InternalRequirementDSL.g:3067:5: lv_comperator_2_0= ruleComperators
                    {

                    					newCompositeNode(grammarAccess.getConstraintOrdinatorsAccess().getComperatorComperatorsParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_comperator_2_0=ruleComperators();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getConstraintOrdinatorsRule());
                    					}
                    					set(
                    						current,
                    						"comperator",
                    						lv_comperator_2_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.Comperators");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConstraintOrdinators"


    // $ANTLR start "entryRuleSetConstraint"
    // InternalRequirementDSL.g:3088:1: entryRuleSetConstraint returns [EObject current=null] : iv_ruleSetConstraint= ruleSetConstraint EOF ;
    public final EObject entryRuleSetConstraint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetConstraint = null;


        try {
            // InternalRequirementDSL.g:3088:54: (iv_ruleSetConstraint= ruleSetConstraint EOF )
            // InternalRequirementDSL.g:3089:2: iv_ruleSetConstraint= ruleSetConstraint EOF
            {
             newCompositeNode(grammarAccess.getSetConstraintRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSetConstraint=ruleSetConstraint();

            state._fsp--;

             current =iv_ruleSetConstraint; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSetConstraint"


    // $ANTLR start "ruleSetConstraint"
    // InternalRequirementDSL.g:3095:1: ruleSetConstraint returns [EObject current=null] : ( ( (lv_set_0_0= ruleObjectSet ) ) | ( (lv_set_1_0= ruleValueSet ) ) ) ;
    public final EObject ruleSetConstraint() throws RecognitionException {
        EObject current = null;

        EObject lv_set_0_0 = null;

        EObject lv_set_1_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:3101:2: ( ( ( (lv_set_0_0= ruleObjectSet ) ) | ( (lv_set_1_0= ruleValueSet ) ) ) )
            // InternalRequirementDSL.g:3102:2: ( ( (lv_set_0_0= ruleObjectSet ) ) | ( (lv_set_1_0= ruleValueSet ) ) )
            {
            // InternalRequirementDSL.g:3102:2: ( ( (lv_set_0_0= ruleObjectSet ) ) | ( (lv_set_1_0= ruleValueSet ) ) )
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==25) ) {
                int LA70_1 = input.LA(2);

                if ( (LA70_1==RULE_STRING||LA70_1==RULE_ID||(LA70_1>=67 && LA70_1<=80)||(LA70_1>=86 && LA70_1<=95)) ) {
                    alt70=1;
                }
                else if ( (LA70_1==RULE_INT) ) {
                    alt70=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 70, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 70, 0, input);

                throw nvae;
            }
            switch (alt70) {
                case 1 :
                    // InternalRequirementDSL.g:3103:3: ( (lv_set_0_0= ruleObjectSet ) )
                    {
                    // InternalRequirementDSL.g:3103:3: ( (lv_set_0_0= ruleObjectSet ) )
                    // InternalRequirementDSL.g:3104:4: (lv_set_0_0= ruleObjectSet )
                    {
                    // InternalRequirementDSL.g:3104:4: (lv_set_0_0= ruleObjectSet )
                    // InternalRequirementDSL.g:3105:5: lv_set_0_0= ruleObjectSet
                    {

                    					newCompositeNode(grammarAccess.getSetConstraintAccess().getSetObjectSetParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_set_0_0=ruleObjectSet();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getSetConstraintRule());
                    					}
                    					set(
                    						current,
                    						"set",
                    						lv_set_0_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.ObjectSet");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:3123:3: ( (lv_set_1_0= ruleValueSet ) )
                    {
                    // InternalRequirementDSL.g:3123:3: ( (lv_set_1_0= ruleValueSet ) )
                    // InternalRequirementDSL.g:3124:4: (lv_set_1_0= ruleValueSet )
                    {
                    // InternalRequirementDSL.g:3124:4: (lv_set_1_0= ruleValueSet )
                    // InternalRequirementDSL.g:3125:5: lv_set_1_0= ruleValueSet
                    {

                    					newCompositeNode(grammarAccess.getSetConstraintAccess().getSetValueSetParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_set_1_0=ruleValueSet();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getSetConstraintRule());
                    					}
                    					set(
                    						current,
                    						"set",
                    						lv_set_1_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.ValueSet");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSetConstraint"


    // $ANTLR start "entryRuleTimeConstraint"
    // InternalRequirementDSL.g:3146:1: entryRuleTimeConstraint returns [EObject current=null] : iv_ruleTimeConstraint= ruleTimeConstraint EOF ;
    public final EObject entryRuleTimeConstraint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTimeConstraint = null;


        try {
            // InternalRequirementDSL.g:3146:55: (iv_ruleTimeConstraint= ruleTimeConstraint EOF )
            // InternalRequirementDSL.g:3147:2: iv_ruleTimeConstraint= ruleTimeConstraint EOF
            {
             newCompositeNode(grammarAccess.getTimeConstraintRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTimeConstraint=ruleTimeConstraint();

            state._fsp--;

             current =iv_ruleTimeConstraint; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTimeConstraint"


    // $ANTLR start "ruleTimeConstraint"
    // InternalRequirementDSL.g:3153:1: ruleTimeConstraint returns [EObject current=null] : ( ( (lv_ordinator_0_0= ruleConstraintOrdinators ) ) ( (lv_time_1_0= RULE_INT ) ) ( (lv_unit_2_0= ruleTimeUnits ) ) ) ;
    public final EObject ruleTimeConstraint() throws RecognitionException {
        EObject current = null;

        Token lv_time_1_0=null;
        EObject lv_ordinator_0_0 = null;

        AntlrDatatypeRuleToken lv_unit_2_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:3159:2: ( ( ( (lv_ordinator_0_0= ruleConstraintOrdinators ) ) ( (lv_time_1_0= RULE_INT ) ) ( (lv_unit_2_0= ruleTimeUnits ) ) ) )
            // InternalRequirementDSL.g:3160:2: ( ( (lv_ordinator_0_0= ruleConstraintOrdinators ) ) ( (lv_time_1_0= RULE_INT ) ) ( (lv_unit_2_0= ruleTimeUnits ) ) )
            {
            // InternalRequirementDSL.g:3160:2: ( ( (lv_ordinator_0_0= ruleConstraintOrdinators ) ) ( (lv_time_1_0= RULE_INT ) ) ( (lv_unit_2_0= ruleTimeUnits ) ) )
            // InternalRequirementDSL.g:3161:3: ( (lv_ordinator_0_0= ruleConstraintOrdinators ) ) ( (lv_time_1_0= RULE_INT ) ) ( (lv_unit_2_0= ruleTimeUnits ) )
            {
            // InternalRequirementDSL.g:3161:3: ( (lv_ordinator_0_0= ruleConstraintOrdinators ) )
            // InternalRequirementDSL.g:3162:4: (lv_ordinator_0_0= ruleConstraintOrdinators )
            {
            // InternalRequirementDSL.g:3162:4: (lv_ordinator_0_0= ruleConstraintOrdinators )
            // InternalRequirementDSL.g:3163:5: lv_ordinator_0_0= ruleConstraintOrdinators
            {

            					newCompositeNode(grammarAccess.getTimeConstraintAccess().getOrdinatorConstraintOrdinatorsParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_40);
            lv_ordinator_0_0=ruleConstraintOrdinators();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTimeConstraintRule());
            					}
            					set(
            						current,
            						"ordinator",
            						lv_ordinator_0_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.ConstraintOrdinators");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:3180:3: ( (lv_time_1_0= RULE_INT ) )
            // InternalRequirementDSL.g:3181:4: (lv_time_1_0= RULE_INT )
            {
            // InternalRequirementDSL.g:3181:4: (lv_time_1_0= RULE_INT )
            // InternalRequirementDSL.g:3182:5: lv_time_1_0= RULE_INT
            {
            lv_time_1_0=(Token)match(input,RULE_INT,FOLLOW_41); 

            					newLeafNode(lv_time_1_0, grammarAccess.getTimeConstraintAccess().getTimeINTTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTimeConstraintRule());
            					}
            					setWithLastConsumed(
            						current,
            						"time",
            						lv_time_1_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            // InternalRequirementDSL.g:3198:3: ( (lv_unit_2_0= ruleTimeUnits ) )
            // InternalRequirementDSL.g:3199:4: (lv_unit_2_0= ruleTimeUnits )
            {
            // InternalRequirementDSL.g:3199:4: (lv_unit_2_0= ruleTimeUnits )
            // InternalRequirementDSL.g:3200:5: lv_unit_2_0= ruleTimeUnits
            {

            					newCompositeNode(grammarAccess.getTimeConstraintAccess().getUnitTimeUnitsParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_unit_2_0=ruleTimeUnits();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTimeConstraintRule());
            					}
            					set(
            						current,
            						"unit",
            						lv_unit_2_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.TimeUnits");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTimeConstraint"


    // $ANTLR start "entryRuleObjectConstraint"
    // InternalRequirementDSL.g:3221:1: entryRuleObjectConstraint returns [EObject current=null] : iv_ruleObjectConstraint= ruleObjectConstraint EOF ;
    public final EObject entryRuleObjectConstraint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleObjectConstraint = null;


        try {
            // InternalRequirementDSL.g:3221:57: (iv_ruleObjectConstraint= ruleObjectConstraint EOF )
            // InternalRequirementDSL.g:3222:2: iv_ruleObjectConstraint= ruleObjectConstraint EOF
            {
             newCompositeNode(grammarAccess.getObjectConstraintRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleObjectConstraint=ruleObjectConstraint();

            state._fsp--;

             current =iv_ruleObjectConstraint; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleObjectConstraint"


    // $ANTLR start "ruleObjectConstraint"
    // InternalRequirementDSL.g:3228:1: ruleObjectConstraint returns [EObject current=null] : ( (lv_object_0_0= ruleObject ) ) ;
    public final EObject ruleObjectConstraint() throws RecognitionException {
        EObject current = null;

        EObject lv_object_0_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:3234:2: ( ( (lv_object_0_0= ruleObject ) ) )
            // InternalRequirementDSL.g:3235:2: ( (lv_object_0_0= ruleObject ) )
            {
            // InternalRequirementDSL.g:3235:2: ( (lv_object_0_0= ruleObject ) )
            // InternalRequirementDSL.g:3236:3: (lv_object_0_0= ruleObject )
            {
            // InternalRequirementDSL.g:3236:3: (lv_object_0_0= ruleObject )
            // InternalRequirementDSL.g:3237:4: lv_object_0_0= ruleObject
            {

            				newCompositeNode(grammarAccess.getObjectConstraintAccess().getObjectObjectParserRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_object_0_0=ruleObject();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getObjectConstraintRule());
            				}
            				set(
            					current,
            					"object",
            					lv_object_0_0,
            					"de.fraunhofer.isst.stars.RequirementDSL.Object");
            				afterParserOrEnumRuleCall();
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleObjectConstraint"


    // $ANTLR start "entryRuleUnitConstraints"
    // InternalRequirementDSL.g:3257:1: entryRuleUnitConstraints returns [EObject current=null] : iv_ruleUnitConstraints= ruleUnitConstraints EOF ;
    public final EObject entryRuleUnitConstraints() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnitConstraints = null;


        try {
            // InternalRequirementDSL.g:3257:56: (iv_ruleUnitConstraints= ruleUnitConstraints EOF )
            // InternalRequirementDSL.g:3258:2: iv_ruleUnitConstraints= ruleUnitConstraints EOF
            {
             newCompositeNode(grammarAccess.getUnitConstraintsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUnitConstraints=ruleUnitConstraints();

            state._fsp--;

             current =iv_ruleUnitConstraints; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnitConstraints"


    // $ANTLR start "ruleUnitConstraints"
    // InternalRequirementDSL.g:3264:1: ruleUnitConstraints returns [EObject current=null] : (this_SingleValueConstraints_0= ruleSingleValueConstraints | this_IntervallConstraints_1= ruleIntervallConstraints ) ;
    public final EObject ruleUnitConstraints() throws RecognitionException {
        EObject current = null;

        EObject this_SingleValueConstraints_0 = null;

        EObject this_IntervallConstraints_1 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:3270:2: ( (this_SingleValueConstraints_0= ruleSingleValueConstraints | this_IntervallConstraints_1= ruleIntervallConstraints ) )
            // InternalRequirementDSL.g:3271:2: (this_SingleValueConstraints_0= ruleSingleValueConstraints | this_IntervallConstraints_1= ruleIntervallConstraints )
            {
            // InternalRequirementDSL.g:3271:2: (this_SingleValueConstraints_0= ruleSingleValueConstraints | this_IntervallConstraints_1= ruleIntervallConstraints )
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==RULE_INT) ) {
                alt71=1;
            }
            else if ( (LA71_0==23) ) {
                alt71=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;
            }
            switch (alt71) {
                case 1 :
                    // InternalRequirementDSL.g:3272:3: this_SingleValueConstraints_0= ruleSingleValueConstraints
                    {

                    			newCompositeNode(grammarAccess.getUnitConstraintsAccess().getSingleValueConstraintsParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_SingleValueConstraints_0=ruleSingleValueConstraints();

                    state._fsp--;


                    			current = this_SingleValueConstraints_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:3281:3: this_IntervallConstraints_1= ruleIntervallConstraints
                    {

                    			newCompositeNode(grammarAccess.getUnitConstraintsAccess().getIntervallConstraintsParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_IntervallConstraints_1=ruleIntervallConstraints();

                    state._fsp--;


                    			current = this_IntervallConstraints_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnitConstraints"


    // $ANTLR start "entryRuleIntervallConstraints"
    // InternalRequirementDSL.g:3293:1: entryRuleIntervallConstraints returns [EObject current=null] : iv_ruleIntervallConstraints= ruleIntervallConstraints EOF ;
    public final EObject entryRuleIntervallConstraints() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntervallConstraints = null;


        try {
            // InternalRequirementDSL.g:3293:61: (iv_ruleIntervallConstraints= ruleIntervallConstraints EOF )
            // InternalRequirementDSL.g:3294:2: iv_ruleIntervallConstraints= ruleIntervallConstraints EOF
            {
             newCompositeNode(grammarAccess.getIntervallConstraintsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIntervallConstraints=ruleIntervallConstraints();

            state._fsp--;

             current =iv_ruleIntervallConstraints; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIntervallConstraints"


    // $ANTLR start "ruleIntervallConstraints"
    // InternalRequirementDSL.g:3300:1: ruleIntervallConstraints returns [EObject current=null] : (otherlv_0= '[' ( (lv_lower_1_0= ruleValue ) ) otherlv_2= ',' ( (lv_higher_3_0= ruleValue ) ) otherlv_4= ']' ) ;
    public final EObject ruleIntervallConstraints() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_lower_1_0 = null;

        EObject lv_higher_3_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:3306:2: ( (otherlv_0= '[' ( (lv_lower_1_0= ruleValue ) ) otherlv_2= ',' ( (lv_higher_3_0= ruleValue ) ) otherlv_4= ']' ) )
            // InternalRequirementDSL.g:3307:2: (otherlv_0= '[' ( (lv_lower_1_0= ruleValue ) ) otherlv_2= ',' ( (lv_higher_3_0= ruleValue ) ) otherlv_4= ']' )
            {
            // InternalRequirementDSL.g:3307:2: (otherlv_0= '[' ( (lv_lower_1_0= ruleValue ) ) otherlv_2= ',' ( (lv_higher_3_0= ruleValue ) ) otherlv_4= ']' )
            // InternalRequirementDSL.g:3308:3: otherlv_0= '[' ( (lv_lower_1_0= ruleValue ) ) otherlv_2= ',' ( (lv_higher_3_0= ruleValue ) ) otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,23,FOLLOW_40); 

            			newLeafNode(otherlv_0, grammarAccess.getIntervallConstraintsAccess().getLeftSquareBracketKeyword_0());
            		
            // InternalRequirementDSL.g:3312:3: ( (lv_lower_1_0= ruleValue ) )
            // InternalRequirementDSL.g:3313:4: (lv_lower_1_0= ruleValue )
            {
            // InternalRequirementDSL.g:3313:4: (lv_lower_1_0= ruleValue )
            // InternalRequirementDSL.g:3314:5: lv_lower_1_0= ruleValue
            {

            					newCompositeNode(grammarAccess.getIntervallConstraintsAccess().getLowerValueParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_20);
            lv_lower_1_0=ruleValue();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getIntervallConstraintsRule());
            					}
            					set(
            						current,
            						"lower",
            						lv_lower_1_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.Value");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,17,FOLLOW_40); 

            			newLeafNode(otherlv_2, grammarAccess.getIntervallConstraintsAccess().getCommaKeyword_2());
            		
            // InternalRequirementDSL.g:3335:3: ( (lv_higher_3_0= ruleValue ) )
            // InternalRequirementDSL.g:3336:4: (lv_higher_3_0= ruleValue )
            {
            // InternalRequirementDSL.g:3336:4: (lv_higher_3_0= ruleValue )
            // InternalRequirementDSL.g:3337:5: lv_higher_3_0= ruleValue
            {

            					newCompositeNode(grammarAccess.getIntervallConstraintsAccess().getHigherValueParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_42);
            lv_higher_3_0=ruleValue();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getIntervallConstraintsRule());
            					}
            					set(
            						current,
            						"higher",
            						lv_higher_3_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.Value");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,24,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getIntervallConstraintsAccess().getRightSquareBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIntervallConstraints"


    // $ANTLR start "entryRuleSingleValueConstraints"
    // InternalRequirementDSL.g:3362:1: entryRuleSingleValueConstraints returns [EObject current=null] : iv_ruleSingleValueConstraints= ruleSingleValueConstraints EOF ;
    public final EObject entryRuleSingleValueConstraints() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSingleValueConstraints = null;


        try {
            // InternalRequirementDSL.g:3362:63: (iv_ruleSingleValueConstraints= ruleSingleValueConstraints EOF )
            // InternalRequirementDSL.g:3363:2: iv_ruleSingleValueConstraints= ruleSingleValueConstraints EOF
            {
             newCompositeNode(grammarAccess.getSingleValueConstraintsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSingleValueConstraints=ruleSingleValueConstraints();

            state._fsp--;

             current =iv_ruleSingleValueConstraints; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSingleValueConstraints"


    // $ANTLR start "ruleSingleValueConstraints"
    // InternalRequirementDSL.g:3369:1: ruleSingleValueConstraints returns [EObject current=null] : ( (lv_value_0_0= ruleValue ) ) ;
    public final EObject ruleSingleValueConstraints() throws RecognitionException {
        EObject current = null;

        EObject lv_value_0_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:3375:2: ( ( (lv_value_0_0= ruleValue ) ) )
            // InternalRequirementDSL.g:3376:2: ( (lv_value_0_0= ruleValue ) )
            {
            // InternalRequirementDSL.g:3376:2: ( (lv_value_0_0= ruleValue ) )
            // InternalRequirementDSL.g:3377:3: (lv_value_0_0= ruleValue )
            {
            // InternalRequirementDSL.g:3377:3: (lv_value_0_0= ruleValue )
            // InternalRequirementDSL.g:3378:4: lv_value_0_0= ruleValue
            {

            				newCompositeNode(grammarAccess.getSingleValueConstraintsAccess().getValueValueParserRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_value_0_0=ruleValue();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getSingleValueConstraintsRule());
            				}
            				set(
            					current,
            					"value",
            					lv_value_0_0,
            					"de.fraunhofer.isst.stars.RequirementDSL.Value");
            				afterParserOrEnumRuleCall();
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSingleValueConstraints"


    // $ANTLR start "entryRuleValueSet"
    // InternalRequirementDSL.g:3398:1: entryRuleValueSet returns [EObject current=null] : iv_ruleValueSet= ruleValueSet EOF ;
    public final EObject entryRuleValueSet() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValueSet = null;


        try {
            // InternalRequirementDSL.g:3398:49: (iv_ruleValueSet= ruleValueSet EOF )
            // InternalRequirementDSL.g:3399:2: iv_ruleValueSet= ruleValueSet EOF
            {
             newCompositeNode(grammarAccess.getValueSetRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleValueSet=ruleValueSet();

            state._fsp--;

             current =iv_ruleValueSet; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleValueSet"


    // $ANTLR start "ruleValueSet"
    // InternalRequirementDSL.g:3405:1: ruleValueSet returns [EObject current=null] : (otherlv_0= '{' ( (lv_elements_1_0= ruleValue ) ) (otherlv_2= ';' ( (lv_elements_3_0= ruleValue ) ) )* otherlv_4= '}' ) ;
    public final EObject ruleValueSet() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_elements_1_0 = null;

        EObject lv_elements_3_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:3411:2: ( (otherlv_0= '{' ( (lv_elements_1_0= ruleValue ) ) (otherlv_2= ';' ( (lv_elements_3_0= ruleValue ) ) )* otherlv_4= '}' ) )
            // InternalRequirementDSL.g:3412:2: (otherlv_0= '{' ( (lv_elements_1_0= ruleValue ) ) (otherlv_2= ';' ( (lv_elements_3_0= ruleValue ) ) )* otherlv_4= '}' )
            {
            // InternalRequirementDSL.g:3412:2: (otherlv_0= '{' ( (lv_elements_1_0= ruleValue ) ) (otherlv_2= ';' ( (lv_elements_3_0= ruleValue ) ) )* otherlv_4= '}' )
            // InternalRequirementDSL.g:3413:3: otherlv_0= '{' ( (lv_elements_1_0= ruleValue ) ) (otherlv_2= ';' ( (lv_elements_3_0= ruleValue ) ) )* otherlv_4= '}'
            {
            otherlv_0=(Token)match(input,25,FOLLOW_40); 

            			newLeafNode(otherlv_0, grammarAccess.getValueSetAccess().getLeftCurlyBracketKeyword_0());
            		
            // InternalRequirementDSL.g:3417:3: ( (lv_elements_1_0= ruleValue ) )
            // InternalRequirementDSL.g:3418:4: (lv_elements_1_0= ruleValue )
            {
            // InternalRequirementDSL.g:3418:4: (lv_elements_1_0= ruleValue )
            // InternalRequirementDSL.g:3419:5: lv_elements_1_0= ruleValue
            {

            					newCompositeNode(grammarAccess.getValueSetAccess().getElementsValueParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_43);
            lv_elements_1_0=ruleValue();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getValueSetRule());
            					}
            					add(
            						current,
            						"elements",
            						lv_elements_1_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.Value");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:3436:3: (otherlv_2= ';' ( (lv_elements_3_0= ruleValue ) ) )*
            loop72:
            do {
                int alt72=2;
                int LA72_0 = input.LA(1);

                if ( (LA72_0==16) ) {
                    alt72=1;
                }


                switch (alt72) {
            	case 1 :
            	    // InternalRequirementDSL.g:3437:4: otherlv_2= ';' ( (lv_elements_3_0= ruleValue ) )
            	    {
            	    otherlv_2=(Token)match(input,16,FOLLOW_40); 

            	    				newLeafNode(otherlv_2, grammarAccess.getValueSetAccess().getSemicolonKeyword_2_0());
            	    			
            	    // InternalRequirementDSL.g:3441:4: ( (lv_elements_3_0= ruleValue ) )
            	    // InternalRequirementDSL.g:3442:5: (lv_elements_3_0= ruleValue )
            	    {
            	    // InternalRequirementDSL.g:3442:5: (lv_elements_3_0= ruleValue )
            	    // InternalRequirementDSL.g:3443:6: lv_elements_3_0= ruleValue
            	    {

            	    						newCompositeNode(grammarAccess.getValueSetAccess().getElementsValueParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_43);
            	    lv_elements_3_0=ruleValue();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getValueSetRule());
            	    						}
            	    						add(
            	    							current,
            	    							"elements",
            	    							lv_elements_3_0,
            	    							"de.fraunhofer.isst.stars.RequirementDSL.Value");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop72;
                }
            } while (true);

            otherlv_4=(Token)match(input,26,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getValueSetAccess().getRightCurlyBracketKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValueSet"


    // $ANTLR start "entryRuleObjectSet"
    // InternalRequirementDSL.g:3469:1: entryRuleObjectSet returns [EObject current=null] : iv_ruleObjectSet= ruleObjectSet EOF ;
    public final EObject entryRuleObjectSet() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleObjectSet = null;


        try {
            // InternalRequirementDSL.g:3469:50: (iv_ruleObjectSet= ruleObjectSet EOF )
            // InternalRequirementDSL.g:3470:2: iv_ruleObjectSet= ruleObjectSet EOF
            {
             newCompositeNode(grammarAccess.getObjectSetRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleObjectSet=ruleObjectSet();

            state._fsp--;

             current =iv_ruleObjectSet; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleObjectSet"


    // $ANTLR start "ruleObjectSet"
    // InternalRequirementDSL.g:3476:1: ruleObjectSet returns [EObject current=null] : (otherlv_0= '{' ( (lv_elements_1_0= ruleActor ) ) (otherlv_2= ';' ( (lv_elements_3_0= ruleActor ) ) )* otherlv_4= '}' ) ;
    public final EObject ruleObjectSet() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_elements_1_0 = null;

        EObject lv_elements_3_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:3482:2: ( (otherlv_0= '{' ( (lv_elements_1_0= ruleActor ) ) (otherlv_2= ';' ( (lv_elements_3_0= ruleActor ) ) )* otherlv_4= '}' ) )
            // InternalRequirementDSL.g:3483:2: (otherlv_0= '{' ( (lv_elements_1_0= ruleActor ) ) (otherlv_2= ';' ( (lv_elements_3_0= ruleActor ) ) )* otherlv_4= '}' )
            {
            // InternalRequirementDSL.g:3483:2: (otherlv_0= '{' ( (lv_elements_1_0= ruleActor ) ) (otherlv_2= ';' ( (lv_elements_3_0= ruleActor ) ) )* otherlv_4= '}' )
            // InternalRequirementDSL.g:3484:3: otherlv_0= '{' ( (lv_elements_1_0= ruleActor ) ) (otherlv_2= ';' ( (lv_elements_3_0= ruleActor ) ) )* otherlv_4= '}'
            {
            otherlv_0=(Token)match(input,25,FOLLOW_13); 

            			newLeafNode(otherlv_0, grammarAccess.getObjectSetAccess().getLeftCurlyBracketKeyword_0());
            		
            // InternalRequirementDSL.g:3488:3: ( (lv_elements_1_0= ruleActor ) )
            // InternalRequirementDSL.g:3489:4: (lv_elements_1_0= ruleActor )
            {
            // InternalRequirementDSL.g:3489:4: (lv_elements_1_0= ruleActor )
            // InternalRequirementDSL.g:3490:5: lv_elements_1_0= ruleActor
            {

            					newCompositeNode(grammarAccess.getObjectSetAccess().getElementsActorParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_43);
            lv_elements_1_0=ruleActor();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getObjectSetRule());
            					}
            					add(
            						current,
            						"elements",
            						lv_elements_1_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.Actor");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:3507:3: (otherlv_2= ';' ( (lv_elements_3_0= ruleActor ) ) )*
            loop73:
            do {
                int alt73=2;
                int LA73_0 = input.LA(1);

                if ( (LA73_0==16) ) {
                    alt73=1;
                }


                switch (alt73) {
            	case 1 :
            	    // InternalRequirementDSL.g:3508:4: otherlv_2= ';' ( (lv_elements_3_0= ruleActor ) )
            	    {
            	    otherlv_2=(Token)match(input,16,FOLLOW_13); 

            	    				newLeafNode(otherlv_2, grammarAccess.getObjectSetAccess().getSemicolonKeyword_2_0());
            	    			
            	    // InternalRequirementDSL.g:3512:4: ( (lv_elements_3_0= ruleActor ) )
            	    // InternalRequirementDSL.g:3513:5: (lv_elements_3_0= ruleActor )
            	    {
            	    // InternalRequirementDSL.g:3513:5: (lv_elements_3_0= ruleActor )
            	    // InternalRequirementDSL.g:3514:6: lv_elements_3_0= ruleActor
            	    {

            	    						newCompositeNode(grammarAccess.getObjectSetAccess().getElementsActorParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_43);
            	    lv_elements_3_0=ruleActor();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getObjectSetRule());
            	    						}
            	    						add(
            	    							current,
            	    							"elements",
            	    							lv_elements_3_0,
            	    							"de.fraunhofer.isst.stars.RequirementDSL.Actor");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop73;
                }
            } while (true);

            otherlv_4=(Token)match(input,26,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getObjectSetAccess().getRightCurlyBracketKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleObjectSet"


    // $ANTLR start "entryRuleValue"
    // InternalRequirementDSL.g:3540:1: entryRuleValue returns [EObject current=null] : iv_ruleValue= ruleValue EOF ;
    public final EObject entryRuleValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValue = null;


        try {
            // InternalRequirementDSL.g:3540:46: (iv_ruleValue= ruleValue EOF )
            // InternalRequirementDSL.g:3541:2: iv_ruleValue= ruleValue EOF
            {
             newCompositeNode(grammarAccess.getValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleValue=ruleValue();

            state._fsp--;

             current =iv_ruleValue; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleValue"


    // $ANTLR start "ruleValue"
    // InternalRequirementDSL.g:3547:1: ruleValue returns [EObject current=null] : (this_IntValue_0= ruleIntValue | this_FloatValue_1= ruleFloatValue ) ;
    public final EObject ruleValue() throws RecognitionException {
        EObject current = null;

        EObject this_IntValue_0 = null;

        EObject this_FloatValue_1 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:3553:2: ( (this_IntValue_0= ruleIntValue | this_FloatValue_1= ruleFloatValue ) )
            // InternalRequirementDSL.g:3554:2: (this_IntValue_0= ruleIntValue | this_FloatValue_1= ruleFloatValue )
            {
            // InternalRequirementDSL.g:3554:2: (this_IntValue_0= ruleIntValue | this_FloatValue_1= ruleFloatValue )
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==RULE_INT) ) {
                int LA74_1 = input.LA(2);

                if ( (LA74_1==EOF||LA74_1==RULE_ID||(LA74_1>=16 && LA74_1<=18)||LA74_1==24||LA74_1==26||(LA74_1>=37 && LA74_1<=38)||LA74_1==43||(LA74_1>=45 && LA74_1<=66)||LA74_1==96||(LA74_1>=101 && LA74_1<=123)||(LA74_1>=160 && LA74_1<=175)) ) {
                    alt74=1;
                }
                else if ( (LA74_1==15) ) {
                    int LA74_3 = input.LA(3);

                    if ( (LA74_3==RULE_INT) ) {
                        alt74=2;
                    }
                    else if ( (LA74_3==EOF||LA74_3==13) ) {
                        alt74=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 74, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 74, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 74, 0, input);

                throw nvae;
            }
            switch (alt74) {
                case 1 :
                    // InternalRequirementDSL.g:3555:3: this_IntValue_0= ruleIntValue
                    {

                    			newCompositeNode(grammarAccess.getValueAccess().getIntValueParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_IntValue_0=ruleIntValue();

                    state._fsp--;


                    			current = this_IntValue_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:3564:3: this_FloatValue_1= ruleFloatValue
                    {

                    			newCompositeNode(grammarAccess.getValueAccess().getFloatValueParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_FloatValue_1=ruleFloatValue();

                    state._fsp--;


                    			current = this_FloatValue_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleValue"


    // $ANTLR start "entryRuleIntValue"
    // InternalRequirementDSL.g:3576:1: entryRuleIntValue returns [EObject current=null] : iv_ruleIntValue= ruleIntValue EOF ;
    public final EObject entryRuleIntValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntValue = null;


        try {
            // InternalRequirementDSL.g:3576:49: (iv_ruleIntValue= ruleIntValue EOF )
            // InternalRequirementDSL.g:3577:2: iv_ruleIntValue= ruleIntValue EOF
            {
             newCompositeNode(grammarAccess.getIntValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIntValue=ruleIntValue();

            state._fsp--;

             current =iv_ruleIntValue; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIntValue"


    // $ANTLR start "ruleIntValue"
    // InternalRequirementDSL.g:3583:1: ruleIntValue returns [EObject current=null] : ( ( (lv_value_0_0= RULE_INT ) ) ( (lv_unit_1_0= ruleUnit ) )? ( (lv_object_2_0= ruleWORD ) )* ) ;
    public final EObject ruleIntValue() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;
        AntlrDatatypeRuleToken lv_unit_1_0 = null;

        AntlrDatatypeRuleToken lv_object_2_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:3589:2: ( ( ( (lv_value_0_0= RULE_INT ) ) ( (lv_unit_1_0= ruleUnit ) )? ( (lv_object_2_0= ruleWORD ) )* ) )
            // InternalRequirementDSL.g:3590:2: ( ( (lv_value_0_0= RULE_INT ) ) ( (lv_unit_1_0= ruleUnit ) )? ( (lv_object_2_0= ruleWORD ) )* )
            {
            // InternalRequirementDSL.g:3590:2: ( ( (lv_value_0_0= RULE_INT ) ) ( (lv_unit_1_0= ruleUnit ) )? ( (lv_object_2_0= ruleWORD ) )* )
            // InternalRequirementDSL.g:3591:3: ( (lv_value_0_0= RULE_INT ) ) ( (lv_unit_1_0= ruleUnit ) )? ( (lv_object_2_0= ruleWORD ) )*
            {
            // InternalRequirementDSL.g:3591:3: ( (lv_value_0_0= RULE_INT ) )
            // InternalRequirementDSL.g:3592:4: (lv_value_0_0= RULE_INT )
            {
            // InternalRequirementDSL.g:3592:4: (lv_value_0_0= RULE_INT )
            // InternalRequirementDSL.g:3593:5: lv_value_0_0= RULE_INT
            {
            lv_value_0_0=(Token)match(input,RULE_INT,FOLLOW_44); 

            					newLeafNode(lv_value_0_0, grammarAccess.getIntValueAccess().getValueINTTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getIntValueRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_0_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            // InternalRequirementDSL.g:3609:3: ( (lv_unit_1_0= ruleUnit ) )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( ((LA75_0>=101 && LA75_0<=123)) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // InternalRequirementDSL.g:3610:4: (lv_unit_1_0= ruleUnit )
                    {
                    // InternalRequirementDSL.g:3610:4: (lv_unit_1_0= ruleUnit )
                    // InternalRequirementDSL.g:3611:5: lv_unit_1_0= ruleUnit
                    {

                    					newCompositeNode(grammarAccess.getIntValueAccess().getUnitUnitParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_27);
                    lv_unit_1_0=ruleUnit();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getIntValueRule());
                    					}
                    					set(
                    						current,
                    						"unit",
                    						lv_unit_1_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.Unit");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalRequirementDSL.g:3628:3: ( (lv_object_2_0= ruleWORD ) )*
            loop76:
            do {
                int alt76=2;
                int LA76_0 = input.LA(1);

                if ( (LA76_0==RULE_ID) ) {
                    alt76=1;
                }


                switch (alt76) {
            	case 1 :
            	    // InternalRequirementDSL.g:3629:4: (lv_object_2_0= ruleWORD )
            	    {
            	    // InternalRequirementDSL.g:3629:4: (lv_object_2_0= ruleWORD )
            	    // InternalRequirementDSL.g:3630:5: lv_object_2_0= ruleWORD
            	    {

            	    					newCompositeNode(grammarAccess.getIntValueAccess().getObjectWORDParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_27);
            	    lv_object_2_0=ruleWORD();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getIntValueRule());
            	    					}
            	    					add(
            	    						current,
            	    						"object",
            	    						lv_object_2_0,
            	    						"de.fraunhofer.isst.stars.RequirementDSL.WORD");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop76;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIntValue"


    // $ANTLR start "entryRuleFloatValue"
    // InternalRequirementDSL.g:3651:1: entryRuleFloatValue returns [EObject current=null] : iv_ruleFloatValue= ruleFloatValue EOF ;
    public final EObject entryRuleFloatValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFloatValue = null;


        try {
            // InternalRequirementDSL.g:3651:51: (iv_ruleFloatValue= ruleFloatValue EOF )
            // InternalRequirementDSL.g:3652:2: iv_ruleFloatValue= ruleFloatValue EOF
            {
             newCompositeNode(grammarAccess.getFloatValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFloatValue=ruleFloatValue();

            state._fsp--;

             current =iv_ruleFloatValue; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFloatValue"


    // $ANTLR start "ruleFloatValue"
    // InternalRequirementDSL.g:3658:1: ruleFloatValue returns [EObject current=null] : ( ( (lv_value_0_0= ruleFLOAT ) ) ( (lv_unit_1_0= ruleUnit ) )? ( (lv_object_2_0= ruleWORD ) )* ) ;
    public final EObject ruleFloatValue() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_value_0_0 = null;

        AntlrDatatypeRuleToken lv_unit_1_0 = null;

        AntlrDatatypeRuleToken lv_object_2_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:3664:2: ( ( ( (lv_value_0_0= ruleFLOAT ) ) ( (lv_unit_1_0= ruleUnit ) )? ( (lv_object_2_0= ruleWORD ) )* ) )
            // InternalRequirementDSL.g:3665:2: ( ( (lv_value_0_0= ruleFLOAT ) ) ( (lv_unit_1_0= ruleUnit ) )? ( (lv_object_2_0= ruleWORD ) )* )
            {
            // InternalRequirementDSL.g:3665:2: ( ( (lv_value_0_0= ruleFLOAT ) ) ( (lv_unit_1_0= ruleUnit ) )? ( (lv_object_2_0= ruleWORD ) )* )
            // InternalRequirementDSL.g:3666:3: ( (lv_value_0_0= ruleFLOAT ) ) ( (lv_unit_1_0= ruleUnit ) )? ( (lv_object_2_0= ruleWORD ) )*
            {
            // InternalRequirementDSL.g:3666:3: ( (lv_value_0_0= ruleFLOAT ) )
            // InternalRequirementDSL.g:3667:4: (lv_value_0_0= ruleFLOAT )
            {
            // InternalRequirementDSL.g:3667:4: (lv_value_0_0= ruleFLOAT )
            // InternalRequirementDSL.g:3668:5: lv_value_0_0= ruleFLOAT
            {

            					newCompositeNode(grammarAccess.getFloatValueAccess().getValueFLOATParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_44);
            lv_value_0_0=ruleFLOAT();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFloatValueRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_0_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.FLOAT");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:3685:3: ( (lv_unit_1_0= ruleUnit ) )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( ((LA77_0>=101 && LA77_0<=123)) ) {
                alt77=1;
            }
            switch (alt77) {
                case 1 :
                    // InternalRequirementDSL.g:3686:4: (lv_unit_1_0= ruleUnit )
                    {
                    // InternalRequirementDSL.g:3686:4: (lv_unit_1_0= ruleUnit )
                    // InternalRequirementDSL.g:3687:5: lv_unit_1_0= ruleUnit
                    {

                    					newCompositeNode(grammarAccess.getFloatValueAccess().getUnitUnitParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_27);
                    lv_unit_1_0=ruleUnit();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getFloatValueRule());
                    					}
                    					set(
                    						current,
                    						"unit",
                    						lv_unit_1_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.Unit");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalRequirementDSL.g:3704:3: ( (lv_object_2_0= ruleWORD ) )*
            loop78:
            do {
                int alt78=2;
                int LA78_0 = input.LA(1);

                if ( (LA78_0==RULE_ID) ) {
                    alt78=1;
                }


                switch (alt78) {
            	case 1 :
            	    // InternalRequirementDSL.g:3705:4: (lv_object_2_0= ruleWORD )
            	    {
            	    // InternalRequirementDSL.g:3705:4: (lv_object_2_0= ruleWORD )
            	    // InternalRequirementDSL.g:3706:5: lv_object_2_0= ruleWORD
            	    {

            	    					newCompositeNode(grammarAccess.getFloatValueAccess().getObjectWORDParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_27);
            	    lv_object_2_0=ruleWORD();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getFloatValueRule());
            	    					}
            	    					add(
            	    						current,
            	    						"object",
            	    						lv_object_2_0,
            	    						"de.fraunhofer.isst.stars.RequirementDSL.WORD");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop78;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFloatValue"


    // $ANTLR start "entryRuleReqID"
    // InternalRequirementDSL.g:3727:1: entryRuleReqID returns [String current=null] : iv_ruleReqID= ruleReqID EOF ;
    public final String entryRuleReqID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleReqID = null;


        try {
            // InternalRequirementDSL.g:3727:45: (iv_ruleReqID= ruleReqID EOF )
            // InternalRequirementDSL.g:3728:2: iv_ruleReqID= ruleReqID EOF
            {
             newCompositeNode(grammarAccess.getReqIDRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleReqID=ruleReqID();

            state._fsp--;

             current =iv_ruleReqID.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleReqID"


    // $ANTLR start "ruleReqID"
    // InternalRequirementDSL.g:3734:1: ruleReqID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (this_ID_0= RULE_ID | this_INT_1= RULE_INT ) (kw= '.' | this_INT_3= RULE_INT )* ) ;
    public final AntlrDatatypeRuleToken ruleReqID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token this_INT_1=null;
        Token kw=null;
        Token this_INT_3=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:3740:2: ( ( (this_ID_0= RULE_ID | this_INT_1= RULE_INT ) (kw= '.' | this_INT_3= RULE_INT )* ) )
            // InternalRequirementDSL.g:3741:2: ( (this_ID_0= RULE_ID | this_INT_1= RULE_INT ) (kw= '.' | this_INT_3= RULE_INT )* )
            {
            // InternalRequirementDSL.g:3741:2: ( (this_ID_0= RULE_ID | this_INT_1= RULE_INT ) (kw= '.' | this_INT_3= RULE_INT )* )
            // InternalRequirementDSL.g:3742:3: (this_ID_0= RULE_ID | this_INT_1= RULE_INT ) (kw= '.' | this_INT_3= RULE_INT )*
            {
            // InternalRequirementDSL.g:3742:3: (this_ID_0= RULE_ID | this_INT_1= RULE_INT )
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==RULE_ID) ) {
                alt79=1;
            }
            else if ( (LA79_0==RULE_INT) ) {
                alt79=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 79, 0, input);

                throw nvae;
            }
            switch (alt79) {
                case 1 :
                    // InternalRequirementDSL.g:3743:4: this_ID_0= RULE_ID
                    {
                    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_45); 

                    				current.merge(this_ID_0);
                    			

                    				newLeafNode(this_ID_0, grammarAccess.getReqIDAccess().getIDTerminalRuleCall_0_0());
                    			

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:3751:4: this_INT_1= RULE_INT
                    {
                    this_INT_1=(Token)match(input,RULE_INT,FOLLOW_45); 

                    				current.merge(this_INT_1);
                    			

                    				newLeafNode(this_INT_1, grammarAccess.getReqIDAccess().getINTTerminalRuleCall_0_1());
                    			

                    }
                    break;

            }

            // InternalRequirementDSL.g:3759:3: (kw= '.' | this_INT_3= RULE_INT )*
            loop80:
            do {
                int alt80=3;
                int LA80_0 = input.LA(1);

                if ( (LA80_0==15) ) {
                    alt80=1;
                }
                else if ( (LA80_0==RULE_INT) ) {
                    alt80=2;
                }


                switch (alt80) {
            	case 1 :
            	    // InternalRequirementDSL.g:3760:4: kw= '.'
            	    {
            	    kw=(Token)match(input,15,FOLLOW_45); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getReqIDAccess().getFullStopKeyword_1_0());
            	    			

            	    }
            	    break;
            	case 2 :
            	    // InternalRequirementDSL.g:3766:4: this_INT_3= RULE_INT
            	    {
            	    this_INT_3=(Token)match(input,RULE_INT,FOLLOW_45); 

            	    				current.merge(this_INT_3);
            	    			

            	    				newLeafNode(this_INT_3, grammarAccess.getReqIDAccess().getINTTerminalRuleCall_1_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop80;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleReqID"


    // $ANTLR start "entryRuleWORD"
    // InternalRequirementDSL.g:3778:1: entryRuleWORD returns [String current=null] : iv_ruleWORD= ruleWORD EOF ;
    public final String entryRuleWORD() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleWORD = null;


        try {
            // InternalRequirementDSL.g:3778:44: (iv_ruleWORD= ruleWORD EOF )
            // InternalRequirementDSL.g:3779:2: iv_ruleWORD= ruleWORD EOF
            {
             newCompositeNode(grammarAccess.getWORDRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleWORD=ruleWORD();

            state._fsp--;

             current =iv_ruleWORD.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWORD"


    // $ANTLR start "ruleWORD"
    // InternalRequirementDSL.g:3785:1: ruleWORD returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '-' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleWORD() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:3791:2: ( (this_ID_0= RULE_ID (kw= '-' this_ID_2= RULE_ID )* ) )
            // InternalRequirementDSL.g:3792:2: (this_ID_0= RULE_ID (kw= '-' this_ID_2= RULE_ID )* )
            {
            // InternalRequirementDSL.g:3792:2: (this_ID_0= RULE_ID (kw= '-' this_ID_2= RULE_ID )* )
            // InternalRequirementDSL.g:3793:3: this_ID_0= RULE_ID (kw= '-' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_46); 

            			current.merge(this_ID_0);
            		

            			newLeafNode(this_ID_0, grammarAccess.getWORDAccess().getIDTerminalRuleCall_0());
            		
            // InternalRequirementDSL.g:3800:3: (kw= '-' this_ID_2= RULE_ID )*
            loop81:
            do {
                int alt81=2;
                int LA81_0 = input.LA(1);

                if ( (LA81_0==27) ) {
                    alt81=1;
                }


                switch (alt81) {
            	case 1 :
            	    // InternalRequirementDSL.g:3801:4: kw= '-' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,27,FOLLOW_47); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getWORDAccess().getHyphenMinusKeyword_1_0());
            	    			
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_46); 

            	    				current.merge(this_ID_2);
            	    			

            	    				newLeafNode(this_ID_2, grammarAccess.getWORDAccess().getIDTerminalRuleCall_1_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop81;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWORD"


    // $ANTLR start "entryRuleRelationDelimiter"
    // InternalRequirementDSL.g:3818:1: entryRuleRelationDelimiter returns [String current=null] : iv_ruleRelationDelimiter= ruleRelationDelimiter EOF ;
    public final String entryRuleRelationDelimiter() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleRelationDelimiter = null;


        try {
            // InternalRequirementDSL.g:3818:57: (iv_ruleRelationDelimiter= ruleRelationDelimiter EOF )
            // InternalRequirementDSL.g:3819:2: iv_ruleRelationDelimiter= ruleRelationDelimiter EOF
            {
             newCompositeNode(grammarAccess.getRelationDelimiterRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRelationDelimiter=ruleRelationDelimiter();

            state._fsp--;

             current =iv_ruleRelationDelimiter.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRelationDelimiter"


    // $ANTLR start "ruleRelationDelimiter"
    // InternalRequirementDSL.g:3825:1: ruleRelationDelimiter returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= 'relation' ;
    public final AntlrDatatypeRuleToken ruleRelationDelimiter() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:3831:2: (kw= 'relation' )
            // InternalRequirementDSL.g:3832:2: kw= 'relation'
            {
            kw=(Token)match(input,28,FOLLOW_2); 

            		current.merge(kw);
            		newLeafNode(kw, grammarAccess.getRelationDelimiterAccess().getRelationKeyword());
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRelationDelimiter"


    // $ANTLR start "entryRuleAuxiliaryVerb"
    // InternalRequirementDSL.g:3840:1: entryRuleAuxiliaryVerb returns [String current=null] : iv_ruleAuxiliaryVerb= ruleAuxiliaryVerb EOF ;
    public final String entryRuleAuxiliaryVerb() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleAuxiliaryVerb = null;


        try {
            // InternalRequirementDSL.g:3840:53: (iv_ruleAuxiliaryVerb= ruleAuxiliaryVerb EOF )
            // InternalRequirementDSL.g:3841:2: iv_ruleAuxiliaryVerb= ruleAuxiliaryVerb EOF
            {
             newCompositeNode(grammarAccess.getAuxiliaryVerbRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAuxiliaryVerb=ruleAuxiliaryVerb();

            state._fsp--;

             current =iv_ruleAuxiliaryVerb.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAuxiliaryVerb"


    // $ANTLR start "ruleAuxiliaryVerb"
    // InternalRequirementDSL.g:3847:1: ruleAuxiliaryVerb returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'is' | kw= 'are' | kw= 'be' | kw= 'been' | kw= 'has' | kw= 'have' | kw= 'do' | kw= 'does' ) ;
    public final AntlrDatatypeRuleToken ruleAuxiliaryVerb() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:3853:2: ( (kw= 'is' | kw= 'are' | kw= 'be' | kw= 'been' | kw= 'has' | kw= 'have' | kw= 'do' | kw= 'does' ) )
            // InternalRequirementDSL.g:3854:2: (kw= 'is' | kw= 'are' | kw= 'be' | kw= 'been' | kw= 'has' | kw= 'have' | kw= 'do' | kw= 'does' )
            {
            // InternalRequirementDSL.g:3854:2: (kw= 'is' | kw= 'are' | kw= 'be' | kw= 'been' | kw= 'has' | kw= 'have' | kw= 'do' | kw= 'does' )
            int alt82=8;
            switch ( input.LA(1) ) {
            case 29:
                {
                alt82=1;
                }
                break;
            case 30:
                {
                alt82=2;
                }
                break;
            case 31:
                {
                alt82=3;
                }
                break;
            case 32:
                {
                alt82=4;
                }
                break;
            case 33:
                {
                alt82=5;
                }
                break;
            case 34:
                {
                alt82=6;
                }
                break;
            case 35:
                {
                alt82=7;
                }
                break;
            case 36:
                {
                alt82=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 82, 0, input);

                throw nvae;
            }

            switch (alt82) {
                case 1 :
                    // InternalRequirementDSL.g:3855:3: kw= 'is'
                    {
                    kw=(Token)match(input,29,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getAuxiliaryVerbAccess().getIsKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:3861:3: kw= 'are'
                    {
                    kw=(Token)match(input,30,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getAuxiliaryVerbAccess().getAreKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:3867:3: kw= 'be'
                    {
                    kw=(Token)match(input,31,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getAuxiliaryVerbAccess().getBeKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:3873:3: kw= 'been'
                    {
                    kw=(Token)match(input,32,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getAuxiliaryVerbAccess().getBeenKeyword_3());
                    		

                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:3879:3: kw= 'has'
                    {
                    kw=(Token)match(input,33,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getAuxiliaryVerbAccess().getHasKeyword_4());
                    		

                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:3885:3: kw= 'have'
                    {
                    kw=(Token)match(input,34,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getAuxiliaryVerbAccess().getHaveKeyword_5());
                    		

                    }
                    break;
                case 7 :
                    // InternalRequirementDSL.g:3891:3: kw= 'do'
                    {
                    kw=(Token)match(input,35,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getAuxiliaryVerbAccess().getDoKeyword_6());
                    		

                    }
                    break;
                case 8 :
                    // InternalRequirementDSL.g:3897:3: kw= 'does'
                    {
                    kw=(Token)match(input,36,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getAuxiliaryVerbAccess().getDoesKeyword_7());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAuxiliaryVerb"


    // $ANTLR start "entryRuleConjunction"
    // InternalRequirementDSL.g:3906:1: entryRuleConjunction returns [String current=null] : iv_ruleConjunction= ruleConjunction EOF ;
    public final String entryRuleConjunction() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleConjunction = null;


        try {
            // InternalRequirementDSL.g:3906:51: (iv_ruleConjunction= ruleConjunction EOF )
            // InternalRequirementDSL.g:3907:2: iv_ruleConjunction= ruleConjunction EOF
            {
             newCompositeNode(grammarAccess.getConjunctionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConjunction=ruleConjunction();

            state._fsp--;

             current =iv_ruleConjunction.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConjunction"


    // $ANTLR start "ruleConjunction"
    // InternalRequirementDSL.g:3913:1: ruleConjunction returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'and' | kw= 'or' ) ;
    public final AntlrDatatypeRuleToken ruleConjunction() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:3919:2: ( (kw= 'and' | kw= 'or' ) )
            // InternalRequirementDSL.g:3920:2: (kw= 'and' | kw= 'or' )
            {
            // InternalRequirementDSL.g:3920:2: (kw= 'and' | kw= 'or' )
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==37) ) {
                alt83=1;
            }
            else if ( (LA83_0==38) ) {
                alt83=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 83, 0, input);

                throw nvae;
            }
            switch (alt83) {
                case 1 :
                    // InternalRequirementDSL.g:3921:3: kw= 'and'
                    {
                    kw=(Token)match(input,37,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getConjunctionAccess().getAndKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:3927:3: kw= 'or'
                    {
                    kw=(Token)match(input,38,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getConjunctionAccess().getOrKeyword_1());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConjunction"


    // $ANTLR start "entryRuleRelConjunction"
    // InternalRequirementDSL.g:3936:1: entryRuleRelConjunction returns [String current=null] : iv_ruleRelConjunction= ruleRelConjunction EOF ;
    public final String entryRuleRelConjunction() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleRelConjunction = null;


        try {
            // InternalRequirementDSL.g:3936:54: (iv_ruleRelConjunction= ruleRelConjunction EOF )
            // InternalRequirementDSL.g:3937:2: iv_ruleRelConjunction= ruleRelConjunction EOF
            {
             newCompositeNode(grammarAccess.getRelConjunctionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRelConjunction=ruleRelConjunction();

            state._fsp--;

             current =iv_ruleRelConjunction.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRelConjunction"


    // $ANTLR start "ruleRelConjunction"
    // InternalRequirementDSL.g:3943:1: ruleRelConjunction returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'and_to' | kw= 'or_to' ) ;
    public final AntlrDatatypeRuleToken ruleRelConjunction() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:3949:2: ( (kw= 'and_to' | kw= 'or_to' ) )
            // InternalRequirementDSL.g:3950:2: (kw= 'and_to' | kw= 'or_to' )
            {
            // InternalRequirementDSL.g:3950:2: (kw= 'and_to' | kw= 'or_to' )
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==39) ) {
                alt84=1;
            }
            else if ( (LA84_0==40) ) {
                alt84=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;
            }
            switch (alt84) {
                case 1 :
                    // InternalRequirementDSL.g:3951:3: kw= 'and_to'
                    {
                    kw=(Token)match(input,39,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRelConjunctionAccess().getAnd_toKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:3957:3: kw= 'or_to'
                    {
                    kw=(Token)match(input,40,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRelConjunctionAccess().getOr_toKeyword_1());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRelConjunction"


    // $ANTLR start "entryRuleComperators"
    // InternalRequirementDSL.g:3966:1: entryRuleComperators returns [String current=null] : iv_ruleComperators= ruleComperators EOF ;
    public final String entryRuleComperators() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleComperators = null;


        try {
            // InternalRequirementDSL.g:3966:51: (iv_ruleComperators= ruleComperators EOF )
            // InternalRequirementDSL.g:3967:2: iv_ruleComperators= ruleComperators EOF
            {
             newCompositeNode(grammarAccess.getComperatorsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleComperators=ruleComperators();

            state._fsp--;

             current =iv_ruleComperators.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleComperators"


    // $ANTLR start "ruleComperators"
    // InternalRequirementDSL.g:3973:1: ruleComperators returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'than' | kw= 'as' | kw= 'to' | kw= 'of' ) ;
    public final AntlrDatatypeRuleToken ruleComperators() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:3979:2: ( (kw= 'than' | kw= 'as' | kw= 'to' | kw= 'of' ) )
            // InternalRequirementDSL.g:3980:2: (kw= 'than' | kw= 'as' | kw= 'to' | kw= 'of' )
            {
            // InternalRequirementDSL.g:3980:2: (kw= 'than' | kw= 'as' | kw= 'to' | kw= 'of' )
            int alt85=4;
            switch ( input.LA(1) ) {
            case 41:
                {
                alt85=1;
                }
                break;
            case 42:
                {
                alt85=2;
                }
                break;
            case 43:
                {
                alt85=3;
                }
                break;
            case 44:
                {
                alt85=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 85, 0, input);

                throw nvae;
            }

            switch (alt85) {
                case 1 :
                    // InternalRequirementDSL.g:3981:3: kw= 'than'
                    {
                    kw=(Token)match(input,41,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getComperatorsAccess().getThanKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:3987:3: kw= 'as'
                    {
                    kw=(Token)match(input,42,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getComperatorsAccess().getAsKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:3993:3: kw= 'to'
                    {
                    kw=(Token)match(input,43,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getComperatorsAccess().getToKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:3999:3: kw= 'of'
                    {
                    kw=(Token)match(input,44,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getComperatorsAccess().getOfKeyword_3());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComperators"


    // $ANTLR start "entryRuleSizeAdverbial"
    // InternalRequirementDSL.g:4008:1: entryRuleSizeAdverbial returns [String current=null] : iv_ruleSizeAdverbial= ruleSizeAdverbial EOF ;
    public final String entryRuleSizeAdverbial() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleSizeAdverbial = null;


        try {
            // InternalRequirementDSL.g:4008:53: (iv_ruleSizeAdverbial= ruleSizeAdverbial EOF )
            // InternalRequirementDSL.g:4009:2: iv_ruleSizeAdverbial= ruleSizeAdverbial EOF
            {
             newCompositeNode(grammarAccess.getSizeAdverbialRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSizeAdverbial=ruleSizeAdverbial();

            state._fsp--;

             current =iv_ruleSizeAdverbial.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSizeAdverbial"


    // $ANTLR start "ruleSizeAdverbial"
    // InternalRequirementDSL.g:4015:1: ruleSizeAdverbial returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'higher' | kw= 'less' | kw= 'more' | kw= 'larger' | kw= 'smaller' | kw= 'as_long_as' ) ;
    public final AntlrDatatypeRuleToken ruleSizeAdverbial() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4021:2: ( (kw= 'higher' | kw= 'less' | kw= 'more' | kw= 'larger' | kw= 'smaller' | kw= 'as_long_as' ) )
            // InternalRequirementDSL.g:4022:2: (kw= 'higher' | kw= 'less' | kw= 'more' | kw= 'larger' | kw= 'smaller' | kw= 'as_long_as' )
            {
            // InternalRequirementDSL.g:4022:2: (kw= 'higher' | kw= 'less' | kw= 'more' | kw= 'larger' | kw= 'smaller' | kw= 'as_long_as' )
            int alt86=6;
            switch ( input.LA(1) ) {
            case 45:
                {
                alt86=1;
                }
                break;
            case 46:
                {
                alt86=2;
                }
                break;
            case 47:
                {
                alt86=3;
                }
                break;
            case 48:
                {
                alt86=4;
                }
                break;
            case 49:
                {
                alt86=5;
                }
                break;
            case 50:
                {
                alt86=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 86, 0, input);

                throw nvae;
            }

            switch (alt86) {
                case 1 :
                    // InternalRequirementDSL.g:4023:3: kw= 'higher'
                    {
                    kw=(Token)match(input,45,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getSizeAdverbialAccess().getHigherKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4029:3: kw= 'less'
                    {
                    kw=(Token)match(input,46,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getSizeAdverbialAccess().getLessKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4035:3: kw= 'more'
                    {
                    kw=(Token)match(input,47,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getSizeAdverbialAccess().getMoreKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4041:3: kw= 'larger'
                    {
                    kw=(Token)match(input,48,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getSizeAdverbialAccess().getLargerKeyword_3());
                    		

                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:4047:3: kw= 'smaller'
                    {
                    kw=(Token)match(input,49,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getSizeAdverbialAccess().getSmallerKeyword_4());
                    		

                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:4053:3: kw= 'as_long_as'
                    {
                    kw=(Token)match(input,50,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getSizeAdverbialAccess().getAs_long_asKeyword_5());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSizeAdverbial"


    // $ANTLR start "entryRulePositionAdverbial"
    // InternalRequirementDSL.g:4062:1: entryRulePositionAdverbial returns [String current=null] : iv_rulePositionAdverbial= rulePositionAdverbial EOF ;
    public final String entryRulePositionAdverbial() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePositionAdverbial = null;


        try {
            // InternalRequirementDSL.g:4062:57: (iv_rulePositionAdverbial= rulePositionAdverbial EOF )
            // InternalRequirementDSL.g:4063:2: iv_rulePositionAdverbial= rulePositionAdverbial EOF
            {
             newCompositeNode(grammarAccess.getPositionAdverbialRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePositionAdverbial=rulePositionAdverbial();

            state._fsp--;

             current =iv_rulePositionAdverbial.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePositionAdverbial"


    // $ANTLR start "rulePositionAdverbial"
    // InternalRequirementDSL.g:4069:1: rulePositionAdverbial returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'between' | kw= 'next' | kw= 'on' | kw= 'above' | kw= 'below' | kw= 'in' | kw= 'within' | kw= 'in_front_of' | kw= 'behind' | kw= 'out' | kw= 'under' ) ;
    public final AntlrDatatypeRuleToken rulePositionAdverbial() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4075:2: ( (kw= 'between' | kw= 'next' | kw= 'on' | kw= 'above' | kw= 'below' | kw= 'in' | kw= 'within' | kw= 'in_front_of' | kw= 'behind' | kw= 'out' | kw= 'under' ) )
            // InternalRequirementDSL.g:4076:2: (kw= 'between' | kw= 'next' | kw= 'on' | kw= 'above' | kw= 'below' | kw= 'in' | kw= 'within' | kw= 'in_front_of' | kw= 'behind' | kw= 'out' | kw= 'under' )
            {
            // InternalRequirementDSL.g:4076:2: (kw= 'between' | kw= 'next' | kw= 'on' | kw= 'above' | kw= 'below' | kw= 'in' | kw= 'within' | kw= 'in_front_of' | kw= 'behind' | kw= 'out' | kw= 'under' )
            int alt87=11;
            switch ( input.LA(1) ) {
            case 51:
                {
                alt87=1;
                }
                break;
            case 52:
                {
                alt87=2;
                }
                break;
            case 53:
                {
                alt87=3;
                }
                break;
            case 54:
                {
                alt87=4;
                }
                break;
            case 55:
                {
                alt87=5;
                }
                break;
            case 56:
                {
                alt87=6;
                }
                break;
            case 57:
                {
                alt87=7;
                }
                break;
            case 58:
                {
                alt87=8;
                }
                break;
            case 59:
                {
                alt87=9;
                }
                break;
            case 60:
                {
                alt87=10;
                }
                break;
            case 61:
                {
                alt87=11;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 87, 0, input);

                throw nvae;
            }

            switch (alt87) {
                case 1 :
                    // InternalRequirementDSL.g:4077:3: kw= 'between'
                    {
                    kw=(Token)match(input,51,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAdverbialAccess().getBetweenKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4083:3: kw= 'next'
                    {
                    kw=(Token)match(input,52,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAdverbialAccess().getNextKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4089:3: kw= 'on'
                    {
                    kw=(Token)match(input,53,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAdverbialAccess().getOnKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4095:3: kw= 'above'
                    {
                    kw=(Token)match(input,54,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAdverbialAccess().getAboveKeyword_3());
                    		

                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:4101:3: kw= 'below'
                    {
                    kw=(Token)match(input,55,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAdverbialAccess().getBelowKeyword_4());
                    		

                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:4107:3: kw= 'in'
                    {
                    kw=(Token)match(input,56,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAdverbialAccess().getInKeyword_5());
                    		

                    }
                    break;
                case 7 :
                    // InternalRequirementDSL.g:4113:3: kw= 'within'
                    {
                    kw=(Token)match(input,57,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAdverbialAccess().getWithinKeyword_6());
                    		

                    }
                    break;
                case 8 :
                    // InternalRequirementDSL.g:4119:3: kw= 'in_front_of'
                    {
                    kw=(Token)match(input,58,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAdverbialAccess().getIn_front_ofKeyword_7());
                    		

                    }
                    break;
                case 9 :
                    // InternalRequirementDSL.g:4125:3: kw= 'behind'
                    {
                    kw=(Token)match(input,59,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAdverbialAccess().getBehindKeyword_8());
                    		

                    }
                    break;
                case 10 :
                    // InternalRequirementDSL.g:4131:3: kw= 'out'
                    {
                    kw=(Token)match(input,60,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAdverbialAccess().getOutKeyword_9());
                    		

                    }
                    break;
                case 11 :
                    // InternalRequirementDSL.g:4137:3: kw= 'under'
                    {
                    kw=(Token)match(input,61,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAdverbialAccess().getUnderKeyword_10());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePositionAdverbial"


    // $ANTLR start "entryRuleComparisonAdverbial"
    // InternalRequirementDSL.g:4146:1: entryRuleComparisonAdverbial returns [String current=null] : iv_ruleComparisonAdverbial= ruleComparisonAdverbial EOF ;
    public final String entryRuleComparisonAdverbial() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleComparisonAdverbial = null;


        try {
            // InternalRequirementDSL.g:4146:59: (iv_ruleComparisonAdverbial= ruleComparisonAdverbial EOF )
            // InternalRequirementDSL.g:4147:2: iv_ruleComparisonAdverbial= ruleComparisonAdverbial EOF
            {
             newCompositeNode(grammarAccess.getComparisonAdverbialRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleComparisonAdverbial=ruleComparisonAdverbial();

            state._fsp--;

             current =iv_ruleComparisonAdverbial.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleComparisonAdverbial"


    // $ANTLR start "ruleComparisonAdverbial"
    // InternalRequirementDSL.g:4153:1: ruleComparisonAdverbial returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'equal' | kw= 'faster' | kw= 'slower' | kw= 'better' | kw= 'by' | kw= 'to' ) ;
    public final AntlrDatatypeRuleToken ruleComparisonAdverbial() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4159:2: ( (kw= 'equal' | kw= 'faster' | kw= 'slower' | kw= 'better' | kw= 'by' | kw= 'to' ) )
            // InternalRequirementDSL.g:4160:2: (kw= 'equal' | kw= 'faster' | kw= 'slower' | kw= 'better' | kw= 'by' | kw= 'to' )
            {
            // InternalRequirementDSL.g:4160:2: (kw= 'equal' | kw= 'faster' | kw= 'slower' | kw= 'better' | kw= 'by' | kw= 'to' )
            int alt88=6;
            switch ( input.LA(1) ) {
            case 62:
                {
                alt88=1;
                }
                break;
            case 63:
                {
                alt88=2;
                }
                break;
            case 64:
                {
                alt88=3;
                }
                break;
            case 65:
                {
                alt88=4;
                }
                break;
            case 66:
                {
                alt88=5;
                }
                break;
            case 43:
                {
                alt88=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 88, 0, input);

                throw nvae;
            }

            switch (alt88) {
                case 1 :
                    // InternalRequirementDSL.g:4161:3: kw= 'equal'
                    {
                    kw=(Token)match(input,62,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getComparisonAdverbialAccess().getEqualKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4167:3: kw= 'faster'
                    {
                    kw=(Token)match(input,63,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getComparisonAdverbialAccess().getFasterKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4173:3: kw= 'slower'
                    {
                    kw=(Token)match(input,64,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getComparisonAdverbialAccess().getSlowerKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4179:3: kw= 'better'
                    {
                    kw=(Token)match(input,65,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getComparisonAdverbialAccess().getBetterKeyword_3());
                    		

                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:4185:3: kw= 'by'
                    {
                    kw=(Token)match(input,66,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getComparisonAdverbialAccess().getByKeyword_4());
                    		

                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:4191:3: kw= 'to'
                    {
                    kw=(Token)match(input,43,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getComparisonAdverbialAccess().getToKeyword_5());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComparisonAdverbial"


    // $ANTLR start "entryRuleQuantification"
    // InternalRequirementDSL.g:4200:1: entryRuleQuantification returns [String current=null] : iv_ruleQuantification= ruleQuantification EOF ;
    public final String entryRuleQuantification() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQuantification = null;


        try {
            // InternalRequirementDSL.g:4200:54: (iv_ruleQuantification= ruleQuantification EOF )
            // InternalRequirementDSL.g:4201:2: iv_ruleQuantification= ruleQuantification EOF
            {
             newCompositeNode(grammarAccess.getQuantificationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQuantification=ruleQuantification();

            state._fsp--;

             current =iv_ruleQuantification.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQuantification"


    // $ANTLR start "ruleQuantification"
    // InternalRequirementDSL.g:4207:1: ruleQuantification returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'all' | kw= 'every' | kw= 'each' | kw= 'whole' | kw= 'any' | kw= 'several' | kw= 'either' | kw= 'All' | kw= 'Every' | kw= 'Each' | kw= 'Whole' | kw= 'Any' | kw= 'Several' | kw= 'Either' ) ;
    public final AntlrDatatypeRuleToken ruleQuantification() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4213:2: ( (kw= 'all' | kw= 'every' | kw= 'each' | kw= 'whole' | kw= 'any' | kw= 'several' | kw= 'either' | kw= 'All' | kw= 'Every' | kw= 'Each' | kw= 'Whole' | kw= 'Any' | kw= 'Several' | kw= 'Either' ) )
            // InternalRequirementDSL.g:4214:2: (kw= 'all' | kw= 'every' | kw= 'each' | kw= 'whole' | kw= 'any' | kw= 'several' | kw= 'either' | kw= 'All' | kw= 'Every' | kw= 'Each' | kw= 'Whole' | kw= 'Any' | kw= 'Several' | kw= 'Either' )
            {
            // InternalRequirementDSL.g:4214:2: (kw= 'all' | kw= 'every' | kw= 'each' | kw= 'whole' | kw= 'any' | kw= 'several' | kw= 'either' | kw= 'All' | kw= 'Every' | kw= 'Each' | kw= 'Whole' | kw= 'Any' | kw= 'Several' | kw= 'Either' )
            int alt89=14;
            switch ( input.LA(1) ) {
            case 67:
                {
                alt89=1;
                }
                break;
            case 68:
                {
                alt89=2;
                }
                break;
            case 69:
                {
                alt89=3;
                }
                break;
            case 70:
                {
                alt89=4;
                }
                break;
            case 71:
                {
                alt89=5;
                }
                break;
            case 72:
                {
                alt89=6;
                }
                break;
            case 73:
                {
                alt89=7;
                }
                break;
            case 74:
                {
                alt89=8;
                }
                break;
            case 75:
                {
                alt89=9;
                }
                break;
            case 76:
                {
                alt89=10;
                }
                break;
            case 77:
                {
                alt89=11;
                }
                break;
            case 78:
                {
                alt89=12;
                }
                break;
            case 79:
                {
                alt89=13;
                }
                break;
            case 80:
                {
                alt89=14;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 89, 0, input);

                throw nvae;
            }

            switch (alt89) {
                case 1 :
                    // InternalRequirementDSL.g:4215:3: kw= 'all'
                    {
                    kw=(Token)match(input,67,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getAllKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4221:3: kw= 'every'
                    {
                    kw=(Token)match(input,68,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getEveryKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4227:3: kw= 'each'
                    {
                    kw=(Token)match(input,69,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getEachKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4233:3: kw= 'whole'
                    {
                    kw=(Token)match(input,70,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getWholeKeyword_3());
                    		

                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:4239:3: kw= 'any'
                    {
                    kw=(Token)match(input,71,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getAnyKeyword_4());
                    		

                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:4245:3: kw= 'several'
                    {
                    kw=(Token)match(input,72,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getSeveralKeyword_5());
                    		

                    }
                    break;
                case 7 :
                    // InternalRequirementDSL.g:4251:3: kw= 'either'
                    {
                    kw=(Token)match(input,73,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getEitherKeyword_6());
                    		

                    }
                    break;
                case 8 :
                    // InternalRequirementDSL.g:4257:3: kw= 'All'
                    {
                    kw=(Token)match(input,74,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getAllKeyword_7());
                    		

                    }
                    break;
                case 9 :
                    // InternalRequirementDSL.g:4263:3: kw= 'Every'
                    {
                    kw=(Token)match(input,75,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getEveryKeyword_8());
                    		

                    }
                    break;
                case 10 :
                    // InternalRequirementDSL.g:4269:3: kw= 'Each'
                    {
                    kw=(Token)match(input,76,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getEachKeyword_9());
                    		

                    }
                    break;
                case 11 :
                    // InternalRequirementDSL.g:4275:3: kw= 'Whole'
                    {
                    kw=(Token)match(input,77,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getWholeKeyword_10());
                    		

                    }
                    break;
                case 12 :
                    // InternalRequirementDSL.g:4281:3: kw= 'Any'
                    {
                    kw=(Token)match(input,78,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getAnyKeyword_11());
                    		

                    }
                    break;
                case 13 :
                    // InternalRequirementDSL.g:4287:3: kw= 'Several'
                    {
                    kw=(Token)match(input,79,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getSeveralKeyword_12());
                    		

                    }
                    break;
                case 14 :
                    // InternalRequirementDSL.g:4293:3: kw= 'Either'
                    {
                    kw=(Token)match(input,80,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getEitherKeyword_13());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQuantification"


    // $ANTLR start "entryRuleNegation"
    // InternalRequirementDSL.g:4302:1: entryRuleNegation returns [String current=null] : iv_ruleNegation= ruleNegation EOF ;
    public final String entryRuleNegation() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNegation = null;


        try {
            // InternalRequirementDSL.g:4302:48: (iv_ruleNegation= ruleNegation EOF )
            // InternalRequirementDSL.g:4303:2: iv_ruleNegation= ruleNegation EOF
            {
             newCompositeNode(grammarAccess.getNegationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNegation=ruleNegation();

            state._fsp--;

             current =iv_ruleNegation.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNegation"


    // $ANTLR start "ruleNegation"
    // InternalRequirementDSL.g:4309:1: ruleNegation returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= 'not' ;
    public final AntlrDatatypeRuleToken ruleNegation() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4315:2: (kw= 'not' )
            // InternalRequirementDSL.g:4316:2: kw= 'not'
            {
            kw=(Token)match(input,81,FOLLOW_2); 

            		current.merge(kw);
            		newLeafNode(kw, grammarAccess.getNegationAccess().getNotKeyword());
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNegation"


    // $ANTLR start "entryRuleAuxiliaryVerbNegation"
    // InternalRequirementDSL.g:4324:1: entryRuleAuxiliaryVerbNegation returns [String current=null] : iv_ruleAuxiliaryVerbNegation= ruleAuxiliaryVerbNegation EOF ;
    public final String entryRuleAuxiliaryVerbNegation() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleAuxiliaryVerbNegation = null;


        try {
            // InternalRequirementDSL.g:4324:61: (iv_ruleAuxiliaryVerbNegation= ruleAuxiliaryVerbNegation EOF )
            // InternalRequirementDSL.g:4325:2: iv_ruleAuxiliaryVerbNegation= ruleAuxiliaryVerbNegation EOF
            {
             newCompositeNode(grammarAccess.getAuxiliaryVerbNegationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAuxiliaryVerbNegation=ruleAuxiliaryVerbNegation();

            state._fsp--;

             current =iv_ruleAuxiliaryVerbNegation.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAuxiliaryVerbNegation"


    // $ANTLR start "ruleAuxiliaryVerbNegation"
    // InternalRequirementDSL.g:4331:1: ruleAuxiliaryVerbNegation returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'doesn\\u00B4t' | kw= 'don\\u00B4t' | kw= 'isn\\u00B4t' | kw= 'aren\\u00B4t' ) ;
    public final AntlrDatatypeRuleToken ruleAuxiliaryVerbNegation() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4337:2: ( (kw= 'doesn\\u00B4t' | kw= 'don\\u00B4t' | kw= 'isn\\u00B4t' | kw= 'aren\\u00B4t' ) )
            // InternalRequirementDSL.g:4338:2: (kw= 'doesn\\u00B4t' | kw= 'don\\u00B4t' | kw= 'isn\\u00B4t' | kw= 'aren\\u00B4t' )
            {
            // InternalRequirementDSL.g:4338:2: (kw= 'doesn\\u00B4t' | kw= 'don\\u00B4t' | kw= 'isn\\u00B4t' | kw= 'aren\\u00B4t' )
            int alt90=4;
            switch ( input.LA(1) ) {
            case 82:
                {
                alt90=1;
                }
                break;
            case 83:
                {
                alt90=2;
                }
                break;
            case 84:
                {
                alt90=3;
                }
                break;
            case 85:
                {
                alt90=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 90, 0, input);

                throw nvae;
            }

            switch (alt90) {
                case 1 :
                    // InternalRequirementDSL.g:4339:3: kw= 'doesn\\u00B4t'
                    {
                    kw=(Token)match(input,82,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getAuxiliaryVerbNegationAccess().getDoesnTKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4345:3: kw= 'don\\u00B4t'
                    {
                    kw=(Token)match(input,83,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getAuxiliaryVerbNegationAccess().getDonTKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4351:3: kw= 'isn\\u00B4t'
                    {
                    kw=(Token)match(input,84,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getAuxiliaryVerbNegationAccess().getIsnTKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4357:3: kw= 'aren\\u00B4t'
                    {
                    kw=(Token)match(input,85,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getAuxiliaryVerbNegationAccess().getArenTKeyword_3());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAuxiliaryVerbNegation"


    // $ANTLR start "entryRuleArticles"
    // InternalRequirementDSL.g:4366:1: entryRuleArticles returns [String current=null] : iv_ruleArticles= ruleArticles EOF ;
    public final String entryRuleArticles() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleArticles = null;


        try {
            // InternalRequirementDSL.g:4366:48: (iv_ruleArticles= ruleArticles EOF )
            // InternalRequirementDSL.g:4367:2: iv_ruleArticles= ruleArticles EOF
            {
             newCompositeNode(grammarAccess.getArticlesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleArticles=ruleArticles();

            state._fsp--;

             current =iv_ruleArticles.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleArticles"


    // $ANTLR start "ruleArticles"
    // InternalRequirementDSL.g:4373:1: ruleArticles returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'the' | kw= 'a' | kw= 'an' | kw= 'The' | kw= 'A' | kw= 'An' ) ;
    public final AntlrDatatypeRuleToken ruleArticles() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4379:2: ( (kw= 'the' | kw= 'a' | kw= 'an' | kw= 'The' | kw= 'A' | kw= 'An' ) )
            // InternalRequirementDSL.g:4380:2: (kw= 'the' | kw= 'a' | kw= 'an' | kw= 'The' | kw= 'A' | kw= 'An' )
            {
            // InternalRequirementDSL.g:4380:2: (kw= 'the' | kw= 'a' | kw= 'an' | kw= 'The' | kw= 'A' | kw= 'An' )
            int alt91=6;
            switch ( input.LA(1) ) {
            case 86:
                {
                alt91=1;
                }
                break;
            case 87:
                {
                alt91=2;
                }
                break;
            case 88:
                {
                alt91=3;
                }
                break;
            case 89:
                {
                alt91=4;
                }
                break;
            case 90:
                {
                alt91=5;
                }
                break;
            case 91:
                {
                alt91=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 91, 0, input);

                throw nvae;
            }

            switch (alt91) {
                case 1 :
                    // InternalRequirementDSL.g:4381:3: kw= 'the'
                    {
                    kw=(Token)match(input,86,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getArticlesAccess().getTheKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4387:3: kw= 'a'
                    {
                    kw=(Token)match(input,87,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getArticlesAccess().getAKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4393:3: kw= 'an'
                    {
                    kw=(Token)match(input,88,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getArticlesAccess().getAnKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4399:3: kw= 'The'
                    {
                    kw=(Token)match(input,89,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getArticlesAccess().getTheKeyword_3());
                    		

                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:4405:3: kw= 'A'
                    {
                    kw=(Token)match(input,90,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getArticlesAccess().getAKeyword_4());
                    		

                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:4411:3: kw= 'An'
                    {
                    kw=(Token)match(input,91,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getArticlesAccess().getAnKeyword_5());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleArticles"


    // $ANTLR start "entryRuleRefArticles"
    // InternalRequirementDSL.g:4420:1: entryRuleRefArticles returns [String current=null] : iv_ruleRefArticles= ruleRefArticles EOF ;
    public final String entryRuleRefArticles() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleRefArticles = null;


        try {
            // InternalRequirementDSL.g:4420:51: (iv_ruleRefArticles= ruleRefArticles EOF )
            // InternalRequirementDSL.g:4421:2: iv_ruleRefArticles= ruleRefArticles EOF
            {
             newCompositeNode(grammarAccess.getRefArticlesRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRefArticles=ruleRefArticles();

            state._fsp--;

             current =iv_ruleRefArticles.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRefArticles"


    // $ANTLR start "ruleRefArticles"
    // InternalRequirementDSL.g:4427:1: ruleRefArticles returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'that' | kw= 'this' | kw= 'That' | kw= 'This' ) ;
    public final AntlrDatatypeRuleToken ruleRefArticles() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4433:2: ( (kw= 'that' | kw= 'this' | kw= 'That' | kw= 'This' ) )
            // InternalRequirementDSL.g:4434:2: (kw= 'that' | kw= 'this' | kw= 'That' | kw= 'This' )
            {
            // InternalRequirementDSL.g:4434:2: (kw= 'that' | kw= 'this' | kw= 'That' | kw= 'This' )
            int alt92=4;
            switch ( input.LA(1) ) {
            case 92:
                {
                alt92=1;
                }
                break;
            case 93:
                {
                alt92=2;
                }
                break;
            case 94:
                {
                alt92=3;
                }
                break;
            case 95:
                {
                alt92=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 92, 0, input);

                throw nvae;
            }

            switch (alt92) {
                case 1 :
                    // InternalRequirementDSL.g:4435:3: kw= 'that'
                    {
                    kw=(Token)match(input,92,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRefArticlesAccess().getThatKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4441:3: kw= 'this'
                    {
                    kw=(Token)match(input,93,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRefArticlesAccess().getThisKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4447:3: kw= 'That'
                    {
                    kw=(Token)match(input,94,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRefArticlesAccess().getThatKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4453:3: kw= 'This'
                    {
                    kw=(Token)match(input,95,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRefArticlesAccess().getThisKeyword_3());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRefArticles"


    // $ANTLR start "entryRuleStuffWord"
    // InternalRequirementDSL.g:4462:1: entryRuleStuffWord returns [String current=null] : iv_ruleStuffWord= ruleStuffWord EOF ;
    public final String entryRuleStuffWord() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleStuffWord = null;


        try {
            // InternalRequirementDSL.g:4462:49: (iv_ruleStuffWord= ruleStuffWord EOF )
            // InternalRequirementDSL.g:4463:2: iv_ruleStuffWord= ruleStuffWord EOF
            {
             newCompositeNode(grammarAccess.getStuffWordRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStuffWord=ruleStuffWord();

            state._fsp--;

             current =iv_ruleStuffWord.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStuffWord"


    // $ANTLR start "ruleStuffWord"
    // InternalRequirementDSL.g:4469:1: ruleStuffWord returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= 'with' ;
    public final AntlrDatatypeRuleToken ruleStuffWord() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4475:2: (kw= 'with' )
            // InternalRequirementDSL.g:4476:2: kw= 'with'
            {
            kw=(Token)match(input,96,FOLLOW_2); 

            		current.merge(kw);
            		newLeafNode(kw, grammarAccess.getStuffWordAccess().getWithKeyword());
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStuffWord"


    // $ANTLR start "entryRuleRelativePronounsSubject"
    // InternalRequirementDSL.g:4484:1: entryRuleRelativePronounsSubject returns [String current=null] : iv_ruleRelativePronounsSubject= ruleRelativePronounsSubject EOF ;
    public final String entryRuleRelativePronounsSubject() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleRelativePronounsSubject = null;


        try {
            // InternalRequirementDSL.g:4484:63: (iv_ruleRelativePronounsSubject= ruleRelativePronounsSubject EOF )
            // InternalRequirementDSL.g:4485:2: iv_ruleRelativePronounsSubject= ruleRelativePronounsSubject EOF
            {
             newCompositeNode(grammarAccess.getRelativePronounsSubjectRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRelativePronounsSubject=ruleRelativePronounsSubject();

            state._fsp--;

             current =iv_ruleRelativePronounsSubject.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRelativePronounsSubject"


    // $ANTLR start "ruleRelativePronounsSubject"
    // InternalRequirementDSL.g:4491:1: ruleRelativePronounsSubject returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'which' | kw= 'who' | kw= 'that' ) ;
    public final AntlrDatatypeRuleToken ruleRelativePronounsSubject() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4497:2: ( (kw= 'which' | kw= 'who' | kw= 'that' ) )
            // InternalRequirementDSL.g:4498:2: (kw= 'which' | kw= 'who' | kw= 'that' )
            {
            // InternalRequirementDSL.g:4498:2: (kw= 'which' | kw= 'who' | kw= 'that' )
            int alt93=3;
            switch ( input.LA(1) ) {
            case 97:
                {
                alt93=1;
                }
                break;
            case 98:
                {
                alt93=2;
                }
                break;
            case 92:
                {
                alt93=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 93, 0, input);

                throw nvae;
            }

            switch (alt93) {
                case 1 :
                    // InternalRequirementDSL.g:4499:3: kw= 'which'
                    {
                    kw=(Token)match(input,97,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRelativePronounsSubjectAccess().getWhichKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4505:3: kw= 'who'
                    {
                    kw=(Token)match(input,98,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRelativePronounsSubjectAccess().getWhoKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4511:3: kw= 'that'
                    {
                    kw=(Token)match(input,92,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRelativePronounsSubjectAccess().getThatKeyword_2());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRelativePronounsSubject"


    // $ANTLR start "entryRuleRelativePronounsObject"
    // InternalRequirementDSL.g:4520:1: entryRuleRelativePronounsObject returns [String current=null] : iv_ruleRelativePronounsObject= ruleRelativePronounsObject EOF ;
    public final String entryRuleRelativePronounsObject() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleRelativePronounsObject = null;


        try {
            // InternalRequirementDSL.g:4520:62: (iv_ruleRelativePronounsObject= ruleRelativePronounsObject EOF )
            // InternalRequirementDSL.g:4521:2: iv_ruleRelativePronounsObject= ruleRelativePronounsObject EOF
            {
             newCompositeNode(grammarAccess.getRelativePronounsObjectRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRelativePronounsObject=ruleRelativePronounsObject();

            state._fsp--;

             current =iv_ruleRelativePronounsObject.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRelativePronounsObject"


    // $ANTLR start "ruleRelativePronounsObject"
    // InternalRequirementDSL.g:4527:1: ruleRelativePronounsObject returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'whose' | kw= 'whom' ) ;
    public final AntlrDatatypeRuleToken ruleRelativePronounsObject() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4533:2: ( (kw= 'whose' | kw= 'whom' ) )
            // InternalRequirementDSL.g:4534:2: (kw= 'whose' | kw= 'whom' )
            {
            // InternalRequirementDSL.g:4534:2: (kw= 'whose' | kw= 'whom' )
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==99) ) {
                alt94=1;
            }
            else if ( (LA94_0==100) ) {
                alt94=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 94, 0, input);

                throw nvae;
            }
            switch (alt94) {
                case 1 :
                    // InternalRequirementDSL.g:4535:3: kw= 'whose'
                    {
                    kw=(Token)match(input,99,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRelativePronounsObjectAccess().getWhoseKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4541:3: kw= 'whom'
                    {
                    kw=(Token)match(input,100,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRelativePronounsObjectAccess().getWhomKeyword_1());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRelativePronounsObject"


    // $ANTLR start "entryRuleFLOAT"
    // InternalRequirementDSL.g:4550:1: entryRuleFLOAT returns [String current=null] : iv_ruleFLOAT= ruleFLOAT EOF ;
    public final String entryRuleFLOAT() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFLOAT = null;


        try {
            // InternalRequirementDSL.g:4550:45: (iv_ruleFLOAT= ruleFLOAT EOF )
            // InternalRequirementDSL.g:4551:2: iv_ruleFLOAT= ruleFLOAT EOF
            {
             newCompositeNode(grammarAccess.getFLOATRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFLOAT=ruleFLOAT();

            state._fsp--;

             current =iv_ruleFLOAT.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFLOAT"


    // $ANTLR start "ruleFLOAT"
    // InternalRequirementDSL.g:4557:1: ruleFLOAT returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_INT_0= RULE_INT kw= '.' this_INT_2= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleFLOAT() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;
        Token kw=null;
        Token this_INT_2=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4563:2: ( (this_INT_0= RULE_INT kw= '.' this_INT_2= RULE_INT ) )
            // InternalRequirementDSL.g:4564:2: (this_INT_0= RULE_INT kw= '.' this_INT_2= RULE_INT )
            {
            // InternalRequirementDSL.g:4564:2: (this_INT_0= RULE_INT kw= '.' this_INT_2= RULE_INT )
            // InternalRequirementDSL.g:4565:3: this_INT_0= RULE_INT kw= '.' this_INT_2= RULE_INT
            {
            this_INT_0=(Token)match(input,RULE_INT,FOLLOW_48); 

            			current.merge(this_INT_0);
            		

            			newLeafNode(this_INT_0, grammarAccess.getFLOATAccess().getINTTerminalRuleCall_0());
            		
            kw=(Token)match(input,15,FOLLOW_40); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getFLOATAccess().getFullStopKeyword_1());
            		
            this_INT_2=(Token)match(input,RULE_INT,FOLLOW_2); 

            			current.merge(this_INT_2);
            		

            			newLeafNode(this_INT_2, grammarAccess.getFLOATAccess().getINTTerminalRuleCall_2());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFLOAT"


    // $ANTLR start "entryRuleUnit"
    // InternalRequirementDSL.g:4588:1: entryRuleUnit returns [String current=null] : iv_ruleUnit= ruleUnit EOF ;
    public final String entryRuleUnit() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnit = null;


        try {
            // InternalRequirementDSL.g:4588:44: (iv_ruleUnit= ruleUnit EOF )
            // InternalRequirementDSL.g:4589:2: iv_ruleUnit= ruleUnit EOF
            {
             newCompositeNode(grammarAccess.getUnitRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUnit=ruleUnit();

            state._fsp--;

             current =iv_ruleUnit.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnit"


    // $ANTLR start "ruleUnit"
    // InternalRequirementDSL.g:4595:1: ruleUnit returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_LengthUnits_0= ruleLengthUnits | this_PresureUnits_1= rulePresureUnits | this_HeatUnits_2= ruleHeatUnits | this_MassUnits_3= ruleMassUnits | this_VelcoityUnits_4= ruleVelcoityUnits | this_Cuvature_5= ruleCuvature ) ;
    public final AntlrDatatypeRuleToken ruleUnit() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_LengthUnits_0 = null;

        AntlrDatatypeRuleToken this_PresureUnits_1 = null;

        AntlrDatatypeRuleToken this_HeatUnits_2 = null;

        AntlrDatatypeRuleToken this_MassUnits_3 = null;

        AntlrDatatypeRuleToken this_VelcoityUnits_4 = null;

        AntlrDatatypeRuleToken this_Cuvature_5 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:4601:2: ( (this_LengthUnits_0= ruleLengthUnits | this_PresureUnits_1= rulePresureUnits | this_HeatUnits_2= ruleHeatUnits | this_MassUnits_3= ruleMassUnits | this_VelcoityUnits_4= ruleVelcoityUnits | this_Cuvature_5= ruleCuvature ) )
            // InternalRequirementDSL.g:4602:2: (this_LengthUnits_0= ruleLengthUnits | this_PresureUnits_1= rulePresureUnits | this_HeatUnits_2= ruleHeatUnits | this_MassUnits_3= ruleMassUnits | this_VelcoityUnits_4= ruleVelcoityUnits | this_Cuvature_5= ruleCuvature )
            {
            // InternalRequirementDSL.g:4602:2: (this_LengthUnits_0= ruleLengthUnits | this_PresureUnits_1= rulePresureUnits | this_HeatUnits_2= ruleHeatUnits | this_MassUnits_3= ruleMassUnits | this_VelcoityUnits_4= ruleVelcoityUnits | this_Cuvature_5= ruleCuvature )
            int alt95=6;
            switch ( input.LA(1) ) {
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
                {
                alt95=1;
                }
                break;
            case 115:
            case 116:
            case 117:
                {
                alt95=2;
                }
                break;
            case 113:
            case 114:
                {
                alt95=3;
                }
                break;
            case 109:
            case 110:
            case 111:
            case 112:
                {
                alt95=4;
                }
                break;
            case 105:
            case 106:
            case 107:
            case 108:
                {
                alt95=5;
                }
                break;
            case 101:
            case 102:
            case 103:
            case 104:
                {
                alt95=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 95, 0, input);

                throw nvae;
            }

            switch (alt95) {
                case 1 :
                    // InternalRequirementDSL.g:4603:3: this_LengthUnits_0= ruleLengthUnits
                    {

                    			newCompositeNode(grammarAccess.getUnitAccess().getLengthUnitsParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_LengthUnits_0=ruleLengthUnits();

                    state._fsp--;


                    			current.merge(this_LengthUnits_0);
                    		

                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4614:3: this_PresureUnits_1= rulePresureUnits
                    {

                    			newCompositeNode(grammarAccess.getUnitAccess().getPresureUnitsParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_PresureUnits_1=rulePresureUnits();

                    state._fsp--;


                    			current.merge(this_PresureUnits_1);
                    		

                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4625:3: this_HeatUnits_2= ruleHeatUnits
                    {

                    			newCompositeNode(grammarAccess.getUnitAccess().getHeatUnitsParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_HeatUnits_2=ruleHeatUnits();

                    state._fsp--;


                    			current.merge(this_HeatUnits_2);
                    		

                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4636:3: this_MassUnits_3= ruleMassUnits
                    {

                    			newCompositeNode(grammarAccess.getUnitAccess().getMassUnitsParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_MassUnits_3=ruleMassUnits();

                    state._fsp--;


                    			current.merge(this_MassUnits_3);
                    		

                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:4647:3: this_VelcoityUnits_4= ruleVelcoityUnits
                    {

                    			newCompositeNode(grammarAccess.getUnitAccess().getVelcoityUnitsParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_VelcoityUnits_4=ruleVelcoityUnits();

                    state._fsp--;


                    			current.merge(this_VelcoityUnits_4);
                    		

                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:4658:3: this_Cuvature_5= ruleCuvature
                    {

                    			newCompositeNode(grammarAccess.getUnitAccess().getCuvatureParserRuleCall_5());
                    		
                    pushFollow(FOLLOW_2);
                    this_Cuvature_5=ruleCuvature();

                    state._fsp--;


                    			current.merge(this_Cuvature_5);
                    		

                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnit"


    // $ANTLR start "entryRuleCuvature"
    // InternalRequirementDSL.g:4672:1: entryRuleCuvature returns [String current=null] : iv_ruleCuvature= ruleCuvature EOF ;
    public final String entryRuleCuvature() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCuvature = null;


        try {
            // InternalRequirementDSL.g:4672:48: (iv_ruleCuvature= ruleCuvature EOF )
            // InternalRequirementDSL.g:4673:2: iv_ruleCuvature= ruleCuvature EOF
            {
             newCompositeNode(grammarAccess.getCuvatureRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCuvature=ruleCuvature();

            state._fsp--;

             current =iv_ruleCuvature.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCuvature"


    // $ANTLR start "ruleCuvature"
    // InternalRequirementDSL.g:4679:1: ruleCuvature returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'rad/m' | kw= '\\u00B0' | kw= 'rad' | kw= '\\u00B0/m' ) ;
    public final AntlrDatatypeRuleToken ruleCuvature() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4685:2: ( (kw= 'rad/m' | kw= '\\u00B0' | kw= 'rad' | kw= '\\u00B0/m' ) )
            // InternalRequirementDSL.g:4686:2: (kw= 'rad/m' | kw= '\\u00B0' | kw= 'rad' | kw= '\\u00B0/m' )
            {
            // InternalRequirementDSL.g:4686:2: (kw= 'rad/m' | kw= '\\u00B0' | kw= 'rad' | kw= '\\u00B0/m' )
            int alt96=4;
            switch ( input.LA(1) ) {
            case 101:
                {
                alt96=1;
                }
                break;
            case 102:
                {
                alt96=2;
                }
                break;
            case 103:
                {
                alt96=3;
                }
                break;
            case 104:
                {
                alt96=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 96, 0, input);

                throw nvae;
            }

            switch (alt96) {
                case 1 :
                    // InternalRequirementDSL.g:4687:3: kw= 'rad/m'
                    {
                    kw=(Token)match(input,101,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getCuvatureAccess().getRadMKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4693:3: kw= '\\u00B0'
                    {
                    kw=(Token)match(input,102,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getCuvatureAccess().getDegreeSignKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4699:3: kw= 'rad'
                    {
                    kw=(Token)match(input,103,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getCuvatureAccess().getRadKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4705:3: kw= '\\u00B0/m'
                    {
                    kw=(Token)match(input,104,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getCuvatureAccess().getMKeyword_3());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCuvature"


    // $ANTLR start "entryRuleVelcoityUnits"
    // InternalRequirementDSL.g:4714:1: entryRuleVelcoityUnits returns [String current=null] : iv_ruleVelcoityUnits= ruleVelcoityUnits EOF ;
    public final String entryRuleVelcoityUnits() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleVelcoityUnits = null;


        try {
            // InternalRequirementDSL.g:4714:53: (iv_ruleVelcoityUnits= ruleVelcoityUnits EOF )
            // InternalRequirementDSL.g:4715:2: iv_ruleVelcoityUnits= ruleVelcoityUnits EOF
            {
             newCompositeNode(grammarAccess.getVelcoityUnitsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVelcoityUnits=ruleVelcoityUnits();

            state._fsp--;

             current =iv_ruleVelcoityUnits.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVelcoityUnits"


    // $ANTLR start "ruleVelcoityUnits"
    // InternalRequirementDSL.g:4721:1: ruleVelcoityUnits returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'm/s' | kw= 'knots' | kw= 'km/h' | kw= 'm/min' ) ;
    public final AntlrDatatypeRuleToken ruleVelcoityUnits() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4727:2: ( (kw= 'm/s' | kw= 'knots' | kw= 'km/h' | kw= 'm/min' ) )
            // InternalRequirementDSL.g:4728:2: (kw= 'm/s' | kw= 'knots' | kw= 'km/h' | kw= 'm/min' )
            {
            // InternalRequirementDSL.g:4728:2: (kw= 'm/s' | kw= 'knots' | kw= 'km/h' | kw= 'm/min' )
            int alt97=4;
            switch ( input.LA(1) ) {
            case 105:
                {
                alt97=1;
                }
                break;
            case 106:
                {
                alt97=2;
                }
                break;
            case 107:
                {
                alt97=3;
                }
                break;
            case 108:
                {
                alt97=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 97, 0, input);

                throw nvae;
            }

            switch (alt97) {
                case 1 :
                    // InternalRequirementDSL.g:4729:3: kw= 'm/s'
                    {
                    kw=(Token)match(input,105,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getVelcoityUnitsAccess().getMSKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4735:3: kw= 'knots'
                    {
                    kw=(Token)match(input,106,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getVelcoityUnitsAccess().getKnotsKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4741:3: kw= 'km/h'
                    {
                    kw=(Token)match(input,107,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getVelcoityUnitsAccess().getKmHKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4747:3: kw= 'm/min'
                    {
                    kw=(Token)match(input,108,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getVelcoityUnitsAccess().getMMinKeyword_3());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVelcoityUnits"


    // $ANTLR start "entryRuleMassUnits"
    // InternalRequirementDSL.g:4756:1: entryRuleMassUnits returns [String current=null] : iv_ruleMassUnits= ruleMassUnits EOF ;
    public final String entryRuleMassUnits() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleMassUnits = null;


        try {
            // InternalRequirementDSL.g:4756:49: (iv_ruleMassUnits= ruleMassUnits EOF )
            // InternalRequirementDSL.g:4757:2: iv_ruleMassUnits= ruleMassUnits EOF
            {
             newCompositeNode(grammarAccess.getMassUnitsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMassUnits=ruleMassUnits();

            state._fsp--;

             current =iv_ruleMassUnits.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMassUnits"


    // $ANTLR start "ruleMassUnits"
    // InternalRequirementDSL.g:4763:1: ruleMassUnits returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'kg' | kw= 'g' | kw= 'mg' | kw= 't' ) ;
    public final AntlrDatatypeRuleToken ruleMassUnits() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4769:2: ( (kw= 'kg' | kw= 'g' | kw= 'mg' | kw= 't' ) )
            // InternalRequirementDSL.g:4770:2: (kw= 'kg' | kw= 'g' | kw= 'mg' | kw= 't' )
            {
            // InternalRequirementDSL.g:4770:2: (kw= 'kg' | kw= 'g' | kw= 'mg' | kw= 't' )
            int alt98=4;
            switch ( input.LA(1) ) {
            case 109:
                {
                alt98=1;
                }
                break;
            case 110:
                {
                alt98=2;
                }
                break;
            case 111:
                {
                alt98=3;
                }
                break;
            case 112:
                {
                alt98=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 98, 0, input);

                throw nvae;
            }

            switch (alt98) {
                case 1 :
                    // InternalRequirementDSL.g:4771:3: kw= 'kg'
                    {
                    kw=(Token)match(input,109,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getMassUnitsAccess().getKgKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4777:3: kw= 'g'
                    {
                    kw=(Token)match(input,110,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getMassUnitsAccess().getGKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4783:3: kw= 'mg'
                    {
                    kw=(Token)match(input,111,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getMassUnitsAccess().getMgKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4789:3: kw= 't'
                    {
                    kw=(Token)match(input,112,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getMassUnitsAccess().getTKeyword_3());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMassUnits"


    // $ANTLR start "entryRuleHeatUnits"
    // InternalRequirementDSL.g:4798:1: entryRuleHeatUnits returns [String current=null] : iv_ruleHeatUnits= ruleHeatUnits EOF ;
    public final String entryRuleHeatUnits() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleHeatUnits = null;


        try {
            // InternalRequirementDSL.g:4798:49: (iv_ruleHeatUnits= ruleHeatUnits EOF )
            // InternalRequirementDSL.g:4799:2: iv_ruleHeatUnits= ruleHeatUnits EOF
            {
             newCompositeNode(grammarAccess.getHeatUnitsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleHeatUnits=ruleHeatUnits();

            state._fsp--;

             current =iv_ruleHeatUnits.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleHeatUnits"


    // $ANTLR start "ruleHeatUnits"
    // InternalRequirementDSL.g:4805:1: ruleHeatUnits returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'C' | kw= 'F' ) ;
    public final AntlrDatatypeRuleToken ruleHeatUnits() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4811:2: ( (kw= 'C' | kw= 'F' ) )
            // InternalRequirementDSL.g:4812:2: (kw= 'C' | kw= 'F' )
            {
            // InternalRequirementDSL.g:4812:2: (kw= 'C' | kw= 'F' )
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==113) ) {
                alt99=1;
            }
            else if ( (LA99_0==114) ) {
                alt99=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 99, 0, input);

                throw nvae;
            }
            switch (alt99) {
                case 1 :
                    // InternalRequirementDSL.g:4813:3: kw= 'C'
                    {
                    kw=(Token)match(input,113,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getHeatUnitsAccess().getCKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4819:3: kw= 'F'
                    {
                    kw=(Token)match(input,114,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getHeatUnitsAccess().getFKeyword_1());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleHeatUnits"


    // $ANTLR start "entryRulePresureUnits"
    // InternalRequirementDSL.g:4828:1: entryRulePresureUnits returns [String current=null] : iv_rulePresureUnits= rulePresureUnits EOF ;
    public final String entryRulePresureUnits() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePresureUnits = null;


        try {
            // InternalRequirementDSL.g:4828:52: (iv_rulePresureUnits= rulePresureUnits EOF )
            // InternalRequirementDSL.g:4829:2: iv_rulePresureUnits= rulePresureUnits EOF
            {
             newCompositeNode(grammarAccess.getPresureUnitsRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePresureUnits=rulePresureUnits();

            state._fsp--;

             current =iv_rulePresureUnits.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePresureUnits"


    // $ANTLR start "rulePresureUnits"
    // InternalRequirementDSL.g:4835:1: rulePresureUnits returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'bar' | kw= 'Pa' | kw= 'hPa' ) ;
    public final AntlrDatatypeRuleToken rulePresureUnits() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4841:2: ( (kw= 'bar' | kw= 'Pa' | kw= 'hPa' ) )
            // InternalRequirementDSL.g:4842:2: (kw= 'bar' | kw= 'Pa' | kw= 'hPa' )
            {
            // InternalRequirementDSL.g:4842:2: (kw= 'bar' | kw= 'Pa' | kw= 'hPa' )
            int alt100=3;
            switch ( input.LA(1) ) {
            case 115:
                {
                alt100=1;
                }
                break;
            case 116:
                {
                alt100=2;
                }
                break;
            case 117:
                {
                alt100=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 100, 0, input);

                throw nvae;
            }

            switch (alt100) {
                case 1 :
                    // InternalRequirementDSL.g:4843:3: kw= 'bar'
                    {
                    kw=(Token)match(input,115,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPresureUnitsAccess().getBarKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4849:3: kw= 'Pa'
                    {
                    kw=(Token)match(input,116,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPresureUnitsAccess().getPaKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4855:3: kw= 'hPa'
                    {
                    kw=(Token)match(input,117,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPresureUnitsAccess().getHPaKeyword_2());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePresureUnits"


    // $ANTLR start "entryRuleLengthUnits"
    // InternalRequirementDSL.g:4864:1: entryRuleLengthUnits returns [String current=null] : iv_ruleLengthUnits= ruleLengthUnits EOF ;
    public final String entryRuleLengthUnits() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleLengthUnits = null;


        try {
            // InternalRequirementDSL.g:4864:51: (iv_ruleLengthUnits= ruleLengthUnits EOF )
            // InternalRequirementDSL.g:4865:2: iv_ruleLengthUnits= ruleLengthUnits EOF
            {
             newCompositeNode(grammarAccess.getLengthUnitsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLengthUnits=ruleLengthUnits();

            state._fsp--;

             current =iv_ruleLengthUnits.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLengthUnits"


    // $ANTLR start "ruleLengthUnits"
    // InternalRequirementDSL.g:4871:1: ruleLengthUnits returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'm' | kw= 'f' | kw= 'km' | kw= 'cm' | kw= 'mm' | kw= 'nm' ) ;
    public final AntlrDatatypeRuleToken ruleLengthUnits() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4877:2: ( (kw= 'm' | kw= 'f' | kw= 'km' | kw= 'cm' | kw= 'mm' | kw= 'nm' ) )
            // InternalRequirementDSL.g:4878:2: (kw= 'm' | kw= 'f' | kw= 'km' | kw= 'cm' | kw= 'mm' | kw= 'nm' )
            {
            // InternalRequirementDSL.g:4878:2: (kw= 'm' | kw= 'f' | kw= 'km' | kw= 'cm' | kw= 'mm' | kw= 'nm' )
            int alt101=6;
            switch ( input.LA(1) ) {
            case 118:
                {
                alt101=1;
                }
                break;
            case 119:
                {
                alt101=2;
                }
                break;
            case 120:
                {
                alt101=3;
                }
                break;
            case 121:
                {
                alt101=4;
                }
                break;
            case 122:
                {
                alt101=5;
                }
                break;
            case 123:
                {
                alt101=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 101, 0, input);

                throw nvae;
            }

            switch (alt101) {
                case 1 :
                    // InternalRequirementDSL.g:4879:3: kw= 'm'
                    {
                    kw=(Token)match(input,118,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getLengthUnitsAccess().getMKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4885:3: kw= 'f'
                    {
                    kw=(Token)match(input,119,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getLengthUnitsAccess().getFKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4891:3: kw= 'km'
                    {
                    kw=(Token)match(input,120,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getLengthUnitsAccess().getKmKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4897:3: kw= 'cm'
                    {
                    kw=(Token)match(input,121,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getLengthUnitsAccess().getCmKeyword_3());
                    		

                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:4903:3: kw= 'mm'
                    {
                    kw=(Token)match(input,122,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getLengthUnitsAccess().getMmKeyword_4());
                    		

                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:4909:3: kw= 'nm'
                    {
                    kw=(Token)match(input,123,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getLengthUnitsAccess().getNmKeyword_5());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLengthUnits"


    // $ANTLR start "entryRuleTimeUnits"
    // InternalRequirementDSL.g:4918:1: entryRuleTimeUnits returns [String current=null] : iv_ruleTimeUnits= ruleTimeUnits EOF ;
    public final String entryRuleTimeUnits() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleTimeUnits = null;


        try {
            // InternalRequirementDSL.g:4918:49: (iv_ruleTimeUnits= ruleTimeUnits EOF )
            // InternalRequirementDSL.g:4919:2: iv_ruleTimeUnits= ruleTimeUnits EOF
            {
             newCompositeNode(grammarAccess.getTimeUnitsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTimeUnits=ruleTimeUnits();

            state._fsp--;

             current =iv_ruleTimeUnits.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTimeUnits"


    // $ANTLR start "ruleTimeUnits"
    // InternalRequirementDSL.g:4925:1: ruleTimeUnits returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'ns' | kw= 'ms' | kw= 's' | kw= 'sec' | kw= 'second' | kw= 'seconds' | kw= 'minute' | kw= 'minutes' | kw= 'min' | kw= 'hour' | kw= 'hours' | kw= 'h' | kw= 'day' | kw= 'days' | kw= 'd' | kw= 'month' | kw= 'months' | kw= 'mon' | kw= 'year' | kw= 'years' | kw= 'y' ) ;
    public final AntlrDatatypeRuleToken ruleTimeUnits() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4931:2: ( (kw= 'ns' | kw= 'ms' | kw= 's' | kw= 'sec' | kw= 'second' | kw= 'seconds' | kw= 'minute' | kw= 'minutes' | kw= 'min' | kw= 'hour' | kw= 'hours' | kw= 'h' | kw= 'day' | kw= 'days' | kw= 'd' | kw= 'month' | kw= 'months' | kw= 'mon' | kw= 'year' | kw= 'years' | kw= 'y' ) )
            // InternalRequirementDSL.g:4932:2: (kw= 'ns' | kw= 'ms' | kw= 's' | kw= 'sec' | kw= 'second' | kw= 'seconds' | kw= 'minute' | kw= 'minutes' | kw= 'min' | kw= 'hour' | kw= 'hours' | kw= 'h' | kw= 'day' | kw= 'days' | kw= 'd' | kw= 'month' | kw= 'months' | kw= 'mon' | kw= 'year' | kw= 'years' | kw= 'y' )
            {
            // InternalRequirementDSL.g:4932:2: (kw= 'ns' | kw= 'ms' | kw= 's' | kw= 'sec' | kw= 'second' | kw= 'seconds' | kw= 'minute' | kw= 'minutes' | kw= 'min' | kw= 'hour' | kw= 'hours' | kw= 'h' | kw= 'day' | kw= 'days' | kw= 'd' | kw= 'month' | kw= 'months' | kw= 'mon' | kw= 'year' | kw= 'years' | kw= 'y' )
            int alt102=21;
            switch ( input.LA(1) ) {
            case 124:
                {
                alt102=1;
                }
                break;
            case 125:
                {
                alt102=2;
                }
                break;
            case 126:
                {
                alt102=3;
                }
                break;
            case 127:
                {
                alt102=4;
                }
                break;
            case 128:
                {
                alt102=5;
                }
                break;
            case 129:
                {
                alt102=6;
                }
                break;
            case 130:
                {
                alt102=7;
                }
                break;
            case 131:
                {
                alt102=8;
                }
                break;
            case 132:
                {
                alt102=9;
                }
                break;
            case 133:
                {
                alt102=10;
                }
                break;
            case 134:
                {
                alt102=11;
                }
                break;
            case 135:
                {
                alt102=12;
                }
                break;
            case 136:
                {
                alt102=13;
                }
                break;
            case 137:
                {
                alt102=14;
                }
                break;
            case 138:
                {
                alt102=15;
                }
                break;
            case 139:
                {
                alt102=16;
                }
                break;
            case 140:
                {
                alt102=17;
                }
                break;
            case 141:
                {
                alt102=18;
                }
                break;
            case 142:
                {
                alt102=19;
                }
                break;
            case 143:
                {
                alt102=20;
                }
                break;
            case 144:
                {
                alt102=21;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 102, 0, input);

                throw nvae;
            }

            switch (alt102) {
                case 1 :
                    // InternalRequirementDSL.g:4933:3: kw= 'ns'
                    {
                    kw=(Token)match(input,124,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getNsKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4939:3: kw= 'ms'
                    {
                    kw=(Token)match(input,125,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getMsKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4945:3: kw= 's'
                    {
                    kw=(Token)match(input,126,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getSKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4951:3: kw= 'sec'
                    {
                    kw=(Token)match(input,127,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getSecKeyword_3());
                    		

                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:4957:3: kw= 'second'
                    {
                    kw=(Token)match(input,128,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getSecondKeyword_4());
                    		

                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:4963:3: kw= 'seconds'
                    {
                    kw=(Token)match(input,129,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getSecondsKeyword_5());
                    		

                    }
                    break;
                case 7 :
                    // InternalRequirementDSL.g:4969:3: kw= 'minute'
                    {
                    kw=(Token)match(input,130,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getMinuteKeyword_6());
                    		

                    }
                    break;
                case 8 :
                    // InternalRequirementDSL.g:4975:3: kw= 'minutes'
                    {
                    kw=(Token)match(input,131,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getMinutesKeyword_7());
                    		

                    }
                    break;
                case 9 :
                    // InternalRequirementDSL.g:4981:3: kw= 'min'
                    {
                    kw=(Token)match(input,132,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getMinKeyword_8());
                    		

                    }
                    break;
                case 10 :
                    // InternalRequirementDSL.g:4987:3: kw= 'hour'
                    {
                    kw=(Token)match(input,133,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getHourKeyword_9());
                    		

                    }
                    break;
                case 11 :
                    // InternalRequirementDSL.g:4993:3: kw= 'hours'
                    {
                    kw=(Token)match(input,134,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getHoursKeyword_10());
                    		

                    }
                    break;
                case 12 :
                    // InternalRequirementDSL.g:4999:3: kw= 'h'
                    {
                    kw=(Token)match(input,135,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getHKeyword_11());
                    		

                    }
                    break;
                case 13 :
                    // InternalRequirementDSL.g:5005:3: kw= 'day'
                    {
                    kw=(Token)match(input,136,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getDayKeyword_12());
                    		

                    }
                    break;
                case 14 :
                    // InternalRequirementDSL.g:5011:3: kw= 'days'
                    {
                    kw=(Token)match(input,137,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getDaysKeyword_13());
                    		

                    }
                    break;
                case 15 :
                    // InternalRequirementDSL.g:5017:3: kw= 'd'
                    {
                    kw=(Token)match(input,138,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getDKeyword_14());
                    		

                    }
                    break;
                case 16 :
                    // InternalRequirementDSL.g:5023:3: kw= 'month'
                    {
                    kw=(Token)match(input,139,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getMonthKeyword_15());
                    		

                    }
                    break;
                case 17 :
                    // InternalRequirementDSL.g:5029:3: kw= 'months'
                    {
                    kw=(Token)match(input,140,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getMonthsKeyword_16());
                    		

                    }
                    break;
                case 18 :
                    // InternalRequirementDSL.g:5035:3: kw= 'mon'
                    {
                    kw=(Token)match(input,141,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getMonKeyword_17());
                    		

                    }
                    break;
                case 19 :
                    // InternalRequirementDSL.g:5041:3: kw= 'year'
                    {
                    kw=(Token)match(input,142,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getYearKeyword_18());
                    		

                    }
                    break;
                case 20 :
                    // InternalRequirementDSL.g:5047:3: kw= 'years'
                    {
                    kw=(Token)match(input,143,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getYearsKeyword_19());
                    		

                    }
                    break;
                case 21 :
                    // InternalRequirementDSL.g:5053:3: kw= 'y'
                    {
                    kw=(Token)match(input,144,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getYKeyword_20());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTimeUnits"


    // $ANTLR start "ruleModality"
    // InternalRequirementDSL.g:5062:1: ruleModality returns [Enumerator current=null] : ( (enumLiteral_0= 'shall' ) | (enumLiteral_1= 'should' ) | (enumLiteral_2= 'will' ) | (enumLiteral_3= 'would' ) | (enumLiteral_4= 'can' ) | (enumLiteral_5= 'could' ) | (enumLiteral_6= 'must' ) ) ;
    public final Enumerator ruleModality() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;
        Token enumLiteral_6=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:5068:2: ( ( (enumLiteral_0= 'shall' ) | (enumLiteral_1= 'should' ) | (enumLiteral_2= 'will' ) | (enumLiteral_3= 'would' ) | (enumLiteral_4= 'can' ) | (enumLiteral_5= 'could' ) | (enumLiteral_6= 'must' ) ) )
            // InternalRequirementDSL.g:5069:2: ( (enumLiteral_0= 'shall' ) | (enumLiteral_1= 'should' ) | (enumLiteral_2= 'will' ) | (enumLiteral_3= 'would' ) | (enumLiteral_4= 'can' ) | (enumLiteral_5= 'could' ) | (enumLiteral_6= 'must' ) )
            {
            // InternalRequirementDSL.g:5069:2: ( (enumLiteral_0= 'shall' ) | (enumLiteral_1= 'should' ) | (enumLiteral_2= 'will' ) | (enumLiteral_3= 'would' ) | (enumLiteral_4= 'can' ) | (enumLiteral_5= 'could' ) | (enumLiteral_6= 'must' ) )
            int alt103=7;
            switch ( input.LA(1) ) {
            case 145:
                {
                alt103=1;
                }
                break;
            case 146:
                {
                alt103=2;
                }
                break;
            case 147:
                {
                alt103=3;
                }
                break;
            case 148:
                {
                alt103=4;
                }
                break;
            case 149:
                {
                alt103=5;
                }
                break;
            case 150:
                {
                alt103=6;
                }
                break;
            case 151:
                {
                alt103=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 103, 0, input);

                throw nvae;
            }

            switch (alt103) {
                case 1 :
                    // InternalRequirementDSL.g:5070:3: (enumLiteral_0= 'shall' )
                    {
                    // InternalRequirementDSL.g:5070:3: (enumLiteral_0= 'shall' )
                    // InternalRequirementDSL.g:5071:4: enumLiteral_0= 'shall'
                    {
                    enumLiteral_0=(Token)match(input,145,FOLLOW_2); 

                    				current = grammarAccess.getModalityAccess().getSHALLEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getModalityAccess().getSHALLEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:5078:3: (enumLiteral_1= 'should' )
                    {
                    // InternalRequirementDSL.g:5078:3: (enumLiteral_1= 'should' )
                    // InternalRequirementDSL.g:5079:4: enumLiteral_1= 'should'
                    {
                    enumLiteral_1=(Token)match(input,146,FOLLOW_2); 

                    				current = grammarAccess.getModalityAccess().getSHOULDEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getModalityAccess().getSHOULDEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:5086:3: (enumLiteral_2= 'will' )
                    {
                    // InternalRequirementDSL.g:5086:3: (enumLiteral_2= 'will' )
                    // InternalRequirementDSL.g:5087:4: enumLiteral_2= 'will'
                    {
                    enumLiteral_2=(Token)match(input,147,FOLLOW_2); 

                    				current = grammarAccess.getModalityAccess().getWILLEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getModalityAccess().getWILLEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:5094:3: (enumLiteral_3= 'would' )
                    {
                    // InternalRequirementDSL.g:5094:3: (enumLiteral_3= 'would' )
                    // InternalRequirementDSL.g:5095:4: enumLiteral_3= 'would'
                    {
                    enumLiteral_3=(Token)match(input,148,FOLLOW_2); 

                    				current = grammarAccess.getModalityAccess().getWOULDEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getModalityAccess().getWOULDEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:5102:3: (enumLiteral_4= 'can' )
                    {
                    // InternalRequirementDSL.g:5102:3: (enumLiteral_4= 'can' )
                    // InternalRequirementDSL.g:5103:4: enumLiteral_4= 'can'
                    {
                    enumLiteral_4=(Token)match(input,149,FOLLOW_2); 

                    				current = grammarAccess.getModalityAccess().getCANEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getModalityAccess().getCANEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:5110:3: (enumLiteral_5= 'could' )
                    {
                    // InternalRequirementDSL.g:5110:3: (enumLiteral_5= 'could' )
                    // InternalRequirementDSL.g:5111:4: enumLiteral_5= 'could'
                    {
                    enumLiteral_5=(Token)match(input,150,FOLLOW_2); 

                    				current = grammarAccess.getModalityAccess().getCOULDEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getModalityAccess().getCOULDEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalRequirementDSL.g:5118:3: (enumLiteral_6= 'must' )
                    {
                    // InternalRequirementDSL.g:5118:3: (enumLiteral_6= 'must' )
                    // InternalRequirementDSL.g:5119:4: enumLiteral_6= 'must'
                    {
                    enumLiteral_6=(Token)match(input,151,FOLLOW_2); 

                    				current = grammarAccess.getModalityAccess().getMUSTEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getModalityAccess().getMUSTEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModality"


    // $ANTLR start "ruleModifier"
    // InternalRequirementDSL.g:5129:1: ruleModifier returns [Enumerator current=null] : ( (enumLiteral_0= 'Globally' ) | (enumLiteral_1= 'globally' ) | (enumLiteral_2= 'Always' ) | (enumLiteral_3= 'always' ) | (enumLiteral_4= 'Sometimes' ) | (enumLiteral_5= 'sometimes' ) | (enumLiteral_6= 'Eventually' ) | (enumLiteral_7= 'eventually' ) ) ;
    public final Enumerator ruleModifier() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;
        Token enumLiteral_6=null;
        Token enumLiteral_7=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:5135:2: ( ( (enumLiteral_0= 'Globally' ) | (enumLiteral_1= 'globally' ) | (enumLiteral_2= 'Always' ) | (enumLiteral_3= 'always' ) | (enumLiteral_4= 'Sometimes' ) | (enumLiteral_5= 'sometimes' ) | (enumLiteral_6= 'Eventually' ) | (enumLiteral_7= 'eventually' ) ) )
            // InternalRequirementDSL.g:5136:2: ( (enumLiteral_0= 'Globally' ) | (enumLiteral_1= 'globally' ) | (enumLiteral_2= 'Always' ) | (enumLiteral_3= 'always' ) | (enumLiteral_4= 'Sometimes' ) | (enumLiteral_5= 'sometimes' ) | (enumLiteral_6= 'Eventually' ) | (enumLiteral_7= 'eventually' ) )
            {
            // InternalRequirementDSL.g:5136:2: ( (enumLiteral_0= 'Globally' ) | (enumLiteral_1= 'globally' ) | (enumLiteral_2= 'Always' ) | (enumLiteral_3= 'always' ) | (enumLiteral_4= 'Sometimes' ) | (enumLiteral_5= 'sometimes' ) | (enumLiteral_6= 'Eventually' ) | (enumLiteral_7= 'eventually' ) )
            int alt104=8;
            switch ( input.LA(1) ) {
            case 152:
                {
                alt104=1;
                }
                break;
            case 153:
                {
                alt104=2;
                }
                break;
            case 154:
                {
                alt104=3;
                }
                break;
            case 155:
                {
                alt104=4;
                }
                break;
            case 156:
                {
                alt104=5;
                }
                break;
            case 157:
                {
                alt104=6;
                }
                break;
            case 158:
                {
                alt104=7;
                }
                break;
            case 159:
                {
                alt104=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 104, 0, input);

                throw nvae;
            }

            switch (alt104) {
                case 1 :
                    // InternalRequirementDSL.g:5137:3: (enumLiteral_0= 'Globally' )
                    {
                    // InternalRequirementDSL.g:5137:3: (enumLiteral_0= 'Globally' )
                    // InternalRequirementDSL.g:5138:4: enumLiteral_0= 'Globally'
                    {
                    enumLiteral_0=(Token)match(input,152,FOLLOW_2); 

                    				current = grammarAccess.getModifierAccess().getGLOBALLYEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getModifierAccess().getGLOBALLYEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:5145:3: (enumLiteral_1= 'globally' )
                    {
                    // InternalRequirementDSL.g:5145:3: (enumLiteral_1= 'globally' )
                    // InternalRequirementDSL.g:5146:4: enumLiteral_1= 'globally'
                    {
                    enumLiteral_1=(Token)match(input,153,FOLLOW_2); 

                    				current = grammarAccess.getModifierAccess().getGLOBALLY_LEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getModifierAccess().getGLOBALLY_LEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:5153:3: (enumLiteral_2= 'Always' )
                    {
                    // InternalRequirementDSL.g:5153:3: (enumLiteral_2= 'Always' )
                    // InternalRequirementDSL.g:5154:4: enumLiteral_2= 'Always'
                    {
                    enumLiteral_2=(Token)match(input,154,FOLLOW_2); 

                    				current = grammarAccess.getModifierAccess().getALWAYSEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getModifierAccess().getALWAYSEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:5161:3: (enumLiteral_3= 'always' )
                    {
                    // InternalRequirementDSL.g:5161:3: (enumLiteral_3= 'always' )
                    // InternalRequirementDSL.g:5162:4: enumLiteral_3= 'always'
                    {
                    enumLiteral_3=(Token)match(input,155,FOLLOW_2); 

                    				current = grammarAccess.getModifierAccess().getALWAYS_LEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getModifierAccess().getALWAYS_LEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:5169:3: (enumLiteral_4= 'Sometimes' )
                    {
                    // InternalRequirementDSL.g:5169:3: (enumLiteral_4= 'Sometimes' )
                    // InternalRequirementDSL.g:5170:4: enumLiteral_4= 'Sometimes'
                    {
                    enumLiteral_4=(Token)match(input,156,FOLLOW_2); 

                    				current = grammarAccess.getModifierAccess().getSOMETIMESEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getModifierAccess().getSOMETIMESEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:5177:3: (enumLiteral_5= 'sometimes' )
                    {
                    // InternalRequirementDSL.g:5177:3: (enumLiteral_5= 'sometimes' )
                    // InternalRequirementDSL.g:5178:4: enumLiteral_5= 'sometimes'
                    {
                    enumLiteral_5=(Token)match(input,157,FOLLOW_2); 

                    				current = grammarAccess.getModifierAccess().getSOMETIMES_LEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getModifierAccess().getSOMETIMES_LEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalRequirementDSL.g:5185:3: (enumLiteral_6= 'Eventually' )
                    {
                    // InternalRequirementDSL.g:5185:3: (enumLiteral_6= 'Eventually' )
                    // InternalRequirementDSL.g:5186:4: enumLiteral_6= 'Eventually'
                    {
                    enumLiteral_6=(Token)match(input,158,FOLLOW_2); 

                    				current = grammarAccess.getModifierAccess().getEVENTUALLYEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getModifierAccess().getEVENTUALLYEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalRequirementDSL.g:5193:3: (enumLiteral_7= 'eventually' )
                    {
                    // InternalRequirementDSL.g:5193:3: (enumLiteral_7= 'eventually' )
                    // InternalRequirementDSL.g:5194:4: enumLiteral_7= 'eventually'
                    {
                    enumLiteral_7=(Token)match(input,159,FOLLOW_2); 

                    				current = grammarAccess.getModifierAccess().getEVENTUALLY_LEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_7, grammarAccess.getModifierAccess().getEVENTUALLY_LEnumLiteralDeclaration_7());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModifier"


    // $ANTLR start "ruleClauseOrdinator"
    // InternalRequirementDSL.g:5204:1: ruleClauseOrdinator returns [Enumerator current=null] : ( (enumLiteral_0= 'if' ) | (enumLiteral_1= 'after' ) | (enumLiteral_2= 'once' ) | (enumLiteral_3= 'when' ) | (enumLiteral_4= 'whenever' ) | (enumLiteral_5= 'while' ) | (enumLiteral_6= 'before' ) | (enumLiteral_7= 'until' ) | (enumLiteral_8= 'If' ) | (enumLiteral_9= 'After' ) | (enumLiteral_10= 'Once' ) | (enumLiteral_11= 'When' ) | (enumLiteral_12= 'Whenever' ) | (enumLiteral_13= 'While' ) | (enumLiteral_14= 'Before' ) | (enumLiteral_15= 'Until' ) ) ;
    public final Enumerator ruleClauseOrdinator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;
        Token enumLiteral_6=null;
        Token enumLiteral_7=null;
        Token enumLiteral_8=null;
        Token enumLiteral_9=null;
        Token enumLiteral_10=null;
        Token enumLiteral_11=null;
        Token enumLiteral_12=null;
        Token enumLiteral_13=null;
        Token enumLiteral_14=null;
        Token enumLiteral_15=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:5210:2: ( ( (enumLiteral_0= 'if' ) | (enumLiteral_1= 'after' ) | (enumLiteral_2= 'once' ) | (enumLiteral_3= 'when' ) | (enumLiteral_4= 'whenever' ) | (enumLiteral_5= 'while' ) | (enumLiteral_6= 'before' ) | (enumLiteral_7= 'until' ) | (enumLiteral_8= 'If' ) | (enumLiteral_9= 'After' ) | (enumLiteral_10= 'Once' ) | (enumLiteral_11= 'When' ) | (enumLiteral_12= 'Whenever' ) | (enumLiteral_13= 'While' ) | (enumLiteral_14= 'Before' ) | (enumLiteral_15= 'Until' ) ) )
            // InternalRequirementDSL.g:5211:2: ( (enumLiteral_0= 'if' ) | (enumLiteral_1= 'after' ) | (enumLiteral_2= 'once' ) | (enumLiteral_3= 'when' ) | (enumLiteral_4= 'whenever' ) | (enumLiteral_5= 'while' ) | (enumLiteral_6= 'before' ) | (enumLiteral_7= 'until' ) | (enumLiteral_8= 'If' ) | (enumLiteral_9= 'After' ) | (enumLiteral_10= 'Once' ) | (enumLiteral_11= 'When' ) | (enumLiteral_12= 'Whenever' ) | (enumLiteral_13= 'While' ) | (enumLiteral_14= 'Before' ) | (enumLiteral_15= 'Until' ) )
            {
            // InternalRequirementDSL.g:5211:2: ( (enumLiteral_0= 'if' ) | (enumLiteral_1= 'after' ) | (enumLiteral_2= 'once' ) | (enumLiteral_3= 'when' ) | (enumLiteral_4= 'whenever' ) | (enumLiteral_5= 'while' ) | (enumLiteral_6= 'before' ) | (enumLiteral_7= 'until' ) | (enumLiteral_8= 'If' ) | (enumLiteral_9= 'After' ) | (enumLiteral_10= 'Once' ) | (enumLiteral_11= 'When' ) | (enumLiteral_12= 'Whenever' ) | (enumLiteral_13= 'While' ) | (enumLiteral_14= 'Before' ) | (enumLiteral_15= 'Until' ) )
            int alt105=16;
            switch ( input.LA(1) ) {
            case 160:
                {
                alt105=1;
                }
                break;
            case 161:
                {
                alt105=2;
                }
                break;
            case 162:
                {
                alt105=3;
                }
                break;
            case 163:
                {
                alt105=4;
                }
                break;
            case 164:
                {
                alt105=5;
                }
                break;
            case 165:
                {
                alt105=6;
                }
                break;
            case 166:
                {
                alt105=7;
                }
                break;
            case 167:
                {
                alt105=8;
                }
                break;
            case 168:
                {
                alt105=9;
                }
                break;
            case 169:
                {
                alt105=10;
                }
                break;
            case 170:
                {
                alt105=11;
                }
                break;
            case 171:
                {
                alt105=12;
                }
                break;
            case 172:
                {
                alt105=13;
                }
                break;
            case 173:
                {
                alt105=14;
                }
                break;
            case 174:
                {
                alt105=15;
                }
                break;
            case 175:
                {
                alt105=16;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 105, 0, input);

                throw nvae;
            }

            switch (alt105) {
                case 1 :
                    // InternalRequirementDSL.g:5212:3: (enumLiteral_0= 'if' )
                    {
                    // InternalRequirementDSL.g:5212:3: (enumLiteral_0= 'if' )
                    // InternalRequirementDSL.g:5213:4: enumLiteral_0= 'if'
                    {
                    enumLiteral_0=(Token)match(input,160,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getIF_LEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getClauseOrdinatorAccess().getIF_LEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:5220:3: (enumLiteral_1= 'after' )
                    {
                    // InternalRequirementDSL.g:5220:3: (enumLiteral_1= 'after' )
                    // InternalRequirementDSL.g:5221:4: enumLiteral_1= 'after'
                    {
                    enumLiteral_1=(Token)match(input,161,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getAFTER_LEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getClauseOrdinatorAccess().getAFTER_LEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:5228:3: (enumLiteral_2= 'once' )
                    {
                    // InternalRequirementDSL.g:5228:3: (enumLiteral_2= 'once' )
                    // InternalRequirementDSL.g:5229:4: enumLiteral_2= 'once'
                    {
                    enumLiteral_2=(Token)match(input,162,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getONCE_LEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getClauseOrdinatorAccess().getONCE_LEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:5236:3: (enumLiteral_3= 'when' )
                    {
                    // InternalRequirementDSL.g:5236:3: (enumLiteral_3= 'when' )
                    // InternalRequirementDSL.g:5237:4: enumLiteral_3= 'when'
                    {
                    enumLiteral_3=(Token)match(input,163,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getWHEN_LEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getClauseOrdinatorAccess().getWHEN_LEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:5244:3: (enumLiteral_4= 'whenever' )
                    {
                    // InternalRequirementDSL.g:5244:3: (enumLiteral_4= 'whenever' )
                    // InternalRequirementDSL.g:5245:4: enumLiteral_4= 'whenever'
                    {
                    enumLiteral_4=(Token)match(input,164,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getWHENEVER_LEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getClauseOrdinatorAccess().getWHENEVER_LEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:5252:3: (enumLiteral_5= 'while' )
                    {
                    // InternalRequirementDSL.g:5252:3: (enumLiteral_5= 'while' )
                    // InternalRequirementDSL.g:5253:4: enumLiteral_5= 'while'
                    {
                    enumLiteral_5=(Token)match(input,165,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getWHILE_LEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getClauseOrdinatorAccess().getWHILE_LEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalRequirementDSL.g:5260:3: (enumLiteral_6= 'before' )
                    {
                    // InternalRequirementDSL.g:5260:3: (enumLiteral_6= 'before' )
                    // InternalRequirementDSL.g:5261:4: enumLiteral_6= 'before'
                    {
                    enumLiteral_6=(Token)match(input,166,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getBEFORE_LEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getClauseOrdinatorAccess().getBEFORE_LEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalRequirementDSL.g:5268:3: (enumLiteral_7= 'until' )
                    {
                    // InternalRequirementDSL.g:5268:3: (enumLiteral_7= 'until' )
                    // InternalRequirementDSL.g:5269:4: enumLiteral_7= 'until'
                    {
                    enumLiteral_7=(Token)match(input,167,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getUNTIL_LEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_7, grammarAccess.getClauseOrdinatorAccess().getUNTIL_LEnumLiteralDeclaration_7());
                    			

                    }


                    }
                    break;
                case 9 :
                    // InternalRequirementDSL.g:5276:3: (enumLiteral_8= 'If' )
                    {
                    // InternalRequirementDSL.g:5276:3: (enumLiteral_8= 'If' )
                    // InternalRequirementDSL.g:5277:4: enumLiteral_8= 'If'
                    {
                    enumLiteral_8=(Token)match(input,168,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getIFEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_8, grammarAccess.getClauseOrdinatorAccess().getIFEnumLiteralDeclaration_8());
                    			

                    }


                    }
                    break;
                case 10 :
                    // InternalRequirementDSL.g:5284:3: (enumLiteral_9= 'After' )
                    {
                    // InternalRequirementDSL.g:5284:3: (enumLiteral_9= 'After' )
                    // InternalRequirementDSL.g:5285:4: enumLiteral_9= 'After'
                    {
                    enumLiteral_9=(Token)match(input,169,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getAFTEREnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_9, grammarAccess.getClauseOrdinatorAccess().getAFTEREnumLiteralDeclaration_9());
                    			

                    }


                    }
                    break;
                case 11 :
                    // InternalRequirementDSL.g:5292:3: (enumLiteral_10= 'Once' )
                    {
                    // InternalRequirementDSL.g:5292:3: (enumLiteral_10= 'Once' )
                    // InternalRequirementDSL.g:5293:4: enumLiteral_10= 'Once'
                    {
                    enumLiteral_10=(Token)match(input,170,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getONCEEnumLiteralDeclaration_10().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_10, grammarAccess.getClauseOrdinatorAccess().getONCEEnumLiteralDeclaration_10());
                    			

                    }


                    }
                    break;
                case 12 :
                    // InternalRequirementDSL.g:5300:3: (enumLiteral_11= 'When' )
                    {
                    // InternalRequirementDSL.g:5300:3: (enumLiteral_11= 'When' )
                    // InternalRequirementDSL.g:5301:4: enumLiteral_11= 'When'
                    {
                    enumLiteral_11=(Token)match(input,171,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getWHENEnumLiteralDeclaration_11().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_11, grammarAccess.getClauseOrdinatorAccess().getWHENEnumLiteralDeclaration_11());
                    			

                    }


                    }
                    break;
                case 13 :
                    // InternalRequirementDSL.g:5308:3: (enumLiteral_12= 'Whenever' )
                    {
                    // InternalRequirementDSL.g:5308:3: (enumLiteral_12= 'Whenever' )
                    // InternalRequirementDSL.g:5309:4: enumLiteral_12= 'Whenever'
                    {
                    enumLiteral_12=(Token)match(input,172,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getWHENEVEREnumLiteralDeclaration_12().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_12, grammarAccess.getClauseOrdinatorAccess().getWHENEVEREnumLiteralDeclaration_12());
                    			

                    }


                    }
                    break;
                case 14 :
                    // InternalRequirementDSL.g:5316:3: (enumLiteral_13= 'While' )
                    {
                    // InternalRequirementDSL.g:5316:3: (enumLiteral_13= 'While' )
                    // InternalRequirementDSL.g:5317:4: enumLiteral_13= 'While'
                    {
                    enumLiteral_13=(Token)match(input,173,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getWHILEEnumLiteralDeclaration_13().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_13, grammarAccess.getClauseOrdinatorAccess().getWHILEEnumLiteralDeclaration_13());
                    			

                    }


                    }
                    break;
                case 15 :
                    // InternalRequirementDSL.g:5324:3: (enumLiteral_14= 'Before' )
                    {
                    // InternalRequirementDSL.g:5324:3: (enumLiteral_14= 'Before' )
                    // InternalRequirementDSL.g:5325:4: enumLiteral_14= 'Before'
                    {
                    enumLiteral_14=(Token)match(input,174,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getBEFOREEnumLiteralDeclaration_14().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_14, grammarAccess.getClauseOrdinatorAccess().getBEFOREEnumLiteralDeclaration_14());
                    			

                    }


                    }
                    break;
                case 16 :
                    // InternalRequirementDSL.g:5332:3: (enumLiteral_15= 'Until' )
                    {
                    // InternalRequirementDSL.g:5332:3: (enumLiteral_15= 'Until' )
                    // InternalRequirementDSL.g:5333:4: enumLiteral_15= 'Until'
                    {
                    enumLiteral_15=(Token)match(input,175,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getUNTILEnumLiteralDeclaration_15().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_15, grammarAccess.getClauseOrdinatorAccess().getUNTILEnumLiteralDeclaration_15());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleClauseOrdinator"

    // Delegated rules


    protected DFA10 dfa10 = new DFA10(this);
    protected DFA21 dfa21 = new DFA21(this);
    protected DFA29 dfa29 = new DFA29(this);
    protected DFA35 dfa35 = new DFA35(this);
    protected DFA33 dfa33 = new DFA33(this);
    protected DFA39 dfa39 = new DFA39(this);
    protected DFA41 dfa41 = new DFA41(this);
    protected DFA49 dfa49 = new DFA49(this);
    protected DFA66 dfa66 = new DFA66(this);
    static final String dfa_1s = "\u00ce\uffff";
    static final String dfa_2s = "\1\5\13\34\30\5\2\4\1\uffff\1\51\1\7\2\5\3\uffff\4\5\1\4\30\5\2\4\30\5\2\4\2\7\4\5\2\4\1\7\1\21\30\5\2\4\32\5\2\7\1\5\1\7\2\5\1\7\1\4\1\7\1\21\33\5\3\7\1\5";
    static final String dfa_3s = "\1\137\13\34\30\7\2\u0097\1\uffff\1\54\1\7\2\137\3\uffff\4\137\1\u0097\30\7\2\u0097\30\7\2\50\3\7\3\137\1\u0097\3\50\30\7\2\50\30\7\2\u0097\4\7\2\137\4\50\1\u0097\30\7\2\u0097\2\7\1\50\1\u0097";
    static final String dfa_4s = "\46\uffff\1\3\4\uffff\1\1\1\2\1\4\u00a0\uffff";
    static final String dfa_5s = "\u00ce\uffff}>";
    static final String[] dfa_6s = {
            "\1\45\1\uffff\1\44\13\uffff\2\46\36\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\5\uffff\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31\5\uffff\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\41\1\42\1\43",
            "\1\47",
            "\1\47",
            "\1\47",
            "\1\47",
            "\1\47",
            "\1\47",
            "\1\47",
            "\1\47",
            "\1\47",
            "\1\47",
            "\1\47",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\55\1\54\1\uffff\1\54\23\uffff\1\50\1\uffff\10\54\1\51\1\52\34\uffff\16\54\1\uffff\16\54\61\uffff\7\53",
            "\1\55\1\54\1\uffff\1\54\25\uffff\10\54\1\51\1\52\34\uffff\16\54\1\uffff\16\54\61\uffff\7\53",
            "",
            "\1\56\1\57\1\60\1\61",
            "\1\62",
            "\1\114\1\uffff\1\113\73\uffff\1\63\1\64\1\65\1\66\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100\5\uffff\1\101\1\102\1\103\1\104\1\105\1\106\1\107\1\110\1\111\1\112",
            "\1\114\1\uffff\1\113\73\uffff\1\63\1\64\1\65\1\66\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100\5\uffff\1\101\1\102\1\103\1\104\1\105\1\106\1\107\1\110\1\111\1\112",
            "",
            "",
            "",
            "\1\146\1\uffff\1\145\73\uffff\1\115\1\116\1\117\1\120\1\121\1\122\1\123\1\124\1\125\1\126\1\127\1\130\1\131\1\132\5\uffff\1\133\1\134\1\135\1\136\1\137\1\140\1\141\1\142\1\143\1\144",
            "\1\146\1\uffff\1\145\73\uffff\1\115\1\116\1\117\1\120\1\121\1\122\1\123\1\124\1\125\1\126\1\127\1\130\1\131\1\132\5\uffff\1\133\1\134\1\135\1\136\1\137\1\140\1\141\1\142\1\143\1\144",
            "\1\146\1\uffff\1\145\73\uffff\1\115\1\116\1\117\1\120\1\121\1\122\1\123\1\124\1\125\1\126\1\127\1\130\1\131\1\132\5\uffff\1\133\1\134\1\135\1\136\1\137\1\140\1\141\1\142\1\143\1\144",
            "\1\146\1\uffff\1\145\73\uffff\1\115\1\116\1\117\1\120\1\121\1\122\1\123\1\124\1\125\1\126\1\127\1\130\1\131\1\132\5\uffff\1\133\1\134\1\135\1\136\1\137\1\140\1\141\1\142\1\143\1\144",
            "\1\55\1\54\1\uffff\1\54\23\uffff\1\50\1\uffff\10\54\1\51\1\52\34\uffff\16\54\1\uffff\16\54\61\uffff\7\53",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\114\1\uffff\1\113",
            "\1\55\1\54\1\uffff\1\54\23\uffff\1\147\1\uffff\10\54\1\51\1\52\34\uffff\16\54\1\uffff\16\54\61\uffff\7\53",
            "\1\55\1\54\1\uffff\1\54\25\uffff\10\54\1\51\1\52\34\uffff\16\54\1\uffff\16\54\61\uffff\7\53",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\146\1\uffff\1\145",
            "\1\151\2\uffff\1\145\11\uffff\1\154\11\uffff\1\150\13\uffff\1\152\1\153",
            "\1\151\14\uffff\1\154\25\uffff\1\152\1\153",
            "\1\155",
            "\1\156",
            "\1\160\1\uffff\1\157",
            "\1\u008a\1\uffff\1\u0089\73\uffff\1\161\1\162\1\163\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\5\uffff\1\177\1\u0080\1\u0081\1\u0082\1\u0083\1\u0084\1\u0085\1\u0086\1\u0087\1\u0088",
            "\1\u008a\1\uffff\1\u0089\73\uffff\1\161\1\162\1\163\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\5\uffff\1\177\1\u0080\1\u0081\1\u0082\1\u0083\1\u0084\1\u0085\1\u0086\1\u0087\1\u0088",
            "\1\u00a4\1\uffff\1\u00a3\73\uffff\1\u008b\1\u008c\1\u008d\1\u008e\1\u008f\1\u0090\1\u0091\1\u0092\1\u0093\1\u0094\1\u0095\1\u0096\1\u0097\1\u0098\5\uffff\1\u0099\1\u009a\1\u009b\1\u009c\1\u009d\1\u009e\1\u009f\1\u00a0\1\u00a1\1\u00a2",
            "\1\55\1\54\1\uffff\1\54\23\uffff\1\147\1\uffff\10\54\1\51\1\52\34\uffff\16\54\1\uffff\16\54\61\uffff\7\53",
            "\1\151\2\uffff\1\145\11\uffff\1\154\11\uffff\1\150\13\uffff\1\152\1\153",
            "\1\157\11\uffff\1\154\11\uffff\1\u00a5\13\uffff\1\152\1\153",
            "\1\154\25\uffff\1\152\1\153",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u008a\1\uffff\1\u0089",
            "\1\u00a7\2\uffff\1\u0089\11\uffff\1\154\11\uffff\1\u00a6\13\uffff\1\152\1\153",
            "\1\u00a7\14\uffff\1\154\25\uffff\1\152\1\153",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\u00a4\1\uffff\1\u00a3",
            "\1\54\1\uffff\1\54\23\uffff\1\u00a8\1\uffff\10\54\1\u00a9\1\u00aa\34\uffff\16\54\1\uffff\16\54\61\uffff\7\53",
            "\1\54\1\uffff\1\54\25\uffff\10\54\1\u00a9\1\u00aa\34\uffff\16\54\1\uffff\16\54\61\uffff\7\53",
            "\1\u00ab",
            "\1\u00ac",
            "\1\u00ae\1\uffff\1\u00ad",
            "\1\u00af",
            "\1\u00c9\1\uffff\1\u00c8\73\uffff\1\u00b0\1\u00b1\1\u00b2\1\u00b3\1\u00b4\1\u00b5\1\u00b6\1\u00b7\1\u00b8\1\u00b9\1\u00ba\1\u00bb\1\u00bc\1\u00bd\5\uffff\1\u00be\1\u00bf\1\u00c0\1\u00c1\1\u00c2\1\u00c3\1\u00c4\1\u00c5\1\u00c6\1\u00c7",
            "\1\u00c9\1\uffff\1\u00c8\73\uffff\1\u00b0\1\u00b1\1\u00b2\1\u00b3\1\u00b4\1\u00b5\1\u00b6\1\u00b7\1\u00b8\1\u00b9\1\u00ba\1\u00bb\1\u00bc\1\u00bd\5\uffff\1\u00be\1\u00bf\1\u00c0\1\u00c1\1\u00c2\1\u00c3\1\u00c4\1\u00c5\1\u00c6\1\u00c7",
            "\1\157\11\uffff\1\154\11\uffff\1\u00a5\13\uffff\1\152\1\153",
            "\1\u00a7\2\uffff\1\u0089\11\uffff\1\154\11\uffff\1\u00a6\13\uffff\1\152\1\153",
            "\1\u00ad\11\uffff\1\154\11\uffff\1\u00ca\13\uffff\1\152\1\153",
            "\1\154\25\uffff\1\152\1\153",
            "\1\54\1\uffff\1\54\23\uffff\1\u00a8\1\uffff\10\54\1\u00a9\1\u00aa\34\uffff\16\54\1\uffff\16\54\61\uffff\7\53",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\u00c9\1\uffff\1\u00c8",
            "\1\54\1\uffff\1\54\23\uffff\1\u00cb\1\uffff\10\54\1\u00a9\1\u00aa\34\uffff\16\54\1\uffff\16\54\61\uffff\7\53",
            "\1\54\1\uffff\1\54\25\uffff\10\54\1\u00a9\1\u00aa\34\uffff\16\54\1\uffff\16\54\61\uffff\7\53",
            "\1\u00cc",
            "\1\u00cd",
            "\1\u00ad\11\uffff\1\154\11\uffff\1\u00ca\13\uffff\1\152\1\153",
            "\1\54\1\uffff\1\54\23\uffff\1\u00cb\1\uffff\10\54\1\u00a9\1\u00aa\34\uffff\16\54\1\uffff\16\54\61\uffff\7\53"
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "479:2: (this_ModalitySentence_0= ruleModalitySentence | this_PredicateSentence_1= rulePredicateSentence | this_ExistenceSentence_2= ruleExistenceSentence | this_PropertySentence_3= rulePropertySentence )";
        }
    }
    static final String dfa_7s = "\u0092\uffff";
    static final String dfa_8s = "\1\5\13\34\32\5\1\51\1\7\2\5\2\uffff\67\5\2\4\2\7\5\5\1\4\1\7\1\21\30\5\2\4\2\7\1\5\1\7\1\4\1\7\1\21\2\7";
    static final String dfa_9s = "\1\137\13\34\30\7\2\137\1\54\1\7\2\137\2\uffff\5\137\30\7\2\137\30\7\2\50\3\7\4\137\3\50\30\7\2\50\3\7\4\50\1\7\1\50";
    static final String dfa_10s = "\52\uffff\1\2\1\1\146\uffff";
    static final String dfa_11s = "\u0092\uffff}>";
    static final String[] dfa_12s = {
            "\1\45\1\uffff\1\44\53\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\5\uffff\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31\5\uffff\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\41\1\42\1\43",
            "\1\46",
            "\1\46",
            "\1\46",
            "\1\46",
            "\1\46",
            "\1\46",
            "\1\46",
            "\1\46",
            "\1\46",
            "\1\46",
            "\1\46",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\52\1\uffff\1\52\23\uffff\1\47\1\uffff\10\53\1\50\1\51\34\uffff\16\52\1\uffff\4\53\12\52",
            "\1\52\1\uffff\1\52\25\uffff\10\53\1\50\1\51\34\uffff\16\52\1\uffff\4\53\12\52",
            "\1\54\1\55\1\56\1\57",
            "\1\60",
            "\1\112\1\uffff\1\111\73\uffff\1\61\1\62\1\63\1\64\1\65\1\66\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\5\uffff\1\77\1\100\1\101\1\102\1\103\1\104\1\105\1\106\1\107\1\110",
            "\1\112\1\uffff\1\111\73\uffff\1\61\1\62\1\63\1\64\1\65\1\66\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\5\uffff\1\77\1\100\1\101\1\102\1\103\1\104\1\105\1\106\1\107\1\110",
            "",
            "",
            "\1\144\1\uffff\1\143\73\uffff\1\113\1\114\1\115\1\116\1\117\1\120\1\121\1\122\1\123\1\124\1\125\1\126\1\127\1\130\5\uffff\1\131\1\132\1\133\1\134\1\135\1\136\1\137\1\140\1\141\1\142",
            "\1\144\1\uffff\1\143\73\uffff\1\113\1\114\1\115\1\116\1\117\1\120\1\121\1\122\1\123\1\124\1\125\1\126\1\127\1\130\5\uffff\1\131\1\132\1\133\1\134\1\135\1\136\1\137\1\140\1\141\1\142",
            "\1\144\1\uffff\1\143\73\uffff\1\113\1\114\1\115\1\116\1\117\1\120\1\121\1\122\1\123\1\124\1\125\1\126\1\127\1\130\5\uffff\1\131\1\132\1\133\1\134\1\135\1\136\1\137\1\140\1\141\1\142",
            "\1\144\1\uffff\1\143\73\uffff\1\113\1\114\1\115\1\116\1\117\1\120\1\121\1\122\1\123\1\124\1\125\1\126\1\127\1\130\5\uffff\1\131\1\132\1\133\1\134\1\135\1\136\1\137\1\140\1\141\1\142",
            "\1\52\1\uffff\1\52\23\uffff\1\47\1\uffff\10\53\1\50\1\51\34\uffff\16\52\1\uffff\4\53\12\52",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\52\1\uffff\1\52\23\uffff\1\145\1\uffff\10\53\1\50\1\51\34\uffff\16\52\1\uffff\4\53\12\52",
            "\1\52\1\uffff\1\52\25\uffff\10\53\1\50\1\51\34\uffff\16\52\1\uffff\4\53\12\52",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\147\2\uffff\1\143\11\uffff\1\152\11\uffff\1\146\13\uffff\1\150\1\151",
            "\1\147\14\uffff\1\152\25\uffff\1\150\1\151",
            "\1\153",
            "\1\154",
            "\1\156\1\uffff\1\155",
            "\1\u0088\1\uffff\1\u0087\73\uffff\1\157\1\160\1\161\1\162\1\163\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\5\uffff\1\175\1\176\1\177\1\u0080\1\u0081\1\u0082\1\u0083\1\u0084\1\u0085\1\u0086",
            "\1\u0088\1\uffff\1\u0087\73\uffff\1\157\1\160\1\161\1\162\1\163\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\5\uffff\1\175\1\176\1\177\1\u0080\1\u0081\1\u0082\1\u0083\1\u0084\1\u0085\1\u0086",
            "\1\45\1\uffff\1\44\73\uffff\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31\5\uffff\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\41\1\42\1\43",
            "\1\52\1\uffff\1\52\23\uffff\1\145\1\uffff\10\53\1\50\1\51\34\uffff\16\52\1\uffff\4\53\12\52",
            "\1\147\2\uffff\1\143\11\uffff\1\152\11\uffff\1\146\13\uffff\1\150\1\151",
            "\1\155\11\uffff\1\152\11\uffff\1\u0089\13\uffff\1\150\1\151",
            "\1\152\25\uffff\1\150\1\151",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u008b\2\uffff\1\u0087\11\uffff\1\152\11\uffff\1\u008a\13\uffff\1\150\1\151",
            "\1\u008b\14\uffff\1\152\25\uffff\1\150\1\151",
            "\1\u008c",
            "\1\u008d",
            "\1\u008f\1\uffff\1\u008e",
            "\1\155\11\uffff\1\152\11\uffff\1\u0089\13\uffff\1\150\1\151",
            "\1\u008b\2\uffff\1\u0087\11\uffff\1\152\11\uffff\1\u008a\13\uffff\1\150\1\151",
            "\1\u008e\11\uffff\1\152\11\uffff\1\u0090\13\uffff\1\150\1\151",
            "\1\152\25\uffff\1\150\1\151",
            "\1\u0091",
            "\1\u008e\11\uffff\1\152\11\uffff\1\u0090\13\uffff\1\150\1\151"
    };

    static final short[] dfa_7 = DFA.unpackEncodedString(dfa_7s);
    static final char[] dfa_8 = DFA.unpackEncodedStringToUnsignedChars(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final short[] dfa_10 = DFA.unpackEncodedString(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[][] dfa_12 = unpackEncodedStringArray(dfa_12s);

    class DFA21 extends DFA {

        public DFA21(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 21;
            this.eot = dfa_7;
            this.eof = dfa_7;
            this.min = dfa_8;
            this.max = dfa_9;
            this.accept = dfa_10;
            this.special = dfa_11;
            this.transition = dfa_12;
        }
        public String getDescription() {
            return "685:2: ( ( ( (lv_begin_0_0= ruleSentenceBegin ) )? ( (lv_actors_1_0= ruleActors ) ) ( (lv_auxNeg_2_0= ruleAuxNeg ) ) ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )? ( (lv_preds_4_0= rulePreds ) )? ( (lv_ending_5_0= ruleSentenceEnding ) )? ) | ( ( (lv_begin_6_0= ruleSentenceBegin ) )? ( (lv_actors_7_0= ruleActors ) ) ( (lv_preds_8_0= rulePreds ) ) ( (lv_ending_9_0= ruleSentenceEnding ) )? ) )";
        }
    }
    static final String dfa_13s = "\u0096\uffff";
    static final String dfa_14s = "\31\5\2\4\1\7\3\5\1\4\30\5\2\4\1\7\1\35\2\7\13\34\2\uffff\1\4\1\7\1\51\34\5\2\4\1\7\3\5\1\4\1\7\1\35\30\5\2\4\2\7\1\5\1\7\1\4\1\7\1\35\2\7";
    static final String dfa_15s = "\1\137\30\7\2\46\1\7\2\137\1\7\1\46\30\7\2\46\2\u0097\2\7\13\34\2\uffff\1\46\1\u0097\1\54\4\137\30\7\2\u0097\2\7\2\137\3\u0097\30\7\2\u0097\3\7\4\u0097\1\7\1\u0097";
    static final String dfa_16s = "\111\uffff\1\2\1\1\113\uffff";
    static final String dfa_17s = "\u0096\uffff}>";
    static final String[] dfa_18s = {
            "\1\32\1\uffff\1\31\73\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\5\uffff\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\32\1\uffff\1\31",
            "\1\36\26\uffff\1\33\11\uffff\1\34\1\35",
            "\1\36\40\uffff\1\34\1\35",
            "\1\37",
            "\1\71\1\uffff\1\70\73\uffff\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52\1\53\1\54\1\55\5\uffff\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67",
            "\1\71\1\uffff\1\70\73\uffff\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52\1\53\1\54\1\55\5\uffff\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67",
            "\1\73\1\uffff\1\72",
            "\1\36\26\uffff\1\33\11\uffff\1\34\1\35",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\71\1\uffff\1\70",
            "\1\36\26\uffff\1\74\11\uffff\1\34\1\35",
            "\1\36\40\uffff\1\34\1\35",
            "\1\72\23\uffff\1\75\1\uffff\10\111\16\uffff\1\76\1\77\1\100\1\101\1\102\1\103\1\104\1\105\1\106\1\107\1\110\24\uffff\4\111\73\uffff\7\112",
            "\10\111\16\uffff\1\76\1\77\1\100\1\101\1\102\1\103\1\104\1\105\1\106\1\107\1\110\24\uffff\4\111\73\uffff\7\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "\1\115",
            "\1\115",
            "\1\115",
            "\1\115",
            "\1\115",
            "\1\115",
            "\1\115",
            "\1\115",
            "\1\115",
            "\1\115",
            "",
            "",
            "\1\36\26\uffff\1\74\11\uffff\1\34\1\35",
            "\1\72\23\uffff\1\75\1\uffff\10\111\16\uffff\1\76\1\77\1\100\1\101\1\102\1\103\1\104\1\105\1\106\1\107\1\110\24\uffff\4\111\73\uffff\7\112",
            "\1\116\1\117\1\120\1\121",
            "\1\153\1\uffff\1\152\73\uffff\1\122\1\123\1\124\1\125\1\126\1\127\1\130\1\131\1\132\1\133\1\134\1\135\1\136\1\137\5\uffff\1\140\1\141\1\142\1\143\1\144\1\145\1\146\1\147\1\150\1\151",
            "\1\153\1\uffff\1\152\73\uffff\1\122\1\123\1\124\1\125\1\126\1\127\1\130\1\131\1\132\1\133\1\134\1\135\1\136\1\137\5\uffff\1\140\1\141\1\142\1\143\1\144\1\145\1\146\1\147\1\150\1\151",
            "\1\153\1\uffff\1\152\73\uffff\1\122\1\123\1\124\1\125\1\126\1\127\1\130\1\131\1\132\1\133\1\134\1\135\1\136\1\137\5\uffff\1\140\1\141\1\142\1\143\1\144\1\145\1\146\1\147\1\150\1\151",
            "\1\153\1\uffff\1\152\73\uffff\1\122\1\123\1\124\1\125\1\126\1\127\1\130\1\131\1\132\1\133\1\134\1\135\1\136\1\137\5\uffff\1\140\1\141\1\142\1\143\1\144\1\145\1\146\1\147\1\150\1\151",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\153\1\uffff\1\152",
            "\1\155\2\uffff\1\152\23\uffff\1\154\1\uffff\10\111\2\uffff\1\156\1\157\51\uffff\4\111\73\uffff\7\112",
            "\1\155\30\uffff\10\111\2\uffff\1\156\1\157\51\uffff\4\111\73\uffff\7\112",
            "\1\160",
            "\1\162\1\uffff\1\161",
            "\1\u008c\1\uffff\1\u008b\73\uffff\1\163\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\177\1\u0080\5\uffff\1\u0081\1\u0082\1\u0083\1\u0084\1\u0085\1\u0086\1\u0087\1\u0088\1\u0089\1\u008a",
            "\1\u008c\1\uffff\1\u008b\73\uffff\1\163\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\177\1\u0080\5\uffff\1\u0081\1\u0082\1\u0083\1\u0084\1\u0085\1\u0086\1\u0087\1\u0088\1\u0089\1\u008a",
            "\1\155\2\uffff\1\152\23\uffff\1\154\1\uffff\10\111\2\uffff\1\156\1\157\51\uffff\4\111\73\uffff\7\112",
            "\1\161\23\uffff\1\u008d\1\uffff\10\111\2\uffff\1\156\1\157\51\uffff\4\111\73\uffff\7\112",
            "\10\111\2\uffff\1\156\1\157\51\uffff\4\111\73\uffff\7\112",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008c\1\uffff\1\u008b",
            "\1\u008f\2\uffff\1\u008b\23\uffff\1\u008e\1\uffff\10\111\2\uffff\1\156\1\157\51\uffff\4\111\73\uffff\7\112",
            "\1\u008f\30\uffff\10\111\2\uffff\1\156\1\157\51\uffff\4\111\73\uffff\7\112",
            "\1\u0090",
            "\1\u0091",
            "\1\u0093\1\uffff\1\u0092",
            "\1\161\23\uffff\1\u008d\1\uffff\10\111\2\uffff\1\156\1\157\51\uffff\4\111\73\uffff\7\112",
            "\1\u008f\2\uffff\1\u008b\23\uffff\1\u008e\1\uffff\10\111\2\uffff\1\156\1\157\51\uffff\4\111\73\uffff\7\112",
            "\1\u0092\23\uffff\1\u0094\1\uffff\10\111\2\uffff\1\156\1\157\51\uffff\4\111\73\uffff\7\112",
            "\10\111\2\uffff\1\156\1\157\51\uffff\4\111\73\uffff\7\112",
            "\1\u0095",
            "\1\u0092\23\uffff\1\u0094\1\uffff\10\111\2\uffff\1\156\1\157\51\uffff\4\111\73\uffff\7\112"
    };

    static final short[] dfa_13 = DFA.unpackEncodedString(dfa_13s);
    static final char[] dfa_14 = DFA.unpackEncodedStringToUnsignedChars(dfa_14s);
    static final char[] dfa_15 = DFA.unpackEncodedStringToUnsignedChars(dfa_15s);
    static final short[] dfa_16 = DFA.unpackEncodedString(dfa_16s);
    static final short[] dfa_17 = DFA.unpackEncodedString(dfa_17s);
    static final short[][] dfa_18 = unpackEncodedStringArray(dfa_18s);

    class DFA29 extends DFA {

        public DFA29(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 29;
            this.eot = dfa_13;
            this.eof = dfa_13;
            this.min = dfa_14;
            this.max = dfa_15;
            this.accept = dfa_16;
            this.special = dfa_17;
            this.transition = dfa_18;
        }
        public String getDescription() {
            return "972:2: ( ( ( (lv_actors_0_0= ruleActors ) ) ( (lv_property_1_0= ruleProperty ) ) ( (lv_rela_2_0= ruleRelation ) )? ( (lv_modality_3_0= ruleModality ) ) ( (lv_negation_4_0= ruleNegation ) )? ( (lv_auxiliarVerb_5_0= ruleAuxiliaryVerb ) )? ( (lv_predObj_6_0= rulePredOrObject ) ) ( (lv_ending_7_0= ruleSentenceEnding ) )? ) | ( ( (lv_actors_8_0= ruleActors ) ) ( (lv_property_9_0= ruleProperty ) ) ( (lv_rela_10_0= ruleRelation ) )? ( (lv_auxNeg_11_0= ruleAuxNeg ) ) ( ( (lv_predObj_12_0= rulePredOrObject ) ) | ( (lv_constraints_13_0= ruleConstraints ) ) ) ( (lv_ending_14_0= ruleSentenceEnding ) )? ) )";
        }
    }
    static final String dfa_19s = "\u0203\uffff";
    static final String dfa_20s = "\50\uffff\1\177\30\uffff\2\177\3\uffff\56\177\55\uffff\1\177\2\uffff\1\177\64\uffff\1\177\2\uffff\1\177\30\uffff\2\u00a3\31\177\131\uffff\1\177\63\uffff\3\u00a3\30\uffff\2\u00a3\1\177\76\uffff\4\u00a3\5\uffff\1\u00a3";
    static final String dfa_21s = "\2\53\46\5\1\7\30\5\1\7\1\17\1\6\1\5\1\51\1\6\25\17\30\7\13\5\1\uffff\3\7\30\5\2\20\4\5\2\7\1\uffff\1\7\1\6\30\7\2\6\30\7\1\6\1\17\1\7\1\5\1\17\30\5\2\4\37\7\1\20\30\5\2\20\1\7\3\5\1\uffff\32\7\1\6\30\7\1\17\31\7\1\6\31\7\1\4\1\7\1\17\30\5\2\4\7\7\1\20\2\7\1\5\65\7\1\4\1\7\1\17\6\7";
    static final String dfa_22s = "\1\140\1\102\46\137\1\u00af\30\7\2\u00af\1\6\1\137\1\54\1\15\55\u00af\13\137\1\uffff\1\7\2\173\30\7\1\33\1\32\4\137\1\u00af\1\7\1\uffff\1\u00af\1\6\27\21\1\33\2\6\27\32\1\33\1\6\1\u00af\1\7\1\137\1\u00af\30\7\33\u00af\1\173\1\7\2\173\1\7\1\173\1\33\30\7\1\33\1\32\2\7\2\137\1\uffff\1\7\27\21\2\33\1\6\27\30\1\33\1\u00af\27\32\2\33\1\6\27\32\1\33\1\7\3\u00af\30\7\3\u00af\1\7\1\173\2\7\1\173\1\7\1\33\3\7\1\33\27\30\3\33\27\32\2\33\4\u00af\3\7\2\33\1\u00af";
    static final String dfa_23s = "\177\uffff\1\1\43\uffff\1\2\u0091\uffff\1\3\u00cd\uffff";
    static final String dfa_24s = "\u0203\uffff}>";
    static final String[] dfa_25s = {
            "\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1",
            "\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\31\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\41\1\42\1\43\1\23\1\24\1\25\1\26\1\27",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\105\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\105\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\105\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\105\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\105\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\105\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\105\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\105\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\105\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\105\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\105\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\17\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\51\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\51\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\51\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\51\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\163\7\uffff\1\106\3\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\4\uffff\1\157\1\160\1\161\1\162\1\153\1\154\1\155\1\156\1\147\1\150\1\151\1\152\1\145\1\146\1\142\1\143\1\144\1\134\1\135\1\136\1\137\1\140\1\141\1\107\1\110\1\111\1\112\1\113\1\114\1\115\1\116\1\117\1\120\1\121\1\122\1\123\1\124\1\125\1\126\1\127\1\130\1\131\1\132\1\133\17\uffff\20\177",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\102\1\uffff\1\101",
            "\1\101\7\uffff\4\177\10\uffff\1\u0080\11\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u0081",
            "\1\u009c\1\u0082\1\u009b\73\uffff\1\u0083\1\u0084\1\u0085\1\u0086\1\u0087\1\u0088\1\u0089\1\u008a\1\u008b\1\u008c\1\u008d\1\u008e\1\u008f\1\u0090\5\uffff\1\u0091\1\u0092\1\u0093\1\u0094\1\u0095\1\u0096\1\u0097\1\u0098\1\u0099\1\u009a",
            "\1\u009d\1\u009e\1\u009f\1\u00a0",
            "\1\u00a1\6\uffff\1\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\10\uffff\1\u00a2\11\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\u00a3\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\u00a3\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\u00a3\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\u00a3\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\u00a3\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\u00a3\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\u00a3\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\u00a3\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\u00a3\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\u00a3\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "\1\102\1\50\1\101\17\uffff\1\103\1\uffff\1\104\2\uffff\1\u00a3\14\uffff\1\44\1\45\1\46\1\47\26\uffff\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\5\uffff\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77\1\100",
            "",
            "\1\u00a4",
            "\1\u00bd\7\uffff\1\u00a5\1\uffff\1\u00be\123\uffff\1\u00b9\1\u00ba\1\u00bb\1\u00bc\1\u00b5\1\u00b6\1\u00b7\1\u00b8\1\u00b1\1\u00b2\1\u00b3\1\u00b4\1\u00af\1\u00b0\1\u00ac\1\u00ad\1\u00ae\1\u00a6\1\u00a7\1\u00a8\1\u00a9\1\u00aa\1\u00ab",
            "\1\u00d7\7\uffff\1\u00bf\1\u00d8\11\uffff\1\u00d9\112\uffff\1\u00d3\1\u00d4\1\u00d5\1\u00d6\1\u00cf\1\u00d0\1\u00d1\1\u00d2\1\u00cb\1\u00cc\1\u00cd\1\u00ce\1\u00c9\1\u00ca\1\u00c6\1\u00c7\1\u00c8\1\u00c0\1\u00c1\1\u00c2\1\u00c3\1\u00c4\1\u00c5",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u009c\1\uffff\1\u009b",
            "\1\u00db\11\uffff\1\u00dc\1\u00da",
            "\1\u00db\11\uffff\1\u00dc",
            "\1\u00f6\1\uffff\1\u00f5\73\uffff\1\u00dd\1\u00de\1\u00df\1\u00e0\1\u00e1\1\u00e2\1\u00e3\1\u00e4\1\u00e5\1\u00e6\1\u00e7\1\u00e8\1\u00e9\1\u00ea\5\uffff\1\u00eb\1\u00ec\1\u00ed\1\u00ee\1\u00ef\1\u00f0\1\u00f1\1\u00f2\1\u00f3\1\u00f4",
            "\1\u00f6\1\uffff\1\u00f5\73\uffff\1\u00dd\1\u00de\1\u00df\1\u00e0\1\u00e1\1\u00e2\1\u00e3\1\u00e4\1\u00e5\1\u00e6\1\u00e7\1\u00e8\1\u00e9\1\u00ea\5\uffff\1\u00eb\1\u00ec\1\u00ed\1\u00ee\1\u00ef\1\u00f0\1\u00f1\1\u00f2\1\u00f3\1\u00f4",
            "\1\u00f6\1\uffff\1\u00f5\73\uffff\1\u00dd\1\u00de\1\u00df\1\u00e0\1\u00e1\1\u00e2\1\u00e3\1\u00e4\1\u00e5\1\u00e6\1\u00e7\1\u00e8\1\u00e9\1\u00ea\5\uffff\1\u00eb\1\u00ec\1\u00ed\1\u00ee\1\u00ef\1\u00f0\1\u00f1\1\u00f2\1\u00f3\1\u00f4",
            "\1\u00f6\1\uffff\1\u00f5\73\uffff\1\u00dd\1\u00de\1\u00df\1\u00e0\1\u00e1\1\u00e2\1\u00e3\1\u00e4\1\u00e5\1\u00e6\1\u00e7\1\u00e8\1\u00e9\1\u00ea\5\uffff\1\u00eb\1\u00ec\1\u00ed\1\u00ee\1\u00ef\1\u00f0\1\u00f1\1\u00f2\1\u00f3\1\u00f4",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\4\uffff\1\u010a\1\u010b\1\u010c\1\u010d\1\u0106\1\u0107\1\u0108\1\u0109\1\u0102\1\u0103\1\u0104\1\u0105\1\u0100\1\u0101\1\u00fd\1\u00fe\1\u00ff\1\u00f7\1\u00f8\1\u00f9\1\u00fa\1\u00fb\1\u00fc\44\uffff\20\177",
            "\1\u010f",
            "",
            "\1\101\7\uffff\4\177\10\uffff\1\u0080\11\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u0110",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be",
            "\1\u00bd\11\uffff\1\u00be\11\uffff\1\u0111",
            "\1\u0112",
            "\1\u0113",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9\1\u0114",
            "\1\u0115",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u0116",
            "\1\u0130\1\uffff\1\u012f\73\uffff\1\u0117\1\u0118\1\u0119\1\u011a\1\u011b\1\u011c\1\u011d\1\u011e\1\u011f\1\u0120\1\u0121\1\u0122\1\u0123\1\u0124\5\uffff\1\u0125\1\u0126\1\u0127\1\u0128\1\u0129\1\u012a\1\u012b\1\u012c\1\u012d\1\u012e",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u00f6\1\uffff\1\u00f5",
            "\1\u0132\2\uffff\1\u00f5\7\uffff\4\u00a3\10\uffff\1\u0131\11\uffff\2\u00a3\1\u0133\1\u0134\2\uffff\1\u0135\1\uffff\26\u0135\35\uffff\1\u0135\77\uffff\20\u00a3",
            "\1\u0132\12\uffff\4\u00a3\22\uffff\2\u00a3\1\u0133\1\u0134\2\uffff\1\u0135\1\uffff\26\u0135\35\uffff\1\u0135\77\uffff\20\u00a3",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u010e\7\uffff\4\177\10\uffff\1\u0136\11\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\163\7\uffff\4\177\10\uffff\1\u00a2\11\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u014e\11\uffff\1\u00be\123\uffff\1\u014a\1\u014b\1\u014c\1\u014d\1\u0146\1\u0147\1\u0148\1\u0149\1\u0142\1\u0143\1\u0144\1\u0145\1\u0140\1\u0141\1\u013d\1\u013e\1\u013f\1\u0137\1\u0138\1\u0139\1\u013a\1\u013b\1\u013c",
            "\1\u014f",
            "\1\u0168\7\uffff\1\u0150\10\uffff\1\u0169\114\uffff\1\u0164\1\u0165\1\u0166\1\u0167\1\u0160\1\u0161\1\u0162\1\u0163\1\u015c\1\u015d\1\u015e\1\u015f\1\u015a\1\u015b\1\u0157\1\u0158\1\u0159\1\u0151\1\u0152\1\u0153\1\u0154\1\u0155\1\u0156",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9\112\uffff\1\u017d\1\u017e\1\u017f\1\u0180\1\u0179\1\u017a\1\u017b\1\u017c\1\u0175\1\u0176\1\u0177\1\u0178\1\u0173\1\u0174\1\u0170\1\u0171\1\u0172\1\u016a\1\u016b\1\u016c\1\u016d\1\u016e\1\u016f",
            "\1\u0182",
            "\1\u019b\7\uffff\1\u0183\1\u00d8\11\uffff\1\u00d9\112\uffff\1\u0197\1\u0198\1\u0199\1\u019a\1\u0193\1\u0194\1\u0195\1\u0196\1\u018f\1\u0190\1\u0191\1\u0192\1\u018d\1\u018e\1\u018a\1\u018b\1\u018c\1\u0184\1\u0185\1\u0186\1\u0187\1\u0188\1\u0189",
            "\1\u00db\11\uffff\1\u00dc\1\u00da",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u0130\1\uffff\1\u012f",
            "\1\u00db\11\uffff\1\u00dc\1\u019c",
            "\1\u00db\11\uffff\1\u00dc",
            "\1\u019d",
            "\1\u019f\1\uffff\1\u019e",
            "\1\u01b9\1\uffff\1\u01b8\73\uffff\1\u01a0\1\u01a1\1\u01a2\1\u01a3\1\u01a4\1\u01a5\1\u01a6\1\u01a7\1\u01a8\1\u01a9\1\u01aa\1\u01ab\1\u01ac\1\u01ad\5\uffff\1\u01ae\1\u01af\1\u01b0\1\u01b1\1\u01b2\1\u01b3\1\u01b4\1\u01b5\1\u01b6\1\u01b7",
            "\1\u01b9\1\uffff\1\u01b8\73\uffff\1\u01a0\1\u01a1\1\u01a2\1\u01a3\1\u01a4\1\u01a5\1\u01a6\1\u01a7\1\u01a8\1\u01a9\1\u01aa\1\u01ab\1\u01ac\1\u01ad\5\uffff\1\u01ae\1\u01af\1\u01b0\1\u01b1\1\u01b2\1\u01b3\1\u01b4\1\u01b5\1\u01b6\1\u01b7",
            "",
            "\1\u01ba",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be",
            "\1\u014e\11\uffff\1\u00be\11\uffff\1\u01bb",
            "\1\u00bd\11\uffff\1\u00be\11\uffff\1\u0111",
            "\1\u01bc",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169",
            "\1\u0168\20\uffff\1\u0169\2\uffff\1\u01bd",
            "\4\177\22\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9\1\u01be",
            "\1\u00d7\10\uffff\1\u00d8\11\uffff\1\u00d9\1\u0114",
            "\1\u01bf",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9\1\u01c0",
            "\1\u01c1",
            "\1\u0132\2\uffff\1\u00f5\7\uffff\4\u00a3\10\uffff\1\u0131\11\uffff\2\u00a3\1\u0133\1\u0134\2\uffff\1\u0135\1\uffff\26\u0135\35\uffff\1\u0135\77\uffff\20\u00a3",
            "\1\u019e\7\uffff\4\u00a3\10\uffff\1\u01c2\11\uffff\2\u00a3\1\u0133\1\u0134\2\uffff\1\u0135\1\uffff\26\u0135\35\uffff\1\u0135\77\uffff\20\u00a3",
            "\4\u00a3\22\uffff\2\u00a3\1\u0133\1\u0134\2\uffff\1\u0135\1\uffff\26\u0135\35\uffff\1\u0135\77\uffff\20\u00a3",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01b9\1\uffff\1\u01b8",
            "\1\u01c4\2\uffff\1\u01b8\7\uffff\4\u00a3\10\uffff\1\u01c3\11\uffff\2\u00a3\1\u0133\1\u0134\2\uffff\1\u0135\1\uffff\26\u0135\35\uffff\1\u0135\77\uffff\20\u00a3",
            "\1\u01c4\12\uffff\4\u00a3\22\uffff\2\u00a3\1\u0133\1\u0134\2\uffff\1\u0135\1\uffff\26\u0135\35\uffff\1\u0135\77\uffff\20\u00a3",
            "\1\u010e\7\uffff\4\177\10\uffff\1\u0136\11\uffff\2\177\4\uffff\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\1\175\1\176\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1\77\uffff\20\177",
            "\1\u01c5",
            "\1\u01dd\20\uffff\1\u0169\114\uffff\1\u01d9\1\u01da\1\u01db\1\u01dc\1\u01d5\1\u01d6\1\u01d7\1\u01d8\1\u01d1\1\u01d2\1\u01d3\1\u01d4\1\u01cf\1\u01d0\1\u01cc\1\u01cd\1\u01ce\1\u01c6\1\u01c7\1\u01c8\1\u01c9\1\u01ca\1\u01cb",
            "\1\u01de",
            "\1\u01df",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9\112\uffff\1\u01f3\1\u01f4\1\u01f5\1\u01f6\1\u01ef\1\u01f0\1\u01f1\1\u01f2\1\u01eb\1\u01ec\1\u01ed\1\u01ee\1\u01e9\1\u01ea\1\u01e6\1\u01e7\1\u01e8\1\u01e0\1\u01e1\1\u01e2\1\u01e3\1\u01e4\1\u01e5",
            "\1\u01f8",
            "\1\u00db\11\uffff\1\u00dc\1\u019c",
            "\1\u01f9",
            "\1\u01fa",
            "\1\u01fc\1\uffff\1\u01fb",
            "\1\u014e\11\uffff\1\u00be\11\uffff\1\u01bb",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169",
            "\1\u01dd\20\uffff\1\u0169\2\uffff\1\u01fd",
            "\1\u0168\20\uffff\1\u0169\2\uffff\1\u01bd",
            "\1\u0181\10\uffff\1\u00d8\11\uffff\1\u00d9\1\u01be",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9\1\u01fe",
            "\1\u019b\10\uffff\1\u00d8\11\uffff\1\u00d9\1\u01c0",
            "\1\u019e\7\uffff\4\u00a3\10\uffff\1\u01c2\11\uffff\2\u00a3\1\u0133\1\u0134\2\uffff\1\u0135\1\uffff\26\u0135\35\uffff\1\u0135\77\uffff\20\u00a3",
            "\1\u01c4\2\uffff\1\u01b8\7\uffff\4\u00a3\10\uffff\1\u01c3\11\uffff\2\u00a3\1\u0133\1\u0134\2\uffff\1\u0135\1\uffff\26\u0135\35\uffff\1\u0135\77\uffff\20\u00a3",
            "\1\u01fb\7\uffff\4\u00a3\10\uffff\1\u01ff\11\uffff\2\u00a3\1\u0133\1\u0134\2\uffff\1\u0135\1\uffff\26\u0135\35\uffff\1\u0135\77\uffff\20\u00a3",
            "\4\u00a3\22\uffff\2\u00a3\1\u0133\1\u0134\2\uffff\1\u0135\1\uffff\26\u0135\35\uffff\1\u0135\77\uffff\20\u00a3",
            "\1\u0200",
            "\1\u0201",
            "\1\u0202",
            "\1\u01dd\20\uffff\1\u0169\2\uffff\1\u01fd",
            "\1\u01f7\10\uffff\1\u00d8\11\uffff\1\u00d9\1\u01fe",
            "\1\u01fb\7\uffff\4\u00a3\10\uffff\1\u01ff\11\uffff\2\u00a3\1\u0133\1\u0134\2\uffff\1\u0135\1\uffff\26\u0135\35\uffff\1\u0135\77\uffff\20\u00a3"
    };

    static final short[] dfa_19 = DFA.unpackEncodedString(dfa_19s);
    static final short[] dfa_20 = DFA.unpackEncodedString(dfa_20s);
    static final char[] dfa_21 = DFA.unpackEncodedStringToUnsignedChars(dfa_21s);
    static final char[] dfa_22 = DFA.unpackEncodedStringToUnsignedChars(dfa_22s);
    static final short[] dfa_23 = DFA.unpackEncodedString(dfa_23s);
    static final short[] dfa_24 = DFA.unpackEncodedString(dfa_24s);
    static final short[][] dfa_25 = unpackEncodedStringArray(dfa_25s);

    class DFA35 extends DFA {

        public DFA35(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 35;
            this.eot = dfa_19;
            this.eof = dfa_20;
            this.min = dfa_21;
            this.max = dfa_22;
            this.accept = dfa_23;
            this.special = dfa_24;
            this.transition = dfa_25;
        }
        public String getDescription() {
            return "1389:2: ( ( (lv_const_0_0= ruleConstraints ) )+ | ( ( (lv_const_1_0= ruleConstraints ) )* ( (lv_rela_2_0= ruleRelation ) ) ) | ( ( (lv_rela_3_0= ruleRelation ) ) ( (lv_const_4_0= ruleConstraints ) )+ ) )";
        }
    }
    static final String dfa_26s = "\16\uffff";
    static final String dfa_27s = "\1\53\13\5\2\uffff";
    static final String dfa_28s = "\1\140\13\137\2\uffff";
    static final String dfa_29s = "\14\uffff\1\1\1\2";
    static final String dfa_30s = "\16\uffff}>";
    static final String[] dfa_31s = {
            "\1\14\1\uffff\6\14\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\5\14\35\uffff\1\14",
            "\3\14\17\uffff\1\14\1\uffff\1\14\2\uffff\1\15\14\uffff\4\14\26\uffff\16\14\5\uffff\12\14",
            "\3\14\17\uffff\1\14\1\uffff\1\14\2\uffff\1\15\14\uffff\4\14\26\uffff\16\14\5\uffff\12\14",
            "\3\14\17\uffff\1\14\1\uffff\1\14\2\uffff\1\15\14\uffff\4\14\26\uffff\16\14\5\uffff\12\14",
            "\3\14\17\uffff\1\14\1\uffff\1\14\2\uffff\1\15\14\uffff\4\14\26\uffff\16\14\5\uffff\12\14",
            "\3\14\17\uffff\1\14\1\uffff\1\14\2\uffff\1\15\14\uffff\4\14\26\uffff\16\14\5\uffff\12\14",
            "\3\14\17\uffff\1\14\1\uffff\1\14\2\uffff\1\15\14\uffff\4\14\26\uffff\16\14\5\uffff\12\14",
            "\3\14\17\uffff\1\14\1\uffff\1\14\2\uffff\1\15\14\uffff\4\14\26\uffff\16\14\5\uffff\12\14",
            "\3\14\17\uffff\1\14\1\uffff\1\14\2\uffff\1\15\14\uffff\4\14\26\uffff\16\14\5\uffff\12\14",
            "\3\14\17\uffff\1\14\1\uffff\1\14\2\uffff\1\15\14\uffff\4\14\26\uffff\16\14\5\uffff\12\14",
            "\3\14\17\uffff\1\14\1\uffff\1\14\2\uffff\1\15\14\uffff\4\14\26\uffff\16\14\5\uffff\12\14",
            "\3\14\17\uffff\1\14\1\uffff\1\14\2\uffff\1\15\14\uffff\4\14\26\uffff\16\14\5\uffff\12\14",
            "",
            ""
    };

    static final short[] dfa_26 = DFA.unpackEncodedString(dfa_26s);
    static final char[] dfa_27 = DFA.unpackEncodedStringToUnsignedChars(dfa_27s);
    static final char[] dfa_28 = DFA.unpackEncodedStringToUnsignedChars(dfa_28s);
    static final short[] dfa_29 = DFA.unpackEncodedString(dfa_29s);
    static final short[] dfa_30 = DFA.unpackEncodedString(dfa_30s);
    static final short[][] dfa_31 = unpackEncodedStringArray(dfa_31s);

    class DFA33 extends DFA {

        public DFA33(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 33;
            this.eot = dfa_26;
            this.eof = dfa_26;
            this.min = dfa_27;
            this.max = dfa_28;
            this.accept = dfa_29;
            this.special = dfa_30;
            this.transition = dfa_31;
        }
        public String getDescription() {
            return "()* loopback of 1411:4: ( (lv_const_1_0= ruleConstraints ) )*";
        }
    }
    static final String dfa_32s = "\6\uffff";
    static final String dfa_33s = "\1\uffff\1\2\3\uffff\1\2";
    static final String dfa_34s = "\1\5\1\7\1\uffff\1\7\1\uffff\1\7";
    static final String dfa_35s = "\1\7\1\140\1\uffff\1\7\1\uffff\1\140";
    static final String dfa_36s = "\2\uffff\1\2\1\uffff\1\1\1\uffff";
    static final String dfa_37s = "\6\uffff}>";
    static final String[] dfa_38s = {
            "\1\2\1\uffff\1\1",
            "\1\2\11\uffff\1\2\11\uffff\1\3\11\uffff\2\2\4\uffff\1\2\1\uffff\44\2\1\4\4\uffff\13\2",
            "",
            "\1\5",
            "",
            "\1\2\11\uffff\1\2\11\uffff\1\3\11\uffff\2\2\4\uffff\1\2\1\uffff\44\2\1\4\4\uffff\13\2"
    };

    static final short[] dfa_32 = DFA.unpackEncodedString(dfa_32s);
    static final short[] dfa_33 = DFA.unpackEncodedString(dfa_33s);
    static final char[] dfa_34 = DFA.unpackEncodedStringToUnsignedChars(dfa_34s);
    static final char[] dfa_35 = DFA.unpackEncodedStringToUnsignedChars(dfa_35s);
    static final short[] dfa_36 = DFA.unpackEncodedString(dfa_36s);
    static final short[] dfa_37 = DFA.unpackEncodedString(dfa_37s);
    static final short[][] dfa_38 = unpackEncodedStringArray(dfa_38s);

    class DFA39 extends DFA {

        public DFA39(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 39;
            this.eot = dfa_32;
            this.eof = dfa_33;
            this.min = dfa_34;
            this.max = dfa_35;
            this.accept = dfa_36;
            this.special = dfa_37;
            this.transition = dfa_38;
        }
        public String getDescription() {
            return "1706:4: ( ( (lv_auxiliar_6_0= ruleWORD ) ) ( (lv_negation_7_0= ruleNegation ) ) )?";
        }
    }
    static final String dfa_39s = "\1\137\13\34\30\7\2\u0097\1\54\1\7\2\137\2\uffff\4\137\1\u0097\30\7\2\u0097\30\7\2\50\3\7\3\137\1\u0097\3\50\30\7\2\50\3\7\4\50\1\7\1\50";
    static final String dfa_40s = "\52\uffff\1\1\1\2\146\uffff";
    static final String[] dfa_41s = {
            "\1\45\1\uffff\1\44\53\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\5\uffff\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31\5\uffff\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\41\1\42\1\43",
            "\1\46",
            "\1\46",
            "\1\46",
            "\1\46",
            "\1\46",
            "\1\46",
            "\1\46",
            "\1\46",
            "\1\46",
            "\1\46",
            "\1\46",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\45\1\uffff\1\44",
            "\1\53\1\uffff\1\53\23\uffff\1\47\1\uffff\10\53\1\50\1\51\34\uffff\16\53\1\uffff\16\53\61\uffff\7\52",
            "\1\53\1\uffff\1\53\25\uffff\10\53\1\50\1\51\34\uffff\16\53\1\uffff\16\53\61\uffff\7\52",
            "\1\54\1\55\1\56\1\57",
            "\1\60",
            "\1\112\1\uffff\1\111\73\uffff\1\61\1\62\1\63\1\64\1\65\1\66\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\5\uffff\1\77\1\100\1\101\1\102\1\103\1\104\1\105\1\106\1\107\1\110",
            "\1\112\1\uffff\1\111\73\uffff\1\61\1\62\1\63\1\64\1\65\1\66\1\67\1\70\1\71\1\72\1\73\1\74\1\75\1\76\5\uffff\1\77\1\100\1\101\1\102\1\103\1\104\1\105\1\106\1\107\1\110",
            "",
            "",
            "\1\144\1\uffff\1\143\73\uffff\1\113\1\114\1\115\1\116\1\117\1\120\1\121\1\122\1\123\1\124\1\125\1\126\1\127\1\130\5\uffff\1\131\1\132\1\133\1\134\1\135\1\136\1\137\1\140\1\141\1\142",
            "\1\144\1\uffff\1\143\73\uffff\1\113\1\114\1\115\1\116\1\117\1\120\1\121\1\122\1\123\1\124\1\125\1\126\1\127\1\130\5\uffff\1\131\1\132\1\133\1\134\1\135\1\136\1\137\1\140\1\141\1\142",
            "\1\144\1\uffff\1\143\73\uffff\1\113\1\114\1\115\1\116\1\117\1\120\1\121\1\122\1\123\1\124\1\125\1\126\1\127\1\130\5\uffff\1\131\1\132\1\133\1\134\1\135\1\136\1\137\1\140\1\141\1\142",
            "\1\144\1\uffff\1\143\73\uffff\1\113\1\114\1\115\1\116\1\117\1\120\1\121\1\122\1\123\1\124\1\125\1\126\1\127\1\130\5\uffff\1\131\1\132\1\133\1\134\1\135\1\136\1\137\1\140\1\141\1\142",
            "\1\53\1\uffff\1\53\23\uffff\1\47\1\uffff\10\53\1\50\1\51\34\uffff\16\53\1\uffff\16\53\61\uffff\7\52",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\112\1\uffff\1\111",
            "\1\53\1\uffff\1\53\23\uffff\1\145\1\uffff\10\53\1\50\1\51\34\uffff\16\53\1\uffff\16\53\61\uffff\7\52",
            "\1\53\1\uffff\1\53\25\uffff\10\53\1\50\1\51\34\uffff\16\53\1\uffff\16\53\61\uffff\7\52",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\144\1\uffff\1\143",
            "\1\147\2\uffff\1\143\11\uffff\1\152\11\uffff\1\146\13\uffff\1\150\1\151",
            "\1\147\14\uffff\1\152\25\uffff\1\150\1\151",
            "\1\153",
            "\1\154",
            "\1\156\1\uffff\1\155",
            "\1\u0088\1\uffff\1\u0087\73\uffff\1\157\1\160\1\161\1\162\1\163\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\5\uffff\1\175\1\176\1\177\1\u0080\1\u0081\1\u0082\1\u0083\1\u0084\1\u0085\1\u0086",
            "\1\u0088\1\uffff\1\u0087\73\uffff\1\157\1\160\1\161\1\162\1\163\1\164\1\165\1\166\1\167\1\170\1\171\1\172\1\173\1\174\5\uffff\1\175\1\176\1\177\1\u0080\1\u0081\1\u0082\1\u0083\1\u0084\1\u0085\1\u0086",
            "\1\45\1\uffff\1\44\73\uffff\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31\5\uffff\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\41\1\42\1\43",
            "\1\53\1\uffff\1\53\23\uffff\1\145\1\uffff\10\53\1\50\1\51\34\uffff\16\53\1\uffff\16\53\61\uffff\7\52",
            "\1\147\2\uffff\1\143\11\uffff\1\152\11\uffff\1\146\13\uffff\1\150\1\151",
            "\1\155\11\uffff\1\152\11\uffff\1\u0089\13\uffff\1\150\1\151",
            "\1\152\25\uffff\1\150\1\151",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u0088\1\uffff\1\u0087",
            "\1\u008b\2\uffff\1\u0087\11\uffff\1\152\11\uffff\1\u008a\13\uffff\1\150\1\151",
            "\1\u008b\14\uffff\1\152\25\uffff\1\150\1\151",
            "\1\u008c",
            "\1\u008d",
            "\1\u008f\1\uffff\1\u008e",
            "\1\155\11\uffff\1\152\11\uffff\1\u0089\13\uffff\1\150\1\151",
            "\1\u008b\2\uffff\1\u0087\11\uffff\1\152\11\uffff\1\u008a\13\uffff\1\150\1\151",
            "\1\u008e\11\uffff\1\152\11\uffff\1\u0090\13\uffff\1\150\1\151",
            "\1\152\25\uffff\1\150\1\151",
            "\1\u0091",
            "\1\u008e\11\uffff\1\152\11\uffff\1\u0090\13\uffff\1\150\1\151"
    };
    static final char[] dfa_39 = DFA.unpackEncodedStringToUnsignedChars(dfa_39s);
    static final short[] dfa_40 = DFA.unpackEncodedString(dfa_40s);
    static final short[][] dfa_41 = unpackEncodedStringArray(dfa_41s);

    class DFA41 extends DFA {

        public DFA41(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 41;
            this.eot = dfa_7;
            this.eof = dfa_7;
            this.min = dfa_8;
            this.max = dfa_39;
            this.accept = dfa_40;
            this.special = dfa_11;
            this.transition = dfa_41;
        }
        public String getDescription() {
            return "1806:4: ( ( (lv_clause_11_0= ruleModalitySentence ) ) | ( (lv_clause_12_0= rulePredicateSentence ) ) )";
        }
    }
    static final String dfa_42s = "\7\uffff";
    static final String dfa_43s = "\1\uffff\1\5\4\uffff\1\5";
    static final String dfa_44s = "\1\5\1\7\1\uffff\1\7\2\uffff\1\7";
    static final String dfa_45s = "\1\7\1\u00af\1\uffff\1\7\2\uffff\1\u00af";
    static final String dfa_46s = "\2\uffff\1\2\1\uffff\1\3\1\1\1\uffff";
    static final String dfa_47s = "\7\uffff}>";
    static final String[] dfa_48s = {
            "\1\2\1\uffff\1\1",
            "\1\1\7\uffff\4\5\10\uffff\1\3\11\uffff\2\5\4\uffff\1\5\1\uffff\26\5\16\4\5\uffff\12\4\1\5\77\uffff\20\5",
            "",
            "\1\6",
            "",
            "",
            "\1\1\7\uffff\4\5\10\uffff\1\3\11\uffff\2\5\4\uffff\1\5\1\uffff\26\5\16\4\5\uffff\12\4\1\5\77\uffff\20\5"
    };

    static final short[] dfa_42 = DFA.unpackEncodedString(dfa_42s);
    static final short[] dfa_43 = DFA.unpackEncodedString(dfa_43s);
    static final char[] dfa_44 = DFA.unpackEncodedStringToUnsignedChars(dfa_44s);
    static final char[] dfa_45 = DFA.unpackEncodedStringToUnsignedChars(dfa_45s);
    static final short[] dfa_46 = DFA.unpackEncodedString(dfa_46s);
    static final short[] dfa_47 = DFA.unpackEncodedString(dfa_47s);
    static final short[][] dfa_48 = unpackEncodedStringArray(dfa_48s);

    class DFA49 extends DFA {

        public DFA49(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 49;
            this.eot = dfa_42;
            this.eof = dfa_43;
            this.min = dfa_44;
            this.max = dfa_45;
            this.accept = dfa_46;
            this.special = dfa_47;
            this.transition = dfa_48;
        }
        public String getDescription() {
            return "2071:2: ( ( (lv_predicates_0_0= ruleWORD ) )+ | ( (lv_predicates_1_0= RULE_STRING ) ) | ( ( (lv_predicates_2_0= ruleWORD ) )+ ( (lv_object_3_0= rulePredicateObject ) ) ) )";
        }
    }
    static final String dfa_49s = "\40\uffff";
    static final String dfa_50s = "\35\uffff\1\36\2\uffff";
    static final String dfa_51s = "\2\53\33\5\1\7\2\uffff";
    static final String dfa_52s = "\1\140\1\102\33\137\1\u00af\2\uffff";
    static final String dfa_53s = "\36\uffff\1\2\1\1";
    static final String dfa_54s = "\40\uffff}>";
    static final String[] dfa_55s = {
            "\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1",
            "\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\17\uffff\1\31\1\32\1\33\1\34\26\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\51\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\51\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\51\uffff\16\36\5\uffff\12\36",
            "\1\36\1\35\1\36\17\uffff\1\36\1\uffff\1\36\51\uffff\16\36\5\uffff\12\36",
            "\1\36\7\uffff\4\36\22\uffff\2\36\4\uffff\1\36\1\uffff\26\36\35\uffff\1\36\4\uffff\27\36\25\37\17\uffff\20\36",
            "",
            ""
    };

    static final short[] dfa_49 = DFA.unpackEncodedString(dfa_49s);
    static final short[] dfa_50 = DFA.unpackEncodedString(dfa_50s);
    static final char[] dfa_51 = DFA.unpackEncodedStringToUnsignedChars(dfa_51s);
    static final char[] dfa_52 = DFA.unpackEncodedStringToUnsignedChars(dfa_52s);
    static final short[] dfa_53 = DFA.unpackEncodedString(dfa_53s);
    static final short[] dfa_54 = DFA.unpackEncodedString(dfa_54s);
    static final short[][] dfa_55 = unpackEncodedStringArray(dfa_55s);

    class DFA66 extends DFA {

        public DFA66(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 66;
            this.eot = dfa_49;
            this.eof = dfa_50;
            this.min = dfa_51;
            this.max = dfa_52;
            this.accept = dfa_53;
            this.special = dfa_54;
            this.transition = dfa_55;
        }
        public String getDescription() {
            return "2869:2: ( ( (lv_timeConstraint_0_0= ruleTimeConstraint ) ) | ( (lv_constraint_1_0= ruleConstraint ) ) )";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x00000000000040C0L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x3FF80000001800A0L,0x00000000FFC1FFF8L,0x0000FFFFFF000000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000020002L,0x0000000000000000L,0x0000FFFF00000000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000FFFF00000000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000006000000002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x3FF80000000000A0L,0x00000000FFC1FFF8L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x0000000000FE0000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000001FE00000A0L,0x0000000000020000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0xFFFFE80000000002L,0x0000000100000007L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000001FE0000000L,0x00000000003C0000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0xFFFFE81FE00000A2L,0x00000001FFC3FFFFL});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000001FE00000A0L,0x00000000FFC3FFF8L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000000000L,0x0000001E10000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x3FF8000000000000L,0x0000000000000000L,0x0000000000FE0000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x3FF8001FE0000000L,0x00000000003C0000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0xFFFFE81FE00000A0L,0x00000001FFC3FFFFL});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x00000000000000A0L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0xFFFFE80000000000L,0x0000000100000007L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000000600000L,0x0000000000000000L,0x00000000FF000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000000600000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x00001E0000000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x00000000000000A0L,0x00000000FFC1FFF8L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000018000000012L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000018000000002L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x00000000028000E0L,0x00000000FFC1FFF8L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x00001E0000000002L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000000000000000L,0xF000000000000000L,0x000000000001FFFFL});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000000004010000L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000000000000082L,0x0FFFFFE000000000L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000000000008042L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0000000000008000L});

}