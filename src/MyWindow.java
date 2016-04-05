import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MyWindow extends JFrame {

    public MyWindow() {
        setTitle("My First Window"); // Устанавливаем заголовок окна
        setSize(400, 400); // Размер окна
        setLocation(1200, 200); // Положение
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Включаем завершение работы программы по зарытию окна
        JTextArea jta = new JTextArea(); // Создаем большое текстовое поле
        jta.setBackground(Color.lightGray); // Красим его в серый цвет
        add(jta); // добавляем его на форму
        JPanel southPanel = new JPanel(); // Создаем южную(нижнюю) панель
        southPanel.setLayout(new BorderLayout()); // Задаем для нее мспособ компоновки элементов
        add(southPanel, BorderLayout.SOUTH); // Добавляем панель на форму
        JButton jb = new JButton("Найти"); // Создаем кнопку
        JTextField jtf = new JTextField(); // И однострочное текстовое поле
        southPanel.add(jb, BorderLayout.EAST); // Кнопки ставим на восток нижней панели
        southPanel.add(jtf, BorderLayout.CENTER); // Текстовое пле в центр панели
//        jta.setFont(new Font("Times New Roman", Font.ITALIC, 24)); // Для большого текстового поля устанавливаем шрифт
        jta.setText(getAllDb());
        jb.addActionListener(new ActionListener() { // вешаем на кнопку прослушивальщик действий
            @Override
            public void actionPerformed(ActionEvent e) {
                jta.setText(getItemBySurmane(jtf.getText())); // Сообщение из нижнего поля добавляется в верхнее
                jtf.setText(""); // Нижнее текстовое поле очищается
            }
        });
        setVisible(true); // Переключаем видимость формы в true

    }

    public String getAllDb() {
        Connection c = null; // Соединение с БД
        Statement stmt = null; //
        String result = "";
        try {
            Class.forName("org.sqlite.JDBC"); // регистрируем драйвер для работы с БД
            c = DriverManager.getConnection("jdbc:sqlite:phone_base.db3"); // открываем соединение
            stmt = c.createStatement(); // инициализируем объект, который умеет посылать запросы в БД
            ResultSet rs; // Читалка результата запросов
            rs = stmt.executeQuery("SELECT * FROM base"); // Вытаскиваем все записи у которых ID < 4
            while (rs.next()) { // Читаем до тех пор пока еще есть строки в ответной таблице
                result += rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + '\n';
            }
        } catch (Exception e) {
            System.out.println("Error"); // Если не удалось подключиться, пишем ошибку
        } finally {
            try {
                c.close(); // закрываем соединение
            } catch (SQLException e) {
            }
        }
        return result;
    }

    public String getItemBySurmane(String _surname) {
        Connection c = null; // Соединение с БД
        Statement stmt = null; //
        String result = "";
        try {
            Class.forName("org.sqlite.JDBC"); // регистрируем драйвер для работы с БД
            c = DriverManager.getConnection("jdbc:sqlite:phone_base.db3"); // открываем соединение
            stmt = c.createStatement(); // инициализируем объект, который умеет посылать запросы в БД
            ResultSet rs; // Читалка результата запросов
            rs = stmt.executeQuery("SELECT * FROM base WHERE surname='" + _surname+"'"); // Вытаскиваем все записи у которых ID < 4
            while (rs.next()) { // Читаем до тех пор пока еще есть строки в ответной таблице
                result += rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5) + " " + '\n';
            }
        } catch (Exception e) {
            System.out.println("Error"); // Если не удалось подключиться, пишем ошибку
        } finally {
            try {
                c.close(); // закрываем соединение
            } catch (SQLException e) {
            }
        }
        return result;
    }

}
