
package DAO;

import CONEXAO.Conexao;
import VIEW.Principal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import MODEL.Conta;
import java.sql.ResultSet;

public class ContaDAO {
     private String sql;
    private Conexao conexao;
    ArrayList<Conta> listaContaAuxDelet= new ArrayList<Conta>();
    
    private static ContaDAO instance;

    public static ContaDAO getInstance() {
        if (instance == null) {
            instance = new ContaDAO();
        }
        return instance;
    }
    
     public static boolean salvar(Conta conta){
        PreparedStatement ps2;
        try {
            ps2 = Conexao.conexao().prepareStatement("Insert into conta (cod_conta, cod_cliente,vencimento_conta, valor_conta) values (?,?,?,?)");
            ps2.setInt(1, conta.getCodigo());
            ps2.setString(2, conta.getCliente().getNome());
            ps2.setString(3, conta.getData());
            ps2.setInt(4, conta.getValor());
                   
            ps2.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;        
    }
    
     public static boolean editar(Conta conta, String aux){
        try {
            System.out.println("aux "+ conta.getData());
            PreparedStatement ps = null;
            ps = Conexao.conexao().prepareStatement("Update conta set cod_cliente = ?, vencimento_conta=?, valor_conta=? WHERE conta.vencimento_conta=?" );
                        ps.setString(1, conta.getCliente().getNome());
                        ps.setString(2, conta.getData());
                        ps.setInt(3, conta.getValor());
                        ps.setString(4, aux);
                        ps.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean excluir(){
        PreparedStatement ps = null;      
        ResultSet rs = null;
        
        try {
            ps = Conexao.conexao().prepareStatement("Select * from conta");          
            rs = ps.executeQuery();
           
            while (rs.next()) {
                 if (rs.getString("vencimento_conta").equals(listaContaAuxDelet.get(0).getData())) {
                    ps = Conexao.conexao().prepareStatement("Delete from conta Where vencimento_conta = ?");
                    ps.setString(1, listaContaAuxDelet.get(0).getData());
                    ps.executeUpdate();      
                }
            }        
        } catch (SQLException e) {
            System.out.println("Erro ao executar o comando SQL" + e);
        }
        
        return true;
    }
     
    
}
