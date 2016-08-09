package com.sunnywr.service;

import com.sunnywr.dao.MessageDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 维护业务相关功能
 */
public class MaintainService {
    /**
     * 单条记录删除
     */
    public void deleteOne(String id) {
        if(id != null && !"".equals(id.trim())) {
            MessageDao messageDao = new MessageDao();
            messageDao.deleteOne(Integer.valueOf(id));
        }
    }

    /**
     * 多条记录删除
     */
    public void deleteMulti(String[] ids) {
        List<Integer> idList = new ArrayList<Integer>();
        for(String id : ids)
            idList.add(Integer.valueOf(id));
        MessageDao messageDao = new MessageDao();
        messageDao.deleteMulti(idList);
    }
}
