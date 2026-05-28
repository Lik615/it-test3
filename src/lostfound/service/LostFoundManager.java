package lostfound.service;

import lostfound.model.LostItem;
import lostfound.model.FoundItem;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 失物招领管理器 - 数据存储与业务逻辑
 * @author 张子妍
 */
public class LostFoundManager {

    private List<LostItem> lostItems;
    private List<FoundItem> foundItems;

    public LostFoundManager() {
        lostItems = new ArrayList<>();
        foundItems = new ArrayList<>();
        initData();
    }

    private void initData() {
        lostItems.add(new LostItem("校园卡", "证件", "蓝色卡面",
            "图书馆二楼", "2024-05-20", "张三", "13800001111"));
        lostItems.add(new LostItem("蓝牙耳机", "电子产品", "白色AirPods",
            "教学楼A301", "2024-05-21", "李四", "13800002222"));
        foundItems.add(new FoundItem("校园卡", "证件", "蓝色卡面",
            "食堂门口", "2024-05-20", "赵六", "13800004444", "招领中心"));
        foundItems.add(new FoundItem("雨伞", "生活用品", "蓝色折叠伞",
            "图书馆一楼", "2024-05-21", "孙七", "13800005555", "招领中心"));
    }

    // ==================== 失物管理 ====================

    public void addLostItem(LostItem item) {
        lostItems.add(item);
        System.out.println("失物登记成功! 编号: " + item.getId());
    }

    public List<LostItem> getAllLostItems() {
        return lostItems;
    }

    public LostItem findLostItemById(int id) {
        for (LostItem item : lostItems) {
            if (item.getId() == id) return item;
        }
        return null;
    }

    public List<LostItem> searchLostItems(String keyword) {
        return lostItems.stream()
            .filter(i -> i.getItemName().contains(keyword) ||
                i.getDescription().contains(keyword) ||
                i.getLostLocation().contains(keyword))
            .collect(Collectors.toList());
    }

    public boolean markLostFound(int id) {
        LostItem item = findLostItemById(id);
        if (item != null) { item.setStatus("已找到"); return true; }
        return false;
    }

    // ==================== 拾物管理 ====================

    public void addFoundItem(FoundItem item) {
        foundItems.add(item);
        System.out.println("拾物登记成功! 编号: " + item.getId());
    }

    public List<FoundItem> getAllFoundItems() {
        return foundItems;
    }

    public FoundItem findFoundItemById(int id) {
        for (FoundItem item : foundItems) {
            if (item.getId() == id) return item;
        }
        return null;
    }

    public List<FoundItem> searchFoundItems(String keyword) {
        return foundItems.stream()
            .filter(i -> i.getItemName().contains(keyword) ||
                i.getDescription().contains(keyword) ||
                i.getFoundLocation().contains(keyword))
            .collect(Collectors.toList());
    }

    public boolean markFoundClaimed(int id) {
        FoundItem item = findFoundItemById(id);
        if (item != null) { item.setStatus("已认领"); return true; }
        return false;
    }

    // ==================== 匹配与统计 ====================

    public void autoMatch() {
        System.out.println("===== 智能匹配结果 =====");
        int count = 0;
        for (LostItem lost : lostItems) {
            if (!"未找到".equals(lost.getStatus())) continue;
            for (FoundItem found : foundItems) {
                if (!"待认领".equals(found.getStatus())) continue;
                if (lost.getItemName().equals(found.getItemName()) &&
                    lost.getCategory().equals(found.getCategory())) {
                    System.out.println("失物[" + lost.getId() + "]" + lost.getItemName() +
                        " <--> 拾物[" + found.getId() + "]" + found.getItemName());
                    count++;
                }
            }
        }
        if (count == 0) System.out.println("暂无匹配项");
        else System.out.println("共匹配 " + count + " 条记录");
    }

    public void showStats() {
        long lost = lostItems.stream().filter(i -> "未找到".equals(i.getStatus())).count();
        long found = foundItems.stream().filter(i -> "待认领".equals(i.getStatus())).count();
        System.out.println("===== 系统统计 =====");
        System.out.println("失物总数: " + lostItems.size() + " (未找到: " + lost + ")");
        System.out.println("拾物总数: " + foundItems.size() + " (待认领: " + found + ")");
    }
}
