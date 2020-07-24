package com.yidiandian;

import com.yidiandian.entity.Coupon;
import com.yidiandian.service.CouponEsService;
import com.yidiandian.service.CouponService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Component
public class CouponESTests extends CouponServerCenterApplicationTests{

	@Autowired
	CouponEsService couponEsService;

	//保存or更新ES
	@Test
	public void saveOrUpdateESTest() throws InterruptedException {
		couponEsService.saveOrUpdateES();
	}

	@Test
	public void deleteESTest(){
		String id = "";
		couponEsService.deleteById(id);
	}


}
