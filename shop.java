/*
 * ============================================================================
 * 文件名 : ShoppingSystem.java(简易商店账户与结账系统)
 * ----------------------------------------------------------------------------
 * 需求来源:手写笔记《账户 / 商》
 *   ① 帐户创建系统   —— 账户分为"学生"和"普通用户"两种身份
 *   ② 商品信息录入系统 —— 录入商品的名称、单价、购买数量
 *   ③ 身份识别系统   —— 学生:应付金额 ×90%(九折);非学生:×100%(原价)
 *   ④ 结帐 / 欠帐    —— 结账前先"读取身份信息、校验身份",校验通过才结算;
 *                      余额不足时,差额记为"欠账"
 * ----------------------------------------------------------------------------
 * 编译命令:javac -encoding UTF-8 ShoppingSystem.java
 * 运行命令:java ShoppingSystem
 * (若命令行中文乱码,可先执行 chcp 65001 再运行)
 * ============================================================================
 */

// 导入 java.util 包中的 ArrayList,用一个"动态列表"来保存多件录入的商品
import java.util.ArrayList;
// 导入 java.util 包中的 Scanner(扫描器),用来读取键盘输入
import java.util.Scanner;

// ============================================================================
// ① 帐户创建系统 —— Account(账户)类
//    作用:描述一个账户,记录 姓名、身份(学生/普通用户)、账户编号、余额
// ============================================================================
class Account {

    // 成员变量:账户持有人的姓名
    private String name;

    // 成员变量:身份标记,true 表示"学生",false 表示"普通用户"
    private boolean student;

    // 成员变量:账户编号(唯一),④结账时靠它"读取身份信息、校验身份"
    private int id;

    // 成员变量:账户余额(元),④结账时扣款,钱不够就产生"欠账"
    private double balance;

    // 构造方法:新建账户时,一次性传入 姓名、身份、编号、初始余额
    public Account(String name, boolean student, int id, double balance) {
        this.name = name;           // 把参数 name 保存到当前对象的成员变量 name 中
        this.student = student;     // 把参数 student 保存到当前对象的成员变量 student 中
        this.id = id;               // 把参数 id 保存到当前对象的成员变量 id 中
        this.balance = balance;     // 把参数 balance 保存到当前对象的成员变量 balance 中
    }

    // 方法:获取账户姓名(打印小票时使用)
    public String getName() {
        return name;                // 返回姓名
    }

    // 方法:获取身份标记(true = 学生 / false = 普通用户)
    public boolean isStudent() {
        return student;             // 返回身份标记
    }

    // 方法:获取账户编号
    public int getId() {
        return id;                  // 返回账户编号
    }

    // 方法:获取当前余额(若为负数,负数部分即为欠账金额)
    public double getBalance() {
        return balance;             // 返回余额
    }

    // 方法:扣款,从余额中扣除应付金额(余额不足时会变成负数,即欠账)
    public void deduct(double amount) {
        this.balance = this.balance - amount; // 余额 = 原余额 - 应付金额
    }
}

// ============================================================================
// ② 商品信息录入系统 —— Product(商品)类
//    作用:描述一件录入的商品(名称、单价、数量),并能计算"小计金额"
// ============================================================================
class Product {

    // 成员变量:商品名称
    private String name;

    // 成员变量:商品单价(元)
    private double price;

    // 成员变量:购买数量(件)
    private int quantity;

    // 构造方法:录入商品时,传入 名称、单价、数量
    public Product(String name, double price, int quantity) {
        this.name = name;           // 把参数 name 保存到成员变量 name 中
        this.price = price;         // 把参数 price 保存到成员变量 price 中
        this.quantity = quantity;   // 把参数 quantity 保存到成员变量 quantity 中
    }

    // 方法:获取商品名称
    public String getName() {
        return name;                // 返回商品名称
    }

    // 方法:获取商品单价
    public double getPrice() {
        return price;               // 返回单价
    }

    // 方法:获取购买数量
    public int getQuantity() {
        return quantity;            // 返回数量
    }

    // 方法:计算该商品的小计金额 = 单价 × 数量(折扣前的金额)
    public double getSubtotal() {
        return price * quantity;    // 返回小计
    }
}

// ============================================================================
// 主类 ShoppingSystem —— 程序入口,把 ①②③④ 四个子系统串成完整流程:
// 创建账户 → 录入商品 → 身份识别 → 校验身份 → 结账/欠账
// ============================================================================
public class ShoppingSystem {

    // main 方法:Java 程序的入口,虚拟机(JVM)从这里开始执行
    public static void main(String[] args) {

        // 创建 Scanner 对象 sc,绑定标准输入(键盘),用于读取用户输入
        Scanner sc = new Scanner(System.in);

        // 创建商品列表 products,用来存放②中录入的所有商品对象
        ArrayList<Product> products = new ArrayList<Product>();

        // 打印系统标题,分隔线让界面更清晰
        System.out.println("=========== 简易商店账户与结账系统 ===========");

        // =====================================================================
        // ① 帐户创建系统
        // =====================================================================
        System.out.println("【① 帐户创建系统】");            // 提示进入第①步

        System.out.print("请输入账户姓名:");                  // 提示输入姓名
        String name = sc.next();                            // 读取用户输入的姓名

        System.out.print("请选择身份(1 = 学生,2 = 普通用户):"); // 提示选择身份
        int role = sc.nextInt();                            // 读取身份编号(1 或 2)
        boolean isStudent = (role == 1);                    // 输 1 → 学生(true);否则 → 普通用户(false)

        System.out.print("请输入账户编号(整数,如 1001):");    // 提示输入账户编号
        int id = sc.nextInt();                              // 读取账户编号

        System.out.print("请输入账户初始余额(元):");          // 提示输入初始余额
        double balance = sc.nextDouble();                   // 读取初始余额

        // 用上面录入的信息创建一个账户对象 account
        Account account = new Account(name, isStudent, id, balance);

        // 打印创建成功的提示:姓名 + 身份(三目运算符:true 显示"学生",false 显示"普通用户")
        System.out.println(">> 账户创建成功:" + account.getName()
                + " (身份:" + (account.isStudent() ? "学生" : "普通用户")
                + ",编号:" + account.getId() + ",余额:" + account.getBalance() + " 元)");

        // =====================================================================
        // ② 商品信息录入系统
        // =====================================================================
        System.out.println();                               // 输出一个空行,分隔步骤
        System.out.println("【② 商品信息录入系统】");         // 提示进入第②步

        System.out.print("请问要录入几件商品?");              // 提示输入商品件数
        int count = sc.nextInt();                           // 读取商品件数

        // for 循环:循环变量 i 从 1 到 count,一件一件地录入商品
        for (int i = 1; i <= count; i++) {
            System.out.println("--- 第 " + i + " 件商品 ---"); // 提示当前录入第几件

            System.out.print("  商品名称:");                  // 提示输入商品名称
            String pName = sc.next();                       // 读取商品名称

            System.out.print("  商品单价(元):");              // 提示输入单价
            double pPrice = sc.nextDouble();                // 读取单价

            System.out.print("  购买数量:");                  // 提示输入数量
            int pQty = sc.nextInt();                        // 读取数量

            // 用录入的信息创建商品对象,并加入商品列表 products 中
            products.add(new Product(pName, pPrice, pQty));
        }

        // =====================================================================
        // ③ 身份识别系统(学生打折 ×90%,非学生 ×100%)
        // =====================================================================
        System.out.println();                               // 空行分隔
        System.out.println("【③ 身份识别系统】");             // 提示进入第③步

        // 计算折扣率:学生 → 0.9(×90%,九折);非学生 → 1.0(×100%,原价)
        double discount = account.isStudent() ? 0.9 : 1.0;

        // 打印识别结果:告知当前账户按什么折扣结算
        System.out.println(">> 识别结果:"
                + (account.isStudent() ? "学生 → 应付金额 ×90%(九折)"
                                       : "非学生 → 应付金额 ×100%(原价)"));

        // =====================================================================
        // ④ 结帐 / 欠帐(先"读取身份信息、校验身份",再结算)
        // =====================================================================
        System.out.println();                               // 空行分隔
        System.out.println("【④ 结帐系统】");                 // 提示进入第④步

        // 读取身份信息:模拟"刷卡",要求再次输入账户编号
        System.out.print("请刷卡校验身份(输入账户编号):");    // 提示输入编号
        int inputId = sc.nextInt();                         // 读取输入的编号

        // 校验身份:输入的编号与账户编号一致才算校验通过
        if (inputId != account.getId()) {                   // 若编号不一致
            System.out.println(">> 身份校验失败,无法结账!程序结束。"); // 提示失败
            sc.close();                                     // 关闭扫描器,释放资源
            return;                                         // 直接结束 main 方法
        }

        // 校验通过后,打印读取到的身份信息
        System.out.println(">> 身份校验通过!读取到身份:"
                + account.getName() + "(" + (account.isStudent() ? "学生" : "普通用户") + ")");

        // 计算所有商品的原价合计:total 累加每一件商品的小计
        double total = 0;                                   // 定义合计变量,初值为 0
        for (Product p : products) {                        // 增强 for:遍历商品列表
            total = total + p.getSubtotal();                // 累加当前商品的小计金额
        }

        // 应付金额 = 原价合计 × 折扣率(学生 ×90%,非学生 ×100%)
        double due = total * discount;

        // 打印购物清单(小票明细)
        System.out.println("---------- 购物清单 ----------"); // 小票标题
        for (Product p : products) {                        // 遍历商品列表逐行打印
            System.out.println(p.getName() + "  单价:" + p.getPrice() // 商品名、单价
                    + " 元  数量:" + p.getQuantity()          // 数量
                    + "  小计:" + p.getSubtotal() + " 元");   // 小计
        }
        System.out.println("--------------------------------"); // 分隔线
        System.out.println("原价合计:" + total + " 元");       // 打印原价合计
        System.out.println("折扣率:" + (discount * 100) + "%"); // 打印折扣率(90 或 100)
        System.out.println("应付金额:" + due + " 元");         // 打印应付金额

        // 结账扣款:从账户余额中扣除应付金额(不足则余额变负,即欠账)
        account.deduct(due);                                // 调用扣款方法

        // 获取扣款后的余额
        double after = account.getBalance();                // after 为扣款后的余额

        if (after >= 0) {                                   // 若余额大于等于 0 → 结清,无欠账
            System.out.println(">> 结账成功!支付 " + due + " 元,剩余余额:" + after + " 元");
        } else {                                            // 否则余额不够 → 产生欠账
            System.out.println(">> 余额不足!已记账,欠账金额:" + (-after) + " 元"); // 打印欠账
            System.out.println(">> 账户当前余额:" + after + " 元(负数部分即欠账)");   // 打印负余额
        }

        System.out.println("=========== 感谢使用,再见! ==========="); // 打印结束语

        sc.close();                                         // 关闭 Scanner,释放资源
    }
}