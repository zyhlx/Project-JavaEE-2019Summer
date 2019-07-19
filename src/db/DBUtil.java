package db;

import Utils.ReflectionUtils;
import bean.Painting;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {

    public static <T> List<T> get(Class<T> clazz, String sql, Object... args) {
        List<T> results = new ArrayList<>();
//        T entity = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = new OpenConnection().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                ResultSetMetaData rsmd = resultSet.getMetaData();
                Map<String, Object> values = new HashMap<>();
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    String columLabel = rsmd.getColumnLabel(i + 1);
                    Object cclumnValue = resultSet.getObject(i + 1);
                    values.put(columLabel, cclumnValue);
                }
                if (values.size() > 0) {
                    T entity = clazz.newInstance();
                    for (Map.Entry<String, Object> entry : values.entrySet()) {
                        String fieldName = entry.getKey();
                        Object fieldValue = entry.getValue();
                        ReflectionUtils.setFieldValue(entity, fieldName, fieldValue);
                    }
                    results.add(entity);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return results;
    }


    //返回影响的数据个数 失败-1
    public static int insert(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int res = -1;
        try {
            connection = new OpenConnection().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            res = preparedStatement.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        return res;
    }


    public static List<Integer> getID(String query, String nameOfID) {
        List<Integer> results = new ArrayList<>();
        //获取连接
        Connection conn = new OpenConnection().getConnection();
        PreparedStatement ptmt = null;
        try {
            //预编译SQL，减少sql执行
            ptmt = conn.prepareStatement(query);

            //执行
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                results.add(rs.getInt(nameOfID));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (ptmt != null) {
                try {
                    ptmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        return results;

    }


}
