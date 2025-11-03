package querys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public final class App {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/sports?charSet=UTF8";
        String user = "postgres";
        String password = "1qazZAQ!";

        String query = "SELECT d.cod AS codigo, d.nombre AS deporte, dp.nombre AS deportista FROM DEPORTES d JOIN DEPORTISTAS dp ON d.cod = dp.cod_deporte";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String deporte = rs.getString("deporte");
                String deportista = rs.getString("deportista");
                System.out.printf("Deporte: %s, Deportista: %s%n", deporte, deportista);
            }
        } catch (SQLException e) {
            System.err.println("Error de connexió o consulta:");
            e.printStackTrace();
        }
    }
}
