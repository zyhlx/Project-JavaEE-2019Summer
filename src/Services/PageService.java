package Services;

import bean.PageInfo;

public interface PageService {
    public <T> PageInfo<T> findAlls(Class<T> clazz,String method ,String input, int pageNum, int pageSize);
    public int getTotalCount(String type,String input);
}
