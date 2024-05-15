
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// 菜品类
//class Food {
//    private String name;
//    private double price;
//
//    // 省略其他构造函数和方法
//}

// 菜品类
class Food {
    private String name;
    private double price;

    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - ￥" + price;
    }
}

// 订单类
class Order {
    private List<Food> foods = new ArrayList<>();

    // 添加菜品
    public void addFood(Food food) {
        foods.add(food);
    }

    // 获取菜品列表
    public List<Food> getFoods() {
        return foods;
    }

    // 计算总价
    private double calculateTotal() {
        double total = 0;
        for (Food food : foods) {
            total += food.getPrice();
        }
        return total;
    }
}

// 菜单类
class Menu {
    private List<Food> foods = new ArrayList<>();

    // 添加菜品
    public void addFood(Food food) {
        foods.add(food);
    }

    // 获取菜品列表
    public List<Food> getFoods() {
        return foods;
    }
}

// 顾客类
class Customer {
    private JFrame frame;
    private JPanel mainPanel;
    private JButton addFoodButton;
    private JButton printOrderButton;
    private JButton checkoutButton;
    private JList<Food> foodList;
    private JTextField totalTextField;
    private Order order;
    private Menu menu;

    public Customer() {
        order = new Order();
        menu = new Menu();
        // 初始化图形化界面
        initGui();
    }

    // 初始化图形化界面
    private void initGui() {
        // 创建JFrame
        frame = new JFrame("点餐系统");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建JPanel
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));

        // 创建添加菜品按钮
        addFoodButton = new JButton("添加菜品");
        addFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFood();
            }
        });
        mainPanel.add(addFoodButton);

        // 创建菜品列表
        foodList = new JList<Food>();
        foodList.setModel(new DefaultListModel<Food>());
        foodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mainPanel.add(new JScrollPane(foodList));

        // 创建打印订单按钮
        printOrderButton = new JButton("打印订单");
        printOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printOrder();
            }
        });
        mainPanel.add(printOrderButton);

        // 创建总价文本框
        totalTextField = new JTextField();
        totalTextField.setEditable(false);
        mainPanel.add(totalTextField);

        // 创建结账按钮
        checkoutButton = new JButton("结账");
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkout();
            }
        });
        mainPanel.add(checkoutButton);

        // 添加JPanel到JFrame
        frame.add(mainPanel);

        // 设置JFrame可见
        frame.pack();
        frame.setVisible(true);
    }

    // 添加菜品
    private void addFood() {
        // 省略获取食物列表的代码...
        // 如果选择菜品，添加到订单和列表
        // ...
    }

    // 打印订单
    private void printOrder() {
        // 省略打印订单的代码...
    }

    // 结账
    private void checkout() {
        // 省略结账的代码...
    }

    // 其他方法
    // 省略其他代码
}

public class System2 {
    public static void main(String[] args) {
        Customer customer = new Customer();
    }
}
