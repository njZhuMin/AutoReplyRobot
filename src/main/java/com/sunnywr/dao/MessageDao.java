package com.sunnywr.dao;
import com.sunnywr.bean.Message;

import java.sql.*;
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
        List<Message> messageList = new ArrayList<Message>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/micro_message?characterEncoding=utf8", "root", "minmin95420");
            StringBuilder sql = new StringBuilder
                    ("SELECT ID, COMMAND, DESCRIPTION, CONTENT FROM message WHERE 1=1");

            // 缓存查询条件
            List<String> paramList = new ArrayList<String>();
            if(command != null && !"".equals(command.trim())) {
                sql.append(" AND COMMAND= ? ");
                paramList.add(command);
            }
            if(description != null && !"".equals(description.trim())) {
                sql.append(" AND DESCRIPTION LIKE '%' ? '%'");
                paramList.add(description);
            }
            PreparedStatement statement = conn.prepareStatement(sql.toString());
            for(int i = 0; i < paramList.size(); i++) {
                statement.setString(i+1, paramList.get(i));
            }

            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Message message = new Message();
                messageList.add(message);
                message.setId(rs.getString("ID"));
                message.setCommand(rs.getString("COMMAND"));
                message.setDescription(rs.getString("DESCRIPTION"));
                message.setContent(rs.getString("CONTENT"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messageList;
    }
}

