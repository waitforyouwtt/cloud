package com.yidiandian.service;

import com.yidiandian.entity.UserInfo;
import com.yidiandian.request.RequestPay;

import java.util.List;

/**
 * 用户信息表(UserInfo)表服务接口
 *
 * @author makejava
 * @since 2020-07-14 11:36:10
 */
public interface UserInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    UserInfo queryById(String userId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userInfo 实例对象
     * @return 实例对象
     */
    UserInfo insert(UserInfo userInfo);

    /**
     * 修改数据
     *
     * @param userInfo 实例对象
     * @return 实例对象
     */
    UserInfo update(UserInfo userInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 使用优惠券支付
     * @param requestPay
     */
    void payOfCoupon(RequestPay requestPay);

    /**
     * 根据ID查询用户名称
     */

    String findUserNameById(String userId);
}