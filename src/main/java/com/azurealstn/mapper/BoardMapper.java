package com.azurealstn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.azurealstn.beans.ContentBean;

public interface BoardMapper {
	
	@SelectKey(statement = "SELECT content_seq.nextval FROM DUAL", keyProperty = "content_idx", before = true, resultType = int.class)
	

	@Insert("INSERT INTO CONTENT_TABLE(CONTENT_IDX, CONTENT_SUBJECT, CONTENT_TEXT, CONTENT_FILE, CONTENT_WRITER_IDX, CONTENT_BOARD_IDX, CONTENT_DATE) "
			+ "VALUES (#{content_idx}, #{content_subject}, #{content_text}, #{content_file, jdbcType=VARCHAR}, #{content_writer_idx}, #{content_board_idx}, sysdate)")
	void addContentInfo(ContentBean writeContentBean);
	
	@Select("SELECT BOARD_INFO_NAME FROM BOARD_INFO_TABLE WHERE BOARD_INFO_IDX = #{board_info_idx}")
	String getBoardInfoName(int board_info_idx);
	
	@Select("SELECT T1.CONTENT_IDX, T1.CONTENT_SUBJECT, T2.USER_NAME AS CONTENT_WRITER_NAME, TO_CHAR(T1.CONTENT_DATE, 'YYYY-MM-DD') AS CONTENT_DATE "
			+ "FROM CONTENT_TABLE T1 "
			+ "INNER JOIN USER_TABLE T2 ON T1.CONTENT_WRITER_IDX = T2.USER_IDX " 
			+ "ORDER BY T1.CONTENT_IDX DESC")
	List<ContentBean> getContentList(int board_info_idx, RowBounds rowBounds);
	
	@Select("SELECT T2.USER_NAME AS CONTENT_WRITER_NAME, TO_CHAR(T1.CONTENT_DATE, 'YYYY-MM-DD') AS CONTENT_DATE, "
			+ "T1.CONTENT_SUBJECT, T1.CONTENT_FILE, T1.CONTENT_TEXT, T1.CONTENT_WRITER_IDX "
			+ "FROM CONTENT_TABLE T1 "
			+ "INNER JOIN USER_TABLE T2 ON T1.CONTENT_WRITER_IDX = T2.USER_IDX "
			+ "WHERE T1.CONTENT_IDX = #{content_idx}")
	ContentBean getContentInfo(int content_idx);
	
	@Update("UPDATE CONTENT_TABLE "
			+ "SET CONTENT_SUBJECT = #{content_subject}"
			+ ",	CONTENT_TEXT = #{content_text}"
			+ ",	CONTENT_FILE = #{content_file, jdbcType=VARCHAR}"
			+ "WHERE CONTENT_IDX = #{content_idx}")
	void modifyContentInfo(ContentBean modifyContentBean);
	
	@Delete("DELETE FROM CONTENT_TABLE WHERE CONTENT_IDX = #{content_idx}")
	void deleteContentInfo(int content_idx);
	
	@Select("SELECT count(*) FROM CONTENT_TABLE WHERE CONTENT_BOARD_IDX = #{content_board_idx}")
	int getContentCnt(int content_board_idx);
}
