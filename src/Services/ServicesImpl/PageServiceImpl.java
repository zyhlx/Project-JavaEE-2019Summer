package Services.ServicesImpl;

import Services.PageService;
import bean.PageInfo;
import dao.IPageDAO;
import dao.impl.PageDAOImpl;

public class PageServiceImpl implements PageService {
    private IPageDAO dao;
    public PageServiceImpl(){
        dao = new PageDAOImpl();
    }
    /**
     * 查询所有的新闻信息
     */
    @Override
    public <T> PageInfo<T> findAlls(Class<T> clazz,String tableName,int pageNum, int pageSize) {
        PageInfo<T> pageInfo = new PageInfo<>();
        // 给pageInfo对象的list集合赋值
        pageInfo.setList(dao.findAlls(clazz,tableName,pageNum*5, pageSize));
        return pageInfo;
    }
    /**
     *  获取新闻总页数
     */
    @Override
    public int getTotalCount() {
        return dao.getTotalCount();
    }

}
