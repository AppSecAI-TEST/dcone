package com.dcone.dtss.DAO;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.dc_user;
import com.dcone.dtss.model.dc_user_wallet;

public class UserWalletDAO {
	/**
	 * ͨ���û�������ͼ
	 * @param user �����û�
	 * @param jdbcTemplate Spring����
	 * @return ����һ���û����˻���Ϣ
	 */
	public dc_user_wallet getWallInfoByUser(dc_user user,JdbcTemplate jdbcTemplate) {
		dc_user_wallet userwallet=null;
		RowMapper<dc_user_wallet> userwallet_mapper = new BeanPropertyRowMapper<dc_user_wallet>(dc_user_wallet.class);
		try {
			userwallet=jdbcTemplate.queryForObject("select * from dc_user_wallet where uid=? ;", userwallet_mapper,user.getUid());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return userwallet;
	}
	/**
	 * ͨ��uid������ͼ
	 * @param uid
	 * @param jdbcTemplate
	 * @return ����һ���û����˻���Ϣ
	 */
	public dc_user_wallet getWallInfoByUid(int uid,JdbcTemplate jdbcTemplate) {
		dc_user user = UserDAO.getUserByUid(uid,jdbcTemplate);
		return getWallInfoByUser(user,jdbcTemplate);
	}
	/**
	 * ͨ��itcode������ͼ
	 * @param itcode
	 * @param jdbcTemplate Spring����
	 * @return ����һ���û����˻���Ϣ
	 */
	public dc_user_wallet getWallInfoByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		dc_user user = UserDAO.getUserByItcode(itcode,jdbcTemplate);
		return getWallInfoByUser(user,jdbcTemplate);
	}
	/**
	 * �鿴�����û����˻���Ϣ
	 * @param jdbcTemplate Spring����
	 * @return ���������û����˻���Ϣ��һ���б�
	 */
	public List<dc_user_wallet> getAllWallInfoByUser(JdbcTemplate jdbcTemplate) {
		List<dc_user_wallet> userwallets=null;
		RowMapper<dc_user_wallet> userwallet_mapper = new BeanPropertyRowMapper<dc_user_wallet>(dc_user_wallet.class);
		try {
			userwallets=jdbcTemplate.query("select * from dc_user_wallet;", userwallet_mapper);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return userwallets;
	}
}
