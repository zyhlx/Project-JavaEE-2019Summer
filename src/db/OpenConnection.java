package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Martin17 on 2017/3/16.
 */
public class OpenConnection {
    public Connection getConnection(){

        String driver = null;
        String url = null;
        String user = null;
        String psd = null;
        Connection conn = null;

        Properties pro = new Properties();    //新建一个properties实例，用于从DBConfig中拿到连接参数。
        try {
            pro.load(this.getClass().getClassLoader().getResourceAsStream("db/DBConfig.properties")); //加载DBConfig文件。
            driver = pro.getProperty("driver");
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            psd = pro.getProperty("psd");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName(driver);   //加载jdbc驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(url,user,psd);  //获取数据库连接
            conn.setAutoCommit(false);
            return conn;       //返回一个数据库连接。
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}

