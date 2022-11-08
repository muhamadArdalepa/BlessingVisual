/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package blessingvisual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author User
 */
public class DialogOrderBaru extends javax.swing.JDialog {

    /**
     * Creates new form DialogOrderBaruPelanggan
     */
    Frame parent = null;
    static String idEdit;
    Object[][] arrObjectBarang;
    String[][] arrDataBarang;
    String[] arrDataOrder;
    static ArrayList<String> arrLiTempBarang = new ArrayList<>();
    DefaultTableModel dtmPinjam;
    TableColumn tc;
    static String idOrder;
    double total = 0 ;
    boolean isClose = true;
    public DialogOrderBaru(java.awt.Frame parent, boolean modal,String idEdit,String idOrder) {
        super(parent, modal);
        this.parent=parent;
        this.idEdit = idEdit;
        this.idOrder = idOrder;
        initComponents();
        dtmPinjam = (DefaultTableModel)jTable1.getModel();
        tc = jTable1.getColumnModel().getColumn(3);
        tc.setCellRenderer(new ColumnColorRenderer(view.Tertiary));
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(10);
        
        
        if (idOrder==null) {
           generateId();        
        }else{
            arrDataOrder = model.arrResult("select * from tbl_order where id_order = '"+idOrder+"'");
            arrLiTempBarang = model.arrLiResult("select id_barang from tbl_detail_order where id_order = '"+idOrder+"'");
            tfDurasi.setText(arrDataOrder[4]);
        }
        showBarangPenyewaan(tfSearch.getText());
        showBarang();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    
    public void showBarangPenyewaan(String search){
        if (search.equals("Cari")) {
            search="";
        }
        panMain.removeAll();
        panMain.repaint();
        panMain.revalidate();
        String sql = "select * from (select id_barang,image,merk,nm_barang,harga from tbl_barang where status='Tersedia' ";
        if (!arrLiTempBarang.isEmpty()) {
            for (int i = 0; i < arrLiTempBarang.size(); i++) {
                sql += "and id_barang<>'"+arrLiTempBarang.get(i)+"' ";
            }
        }
        sql+= ")as tbl where nm_barang like '%"+search+"%' or merk like '%"+search+"%' ";
        arrDataBarang=model.arr2Result(sql);
        arrObjectBarang=new Object[arrDataBarang.length][arrDataBarang[0].length+1];
        for (int i = 0; i < arrDataBarang.length; i++) {
            arrObjectBarang[i][0]=new JPanel();
            ((JPanel)arrObjectBarang[i][0]).setBackground(view.White);
            ((JPanel)arrObjectBarang[i][0]).setLayout(null);
            ((JPanel)arrObjectBarang[i][0]).setName(arrDataBarang[i][0]);
            ((JPanel)arrObjectBarang[i][0]).setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, view.Secondary));
            for (int j = 1; j < arrDataBarang[0].length; j++) {
                arrObjectBarang[i][j]=new JLabel();
                if (j==1) {
                    ((JLabel)arrObjectBarang[i][j]).setIcon(new ImageIcon(view.showIcon(arrDataBarang[i][1])));
                }else if (j==4) {
                    ((JLabel)arrObjectBarang[i][j]).setText(NumberFormat.getCurrencyInstance().format(Float.parseFloat(arrDataBarang[i][4])));
                    ((JLabel)arrObjectBarang[i][j]).setFont(new Font("Montserrat ExtraBold",0,14));
                }else{
                    ((JLabel)arrObjectBarang[i][j]).setText(arrDataBarang[i][j]);
                    ((JLabel)arrObjectBarang[i][j]).setFont(view.segoe(0, 14));
                }
                
                ((JPanel)arrObjectBarang[i][0]).add((JLabel)arrObjectBarang[i][j]);
                
            }
            ((JLabel)arrObjectBarang[i][2]).setVerticalAlignment(SwingConstants.BOTTOM);
            ((JLabel)arrObjectBarang[i][3]).setVerticalAlignment(SwingConstants.TOP);
            ((JLabel)arrObjectBarang[i][4]).setVerticalAlignment(SwingConstants.BOTTOM);
            
            arrObjectBarang[i][5]=new JButton();
            ((JButton)arrObjectBarang[i][5]).setFont(new Font("Montserrat ExtraBold",0,14));
            ((JButton)arrObjectBarang[i][5]).setText("+");
            ((JButton)arrObjectBarang[i][5]).setName(arrDataBarang[i][0]);
            ((JButton)arrObjectBarang[i][5]).setForeground(Color.WHITE);
            ((JButton)arrObjectBarang[i][5]).setBackground(view.Primary);
            ((JButton)arrObjectBarang[i][5]).setFocusPainted(false);
            ((JButton)arrObjectBarang[i][5]).setMargin(new Insets(0, 0, 0, 0));
            ((JButton)arrObjectBarang[i][5]).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addBarang(e);
                    }
                });
            
            
            ((JPanel)arrObjectBarang[i][0]).add((JButton)arrObjectBarang[i][5]);
            
            
            panMain.add((JPanel)arrObjectBarang[i][0]);
        }
        
        panMain.setPreferredSize(new Dimension(400,(arrDataBarang.length*120)));
        boundingBarang();
    }
    public void boundingBarang(){
        for (int i = 0; i < arrObjectBarang.length; i++) {
            ((JPanel)arrObjectBarang[i][0]).setBounds(0,i*120,400,120);
            ((JLabel)arrObjectBarang[i][1]).setBounds(10,10,100,100);
            ((JLabel)arrObjectBarang[i][2]).setBounds(120,10,150,30);
            ((JLabel)arrObjectBarang[i][3]).setBounds(120,40,150,30);
            ((JLabel)arrObjectBarang[i][4]).setBounds(120,70,150,30);
            ((JButton)arrObjectBarang[i][5]).setBounds(300,70,30,30);
        }
    }
    public void addBarang(ActionEvent e){
        if (tfDurasi.getText().equals("")) {
            DialogInfo.showMsg(this, true, "Masukkan Durasi", "Info", true);
            tfDurasi.requestFocus();
        }else{
            arrLiTempBarang.add(((JButton)e.getSource()).getName());
            showBarangPenyewaan(tfSearch.getText());
            showBarang();
        }
    }
    public void showBarang(){

        dtmPinjam.setRowCount(0);
        dtmPinjam.fireTableDataChanged();
        if(tfDurasi.getText().equals("")){
            tfDurasi.setText("0");
        }
        int durasi = Integer.parseInt(tfDurasi.getText());
        for (int i = 0; i < arrLiTempBarang.size(); i++) {
            String[] data = model.arrResult("select merk,nm_barang,harga from tbl_barang where id_barang='"+arrLiTempBarang.get(i)+"'");
            String nm = data[0]+" "+data[1];
            String harga = NumberFormat.getCurrencyInstance().format(Float.parseFloat(data[2])*durasi);
            dtmPinjam.addRow(new String[]{
                arrLiTempBarang.get(i),
                nm,
                harga,
                "Hapus"
            });
        }
        total =0;
        if (arrLiTempBarang.size()==0) {
        }else{
            for (int i = 0; i < arrLiTempBarang.size(); i++) {
                try {
                    total+=(model.doubleResult("select harga from tbl_barang where id_barang='"+arrLiTempBarang.get(i)+"'")*durasi);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        tfTotal.setText("Total Bayar : "+NumberFormat.getCurrencyInstance().format(total));
    }
     public void generateId(){
        idOrder = "";
        String date = view.idFormat.format(new Date());
        String sql = "Select id_order from tbl_order where id_order like '%"+date+"%'";
        String sIdOrder = model.stringResult(sql);
        if (sIdOrder.isEmpty()) {
            sIdOrder = "O000";
        }
        
        for (int i = 1; i < 4; i++) {
            idOrder += sIdOrder.charAt(i);
        }

        int iIdOrder = Integer.parseInt(idOrder)+1;
        
        if(iIdOrder<10){
            idOrder= "O00"+iIdOrder;
        }else if(iIdOrder<100){
            idOrder= "O0"+iIdOrder;
        }else{
            idOrder= "O"+iIdOrder;
        }
        idOrder+=date;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panMain = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        tfDurasi = new javax.swing.JTextField();
        tfSearch = new javax.swing.JTextField();
        lbSearch = new javax.swing.JLabel();
        tfTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(500, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formClose(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formClose(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setRequestFocusEnabled(false);

        jLabel1.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 16)); // NOI18N
        jLabel1.setForeground(view.Primary);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Order Baru");

        jButton3.setBackground(view.Danger);
        jButton3.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Tutup");
        jButton3.setFocusPainted(false);
        jButton3.setMaximumSize(new java.awt.Dimension(120, 30));
        jButton3.setMinimumSize(new java.awt.Dimension(120, 30));
        jButton3.setPreferredSize(new java.awt.Dimension(120, 30));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnSimpan.setBackground(view.Primary);
        btnSimpan.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 14)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("Tambah");
        btnSimpan.setFocusPainted(false);
        btnSimpan.setMaximumSize(new java.awt.Dimension(120, 30));
        btnSimpan.setMinimumSize(new java.awt.Dimension(120, 30));
        btnSimpan.setPreferredSize(new java.awt.Dimension(120, 30));
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnKembali.setBackground(new java.awt.Color(255, 255, 255));
        btnKembali.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 14)); // NOI18N
        btnKembali.setForeground(view.Primary);
        btnKembali.setText("Kembali");
        btnKembali.setFocusPainted(false);
        btnKembali.setMaximumSize(new java.awt.Dimension(120, 30));
        btnKembali.setMinimumSize(new java.awt.Dimension(120, 30));
        btnKembali.setPreferredSize(new java.awt.Dimension(120, 30));
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panMain.setBackground(new java.awt.Color(255, 255, 255));
        panMain.setLayout(null);
        jScrollPane1.setViewportView(panMain);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBackground(new java.awt.Color(255, 255, 255));
        jTable1.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        jTable1.setForeground(new java.awt.Color(50, 79, 83));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Barang", "Total", "Hapus"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setFocusable(false);
        jTable1.setGridColor(view.Secondary);
        jTable1.setIntercellSpacing(new java.awt.Dimension(30, 0));
        jTable1.setRowHeight(30);
        jTable1.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jTable1.setSelectionForeground(view.Primary);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setShowGrid(true);
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(0);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(0);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setMinWidth(110);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(110);
        }

        jLabel2.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Durasi(hari)");

        tfDurasi.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        tfDurasi.setForeground(view.Primary);
        tfDurasi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfDurasi.setText("0");
        tfDurasi.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(view.Secondary), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        tfDurasi.setSelectionColor(view.Secondary);
        tfDurasi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfDurasiFocusGained(evt);
                tfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfDurasiFocusLost(evt);
            }
        });
        tfDurasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfDurasiKeyReleased(evt);
            }
        });

        tfSearch.setBackground(new java.awt.Color(255, 255, 255));
        tfSearch.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        tfSearch.setForeground(new java.awt.Color(140, 155, 154));
        tfSearch.setText("Cari");
        tfSearch.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(140, 155, 154)), javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 10)));
        tfSearch.setDisabledTextColor(new java.awt.Color(140, 155, 154));
        tfSearch.setMinimumSize(new java.awt.Dimension(30, 200));
        tfSearch.setPreferredSize(new java.awt.Dimension(30, 200));
        tfSearch.setSelectionColor(new java.awt.Color(140, 155, 154));
        tfSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfFocusLost(evt);
            }
        });
        tfSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSearchKeyReleased(evt);
            }
        });

        lbSearch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/magnifying-glass.png"))); // NOI18N
        lbSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(140, 155, 154)));
        lbSearch.setMaximumSize(new java.awt.Dimension(30, 30));
        lbSearch.setMinimumSize(new java.awt.Dimension(30, 30));
        lbSearch.setPreferredSize(new java.awt.Dimension(30, 30));

        tfTotal.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        tfTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tfTotal.setText("Total");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfDurasi, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                                    .addComponent(tfTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addGap(48, 48, 48))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfDurasi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        isClose=false;
        if (arrLiTempBarang.isEmpty()) {
            DialogInfo.showMsg(this, true, "Tambah Barang!", "Info", true);
        }else{
            if (tfDurasi.getText().equals("")) {
                tfDurasi.setText("0");
            }
            if (model.stringResult("select id_order from tbl_order where id_order='"+idOrder+"'").isEmpty()) {
                model.updOrIns("Insert into tbl_order values("
                    + "'"+idOrder+"', "
                    + "'"+idEdit+"', "
                    + "now(), "
                    + "'"+FormMain.arrUser[0]+"',"
                    + ""+tfDurasi.getText()+","
                    + "null, null, "
                    + "'Ongoing', "
                    + total
                    + ")");
            }else{
                model.updOrIns("update tbl_order set tgl_order= "
                    + "now(), "
                    + "durasi="+tfDurasi.getText()+", "
                    + "total_bayar= "+total+" "
                    + "where id_order ='"+idOrder+"'");
            }

            for (int i = 0; i < arrLiTempBarang.size(); i++) {
                try {
                    model.updOrIns("Insert into tbl_detail_order values("
                            + "'"+idOrder+"',"
                            + "'"+jTable1.getValueAt(i, 0)+"',"
                            + NumberFormat.getCurrencyInstance().parse((String)jTable1.getValueAt(i, 2))
                            + ")");
                    model.updOrIns("update tbl_barang set status ='Disewa' where id_barang='"+arrLiTempBarang.get(i)+"'");
                } catch (ParseException e) {
                    System.out.println(e);
                }

            }
            dispose();
            DialogInfo.showMsg(parent, true, "Order Berhasil", "info", true);
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void tfDurasiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfDurasiFocusGained
        if (tfDurasi.getText().equals("0")) {
            tfDurasi.setText("");
        }
    }//GEN-LAST:event_tfDurasiFocusGained

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        isClose=false;
        if (tfDurasi.getText().equals("")) {
            tfDurasi.setText("0");
        }
        if (model.stringResult("select id_order from tbl_order where id_order='"+idOrder+"'").isEmpty()) {
            model.updOrIns("Insert into tbl_order values("
                + "'"+idOrder+"', "
                + "'"+idEdit+"', "
                + "now(), "
                + "'"+FormMain.arrUser[0]+"',"
                + ""+tfDurasi.getText()+","
                + "null, null, "
                + "'Ongoing', "
                + total
                + ")");
        }else{
            model.updOrIns("update tbl_order set tgl_order= "
                + "now(), "
                + "durasi="+tfDurasi.getText()+", "
                + "total_bayar= "+total+" "
                + "where id_order ='"+idOrder+"'");
            model.updOrIns("delete from tbl_detail_order where id_order='"+idOrder+"'");
            
        }
        
        if (!arrLiTempBarang.isEmpty()) {
            
            for (int i = 0; i < arrLiTempBarang.size(); i++) {
                try {
                    model.updOrIns("Insert into tbl_detail_order values("
                            + "'"+idOrder+"',"
                            + "'"+jTable1.getValueAt(i, 0)+"',"
                            + NumberFormat.getCurrencyInstance().parse((String)jTable1.getValueAt(i, 2))
                            + ")");
                } catch (ParseException e) {
                    System.out.println(e);
                }

            }
        }
        dispose();
        new DialogEditBaru(parent, true, "Penyewaan", idEdit, idOrder).show();
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tfDurasi.requestFocus();
    }//GEN-LAST:event_formWindowOpened

    private void tfDurasiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDurasiKeyReleased
        if (!tfDurasi.getText().isEmpty()) {
            showBarang();
        }
    }//GEN-LAST:event_tfDurasiKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (jTable1.getSelectedColumn()==3) {
            jTable1.setSelectionForeground(view.Tertiary);
            arrLiTempBarang.remove(jTable1.getValueAt(jTable1.getSelectedRow(), 0));
            showBarangPenyewaan(tfSearch.getText());
            showBarang();

        }else{
            jTable1.setSelectionForeground(view.Primary);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfFocusGained
        tfSearch.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, view.Primary));
        tfSearch.setForeground(view.Primary);
        lbSearch.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, view.Primary));
        if (tfSearch.getText().equals("Cari")) {
            tfSearch.setText("");
        }
    }//GEN-LAST:event_tfFocusGained

    private void tfDurasiFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfDurasiFocusLost
        if (tfDurasi.getText().equals("")) {
            tfDurasi.setText("0");
        }
    }//GEN-LAST:event_tfDurasiFocusLost

    private void formClose(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formClose
        if (isClose) {
            model.updOrIns("delete from tbl_order where id_order = '"+idOrder+"'");
            model.updOrIns("delete from tbl_pelanggan where id_pelanggan = '"+idEdit+"'");
        }
    }//GEN-LAST:event_formClose

    private void tfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfFocusLost
        tfSearch.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, view.Secondary));
        tfSearch.setForeground(view.Secondary);
        lbSearch.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, view.Secondary));
        if (tfSearch.getText().equals("")) {
            tfSearch.setText("Cari");
        }
    }//GEN-LAST:event_tfFocusLost

    private void tfSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchKeyReleased
        showBarangPenyewaan(tfSearch.getText());
    }//GEN-LAST:event_tfSearchKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DialogOrderBaru dialog = new DialogOrderBaru(new javax.swing.JFrame(), true,idEdit,idOrder);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbSearch;
    private javax.swing.JPanel panMain;
    private javax.swing.JTextField tfDurasi;
    private javax.swing.JTextField tfSearch;
    private javax.swing.JLabel tfTotal;
    // End of variables declaration//GEN-END:variables
}
