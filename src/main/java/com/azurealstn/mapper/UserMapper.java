package com.azurealstn.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.azurealstn.beans.UserBean;

public interface UserMapper {

	@Select("SELECT USER_NAME FROM USER_TABLE WHERE USER_ID = #{user_id}")
	String checkUserIdExist(String user_id);

	@Insert("insert into user_table values(user_seq.nextval, #{user_name}, #{user_id}, #{user_pw})")
	void addUserInfo(UserBean joinUserBean);
	
	@Select("SELECT USER_IDX, USER_NAME FROM USER_TABLE WHERE USER_ID = #{user_id} AND user_pw = #{user_pw}")
	UserBean getLoginUserInfo(UserBean tempLoginUserBean);
	
	@Select("SELECT USER_ID, USER_NAME FROM USER_TABLE WHERE USER_IDX = #{user_idx}")
	UserBean getModifyUserBean(int user_idx);
	
	@Update("UPDATE USER_TABLE SET USER_PW = #{user_pw} WHERE USER_IDX = #{user_idx}")
	void modifyUserInfo(UserBean modifyUserBean);
}
