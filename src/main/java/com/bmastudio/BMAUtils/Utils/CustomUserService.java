/**
 * This project @copyright by Lucas Vall
 * Create by user
 * Create date: Sep 10, 2012
 */
package com.bmastudio.BMAUtils.Utils;

import java.util.ArrayList;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bmastudio.dotbrandtools.dao.UserDAO;

/**
 * 
 * @author Vu Hoai Nam
 *
 * Create date: Sep 3, 2012
 * <p>Description: This class using for logging security
 */
public class CustomUserService implements UserDetailsService { 

	/**
	 * This function check user login with username and password
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		UserDetails user = null;
		
		try{
			UserDAO userDao = new UserDAO();
			com.bmastudio.dotbrandtools.data.User userLogin = userDao.getUserByName(username);
			ArrayList<GrantedAuthority> listAuthor = new ArrayList<GrantedAuthority>();
			
			String[] roles = userLogin.getRoles();
			for (int i = 0; i < roles.length; i++) {
				listAuthor.add(new GrantedAuthorityImpl(roles[i]));
			}
			user = new User(userLogin.getUsername(), userLogin.getPassword() , listAuthor);
		}
		catch( Exception ex ){
			ex.printStackTrace();
			throw new UsernameNotFoundException(ex.getMessage());
		}
		return user;
	}

}
