package tarefa4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
     private static Connection conn = null;

    private Conexao() {
        String userName = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/energia_eletrica"; 
        String driver = "com.mysql.jdbc.Driver";

        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            conn = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection conexao() {
        if (conn == null) {
             new Conexao();
        }
            return conn;
        }
    }
