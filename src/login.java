import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.cj.protocol.Resultset;

import javafx.scene.paint.Color;

class login extends JFrame {
    login(String s) {
        // JFrame frame = new JFrame("Login");
        super(s);
        setLayout(new GridLayout(6, 1));
        JLabel loginlJLabel = new JLabel("Login");
        loginlJLabel.setForeground(java.awt.Color.BLUE);
        loginlJLabel.setFont(new Font("Times", Font.BOLD, 35));
        setLayout(new GridLayout(5, 1));
        setResizable(false);
        setSize(500, 500);
        // 1st panel
        JPanel loginTextPanel = new JPanel();
        loginTextPanel.add(loginlJLabel);
        add(loginTextPanel);
        // 2nd panel
        // email
        JPanel p3 = new JPanel();
        JLabel emailLabel = new JLabel("Email");

        p3.add(emailLabel);
        emailLabel.setFont(new Font("Times", Font.PLAIN, 20));

        JTextField tf2 = new JTextField(20);
        tf2.setFont(new Font("Times", Font.PLAIN, 20));

        p3.add(tf2);
        add(p3);
        // 3rd panel
        // email
        JPanel p4 = new JPanel();
        JLabel pwdLabel = new JLabel("Password");

        p4.add(pwdLabel);
        pwdLabel.setFont(new Font("Times", Font.PLAIN, 20));

        JTextField tf3 = new JTextField(20);
        tf3.setFont(new Font("Times", Font.PLAIN, 20));

        p4.add(tf3);
        add(p4);
        // 4th panel
        JPanel p5 = new JPanel();
        JButton login = new JButton("Login");
        login.setBackground(java.awt.Color.BLUE);
        login.setForeground(java.awt.Color.WHITE);
        login.setFont(new Font("Times", Font.BOLD, 20));
        p5.add(login);
        // click event on login button
        login.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("login");
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root",
                            "avcdFtr5#%@1*fGj");
                    String email = tf2.getText();
                    String sql = "select * from t1 where email='" + email + "'";
                    Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
                    ResultSet rslt = statement.executeQuery(sql);
                    if (rslt.next()) {
                        rslt.absolute(1);
                        String realPwd = rslt.getString(4);
                        String enteredPwd = tf3.getText().toString();
                        System.out.println(rslt.getString(2) + rslt.getString(3) + rslt.getString(4));
                        // System.out.println("Insertion completed successfully");
                        System.out.println("Entered PWD: " + enteredPwd);
                        System.out.println("Actual PWD: " + realPwd);
                        if (realPwd.equals(enteredPwd)) {
                            System.out.println("Login successful");
                            new home("Home");
                            

                        } else {
                            System.out.println("Login failed");
                        }
                    } else {
                        System.out.println("User not found");
                    }
                    conn.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        add(p5);
        // 5th panel
        JButton register = new JButton("Register");
        register.setBackground(java.awt.Color.WHITE);
        register.setForeground(java.awt.Color.BLUE);
        register.setFont(new Font("Times", Font.PLAIN, 17));

        JPanel registerPanel = new JPanel();
        registerPanel.add(register);
        setIconImage(Toolkit.getDefaultToolkit()
                .getImage("images/logo.png"));
        setVisible(true);
        add(registerPanel);
    }
}
