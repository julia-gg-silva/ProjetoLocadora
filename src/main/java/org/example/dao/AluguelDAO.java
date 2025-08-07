package org.example.dao;

import org.example.model.Aluguel;
import org.example.model.AluguelDevolucao;
import org.example.model.Cliente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AluguelDAO {

    public void cadastrarAluguel(Aluguel aluguel) {
        String sql = "INSERT INTO aluguel (cliente_id, filme_id, dataAluguel) VALUES (?,?,?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, aluguel.getClienteId());
            stmt.setInt(2, aluguel.getFilmeId());
            stmt.setDate(3, Date.valueOf(aluguel.getDataAluguel()));

            stmt.executeUpdate();

            System.out.println("\nAluguel cadastrado com sucesso!\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AluguelDevolucao> listarAlugueisDevolucoes() {
        String sql = "SELECT a.id, f.titulo as tituloFilme, c.nome as nomeCliente, a.dataAluguel\n" +
                "FROM aluguel a\n" +
                "LEFT JOIN filme f\n" +
                "ON a.filme_id = f.id\n" +
                "LEFT JOIN cliente c\n" +
                "ON a.cliente_id = c.id\n" +
                "WHERE dataDevolucao IS NULL;";
        List<AluguelDevolucao> alugueis = new ArrayList<>();

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idAluguel = rs.getInt("id");
                String tituloFilme = rs.getString("tituloFilme");
                String nomeCliente = rs.getString("nomeCliente");
                LocalDate dataAluguel = rs.getDate("dataAluguel").toLocalDate();

                AluguelDevolucao aluguelDevolucao = new AluguelDevolucao(idAluguel, tituloFilme, nomeCliente, dataAluguel);
                alugueis.add(aluguelDevolucao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alugueis;
    }

    public void registroDevolucao(int id) {
        String sql = "UPDATE aluguel set dataDevolucao = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            stmt.setInt(2, id);

            stmt.executeUpdate();


            System.out.println("\n Filme devolvido com sucesso!\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AluguelDevolucao> listarAlugueis() {
        String sql = "SELECT a.id, f.titulo as tituloFilme, c.nome as nomeCliente, a.dataAluguel, a.dataDevolucao\n" +
                "FROM aluguel a\n" +
                "LEFT JOIN filme f\n" +
                "ON a.filme_id = f.id\n" +
                "LEFT JOIN cliente c\n" +
                "ON a.cliente_id = c.id\n";

        List<AluguelDevolucao> alugueis = new ArrayList<>();
        AluguelDevolucao aluguelDevolucao;

        try (Connection connection = Conexao.conectar();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idAluguel = rs.getInt("id");
                String tituloFilme = rs.getString("tituloFilme");
                String nomeCliente = rs.getString("nomeCliente");
                LocalDate dataAluguel = rs.getDate("dataAluguel").toLocalDate();
                if (rs.getDate("dataDevolucao") != null) {
                    LocalDate dataDevolucao = rs.getDate("dataDevolucao").toLocalDate();
                    aluguelDevolucao = new AluguelDevolucao(idAluguel, tituloFilme, nomeCliente, dataAluguel, dataDevolucao);
                } else {
                    aluguelDevolucao = new AluguelDevolucao(idAluguel, tituloFilme, nomeCliente, dataAluguel);
                }

                alugueis.add(aluguelDevolucao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alugueis;
    }

    public List<Cliente> listarFilmesPorCLiente(int id){

        String sql = "SELECT c.id, c.nome, c.email\n" +
                    "FROM filme f\n" +
                    "RIGHT JOIN aluguel a\n" +
                    "ON f.id = a.filme_id\n" +
                    "RIGHT JOIN cliente c\n" +
                    "ON c.id = a.cliente_id\n" +
                    "WHERE f.id = ?;";
        List<Cliente> clientes = new ArrayList<>();

        try(Connection connection = Conexao.conectar();
        PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int idCliente = rs.getInt("id");
                String nomeCliente = rs.getString("nome");
                String emailCliente = rs.getString("email");

                Cliente cliente = new Cliente(idCliente,nomeCliente, emailCliente);
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
}
