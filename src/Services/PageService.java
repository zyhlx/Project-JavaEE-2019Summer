package Services;

import bean.PageInfo;

public interface PageService {
    public <T> PageInfo<T> findAlls(Class<T> clazz,String tableName,int pageNum, int pageSize);
    public int getTotalCount();
}
