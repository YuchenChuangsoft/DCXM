import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Food {
    private String name;
    private double price;
    private int quantity;

    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getTotalPrice() {
        return price * quantity;
    }

    public String getName() {
        return null;
    }
}
 class Food1 {
    private String name;
    private double price;

    // 构造函数
    public Food1(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // 方法来获取名称
    public String getName() {
        return name;
    }
}


public class System5 {
    private static final Food[] foods = {new Food("菜品1", 10.0), new Food("菜品2", 15.0), new Food("菜品3", 20.0)};
    private int[] foodQuantities = new int[foods.length];
    private double totalPrice = 0.0;

    private JFrame frame;
    private JPanel panel;
    private JButton[] addButtons;
    private JButton[] plusButtons;
    private JButton[] minusButtons;
    private JTextField[] quantityFields;
    private JLabel[] itemLabels;
    private JLabel totalPriceLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                System5 menuSystem = new System5();
                menuSystem.createAndShowGUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void createAndShowGUI() {
        frame = new JFrame("点餐系统");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLayout(new GridLayout(foods.length + 1, 1));

        panel = new JPanel();
        frame.add(panel);

        addButtons = new JButton[foods.length];
        plusButtons = new JButton[foods.length];
        minusButtons = new JButton[foods.length];
        quantityFields = new JTextField[foods.length];
        itemLabels = new JLabel[foods.length];

        for (int i = 0; i < foods.length; i++) {
            JPanel foodPanel = new JPanel();
            foodPanel.setLayout(new GridLayout(1, 5));

            addButtons[i] = new JButton(foods[i].getName());
            addButtons[i].addActionListener(new AddToCartListener(i));
            foodPanel.add(addButtons[i]);

            plusButtons[i] = new JButton("+");
            plusButtons[i].addActionListener(new ChangeFoodQuantityListener(i, 1));
            foodPanel.add(plusButtons[i]);

            minusButtons[i] = new JButton("-");
            minusButtons[i].addActionListener(new ChangeFoodQuantityListener(i, -1));
            foodPanel.add(minusButtons[i]);

            quantityFields[i] = new JTextField("1");
            quantityFields[i].setEditable(false);
            foodPanel.add(quantityFields[i]);

            itemLabels[i] = new JLabel(foods[i].getName() + " - 价格: " + foods[i].getPrice());
            foodPanel.add(itemLabels[i]);

            panel.add(foodPanel);
        }

        JPanel checkoutPanel = new JPanel();
        checkoutPanel.setLayout(new GridLayout(1, 2));

        JButton checkoutButton = new JButton("结账");
        checkoutButton.addActionListener(new CheckoutListener());
        checkoutPanel.add(checkoutButton);

        totalPriceLabel = new JLabel("总价: " + totalPrice);
        checkoutPanel.add(totalPriceLabel);

        panel.add(checkoutPanel);

        frame.setVisible(true);
    }

    class AddToCartListener implements ActionListener {
        private int index;

        public AddToCartListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int quantity = foodQuantities[index];
            quantityFields[index].setText(String.valueOf(quantity));
        }
    }

    class ChangeFoodQuantityListener implements ActionListener {
        private int index;
        private int change;

        public ChangeFoodQuantityListener(int index, int change) {
            this.index = index;
            this.change = change;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int quantity = foodQuantities[index] + change;
            if (quantity < 0) {
                quantity = 0;
            }
            foodQuantities[index] = quantity;
            quantityFields[index].setText(String.valueOf(quantity));
        }
    }

    class CheckoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double total = 0.0;
            for (int i = 0; i < foods.length; i++) {
                total += foods[i].getTotalPrice() * foodQuantities[i];
            }
            totalPrice = total;
            totalPriceLabel.setText("总价: " + totalPrice);
        }
    }
}
