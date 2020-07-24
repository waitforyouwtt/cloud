package com.yidiandian.service;

import com.yidiandian.entity.Coupon;

import java.util.List;

/**
 * (Coupon)表服务接口
 *
 * @author makejava
 * @since 2020-07-16 16:23:15
 */
public interface CouponEsService {

    /**
     * 创建优惠券es索引库[不存在则创建，存在则修改]
     */
     void createIndex();

    /**
     * 保存或更新ES
     */
     void saveOrUpdateES() throws InterruptedException;

    /**
     * 根据Id 进行删除
     * @param id
     */
    void deleteById(String id);

    /**
     * 通过id 查询ES
     * @param id
     * @return
     */
    Coupon queryProjectESById(String id);


}