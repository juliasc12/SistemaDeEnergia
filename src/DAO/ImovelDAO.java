
package DAO;

import tarefa4.Clientes;
import tarefa4.Principal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tarefa4.Imovel;

public class ImovelDAO {
    private String sql;
    private Conexao conexao;
    ArrayList<Imovel> listaAgAuxDelet= new ArrayList<Imovel>();


    private static ImovelDAO instance;

    public static ImovelDAO getInstance() {
        if (instance == null) {
            instance = new ImovelDAO();
        }
        return instance;
    }
    
    public static void salvar(Imovel imovel){
        PreparedStatement ps2;
        try {
            ps2 = Conexao.conexao().prepareStatement("Insert into imovel (cod_imovel, nome_cliente, endereco_imovel, unidade_consumidora) values (?,?,?,?)");
            ps2.setInt(1, imovel.getCodigo());
            ps2.setString(2, imovel.getCliente().getNome());
            ps2.setString(3, imovel.getEndereco());
            ps2.setString(4, imovel.getUC());
            
            ps2.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
