package org.example.dao;

import org.example.model.Aluguel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AluguelDAO {
    public void cadastrarAluguel(Aluguel aluguel){
        String sql = "INSERT INTO aluguel (cliente_id, filme_id, dataAluguel,) VALUES (?,?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, aluguel.getClienteId());
            stmt.setInt(2, aluguel.getFilmeId());
            stmt.setDate(3, Date.valueOf(aluguel.getDataAluguel()));

            stmt.executeUpdate();

            System.out.println("\nAluguel cadastrado com sucesso!\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
