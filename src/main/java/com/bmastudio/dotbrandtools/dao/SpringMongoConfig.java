/**
 * 
 */
package com.bmastudio.dotbrandtools.dao;

import javax.inject.Singleton;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoOptions;

/**
 * @author Vu Hoai Nam
 * Description: The Base connection configure of dotbrandtools
 */

@Singleton
public class SpringMongoConfig {
	
	/**
	 * 
	 * Create by Vu Hoai Nam
	 * <p>Description: Get the Mongo connection configure.
	 * @return Mongo
	 * @throws Exception
	 */
	private static final Mongo getMongo() throws Exception{
		
		PropertiesConfiguration config = null;
		try {
			config = new PropertiesConfiguration("configure.properties");
			config.setReloadingStrategy(new FileChangedReloadingStrategy());
		} catch (ConfigurationException e) {
			e.printStackTrace();
			throw e;
		}
		//Get connection information from properties
		String host = config.getString("host");
		String port = config.getString("port");
		
		
		//Create connection
		MongoOptions options = new MongoOptions();
		options.setThreadsAllowedToBlockForConnectionMultiplier(100);
		options.connectionsPerHost = 100;
		options.setConnectTimeout(3000);
		
		Mongo mongo = null;
		if( port != null && !port.equalsIgnoreCase("")){
			mongo = new Mongo( host+":"+port , options );
		}
		else
			mongo = new Mongo( host , options );
		
		return mongo;
	}
	
	/**
	 * Create by Vu Hoai Nam
	 * <p>Description: Get the mongoTemplate for execute the action to mongo.
	 * @return MongoTemplate
	 * @throws Exception
	 */
	public static MongoTemplate mongoTemplate() throws Exception {
		
		PropertiesConfiguration config = null;
		try {
			config = new PropertiesConfiguration("configure.properties");
			config.setReloadingStrategy(new FileChangedReloadingStrategy());
		} catch (ConfigurationException e) {
			e.printStackTrace();
			throw e;
		}
		
		String username = config.getString("username");
		String password = config.getString("password");
		String database = config.getString("database");
		
		UserCredentials user = null;
		if( username != null && password != null )
			user = new UserCredentials(username, password);
		
		return new MongoTemplate( getMongo() , database, user);
	}

}
