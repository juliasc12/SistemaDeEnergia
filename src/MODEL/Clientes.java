package MODEL;

import java.util.ArrayList;

public class Clientes {
    private int Codigo; 
    private String Nome;
    private String CPF;
    private String data;
    ArrayList<Imovel> ListaImovel;
    ArrayList<Conta> ListaConta;
    
    public Clientes(){
        ListaImovel = new ArrayList();
        ListaConta = new ArrayList();  
    }

    public Clientes(int Codigo, String Nome, String CPF, String data) {
        this.Codigo = Codigo;
        this.Nome = Nome;
        this.CPF = CPF;
        this.data = data;
        ListaImovel = new ArrayList();
        ListaConta = new ArrayList();
    }


    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ArrayList<Imovel> getListaCliente() {
        return ListaImovel;
    }

    public void setListaCliente(ArrayList<Imovel> ListaCliente) {
        this.ListaImovel = ListaImovel;
    }

    public void setListaImovel(ArrayList<Imovel> ListaImovel) {
        this.ListaImovel = ListaImovel;
    }

    public ArrayList<Conta> getListaConta() {
        return ListaConta;
    }

    public void setListaConta(ArrayList<Conta> ListaConta) {
        this.ListaConta = ListaConta;
    }
    
   
    public void addImovel(Imovel F){
        F.setCliente(this);
        ListaImovel.add(F);
    }
    
    public void addConta(Conta F){
        F.setCliente(this);
        ListaConta.add(F);
    }
    
   
}
