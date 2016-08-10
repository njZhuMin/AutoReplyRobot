package com.sunnywr.dao;

import com.sunnywr.bean.Message;

import java.util.List;
import java.util.Map;

/**
 * 与Message.xml对应的接口
 */
public interface MessageImpl {
    /**
     * 根据查询条件查询消息列表
     */
    public List<Message> queryMessageList(Map<String,Object> parameter);

    /**
     * 根据查询条件查询消息列表的条数
     */
    public int count(Message message);

    /**
     * 根据查询条件分页查询消息列表
     */
    public List<Message> queryMessageListByPage(Map<String,Object> parameter);
}
