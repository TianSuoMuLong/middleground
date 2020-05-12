package com.common.util;


import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import com.common.date.utils.DateUtil;

public class CommonTest {

	@Test
	public void get11() {
		System.out.println("111111111");
	}
	@Test
	public void datetest1() {
		DateUtil du=new DateUtil();
		String dateToString = du.dateToString(new Date(), "yyyyMMdd");
		System.out.println(dateToString);
		
		
		BigDecimal big=new BigDecimal(10000);
		BigDecimal big2=new BigDecimal(10000.00);
		System.out.println(big.compareTo(big2));
	}
	
}
