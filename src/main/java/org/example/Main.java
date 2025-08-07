package org.example;

import org.example.dao.AluguelDAO;
import org.example.dao.ClienteDAO;
import org.example.dao.FilmeDAO;
import org.example.model.Aluguel;
import org.example.model.AluguelDevolucao;
import org.example.model.Cliente;
import org.example.model.Filme;

import java.time.LocalDate;
import java.util.Scanner;

import static java.lang.System.exit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        int opcao = 1;
        boolean sair = false;

        System.out.println("=== LOCADORA DE FILMES ===");
        System.out.println("1. Cadastrar cliente");
        System.out.println("2. Cadastrar filme");
        System.out.println("3. Realizar aluguel");
        System.out.println("4. Devolver filme");
        System.out.println("5. Listar todos os clientes");
        System.out.println("6. Listar todos os filmes");
        System.out.println("7. Listar todos os aluguéis");
        System.out.println("8. Listar aluguéis pendentes");
        System.out.println("9. Listar filmes por cliente");
        System.out.println("10. Listar clientes por filme");
        System.out.println("0. Sair");
        opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 0: {
                sair = true;
                System.out.println("Saindo...");
                exit(0);
                break;
            }
            case 1: {
                cadastrarCliente();
                break;
            }
            case 2: {
                cadastrarFilme();
                break;
            }
            case 3: {
                realizarAluguel();
                break;
            }
            case 4: {
                devolverFilmes();
                break;
            }
            case 5: {
                listarClientes();
                break;
            }
            case 6: {
                listarFilmes();
                break;
            }
            case 7: {
                listarAlugueis();
                break;
            }
            case 8: {
                listarAlugueisPendentes();
                break;
            }
            case 9: {
                listarFilmesPorClientes();
            }
        }

        if (!sair) {
            menu();
        }
    }

    private static void listarFilmesPorClientes() {
        FilmeDAO filmeDao = new FilmeDAO();

        System.out.println("\n---Filmes---");;
        for(Filme filme : filmeDao.listarFilmes()){
            System.out.println(filme);
        }
        System.out.println("Informe o id do filme que deseja visualizar os alugueis: ");
        int id = sc.nextInt();

        AluguelDAO aluguelDAO = new AluguelDAO();

        Filme filme = filmeDao.buscarFilme(id);

        System.out.println("Clientes que alugaram o filme '" + filme);
        for(Cliente cliente : aluguelDAO.listarFilmesPorCLiente(id)){
            System.out.println(cliente);
        }
    }

    private static void listarAlugueisPendentes() {
        AluguelDAO dao = new AluguelDAO();
        System.out.println("\n---Alugueis pendentes---");
        for(AluguelDevolucao aluguelDevolucao : dao.listarAlugueisDevolucoes()){
            System.out.println(aluguelDevolucao);
        }
    }

    private static void listarAlugueis() {
        AluguelDAO dao = new AluguelDAO();
        System.out.println("\n---Histórico de alugueis---");
        for(AluguelDevolucao aluguelDevolucao : dao.listarAlugueis()){
            System.out.println(aluguelDevolucao + "Data de devolução: " + aluguelDevolucao.getDataDevolucao() + "\n");
        }
    }

    private static void listarFilmes() {
        FilmeDAO dao = new FilmeDAO();
        System.out.println("\n---Filmes---");
        for(Filme filme : dao.listarFilmes()){
            System.out.println(filme);
        }
    }

    private static void listarClientes() {
        ClienteDAO dao = new ClienteDAO();
        System.out.println("\n---Clientes Cadastrados---");
        for(Cliente cliente : dao.listarClientes()){
            System.out.println(cliente);
        }
    }

    private static void devolverFilmes() {
        System.out.println("---Devolver Filme---");

        AluguelDAO aluguelDAO = new AluguelDAO();
        for(AluguelDevolucao aluguelDevolucao : aluguelDAO.listarAlugueisDevolucoes()){
            System.out.println(aluguelDevolucao);
        }
        System.out.println("Informe o id do Aluguel que deseja devolver: ");
        int id = sc.nextInt();
        sc.nextLine();

        aluguelDAO.registroDevolucao(id);

    }

    private static void realizarAluguel() {
        System.out.println("---Realizar Aluguel---");

        ClienteDAO clientedao = new ClienteDAO();

        System.out.println("\nClientes: ");
        for(Cliente clientes : clientedao.listarClientes()){
            System.out.println(clientes);
        }

        System.out.println("Informe o id do Cliente que deseja realizar o aluguel: ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        FilmeDAO filmedao = new FilmeDAO();

        System.out.println("\nFilmes: ");
        for(Filme filme : filmedao.listarFilmes()){
            System.out.println(filme);
        }

        System.out.println("Informe o id do Filme que deseja alugar: ");
        int idFilme = sc.nextInt();
        sc.nextLine();

        LocalDate dataAluguel = LocalDate.now();

        Aluguel aluguel = new Aluguel(idCliente, idFilme, dataAluguel, null);

        AluguelDAO aluguelDao = new AluguelDAO();

        aluguelDao.cadastrarAluguel(aluguel);
    }

    private static void cadastrarFilme() {

        System.out.println("---Cadastro Filme---");
        System.out.println("Informe o título do filme: ");
        String titulo = sc.nextLine();

        System.out.println("Informe o genero do filme: ");
        String genero = sc.nextLine();

        System.out.println("Informe o ano do filme: ");
        int anoLancamento = sc.nextInt();
        sc.nextLine();

        Filme filme = new Filme(titulo, genero, anoLancamento);
        FilmeDAO dao = new FilmeDAO();

        dao.cadastrarFilme(filme);
    }

    private static void cadastrarCliente() {

        System.out.println("---Cadastro Cliente---");
        System.out.println("Informe o nome do cliente: ");
        String nome = sc.nextLine();

        System.out.println("Informe o email do cliente: ");
        String email = sc.nextLine();

        Cliente cliente = new Cliente(nome,email);
        ClienteDAO dao = new ClienteDAO();
        dao.cadastrarCliente(cliente);
    }

}