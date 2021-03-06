package com.rabbitown.yalib.database.impl;

import com.rabbitown.yalib.database.DataBase;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDataBaseImpl implements DataBase {
    @Override
    public Connection loadDataBase(String dataBaseName) {
        Connection connection;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataBaseName);
            Bukkit.getServer().getLogger().info("[SQLITE][INFO] 数据库加载成功");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            Bukkit.getServer().getLogger().warning("[SQLITE][ERROR] 连接数据库发生错误");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Connection loadDateBase(String dataBaseURL, String username, String password) {
        return null;
    }

    @Override
    public void execute(Connection connection, String sql) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
