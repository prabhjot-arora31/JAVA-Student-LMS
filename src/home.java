import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;;

public class home extends JFrame {

    home(String s) {
        super(s);

        SwingUtilities.invokeLater(() -> {
            // this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JLabel movieLabel = new JLabel("Movies");
            movieLabel.setBackground(java.awt.Color.WHITE);
            movieLabel.setForeground(java.awt.Color.BLACK);
            movieLabel.setFont(new Font("Times", Font.BOLD, 30));
            add(movieLabel);
            // setLayout(new GridLayout(2, 1));
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Use
            // BoxLayout
            JScrollPane scrollPane = new JScrollPane(mainPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            // scrollPane.setVisible(true);
            add(scrollPane);
            // setLayout(new GridLayout(5, 1));
            setSize(500, 500);
            try {
                String api = "https://api.themoviedb.org/3/discover/movie?api_key=fafef439971c0bedf1c12e7a5be971c2";
                URL url = new URL(api);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int statusCode = connection.getResponseCode();
                if (statusCode == 200) {

                    ObjectMapper objectMapper = new ObjectMapper();
                    MovieData obj = objectMapper.readValue(connection.getInputStream(), MovieData.class);
                    List<Movie> movies = obj.getResults();
                    mainPanel.add(movieLabel);
                    for (Movie movie : movies) {
                        // URL imageUrl = new URL("https://image.tmdb.org/t/p/original" +
                        // movie.getBackdrop_path());
                        // BufferedImage img2 = ImageIO.read(imageUrl);
                        // ImageIcon icon = new ImageIcon(img2);
                        // JLabel imageLabel = new JLabel(icon);
                        JLabel label2 = new JLabel("Title:  " + movie.getTitle());
                        JLabel label3 = new JLabel("Overview: " + movie.getOverview());
                        label2.setBackground(java.awt.Color.WHITE);
                        label2.setForeground(java.awt.Color.BLUE);
                        label2.setFont(new Font("Times", Font.BOLD, 20));
                        // label3.setSize(100, 100);
                        label3.setPreferredSize(new Dimension(85, label3.getPreferredSize().height));
                        label3.setBackground(java.awt.Color.WHITE);
                        label3.setForeground(java.awt.Color.BLACK);
                        label3.setFont(new Font("Times", Font.BOLD, 14));
                        // URL img = new URL("https://image.tmdb.org/t/p/original" +
                        // movie.getBackdrop_path());
                        // Image image = ImageIO.read(img);
                        // JLabel image2 = new JLabel(new ImageIcon(image));
                        // JPanel collect = new JPanel();
                        // collect.add(label);
                        // collect.add(label2);
                        mainPanel.add(label2);
                        mainPanel.add(label3);
                        // mainPanel.add(label2);
                        // mainPanel.add(collect);
                    }
                } else {
                    System.out.println("HTTP error: " + connection.getResponseCode());
                }
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            setVisible(true);
        });

    }
}
