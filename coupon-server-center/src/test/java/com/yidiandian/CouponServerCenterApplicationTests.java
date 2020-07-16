package com.yidiandian;

import com.yidiandian.dao.CouponDao;
import com.yidiandian.entity.Coupon;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
class CouponServerCenterApplicationTests {

	@Resource
	CouponDao couponDao;

}
