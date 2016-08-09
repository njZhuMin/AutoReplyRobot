package com.sunnywr.dao;
import com.sunnywr.bean.Message;
import com.sunnywr.db.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * message表相关操作
 */
public class MessageDao {

    /**
     * 根据查询条件查询message列表
     */
    public List<Message> queryMessageList(String command, String description) {
        DBAccess dbAccess = new DBAccess();
        List<Message> messageList = new ArrayList<Message>();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            // 执行SQL语句
            Message message = new Message();
            message.setCommand(command);
            message.setDescription(description);
            messageList = sqlSession.selectList("Message.queryMessageList", message);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(sqlSession != null)
                sqlSession.close();
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

