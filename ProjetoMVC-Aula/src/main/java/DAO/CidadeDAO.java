/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.Cidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Desenvolvedor
 */
public class CidadeDAO {
    
    public static final int cNavPrimeiro = 0;
    public static final int cNavAnterior = 1;
    public static final int cNavProximo = 2;
    public static final int cNavUltimo = 3;
    
    public static int PegaCodigoPelaNavegacao(int iOpcao, int icodigoAtual){
        Connection conexao = FabricaConexao.getConnection();
        
        Statement consulta = null;
        ResultSet resultado = null;
        int CodigoEncontrado = -1;
        
        String sql = "";
                
        switch(iOpcao){
            case cNavPrimeiro: 
                sql = "select min(CODIGO) as CODIGO from CIDADE"; 
                break;
            case cNavAnterior: 
                sql = "select max(CODIGO) as CODIGO from CIDADE where CODIGO < " 
                        + String.valueOf(icodigoAtual); 
                break;
            case cNavProximo: 
                sql = "select min(CODIGO) as CODIGO from CIDADE where CODIGO > " 
                        + String.valueOf(icodigoAtual); 
                break;
            case cNavUltimo: 
                sql = "select max(CODIGO) as CODIGO from CIDADE"; 
                break;
        }
        
        try {
            consulta = (Statement)conexao.createStatement();
            resultado = consulta.executeQuery(sql);
            resultado.next();
            CodigoEncontrado = resultado.getInt("CODIGO");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar SQL de navegação: " + e.getMessage());
        } finally {
            try {
               consulta.close();
               conexao.close(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão na função PegaCodigoPelaNavegacao(): " + e.getMessage());
            }  
        }
        
        return CodigoEncontrado;
    }
    
    public static int ProximoCodigo(){
        Connection conexao = FabricaConexao.getConnection();
        
        Statement consulta = null;
        ResultSet resultado = null;
        int codigo = -1;
        
        String sql = "select max(codigo) as CODIGO from CIDADE";
        
        try {
            consulta = (Statement)conexao.createStatement();
            resultado = consulta.executeQuery(sql);
            resultado.next();
            codigo = resultado.getInt("CODIGO");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar SQL de navegação: " + e.getMessage());
        } finally {
            try {
               consulta.close();
               conexao.close(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão na função ProximoCodigo(): " + e.getMessage());
            }            
        }      
        
        return codigo + 1;
    }
    
    public static void Salvar(Cidade cidade){
        Connection conexao = FabricaConexao.getConnection();
        
        PreparedStatement insereSt = null;
        
        String sql = "insert cidade (CODIGO, NOME, ESTADO, CEP) values (?,?,?,?)";
        
        try {            
            insereSt = conexao.prepareStatement(sql);
            insereSt.setInt(1, cidade.getCodigo());
            insereSt.setString(2, cidade.getNome());
            insereSt.setString(3, cidade.getEstado());
            insereSt.setString(4, cidade.getCep());
            insereSt.executeUpdate();            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir cidade: " + e.getMessage());
        } finally {
            try {
                JOptionPane.showMessageDialog(null, "Cidade incluída com sucesso.");
                insereSt.close();
                conexao.close(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão na função Salvar(): " + e.getMessage());
            }
        }            
    }
    
    public static Cidade RecuperarCidade(int iCod){
        Connection conexao = FabricaConexao.getConnection();
        
        Cidade cidadeRecuperada = new Cidade();
        Statement consulta = null;
        ResultSet resultado = null;
        
        String sql = "select * from cidade where codigo = " + iCod;
        
        try {
            consulta = conexao.createStatement();
            resultado = consulta.executeQuery(sql);
            
            resultado.next();
            
            if (resultado.getRow() == 1){
                
                cidadeRecuperada.setCodigo(resultado.getInt("CODIGO"));
                cidadeRecuperada.setNome(resultado.getString("NOME"));
                cidadeRecuperada.setEstado(resultado.getString("ESTADO"));
                cidadeRecuperada.setCep(resultado.getString("CEP"));
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao recuperar cidade: " + e.getMessage());
        } finally {
            try {
                consulta.close();
                resultado.close();
                conexao.close(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão na função RecuperarCidade(): " + e.getMessage());
            }
        }
        
        return cidadeRecuperada;
    }
    
    public static void Atualizar(Cidade cidade){
        Connection conexao = FabricaConexao.getConnection();
        
        PreparedStatement atualizaSt = null;
        
        String sql = "update cidade set nome = ?, estado = ?, cep = ? where codigo = ?";
        
        try {
            atualizaSt = conexao.prepareStatement(sql);
            atualizaSt.setString(1, cidade.getNome());
            atualizaSt.setString(2, cidade.getEstado());
            atualizaSt.setString(3, cidade.getCep());
            atualizaSt.setInt(4, cidade.getCodigo());
            atualizaSt.executeUpdate();            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cidade: " + e.getMessage());
        } finally {
            try {
                atualizaSt.close();
                conexao.close(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão na função Atualizar(): " + e.getMessage());
            }
        }
    }
        
    public static void Excluir(int iCod){
        Connection conexao = FabricaConexao.getConnection();
        
        PreparedStatement excluiSt = null;
        
        String sql = "delete from cidade where codigo = " + iCod;
        
        try {
            excluiSt = conexao.prepareStatement(sql);
            excluiSt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir cidade: " + e.getMessage());
        } finally {
            try {
                excluiSt.close();
                conexao.close(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão na função Excluir(): " + e.getMessage());
            }
        }
    }
    
    public static ArrayList<Cidade> RecuperaObjetos(String pCampo, String pValor){
        Connection conexao = FabricaConexao.getConnection();
        
        ArrayList<Cidade> cidades = new ArrayList<>();
        Statement consulta = null;
        ResultSet resultado = null;
        
        String sql = "select * from CIDADE where " + pCampo + " like '%" + pValor + "%'";      
        
        try {
            consulta = conexao.createStatement();
            resultado = consulta.executeQuery(sql);
            
            while(resultado.next()){
                Cidade cidadeTemp = new Cidade();
                cidadeTemp.setCodigo(resultado.getInt("CODIGO"));
                cidadeTemp.setNome(resultado.getString("NOME"));
                cidadeTemp.setEstado(resultado.getString("ESTADO"));
                cidadeTemp.setCep(resultado.getString("CEP"));
                cidades.add(cidadeTemp);
            }            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao recuperar cidades: " + e.getMessage() + "\n" + sql);
        } finally {
            try {
                consulta.close();
                resultado.close();
                conexao.close(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão na função RecuperaObjetos(): " + e.getMessage());
            }
        }
        
        return cidades;        
    }
}
