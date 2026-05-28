package lostfound.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 用户实体类
 * 负责封装系统用户的基本信息
 *
 * @author 李阔
 * @version 1.0
 */
public class User {

    private int userId;
    private String username;
    private String realName;
    private String phone;
    private String email;
    private String role;
    private String createdAt;

    private static int userIdCounter = 100;

    public User() {
        this.userId = ++userIdCounter;
        this.createdAt = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public User(String username, String realName, String phone,
                String email, String role) {
        this();
        this.username = username;
        this.realName = realName;
        this.phone = phone;
        this.email = email;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public static int getUserIdCounter() {
        return userIdCounter;
    }

    public static void setUserIdCounter(int counter) {
        userIdCounter = counter;
    }

    @Override
    public String toString() {
        return "===== 用户信息 =====" +
               "\n用户ID: " + userId +
               "\n用户名: " + username +
               "\n真实姓名: " + realName +
               "\n手机号: " + phone +
               "\n邮箱: " + email +
               "\n角色: " + role +
               "\n注册时间: " + createdAt;
    }

    public String toShortString() {
        return String.format("%-6d %-12s %-10s %-15s %-20s %-8s",
            userId, username, realName, phone, email, role);
    }
}
