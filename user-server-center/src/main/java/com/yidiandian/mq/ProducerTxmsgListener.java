package com.yidiandian.mq;

import com.alibaba.fastjson.JSONObject;

import com.yidiandian.request.RequestPay;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @Author: luoxian
 * @Date: 2020/6/4 18:31
 * @Email: 15290810931@163.com
 */
@Component
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "pay_group_txmsg")
public class ProducerTxmsgListener implements RocketMQLocalTransactionListener {



   /* @Autowired
    Bank2DuplicationDao deDuplicationDao;*/

    /**
     * 事务消息发送后的回调方法，当消息发送给mq成功，此方法被回调
     * @param message
     * @param o
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {
            log.info("executeLocalTransaction begin-----------------------");

            //解析message，转成AccountChangeEvent
            String messageString = new String((byte[]) message.getPayload());
            JSONObject jsonObject = JSONObject.parseObject(messageString);
            String accountChangeString = jsonObject.getString("requestPay");
            //将accountChange（json）转成AccountChangeEvent
            RequestPay accountChangeEvent = JSONObject.parseObject(accountChangeString, RequestPay.class);
            log.info("事务消息发送后的回调方法，当消息发送给mq成功，此方法被回调：{}", JSONObject.toJSONString(accountChangeEvent));

            //执行本地事务，扣减金额
            //accountPayService.doUpdateAccountBalance(accountChangeEvent);
            //当返回RocketMQLocalTransactionState.COMMIT，自动向mq发送commit消息，mq将消息的状态改为可消费
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 事务状态回查，查询是否扣减金额
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        log.info("checkLocalTransaction begin--------------------------");

        //解析message，转成AccountChangeEvent
        String messageString = new String((byte[]) message.getPayload());
        JSONObject jsonObject = JSONObject.parseObject(messageString);
        String accountChangeString = jsonObject.getString("requestPay");
        //将accountChange（json）转成AccountChangeEvent
        RequestPay accountChangeEvent = JSONObject.parseObject(accountChangeString, RequestPay.class);
        //事务id
        String txNo = accountChangeEvent.getTxNo();
        return RocketMQLocalTransactionState.COMMIT;
        /*if(deDuplicationDao.queryById(txNo) != null){
            log.info("checkLocalTransaction commit ---------");
            return RocketMQLocalTransactionState.COMMIT;
        }else{
            log.info("checkLocalTransaction unknown----------");
            return RocketMQLocalTransactionState.UNKNOWN;
        }*/
    }
}
