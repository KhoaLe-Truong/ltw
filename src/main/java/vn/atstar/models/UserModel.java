package vn.atstar.models;

import java.io.Serializable;
import java.util.Date;

public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String username;
    private String email;
    private String password;
    private String fullname;
    private String images;
    private String phone;
    private int roleId;
    private Date createDay;

    public UserModel() {
        super();
    }

    public UserModel(int id, String username, String email, String password, String fullname, String images, String phone, int roleId, Date createDay) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.images = images;
        this.phone = phone;
        this.roleId = roleId;
        this.createDay = createDay;
    }

    public UserModel( String username, String email, String password, String fullname, String images, String phone, int roleId, Date createDay) {
        super();
       
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.images = images;
        this.phone = phone;
        this.roleId = roleId;
        this.createDay = createDay;
    }
    
    public UserModel(String username, String email, String password) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getter và setter cho tất cả các thuộc tính
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Date getCreateDay() {
        return createDay;
    }

    public void setCreateDay(Date createDay) {
        this.createDay = createDay;
    }

    @Override
    public String toString() {
        return "UserModel [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
                + ", fullname=" + fullname + ", images=" + images + ", phone=" + phone + ", roleId=" + roleId
                + ", createDay=" + createDay + "]";
    }
}