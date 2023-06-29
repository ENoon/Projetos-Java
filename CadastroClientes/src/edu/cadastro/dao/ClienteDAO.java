
package edu.cadastro.dao;

import edu.cadastro.model.Cliente;
import edu.cadastro.view.AddDados;
import edu.cadastro.view.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ClienteDAO {
    
    private final Connection connection;
    
    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void insert(Cliente cliente) throws SQLException{
        
        String sql = "INSERT into cliente(nome, idade, cidade, bairro, endereco, complemento) values('"+cliente.getNome()+"','"+cliente.getIdade()+"','"+cliente.getCidade()+"','"+cliente.getBairro()+"','"+cliente.getEndereco()+"','"+cliente.getComplemento()+"') ; ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.execute();
        DefaultTableModel modelo = (DefaultTableModel) Menu.tbClientes.getModel();
        modelo.addRow(new Object[]{cliente.getNome(), cliente.getIdade(), cliente.getCidade()});
        
    }
    
    public void update(Cliente cliente) throws SQLException{
        String sql = "UPDATE cliente SET nome = ?, idade = ?, cidade = ?, bairro = ?, endereco = ?, complemento = ? WHERE nome = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        statement.setString(1, cliente.getNome());
        statement.setInt(2, cliente.getIdade());
        statement.setString(3, cliente.getCidade());
        statement.setString(4, cliente.getBairro());
        statement.setString(5, cliente.getEndereco());
        statement.setString(6, cliente.getComplemento());
        statement.setString(7, cliente.getNome());
        statement.execute();
       
    }
    
    public void delete() throws SQLException{
        int selectedRowIndex = Menu.tbClientes.getSelectedRow();
        if (selectedRowIndex == -1) {
            JOptionPane.showMessageDialog(null, "Nenhum registro selecionado", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String nome = (String) Menu.tbClientes.getValueAt(selectedRowIndex, 0);
        int idade = (int) Menu.tbClientes.getValueAt(selectedRowIndex, 1);
        String cidade = (String) Menu.tbClientes.getValueAt(selectedRowIndex, 2);
        
        
        String sql = "DELETE FROM cliente WHERE nome = ? AND idade = ? AND cidade = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        statement.setString(1, nome);
        statement.setInt(2, idade);
        statement.setString(3, cidade);
        statement.executeUpdate();
        
        DefaultTableModel model = (DefaultTableModel) Menu.tbClientes.getModel();
        model.removeRow(selectedRowIndex);
    }
    
    public ArrayList<Cliente> select() throws SQLException{
        
        DefaultTableModel modelo = (DefaultTableModel)Menu.tbClientes.getModel();
        modelo.setNumRows(0);
        
        Menu.tbClientes.getColumnModel().getColumn(0).setPreferredWidth(80);
        Menu.tbClientes.getColumnModel().getColumn(0).setPreferredWidth(20);
        Menu.tbClientes.getColumnModel().getColumn(0).setPreferredWidth(40);
        
        String sql = "SELECT nome, idade , cidade FROM cliente";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        ArrayList<Cliente> tabela = new ArrayList<>();
        
        
        ResultSet resultSet = statement.executeQuery();
       
        while(resultSet.next()){
            String nome = resultSet.getString("nome");
            int idade = resultSet.getInt("idade");
            String cidade = resultSet.getString("cidade");
            
            Cliente clienteDoBanco = new Cliente(nome, idade, cidade);
            tabela.add(clienteDoBanco);
            
            modelo.addRow(new Object[]{nome, idade, cidade});
        }
        return tabela;
    }
    
    public String[] selectUm() throws SQLException{
            
            int selectedRowIndex = Menu.tbClientes.getSelectedRow();
            if (selectedRowIndex == -1) {
                JOptionPane.showMessageDialog(null, "Nenhum registro selecionado", "Aviso", JOptionPane.WARNING_MESSAGE);
                return null;
            }
            String nome = (String) Menu.tbClientes.getValueAt(selectedRowIndex, 0);
            
        
            String sql = "SELECT nome, idade, cidade, bairro, endereco, complemento FROM cliente WHERE nome = ?";
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try{
                
                statement = connection.prepareStatement(sql);
                statement.setString(1, nome);
                resultSet = statement.executeQuery();
                
                if (resultSet.next()) {
                    String[] dadosCliente = new String[6]; 
                            
                    dadosCliente[0] = resultSet.getString("nome");
                    dadosCliente[1] = String.valueOf( resultSet.getInt("idade"));
                    dadosCliente[2] = resultSet.getString("cidade");
                    dadosCliente[3] = resultSet.getString("bairro");
                    dadosCliente[4] = resultSet.getString("endereco");
                    dadosCliente[5] = resultSet.getString("complemento");
                    return dadosCliente;
                }
            }catch(SQLException ex){
                    Logger.getLogger(AddDados.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
            }
            
            return null;
    }
}
