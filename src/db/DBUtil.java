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
        }

        return results;

    }


//    public static void main(String[] args){
//        String query = "SELECT ImageFileName imageFileName,Title title,Description description,PaintingID paintingID FROM paintings ORDER BY MSRP desc LIMIT 0 , 1";
//        Painting painting = get(Painting.class,query);
////        System
//        painting.getPaintingID();
//    }


    public static List<Integer> getID(String query, String nameOfID) {
        List<Integer> results = new ArrayList<>();
        //获取连接
        Connection conn = new OpenConnection().getConnection();
        try {
            //预编译SQL，减少sql执行
            PreparedStatement ptmt = conn.prepareStatement(query);

            //执行
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                results.add(rs.getInt(nameOfID));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;

    }


}
