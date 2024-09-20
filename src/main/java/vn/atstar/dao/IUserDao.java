package vn.atstar.dao;

import vn.atstar.models.UserModel;
import java.util.List;

public interface IUserDao {
    List<UserModel> findAll();

    UserModel findById(int id);

    UserModel findByUserName(String username);

    void insert(UserModel user);
    
    boolean update(UserModel user); // Phương thức để cập nhật thông tin người dùng
    
    boolean delete(int id); // Phương thức để xóa người dùng
}
