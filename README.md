# 校园失物招领管理系统

## 项目简介
本项目是一个基于Java控制台的校园失物招领管理系统，实现了失物登记、拾物登记、信息查询、智能匹配等核心功能。

## 小组成员
- 李阔：负责Model层实体类设计（LostItem、FoundItem、User）
- 张子妍：负责Service业务逻辑层和UI交互层（DataStore、LostFoundService、MainApplication）

## 功能模块
1. 失物信息管理：登记、查询、修改状态、删除
2. 拾物信息管理：登记、查询、修改状态、删除
3. 关键词搜索：根据物品名称、类别、地点等搜索
4. 智能匹配：自动匹配失物与拾物记录
5. 统计信息：系统数据概览

## 技术栈
- 语言：Java
- 数据存储：内存存储（ArrayList）
- 设计模式：单例模式（DataStore）

## 项目结构
```
src/
├── lostfound/
│   ├── model/
│   │   ├── LostItem.java      (李阔)
│   │   ├── FoundItem.java     (李阔)
│   │   └── User.java          (李阔)
│   ├── service/
│   │   ├── DataStore.java     (张子妍)
│   │   └── LostFoundService.java (张子妍)
│   └── ui/
│       └── MainApplication.java  (张子妍)
```
