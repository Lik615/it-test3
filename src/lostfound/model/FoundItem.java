package lostfound.model;

/**
 * 拾物信息实体类
 * @author 李阔
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

    private static int idCounter = 2000;

    public FoundItem() {
        this.id = ++idCounter;
        this.status = "待认领";
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

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getFoundLocation() { return foundLocation; }
    public void setFoundLocation(String foundLocation) { this.foundLocation = foundLocation; }

    public String getFoundDate() { return foundDate; }
    public void setFoundDate(String foundDate) { this.foundDate = foundDate; }

    public String getFinderName() { return finderName; }
    public void setFinderName(String finderName) { this.finderName = finderName; }

    public String getFinderPhone() { return finderPhone; }
    public void setFinderPhone(String finderPhone) { this.finderPhone = finderPhone; }

    public String getStorageLocation() { return storageLocation; }
    public void setStorageLocation(String storageLocation) { this.storageLocation = storageLocation; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public static void setIdCounter(int counter) { idCounter = counter; }

    /**
     * 判断信息是否填写完整
     */
    public boolean isComplete() {
        return itemName != null && !itemName.trim().isEmpty() &&
               finderPhone != null && !finderPhone.trim().isEmpty() &&
               foundLocation != null && !foundLocation.trim().isEmpty();
    }

    /**
     * 验证手机号格式
     */
    public boolean isValidPhone() {
        return finderPhone != null && finderPhone.matches("\\d{11}");
    }

    /**
     * 获取格式化后的简要信息（用于列表展示）
     */
    public String toShortString() {
        return String.format("%-5d %-10s %-6s %-14s %-10s %-8s %-8s",
            id, itemName, category, foundLocation, foundDate, finderName, status);
    }

    /**
     * 获取表头（用于列表展示对齐）
     */
    public static String getTableHeader() {
        return String.format("%-5s %-10s %-6s %-14s %-10s %-8s %-8s",
            "编号", "物品", "类别", "拾到地点", "拾到时间", "拾到人", "状态");
    }

    @Override
    public String toString() {
        return "编号:" + id + " | 物品:" + itemName + " | 类别:" + category +
               " | 描述:" + description + " | 拾到地点:" + foundLocation +
               " | 拾到时间:" + foundDate + " | 拾到人:" + finderName +
               " | 电话:" + finderPhone + " | 存放位置:" + storageLocation +
               " | 状态:" + status;
    }
}
