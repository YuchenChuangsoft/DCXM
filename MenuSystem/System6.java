import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// 策略模式：定义不同的行为接口，让不同的类实现这些接口
interface FoodQuantityChanger {
    void changeQuantity(int index, int change);
}

class AddToCart implements FoodQuantityChanger {
    private int index;

    public AddToCart(int index) {
        this.index = index;
    }

    @Override
    public void changeQuantity(int index, int change) {
        // 实现添加到购物车的逻辑
    }
}

class ChangeQuantity implements FoodQuantityChanger {
    private int change;

    public ChangeQuantity(int change) {
        this.change = change;
    }

    @Override
    public void changeQuantity(int index, int change) {
        // 实现改变数量的逻辑
    }
}

// 单例模式：确保一个类只有一个实例，并提供一个全局访问点
class MenuSystemSingleton {
    private static MenuSystemSingleton instance;
    private MenuSystem menuSystem;

    private MenuSystemSingleton() {
        menuSystem = new MenuSystem();
    }

    public static MenuSystemSingleton getInstance() {
        if (instance == null) {
            instance = new MenuSystemSingleton();
        }
        return instance;
    }

    public void createAndShowGUI() {
        menuSystem.createAndShowGUI();
    }
}

// 装饰者模式：动态地给一个对象添加一些额外的职责
class FoodDecorator extends Food {
    protected Food decoratedFood;

    public FoodDecorator(Food decoratedFood) {
        this.decoratedFood = decoratedFood;
    }

    // 可以在这里添加额外的功能，比如折扣、促销等
}

// 工厂方法模式：定义一个创建对象的接口，但让子类决定实例化哪个类
interface FoodFactory {
    Food createFood(String name, double price);
}

class SimpleFoodFactory implements FoodFactory {
    @Override
    public Food createFood(String name, double price) {
        return new Food(name, price);
    }
}

// 命令模式：将请求封装成对象，以便使用不同的请求、队列或者日志来参数化其他对象
class OrderCommand implements ActionListener {
    private FoodQuantityChanger changer;
    private int index;

    public OrderCommand(FoodQuantityChanger changer, int index) {
        this.changer = changer;
        this.index = index;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        changer.changeQuantity(index, 1);
    }
}

// 观察者模式：定义对象之间的一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并自动更新
interface Observer {
    void update(double totalPrice);
}

class TotalPriceObserver implements Observer {
    private JLabel totalPriceLabel;

    public TotalPriceObserver(JLabel totalPriceLabel) {
        this.totalPriceLabel = totalPriceLabel;
    }

    @Override
    public void update(double totalPrice) {
        totalPriceLabel.setText("总价: " + totalPrice);
    }
}

// 适配器模式：将一个类的接口转换成客户希望的另外一个接口
class FoodQuantityAdapter implements FoodQuantityChanger {
    private JTextField quantityField;

    public FoodQuantityAdapter(JTextField quantityField) {
        this.quantityField = quantityField;
    }

    @Override
    public void changeQuantity(int index, int change) {
        // 实现适配器逻辑，将文本框的变化适配到数量变化
    }
}

// 原始的Food类和其他代码保持不变

public class MenuSystem {
    // ... 省略其他代码 ...

    public void createAndShowGUI() {
        // ... 省略其他代码 ...

        // 使用策略模式和命令模式
        for (int i = 0; i < foodNames.length; i++) {
            FoodQuantityChanger addToCart = new AddToCart(i);
            FoodQuantityChanger changeQuantity = new ChangeQuantity(1);
            addButtons[i].addActionListener(new OrderCommand(addToCart, i));
            plusButtons[i].addActionListener(new OrderCommand(changeQuantity, i));
            minusButtons[i].addActionListener(new OrderCommand(changeQuantity, i));

            // 使用观察者模式
            Observer observer = new TotalPriceObserver(totalPriceLabel);
            observer.update(totalPrice);

            // 使用适配器模式
            FoodQuantityAdapter adapter = new FoodQuantityAdapter(quantityFields[i]);
            adapter.changeQuantity(i, 1);
        }

        // ... 省略其他代码 ...
    }

    // ... 省略其他代码 ...
}

// 主函数中使用单例模式
public class System6 {
    public static void main(String[] args) {
        MenuSystemSingleton.getInstance().createAndShowGUI();
    }
}
