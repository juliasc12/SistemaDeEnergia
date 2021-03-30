
package DAO;

import CONEXAO.Conexao;
import VIEW.Principal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import MODEL.Funcionario;
import MODEL.Funcionario;
import java.sql.ResultSet;

public class FuncionarioDAO {
    private String sql;
    private Conexao conexao;
    ArrayList<Funcionario> listaFuncAuxDelet= new ArrayList<Funcionario>();


    private static FuncionarioDAO instance;

    public static FuncionarioDAO getInstance() {
        if (instance == null) {
            instance = new FuncionarioDAO();
        }
        return instance;
    }
    
     public static boolean salvar(Funcionario funcionario){
        PreparedStatement ps2;
        try {
            ps2 = Conexao.conexao().prepareStatement("Insert into funcionario (matricula_func, nome_func, salario_func, cod_Funcionario) values (?,?,?,?)");
            ps2.setInt(1, funcionario.getMatricula());
            ps2.setString(2, funcionario.getNome());
            ps2.setInt(3, funcionario.getSalario());
            ps2.setInt(4, funcionario.getDep().getCodigo());
                   
            ps2.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
     return true;
     }
     
    public static boolean editar(Funcionario funcionario, String aux){
        try {
            System.out.println("aux "+ funcionario.getNome());
            PreparedStatement ps = null;
            ps = Conexao.conexao().prepareStatement("Update funcionario set nome_func = ?, salario_func=?, cod_agencia=? WHERE funcionario.nome_func=?" );
                        ps.setString(1, funcionario.getNome());
                        ps.setString(2, funcionario.getNome());
                        ps.setInt(3, funcionario.getSalario());
                        ps.setInt(4, funcionario.getDep().getCodigo());
                        ps.setString(2, aux);
                        ps.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean excluir(){
        PreparedStatement ps = null;      
        ResultSet rs = null;
        
        try {
            ps = Conexao.conexao().prepareStatement("Select * from funcionario");          
            rs = ps.executeQuery();
           
            while (rs.next()) {
                 if (rs.getString("nome_func").equals(listaFuncAuxDelet.get(0).getNome())) {
                    ps = Conexao.conexao().prepareStatement("Delete from funcionario Where nome_func = ?");
                    ps.setString(1, listaFuncAuxDelet.get(0).getNome());
                    ps.executeUpdate();      
                }
            }        
        } catch (SQLException e) {
            System.out.println("Erro ao executar o comando SQL" + e);
        }
        
        return true;
    }
    
}
