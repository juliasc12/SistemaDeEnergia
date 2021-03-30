
package CONTROLLER;

import DAO.AgenciaDAO;
import MODEL.Agencia;

public class ControllerAgencia {
   
    public boolean salvar(String nome){
        Agencia agencia = new Agencia();
        agencia.setNome(nome);
        
        AgenciaDAO b = new AgenciaDAO();
        return b.salvar(agencia);        
    }
    
    public static boolean editar(String nome){
        Agencia agencia = new Agencia();
        agencia.setNome(nome);
        String aux="";
        
        AgenciaDAO b = new AgenciaDAO();
        return b.editar(agencia,aux); 
                
    }
    
    public static boolean excluir(){
        AgenciaDAO b = new AgenciaDAO();
        return b.excluir();
    }
    
    
}
