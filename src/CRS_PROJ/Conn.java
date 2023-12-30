package CRS_PROJ;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
    Connection c;
    Statement s;

    public Conn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.c = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "root", "hassan123");
            this.s = this.c.createStatement();
        } catch (Exception var2) {
            System.out.println(var2);
        }

    }
}
