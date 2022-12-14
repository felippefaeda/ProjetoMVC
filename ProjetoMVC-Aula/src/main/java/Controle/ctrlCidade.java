/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import Modelo.Cidade;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

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
    
    public ArrayList<String> ConverterObjetoParaArray(){
        ArrayList<String> vetCampos = new ArrayList<>();
        vetCampos.add(String.valueOf(this.objCidade.getCodigo()));
        vetCampos.add(this.objCidade.getNome());
        vetCampos.add(this.objCidade.getCep());
        vetCampos.add(this.objCidade.getEstado());
        
        return vetCampos;
    }
    
    public ArrayList<String> RecuperaObjeto(int Codigo){
        this.objCidade.RecuperaObjeto(Codigo);
        return ConverterObjetoParaArray();
    }
    
    public ArrayList<String> RecuperaObjetoNavegacao(int Opcao, int Codigo){
        this.objCidade.RecuperaObjetoNavegacao(Opcao, Codigo);
        return ConverterObjetoParaArray();
    }
    
    public void Atualizar(ArrayList<String> pLista){
        this.objCidade.setCodigo(Integer.valueOf(pLista.get(0)));
        this.objCidade.setNome(pLista.get(1));
        this.objCidade.setCep(pLista.get(2));
        this.objCidade.setEstado(pLista.get(3));
        
        this.objCidade.Atualizar();
    }
    
    public void Excluir(int Chave){
        this.objCidade.setCodigo(Chave);
        this.objCidade.Excluir(Chave);
    }
    
    public DefaultTableModel PesquisaObjeto(ArrayList<String> Parametros, DefaultTableModel ModeloTabela){
        String Campo = Parametros.get(0);
        String Valor = Parametros.get(1);
        
        ArrayList<Cidade> Cidades = this.objCidade.RecuperaObjetos(Campo, Valor);
        
        Vector<String> vetVetor;
        Cidade objCidadeBuffer;
        
        for (int i = 0; i < Cidades.size(); i++) {
            vetVetor = new Vector<>();
            objCidadeBuffer = Cidades.get(i);
            
            vetVetor.addElement(String.valueOf(objCidadeBuffer.getCodigo()));
            vetVetor.addElement(objCidadeBuffer.getNome());
            vetVetor.addElement(objCidadeBuffer.getEstado());
            vetVetor.addElement(objCidadeBuffer.getCep());
            ModeloTabela.addRow(vetVetor);
        }
        
        return ModeloTabela;
    }
}
