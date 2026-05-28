package lostfound.ui;

import lostfound.model.LostItem;
import lostfound.model.FoundItem;
import lostfound.service.LostFoundManager;
import java.util.List;
import java.util.Scanner;

/**
 * 校园失物招领管理系统 - 主程序
 * @author 张子妍
 */
public class MainApplication {

    private static LostFoundManager manager = new LostFoundManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("===== 校园失物招领管理系统 =====");
        boolean running = true;
        while (running) {
            System.out.println("\n1.查看失物  2.查看拾物  3.登记失物");
            System.out.println("4.登记拾物  5.搜索物品  6.智能匹配  7.统计  0.退出");
            System.out.print("请选择: ");
            int choice = readInt();
            switch (choice) {
                case 1: showLostItems(); break;
                case 2: showFoundItems(); break;
                case 3: registerLost(); break;
                case 4: registerFound(); break;
                case 5: searchItems(); break;
                case 6: manager.autoMatch(); break;
                case 7: manager.showStats(); break;
                case 0: running = false; break;
                default: System.out.println("无效选择!");
            }
        }
        System.out.println("感谢使用，再见!");
        scanner.close();
    }

    // ==================== 失物相关 ====================

    private static void showLostItems() {
        List<LostItem> items = manager.getAllLostItems();
        System.out.println("\n--- 失物列表 (" + items.size() + "条) ---");
        for (LostItem i : items) {
            System.out.println(i.toShortString());
        }
        System.out.println("\n1.按编号查询  2.标记已找到  0.返回");
        System.out.print("请选择: ");
        switch (readInt()) {
            case 1:
                System.out.print("输入编号: ");
                LostItem item = manager.findLostItemById(readInt());
                System.out.println(item != null ? item.toString() : "未找到!");
                break;
            case 2:
                System.out.print("输入编号: ");
                System.out.println(manager.markLostFound(readInt()) ? "已标记!" : "失败!");
                break;
        }
    }

    // ==================== 拾物相关 ====================

    private static void showFoundItems() {
        List<FoundItem> items = manager.getAllFoundItems();
        System.out.println("\n--- 拾物列表 (" + items.size() + "条) ---");
        for (FoundItem i : items) {
            System.out.println(i.toShortString());
        }
        System.out.println("\n1.按编号查询  2.标记已认领  0.返回");
        System.out.print("请选择: ");
        switch (readInt()) {
            case 1:
                System.out.print("输入编号: ");
                FoundItem item = manager.findFoundItemById(readInt());
                System.out.println(item != null ? item.toString() : "未找到!");
                break;
            case 2:
                System.out.print("输入编号: ");
                System.out.println(manager.markFoundClaimed(readInt()) ? "已标记!" : "失败!");
                break;
        }
    }

    // ==================== 登记功能 ====================

    private static void registerLost() {
        System.out.println("\n===== 登记失物 =====");
        System.out.print("物品名称: ");
        String name = scanner.nextLine();
        System.out.print("类别: ");
        String cat = scanner.nextLine();
        System.out.print("描述: ");
        String desc = scanner.nextLine();
        System.out.print("丢失地点: ");
        String loc = scanner.nextLine();
        System.out.print("丢失日期: ");
        String date = scanner.nextLine();
        System.out.print("联系人: ");
        String person = scanner.nextLine();
        System.out.print("联系电话: ");
        String phone = scanner.nextLine();
        manager.addLostItem(new LostItem(name, cat, desc, loc, date, person, phone));
    }

    private static void registerFound() {
        System.out.println("\n===== 登记拾物 =====");
        System.out.print("物品名称: ");
        String name = scanner.nextLine();
        System.out.print("类别: ");
        String cat = scanner.nextLine();
        System.out.print("描述: ");
        String desc = scanner.nextLine();
        System.out.print("拾到地点: ");
        String loc = scanner.nextLine();
        System.out.print("拾到日期: ");
        String date = scanner.nextLine();
        System.out.print("拾到人: ");
        String finder = scanner.nextLine();
        System.out.print("联系电话: ");
        String phone = scanner.nextLine();
        System.out.print("存放位置: ");
        String storage = scanner.nextLine();
        manager.addFoundItem(new FoundItem(name, cat, desc, loc, date, finder, phone, storage));
    }

    // ==================== 搜索 ====================

    private static void searchItems() {
        System.out.print("\n输入关键词: ");
        String kw = scanner.nextLine();
        List<LostItem> lost = manager.searchLostItems(kw);
        List<FoundItem> found = manager.searchFoundItems(kw);
        System.out.println("\n失物匹配(" + lost.size() + "条):");
        for (LostItem i : lost) System.out.println(i.toShortString());
        System.out.println("\n拾物匹配(" + found.size() + "条):");
        for (FoundItem i : found) System.out.println(i.toShortString());
    }

    private static int readInt() {
        try { return Integer.parseInt(scanner.nextLine().trim()); }
        catch (NumberFormatException e) { return -1; }
    }
}
