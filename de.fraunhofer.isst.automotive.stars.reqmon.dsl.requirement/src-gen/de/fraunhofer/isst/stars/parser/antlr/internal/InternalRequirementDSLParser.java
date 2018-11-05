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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_PROPERTY_TERM", "RULE_INT", "RULE_FLOAT", "RULE_ID", "RULE_WS_HYPHEN", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'Req'", "':'", "'.'", "';'", "','", "'then'", "'there'", "'['", "']'", "'{'", "'}'", "'-'", "'is'", "'be'", "'been'", "'has'", "'do'", "'does'", "'and'", "'or'", "'than'", "'as'", "'to'", "'of'", "'higher'", "'less'", "'more'", "'larger'", "'smaller'", "'as_long_as'", "'between'", "'next'", "'on'", "'above'", "'below'", "'in'", "'within'", "'in_front_of'", "'behind'", "'out'", "'under'", "'equal'", "'faster'", "'slower'", "'better'", "'by'", "'all'", "'every'", "'each'", "'whole'", "'any'", "'several'", "'either'", "'All'", "'Every'", "'Each'", "'Whole'", "'Any'", "'Several'", "'Either'", "'not'", "'donot'", "'doesnot'", "'doesn\\'t'", "'don\\'t'", "'the'", "'a'", "'an'", "'The'", "'A'", "'An'", "'that'", "'this'", "'That'", "'This'", "'with'", "'which'", "'who'", "'whose'", "'whom'", "'rad/m'", "'\\u00B0'", "'rad'", "'\\u00B0/m'", "'m/s'", "'knots'", "'km/h'", "'m/min'", "'kg'", "'g'", "'mg'", "'t'", "'C'", "'F'", "'bar'", "'Pa'", "'hPa'", "'m'", "'f'", "'km'", "'cm'", "'mm'", "'nm'", "'ns'", "'ms'", "'s'", "'sec'", "'second'", "'seconds'", "'minute'", "'minutes'", "'min'", "'hour'", "'hours'", "'h'", "'day'", "'days'", "'d'", "'month'", "'months'", "'mon'", "'year'", "'years'", "'y'", "'shall'", "'should'", "'will'", "'would'", "'can'", "'could'", "'must'", "'Globally'", "'globally'", "'Always'", "'always'", "'Sometimes'", "'sometimes'", "'Eventually'", "'eventually'", "'if'", "'after'", "'once'", "'when'", "'whenever'", "'while'", "'before'", "'until'", "'If'", "'After'", "'Once'", "'When'", "'Whenever'", "'While'", "'Before'", "'Until'"
    };
    public static final int T__144=144;
    public static final int T__143=143;
    public static final int T__146=146;
    public static final int T__50=50;
    public static final int T__145=145;
    public static final int T__140=140;
    public static final int T__142=142;
    public static final int T__141=141;
    public static final int RULE_PROPERTY_TERM=5;
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
    public static final int RULE_ID=8;
    public static final int T__131=131;
    public static final int T__130=130;
    public static final int RULE_INT=6;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=10;
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
    public static final int RULE_FLOAT=7;
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
    public static final int T__14=14;
    public static final int T__95=95;
    public static final int T__96=96;
    public static final int T__97=97;
    public static final int T__98=98;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int RULE_WS_HYPHEN=9;
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
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=11;
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
    public static final int RULE_WS=12;
    public static final int RULE_ANY_OTHER=13;
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

                if ( (LA1_0==RULE_INT||LA1_0==RULE_ID||(LA1_0>=14 && LA1_0<=16)) ) {
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
    // InternalRequirementDSL.g:108:1: ruleRequirement returns [EObject current=null] : ( (otherlv_0= 'Req' )? ( (lv_reqID_1_0= ruleReqID ) )? (otherlv_2= ':' | otherlv_3= '.' ) ( (lv_text_4_0= ruleRequirementText ) ) (otherlv_5= '.' | otherlv_6= ';' ) ) ;
    public final EObject ruleRequirement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        AntlrDatatypeRuleToken lv_reqID_1_0 = null;

        EObject lv_text_4_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:114:2: ( ( (otherlv_0= 'Req' )? ( (lv_reqID_1_0= ruleReqID ) )? (otherlv_2= ':' | otherlv_3= '.' ) ( (lv_text_4_0= ruleRequirementText ) ) (otherlv_5= '.' | otherlv_6= ';' ) ) )
            // InternalRequirementDSL.g:115:2: ( (otherlv_0= 'Req' )? ( (lv_reqID_1_0= ruleReqID ) )? (otherlv_2= ':' | otherlv_3= '.' ) ( (lv_text_4_0= ruleRequirementText ) ) (otherlv_5= '.' | otherlv_6= ';' ) )
            {
            // InternalRequirementDSL.g:115:2: ( (otherlv_0= 'Req' )? ( (lv_reqID_1_0= ruleReqID ) )? (otherlv_2= ':' | otherlv_3= '.' ) ( (lv_text_4_0= ruleRequirementText ) ) (otherlv_5= '.' | otherlv_6= ';' ) )
            // InternalRequirementDSL.g:116:3: (otherlv_0= 'Req' )? ( (lv_reqID_1_0= ruleReqID ) )? (otherlv_2= ':' | otherlv_3= '.' ) ( (lv_text_4_0= ruleRequirementText ) ) (otherlv_5= '.' | otherlv_6= ';' )
            {
            // InternalRequirementDSL.g:116:3: (otherlv_0= 'Req' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==14) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalRequirementDSL.g:117:4: otherlv_0= 'Req'
                    {
                    otherlv_0=(Token)match(input,14,FOLLOW_4); 

                    				newLeafNode(otherlv_0, grammarAccess.getRequirementAccess().getReqKeyword_0());
                    			

                    }
                    break;

            }

            // InternalRequirementDSL.g:122:3: ( (lv_reqID_1_0= ruleReqID ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_INT||LA3_0==RULE_ID) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalRequirementDSL.g:123:4: (lv_reqID_1_0= ruleReqID )
                    {
                    // InternalRequirementDSL.g:123:4: (lv_reqID_1_0= ruleReqID )
                    // InternalRequirementDSL.g:124:5: lv_reqID_1_0= ruleReqID
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

            // InternalRequirementDSL.g:141:3: (otherlv_2= ':' | otherlv_3= '.' )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==15) ) {
                alt4=1;
            }
            else if ( (LA4_0==16) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalRequirementDSL.g:142:4: otherlv_2= ':'
                    {
                    otherlv_2=(Token)match(input,15,FOLLOW_6); 

                    				newLeafNode(otherlv_2, grammarAccess.getRequirementAccess().getColonKeyword_2_0());
                    			

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:147:4: otherlv_3= '.'
                    {
                    otherlv_3=(Token)match(input,16,FOLLOW_6); 

                    				newLeafNode(otherlv_3, grammarAccess.getRequirementAccess().getFullStopKeyword_2_1());
                    			

                    }
                    break;

            }

            // InternalRequirementDSL.g:152:3: ( (lv_text_4_0= ruleRequirementText ) )
            // InternalRequirementDSL.g:153:4: (lv_text_4_0= ruleRequirementText )
            {
            // InternalRequirementDSL.g:153:4: (lv_text_4_0= ruleRequirementText )
            // InternalRequirementDSL.g:154:5: lv_text_4_0= ruleRequirementText
            {

            					newCompositeNode(grammarAccess.getRequirementAccess().getTextRequirementTextParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_7);
            lv_text_4_0=ruleRequirementText();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRequirementRule());
            					}
            					set(
            						current,
            						"text",
            						lv_text_4_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.RequirementText");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:171:3: (otherlv_5= '.' | otherlv_6= ';' )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==16) ) {
                alt5=1;
            }
            else if ( (LA5_0==17) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalRequirementDSL.g:172:4: otherlv_5= '.'
                    {
                    otherlv_5=(Token)match(input,16,FOLLOW_2); 

                    				newLeafNode(otherlv_5, grammarAccess.getRequirementAccess().getFullStopKeyword_4_0());
                    			

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:177:4: otherlv_6= ';'
                    {
                    otherlv_6=(Token)match(input,17,FOLLOW_2); 

                    				newLeafNode(otherlv_6, grammarAccess.getRequirementAccess().getSemicolonKeyword_4_1());
                    			

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
    // InternalRequirementDSL.g:186:1: entryRuleRequirementText returns [EObject current=null] : iv_ruleRequirementText= ruleRequirementText EOF ;
    public final EObject entryRuleRequirementText() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRequirementText = null;


        try {
            // InternalRequirementDSL.g:186:56: (iv_ruleRequirementText= ruleRequirementText EOF )
            // InternalRequirementDSL.g:187:2: iv_ruleRequirementText= ruleRequirementText EOF
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
    // InternalRequirementDSL.g:193:1: ruleRequirementText returns [EObject current=null] : ( ( ( (lv_condClauses_0_0= ruleConditionalClause ) ) (otherlv_1= ',' )? otherlv_2= 'then' )? ( (lv_mainclauses_3_0= ruleMainClause ) ) ( (otherlv_4= ',' )? ( (lv_condClauses_5_0= ruleConditionalClause ) ) )? ) ;
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
            // InternalRequirementDSL.g:199:2: ( ( ( ( (lv_condClauses_0_0= ruleConditionalClause ) ) (otherlv_1= ',' )? otherlv_2= 'then' )? ( (lv_mainclauses_3_0= ruleMainClause ) ) ( (otherlv_4= ',' )? ( (lv_condClauses_5_0= ruleConditionalClause ) ) )? ) )
            // InternalRequirementDSL.g:200:2: ( ( ( (lv_condClauses_0_0= ruleConditionalClause ) ) (otherlv_1= ',' )? otherlv_2= 'then' )? ( (lv_mainclauses_3_0= ruleMainClause ) ) ( (otherlv_4= ',' )? ( (lv_condClauses_5_0= ruleConditionalClause ) ) )? )
            {
            // InternalRequirementDSL.g:200:2: ( ( ( (lv_condClauses_0_0= ruleConditionalClause ) ) (otherlv_1= ',' )? otherlv_2= 'then' )? ( (lv_mainclauses_3_0= ruleMainClause ) ) ( (otherlv_4= ',' )? ( (lv_condClauses_5_0= ruleConditionalClause ) ) )? )
            // InternalRequirementDSL.g:201:3: ( ( (lv_condClauses_0_0= ruleConditionalClause ) ) (otherlv_1= ',' )? otherlv_2= 'then' )? ( (lv_mainclauses_3_0= ruleMainClause ) ) ( (otherlv_4= ',' )? ( (lv_condClauses_5_0= ruleConditionalClause ) ) )?
            {
            // InternalRequirementDSL.g:201:3: ( ( (lv_condClauses_0_0= ruleConditionalClause ) ) (otherlv_1= ',' )? otherlv_2= 'then' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( ((LA7_0>=153 && LA7_0<=168)) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalRequirementDSL.g:202:4: ( (lv_condClauses_0_0= ruleConditionalClause ) ) (otherlv_1= ',' )? otherlv_2= 'then'
                    {
                    // InternalRequirementDSL.g:202:4: ( (lv_condClauses_0_0= ruleConditionalClause ) )
                    // InternalRequirementDSL.g:203:5: (lv_condClauses_0_0= ruleConditionalClause )
                    {
                    // InternalRequirementDSL.g:203:5: (lv_condClauses_0_0= ruleConditionalClause )
                    // InternalRequirementDSL.g:204:6: lv_condClauses_0_0= ruleConditionalClause
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

                    // InternalRequirementDSL.g:221:4: (otherlv_1= ',' )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==18) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // InternalRequirementDSL.g:222:5: otherlv_1= ','
                            {
                            otherlv_1=(Token)match(input,18,FOLLOW_9); 

                            					newLeafNode(otherlv_1, grammarAccess.getRequirementTextAccess().getCommaKeyword_0_1());
                            				

                            }
                            break;

                    }

                    otherlv_2=(Token)match(input,19,FOLLOW_6); 

                    				newLeafNode(otherlv_2, grammarAccess.getRequirementTextAccess().getThenKeyword_0_2());
                    			

                    }
                    break;

            }

            // InternalRequirementDSL.g:232:3: ( (lv_mainclauses_3_0= ruleMainClause ) )
            // InternalRequirementDSL.g:233:4: (lv_mainclauses_3_0= ruleMainClause )
            {
            // InternalRequirementDSL.g:233:4: (lv_mainclauses_3_0= ruleMainClause )
            // InternalRequirementDSL.g:234:5: lv_mainclauses_3_0= ruleMainClause
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

            // InternalRequirementDSL.g:251:3: ( (otherlv_4= ',' )? ( (lv_condClauses_5_0= ruleConditionalClause ) ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==18||(LA9_0>=153 && LA9_0<=168)) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalRequirementDSL.g:252:4: (otherlv_4= ',' )? ( (lv_condClauses_5_0= ruleConditionalClause ) )
                    {
                    // InternalRequirementDSL.g:252:4: (otherlv_4= ',' )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==18) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // InternalRequirementDSL.g:253:5: otherlv_4= ','
                            {
                            otherlv_4=(Token)match(input,18,FOLLOW_11); 

                            					newLeafNode(otherlv_4, grammarAccess.getRequirementTextAccess().getCommaKeyword_2_0());
                            				

                            }
                            break;

                    }

                    // InternalRequirementDSL.g:258:4: ( (lv_condClauses_5_0= ruleConditionalClause ) )
                    // InternalRequirementDSL.g:259:5: (lv_condClauses_5_0= ruleConditionalClause )
                    {
                    // InternalRequirementDSL.g:259:5: (lv_condClauses_5_0= ruleConditionalClause )
                    // InternalRequirementDSL.g:260:6: lv_condClauses_5_0= ruleConditionalClause
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
    // InternalRequirementDSL.g:282:1: entryRuleConditionalClause returns [EObject current=null] : iv_ruleConditionalClause= ruleConditionalClause EOF ;
    public final EObject entryRuleConditionalClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConditionalClause = null;


        try {
            // InternalRequirementDSL.g:282:58: (iv_ruleConditionalClause= ruleConditionalClause EOF )
            // InternalRequirementDSL.g:283:2: iv_ruleConditionalClause= ruleConditionalClause EOF
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
    // InternalRequirementDSL.g:289:1: ruleConditionalClause returns [EObject current=null] : ( ( (lv_ordinator_0_0= ruleClauseOrdinator ) ) ( (lv_clauses_1_0= ruleClauses ) ) ) ;
    public final EObject ruleConditionalClause() throws RecognitionException {
        EObject current = null;

        Enumerator lv_ordinator_0_0 = null;

        EObject lv_clauses_1_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:295:2: ( ( ( (lv_ordinator_0_0= ruleClauseOrdinator ) ) ( (lv_clauses_1_0= ruleClauses ) ) ) )
            // InternalRequirementDSL.g:296:2: ( ( (lv_ordinator_0_0= ruleClauseOrdinator ) ) ( (lv_clauses_1_0= ruleClauses ) ) )
            {
            // InternalRequirementDSL.g:296:2: ( ( (lv_ordinator_0_0= ruleClauseOrdinator ) ) ( (lv_clauses_1_0= ruleClauses ) ) )
            // InternalRequirementDSL.g:297:3: ( (lv_ordinator_0_0= ruleClauseOrdinator ) ) ( (lv_clauses_1_0= ruleClauses ) )
            {
            // InternalRequirementDSL.g:297:3: ( (lv_ordinator_0_0= ruleClauseOrdinator ) )
            // InternalRequirementDSL.g:298:4: (lv_ordinator_0_0= ruleClauseOrdinator )
            {
            // InternalRequirementDSL.g:298:4: (lv_ordinator_0_0= ruleClauseOrdinator )
            // InternalRequirementDSL.g:299:5: lv_ordinator_0_0= ruleClauseOrdinator
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

            // InternalRequirementDSL.g:316:3: ( (lv_clauses_1_0= ruleClauses ) )
            // InternalRequirementDSL.g:317:4: (lv_clauses_1_0= ruleClauses )
            {
            // InternalRequirementDSL.g:317:4: (lv_clauses_1_0= ruleClauses )
            // InternalRequirementDSL.g:318:5: lv_clauses_1_0= ruleClauses
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
    // InternalRequirementDSL.g:339:1: entryRuleMainClause returns [EObject current=null] : iv_ruleMainClause= ruleMainClause EOF ;
    public final EObject entryRuleMainClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMainClause = null;


        try {
            // InternalRequirementDSL.g:339:51: (iv_ruleMainClause= ruleMainClause EOF )
            // InternalRequirementDSL.g:340:2: iv_ruleMainClause= ruleMainClause EOF
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
    // InternalRequirementDSL.g:346:1: ruleMainClause returns [EObject current=null] : ( ( (lv_modifier_0_0= ruleModifier ) )? ( (lv_clauses_1_0= ruleClause ) ) ) ;
    public final EObject ruleMainClause() throws RecognitionException {
        EObject current = null;

        Enumerator lv_modifier_0_0 = null;

        EObject lv_clauses_1_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:352:2: ( ( ( (lv_modifier_0_0= ruleModifier ) )? ( (lv_clauses_1_0= ruleClause ) ) ) )
            // InternalRequirementDSL.g:353:2: ( ( (lv_modifier_0_0= ruleModifier ) )? ( (lv_clauses_1_0= ruleClause ) ) )
            {
            // InternalRequirementDSL.g:353:2: ( ( (lv_modifier_0_0= ruleModifier ) )? ( (lv_clauses_1_0= ruleClause ) ) )
            // InternalRequirementDSL.g:354:3: ( (lv_modifier_0_0= ruleModifier ) )? ( (lv_clauses_1_0= ruleClause ) )
            {
            // InternalRequirementDSL.g:354:3: ( (lv_modifier_0_0= ruleModifier ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( ((LA10_0>=145 && LA10_0<=152)) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalRequirementDSL.g:355:4: (lv_modifier_0_0= ruleModifier )
                    {
                    // InternalRequirementDSL.g:355:4: (lv_modifier_0_0= ruleModifier )
                    // InternalRequirementDSL.g:356:5: lv_modifier_0_0= ruleModifier
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

            // InternalRequirementDSL.g:373:3: ( (lv_clauses_1_0= ruleClause ) )
            // InternalRequirementDSL.g:374:4: (lv_clauses_1_0= ruleClause )
            {
            // InternalRequirementDSL.g:374:4: (lv_clauses_1_0= ruleClause )
            // InternalRequirementDSL.g:375:5: lv_clauses_1_0= ruleClause
            {

            					newCompositeNode(grammarAccess.getMainClauseAccess().getClausesClauseParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_clauses_1_0=ruleClause();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMainClauseRule());
            					}
            					add(
            						current,
            						"clauses",
            						lv_clauses_1_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.Clause");
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
    // InternalRequirementDSL.g:396:1: entryRuleClauses returns [EObject current=null] : iv_ruleClauses= ruleClauses EOF ;
    public final EObject entryRuleClauses() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClauses = null;


        try {
            // InternalRequirementDSL.g:396:48: (iv_ruleClauses= ruleClauses EOF )
            // InternalRequirementDSL.g:397:2: iv_ruleClauses= ruleClauses EOF
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
    // InternalRequirementDSL.g:403:1: ruleClauses returns [EObject current=null] : ( ( (lv_clauses_0_0= ruleClause ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_clauses_2_0= ruleClause ) ) )* ) ;
    public final EObject ruleClauses() throws RecognitionException {
        EObject current = null;

        EObject lv_clauses_0_0 = null;

        AntlrDatatypeRuleToken lv_conjunction_1_0 = null;

        EObject lv_clauses_2_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:409:2: ( ( ( (lv_clauses_0_0= ruleClause ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_clauses_2_0= ruleClause ) ) )* ) )
            // InternalRequirementDSL.g:410:2: ( ( (lv_clauses_0_0= ruleClause ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_clauses_2_0= ruleClause ) ) )* )
            {
            // InternalRequirementDSL.g:410:2: ( ( (lv_clauses_0_0= ruleClause ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_clauses_2_0= ruleClause ) ) )* )
            // InternalRequirementDSL.g:411:3: ( (lv_clauses_0_0= ruleClause ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_clauses_2_0= ruleClause ) ) )*
            {
            // InternalRequirementDSL.g:411:3: ( (lv_clauses_0_0= ruleClause ) )
            // InternalRequirementDSL.g:412:4: (lv_clauses_0_0= ruleClause )
            {
            // InternalRequirementDSL.g:412:4: (lv_clauses_0_0= ruleClause )
            // InternalRequirementDSL.g:413:5: lv_clauses_0_0= ruleClause
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

            // InternalRequirementDSL.g:430:3: ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_clauses_2_0= ruleClause ) ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==32) ) {
                    int LA11_2 = input.LA(2);

                    if ( (LA11_2==RULE_STRING||LA11_2==RULE_ID||LA11_2==20||(LA11_2>=60 && LA11_2<=66)||(LA11_2>=68 && LA11_2<=73)||(LA11_2>=79 && LA11_2<=88)) ) {
                        alt11=1;
                    }


                }
                else if ( (LA11_0==33) ) {
                    int LA11_3 = input.LA(2);

                    if ( (LA11_3==RULE_STRING||LA11_3==RULE_ID||LA11_3==20||(LA11_3>=60 && LA11_3<=66)||(LA11_3>=68 && LA11_3<=73)||(LA11_3>=79 && LA11_3<=88)) ) {
                        alt11=1;
                    }


                }


                switch (alt11) {
            	case 1 :
            	    // InternalRequirementDSL.g:431:4: ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_clauses_2_0= ruleClause ) )
            	    {
            	    // InternalRequirementDSL.g:431:4: ( (lv_conjunction_1_0= ruleConjunction ) )
            	    // InternalRequirementDSL.g:432:5: (lv_conjunction_1_0= ruleConjunction )
            	    {
            	    // InternalRequirementDSL.g:432:5: (lv_conjunction_1_0= ruleConjunction )
            	    // InternalRequirementDSL.g:433:6: lv_conjunction_1_0= ruleConjunction
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

            	    // InternalRequirementDSL.g:450:4: ( (lv_clauses_2_0= ruleClause ) )
            	    // InternalRequirementDSL.g:451:5: (lv_clauses_2_0= ruleClause )
            	    {
            	    // InternalRequirementDSL.g:451:5: (lv_clauses_2_0= ruleClause )
            	    // InternalRequirementDSL.g:452:6: lv_clauses_2_0= ruleClause
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
            	    break loop11;
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
    // InternalRequirementDSL.g:474:1: entryRuleClause returns [EObject current=null] : iv_ruleClause= ruleClause EOF ;
    public final EObject entryRuleClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClause = null;


        try {
            // InternalRequirementDSL.g:474:47: (iv_ruleClause= ruleClause EOF )
            // InternalRequirementDSL.g:475:2: iv_ruleClause= ruleClause EOF
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
    // InternalRequirementDSL.g:481:1: ruleClause returns [EObject current=null] : (this_ModalitySentence_0= ruleModalitySentence | this_PredicateSentence_1= rulePredicateSentence | this_ExistenceSentence_2= ruleExistenceSentence | this_PropertySentence_3= rulePropertySentence ) ;
    public final EObject ruleClause() throws RecognitionException {
        EObject current = null;

        EObject this_ModalitySentence_0 = null;

        EObject this_PredicateSentence_1 = null;

        EObject this_ExistenceSentence_2 = null;

        EObject this_PropertySentence_3 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:487:2: ( (this_ModalitySentence_0= ruleModalitySentence | this_PredicateSentence_1= rulePredicateSentence | this_ExistenceSentence_2= ruleExistenceSentence | this_PropertySentence_3= rulePropertySentence ) )
            // InternalRequirementDSL.g:488:2: (this_ModalitySentence_0= ruleModalitySentence | this_PredicateSentence_1= rulePredicateSentence | this_ExistenceSentence_2= ruleExistenceSentence | this_PropertySentence_3= rulePropertySentence )
            {
            // InternalRequirementDSL.g:488:2: (this_ModalitySentence_0= ruleModalitySentence | this_PredicateSentence_1= rulePredicateSentence | this_ExistenceSentence_2= ruleExistenceSentence | this_PropertySentence_3= rulePropertySentence )
            int alt12=4;
            alt12 = dfa12.predict(input);
            switch (alt12) {
                case 1 :
                    // InternalRequirementDSL.g:489:3: this_ModalitySentence_0= ruleModalitySentence
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
                    // InternalRequirementDSL.g:498:3: this_PredicateSentence_1= rulePredicateSentence
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
                    // InternalRequirementDSL.g:507:3: this_ExistenceSentence_2= ruleExistenceSentence
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
                    // InternalRequirementDSL.g:516:3: this_PropertySentence_3= rulePropertySentence
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
    // InternalRequirementDSL.g:528:1: entryRuleModalitySentence returns [EObject current=null] : iv_ruleModalitySentence= ruleModalitySentence EOF ;
    public final EObject entryRuleModalitySentence() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModalitySentence = null;


        try {
            // InternalRequirementDSL.g:528:57: (iv_ruleModalitySentence= ruleModalitySentence EOF )
            // InternalRequirementDSL.g:529:2: iv_ruleModalitySentence= ruleModalitySentence EOF
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
    // InternalRequirementDSL.g:535:1: ruleModalitySentence returns [EObject current=null] : ( ( (lv_actors_0_0= ruleActors ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_4_0= rulePredicate ) ) ( (lv_constraints_5_0= ruleConstraints ) )* ) ;
    public final EObject ruleModalitySentence() throws RecognitionException {
        EObject current = null;

        EObject lv_actors_0_0 = null;

        Enumerator lv_modelity_1_0 = null;

        AntlrDatatypeRuleToken lv_negation_2_0 = null;

        AntlrDatatypeRuleToken lv_auxiliarVerb_3_0 = null;

        EObject lv_predicate_4_0 = null;

        EObject lv_constraints_5_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:541:2: ( ( ( (lv_actors_0_0= ruleActors ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_4_0= rulePredicate ) ) ( (lv_constraints_5_0= ruleConstraints ) )* ) )
            // InternalRequirementDSL.g:542:2: ( ( (lv_actors_0_0= ruleActors ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_4_0= rulePredicate ) ) ( (lv_constraints_5_0= ruleConstraints ) )* )
            {
            // InternalRequirementDSL.g:542:2: ( ( (lv_actors_0_0= ruleActors ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_4_0= rulePredicate ) ) ( (lv_constraints_5_0= ruleConstraints ) )* )
            // InternalRequirementDSL.g:543:3: ( (lv_actors_0_0= ruleActors ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_4_0= rulePredicate ) ) ( (lv_constraints_5_0= ruleConstraints ) )*
            {
            // InternalRequirementDSL.g:543:3: ( (lv_actors_0_0= ruleActors ) )
            // InternalRequirementDSL.g:544:4: (lv_actors_0_0= ruleActors )
            {
            // InternalRequirementDSL.g:544:4: (lv_actors_0_0= ruleActors )
            // InternalRequirementDSL.g:545:5: lv_actors_0_0= ruleActors
            {

            					newCompositeNode(grammarAccess.getModalitySentenceAccess().getActorsActorsParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_13);
            lv_actors_0_0=ruleActors();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getModalitySentenceRule());
            					}
            					set(
            						current,
            						"actors",
            						lv_actors_0_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.Actors");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:562:3: ( (lv_modelity_1_0= ruleModality ) )
            // InternalRequirementDSL.g:563:4: (lv_modelity_1_0= ruleModality )
            {
            // InternalRequirementDSL.g:563:4: (lv_modelity_1_0= ruleModality )
            // InternalRequirementDSL.g:564:5: lv_modelity_1_0= ruleModality
            {

            					newCompositeNode(grammarAccess.getModalitySentenceAccess().getModelityModalityEnumRuleCall_1_0());
            				
            pushFollow(FOLLOW_14);
            lv_modelity_1_0=ruleModality();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getModalitySentenceRule());
            					}
            					set(
            						current,
            						"modelity",
            						lv_modelity_1_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.Modality");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:581:3: ( (lv_negation_2_0= ruleNegation ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( ((LA13_0>=74 && LA13_0<=78)) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalRequirementDSL.g:582:4: (lv_negation_2_0= ruleNegation )
                    {
                    // InternalRequirementDSL.g:582:4: (lv_negation_2_0= ruleNegation )
                    // InternalRequirementDSL.g:583:5: lv_negation_2_0= ruleNegation
                    {

                    					newCompositeNode(grammarAccess.getModalitySentenceAccess().getNegationNegationParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_14);
                    lv_negation_2_0=ruleNegation();

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

            // InternalRequirementDSL.g:600:3: ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>=26 && LA14_0<=31)) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalRequirementDSL.g:601:4: (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb )
                    {
                    // InternalRequirementDSL.g:601:4: (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb )
                    // InternalRequirementDSL.g:602:5: lv_auxiliarVerb_3_0= ruleAuxiliaryVerb
                    {

                    					newCompositeNode(grammarAccess.getModalitySentenceAccess().getAuxiliarVerbAuxiliaryVerbParserRuleCall_3_0());
                    				
                    pushFollow(FOLLOW_14);
                    lv_auxiliarVerb_3_0=ruleAuxiliaryVerb();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getModalitySentenceRule());
                    					}
                    					set(
                    						current,
                    						"auxiliarVerb",
                    						lv_auxiliarVerb_3_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.AuxiliaryVerb");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalRequirementDSL.g:619:3: ( (lv_predicate_4_0= rulePredicate ) )
            // InternalRequirementDSL.g:620:4: (lv_predicate_4_0= rulePredicate )
            {
            // InternalRequirementDSL.g:620:4: (lv_predicate_4_0= rulePredicate )
            // InternalRequirementDSL.g:621:5: lv_predicate_4_0= rulePredicate
            {

            					newCompositeNode(grammarAccess.getModalitySentenceAccess().getPredicatePredicateParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_15);
            lv_predicate_4_0=rulePredicate();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getModalitySentenceRule());
            					}
            					set(
            						current,
            						"predicate",
            						lv_predicate_4_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.Predicate");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:638:3: ( (lv_constraints_5_0= ruleConstraints ) )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==36||(LA15_0>=38 && LA15_0<=59)||LA15_0==89) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalRequirementDSL.g:639:4: (lv_constraints_5_0= ruleConstraints )
            	    {
            	    // InternalRequirementDSL.g:639:4: (lv_constraints_5_0= ruleConstraints )
            	    // InternalRequirementDSL.g:640:5: lv_constraints_5_0= ruleConstraints
            	    {

            	    					newCompositeNode(grammarAccess.getModalitySentenceAccess().getConstraintsConstraintsParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_15);
            	    lv_constraints_5_0=ruleConstraints();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getModalitySentenceRule());
            	    					}
            	    					add(
            	    						current,
            	    						"constraints",
            	    						lv_constraints_5_0,
            	    						"de.fraunhofer.isst.stars.RequirementDSL.Constraints");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop15;
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
    // $ANTLR end "ruleModalitySentence"


    // $ANTLR start "entryRulePredicateSentence"
    // InternalRequirementDSL.g:661:1: entryRulePredicateSentence returns [EObject current=null] : iv_rulePredicateSentence= rulePredicateSentence EOF ;
    public final EObject entryRulePredicateSentence() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePredicateSentence = null;


        try {
            // InternalRequirementDSL.g:661:58: (iv_rulePredicateSentence= rulePredicateSentence EOF )
            // InternalRequirementDSL.g:662:2: iv_rulePredicateSentence= rulePredicateSentence EOF
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
    // InternalRequirementDSL.g:668:1: rulePredicateSentence returns [EObject current=null] : ( ( ( (lv_actors_0_0= ruleActors ) ) ( (lv_predicate_1_0= rulePredicate ) ) ( (lv_constraints_2_0= ruleConstraints ) )* ) | ( ( (lv_actors_3_0= ruleActors ) ) ( (lv_auxiliarVerb_4_0= ruleAuxiliaryVerb ) ) ( (lv_negation_5_0= ruleNegation ) )? ( (lv_auxiliarVerb_6_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_7_0= rulePredicate ) ) ( (lv_constraints_8_0= ruleConstraints ) )* ) | ( ( (lv_actors_9_0= ruleActors ) ) ( (lv_auxiliarVerb_10_0= ruleAuxiliaryVerb ) ) ( (lv_negation_11_0= ruleNegation ) )? ( (lv_object_12_0= rulePredicateObject ) )? ( (lv_constraints_13_0= ruleConstraints ) )* ) ) ;
    public final EObject rulePredicateSentence() throws RecognitionException {
        EObject current = null;

        EObject lv_actors_0_0 = null;

        EObject lv_predicate_1_0 = null;

        EObject lv_constraints_2_0 = null;

        EObject lv_actors_3_0 = null;

        AntlrDatatypeRuleToken lv_auxiliarVerb_4_0 = null;

        AntlrDatatypeRuleToken lv_negation_5_0 = null;

        AntlrDatatypeRuleToken lv_auxiliarVerb_6_0 = null;

        EObject lv_predicate_7_0 = null;

        EObject lv_constraints_8_0 = null;

        EObject lv_actors_9_0 = null;

        AntlrDatatypeRuleToken lv_auxiliarVerb_10_0 = null;

        AntlrDatatypeRuleToken lv_negation_11_0 = null;

        EObject lv_object_12_0 = null;

        EObject lv_constraints_13_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:674:2: ( ( ( ( (lv_actors_0_0= ruleActors ) ) ( (lv_predicate_1_0= rulePredicate ) ) ( (lv_constraints_2_0= ruleConstraints ) )* ) | ( ( (lv_actors_3_0= ruleActors ) ) ( (lv_auxiliarVerb_4_0= ruleAuxiliaryVerb ) ) ( (lv_negation_5_0= ruleNegation ) )? ( (lv_auxiliarVerb_6_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_7_0= rulePredicate ) ) ( (lv_constraints_8_0= ruleConstraints ) )* ) | ( ( (lv_actors_9_0= ruleActors ) ) ( (lv_auxiliarVerb_10_0= ruleAuxiliaryVerb ) ) ( (lv_negation_11_0= ruleNegation ) )? ( (lv_object_12_0= rulePredicateObject ) )? ( (lv_constraints_13_0= ruleConstraints ) )* ) ) )
            // InternalRequirementDSL.g:675:2: ( ( ( (lv_actors_0_0= ruleActors ) ) ( (lv_predicate_1_0= rulePredicate ) ) ( (lv_constraints_2_0= ruleConstraints ) )* ) | ( ( (lv_actors_3_0= ruleActors ) ) ( (lv_auxiliarVerb_4_0= ruleAuxiliaryVerb ) ) ( (lv_negation_5_0= ruleNegation ) )? ( (lv_auxiliarVerb_6_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_7_0= rulePredicate ) ) ( (lv_constraints_8_0= ruleConstraints ) )* ) | ( ( (lv_actors_9_0= ruleActors ) ) ( (lv_auxiliarVerb_10_0= ruleAuxiliaryVerb ) ) ( (lv_negation_11_0= ruleNegation ) )? ( (lv_object_12_0= rulePredicateObject ) )? ( (lv_constraints_13_0= ruleConstraints ) )* ) )
            {
            // InternalRequirementDSL.g:675:2: ( ( ( (lv_actors_0_0= ruleActors ) ) ( (lv_predicate_1_0= rulePredicate ) ) ( (lv_constraints_2_0= ruleConstraints ) )* ) | ( ( (lv_actors_3_0= ruleActors ) ) ( (lv_auxiliarVerb_4_0= ruleAuxiliaryVerb ) ) ( (lv_negation_5_0= ruleNegation ) )? ( (lv_auxiliarVerb_6_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_7_0= rulePredicate ) ) ( (lv_constraints_8_0= ruleConstraints ) )* ) | ( ( (lv_actors_9_0= ruleActors ) ) ( (lv_auxiliarVerb_10_0= ruleAuxiliaryVerb ) ) ( (lv_negation_11_0= ruleNegation ) )? ( (lv_object_12_0= rulePredicateObject ) )? ( (lv_constraints_13_0= ruleConstraints ) )* ) )
            int alt23=3;
            alt23 = dfa23.predict(input);
            switch (alt23) {
                case 1 :
                    // InternalRequirementDSL.g:676:3: ( ( (lv_actors_0_0= ruleActors ) ) ( (lv_predicate_1_0= rulePredicate ) ) ( (lv_constraints_2_0= ruleConstraints ) )* )
                    {
                    // InternalRequirementDSL.g:676:3: ( ( (lv_actors_0_0= ruleActors ) ) ( (lv_predicate_1_0= rulePredicate ) ) ( (lv_constraints_2_0= ruleConstraints ) )* )
                    // InternalRequirementDSL.g:677:4: ( (lv_actors_0_0= ruleActors ) ) ( (lv_predicate_1_0= rulePredicate ) ) ( (lv_constraints_2_0= ruleConstraints ) )*
                    {
                    // InternalRequirementDSL.g:677:4: ( (lv_actors_0_0= ruleActors ) )
                    // InternalRequirementDSL.g:678:5: (lv_actors_0_0= ruleActors )
                    {
                    // InternalRequirementDSL.g:678:5: (lv_actors_0_0= ruleActors )
                    // InternalRequirementDSL.g:679:6: lv_actors_0_0= ruleActors
                    {

                    						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getActorsActorsParserRuleCall_0_0_0());
                    					
                    pushFollow(FOLLOW_14);
                    lv_actors_0_0=ruleActors();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                    						}
                    						set(
                    							current,
                    							"actors",
                    							lv_actors_0_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Actors");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:696:4: ( (lv_predicate_1_0= rulePredicate ) )
                    // InternalRequirementDSL.g:697:5: (lv_predicate_1_0= rulePredicate )
                    {
                    // InternalRequirementDSL.g:697:5: (lv_predicate_1_0= rulePredicate )
                    // InternalRequirementDSL.g:698:6: lv_predicate_1_0= rulePredicate
                    {

                    						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getPredicatePredicateParserRuleCall_0_1_0());
                    					
                    pushFollow(FOLLOW_15);
                    lv_predicate_1_0=rulePredicate();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                    						}
                    						set(
                    							current,
                    							"predicate",
                    							lv_predicate_1_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Predicate");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:715:4: ( (lv_constraints_2_0= ruleConstraints ) )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==36||(LA16_0>=38 && LA16_0<=59)||LA16_0==89) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:716:5: (lv_constraints_2_0= ruleConstraints )
                    	    {
                    	    // InternalRequirementDSL.g:716:5: (lv_constraints_2_0= ruleConstraints )
                    	    // InternalRequirementDSL.g:717:6: lv_constraints_2_0= ruleConstraints
                    	    {

                    	    						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getConstraintsConstraintsParserRuleCall_0_2_0());
                    	    					
                    	    pushFollow(FOLLOW_15);
                    	    lv_constraints_2_0=ruleConstraints();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"constraints",
                    	    							lv_constraints_2_0,
                    	    							"de.fraunhofer.isst.stars.RequirementDSL.Constraints");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);


                    }


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:736:3: ( ( (lv_actors_3_0= ruleActors ) ) ( (lv_auxiliarVerb_4_0= ruleAuxiliaryVerb ) ) ( (lv_negation_5_0= ruleNegation ) )? ( (lv_auxiliarVerb_6_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_7_0= rulePredicate ) ) ( (lv_constraints_8_0= ruleConstraints ) )* )
                    {
                    // InternalRequirementDSL.g:736:3: ( ( (lv_actors_3_0= ruleActors ) ) ( (lv_auxiliarVerb_4_0= ruleAuxiliaryVerb ) ) ( (lv_negation_5_0= ruleNegation ) )? ( (lv_auxiliarVerb_6_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_7_0= rulePredicate ) ) ( (lv_constraints_8_0= ruleConstraints ) )* )
                    // InternalRequirementDSL.g:737:4: ( (lv_actors_3_0= ruleActors ) ) ( (lv_auxiliarVerb_4_0= ruleAuxiliaryVerb ) ) ( (lv_negation_5_0= ruleNegation ) )? ( (lv_auxiliarVerb_6_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_7_0= rulePredicate ) ) ( (lv_constraints_8_0= ruleConstraints ) )*
                    {
                    // InternalRequirementDSL.g:737:4: ( (lv_actors_3_0= ruleActors ) )
                    // InternalRequirementDSL.g:738:5: (lv_actors_3_0= ruleActors )
                    {
                    // InternalRequirementDSL.g:738:5: (lv_actors_3_0= ruleActors )
                    // InternalRequirementDSL.g:739:6: lv_actors_3_0= ruleActors
                    {

                    						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getActorsActorsParserRuleCall_1_0_0());
                    					
                    pushFollow(FOLLOW_16);
                    lv_actors_3_0=ruleActors();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                    						}
                    						set(
                    							current,
                    							"actors",
                    							lv_actors_3_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Actors");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:756:4: ( (lv_auxiliarVerb_4_0= ruleAuxiliaryVerb ) )
                    // InternalRequirementDSL.g:757:5: (lv_auxiliarVerb_4_0= ruleAuxiliaryVerb )
                    {
                    // InternalRequirementDSL.g:757:5: (lv_auxiliarVerb_4_0= ruleAuxiliaryVerb )
                    // InternalRequirementDSL.g:758:6: lv_auxiliarVerb_4_0= ruleAuxiliaryVerb
                    {

                    						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getAuxiliarVerbAuxiliaryVerbParserRuleCall_1_1_0());
                    					
                    pushFollow(FOLLOW_14);
                    lv_auxiliarVerb_4_0=ruleAuxiliaryVerb();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                    						}
                    						add(
                    							current,
                    							"auxiliarVerb",
                    							lv_auxiliarVerb_4_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.AuxiliaryVerb");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:775:4: ( (lv_negation_5_0= ruleNegation ) )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( ((LA17_0>=74 && LA17_0<=78)) ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // InternalRequirementDSL.g:776:5: (lv_negation_5_0= ruleNegation )
                            {
                            // InternalRequirementDSL.g:776:5: (lv_negation_5_0= ruleNegation )
                            // InternalRequirementDSL.g:777:6: lv_negation_5_0= ruleNegation
                            {

                            						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getNegationNegationParserRuleCall_1_2_0());
                            					
                            pushFollow(FOLLOW_14);
                            lv_negation_5_0=ruleNegation();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
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

                    // InternalRequirementDSL.g:794:4: ( (lv_auxiliarVerb_6_0= ruleAuxiliaryVerb ) )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( ((LA18_0>=26 && LA18_0<=31)) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // InternalRequirementDSL.g:795:5: (lv_auxiliarVerb_6_0= ruleAuxiliaryVerb )
                            {
                            // InternalRequirementDSL.g:795:5: (lv_auxiliarVerb_6_0= ruleAuxiliaryVerb )
                            // InternalRequirementDSL.g:796:6: lv_auxiliarVerb_6_0= ruleAuxiliaryVerb
                            {

                            						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getAuxiliarVerbAuxiliaryVerbParserRuleCall_1_3_0());
                            					
                            pushFollow(FOLLOW_14);
                            lv_auxiliarVerb_6_0=ruleAuxiliaryVerb();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                            						}
                            						add(
                            							current,
                            							"auxiliarVerb",
                            							lv_auxiliarVerb_6_0,
                            							"de.fraunhofer.isst.stars.RequirementDSL.AuxiliaryVerb");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }

                    // InternalRequirementDSL.g:813:4: ( (lv_predicate_7_0= rulePredicate ) )
                    // InternalRequirementDSL.g:814:5: (lv_predicate_7_0= rulePredicate )
                    {
                    // InternalRequirementDSL.g:814:5: (lv_predicate_7_0= rulePredicate )
                    // InternalRequirementDSL.g:815:6: lv_predicate_7_0= rulePredicate
                    {

                    						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getPredicatePredicateParserRuleCall_1_4_0());
                    					
                    pushFollow(FOLLOW_15);
                    lv_predicate_7_0=rulePredicate();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                    						}
                    						set(
                    							current,
                    							"predicate",
                    							lv_predicate_7_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Predicate");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:832:4: ( (lv_constraints_8_0= ruleConstraints ) )*
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==36||(LA19_0>=38 && LA19_0<=59)||LA19_0==89) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:833:5: (lv_constraints_8_0= ruleConstraints )
                    	    {
                    	    // InternalRequirementDSL.g:833:5: (lv_constraints_8_0= ruleConstraints )
                    	    // InternalRequirementDSL.g:834:6: lv_constraints_8_0= ruleConstraints
                    	    {

                    	    						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getConstraintsConstraintsParserRuleCall_1_5_0());
                    	    					
                    	    pushFollow(FOLLOW_15);
                    	    lv_constraints_8_0=ruleConstraints();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"constraints",
                    	    							lv_constraints_8_0,
                    	    							"de.fraunhofer.isst.stars.RequirementDSL.Constraints");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop19;
                        }
                    } while (true);


                    }


                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:853:3: ( ( (lv_actors_9_0= ruleActors ) ) ( (lv_auxiliarVerb_10_0= ruleAuxiliaryVerb ) ) ( (lv_negation_11_0= ruleNegation ) )? ( (lv_object_12_0= rulePredicateObject ) )? ( (lv_constraints_13_0= ruleConstraints ) )* )
                    {
                    // InternalRequirementDSL.g:853:3: ( ( (lv_actors_9_0= ruleActors ) ) ( (lv_auxiliarVerb_10_0= ruleAuxiliaryVerb ) ) ( (lv_negation_11_0= ruleNegation ) )? ( (lv_object_12_0= rulePredicateObject ) )? ( (lv_constraints_13_0= ruleConstraints ) )* )
                    // InternalRequirementDSL.g:854:4: ( (lv_actors_9_0= ruleActors ) ) ( (lv_auxiliarVerb_10_0= ruleAuxiliaryVerb ) ) ( (lv_negation_11_0= ruleNegation ) )? ( (lv_object_12_0= rulePredicateObject ) )? ( (lv_constraints_13_0= ruleConstraints ) )*
                    {
                    // InternalRequirementDSL.g:854:4: ( (lv_actors_9_0= ruleActors ) )
                    // InternalRequirementDSL.g:855:5: (lv_actors_9_0= ruleActors )
                    {
                    // InternalRequirementDSL.g:855:5: (lv_actors_9_0= ruleActors )
                    // InternalRequirementDSL.g:856:6: lv_actors_9_0= ruleActors
                    {

                    						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getActorsActorsParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_16);
                    lv_actors_9_0=ruleActors();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                    						}
                    						set(
                    							current,
                    							"actors",
                    							lv_actors_9_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Actors");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:873:4: ( (lv_auxiliarVerb_10_0= ruleAuxiliaryVerb ) )
                    // InternalRequirementDSL.g:874:5: (lv_auxiliarVerb_10_0= ruleAuxiliaryVerb )
                    {
                    // InternalRequirementDSL.g:874:5: (lv_auxiliarVerb_10_0= ruleAuxiliaryVerb )
                    // InternalRequirementDSL.g:875:6: lv_auxiliarVerb_10_0= ruleAuxiliaryVerb
                    {

                    						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getAuxiliarVerbAuxiliaryVerbParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_17);
                    lv_auxiliarVerb_10_0=ruleAuxiliaryVerb();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                    						}
                    						add(
                    							current,
                    							"auxiliarVerb",
                    							lv_auxiliarVerb_10_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.AuxiliaryVerb");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:892:4: ( (lv_negation_11_0= ruleNegation ) )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( ((LA20_0>=74 && LA20_0<=78)) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // InternalRequirementDSL.g:893:5: (lv_negation_11_0= ruleNegation )
                            {
                            // InternalRequirementDSL.g:893:5: (lv_negation_11_0= ruleNegation )
                            // InternalRequirementDSL.g:894:6: lv_negation_11_0= ruleNegation
                            {

                            						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getNegationNegationParserRuleCall_2_2_0());
                            					
                            pushFollow(FOLLOW_18);
                            lv_negation_11_0=ruleNegation();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
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

                    // InternalRequirementDSL.g:911:4: ( (lv_object_12_0= rulePredicateObject ) )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( ((LA21_0>=60 && LA21_0<=66)||(LA21_0>=68 && LA21_0<=73)||(LA21_0>=79 && LA21_0<=88)) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // InternalRequirementDSL.g:912:5: (lv_object_12_0= rulePredicateObject )
                            {
                            // InternalRequirementDSL.g:912:5: (lv_object_12_0= rulePredicateObject )
                            // InternalRequirementDSL.g:913:6: lv_object_12_0= rulePredicateObject
                            {

                            						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getObjectPredicateObjectParserRuleCall_2_3_0());
                            					
                            pushFollow(FOLLOW_15);
                            lv_object_12_0=rulePredicateObject();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                            						}
                            						set(
                            							current,
                            							"object",
                            							lv_object_12_0,
                            							"de.fraunhofer.isst.stars.RequirementDSL.PredicateObject");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }

                    // InternalRequirementDSL.g:930:4: ( (lv_constraints_13_0= ruleConstraints ) )*
                    loop22:
                    do {
                        int alt22=2;
                        int LA22_0 = input.LA(1);

                        if ( (LA22_0==36||(LA22_0>=38 && LA22_0<=59)||LA22_0==89) ) {
                            alt22=1;
                        }


                        switch (alt22) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:931:5: (lv_constraints_13_0= ruleConstraints )
                    	    {
                    	    // InternalRequirementDSL.g:931:5: (lv_constraints_13_0= ruleConstraints )
                    	    // InternalRequirementDSL.g:932:6: lv_constraints_13_0= ruleConstraints
                    	    {

                    	    						newCompositeNode(grammarAccess.getPredicateSentenceAccess().getConstraintsConstraintsParserRuleCall_2_4_0());
                    	    					
                    	    pushFollow(FOLLOW_15);
                    	    lv_constraints_13_0=ruleConstraints();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getPredicateSentenceRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"constraints",
                    	    							lv_constraints_13_0,
                    	    							"de.fraunhofer.isst.stars.RequirementDSL.Constraints");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop22;
                        }
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
    // $ANTLR end "rulePredicateSentence"


    // $ANTLR start "entryRuleExistenceSentence"
    // InternalRequirementDSL.g:954:1: entryRuleExistenceSentence returns [EObject current=null] : iv_ruleExistenceSentence= ruleExistenceSentence EOF ;
    public final EObject entryRuleExistenceSentence() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExistenceSentence = null;


        try {
            // InternalRequirementDSL.g:954:58: (iv_ruleExistenceSentence= ruleExistenceSentence EOF )
            // InternalRequirementDSL.g:955:2: iv_ruleExistenceSentence= ruleExistenceSentence EOF
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
    // InternalRequirementDSL.g:961:1: ruleExistenceSentence returns [EObject current=null] : (this_ExistencePreface_0= ruleExistencePreface ( (lv_actors_1_0= ruleActors ) ) otherlv_2= ',' ( (lv_relativeClause_3_0= rulerelativeClause ) ) otherlv_4= ',' ) ;
    public final EObject ruleExistenceSentence() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_ExistencePreface_0 = null;

        EObject lv_actors_1_0 = null;

        EObject lv_relativeClause_3_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:967:2: ( (this_ExistencePreface_0= ruleExistencePreface ( (lv_actors_1_0= ruleActors ) ) otherlv_2= ',' ( (lv_relativeClause_3_0= rulerelativeClause ) ) otherlv_4= ',' ) )
            // InternalRequirementDSL.g:968:2: (this_ExistencePreface_0= ruleExistencePreface ( (lv_actors_1_0= ruleActors ) ) otherlv_2= ',' ( (lv_relativeClause_3_0= rulerelativeClause ) ) otherlv_4= ',' )
            {
            // InternalRequirementDSL.g:968:2: (this_ExistencePreface_0= ruleExistencePreface ( (lv_actors_1_0= ruleActors ) ) otherlv_2= ',' ( (lv_relativeClause_3_0= rulerelativeClause ) ) otherlv_4= ',' )
            // InternalRequirementDSL.g:969:3: this_ExistencePreface_0= ruleExistencePreface ( (lv_actors_1_0= ruleActors ) ) otherlv_2= ',' ( (lv_relativeClause_3_0= rulerelativeClause ) ) otherlv_4= ','
            {

            			newCompositeNode(grammarAccess.getExistenceSentenceAccess().getExistencePrefaceParserRuleCall_0());
            		
            pushFollow(FOLLOW_19);
            this_ExistencePreface_0=ruleExistencePreface();

            state._fsp--;


            			current = this_ExistencePreface_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalRequirementDSL.g:977:3: ( (lv_actors_1_0= ruleActors ) )
            // InternalRequirementDSL.g:978:4: (lv_actors_1_0= ruleActors )
            {
            // InternalRequirementDSL.g:978:4: (lv_actors_1_0= ruleActors )
            // InternalRequirementDSL.g:979:5: lv_actors_1_0= ruleActors
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

            otherlv_2=(Token)match(input,18,FOLLOW_21); 

            			newLeafNode(otherlv_2, grammarAccess.getExistenceSentenceAccess().getCommaKeyword_2());
            		
            // InternalRequirementDSL.g:1000:3: ( (lv_relativeClause_3_0= rulerelativeClause ) )
            // InternalRequirementDSL.g:1001:4: (lv_relativeClause_3_0= rulerelativeClause )
            {
            // InternalRequirementDSL.g:1001:4: (lv_relativeClause_3_0= rulerelativeClause )
            // InternalRequirementDSL.g:1002:5: lv_relativeClause_3_0= rulerelativeClause
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

            otherlv_4=(Token)match(input,18,FOLLOW_2); 

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
    // InternalRequirementDSL.g:1027:1: entryRulePropertySentence returns [EObject current=null] : iv_rulePropertySentence= rulePropertySentence EOF ;
    public final EObject entryRulePropertySentence() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertySentence = null;


        try {
            // InternalRequirementDSL.g:1027:57: (iv_rulePropertySentence= rulePropertySentence EOF )
            // InternalRequirementDSL.g:1028:2: iv_rulePropertySentence= rulePropertySentence EOF
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
    // InternalRequirementDSL.g:1034:1: rulePropertySentence returns [EObject current=null] : ( ( ( (lv_property_0_0= ruleProperty ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_4_0= rulePredicate ) ) ( (lv_constraints_5_0= ruleConstraints ) )* ) | ( ( (lv_property_6_0= ruleProperty ) ) ( (lv_modelity_7_0= ruleModality ) ) ( (lv_negation_8_0= ruleNegation ) )? ( (lv_auxiliarVerb_9_0= ruleAuxiliaryVerb ) ) ( (lv_object_10_0= rulePredicateObject ) )? ( (lv_constraints_11_0= ruleConstraints ) )* ) | ( ( (lv_property_12_0= ruleProperty ) ) ( (lv_auxiliarVerb_13_0= ruleAuxiliaryVerb ) ) ( (lv_negation_14_0= ruleNegation ) )? ( ( ( ( (lv_predicate_15_0= rulePredicate ) ) | ( (lv_object_16_0= rulePredicateObject ) ) ) ( (lv_constraints_17_0= ruleConstraints ) )* ) | ( (lv_constraints_18_0= ruleConstraints ) )+ ) ) | ( ( (lv_property_19_0= ruleProperty ) ) ( ( (lv_predicateWord_20_0= ruleWORD ) ) | ( (lv_predicateWord_21_0= RULE_STRING ) ) ) ( (lv_object_22_0= rulePredicateObject ) )? ( (lv_constraints_23_0= ruleConstraints ) )* ) ) ;
    public final EObject rulePropertySentence() throws RecognitionException {
        EObject current = null;

        Token lv_predicateWord_21_0=null;
        EObject lv_property_0_0 = null;

        Enumerator lv_modelity_1_0 = null;

        AntlrDatatypeRuleToken lv_negation_2_0 = null;

        AntlrDatatypeRuleToken lv_auxiliarVerb_3_0 = null;

        EObject lv_predicate_4_0 = null;

        EObject lv_constraints_5_0 = null;

        EObject lv_property_6_0 = null;

        Enumerator lv_modelity_7_0 = null;

        AntlrDatatypeRuleToken lv_negation_8_0 = null;

        AntlrDatatypeRuleToken lv_auxiliarVerb_9_0 = null;

        EObject lv_object_10_0 = null;

        EObject lv_constraints_11_0 = null;

        EObject lv_property_12_0 = null;

        AntlrDatatypeRuleToken lv_auxiliarVerb_13_0 = null;

        AntlrDatatypeRuleToken lv_negation_14_0 = null;

        EObject lv_predicate_15_0 = null;

        EObject lv_object_16_0 = null;

        EObject lv_constraints_17_0 = null;

        EObject lv_constraints_18_0 = null;

        EObject lv_property_19_0 = null;

        AntlrDatatypeRuleToken lv_predicateWord_20_0 = null;

        EObject lv_object_22_0 = null;

        EObject lv_constraints_23_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:1040:2: ( ( ( ( (lv_property_0_0= ruleProperty ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_4_0= rulePredicate ) ) ( (lv_constraints_5_0= ruleConstraints ) )* ) | ( ( (lv_property_6_0= ruleProperty ) ) ( (lv_modelity_7_0= ruleModality ) ) ( (lv_negation_8_0= ruleNegation ) )? ( (lv_auxiliarVerb_9_0= ruleAuxiliaryVerb ) ) ( (lv_object_10_0= rulePredicateObject ) )? ( (lv_constraints_11_0= ruleConstraints ) )* ) | ( ( (lv_property_12_0= ruleProperty ) ) ( (lv_auxiliarVerb_13_0= ruleAuxiliaryVerb ) ) ( (lv_negation_14_0= ruleNegation ) )? ( ( ( ( (lv_predicate_15_0= rulePredicate ) ) | ( (lv_object_16_0= rulePredicateObject ) ) ) ( (lv_constraints_17_0= ruleConstraints ) )* ) | ( (lv_constraints_18_0= ruleConstraints ) )+ ) ) | ( ( (lv_property_19_0= ruleProperty ) ) ( ( (lv_predicateWord_20_0= ruleWORD ) ) | ( (lv_predicateWord_21_0= RULE_STRING ) ) ) ( (lv_object_22_0= rulePredicateObject ) )? ( (lv_constraints_23_0= ruleConstraints ) )* ) ) )
            // InternalRequirementDSL.g:1041:2: ( ( ( (lv_property_0_0= ruleProperty ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_4_0= rulePredicate ) ) ( (lv_constraints_5_0= ruleConstraints ) )* ) | ( ( (lv_property_6_0= ruleProperty ) ) ( (lv_modelity_7_0= ruleModality ) ) ( (lv_negation_8_0= ruleNegation ) )? ( (lv_auxiliarVerb_9_0= ruleAuxiliaryVerb ) ) ( (lv_object_10_0= rulePredicateObject ) )? ( (lv_constraints_11_0= ruleConstraints ) )* ) | ( ( (lv_property_12_0= ruleProperty ) ) ( (lv_auxiliarVerb_13_0= ruleAuxiliaryVerb ) ) ( (lv_negation_14_0= ruleNegation ) )? ( ( ( ( (lv_predicate_15_0= rulePredicate ) ) | ( (lv_object_16_0= rulePredicateObject ) ) ) ( (lv_constraints_17_0= ruleConstraints ) )* ) | ( (lv_constraints_18_0= ruleConstraints ) )+ ) ) | ( ( (lv_property_19_0= ruleProperty ) ) ( ( (lv_predicateWord_20_0= ruleWORD ) ) | ( (lv_predicateWord_21_0= RULE_STRING ) ) ) ( (lv_object_22_0= rulePredicateObject ) )? ( (lv_constraints_23_0= ruleConstraints ) )* ) )
            {
            // InternalRequirementDSL.g:1041:2: ( ( ( (lv_property_0_0= ruleProperty ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_4_0= rulePredicate ) ) ( (lv_constraints_5_0= ruleConstraints ) )* ) | ( ( (lv_property_6_0= ruleProperty ) ) ( (lv_modelity_7_0= ruleModality ) ) ( (lv_negation_8_0= ruleNegation ) )? ( (lv_auxiliarVerb_9_0= ruleAuxiliaryVerb ) ) ( (lv_object_10_0= rulePredicateObject ) )? ( (lv_constraints_11_0= ruleConstraints ) )* ) | ( ( (lv_property_12_0= ruleProperty ) ) ( (lv_auxiliarVerb_13_0= ruleAuxiliaryVerb ) ) ( (lv_negation_14_0= ruleNegation ) )? ( ( ( ( (lv_predicate_15_0= rulePredicate ) ) | ( (lv_object_16_0= rulePredicateObject ) ) ) ( (lv_constraints_17_0= ruleConstraints ) )* ) | ( (lv_constraints_18_0= ruleConstraints ) )+ ) ) | ( ( (lv_property_19_0= ruleProperty ) ) ( ( (lv_predicateWord_20_0= ruleWORD ) ) | ( (lv_predicateWord_21_0= RULE_STRING ) ) ) ( (lv_object_22_0= rulePredicateObject ) )? ( (lv_constraints_23_0= ruleConstraints ) )* ) )
            int alt38=4;
            alt38 = dfa38.predict(input);
            switch (alt38) {
                case 1 :
                    // InternalRequirementDSL.g:1042:3: ( ( (lv_property_0_0= ruleProperty ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_4_0= rulePredicate ) ) ( (lv_constraints_5_0= ruleConstraints ) )* )
                    {
                    // InternalRequirementDSL.g:1042:3: ( ( (lv_property_0_0= ruleProperty ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_4_0= rulePredicate ) ) ( (lv_constraints_5_0= ruleConstraints ) )* )
                    // InternalRequirementDSL.g:1043:4: ( (lv_property_0_0= ruleProperty ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_4_0= rulePredicate ) ) ( (lv_constraints_5_0= ruleConstraints ) )*
                    {
                    // InternalRequirementDSL.g:1043:4: ( (lv_property_0_0= ruleProperty ) )
                    // InternalRequirementDSL.g:1044:5: (lv_property_0_0= ruleProperty )
                    {
                    // InternalRequirementDSL.g:1044:5: (lv_property_0_0= ruleProperty )
                    // InternalRequirementDSL.g:1045:6: lv_property_0_0= ruleProperty
                    {

                    						newCompositeNode(grammarAccess.getPropertySentenceAccess().getPropertyPropertyParserRuleCall_0_0_0());
                    					
                    pushFollow(FOLLOW_13);
                    lv_property_0_0=ruleProperty();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                    						}
                    						set(
                    							current,
                    							"property",
                    							lv_property_0_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Property");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1062:4: ( (lv_modelity_1_0= ruleModality ) )
                    // InternalRequirementDSL.g:1063:5: (lv_modelity_1_0= ruleModality )
                    {
                    // InternalRequirementDSL.g:1063:5: (lv_modelity_1_0= ruleModality )
                    // InternalRequirementDSL.g:1064:6: lv_modelity_1_0= ruleModality
                    {

                    						newCompositeNode(grammarAccess.getPropertySentenceAccess().getModelityModalityEnumRuleCall_0_1_0());
                    					
                    pushFollow(FOLLOW_14);
                    lv_modelity_1_0=ruleModality();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                    						}
                    						set(
                    							current,
                    							"modelity",
                    							lv_modelity_1_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Modality");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1081:4: ( (lv_negation_2_0= ruleNegation ) )?
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( ((LA24_0>=74 && LA24_0<=78)) ) {
                        alt24=1;
                    }
                    switch (alt24) {
                        case 1 :
                            // InternalRequirementDSL.g:1082:5: (lv_negation_2_0= ruleNegation )
                            {
                            // InternalRequirementDSL.g:1082:5: (lv_negation_2_0= ruleNegation )
                            // InternalRequirementDSL.g:1083:6: lv_negation_2_0= ruleNegation
                            {

                            						newCompositeNode(grammarAccess.getPropertySentenceAccess().getNegationNegationParserRuleCall_0_2_0());
                            					
                            pushFollow(FOLLOW_14);
                            lv_negation_2_0=ruleNegation();

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

                    // InternalRequirementDSL.g:1100:4: ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )?
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( ((LA25_0>=26 && LA25_0<=31)) ) {
                        alt25=1;
                    }
                    switch (alt25) {
                        case 1 :
                            // InternalRequirementDSL.g:1101:5: (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb )
                            {
                            // InternalRequirementDSL.g:1101:5: (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb )
                            // InternalRequirementDSL.g:1102:6: lv_auxiliarVerb_3_0= ruleAuxiliaryVerb
                            {

                            						newCompositeNode(grammarAccess.getPropertySentenceAccess().getAuxiliarVerbAuxiliaryVerbParserRuleCall_0_3_0());
                            					
                            pushFollow(FOLLOW_14);
                            lv_auxiliarVerb_3_0=ruleAuxiliaryVerb();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                            						}
                            						set(
                            							current,
                            							"auxiliarVerb",
                            							lv_auxiliarVerb_3_0,
                            							"de.fraunhofer.isst.stars.RequirementDSL.AuxiliaryVerb");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }

                    // InternalRequirementDSL.g:1119:4: ( (lv_predicate_4_0= rulePredicate ) )
                    // InternalRequirementDSL.g:1120:5: (lv_predicate_4_0= rulePredicate )
                    {
                    // InternalRequirementDSL.g:1120:5: (lv_predicate_4_0= rulePredicate )
                    // InternalRequirementDSL.g:1121:6: lv_predicate_4_0= rulePredicate
                    {

                    						newCompositeNode(grammarAccess.getPropertySentenceAccess().getPredicatePredicateParserRuleCall_0_4_0());
                    					
                    pushFollow(FOLLOW_15);
                    lv_predicate_4_0=rulePredicate();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                    						}
                    						set(
                    							current,
                    							"predicate",
                    							lv_predicate_4_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Predicate");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1138:4: ( (lv_constraints_5_0= ruleConstraints ) )*
                    loop26:
                    do {
                        int alt26=2;
                        int LA26_0 = input.LA(1);

                        if ( (LA26_0==36||(LA26_0>=38 && LA26_0<=59)||LA26_0==89) ) {
                            alt26=1;
                        }


                        switch (alt26) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:1139:5: (lv_constraints_5_0= ruleConstraints )
                    	    {
                    	    // InternalRequirementDSL.g:1139:5: (lv_constraints_5_0= ruleConstraints )
                    	    // InternalRequirementDSL.g:1140:6: lv_constraints_5_0= ruleConstraints
                    	    {

                    	    						newCompositeNode(grammarAccess.getPropertySentenceAccess().getConstraintsConstraintsParserRuleCall_0_5_0());
                    	    					
                    	    pushFollow(FOLLOW_15);
                    	    lv_constraints_5_0=ruleConstraints();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"constraints",
                    	    							lv_constraints_5_0,
                    	    							"de.fraunhofer.isst.stars.RequirementDSL.Constraints");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop26;
                        }
                    } while (true);


                    }


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:1159:3: ( ( (lv_property_6_0= ruleProperty ) ) ( (lv_modelity_7_0= ruleModality ) ) ( (lv_negation_8_0= ruleNegation ) )? ( (lv_auxiliarVerb_9_0= ruleAuxiliaryVerb ) ) ( (lv_object_10_0= rulePredicateObject ) )? ( (lv_constraints_11_0= ruleConstraints ) )* )
                    {
                    // InternalRequirementDSL.g:1159:3: ( ( (lv_property_6_0= ruleProperty ) ) ( (lv_modelity_7_0= ruleModality ) ) ( (lv_negation_8_0= ruleNegation ) )? ( (lv_auxiliarVerb_9_0= ruleAuxiliaryVerb ) ) ( (lv_object_10_0= rulePredicateObject ) )? ( (lv_constraints_11_0= ruleConstraints ) )* )
                    // InternalRequirementDSL.g:1160:4: ( (lv_property_6_0= ruleProperty ) ) ( (lv_modelity_7_0= ruleModality ) ) ( (lv_negation_8_0= ruleNegation ) )? ( (lv_auxiliarVerb_9_0= ruleAuxiliaryVerb ) ) ( (lv_object_10_0= rulePredicateObject ) )? ( (lv_constraints_11_0= ruleConstraints ) )*
                    {
                    // InternalRequirementDSL.g:1160:4: ( (lv_property_6_0= ruleProperty ) )
                    // InternalRequirementDSL.g:1161:5: (lv_property_6_0= ruleProperty )
                    {
                    // InternalRequirementDSL.g:1161:5: (lv_property_6_0= ruleProperty )
                    // InternalRequirementDSL.g:1162:6: lv_property_6_0= ruleProperty
                    {

                    						newCompositeNode(grammarAccess.getPropertySentenceAccess().getPropertyPropertyParserRuleCall_1_0_0());
                    					
                    pushFollow(FOLLOW_13);
                    lv_property_6_0=ruleProperty();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                    						}
                    						set(
                    							current,
                    							"property",
                    							lv_property_6_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Property");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1179:4: ( (lv_modelity_7_0= ruleModality ) )
                    // InternalRequirementDSL.g:1180:5: (lv_modelity_7_0= ruleModality )
                    {
                    // InternalRequirementDSL.g:1180:5: (lv_modelity_7_0= ruleModality )
                    // InternalRequirementDSL.g:1181:6: lv_modelity_7_0= ruleModality
                    {

                    						newCompositeNode(grammarAccess.getPropertySentenceAccess().getModelityModalityEnumRuleCall_1_1_0());
                    					
                    pushFollow(FOLLOW_22);
                    lv_modelity_7_0=ruleModality();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                    						}
                    						set(
                    							current,
                    							"modelity",
                    							lv_modelity_7_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Modality");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1198:4: ( (lv_negation_8_0= ruleNegation ) )?
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( ((LA27_0>=74 && LA27_0<=78)) ) {
                        alt27=1;
                    }
                    switch (alt27) {
                        case 1 :
                            // InternalRequirementDSL.g:1199:5: (lv_negation_8_0= ruleNegation )
                            {
                            // InternalRequirementDSL.g:1199:5: (lv_negation_8_0= ruleNegation )
                            // InternalRequirementDSL.g:1200:6: lv_negation_8_0= ruleNegation
                            {

                            						newCompositeNode(grammarAccess.getPropertySentenceAccess().getNegationNegationParserRuleCall_1_2_0());
                            					
                            pushFollow(FOLLOW_16);
                            lv_negation_8_0=ruleNegation();

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

                    // InternalRequirementDSL.g:1217:4: ( (lv_auxiliarVerb_9_0= ruleAuxiliaryVerb ) )
                    // InternalRequirementDSL.g:1218:5: (lv_auxiliarVerb_9_0= ruleAuxiliaryVerb )
                    {
                    // InternalRequirementDSL.g:1218:5: (lv_auxiliarVerb_9_0= ruleAuxiliaryVerb )
                    // InternalRequirementDSL.g:1219:6: lv_auxiliarVerb_9_0= ruleAuxiliaryVerb
                    {

                    						newCompositeNode(grammarAccess.getPropertySentenceAccess().getAuxiliarVerbAuxiliaryVerbParserRuleCall_1_3_0());
                    					
                    pushFollow(FOLLOW_18);
                    lv_auxiliarVerb_9_0=ruleAuxiliaryVerb();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                    						}
                    						set(
                    							current,
                    							"auxiliarVerb",
                    							lv_auxiliarVerb_9_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.AuxiliaryVerb");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1236:4: ( (lv_object_10_0= rulePredicateObject ) )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( ((LA28_0>=60 && LA28_0<=66)||(LA28_0>=68 && LA28_0<=73)||(LA28_0>=79 && LA28_0<=88)) ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // InternalRequirementDSL.g:1237:5: (lv_object_10_0= rulePredicateObject )
                            {
                            // InternalRequirementDSL.g:1237:5: (lv_object_10_0= rulePredicateObject )
                            // InternalRequirementDSL.g:1238:6: lv_object_10_0= rulePredicateObject
                            {

                            						newCompositeNode(grammarAccess.getPropertySentenceAccess().getObjectPredicateObjectParserRuleCall_1_4_0());
                            					
                            pushFollow(FOLLOW_15);
                            lv_object_10_0=rulePredicateObject();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                            						}
                            						set(
                            							current,
                            							"object",
                            							lv_object_10_0,
                            							"de.fraunhofer.isst.stars.RequirementDSL.PredicateObject");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }

                    // InternalRequirementDSL.g:1255:4: ( (lv_constraints_11_0= ruleConstraints ) )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==36||(LA29_0>=38 && LA29_0<=59)||LA29_0==89) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:1256:5: (lv_constraints_11_0= ruleConstraints )
                    	    {
                    	    // InternalRequirementDSL.g:1256:5: (lv_constraints_11_0= ruleConstraints )
                    	    // InternalRequirementDSL.g:1257:6: lv_constraints_11_0= ruleConstraints
                    	    {

                    	    						newCompositeNode(grammarAccess.getPropertySentenceAccess().getConstraintsConstraintsParserRuleCall_1_5_0());
                    	    					
                    	    pushFollow(FOLLOW_15);
                    	    lv_constraints_11_0=ruleConstraints();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"constraints",
                    	    							lv_constraints_11_0,
                    	    							"de.fraunhofer.isst.stars.RequirementDSL.Constraints");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);


                    }


                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:1276:3: ( ( (lv_property_12_0= ruleProperty ) ) ( (lv_auxiliarVerb_13_0= ruleAuxiliaryVerb ) ) ( (lv_negation_14_0= ruleNegation ) )? ( ( ( ( (lv_predicate_15_0= rulePredicate ) ) | ( (lv_object_16_0= rulePredicateObject ) ) ) ( (lv_constraints_17_0= ruleConstraints ) )* ) | ( (lv_constraints_18_0= ruleConstraints ) )+ ) )
                    {
                    // InternalRequirementDSL.g:1276:3: ( ( (lv_property_12_0= ruleProperty ) ) ( (lv_auxiliarVerb_13_0= ruleAuxiliaryVerb ) ) ( (lv_negation_14_0= ruleNegation ) )? ( ( ( ( (lv_predicate_15_0= rulePredicate ) ) | ( (lv_object_16_0= rulePredicateObject ) ) ) ( (lv_constraints_17_0= ruleConstraints ) )* ) | ( (lv_constraints_18_0= ruleConstraints ) )+ ) )
                    // InternalRequirementDSL.g:1277:4: ( (lv_property_12_0= ruleProperty ) ) ( (lv_auxiliarVerb_13_0= ruleAuxiliaryVerb ) ) ( (lv_negation_14_0= ruleNegation ) )? ( ( ( ( (lv_predicate_15_0= rulePredicate ) ) | ( (lv_object_16_0= rulePredicateObject ) ) ) ( (lv_constraints_17_0= ruleConstraints ) )* ) | ( (lv_constraints_18_0= ruleConstraints ) )+ )
                    {
                    // InternalRequirementDSL.g:1277:4: ( (lv_property_12_0= ruleProperty ) )
                    // InternalRequirementDSL.g:1278:5: (lv_property_12_0= ruleProperty )
                    {
                    // InternalRequirementDSL.g:1278:5: (lv_property_12_0= ruleProperty )
                    // InternalRequirementDSL.g:1279:6: lv_property_12_0= ruleProperty
                    {

                    						newCompositeNode(grammarAccess.getPropertySentenceAccess().getPropertyPropertyParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_16);
                    lv_property_12_0=ruleProperty();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                    						}
                    						set(
                    							current,
                    							"property",
                    							lv_property_12_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Property");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1296:4: ( (lv_auxiliarVerb_13_0= ruleAuxiliaryVerb ) )
                    // InternalRequirementDSL.g:1297:5: (lv_auxiliarVerb_13_0= ruleAuxiliaryVerb )
                    {
                    // InternalRequirementDSL.g:1297:5: (lv_auxiliarVerb_13_0= ruleAuxiliaryVerb )
                    // InternalRequirementDSL.g:1298:6: lv_auxiliarVerb_13_0= ruleAuxiliaryVerb
                    {

                    						newCompositeNode(grammarAccess.getPropertySentenceAccess().getAuxiliarVerbAuxiliaryVerbParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_23);
                    lv_auxiliarVerb_13_0=ruleAuxiliaryVerb();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                    						}
                    						set(
                    							current,
                    							"auxiliarVerb",
                    							lv_auxiliarVerb_13_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.AuxiliaryVerb");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1315:4: ( (lv_negation_14_0= ruleNegation ) )?
                    int alt30=2;
                    int LA30_0 = input.LA(1);

                    if ( ((LA30_0>=74 && LA30_0<=78)) ) {
                        alt30=1;
                    }
                    switch (alt30) {
                        case 1 :
                            // InternalRequirementDSL.g:1316:5: (lv_negation_14_0= ruleNegation )
                            {
                            // InternalRequirementDSL.g:1316:5: (lv_negation_14_0= ruleNegation )
                            // InternalRequirementDSL.g:1317:6: lv_negation_14_0= ruleNegation
                            {

                            						newCompositeNode(grammarAccess.getPropertySentenceAccess().getNegationNegationParserRuleCall_2_2_0());
                            					
                            pushFollow(FOLLOW_23);
                            lv_negation_14_0=ruleNegation();

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

                    // InternalRequirementDSL.g:1334:4: ( ( ( ( (lv_predicate_15_0= rulePredicate ) ) | ( (lv_object_16_0= rulePredicateObject ) ) ) ( (lv_constraints_17_0= ruleConstraints ) )* ) | ( (lv_constraints_18_0= ruleConstraints ) )+ )
                    int alt34=2;
                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==RULE_STRING||LA34_0==RULE_ID||(LA34_0>=60 && LA34_0<=66)||(LA34_0>=68 && LA34_0<=73)||(LA34_0>=79 && LA34_0<=88)) ) {
                        alt34=1;
                    }
                    else if ( (LA34_0==36||(LA34_0>=38 && LA34_0<=59)||LA34_0==89) ) {
                        alt34=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 34, 0, input);

                        throw nvae;
                    }
                    switch (alt34) {
                        case 1 :
                            // InternalRequirementDSL.g:1335:5: ( ( ( (lv_predicate_15_0= rulePredicate ) ) | ( (lv_object_16_0= rulePredicateObject ) ) ) ( (lv_constraints_17_0= ruleConstraints ) )* )
                            {
                            // InternalRequirementDSL.g:1335:5: ( ( ( (lv_predicate_15_0= rulePredicate ) ) | ( (lv_object_16_0= rulePredicateObject ) ) ) ( (lv_constraints_17_0= ruleConstraints ) )* )
                            // InternalRequirementDSL.g:1336:6: ( ( (lv_predicate_15_0= rulePredicate ) ) | ( (lv_object_16_0= rulePredicateObject ) ) ) ( (lv_constraints_17_0= ruleConstraints ) )*
                            {
                            // InternalRequirementDSL.g:1336:6: ( ( (lv_predicate_15_0= rulePredicate ) ) | ( (lv_object_16_0= rulePredicateObject ) ) )
                            int alt31=2;
                            int LA31_0 = input.LA(1);

                            if ( (LA31_0==RULE_STRING||LA31_0==RULE_ID) ) {
                                alt31=1;
                            }
                            else if ( ((LA31_0>=60 && LA31_0<=66)||(LA31_0>=68 && LA31_0<=73)||(LA31_0>=79 && LA31_0<=88)) ) {
                                alt31=2;
                            }
                            else {
                                NoViableAltException nvae =
                                    new NoViableAltException("", 31, 0, input);

                                throw nvae;
                            }
                            switch (alt31) {
                                case 1 :
                                    // InternalRequirementDSL.g:1337:7: ( (lv_predicate_15_0= rulePredicate ) )
                                    {
                                    // InternalRequirementDSL.g:1337:7: ( (lv_predicate_15_0= rulePredicate ) )
                                    // InternalRequirementDSL.g:1338:8: (lv_predicate_15_0= rulePredicate )
                                    {
                                    // InternalRequirementDSL.g:1338:8: (lv_predicate_15_0= rulePredicate )
                                    // InternalRequirementDSL.g:1339:9: lv_predicate_15_0= rulePredicate
                                    {

                                    									newCompositeNode(grammarAccess.getPropertySentenceAccess().getPredicatePredicateParserRuleCall_2_3_0_0_0_0());
                                    								
                                    pushFollow(FOLLOW_15);
                                    lv_predicate_15_0=rulePredicate();

                                    state._fsp--;


                                    									if (current==null) {
                                    										current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                                    									}
                                    									set(
                                    										current,
                                    										"predicate",
                                    										lv_predicate_15_0,
                                    										"de.fraunhofer.isst.stars.RequirementDSL.Predicate");
                                    									afterParserOrEnumRuleCall();
                                    								

                                    }


                                    }


                                    }
                                    break;
                                case 2 :
                                    // InternalRequirementDSL.g:1357:7: ( (lv_object_16_0= rulePredicateObject ) )
                                    {
                                    // InternalRequirementDSL.g:1357:7: ( (lv_object_16_0= rulePredicateObject ) )
                                    // InternalRequirementDSL.g:1358:8: (lv_object_16_0= rulePredicateObject )
                                    {
                                    // InternalRequirementDSL.g:1358:8: (lv_object_16_0= rulePredicateObject )
                                    // InternalRequirementDSL.g:1359:9: lv_object_16_0= rulePredicateObject
                                    {

                                    									newCompositeNode(grammarAccess.getPropertySentenceAccess().getObjectPredicateObjectParserRuleCall_2_3_0_0_1_0());
                                    								
                                    pushFollow(FOLLOW_15);
                                    lv_object_16_0=rulePredicateObject();

                                    state._fsp--;


                                    									if (current==null) {
                                    										current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                                    									}
                                    									set(
                                    										current,
                                    										"object",
                                    										lv_object_16_0,
                                    										"de.fraunhofer.isst.stars.RequirementDSL.PredicateObject");
                                    									afterParserOrEnumRuleCall();
                                    								

                                    }


                                    }


                                    }
                                    break;

                            }

                            // InternalRequirementDSL.g:1377:6: ( (lv_constraints_17_0= ruleConstraints ) )*
                            loop32:
                            do {
                                int alt32=2;
                                int LA32_0 = input.LA(1);

                                if ( (LA32_0==36||(LA32_0>=38 && LA32_0<=59)||LA32_0==89) ) {
                                    alt32=1;
                                }


                                switch (alt32) {
                            	case 1 :
                            	    // InternalRequirementDSL.g:1378:7: (lv_constraints_17_0= ruleConstraints )
                            	    {
                            	    // InternalRequirementDSL.g:1378:7: (lv_constraints_17_0= ruleConstraints )
                            	    // InternalRequirementDSL.g:1379:8: lv_constraints_17_0= ruleConstraints
                            	    {

                            	    								newCompositeNode(grammarAccess.getPropertySentenceAccess().getConstraintsConstraintsParserRuleCall_2_3_0_1_0());
                            	    							
                            	    pushFollow(FOLLOW_15);
                            	    lv_constraints_17_0=ruleConstraints();

                            	    state._fsp--;


                            	    								if (current==null) {
                            	    									current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                            	    								}
                            	    								add(
                            	    									current,
                            	    									"constraints",
                            	    									lv_constraints_17_0,
                            	    									"de.fraunhofer.isst.stars.RequirementDSL.Constraints");
                            	    								afterParserOrEnumRuleCall();
                            	    							

                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop32;
                                }
                            } while (true);


                            }


                            }
                            break;
                        case 2 :
                            // InternalRequirementDSL.g:1398:5: ( (lv_constraints_18_0= ruleConstraints ) )+
                            {
                            // InternalRequirementDSL.g:1398:5: ( (lv_constraints_18_0= ruleConstraints ) )+
                            int cnt33=0;
                            loop33:
                            do {
                                int alt33=2;
                                int LA33_0 = input.LA(1);

                                if ( (LA33_0==36||(LA33_0>=38 && LA33_0<=59)||LA33_0==89) ) {
                                    alt33=1;
                                }


                                switch (alt33) {
                            	case 1 :
                            	    // InternalRequirementDSL.g:1399:6: (lv_constraints_18_0= ruleConstraints )
                            	    {
                            	    // InternalRequirementDSL.g:1399:6: (lv_constraints_18_0= ruleConstraints )
                            	    // InternalRequirementDSL.g:1400:7: lv_constraints_18_0= ruleConstraints
                            	    {

                            	    							newCompositeNode(grammarAccess.getPropertySentenceAccess().getConstraintsConstraintsParserRuleCall_2_3_1_0());
                            	    						
                            	    pushFollow(FOLLOW_15);
                            	    lv_constraints_18_0=ruleConstraints();

                            	    state._fsp--;


                            	    							if (current==null) {
                            	    								current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                            	    							}
                            	    							add(
                            	    								current,
                            	    								"constraints",
                            	    								lv_constraints_18_0,
                            	    								"de.fraunhofer.isst.stars.RequirementDSL.Constraints");
                            	    							afterParserOrEnumRuleCall();
                            	    						

                            	    }


                            	    }
                            	    break;

                            	default :
                            	    if ( cnt33 >= 1 ) break loop33;
                                        EarlyExitException eee =
                                            new EarlyExitException(33, input);
                                        throw eee;
                                }
                                cnt33++;
                            } while (true);


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:1420:3: ( ( (lv_property_19_0= ruleProperty ) ) ( ( (lv_predicateWord_20_0= ruleWORD ) ) | ( (lv_predicateWord_21_0= RULE_STRING ) ) ) ( (lv_object_22_0= rulePredicateObject ) )? ( (lv_constraints_23_0= ruleConstraints ) )* )
                    {
                    // InternalRequirementDSL.g:1420:3: ( ( (lv_property_19_0= ruleProperty ) ) ( ( (lv_predicateWord_20_0= ruleWORD ) ) | ( (lv_predicateWord_21_0= RULE_STRING ) ) ) ( (lv_object_22_0= rulePredicateObject ) )? ( (lv_constraints_23_0= ruleConstraints ) )* )
                    // InternalRequirementDSL.g:1421:4: ( (lv_property_19_0= ruleProperty ) ) ( ( (lv_predicateWord_20_0= ruleWORD ) ) | ( (lv_predicateWord_21_0= RULE_STRING ) ) ) ( (lv_object_22_0= rulePredicateObject ) )? ( (lv_constraints_23_0= ruleConstraints ) )*
                    {
                    // InternalRequirementDSL.g:1421:4: ( (lv_property_19_0= ruleProperty ) )
                    // InternalRequirementDSL.g:1422:5: (lv_property_19_0= ruleProperty )
                    {
                    // InternalRequirementDSL.g:1422:5: (lv_property_19_0= ruleProperty )
                    // InternalRequirementDSL.g:1423:6: lv_property_19_0= ruleProperty
                    {

                    						newCompositeNode(grammarAccess.getPropertySentenceAccess().getPropertyPropertyParserRuleCall_3_0_0());
                    					
                    pushFollow(FOLLOW_24);
                    lv_property_19_0=ruleProperty();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                    						}
                    						set(
                    							current,
                    							"property",
                    							lv_property_19_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Property");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRequirementDSL.g:1440:4: ( ( (lv_predicateWord_20_0= ruleWORD ) ) | ( (lv_predicateWord_21_0= RULE_STRING ) ) )
                    int alt35=2;
                    int LA35_0 = input.LA(1);

                    if ( (LA35_0==RULE_ID) ) {
                        alt35=1;
                    }
                    else if ( (LA35_0==RULE_STRING) ) {
                        alt35=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 35, 0, input);

                        throw nvae;
                    }
                    switch (alt35) {
                        case 1 :
                            // InternalRequirementDSL.g:1441:5: ( (lv_predicateWord_20_0= ruleWORD ) )
                            {
                            // InternalRequirementDSL.g:1441:5: ( (lv_predicateWord_20_0= ruleWORD ) )
                            // InternalRequirementDSL.g:1442:6: (lv_predicateWord_20_0= ruleWORD )
                            {
                            // InternalRequirementDSL.g:1442:6: (lv_predicateWord_20_0= ruleWORD )
                            // InternalRequirementDSL.g:1443:7: lv_predicateWord_20_0= ruleWORD
                            {

                            							newCompositeNode(grammarAccess.getPropertySentenceAccess().getPredicateWordWORDParserRuleCall_3_1_0_0());
                            						
                            pushFollow(FOLLOW_18);
                            lv_predicateWord_20_0=ruleWORD();

                            state._fsp--;


                            							if (current==null) {
                            								current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                            							}
                            							set(
                            								current,
                            								"predicateWord",
                            								lv_predicateWord_20_0,
                            								"de.fraunhofer.isst.stars.RequirementDSL.WORD");
                            							afterParserOrEnumRuleCall();
                            						

                            }


                            }


                            }
                            break;
                        case 2 :
                            // InternalRequirementDSL.g:1461:5: ( (lv_predicateWord_21_0= RULE_STRING ) )
                            {
                            // InternalRequirementDSL.g:1461:5: ( (lv_predicateWord_21_0= RULE_STRING ) )
                            // InternalRequirementDSL.g:1462:6: (lv_predicateWord_21_0= RULE_STRING )
                            {
                            // InternalRequirementDSL.g:1462:6: (lv_predicateWord_21_0= RULE_STRING )
                            // InternalRequirementDSL.g:1463:7: lv_predicateWord_21_0= RULE_STRING
                            {
                            lv_predicateWord_21_0=(Token)match(input,RULE_STRING,FOLLOW_18); 

                            							newLeafNode(lv_predicateWord_21_0, grammarAccess.getPropertySentenceAccess().getPredicateWordSTRINGTerminalRuleCall_3_1_1_0());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getPropertySentenceRule());
                            							}
                            							setWithLastConsumed(
                            								current,
                            								"predicateWord",
                            								lv_predicateWord_21_0,
                            								"org.eclipse.xtext.common.Terminals.STRING");
                            						

                            }


                            }


                            }
                            break;

                    }

                    // InternalRequirementDSL.g:1480:4: ( (lv_object_22_0= rulePredicateObject ) )?
                    int alt36=2;
                    int LA36_0 = input.LA(1);

                    if ( ((LA36_0>=60 && LA36_0<=66)||(LA36_0>=68 && LA36_0<=73)||(LA36_0>=79 && LA36_0<=88)) ) {
                        alt36=1;
                    }
                    switch (alt36) {
                        case 1 :
                            // InternalRequirementDSL.g:1481:5: (lv_object_22_0= rulePredicateObject )
                            {
                            // InternalRequirementDSL.g:1481:5: (lv_object_22_0= rulePredicateObject )
                            // InternalRequirementDSL.g:1482:6: lv_object_22_0= rulePredicateObject
                            {

                            						newCompositeNode(grammarAccess.getPropertySentenceAccess().getObjectPredicateObjectParserRuleCall_3_2_0());
                            					
                            pushFollow(FOLLOW_15);
                            lv_object_22_0=rulePredicateObject();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                            						}
                            						set(
                            							current,
                            							"object",
                            							lv_object_22_0,
                            							"de.fraunhofer.isst.stars.RequirementDSL.PredicateObject");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }

                    // InternalRequirementDSL.g:1499:4: ( (lv_constraints_23_0= ruleConstraints ) )*
                    loop37:
                    do {
                        int alt37=2;
                        int LA37_0 = input.LA(1);

                        if ( (LA37_0==36||(LA37_0>=38 && LA37_0<=59)||LA37_0==89) ) {
                            alt37=1;
                        }


                        switch (alt37) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:1500:5: (lv_constraints_23_0= ruleConstraints )
                    	    {
                    	    // InternalRequirementDSL.g:1500:5: (lv_constraints_23_0= ruleConstraints )
                    	    // InternalRequirementDSL.g:1501:6: lv_constraints_23_0= ruleConstraints
                    	    {

                    	    						newCompositeNode(grammarAccess.getPropertySentenceAccess().getConstraintsConstraintsParserRuleCall_3_3_0());
                    	    					
                    	    pushFollow(FOLLOW_15);
                    	    lv_constraints_23_0=ruleConstraints();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getPropertySentenceRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"constraints",
                    	    							lv_constraints_23_0,
                    	    							"de.fraunhofer.isst.stars.RequirementDSL.Constraints");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop37;
                        }
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
    // $ANTLR end "rulePropertySentence"


    // $ANTLR start "entryRuleProperty"
    // InternalRequirementDSL.g:1523:1: entryRuleProperty returns [EObject current=null] : iv_ruleProperty= ruleProperty EOF ;
    public final EObject entryRuleProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProperty = null;


        try {
            // InternalRequirementDSL.g:1523:49: (iv_ruleProperty= ruleProperty EOF )
            // InternalRequirementDSL.g:1524:2: iv_ruleProperty= ruleProperty EOF
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
    // InternalRequirementDSL.g:1530:1: ruleProperty returns [EObject current=null] : ( ( ( (lv_quantifier_0_0= ruleQuantification ) ) | ( (lv_article_1_0= ruleArticles ) ) | ( (lv_article_2_0= ruleRefArticles ) ) )? ( ( (lv_object_3_0= ruleWORD ) )+ | ( (lv_object_4_0= RULE_STRING ) ) ) this_PROPERTY_TERM_5= RULE_PROPERTY_TERM ( ( (lv_property_6_0= ruleWORD ) )+ | ( (lv_property_7_0= RULE_STRING ) ) ) ) ;
    public final EObject ruleProperty() throws RecognitionException {
        EObject current = null;

        Token lv_object_4_0=null;
        Token this_PROPERTY_TERM_5=null;
        Token lv_property_7_0=null;
        AntlrDatatypeRuleToken lv_quantifier_0_0 = null;

        AntlrDatatypeRuleToken lv_article_1_0 = null;

        AntlrDatatypeRuleToken lv_article_2_0 = null;

        AntlrDatatypeRuleToken lv_object_3_0 = null;

        AntlrDatatypeRuleToken lv_property_6_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:1536:2: ( ( ( ( (lv_quantifier_0_0= ruleQuantification ) ) | ( (lv_article_1_0= ruleArticles ) ) | ( (lv_article_2_0= ruleRefArticles ) ) )? ( ( (lv_object_3_0= ruleWORD ) )+ | ( (lv_object_4_0= RULE_STRING ) ) ) this_PROPERTY_TERM_5= RULE_PROPERTY_TERM ( ( (lv_property_6_0= ruleWORD ) )+ | ( (lv_property_7_0= RULE_STRING ) ) ) ) )
            // InternalRequirementDSL.g:1537:2: ( ( ( (lv_quantifier_0_0= ruleQuantification ) ) | ( (lv_article_1_0= ruleArticles ) ) | ( (lv_article_2_0= ruleRefArticles ) ) )? ( ( (lv_object_3_0= ruleWORD ) )+ | ( (lv_object_4_0= RULE_STRING ) ) ) this_PROPERTY_TERM_5= RULE_PROPERTY_TERM ( ( (lv_property_6_0= ruleWORD ) )+ | ( (lv_property_7_0= RULE_STRING ) ) ) )
            {
            // InternalRequirementDSL.g:1537:2: ( ( ( (lv_quantifier_0_0= ruleQuantification ) ) | ( (lv_article_1_0= ruleArticles ) ) | ( (lv_article_2_0= ruleRefArticles ) ) )? ( ( (lv_object_3_0= ruleWORD ) )+ | ( (lv_object_4_0= RULE_STRING ) ) ) this_PROPERTY_TERM_5= RULE_PROPERTY_TERM ( ( (lv_property_6_0= ruleWORD ) )+ | ( (lv_property_7_0= RULE_STRING ) ) ) )
            // InternalRequirementDSL.g:1538:3: ( ( (lv_quantifier_0_0= ruleQuantification ) ) | ( (lv_article_1_0= ruleArticles ) ) | ( (lv_article_2_0= ruleRefArticles ) ) )? ( ( (lv_object_3_0= ruleWORD ) )+ | ( (lv_object_4_0= RULE_STRING ) ) ) this_PROPERTY_TERM_5= RULE_PROPERTY_TERM ( ( (lv_property_6_0= ruleWORD ) )+ | ( (lv_property_7_0= RULE_STRING ) ) )
            {
            // InternalRequirementDSL.g:1538:3: ( ( (lv_quantifier_0_0= ruleQuantification ) ) | ( (lv_article_1_0= ruleArticles ) ) | ( (lv_article_2_0= ruleRefArticles ) ) )?
            int alt39=4;
            switch ( input.LA(1) ) {
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 68:
                case 69:
                case 70:
                case 71:
                case 72:
                case 73:
                    {
                    alt39=1;
                    }
                    break;
                case 79:
                case 80:
                case 81:
                case 82:
                case 83:
                case 84:
                    {
                    alt39=2;
                    }
                    break;
                case 85:
                case 86:
                case 87:
                case 88:
                    {
                    alt39=3;
                    }
                    break;
            }

            switch (alt39) {
                case 1 :
                    // InternalRequirementDSL.g:1539:4: ( (lv_quantifier_0_0= ruleQuantification ) )
                    {
                    // InternalRequirementDSL.g:1539:4: ( (lv_quantifier_0_0= ruleQuantification ) )
                    // InternalRequirementDSL.g:1540:5: (lv_quantifier_0_0= ruleQuantification )
                    {
                    // InternalRequirementDSL.g:1540:5: (lv_quantifier_0_0= ruleQuantification )
                    // InternalRequirementDSL.g:1541:6: lv_quantifier_0_0= ruleQuantification
                    {

                    						newCompositeNode(grammarAccess.getPropertyAccess().getQuantifierQuantificationParserRuleCall_0_0_0());
                    					
                    pushFollow(FOLLOW_24);
                    lv_quantifier_0_0=ruleQuantification();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertyRule());
                    						}
                    						set(
                    							current,
                    							"quantifier",
                    							lv_quantifier_0_0,
                    							"de.fraunhofer.isst.stars.RequirementDSL.Quantification");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:1559:4: ( (lv_article_1_0= ruleArticles ) )
                    {
                    // InternalRequirementDSL.g:1559:4: ( (lv_article_1_0= ruleArticles ) )
                    // InternalRequirementDSL.g:1560:5: (lv_article_1_0= ruleArticles )
                    {
                    // InternalRequirementDSL.g:1560:5: (lv_article_1_0= ruleArticles )
                    // InternalRequirementDSL.g:1561:6: lv_article_1_0= ruleArticles
                    {

                    						newCompositeNode(grammarAccess.getPropertyAccess().getArticleArticlesParserRuleCall_0_1_0());
                    					
                    pushFollow(FOLLOW_24);
                    lv_article_1_0=ruleArticles();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertyRule());
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
                    // InternalRequirementDSL.g:1579:4: ( (lv_article_2_0= ruleRefArticles ) )
                    {
                    // InternalRequirementDSL.g:1579:4: ( (lv_article_2_0= ruleRefArticles ) )
                    // InternalRequirementDSL.g:1580:5: (lv_article_2_0= ruleRefArticles )
                    {
                    // InternalRequirementDSL.g:1580:5: (lv_article_2_0= ruleRefArticles )
                    // InternalRequirementDSL.g:1581:6: lv_article_2_0= ruleRefArticles
                    {

                    						newCompositeNode(grammarAccess.getPropertyAccess().getArticleRefArticlesParserRuleCall_0_2_0());
                    					
                    pushFollow(FOLLOW_24);
                    lv_article_2_0=ruleRefArticles();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPropertyRule());
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

            // InternalRequirementDSL.g:1599:3: ( ( (lv_object_3_0= ruleWORD ) )+ | ( (lv_object_4_0= RULE_STRING ) ) )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==RULE_ID) ) {
                alt41=1;
            }
            else if ( (LA41_0==RULE_STRING) ) {
                alt41=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }
            switch (alt41) {
                case 1 :
                    // InternalRequirementDSL.g:1600:4: ( (lv_object_3_0= ruleWORD ) )+
                    {
                    // InternalRequirementDSL.g:1600:4: ( (lv_object_3_0= ruleWORD ) )+
                    int cnt40=0;
                    loop40:
                    do {
                        int alt40=2;
                        int LA40_0 = input.LA(1);

                        if ( (LA40_0==RULE_ID) ) {
                            alt40=1;
                        }


                        switch (alt40) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:1601:5: (lv_object_3_0= ruleWORD )
                    	    {
                    	    // InternalRequirementDSL.g:1601:5: (lv_object_3_0= ruleWORD )
                    	    // InternalRequirementDSL.g:1602:6: lv_object_3_0= ruleWORD
                    	    {

                    	    						newCompositeNode(grammarAccess.getPropertyAccess().getObjectWORDParserRuleCall_1_0_0());
                    	    					
                    	    pushFollow(FOLLOW_25);
                    	    lv_object_3_0=ruleWORD();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getPropertyRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"object",
                    	    							lv_object_3_0,
                    	    							"de.fraunhofer.isst.stars.RequirementDSL.WORD");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt40 >= 1 ) break loop40;
                                EarlyExitException eee =
                                    new EarlyExitException(40, input);
                                throw eee;
                        }
                        cnt40++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:1620:4: ( (lv_object_4_0= RULE_STRING ) )
                    {
                    // InternalRequirementDSL.g:1620:4: ( (lv_object_4_0= RULE_STRING ) )
                    // InternalRequirementDSL.g:1621:5: (lv_object_4_0= RULE_STRING )
                    {
                    // InternalRequirementDSL.g:1621:5: (lv_object_4_0= RULE_STRING )
                    // InternalRequirementDSL.g:1622:6: lv_object_4_0= RULE_STRING
                    {
                    lv_object_4_0=(Token)match(input,RULE_STRING,FOLLOW_26); 

                    						newLeafNode(lv_object_4_0, grammarAccess.getPropertyAccess().getObjectSTRINGTerminalRuleCall_1_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getPropertyRule());
                    						}
                    						addWithLastConsumed(
                    							current,
                    							"object",
                    							lv_object_4_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }
                    break;

            }

            this_PROPERTY_TERM_5=(Token)match(input,RULE_PROPERTY_TERM,FOLLOW_24); 

            			newLeafNode(this_PROPERTY_TERM_5, grammarAccess.getPropertyAccess().getPROPERTY_TERMTerminalRuleCall_2());
            		
            // InternalRequirementDSL.g:1643:3: ( ( (lv_property_6_0= ruleWORD ) )+ | ( (lv_property_7_0= RULE_STRING ) ) )
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==RULE_ID) ) {
                alt43=1;
            }
            else if ( (LA43_0==RULE_STRING) ) {
                alt43=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                throw nvae;
            }
            switch (alt43) {
                case 1 :
                    // InternalRequirementDSL.g:1644:4: ( (lv_property_6_0= ruleWORD ) )+
                    {
                    // InternalRequirementDSL.g:1644:4: ( (lv_property_6_0= ruleWORD ) )+
                    int cnt42=0;
                    loop42:
                    do {
                        int alt42=2;
                        alt42 = dfa42.predict(input);
                        switch (alt42) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:1645:5: (lv_property_6_0= ruleWORD )
                    	    {
                    	    // InternalRequirementDSL.g:1645:5: (lv_property_6_0= ruleWORD )
                    	    // InternalRequirementDSL.g:1646:6: lv_property_6_0= ruleWORD
                    	    {

                    	    						newCompositeNode(grammarAccess.getPropertyAccess().getPropertyWORDParserRuleCall_3_0_0());
                    	    					
                    	    pushFollow(FOLLOW_27);
                    	    lv_property_6_0=ruleWORD();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getPropertyRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"property",
                    	    							lv_property_6_0,
                    	    							"de.fraunhofer.isst.stars.RequirementDSL.WORD");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt42 >= 1 ) break loop42;
                                EarlyExitException eee =
                                    new EarlyExitException(42, input);
                                throw eee;
                        }
                        cnt42++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:1664:4: ( (lv_property_7_0= RULE_STRING ) )
                    {
                    // InternalRequirementDSL.g:1664:4: ( (lv_property_7_0= RULE_STRING ) )
                    // InternalRequirementDSL.g:1665:5: (lv_property_7_0= RULE_STRING )
                    {
                    // InternalRequirementDSL.g:1665:5: (lv_property_7_0= RULE_STRING )
                    // InternalRequirementDSL.g:1666:6: lv_property_7_0= RULE_STRING
                    {
                    lv_property_7_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						newLeafNode(lv_property_7_0, grammarAccess.getPropertyAccess().getPropertySTRINGTerminalRuleCall_3_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getPropertyRule());
                    						}
                    						addWithLastConsumed(
                    							current,
                    							"property",
                    							lv_property_7_0,
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


    // $ANTLR start "entryRulerelativeClause"
    // InternalRequirementDSL.g:1687:1: entryRulerelativeClause returns [EObject current=null] : iv_rulerelativeClause= rulerelativeClause EOF ;
    public final EObject entryRulerelativeClause() throws RecognitionException {
        EObject current = null;

        EObject iv_rulerelativeClause = null;


        try {
            // InternalRequirementDSL.g:1687:55: (iv_rulerelativeClause= rulerelativeClause EOF )
            // InternalRequirementDSL.g:1688:2: iv_rulerelativeClause= rulerelativeClause EOF
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
    // InternalRequirementDSL.g:1694:1: rulerelativeClause returns [EObject current=null] : ( ( (lv_sentence_0_0= rulerelativeSentence ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_condClauses_2_0= ruleConditionalClause ) ) )* ) ;
    public final EObject rulerelativeClause() throws RecognitionException {
        EObject current = null;

        EObject lv_sentence_0_0 = null;

        AntlrDatatypeRuleToken lv_conjunction_1_0 = null;

        EObject lv_condClauses_2_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:1700:2: ( ( ( (lv_sentence_0_0= rulerelativeSentence ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_condClauses_2_0= ruleConditionalClause ) ) )* ) )
            // InternalRequirementDSL.g:1701:2: ( ( (lv_sentence_0_0= rulerelativeSentence ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_condClauses_2_0= ruleConditionalClause ) ) )* )
            {
            // InternalRequirementDSL.g:1701:2: ( ( (lv_sentence_0_0= rulerelativeSentence ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_condClauses_2_0= ruleConditionalClause ) ) )* )
            // InternalRequirementDSL.g:1702:3: ( (lv_sentence_0_0= rulerelativeSentence ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_condClauses_2_0= ruleConditionalClause ) ) )*
            {
            // InternalRequirementDSL.g:1702:3: ( (lv_sentence_0_0= rulerelativeSentence ) )
            // InternalRequirementDSL.g:1703:4: (lv_sentence_0_0= rulerelativeSentence )
            {
            // InternalRequirementDSL.g:1703:4: (lv_sentence_0_0= rulerelativeSentence )
            // InternalRequirementDSL.g:1704:5: lv_sentence_0_0= rulerelativeSentence
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

            // InternalRequirementDSL.g:1721:3: ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_condClauses_2_0= ruleConditionalClause ) ) )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( ((LA44_0>=32 && LA44_0<=33)) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // InternalRequirementDSL.g:1722:4: ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_condClauses_2_0= ruleConditionalClause ) )
            	    {
            	    // InternalRequirementDSL.g:1722:4: ( (lv_conjunction_1_0= ruleConjunction ) )
            	    // InternalRequirementDSL.g:1723:5: (lv_conjunction_1_0= ruleConjunction )
            	    {
            	    // InternalRequirementDSL.g:1723:5: (lv_conjunction_1_0= ruleConjunction )
            	    // InternalRequirementDSL.g:1724:6: lv_conjunction_1_0= ruleConjunction
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

            	    // InternalRequirementDSL.g:1741:4: ( (lv_condClauses_2_0= ruleConditionalClause ) )
            	    // InternalRequirementDSL.g:1742:5: (lv_condClauses_2_0= ruleConditionalClause )
            	    {
            	    // InternalRequirementDSL.g:1742:5: (lv_condClauses_2_0= ruleConditionalClause )
            	    // InternalRequirementDSL.g:1743:6: lv_condClauses_2_0= ruleConditionalClause
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
            	    break loop44;
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
    // InternalRequirementDSL.g:1765:1: entryRulerelativeSentence returns [EObject current=null] : iv_rulerelativeSentence= rulerelativeSentence EOF ;
    public final EObject entryRulerelativeSentence() throws RecognitionException {
        EObject current = null;

        EObject iv_rulerelativeSentence = null;


        try {
            // InternalRequirementDSL.g:1765:57: (iv_rulerelativeSentence= rulerelativeSentence EOF )
            // InternalRequirementDSL.g:1766:2: iv_rulerelativeSentence= rulerelativeSentence EOF
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
    // InternalRequirementDSL.g:1772:1: rulerelativeSentence returns [EObject current=null] : ( ( ( (lv_pronoun_0_0= ruleRelativePronounsSubject ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_predicate_3_0= rulePredicate ) ) ( (lv_constraints_4_0= ruleConstraints ) )* ) | ( ( (lv_pronoun_5_0= ruleRelativePronounsSubject ) ) ( ( (lv_auxiliar_6_0= ruleWORD ) ) ( (lv_negation_7_0= ruleNegation ) ) )? ( (lv_predicate_8_0= rulePredicate ) ) ( (lv_constraints_9_0= ruleConstraints ) )* ) | ( ( (lv_pronoun_10_0= ruleRelativePronounsObject ) ) ( ( (lv_clause_11_0= ruleModalitySentence ) ) | ( (lv_clause_12_0= rulePredicateSentence ) ) ) ) ) ;
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
            // InternalRequirementDSL.g:1778:2: ( ( ( ( (lv_pronoun_0_0= ruleRelativePronounsSubject ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_predicate_3_0= rulePredicate ) ) ( (lv_constraints_4_0= ruleConstraints ) )* ) | ( ( (lv_pronoun_5_0= ruleRelativePronounsSubject ) ) ( ( (lv_auxiliar_6_0= ruleWORD ) ) ( (lv_negation_7_0= ruleNegation ) ) )? ( (lv_predicate_8_0= rulePredicate ) ) ( (lv_constraints_9_0= ruleConstraints ) )* ) | ( ( (lv_pronoun_10_0= ruleRelativePronounsObject ) ) ( ( (lv_clause_11_0= ruleModalitySentence ) ) | ( (lv_clause_12_0= rulePredicateSentence ) ) ) ) ) )
            // InternalRequirementDSL.g:1779:2: ( ( ( (lv_pronoun_0_0= ruleRelativePronounsSubject ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_predicate_3_0= rulePredicate ) ) ( (lv_constraints_4_0= ruleConstraints ) )* ) | ( ( (lv_pronoun_5_0= ruleRelativePronounsSubject ) ) ( ( (lv_auxiliar_6_0= ruleWORD ) ) ( (lv_negation_7_0= ruleNegation ) ) )? ( (lv_predicate_8_0= rulePredicate ) ) ( (lv_constraints_9_0= ruleConstraints ) )* ) | ( ( (lv_pronoun_10_0= ruleRelativePronounsObject ) ) ( ( (lv_clause_11_0= ruleModalitySentence ) ) | ( (lv_clause_12_0= rulePredicateSentence ) ) ) ) )
            {
            // InternalRequirementDSL.g:1779:2: ( ( ( (lv_pronoun_0_0= ruleRelativePronounsSubject ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_predicate_3_0= rulePredicate ) ) ( (lv_constraints_4_0= ruleConstraints ) )* ) | ( ( (lv_pronoun_5_0= ruleRelativePronounsSubject ) ) ( ( (lv_auxiliar_6_0= ruleWORD ) ) ( (lv_negation_7_0= ruleNegation ) ) )? ( (lv_predicate_8_0= rulePredicate ) ) ( (lv_constraints_9_0= ruleConstraints ) )* ) | ( ( (lv_pronoun_10_0= ruleRelativePronounsObject ) ) ( ( (lv_clause_11_0= ruleModalitySentence ) ) | ( (lv_clause_12_0= rulePredicateSentence ) ) ) ) )
            int alt50=3;
            switch ( input.LA(1) ) {
            case 90:
                {
                int LA50_1 = input.LA(2);

                if ( ((LA50_1>=138 && LA50_1<=144)) ) {
                    alt50=1;
                }
                else if ( (LA50_1==RULE_STRING||LA50_1==RULE_ID) ) {
                    alt50=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 50, 1, input);

                    throw nvae;
                }
                }
                break;
            case 91:
                {
                int LA50_2 = input.LA(2);

                if ( ((LA50_2>=138 && LA50_2<=144)) ) {
                    alt50=1;
                }
                else if ( (LA50_2==RULE_STRING||LA50_2==RULE_ID) ) {
                    alt50=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 50, 2, input);

                    throw nvae;
                }
                }
                break;
            case 85:
                {
                int LA50_3 = input.LA(2);

                if ( (LA50_3==RULE_STRING||LA50_3==RULE_ID) ) {
                    alt50=2;
                }
                else if ( ((LA50_3>=138 && LA50_3<=144)) ) {
                    alt50=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 50, 3, input);

                    throw nvae;
                }
                }
                break;
            case 92:
            case 93:
                {
                alt50=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                throw nvae;
            }

            switch (alt50) {
                case 1 :
                    // InternalRequirementDSL.g:1780:3: ( ( (lv_pronoun_0_0= ruleRelativePronounsSubject ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_predicate_3_0= rulePredicate ) ) ( (lv_constraints_4_0= ruleConstraints ) )* )
                    {
                    // InternalRequirementDSL.g:1780:3: ( ( (lv_pronoun_0_0= ruleRelativePronounsSubject ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_predicate_3_0= rulePredicate ) ) ( (lv_constraints_4_0= ruleConstraints ) )* )
                    // InternalRequirementDSL.g:1781:4: ( (lv_pronoun_0_0= ruleRelativePronounsSubject ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_predicate_3_0= rulePredicate ) ) ( (lv_constraints_4_0= ruleConstraints ) )*
                    {
                    // InternalRequirementDSL.g:1781:4: ( (lv_pronoun_0_0= ruleRelativePronounsSubject ) )
                    // InternalRequirementDSL.g:1782:5: (lv_pronoun_0_0= ruleRelativePronounsSubject )
                    {
                    // InternalRequirementDSL.g:1782:5: (lv_pronoun_0_0= ruleRelativePronounsSubject )
                    // InternalRequirementDSL.g:1783:6: lv_pronoun_0_0= ruleRelativePronounsSubject
                    {

                    						newCompositeNode(grammarAccess.getRelativeSentenceAccess().getPronounRelativePronounsSubjectParserRuleCall_0_0_0());
                    					
                    pushFollow(FOLLOW_13);
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

                    // InternalRequirementDSL.g:1800:4: ( (lv_modelity_1_0= ruleModality ) )
                    // InternalRequirementDSL.g:1801:5: (lv_modelity_1_0= ruleModality )
                    {
                    // InternalRequirementDSL.g:1801:5: (lv_modelity_1_0= ruleModality )
                    // InternalRequirementDSL.g:1802:6: lv_modelity_1_0= ruleModality
                    {

                    						newCompositeNode(grammarAccess.getRelativeSentenceAccess().getModelityModalityEnumRuleCall_0_1_0());
                    					
                    pushFollow(FOLLOW_14);
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

                    // InternalRequirementDSL.g:1819:4: ( (lv_negation_2_0= ruleNegation ) )?
                    int alt45=2;
                    int LA45_0 = input.LA(1);

                    if ( ((LA45_0>=74 && LA45_0<=78)) ) {
                        alt45=1;
                    }
                    switch (alt45) {
                        case 1 :
                            // InternalRequirementDSL.g:1820:5: (lv_negation_2_0= ruleNegation )
                            {
                            // InternalRequirementDSL.g:1820:5: (lv_negation_2_0= ruleNegation )
                            // InternalRequirementDSL.g:1821:6: lv_negation_2_0= ruleNegation
                            {

                            						newCompositeNode(grammarAccess.getRelativeSentenceAccess().getNegationNegationParserRuleCall_0_2_0());
                            					
                            pushFollow(FOLLOW_14);
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

                    // InternalRequirementDSL.g:1838:4: ( (lv_predicate_3_0= rulePredicate ) )
                    // InternalRequirementDSL.g:1839:5: (lv_predicate_3_0= rulePredicate )
                    {
                    // InternalRequirementDSL.g:1839:5: (lv_predicate_3_0= rulePredicate )
                    // InternalRequirementDSL.g:1840:6: lv_predicate_3_0= rulePredicate
                    {

                    						newCompositeNode(grammarAccess.getRelativeSentenceAccess().getPredicatePredicateParserRuleCall_0_3_0());
                    					
                    pushFollow(FOLLOW_15);
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

                    // InternalRequirementDSL.g:1857:4: ( (lv_constraints_4_0= ruleConstraints ) )*
                    loop46:
                    do {
                        int alt46=2;
                        int LA46_0 = input.LA(1);

                        if ( (LA46_0==36||(LA46_0>=38 && LA46_0<=59)||LA46_0==89) ) {
                            alt46=1;
                        }


                        switch (alt46) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:1858:5: (lv_constraints_4_0= ruleConstraints )
                    	    {
                    	    // InternalRequirementDSL.g:1858:5: (lv_constraints_4_0= ruleConstraints )
                    	    // InternalRequirementDSL.g:1859:6: lv_constraints_4_0= ruleConstraints
                    	    {

                    	    						newCompositeNode(grammarAccess.getRelativeSentenceAccess().getConstraintsConstraintsParserRuleCall_0_4_0());
                    	    					
                    	    pushFollow(FOLLOW_15);
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
                    	    break loop46;
                        }
                    } while (true);


                    }


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:1878:3: ( ( (lv_pronoun_5_0= ruleRelativePronounsSubject ) ) ( ( (lv_auxiliar_6_0= ruleWORD ) ) ( (lv_negation_7_0= ruleNegation ) ) )? ( (lv_predicate_8_0= rulePredicate ) ) ( (lv_constraints_9_0= ruleConstraints ) )* )
                    {
                    // InternalRequirementDSL.g:1878:3: ( ( (lv_pronoun_5_0= ruleRelativePronounsSubject ) ) ( ( (lv_auxiliar_6_0= ruleWORD ) ) ( (lv_negation_7_0= ruleNegation ) ) )? ( (lv_predicate_8_0= rulePredicate ) ) ( (lv_constraints_9_0= ruleConstraints ) )* )
                    // InternalRequirementDSL.g:1879:4: ( (lv_pronoun_5_0= ruleRelativePronounsSubject ) ) ( ( (lv_auxiliar_6_0= ruleWORD ) ) ( (lv_negation_7_0= ruleNegation ) ) )? ( (lv_predicate_8_0= rulePredicate ) ) ( (lv_constraints_9_0= ruleConstraints ) )*
                    {
                    // InternalRequirementDSL.g:1879:4: ( (lv_pronoun_5_0= ruleRelativePronounsSubject ) )
                    // InternalRequirementDSL.g:1880:5: (lv_pronoun_5_0= ruleRelativePronounsSubject )
                    {
                    // InternalRequirementDSL.g:1880:5: (lv_pronoun_5_0= ruleRelativePronounsSubject )
                    // InternalRequirementDSL.g:1881:6: lv_pronoun_5_0= ruleRelativePronounsSubject
                    {

                    						newCompositeNode(grammarAccess.getRelativeSentenceAccess().getPronounRelativePronounsSubjectParserRuleCall_1_0_0());
                    					
                    pushFollow(FOLLOW_14);
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

                    // InternalRequirementDSL.g:1898:4: ( ( (lv_auxiliar_6_0= ruleWORD ) ) ( (lv_negation_7_0= ruleNegation ) ) )?
                    int alt47=2;
                    alt47 = dfa47.predict(input);
                    switch (alt47) {
                        case 1 :
                            // InternalRequirementDSL.g:1899:5: ( (lv_auxiliar_6_0= ruleWORD ) ) ( (lv_negation_7_0= ruleNegation ) )
                            {
                            // InternalRequirementDSL.g:1899:5: ( (lv_auxiliar_6_0= ruleWORD ) )
                            // InternalRequirementDSL.g:1900:6: (lv_auxiliar_6_0= ruleWORD )
                            {
                            // InternalRequirementDSL.g:1900:6: (lv_auxiliar_6_0= ruleWORD )
                            // InternalRequirementDSL.g:1901:7: lv_auxiliar_6_0= ruleWORD
                            {

                            							newCompositeNode(grammarAccess.getRelativeSentenceAccess().getAuxiliarWORDParserRuleCall_1_1_0_0());
                            						
                            pushFollow(FOLLOW_28);
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

                            // InternalRequirementDSL.g:1918:5: ( (lv_negation_7_0= ruleNegation ) )
                            // InternalRequirementDSL.g:1919:6: (lv_negation_7_0= ruleNegation )
                            {
                            // InternalRequirementDSL.g:1919:6: (lv_negation_7_0= ruleNegation )
                            // InternalRequirementDSL.g:1920:7: lv_negation_7_0= ruleNegation
                            {

                            							newCompositeNode(grammarAccess.getRelativeSentenceAccess().getNegationNegationParserRuleCall_1_1_1_0());
                            						
                            pushFollow(FOLLOW_14);
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

                    // InternalRequirementDSL.g:1938:4: ( (lv_predicate_8_0= rulePredicate ) )
                    // InternalRequirementDSL.g:1939:5: (lv_predicate_8_0= rulePredicate )
                    {
                    // InternalRequirementDSL.g:1939:5: (lv_predicate_8_0= rulePredicate )
                    // InternalRequirementDSL.g:1940:6: lv_predicate_8_0= rulePredicate
                    {

                    						newCompositeNode(grammarAccess.getRelativeSentenceAccess().getPredicatePredicateParserRuleCall_1_2_0());
                    					
                    pushFollow(FOLLOW_15);
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

                    // InternalRequirementDSL.g:1957:4: ( (lv_constraints_9_0= ruleConstraints ) )*
                    loop48:
                    do {
                        int alt48=2;
                        int LA48_0 = input.LA(1);

                        if ( (LA48_0==36||(LA48_0>=38 && LA48_0<=59)||LA48_0==89) ) {
                            alt48=1;
                        }


                        switch (alt48) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:1958:5: (lv_constraints_9_0= ruleConstraints )
                    	    {
                    	    // InternalRequirementDSL.g:1958:5: (lv_constraints_9_0= ruleConstraints )
                    	    // InternalRequirementDSL.g:1959:6: lv_constraints_9_0= ruleConstraints
                    	    {

                    	    						newCompositeNode(grammarAccess.getRelativeSentenceAccess().getConstraintsConstraintsParserRuleCall_1_3_0());
                    	    					
                    	    pushFollow(FOLLOW_15);
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
                    	    break loop48;
                        }
                    } while (true);


                    }


                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:1978:3: ( ( (lv_pronoun_10_0= ruleRelativePronounsObject ) ) ( ( (lv_clause_11_0= ruleModalitySentence ) ) | ( (lv_clause_12_0= rulePredicateSentence ) ) ) )
                    {
                    // InternalRequirementDSL.g:1978:3: ( ( (lv_pronoun_10_0= ruleRelativePronounsObject ) ) ( ( (lv_clause_11_0= ruleModalitySentence ) ) | ( (lv_clause_12_0= rulePredicateSentence ) ) ) )
                    // InternalRequirementDSL.g:1979:4: ( (lv_pronoun_10_0= ruleRelativePronounsObject ) ) ( ( (lv_clause_11_0= ruleModalitySentence ) ) | ( (lv_clause_12_0= rulePredicateSentence ) ) )
                    {
                    // InternalRequirementDSL.g:1979:4: ( (lv_pronoun_10_0= ruleRelativePronounsObject ) )
                    // InternalRequirementDSL.g:1980:5: (lv_pronoun_10_0= ruleRelativePronounsObject )
                    {
                    // InternalRequirementDSL.g:1980:5: (lv_pronoun_10_0= ruleRelativePronounsObject )
                    // InternalRequirementDSL.g:1981:6: lv_pronoun_10_0= ruleRelativePronounsObject
                    {

                    						newCompositeNode(grammarAccess.getRelativeSentenceAccess().getPronounRelativePronounsObjectParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_19);
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

                    // InternalRequirementDSL.g:1998:4: ( ( (lv_clause_11_0= ruleModalitySentence ) ) | ( (lv_clause_12_0= rulePredicateSentence ) ) )
                    int alt49=2;
                    alt49 = dfa49.predict(input);
                    switch (alt49) {
                        case 1 :
                            // InternalRequirementDSL.g:1999:5: ( (lv_clause_11_0= ruleModalitySentence ) )
                            {
                            // InternalRequirementDSL.g:1999:5: ( (lv_clause_11_0= ruleModalitySentence ) )
                            // InternalRequirementDSL.g:2000:6: (lv_clause_11_0= ruleModalitySentence )
                            {
                            // InternalRequirementDSL.g:2000:6: (lv_clause_11_0= ruleModalitySentence )
                            // InternalRequirementDSL.g:2001:7: lv_clause_11_0= ruleModalitySentence
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
                            // InternalRequirementDSL.g:2019:5: ( (lv_clause_12_0= rulePredicateSentence ) )
                            {
                            // InternalRequirementDSL.g:2019:5: ( (lv_clause_12_0= rulePredicateSentence ) )
                            // InternalRequirementDSL.g:2020:6: (lv_clause_12_0= rulePredicateSentence )
                            {
                            // InternalRequirementDSL.g:2020:6: (lv_clause_12_0= rulePredicateSentence )
                            // InternalRequirementDSL.g:2021:7: lv_clause_12_0= rulePredicateSentence
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
    // InternalRequirementDSL.g:2044:1: entryRuleActors returns [EObject current=null] : iv_ruleActors= ruleActors EOF ;
    public final EObject entryRuleActors() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleActors = null;


        try {
            // InternalRequirementDSL.g:2044:47: (iv_ruleActors= ruleActors EOF )
            // InternalRequirementDSL.g:2045:2: iv_ruleActors= ruleActors EOF
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
    // InternalRequirementDSL.g:2051:1: ruleActors returns [EObject current=null] : ( ( (lv_actors_0_0= ruleActor ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_actors_2_0= ruleActor ) ) )* ) ;
    public final EObject ruleActors() throws RecognitionException {
        EObject current = null;

        EObject lv_actors_0_0 = null;

        AntlrDatatypeRuleToken lv_conjunction_1_0 = null;

        EObject lv_actors_2_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2057:2: ( ( ( (lv_actors_0_0= ruleActor ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_actors_2_0= ruleActor ) ) )* ) )
            // InternalRequirementDSL.g:2058:2: ( ( (lv_actors_0_0= ruleActor ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_actors_2_0= ruleActor ) ) )* )
            {
            // InternalRequirementDSL.g:2058:2: ( ( (lv_actors_0_0= ruleActor ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_actors_2_0= ruleActor ) ) )* )
            // InternalRequirementDSL.g:2059:3: ( (lv_actors_0_0= ruleActor ) ) ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_actors_2_0= ruleActor ) ) )*
            {
            // InternalRequirementDSL.g:2059:3: ( (lv_actors_0_0= ruleActor ) )
            // InternalRequirementDSL.g:2060:4: (lv_actors_0_0= ruleActor )
            {
            // InternalRequirementDSL.g:2060:4: (lv_actors_0_0= ruleActor )
            // InternalRequirementDSL.g:2061:5: lv_actors_0_0= ruleActor
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

            // InternalRequirementDSL.g:2078:3: ( ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_actors_2_0= ruleActor ) ) )*
            loop51:
            do {
                int alt51=2;
                int LA51_0 = input.LA(1);

                if ( ((LA51_0>=32 && LA51_0<=33)) ) {
                    alt51=1;
                }


                switch (alt51) {
            	case 1 :
            	    // InternalRequirementDSL.g:2079:4: ( (lv_conjunction_1_0= ruleConjunction ) ) ( (lv_actors_2_0= ruleActor ) )
            	    {
            	    // InternalRequirementDSL.g:2079:4: ( (lv_conjunction_1_0= ruleConjunction ) )
            	    // InternalRequirementDSL.g:2080:5: (lv_conjunction_1_0= ruleConjunction )
            	    {
            	    // InternalRequirementDSL.g:2080:5: (lv_conjunction_1_0= ruleConjunction )
            	    // InternalRequirementDSL.g:2081:6: lv_conjunction_1_0= ruleConjunction
            	    {

            	    						newCompositeNode(grammarAccess.getActorsAccess().getConjunctionConjunctionParserRuleCall_1_0_0());
            	    					
            	    pushFollow(FOLLOW_19);
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

            	    // InternalRequirementDSL.g:2098:4: ( (lv_actors_2_0= ruleActor ) )
            	    // InternalRequirementDSL.g:2099:5: (lv_actors_2_0= ruleActor )
            	    {
            	    // InternalRequirementDSL.g:2099:5: (lv_actors_2_0= ruleActor )
            	    // InternalRequirementDSL.g:2100:6: lv_actors_2_0= ruleActor
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
            	    break loop51;
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
    // InternalRequirementDSL.g:2122:1: entryRuleActor returns [EObject current=null] : iv_ruleActor= ruleActor EOF ;
    public final EObject entryRuleActor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleActor = null;


        try {
            // InternalRequirementDSL.g:2122:46: (iv_ruleActor= ruleActor EOF )
            // InternalRequirementDSL.g:2123:2: iv_ruleActor= ruleActor EOF
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
    // InternalRequirementDSL.g:2129:1: ruleActor returns [EObject current=null] : ( (this_PreNominative_0= rulePreNominative )? ( ( (lv_actor_1_0= ruleWORD ) ) | ( (lv_actor_2_0= RULE_STRING ) ) ) ) ;
    public final EObject ruleActor() throws RecognitionException {
        EObject current = null;

        Token lv_actor_2_0=null;
        EObject this_PreNominative_0 = null;

        AntlrDatatypeRuleToken lv_actor_1_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2135:2: ( ( (this_PreNominative_0= rulePreNominative )? ( ( (lv_actor_1_0= ruleWORD ) ) | ( (lv_actor_2_0= RULE_STRING ) ) ) ) )
            // InternalRequirementDSL.g:2136:2: ( (this_PreNominative_0= rulePreNominative )? ( ( (lv_actor_1_0= ruleWORD ) ) | ( (lv_actor_2_0= RULE_STRING ) ) ) )
            {
            // InternalRequirementDSL.g:2136:2: ( (this_PreNominative_0= rulePreNominative )? ( ( (lv_actor_1_0= ruleWORD ) ) | ( (lv_actor_2_0= RULE_STRING ) ) ) )
            // InternalRequirementDSL.g:2137:3: (this_PreNominative_0= rulePreNominative )? ( ( (lv_actor_1_0= ruleWORD ) ) | ( (lv_actor_2_0= RULE_STRING ) ) )
            {
            // InternalRequirementDSL.g:2137:3: (this_PreNominative_0= rulePreNominative )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( ((LA52_0>=60 && LA52_0<=66)||(LA52_0>=68 && LA52_0<=73)||(LA52_0>=79 && LA52_0<=88)) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // InternalRequirementDSL.g:2138:4: this_PreNominative_0= rulePreNominative
                    {

                    				newCompositeNode(grammarAccess.getActorAccess().getPreNominativeParserRuleCall_0());
                    			
                    pushFollow(FOLLOW_24);
                    this_PreNominative_0=rulePreNominative();

                    state._fsp--;


                    				current = this_PreNominative_0;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;

            }

            // InternalRequirementDSL.g:2147:3: ( ( (lv_actor_1_0= ruleWORD ) ) | ( (lv_actor_2_0= RULE_STRING ) ) )
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==RULE_ID) ) {
                alt53=1;
            }
            else if ( (LA53_0==RULE_STRING) ) {
                alt53=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;
            }
            switch (alt53) {
                case 1 :
                    // InternalRequirementDSL.g:2148:4: ( (lv_actor_1_0= ruleWORD ) )
                    {
                    // InternalRequirementDSL.g:2148:4: ( (lv_actor_1_0= ruleWORD ) )
                    // InternalRequirementDSL.g:2149:5: (lv_actor_1_0= ruleWORD )
                    {
                    // InternalRequirementDSL.g:2149:5: (lv_actor_1_0= ruleWORD )
                    // InternalRequirementDSL.g:2150:6: lv_actor_1_0= ruleWORD
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
                    // InternalRequirementDSL.g:2168:4: ( (lv_actor_2_0= RULE_STRING ) )
                    {
                    // InternalRequirementDSL.g:2168:4: ( (lv_actor_2_0= RULE_STRING ) )
                    // InternalRequirementDSL.g:2169:5: (lv_actor_2_0= RULE_STRING )
                    {
                    // InternalRequirementDSL.g:2169:5: (lv_actor_2_0= RULE_STRING )
                    // InternalRequirementDSL.g:2170:6: lv_actor_2_0= RULE_STRING
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


    // $ANTLR start "entryRulePredicate"
    // InternalRequirementDSL.g:2191:1: entryRulePredicate returns [EObject current=null] : iv_rulePredicate= rulePredicate EOF ;
    public final EObject entryRulePredicate() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePredicate = null;


        try {
            // InternalRequirementDSL.g:2191:50: (iv_rulePredicate= rulePredicate EOF )
            // InternalRequirementDSL.g:2192:2: iv_rulePredicate= rulePredicate EOF
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
    // InternalRequirementDSL.g:2198:1: rulePredicate returns [EObject current=null] : ( ( (lv_predicates_0_0= ruleWORD ) )+ | ( (lv_predicates_1_0= RULE_STRING ) ) | ( ( (lv_predicates_2_0= ruleWORD ) )+ ( (lv_object_3_0= rulePredicateObject ) ) ) ) ;
    public final EObject rulePredicate() throws RecognitionException {
        EObject current = null;

        Token lv_predicates_1_0=null;
        AntlrDatatypeRuleToken lv_predicates_0_0 = null;

        AntlrDatatypeRuleToken lv_predicates_2_0 = null;

        EObject lv_object_3_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2204:2: ( ( ( (lv_predicates_0_0= ruleWORD ) )+ | ( (lv_predicates_1_0= RULE_STRING ) ) | ( ( (lv_predicates_2_0= ruleWORD ) )+ ( (lv_object_3_0= rulePredicateObject ) ) ) ) )
            // InternalRequirementDSL.g:2205:2: ( ( (lv_predicates_0_0= ruleWORD ) )+ | ( (lv_predicates_1_0= RULE_STRING ) ) | ( ( (lv_predicates_2_0= ruleWORD ) )+ ( (lv_object_3_0= rulePredicateObject ) ) ) )
            {
            // InternalRequirementDSL.g:2205:2: ( ( (lv_predicates_0_0= ruleWORD ) )+ | ( (lv_predicates_1_0= RULE_STRING ) ) | ( ( (lv_predicates_2_0= ruleWORD ) )+ ( (lv_object_3_0= rulePredicateObject ) ) ) )
            int alt56=3;
            alt56 = dfa56.predict(input);
            switch (alt56) {
                case 1 :
                    // InternalRequirementDSL.g:2206:3: ( (lv_predicates_0_0= ruleWORD ) )+
                    {
                    // InternalRequirementDSL.g:2206:3: ( (lv_predicates_0_0= ruleWORD ) )+
                    int cnt54=0;
                    loop54:
                    do {
                        int alt54=2;
                        int LA54_0 = input.LA(1);

                        if ( (LA54_0==RULE_ID) ) {
                            alt54=1;
                        }


                        switch (alt54) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:2207:4: (lv_predicates_0_0= ruleWORD )
                    	    {
                    	    // InternalRequirementDSL.g:2207:4: (lv_predicates_0_0= ruleWORD )
                    	    // InternalRequirementDSL.g:2208:5: lv_predicates_0_0= ruleWORD
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
                    	    if ( cnt54 >= 1 ) break loop54;
                                EarlyExitException eee =
                                    new EarlyExitException(54, input);
                                throw eee;
                        }
                        cnt54++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:2226:3: ( (lv_predicates_1_0= RULE_STRING ) )
                    {
                    // InternalRequirementDSL.g:2226:3: ( (lv_predicates_1_0= RULE_STRING ) )
                    // InternalRequirementDSL.g:2227:4: (lv_predicates_1_0= RULE_STRING )
                    {
                    // InternalRequirementDSL.g:2227:4: (lv_predicates_1_0= RULE_STRING )
                    // InternalRequirementDSL.g:2228:5: lv_predicates_1_0= RULE_STRING
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
                    // InternalRequirementDSL.g:2245:3: ( ( (lv_predicates_2_0= ruleWORD ) )+ ( (lv_object_3_0= rulePredicateObject ) ) )
                    {
                    // InternalRequirementDSL.g:2245:3: ( ( (lv_predicates_2_0= ruleWORD ) )+ ( (lv_object_3_0= rulePredicateObject ) ) )
                    // InternalRequirementDSL.g:2246:4: ( (lv_predicates_2_0= ruleWORD ) )+ ( (lv_object_3_0= rulePredicateObject ) )
                    {
                    // InternalRequirementDSL.g:2246:4: ( (lv_predicates_2_0= ruleWORD ) )+
                    int cnt55=0;
                    loop55:
                    do {
                        int alt55=2;
                        int LA55_0 = input.LA(1);

                        if ( (LA55_0==RULE_ID) ) {
                            alt55=1;
                        }


                        switch (alt55) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:2247:5: (lv_predicates_2_0= ruleWORD )
                    	    {
                    	    // InternalRequirementDSL.g:2247:5: (lv_predicates_2_0= ruleWORD )
                    	    // InternalRequirementDSL.g:2248:6: lv_predicates_2_0= ruleWORD
                    	    {

                    	    						newCompositeNode(grammarAccess.getPredicateAccess().getPredicatesWORDParserRuleCall_2_0_0());
                    	    					
                    	    pushFollow(FOLLOW_29);
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
                    	    if ( cnt55 >= 1 ) break loop55;
                                EarlyExitException eee =
                                    new EarlyExitException(55, input);
                                throw eee;
                        }
                        cnt55++;
                    } while (true);

                    // InternalRequirementDSL.g:2265:4: ( (lv_object_3_0= rulePredicateObject ) )
                    // InternalRequirementDSL.g:2266:5: (lv_object_3_0= rulePredicateObject )
                    {
                    // InternalRequirementDSL.g:2266:5: (lv_object_3_0= rulePredicateObject )
                    // InternalRequirementDSL.g:2267:6: lv_object_3_0= rulePredicateObject
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
    // InternalRequirementDSL.g:2289:1: entryRulePredicateObject returns [EObject current=null] : iv_rulePredicateObject= rulePredicateObject EOF ;
    public final EObject entryRulePredicateObject() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePredicateObject = null;


        try {
            // InternalRequirementDSL.g:2289:56: (iv_rulePredicateObject= rulePredicateObject EOF )
            // InternalRequirementDSL.g:2290:2: iv_rulePredicateObject= rulePredicateObject EOF
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
    // InternalRequirementDSL.g:2296:1: rulePredicateObject returns [EObject current=null] : ( ( (lv_article_0_0= rulePreNominative ) ) ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) ) ) ;
    public final EObject rulePredicateObject() throws RecognitionException {
        EObject current = null;

        Token lv_object_2_0=null;
        EObject lv_article_0_0 = null;

        AntlrDatatypeRuleToken lv_object_1_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2302:2: ( ( ( (lv_article_0_0= rulePreNominative ) ) ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) ) ) )
            // InternalRequirementDSL.g:2303:2: ( ( (lv_article_0_0= rulePreNominative ) ) ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) ) )
            {
            // InternalRequirementDSL.g:2303:2: ( ( (lv_article_0_0= rulePreNominative ) ) ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) ) )
            // InternalRequirementDSL.g:2304:3: ( (lv_article_0_0= rulePreNominative ) ) ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) )
            {
            // InternalRequirementDSL.g:2304:3: ( (lv_article_0_0= rulePreNominative ) )
            // InternalRequirementDSL.g:2305:4: (lv_article_0_0= rulePreNominative )
            {
            // InternalRequirementDSL.g:2305:4: (lv_article_0_0= rulePreNominative )
            // InternalRequirementDSL.g:2306:5: lv_article_0_0= rulePreNominative
            {

            					newCompositeNode(grammarAccess.getPredicateObjectAccess().getArticlePreNominativeParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_24);
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

            // InternalRequirementDSL.g:2323:3: ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) )
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==RULE_ID) ) {
                alt58=1;
            }
            else if ( (LA58_0==RULE_STRING) ) {
                alt58=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;
            }
            switch (alt58) {
                case 1 :
                    // InternalRequirementDSL.g:2324:4: ( (lv_object_1_0= ruleWORD ) )+
                    {
                    // InternalRequirementDSL.g:2324:4: ( (lv_object_1_0= ruleWORD ) )+
                    int cnt57=0;
                    loop57:
                    do {
                        int alt57=2;
                        int LA57_0 = input.LA(1);

                        if ( (LA57_0==RULE_ID) ) {
                            alt57=1;
                        }


                        switch (alt57) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:2325:5: (lv_object_1_0= ruleWORD )
                    	    {
                    	    // InternalRequirementDSL.g:2325:5: (lv_object_1_0= ruleWORD )
                    	    // InternalRequirementDSL.g:2326:6: lv_object_1_0= ruleWORD
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
                    	    if ( cnt57 >= 1 ) break loop57;
                                EarlyExitException eee =
                                    new EarlyExitException(57, input);
                                throw eee;
                        }
                        cnt57++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:2344:4: ( (lv_object_2_0= RULE_STRING ) )
                    {
                    // InternalRequirementDSL.g:2344:4: ( (lv_object_2_0= RULE_STRING ) )
                    // InternalRequirementDSL.g:2345:5: (lv_object_2_0= RULE_STRING )
                    {
                    // InternalRequirementDSL.g:2345:5: (lv_object_2_0= RULE_STRING )
                    // InternalRequirementDSL.g:2346:6: lv_object_2_0= RULE_STRING
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


    // $ANTLR start "entryRuleExistencePreface"
    // InternalRequirementDSL.g:2367:1: entryRuleExistencePreface returns [EObject current=null] : iv_ruleExistencePreface= ruleExistencePreface EOF ;
    public final EObject entryRuleExistencePreface() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExistencePreface = null;


        try {
            // InternalRequirementDSL.g:2367:57: (iv_ruleExistencePreface= ruleExistencePreface EOF )
            // InternalRequirementDSL.g:2368:2: iv_ruleExistencePreface= ruleExistencePreface EOF
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
    // InternalRequirementDSL.g:2374:1: ruleExistencePreface returns [EObject current=null] : ( () otherlv_1= 'there' ( (lv_modifier_2_0= ruleModifier ) )? ( ( ruleWORD )+ | this_STRING_4= RULE_STRING ) ) ;
    public final EObject ruleExistencePreface() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token this_STRING_4=null;
        Enumerator lv_modifier_2_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2380:2: ( ( () otherlv_1= 'there' ( (lv_modifier_2_0= ruleModifier ) )? ( ( ruleWORD )+ | this_STRING_4= RULE_STRING ) ) )
            // InternalRequirementDSL.g:2381:2: ( () otherlv_1= 'there' ( (lv_modifier_2_0= ruleModifier ) )? ( ( ruleWORD )+ | this_STRING_4= RULE_STRING ) )
            {
            // InternalRequirementDSL.g:2381:2: ( () otherlv_1= 'there' ( (lv_modifier_2_0= ruleModifier ) )? ( ( ruleWORD )+ | this_STRING_4= RULE_STRING ) )
            // InternalRequirementDSL.g:2382:3: () otherlv_1= 'there' ( (lv_modifier_2_0= ruleModifier ) )? ( ( ruleWORD )+ | this_STRING_4= RULE_STRING )
            {
            // InternalRequirementDSL.g:2382:3: ()
            // InternalRequirementDSL.g:2383:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getExistencePrefaceAccess().getExistencePrefaceAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,20,FOLLOW_30); 

            			newLeafNode(otherlv_1, grammarAccess.getExistencePrefaceAccess().getThereKeyword_1());
            		
            // InternalRequirementDSL.g:2393:3: ( (lv_modifier_2_0= ruleModifier ) )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( ((LA59_0>=145 && LA59_0<=152)) ) {
                alt59=1;
            }
            switch (alt59) {
                case 1 :
                    // InternalRequirementDSL.g:2394:4: (lv_modifier_2_0= ruleModifier )
                    {
                    // InternalRequirementDSL.g:2394:4: (lv_modifier_2_0= ruleModifier )
                    // InternalRequirementDSL.g:2395:5: lv_modifier_2_0= ruleModifier
                    {

                    					newCompositeNode(grammarAccess.getExistencePrefaceAccess().getModifierModifierEnumRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_24);
                    lv_modifier_2_0=ruleModifier();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getExistencePrefaceRule());
                    					}
                    					set(
                    						current,
                    						"modifier",
                    						lv_modifier_2_0,
                    						"de.fraunhofer.isst.stars.RequirementDSL.Modifier");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            // InternalRequirementDSL.g:2412:3: ( ( ruleWORD )+ | this_STRING_4= RULE_STRING )
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==RULE_ID) ) {
                alt61=1;
            }
            else if ( (LA61_0==RULE_STRING) ) {
                alt61=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;
            }
            switch (alt61) {
                case 1 :
                    // InternalRequirementDSL.g:2413:4: ( ruleWORD )+
                    {
                    // InternalRequirementDSL.g:2413:4: ( ruleWORD )+
                    int cnt60=0;
                    loop60:
                    do {
                        int alt60=2;
                        alt60 = dfa60.predict(input);
                        switch (alt60) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:2414:5: ruleWORD
                    	    {

                    	    					newCompositeNode(grammarAccess.getExistencePrefaceAccess().getWORDParserRuleCall_3_0());
                    	    				
                    	    pushFollow(FOLLOW_27);
                    	    ruleWORD();

                    	    state._fsp--;


                    	    					afterParserOrEnumRuleCall();
                    	    				

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt60 >= 1 ) break loop60;
                                EarlyExitException eee =
                                    new EarlyExitException(60, input);
                                throw eee;
                        }
                        cnt60++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:2423:4: this_STRING_4= RULE_STRING
                    {
                    this_STRING_4=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    				newLeafNode(this_STRING_4, grammarAccess.getExistencePrefaceAccess().getSTRINGTerminalRuleCall_3_1());
                    			

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


    // $ANTLR start "entryRuleObject"
    // InternalRequirementDSL.g:2432:1: entryRuleObject returns [EObject current=null] : iv_ruleObject= ruleObject EOF ;
    public final EObject entryRuleObject() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleObject = null;


        try {
            // InternalRequirementDSL.g:2432:47: (iv_ruleObject= ruleObject EOF )
            // InternalRequirementDSL.g:2433:2: iv_ruleObject= ruleObject EOF
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
    // InternalRequirementDSL.g:2439:1: ruleObject returns [EObject current=null] : ( (this_PreNominative_0= rulePreNominative )? ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) ) ) ;
    public final EObject ruleObject() throws RecognitionException {
        EObject current = null;

        Token lv_object_2_0=null;
        EObject this_PreNominative_0 = null;

        AntlrDatatypeRuleToken lv_object_1_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2445:2: ( ( (this_PreNominative_0= rulePreNominative )? ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) ) ) )
            // InternalRequirementDSL.g:2446:2: ( (this_PreNominative_0= rulePreNominative )? ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) ) )
            {
            // InternalRequirementDSL.g:2446:2: ( (this_PreNominative_0= rulePreNominative )? ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) ) )
            // InternalRequirementDSL.g:2447:3: (this_PreNominative_0= rulePreNominative )? ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) )
            {
            // InternalRequirementDSL.g:2447:3: (this_PreNominative_0= rulePreNominative )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( ((LA62_0>=60 && LA62_0<=66)||(LA62_0>=68 && LA62_0<=73)||(LA62_0>=79 && LA62_0<=88)) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // InternalRequirementDSL.g:2448:4: this_PreNominative_0= rulePreNominative
                    {

                    				newCompositeNode(grammarAccess.getObjectAccess().getPreNominativeParserRuleCall_0());
                    			
                    pushFollow(FOLLOW_24);
                    this_PreNominative_0=rulePreNominative();

                    state._fsp--;


                    				current = this_PreNominative_0;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;

            }

            // InternalRequirementDSL.g:2457:3: ( ( (lv_object_1_0= ruleWORD ) )+ | ( (lv_object_2_0= RULE_STRING ) ) )
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==RULE_ID) ) {
                alt64=1;
            }
            else if ( (LA64_0==RULE_STRING) ) {
                alt64=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;
            }
            switch (alt64) {
                case 1 :
                    // InternalRequirementDSL.g:2458:4: ( (lv_object_1_0= ruleWORD ) )+
                    {
                    // InternalRequirementDSL.g:2458:4: ( (lv_object_1_0= ruleWORD ) )+
                    int cnt63=0;
                    loop63:
                    do {
                        int alt63=2;
                        int LA63_0 = input.LA(1);

                        if ( (LA63_0==RULE_ID) ) {
                            alt63=1;
                        }


                        switch (alt63) {
                    	case 1 :
                    	    // InternalRequirementDSL.g:2459:5: (lv_object_1_0= ruleWORD )
                    	    {
                    	    // InternalRequirementDSL.g:2459:5: (lv_object_1_0= ruleWORD )
                    	    // InternalRequirementDSL.g:2460:6: lv_object_1_0= ruleWORD
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
                    	    if ( cnt63 >= 1 ) break loop63;
                                EarlyExitException eee =
                                    new EarlyExitException(63, input);
                                throw eee;
                        }
                        cnt63++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:2478:4: ( (lv_object_2_0= RULE_STRING ) )
                    {
                    // InternalRequirementDSL.g:2478:4: ( (lv_object_2_0= RULE_STRING ) )
                    // InternalRequirementDSL.g:2479:5: (lv_object_2_0= RULE_STRING )
                    {
                    // InternalRequirementDSL.g:2479:5: (lv_object_2_0= RULE_STRING )
                    // InternalRequirementDSL.g:2480:6: lv_object_2_0= RULE_STRING
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


    // $ANTLR start "entryRulePreNominative"
    // InternalRequirementDSL.g:2501:1: entryRulePreNominative returns [EObject current=null] : iv_rulePreNominative= rulePreNominative EOF ;
    public final EObject entryRulePreNominative() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePreNominative = null;


        try {
            // InternalRequirementDSL.g:2501:54: (iv_rulePreNominative= rulePreNominative EOF )
            // InternalRequirementDSL.g:2502:2: iv_rulePreNominative= rulePreNominative EOF
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
    // InternalRequirementDSL.g:2508:1: rulePreNominative returns [EObject current=null] : ( ( (lv_determiner_0_0= ruleQuantification ) ) | ( (lv_article_1_0= ruleArticles ) ) | ( (lv_article_2_0= ruleRefArticles ) ) ) ;
    public final EObject rulePreNominative() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_determiner_0_0 = null;

        AntlrDatatypeRuleToken lv_article_1_0 = null;

        AntlrDatatypeRuleToken lv_article_2_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2514:2: ( ( ( (lv_determiner_0_0= ruleQuantification ) ) | ( (lv_article_1_0= ruleArticles ) ) | ( (lv_article_2_0= ruleRefArticles ) ) ) )
            // InternalRequirementDSL.g:2515:2: ( ( (lv_determiner_0_0= ruleQuantification ) ) | ( (lv_article_1_0= ruleArticles ) ) | ( (lv_article_2_0= ruleRefArticles ) ) )
            {
            // InternalRequirementDSL.g:2515:2: ( ( (lv_determiner_0_0= ruleQuantification ) ) | ( (lv_article_1_0= ruleArticles ) ) | ( (lv_article_2_0= ruleRefArticles ) ) )
            int alt65=3;
            switch ( input.LA(1) ) {
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
                {
                alt65=1;
                }
                break;
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
                {
                alt65=2;
                }
                break;
            case 85:
            case 86:
            case 87:
            case 88:
                {
                alt65=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 65, 0, input);

                throw nvae;
            }

            switch (alt65) {
                case 1 :
                    // InternalRequirementDSL.g:2516:3: ( (lv_determiner_0_0= ruleQuantification ) )
                    {
                    // InternalRequirementDSL.g:2516:3: ( (lv_determiner_0_0= ruleQuantification ) )
                    // InternalRequirementDSL.g:2517:4: (lv_determiner_0_0= ruleQuantification )
                    {
                    // InternalRequirementDSL.g:2517:4: (lv_determiner_0_0= ruleQuantification )
                    // InternalRequirementDSL.g:2518:5: lv_determiner_0_0= ruleQuantification
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
                    // InternalRequirementDSL.g:2536:3: ( (lv_article_1_0= ruleArticles ) )
                    {
                    // InternalRequirementDSL.g:2536:3: ( (lv_article_1_0= ruleArticles ) )
                    // InternalRequirementDSL.g:2537:4: (lv_article_1_0= ruleArticles )
                    {
                    // InternalRequirementDSL.g:2537:4: (lv_article_1_0= ruleArticles )
                    // InternalRequirementDSL.g:2538:5: lv_article_1_0= ruleArticles
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
                    // InternalRequirementDSL.g:2556:3: ( (lv_article_2_0= ruleRefArticles ) )
                    {
                    // InternalRequirementDSL.g:2556:3: ( (lv_article_2_0= ruleRefArticles ) )
                    // InternalRequirementDSL.g:2557:4: (lv_article_2_0= ruleRefArticles )
                    {
                    // InternalRequirementDSL.g:2557:4: (lv_article_2_0= ruleRefArticles )
                    // InternalRequirementDSL.g:2558:5: lv_article_2_0= ruleRefArticles
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
    // InternalRequirementDSL.g:2579:1: entryRuleAdverbial returns [String current=null] : iv_ruleAdverbial= ruleAdverbial EOF ;
    public final String entryRuleAdverbial() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleAdverbial = null;


        try {
            // InternalRequirementDSL.g:2579:49: (iv_ruleAdverbial= ruleAdverbial EOF )
            // InternalRequirementDSL.g:2580:2: iv_ruleAdverbial= ruleAdverbial EOF
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
    // InternalRequirementDSL.g:2586:1: ruleAdverbial returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_SizeAdverbial_0= ruleSizeAdverbial | this_PositionAdverbial_1= rulePositionAdverbial | this_ComparisonAdverbial_2= ruleComparisonAdverbial ) ;
    public final AntlrDatatypeRuleToken ruleAdverbial() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_SizeAdverbial_0 = null;

        AntlrDatatypeRuleToken this_PositionAdverbial_1 = null;

        AntlrDatatypeRuleToken this_ComparisonAdverbial_2 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2592:2: ( (this_SizeAdverbial_0= ruleSizeAdverbial | this_PositionAdverbial_1= rulePositionAdverbial | this_ComparisonAdverbial_2= ruleComparisonAdverbial ) )
            // InternalRequirementDSL.g:2593:2: (this_SizeAdverbial_0= ruleSizeAdverbial | this_PositionAdverbial_1= rulePositionAdverbial | this_ComparisonAdverbial_2= ruleComparisonAdverbial )
            {
            // InternalRequirementDSL.g:2593:2: (this_SizeAdverbial_0= ruleSizeAdverbial | this_PositionAdverbial_1= rulePositionAdverbial | this_ComparisonAdverbial_2= ruleComparisonAdverbial )
            int alt66=3;
            switch ( input.LA(1) ) {
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
                {
                alt66=1;
                }
                break;
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
                {
                alt66=2;
                }
                break;
            case 36:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                {
                alt66=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;
            }

            switch (alt66) {
                case 1 :
                    // InternalRequirementDSL.g:2594:3: this_SizeAdverbial_0= ruleSizeAdverbial
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
                    // InternalRequirementDSL.g:2605:3: this_PositionAdverbial_1= rulePositionAdverbial
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
                    // InternalRequirementDSL.g:2616:3: this_ComparisonAdverbial_2= ruleComparisonAdverbial
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


    // $ANTLR start "entryRuleConstraints"
    // InternalRequirementDSL.g:2630:1: entryRuleConstraints returns [EObject current=null] : iv_ruleConstraints= ruleConstraints EOF ;
    public final EObject entryRuleConstraints() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstraints = null;


        try {
            // InternalRequirementDSL.g:2630:52: (iv_ruleConstraints= ruleConstraints EOF )
            // InternalRequirementDSL.g:2631:2: iv_ruleConstraints= ruleConstraints EOF
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
    // InternalRequirementDSL.g:2637:1: ruleConstraints returns [EObject current=null] : ( ( (lv_timeConstraint_0_0= ruleTimeConstraint ) ) | ( (lv_constraint_1_0= ruleConstraint ) ) ) ;
    public final EObject ruleConstraints() throws RecognitionException {
        EObject current = null;

        EObject lv_timeConstraint_0_0 = null;

        EObject lv_constraint_1_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2643:2: ( ( ( (lv_timeConstraint_0_0= ruleTimeConstraint ) ) | ( (lv_constraint_1_0= ruleConstraint ) ) ) )
            // InternalRequirementDSL.g:2644:2: ( ( (lv_timeConstraint_0_0= ruleTimeConstraint ) ) | ( (lv_constraint_1_0= ruleConstraint ) ) )
            {
            // InternalRequirementDSL.g:2644:2: ( ( (lv_timeConstraint_0_0= ruleTimeConstraint ) ) | ( (lv_constraint_1_0= ruleConstraint ) ) )
            int alt67=2;
            alt67 = dfa67.predict(input);
            switch (alt67) {
                case 1 :
                    // InternalRequirementDSL.g:2645:3: ( (lv_timeConstraint_0_0= ruleTimeConstraint ) )
                    {
                    // InternalRequirementDSL.g:2645:3: ( (lv_timeConstraint_0_0= ruleTimeConstraint ) )
                    // InternalRequirementDSL.g:2646:4: (lv_timeConstraint_0_0= ruleTimeConstraint )
                    {
                    // InternalRequirementDSL.g:2646:4: (lv_timeConstraint_0_0= ruleTimeConstraint )
                    // InternalRequirementDSL.g:2647:5: lv_timeConstraint_0_0= ruleTimeConstraint
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
                    // InternalRequirementDSL.g:2665:3: ( (lv_constraint_1_0= ruleConstraint ) )
                    {
                    // InternalRequirementDSL.g:2665:3: ( (lv_constraint_1_0= ruleConstraint ) )
                    // InternalRequirementDSL.g:2666:4: (lv_constraint_1_0= ruleConstraint )
                    {
                    // InternalRequirementDSL.g:2666:4: (lv_constraint_1_0= ruleConstraint )
                    // InternalRequirementDSL.g:2667:5: lv_constraint_1_0= ruleConstraint
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
    // InternalRequirementDSL.g:2688:1: entryRuleConstraint returns [EObject current=null] : iv_ruleConstraint= ruleConstraint EOF ;
    public final EObject entryRuleConstraint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstraint = null;


        try {
            // InternalRequirementDSL.g:2688:51: (iv_ruleConstraint= ruleConstraint EOF )
            // InternalRequirementDSL.g:2689:2: iv_ruleConstraint= ruleConstraint EOF
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
    // InternalRequirementDSL.g:2695:1: ruleConstraint returns [EObject current=null] : ( ( (lv_ordinator_0_0= ruleConstraintOrdinators ) ) ( ( (lv_constraint_1_0= ruleObjectConstraint ) ) | ( (lv_constraint_2_0= ruleUnitConstraints ) ) | ( (lv_constraint_3_0= ruleSetConstraint ) ) ) ) ;
    public final EObject ruleConstraint() throws RecognitionException {
        EObject current = null;

        EObject lv_ordinator_0_0 = null;

        EObject lv_constraint_1_0 = null;

        EObject lv_constraint_2_0 = null;

        EObject lv_constraint_3_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2701:2: ( ( ( (lv_ordinator_0_0= ruleConstraintOrdinators ) ) ( ( (lv_constraint_1_0= ruleObjectConstraint ) ) | ( (lv_constraint_2_0= ruleUnitConstraints ) ) | ( (lv_constraint_3_0= ruleSetConstraint ) ) ) ) )
            // InternalRequirementDSL.g:2702:2: ( ( (lv_ordinator_0_0= ruleConstraintOrdinators ) ) ( ( (lv_constraint_1_0= ruleObjectConstraint ) ) | ( (lv_constraint_2_0= ruleUnitConstraints ) ) | ( (lv_constraint_3_0= ruleSetConstraint ) ) ) )
            {
            // InternalRequirementDSL.g:2702:2: ( ( (lv_ordinator_0_0= ruleConstraintOrdinators ) ) ( ( (lv_constraint_1_0= ruleObjectConstraint ) ) | ( (lv_constraint_2_0= ruleUnitConstraints ) ) | ( (lv_constraint_3_0= ruleSetConstraint ) ) ) )
            // InternalRequirementDSL.g:2703:3: ( (lv_ordinator_0_0= ruleConstraintOrdinators ) ) ( ( (lv_constraint_1_0= ruleObjectConstraint ) ) | ( (lv_constraint_2_0= ruleUnitConstraints ) ) | ( (lv_constraint_3_0= ruleSetConstraint ) ) )
            {
            // InternalRequirementDSL.g:2703:3: ( (lv_ordinator_0_0= ruleConstraintOrdinators ) )
            // InternalRequirementDSL.g:2704:4: (lv_ordinator_0_0= ruleConstraintOrdinators )
            {
            // InternalRequirementDSL.g:2704:4: (lv_ordinator_0_0= ruleConstraintOrdinators )
            // InternalRequirementDSL.g:2705:5: lv_ordinator_0_0= ruleConstraintOrdinators
            {

            					newCompositeNode(grammarAccess.getConstraintAccess().getOrdinatorConstraintOrdinatorsParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_31);
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

            // InternalRequirementDSL.g:2722:3: ( ( (lv_constraint_1_0= ruleObjectConstraint ) ) | ( (lv_constraint_2_0= ruleUnitConstraints ) ) | ( (lv_constraint_3_0= ruleSetConstraint ) ) )
            int alt68=3;
            switch ( input.LA(1) ) {
            case RULE_STRING:
            case RULE_ID:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
                {
                alt68=1;
                }
                break;
            case RULE_INT:
            case RULE_FLOAT:
            case 21:
                {
                alt68=2;
                }
                break;
            case 23:
                {
                alt68=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 68, 0, input);

                throw nvae;
            }

            switch (alt68) {
                case 1 :
                    // InternalRequirementDSL.g:2723:4: ( (lv_constraint_1_0= ruleObjectConstraint ) )
                    {
                    // InternalRequirementDSL.g:2723:4: ( (lv_constraint_1_0= ruleObjectConstraint ) )
                    // InternalRequirementDSL.g:2724:5: (lv_constraint_1_0= ruleObjectConstraint )
                    {
                    // InternalRequirementDSL.g:2724:5: (lv_constraint_1_0= ruleObjectConstraint )
                    // InternalRequirementDSL.g:2725:6: lv_constraint_1_0= ruleObjectConstraint
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
                    // InternalRequirementDSL.g:2743:4: ( (lv_constraint_2_0= ruleUnitConstraints ) )
                    {
                    // InternalRequirementDSL.g:2743:4: ( (lv_constraint_2_0= ruleUnitConstraints ) )
                    // InternalRequirementDSL.g:2744:5: (lv_constraint_2_0= ruleUnitConstraints )
                    {
                    // InternalRequirementDSL.g:2744:5: (lv_constraint_2_0= ruleUnitConstraints )
                    // InternalRequirementDSL.g:2745:6: lv_constraint_2_0= ruleUnitConstraints
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
                    // InternalRequirementDSL.g:2763:4: ( (lv_constraint_3_0= ruleSetConstraint ) )
                    {
                    // InternalRequirementDSL.g:2763:4: ( (lv_constraint_3_0= ruleSetConstraint ) )
                    // InternalRequirementDSL.g:2764:5: (lv_constraint_3_0= ruleSetConstraint )
                    {
                    // InternalRequirementDSL.g:2764:5: (lv_constraint_3_0= ruleSetConstraint )
                    // InternalRequirementDSL.g:2765:6: lv_constraint_3_0= ruleSetConstraint
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
    // InternalRequirementDSL.g:2787:1: entryRuleConstraintOrdinators returns [EObject current=null] : iv_ruleConstraintOrdinators= ruleConstraintOrdinators EOF ;
    public final EObject entryRuleConstraintOrdinators() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstraintOrdinators = null;


        try {
            // InternalRequirementDSL.g:2787:61: (iv_ruleConstraintOrdinators= ruleConstraintOrdinators EOF )
            // InternalRequirementDSL.g:2788:2: iv_ruleConstraintOrdinators= ruleConstraintOrdinators EOF
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
    // InternalRequirementDSL.g:2794:1: ruleConstraintOrdinators returns [EObject current=null] : ( ( (lv_stuffing_0_0= ruleStuffWord ) )? ( (lv_adverbial_1_0= ruleAdverbial ) ) ( (lv_comperator_2_0= ruleComperators ) )? ) ;
    public final EObject ruleConstraintOrdinators() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_stuffing_0_0 = null;

        AntlrDatatypeRuleToken lv_adverbial_1_0 = null;

        AntlrDatatypeRuleToken lv_comperator_2_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2800:2: ( ( ( (lv_stuffing_0_0= ruleStuffWord ) )? ( (lv_adverbial_1_0= ruleAdverbial ) ) ( (lv_comperator_2_0= ruleComperators ) )? ) )
            // InternalRequirementDSL.g:2801:2: ( ( (lv_stuffing_0_0= ruleStuffWord ) )? ( (lv_adverbial_1_0= ruleAdverbial ) ) ( (lv_comperator_2_0= ruleComperators ) )? )
            {
            // InternalRequirementDSL.g:2801:2: ( ( (lv_stuffing_0_0= ruleStuffWord ) )? ( (lv_adverbial_1_0= ruleAdverbial ) ) ( (lv_comperator_2_0= ruleComperators ) )? )
            // InternalRequirementDSL.g:2802:3: ( (lv_stuffing_0_0= ruleStuffWord ) )? ( (lv_adverbial_1_0= ruleAdverbial ) ) ( (lv_comperator_2_0= ruleComperators ) )?
            {
            // InternalRequirementDSL.g:2802:3: ( (lv_stuffing_0_0= ruleStuffWord ) )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==89) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // InternalRequirementDSL.g:2803:4: (lv_stuffing_0_0= ruleStuffWord )
                    {
                    // InternalRequirementDSL.g:2803:4: (lv_stuffing_0_0= ruleStuffWord )
                    // InternalRequirementDSL.g:2804:5: lv_stuffing_0_0= ruleStuffWord
                    {

                    					newCompositeNode(grammarAccess.getConstraintOrdinatorsAccess().getStuffingStuffWordParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_32);
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

            // InternalRequirementDSL.g:2821:3: ( (lv_adverbial_1_0= ruleAdverbial ) )
            // InternalRequirementDSL.g:2822:4: (lv_adverbial_1_0= ruleAdverbial )
            {
            // InternalRequirementDSL.g:2822:4: (lv_adverbial_1_0= ruleAdverbial )
            // InternalRequirementDSL.g:2823:5: lv_adverbial_1_0= ruleAdverbial
            {

            					newCompositeNode(grammarAccess.getConstraintOrdinatorsAccess().getAdverbialAdverbialParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_33);
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

            // InternalRequirementDSL.g:2840:3: ( (lv_comperator_2_0= ruleComperators ) )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( ((LA70_0>=34 && LA70_0<=37)) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // InternalRequirementDSL.g:2841:4: (lv_comperator_2_0= ruleComperators )
                    {
                    // InternalRequirementDSL.g:2841:4: (lv_comperator_2_0= ruleComperators )
                    // InternalRequirementDSL.g:2842:5: lv_comperator_2_0= ruleComperators
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
    // InternalRequirementDSL.g:2863:1: entryRuleSetConstraint returns [EObject current=null] : iv_ruleSetConstraint= ruleSetConstraint EOF ;
    public final EObject entryRuleSetConstraint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetConstraint = null;


        try {
            // InternalRequirementDSL.g:2863:54: (iv_ruleSetConstraint= ruleSetConstraint EOF )
            // InternalRequirementDSL.g:2864:2: iv_ruleSetConstraint= ruleSetConstraint EOF
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
    // InternalRequirementDSL.g:2870:1: ruleSetConstraint returns [EObject current=null] : ( ( (lv_set_0_0= ruleObjectSet ) ) | ( (lv_set_1_0= ruleValueSet ) ) ) ;
    public final EObject ruleSetConstraint() throws RecognitionException {
        EObject current = null;

        EObject lv_set_0_0 = null;

        EObject lv_set_1_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2876:2: ( ( ( (lv_set_0_0= ruleObjectSet ) ) | ( (lv_set_1_0= ruleValueSet ) ) ) )
            // InternalRequirementDSL.g:2877:2: ( ( (lv_set_0_0= ruleObjectSet ) ) | ( (lv_set_1_0= ruleValueSet ) ) )
            {
            // InternalRequirementDSL.g:2877:2: ( ( (lv_set_0_0= ruleObjectSet ) ) | ( (lv_set_1_0= ruleValueSet ) ) )
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==23) ) {
                int LA71_1 = input.LA(2);

                if ( ((LA71_1>=RULE_INT && LA71_1<=RULE_FLOAT)) ) {
                    alt71=2;
                }
                else if ( (LA71_1==RULE_STRING||LA71_1==RULE_ID||(LA71_1>=60 && LA71_1<=66)||(LA71_1>=68 && LA71_1<=73)||(LA71_1>=79 && LA71_1<=88)) ) {
                    alt71=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 71, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 71, 0, input);

                throw nvae;
            }
            switch (alt71) {
                case 1 :
                    // InternalRequirementDSL.g:2878:3: ( (lv_set_0_0= ruleObjectSet ) )
                    {
                    // InternalRequirementDSL.g:2878:3: ( (lv_set_0_0= ruleObjectSet ) )
                    // InternalRequirementDSL.g:2879:4: (lv_set_0_0= ruleObjectSet )
                    {
                    // InternalRequirementDSL.g:2879:4: (lv_set_0_0= ruleObjectSet )
                    // InternalRequirementDSL.g:2880:5: lv_set_0_0= ruleObjectSet
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
                    // InternalRequirementDSL.g:2898:3: ( (lv_set_1_0= ruleValueSet ) )
                    {
                    // InternalRequirementDSL.g:2898:3: ( (lv_set_1_0= ruleValueSet ) )
                    // InternalRequirementDSL.g:2899:4: (lv_set_1_0= ruleValueSet )
                    {
                    // InternalRequirementDSL.g:2899:4: (lv_set_1_0= ruleValueSet )
                    // InternalRequirementDSL.g:2900:5: lv_set_1_0= ruleValueSet
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
    // InternalRequirementDSL.g:2921:1: entryRuleTimeConstraint returns [EObject current=null] : iv_ruleTimeConstraint= ruleTimeConstraint EOF ;
    public final EObject entryRuleTimeConstraint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTimeConstraint = null;


        try {
            // InternalRequirementDSL.g:2921:55: (iv_ruleTimeConstraint= ruleTimeConstraint EOF )
            // InternalRequirementDSL.g:2922:2: iv_ruleTimeConstraint= ruleTimeConstraint EOF
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
    // InternalRequirementDSL.g:2928:1: ruleTimeConstraint returns [EObject current=null] : ( ( (lv_ordinator_0_0= ruleConstraintOrdinators ) ) ( (lv_time_1_0= RULE_INT ) ) ( (lv_unit_2_0= ruleTimeUnits ) ) ) ;
    public final EObject ruleTimeConstraint() throws RecognitionException {
        EObject current = null;

        Token lv_time_1_0=null;
        EObject lv_ordinator_0_0 = null;

        AntlrDatatypeRuleToken lv_unit_2_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:2934:2: ( ( ( (lv_ordinator_0_0= ruleConstraintOrdinators ) ) ( (lv_time_1_0= RULE_INT ) ) ( (lv_unit_2_0= ruleTimeUnits ) ) ) )
            // InternalRequirementDSL.g:2935:2: ( ( (lv_ordinator_0_0= ruleConstraintOrdinators ) ) ( (lv_time_1_0= RULE_INT ) ) ( (lv_unit_2_0= ruleTimeUnits ) ) )
            {
            // InternalRequirementDSL.g:2935:2: ( ( (lv_ordinator_0_0= ruleConstraintOrdinators ) ) ( (lv_time_1_0= RULE_INT ) ) ( (lv_unit_2_0= ruleTimeUnits ) ) )
            // InternalRequirementDSL.g:2936:3: ( (lv_ordinator_0_0= ruleConstraintOrdinators ) ) ( (lv_time_1_0= RULE_INT ) ) ( (lv_unit_2_0= ruleTimeUnits ) )
            {
            // InternalRequirementDSL.g:2936:3: ( (lv_ordinator_0_0= ruleConstraintOrdinators ) )
            // InternalRequirementDSL.g:2937:4: (lv_ordinator_0_0= ruleConstraintOrdinators )
            {
            // InternalRequirementDSL.g:2937:4: (lv_ordinator_0_0= ruleConstraintOrdinators )
            // InternalRequirementDSL.g:2938:5: lv_ordinator_0_0= ruleConstraintOrdinators
            {

            					newCompositeNode(grammarAccess.getTimeConstraintAccess().getOrdinatorConstraintOrdinatorsParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_34);
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

            // InternalRequirementDSL.g:2955:3: ( (lv_time_1_0= RULE_INT ) )
            // InternalRequirementDSL.g:2956:4: (lv_time_1_0= RULE_INT )
            {
            // InternalRequirementDSL.g:2956:4: (lv_time_1_0= RULE_INT )
            // InternalRequirementDSL.g:2957:5: lv_time_1_0= RULE_INT
            {
            lv_time_1_0=(Token)match(input,RULE_INT,FOLLOW_35); 

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

            // InternalRequirementDSL.g:2973:3: ( (lv_unit_2_0= ruleTimeUnits ) )
            // InternalRequirementDSL.g:2974:4: (lv_unit_2_0= ruleTimeUnits )
            {
            // InternalRequirementDSL.g:2974:4: (lv_unit_2_0= ruleTimeUnits )
            // InternalRequirementDSL.g:2975:5: lv_unit_2_0= ruleTimeUnits
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
    // InternalRequirementDSL.g:2996:1: entryRuleObjectConstraint returns [EObject current=null] : iv_ruleObjectConstraint= ruleObjectConstraint EOF ;
    public final EObject entryRuleObjectConstraint() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleObjectConstraint = null;


        try {
            // InternalRequirementDSL.g:2996:57: (iv_ruleObjectConstraint= ruleObjectConstraint EOF )
            // InternalRequirementDSL.g:2997:2: iv_ruleObjectConstraint= ruleObjectConstraint EOF
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
    // InternalRequirementDSL.g:3003:1: ruleObjectConstraint returns [EObject current=null] : ( (lv_object_0_0= ruleObject ) ) ;
    public final EObject ruleObjectConstraint() throws RecognitionException {
        EObject current = null;

        EObject lv_object_0_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:3009:2: ( ( (lv_object_0_0= ruleObject ) ) )
            // InternalRequirementDSL.g:3010:2: ( (lv_object_0_0= ruleObject ) )
            {
            // InternalRequirementDSL.g:3010:2: ( (lv_object_0_0= ruleObject ) )
            // InternalRequirementDSL.g:3011:3: (lv_object_0_0= ruleObject )
            {
            // InternalRequirementDSL.g:3011:3: (lv_object_0_0= ruleObject )
            // InternalRequirementDSL.g:3012:4: lv_object_0_0= ruleObject
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
    // InternalRequirementDSL.g:3032:1: entryRuleUnitConstraints returns [EObject current=null] : iv_ruleUnitConstraints= ruleUnitConstraints EOF ;
    public final EObject entryRuleUnitConstraints() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnitConstraints = null;


        try {
            // InternalRequirementDSL.g:3032:56: (iv_ruleUnitConstraints= ruleUnitConstraints EOF )
            // InternalRequirementDSL.g:3033:2: iv_ruleUnitConstraints= ruleUnitConstraints EOF
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
    // InternalRequirementDSL.g:3039:1: ruleUnitConstraints returns [EObject current=null] : (this_SingleValueConstraints_0= ruleSingleValueConstraints | this_IntervallConstraints_1= ruleIntervallConstraints ) ;
    public final EObject ruleUnitConstraints() throws RecognitionException {
        EObject current = null;

        EObject this_SingleValueConstraints_0 = null;

        EObject this_IntervallConstraints_1 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:3045:2: ( (this_SingleValueConstraints_0= ruleSingleValueConstraints | this_IntervallConstraints_1= ruleIntervallConstraints ) )
            // InternalRequirementDSL.g:3046:2: (this_SingleValueConstraints_0= ruleSingleValueConstraints | this_IntervallConstraints_1= ruleIntervallConstraints )
            {
            // InternalRequirementDSL.g:3046:2: (this_SingleValueConstraints_0= ruleSingleValueConstraints | this_IntervallConstraints_1= ruleIntervallConstraints )
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( ((LA72_0>=RULE_INT && LA72_0<=RULE_FLOAT)) ) {
                alt72=1;
            }
            else if ( (LA72_0==21) ) {
                alt72=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 72, 0, input);

                throw nvae;
            }
            switch (alt72) {
                case 1 :
                    // InternalRequirementDSL.g:3047:3: this_SingleValueConstraints_0= ruleSingleValueConstraints
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
                    // InternalRequirementDSL.g:3056:3: this_IntervallConstraints_1= ruleIntervallConstraints
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
    // InternalRequirementDSL.g:3068:1: entryRuleIntervallConstraints returns [EObject current=null] : iv_ruleIntervallConstraints= ruleIntervallConstraints EOF ;
    public final EObject entryRuleIntervallConstraints() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntervallConstraints = null;


        try {
            // InternalRequirementDSL.g:3068:61: (iv_ruleIntervallConstraints= ruleIntervallConstraints EOF )
            // InternalRequirementDSL.g:3069:2: iv_ruleIntervallConstraints= ruleIntervallConstraints EOF
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
    // InternalRequirementDSL.g:3075:1: ruleIntervallConstraints returns [EObject current=null] : (otherlv_0= '[' ( (lv_lower_1_0= ruleValue ) ) otherlv_2= ',' ( (lv_higher_3_0= ruleValue ) ) otherlv_4= ']' ) ;
    public final EObject ruleIntervallConstraints() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_lower_1_0 = null;

        EObject lv_higher_3_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:3081:2: ( (otherlv_0= '[' ( (lv_lower_1_0= ruleValue ) ) otherlv_2= ',' ( (lv_higher_3_0= ruleValue ) ) otherlv_4= ']' ) )
            // InternalRequirementDSL.g:3082:2: (otherlv_0= '[' ( (lv_lower_1_0= ruleValue ) ) otherlv_2= ',' ( (lv_higher_3_0= ruleValue ) ) otherlv_4= ']' )
            {
            // InternalRequirementDSL.g:3082:2: (otherlv_0= '[' ( (lv_lower_1_0= ruleValue ) ) otherlv_2= ',' ( (lv_higher_3_0= ruleValue ) ) otherlv_4= ']' )
            // InternalRequirementDSL.g:3083:3: otherlv_0= '[' ( (lv_lower_1_0= ruleValue ) ) otherlv_2= ',' ( (lv_higher_3_0= ruleValue ) ) otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,21,FOLLOW_36); 

            			newLeafNode(otherlv_0, grammarAccess.getIntervallConstraintsAccess().getLeftSquareBracketKeyword_0());
            		
            // InternalRequirementDSL.g:3087:3: ( (lv_lower_1_0= ruleValue ) )
            // InternalRequirementDSL.g:3088:4: (lv_lower_1_0= ruleValue )
            {
            // InternalRequirementDSL.g:3088:4: (lv_lower_1_0= ruleValue )
            // InternalRequirementDSL.g:3089:5: lv_lower_1_0= ruleValue
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

            otherlv_2=(Token)match(input,18,FOLLOW_36); 

            			newLeafNode(otherlv_2, grammarAccess.getIntervallConstraintsAccess().getCommaKeyword_2());
            		
            // InternalRequirementDSL.g:3110:3: ( (lv_higher_3_0= ruleValue ) )
            // InternalRequirementDSL.g:3111:4: (lv_higher_3_0= ruleValue )
            {
            // InternalRequirementDSL.g:3111:4: (lv_higher_3_0= ruleValue )
            // InternalRequirementDSL.g:3112:5: lv_higher_3_0= ruleValue
            {

            					newCompositeNode(grammarAccess.getIntervallConstraintsAccess().getHigherValueParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_37);
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

            otherlv_4=(Token)match(input,22,FOLLOW_2); 

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
    // InternalRequirementDSL.g:3137:1: entryRuleSingleValueConstraints returns [EObject current=null] : iv_ruleSingleValueConstraints= ruleSingleValueConstraints EOF ;
    public final EObject entryRuleSingleValueConstraints() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSingleValueConstraints = null;


        try {
            // InternalRequirementDSL.g:3137:63: (iv_ruleSingleValueConstraints= ruleSingleValueConstraints EOF )
            // InternalRequirementDSL.g:3138:2: iv_ruleSingleValueConstraints= ruleSingleValueConstraints EOF
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
    // InternalRequirementDSL.g:3144:1: ruleSingleValueConstraints returns [EObject current=null] : ( (lv_value_0_0= ruleValue ) ) ;
    public final EObject ruleSingleValueConstraints() throws RecognitionException {
        EObject current = null;

        EObject lv_value_0_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:3150:2: ( ( (lv_value_0_0= ruleValue ) ) )
            // InternalRequirementDSL.g:3151:2: ( (lv_value_0_0= ruleValue ) )
            {
            // InternalRequirementDSL.g:3151:2: ( (lv_value_0_0= ruleValue ) )
            // InternalRequirementDSL.g:3152:3: (lv_value_0_0= ruleValue )
            {
            // InternalRequirementDSL.g:3152:3: (lv_value_0_0= ruleValue )
            // InternalRequirementDSL.g:3153:4: lv_value_0_0= ruleValue
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
    // InternalRequirementDSL.g:3173:1: entryRuleValueSet returns [EObject current=null] : iv_ruleValueSet= ruleValueSet EOF ;
    public final EObject entryRuleValueSet() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValueSet = null;


        try {
            // InternalRequirementDSL.g:3173:49: (iv_ruleValueSet= ruleValueSet EOF )
            // InternalRequirementDSL.g:3174:2: iv_ruleValueSet= ruleValueSet EOF
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
    // InternalRequirementDSL.g:3180:1: ruleValueSet returns [EObject current=null] : (otherlv_0= '{' ( (lv_elements_1_0= ruleValue ) ) (otherlv_2= ';' ( (lv_elements_3_0= ruleValue ) ) )* otherlv_4= '}' ) ;
    public final EObject ruleValueSet() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_elements_1_0 = null;

        EObject lv_elements_3_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:3186:2: ( (otherlv_0= '{' ( (lv_elements_1_0= ruleValue ) ) (otherlv_2= ';' ( (lv_elements_3_0= ruleValue ) ) )* otherlv_4= '}' ) )
            // InternalRequirementDSL.g:3187:2: (otherlv_0= '{' ( (lv_elements_1_0= ruleValue ) ) (otherlv_2= ';' ( (lv_elements_3_0= ruleValue ) ) )* otherlv_4= '}' )
            {
            // InternalRequirementDSL.g:3187:2: (otherlv_0= '{' ( (lv_elements_1_0= ruleValue ) ) (otherlv_2= ';' ( (lv_elements_3_0= ruleValue ) ) )* otherlv_4= '}' )
            // InternalRequirementDSL.g:3188:3: otherlv_0= '{' ( (lv_elements_1_0= ruleValue ) ) (otherlv_2= ';' ( (lv_elements_3_0= ruleValue ) ) )* otherlv_4= '}'
            {
            otherlv_0=(Token)match(input,23,FOLLOW_36); 

            			newLeafNode(otherlv_0, grammarAccess.getValueSetAccess().getLeftCurlyBracketKeyword_0());
            		
            // InternalRequirementDSL.g:3192:3: ( (lv_elements_1_0= ruleValue ) )
            // InternalRequirementDSL.g:3193:4: (lv_elements_1_0= ruleValue )
            {
            // InternalRequirementDSL.g:3193:4: (lv_elements_1_0= ruleValue )
            // InternalRequirementDSL.g:3194:5: lv_elements_1_0= ruleValue
            {

            					newCompositeNode(grammarAccess.getValueSetAccess().getElementsValueParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_38);
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

            // InternalRequirementDSL.g:3211:3: (otherlv_2= ';' ( (lv_elements_3_0= ruleValue ) ) )*
            loop73:
            do {
                int alt73=2;
                int LA73_0 = input.LA(1);

                if ( (LA73_0==17) ) {
                    alt73=1;
                }


                switch (alt73) {
            	case 1 :
            	    // InternalRequirementDSL.g:3212:4: otherlv_2= ';' ( (lv_elements_3_0= ruleValue ) )
            	    {
            	    otherlv_2=(Token)match(input,17,FOLLOW_36); 

            	    				newLeafNode(otherlv_2, grammarAccess.getValueSetAccess().getSemicolonKeyword_2_0());
            	    			
            	    // InternalRequirementDSL.g:3216:4: ( (lv_elements_3_0= ruleValue ) )
            	    // InternalRequirementDSL.g:3217:5: (lv_elements_3_0= ruleValue )
            	    {
            	    // InternalRequirementDSL.g:3217:5: (lv_elements_3_0= ruleValue )
            	    // InternalRequirementDSL.g:3218:6: lv_elements_3_0= ruleValue
            	    {

            	    						newCompositeNode(grammarAccess.getValueSetAccess().getElementsValueParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_38);
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
            	    break loop73;
                }
            } while (true);

            otherlv_4=(Token)match(input,24,FOLLOW_2); 

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
    // InternalRequirementDSL.g:3244:1: entryRuleObjectSet returns [EObject current=null] : iv_ruleObjectSet= ruleObjectSet EOF ;
    public final EObject entryRuleObjectSet() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleObjectSet = null;


        try {
            // InternalRequirementDSL.g:3244:50: (iv_ruleObjectSet= ruleObjectSet EOF )
            // InternalRequirementDSL.g:3245:2: iv_ruleObjectSet= ruleObjectSet EOF
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
    // InternalRequirementDSL.g:3251:1: ruleObjectSet returns [EObject current=null] : (otherlv_0= '{' ( (lv_elements_1_0= ruleObject ) ) (otherlv_2= ';' ( (lv_elements_3_0= ruleObject ) ) )* otherlv_4= '}' ) ;
    public final EObject ruleObjectSet() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_elements_1_0 = null;

        EObject lv_elements_3_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:3257:2: ( (otherlv_0= '{' ( (lv_elements_1_0= ruleObject ) ) (otherlv_2= ';' ( (lv_elements_3_0= ruleObject ) ) )* otherlv_4= '}' ) )
            // InternalRequirementDSL.g:3258:2: (otherlv_0= '{' ( (lv_elements_1_0= ruleObject ) ) (otherlv_2= ';' ( (lv_elements_3_0= ruleObject ) ) )* otherlv_4= '}' )
            {
            // InternalRequirementDSL.g:3258:2: (otherlv_0= '{' ( (lv_elements_1_0= ruleObject ) ) (otherlv_2= ';' ( (lv_elements_3_0= ruleObject ) ) )* otherlv_4= '}' )
            // InternalRequirementDSL.g:3259:3: otherlv_0= '{' ( (lv_elements_1_0= ruleObject ) ) (otherlv_2= ';' ( (lv_elements_3_0= ruleObject ) ) )* otherlv_4= '}'
            {
            otherlv_0=(Token)match(input,23,FOLLOW_19); 

            			newLeafNode(otherlv_0, grammarAccess.getObjectSetAccess().getLeftCurlyBracketKeyword_0());
            		
            // InternalRequirementDSL.g:3263:3: ( (lv_elements_1_0= ruleObject ) )
            // InternalRequirementDSL.g:3264:4: (lv_elements_1_0= ruleObject )
            {
            // InternalRequirementDSL.g:3264:4: (lv_elements_1_0= ruleObject )
            // InternalRequirementDSL.g:3265:5: lv_elements_1_0= ruleObject
            {

            					newCompositeNode(grammarAccess.getObjectSetAccess().getElementsObjectParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_38);
            lv_elements_1_0=ruleObject();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getObjectSetRule());
            					}
            					add(
            						current,
            						"elements",
            						lv_elements_1_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.Object");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRequirementDSL.g:3282:3: (otherlv_2= ';' ( (lv_elements_3_0= ruleObject ) ) )*
            loop74:
            do {
                int alt74=2;
                int LA74_0 = input.LA(1);

                if ( (LA74_0==17) ) {
                    alt74=1;
                }


                switch (alt74) {
            	case 1 :
            	    // InternalRequirementDSL.g:3283:4: otherlv_2= ';' ( (lv_elements_3_0= ruleObject ) )
            	    {
            	    otherlv_2=(Token)match(input,17,FOLLOW_19); 

            	    				newLeafNode(otherlv_2, grammarAccess.getObjectSetAccess().getSemicolonKeyword_2_0());
            	    			
            	    // InternalRequirementDSL.g:3287:4: ( (lv_elements_3_0= ruleObject ) )
            	    // InternalRequirementDSL.g:3288:5: (lv_elements_3_0= ruleObject )
            	    {
            	    // InternalRequirementDSL.g:3288:5: (lv_elements_3_0= ruleObject )
            	    // InternalRequirementDSL.g:3289:6: lv_elements_3_0= ruleObject
            	    {

            	    						newCompositeNode(grammarAccess.getObjectSetAccess().getElementsObjectParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_38);
            	    lv_elements_3_0=ruleObject();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getObjectSetRule());
            	    						}
            	    						add(
            	    							current,
            	    							"elements",
            	    							lv_elements_3_0,
            	    							"de.fraunhofer.isst.stars.RequirementDSL.Object");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop74;
                }
            } while (true);

            otherlv_4=(Token)match(input,24,FOLLOW_2); 

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
    // InternalRequirementDSL.g:3315:1: entryRuleValue returns [EObject current=null] : iv_ruleValue= ruleValue EOF ;
    public final EObject entryRuleValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValue = null;


        try {
            // InternalRequirementDSL.g:3315:46: (iv_ruleValue= ruleValue EOF )
            // InternalRequirementDSL.g:3316:2: iv_ruleValue= ruleValue EOF
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
    // InternalRequirementDSL.g:3322:1: ruleValue returns [EObject current=null] : (this_IntValue_0= ruleIntValue | this_FloatValue_1= ruleFloatValue ) ;
    public final EObject ruleValue() throws RecognitionException {
        EObject current = null;

        EObject this_IntValue_0 = null;

        EObject this_FloatValue_1 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:3328:2: ( (this_IntValue_0= ruleIntValue | this_FloatValue_1= ruleFloatValue ) )
            // InternalRequirementDSL.g:3329:2: (this_IntValue_0= ruleIntValue | this_FloatValue_1= ruleFloatValue )
            {
            // InternalRequirementDSL.g:3329:2: (this_IntValue_0= ruleIntValue | this_FloatValue_1= ruleFloatValue )
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==RULE_INT) ) {
                alt75=1;
            }
            else if ( (LA75_0==RULE_FLOAT) ) {
                alt75=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 75, 0, input);

                throw nvae;
            }
            switch (alt75) {
                case 1 :
                    // InternalRequirementDSL.g:3330:3: this_IntValue_0= ruleIntValue
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
                    // InternalRequirementDSL.g:3339:3: this_FloatValue_1= ruleFloatValue
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
    // InternalRequirementDSL.g:3351:1: entryRuleIntValue returns [EObject current=null] : iv_ruleIntValue= ruleIntValue EOF ;
    public final EObject entryRuleIntValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIntValue = null;


        try {
            // InternalRequirementDSL.g:3351:49: (iv_ruleIntValue= ruleIntValue EOF )
            // InternalRequirementDSL.g:3352:2: iv_ruleIntValue= ruleIntValue EOF
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
    // InternalRequirementDSL.g:3358:1: ruleIntValue returns [EObject current=null] : ( ( (lv_value_0_0= RULE_INT ) ) ( (lv_unit_1_0= ruleUnit ) )? ) ;
    public final EObject ruleIntValue() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;
        AntlrDatatypeRuleToken lv_unit_1_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:3364:2: ( ( ( (lv_value_0_0= RULE_INT ) ) ( (lv_unit_1_0= ruleUnit ) )? ) )
            // InternalRequirementDSL.g:3365:2: ( ( (lv_value_0_0= RULE_INT ) ) ( (lv_unit_1_0= ruleUnit ) )? )
            {
            // InternalRequirementDSL.g:3365:2: ( ( (lv_value_0_0= RULE_INT ) ) ( (lv_unit_1_0= ruleUnit ) )? )
            // InternalRequirementDSL.g:3366:3: ( (lv_value_0_0= RULE_INT ) ) ( (lv_unit_1_0= ruleUnit ) )?
            {
            // InternalRequirementDSL.g:3366:3: ( (lv_value_0_0= RULE_INT ) )
            // InternalRequirementDSL.g:3367:4: (lv_value_0_0= RULE_INT )
            {
            // InternalRequirementDSL.g:3367:4: (lv_value_0_0= RULE_INT )
            // InternalRequirementDSL.g:3368:5: lv_value_0_0= RULE_INT
            {
            lv_value_0_0=(Token)match(input,RULE_INT,FOLLOW_39); 

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

            // InternalRequirementDSL.g:3384:3: ( (lv_unit_1_0= ruleUnit ) )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( ((LA76_0>=94 && LA76_0<=116)) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // InternalRequirementDSL.g:3385:4: (lv_unit_1_0= ruleUnit )
                    {
                    // InternalRequirementDSL.g:3385:4: (lv_unit_1_0= ruleUnit )
                    // InternalRequirementDSL.g:3386:5: lv_unit_1_0= ruleUnit
                    {

                    					newCompositeNode(grammarAccess.getIntValueAccess().getUnitUnitParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_2);
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
    // InternalRequirementDSL.g:3407:1: entryRuleFloatValue returns [EObject current=null] : iv_ruleFloatValue= ruleFloatValue EOF ;
    public final EObject entryRuleFloatValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFloatValue = null;


        try {
            // InternalRequirementDSL.g:3407:51: (iv_ruleFloatValue= ruleFloatValue EOF )
            // InternalRequirementDSL.g:3408:2: iv_ruleFloatValue= ruleFloatValue EOF
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
    // InternalRequirementDSL.g:3414:1: ruleFloatValue returns [EObject current=null] : ( ( (lv_value_0_0= RULE_FLOAT ) ) ( (lv_unit_1_0= ruleUnit ) )? ) ;
    public final EObject ruleFloatValue() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;
        AntlrDatatypeRuleToken lv_unit_1_0 = null;



        	enterRule();

        try {
            // InternalRequirementDSL.g:3420:2: ( ( ( (lv_value_0_0= RULE_FLOAT ) ) ( (lv_unit_1_0= ruleUnit ) )? ) )
            // InternalRequirementDSL.g:3421:2: ( ( (lv_value_0_0= RULE_FLOAT ) ) ( (lv_unit_1_0= ruleUnit ) )? )
            {
            // InternalRequirementDSL.g:3421:2: ( ( (lv_value_0_0= RULE_FLOAT ) ) ( (lv_unit_1_0= ruleUnit ) )? )
            // InternalRequirementDSL.g:3422:3: ( (lv_value_0_0= RULE_FLOAT ) ) ( (lv_unit_1_0= ruleUnit ) )?
            {
            // InternalRequirementDSL.g:3422:3: ( (lv_value_0_0= RULE_FLOAT ) )
            // InternalRequirementDSL.g:3423:4: (lv_value_0_0= RULE_FLOAT )
            {
            // InternalRequirementDSL.g:3423:4: (lv_value_0_0= RULE_FLOAT )
            // InternalRequirementDSL.g:3424:5: lv_value_0_0= RULE_FLOAT
            {
            lv_value_0_0=(Token)match(input,RULE_FLOAT,FOLLOW_39); 

            					newLeafNode(lv_value_0_0, grammarAccess.getFloatValueAccess().getValueFLOATTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFloatValueRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_0_0,
            						"de.fraunhofer.isst.stars.RequirementDSL.FLOAT");
            				

            }


            }

            // InternalRequirementDSL.g:3440:3: ( (lv_unit_1_0= ruleUnit ) )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( ((LA77_0>=94 && LA77_0<=116)) ) {
                alt77=1;
            }
            switch (alt77) {
                case 1 :
                    // InternalRequirementDSL.g:3441:4: (lv_unit_1_0= ruleUnit )
                    {
                    // InternalRequirementDSL.g:3441:4: (lv_unit_1_0= ruleUnit )
                    // InternalRequirementDSL.g:3442:5: lv_unit_1_0= ruleUnit
                    {

                    					newCompositeNode(grammarAccess.getFloatValueAccess().getUnitUnitParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_2);
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
    // InternalRequirementDSL.g:3463:1: entryRuleReqID returns [String current=null] : iv_ruleReqID= ruleReqID EOF ;
    public final String entryRuleReqID() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleReqID = null;


        try {
            // InternalRequirementDSL.g:3463:45: (iv_ruleReqID= ruleReqID EOF )
            // InternalRequirementDSL.g:3464:2: iv_ruleReqID= ruleReqID EOF
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
    // InternalRequirementDSL.g:3470:1: ruleReqID returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (this_ID_0= RULE_ID | this_INT_1= RULE_INT ) (kw= '.' | this_INT_3= RULE_INT )* ) ;
    public final AntlrDatatypeRuleToken ruleReqID() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token this_INT_1=null;
        Token kw=null;
        Token this_INT_3=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:3476:2: ( ( (this_ID_0= RULE_ID | this_INT_1= RULE_INT ) (kw= '.' | this_INT_3= RULE_INT )* ) )
            // InternalRequirementDSL.g:3477:2: ( (this_ID_0= RULE_ID | this_INT_1= RULE_INT ) (kw= '.' | this_INT_3= RULE_INT )* )
            {
            // InternalRequirementDSL.g:3477:2: ( (this_ID_0= RULE_ID | this_INT_1= RULE_INT ) (kw= '.' | this_INT_3= RULE_INT )* )
            // InternalRequirementDSL.g:3478:3: (this_ID_0= RULE_ID | this_INT_1= RULE_INT ) (kw= '.' | this_INT_3= RULE_INT )*
            {
            // InternalRequirementDSL.g:3478:3: (this_ID_0= RULE_ID | this_INT_1= RULE_INT )
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==RULE_ID) ) {
                alt78=1;
            }
            else if ( (LA78_0==RULE_INT) ) {
                alt78=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 78, 0, input);

                throw nvae;
            }
            switch (alt78) {
                case 1 :
                    // InternalRequirementDSL.g:3479:4: this_ID_0= RULE_ID
                    {
                    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_40); 

                    				current.merge(this_ID_0);
                    			

                    				newLeafNode(this_ID_0, grammarAccess.getReqIDAccess().getIDTerminalRuleCall_0_0());
                    			

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:3487:4: this_INT_1= RULE_INT
                    {
                    this_INT_1=(Token)match(input,RULE_INT,FOLLOW_40); 

                    				current.merge(this_INT_1);
                    			

                    				newLeafNode(this_INT_1, grammarAccess.getReqIDAccess().getINTTerminalRuleCall_0_1());
                    			

                    }
                    break;

            }

            // InternalRequirementDSL.g:3495:3: (kw= '.' | this_INT_3= RULE_INT )*
            loop79:
            do {
                int alt79=3;
                int LA79_0 = input.LA(1);

                if ( (LA79_0==16) ) {
                    int LA79_2 = input.LA(2);

                    if ( (LA79_2==EOF||LA79_2==RULE_INT||(LA79_2>=15 && LA79_2<=16)) ) {
                        alt79=1;
                    }


                }
                else if ( (LA79_0==RULE_INT) ) {
                    alt79=2;
                }


                switch (alt79) {
            	case 1 :
            	    // InternalRequirementDSL.g:3496:4: kw= '.'
            	    {
            	    kw=(Token)match(input,16,FOLLOW_40); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getReqIDAccess().getFullStopKeyword_1_0());
            	    			

            	    }
            	    break;
            	case 2 :
            	    // InternalRequirementDSL.g:3502:4: this_INT_3= RULE_INT
            	    {
            	    this_INT_3=(Token)match(input,RULE_INT,FOLLOW_40); 

            	    				current.merge(this_INT_3);
            	    			

            	    				newLeafNode(this_INT_3, grammarAccess.getReqIDAccess().getINTTerminalRuleCall_1_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop79;
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
    // InternalRequirementDSL.g:3514:1: entryRuleWORD returns [String current=null] : iv_ruleWORD= ruleWORD EOF ;
    public final String entryRuleWORD() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleWORD = null;


        try {
            // InternalRequirementDSL.g:3514:44: (iv_ruleWORD= ruleWORD EOF )
            // InternalRequirementDSL.g:3515:2: iv_ruleWORD= ruleWORD EOF
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
    // InternalRequirementDSL.g:3521:1: ruleWORD returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID (kw= '-' this_ID_2= RULE_ID )* ) ;
    public final AntlrDatatypeRuleToken ruleWORD() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token kw=null;
        Token this_ID_2=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:3527:2: ( (this_ID_0= RULE_ID (kw= '-' this_ID_2= RULE_ID )* ) )
            // InternalRequirementDSL.g:3528:2: (this_ID_0= RULE_ID (kw= '-' this_ID_2= RULE_ID )* )
            {
            // InternalRequirementDSL.g:3528:2: (this_ID_0= RULE_ID (kw= '-' this_ID_2= RULE_ID )* )
            // InternalRequirementDSL.g:3529:3: this_ID_0= RULE_ID (kw= '-' this_ID_2= RULE_ID )*
            {
            this_ID_0=(Token)match(input,RULE_ID,FOLLOW_41); 

            			current.merge(this_ID_0);
            		

            			newLeafNode(this_ID_0, grammarAccess.getWORDAccess().getIDTerminalRuleCall_0());
            		
            // InternalRequirementDSL.g:3536:3: (kw= '-' this_ID_2= RULE_ID )*
            loop80:
            do {
                int alt80=2;
                int LA80_0 = input.LA(1);

                if ( (LA80_0==25) ) {
                    alt80=1;
                }


                switch (alt80) {
            	case 1 :
            	    // InternalRequirementDSL.g:3537:4: kw= '-' this_ID_2= RULE_ID
            	    {
            	    kw=(Token)match(input,25,FOLLOW_42); 

            	    				current.merge(kw);
            	    				newLeafNode(kw, grammarAccess.getWORDAccess().getHyphenMinusKeyword_1_0());
            	    			
            	    this_ID_2=(Token)match(input,RULE_ID,FOLLOW_41); 

            	    				current.merge(this_ID_2);
            	    			

            	    				newLeafNode(this_ID_2, grammarAccess.getWORDAccess().getIDTerminalRuleCall_1_1());
            	    			

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
    // $ANTLR end "ruleWORD"


    // $ANTLR start "entryRuleAuxiliaryVerb"
    // InternalRequirementDSL.g:3554:1: entryRuleAuxiliaryVerb returns [String current=null] : iv_ruleAuxiliaryVerb= ruleAuxiliaryVerb EOF ;
    public final String entryRuleAuxiliaryVerb() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleAuxiliaryVerb = null;


        try {
            // InternalRequirementDSL.g:3554:53: (iv_ruleAuxiliaryVerb= ruleAuxiliaryVerb EOF )
            // InternalRequirementDSL.g:3555:2: iv_ruleAuxiliaryVerb= ruleAuxiliaryVerb EOF
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
    // InternalRequirementDSL.g:3561:1: ruleAuxiliaryVerb returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'is' | kw= 'be' | kw= 'been' | kw= 'has' | kw= 'do' | kw= 'does' ) ;
    public final AntlrDatatypeRuleToken ruleAuxiliaryVerb() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:3567:2: ( (kw= 'is' | kw= 'be' | kw= 'been' | kw= 'has' | kw= 'do' | kw= 'does' ) )
            // InternalRequirementDSL.g:3568:2: (kw= 'is' | kw= 'be' | kw= 'been' | kw= 'has' | kw= 'do' | kw= 'does' )
            {
            // InternalRequirementDSL.g:3568:2: (kw= 'is' | kw= 'be' | kw= 'been' | kw= 'has' | kw= 'do' | kw= 'does' )
            int alt81=6;
            switch ( input.LA(1) ) {
            case 26:
                {
                alt81=1;
                }
                break;
            case 27:
                {
                alt81=2;
                }
                break;
            case 28:
                {
                alt81=3;
                }
                break;
            case 29:
                {
                alt81=4;
                }
                break;
            case 30:
                {
                alt81=5;
                }
                break;
            case 31:
                {
                alt81=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 81, 0, input);

                throw nvae;
            }

            switch (alt81) {
                case 1 :
                    // InternalRequirementDSL.g:3569:3: kw= 'is'
                    {
                    kw=(Token)match(input,26,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getAuxiliaryVerbAccess().getIsKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:3575:3: kw= 'be'
                    {
                    kw=(Token)match(input,27,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getAuxiliaryVerbAccess().getBeKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:3581:3: kw= 'been'
                    {
                    kw=(Token)match(input,28,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getAuxiliaryVerbAccess().getBeenKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:3587:3: kw= 'has'
                    {
                    kw=(Token)match(input,29,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getAuxiliaryVerbAccess().getHasKeyword_3());
                    		

                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:3593:3: kw= 'do'
                    {
                    kw=(Token)match(input,30,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getAuxiliaryVerbAccess().getDoKeyword_4());
                    		

                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:3599:3: kw= 'does'
                    {
                    kw=(Token)match(input,31,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getAuxiliaryVerbAccess().getDoesKeyword_5());
                    		

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
    // InternalRequirementDSL.g:3608:1: entryRuleConjunction returns [String current=null] : iv_ruleConjunction= ruleConjunction EOF ;
    public final String entryRuleConjunction() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleConjunction = null;


        try {
            // InternalRequirementDSL.g:3608:51: (iv_ruleConjunction= ruleConjunction EOF )
            // InternalRequirementDSL.g:3609:2: iv_ruleConjunction= ruleConjunction EOF
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
    // InternalRequirementDSL.g:3615:1: ruleConjunction returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'and' | kw= 'or' ) ;
    public final AntlrDatatypeRuleToken ruleConjunction() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:3621:2: ( (kw= 'and' | kw= 'or' ) )
            // InternalRequirementDSL.g:3622:2: (kw= 'and' | kw= 'or' )
            {
            // InternalRequirementDSL.g:3622:2: (kw= 'and' | kw= 'or' )
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==32) ) {
                alt82=1;
            }
            else if ( (LA82_0==33) ) {
                alt82=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 82, 0, input);

                throw nvae;
            }
            switch (alt82) {
                case 1 :
                    // InternalRequirementDSL.g:3623:3: kw= 'and'
                    {
                    kw=(Token)match(input,32,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getConjunctionAccess().getAndKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:3629:3: kw= 'or'
                    {
                    kw=(Token)match(input,33,FOLLOW_2); 

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


    // $ANTLR start "entryRuleComperators"
    // InternalRequirementDSL.g:3638:1: entryRuleComperators returns [String current=null] : iv_ruleComperators= ruleComperators EOF ;
    public final String entryRuleComperators() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleComperators = null;


        try {
            // InternalRequirementDSL.g:3638:51: (iv_ruleComperators= ruleComperators EOF )
            // InternalRequirementDSL.g:3639:2: iv_ruleComperators= ruleComperators EOF
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
    // InternalRequirementDSL.g:3645:1: ruleComperators returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'than' | kw= 'as' | kw= 'to' | kw= 'of' ) ;
    public final AntlrDatatypeRuleToken ruleComperators() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:3651:2: ( (kw= 'than' | kw= 'as' | kw= 'to' | kw= 'of' ) )
            // InternalRequirementDSL.g:3652:2: (kw= 'than' | kw= 'as' | kw= 'to' | kw= 'of' )
            {
            // InternalRequirementDSL.g:3652:2: (kw= 'than' | kw= 'as' | kw= 'to' | kw= 'of' )
            int alt83=4;
            switch ( input.LA(1) ) {
            case 34:
                {
                alt83=1;
                }
                break;
            case 35:
                {
                alt83=2;
                }
                break;
            case 36:
                {
                alt83=3;
                }
                break;
            case 37:
                {
                alt83=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 83, 0, input);

                throw nvae;
            }

            switch (alt83) {
                case 1 :
                    // InternalRequirementDSL.g:3653:3: kw= 'than'
                    {
                    kw=(Token)match(input,34,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getComperatorsAccess().getThanKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:3659:3: kw= 'as'
                    {
                    kw=(Token)match(input,35,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getComperatorsAccess().getAsKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:3665:3: kw= 'to'
                    {
                    kw=(Token)match(input,36,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getComperatorsAccess().getToKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:3671:3: kw= 'of'
                    {
                    kw=(Token)match(input,37,FOLLOW_2); 

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
    // InternalRequirementDSL.g:3680:1: entryRuleSizeAdverbial returns [String current=null] : iv_ruleSizeAdverbial= ruleSizeAdverbial EOF ;
    public final String entryRuleSizeAdverbial() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleSizeAdverbial = null;


        try {
            // InternalRequirementDSL.g:3680:53: (iv_ruleSizeAdverbial= ruleSizeAdverbial EOF )
            // InternalRequirementDSL.g:3681:2: iv_ruleSizeAdverbial= ruleSizeAdverbial EOF
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
    // InternalRequirementDSL.g:3687:1: ruleSizeAdverbial returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'higher' | kw= 'less' | kw= 'more' | kw= 'larger' | kw= 'smaller' | kw= 'as_long_as' ) ;
    public final AntlrDatatypeRuleToken ruleSizeAdverbial() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:3693:2: ( (kw= 'higher' | kw= 'less' | kw= 'more' | kw= 'larger' | kw= 'smaller' | kw= 'as_long_as' ) )
            // InternalRequirementDSL.g:3694:2: (kw= 'higher' | kw= 'less' | kw= 'more' | kw= 'larger' | kw= 'smaller' | kw= 'as_long_as' )
            {
            // InternalRequirementDSL.g:3694:2: (kw= 'higher' | kw= 'less' | kw= 'more' | kw= 'larger' | kw= 'smaller' | kw= 'as_long_as' )
            int alt84=6;
            switch ( input.LA(1) ) {
            case 38:
                {
                alt84=1;
                }
                break;
            case 39:
                {
                alt84=2;
                }
                break;
            case 40:
                {
                alt84=3;
                }
                break;
            case 41:
                {
                alt84=4;
                }
                break;
            case 42:
                {
                alt84=5;
                }
                break;
            case 43:
                {
                alt84=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 84, 0, input);

                throw nvae;
            }

            switch (alt84) {
                case 1 :
                    // InternalRequirementDSL.g:3695:3: kw= 'higher'
                    {
                    kw=(Token)match(input,38,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getSizeAdverbialAccess().getHigherKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:3701:3: kw= 'less'
                    {
                    kw=(Token)match(input,39,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getSizeAdverbialAccess().getLessKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:3707:3: kw= 'more'
                    {
                    kw=(Token)match(input,40,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getSizeAdverbialAccess().getMoreKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:3713:3: kw= 'larger'
                    {
                    kw=(Token)match(input,41,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getSizeAdverbialAccess().getLargerKeyword_3());
                    		

                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:3719:3: kw= 'smaller'
                    {
                    kw=(Token)match(input,42,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getSizeAdverbialAccess().getSmallerKeyword_4());
                    		

                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:3725:3: kw= 'as_long_as'
                    {
                    kw=(Token)match(input,43,FOLLOW_2); 

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
    // InternalRequirementDSL.g:3734:1: entryRulePositionAdverbial returns [String current=null] : iv_rulePositionAdverbial= rulePositionAdverbial EOF ;
    public final String entryRulePositionAdverbial() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePositionAdverbial = null;


        try {
            // InternalRequirementDSL.g:3734:57: (iv_rulePositionAdverbial= rulePositionAdverbial EOF )
            // InternalRequirementDSL.g:3735:2: iv_rulePositionAdverbial= rulePositionAdverbial EOF
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
    // InternalRequirementDSL.g:3741:1: rulePositionAdverbial returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'between' | kw= 'next' | kw= 'on' | kw= 'above' | kw= 'below' | kw= 'in' | kw= 'within' | kw= 'in_front_of' | kw= 'behind' | kw= 'out' | kw= 'under' ) ;
    public final AntlrDatatypeRuleToken rulePositionAdverbial() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:3747:2: ( (kw= 'between' | kw= 'next' | kw= 'on' | kw= 'above' | kw= 'below' | kw= 'in' | kw= 'within' | kw= 'in_front_of' | kw= 'behind' | kw= 'out' | kw= 'under' ) )
            // InternalRequirementDSL.g:3748:2: (kw= 'between' | kw= 'next' | kw= 'on' | kw= 'above' | kw= 'below' | kw= 'in' | kw= 'within' | kw= 'in_front_of' | kw= 'behind' | kw= 'out' | kw= 'under' )
            {
            // InternalRequirementDSL.g:3748:2: (kw= 'between' | kw= 'next' | kw= 'on' | kw= 'above' | kw= 'below' | kw= 'in' | kw= 'within' | kw= 'in_front_of' | kw= 'behind' | kw= 'out' | kw= 'under' )
            int alt85=11;
            switch ( input.LA(1) ) {
            case 44:
                {
                alt85=1;
                }
                break;
            case 45:
                {
                alt85=2;
                }
                break;
            case 46:
                {
                alt85=3;
                }
                break;
            case 47:
                {
                alt85=4;
                }
                break;
            case 48:
                {
                alt85=5;
                }
                break;
            case 49:
                {
                alt85=6;
                }
                break;
            case 50:
                {
                alt85=7;
                }
                break;
            case 51:
                {
                alt85=8;
                }
                break;
            case 52:
                {
                alt85=9;
                }
                break;
            case 53:
                {
                alt85=10;
                }
                break;
            case 54:
                {
                alt85=11;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 85, 0, input);

                throw nvae;
            }

            switch (alt85) {
                case 1 :
                    // InternalRequirementDSL.g:3749:3: kw= 'between'
                    {
                    kw=(Token)match(input,44,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAdverbialAccess().getBetweenKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:3755:3: kw= 'next'
                    {
                    kw=(Token)match(input,45,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAdverbialAccess().getNextKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:3761:3: kw= 'on'
                    {
                    kw=(Token)match(input,46,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAdverbialAccess().getOnKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:3767:3: kw= 'above'
                    {
                    kw=(Token)match(input,47,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAdverbialAccess().getAboveKeyword_3());
                    		

                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:3773:3: kw= 'below'
                    {
                    kw=(Token)match(input,48,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAdverbialAccess().getBelowKeyword_4());
                    		

                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:3779:3: kw= 'in'
                    {
                    kw=(Token)match(input,49,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAdverbialAccess().getInKeyword_5());
                    		

                    }
                    break;
                case 7 :
                    // InternalRequirementDSL.g:3785:3: kw= 'within'
                    {
                    kw=(Token)match(input,50,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAdverbialAccess().getWithinKeyword_6());
                    		

                    }
                    break;
                case 8 :
                    // InternalRequirementDSL.g:3791:3: kw= 'in_front_of'
                    {
                    kw=(Token)match(input,51,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAdverbialAccess().getIn_front_ofKeyword_7());
                    		

                    }
                    break;
                case 9 :
                    // InternalRequirementDSL.g:3797:3: kw= 'behind'
                    {
                    kw=(Token)match(input,52,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAdverbialAccess().getBehindKeyword_8());
                    		

                    }
                    break;
                case 10 :
                    // InternalRequirementDSL.g:3803:3: kw= 'out'
                    {
                    kw=(Token)match(input,53,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPositionAdverbialAccess().getOutKeyword_9());
                    		

                    }
                    break;
                case 11 :
                    // InternalRequirementDSL.g:3809:3: kw= 'under'
                    {
                    kw=(Token)match(input,54,FOLLOW_2); 

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
    // InternalRequirementDSL.g:3818:1: entryRuleComparisonAdverbial returns [String current=null] : iv_ruleComparisonAdverbial= ruleComparisonAdverbial EOF ;
    public final String entryRuleComparisonAdverbial() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleComparisonAdverbial = null;


        try {
            // InternalRequirementDSL.g:3818:59: (iv_ruleComparisonAdverbial= ruleComparisonAdverbial EOF )
            // InternalRequirementDSL.g:3819:2: iv_ruleComparisonAdverbial= ruleComparisonAdverbial EOF
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
    // InternalRequirementDSL.g:3825:1: ruleComparisonAdverbial returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'equal' | kw= 'faster' | kw= 'slower' | kw= 'better' | kw= 'by' | kw= 'to' ) ;
    public final AntlrDatatypeRuleToken ruleComparisonAdverbial() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:3831:2: ( (kw= 'equal' | kw= 'faster' | kw= 'slower' | kw= 'better' | kw= 'by' | kw= 'to' ) )
            // InternalRequirementDSL.g:3832:2: (kw= 'equal' | kw= 'faster' | kw= 'slower' | kw= 'better' | kw= 'by' | kw= 'to' )
            {
            // InternalRequirementDSL.g:3832:2: (kw= 'equal' | kw= 'faster' | kw= 'slower' | kw= 'better' | kw= 'by' | kw= 'to' )
            int alt86=6;
            switch ( input.LA(1) ) {
            case 55:
                {
                alt86=1;
                }
                break;
            case 56:
                {
                alt86=2;
                }
                break;
            case 57:
                {
                alt86=3;
                }
                break;
            case 58:
                {
                alt86=4;
                }
                break;
            case 59:
                {
                alt86=5;
                }
                break;
            case 36:
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
                    // InternalRequirementDSL.g:3833:3: kw= 'equal'
                    {
                    kw=(Token)match(input,55,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getComparisonAdverbialAccess().getEqualKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:3839:3: kw= 'faster'
                    {
                    kw=(Token)match(input,56,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getComparisonAdverbialAccess().getFasterKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:3845:3: kw= 'slower'
                    {
                    kw=(Token)match(input,57,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getComparisonAdverbialAccess().getSlowerKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:3851:3: kw= 'better'
                    {
                    kw=(Token)match(input,58,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getComparisonAdverbialAccess().getBetterKeyword_3());
                    		

                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:3857:3: kw= 'by'
                    {
                    kw=(Token)match(input,59,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getComparisonAdverbialAccess().getByKeyword_4());
                    		

                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:3863:3: kw= 'to'
                    {
                    kw=(Token)match(input,36,FOLLOW_2); 

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
    // InternalRequirementDSL.g:3872:1: entryRuleQuantification returns [String current=null] : iv_ruleQuantification= ruleQuantification EOF ;
    public final String entryRuleQuantification() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleQuantification = null;


        try {
            // InternalRequirementDSL.g:3872:54: (iv_ruleQuantification= ruleQuantification EOF )
            // InternalRequirementDSL.g:3873:2: iv_ruleQuantification= ruleQuantification EOF
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
    // InternalRequirementDSL.g:3879:1: ruleQuantification returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'all' | kw= 'every' | kw= 'each' | kw= 'whole' | kw= 'any' | kw= 'several' | (kw= 'either' kw= 'All' ) | kw= 'Every' | kw= 'Each' | kw= 'Whole' | kw= 'Any' | kw= 'Several' | kw= 'Either' ) ;
    public final AntlrDatatypeRuleToken ruleQuantification() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:3885:2: ( (kw= 'all' | kw= 'every' | kw= 'each' | kw= 'whole' | kw= 'any' | kw= 'several' | (kw= 'either' kw= 'All' ) | kw= 'Every' | kw= 'Each' | kw= 'Whole' | kw= 'Any' | kw= 'Several' | kw= 'Either' ) )
            // InternalRequirementDSL.g:3886:2: (kw= 'all' | kw= 'every' | kw= 'each' | kw= 'whole' | kw= 'any' | kw= 'several' | (kw= 'either' kw= 'All' ) | kw= 'Every' | kw= 'Each' | kw= 'Whole' | kw= 'Any' | kw= 'Several' | kw= 'Either' )
            {
            // InternalRequirementDSL.g:3886:2: (kw= 'all' | kw= 'every' | kw= 'each' | kw= 'whole' | kw= 'any' | kw= 'several' | (kw= 'either' kw= 'All' ) | kw= 'Every' | kw= 'Each' | kw= 'Whole' | kw= 'Any' | kw= 'Several' | kw= 'Either' )
            int alt87=13;
            switch ( input.LA(1) ) {
            case 60:
                {
                alt87=1;
                }
                break;
            case 61:
                {
                alt87=2;
                }
                break;
            case 62:
                {
                alt87=3;
                }
                break;
            case 63:
                {
                alt87=4;
                }
                break;
            case 64:
                {
                alt87=5;
                }
                break;
            case 65:
                {
                alt87=6;
                }
                break;
            case 66:
                {
                alt87=7;
                }
                break;
            case 68:
                {
                alt87=8;
                }
                break;
            case 69:
                {
                alt87=9;
                }
                break;
            case 70:
                {
                alt87=10;
                }
                break;
            case 71:
                {
                alt87=11;
                }
                break;
            case 72:
                {
                alt87=12;
                }
                break;
            case 73:
                {
                alt87=13;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 87, 0, input);

                throw nvae;
            }

            switch (alt87) {
                case 1 :
                    // InternalRequirementDSL.g:3887:3: kw= 'all'
                    {
                    kw=(Token)match(input,60,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getAllKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:3893:3: kw= 'every'
                    {
                    kw=(Token)match(input,61,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getEveryKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:3899:3: kw= 'each'
                    {
                    kw=(Token)match(input,62,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getEachKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:3905:3: kw= 'whole'
                    {
                    kw=(Token)match(input,63,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getWholeKeyword_3());
                    		

                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:3911:3: kw= 'any'
                    {
                    kw=(Token)match(input,64,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getAnyKeyword_4());
                    		

                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:3917:3: kw= 'several'
                    {
                    kw=(Token)match(input,65,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getSeveralKeyword_5());
                    		

                    }
                    break;
                case 7 :
                    // InternalRequirementDSL.g:3923:3: (kw= 'either' kw= 'All' )
                    {
                    // InternalRequirementDSL.g:3923:3: (kw= 'either' kw= 'All' )
                    // InternalRequirementDSL.g:3924:4: kw= 'either' kw= 'All'
                    {
                    kw=(Token)match(input,66,FOLLOW_43); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getQuantificationAccess().getEitherKeyword_6_0());
                    			
                    kw=(Token)match(input,67,FOLLOW_2); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getQuantificationAccess().getAllKeyword_6_1());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalRequirementDSL.g:3936:3: kw= 'Every'
                    {
                    kw=(Token)match(input,68,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getEveryKeyword_7());
                    		

                    }
                    break;
                case 9 :
                    // InternalRequirementDSL.g:3942:3: kw= 'Each'
                    {
                    kw=(Token)match(input,69,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getEachKeyword_8());
                    		

                    }
                    break;
                case 10 :
                    // InternalRequirementDSL.g:3948:3: kw= 'Whole'
                    {
                    kw=(Token)match(input,70,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getWholeKeyword_9());
                    		

                    }
                    break;
                case 11 :
                    // InternalRequirementDSL.g:3954:3: kw= 'Any'
                    {
                    kw=(Token)match(input,71,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getAnyKeyword_10());
                    		

                    }
                    break;
                case 12 :
                    // InternalRequirementDSL.g:3960:3: kw= 'Several'
                    {
                    kw=(Token)match(input,72,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getSeveralKeyword_11());
                    		

                    }
                    break;
                case 13 :
                    // InternalRequirementDSL.g:3966:3: kw= 'Either'
                    {
                    kw=(Token)match(input,73,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getQuantificationAccess().getEitherKeyword_12());
                    		

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
    // InternalRequirementDSL.g:3975:1: entryRuleNegation returns [String current=null] : iv_ruleNegation= ruleNegation EOF ;
    public final String entryRuleNegation() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNegation = null;


        try {
            // InternalRequirementDSL.g:3975:48: (iv_ruleNegation= ruleNegation EOF )
            // InternalRequirementDSL.g:3976:2: iv_ruleNegation= ruleNegation EOF
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
    // InternalRequirementDSL.g:3982:1: ruleNegation returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'not' | kw= 'donot' | kw= 'doesnot' | kw= 'doesn\\'t' | kw= 'don\\'t' ) ;
    public final AntlrDatatypeRuleToken ruleNegation() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:3988:2: ( (kw= 'not' | kw= 'donot' | kw= 'doesnot' | kw= 'doesn\\'t' | kw= 'don\\'t' ) )
            // InternalRequirementDSL.g:3989:2: (kw= 'not' | kw= 'donot' | kw= 'doesnot' | kw= 'doesn\\'t' | kw= 'don\\'t' )
            {
            // InternalRequirementDSL.g:3989:2: (kw= 'not' | kw= 'donot' | kw= 'doesnot' | kw= 'doesn\\'t' | kw= 'don\\'t' )
            int alt88=5;
            switch ( input.LA(1) ) {
            case 74:
                {
                alt88=1;
                }
                break;
            case 75:
                {
                alt88=2;
                }
                break;
            case 76:
                {
                alt88=3;
                }
                break;
            case 77:
                {
                alt88=4;
                }
                break;
            case 78:
                {
                alt88=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 88, 0, input);

                throw nvae;
            }

            switch (alt88) {
                case 1 :
                    // InternalRequirementDSL.g:3990:3: kw= 'not'
                    {
                    kw=(Token)match(input,74,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getNegationAccess().getNotKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:3996:3: kw= 'donot'
                    {
                    kw=(Token)match(input,75,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getNegationAccess().getDonotKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4002:3: kw= 'doesnot'
                    {
                    kw=(Token)match(input,76,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getNegationAccess().getDoesnotKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4008:3: kw= 'doesn\\'t'
                    {
                    kw=(Token)match(input,77,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getNegationAccess().getDoesnTKeyword_3());
                    		

                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:4014:3: kw= 'don\\'t'
                    {
                    kw=(Token)match(input,78,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getNegationAccess().getDonTKeyword_4());
                    		

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
    // $ANTLR end "ruleNegation"


    // $ANTLR start "entryRuleArticles"
    // InternalRequirementDSL.g:4023:1: entryRuleArticles returns [String current=null] : iv_ruleArticles= ruleArticles EOF ;
    public final String entryRuleArticles() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleArticles = null;


        try {
            // InternalRequirementDSL.g:4023:48: (iv_ruleArticles= ruleArticles EOF )
            // InternalRequirementDSL.g:4024:2: iv_ruleArticles= ruleArticles EOF
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
    // InternalRequirementDSL.g:4030:1: ruleArticles returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'the' | kw= 'a' | kw= 'an' | kw= 'The' | kw= 'A' | kw= 'An' ) ;
    public final AntlrDatatypeRuleToken ruleArticles() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4036:2: ( (kw= 'the' | kw= 'a' | kw= 'an' | kw= 'The' | kw= 'A' | kw= 'An' ) )
            // InternalRequirementDSL.g:4037:2: (kw= 'the' | kw= 'a' | kw= 'an' | kw= 'The' | kw= 'A' | kw= 'An' )
            {
            // InternalRequirementDSL.g:4037:2: (kw= 'the' | kw= 'a' | kw= 'an' | kw= 'The' | kw= 'A' | kw= 'An' )
            int alt89=6;
            switch ( input.LA(1) ) {
            case 79:
                {
                alt89=1;
                }
                break;
            case 80:
                {
                alt89=2;
                }
                break;
            case 81:
                {
                alt89=3;
                }
                break;
            case 82:
                {
                alt89=4;
                }
                break;
            case 83:
                {
                alt89=5;
                }
                break;
            case 84:
                {
                alt89=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 89, 0, input);

                throw nvae;
            }

            switch (alt89) {
                case 1 :
                    // InternalRequirementDSL.g:4038:3: kw= 'the'
                    {
                    kw=(Token)match(input,79,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getArticlesAccess().getTheKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4044:3: kw= 'a'
                    {
                    kw=(Token)match(input,80,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getArticlesAccess().getAKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4050:3: kw= 'an'
                    {
                    kw=(Token)match(input,81,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getArticlesAccess().getAnKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4056:3: kw= 'The'
                    {
                    kw=(Token)match(input,82,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getArticlesAccess().getTheKeyword_3());
                    		

                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:4062:3: kw= 'A'
                    {
                    kw=(Token)match(input,83,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getArticlesAccess().getAKeyword_4());
                    		

                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:4068:3: kw= 'An'
                    {
                    kw=(Token)match(input,84,FOLLOW_2); 

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
    // InternalRequirementDSL.g:4077:1: entryRuleRefArticles returns [String current=null] : iv_ruleRefArticles= ruleRefArticles EOF ;
    public final String entryRuleRefArticles() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleRefArticles = null;


        try {
            // InternalRequirementDSL.g:4077:51: (iv_ruleRefArticles= ruleRefArticles EOF )
            // InternalRequirementDSL.g:4078:2: iv_ruleRefArticles= ruleRefArticles EOF
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
    // InternalRequirementDSL.g:4084:1: ruleRefArticles returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'that' | kw= 'this' | kw= 'That' | kw= 'This' ) ;
    public final AntlrDatatypeRuleToken ruleRefArticles() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4090:2: ( (kw= 'that' | kw= 'this' | kw= 'That' | kw= 'This' ) )
            // InternalRequirementDSL.g:4091:2: (kw= 'that' | kw= 'this' | kw= 'That' | kw= 'This' )
            {
            // InternalRequirementDSL.g:4091:2: (kw= 'that' | kw= 'this' | kw= 'That' | kw= 'This' )
            int alt90=4;
            switch ( input.LA(1) ) {
            case 85:
                {
                alt90=1;
                }
                break;
            case 86:
                {
                alt90=2;
                }
                break;
            case 87:
                {
                alt90=3;
                }
                break;
            case 88:
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
                    // InternalRequirementDSL.g:4092:3: kw= 'that'
                    {
                    kw=(Token)match(input,85,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRefArticlesAccess().getThatKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4098:3: kw= 'this'
                    {
                    kw=(Token)match(input,86,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRefArticlesAccess().getThisKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4104:3: kw= 'That'
                    {
                    kw=(Token)match(input,87,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRefArticlesAccess().getThatKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4110:3: kw= 'This'
                    {
                    kw=(Token)match(input,88,FOLLOW_2); 

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
    // InternalRequirementDSL.g:4119:1: entryRuleStuffWord returns [String current=null] : iv_ruleStuffWord= ruleStuffWord EOF ;
    public final String entryRuleStuffWord() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleStuffWord = null;


        try {
            // InternalRequirementDSL.g:4119:49: (iv_ruleStuffWord= ruleStuffWord EOF )
            // InternalRequirementDSL.g:4120:2: iv_ruleStuffWord= ruleStuffWord EOF
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
    // InternalRequirementDSL.g:4126:1: ruleStuffWord returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= 'with' ;
    public final AntlrDatatypeRuleToken ruleStuffWord() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4132:2: (kw= 'with' )
            // InternalRequirementDSL.g:4133:2: kw= 'with'
            {
            kw=(Token)match(input,89,FOLLOW_2); 

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
    // InternalRequirementDSL.g:4141:1: entryRuleRelativePronounsSubject returns [String current=null] : iv_ruleRelativePronounsSubject= ruleRelativePronounsSubject EOF ;
    public final String entryRuleRelativePronounsSubject() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleRelativePronounsSubject = null;


        try {
            // InternalRequirementDSL.g:4141:63: (iv_ruleRelativePronounsSubject= ruleRelativePronounsSubject EOF )
            // InternalRequirementDSL.g:4142:2: iv_ruleRelativePronounsSubject= ruleRelativePronounsSubject EOF
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
    // InternalRequirementDSL.g:4148:1: ruleRelativePronounsSubject returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'which' | kw= 'who' | kw= 'that' ) ;
    public final AntlrDatatypeRuleToken ruleRelativePronounsSubject() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4154:2: ( (kw= 'which' | kw= 'who' | kw= 'that' ) )
            // InternalRequirementDSL.g:4155:2: (kw= 'which' | kw= 'who' | kw= 'that' )
            {
            // InternalRequirementDSL.g:4155:2: (kw= 'which' | kw= 'who' | kw= 'that' )
            int alt91=3;
            switch ( input.LA(1) ) {
            case 90:
                {
                alt91=1;
                }
                break;
            case 91:
                {
                alt91=2;
                }
                break;
            case 85:
                {
                alt91=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 91, 0, input);

                throw nvae;
            }

            switch (alt91) {
                case 1 :
                    // InternalRequirementDSL.g:4156:3: kw= 'which'
                    {
                    kw=(Token)match(input,90,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRelativePronounsSubjectAccess().getWhichKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4162:3: kw= 'who'
                    {
                    kw=(Token)match(input,91,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRelativePronounsSubjectAccess().getWhoKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4168:3: kw= 'that'
                    {
                    kw=(Token)match(input,85,FOLLOW_2); 

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
    // InternalRequirementDSL.g:4177:1: entryRuleRelativePronounsObject returns [String current=null] : iv_ruleRelativePronounsObject= ruleRelativePronounsObject EOF ;
    public final String entryRuleRelativePronounsObject() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleRelativePronounsObject = null;


        try {
            // InternalRequirementDSL.g:4177:62: (iv_ruleRelativePronounsObject= ruleRelativePronounsObject EOF )
            // InternalRequirementDSL.g:4178:2: iv_ruleRelativePronounsObject= ruleRelativePronounsObject EOF
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
    // InternalRequirementDSL.g:4184:1: ruleRelativePronounsObject returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'whose' | kw= 'whom' ) ;
    public final AntlrDatatypeRuleToken ruleRelativePronounsObject() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4190:2: ( (kw= 'whose' | kw= 'whom' ) )
            // InternalRequirementDSL.g:4191:2: (kw= 'whose' | kw= 'whom' )
            {
            // InternalRequirementDSL.g:4191:2: (kw= 'whose' | kw= 'whom' )
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==92) ) {
                alt92=1;
            }
            else if ( (LA92_0==93) ) {
                alt92=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 92, 0, input);

                throw nvae;
            }
            switch (alt92) {
                case 1 :
                    // InternalRequirementDSL.g:4192:3: kw= 'whose'
                    {
                    kw=(Token)match(input,92,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRelativePronounsObjectAccess().getWhoseKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4198:3: kw= 'whom'
                    {
                    kw=(Token)match(input,93,FOLLOW_2); 

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


    // $ANTLR start "entryRuleUnit"
    // InternalRequirementDSL.g:4207:1: entryRuleUnit returns [String current=null] : iv_ruleUnit= ruleUnit EOF ;
    public final String entryRuleUnit() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleUnit = null;


        try {
            // InternalRequirementDSL.g:4207:44: (iv_ruleUnit= ruleUnit EOF )
            // InternalRequirementDSL.g:4208:2: iv_ruleUnit= ruleUnit EOF
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
    // InternalRequirementDSL.g:4214:1: ruleUnit returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_LengthUnits_0= ruleLengthUnits | this_PresureUnits_1= rulePresureUnits | this_HeatUnits_2= ruleHeatUnits | this_MassUnits_3= ruleMassUnits | this_VelcoityUnits_4= ruleVelcoityUnits | this_Cuvature_5= ruleCuvature ) ;
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
            // InternalRequirementDSL.g:4220:2: ( (this_LengthUnits_0= ruleLengthUnits | this_PresureUnits_1= rulePresureUnits | this_HeatUnits_2= ruleHeatUnits | this_MassUnits_3= ruleMassUnits | this_VelcoityUnits_4= ruleVelcoityUnits | this_Cuvature_5= ruleCuvature ) )
            // InternalRequirementDSL.g:4221:2: (this_LengthUnits_0= ruleLengthUnits | this_PresureUnits_1= rulePresureUnits | this_HeatUnits_2= ruleHeatUnits | this_MassUnits_3= ruleMassUnits | this_VelcoityUnits_4= ruleVelcoityUnits | this_Cuvature_5= ruleCuvature )
            {
            // InternalRequirementDSL.g:4221:2: (this_LengthUnits_0= ruleLengthUnits | this_PresureUnits_1= rulePresureUnits | this_HeatUnits_2= ruleHeatUnits | this_MassUnits_3= ruleMassUnits | this_VelcoityUnits_4= ruleVelcoityUnits | this_Cuvature_5= ruleCuvature )
            int alt93=6;
            switch ( input.LA(1) ) {
            case 111:
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
                {
                alt93=1;
                }
                break;
            case 108:
            case 109:
            case 110:
                {
                alt93=2;
                }
                break;
            case 106:
            case 107:
                {
                alt93=3;
                }
                break;
            case 102:
            case 103:
            case 104:
            case 105:
                {
                alt93=4;
                }
                break;
            case 98:
            case 99:
            case 100:
            case 101:
                {
                alt93=5;
                }
                break;
            case 94:
            case 95:
            case 96:
            case 97:
                {
                alt93=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 93, 0, input);

                throw nvae;
            }

            switch (alt93) {
                case 1 :
                    // InternalRequirementDSL.g:4222:3: this_LengthUnits_0= ruleLengthUnits
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
                    // InternalRequirementDSL.g:4233:3: this_PresureUnits_1= rulePresureUnits
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
                    // InternalRequirementDSL.g:4244:3: this_HeatUnits_2= ruleHeatUnits
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
                    // InternalRequirementDSL.g:4255:3: this_MassUnits_3= ruleMassUnits
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
                    // InternalRequirementDSL.g:4266:3: this_VelcoityUnits_4= ruleVelcoityUnits
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
                    // InternalRequirementDSL.g:4277:3: this_Cuvature_5= ruleCuvature
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
    // InternalRequirementDSL.g:4291:1: entryRuleCuvature returns [String current=null] : iv_ruleCuvature= ruleCuvature EOF ;
    public final String entryRuleCuvature() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCuvature = null;


        try {
            // InternalRequirementDSL.g:4291:48: (iv_ruleCuvature= ruleCuvature EOF )
            // InternalRequirementDSL.g:4292:2: iv_ruleCuvature= ruleCuvature EOF
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
    // InternalRequirementDSL.g:4298:1: ruleCuvature returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'rad/m' | kw= '\\u00B0' | kw= 'rad' | kw= '\\u00B0/m' ) ;
    public final AntlrDatatypeRuleToken ruleCuvature() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4304:2: ( (kw= 'rad/m' | kw= '\\u00B0' | kw= 'rad' | kw= '\\u00B0/m' ) )
            // InternalRequirementDSL.g:4305:2: (kw= 'rad/m' | kw= '\\u00B0' | kw= 'rad' | kw= '\\u00B0/m' )
            {
            // InternalRequirementDSL.g:4305:2: (kw= 'rad/m' | kw= '\\u00B0' | kw= 'rad' | kw= '\\u00B0/m' )
            int alt94=4;
            switch ( input.LA(1) ) {
            case 94:
                {
                alt94=1;
                }
                break;
            case 95:
                {
                alt94=2;
                }
                break;
            case 96:
                {
                alt94=3;
                }
                break;
            case 97:
                {
                alt94=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 94, 0, input);

                throw nvae;
            }

            switch (alt94) {
                case 1 :
                    // InternalRequirementDSL.g:4306:3: kw= 'rad/m'
                    {
                    kw=(Token)match(input,94,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getCuvatureAccess().getRadMKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4312:3: kw= '\\u00B0'
                    {
                    kw=(Token)match(input,95,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getCuvatureAccess().getDegreeSignKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4318:3: kw= 'rad'
                    {
                    kw=(Token)match(input,96,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getCuvatureAccess().getRadKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4324:3: kw= '\\u00B0/m'
                    {
                    kw=(Token)match(input,97,FOLLOW_2); 

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
    // InternalRequirementDSL.g:4333:1: entryRuleVelcoityUnits returns [String current=null] : iv_ruleVelcoityUnits= ruleVelcoityUnits EOF ;
    public final String entryRuleVelcoityUnits() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleVelcoityUnits = null;


        try {
            // InternalRequirementDSL.g:4333:53: (iv_ruleVelcoityUnits= ruleVelcoityUnits EOF )
            // InternalRequirementDSL.g:4334:2: iv_ruleVelcoityUnits= ruleVelcoityUnits EOF
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
    // InternalRequirementDSL.g:4340:1: ruleVelcoityUnits returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'm/s' | kw= 'knots' | kw= 'km/h' | kw= 'm/min' ) ;
    public final AntlrDatatypeRuleToken ruleVelcoityUnits() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4346:2: ( (kw= 'm/s' | kw= 'knots' | kw= 'km/h' | kw= 'm/min' ) )
            // InternalRequirementDSL.g:4347:2: (kw= 'm/s' | kw= 'knots' | kw= 'km/h' | kw= 'm/min' )
            {
            // InternalRequirementDSL.g:4347:2: (kw= 'm/s' | kw= 'knots' | kw= 'km/h' | kw= 'm/min' )
            int alt95=4;
            switch ( input.LA(1) ) {
            case 98:
                {
                alt95=1;
                }
                break;
            case 99:
                {
                alt95=2;
                }
                break;
            case 100:
                {
                alt95=3;
                }
                break;
            case 101:
                {
                alt95=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 95, 0, input);

                throw nvae;
            }

            switch (alt95) {
                case 1 :
                    // InternalRequirementDSL.g:4348:3: kw= 'm/s'
                    {
                    kw=(Token)match(input,98,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getVelcoityUnitsAccess().getMSKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4354:3: kw= 'knots'
                    {
                    kw=(Token)match(input,99,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getVelcoityUnitsAccess().getKnotsKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4360:3: kw= 'km/h'
                    {
                    kw=(Token)match(input,100,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getVelcoityUnitsAccess().getKmHKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4366:3: kw= 'm/min'
                    {
                    kw=(Token)match(input,101,FOLLOW_2); 

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
    // InternalRequirementDSL.g:4375:1: entryRuleMassUnits returns [String current=null] : iv_ruleMassUnits= ruleMassUnits EOF ;
    public final String entryRuleMassUnits() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleMassUnits = null;


        try {
            // InternalRequirementDSL.g:4375:49: (iv_ruleMassUnits= ruleMassUnits EOF )
            // InternalRequirementDSL.g:4376:2: iv_ruleMassUnits= ruleMassUnits EOF
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
    // InternalRequirementDSL.g:4382:1: ruleMassUnits returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'kg' | kw= 'g' | kw= 'mg' | kw= 't' ) ;
    public final AntlrDatatypeRuleToken ruleMassUnits() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4388:2: ( (kw= 'kg' | kw= 'g' | kw= 'mg' | kw= 't' ) )
            // InternalRequirementDSL.g:4389:2: (kw= 'kg' | kw= 'g' | kw= 'mg' | kw= 't' )
            {
            // InternalRequirementDSL.g:4389:2: (kw= 'kg' | kw= 'g' | kw= 'mg' | kw= 't' )
            int alt96=4;
            switch ( input.LA(1) ) {
            case 102:
                {
                alt96=1;
                }
                break;
            case 103:
                {
                alt96=2;
                }
                break;
            case 104:
                {
                alt96=3;
                }
                break;
            case 105:
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
                    // InternalRequirementDSL.g:4390:3: kw= 'kg'
                    {
                    kw=(Token)match(input,102,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getMassUnitsAccess().getKgKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4396:3: kw= 'g'
                    {
                    kw=(Token)match(input,103,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getMassUnitsAccess().getGKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4402:3: kw= 'mg'
                    {
                    kw=(Token)match(input,104,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getMassUnitsAccess().getMgKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4408:3: kw= 't'
                    {
                    kw=(Token)match(input,105,FOLLOW_2); 

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
    // InternalRequirementDSL.g:4417:1: entryRuleHeatUnits returns [String current=null] : iv_ruleHeatUnits= ruleHeatUnits EOF ;
    public final String entryRuleHeatUnits() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleHeatUnits = null;


        try {
            // InternalRequirementDSL.g:4417:49: (iv_ruleHeatUnits= ruleHeatUnits EOF )
            // InternalRequirementDSL.g:4418:2: iv_ruleHeatUnits= ruleHeatUnits EOF
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
    // InternalRequirementDSL.g:4424:1: ruleHeatUnits returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'C' | kw= 'F' ) ;
    public final AntlrDatatypeRuleToken ruleHeatUnits() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4430:2: ( (kw= 'C' | kw= 'F' ) )
            // InternalRequirementDSL.g:4431:2: (kw= 'C' | kw= 'F' )
            {
            // InternalRequirementDSL.g:4431:2: (kw= 'C' | kw= 'F' )
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==106) ) {
                alt97=1;
            }
            else if ( (LA97_0==107) ) {
                alt97=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 97, 0, input);

                throw nvae;
            }
            switch (alt97) {
                case 1 :
                    // InternalRequirementDSL.g:4432:3: kw= 'C'
                    {
                    kw=(Token)match(input,106,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getHeatUnitsAccess().getCKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4438:3: kw= 'F'
                    {
                    kw=(Token)match(input,107,FOLLOW_2); 

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
    // InternalRequirementDSL.g:4447:1: entryRulePresureUnits returns [String current=null] : iv_rulePresureUnits= rulePresureUnits EOF ;
    public final String entryRulePresureUnits() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulePresureUnits = null;


        try {
            // InternalRequirementDSL.g:4447:52: (iv_rulePresureUnits= rulePresureUnits EOF )
            // InternalRequirementDSL.g:4448:2: iv_rulePresureUnits= rulePresureUnits EOF
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
    // InternalRequirementDSL.g:4454:1: rulePresureUnits returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'bar' | kw= 'Pa' | kw= 'hPa' ) ;
    public final AntlrDatatypeRuleToken rulePresureUnits() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4460:2: ( (kw= 'bar' | kw= 'Pa' | kw= 'hPa' ) )
            // InternalRequirementDSL.g:4461:2: (kw= 'bar' | kw= 'Pa' | kw= 'hPa' )
            {
            // InternalRequirementDSL.g:4461:2: (kw= 'bar' | kw= 'Pa' | kw= 'hPa' )
            int alt98=3;
            switch ( input.LA(1) ) {
            case 108:
                {
                alt98=1;
                }
                break;
            case 109:
                {
                alt98=2;
                }
                break;
            case 110:
                {
                alt98=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 98, 0, input);

                throw nvae;
            }

            switch (alt98) {
                case 1 :
                    // InternalRequirementDSL.g:4462:3: kw= 'bar'
                    {
                    kw=(Token)match(input,108,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPresureUnitsAccess().getBarKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4468:3: kw= 'Pa'
                    {
                    kw=(Token)match(input,109,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getPresureUnitsAccess().getPaKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4474:3: kw= 'hPa'
                    {
                    kw=(Token)match(input,110,FOLLOW_2); 

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
    // InternalRequirementDSL.g:4483:1: entryRuleLengthUnits returns [String current=null] : iv_ruleLengthUnits= ruleLengthUnits EOF ;
    public final String entryRuleLengthUnits() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleLengthUnits = null;


        try {
            // InternalRequirementDSL.g:4483:51: (iv_ruleLengthUnits= ruleLengthUnits EOF )
            // InternalRequirementDSL.g:4484:2: iv_ruleLengthUnits= ruleLengthUnits EOF
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
    // InternalRequirementDSL.g:4490:1: ruleLengthUnits returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'm' | kw= 'f' | kw= 'km' | kw= 'cm' | kw= 'mm' | kw= 'nm' ) ;
    public final AntlrDatatypeRuleToken ruleLengthUnits() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4496:2: ( (kw= 'm' | kw= 'f' | kw= 'km' | kw= 'cm' | kw= 'mm' | kw= 'nm' ) )
            // InternalRequirementDSL.g:4497:2: (kw= 'm' | kw= 'f' | kw= 'km' | kw= 'cm' | kw= 'mm' | kw= 'nm' )
            {
            // InternalRequirementDSL.g:4497:2: (kw= 'm' | kw= 'f' | kw= 'km' | kw= 'cm' | kw= 'mm' | kw= 'nm' )
            int alt99=6;
            switch ( input.LA(1) ) {
            case 111:
                {
                alt99=1;
                }
                break;
            case 112:
                {
                alt99=2;
                }
                break;
            case 113:
                {
                alt99=3;
                }
                break;
            case 114:
                {
                alt99=4;
                }
                break;
            case 115:
                {
                alt99=5;
                }
                break;
            case 116:
                {
                alt99=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 99, 0, input);

                throw nvae;
            }

            switch (alt99) {
                case 1 :
                    // InternalRequirementDSL.g:4498:3: kw= 'm'
                    {
                    kw=(Token)match(input,111,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getLengthUnitsAccess().getMKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4504:3: kw= 'f'
                    {
                    kw=(Token)match(input,112,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getLengthUnitsAccess().getFKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4510:3: kw= 'km'
                    {
                    kw=(Token)match(input,113,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getLengthUnitsAccess().getKmKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4516:3: kw= 'cm'
                    {
                    kw=(Token)match(input,114,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getLengthUnitsAccess().getCmKeyword_3());
                    		

                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:4522:3: kw= 'mm'
                    {
                    kw=(Token)match(input,115,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getLengthUnitsAccess().getMmKeyword_4());
                    		

                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:4528:3: kw= 'nm'
                    {
                    kw=(Token)match(input,116,FOLLOW_2); 

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
    // InternalRequirementDSL.g:4537:1: entryRuleTimeUnits returns [String current=null] : iv_ruleTimeUnits= ruleTimeUnits EOF ;
    public final String entryRuleTimeUnits() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleTimeUnits = null;


        try {
            // InternalRequirementDSL.g:4537:49: (iv_ruleTimeUnits= ruleTimeUnits EOF )
            // InternalRequirementDSL.g:4538:2: iv_ruleTimeUnits= ruleTimeUnits EOF
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
    // InternalRequirementDSL.g:4544:1: ruleTimeUnits returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'ns' | kw= 'ms' | kw= 's' | kw= 'sec' | kw= 'second' | kw= 'seconds' | kw= 'minute' | kw= 'minutes' | kw= 'min' | kw= 'hour' | kw= 'hours' | kw= 'h' | kw= 'day' | kw= 'days' | kw= 'd' | kw= 'month' | kw= 'months' | kw= 'mon' | kw= 'year' | kw= 'years' | kw= 'y' ) ;
    public final AntlrDatatypeRuleToken ruleTimeUnits() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalRequirementDSL.g:4550:2: ( (kw= 'ns' | kw= 'ms' | kw= 's' | kw= 'sec' | kw= 'second' | kw= 'seconds' | kw= 'minute' | kw= 'minutes' | kw= 'min' | kw= 'hour' | kw= 'hours' | kw= 'h' | kw= 'day' | kw= 'days' | kw= 'd' | kw= 'month' | kw= 'months' | kw= 'mon' | kw= 'year' | kw= 'years' | kw= 'y' ) )
            // InternalRequirementDSL.g:4551:2: (kw= 'ns' | kw= 'ms' | kw= 's' | kw= 'sec' | kw= 'second' | kw= 'seconds' | kw= 'minute' | kw= 'minutes' | kw= 'min' | kw= 'hour' | kw= 'hours' | kw= 'h' | kw= 'day' | kw= 'days' | kw= 'd' | kw= 'month' | kw= 'months' | kw= 'mon' | kw= 'year' | kw= 'years' | kw= 'y' )
            {
            // InternalRequirementDSL.g:4551:2: (kw= 'ns' | kw= 'ms' | kw= 's' | kw= 'sec' | kw= 'second' | kw= 'seconds' | kw= 'minute' | kw= 'minutes' | kw= 'min' | kw= 'hour' | kw= 'hours' | kw= 'h' | kw= 'day' | kw= 'days' | kw= 'd' | kw= 'month' | kw= 'months' | kw= 'mon' | kw= 'year' | kw= 'years' | kw= 'y' )
            int alt100=21;
            switch ( input.LA(1) ) {
            case 117:
                {
                alt100=1;
                }
                break;
            case 118:
                {
                alt100=2;
                }
                break;
            case 119:
                {
                alt100=3;
                }
                break;
            case 120:
                {
                alt100=4;
                }
                break;
            case 121:
                {
                alt100=5;
                }
                break;
            case 122:
                {
                alt100=6;
                }
                break;
            case 123:
                {
                alt100=7;
                }
                break;
            case 124:
                {
                alt100=8;
                }
                break;
            case 125:
                {
                alt100=9;
                }
                break;
            case 126:
                {
                alt100=10;
                }
                break;
            case 127:
                {
                alt100=11;
                }
                break;
            case 128:
                {
                alt100=12;
                }
                break;
            case 129:
                {
                alt100=13;
                }
                break;
            case 130:
                {
                alt100=14;
                }
                break;
            case 131:
                {
                alt100=15;
                }
                break;
            case 132:
                {
                alt100=16;
                }
                break;
            case 133:
                {
                alt100=17;
                }
                break;
            case 134:
                {
                alt100=18;
                }
                break;
            case 135:
                {
                alt100=19;
                }
                break;
            case 136:
                {
                alt100=20;
                }
                break;
            case 137:
                {
                alt100=21;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 100, 0, input);

                throw nvae;
            }

            switch (alt100) {
                case 1 :
                    // InternalRequirementDSL.g:4552:3: kw= 'ns'
                    {
                    kw=(Token)match(input,117,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getNsKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4558:3: kw= 'ms'
                    {
                    kw=(Token)match(input,118,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getMsKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4564:3: kw= 's'
                    {
                    kw=(Token)match(input,119,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getSKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4570:3: kw= 'sec'
                    {
                    kw=(Token)match(input,120,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getSecKeyword_3());
                    		

                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:4576:3: kw= 'second'
                    {
                    kw=(Token)match(input,121,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getSecondKeyword_4());
                    		

                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:4582:3: kw= 'seconds'
                    {
                    kw=(Token)match(input,122,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getSecondsKeyword_5());
                    		

                    }
                    break;
                case 7 :
                    // InternalRequirementDSL.g:4588:3: kw= 'minute'
                    {
                    kw=(Token)match(input,123,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getMinuteKeyword_6());
                    		

                    }
                    break;
                case 8 :
                    // InternalRequirementDSL.g:4594:3: kw= 'minutes'
                    {
                    kw=(Token)match(input,124,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getMinutesKeyword_7());
                    		

                    }
                    break;
                case 9 :
                    // InternalRequirementDSL.g:4600:3: kw= 'min'
                    {
                    kw=(Token)match(input,125,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getMinKeyword_8());
                    		

                    }
                    break;
                case 10 :
                    // InternalRequirementDSL.g:4606:3: kw= 'hour'
                    {
                    kw=(Token)match(input,126,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getHourKeyword_9());
                    		

                    }
                    break;
                case 11 :
                    // InternalRequirementDSL.g:4612:3: kw= 'hours'
                    {
                    kw=(Token)match(input,127,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getHoursKeyword_10());
                    		

                    }
                    break;
                case 12 :
                    // InternalRequirementDSL.g:4618:3: kw= 'h'
                    {
                    kw=(Token)match(input,128,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getHKeyword_11());
                    		

                    }
                    break;
                case 13 :
                    // InternalRequirementDSL.g:4624:3: kw= 'day'
                    {
                    kw=(Token)match(input,129,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getDayKeyword_12());
                    		

                    }
                    break;
                case 14 :
                    // InternalRequirementDSL.g:4630:3: kw= 'days'
                    {
                    kw=(Token)match(input,130,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getDaysKeyword_13());
                    		

                    }
                    break;
                case 15 :
                    // InternalRequirementDSL.g:4636:3: kw= 'd'
                    {
                    kw=(Token)match(input,131,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getDKeyword_14());
                    		

                    }
                    break;
                case 16 :
                    // InternalRequirementDSL.g:4642:3: kw= 'month'
                    {
                    kw=(Token)match(input,132,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getMonthKeyword_15());
                    		

                    }
                    break;
                case 17 :
                    // InternalRequirementDSL.g:4648:3: kw= 'months'
                    {
                    kw=(Token)match(input,133,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getMonthsKeyword_16());
                    		

                    }
                    break;
                case 18 :
                    // InternalRequirementDSL.g:4654:3: kw= 'mon'
                    {
                    kw=(Token)match(input,134,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getMonKeyword_17());
                    		

                    }
                    break;
                case 19 :
                    // InternalRequirementDSL.g:4660:3: kw= 'year'
                    {
                    kw=(Token)match(input,135,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getYearKeyword_18());
                    		

                    }
                    break;
                case 20 :
                    // InternalRequirementDSL.g:4666:3: kw= 'years'
                    {
                    kw=(Token)match(input,136,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getTimeUnitsAccess().getYearsKeyword_19());
                    		

                    }
                    break;
                case 21 :
                    // InternalRequirementDSL.g:4672:3: kw= 'y'
                    {
                    kw=(Token)match(input,137,FOLLOW_2); 

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
    // InternalRequirementDSL.g:4681:1: ruleModality returns [Enumerator current=null] : ( (enumLiteral_0= 'shall' ) | (enumLiteral_1= 'should' ) | (enumLiteral_2= 'will' ) | (enumLiteral_3= 'would' ) | (enumLiteral_4= 'can' ) | (enumLiteral_5= 'could' ) | (enumLiteral_6= 'must' ) ) ;
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
            // InternalRequirementDSL.g:4687:2: ( ( (enumLiteral_0= 'shall' ) | (enumLiteral_1= 'should' ) | (enumLiteral_2= 'will' ) | (enumLiteral_3= 'would' ) | (enumLiteral_4= 'can' ) | (enumLiteral_5= 'could' ) | (enumLiteral_6= 'must' ) ) )
            // InternalRequirementDSL.g:4688:2: ( (enumLiteral_0= 'shall' ) | (enumLiteral_1= 'should' ) | (enumLiteral_2= 'will' ) | (enumLiteral_3= 'would' ) | (enumLiteral_4= 'can' ) | (enumLiteral_5= 'could' ) | (enumLiteral_6= 'must' ) )
            {
            // InternalRequirementDSL.g:4688:2: ( (enumLiteral_0= 'shall' ) | (enumLiteral_1= 'should' ) | (enumLiteral_2= 'will' ) | (enumLiteral_3= 'would' ) | (enumLiteral_4= 'can' ) | (enumLiteral_5= 'could' ) | (enumLiteral_6= 'must' ) )
            int alt101=7;
            switch ( input.LA(1) ) {
            case 138:
                {
                alt101=1;
                }
                break;
            case 139:
                {
                alt101=2;
                }
                break;
            case 140:
                {
                alt101=3;
                }
                break;
            case 141:
                {
                alt101=4;
                }
                break;
            case 142:
                {
                alt101=5;
                }
                break;
            case 143:
                {
                alt101=6;
                }
                break;
            case 144:
                {
                alt101=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 101, 0, input);

                throw nvae;
            }

            switch (alt101) {
                case 1 :
                    // InternalRequirementDSL.g:4689:3: (enumLiteral_0= 'shall' )
                    {
                    // InternalRequirementDSL.g:4689:3: (enumLiteral_0= 'shall' )
                    // InternalRequirementDSL.g:4690:4: enumLiteral_0= 'shall'
                    {
                    enumLiteral_0=(Token)match(input,138,FOLLOW_2); 

                    				current = grammarAccess.getModalityAccess().getSHALLEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getModalityAccess().getSHALLEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4697:3: (enumLiteral_1= 'should' )
                    {
                    // InternalRequirementDSL.g:4697:3: (enumLiteral_1= 'should' )
                    // InternalRequirementDSL.g:4698:4: enumLiteral_1= 'should'
                    {
                    enumLiteral_1=(Token)match(input,139,FOLLOW_2); 

                    				current = grammarAccess.getModalityAccess().getSHOULDEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getModalityAccess().getSHOULDEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4705:3: (enumLiteral_2= 'will' )
                    {
                    // InternalRequirementDSL.g:4705:3: (enumLiteral_2= 'will' )
                    // InternalRequirementDSL.g:4706:4: enumLiteral_2= 'will'
                    {
                    enumLiteral_2=(Token)match(input,140,FOLLOW_2); 

                    				current = grammarAccess.getModalityAccess().getWILLEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getModalityAccess().getWILLEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4713:3: (enumLiteral_3= 'would' )
                    {
                    // InternalRequirementDSL.g:4713:3: (enumLiteral_3= 'would' )
                    // InternalRequirementDSL.g:4714:4: enumLiteral_3= 'would'
                    {
                    enumLiteral_3=(Token)match(input,141,FOLLOW_2); 

                    				current = grammarAccess.getModalityAccess().getWOULDEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getModalityAccess().getWOULDEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:4721:3: (enumLiteral_4= 'can' )
                    {
                    // InternalRequirementDSL.g:4721:3: (enumLiteral_4= 'can' )
                    // InternalRequirementDSL.g:4722:4: enumLiteral_4= 'can'
                    {
                    enumLiteral_4=(Token)match(input,142,FOLLOW_2); 

                    				current = grammarAccess.getModalityAccess().getCANEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getModalityAccess().getCANEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:4729:3: (enumLiteral_5= 'could' )
                    {
                    // InternalRequirementDSL.g:4729:3: (enumLiteral_5= 'could' )
                    // InternalRequirementDSL.g:4730:4: enumLiteral_5= 'could'
                    {
                    enumLiteral_5=(Token)match(input,143,FOLLOW_2); 

                    				current = grammarAccess.getModalityAccess().getCOULDEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getModalityAccess().getCOULDEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalRequirementDSL.g:4737:3: (enumLiteral_6= 'must' )
                    {
                    // InternalRequirementDSL.g:4737:3: (enumLiteral_6= 'must' )
                    // InternalRequirementDSL.g:4738:4: enumLiteral_6= 'must'
                    {
                    enumLiteral_6=(Token)match(input,144,FOLLOW_2); 

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
    // InternalRequirementDSL.g:4748:1: ruleModifier returns [Enumerator current=null] : ( (enumLiteral_0= 'Globally' ) | (enumLiteral_1= 'globally' ) | (enumLiteral_2= 'Always' ) | (enumLiteral_3= 'always' ) | (enumLiteral_4= 'Sometimes' ) | (enumLiteral_5= 'sometimes' ) | (enumLiteral_6= 'Eventually' ) | (enumLiteral_7= 'eventually' ) ) ;
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
            // InternalRequirementDSL.g:4754:2: ( ( (enumLiteral_0= 'Globally' ) | (enumLiteral_1= 'globally' ) | (enumLiteral_2= 'Always' ) | (enumLiteral_3= 'always' ) | (enumLiteral_4= 'Sometimes' ) | (enumLiteral_5= 'sometimes' ) | (enumLiteral_6= 'Eventually' ) | (enumLiteral_7= 'eventually' ) ) )
            // InternalRequirementDSL.g:4755:2: ( (enumLiteral_0= 'Globally' ) | (enumLiteral_1= 'globally' ) | (enumLiteral_2= 'Always' ) | (enumLiteral_3= 'always' ) | (enumLiteral_4= 'Sometimes' ) | (enumLiteral_5= 'sometimes' ) | (enumLiteral_6= 'Eventually' ) | (enumLiteral_7= 'eventually' ) )
            {
            // InternalRequirementDSL.g:4755:2: ( (enumLiteral_0= 'Globally' ) | (enumLiteral_1= 'globally' ) | (enumLiteral_2= 'Always' ) | (enumLiteral_3= 'always' ) | (enumLiteral_4= 'Sometimes' ) | (enumLiteral_5= 'sometimes' ) | (enumLiteral_6= 'Eventually' ) | (enumLiteral_7= 'eventually' ) )
            int alt102=8;
            switch ( input.LA(1) ) {
            case 145:
                {
                alt102=1;
                }
                break;
            case 146:
                {
                alt102=2;
                }
                break;
            case 147:
                {
                alt102=3;
                }
                break;
            case 148:
                {
                alt102=4;
                }
                break;
            case 149:
                {
                alt102=5;
                }
                break;
            case 150:
                {
                alt102=6;
                }
                break;
            case 151:
                {
                alt102=7;
                }
                break;
            case 152:
                {
                alt102=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 102, 0, input);

                throw nvae;
            }

            switch (alt102) {
                case 1 :
                    // InternalRequirementDSL.g:4756:3: (enumLiteral_0= 'Globally' )
                    {
                    // InternalRequirementDSL.g:4756:3: (enumLiteral_0= 'Globally' )
                    // InternalRequirementDSL.g:4757:4: enumLiteral_0= 'Globally'
                    {
                    enumLiteral_0=(Token)match(input,145,FOLLOW_2); 

                    				current = grammarAccess.getModifierAccess().getGLOBALLYEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getModifierAccess().getGLOBALLYEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4764:3: (enumLiteral_1= 'globally' )
                    {
                    // InternalRequirementDSL.g:4764:3: (enumLiteral_1= 'globally' )
                    // InternalRequirementDSL.g:4765:4: enumLiteral_1= 'globally'
                    {
                    enumLiteral_1=(Token)match(input,146,FOLLOW_2); 

                    				current = grammarAccess.getModifierAccess().getGLOBALLYEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getModifierAccess().getGLOBALLYEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4772:3: (enumLiteral_2= 'Always' )
                    {
                    // InternalRequirementDSL.g:4772:3: (enumLiteral_2= 'Always' )
                    // InternalRequirementDSL.g:4773:4: enumLiteral_2= 'Always'
                    {
                    enumLiteral_2=(Token)match(input,147,FOLLOW_2); 

                    				current = grammarAccess.getModifierAccess().getALWAYSEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getModifierAccess().getALWAYSEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4780:3: (enumLiteral_3= 'always' )
                    {
                    // InternalRequirementDSL.g:4780:3: (enumLiteral_3= 'always' )
                    // InternalRequirementDSL.g:4781:4: enumLiteral_3= 'always'
                    {
                    enumLiteral_3=(Token)match(input,148,FOLLOW_2); 

                    				current = grammarAccess.getModifierAccess().getALWAYSEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getModifierAccess().getALWAYSEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:4788:3: (enumLiteral_4= 'Sometimes' )
                    {
                    // InternalRequirementDSL.g:4788:3: (enumLiteral_4= 'Sometimes' )
                    // InternalRequirementDSL.g:4789:4: enumLiteral_4= 'Sometimes'
                    {
                    enumLiteral_4=(Token)match(input,149,FOLLOW_2); 

                    				current = grammarAccess.getModifierAccess().getSOMETIMESEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getModifierAccess().getSOMETIMESEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:4796:3: (enumLiteral_5= 'sometimes' )
                    {
                    // InternalRequirementDSL.g:4796:3: (enumLiteral_5= 'sometimes' )
                    // InternalRequirementDSL.g:4797:4: enumLiteral_5= 'sometimes'
                    {
                    enumLiteral_5=(Token)match(input,150,FOLLOW_2); 

                    				current = grammarAccess.getModifierAccess().getSOMETIMESEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getModifierAccess().getSOMETIMESEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalRequirementDSL.g:4804:3: (enumLiteral_6= 'Eventually' )
                    {
                    // InternalRequirementDSL.g:4804:3: (enumLiteral_6= 'Eventually' )
                    // InternalRequirementDSL.g:4805:4: enumLiteral_6= 'Eventually'
                    {
                    enumLiteral_6=(Token)match(input,151,FOLLOW_2); 

                    				current = grammarAccess.getModifierAccess().getEVENTUALLYEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getModifierAccess().getEVENTUALLYEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalRequirementDSL.g:4812:3: (enumLiteral_7= 'eventually' )
                    {
                    // InternalRequirementDSL.g:4812:3: (enumLiteral_7= 'eventually' )
                    // InternalRequirementDSL.g:4813:4: enumLiteral_7= 'eventually'
                    {
                    enumLiteral_7=(Token)match(input,152,FOLLOW_2); 

                    				current = grammarAccess.getModifierAccess().getEVENTUALLYEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_7, grammarAccess.getModifierAccess().getEVENTUALLYEnumLiteralDeclaration_7());
                    			

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
    // InternalRequirementDSL.g:4823:1: ruleClauseOrdinator returns [Enumerator current=null] : ( (enumLiteral_0= 'if' ) | (enumLiteral_1= 'after' ) | (enumLiteral_2= 'once' ) | (enumLiteral_3= 'when' ) | (enumLiteral_4= 'whenever' ) | (enumLiteral_5= 'while' ) | (enumLiteral_6= 'before' ) | (enumLiteral_7= 'until' ) | (enumLiteral_8= 'If' ) | (enumLiteral_9= 'After' ) | (enumLiteral_10= 'Once' ) | (enumLiteral_11= 'When' ) | (enumLiteral_12= 'Whenever' ) | (enumLiteral_13= 'While' ) | (enumLiteral_14= 'Before' ) | (enumLiteral_15= 'Until' ) ) ;
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
            // InternalRequirementDSL.g:4829:2: ( ( (enumLiteral_0= 'if' ) | (enumLiteral_1= 'after' ) | (enumLiteral_2= 'once' ) | (enumLiteral_3= 'when' ) | (enumLiteral_4= 'whenever' ) | (enumLiteral_5= 'while' ) | (enumLiteral_6= 'before' ) | (enumLiteral_7= 'until' ) | (enumLiteral_8= 'If' ) | (enumLiteral_9= 'After' ) | (enumLiteral_10= 'Once' ) | (enumLiteral_11= 'When' ) | (enumLiteral_12= 'Whenever' ) | (enumLiteral_13= 'While' ) | (enumLiteral_14= 'Before' ) | (enumLiteral_15= 'Until' ) ) )
            // InternalRequirementDSL.g:4830:2: ( (enumLiteral_0= 'if' ) | (enumLiteral_1= 'after' ) | (enumLiteral_2= 'once' ) | (enumLiteral_3= 'when' ) | (enumLiteral_4= 'whenever' ) | (enumLiteral_5= 'while' ) | (enumLiteral_6= 'before' ) | (enumLiteral_7= 'until' ) | (enumLiteral_8= 'If' ) | (enumLiteral_9= 'After' ) | (enumLiteral_10= 'Once' ) | (enumLiteral_11= 'When' ) | (enumLiteral_12= 'Whenever' ) | (enumLiteral_13= 'While' ) | (enumLiteral_14= 'Before' ) | (enumLiteral_15= 'Until' ) )
            {
            // InternalRequirementDSL.g:4830:2: ( (enumLiteral_0= 'if' ) | (enumLiteral_1= 'after' ) | (enumLiteral_2= 'once' ) | (enumLiteral_3= 'when' ) | (enumLiteral_4= 'whenever' ) | (enumLiteral_5= 'while' ) | (enumLiteral_6= 'before' ) | (enumLiteral_7= 'until' ) | (enumLiteral_8= 'If' ) | (enumLiteral_9= 'After' ) | (enumLiteral_10= 'Once' ) | (enumLiteral_11= 'When' ) | (enumLiteral_12= 'Whenever' ) | (enumLiteral_13= 'While' ) | (enumLiteral_14= 'Before' ) | (enumLiteral_15= 'Until' ) )
            int alt103=16;
            switch ( input.LA(1) ) {
            case 153:
                {
                alt103=1;
                }
                break;
            case 154:
                {
                alt103=2;
                }
                break;
            case 155:
                {
                alt103=3;
                }
                break;
            case 156:
                {
                alt103=4;
                }
                break;
            case 157:
                {
                alt103=5;
                }
                break;
            case 158:
                {
                alt103=6;
                }
                break;
            case 159:
                {
                alt103=7;
                }
                break;
            case 160:
                {
                alt103=8;
                }
                break;
            case 161:
                {
                alt103=9;
                }
                break;
            case 162:
                {
                alt103=10;
                }
                break;
            case 163:
                {
                alt103=11;
                }
                break;
            case 164:
                {
                alt103=12;
                }
                break;
            case 165:
                {
                alt103=13;
                }
                break;
            case 166:
                {
                alt103=14;
                }
                break;
            case 167:
                {
                alt103=15;
                }
                break;
            case 168:
                {
                alt103=16;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 103, 0, input);

                throw nvae;
            }

            switch (alt103) {
                case 1 :
                    // InternalRequirementDSL.g:4831:3: (enumLiteral_0= 'if' )
                    {
                    // InternalRequirementDSL.g:4831:3: (enumLiteral_0= 'if' )
                    // InternalRequirementDSL.g:4832:4: enumLiteral_0= 'if'
                    {
                    enumLiteral_0=(Token)match(input,153,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getIFEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getClauseOrdinatorAccess().getIFEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalRequirementDSL.g:4839:3: (enumLiteral_1= 'after' )
                    {
                    // InternalRequirementDSL.g:4839:3: (enumLiteral_1= 'after' )
                    // InternalRequirementDSL.g:4840:4: enumLiteral_1= 'after'
                    {
                    enumLiteral_1=(Token)match(input,154,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getAFTEREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getClauseOrdinatorAccess().getAFTEREnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalRequirementDSL.g:4847:3: (enumLiteral_2= 'once' )
                    {
                    // InternalRequirementDSL.g:4847:3: (enumLiteral_2= 'once' )
                    // InternalRequirementDSL.g:4848:4: enumLiteral_2= 'once'
                    {
                    enumLiteral_2=(Token)match(input,155,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getONCEEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getClauseOrdinatorAccess().getONCEEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalRequirementDSL.g:4855:3: (enumLiteral_3= 'when' )
                    {
                    // InternalRequirementDSL.g:4855:3: (enumLiteral_3= 'when' )
                    // InternalRequirementDSL.g:4856:4: enumLiteral_3= 'when'
                    {
                    enumLiteral_3=(Token)match(input,156,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getWHENEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getClauseOrdinatorAccess().getWHENEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalRequirementDSL.g:4863:3: (enumLiteral_4= 'whenever' )
                    {
                    // InternalRequirementDSL.g:4863:3: (enumLiteral_4= 'whenever' )
                    // InternalRequirementDSL.g:4864:4: enumLiteral_4= 'whenever'
                    {
                    enumLiteral_4=(Token)match(input,157,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getWHENEVEREnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getClauseOrdinatorAccess().getWHENEVEREnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalRequirementDSL.g:4871:3: (enumLiteral_5= 'while' )
                    {
                    // InternalRequirementDSL.g:4871:3: (enumLiteral_5= 'while' )
                    // InternalRequirementDSL.g:4872:4: enumLiteral_5= 'while'
                    {
                    enumLiteral_5=(Token)match(input,158,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getWHILEEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getClauseOrdinatorAccess().getWHILEEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalRequirementDSL.g:4879:3: (enumLiteral_6= 'before' )
                    {
                    // InternalRequirementDSL.g:4879:3: (enumLiteral_6= 'before' )
                    // InternalRequirementDSL.g:4880:4: enumLiteral_6= 'before'
                    {
                    enumLiteral_6=(Token)match(input,159,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getBEFOREEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getClauseOrdinatorAccess().getBEFOREEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalRequirementDSL.g:4887:3: (enumLiteral_7= 'until' )
                    {
                    // InternalRequirementDSL.g:4887:3: (enumLiteral_7= 'until' )
                    // InternalRequirementDSL.g:4888:4: enumLiteral_7= 'until'
                    {
                    enumLiteral_7=(Token)match(input,160,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getUNTILEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_7, grammarAccess.getClauseOrdinatorAccess().getUNTILEnumLiteralDeclaration_7());
                    			

                    }


                    }
                    break;
                case 9 :
                    // InternalRequirementDSL.g:4895:3: (enumLiteral_8= 'If' )
                    {
                    // InternalRequirementDSL.g:4895:3: (enumLiteral_8= 'If' )
                    // InternalRequirementDSL.g:4896:4: enumLiteral_8= 'If'
                    {
                    enumLiteral_8=(Token)match(input,161,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getIFEnumLiteralDeclaration_8().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_8, grammarAccess.getClauseOrdinatorAccess().getIFEnumLiteralDeclaration_8());
                    			

                    }


                    }
                    break;
                case 10 :
                    // InternalRequirementDSL.g:4903:3: (enumLiteral_9= 'After' )
                    {
                    // InternalRequirementDSL.g:4903:3: (enumLiteral_9= 'After' )
                    // InternalRequirementDSL.g:4904:4: enumLiteral_9= 'After'
                    {
                    enumLiteral_9=(Token)match(input,162,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getAFTEREnumLiteralDeclaration_9().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_9, grammarAccess.getClauseOrdinatorAccess().getAFTEREnumLiteralDeclaration_9());
                    			

                    }


                    }
                    break;
                case 11 :
                    // InternalRequirementDSL.g:4911:3: (enumLiteral_10= 'Once' )
                    {
                    // InternalRequirementDSL.g:4911:3: (enumLiteral_10= 'Once' )
                    // InternalRequirementDSL.g:4912:4: enumLiteral_10= 'Once'
                    {
                    enumLiteral_10=(Token)match(input,163,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getONCEEnumLiteralDeclaration_10().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_10, grammarAccess.getClauseOrdinatorAccess().getONCEEnumLiteralDeclaration_10());
                    			

                    }


                    }
                    break;
                case 12 :
                    // InternalRequirementDSL.g:4919:3: (enumLiteral_11= 'When' )
                    {
                    // InternalRequirementDSL.g:4919:3: (enumLiteral_11= 'When' )
                    // InternalRequirementDSL.g:4920:4: enumLiteral_11= 'When'
                    {
                    enumLiteral_11=(Token)match(input,164,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getWHENEnumLiteralDeclaration_11().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_11, grammarAccess.getClauseOrdinatorAccess().getWHENEnumLiteralDeclaration_11());
                    			

                    }


                    }
                    break;
                case 13 :
                    // InternalRequirementDSL.g:4927:3: (enumLiteral_12= 'Whenever' )
                    {
                    // InternalRequirementDSL.g:4927:3: (enumLiteral_12= 'Whenever' )
                    // InternalRequirementDSL.g:4928:4: enumLiteral_12= 'Whenever'
                    {
                    enumLiteral_12=(Token)match(input,165,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getWHENEVEREnumLiteralDeclaration_12().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_12, grammarAccess.getClauseOrdinatorAccess().getWHENEVEREnumLiteralDeclaration_12());
                    			

                    }


                    }
                    break;
                case 14 :
                    // InternalRequirementDSL.g:4935:3: (enumLiteral_13= 'While' )
                    {
                    // InternalRequirementDSL.g:4935:3: (enumLiteral_13= 'While' )
                    // InternalRequirementDSL.g:4936:4: enumLiteral_13= 'While'
                    {
                    enumLiteral_13=(Token)match(input,166,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getWHILEEnumLiteralDeclaration_13().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_13, grammarAccess.getClauseOrdinatorAccess().getWHILEEnumLiteralDeclaration_13());
                    			

                    }


                    }
                    break;
                case 15 :
                    // InternalRequirementDSL.g:4943:3: (enumLiteral_14= 'Before' )
                    {
                    // InternalRequirementDSL.g:4943:3: (enumLiteral_14= 'Before' )
                    // InternalRequirementDSL.g:4944:4: enumLiteral_14= 'Before'
                    {
                    enumLiteral_14=(Token)match(input,167,FOLLOW_2); 

                    				current = grammarAccess.getClauseOrdinatorAccess().getBEFOREEnumLiteralDeclaration_14().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_14, grammarAccess.getClauseOrdinatorAccess().getBEFOREEnumLiteralDeclaration_14());
                    			

                    }


                    }
                    break;
                case 16 :
                    // InternalRequirementDSL.g:4951:3: (enumLiteral_15= 'Until' )
                    {
                    // InternalRequirementDSL.g:4951:3: (enumLiteral_15= 'Until' )
                    // InternalRequirementDSL.g:4952:4: enumLiteral_15= 'Until'
                    {
                    enumLiteral_15=(Token)match(input,168,FOLLOW_2); 

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


    protected DFA12 dfa12 = new DFA12(this);
    protected DFA23 dfa23 = new DFA23(this);
    protected DFA38 dfa38 = new DFA38(this);
    protected DFA42 dfa42 = new DFA42(this);
    protected DFA47 dfa47 = new DFA47(this);
    protected DFA49 dfa49 = new DFA49(this);
    protected DFA56 dfa56 = new DFA56(this);
    protected DFA60 dfa60 = new DFA60(this);
    protected DFA67 dfa67 = new DFA67(this);
    static final String dfa_1s = "\102\uffff";
    static final String dfa_2s = "\41\uffff\1\37\36\uffff\1\37\1\uffff";
    static final String dfa_3s = "\7\4\1\103\22\4\1\uffff\1\4\1\10\2\4\2\uffff\1\5\1\uffff\7\4\1\103\22\4\1\10\1\4\1\10\1\5\1\4";
    static final String dfa_4s = "\1\130\6\10\1\103\20\10\2\u0090\1\uffff\2\10\2\130\2\uffff\1\u00a8\1\uffff\1\u0090\6\10\1\103\20\10\2\u0090\3\10\1\u00a8\1\u0090";
    static final String dfa_5s = "\32\uffff\1\3\4\uffff\1\2\1\4\1\uffff\1\1\37\uffff";
    static final String dfa_6s = "\102\uffff}>";
    static final String[] dfa_7s = {
            "\1\31\3\uffff\1\30\13\uffff\1\32\47\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\uffff\1\10\1\11\1\12\1\13\1\14\1\15\5\uffff\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\33",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\37\1\40\2\uffff\1\41\20\uffff\1\34\6\37\1\35\1\36\150\uffff\7\42",
            "\1\37\1\40\2\uffff\1\37\21\uffff\6\37\1\35\1\36\150\uffff\7\42",
            "",
            "\1\31\3\uffff\1\30",
            "\1\43",
            "\1\74\3\uffff\1\73\63\uffff\1\44\1\45\1\46\1\47\1\50\1\51\1\52\1\uffff\1\53\1\54\1\55\1\56\1\57\1\60\5\uffff\1\61\1\62\1\63\1\64\1\65\1\66\1\67\1\70\1\71\1\72",
            "\1\74\3\uffff\1\73\63\uffff\1\44\1\45\1\46\1\47\1\50\1\51\1\52\1\uffff\1\53\1\54\1\55\1\56\1\57\1\60\5\uffff\1\61\1\62\1\63\1\64\1\65\1\66\1\67\1\70\1\71\1\72",
            "",
            "",
            "\1\40\2\uffff\1\41\7\uffff\4\37\5\uffff\1\75\6\uffff\2\37\2\uffff\1\37\1\uffff\35\37\1\uffff\6\37\5\uffff\13\37\77\uffff\20\37",
            "",
            "\1\37\1\40\2\uffff\1\41\20\uffff\1\34\6\37\1\35\1\36\150\uffff\7\42",
            "\1\74\3\uffff\1\73",
            "\1\74\3\uffff\1\73",
            "\1\74\3\uffff\1\73",
            "\1\74\3\uffff\1\73",
            "\1\74\3\uffff\1\73",
            "\1\74\3\uffff\1\73",
            "\1\76",
            "\1\74\3\uffff\1\73",
            "\1\74\3\uffff\1\73",
            "\1\74\3\uffff\1\73",
            "\1\74\3\uffff\1\73",
            "\1\74\3\uffff\1\73",
            "\1\74\3\uffff\1\73",
            "\1\74\3\uffff\1\73",
            "\1\74\3\uffff\1\73",
            "\1\74\3\uffff\1\73",
            "\1\74\3\uffff\1\73",
            "\1\74\3\uffff\1\73",
            "\1\74\3\uffff\1\73",
            "\1\74\3\uffff\1\73",
            "\1\74\3\uffff\1\73",
            "\1\74\3\uffff\1\73",
            "\1\74\3\uffff\1\73",
            "\1\37\3\uffff\1\37\20\uffff\1\77\6\37\1\35\1\36\150\uffff\7\42",
            "\1\37\3\uffff\1\37\21\uffff\6\37\1\35\1\36\150\uffff\7\42",
            "\1\100",
            "\1\74\3\uffff\1\73",
            "\1\101",
            "\1\40\2\uffff\1\41\7\uffff\4\37\5\uffff\1\75\6\uffff\2\37\2\uffff\1\37\1\uffff\35\37\1\uffff\6\37\5\uffff\13\37\77\uffff\20\37",
            "\1\37\3\uffff\1\37\20\uffff\1\77\6\37\1\35\1\36\150\uffff\7\42"
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final short[] dfa_2 = DFA.unpackEncodedString(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final char[] dfa_4 = DFA.unpackEncodedStringToUnsignedChars(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[] dfa_6 = DFA.unpackEncodedString(dfa_6s);
    static final short[][] dfa_7 = unpackEncodedStringArray(dfa_7s);

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "488:2: (this_ModalitySentence_0= ruleModalitySentence | this_PredicateSentence_1= rulePredicateSentence | this_ExistenceSentence_2= ruleExistenceSentence | this_PropertySentence_3= rulePropertySentence )";
        }
    }
    static final String dfa_8s = "\111\uffff";
    static final String dfa_9s = "\36\uffff\6\104\33\uffff\5\104\5\uffff";
    static final String dfa_10s = "\7\4\1\103\23\4\1\10\10\4\1\uffff\7\4\1\103\27\4\2\uffff\1\4\1\10\1\4";
    static final String dfa_11s = "\1\130\6\10\1\103\20\10\2\41\2\10\2\130\6\u00a8\1\uffff\1\41\6\10\1\103\20\10\2\41\5\u00a8\2\uffff\2\10\1\41";
    static final String dfa_12s = "\44\uffff\1\1\37\uffff\1\3\1\2\3\uffff";
    static final String dfa_13s = "\111\uffff}>";
    static final String[] dfa_14s = {
            "\1\31\3\uffff\1\30\63\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\uffff\1\10\1\11\1\12\1\13\1\14\1\15\5\uffff\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\32",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\44\3\uffff\1\44\20\uffff\1\33\1\36\1\37\1\40\1\41\1\42\1\43\1\34\1\35",
            "\1\44\3\uffff\1\44\21\uffff\1\36\1\37\1\40\1\41\1\42\1\43\1\34\1\35",
            "\1\31\3\uffff\1\30",
            "\1\45",
            "\1\76\3\uffff\1\75\63\uffff\1\46\1\47\1\50\1\51\1\52\1\53\1\54\1\uffff\1\55\1\56\1\57\1\60\1\61\1\62\5\uffff\1\63\1\64\1\65\1\66\1\67\1\70\1\71\1\72\1\73\1\74",
            "\1\76\3\uffff\1\75\63\uffff\1\46\1\47\1\50\1\51\1\52\1\53\1\54\1\uffff\1\55\1\56\1\57\1\60\1\61\1\62\5\uffff\1\63\1\64\1\65\1\66\1\67\1\70\1\71\1\72\1\73\1\74",
            "\1\105\3\uffff\1\105\7\uffff\4\104\6\uffff\6\105\2\104\2\uffff\1\104\1\uffff\35\104\1\uffff\6\104\1\77\1\100\1\101\1\102\1\103\13\104\77\uffff\20\104",
            "\1\105\3\uffff\1\105\7\uffff\4\104\6\uffff\6\105\2\104\2\uffff\1\104\1\uffff\35\104\1\uffff\6\104\1\77\1\100\1\101\1\102\1\103\13\104\77\uffff\20\104",
            "\1\105\3\uffff\1\105\7\uffff\4\104\6\uffff\6\105\2\104\2\uffff\1\104\1\uffff\35\104\1\uffff\6\104\1\77\1\100\1\101\1\102\1\103\13\104\77\uffff\20\104",
            "\1\105\3\uffff\1\105\7\uffff\4\104\6\uffff\6\105\2\104\2\uffff\1\104\1\uffff\35\104\1\uffff\6\104\1\77\1\100\1\101\1\102\1\103\13\104\77\uffff\20\104",
            "\1\105\3\uffff\1\105\7\uffff\4\104\6\uffff\6\105\2\104\2\uffff\1\104\1\uffff\35\104\1\uffff\6\104\1\77\1\100\1\101\1\102\1\103\13\104\77\uffff\20\104",
            "\1\105\3\uffff\1\105\7\uffff\4\104\6\uffff\6\105\2\104\2\uffff\1\104\1\uffff\35\104\1\uffff\6\104\1\77\1\100\1\101\1\102\1\103\13\104\77\uffff\20\104",
            "",
            "\1\44\3\uffff\1\44\20\uffff\1\33\1\36\1\37\1\40\1\41\1\42\1\43\1\34\1\35",
            "\1\76\3\uffff\1\75",
            "\1\76\3\uffff\1\75",
            "\1\76\3\uffff\1\75",
            "\1\76\3\uffff\1\75",
            "\1\76\3\uffff\1\75",
            "\1\76\3\uffff\1\75",
            "\1\106",
            "\1\76\3\uffff\1\75",
            "\1\76\3\uffff\1\75",
            "\1\76\3\uffff\1\75",
            "\1\76\3\uffff\1\75",
            "\1\76\3\uffff\1\75",
            "\1\76\3\uffff\1\75",
            "\1\76\3\uffff\1\75",
            "\1\76\3\uffff\1\75",
            "\1\76\3\uffff\1\75",
            "\1\76\3\uffff\1\75",
            "\1\76\3\uffff\1\75",
            "\1\76\3\uffff\1\75",
            "\1\76\3\uffff\1\75",
            "\1\76\3\uffff\1\75",
            "\1\76\3\uffff\1\75",
            "\1\76\3\uffff\1\75",
            "\1\44\3\uffff\1\44\20\uffff\1\107\1\36\1\37\1\40\1\41\1\42\1\43\1\34\1\35",
            "\1\44\3\uffff\1\44\21\uffff\1\36\1\37\1\40\1\41\1\42\1\43\1\34\1\35",
            "\1\105\3\uffff\1\105\7\uffff\4\104\6\uffff\6\105\2\104\2\uffff\1\104\1\uffff\35\104\1\uffff\6\104\5\uffff\13\104\77\uffff\20\104",
            "\1\105\3\uffff\1\105\7\uffff\4\104\6\uffff\6\105\2\104\2\uffff\1\104\1\uffff\35\104\1\uffff\6\104\5\uffff\13\104\77\uffff\20\104",
            "\1\105\3\uffff\1\105\7\uffff\4\104\6\uffff\6\105\2\104\2\uffff\1\104\1\uffff\35\104\1\uffff\6\104\5\uffff\13\104\77\uffff\20\104",
            "\1\105\3\uffff\1\105\7\uffff\4\104\6\uffff\6\105\2\104\2\uffff\1\104\1\uffff\35\104\1\uffff\6\104\5\uffff\13\104\77\uffff\20\104",
            "\1\105\3\uffff\1\105\7\uffff\4\104\6\uffff\6\105\2\104\2\uffff\1\104\1\uffff\35\104\1\uffff\6\104\5\uffff\13\104\77\uffff\20\104",
            "",
            "",
            "\1\76\3\uffff\1\75",
            "\1\110",
            "\1\44\3\uffff\1\44\20\uffff\1\107\1\36\1\37\1\40\1\41\1\42\1\43\1\34\1\35"
    };

    static final short[] dfa_8 = DFA.unpackEncodedString(dfa_8s);
    static final short[] dfa_9 = DFA.unpackEncodedString(dfa_9s);
    static final char[] dfa_10 = DFA.unpackEncodedStringToUnsignedChars(dfa_10s);
    static final char[] dfa_11 = DFA.unpackEncodedStringToUnsignedChars(dfa_11s);
    static final short[] dfa_12 = DFA.unpackEncodedString(dfa_12s);
    static final short[] dfa_13 = DFA.unpackEncodedString(dfa_13s);
    static final short[][] dfa_14 = unpackEncodedStringArray(dfa_14s);

    class DFA23 extends DFA {

        public DFA23(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 23;
            this.eot = dfa_8;
            this.eof = dfa_9;
            this.min = dfa_10;
            this.max = dfa_11;
            this.accept = dfa_12;
            this.special = dfa_13;
            this.transition = dfa_14;
        }
        public String getDescription() {
            return "675:2: ( ( ( (lv_actors_0_0= ruleActors ) ) ( (lv_predicate_1_0= rulePredicate ) ) ( (lv_constraints_2_0= ruleConstraints ) )* ) | ( ( (lv_actors_3_0= ruleActors ) ) ( (lv_auxiliarVerb_4_0= ruleAuxiliaryVerb ) ) ( (lv_negation_5_0= ruleNegation ) )? ( (lv_auxiliarVerb_6_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_7_0= rulePredicate ) ) ( (lv_constraints_8_0= ruleConstraints ) )* ) | ( ( (lv_actors_9_0= ruleActors ) ) ( (lv_auxiliarVerb_10_0= ruleAuxiliaryVerb ) ) ( (lv_negation_11_0= ruleNegation ) )? ( (lv_object_12_0= rulePredicateObject ) )? ( (lv_constraints_13_0= ruleConstraints ) )* ) )";
        }
    }
    static final String dfa_15s = "\73\uffff";
    static final String dfa_16s = "\41\uffff\1\42\20\uffff\6\72\1\uffff\1\42\1\uffff";
    static final String dfa_17s = "\7\4\1\103\20\4\2\5\1\4\1\10\1\4\1\5\2\4\1\10\1\4\2\uffff\10\4\1\10\13\4\1\uffff\1\4\1\uffff";
    static final String dfa_18s = "\1\130\6\10\1\103\20\10\1\31\1\5\3\10\1\31\2\u0090\1\10\1\u00a8\2\uffff\7\116\1\u0090\1\10\5\37\6\u00a8\1\uffff\1\u00a8\1\uffff";
    static final String dfa_19s = "\42\uffff\1\4\1\3\24\uffff\1\1\1\uffff\1\2";
    static final String dfa_20s = "\73\uffff}>";
    static final String[] dfa_21s = {
            "\1\31\3\uffff\1\30\63\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\uffff\1\10\1\11\1\12\1\13\1\14\1\15\5\uffff\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\32",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\34\2\uffff\1\30\20\uffff\1\33",
            "\1\34",
            "\1\31\3\uffff\1\30",
            "\1\35",
            "\1\37\3\uffff\1\36",
            "\1\34\2\uffff\1\30\20\uffff\1\33",
            "\1\42\3\uffff\1\41\20\uffff\1\40\6\43\152\uffff\1\44\1\45\1\46\1\47\1\50\1\51\1\52",
            "\1\42\3\uffff\1\42\21\uffff\6\43\152\uffff\1\44\1\45\1\46\1\47\1\50\1\51\1\52",
            "\1\53",
            "\1\42\3\uffff\1\41\7\uffff\4\42\5\uffff\1\54\6\43\2\42\2\uffff\1\42\1\uffff\35\42\1\uffff\6\42\5\uffff\13\42\60\uffff\1\44\1\45\1\46\1\47\1\50\1\51\1\52\10\uffff\20\42",
            "",
            "",
            "\1\70\3\uffff\1\70\21\uffff\1\62\1\63\1\64\1\65\1\66\1\67\52\uffff\1\55\1\56\1\57\1\60\1\61",
            "\1\70\3\uffff\1\70\21\uffff\1\62\1\63\1\64\1\65\1\66\1\67\52\uffff\1\55\1\56\1\57\1\60\1\61",
            "\1\70\3\uffff\1\70\21\uffff\1\62\1\63\1\64\1\65\1\66\1\67\52\uffff\1\55\1\56\1\57\1\60\1\61",
            "\1\70\3\uffff\1\70\21\uffff\1\62\1\63\1\64\1\65\1\66\1\67\52\uffff\1\55\1\56\1\57\1\60\1\61",
            "\1\70\3\uffff\1\70\21\uffff\1\62\1\63\1\64\1\65\1\66\1\67\52\uffff\1\55\1\56\1\57\1\60\1\61",
            "\1\70\3\uffff\1\70\21\uffff\1\62\1\63\1\64\1\65\1\66\1\67\52\uffff\1\55\1\56\1\57\1\60\1\61",
            "\1\70\3\uffff\1\70\21\uffff\1\62\1\63\1\64\1\65\1\66\1\67\52\uffff\1\55\1\56\1\57\1\60\1\61",
            "\1\42\3\uffff\1\41\20\uffff\1\40\6\43\152\uffff\1\44\1\45\1\46\1\47\1\50\1\51\1\52",
            "\1\71",
            "\1\70\3\uffff\1\70\21\uffff\1\62\1\63\1\64\1\65\1\66\1\67",
            "\1\70\3\uffff\1\70\21\uffff\1\62\1\63\1\64\1\65\1\66\1\67",
            "\1\70\3\uffff\1\70\21\uffff\1\62\1\63\1\64\1\65\1\66\1\67",
            "\1\70\3\uffff\1\70\21\uffff\1\62\1\63\1\64\1\65\1\66\1\67",
            "\1\70\3\uffff\1\70\21\uffff\1\62\1\63\1\64\1\65\1\66\1\67",
            "\1\70\3\uffff\1\70\7\uffff\4\72\14\uffff\2\72\2\uffff\1\72\1\uffff\35\72\1\uffff\6\72\5\uffff\13\72\77\uffff\20\72",
            "\1\70\3\uffff\1\70\7\uffff\4\72\14\uffff\2\72\2\uffff\1\72\1\uffff\35\72\1\uffff\6\72\5\uffff\13\72\77\uffff\20\72",
            "\1\70\3\uffff\1\70\7\uffff\4\72\14\uffff\2\72\2\uffff\1\72\1\uffff\35\72\1\uffff\6\72\5\uffff\13\72\77\uffff\20\72",
            "\1\70\3\uffff\1\70\7\uffff\4\72\14\uffff\2\72\2\uffff\1\72\1\uffff\35\72\1\uffff\6\72\5\uffff\13\72\77\uffff\20\72",
            "\1\70\3\uffff\1\70\7\uffff\4\72\14\uffff\2\72\2\uffff\1\72\1\uffff\35\72\1\uffff\6\72\5\uffff\13\72\77\uffff\20\72",
            "\1\70\3\uffff\1\70\7\uffff\4\72\14\uffff\2\72\2\uffff\1\72\1\uffff\35\72\1\uffff\6\72\5\uffff\13\72\77\uffff\20\72",
            "",
            "\1\42\3\uffff\1\41\7\uffff\4\42\5\uffff\1\54\6\43\2\42\2\uffff\1\42\1\uffff\35\42\1\uffff\6\42\5\uffff\13\42\60\uffff\1\44\1\45\1\46\1\47\1\50\1\51\1\52\10\uffff\20\42",
            ""
    };

    static final short[] dfa_15 = DFA.unpackEncodedString(dfa_15s);
    static final short[] dfa_16 = DFA.unpackEncodedString(dfa_16s);
    static final char[] dfa_17 = DFA.unpackEncodedStringToUnsignedChars(dfa_17s);
    static final char[] dfa_18 = DFA.unpackEncodedStringToUnsignedChars(dfa_18s);
    static final short[] dfa_19 = DFA.unpackEncodedString(dfa_19s);
    static final short[] dfa_20 = DFA.unpackEncodedString(dfa_20s);
    static final short[][] dfa_21 = unpackEncodedStringArray(dfa_21s);

    class DFA38 extends DFA {

        public DFA38(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 38;
            this.eot = dfa_15;
            this.eof = dfa_16;
            this.min = dfa_17;
            this.max = dfa_18;
            this.accept = dfa_19;
            this.special = dfa_20;
            this.transition = dfa_21;
        }
        public String getDescription() {
            return "1041:2: ( ( ( (lv_property_0_0= ruleProperty ) ) ( (lv_modelity_1_0= ruleModality ) ) ( (lv_negation_2_0= ruleNegation ) )? ( (lv_auxiliarVerb_3_0= ruleAuxiliaryVerb ) )? ( (lv_predicate_4_0= rulePredicate ) ) ( (lv_constraints_5_0= ruleConstraints ) )* ) | ( ( (lv_property_6_0= ruleProperty ) ) ( (lv_modelity_7_0= ruleModality ) ) ( (lv_negation_8_0= ruleNegation ) )? ( (lv_auxiliarVerb_9_0= ruleAuxiliaryVerb ) ) ( (lv_object_10_0= rulePredicateObject ) )? ( (lv_constraints_11_0= ruleConstraints ) )* ) | ( ( (lv_property_12_0= ruleProperty ) ) ( (lv_auxiliarVerb_13_0= ruleAuxiliaryVerb ) ) ( (lv_negation_14_0= ruleNegation ) )? ( ( ( ( (lv_predicate_15_0= rulePredicate ) ) | ( (lv_object_16_0= rulePredicateObject ) ) ) ( (lv_constraints_17_0= ruleConstraints ) )* ) | ( (lv_constraints_18_0= ruleConstraints ) )+ ) ) | ( ( (lv_property_19_0= ruleProperty ) ) ( ( (lv_predicateWord_20_0= ruleWORD ) ) | ( (lv_predicateWord_21_0= RULE_STRING ) ) ) ( (lv_object_22_0= rulePredicateObject ) )? ( (lv_constraints_23_0= ruleConstraints ) )* ) )";
        }
    }
    static final String dfa_22s = "\10\uffff";
    static final String dfa_23s = "\1\1\1\uffff\1\4\1\uffff\1\7\1\uffff\1\4\1\uffff";
    static final String dfa_24s = "\1\4\1\uffff\1\4\1\10\1\0\1\uffff\1\4\1\uffff";
    static final String dfa_25s = "\1\u0090\1\uffff\1\u00a8\1\10\1\0\1\uffff\1\u00a8\1\uffff";
    static final String dfa_26s = "\1\uffff\1\2\3\uffff\1\1\1\uffff\1\1";
    static final String dfa_27s = "\10\uffff}>";
    static final String[] dfa_28s = {
            "\1\1\3\uffff\1\2\21\uffff\6\1\152\uffff\7\1",
            "",
            "\1\5\3\uffff\1\5\7\uffff\4\1\5\uffff\1\3\6\5\2\1\2\uffff\1\1\1\uffff\35\1\1\uffff\6\1\5\uffff\13\1\60\uffff\7\5\10\uffff\20\1",
            "\1\6",
            "\1\uffff",
            "",
            "\1\5\3\uffff\1\5\7\uffff\4\1\5\uffff\1\3\6\5\2\1\2\uffff\1\1\1\uffff\35\1\1\uffff\6\1\5\uffff\13\1\60\uffff\7\5\10\uffff\20\1",
            ""
    };

    static final short[] dfa_22 = DFA.unpackEncodedString(dfa_22s);
    static final short[] dfa_23 = DFA.unpackEncodedString(dfa_23s);
    static final char[] dfa_24 = DFA.unpackEncodedStringToUnsignedChars(dfa_24s);
    static final char[] dfa_25 = DFA.unpackEncodedStringToUnsignedChars(dfa_25s);
    static final short[] dfa_26 = DFA.unpackEncodedString(dfa_26s);
    static final short[] dfa_27 = DFA.unpackEncodedString(dfa_27s);
    static final short[][] dfa_28 = unpackEncodedStringArray(dfa_28s);

    class DFA42 extends DFA {

        public DFA42(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 42;
            this.eot = dfa_22;
            this.eof = dfa_23;
            this.min = dfa_24;
            this.max = dfa_25;
            this.accept = dfa_26;
            this.special = dfa_27;
            this.transition = dfa_28;
        }
        public String getDescription() {
            return "()+ loopback of 1644:4: ( (lv_property_6_0= ruleWORD ) )+";
        }
    }
    static final String dfa_29s = "\6\uffff";
    static final String dfa_30s = "\1\uffff\1\2\3\uffff\1\2";
    static final String dfa_31s = "\1\4\1\10\1\uffff\1\10\1\uffff\1\10";
    static final String dfa_32s = "\1\10\1\131\1\uffff\1\10\1\uffff\1\131";
    static final String dfa_33s = "\2\uffff\1\2\1\uffff\1\1\1\uffff";
    static final String dfa_34s = "\6\uffff}>";
    static final String[] dfa_35s = {
            "\1\2\3\uffff\1\1",
            "\1\2\11\uffff\1\2\6\uffff\1\3\6\uffff\2\2\2\uffff\1\2\1\uffff\35\2\1\uffff\6\2\5\4\13\2",
            "",
            "\1\5",
            "",
            "\1\2\11\uffff\1\2\6\uffff\1\3\6\uffff\2\2\2\uffff\1\2\1\uffff\35\2\1\uffff\6\2\5\4\13\2"
    };

    static final short[] dfa_29 = DFA.unpackEncodedString(dfa_29s);
    static final short[] dfa_30 = DFA.unpackEncodedString(dfa_30s);
    static final char[] dfa_31 = DFA.unpackEncodedStringToUnsignedChars(dfa_31s);
    static final char[] dfa_32 = DFA.unpackEncodedStringToUnsignedChars(dfa_32s);
    static final short[] dfa_33 = DFA.unpackEncodedString(dfa_33s);
    static final short[] dfa_34 = DFA.unpackEncodedString(dfa_34s);
    static final short[][] dfa_35 = unpackEncodedStringArray(dfa_35s);

    class DFA47 extends DFA {

        public DFA47(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 47;
            this.eot = dfa_29;
            this.eof = dfa_30;
            this.min = dfa_31;
            this.max = dfa_32;
            this.accept = dfa_33;
            this.special = dfa_34;
            this.transition = dfa_35;
        }
        public String getDescription() {
            return "1898:4: ( ( (lv_auxiliar_6_0= ruleWORD ) ) ( (lv_negation_7_0= ruleNegation ) ) )?";
        }
    }
    static final String dfa_36s = "\75\uffff";
    static final String dfa_37s = "\7\4\1\103\23\4\1\10\2\4\2\uffff\7\4\1\103\23\4\1\10\1\4";
    static final String dfa_38s = "\1\130\6\10\1\103\20\10\2\u0090\2\10\2\130\2\uffff\1\u0090\6\10\1\103\20\10\2\u0090\2\10\1\u0090";
    static final String dfa_39s = "\36\uffff\1\2\1\1\35\uffff";
    static final String dfa_40s = "\75\uffff}>";
    static final String[] dfa_41s = {
            "\1\31\3\uffff\1\30\63\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\uffff\1\10\1\11\1\12\1\13\1\14\1\15\5\uffff\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\32",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\31\3\uffff\1\30",
            "\1\36\3\uffff\1\36\20\uffff\1\33\6\36\1\34\1\35\150\uffff\7\37",
            "\1\36\3\uffff\1\36\21\uffff\6\36\1\34\1\35\150\uffff\7\37",
            "\1\31\3\uffff\1\30",
            "\1\40",
            "\1\71\3\uffff\1\70\63\uffff\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\uffff\1\50\1\51\1\52\1\53\1\54\1\55\5\uffff\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67",
            "\1\71\3\uffff\1\70\63\uffff\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\uffff\1\50\1\51\1\52\1\53\1\54\1\55\5\uffff\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67",
            "",
            "",
            "\1\36\3\uffff\1\36\20\uffff\1\33\6\36\1\34\1\35\150\uffff\7\37",
            "\1\71\3\uffff\1\70",
            "\1\71\3\uffff\1\70",
            "\1\71\3\uffff\1\70",
            "\1\71\3\uffff\1\70",
            "\1\71\3\uffff\1\70",
            "\1\71\3\uffff\1\70",
            "\1\72",
            "\1\71\3\uffff\1\70",
            "\1\71\3\uffff\1\70",
            "\1\71\3\uffff\1\70",
            "\1\71\3\uffff\1\70",
            "\1\71\3\uffff\1\70",
            "\1\71\3\uffff\1\70",
            "\1\71\3\uffff\1\70",
            "\1\71\3\uffff\1\70",
            "\1\71\3\uffff\1\70",
            "\1\71\3\uffff\1\70",
            "\1\71\3\uffff\1\70",
            "\1\71\3\uffff\1\70",
            "\1\71\3\uffff\1\70",
            "\1\71\3\uffff\1\70",
            "\1\71\3\uffff\1\70",
            "\1\71\3\uffff\1\70",
            "\1\36\3\uffff\1\36\20\uffff\1\73\6\36\1\34\1\35\150\uffff\7\37",
            "\1\36\3\uffff\1\36\21\uffff\6\36\1\34\1\35\150\uffff\7\37",
            "\1\71\3\uffff\1\70",
            "\1\74",
            "\1\36\3\uffff\1\36\20\uffff\1\73\6\36\1\34\1\35\150\uffff\7\37"
    };

    static final short[] dfa_36 = DFA.unpackEncodedString(dfa_36s);
    static final char[] dfa_37 = DFA.unpackEncodedStringToUnsignedChars(dfa_37s);
    static final char[] dfa_38 = DFA.unpackEncodedStringToUnsignedChars(dfa_38s);
    static final short[] dfa_39 = DFA.unpackEncodedString(dfa_39s);
    static final short[] dfa_40 = DFA.unpackEncodedString(dfa_40s);
    static final short[][] dfa_41 = unpackEncodedStringArray(dfa_41s);

    class DFA49 extends DFA {

        public DFA49(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 49;
            this.eot = dfa_36;
            this.eof = dfa_36;
            this.min = dfa_37;
            this.max = dfa_38;
            this.accept = dfa_39;
            this.special = dfa_40;
            this.transition = dfa_41;
        }
        public String getDescription() {
            return "1998:4: ( ( (lv_clause_11_0= ruleModalitySentence ) ) | ( (lv_clause_12_0= rulePredicateSentence ) ) )";
        }
    }
    static final String dfa_42s = "\7\uffff";
    static final String dfa_43s = "\1\uffff\1\4\4\uffff\1\4";
    static final String dfa_44s = "\1\4\1\10\1\uffff\1\10\2\uffff\1\10";
    static final String dfa_45s = "\1\10\1\u00a8\1\uffff\1\10\2\uffff\1\u00a8";
    static final String dfa_46s = "\2\uffff\1\2\1\uffff\1\1\1\3\1\uffff";
    static final String dfa_47s = "\7\uffff}>";
    static final String[] dfa_48s = {
            "\1\2\3\uffff\1\1",
            "\1\1\7\uffff\4\4\5\uffff\1\3\6\uffff\2\4\2\uffff\1\4\1\uffff\26\4\7\5\1\uffff\6\5\5\uffff\12\5\1\4\77\uffff\20\4",
            "",
            "\1\6",
            "",
            "",
            "\1\1\7\uffff\4\4\5\uffff\1\3\6\uffff\2\4\2\uffff\1\4\1\uffff\26\4\7\5\1\uffff\6\5\5\uffff\12\5\1\4\77\uffff\20\4"
    };

    static final short[] dfa_42 = DFA.unpackEncodedString(dfa_42s);
    static final short[] dfa_43 = DFA.unpackEncodedString(dfa_43s);
    static final char[] dfa_44 = DFA.unpackEncodedStringToUnsignedChars(dfa_44s);
    static final char[] dfa_45 = DFA.unpackEncodedStringToUnsignedChars(dfa_45s);
    static final short[] dfa_46 = DFA.unpackEncodedString(dfa_46s);
    static final short[] dfa_47 = DFA.unpackEncodedString(dfa_47s);
    static final short[][] dfa_48 = unpackEncodedStringArray(dfa_48s);

    class DFA56 extends DFA {

        public DFA56(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 56;
            this.eot = dfa_42;
            this.eof = dfa_43;
            this.min = dfa_44;
            this.max = dfa_45;
            this.accept = dfa_46;
            this.special = dfa_47;
            this.transition = dfa_48;
        }
        public String getDescription() {
            return "2205:2: ( ( (lv_predicates_0_0= ruleWORD ) )+ | ( (lv_predicates_1_0= RULE_STRING ) ) | ( ( (lv_predicates_2_0= ruleWORD ) )+ ( (lv_object_3_0= rulePredicateObject ) ) ) )";
        }
    }
    static final String dfa_49s = "\1\1\1\uffff\1\4\2\uffff\1\4";
    static final String dfa_50s = "\1\4\1\uffff\1\4\1\10\1\uffff\1\4";
    static final String dfa_51s = "\1\130\1\uffff\1\130\1\10\1\uffff\1\130";
    static final String dfa_52s = "\1\uffff\1\2\2\uffff\1\1\1\uffff";
    static final String[] dfa_53s = {
            "\1\1\3\uffff\1\2\63\uffff\7\1\1\uffff\6\1\5\uffff\12\1",
            "",
            "\1\4\3\uffff\1\4\11\uffff\1\1\6\uffff\1\3\6\uffff\2\1\32\uffff\7\4\1\uffff\6\4\5\uffff\12\4",
            "\1\5",
            "",
            "\1\4\3\uffff\1\4\11\uffff\1\1\6\uffff\1\3\6\uffff\2\1\32\uffff\7\4\1\uffff\6\4\5\uffff\12\4"
    };
    static final short[] dfa_49 = DFA.unpackEncodedString(dfa_49s);
    static final char[] dfa_50 = DFA.unpackEncodedStringToUnsignedChars(dfa_50s);
    static final char[] dfa_51 = DFA.unpackEncodedStringToUnsignedChars(dfa_51s);
    static final short[] dfa_52 = DFA.unpackEncodedString(dfa_52s);
    static final short[][] dfa_53 = unpackEncodedStringArray(dfa_53s);

    class DFA60 extends DFA {

        public DFA60(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 60;
            this.eot = dfa_29;
            this.eof = dfa_49;
            this.min = dfa_50;
            this.max = dfa_51;
            this.accept = dfa_52;
            this.special = dfa_34;
            this.transition = dfa_53;
        }
        public String getDescription() {
            return "()+ loopback of 2413:4: ( ruleWORD )+";
        }
    }
    static final String dfa_54s = "\40\uffff";
    static final String dfa_55s = "\35\uffff\1\36\2\uffff";
    static final String dfa_56s = "\2\44\33\4\1\20\2\uffff";
    static final String dfa_57s = "\1\131\1\73\33\130\1\u00a8\2\uffff";
    static final String dfa_58s = "\36\uffff\1\2\1\1";
    static final String dfa_59s = "\40\uffff}>";
    static final String[] dfa_60s = {
            "\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\35\uffff\1\1",
            "\1\30\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\12\uffff\1\31\1\32\1\33\1\34\26\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\44\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\44\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\44\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\1\36\1\uffff\1\35\2\36\14\uffff\1\36\1\uffff\1\36\44\uffff\7\36\1\uffff\6\36\5\uffff\12\36",
            "\4\36\14\uffff\2\36\2\uffff\1\36\1\uffff\26\36\35\uffff\1\36\4\uffff\27\36\25\37\17\uffff\20\36",
            "",
            ""
    };

    static final short[] dfa_54 = DFA.unpackEncodedString(dfa_54s);
    static final short[] dfa_55 = DFA.unpackEncodedString(dfa_55s);
    static final char[] dfa_56 = DFA.unpackEncodedStringToUnsignedChars(dfa_56s);
    static final char[] dfa_57 = DFA.unpackEncodedStringToUnsignedChars(dfa_57s);
    static final short[] dfa_58 = DFA.unpackEncodedString(dfa_58s);
    static final short[] dfa_59 = DFA.unpackEncodedString(dfa_59s);
    static final short[][] dfa_60 = unpackEncodedStringArray(dfa_60s);

    class DFA67 extends DFA {

        public DFA67(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 67;
            this.eot = dfa_54;
            this.eof = dfa_55;
            this.min = dfa_56;
            this.max = dfa_57;
            this.accept = dfa_58;
            this.special = dfa_59;
            this.transition = dfa_60;
        }
        public String getDescription() {
            return "2644:2: ( ( (lv_timeConstraint_0_0= ruleTimeConstraint ) ) | ( (lv_constraint_1_0= ruleConstraint ) ) )";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x000000000001C142L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000018140L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000018000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0xF000000000100110L,0x0000000001FF83F7L,0x000001FFFFFE0000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x00000000000C0000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000040002L,0x0000000000000000L,0x000001FFFE000000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000001FFFE000000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000300000002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000000L,0x000000000001FC00L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x00000000FC000110L,0x0000000000007C00L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0FFFFFD000000002L,0x0000000002000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x00000000FC000000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0xFFFFFFD000000002L,0x0000000003FFFFF7L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0xFFFFFFD000000002L,0x0000000003FF83F7L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0xF000000000000110L,0x0000000001FF83F7L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000000000L,0x000000003C200000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x00000000FC000000L,0x0000000000007C00L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0xFFFFFFD0FC000110L,0x0000000003FFFFF7L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000000110L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000000120L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000000000000L,0x0000000000007C00L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0xF000000000000100L,0x0000000001FF83F7L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000000000110L,0x0000000000000000L,0x0000000001FE0000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0xF000000000A001D0L,0x0000000001FF83F7L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0FFFFFD000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000003C00000002L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000000000000L,0xFFE0000000000000L,0x00000000000003FFL});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x00000000000000C0L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000000001020000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000000000000002L,0x001FFFFFC0000000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000000000010042L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});

}