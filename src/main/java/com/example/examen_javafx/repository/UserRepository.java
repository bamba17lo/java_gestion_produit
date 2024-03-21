package com.example.examen_javafx.repository;

import com.example.examen_javafx.model.BD;
import com.example.examen_javafx.model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private static Connection connection;

    public UserRepository() {
        this.connection = new BD().getConnection();
    }

    public static void AjoutUser(UserModel user) {
        try {
            String sql = "INSERT user (nom,prenom,telephone,email,login,password) values (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getNom());
            statement.setString(2, user.getPrenom());
            statement.setString(3, user.getTelephone());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getLogin());
            statement.setString(6, user.getPassword());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int login(String login, String password) {
        try {
            String sql = "SELECT id FROM user WHERE login = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, login);
            statement.setString(2, password);


            try (ResultSet res = statement.executeQuery()) {
                if (res.next()) {
                    // Si une ligne est retournée, le login et le mot de passe sont valides
                    return res.getInt("id");
                } else {


                    return -1;
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}