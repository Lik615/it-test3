package lostfound.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 失物信息实体类
 * 负责封装失物登记的所有属性信息
 *
 * @author 李阔
 * @version 1.0
 */
public class LostItem {

    private int id;
    private String itemName;
    private String category;
    private String description;
    private String lostLocation;
    private String lostDate;
    private String contactPerson;
    private String contactPhone;
    private String status;
    private String createdAt;

    private static int idCounter = 1000;

    public LostItem() {
        this.id = ++idCounter;
        this.status = "未找到";
        this.createdAt = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public LostItem(String itemName, String category, String description,
                    String lostLocation, String lostDate,
                    String contactPerson, String contactPhone) {
        this();
        this.itemName = itemName;
        this.category = category;
        this.description = description;
        this.lostLocation = lostLocation;
        this.lostDate = lostDate;
        this.contactPerson = contactPerson;
        this.contactPhone = contactPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLostLocation() {
        return lostLocation;
    }

    public void setLostLocation(String lostLocation) {
        this.lostLocation = lostLocation;
    }

    public String getLostDate() {
        return lostDate;
    }

    public void setLostDate(String lostDate) {
        this.lostDate = lostDate;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int counter) {
        idCounter = counter;
    }

    @Override
    public String toString() {
        return "===== 失物信息 =====" +
               "\n编号: " + id +
               "\n物品名称: " + itemName +
               "\n类别: " + category +
               "\n描述: " + description +
               "\n丢失地点: " + lostLocation +
               "\n丢失时间: " + lostDate +
               "\n联系人: " + contactPerson +
               "\n联系电话: " + contactPhone +
               "\n状态: " + status +
               "\n登记时间: " + createdAt;
    }

    public String toShortString() {
        return String.format("%-6d %-12s %-8s %-16s %-10s %-12s %-8s",
            id, itemName, category, lostLocation, lostDate, contactPerson, status);
    }
}
