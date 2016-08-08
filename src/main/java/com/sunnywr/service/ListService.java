package com.sunnywr.service;

import com.sunnywr.bean.Message;
import com.sunnywr.dao.MessageDao;

import java.util.List;

/**
 * 列表相关的业务
 */
public class ListService {
    public List<Message> queryMessageList(String command, String description) {
        MessageDao messageDao = new MessageDao();
        return messageDao.queryMessageList(command, description);
    }
}
