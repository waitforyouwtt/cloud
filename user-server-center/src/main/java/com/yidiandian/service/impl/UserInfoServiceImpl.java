package com.yidiandian.service.impl;

import com.alibaba.fastjson.JSON;
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
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户信息表(UserInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-07-14 11:36:10
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    JedisPool jedisPool;


    @Resource
    private UserInfoDao userInfoDao;

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public UserInfo queryById(String userId) {
        Jedis jedis = null;
        UserInfo userInfo;
        try {
            jedis = jedisPool.getResource();

            // 1. 查询Redis是否有数据 -- hash的方式
            Map<String, String> result = jedis.hgetAll(userId); // hgetall
            if (result != null && result.size() > 0) {
                // map对象转换为User
                userInfo = JSON.parseObject(JSON.toJSONString(result), UserInfo.class);
                return userInfo; // 命中 缓存
            }

            // 2. 查询数据库
            userInfo = userInfoDao.queryById(Integer.parseInt(userId));
            Map<String,String> userInfoMap = JSONObject.parseObject(JSONObject.toJSONString(userInfo), Map.class);
            // jedis --- hmset key filed1 value1 ....
            jedis.hmset(userId.toString(),userInfoMap );
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return userInfo;
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

    /**
     * 根据ID查询用户名称
     *
     * @param userId
     */
    @Override
    public String findUserNameById(String userId) {
        String uname = null;

        Jedis jedis = null;

        UserInfo userInfo ;
        try {
            jedis = jedisPool.getResource();
            // 1. 查询Redis是否有数据
            uname = jedis.hget(userId.toString(), "uname");
            if (uname != null && !"".equals(uname)) {
                return uname; // 命中 缓存
            }

            // 2. 查询数据库
            // 查询数据库
            userInfo = userInfoDao.queryById(Integer.parseInt(userId));

            // 3. 数据塞到redis中 -- 单独更新uname 对象一个属性
            jedis.hset(userId.toString(), "uname", userInfo.getUserName());

        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        return uname;
    }
}