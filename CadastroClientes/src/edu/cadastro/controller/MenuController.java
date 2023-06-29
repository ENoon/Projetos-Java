package edu.cadastro.controller;

import edu.cadastro.dao.ClienteDAO;
import edu.cadastro.dao.Conexao;
import java.sql.Connection;
import java.sql.SQLException;

public class MenuController {
    
    
    public void carregarTabela() throws SQLException{
        
        Connection conexao = new Conexao().getConnection();
        ClienteDAO clienteDao = new ClienteDAO(conexao);
        clienteDao.select();
        
    }
}
