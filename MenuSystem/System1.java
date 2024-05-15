
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// 订单状态观察者接口
interface OrderStatusObserver {
    void updateOrderStatus(String status);
}

// 订单状态主题接口
interface OrderStatusSubject {
    void registerObserver(OrderStatusObserver observer);
    void removeObserver(OrderStatusObserver observer);
    void notifyObservers(String status);
}

// 订单类
class Order implements OrderStatusSubject {
    private String status;
    private List<OrderStatusObserver> observers = new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObservers(status);
    }

    @Override
    public void registerObserver(OrderStatusObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(OrderStatusObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String status) {
        for (OrderStatusObserver observer : observers) {
            observer.updateOrderStatus(status);
        }
    }
}

// JFrame用户界面实现类
class JFrameUserInterface implements OrderStatusObserver {
    private JFrame frame;

    public JFrameUserInterface() {
        frame = new JFrame("Order Status");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void updateOrderStatus(String status) {
        JOptionPane.showMessageDialog(frame, "Order Status: " + status);
    }
}

public class System1 {
    public static void main(String[] args) {
        // 创建订单和用户界面
        Order order = new Order();
        JFrameUserInterface ui = new JFrameUserInterface();

        // 将用户界面注册为订单状态的观察者
        order.registerObserver(ui);

        // 模拟订单状态的改变
        order.setStatus("Order Received");
        order.setStatus("Preparing Order");
        order.setStatus("Order Ready");
        order.setStatus("Order Delivered");
    }
}
