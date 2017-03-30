package com.beauty.usercenter.rocketmq.common;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public abstract class BaseJsonConsumer extends BaseConsumer {
    private final static Logger logger = LoggerFactory.getLogger(BaseConsumer.class);
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                    ConsumeConcurrentlyContext context) {
        long startTime = System.currentTimeMillis();
        logger.info("receive_message:{0}", msgs);
        if (msgs == null || msgs.size() < 1) {
            logger.error("receive empty msg!");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        final int reconsumeTimes = msgs.get(0).getReconsumeTimes();
        if (reconsumeTimes >= maxRetryCount) {
            logger.error("reconsumeTimes >" + maxRetryCount + "msgs:" + msgs + "context:" + context);
        }
        context.setDelayLevelWhenNextConsume(getDelayLevelWhenNextConsume(reconsumeTimes));
        boolean ret = true;
        for (MessageExt message : msgs) {
            if(!doConsumeMessage(message)) {
                ret = false;
            }
        }
        ConsumeConcurrentlyStatus status = ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        if (!ret) {
            status = ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
        logger.info("ConsumeConcurrentlyStatus:{0}|cost:{1}", status, System.currentTimeMillis() - startTime);
        return status;
    }
}
