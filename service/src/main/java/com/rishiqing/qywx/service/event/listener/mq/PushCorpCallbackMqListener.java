package com.rishiqing.qywx.service.event.listener.mq;

import com.rishiqing.common.exception.NotSupportedException;
import com.rishiqing.qywx.service.callback.PushCallbackHandler;
import com.rishiqing.qywx.service.callback.impl.PushCallbackHandlerImpl;
import com.rishiqing.qywx.service.constant.CallbackChangeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.Map;

/**
 * 处理企业微信接收到的回调消息的mq listener
 * @author Wallace Mao
 * Date: 2018-02-10 14:20
 */
public class PushCorpCallbackMqListener implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger("SERVICE_EVENT_PUSH_LISTENER_LOGGER");

    @Autowired
    private PushCallbackHandler logFailPushCallbackHandler;

    @Override
    public void onMessage(Message message) {
        try {
            MapMessage mapMessage = (MapMessage)message;
            String corpId = mapMessage.getString("corpId");
            CallbackChangeType type = CallbackChangeType.getCallbackChangeType(mapMessage.getString("changeType"));
            Map contentMap = (Map)mapMessage.getObject("content");

            //  如果type没在枚举列表中，那么报出错误
            if(null == type){
                throw new RuntimeException("type not recognized: " + mapMessage.getString("changeType"));
            }

            switch (type){
                case CREATE_PARTY:
                    logFailPushCallbackHandler.handleCreateDept(corpId, contentMap);
                    break;
                case UPDATE_PARTY:
                    logFailPushCallbackHandler.handleUpdateDept(corpId, contentMap);
                    break;
                case DELETE_PARTY:
                    logFailPushCallbackHandler.handleDeleteDept(corpId, contentMap);
                    break;
                case CREATE_USER:
                    logFailPushCallbackHandler.handleCreateUser(corpId, contentMap);
                    break;
                case UPDATE_USER:
                    logFailPushCallbackHandler.handleUpdateUser(corpId, contentMap);
                    break;
                case DELETE_USER:
                    logFailPushCallbackHandler.handleDeleteUser(corpId, contentMap);
                    break;
                case UPDATE_TAG:
                    throw new NotSupportedException("change type UPDATE_TAG not supported now");
                default:
                    break;
            }
        } catch (Exception e) {
            logger.error("error in push corpCallback: ", e);
        }
    }
}
