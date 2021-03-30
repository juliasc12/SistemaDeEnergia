package MODEL;

public class Funcionario {
    private int Matricula;
    private String Nome;
    private int salario;
    private Agencia Dep;

    public Funcionario() {
    }

    public Funcionario(int Matricula, String Nome, int Salario) {
        this.Matricula = Matricula;
        this.Nome = Nome;
        this.salario = Salario;
    }

    public int getMatricula() {
        return Matricula;
    }

    public void setMatricula(int Matricula) {
        this.Matricula = Matricula;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public Agencia getDep() {
        return Dep;
    }

    public void setDep(Agencia Dep) {
        this.Dep = Dep;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
        
    }
    
}
