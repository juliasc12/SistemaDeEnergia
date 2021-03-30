package DAO;

import tarefa4.Clientes;
import tarefa4.Principal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tarefa4.Agencia;

public class AgenciaDAO {
    private String sql;
    private Conexao conexao;
    ArrayList<Agencia> listaAgAuxDelet= new ArrayList<Agencia>();


    private static AgenciaDAO instance;

    public static AgenciaDAO getInstance() {
        if (instance == null) {
            instance = new AgenciaDAO();
        }
        return instance;
    }
    
    public static void salvar(Agencia agencia){
        PreparedStatement ps2;
        try {
            ps2 = Conexao.conexao().prepareStatement("Insert into agencia (nome_agencia) values (?)");
            ps2.setString(1, agencia.getNome());
            ps2.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }

     public static void editar(Agencia agencia, String aux) throws SQLException{

        System.out.println("aux "+ agencia.getNome());
        PreparedStatement ps = null;
        ps = Conexao.conexao().prepareStatement("Update agencia set nome_agencia = ? where nome_agencia=?" );
                    ps.setString(1, agencia.getNome());
                    ps.setString(2, aux);
                    ps.executeUpdate();
        
    }
    

}