/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Aug 23, 2012
 */
package com.bmastudio.dotbrandtools.dao;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import com.bmastudio.BMAUtils.Utils.BmaDataUtils;
import com.bmastudio.BMAUtils.Utils.DotBrandToolsConst;
import com.bmastudio.dotbrandtools.data.Folder;
import com.bmastudio.dotbrandtools.data.Profile;
import com.bmastudio.dotbrandtools.data.UserProfile;
import com.bmastudio.dotbrandtools.dataview.ProfileView;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.DBRef;


/**
 * 
 * @author Vu Hoai Nam
 * <p>Description: The Data Access Object handle the data on Profile collection
 */
public class ProfileDAO extends CommonDAO {
	
	private static Logger logger = LoggerFactory.getLogger(FolderDAO.class);
	
	/**
	 * @construction
	 */
	public ProfileDAO() throws Exception {
		super();
	}
	
	/**
	 * 
	 * Create by user
	 * <p>Create date: Aug 24, 2012
	 * @param profile
	 * @return
	 * @throws Exception 
	 * <p>Description: insert a profile to database. 
	 * <p>If the profile include some folders which did not save, this function will save the folders too.
	 */
	public Profile createProfile( Profile profile ) throws Exception {
		
		logger.debug("DATABASE ACCESS BEGIN INSERT :" + profile.toString());
		
		ProfileDAO dao = new  ProfileDAO();
		
		try{
			
			//Get last profile with max id
			Query query = new Query();
			query.sort().on( Profile.PROFILE_ID , Order.DESCENDING);
			query.limit(1);
			Profile lastPro = dao.getMongoOps().findOne( query ,Profile.class);
			
			if( lastPro != null && lastPro.getProfileId() > 0 )
				profile.setProfileId( lastPro.getProfileId() + 1 );
			else
				profile.setProfileId( 1 );
			
			//Insert the folder mapping to profile
			if( profile.getFolders() != null ){
				logger.debug("DATABASE PROCCESSING INSERT LIST PROFILE'S FOLDERS :" + profile.getFolders().toString() );
				dao.getMongoOps().insert( profile.getFolders() , Folder.class );
			}
			
			dao.getMongoOps().insert(profile);
			logger.debug("DATABASE INSERT SUCCESS :" + profile.toString());
		}
		catch (Exception e) {
			logger.error("DATABASE INSERT ERROR : " + profile.toString());
			logger.error("ERRORS: " + e.getMessage() );
			throw e;
		} finally {
			dao.closeConnection();
		}
		
		return profile;
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Create date: Aug 28, 2012
	 * @param profile
	 * @return
	 * @throws Exception
	 * <p>Description: Update a Profile by userId
	 */
	public Profile updateProfile( Profile profile , int userId ) throws Exception {
		
		logger.debug("DATABASE ACCESS BEGIN UPDATE :" + profile.toString());
		
		ProfileDAO dao = new  ProfileDAO();
		
		try{
			//CHECK USER WRITE ROLE ON PROFILE
			DBRef dbRef = new DBRef( dao.getMongoTemplate().getDb() , Profile.DOCUMENT_NAME , new ObjectId( profile.getId() ) );
			boolean isHashRole = hasRole( dbRef, userId, new String[]{ DotBrandToolsConst.ROLE_WRITE });
			if( !isHashRole ){
				throw new Exception(" USER DON'T HAVE ROLE ON THIS PROFILE ");
			}
			
			//UDPATE THE PROFILE
			if( isHashRole && ObjectId.isValid( profile.getId() ) ){
				logger.debug("DATABASE PROCCESSING UPDATE PROFILE :" + profile );
				Update update = new Update();
				update.set(Profile.NAME, profile.getName());
				update.set(Profile.DESCRIPTION, profile.getDescription());
				update.set(Profile.IMAGE_URL, profile.getImageUrl());
				update.set(Profile.UPD_DATE, new Date() );
				update.set(Profile.UPD_USERID, userId);
				dao.getMongoOps().updateFirst(Query.query(Criteria.where(Profile.PROFILE_ID).is( profile.getProfileId()) ),
									 update, Profile.class);
			}
		}
		catch (Exception e) {
			logger.error("DATABASE UPDATE ERROR : " + profile.toString());
			logger.error("ERRORS: " + e.getMessage() );
			throw e;
		}
		finally {
			dao.closeConnection();
		}
		
		logger.debug("DATABASE UPDATE SUCSESSFULL :" + profile.toString());
		
		return profile;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * Create date: Aug 28, 2012
	 * @param profileId
	 * @return true/false
	 * @throws Exception
	 * <p>Description: Instead of delete a profile by profileId, we update the delete flag by true
	 */
	public boolean deleteProfile( int profileId ) throws Exception {
		
		logger.debug("DATABASE ACCESS BEGIN DELETE PROFILE :" + profileId);
		ProfileDAO dao = new  ProfileDAO();
		
		try{
			
			if( profileId != 0 ){
				
				Update updateProfile = new Update();
				updateProfile.set(Profile.DELETE_FLG, true);
				dao.getMongoOps().updateFirst(Query.query(Criteria.where(Profile.PROFILE_ID).is(profileId)) , updateProfile, Profile.class);
				
				
				//Remove the reference of UserProfile
				Update updateUser = new Update();				
				updateUser.set(UserProfile.DELETE_FLG, true);
				
				Profile deleteUpdateProfile = dao.getMongoOps().findOne( Query.query(Criteria.where(Profile.PROFILE_ID).is(profileId)), Profile.class);
				
				DBRef dbref = new DBRef( dao.getMongoTemplate().getDb() , Profile.DOCUMENT_NAME, new ObjectId( deleteUpdateProfile.getId() ));
				
				dao.getMongoOps().updateFirst(Query.query(Criteria.where(UserProfile.PROFILE).is( dbref ) ), updateUser , UserProfile.class );
				
			}
		}
		catch (Exception e) {
			logger.error("DATABASE DELETE PROFILE ERROR : " + profileId);
			logger.error("ERRORS: " + e.getMessage() );
			throw e;
		}
		finally {
			dao.closeConnection();
		}
		
		logger.debug("DATABASE DELETE SUCSESSFULL PROFILE :" + profileId);
		
		return true ;
	}
	
	/**
	 * Get a profile by profileId
	 * @param profileId
	 * @return Profile
	 * @throws Exception 
	 */
	public Profile getProfileById( int profileId ) throws Exception{
		ProfileDAO dao = new  ProfileDAO();
		List<Profile> list = dao.getMongoOps().find(Query.query(Criteria.where( Profile.PROFILE_ID ).is(profileId)),Profile.class);
		dao.closeConnection();
		return list.get(0);
	}
	
	/**
	 * Get a profile by profileId
	 * @param profileId
	 * @return
	 * @throws Exception 
	 */
	public List<ProfileView> getProfileListId( String[] profileIds, int userId ) throws Exception{
		
		List<ProfileView> list = getProfileByUserId(userId);
		
		List<ProfileView> returnList = new ArrayList<ProfileView>();
		for (ProfileView profileView : list) {
			for (String profileId : profileIds) {
				if( BmaDataUtils.isInt( profileId ) && Integer.parseInt(profileId) == profileView.getProfileId() ){
					returnList.add(profileView);
					break;
				}	
			}
		}
		
		return returnList;
	}
	
	/**
	 * Get a profile by profileId
	 * @param profileId
	 * @return
	 * @throws Exception 
	 */
	public List<Profile> getProfileByName( String name ) throws Exception{
		ProfileDAO dao = new  ProfileDAO();
		List<Profile> list = dao.getMongoOps().find(Query.query(Criteria.where(Profile.NAME).is(name)),Profile.class);
		dao.closeConnection();
		return list;
	}
	
	/**
	 * Get profiles by login userId
	 * @param userId
	 * @throws Exception 
	 * <p>Description: This function get list of profile base on userId, when deleteFlag not raised.
	 */
	public List<ProfileView> getProfileByUserId( int userId ) throws Exception{
		
		ProfileDAO dao = new  ProfileDAO();
		List<UserProfile> userProfile = dao.getMongoOps().find(Query.query(Criteria.where(UserProfile.USER_ID).is(userId).
																	       and(UserProfile.ACCESS_ROLES).all(new String[]{DotBrandToolsConst.ROLE_READ}).
																	       and(UserProfile.DELETE_FLG).is(false)),
														UserProfile.class);
		
		
		List<ProfileView> list = new ArrayList<ProfileView>();
		MongoConverter converter = dao.getMongoOps().getConverter();
		DBCollection coll = dao.getMongoTemplate().getDb().getCollection( Profile.DOCUMENT_NAME );
		for (UserProfile user : userProfile) {
			
			try {
				DBObject dbobject = coll.findOne( new BasicDBObject("_id", user.getProfile().getId() ) );
				Profile profile = converter.read( Profile.class, dbobject );
				
				ProfileView profileView = new ProfileView();
				BeanUtils.copyProperties(profile, profileView);
				
				if( ArrayUtils.contains( user.getAccessRoles() , DotBrandToolsConst.ROLE_READ ) ) {
					profileView.setHasRoleRead(true);
				}
				if( ArrayUtils.contains( user.getAccessRoles() , DotBrandToolsConst.ROLE_WRITE ) ) {
					profileView.setHasRoleWrite(true);
				}
				if( ArrayUtils.contains( user.getAccessRoles() , DotBrandToolsConst.ROLE_DELETE ) ) {
					profileView.setHasRoleDelete(true);
				}
				
				list.add( profileView );
				
			} catch (Exception e) {
				//getMongoOps().remove(user);
				logger.error("DATABASE MAPPING ERROR : user " + user);
				logger.error("ERRORS: " + e.getMessage() );
			}
				
		}
		dao.closeConnection();
		
		Collections.sort(list);
		
		return list;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * <p>Description: Checking whether the userId have Role on profile
	 * @param profile
	 * @param userId
	 * @param roles
	 * @return true/false
	 * @throws Exception
	 */
	public boolean hasRole( DBRef profile, int userId , String[] roles ) throws Exception{
		
		ProfileDAO dao = new  ProfileDAO();
		
		try {
			logger.debug("DATABASE CHECK ROLE ON PROFILE : userId " + userId + "Profile" + profile);
			
			double count = dao.getMongoOps().count(Query.query( Criteria.where(UserProfile.USER_ID).is(userId).
																	  and(UserProfile.PROFILE).is(profile).
																	  and(UserProfile.ACCESS_ROLES).all(roles).
																	  and(UserProfile.DELETE_FLG).is(false) ),
														UserProfile.class);
			if( count > 0 )
				return true;
		} catch (Exception e) {
			logger.error("DATABASE CHECK ROLE ON PROFILE ERROR : userId " + userId + "Profile" + profile);
			logger.error("ERRORS: " + e.getMessage() );
			throw e;
		}
		finally {
			dao.closeConnection();
		}
		
		return false;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * Create date: Sep 3, 2012
	 * @return
	 * @throws Exception 
	 * <p>Description: Clean the not mapping reference in UserProfile
	 */
	public boolean cleanUserProfileMapping() throws Exception{
		
		ProfileDAO dao = new  ProfileDAO();
		
		List<UserProfile> userProfile = dao.getMongoOps().findAll(UserProfile.class);
		MongoConverter converter = dao.getMongoOps().getConverter();
		DBCollection coll = dao.getMongoTemplate().getDb().getCollection( Profile.DOCUMENT_NAME );
		
		for (UserProfile user : userProfile) {
			try {
				DBObject dbobject = coll.findOne( new BasicDBObject("_id", user.getProfile().getId() ) );
				converter.read( Profile.class, dbobject );
			
			} catch (Exception e) {
				dao.getMongoOps().remove( user );
				logger.error("DATABASE MAPPING ERROR : user " + user);
				logger.error("ERRORS: " + e.getMessage() );
				
			}
			
		}
		
		dao.closeConnection();
		
		return true;
	}
	
}
