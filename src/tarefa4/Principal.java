package tarefa4;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import tarefa4.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Principal extends javax.swing.JFrame {
    ArrayList<Agencia> ListaDep;
    ArrayList<Clientes> ListaCliente;
    ArrayList<Funcionario> ListaFunc;
    ArrayList<Imovel> ListaImovel;
    ArrayList<Conta> ListaConta;
    
    String modoDep;
    String modoFunc;
    String modoCliente;
    String modoImovel;
    String modoConta;
    Conexao conn;
    
    
    public void LoadTableDep(){
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Código","Nome"},0);
            
        for(int i=0;i<ListaDep.size();i++){
            Object linha[] = new Object[]{ListaDep.get(i).getCodigo(),
                                          ListaDep.get(i).getNome()};
            modelo.addRow(linha);
        }
        
        tbl_dep_dpts.setModel(modelo);
        tbl_dep_dpts.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbl_dep_dpts.getColumnModel().getColumn(1).setPreferredWidth(200);
        
        LoadCBDep();
    }
    
    public void LoadTableFunc(){
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Matrícula","Nome","Salario", "Agencia"},0);
            
        for(int i=0;i<ListaFunc.size();i++){
            Object linha[] = new Object[]{ListaFunc.get(i).getMatricula(),
                                          ListaFunc.get(i).getNome(),
                                          ListaFunc.get(i).getSalario(),
                                          ListaFunc.get(i).getDep().getNome()};
            modelo.addRow(linha);
        }
        
        tbl_func.setModel(modelo);
        tbl_func.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbl_func.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbl_func.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbl_func.getColumnModel().getColumn(2).setPreferredWidth(150);
    }
    
    public void LoadTableCliente(){
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Codigo","Nome","CPF", "Nascimento"},0);
            
        for(int i=0;i<ListaCliente.size();i++){
            Object linha[] = new Object[]{ListaCliente.get(i).getCodigo(),
                                          ListaCliente.get(i).getNome(),
                                          ListaCliente.get(i).getCPF(),
                                          ListaCliente.get(i).getData()};
            modelo.addRow(linha);
        }
        
        tbl_clientes.setModel(modelo);
        tbl_clientes.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbl_clientes.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbl_clientes.getColumnModel().getColumn(2).setPreferredWidth(150);
        tbl_clientes.getColumnModel().getColumn(2).setPreferredWidth(150);
        
        LoadCBCliente();
    }
    
    public void LoadTableImovel(){
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Codigo","Cliente" , "Endereco","Unidade Consumidora"},0);
            
        for(int i=0;i<ListaImovel.size();i++){
            Object linha[] = new Object[]{ListaImovel.get(i).getCodigo(),
                                          ListaImovel.get(i).getCliente().getNome(),
                                          ListaImovel.get(i).getEndereco(),
                                          ListaImovel.get(i).getUC()};
            modelo.addRow(linha);
        }
        
        tbl_imovel.setModel(modelo);
        tbl_imovel.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbl_imovel.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbl_imovel.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbl_imovel.getColumnModel().getColumn(1).setPreferredWidth(150);
    }
    
    public void LoadTableConta(){
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Codigo","Cliente" , "Vencimento","Valor"},0);
            
        for(int i=0;i<ListaConta.size();i++){
            Object linha[] = new Object[]{ListaConta.get(i).getCodigo(),
                                          ListaConta.get(i).getCliente().getNome(),
                                          ListaConta.get(i).getData(),
                                          ListaConta.get(i).getValor()};
            modelo.addRow(linha);
        }
        
        tbl_conta.setModel(modelo);
        tbl_conta.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbl_conta.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbl_conta.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbl_conta.getColumnModel().getColumn(1).setPreferredWidth(150);
    }
    
    public void LoadCBDep(){
        cb_func_deps.removeAllItems();
        cb_func_deps.addItem("Selecione");
        for(int i=0;i<ListaDep.size();i++){
            cb_func_deps.addItem(ListaDep.get(i).getNome());
        }
    }
    
    public void LoadCBCliente(){
        c_i_cliente.removeAllItems();
        c_i_cliente.addItem("Selecione");
        
        c_c_cliente.removeAllItems();
        c_c_cliente.addItem("Selecione");
        
        for(int i=0;i<ListaCliente.size();i++){
            c_i_cliente.addItem(ListaCliente.get(i).getNome());
            c_c_cliente.addItem(ListaCliente.get(i).getNome());
        }
    }
    
    public Principal() {
        initComponents();
        setLocationRelativeTo(null);
        
        ListaDep = new ArrayList();
        ListaCliente = new ArrayList();
        ListaFunc = new ArrayList();
        //ListaCliente = new ArrayList();
        ListaImovel = new ArrayList();
        ListaConta = new ArrayList();
        
        modoDep = "Navegar";
        modoFunc = "Navegar";
        modoCliente = "Navegar";
        modoImovel = "Navegar";
        modoConta = "Navegar";
        
        ManipulaInterfaceDep();
        ManipulaInterfaceFunc();
        ManipulaInterfaceCliente();
        ManipulaInterfaceImovel();
        ManipulaInterfaceConta();
    }
    
    public void ManipulaInterfaceConta(){
        switch(modoConta){
            case "Navegar":
                btn_i_salvar.setEnabled(false);
                btn_i_cancelar.setEnabled(false);
                c_c_cod.setEnabled(false);
                c_c_cliente.setEnabled(false);
                c_c_venc.setEnabled(false);
                c_c_valor.setEnabled(false);
                btn_i_novo.setEnabled(true);
                btn_i_editar.setEnabled(false);
                btn_i_excluir.setEnabled(false);        
                break;
                
            case "Novo":
                btn_i_salvar.setEnabled(true);
                btn_i_cancelar.setEnabled(true);
                c_c_cod.setEnabled(true);
                c_c_cliente.setEnabled(true);
                c_c_venc.setEnabled(true);
                c_c_valor.setEnabled(true);
                btn_i_novo.setEnabled(true);
                btn_i_editar.setEnabled(false);
                btn_i_excluir.setEnabled(false);        
                break;
        
            case "Editar":
                btn_i_salvar.setEnabled(true);
                btn_i_cancelar.setEnabled(true);
                c_c_cod.setEnabled(true);
                c_c_cliente.setEnabled(true);
                c_c_venc.setEnabled(true);
                c_c_valor.setEnabled(true);
                btn_i_novo.setEnabled(true);
                btn_i_editar.setEnabled(false);
                btn_i_excluir.setEnabled(false);        
                break;
            
            case "Excluir":
                btn_i_salvar.setEnabled(false);
                btn_i_cancelar.setEnabled(false);
                c_c_cod.setEnabled(false);
                c_c_cliente.setEnabled(false);
                c_c_venc.setEnabled(false);
                c_c_valor.setEnabled(false);
                btn_i_novo.setEnabled(true);
                btn_i_editar.setEnabled(false);
                btn_i_excluir.setEnabled(false);        
                break;
                
            case "Selecao":
                btn_i_salvar.setEnabled(false);
                btn_i_cancelar.setEnabled(false);
                c_c_cod.setEnabled(false);
                c_c_cliente.setEnabled(false);
                c_c_venc.setEnabled(false);
                c_c_valor.setEnabled(false);
                btn_i_novo.setEnabled(true);
                btn_i_editar.setEnabled(true);
                btn_i_excluir.setEnabled(true);        
                break;
                
        }
        
    }
    public void ManipulaInterfaceImovel(){
        switch(modoImovel){   
            case "Navegar":
                btn_i_salvar.setEnabled(false);
                btn_i_cancelar.setEnabled(false);
                c_i_cod.setEnabled(false);
                c_i_cliente.setEnabled(false);
                c_i_end.setEnabled(false);
                c_i_uc.setEnabled(false);
                btn_i_novo.setEnabled(true);
                btn_i_editar.setEnabled(false);
                btn_i_excluir.setEnabled(false);        
                break;
                
            case "Novo":
                btn_i_salvar.setEnabled(true);
                btn_i_cancelar.setEnabled(true);
                c_i_cod.setEnabled(true);
                c_i_cliente.setEnabled(true);
                c_i_end.setEnabled(true);
                c_i_uc.setEnabled(true);
                btn_i_novo.setEnabled(true);
                btn_i_editar.setEnabled(false);
                btn_i_excluir.setEnabled(false);        
                break;
                
            case "Editar":
                btn_i_salvar.setEnabled(true);
                btn_i_cancelar.setEnabled(true);
                c_i_cod.setEnabled(true);
                c_i_cliente.setEnabled(true);
                c_i_end.setEnabled(true);
                c_i_uc.setEnabled(true);
                btn_i_novo.setEnabled(true);
                btn_i_editar.setEnabled(false);
                btn_i_excluir.setEnabled(false);        
                break;
                
            case "Excluir":
                btn_i_salvar.setEnabled(false);
                btn_i_cancelar.setEnabled(false);
                c_i_cod.setEnabled(false);
                c_i_cliente.setEnabled(false);
                c_i_end.setEnabled(false);
                 c_i_uc.setEnabled(false);
                btn_i_novo.setEnabled(true);
                btn_i_editar.setEnabled(false);
                btn_i_excluir.setEnabled(false);        
                break;
                
            case "Selecao":
                btn_i_salvar.setEnabled(false);
                btn_i_cancelar.setEnabled(false);
                c_i_cod.setEnabled(false);
                 c_i_cliente.setEnabled(false);
                c_i_end.setEnabled(false);
                c_i_uc.setEnabled(false);
                btn_i_novo.setEnabled(true);
                btn_i_editar.setEnabled(true);
                btn_i_excluir.setEnabled(true);        
                break;
    
        }
    }
    
    public void ManipulaInterfaceCliente(){
        switch(modoCliente){   
            case "Navegar":
                btn_cl_salvar.setEnabled(false);
                btn_cl_cancelar.setEnabled(false);
                c_cl_cod.setEnabled(false);
                c_cl_nome.setEnabled(false);
                c_cl_cpf.setEnabled(false);
                c_cl_data.setEnabled(false);
                btn_cl_novo.setEnabled(true);
                btn_cl_editar.setEnabled(false);
                btn_cl_excluir.setEnabled(false);        
                break;
                
            case "Novo":
                btn_cl_salvar.setEnabled(true);
                btn_cl_cancelar.setEnabled(true);
                c_cl_cod.setEnabled(true);
                c_cl_nome.setEnabled(true);
                c_cl_cpf.setEnabled(true);
                c_cl_data.setEnabled(true);
                btn_cl_novo.setEnabled(false);
                btn_cl_editar.setEnabled(false);
                btn_cl_excluir.setEnabled(false);        
                break;
                
            case "Editar":
                btn_cl_salvar.setEnabled(true);
                btn_cl_cancelar.setEnabled(true);
                c_cl_cod.setEnabled(true);
                c_cl_nome.setEnabled(true);
                c_cl_cpf.setEnabled(true);
                c_cl_data.setEnabled(true);
                btn_cl_novo.setEnabled(true);
                btn_cl_editar.setEnabled(false);
                btn_cl_excluir.setEnabled(false);        
                break;
                
            case "Excluir":
               btn_cl_salvar.setEnabled(false);
                btn_cl_cancelar.setEnabled(false);
                c_cl_cod.setEnabled(false);
                c_cl_nome.setEnabled(false);
                c_cl_cpf.setEnabled(false);
                c_cl_data.setEnabled(false);
                btn_cl_novo.setEnabled(true);
                btn_cl_editar.setEnabled(false);
                btn_cl_excluir.setEnabled(false);
                break;
                
            case "Selecao":
                btn_cl_salvar.setEnabled(false);
                btn_cl_cancelar.setEnabled(false);
                c_cl_cod.setEnabled(false);
                c_cl_nome.setEnabled(false);
                c_cl_cpf.setEnabled(false);
                c_cl_data.setEnabled(false);
                btn_cl_novo.setEnabled(true);
                btn_cl_editar.setEnabled(true);
                btn_cl_excluir.setEnabled(true);
                break;
            default: System.out.println("Modo inválido");
        }
    }
    
    public void ManipulaInterfaceDep(){
        switch(modoDep){
            case "Navegar":
                btn_dep_salvar.setEnabled(false);
                btn_dep_cancelar.setEnabled(false);
                c_dep_codigo.setEnabled(false);
                c_dep_nome.setEnabled(false);
                btn_dep_novo.setEnabled(true);
                btn_dep_editar.setEnabled(false);
                btn_dep_excluir.setEnabled(false);
                break;
            
            case "Novo":
                btn_dep_salvar.setEnabled(true);
                btn_dep_cancelar.setEnabled(true);
                c_dep_codigo.setEnabled(true);
                c_dep_nome.setEnabled(true);
                btn_dep_novo.setEnabled(false);
                btn_dep_editar.setEnabled(false);
                btn_dep_excluir.setEnabled(false);
                break;
                
            case "Editar":
                btn_dep_salvar.setEnabled(true);
                btn_dep_cancelar.setEnabled(true);
                c_dep_codigo.setEnabled(true);
                c_dep_nome.setEnabled(true);
                btn_dep_novo.setEnabled(true);
                btn_dep_editar.setEnabled(false);
                btn_dep_excluir.setEnabled(false);
                break;
                
            case "Excluir":
                btn_dep_salvar.setEnabled(false);
                btn_dep_cancelar.setEnabled(false);
                c_dep_codigo.setEnabled(false);
                c_dep_nome.setEnabled(false);
                btn_dep_novo.setEnabled(true);
                btn_dep_editar.setEnabled(false);
                btn_dep_excluir.setEnabled(false);
                break;
                
            case "Selecao":
                btn_dep_salvar.setEnabled(false);
                btn_dep_cancelar.setEnabled(false);
                c_dep_codigo.setEnabled(false);
                c_dep_nome.setEnabled(false);
                btn_dep_novo.setEnabled(true);
                btn_dep_editar.setEnabled(true);
                btn_dep_excluir.setEnabled(true);
                break;
            default: System.out.println("Modo inválido");
        }
    }
    
    
    public void ManipulaInterfaceFunc(){
        switch(modoFunc){
            case "Navegar":
                btn_func_salvar.setEnabled(false);
                btn_func_cancelar.setEnabled(false);
                c_func_mat.setEnabled(false);
                c_func_nome.setEnabled(false);
                c_func_salario.setEnabled(false);
                btn_func_novo.setEnabled(true);
                btn_func_editar.setEnabled(false);
                btn_func_excluir.setEnabled(false);
                cb_func_deps.setEnabled(false);
                break;
            
            case "Novo":
                btn_func_salvar.setEnabled(true);
                btn_func_cancelar.setEnabled(true);
                c_func_mat.setEnabled(true);
                c_func_nome.setEnabled(true);
                 c_func_salario.setEnabled(true);
                cb_func_deps.setEnabled(true);
                btn_func_novo.setEnabled(false);
                btn_func_editar.setEnabled(false);
                btn_func_excluir.setEnabled(false);
                break;
                
            case "Editar":
                btn_func_salvar.setEnabled(true);
                btn_func_cancelar.setEnabled(true);
                c_func_mat.setEnabled(true);
                c_func_nome.setEnabled(true);
                c_func_salario.setEnabled(true);
                cb_func_deps.setEnabled(true);
                btn_func_novo.setEnabled(true);
                btn_func_editar.setEnabled(false);
                btn_func_excluir.setEnabled(false);
                break;
                
            case "Excluir":
                btn_func_salvar.setEnabled(false);
                btn_func_cancelar.setEnabled(false);
                c_func_mat.setEnabled(false);
                c_func_nome.setEnabled(false);
                c_func_salario.setEnabled(false);
                cb_func_deps.setEnabled(false);
                btn_func_novo.setEnabled(true);
                btn_func_editar.setEnabled(false);
                btn_func_excluir.setEnabled(false);
                break;
                
            case "Selecao":
                btn_func_salvar.setEnabled(false);
                btn_func_cancelar.setEnabled(false);
                c_func_mat.setEnabled(false);
                c_func_nome.setEnabled(false);
                c_func_salario.setEnabled(false);
                cb_func_deps.setEnabled(false);
                btn_func_novo.setEnabled(true);
                btn_func_editar.setEnabled(true);
                btn_func_excluir.setEnabled(true);
                break;
            default: System.out.println("Modo inválido");
        }
    }
    public boolean verificarCPF(String cpf){
        int dig1=0, dig2=0, calc1=0, calc2=0, aux1=10, aux2=11;
        int [] arrayCPF;
        boolean repetido = true;
        arrayCPF = new int[9];
        dig1 = Integer.parseInt(cpf.substring(12,13));
        dig2 = Integer.parseInt(cpf.substring(13,14));
        cpf = cpf.substring(0,3) + cpf.substring(4,7) + cpf.substring(8,11);
        for(int i=0; i<arrayCPF.length; i++){
            arrayCPF[i] = Integer.parseInt(cpf.substring(i, i+1));
            
            calc1 += aux1 * arrayCPF[i];
            aux1--;
            calc2 += aux2 * arrayCPF[i];
            aux2--;
            
            if(arrayCPF[0] != arrayCPF[i] && repetido)
                repetido = false;
        }
        calc2 += dig1 * aux2;
        
        calc1 = (calc1 * 10) % 11;
        calc2 = (calc2 * 10) % 11;
        
        if(calc1 == 10)
            calc1 = 0;
        
        if(calc2 == 10)
            calc2 = 0;
       
                
        if(calc1 == dig1 && calc2 == dig2 && !repetido)
            return true;
        else
            return false;
    }//fim função verifica CPF
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_dep_dpts = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        c_dep_codigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        c_dep_nome = new javax.swing.JTextField();
        btn_dep_salvar = new javax.swing.JButton();
        btn_dep_cancelar = new javax.swing.JButton();
        btn_dep_novo = new javax.swing.JButton();
        btn_dep_editar = new javax.swing.JButton();
        btn_dep_excluir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btn_func_editar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_func = new javax.swing.JTable();
        btn_func_excluir = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        c_func_mat = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        c_func_nome = new javax.swing.JTextField();
        btn_func_salvar = new javax.swing.JButton();
        btn_func_cancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cb_func_deps = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        c_func_salario = new javax.swing.JTextField();
        btn_func_novo = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_clientes = new javax.swing.JTable();
        btn_cl_novo = new javax.swing.JButton();
        btn_cl_editar = new javax.swing.JButton();
        btn_cl_excluir = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        c_cl_cod = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        c_cl_nome = new javax.swing.JTextField();
        btn_cl_salvar = new javax.swing.JButton();
        btn_cl_cancelar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        c_cl_data = new javax.swing.JFormattedTextField();
        c_cl_cpf = new javax.swing.JFormattedTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_imovel = new javax.swing.JTable();
        btn_i_novo = new javax.swing.JButton();
        btn_i_editar = new javax.swing.JButton();
        btn_i_excluir = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        c_i_cod = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        c_i_end = new javax.swing.JTextField();
        btn_i_salvar = new javax.swing.JButton();
        btn_i_cancelar = new javax.swing.JButton();
        c_i_uc = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        c_i_cliente = new javax.swing.JComboBox();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_conta = new javax.swing.JTable();
        btn_c_novo = new javax.swing.JButton();
        btn_c_editar = new javax.swing.JButton();
        btn_c_excluir = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        c_c_cod = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        btn_c_salvar = new javax.swing.JButton();
        btn_c_cancelar = new javax.swing.JButton();
        c_c_valor = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        c_c_cliente = new javax.swing.JComboBox();
        c_c_venc = new javax.swing.JFormattedTextField();

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbl_dep_dpts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_dep_dpts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_dep_dptsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_dep_dpts);
        if (tbl_dep_dpts.getColumnModel().getColumnCount() > 0) {
            tbl_dep_dpts.getColumnModel().getColumn(0).setResizable(false);
            tbl_dep_dpts.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbl_dep_dpts.getColumnModel().getColumn(1).setPreferredWidth(150);
        }

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Agência"));

        jLabel1.setText("Código:");

        jLabel2.setText("Nome:");

        c_dep_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_dep_nomeActionPerformed(evt);
            }
        });

        btn_dep_salvar.setText("Salvar");
        btn_dep_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dep_salvarActionPerformed(evt);
            }
        });

        btn_dep_cancelar.setText("Cancelar");
        btn_dep_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dep_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(c_dep_nome)
                    .addComponent(c_dep_codigo))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(btn_dep_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_dep_cancelar)
                .addContainerGap(212, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(c_dep_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(c_dep_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_dep_salvar)
                    .addComponent(btn_dep_cancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_dep_novo.setText("Novo");
        btn_dep_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dep_novoActionPerformed(evt);
            }
        });

        btn_dep_editar.setText("Editar");
        btn_dep_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dep_editarActionPerformed(evt);
            }
        });

        btn_dep_excluir.setText("Excluir");
        btn_dep_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dep_excluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_dep_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_dep_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_dep_excluir, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_dep_novo)
                    .addComponent(btn_dep_editar)
                    .addComponent(btn_dep_excluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.getAccessibleContext().setAccessibleDescription("");

        jTabbedPane1.addTab("Agência", jPanel1);

        btn_func_editar.setText("Editar");
        btn_func_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_func_editarActionPerformed(evt);
            }
        });

        tbl_func.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matrícula", "Nome", "Salario", "Agencia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_func.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_funcMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_func);
        if (tbl_func.getColumnModel().getColumnCount() > 0) {
            tbl_func.getColumnModel().getColumn(0).setResizable(false);
            tbl_func.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbl_func.getColumnModel().getColumn(1).setResizable(false);
            tbl_func.getColumnModel().getColumn(1).setPreferredWidth(150);
            tbl_func.getColumnModel().getColumn(2).setResizable(false);
            tbl_func.getColumnModel().getColumn(3).setResizable(false);
            tbl_func.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        btn_func_excluir.setText("Excluir");
        btn_func_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_func_excluirActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Funcionário"));

        jLabel3.setText("Matrícula:");

        jLabel4.setText("Nome:");

        btn_func_salvar.setText("Salvar");
        btn_func_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_func_salvarActionPerformed(evt);
            }
        });

        btn_func_cancelar.setText("Cancelar");
        btn_func_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_func_cancelarActionPerformed(evt);
            }
        });

        jLabel5.setText("Agência:");

        cb_func_deps.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cb_func_depsMousePressed(evt);
            }
        });
        cb_func_deps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_func_depsActionPerformed(evt);
            }
        });

        jLabel12.setText("Salário:");

        c_func_salario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_func_salarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(btn_func_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_func_cancelar)
                .addContainerGap(212, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(c_func_nome)
                    .addComponent(cb_func_deps, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(c_func_salario)
                    .addComponent(c_func_mat))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(c_func_mat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(c_func_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cb_func_deps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(c_func_salario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_func_salvar)
                    .addComponent(btn_func_cancelar))
                .addContainerGap())
        );

        btn_func_novo.setText("Novo");
        btn_func_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_func_novoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_func_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_func_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_func_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 130, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_func_novo)
                    .addComponent(btn_func_editar)
                    .addComponent(btn_func_excluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Funcionários", jPanel2);

        tbl_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Conta", "Nome", "CPF", "Nascimento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_clientesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_clientes);
        if (tbl_clientes.getColumnModel().getColumnCount() > 0) {
            tbl_clientes.getColumnModel().getColumn(0).setResizable(false);
            tbl_clientes.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbl_clientes.getColumnModel().getColumn(1).setResizable(false);
            tbl_clientes.getColumnModel().getColumn(1).setPreferredWidth(150);
            tbl_clientes.getColumnModel().getColumn(2).setResizable(false);
            tbl_clientes.getColumnModel().getColumn(2).setPreferredWidth(100);
            tbl_clientes.getColumnModel().getColumn(3).setResizable(false);
        }

        btn_cl_novo.setText("Novo");
        btn_cl_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cl_novoActionPerformed(evt);
            }
        });

        btn_cl_editar.setText("Editar");
        btn_cl_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cl_editarActionPerformed(evt);
            }
        });

        btn_cl_excluir.setText("Excluir");
        btn_cl_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cl_excluirActionPerformed(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Clientes"));

        jLabel6.setText("Código:");

        jLabel7.setText("Nome:");

        btn_cl_salvar.setText("Salvar");
        btn_cl_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cl_salvarActionPerformed(evt);
            }
        });

        btn_cl_cancelar.setText("Cancelar");
        btn_cl_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cl_cancelarActionPerformed(evt);
            }
        });

        jLabel8.setText("CPF:");

        jLabel11.setText("Nascimento:");

        try {
            c_cl_data.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        c_cl_data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_cl_dataActionPerformed(evt);
            }
        });

        try {
            c_cl_cpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btn_cl_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cl_cancelar))
                    .addComponent(c_cl_cpf, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                    .addComponent(c_cl_data)
                    .addComponent(c_cl_nome)
                    .addComponent(c_cl_cod))
                .addGap(0, 35, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(c_cl_cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(c_cl_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(c_cl_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(c_cl_data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cl_salvar)
                    .addComponent(btn_cl_cancelar)))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(btn_cl_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cl_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cl_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cl_novo)
                    .addComponent(btn_cl_editar)
                    .addComponent(btn_cl_excluir))
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Clientes", jPanel6);

        tbl_imovel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Cliente", "Bairro", "Unidade Consumidora"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_imovel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_imovelMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_imovel);
        if (tbl_imovel.getColumnModel().getColumnCount() > 0) {
            tbl_imovel.getColumnModel().getColumn(0).setResizable(false);
            tbl_imovel.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbl_imovel.getColumnModel().getColumn(1).setResizable(false);
            tbl_imovel.getColumnModel().getColumn(2).setResizable(false);
            tbl_imovel.getColumnModel().getColumn(2).setPreferredWidth(150);
            tbl_imovel.getColumnModel().getColumn(3).setResizable(false);
        }

        btn_i_novo.setText("Novo");
        btn_i_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_i_novoActionPerformed(evt);
            }
        });

        btn_i_editar.setText("Editar");
        btn_i_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_i_editarActionPerformed(evt);
            }
        });

        btn_i_excluir.setText("Excluir");
        btn_i_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_i_excluirActionPerformed(evt);
            }
        });

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Clientes"));

        jLabel9.setText("Código:");

        jLabel10.setText("Endereço:");

        btn_i_salvar.setText("Salvar");
        btn_i_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_i_salvarActionPerformed(evt);
            }
        });

        btn_i_cancelar.setText("Cancelar");
        btn_i_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_i_cancelarActionPerformed(evt);
            }
        });

        c_i_uc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_i_ucActionPerformed(evt);
            }
        });

        jLabel13.setText("Unidade Consumidora:");

        jLabel14.setText("Cliente:");

        c_i_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_i_clienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(c_i_cliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(c_i_cod)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(btn_i_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(btn_i_cancelar)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(c_i_uc)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c_i_end)))
                .addGap(23, 23, 23))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(c_i_cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(c_i_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(c_i_end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c_i_uc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_i_salvar)
                    .addComponent(btn_i_cancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(btn_i_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_i_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_i_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_i_novo)
                    .addComponent(btn_i_editar)
                    .addComponent(btn_i_excluir))
                .addGap(26, 26, 26)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Imóveis", jPanel7);

        tbl_conta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Cliente", "Vencimento", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_conta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_contaMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_conta);
        if (tbl_conta.getColumnModel().getColumnCount() > 0) {
            tbl_conta.getColumnModel().getColumn(0).setResizable(false);
            tbl_conta.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbl_conta.getColumnModel().getColumn(1).setResizable(false);
            tbl_conta.getColumnModel().getColumn(2).setResizable(false);
            tbl_conta.getColumnModel().getColumn(2).setPreferredWidth(150);
            tbl_conta.getColumnModel().getColumn(3).setResizable(false);
        }

        btn_c_novo.setText("Novo");
        btn_c_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_c_novoActionPerformed(evt);
            }
        });

        btn_c_editar.setText("Editar");
        btn_c_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_c_editarActionPerformed(evt);
            }
        });

        btn_c_excluir.setText("Excluir");
        btn_c_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_c_excluirActionPerformed(evt);
            }
        });

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Clientes"));

        jLabel15.setText("Código:");

        jLabel16.setText("Vencimento:");

        btn_c_salvar.setText("Salvar");
        btn_c_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_c_salvarActionPerformed(evt);
            }
        });

        btn_c_cancelar.setText("Cancelar");
        btn_c_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_c_cancelarActionPerformed(evt);
            }
        });

        c_c_valor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_c_valorActionPerformed(evt);
            }
        });

        jLabel17.setText("Valor:");

        jLabel18.setText("Cliente:");

        c_c_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_c_clienteActionPerformed(evt);
            }
        });

        try {
            c_c_venc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        c_c_venc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_c_vencActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(c_c_venc)
                            .addComponent(c_c_valor)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(c_c_cliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(c_c_cod)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_c_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_c_cancelar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(23, 23, 23))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(c_c_cod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(c_c_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(c_c_venc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(c_c_valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_c_salvar)
                    .addComponent(btn_c_cancelar))
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btn_c_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_c_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_c_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_c_novo)
                    .addComponent(btn_c_editar)
                    .addComponent(btn_c_excluir))
                .addGap(28, 28, 28)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Contas", jPanel10);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_contaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_contaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_contaMouseClicked

    private void c_i_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_i_clienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_i_clienteActionPerformed

    private void c_i_ucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_i_ucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_i_ucActionPerformed

    private void btn_i_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_i_cancelarActionPerformed
        c_i_cod.setText("");
        c_i_end.setText("");
        c_i_uc.setText("");
        modoImovel = "Navegar";
        ManipulaInterfaceImovel();
    }//GEN-LAST:event_btn_i_cancelarActionPerformed

    private void btn_i_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_i_salvarActionPerformed
        int index = c_i_cliente.getSelectedIndex();
        if(index==0){
            JOptionPane.showMessageDialog(this,"Você deve selecionar um cliente");
        }else{
            Imovel F = new Imovel();
            F.setCodigo(Integer.parseInt(c_i_cod.getText()));
            F.setCliente(ListaCliente.get(index-1));
            F.setEndereco(c_i_end.getText());
            F.setUC(c_i_uc.getText());

            ListaImovel.add(F);
            ListaCliente.get(index-1).addImovel(F);
        }
        LoadTableImovel();
        modoFunc = "Navegar";
        ManipulaInterfaceImovel();
        c_i_cod.setText("");
        c_i_end.setText("");
        c_i_uc.setText("");
    }//GEN-LAST:event_btn_i_salvarActionPerformed

    private void btn_i_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_i_excluirActionPerformed
        int index = tbl_imovel.getSelectedRow();
        if(index>=0 && index<ListaImovel.size()){
            ListaImovel.remove(index);
        }
        LoadTableImovel();
        modoImovel = "Navegar";
        ManipulaInterfaceImovel();
    }//GEN-LAST:event_btn_i_excluirActionPerformed

    private void btn_i_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_i_editarActionPerformed
        modoImovel = "Editar";
        ManipulaInterfaceImovel();
    }//GEN-LAST:event_btn_i_editarActionPerformed

    private void btn_i_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_i_novoActionPerformed
        c_i_cod.setText("");
        c_i_end.setText("");
        c_i_uc.setText("");

        modoImovel = "Novo";
        ManipulaInterfaceImovel();
    }//GEN-LAST:event_btn_i_novoActionPerformed

    private void tbl_imovelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_imovelMouseClicked
        int index = tbl_imovel.getSelectedRow();
        if(index>=0 && index<ListaImovel.size()){
            Imovel D = ListaImovel.get(index);
            c_i_cod.setText(String.valueOf(D.getCodigo()));
            c_i_end.setText(D.getEndereco());
            c_i_uc.setText(D.getUC());
            modoImovel = "Selecao";
            ManipulaInterfaceImovel();
        }
    }//GEN-LAST:event_tbl_imovelMouseClicked

    private void c_cl_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_cl_dataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_cl_dataActionPerformed

    private void btn_cl_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cl_cancelarActionPerformed
        c_cl_cod.setText("");
        c_cl_nome.setText("");
        c_cl_cpf.setText("");
        modoCliente = "Navegar";
        ManipulaInterfaceCliente();
    }//GEN-LAST:event_btn_cl_cancelarActionPerformed

    private void btn_cl_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cl_salvarActionPerformed
        int cod = Integer.parseInt(c_cl_cod.getText());

        if(verificarCPF(c_cl_cpf.getText())==true){
            if(modoCliente.equals("Novo")){
                Clientes F = new Clientes(cod, c_cl_nome.getText(), c_cl_cpf.getText(), c_cl_data.getText());
                ListaCliente.add(F);
            }else if(modoCliente.equals("Editar")){
                int index = tbl_clientes.getSelectedRow();
                ListaCliente.get(index).setCodigo(cod);
                ListaCliente.get(index).setNome(c_cl_nome.getText());
                ListaCliente.get(index).setCPF(c_cl_cpf.getText());
                ListaCliente.get(index).setData(c_cl_data.getText());
            }

            LoadTableCliente();
            modoCliente = "Navegar";
            ManipulaInterfaceCliente();
            c_cl_cod.setText("");
            c_cl_nome.setText("");
            c_cl_cpf.setText("");
            c_cl_data.setText("");
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "CPF inválido");
        }
    }//GEN-LAST:event_btn_cl_salvarActionPerformed

    private void btn_cl_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cl_excluirActionPerformed
        int index = tbl_clientes.getSelectedRow();
        if(index>=0 && index<ListaCliente.size()){
            ListaCliente.remove(index);
        }
        LoadTableCliente();
        modoCliente = "Navegar";
        ManipulaInterfaceCliente();
    }//GEN-LAST:event_btn_cl_excluirActionPerformed

    private void btn_cl_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cl_editarActionPerformed
        modoCliente = "Editar";
        ManipulaInterfaceCliente();
    }//GEN-LAST:event_btn_cl_editarActionPerformed

    private void btn_cl_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cl_novoActionPerformed
        modoCliente = "Novo";
        ManipulaInterfaceCliente();
    }//GEN-LAST:event_btn_cl_novoActionPerformed

    private void tbl_clientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_clientesMouseClicked
        int index = tbl_clientes.getSelectedRow();
        if(index>=0 && index<ListaCliente.size()){
            Clientes D = ListaCliente.get(index);
            c_cl_cod.setText(String.valueOf(D.getCodigo()));
            c_cl_nome.setText(D.getNome());
            c_cl_cpf.setText(D.getCPF());
            c_cl_data.setText(D.getData());
            modoCliente = "Selecao";
            ManipulaInterfaceCliente();
        }
    }//GEN-LAST:event_tbl_clientesMouseClicked

    private void btn_func_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_func_novoActionPerformed
        modoFunc = "Novo";
        ManipulaInterfaceFunc();
    }//GEN-LAST:event_btn_func_novoActionPerformed

    private void c_func_salarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_func_salarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_func_salarioActionPerformed

    private void cb_func_depsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_func_depsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_func_depsActionPerformed

    private void cb_func_depsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_func_depsMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_func_depsMousePressed

    private void btn_func_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_func_cancelarActionPerformed
        modoFunc = "Navegar";
        ManipulaInterfaceFunc();
    }//GEN-LAST:event_btn_func_cancelarActionPerformed

    private void btn_func_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_func_salvarActionPerformed
        int index = cb_func_deps.getSelectedIndex();
        if(index==0){
            JOptionPane.showMessageDialog(this,"Você deve selecionar uma agencia");
        }else{
            Funcionario F = new Funcionario();
            F.setMatricula(Integer.parseInt(c_func_mat.getText()));
            F.setNome(c_func_nome.getText());
            F.setSalario(Integer.parseInt(c_func_salario.getText()));
            F.setDep(ListaDep.get(index-1));

            ListaFunc.add(F);
            ListaDep.get(index-1).addFunc(F);
        }
        LoadTableFunc();
        modoFunc = "Navegar";
        ManipulaInterfaceFunc();
    }//GEN-LAST:event_btn_func_salvarActionPerformed

    private void btn_func_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_func_excluirActionPerformed
        int index = tbl_func.getSelectedRow();
        if(index>=0 && index<ListaFunc.size()){
            ListaFunc.remove(index);
        }
        LoadTableFunc();
        modoFunc = "Navegar";
        ManipulaInterfaceFunc();
    }//GEN-LAST:event_btn_func_excluirActionPerformed

    private void tbl_funcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_funcMouseClicked
        int index = tbl_func.getSelectedRow();
        if(index>=0 && index<ListaFunc.size()){
            Funcionario D = ListaFunc.get(index);
            c_func_mat.setText(String.valueOf(D.getMatricula()));
            c_func_nome.setText(D.getNome());
            c_func_salario.setText(String.valueOf(D.getSalario()));
            modoFunc = "Selecao";
            ManipulaInterfaceFunc();
        }
    }//GEN-LAST:event_tbl_funcMouseClicked

    private void btn_func_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_func_editarActionPerformed
        modoFunc = "Editar";
        ManipulaInterfaceFunc();
    }//GEN-LAST:event_btn_func_editarActionPerformed

    private void btn_dep_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dep_excluirActionPerformed
        int index = tbl_dep_dpts.getSelectedRow();
        if(index>=0 && index<ListaDep.size()){
            ListaDep.remove(index);
        }
        LoadTableDep();
        modoDep = "Navegar";
        ManipulaInterfaceDep();
    }//GEN-LAST:event_btn_dep_excluirActionPerformed

    private void btn_dep_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dep_editarActionPerformed
        modoDep = "Editar";
        ManipulaInterfaceDep();
    }//GEN-LAST:event_btn_dep_editarActionPerformed

    private void btn_dep_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dep_novoActionPerformed
        c_dep_codigo.setText("");
        c_dep_nome.setText("");

        modoDep = "Novo";
        ManipulaInterfaceDep();
    }//GEN-LAST:event_btn_dep_novoActionPerformed

    private void btn_dep_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dep_cancelarActionPerformed
        c_dep_codigo.setText("");
        c_dep_nome.setText("");
        modoDep = "Navegar";
        ManipulaInterfaceDep();
    }//GEN-LAST:event_btn_dep_cancelarActionPerformed

    private void btn_dep_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dep_salvarActionPerformed
        /*
        PreparedStatement ps2 = null;
        try {
            ps2 = Conexao.conexao().prepareStatement("Insert into agencia (nome_agencia) values (?)");
            ps2.setString(1, c_dep_nome.getText());
            ps2.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("erro"+ex);
        }
        */
        
        int cod = Integer.parseInt(c_dep_codigo.getText());
        if(modoDep.equals("Novo")){
            Agencia D = new Agencia(cod, c_dep_nome.getText());
            ListaDep.add(D);
        }else if(modoDep.equals("Editar")){
            int index = tbl_dep_dpts.getSelectedRow();
            ListaDep.get(index).setCodigo(cod);
            ListaDep.get(index).setNome(c_dep_nome.getText());
        }

        LoadTableDep();
        modoDep = "Navegar";
        ManipulaInterfaceDep();
        c_dep_codigo.setText("");
        c_dep_nome.setText("");
    }//GEN-LAST:event_btn_dep_salvarActionPerformed

    private void c_dep_nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_dep_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_dep_nomeActionPerformed

    private void tbl_dep_dptsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_dep_dptsMouseClicked
        int index = tbl_dep_dpts.getSelectedRow();
        if(index>=0 && index<ListaDep.size()){
            Agencia D = ListaDep.get(index);
            c_dep_codigo.setText(String.valueOf(D.getCodigo()));
            c_dep_nome.setText(D.getNome());
            modoDep = "Selecao";
            ManipulaInterfaceDep();
        }
    }//GEN-LAST:event_tbl_dep_dptsMouseClicked

    private void btn_c_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_c_novoActionPerformed
        c_c_cod.setText("");
        c_c_valor.setText("");
        c_c_venc.setText("");

        modoConta = "Novo";
        ManipulaInterfaceConta();
    }//GEN-LAST:event_btn_c_novoActionPerformed

    private void btn_c_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_c_editarActionPerformed
        modoConta = "Editar";
        ManipulaInterfaceConta();
    }//GEN-LAST:event_btn_c_editarActionPerformed

    private void btn_c_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_c_excluirActionPerformed
        int index = tbl_conta.getSelectedRow();
        if(index>=0 && index<ListaConta.size()){
            ListaConta.remove(index);
        }
        LoadTableConta();
        modoConta = "Navegar";
        ManipulaInterfaceConta();
    }//GEN-LAST:event_btn_c_excluirActionPerformed

    private void btn_c_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_c_salvarActionPerformed
        int index = c_c_cliente.getSelectedIndex();
        if(index==0){
            JOptionPane.showMessageDialog(this,"Você deve selecionar um cliente");
        }else{
            Conta F = new Conta();
            F.setCodigo(Integer.parseInt(c_c_cod.getText()));
            F.setValor(Integer.parseInt(c_c_valor.getText()));
            F.setData(c_c_venc.getText());
            F.setCliente(ListaCliente.get(index-1));

            ListaConta.add(F);
            ListaCliente.get(index-1).addConta(F);
        }
        LoadTableConta();
        modoConta = "Navegar";
        ManipulaInterfaceConta();
        c_c_cod.setText("");
        c_c_valor.setText("");
        c_c_venc.setText("");
    }//GEN-LAST:event_btn_c_salvarActionPerformed

    private void btn_c_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_c_cancelarActionPerformed
        c_c_cod.setText("");
        c_c_valor.setText("");
        c_c_venc.setText("");
        modoConta = "Navegar";
        ManipulaInterfaceConta();
    }//GEN-LAST:event_btn_c_cancelarActionPerformed

    private void c_c_valorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_c_valorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_c_valorActionPerformed

    private void c_c_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_c_clienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_c_clienteActionPerformed

    private void c_c_vencActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_c_vencActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_c_vencActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_c_cancelar;
    private javax.swing.JButton btn_c_editar;
    private javax.swing.JButton btn_c_excluir;
    private javax.swing.JButton btn_c_novo;
    private javax.swing.JButton btn_c_salvar;
    private javax.swing.JButton btn_cl_cancelar;
    private javax.swing.JButton btn_cl_editar;
    private javax.swing.JButton btn_cl_excluir;
    private javax.swing.JButton btn_cl_novo;
    private javax.swing.JButton btn_cl_salvar;
    private javax.swing.JButton btn_dep_cancelar;
    private javax.swing.JButton btn_dep_editar;
    private javax.swing.JButton btn_dep_excluir;
    private javax.swing.JButton btn_dep_novo;
    private javax.swing.JButton btn_dep_salvar;
    private javax.swing.JButton btn_func_cancelar;
    private javax.swing.JButton btn_func_editar;
    private javax.swing.JButton btn_func_excluir;
    private javax.swing.JButton btn_func_novo;
    private javax.swing.JButton btn_func_salvar;
    private javax.swing.JButton btn_i_cancelar;
    private javax.swing.JButton btn_i_editar;
    private javax.swing.JButton btn_i_excluir;
    private javax.swing.JButton btn_i_novo;
    private javax.swing.JButton btn_i_salvar;
    private javax.swing.JComboBox c_c_cliente;
    private javax.swing.JTextField c_c_cod;
    private javax.swing.JTextField c_c_valor;
    private javax.swing.JFormattedTextField c_c_venc;
    private javax.swing.JTextField c_cl_cod;
    private javax.swing.JFormattedTextField c_cl_cpf;
    private javax.swing.JFormattedTextField c_cl_data;
    private javax.swing.JTextField c_cl_nome;
    private javax.swing.JTextField c_dep_codigo;
    private javax.swing.JTextField c_dep_nome;
    private javax.swing.JTextField c_func_mat;
    private javax.swing.JTextField c_func_nome;
    private javax.swing.JTextField c_func_salario;
    private javax.swing.JComboBox c_i_cliente;
    private javax.swing.JTextField c_i_cod;
    private javax.swing.JTextField c_i_end;
    private javax.swing.JTextField c_i_uc;
    private javax.swing.JComboBox cb_func_deps;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbl_clientes;
    private javax.swing.JTable tbl_conta;
    private javax.swing.JTable tbl_dep_dpts;
    private javax.swing.JTable tbl_func;
    private javax.swing.JTable tbl_imovel;
    // End of variables declaration//GEN-END:variables
}



