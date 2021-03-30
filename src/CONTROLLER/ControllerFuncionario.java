/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import DAO.FuncionarioDAO;
import MODEL.Funcionario;

public class ControllerFuncionario {
    
    public boolean salvar(String nome, int salario){
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setSalario(salario);
        
        FuncionarioDAO b = new FuncionarioDAO();
        return b.salvar(funcionario);        
    }
    
    public static boolean editar(String nome, int salario){
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setSalario(salario);
        String aux="";
        
        FuncionarioDAO b = new FuncionarioDAO();
        return b.editar(funcionario, aux);  
                
    }
    
    public static boolean excluir(){
        FuncionarioDAO b = new FuncionarioDAO();
        return b.excluir();
    }
}
