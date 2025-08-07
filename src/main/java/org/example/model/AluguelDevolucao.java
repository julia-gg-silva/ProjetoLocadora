package org.example.model;

import java.time.LocalDate;

public class AluguelDevolucao extends Aluguel{

    private String tituloFilme;
    private String nomeCliente;


    public AluguelDevolucao(int id, String tituloFilme, String nomeCliente, LocalDate dataAluguel) {
        super(id, dataAluguel);
        this.tituloFilme = tituloFilme;
        this.nomeCliente = nomeCliente;
    }

    public AluguelDevolucao(int id, String tituloFilme, String nomeCliente, LocalDate dataAluguel, LocalDate dataDevolucao) {
        super(id, dataAluguel, dataDevolucao);
        this.tituloFilme = tituloFilme;
        this.nomeCliente = nomeCliente;
    }

    @Override
    public String toString() {
        return "\nId aluguel: " + getId() +
                "\nTitulo filme: " + tituloFilme+
                "\nNome cliente: " + nomeCliente +
                "\nData do aluguel: " + getDataAluguel() +"\n";
    }

    public String getTituloFilme() {
        return tituloFilme;
    }

    public void setTituloFilme(String tituloFilme) {
        this.tituloFilme = tituloFilme;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
