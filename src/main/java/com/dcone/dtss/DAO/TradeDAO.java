
package com.dcone.dtss.DAO;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.*;
public class TradeDAO {
	/**
	 * ͨ��Ǯ����wid����ȡ���׼�¼����Ϣ
	 * @param wid Ǯ����wid
	 * @param jdbcTemplate Spring����
	 * @return ���׼�¼����Ϣ
	 */
	public static List<dc_trade> getTradesByWid(String wid,JdbcTemplate jdbcTemplate){
		RowMapper<dc_trade> trade_mapper = new BeanPropertyRowMapper<dc_trade>(dc_trade.class);
		List<dc_trade> trades=null;
		try {
		trades = jdbcTemplate.query("select * from dc_trade where wid=?", trade_mapper, wid);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return trades;
	}
	/**
	 * ͨ��Ǯ����uid ����ȡ�����׵���Ϣ
	 * @param uid �û���uid
	 * @param jdbcTemplate Spring����
	 * @return ���ؽ��׼�¼���б�
	 */
	public static List<dc_trade> getTradesByUid(int uid,JdbcTemplate jdbcTemplate){
		dc_wallet wallet=WalletDAO.getWalletByUid(uid, jdbcTemplate);
		int wid=wallet.getWid();
		RowMapper<dc_trade> trade_mapper = new BeanPropertyRowMapper<dc_trade>(dc_trade.class);
		List<dc_trade> trades=null;
		try {
			trades=jdbcTemplate.query("select * from dc_trade where wid = ? ;", trade_mapper,wid);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return trades;
	}
	/**
	 * ͨ���û���itcode��ȡ���׵ļ�¼
	 * @param itcode �û���itcode 
	 * @param jdbcTemplate Spring����
	 * @return ���ؽ��׼�¼���б�
	 */
	public static List<dc_trade> getTradesByItcode(String itcode,JdbcTemplate jdbcTemplate)
	{
		dc_user user=UserDAO.getUserByItcode(itcode,jdbcTemplate);
		List<dc_trade> tradeByUid = getTradesByUid(user.getUid(),jdbcTemplate);
		return tradeByUid;
	}
	/**
	 * ͨ�������user��������ȡ�����׵���Ϣ
	 * @param user �����user����Ϣ
	 * @param jdbcTemplate Spring����
	 * @return ���ؽ��׼�¼���б�
	 */
	public static List<dc_trade> getTradesByUser(dc_user user,JdbcTemplate jdbcTemplate)
	{
		return getTradesByUid(user.getUid(),jdbcTemplate);
	}
	/**
	 * ��ѯ�����ĳ�����׼�¼
	 * @param id
	 * @return ����һ�����׼�¼
	 */
	public static dc_trade getTradeByTid(int tid,JdbcTemplate jdbcTemplate)
	{
		RowMapper<dc_trade> trade_mapper = new BeanPropertyRowMapper<dc_trade>(dc_trade.class);
		dc_trade trade=null;
		try {
			trade=jdbcTemplate.queryForObject("select * from dc_trade where tid=?", trade_mapper, tid);
		} catch (Exception e) {
			// TODO: handle exception
		}	
		return trade;
	}
	/**
	 * �ж�һ��Ǯ������û��Ǯ
	 * @param wid Ǯ��id
	 * @param amount ��Ҫ�����Ľ��
	 * @param jdbcTemplate Spring����
	 * @return ����true��ʾ���Խ��ף�false��ʾ�����Խ���
	 */
	private static boolean preTrade(int wid,int amount,JdbcTemplate jdbcTemplate) {
		dc_wallet wallet=WalletDAO.getWalletByWid(wid, jdbcTemplate);
		if(wallet.getAmount()>=amount)
			return true;
		else
			return false;
	}
	/**
	 * ����һ�����׼�¼
	 * @param wid Ǯ����ID
	 * @param amount �˻���Ǯ
	 * @param date ʱ������
	 * @param memo ��ע��Ϣ
	 * @param jdbcTemplate Spring����
	 * @return ����True��ʾ�����ɹ� ��false��ʾʧ��
	 */
	public static boolean createTrade(int wid,int amount,String date,String memo,JdbcTemplate jdbcTemplate) {
		if(preTrade(wid,amount,jdbcTemplate)) {
			RowMapper<dc_trade> trade_mapper = new BeanPropertyRowMapper<dc_trade>(dc_trade.class);
//			Date nowdate = new Date();
//			SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String nowtime=myFmt.format(nowdate);
//			
			try {
				int i=jdbcTemplate.update("insert into dc_trade values(null,?,?,?,?);",trade_mapper,new Object[] {wid,amount,date,memo});
				if(i>0)
					return true;
			} catch (Exception e) {
				// TODO: handle exception
			}
			return false;
		}else {
		return false;
		}
		
	}
}
