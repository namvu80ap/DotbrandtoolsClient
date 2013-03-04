/**
 * 
 */
package com.bmastudio.dotbrandtools.data;

import java.util.Arrays;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author Vu Hoai Nam
 *
 * Create date: Sep 10, 2012
 * <p>Description: The Data Object mapping with ProfileRules
 */
@Document
public class ProfileRules {
	
	/**
	 * Collection name is profileRules
	 */
	public static final String DOCUMENT_NAME = "profileRules" ;
	
	/*
	 * DECLARE THE FIELDS NAME OF DOCUMENT OBJECT
	 */
	/**
	 * Document field: profileId
	 */
	public static final String PROFILE_ID = "profileId" ;
	/**
	 * Document field: profileRulesId
	 */
	public static final String PROFILE_RULES_ID = "profileRulesId" ;
	/**
	 * Document field: mustStartWith
	 */
	public static final String MUST_START_WITH = "mustStartWith" ;
	/**
	 * Document field: mustStartFlg
	 */
	public static final String MUST_START_FLG = "mustStartFlg" ;
	/**
	 * Document field: mustEndWith
	 */
	public static final String MUST_END_WITH = "mustEndWith";
	/**
	 * Document field: mustEndFlg
	 */
	public static final String MUST_END_FLG = "mustEndFlg";
	/**
	 * Document field: condition
	 */
	public static final String CONDITION = "condition" ;
	/**
	 * Document field: lenght
	 */
	public static final String LENGTH = "lenght" ;
	/**
	 * Document field: mustLengthFlg
	 */
	public static final String MUST_LENGTH_FLG = "mustLengthFlg";
	/**
	 * Document field: delFlag
	 */
	public static final String DELETE_FLG = "delFlag" ;
	
	
	@Id
	private String id;
	
	private int profileId;
	
	@Indexed
	private int profileRulesId;
	
	private String[] mustStartWith;
	
	private  boolean mustStartFlg;
	
	private String[] mustEndWith;
	
	private  boolean mustEndFlg;
	
	private int lenght;
	
	private boolean mustLengthFlg;
	
	private String condition;
	
	private int creUserId;
	
	private Date creDate;
	
	private int updUserId;
	
	private Date updDate;
	
	private boolean delFlag;

	
	public boolean isMustLengthFlg() {
		return mustLengthFlg;
	}

	public void setMustLengthFlg(boolean mustLengthFlg) {
		this.mustLengthFlg = mustLengthFlg;
	}

	public boolean isMustStartFlg() {
		return mustStartFlg;
	}

	public void setMustStartFlg(boolean mustStartFlg) {
		this.mustStartFlg = mustStartFlg;
	}

	public boolean isMustEndFlg() {
		return mustEndFlg;
	}

	public void setMustEndFlg(boolean mustEndFlg) {
		this.mustEndFlg = mustEndFlg;
	}

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public int getProfileRulesId() {
		return profileRulesId;
	}

	public void setProfileRulesId(int profileRulesId) {
		this.profileRulesId = profileRulesId;
	}

	public String[] getMustStartWith() {
		return mustStartWith;
	}

	public void setMustStartWith(String[] mustStartWith) {
		this.mustStartWith = mustStartWith;
	}

	public String[] getMustEndWith() {
		return mustEndWith;
	}

	public void setMustEndWith(String[] mustEndWith) {
		this.mustEndWith = mustEndWith;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getCreUserId() {
		return creUserId;
	}

	public void setCreUserId(int creUserId) {
		this.creUserId = creUserId;
	}

	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public int getUpdUserId() {
		return updUserId;
	}

	public void setUpdUserId(int updUserId) {
		this.updUserId = updUserId;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	@Override
	public String toString() {
		return "ProfileRules [profileId=" + profileId + ", profileRulesId="
				+ profileRulesId + ", mustStartWith="
				+ Arrays.toString(mustStartWith) + ", mustEndWith="
				+ Arrays.toString(mustEndWith) + ", condition=" + condition
				+ ", delFlag=" + delFlag + "]";
	}
	
	public ProfileRules() {
		super();
	}

	public ProfileRules(int profileId, int profileRulesId,
			String[] mustStartWith, boolean mustStartFlg, String[] mustEndWith,
			boolean mustEndFlg, int lenght , boolean mustLengthFlg , String condition, int creUserId,
			Date creDate, int updUserId, Date updDate) {
		super();
		this.profileId = profileId;
		this.profileRulesId = profileRulesId;
		this.mustStartWith = mustStartWith;
		this.mustStartFlg = mustStartFlg;
		this.mustEndWith = mustEndWith;
		this.mustEndFlg = mustEndFlg;
		this.lenght = lenght;
		this.mustLengthFlg = mustLengthFlg;
		this.condition = condition;
		this.creUserId = creUserId;
		this.creDate = creDate;
		this.updUserId = updUserId;
		this.updDate = updDate;
	}

	
	
	
	
}
