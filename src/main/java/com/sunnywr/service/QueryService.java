package com.sunnywr.service;

import com.sunnywr.bean.Command;
import com.sunnywr.bean.CommandContent;
import com.sunnywr.bean.Message;
import com.sunnywr.dao.CommandDao;
import com.sunnywr.dao.MessageDao;
import com.sunnywr.util.Iconst;

import java.util.List;
import java.util.Random;

/**
 * 列表相关的业务
 */
public class QueryService {
    public List<Message> queryMessageList(String command, String description) {
        MessageDao messageDao = new MessageDao();
        return messageDao.queryMessageList(command, description);
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
