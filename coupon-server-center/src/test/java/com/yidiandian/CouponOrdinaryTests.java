package com.yidiandian;

import com.yidiandian.entity.Coupon;
import com.yidiandian.service.CouponService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Component
public class CouponOrdinaryTests extends CouponServerCenterApplicationTests{

	@Autowired
	CouponService couponService;


	@Test
	public void batchInsertTest(){

		List<Coupon> couponList = new ArrayList<>();
		for (int i= 1;i <= 10000;i++){
			Coupon coupon = new Coupon();
			coupon.setUserId(Integer.valueOf(getRandNumberCode()));
			coupon.setGrantDate(new Date());
			coupon.setCouponName("全场九折");
			coupon.setIsUse(1);
			couponList.add(coupon);
		}
		couponService.batchInsert(couponList);
	}


	public  String getRandNumberCode ()    {
		Random random = new Random();
		String result="";
		for(int i=0;i<5;i++){
			result+=random.nextInt(10);
		}
		return result;
	}


}
