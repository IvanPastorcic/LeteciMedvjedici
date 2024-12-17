//package hr.fer.progi.backend.db;
//
//import java.sql.*;
//
//public class Database {
//
//    static {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static final String DB_URL = "jdbc:postgresql://dpg-ctcpekggph6c73attlu0-a:5432/safebear_db:jdbc:postgresql://localhost:5432/db";
//    private static Database instance;
//
//    private Database() {
//
//    }
//
//    public static Database getInstance() {
//        if (instance == null) {
//            instance = new Database();
//            init();
//        }
//        return instance;
//    }
//
//    public static void init() {
//        try (Connection connection = Database.getInstance().getConnection();
//             Statement statement = connection.createStatement()) {
//            System.out.println("INIT DB");
//            statement.execute("RUNSCRIPT FROM 'classpath:init.sql'");
//        } catch (SQLException e) {
//            throw new DatabaseException(e);
//        }
//
//    }
//
//    public Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(DB_URL, "sa", "");
//    }
//
//}
