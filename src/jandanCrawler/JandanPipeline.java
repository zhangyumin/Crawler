package jandanCrawler;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * //
 * //   █████▒█    ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
 * // ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
 * // ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
 * // ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
 * // ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
 * //  ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
 * Created by zym on 16-4-8.
 */
public class JandanPipeline implements Pipeline {

    String text[],id[],judge[] = null;
    ArrayList support = new ArrayList();
    ArrayList unsupport = new ArrayList();
    String page = null;

    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println("get page: " + resultItems.getRequest().getUrl());
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
            if(entry.getKey().equals("text")){
                text = ((String)entry.getValue()).replace("[","").replace("]", "").replace(" ","").split(",");
            }
            else if (entry.getKey().equals("id")){
                id = ((String)entry.getValue()).replace("[","").replace("]", "").replace(" ","").split(",");
            }
            else if (entry.getKey().equals("support")){
                judge = ((String)entry.getValue()).replace("[","").replace("]","").replace(" ","").split(",");
                for (int i = 0; i < judge.length; i+=2) {
                    support.add(judge[i]);
                    unsupport.add(judge[i+1]);
                }
            }
            else if (entry.getKey().equals("page")){
                page = (String)entry.getValue();
            }
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/crawler?characterEncoding=GBK","root","root");
            Statement stmt = conn.createStatement();
            StringBuffer sql = new StringBuffer("insert into jandan values (" + id[0] + ",'" + text[0] + "'," + support.get(0) + "," + unsupport.get(0) + "," + page + ")");
            for (int i = 1; i < id.length; i++) {
                sql.append(",("+ id[i] + ",'" + text[i] + "'," + support.get(i) + "," + unsupport.get(i) + "," + page + ")");
            }
            sql.append(";");
//            System.out.println(sql.toString());
            stmt.executeUpdate(sql.toString());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
