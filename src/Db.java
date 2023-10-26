import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Db {
    static String name, email, password;

    static void getData(String nm, String em, String pwd) {
        name = nm;
        email = em;
        password = pwd;
        // return [name, email, password];
    }

    static String setName() {
        System.out.println(name);
        return name;
    }

    static String setEmail() {
        System.out.println(email);

        return email;
    }

    static String setPwd() {
        System.out.println(password);

        return password;
    }

    static boolean register() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root",
                    "avcdFtr5#%@1*fGj");
            PreparedStatement pstmt = conn
                    .prepareStatement("insert into t1(name, email , password) values(?,?,?)");
            pstmt.setString(1, setName());
            pstmt.setString(2, setEmail());
            pstmt.setString(3, setPwd());
            pstmt.execute();
            System.out.println("Insertion completed successfully");
            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
