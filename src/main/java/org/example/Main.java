package org.example;

import org.example.dao.ClienteDAO;
import org.example.dao.FilmeDAO;
import org.example.model.Cliente;
import org.example.model.Filme;

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
        }

        if (!sair) {
            menu();
        }
    }

    private static void cadastrarFilme() {

        System.out.println("---Cadastro Filme---");
        System.out.println("Informe o título do filme: ");
        String titulo = sc.nextLine();

        System.out.println("Informe o genero do filme: ");
        String genero = sc.nextLine();

        System.out.println("Informe o ano do filme: ");
        int anoLancamento = sc.nextInt();

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