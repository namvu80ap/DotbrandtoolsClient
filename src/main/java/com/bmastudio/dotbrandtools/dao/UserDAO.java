/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Sep 10, 2012
 */
package com.bmastudio.dotbrandtools.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.bmastudio.dotbrandtools.data.User;
import com.bmastudio.dotbrandtools.data.UserServicesCount;


/**
 * @author Vu Hoai Nam
 * <p>Description: The Data Access Object handle the data on User collection
 */
public class UserDAO extends CommonDAO {
	
	private static Logger logger = LoggerFactory.getLogger(UserDAO.class);
	
	/**
	 * @construction
	 */
	public UserDAO() throws Exception {
		super();
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description: Get User object by username
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public User getUserByName(String username) throws Exception {
		
		UserDAO dao = new UserDAO();
		
		User user = dao.getMongoOps().findOne(Query.query(Criteria.where("username").is(username)),User.class);
		
		dao.closeConnection();
		
		return user;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * Create date: Sep 19, 2012
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 * <p>Description: Authenticate the user who login via URL to use the services
	 */
	public User loginAuthenticate(String username ,String password) throws Exception {
		
		UserDAO dao = new UserDAO();
		
		User user = dao.getMongoOps().findOne(Query.query(Criteria.where(User.USERNAME).is(username)
															      .and(User.PASSWORD).is(password)),User.class);
		
		dao.closeConnection();
		
		return user;
	}
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description: Count (increase) the time user call the services
	 * @param userId
	 * @param serviceName
	 * @param isSuccess
	 * @return true/false
	 * @throws Exception
	 */
	public boolean countUserServices( int userId , String serviceName , boolean isSuccess ) throws Exception {
		
		UserDAO dao = new UserDAO();
		
		try {
			UserServicesCount servicesCount = dao.getMongoOps().findOne( 
					Query.query(Criteria.where(UserServicesCount.USER_ID).is(userId)
					.and(UserServicesCount.SERVICE_NAME).is(serviceName)), UserServicesCount.class);
			
			if( servicesCount == null ){
				servicesCount = new UserServicesCount();
				servicesCount.setUserId(userId);
				servicesCount.setServiceName(serviceName);
				servicesCount.setCount(0);
				servicesCount.setFailureCount(0);
				servicesCount.setSuccessCount(0);
				
			}
			
			Update update = new Update();
			update.set(UserServicesCount.USER_ID, servicesCount.getUserId());
			update.set(UserServicesCount.SERVICE_NAME, servicesCount.getServiceName());
			
			update.set( UserServicesCount.COUNT, servicesCount.getCount() + 1);
			if( isSuccess ){
				update.set( UserServicesCount.SUCCESS_COUNT, servicesCount.getSuccessCount() + 1);
				update.set( UserServicesCount.FAILURE_COUNT, servicesCount.getFailureCount());
			}else{
				update.set( UserServicesCount.FAILURE_COUNT, servicesCount.getFailureCount() + 1);
				update.set( UserServicesCount.SUCCESS_COUNT, servicesCount.getSuccessCount());
			}
			
			dao.getMongoOps().upsert( Query.query(Criteria.where(UserServicesCount.USER_ID).is(userId)
								.and(UserServicesCount.SERVICE_NAME).is(serviceName)), update , UserServicesCount.class);


		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally{
			dao.closeConnection();
		}
		
		return true;
	}
	
}
