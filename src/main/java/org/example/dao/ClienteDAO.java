package org.example.dao;

import org.example.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Cliente> listarClientes(){
        String sql = "SELECT id, nome, email FROM cliente";
        List<Cliente> clientes = new ArrayList();

        try(Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                Cliente cliente = new Cliente(id, nome, email);
                clientes.add(cliente);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }
}
