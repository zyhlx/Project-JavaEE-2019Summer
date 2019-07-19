package dao.factory;

import dao.IFavourDAO;
import dao.IGalleryDAO;
import dao.IPaintingDAO;
import dao.IUserDAO;
import dao.impl.FavourDAOImpl;
import dao.impl.GalleryDAOImpl;
import dao.impl.PaintingDAOImpl;
import dao.impl.UserDAOImpl;

//数据层工厂类 为了方便业务层对数据层进行操作 关闭开启数据库
//使用工厂类的特征就是不需要知道具体的子类（第三方接口也是这样 只负责传参 不关心具体的实现类）即业务层是看不见具体的实现类StudentDAOImpl
//除此之外的VO类，IStudentDAO，Connection等都能看见
public class DAOFactory {
    public static IPaintingDAO getIPaintingDAOInstance() {//由外部传入形参
        return new PaintingDAOImpl();//双重形参
    }

    public static IUserDAO getIUserDAOInstance() {//由外部传入形参
        return new UserDAOImpl();//双重形参
    }

    public static IFavourDAO getIFavourDAOInstance() {
        return new FavourDAOImpl();
    }

    public static IGalleryDAO getIGalleryDAOInstance() {
        return new GalleryDAOImpl();
    }

}
