package com.sunnywr.service;

import com.sunnywr.bean.Command;
import com.sunnywr.bean.CommandContent;
import com.sunnywr.bean.Message;
import com.sunnywr.dao.CommandDao;
import com.sunnywr.dao.MessageDao;
import com.sunnywr.entity.Page;
import com.sunnywr.util.Iconst;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 列表相关的业务
 */
public class QueryService {
    /**
     * 根据查询条件查询message列表
     */
    public List<Message> queryMessageList(String command, String description, Page page) {
        // 组织消息对象
        Message message = new Message();
        message.setCommand(command);
        message.setDescription(description);
        MessageDao messageDao = new MessageDao();
        // 根据条件查询条数
        int totalNumber = messageDao.count(message);
        // 组织分页查询参数
        page.setTotalNumber(totalNumber);
        Map<String,Object> parameter = new HashMap<String, Object>();
        parameter.put("message", message);
        parameter.put("page", page);
        // 分页查询并返回结果
        return messageDao.queryMessageList(parameter);
    }

    /**
     * 根据查询条件分页查询消息列表
     */
    public List<Message> queryMessageListByPage(String command,String description,Page page) {
        Map<String,Object> parameter = new HashMap<String, Object>();
        // 组织消息对象
        Message message = new Message();
        message.setCommand(command);
        message.setDescription(description);
        parameter.put("message", message);
        parameter.put("page", page);
        MessageDao messageDao = new MessageDao();
        // 分页查询并返回结果
        return messageDao.queryMessageListByPage(parameter);
    }

    /**
     * 通过指令查询自动回复的内容
     * @param command 指令
     * @return 自动回复的内容
     */
    public String queryByCommand(String command) {
        CommandDao commandDao = new CommandDao();
        List<Command> commandList;
        if(Iconst.HELP_COMMAND.equals(command)) {
            commandList = commandDao.queryCommandList(null, null);
            StringBuilder result = new StringBuilder();
            for(int i = 0; i < commandList.size(); i++) {
                if(i != 0) {
                    result.append("<br/>");
                }
                result.append("回复[" + commandList.get(i).getName() + "]可以查看" + commandList.get(i).getDescription());
            }
            return result.toString();
        }
        commandList = commandDao.queryCommandList(command, null);
        if(commandList.size() > 0) {
            List<CommandContent> contentList = commandList.get(0).getContentList();
            int i = new Random().nextInt(contentList.size());
            return contentList.get(i).getContent();
        }
        return Iconst.NO_MATCHING_CONTENT;
    }
}
