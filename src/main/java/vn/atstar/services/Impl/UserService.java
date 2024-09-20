package vn.atstar.services.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import vn.atstar.configs.DBConnectMySQL;
import vn.atstar.dao.IUserDao;
import vn.atstar.dao.Impl.UserDaoImpl;
import vn.atstar.models.UserModel;
import vn.atstar.services.IUserService;

public class UserService implements IUserService {
	IUserDao userDao = new UserDaoImpl();

	@Override
	public UserModel login(String username, String password) {
		UserModel user = this. FindByUserName (username);
		if (user != null && password.equals(user.getPassword())) {
		return user;
		}
		return null;
		}
		
	@Override
	public UserModel FindByUserName(String username) {
		return userDao.findByUserName(username);
	}

	public boolean register(UserModel user) {
        String sql = "INSERT INTO users (username, email, password, fullname, images, phone, roleId, createDay) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnectMySQL.getDatabaseConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getFullname());
            statement.setString(5, user.getImages());
            statement.setString(6, user.getPhone());
            statement.setInt(7, user.getRoleId());
            statement.setDate(8, new java.sql.Date(user.getCreateDay().getTime()));

            return statement.executeUpdate() > 0; // Trả về true nếu lưu thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	public boolean updatePassword(String username, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        try (Connection connection = DBConnectMySQL.getDatabaseConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newPassword);
            statement.setString(2, username);
            return statement.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
