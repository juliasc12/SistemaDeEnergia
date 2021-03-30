package DAO;

import tarefa4.Clientes;
import tarefa4.Principal;
import java.sql.PreparedStatement;
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
    
    public static void  salvar(Clientes cliente){
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
      
    }
    
    public static void editar(Clientes cliente, String aux) throws SQLException{

        System.out.println("aux "+cliente.getCPF());
        PreparedStatement ps = null;
        ps = Conexao.conexao().prepareStatement("Update clientes set nome_cliente=?, cpf_cliente= ?, nascimento_cliente=? WHERE cpf_cliente = ?" );
                    ps.setString(1, cliente.getNome());
                    ps.setString(1, cliente.getCPF());
                    ps.setString(3, cliente.getData());
                    ps.setString(4, aux);
                    ps.executeUpdate();
        
    }
    
   
}
