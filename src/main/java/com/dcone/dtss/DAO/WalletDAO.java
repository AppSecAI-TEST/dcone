package com.dcone.dtss.DAO;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.dc_user;
import com.dcone.dtss.model.dc_wallet;

public class WalletDAO {

	/**
	 * �û��˻���ֵ
	 * @param itcode ��ֵ��Ա����
	 * @param username ��ֵ��Ա������
	 * @param amount ��ֵ�Ľ��
	 * @param locale ʱ������
	 * @param jdbcTemplate Spring����
	 * @return 1��ʾ�ɹ���-1��ʾ�û���Ϣ����0��������
	 */
	public static int balance_add(String itcode,String username, String amount ,Locale locale, JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		try {
			dc_user user = jdbcTemplate.queryForObject("select * from dc_user where itcode=? and username=?;", user_mapper, new Object[] {itcode, username});
			
			RowMapper<dc_wallet> wallet_mapper = new BeanPropertyRowMapper<dc_wallet>(dc_wallet.class);
			dc_wallet wallet  = jdbcTemplate.queryForObject("select * from dc_wallet where uid  = ?;", wallet_mapper, user.getUid());
			
			Date date = new Date();
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
			String formattedDate = dateFormat.format(date);
			int i = jdbcTemplate.update("insert into dc_trade values(null, ?,?,?,\"��\");", new Object[] {wallet.getWid(),amount,formattedDate});
			if(i>0) {
				int j = jdbcTemplate.update("update dc_wallet set amount = amount + ?;", amount);
				if(j>0) {
					return 1;
				}
			}
			
			
		}catch(Exception e) {
			return -1;
		}
		
		return 0;
	}
	/**
	 * ͨ��uid��ȡǮ������Ϣ
	 * @param uid �û�Ǯ���ĵ�uid
	 * @param jdbcTemplate Spring����
	 * @return
	 */
	public static dc_wallet getWalletByUid(int uid,JdbcTemplate jdbcTemplate) {
		RowMapper<dc_wallet> wallet_mapper = new BeanPropertyRowMapper<dc_wallet>(dc_wallet.class);
		dc_wallet wallet = null;
		try {
			
			wallet = jdbcTemplate.queryForObject("select * from dc_wallet where uid=?;", wallet_mapper, uid);
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return wallet;
	}
	/**
	 * ͨ��wid��ȡǮ������Ϣ
	 * @param wid �û�Ǯ���ĵ�wid
	 * @param jdbcTemplate Spring����
	 * @return
	 */
	public static dc_wallet getWalletByWid(int wid,JdbcTemplate jdbcTemplate) {
		RowMapper<dc_wallet> wallet_mapper = new BeanPropertyRowMapper<dc_wallet>(dc_wallet.class);
		dc_wallet wallet = null;
		try {
			
			wallet = jdbcTemplate.queryForObject("select * from dc_wallet where wid=?;", wallet_mapper, wid);
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return wallet;
	}
	/**
	 * ͨ��itcode��ȡǮ������Ϣ
	 * @param itcode �û�Ǯ���ĵ�itcode
	 * @param jdbcTemplate Spring����
	 * @return
	 */
	public static dc_wallet getWalletByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		int uid=UserDAO.getUserByItcode(itcode, jdbcTemplate).getUid();
		dc_wallet wallet =WalletDAO.getWalletByUid(uid, jdbcTemplate);
		return wallet;
	}
	/**
	 * ͨ��uid����ʼ��Ǯ��
	 * @param uid �û���uid 
	 * @param jdbcTemplate Spring����
	 * @return
	 */
	public static boolean initWalletById(int uid,JdbcTemplate jdbcTemplate) {
		int i=0;
		try {
			i=jdbcTemplate.update("insert into dc_wallet values(null,?,1000);",uid);
			if(i>0)
				return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	/**
	 * ͨ��itcode����ʼ��Ǯ��
	 * @param itcode �û���itcode 
	 * @param jdbcTemplate Spring����
	 * @return
	 */
	public static boolean initWalletByItcode(String itcode,JdbcTemplate jdbcTemplate) {
		dc_user user=UserDAO.getUserByItcode(itcode, jdbcTemplate);	
		return WalletDAO.initWalletById(user.getUid(), jdbcTemplate);
	}
	/**
	 * ��ʾ���е�Ǯ������Ϣ
	 * @param jdbcTemplate
	 * @return ����һ��Ǯ����Ϣ��wallet
	 */
	public static List<dc_wallet> getAllWallets(JdbcTemplate jdbcTemplate) {
		RowMapper<dc_wallet> walletMapper=new BeanPropertyRowMapper<dc_wallet>(dc_wallet.class);
		List<dc_wallet> wallets=null;
		try {
			wallets=jdbcTemplate.query("select * from dc_wallet;",walletMapper);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return wallets;
	}
	

}
