package com.example.root.provaremorzinho.POJO;

import java.io.Serializable;

/**
 * Created by root on 13/06/17.
 */

public class Condominio implements Serializable{
    private String nome;
    private String elevador;
    private String quantidadeAps;
    private String area;
    private String id;
    private int posicao;

    public Condominio() {

    }

    public Condominio(String nome, String elevador, String quantidadeAps, String area, int posicao) {
        this.nome = nome;
        this.elevador = elevador;
        this.quantidadeAps = quantidadeAps;
        this.area = area;
        this.posicao = posicao;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public String getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getElevador() {
        return elevador;
    }

    public void setElevador(String elevador) {
        this.elevador = elevador;
    }

    public String getQuantidadeAps() {
        return quantidadeAps;
    }

    public void setQuantidadeAps(String quantidadeAps) {
        this.quantidadeAps = quantidadeAps;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return this.nome + ", " + this.elevador + ", " + this.quantidadeAps + ", " + this.area;
    }
}
