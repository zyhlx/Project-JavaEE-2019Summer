package dao;

import bean.PageInfo;

import java.util.List;

public interface IPageDAO {
    public <T> List<T> findAlls(Class<T> clazz,String tableName,  int pageNum, int pageSize);
    public int getTotalCount();
}
