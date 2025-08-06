package org.example.dao;

import org.example.model.Filme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FilmeDAO {

    public void cadastrarFilme(Filme filme){
        String sql = "INSERT INTO filme (titulo, genero, anoLancamento) VALUES (?, ?, ?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,filme.getTitulo());
            stmt.setString(2, filme.getGenero());
            stmt.setInt(3,filme.getAnoLancamento());

            stmt.executeUpdate();

            System.out.println("\nFilme cadastrado com sucesso!\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
