package com.dcone.dtss;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dcone.dtss.DAO.LuckyDAO;
import com.dcone.dtss.DAO.WalletDAO;
import com.dcone.dtss.model.dc_wallet;

@Controller
public class AdminController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	boolean flag=false;
	@RequestMapping("/lucky_on")
	public String Lucky_on() {
		List<dc_wallet> wallets=WalletDAO.getAllWallets(jdbcTemplate);
		for(dc_wallet temp:wallets)
		{
		System.out.println(temp.getUid()+" "+temp.getAmount());
			if(flag) {
				LuckyDAO.LuckyRain(jdbcTemplate);
				//�����,�������ƣ��½�һ����񣬰����з��ĺ����ļ�¼����¼������dc_trade���׼�¼,����������Ҫ�˶Ե�
			}
		}
		return null;		
	}
}
