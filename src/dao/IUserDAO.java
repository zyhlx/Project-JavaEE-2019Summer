package dao;

public interface IUserDAO {
    public int insertUser(String name, String pwd, String email, String tel, String address);

    public int getUserID(String userName);
    public boolean login(String userName, String pwd);
}
