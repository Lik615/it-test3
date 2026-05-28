package lostfound.service;

import lostfound.model.LostItem;
import lostfound.model.FoundItem;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据存储类（单例模式）
 * 负责管理系统中的所有失物和拾物数据
 *
 * @author 张子妍
 * @version 1.0
 */
public class DataStore {

    private static DataStore instance;
    private List<LostItem> lostItems;
    private List<FoundItem> foundItems;

    private DataStore() {
        lostItems = new ArrayList<>();
        foundItems = new ArrayList<>();
        initSampleData();
    }

    public static synchronized DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    /**
     * 初始化示例数据
     */
    private void initSampleData() {
        lostItems.add(new LostItem("校园卡", "证件", "蓝色卡面，有照片",
            "图书馆二楼", "2024-05-20", "张三", "13800001111"));
        lostItems.add(new LostItem("蓝牙耳机", "电子产品", "白色AirPods，带保护壳",
            "教学楼A座301", "2024-05-21", "李四", "13800002222"));
        lostItems.add(new LostItem("U盘", "电子产品", "金士顿32G，黑色",
            "计算机楼机房", "2024-05-22", "王五", "13800003333"));

        foundItems.add(new FoundItem("校园卡", "证件", "蓝色卡面，信息模糊",
            "食堂门口", "2024-05-20", "赵六", "13800004444", "失物招领中心"));
        foundItems.add(new FoundItem("雨伞", "生活用品", "蓝色折叠伞",
            "图书馆一楼", "2024-05-21", "孙七", "13800005555", "失物招领中心"));
    }

    // ========== 失物管理 ==========

    public void addLostItem(LostItem item) {
        lostItems.add(item);
    }

    public List<LostItem> getAllLostItems() {
        return new ArrayList<>(lostItems);
    }

    public LostItem findLostItemById(int id) {
        return lostItems.stream()
            .filter(item -> item.getId() == id)
            .findFirst()
            .orElse(null);
    }

    public boolean removeLostItem(int id) {
        return lostItems.removeIf(item -> item.getId() == id);
    }

    public boolean updateLostItemStatus(int id, String status) {
        LostItem item = findLostItemById(id);
        if (item != null) {
            item.setStatus(status);
            return true;
        }
        return false;
    }

    // ========== 拾物管理 ==========

    public void addFoundItem(FoundItem item) {
        foundItems.add(item);
    }

    public List<FoundItem> getAllFoundItems() {
        return new ArrayList<>(foundItems);
    }

    public FoundItem findFoundItemById(int id) {
        return foundItems.stream()
            .filter(item -> item.getId() == id)
            .findFirst()
            .orElse(null);
    }

    public boolean removeFoundItem(int id) {
        return foundItems.removeIf(item -> item.getId() == id);
    }

    public boolean updateFoundItemStatus(int id, String status) {
        FoundItem item = findFoundItemById(id);
        if (item != null) {
            item.setStatus(status);
            return true;
        }
        return false;
    }

    // ========== 搜索功能 ==========

    public List<LostItem> searchLostItems(String keyword) {
        return lostItems.stream()
            .filter(item -> item.getItemName().contains(keyword) ||
                item.getDescription().contains(keyword) ||
                item.getLostLocation().contains(keyword) ||
                item.getCategory().contains(keyword))
            .collect(Collectors.toList());
    }

    public List<FoundItem> searchFoundItems(String keyword) {
        return foundItems.stream()
            .filter(item -> item.getItemName().contains(keyword) ||
                item.getDescription().contains(keyword) ||
                item.getFoundLocation().contains(keyword) ||
                item.getCategory().contains(keyword))
            .collect(Collectors.toList());
    }

    /**
     * 自动匹配失物与拾物（根据物品名称和类别）
     */
    public List<String> matchItems() {
        List<String> matches = new ArrayList<>();
        for (LostItem lost : lostItems) {
            if (!"未找到".equals(lost.getStatus())) {
                continue;
            }
            for (FoundItem found : foundItems) {
                if (!"待认领".equals(found.getStatus())) {
                    continue;
                }
                if (lost.getItemName().equals(found.getItemName()) &&
                    lost.getCategory().equals(found.getCategory())) {
                    matches.add(String.format(
                        "失物[%d]%s(%s) <--> 拾物[%d]%s(%s) 匹配度:高",
                        lost.getId(), lost.getItemName(), lost.getLostLocation(),
                        found.getId(), found.getItemName(), found.getFoundLocation()
                    ));
                }
            }
        }
        return matches;
    }
}
