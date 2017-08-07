package com.dcone.dtss;


import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dcone.dtss.DAO.*;
import com.dcone.dtss.model.dc_trade;

import form.WalletForm;


@Controller
public class BalanceController {
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value="/sort_log",method=RequestMethod.GET)
	public String sortlog() {
		return "sort_log";
	}
	
	@RequestMapping(value="/sort_loging",method=RequestMethod.GET)
	public String sortlogres(String wid,Model model) {
		model.addAttribute("wid",wid);
		List<dc_trade> res=TradeDAO.getTradesByWid(wid,jdbcTemplate);
		model.addAttribute("res",res);
		
//		System.out.println(UserDAO.createUser("2", "haha", 0,jdbcTemplate));
//		System.out.println(UserDAO.checkUserInfo("1", "aa",jdbcTemplate));
//		
		
		return "sort_log_result";
	}
	
	
	
	@RequestMapping(value="/balance_add",method=RequestMethod.GET)
	public String balanceAdd() {
		
		return "balance_add";
	}
	@RequestMapping(value="/balance_adding")
	public String BalanceAdding(@Valid WalletForm walletForm,BindingResult bindingResult,Locale locale,Model model) {
	logger.info("itcode:" +walletForm.getItcode() +"username:"+walletForm.getUsername() + " ��ֵ "+ walletForm.getUsername());
//		System.out.println(jdbcTemplate.toString());
//		System.out.println("itcode:"+itcode+"username:"+username+"��ֵ"+amount);
//	
//		
//		model.addAttribute("itcode", itcode);
//		model.addAttribute("username", username);
//		model.addAttribute("amount", amount);
	String result="";
	if(bindingResult.hasErrors())
	{
		String msg="�û���������Ϊ2���Ϊ8\nitcode����Ϊ5���Ϊ11\n�������100�����100000��";
		model.addAttribute("msg",msg);
		return "balance_add";
	}
	else {
		int i = WalletDAO.balance_add(walletForm.getItcode(), walletForm.getUsername(), walletForm.getAmount(), locale, jdbcTemplate);
	
		if(i == 1) {
			result = "��ֵ�ɹ�";
		} else if(i == -1) {
			result = "�û���Ϣ��д����!";
		}else {
			result = "��ֵʧ��,���Ժ�����!";
		}

		}
	model.addAttribute("result",result);
	
	return "balance_add_result";
	}
	
}
