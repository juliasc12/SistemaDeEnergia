
package CONTROLLER;

import DAO.ContaDAO;
import MODEL.Clientes;
import MODEL.Conta;

public class ControllerConta {
     public boolean salvar(String data, int valor){
        Conta conta = new Conta();
        conta.setData(data);
        conta.setValor(valor);
        
        ContaDAO b = new ContaDAO();
        return b.salvar(conta);        
    }
    
    public static boolean editar(String data, int valor){
        Conta conta = new Conta();
        conta.setData(data);
        conta.setValor(valor);
        String aux="";
        
        ContaDAO b = new ContaDAO();
        return b.editar(conta, aux);  
                
    }
    
    public static boolean excluir(){
        ContaDAO b = new ContaDAO();
        return b.excluir();
    }
    
}
