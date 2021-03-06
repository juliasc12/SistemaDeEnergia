
package tarefa4;

public class Imovel {
    private int Codigo;
    private String Endereco;
    private Clientes Cliente;
    private String UC;
    
    public Imovel(){   
    }

    public Imovel(int Codigo, String Endereco, String UC) {
        this.Codigo = Codigo;
        this.Endereco = Endereco;
        this.UC = UC;
    }
    
    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    public Clientes getCliente() {
        return Cliente;
    }

    public void setCliente (Clientes Cl) {
        this.Cliente = Cl;
    }

    public String getUC() {
        return UC;
    }

    public void setUC(String UC) {
        this.UC = UC;
    }
    
    
    
    
}
