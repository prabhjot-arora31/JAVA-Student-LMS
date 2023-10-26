import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.View;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class UI extends JFrame {
    UI(String s) {
        super(s);
        JLabel isSuccessLabel = new JLabel("Successfully Registered");
        setIconImage(Toolkit.getDefaultToolkit()
                .getImage("images/logo.png"));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 500);
        // register label
        setLayout(new GridLayout(7, 1));
        JPanel p1 = new JPanel();
        JLabel register = new JLabel("Register");
        register.setForeground(Color.BLUE);
        register.setFont(new Font("Times", Font.BOLD, 35));
        p1.add(register);
        // name
        JPanel p2 = new JPanel();
        JLabel nameLabel = new JLabel("Name");
        p2.add(nameLabel);
        nameLabel.setFont(new Font("Times", Font.PLAIN, 20));

        JTextField tf = new JTextField(20);
        // tf.setPreferredSize(new Dimension(150, 20));
        // tf.setSize(40, 40);
        tf.setFont(new Font("Times", Font.PLAIN, 20));
        p2.add(tf);

        // email
        JPanel p3 = new JPanel();
        JLabel emailLabel = new JLabel("Email");

        p3.add(emailLabel);
        emailLabel.setFont(new Font("Times", Font.PLAIN, 20));

        JTextField tf2 = new JTextField(20);
        tf2.setFont(new Font("Times", Font.PLAIN, 20));

        p3.add(tf2);
        // password
        JPanel p4 = new JPanel();
        JLabel passwordLabel = new JLabel("Password");
        // passwordLabel.setIcon(/);
        p4.add(passwordLabel);
        passwordLabel.setFont(new Font("Times", Font.PLAIN, 20));

        JTextField tf3 = new JTextField(20);
        tf3.setFont(new Font("Times", Font.PLAIN, 20));

        p4.add(tf3);
        // button
        JButton btn = new JButton("Submit");
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(47, 97, 214));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(Color.BLUE);
            }

        });
        ;
        btn.setBackground(Color.BLUE);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Times", Font.BOLD, 20));
        JPanel p5 = new JPanel();
        p5.add(btn);
        btn.addActionListener(e -> {
            System.out.println("Hello");
            String name = tf.getText();
            String email = tf2.getText();
            String password = tf3.getText();
            // System.out.println(name + email + password);
            if ((name != null && email != null && password != null)
                    && (!name.isEmpty() && !email.isEmpty() && !password.isEmpty())) {
                new Db().getData(name, email, password);
                boolean isSuccess = new Db().register();
                if (isSuccess) {
                    tf.setText(" ");
                    tf2.setText(" ");
                    tf3.setText(" ");
                    add(isSuccessLabel);
                }
            } else {
                System.out.println("Please enter all required fields");
            }

        });
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        // add();
        add(p5);
        JButton login = new JButton("Login");
        login.setBackground(Color.WHITE);
        login.setForeground(Color.BLUE);
        login.setFont(new Font("Times", Font.PLAIN, 17));

        JPanel loginPanel = new JPanel();
        loginPanel.add(login);
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new login("Login");
                JFrame frame = new JFrame("Login");
                frame.setLayout(new GridLayout(6, 1));
                JLabel loginlJLabel = new JLabel("Login");
                loginlJLabel.setForeground(Color.BLUE);
                loginlJLabel.setFont(new Font("Times", Font.BOLD, 35));
                frame.add(loginlJLabel);
            };
        });
        add(loginPanel);
    }
}
