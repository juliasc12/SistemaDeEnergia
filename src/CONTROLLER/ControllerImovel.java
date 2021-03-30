
package CONTROLLER;

import DAO.ImovelDAO;
import MODEL.Imovel;

public class ControllerImovel {
    public boolean salvar(String endereco, String UC){
        Imovel imovel = new Imovel();
        imovel.setEndereco(endereco);
        imovel.setUC(UC);
        
        ImovelDAO b = new ImovelDAO();
        return b.salvar(imovel);        
    }
    
    public static boolean editar(String endereco, String UC){
        Imovel imovel = new Imovel();
        imovel.setEndereco(endereco);
        imovel.setUC(UC);
        String aux="";
        
        ImovelDAO b = new ImovelDAO();
        return b.editar(imovel,aux); 
                
    }
    
    public static boolean excluir(){
        ImovelDAO b = new ImovelDAO();
        return b.excluir();
    }
}
