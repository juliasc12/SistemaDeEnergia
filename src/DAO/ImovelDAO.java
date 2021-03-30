
package DAO;

import CONEXAO.Conexao;
import MODEL.Clientes;
import VIEW.Principal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import MODEL.Imovel;
import java.sql.ResultSet;

public class ImovelDAO {
    private String sql;
    private Conexao conexao;
    ArrayList<Imovel> listaImovelAuxDelet= new ArrayList<Imovel>();


    private static ImovelDAO instance;

    public static ImovelDAO getInstance() {
        if (instance == null) {
            instance = new ImovelDAO();
        }
        return instance;
    }
    
    public static boolean salvar(Imovel imovel){
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
        
        return true;
    }
    
    public static boolean editar(Imovel imovel, String aux){
        try{
            System.out.println("aux "+imovel.getEndereco());
            PreparedStatement ps = null;
            ps = Conexao.conexao().prepareStatement("Update imovel set nome_cliente=?, endereco_imovel= ?, unidade_consumidora=? WHERE endereco_imovel = ?" );
                    ps.setString(1, imovel.getCliente().getNome());
                    ps.setString(1, imovel.getEndereco());
                    ps.setString(3, imovel.getUC());
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
            ps = Conexao.conexao().prepareStatement("Select * from imovel");          
            rs = ps.executeQuery();
           
            while (rs.next()) {
                 if (rs.getString("endereco").equals(listaImovelAuxDelet.get(0).getEndereco())) {
                    ps = Conexao.conexao().prepareStatement("Delete from imovel Where endereco_imovel = ?");
                    ps.setString(1, listaImovelAuxDelet.get(0).getEndereco());
                    ps.executeUpdate();      
                }
            }        
        } catch (SQLException e) {
            System.out.println("Erro ao executar o comando SQL" + e);
        }
        
        return true;
    }
    
    
}
