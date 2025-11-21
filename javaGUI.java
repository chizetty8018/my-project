
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Графический интерфейс для управления пиццами Лабораторная работа 6 - Java
 * Swing
 */
class PizzaGUI extends JFrame {

    private PizzaRepository pizzaRepo = new PizzaRepository();
    private JTable pizzaTable;
    private DefaultTableModel tableModel;
    private JLabel statsLabel;

    public PizzaGUI() {
        setTitle("Управление пиццами");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        initComponents();
        loadInitialData();
        updateStats();
    }

    private void initComponents() {
        // Основная панель
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Заголовок
        JLabel titleLabel = new JLabel("Система управления пиццами", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Панель с кнопками
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Добавить пиццу");
        JButton editButton = new JButton("Редактировать");
        JButton deleteButton = new JButton("Удалить");
        JButton refreshButton = new JButton("Обновить");
        JButton statsButton = new JButton("Статистика");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(statsButton);

        // Таблица для отображения пицц
        String[] columnNames = {"Тип", "Цена (руб)", "Вес (г)", "Диаметр (см)", "Калории", "Доп. информация"};
        tableModel = new DefaultTableModel(columnNames, 0);
        pizzaTable = new JTable(tableModel);
        pizzaTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane tableScrollPane = new JScrollPane(pizzaTable);

        // Панель статистики
        JPanel statsPanel = new JPanel(new FlowLayout());
        statsLabel = new JLabel("Всего пицц: 0");
        statsPanel.add(statsLabel);

        // Добавляем компоненты на основную панель
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(statsPanel, BorderLayout.SOUTH);

        // Обработчики событий
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPizza();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editPizza();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletePizza();
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });

        statsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showStatistics();
            }
        });

        add(mainPanel);
    }

    /**
     * Загружает начальные данные в репозиторий
     */
    private void loadInitialData() {
        // Добавляем демонстрационные пиццы
        pizzaRepo.addPizza(new Pepperoni(450.0, 800.0, 30.0, 1200.0, 3));
        pizzaRepo.addPizza(new Pepperoni(550.0, 1000.0, 35.0, 1500.0, 4));
        pizzaRepo.addPizza(new Cheese(400.0, 750.0, 30.0, 1100.0, "Моцарелла"));
        pizzaRepo.addPizza(new Cheese(500.0, 900.0, 35.0, 1300.0, "Чеддер"));
        pizzaRepo.addPizza(new Meat(480.0, 850.0, 30.0, 1400.0, "Ветчина"));
        pizzaRepo.addPizza(new Meat(580.0, 1100.0, 35.0, 1700.0, "Салями"));

        refreshTable();
    }

    /**
     * Обновляет таблицу с данными из репозитория
     */
    private void refreshTable() {
        tableModel.setRowCount(0); // Очищаем таблицу

        List<Food> pizzas = pizzaRepo.getAllPizzas();
        for (Food pizza : pizzas) {
            Object[] rowData = new Object[6];
            rowData[0] = pizza.getClass().getSimpleName();
            rowData[1] = pizza.getPrice();
            rowData[2] = pizza.getWeight();
            rowData[3] = pizza.getDiameter();
            rowData[4] = pizza.getCalories();

            // Дополнительная информация в зависимости от типа пиццы
            if (pizza instanceof Pepperoni) {
                rowData[5] = "Острота: " + ((Pepperoni) pizza).getSpicyLevel() + "/5";
            } else if (pizza instanceof Cheese) {
                rowData[5] = "Сыр: " + ((Cheese) pizza).getCheeseType();
            } else if (pizza instanceof Meat) {
                rowData[5] = "Мясо: " + ((Meat) pizza).getMeatType();
            } else {
                rowData[5] = "-";
            }

            tableModel.addRow(rowData);
        }

        updateStats();
    }

    /**
     * Обновляет статистику
     */
    private void updateStats() {
        int count = pizzaRepo.getCount();
        statsLabel.setText("Всего пицц: " + count);
    }

    /**
     * Диалог добавления новой пиццы
     */
    private void addPizza() {
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Pepperoni", "Cheese", "Meat"});

        JTextField priceField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField diameterField = new JTextField();
        JTextField caloriesField = new JTextField();
        JTextField extraField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Тип пиццы:"));
        panel.add(typeCombo);
        panel.add(new JLabel("Цена (руб):"));
        panel.add(priceField);
        panel.add(new JLabel("Вес (г):"));
        panel.add(weightField);
        panel.add(new JLabel("Диаметр (см):"));
        panel.add(diameterField);
        panel.add(new JLabel("Калории:"));
        panel.add(caloriesField);
        panel.add(new JLabel("Доп. информация:"));
        panel.add(extraField);
        panel.add(new JLabel("Pepperoni - уровень остроты (1-5)"));
        panel.add(new JLabel("Cheese - тип сыра"));
        panel.add(new JLabel("Meat - тип мяса"));

        int result = JOptionPane.showConfirmDialog(this, panel, "Добавить пиццу",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                double price = Double.parseDouble(priceField.getText());
                double weight = Double.parseDouble(weightField.getText());
                double diameter = Double.parseDouble(diameterField.getText());
                double calories = Double.parseDouble(caloriesField.getText());
                String extra = extraField.getText();
                String type = (String) typeCombo.getSelectedItem();

                Food newPizza = null;
                switch (type) {
                    case "Pepperoni":
                        int spicyLevel = Integer.parseInt(extra);
                        newPizza = new Pepperoni(price, weight, diameter, calories, spicyLevel);
                        break;
                    case "Cheese":
                        newPizza = new Cheese(price, weight, diameter, calories, extra);
                        break;
                    case "Meat":
                        newPizza = new Meat(price, weight, diameter, calories, extra);
                        break;
                }

                if (newPizza != null) {
                    pizzaRepo.addPizza(newPizza);
                    refreshTable();
                    JOptionPane.showMessageDialog(this, "Пицца добавлена успешно!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ошибка ввода чисел!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Редактирование выбранной пиццы
     */
    private void editPizza() {
        int selectedRow = pizzaTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите пиццу для редактирования!", "Внимание", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Food selectedPizza = pizzaRepo.getAllPizzas().get(selectedRow);

        JTextField priceField = new JTextField(String.valueOf(selectedPizza.getPrice()));
        JTextField weightField = new JTextField(String.valueOf(selectedPizza.getWeight()));
        JTextField diameterField = new JTextField(String.valueOf(selectedPizza.getDiameter()));
        JTextField caloriesField = new JTextField(String.valueOf(selectedPizza.getCalories()));
        JTextField extraField = new JTextField();

        // Заполняем дополнительное поле в зависимости от типа пиццы
        if (selectedPizza instanceof Pepperoni) {
            extraField.setText(String.valueOf(((Pepperoni) selectedPizza).getSpicyLevel()));
        } else if (selectedPizza instanceof Cheese) {
            extraField.setText(((Cheese) selectedPizza).getCheeseType());
        } else if (selectedPizza instanceof Meat) {
            extraField.setText(((Meat) selectedPizza).getMeatType());
        }

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("Тип пиццы:"));
        panel.add(new JLabel(selectedPizza.getClass().getSimpleName()));
        panel.add(new JLabel("Цена (руб):"));
        panel.add(priceField);
        panel.add(new JLabel("Вес (г):"));
        panel.add(weightField);
        panel.add(new JLabel("Диаметр (см):"));
        panel.add(diameterField);
        panel.add(new JLabel("Калории:"));
        panel.add(caloriesField);
        panel.add(new JLabel("Доп. информация:"));
        panel.add(extraField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Редактировать пиццу",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                selectedPizza.setPrice(Double.parseDouble(priceField.getText()));
                selectedPizza.setWeight(Double.parseDouble(weightField.getText()));
                selectedPizza.setDiameter(Double.parseDouble(diameterField.getText()));
                selectedPizza.setCalories(Double.parseDouble(caloriesField.getText()));

                String extra = extraField.getText();
                if (selectedPizza instanceof Pepperoni) {
                    ((Pepperoni) selectedPizza).setSpicyLevel(Integer.parseInt(extra));
                } else if (selectedPizza instanceof Cheese) {
                    ((Cheese) selectedPizza).setCheeseType(extra);
                } else if (selectedPizza instanceof Meat) {
                    ((Meat) selectedPizza).setMeatType(extra);
                }

                refreshTable();
                JOptionPane.showMessageDialog(this, "Пицца обновлена успешно!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ошибка ввода чисел!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Удаление выбранной пиццы
     */
    private void deletePizza() {
        int selectedRow = pizzaTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Выберите пиццу для удаления!", "Внимание", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Вы уверены, что хотите удалить выбранную пиццу?",
                "Подтверждение удаления",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (pizzaRepo.removePizza(selectedRow)) {
                refreshTable();
                JOptionPane.showMessageDialog(this, "Пицца удалена успешно!");
            }
        }
    }

    /**
     * Показывает статистику пицц
     */
    private void showStatistics() {
        List<Food> pizzas = pizzaRepo.getAllPizzas();

        if (pizzas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Нет пицц для отображения статистики!");
            return;
        }

        // Статистика по весу (из лабораторной работы 4)
        int heavyCount = 0;
        double totalCost = 0;
        double totalWeight = 0;

        for (Food pizza : pizzas) {
            if (pizza.getWeight() > 700.0) {
                heavyCount++;
            }
            totalCost += pizza.getPrice();
            totalWeight += pizza.getWeight();
        }

        double averagePrice = totalCost / pizzas.size();
        double averageWeight = totalWeight / pizzas.size();

        String stats = String.format(
                "СТАТИСТИКА ПИЦЦ\n\n"
                + "Всего пицц: %d\n"
                + "Пицц тяжелее 700г: %d\n"
                + "Общая стоимость: %.2f руб.\n"
                + "Средняя цена: %.2f руб.\n"
                + "Средний вес: %.2f г\n\n"
                + "Пиццы диаметром больше 28см:\n",
                pizzas.size(), heavyCount, totalCost, averagePrice, averageWeight
        );

        // Пиццы больше 28см (из лабораторной работы 5)
        for (Food pizza : pizzas) {
            if (pizza.getDiameter() > 28.0) {
                stats += String.format("- %s: %.2f руб.\n",
                        pizza.getClass().getSimpleName(), pizza.getPrice());
            }
        }

        JOptionPane.showMessageDialog(this, stats, "Статистика", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PizzaGUI().setVisible(true);
            }
        });
    }
}

/**
 * Класс-репозиторий для управления коллекцией пицц Обновлен для работы с
 * графическим интерфейсом
 */
class PizzaRepository {

    private List<Food> pizzas = new ArrayList<>();

    // Методы для работы с коллекцией
    public void addPizza(Food pizza) {
        pizzas.add(pizza);
    }

    public boolean removePizza(int index) {
        if (index >= 0 && index < pizzas.size()) {
            pizzas.remove(index);
            return true;
        }
        return false;
    }

    public boolean updatePizza(int index, Food newPizza) {
        if (index >= 0 && index < pizzas.size()) {
            pizzas.set(index, newPizza);
            return true;
        }
        return false;
    }

    public List<Food> getAllPizzas() {
        return new ArrayList<>(pizzas);
    }

    public int getCount() {
        return pizzas.size();
    }
}
