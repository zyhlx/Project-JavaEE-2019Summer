package Services;

import bean.PageInfo;

public interface PageService {
    public <T> PageInfo<T> findAlls(Class<T> clazz,String tableName,String type, int pageNum, int pageSize);
    public int getTotalCount(String type);
}
