package com.dcone.dtss.DAO;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.*;

public class UserDAO {

	/**
	 * 					ͨ��һ��itcode��ѯ���û�
	 * @param itcode	����һ��Ա����	
	 * @param jdbcTemplate Spring����
	 * @return 			����һ��dc_user����user
	 */
	public static dc_user getUserByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		dc_user user = null;
		try {
			
			user = jdbcTemplate.queryForObject("select * from dc_user where itcode=?", user_mapper, itcode);
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;	
	}
	/**
	 * 					ͨ��һ��uid��ѯ���û�
	 * @param uid		����һ��Ա����id
	 * @param jdbcTemplate Spring����
	 * @return			����һ��dc_user����user
	 */
	public static dc_user getUserByUid(int uid,JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		dc_user user = null;
		try {
			user=jdbcTemplate.queryForObject("select * from dc_user where uid=?", user_mapper, uid);
		} catch (Exception e) {
			// TODO: handle exception
		}	
		return user;
	}
	/**
	 *  				���ڴ���һ���µ��û�
	 * @param itcode	�û���Ա����
	 * @param username	�û�������
	 * @param isOnthescene	�û��Ƿ����ֳ�
	 * @param jdbcTemplate  Spring����
	 * @return ����1��ʾ�����ɹ�������0��ʾ�������󣬷���-1��ʾ�û���Ϣ����
	 */
	public static int createUser(String itcode,String username,int isOnthescene,JdbcTemplate jdbcTemplate) {
		int i=0;
		try {
			 i = jdbcTemplate.update("insert into dc_user values(null, ?,?,0,?);", new Object[] {itcode,username,isOnthescene});
			if(i>0)
			 return 1;
		} catch (Exception e) {
			return -1;
			// TODO: handle exception
		}	
		return 0;
//		int i=0;
//		if(jdbcTemplate==null)
//		{
//			return 233;
//		}
//		else
//			{
//			i = jdbcTemplate.update("insert into dc_user values(null, ?,?,0,?);", new Object[] {itcode,username,isOnthescene});
//			if(i>0)
//				return 1;
//		return 0;
//		}
	}
	
	/**
	 * �鿴�û�����Ϣ�Ƿ����
	 * @param itcode �����û���Ա����
	 * @param username �����û�������
	 * @param jdbcTemplate Spring����
	 * @return ����true��ʾ���ڣ�����false��ʾ������
	 */
	public static boolean checkUserInfo(String itcode,String username,JdbcTemplate jdbcTemplate) {
		int i=0;
		try {
			i = jdbcTemplate.queryForInt("select count(*) from dc_user where itcode=? and username=?",new Object[] {itcode,username});
			if(i>0)
			{
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	/**
	 * @param uid ͨ���û���id�������û�
	 * @param jdbcTemplate Spring����
	 * @return ����true��ʾ���óɹ���false��ʾʧ��
	 */
	public static boolean lockUserById(int uid,JdbcTemplate jdbcTemplate) {
		int i=0;
		try {
			 i = jdbcTemplate.update("update dc_user set islocked =1 where uid =?;", uid);
			if(i>0)
			 return true;
		} catch (Exception e) {
			return	false;
			// TODO: handle exception
		}	
		return false;
	}
	/**
	 * ����û�������
	 * @param uid �������������û���uid
	 * @param jdbcTemplate Spring����
	 * @return false��ʾ���ʧ�ܣ�true��ʾ����ɹ�
	 */
	public static boolean unlockUserById(int uid,JdbcTemplate jdbcTemplate)
	{
		int i=0;
		try {
			 i = jdbcTemplate.update("update dc_user set islocked =0 where uid =?;", uid);
			if(i>0)
			 return true;
		} catch (Exception e) {
			return	false;
			// TODO: handle exception
		}	
		return false;
	}
	
	/**
	 * ��Ҫ�����ݿ��м�һ�У�islock,�޸����ݿ⣬���ø�model������û�����
	 * @param itcode �û���itcode
	 * @param jdbcTemplate Spring����
	 * @return
	 */
	public static boolean lockUserByItcode(String itcode,JdbcTemplate jdbcTemplate)
	{
		int i=0;
		try {
			 i = jdbcTemplate.update("update dc_user set islocked =1 where itcode =?;", itcode);
			if(i>0)
			 return true;
		} catch (Exception e) {
			return	false;
			// TODO: handle exception
		}	
		return false;
	}
	/**
	 * �ж�һ���û��Ƿ�����
	 * @param uid �����û���uid
	 * @param jdbcTemplate Spring����
	 * @return false��ʾδ������ ��true��ʾ������
	 */
	public static boolean isLock(int uid,JdbcTemplate jdbcTemplate)
	{
		int i=0;
		try {
			i = jdbcTemplate.queryForInt("select islocked from dc_user where uid= ?",uid);
			if(i>0)
			{
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	/**
	 * �鿴�û����ڳ��ڻ��ǳ���
	 * @param uid �����û���uid
	 * @param jdbcTemplate Spring����
	 * @return ����true��ʾ�ڳ��ڣ�false��ʾ���ڳ���
	 */
	public static boolean isOnthesceneById(int uid,JdbcTemplate jdbcTemplate)
	{
		int i=0;
		try {
			i = jdbcTemplate.queryForInt("select isOnthescene from dc_user where uid= ?",uid);
			if(i>0)
			{
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

}
