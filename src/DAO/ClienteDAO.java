package DAO;

import CONEXAO.Conexao;
import MODEL.Clientes;
import VIEW.Principal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO {
    private String sql;
    private Conexao conexao;
    ArrayList<Clientes>ListaClienteAuxDelet= new ArrayList<Clientes>();
    
    private static ClienteDAO instance;

    public static ClienteDAO getInstance() {
        if (instance == null) {
            instance = new ClienteDAO();
        }
        return instance;
    }
    
    public static boolean salvar(Clientes cliente){
        PreparedStatement ps2;
        try {
            ps2 = Conexao.conexao().prepareStatement("Insert into clientes (nome_cliente, cpf_cliente, nascimento_cliente) values (?,?,?)");
            ps2.setString(1, cliente.getNome());
            ps2.setString(2, cliente.getCPF());
            ps2.setString(3, cliente.getData());
            ps2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      return true;
    }
    
    public static boolean editar(Clientes cliente, String aux){
        try{
            System.out.println("aux "+cliente.getCPF());
            PreparedStatement ps = null;
            ps = Conexao.conexao().prepareStatement("Update clientes set nome_cliente=?, cpf_cliente= ?, nascimento_cliente=? WHERE cpf_cliente = ?" );
                    ps.setString(1, cliente.getNome());
                    ps.setString(1, cliente.getCPF());
                    ps.setString(3, cliente.getData());
                    ps.setString(4, aux);
                    ps.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(AgenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    return true;
    }
    
    public boolean excluir(){
        PreparedStatement ps = null;      
        ResultSet rs = null;
        
        try {
            ps = Conexao.conexao().prepareStatement("Select * from cliente");          
            rs = ps.executeQuery();
           
            while (rs.next()) {
                 if (rs.getString("nome_cliente").equals(ListaClienteAuxDelet.get(0).getNome())) {
                    ps = Conexao.conexao().prepareStatement("Delete from cliente Where nome_cliente = ?");
                    ps.setString(1, ListaClienteAuxDelet.get(0).getNome());
                    ps.executeUpdate();      
                }
            }        
        } catch (SQLException e) {
            System.out.println("Erro ao executar o comando SQL" + e);
        }
        
        return true;
    }
 
    
    
}
