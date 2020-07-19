package com.yidiandian.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yidiandian.entity.UserInfo;
import com.yidiandian.dao.UserInfoDao;
import com.yidiandian.request.RequestPay;
import com.yidiandian.service.UserInfoService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息表(UserInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-07-14 11:36:10
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserInfo queryById(Integer id) {
        return this.userInfoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserInfo> queryAllByLimit(int offset, int limit) {
        return this.userInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userInfo 实例对象
     * @return 实例对象
     */
    @Override
    public UserInfo insert(UserInfo userInfo) {
        this.userInfoDao.insert(userInfo);
        return userInfo;
    }

    /**
     * 修改数据
     *
     * @param userInfo 实例对象
     * @return 实例对象
     */
    @Override
    public UserInfo update(UserInfo userInfo) {
        this.userInfoDao.update(userInfo);
        return this.queryById(userInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userInfoDao.deleteById(id) > 0;
    }

    /**
     * 使用优惠券支付
     *
     * @param requestPay
     */
    @Override
    public void payOfCoupon(RequestPay requestPay) {
        //将accountChangeEvent转成json
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("requestPay",requestPay);
        String jsonString = jsonObject.toJSONString();
        //生成message类型
        Message<String> message = MessageBuilder.withPayload(jsonString).build();
        //发送一条事务消息
        rocketMQTemplate.sendMessageInTransaction("pay_group_txmsg","pay_topic_txmsg",message,null);
    }
}