package edu.cadastro.controller;

import edu.cadastro.dao.ClienteDAO;
import edu.cadastro.dao.Conexao;
import edu.cadastro.view.AddDados;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExcluirController {
    
    public void excluirCliente() throws SQLException{
        
        Connection conexao = null;
        
        try{
            conexao = new Conexao().getConnection();
            ClienteDAO clienteDao = new ClienteDAO(conexao);
            clienteDao.delete();
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
