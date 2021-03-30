package MODEL;

public class Conta {
    private int codigo;
    private int valor;
    private String data;
    private Clientes Cliente;
    
    public Conta(){
        
    }

    public Conta(int codigo, Clientes Cliente, String data, int valor) {
        this.codigo = codigo;
        this.valor = valor;
        this.data = data;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Clientes getCliente() {
        return Cliente;
    }

    public void setCliente(Clientes Cl) {
        this.Cliente = Cl;
    }
    
    
    
}
