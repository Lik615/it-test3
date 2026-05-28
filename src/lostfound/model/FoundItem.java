package lostfound.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 拾物信息实体类
 * 负责封装拾物登记的所有属性信息
 *
 * @author 李阔
 * @version 1.0
 */
public class FoundItem {

    private int id;
    private String itemName;
    private String category;
    private String description;
    private String foundLocation;
    private String foundDate;
    private String finderName;
    private String finderPhone;
    private String storageLocation;
    private String status;
    private String createdAt;

    private static int idCounter = 2000;

    public FoundItem() {
        this.id = ++idCounter;
        this.status = "待认领";
        this.createdAt = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public FoundItem(String itemName, String category, String description,
                     String foundLocation, String foundDate,
                     String finderName, String finderPhone, String storageLocation) {
        this();
        this.itemName = itemName;
        this.category = category;
        this.description = description;
        this.foundLocation = foundLocation;
        this.foundDate = foundDate;
        this.finderName = finderName;
        this.finderPhone = finderPhone;
        this.storageLocation = storageLocation;
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

    public String getFoundLocation() {
        return foundLocation;
    }

    public void setFoundLocation(String foundLocation) {
        this.foundLocation = foundLocation;
    }

    public String getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(String foundDate) {
        this.foundDate = foundDate;
    }

    public String getFinderName() {
        return finderName;
    }

    public void setFinderName(String finderName) {
        this.finderName = finderName;
    }

    public String getFinderPhone() {
        return finderPhone;
    }

    public void setFinderPhone(String finderPhone) {
        this.finderPhone = finderPhone;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
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
        return "===== 拾物信息 =====" +
               "\n编号: " + id +
               "\n物品名称: " + itemName +
               "\n类别: " + category +
               "\n描述: " + description +
               "\n拾到地点: " + foundLocation +
               "\n拾到时间: " + foundDate +
               "\n拾到人: " + finderName +
               "\n联系电话: " + finderPhone +
               "\n存放位置: " + storageLocation +
               "\n状态: " + status +
               "\n登记时间: " + createdAt;
    }

    public String toShortString() {
        return String.format("%-6d %-12s %-8s %-16s %-10s %-10s %-8s",
            id, itemName, category, foundLocation, foundDate, finderName, status);
    }
}
