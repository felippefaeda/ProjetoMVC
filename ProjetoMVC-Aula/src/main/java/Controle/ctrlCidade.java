/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import Modelo.Cidade;
import java.util.ArrayList;

/**
 *
 * @author Desenvolvedor
 */
public class ctrlCidade {
    private final Cidade objCidade;
    
    public ctrlCidade(){
        this.objCidade = new Cidade();
    }
    
    public int Salvar(ArrayList<String> pLista){
        this.objCidade.setNome(pLista.get(1));
        this.objCidade.setCep(pLista.get(2));
        this.objCidade.setEstado(pLista.get(3));
        this.objCidade.setProximoCodigo();
        this.objCidade.Salvar();
        return this.objCidade.getCodigo();
    }
}
