package org.example.dao;

import org.example.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {

    public void cadastrarCliente(Cliente cliente) {

        String sql = "INSERT INTO cliente (nome, email) VALUES (?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,cliente.getNome());
            stmt.setString(2, cliente.getEmail());

            stmt.executeUpdate();
            System.out.println("\nUsuário cadastrado com sucesso!\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
