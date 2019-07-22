package Services.ServicesImpl;

import Services.PageService;
import bean.PageInfo;
import dao.IPageDAO;
import dao.factory.DAOFactory;

public class PageServiceImpl implements PageService {
    private IPageDAO dao;
    public PageServiceImpl(){
        dao = DAOFactory.getIPageDAOInstance();
    }
    /**
     * 查询所有的新闻信息
     */
    @Override
    public <T> PageInfo<T> findAlls(Class<T> clazz,String tableName,String type, int pageNum, int pageSize) {
        PageInfo<T> pageInfo = new PageInfo<>();
        // 给pageInfo对象的list集合赋值
        pageInfo.setList(dao.findAlls(clazz,tableName,type, pageNum*5, pageSize));
        return pageInfo;
    }
    /**
     *  获取新闻总页数
     */
    @Override
    public int getTotalCount(String type,String input) {
        return dao.getTotalCount(type,input);
    }

}
