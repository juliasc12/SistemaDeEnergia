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
import MODEL.Agencia;

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
    
    public static boolean salvar(Agencia agencia){
        PreparedStatement ps2;
        try {
            ps2 = Conexao.conexao().prepareStatement("Insert into agencia (nome_agencia) values (?)");
            ps2.setString(1, agencia.getNome());
            ps2.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public static boolean editar(Agencia agencia, String aux){
        try {
            System.out.println("aux "+ agencia.getNome());
            PreparedStatement ps = null;
            ps = Conexao.conexao().prepareStatement("Update agencia set nome_agencia = ? WHERE agencia.nome_agencia=?" );
                        ps.setString(1, agencia.getNome());
                        ps.setString(2, aux);
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
            ps = Conexao.conexao().prepareStatement("Select * from agencia");          
            rs = ps.executeQuery();
           
            while (rs.next()) {
                 if (rs.getString("nome_agencia").equals(listaAgAuxDelet.get(0).getNome())) {
                    ps = Conexao.conexao().prepareStatement("Delete from agencia Where nome_agencia = ?");
                    ps.setString(1, listaAgAuxDelet.get(0).getNome());
                    ps.executeUpdate();      
                }
            }        
        } catch (SQLException e) {
            System.out.println("Erro ao executar o comando SQL" + e);
        }
        
        return true;
    }
    

    



}