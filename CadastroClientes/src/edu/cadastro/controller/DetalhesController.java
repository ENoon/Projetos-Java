package edu.cadastro.controller;

import edu.cadastro.dao.ClienteDAO;
import edu.cadastro.dao.Conexao;
import edu.cadastro.view.AddDados;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DetalhesController {

    
    public String[] detalhesCliente() throws SQLException{
        
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
}
