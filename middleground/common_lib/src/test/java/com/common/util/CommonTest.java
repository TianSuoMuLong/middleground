package com.common.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import net.sf.json.JSONObject;

public class CommonTest {

	public static void main(String[] args) throws Exception {
		String flowNo = "lsh123";// 核心流水
		String payAcc = "123456789";// 付款账号
		String payAccName = "付款账户名称";// 付款账户名称
		String amt = "99999.09";// 金额
		String hostNo = "c019234";// 收款方核心客户号
		String payeeAcc = "987654321";// 收款账户
		String payeeAccName = " 收款账户名称";// 收款账户名称
		String transTime = "2019-05-19 10:25:32";// 核心交易时间
		// 拼装数据
		HashMap<String, Object> map = new HashMap();
		map.put("bankEncoding", "bank1001");
		map.put("customerName", payeeAccName);
		map.put("bankCode", flowNo);
		map.put("totalAmount", new BigDecimal(amt));
		map.put("paymentBankAccount", payeeAcc);
		map.put("counterpartyBankAccount", payAcc);
		map.put("createTime", transTime);
//		net.sf.json.JSONObject
		JSONObject sendMsg = net.sf.json.JSONObject.fromObject(map);
		System.out.println("发送参数" + sendMsg);
		System.out.println(sendMsg.toString());

//		String resultMsg = "{code:0,msg:\"成功\"}";
		String resultMsg =null;
		
		System.out.println(resultMsg.isEmpty());
		Map resultMap = JSON.parseObject(resultMsg, Map.class);
		
		
		
//		resultMap.get("code")
		String msg=(String) resultMap.get("msg");
		if (!resultMap.get("msg").equals("成功")) {
			System.out.println("返回失败");
		} else {
			System.out.println("返回成功");
		}
		Object[] paramArray=new Object[] { "123",flowNo };
		System.out.println(paramArray);
		

		
		
	}

}
