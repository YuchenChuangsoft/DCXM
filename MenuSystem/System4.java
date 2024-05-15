import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Food {
    private String name;
    private double price;
    private int quantity;

    // 构造函数，getter和setter方法省略

    public double getTotalPrice() {
        return price * quantity;
    }
}

public class System4 {
    private static final String[] foodNames = {"菜品1", "菜品2", "菜品3"}; // 假设菜品名称和价格存储在数组中
    private static final double[] foodPrices = {10.0, 15.0, 20.0}; // 假设价格对应数组中的索引
    private int[] foodQuantities = new int[foodNames.length]; // 初始化菜品数量数组
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
                System4 menuSystem = new System4();
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
        frame.setLayout(new GridLayout(4, 3));

        panel = new JPanel();
        frame.add(panel);

        // 创建菜品按钮、加减按钮和数量输入框
        addButtons = new JButton[foodNames.length];
        plusButtons = new JButton[foodNames.length];
        minusButtons = new JButton[foodNames.length];
        quantityFields = new JTextField[foodNames.length];
        itemLabels = new JLabel[foodNames.length];

        for (int i = 0; i < foodNames.length; i++) {
            addButtons[i] = new JButton(foodNames[i]);
            addButtons[i].addActionListener(new AddToCartListener(i));
            plusButtons[i] = new JButton("+");
            plusButtons[i].addActionListener(new ChangeFoodQuantityListener(i, 1));
            minusButtons[i] = new JButton("-");
            minusButtons[i].addActionListener(new ChangeFoodQuantityListener(i, -1));
            itemLabels[i] = new JLabel(foodNames[i] + " - 价格: " + foodPrices[i]);
            panel.add(addButtons[i]);
            panel.add(plusButtons[i]);
            panel.add(minusButtons[i]);
            quantityFields[i] = new JTextField("1");
            quantityFields[i].setEditable(false);
            panel.add(quantityFields[i]);
            panel.add(itemLabels[i]);
        }

        // 结算按钮和总价标签
        JButton checkoutButton = new JButton("结账");
        checkoutButton.addActionListener(new CheckoutListener());
        panel.add(checkoutButton);
        totalPriceLabel = new JLabel("总价: " + totalPrice);
        panel.add(totalPriceLabel);

        frame.setVisible(true);
    }

    private class ChangeFoodQuantityListener implements ActionListener {
        private int foodIndex;
        private int change;

        public ChangeFoodQuantityListener(int foodIndex, int change) {
            this.foodIndex = foodIndex;
            this.change = change;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int quantity = Integer.parseInt(quantityFields[foodIndex].getText());
            quantity += change;
            if (quantity < 0) {
                quantity = 0;
            }
            foodQuantities[foodIndex] = quantity;
            totalPrice -= foodPrices[foodIndex] * (quantityFields[foodIndex].getText().equals("") ? 0 : Integer.parseInt(quantityFields[foodIndex].getText()));
            quantityFields[foodIndex].setText(Integer.toString(quantity));
            totalPrice += foodPrices[foodIndex] * quantity;
            totalPriceLabel.setText("总价: " + totalPrice);
        }
    }

    private class AddToCartListener implements ActionListener {
        private int foodIndex;

        public AddToCartListener(int foodIndex) {
            this.foodIndex = foodIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int quantity = Integer.parseInt(quantityFields[foodIndex].getText());
            foodQuantities[foodIndex] += quantity;
            totalPrice += foodPrices[foodIndex] * quantity;
            totalPriceLabel.setText("总价: " + totalPrice);
        }
    }

    private class CheckoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            StringBuilder orderedItems = new StringBuilder();
            for (int i = 0; i < foodNames.length; i++) {
                if (foodQuantities[i] > 0) {
                    orderedItems.append(foodNames[i] + ": " + foodQuantities[i] + " 价格: " + foodPrices[i] + "\\n");
                }
            }
            JOptionPane.showMessageDialog(frame, "已点的菜品:\\n" + orderedItems.substring(0, orderedItems.length() - 1) + "\\n总价: " + totalPrice, "结账", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
