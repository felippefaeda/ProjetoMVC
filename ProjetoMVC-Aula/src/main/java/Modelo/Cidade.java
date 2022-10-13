/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import DAO.CidadeDAO;

/**
 *
 * @author Desenvolvedor
 */
public class Cidade {
    private int codigo;
    private String nome;
    private String estado;
    private String cep;
    
    public Cidade(){
        this.codigo = -1;
        this.nome = "";
        this.estado = "";
        this.cep = "";
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    
    public void setProximoCodigo(){
        this.codigo = CidadeDAO.ProximoCodigo();
    }
    
    public void Salvar(){
        CidadeDAO.Salvar(this);
    }
    
    public void RecuperaObjeto(int Codigo){
        Cidade cidadeTemp = CidadeDAO.RecuperarCidade(Codigo);
        this.setCodigo(cidadeTemp.getCodigo());
        this.setNome(cidadeTemp.getNome());
        this.setCep(cidadeTemp.getCep());
        this.setEstado(cidadeTemp.getEstado());
    }
}
