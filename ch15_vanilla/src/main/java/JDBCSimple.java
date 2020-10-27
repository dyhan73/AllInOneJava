import java.sql.*;

public class JDBCSimple {
    public boolean loadDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Connection getDBConnection() {
        String url = "jdbc:mysql://localhost:3306/order_system?serverTimezone=Asia/Seoul";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, "root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public Statement getStatement(Connection conn) {
        Statement statement = null;
        try {
            statement = conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return statement;
    }

    public void selectCustomers(Statement statement) {
        try {
            String sql = "select * from customer";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Long id = rs.getLong("customer_id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                System.out.format("%d, %s, %s, %s", id, name, address, email);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void insertCustomers(Statement statement) {
        try {
            statement.executeUpdate("insert into customer (name, address, email) values ('한대영', '용인시', 'dyhan@sk.com')");
            statement.executeUpdate("insert into customer (name, address, email) values ('장병준', '서울시', 'jang@sk.com')");
            statement.executeUpdate("insert into customer (name, address, email) values ('김규태', '구리시', 'raphael@sk.com')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JDBCSimple proc = new JDBCSimple();

//        if (proc.loadDriver()) return;

        Connection conn = proc.getDBConnection();
        if (conn == null) return;

        Statement stmt = proc.getStatement(conn);
        if (stmt == null) return;

//        proc.insertCustomers(stmt);

        proc.selectCustomers(stmt);

        try {
            stmt.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


}
