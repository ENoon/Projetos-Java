package edu.cadastro.controller;

import edu.cadastro.dao.ClienteDAO;
import edu.cadastro.dao.Conexao;
import edu.cadastro.model.Cliente;
import edu.cadastro.view.AddDados;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class AddDadosController {
    
    private AddDados view;

    public AddDadosController(AddDados view) {
        this.view = view;
    }
    
    public void salvaCliente() throws SQLException{
        
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
            clienteDao.insert(cliente);
        
            JOptionPane.showMessageDialog(null, "Cliente Salvo com Sucesso!");
        
        }catch (SQLException ex){
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
