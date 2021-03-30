package CONTROLLER;

import DAO.ClienteDAO;
import MODEL.Clientes;

public class ControllerCliente {
    public boolean salvar(String nome, String CPF, String data){
        Clientes cliente = new Clientes();
        cliente.setNome(nome);
        cliente.setCPF(CPF);
        cliente.setData(data);
        
        ClienteDAO b = new ClienteDAO();
        return b.salvar(cliente);        
    }
    
    public static boolean editar(String nome, String CPF, String data){
        Clientes cliente = new Clientes();
        cliente.setNome(nome);
        cliente.setCPF(CPF);
        cliente.setData(data);
        String aux="";
        
        ClienteDAO b = new ClienteDAO();
        return b.editar(cliente,aux);  
                
    }
    
    public static boolean excluir(){
        ClienteDAO b = new ClienteDAO();
        return b.excluir();
    }
    
}
