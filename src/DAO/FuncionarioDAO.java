
package DAO;

import tarefa4.Principal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tarefa4.Agencia;
import tarefa4.Funcionario;

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
    
     public static void salvar(Funcionario funcionario){
        PreparedStatement ps2;
        try {
            ps2 = Conexao.conexao().prepareStatement("Insert into funcionario (matricula_func, nome_func, salario_func, cod_agencia) values (?,?,?,?)");
            ps2.setInt(1, funcionario.getMatricula());
            ps2.setString(2, funcionario.getNome());
            ps2.setInt(3, funcionario.getSalario());
            ps2.setInt(4, funcionario.getDep().getCodigo());
                   
            ps2.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }
    
}
