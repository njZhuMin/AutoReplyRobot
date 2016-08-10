package com.sunnywr.dao;
import com.sunnywr.bean.Message;
import com.sunnywr.db.DBAccess;
import com.sunnywr.entity.Page;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * message表相关操作
 */
public class MessageDao {

    /**
     * 根据查询条件查询消息列表
     */
    public List<Message> queryMessageList(Map<String,Object> parameter) {
        DBAccess dbAccess = new DBAccess();
        List<Message> messageList = new ArrayList<Message>();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            // 通过sqlSession执行SQL语句
            MessageImpl imessage = sqlSession.getMapper(MessageImpl.class);
            messageList = imessage.queryMessageList(parameter);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sqlSession != null) {
                sqlSession.close();
            }
        }
        return messageList;
    }

    /**
     * 根据查询条件查询消息列表的条数
     */
    public int count(Message message) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        int result = 0;
        try {
            sqlSession = dbAccess.getSqlSession();
            // 通过sqlSession执行SQL语句
            MessageImpl imessage = sqlSession.getMapper(MessageImpl.class);
            result = imessage.count(message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sqlSession != null) {
                sqlSession.close();
            }
        }
        return result;
    }

    /**
     * 根据查询条件分页查询消息列表
     */
    public List<Message> queryMessageListByPage(Map<String,Object> parameter) {
        DBAccess dbAccess = new DBAccess();
        List<Message> messageList = new ArrayList<Message>();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            // 通过sqlSession执行SQL语句
            MessageImpl imessage = sqlSession.getMapper(MessageImpl.class);
            messageList = imessage.queryMessageListByPage(parameter);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sqlSession != null) {
                sqlSession.close();
            }
        }
        return messageList;
    }

    /**
     * 删除单条记录
     */
    public void deleteOne(int id) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            // 执行SQL语句
            sqlSession.delete("Message.deleteOne", id);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(sqlSession != null)
                sqlSession.close();
        }
    }

    /**
     * 删除多条记录
     */
    public void deleteMulti(List<Integer> ids) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            // 执行SQL语句
            sqlSession.delete("Message.deleteMulti", ids);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(sqlSession != null)
                sqlSession.close();
        }
    }
}

