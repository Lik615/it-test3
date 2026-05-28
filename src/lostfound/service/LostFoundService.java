package lostfound.service;

import lostfound.model.LostItem;
import lostfound.model.FoundItem;
import java.util.List;

/**
 * 失物招领核心业务服务类
 * 封装所有业务逻辑，作为UI层和数据层的桥梁
 *
 * @author 张子妍
 * @version 1.0
 */
public class LostFoundService {

    private DataStore dataStore;

    public LostFoundService() {
        this.dataStore = DataStore.getInstance();
    }

    // ==================== 失物登记 ====================

    public LostItem registerLostItem(String itemName, String category,
                                      String description, String lostLocation,
                                      String lostDate, String contactPerson,
                                      String contactPhone) {
        if (itemName == null || itemName.trim().isEmpty()) {
            System.out.println("错误: 物品名称不能为空!");
            return null;
        }
        if (contactPhone == null || contactPhone.trim().isEmpty()) {
            System.out.println("错误: 联系电话不能为空!");
            return null;
        }
        LostItem item = new LostItem(itemName, category, description,
            lostLocation, lostDate, contactPerson, contactPhone);
        dataStore.addLostItem(item);
        System.out.println("失物登记成功! 编号: " + item.getId());
        return item;
    }

    // ==================== 拾物登记 ====================

    public FoundItem registerFoundItem(String itemName, String category,
                                        String description, String foundLocation,
                                        String foundDate, String finderName,
                                        String finderPhone, String storageLocation) {
        if (itemName == null || itemName.trim().isEmpty()) {
            System.out.println("错误: 物品名称不能为空!");
            return null;
        }
        if (finderPhone == null || finderPhone.trim().isEmpty()) {
            System.out.println("错误: 联系电话不能为空!");
            return null;
        }
        FoundItem item = new FoundItem(itemName, category, description,
            foundLocation, foundDate, finderName, finderPhone, storageLocation);
        dataStore.addFoundItem(item);
        System.out.println("拾物登记成功! 编号: " + item.getId());
        return item;
    }

    // ==================== 失物查询 ====================

    public List<LostItem> getAllLostItems() {
        return dataStore.getAllLostItems();
    }

    public LostItem findLostItemById(int id) {
        LostItem item = dataStore.findLostItemById(id);
        if (item == null) {
            System.out.println("未找到编号为 " + id + " 的失物信息。");
        }
        return item;
    }

    public List<LostItem> searchLostItems(String keyword) {
        List<LostItem> result = dataStore.searchLostItems(keyword);
        if (result.isEmpty()) {
            System.out.println("未找到包含 \"" + keyword + "\" 的失物信息。");
        }
        return result;
    }

    public boolean markLostItemFound(int id) {
        boolean success = dataStore.updateLostItemStatus(id, "已找到");
        if (success) {
            System.out.println("失物编号 " + id + " 已标记为【已找到】。");
        } else {
            System.out.println("操作失败: 未找到该失物记录。");
        }
        return success;
    }

    public boolean removeLostItem(int id) {
        boolean success = dataStore.removeLostItem(id);
        if (success) {
            System.out.println("失物编号 " + id + " 已删除。");
        } else {
            System.out.println("删除失败: 未找到该失物记录。");
        }
        return success;
    }

    // ==================== 拾物查询 ====================

    public List<FoundItem> getAllFoundItems() {
        return dataStore.getAllFoundItems();
    }

    public FoundItem findFoundItemById(int id) {
        FoundItem item = dataStore.findFoundItemById(id);
        if (item == null) {
            System.out.println("未找到编号为 " + id + " 的拾物信息。");
        }
        return item;
    }

    public List<FoundItem> searchFoundItems(String keyword) {
        List<FoundItem> result = dataStore.searchFoundItems(keyword);
        if (result.isEmpty()) {
            System.out.println("未找到包含 \"" + keyword + "\" 的拾物信息。");
        }
        return result;
    }

    public boolean markFoundItemClaimed(int id) {
        boolean success = dataStore.updateFoundItemStatus(id, "已认领");
        if (success) {
            System.out.println("拾物编号 " + id + " 已标记为【已认领】。");
        } else {
            System.out.println("操作失败: 未找到该拾物记录。");
        }
        return success;
    }

    public boolean removeFoundItem(int id) {
        boolean success = dataStore.removeFoundItem(id);
        if (success) {
            System.out.println("拾物编号 " + id + " 已删除。");
        } else {
            System.out.println("删除失败: 未找到该拾物记录。");
        }
        return success;
    }

    // ==================== 智能匹配 ====================

    public List<String> autoMatch() {
        List<String> matches = dataStore.matchItems();
        if (matches.isEmpty()) {
            System.out.println("当前没有可匹配的失物与拾物记录。");
        } else {
            System.out.println("===== 匹配结果 (" + matches.size() + "条) =====");
            for (String match : matches) {
                System.out.println(match);
            }
        }
        return matches;
    }

    // ==================== 统计信息 ====================

    public void showStatistics() {
        List<LostItem> lostItems = dataStore.getAllLostItems();
        List<FoundItem> foundItems = dataStore.getAllFoundItems();
        long unresolvedLost = lostItems.stream()
            .filter(i -> "未找到".equals(i.getStatus())).count();
        long unclaimedFound = foundItems.stream()
            .filter(i -> "待认领".equals(i.getStatus())).count();

        System.out.println("===== 系统统计 =====");
        System.out.println("失物总数: " + lostItems.size() + " (未找到: " + unresolvedLost + ")");
        System.out.println("拾物总数: " + foundItems.size() + " (待认领: " + unclaimedFound + ")");
    }
}
