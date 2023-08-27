package util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseUtil {

    public static HikariDataSource hikariDataSource;

    static {

        HikariConfig configuration = new HikariConfig();
        configuration.setDriverClassName("com.mysql.cj.jdbc.Driver");
        configuration.setUsername("root");
        configuration.setPassword("");
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/belajar_java_todolist?serverTimezone=Asia/Jakarta");

        // Pool
        configuration.setMaximumPoolSize(10);
        configuration.setMinimumIdle(5);
        configuration.setIdleTimeout(60_000); // 1 Minute
        configuration.setMaxLifetime(60 * 60 * 1000); // 1 hour

        hikariDataSource = new HikariDataSource(configuration);
    }
    public static HikariDataSource getDataSource(){
        return hikariDataSource;
    }
}
