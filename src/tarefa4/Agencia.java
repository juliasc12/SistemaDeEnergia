package tarefa4;

import java.util.ArrayList;

public class Agencia {
    private int Codigo;
    private String Nome;
    ArrayList<Funcionario> ListaFunc;

    public Agencia() {
        ListaFunc = new ArrayList();
    }

    public Agencia(int Codigo, String Nome) {
        this.Codigo = Codigo;
        this.Nome = Nome;
        ListaFunc = new ArrayList();
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

    public ArrayList<Funcionario> getListaFunc() {
        return ListaFunc;
    }

    public void setListaFunc(ArrayList<Funcionario> ListaFunc) {
        this.ListaFunc = ListaFunc;
    }
    
    public void addFunc(Funcionario F){
        F.setDep(this);
        ListaFunc.add(F);
    }
}
