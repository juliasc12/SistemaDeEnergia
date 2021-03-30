
package DAO;

import tarefa4.Principal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tarefa4.Conta;

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
    
     public static void salvar(Conta conta){
        PreparedStatement ps2;
        try {
            ps2 = Conexao.conexao().prepareStatement("Insert into conta (cod_conta, cod_cliente,vencimento_conta, valor_conta) values (?,?,?,?)");
            ps2.setInt(1, conta.getCodigo());
            ps2.setString(2, conta.getCliente().getNome());
            ps2.setString(3, conta.getData());
            ps2.setInt(4, conta.getValor());
                   
            ps2.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }
    
    
}
