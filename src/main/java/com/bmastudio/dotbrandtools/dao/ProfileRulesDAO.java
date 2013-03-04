/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Sep 10, 2012
 */
package com.bmastudio.dotbrandtools.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.bmastudio.dotbrandtools.data.ProfileRules;

/**
 * @author Vu Hoai Nam
 *
 * Create date: Sep 10, 2012
 * <p>Description: The Data Access Object handle the data on ProfileRules collection
 */
public class ProfileRulesDAO extends CommonDAO {

	private static Logger logger = LoggerFactory.getLogger(ProfileRulesDAO.class);
	
	/**
	 * @Constructor
	 * @throws Exception
	 */
	public ProfileRulesDAO() throws Exception {
		super();
	}
	
	/**
	 * 
	 * Create by user
	 * Create date: Aug 24, 2012
	 * @param item
	 * @return
	 * @throws Exception
	 * <p>Description: Insert an item to database. If exists itemTypes, this function will insert the itemType too.
	 */
	public ProfileRules createProfileRules( ProfileRules profileRules ) throws Exception {
		
		logger.debug("DATABASE ACCESS BEGIN INSERT :" + profileRules.toString());
		
		ProfileRulesDAO dao = new ProfileRulesDAO();
		
		try{
			//GET LAST PROFILERULES ID
			//Get last folder with max id
			Query query = new Query();
			query.sort().on( ProfileRules.PROFILE_RULES_ID , Order.DESCENDING);
			query.limit(1);
			
			ProfileRules lastProfileRule = dao.getMongoOps().findOne( query ,ProfileRules.class);
			
			if( lastProfileRule != null && lastProfileRule.getProfileRulesId() > 0 )
				profileRules.setProfileRulesId( lastProfileRule.getProfileRulesId() + 1 );
			else
				profileRules.setProfileRulesId( 1 );
			
			dao.getMongoOps().insert( profileRules );
			
			logger.debug("DATABASE INSERT SUCCESS :" + profileRules.toString());
		}
		catch (Exception e) {
			logger.error("DATABASE INSERT ERROR : " + profileRules.toString());
			logger.error("ERRORS: " + e.getMessage() );
			throw e;
		}finally {
			dao.closeConnection();
		}
		
		return profileRules;
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description: Update the profileRules to in collection.
	 * @param profileRules
	 * @return
	 * @throws Exception
	 */
	public ProfileRules updateProfileRules( ProfileRules profileRules ) throws Exception {
		
		logger.debug("DATABASE ACCESS BEGIN INSERT :" + profileRules.toString());
		
		ProfileRulesDAO dao = new ProfileRulesDAO();
		
		try{
			//Create udpate statement
			Update update = new Update();
			update.set( ProfileRules.MUST_START_WITH,  profileRules.getMustStartWith() );
			update.set( ProfileRules.MUST_START_FLG,  profileRules.isMustStartFlg() );
			
			update.set( ProfileRules.MUST_END_WITH,  profileRules.getMustEndWith() );
			update.set( ProfileRules.MUST_END_FLG,  profileRules.isMustEndFlg() );
			
			update.set( ProfileRules.CONDITION,  profileRules.getCondition() );
			update.set( ProfileRules.LENGTH,  profileRules.getLenght() );
			update.set( ProfileRules.MUST_LENGTH_FLG,  profileRules.isMustLengthFlg() );
			
			dao.getMongoOps().updateFirst( Query.query( Criteria.where(profileRules.PROFILE_ID).is(profileRules.getProfileId())
																		.and(profileRules.PROFILE_RULES_ID).is(profileRules.getProfileRulesId())),
																		update ,ProfileRules.class);
			
			logger.debug("DATABASE INSERT SUCCESS :" + profileRules.toString());
		}
		catch (Exception e) {
			logger.error("DATABASE INSERT ERROR : " + profileRules.toString());
			logger.error("ERRORS: " + e.getMessage() );
			throw e;
		}finally {
			dao.closeConnection();
		}
		
		return profileRules;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * Create date: Sep 10, 2012
	 * @param profileId
	 * @return
	 * @throws Exception
	 * <p>Description: Get the list of ProfileRules
	 */
	public ProfileRules getByProfileId( int profileId ) throws Exception {
		
		logger.debug("GET PROFILERULES BY PROFILE_ID" + profileId);
		
		ProfileRulesDAO dao = new ProfileRulesDAO();
		
		ProfileRules profileRule = dao.getMongoOps().findOne( Query.query( Criteria.where(ProfileRules.PROFILE_ID).is(profileId).and(ProfileRules.DELETE_FLG).is(false) )
													, ProfileRules.class );
		
		dao.closeConnection();
		
		return profileRule;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * Create date: Sep 10, 2012
	 * @param profileId
	 * @return
	 * @throws Exception
	 * <p>Description: Get the list of profileRulesId
	 */
	public ProfileRules getByProfileRulesId( int profileRulesId ) throws Exception {
		
		logger.debug("GET PROFILERULES BY PROFILE_ID" + profileRulesId);
		
		ProfileRulesDAO dao = new ProfileRulesDAO();
		
		ProfileRules profileRule = dao.getMongoOps().findOne( Query.query( Criteria.where(ProfileRules.PROFILE_RULES_ID).is(profileRulesId).and(ProfileRules.DELETE_FLG).is(false) )
													, ProfileRules.class );
		
		dao.closeConnection();
		
		return profileRule;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * Create date: Sep 10, 2012
	 * @param profileId
	 * @return
	 * @throws Exception
	 * <p>Description: UPDATE DELETE FLAG TO TRUE INSTEAD OF REMOVE THE DATA
	 */
	public boolean removeByProfileId( int profileId, int profileRuleId ) throws Exception {
		
		logger.debug("REMOVE PROFILERULES BY PROFILE_ID" + profileId);
		
		ProfileRulesDAO dao = new ProfileRulesDAO();
		
		Update update = new Update();
		update.set(ProfileRules.DELETE_FLG, true);
		
		dao.getMongoOps().updateFirst( Query.query( Criteria.where(ProfileRules.PROFILE_ID).is(profileId)
																.and(ProfileRules.PROFILE_RULES_ID).is(profileRuleId)
																.and(ProfileRules.DELETE_FLG).is(false) )
																,update, ProfileRules.class );

		
		dao.closeConnection();
		
		return true;
	}

	/**
	 * Create by Vu Hoai Nam
	 * Create date: Sep 10, 2012
	 * @param profileId
	 * @return boolean
	 * @throws Exception
	 * <p>Description: Check the ProfileRules is exists
	 */
	public boolean isExists( int profileId, int profileRuleId ) throws Exception {
		
		logger.debug("CHECK THE PROFILE RULE EXISTS OR NOT: profileId" + profileId);
		
		ProfileRulesDAO dao = new ProfileRulesDAO();
		
		double count = dao.getMongoOps().count( Query.query( Criteria.where(ProfileRules.PROFILE_ID).is(profileId)
															.and(ProfileRules.PROFILE_RULES_ID).is(profileRuleId)
															.and(ProfileRules.DELETE_FLG).is(false) )
															, ProfileRules.class );
		
		dao.closeConnection();
		
		return (count > 0) ?  true : false;
	}
}
