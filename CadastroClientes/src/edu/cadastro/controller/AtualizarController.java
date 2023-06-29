package edu.cadastro.controller;

import edu.cadastro.dao.ClienteDAO;
import edu.cadastro.dao.Conexao;
import edu.cadastro.model.Cliente;
import edu.cadastro.view.AddDados;
import edu.cadastro.view.Atualizar;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class AtualizarController {
    
    private Atualizar view;

    public AtualizarController(Atualizar view) {
        this.view = view;
    }
    
    public String[] carregarCliente() throws SQLException{
        
        Connection conexao = null;
        String[] dadosCliente = null;
        
        try{
        conexao = new Conexao().getConnection();
        ClienteDAO clienteDao = new ClienteDAO(conexao);
        dadosCliente = clienteDao.selectUm();
        }catch(SQLException ex){
            Logger.getLogger(AddDados.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conexao != null){
               try{
                   conexao.close();
               }catch(SQLException ex){
                    Logger.getLogger(AddDados.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return dadosCliente;
    }
    
    public void atualizarCliente() throws SQLException{
        
        String nome = view.getTxtNome().getText();
        int idade = Integer.parseInt(view.getTxtIdade().getText());
        String cidade = view.getTxtCidade().getText();
        String bairro = view.getTxtBairro().getText();
        String endereco = view.getTxtEndereco().getText();
        String complemento = view.getTxtComplemento().getText();
        
        Cliente cliente = new Cliente(nome, idade, cidade, bairro, endereco, complemento);
        
        Connection conexao = null;
        
        try{
            conexao = new Conexao().getConnection();
            ClienteDAO clienteDao = new ClienteDAO(conexao);
            clienteDao.update(cliente);
            JOptionPane.showMessageDialog(null, "Cliente Atualizado com Sucesso!");
            
        }catch(SQLException ex){
            Logger.getLogger(AddDados.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conexao != null){
               try{
                  conexao.close();
               }catch(SQLException ex){
                   Logger.getLogger(AddDados.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
        }
    }
}
