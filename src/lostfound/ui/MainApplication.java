package lostfound.ui;

import lostfound.model.LostItem;
import lostfound.model.FoundItem;
import lostfound.service.LostFoundService;
import java.util.List;
import java.util.Scanner;

/**
 * 校园失物招领管理系统 - 主程序
 * 提供基于控制台的交互式菜单界面
 *
 * @author 张子妍
 * @version 1.0
 */
public class MainApplication {

    private static LostFoundService service = new LostFoundService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("    欢迎使用校园失物招领管理系统");
        System.out.println("========================================");
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readIntInput("请选择操作: ");
            switch (choice) {
                case 1:
                    showLostItemsMenu();
                    break;
                case 2:
                    showFoundItemsMenu();
                    break;
                case 3:
                    registerLostItem();
                    break;
                case 4:
                    registerFoundItem();
                    break;
                case 5:
                    searchItems();
                    break;
                case 6:
                    service.autoMatch();
                    break;
                case 7:
                    service.showStatistics();
                    break;
                case 0:
                    running = false;
                    System.out.println("感谢使用，再见!");
                    break;
                default:
                    System.out.println("无效选择，请重新输入!");
            }
            if (running) {
                System.out.println("\n按回车键继续...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n========== 主菜单 ==========");
        System.out.println("1. 失物信息查询");
        System.out.println("2. 拾物信息查询");
        System.out.println("3. 登记失物信息");
        System.out.println("4. 登记拾物信息");
        System.out.println("5. 搜索物品");
        System.out.println("6. 智能匹配失物与拾物");
        System.out.println("7. 系统统计");
        System.out.println("0. 退出系统");
        System.out.println("============================");
    }

    private static void showLostItemsMenu() {
        System.out.println("\n========== 失物信息管理 ==========");
        System.out.println("1. 查看所有失物");
        System.out.println("2. 按编号查询");
        System.out.println("3. 标记为已找到");
        System.out.println("4. 删除失物记录");
        System.out.println("0. 返回");
        System.out.println("==================================");
        int choice = readIntInput("请选择: ");
        switch (choice) {
            case 1:
                displayAllLostItems();
                break;
            case 2:
                int id = readIntInput("输入失物编号: ");
                LostItem item = service.findLostItemById(id);
                if (item != null) System.out.println(item);
                break;
            case 3:
                int fid = readIntInput("输入失物编号: ");
                service.markLostItemFound(fid);
                break;
            case 4:
                int did = readIntInput("输入失物编号: ");
                service.removeLostItem(did);
                break;
            case 0:
                break;
            default:
                System.out.println("无效选择!");
        }
    }

    private static void showFoundItemsMenu() {
        System.out.println("\n========== 拾物信息管理 ==========");
        System.out.println("1. 查看所有拾物");
        System.out.println("2. 按编号查询");
        System.out.println("3. 标记为已认领");
        System.out.println("4. 删除拾物记录");
        System.out.println("0. 返回");
        System.out.println("==================================");
        int choice = readIntInput("请选择: ");
        switch (choice) {
            case 1:
                displayAllFoundItems();
                break;
            case 2:
                int id = readIntInput("输入拾物编号: ");
                FoundItem item = service.findFoundItemById(id);
                if (item != null) System.out.println(item);
                break;
            case 3:
                int fid = readIntInput("输入拾物编号: ");
                service.markFoundItemClaimed(fid);
                break;
            case 4:
                int did = readIntInput("输入拾物编号: ");
                service.removeFoundItem(did);
                break;
            case 0:
                break;
            default:
                System.out.println("无效选择!");
        }
    }

    private static void registerLostItem() {
        System.out.println("\n===== 登记失物信息 =====");
        System.out.print("物品名称: ");
        String name = scanner.nextLine();
        System.out.print("类别(证件/电子产品/生活用品/其他): ");
        String category = scanner.nextLine();
        System.out.print("详细描述: ");
        String desc = scanner.nextLine();
        System.out.print("丢失地点: ");
        String location = scanner.nextLine();
        System.out.print("丢失日期(yyyy-MM-dd): ");
        String date = scanner.nextLine();
        System.out.print("联系人: ");
        String person = scanner.nextLine();
        System.out.print("联系电话: ");
        String phone = scanner.nextLine();
        service.registerLostItem(name, category, desc, location, date, person, phone);
    }

    private static void registerFoundItem() {
        System.out.println("\n===== 登记拾物信息 =====");
        System.out.print("物品名称: ");
        String name = scanner.nextLine();
        System.out.print("类别(证件/电子产品/生活用品/其他): ");
        String category = scanner.nextLine();
        System.out.print("详细描述: ");
        String desc = scanner.nextLine();
        System.out.print("拾到地点: ");
        String location = scanner.nextLine();
        System.out.print("拾到日期(yyyy-MM-dd): ");
        String date = scanner.nextLine();
        System.out.print("拾到人: ");
        String finder = scanner.nextLine();
        System.out.print("联系电话: ");
        String phone = scanner.nextLine();
        System.out.print("存放位置: ");
        String storage = scanner.nextLine();
        service.registerFoundItem(name, category, desc, location, date, finder, phone, storage);
    }

    private static void searchItems() {
        System.out.println("\n===== 搜索物品 =====");
        System.out.print("输入搜索关键词: ");
        String keyword = scanner.nextLine();
        System.out.println("\n--- 失物匹配结果 ---");
        List<LostItem> lostResults = service.searchLostItems(keyword);
        if (!lostResults.isEmpty()) {
            printLostHeader();
            for (LostItem item : lostResults) {
                System.out.println(item.toShortString());
            }
        }
        System.out.println("\n--- 拾物匹配结果 ---");
        List<FoundItem> foundResults = service.searchFoundItems(keyword);
        if (!foundResults.isEmpty()) {
            printFoundHeader();
            for (FoundItem item : foundResults) {
                System.out.println(item.toShortString());
            }
        }
    }

    private static void displayAllLostItems() {
        List<LostItem> items = service.getAllLostItems();
        if (items.isEmpty()) {
            System.out.println("暂无失物记录。");
            return;
        }
        System.out.println("\n共 " + items.size() + " 条失物记录:");
        printLostHeader();
        for (LostItem item : items) {
            System.out.println(item.toShortString());
        }
    }

    private static void displayAllFoundItems() {
        List<FoundItem> items = service.getAllFoundItems();
        if (items.isEmpty()) {
            System.out.println("暂无拾物记录。");
            return;
        }
        System.out.println("\n共 " + items.size() + " 条拾物记录:");
        printFoundHeader();
        for (FoundItem item : items) {
            System.out.println(item.toShortString());
        }
    }

    private static void printLostHeader() {
        System.out.println(String.format("%-6s %-12s %-8s %-16s %-10s %-12s %-8s",
            "编号", "物品名称", "类别", "丢失地点", "丢失时间", "联系人", "状态"));
        System.out.println("------------------------------------------------"
            + "------------------------------");
    }

    private static void printFoundHeader() {
        System.out.println(String.format("%-6s %-12s %-8s %-16s %-10s %-10s %-8s",
            "编号", "物品名称", "类别", "拾到地点", "拾到时间", "拾到人", "状态"));
        System.out.println("------------------------------------------------"
            + "------------------------------");
    }

    private static int readIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("请输入有效数字!");
            }
        }
    }
}
