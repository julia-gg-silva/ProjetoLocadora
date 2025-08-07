package org.example.model;

import java.time.LocalDate;

public class Aluguel {
    private int id;
    private int clienteId;
    private int filmeId;
    private LocalDate dataAluguel;
    private LocalDate dataDevolucao;

    public Aluguel(int id, int clienteId, int filmeId, LocalDate dataAluguel, LocalDate dataDevolucao) {
        this.id = id;
        this.clienteId = clienteId;
        this.filmeId = filmeId;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
    }

    public Aluguel(int clienteId, int filmeId, LocalDate dataAluguel, LocalDate dataDevolucao){
        this.clienteId = clienteId;
        this.filmeId = filmeId;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
    }

    public Aluguel(int id, LocalDate dataAluguel){
        this.id = id;
        this.dataAluguel = dataAluguel;
    }

    public Aluguel(int id, LocalDate dataAluguel, LocalDate dataDevolucao){
        this.id = id;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getFilmeId() {
        return filmeId;
    }

    public void setFilmeId(int filmeId) {
        this.filmeId = filmeId;
    }

    public LocalDate getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(LocalDate dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
