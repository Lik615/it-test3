package lostfound.model;

/**
 * 失物信息实体类
 * @author 李阔
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

    private static int idCounter = 1000;

    public LostItem() {
        this.id = ++idCounter;
        this.status = "未找到";
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

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLostLocation() { return lostLocation; }
    public void setLostLocation(String lostLocation) { this.lostLocation = lostLocation; }

    public String getLostDate() { return lostDate; }
    public void setLostDate(String lostDate) { this.lostDate = lostDate; }

    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }

    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public static void setIdCounter(int counter) { idCounter = counter; }

    /**
     * 判断信息是否填写完整
     */
    public boolean isComplete() {
        return itemName != null && !itemName.trim().isEmpty() &&
               contactPhone != null && !contactPhone.trim().isEmpty() &&
               lostLocation != null && !lostLocation.trim().isEmpty();
    }

    /**
     * 验证手机号格式
     */
    public boolean isValidPhone() {
        return contactPhone != null && contactPhone.matches("\\d{11}");
    }

    /**
     * 获取格式化后的简要信息（用于列表展示）
     */
    public String toShortString() {
        return String.format("%-5d %-10s %-6s %-14s %-10s %-8s %-8s",
            id, itemName, category, lostLocation, lostDate, contactPerson, status);
    }

    /**
     * 获取表头（用于列表展示对齐）
     */
    public static String getTableHeader() {
        return String.format("%-5s %-10s %-6s %-14s %-10s %-8s %-8s",
            "编号", "物品", "类别", "丢失地点", "丢失时间", "联系人", "状态");
    }

    @Override
    public String toString() {
        return "编号:" + id + " | 物品:" + itemName + " | 类别:" + category +
               " | 描述:" + description + " | 丢失地点:" + lostLocation +
               " | 丢失时间:" + lostDate + " | 联系人:" + contactPerson +
               " | 电话:" + contactPhone + " | 状态:" + status;
    }
}
