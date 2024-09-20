package vn.atstar.dao.Impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import vn.atstar.configs.DBConnectMySQL;
import vn.atstar.dao.IUserDao;
import vn.atstar.models.UserModel;

public class UserDaoImpl extends DBConnectMySQL implements IUserDao {

    @Override
    public List<UserModel> findAll() {
        String sql = "SELECT * FROM users";
        List<UserModel> list = new ArrayList<>();

        try (Connection conn = super.getDatabaseConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRowToUserModel(rs));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Có thể thay bằng logging
        }
        return list;
    }

    @Override
    public UserModel findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        UserModel user = null;

        try (Connection conn = super.getDatabaseConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = mapRowToUserModel(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void insert(UserModel user) {
        String sql = "INSERT INTO users (username, password, email, fullname, images, phone, roleId, createDay) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = super.getDatabaseConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getFullname());
            ps.setString(5, user.getImages());
            ps.setString(6, user.getPhone());
            ps.setInt(7, user.getRoleId());
            ps.setDate(8, new Date(user.getCreateDay().getTime()));

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserModel findByUserName(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        UserModel user = null;

        try (Connection conn = super.getDatabaseConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = mapRowToUserModel(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    private UserModel mapRowToUserModel(ResultSet rs) throws Exception {
        return new UserModel(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getString("fullname"),
            rs.getString("images"),
            rs.getString("phone"),
            rs.getInt("roleId"),
            rs.getDate("createDay")
        );
    }

    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
        // Chèn một người dùng mới với các giá trị hợp lệ
        userDao.insert(new UserModel("exampleUser", "password123", "example@example.com", "Example User", null, "1234567890", 1, new Date(System.currentTimeMillis())));
        
        // Lấy tất cả người dùng
        List<UserModel> list = userDao.findAll();
        for (UserModel user : list) {
            System.out.println(user);
        }
    }
}
