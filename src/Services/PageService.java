package Services;

import bean.PageInfo;

public interface PageService {
    public <T> PageInfo<T> findAlls(Class<T> clazz,String input,String method, int pageNum, int pageSize);
    public int getTotalCount(String type,String input);
}
