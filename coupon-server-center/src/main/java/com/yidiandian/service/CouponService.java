package com.yidiandian.service;

import com.yidiandian.entity.Coupon;
import java.util.List;
import java.util.Map;

/**
 * (Coupon)表服务接口
 *
 * @author makejava
 * @since 2020-07-16 16:23:15
 */
public interface CouponService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Coupon queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Coupon> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param coupon 实例对象
     * @return 实例对象
     */
    Coupon insert(Coupon coupon);

    /**
     * 修改数据
     *
     * @param coupon 实例对象
     * @return 实例对象
     */
    Coupon update(Coupon coupon);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 批量增加
     * @param entityList
     * @return
     */
    void batchInsert(List<Coupon> entityList);


}