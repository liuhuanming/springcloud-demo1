package com.springcloud.osgi.utils;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @description: 报错channel is not opened.
 * @author: Think
 * @date: 2020-06-03 07:18
 */
public class SSHUtil {
    public static void main(String[] args) throws IOException, JSchException {
        // TODO Auto-generated method stub
        String host = "101.200.228.9";
        int port = 30022;
        String user = "root";
        String password = "Lmn41955989";
        String command = "top";
        List<String> res = exeCommand(host, port, user, password, command);
        res.forEach(it -> {
            System.out.println(it);
        });

    }

    public static List<String> exeCommand(String host, int port, String user, String password, String command) throws JSchException, IOException  {

        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, port);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(password);
        session.connect(2000);

        ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
        InputStream in = channelExec.getInputStream();
        channelExec.setCommand(command);
        channelExec.setErrStream(System.err);
        channelExec.connect(3);
        List<String> out = IOUtils.readLines(in, "UTF-8");

        channelExec.disconnect();
        session.disconnect();

        return out;
    }
}
