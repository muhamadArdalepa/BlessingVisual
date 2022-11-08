/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package blessingvisual;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author User
 */
public class DialogEditBaru extends javax.swing.JDialog {

    /**
     * Creates new form DialogOrderBaruPelanggan
     */
    boolean isClose = true;
    Frame parent = null;
    static String menu = "Kelola Barang";
    static String idEdit;
    static String idOrder;
    String[] arrEdit; 
    String idPegawai,idPelanggan,idBarang;
    File f = new File("src\\img\\images\\default_image.jpg");
    public DialogEditBaru(java.awt.Frame parent, boolean modal,String menu,String idEdit,String idOrder) {
        super(parent, modal);
        this.parent = parent;
        this.menu = menu;
        this.idEdit = idEdit;
        this.idOrder = idOrder;
        initComponents();
        System.out.println(idOrder);
        spMain.getVerticalScrollBar().setUnitIncrement(10);
        tfJK.setVisible(false);
        tfRole.setVisible(false);

        showAll();
        
        if (idEdit==null) {
            generateId();
        }
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
            
       
    }
    public void showAll(){
        if (menu.equals("Kelola Pegawai")||menu.equals("Profile")) {
            lbTitle.setText("Pegawai Baru");
            lbHarga.setVisible(false);
            tfHarga.setVisible(false);
            lbFoto.setVisible(false);
            lbFoto2.setVisible(false);
            btnFoto.setVisible(false);
            setSize(new Dimension(getWidth(),650));
            jPanel1.setPreferredSize(new Dimension(spMain.getWidth(),485));
            if (idEdit!=null) {
                if (menu.equals("Profile")) {
                    lbTitle.setText("Edit Profile");
                    enableAll();
                }else{
                    lbTitle.setText("Rincian");
                }
                arrEdit=model.arrResult("select * from tbl_pegawai where id_pegawai='"+idEdit+"'");
                tfNama.setText(arrEdit[1]);
                cxJK.setSelectedItem(arrEdit[2]);
                tfJK.setText(arrEdit[2]);
                tfTelepon.setText(arrEdit[3]);
                tfEmail.setText(arrEdit[4]);
                tfAlamat.setText(arrEdit[5]);
                cxRole.setSelectedItem(arrEdit[6]);
                tfRole.setText(arrEdit[6]);
                tfPin.setText(arrEdit[7]);
            }
            
        }else if(menu.equals("Kelola Pelanggan")||menu.equals("Penyewaan")){
            switch (menu) {
                case "Kelola Pelanggan":lbTitle.setText("Pelanggan Baru");
                    break;
                case "Penyewaan":lbTitle.setText("Order Baru");
                    break;
                default:
                    break;
            }
            cxJK.setVisible(false);
            lbJK.setVisible(false);
            lbRole.setVisible(false);
            cxRole.setVisible(false);
            lbRole.setVisible(false);
            lbPin.setVisible(false);
            tfPin.setVisible(false);
            lbHarga.setVisible(false);
            tfHarga.setVisible(false);
            lbEmail.setVisible(false);
            tfEmail.setVisible(false);
            lbFoto.setVisible(false);
            lbFoto2.setVisible(false);
            btnFoto.setVisible(false);
            spMain.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            if (idEdit!=null) {
                lbTitle.setText("Rincian");
                arrEdit=model.arrResult("select * from tbl_pelanggan where id_pelanggan='"+idEdit+"'");
                tfNama.setText(arrEdit[1]);
                tfTelepon.setText(arrEdit[2]);
                tfAlamat.setText(arrEdit[3]);
            }
        }else if(menu.equals("Kelola Barang")){
            lbTitle.setText("Tambah Barang");
            tfNama.setVisible(false);
            lbNama.setVisible(false);
            lbJK.setText("Jenis");
            cxJK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Kamera","Lensa","Memori","Baterai","Tripod","Lampu","Lainnya"}));
            lbTelepon.setText("Merk");
            lbEmail.setText("Tipe");
            lbAlm.setVisible(false);
            spTfAlamat.setVisible(false);
            tfAlamat.setVisible(false);
            cxRole.setVisible(false);
            lbRole.setVisible(false);
            lbPin.setVisible(false);
            tfPin.setVisible(false);
            setSize(new Dimension(getWidth(),650));
            spMain.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            if (idEdit!=null) {
                setSize(new Dimension(getWidth(),700));
                lbTitle.setText("Rincian");
                arrEdit=model.arrResult("select * from tbl_barang where id_barang='"+idEdit+"'");
                lbFoto2.setIcon(new ImageIcon(view.showIcon(arrEdit[5])));
                cxJK.setSelectedIndex(Integer.parseInt(arrEdit[1])-1);
                tfJK.setText((String) cxJK.getSelectedItem());
                tfTelepon.setText(arrEdit[2]);
                tfEmail.setText(arrEdit[3]);
                tfHarga.setText(arrEdit[4]);
                lbRole.setVisible(true);
                lbRole.setText("Status");
                cxRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Tersedia","Disewa","Diservis"}));
                cxJK.setSelectedItem(arrEdit[6]);
                tfRole.setText(arrEdit[6]);
            }
        }
                
        if (idEdit!=null&&menu!="Penyewaan"&&menu!="Profile") {
            disableAll();
        }
    }
    public void enableAll(){
        
        btnSimpan.setText("Simpan");
        btnClose.setText("Batal");
        tfAlamat.setEnabled(true);
        tfAlamat.setEditable(true);
        tfEmail.setEnabled(true);
        tfHarga.setEnabled(true);
        tfNama.setEnabled(true);
        tfPin.setEnabled(true);
        tfTelepon.setEnabled(true);
        cxJK.setVisible(true);
        tfJK.setVisible(false);
        cxRole.setVisible(true);
        tfRole.setVisible(false);
        btnFoto.setVisible(true);
                    
        tfAlamat.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(view.Secondary),BorderFactory.createEmptyBorder(5,5,5,5)));
        tfEmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(view.Secondary),BorderFactory.createEmptyBorder(0,5,0,5)));
        tfHarga.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(view.Secondary),BorderFactory.createEmptyBorder(0,5,0,5)));
        tfNama.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(view.Secondary),BorderFactory.createEmptyBorder(0,5,0,5)));
        tfPin.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(view.Secondary),BorderFactory.createEmptyBorder(0,5,0,5)));
        tfTelepon.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(view.Secondary),BorderFactory.createEmptyBorder(0,5,0,5)));
        
        if (!menu.equals("Kelola Barang")) {
            btnFoto.setVisible(false);
        }
        if (menu.equals("Kelola Pelanggan")||menu.equals("Profile")) {
            cxRole.setVisible(false);
            cxJK.setVisible(false);
        }
    }
    public void disableAll(){
        btnSimpan.setText("Edit");
        btnClose.setText("Tutup");
        tfAlamat.setEnabled(false);
        tfEmail.setEnabled(false);
        tfHarga.setEnabled(false);
        tfNama.setEnabled(false);
        tfPin.setEnabled(false);
        tfTelepon.setEnabled(false);
        cxJK.setVisible(false);
        tfJK.setVisible(true);
        tfJK.setText((String)cxJK.getSelectedItem());
        
        cxRole.setVisible(false);
        tfRole.setVisible(true);
        tfRole.setText((String)cxRole.getSelectedItem());
        btnFoto.setVisible(false);
        
        tfAlamat.setBorder(null);
        tfEmail.setBorder(null);
        tfHarga.setBorder(null);
        tfNama.setBorder(null);
        tfPin.setBorder(null);
        tfTelepon.setBorder(null);
        tfJK.setBorder(null);
        tfRole.setBorder(null);
        if (menu.equals("Kelola Pelanggan")) {
            tfJK.setVisible(false);
            tfRole.setVisible(false);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitle = new javax.swing.JLabel();
        spMain = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        lbNama = new javax.swing.JLabel();
        tfNama = new javax.swing.JTextField();
        lbJK = new javax.swing.JLabel();
        cxJK = new javax.swing.JComboBox<>();
        tfTelepon = new javax.swing.JTextField();
        lbTelepon = new javax.swing.JLabel();
        lbEmail = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        lbAlm = new javax.swing.JLabel();
        spTfAlamat = new javax.swing.JScrollPane();
        tfAlamat = new javax.swing.JTextArea();
        lbRole = new javax.swing.JLabel();
        cxRole = new javax.swing.JComboBox<>();
        lbPin = new javax.swing.JLabel();
        tfPin = new javax.swing.JPasswordField();
        tfHarga = new javax.swing.JTextField();
        lbHarga = new javax.swing.JLabel();
        lbFoto = new javax.swing.JLabel();
        btnFoto = new javax.swing.JButton();
        lbFoto2 = new javax.swing.JLabel();
        tfJK = new javax.swing.JLabel();
        tfRole = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnSimpan = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(420, 600));
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

        lbTitle.setBackground(new java.awt.Color(255, 255, 255));
        lbTitle.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 16)); // NOI18N
        lbTitle.setForeground(view.Primary);
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Pengguna Baru");
        lbTitle.setOpaque(true);

        spMain.setBorder(null);
        spMain.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spMain.setToolTipText(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new Dimension(getWidth(),getHeight()));
        jPanel1.setRequestFocusEnabled(false);

        lbNama.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        lbNama.setText("Nama");
        lbNama.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        tfNama.setBackground(new java.awt.Color(255, 255, 255));
        tfNama.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        tfNama.setForeground(new java.awt.Color(50, 79, 83));
        tfNama.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 155, 154)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        tfNama.setDisabledTextColor(new java.awt.Color(50, 79, 83));
        tfNama.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        tfNama.setSelectionColor(view.Secondary);
        tfNama.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfFocusLost(evt);
            }
        });

        lbJK.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        lbJK.setText("Jenis Kelamin");
        lbJK.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        cxJK.setBackground(new java.awt.Color(255, 255, 255));
        cxJK.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        cxJK.setForeground(view.Primary);
        cxJK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-laki", "Perempuan" }));
        cxJK.setBorder(null);

        tfTelepon.setBackground(new java.awt.Color(255, 255, 255));
        tfTelepon.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        tfTelepon.setForeground(new java.awt.Color(50, 79, 83));
        tfTelepon.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 155, 154)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        tfTelepon.setDisabledTextColor(new java.awt.Color(50, 79, 83));
        tfTelepon.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        tfTelepon.setSelectionColor(view.Secondary);
        tfTelepon.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfFocusLost(evt);
            }
        });

        lbTelepon.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        lbTelepon.setText("Telepon");
        lbTelepon.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lbEmail.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        lbEmail.setText("Email");
        lbEmail.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        tfEmail.setBackground(new java.awt.Color(255, 255, 255));
        tfEmail.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        tfEmail.setForeground(new java.awt.Color(50, 79, 83));
        tfEmail.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 155, 154)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        tfEmail.setDisabledTextColor(new java.awt.Color(50, 79, 83));
        tfEmail.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        tfEmail.setSelectionColor(view.Secondary);
        tfEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfFocusLost(evt);
            }
        });

        lbAlm.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        lbAlm.setText("Alamat");
        lbAlm.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        spTfAlamat.setBorder(null);
        spTfAlamat.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        tfAlamat.setBackground(new java.awt.Color(255, 255, 255));
        tfAlamat.setColumns(20);
        tfAlamat.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        tfAlamat.setForeground(new java.awt.Color(50, 79, 83));
        tfAlamat.setLineWrap(true);
        tfAlamat.setRows(3);
        tfAlamat.setWrapStyleWord(true);
        tfAlamat.setAutoscrolls(false);
        tfAlamat.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 155, 154)), javax.swing.BorderFactory.createEmptyBorder(10, 5, 10, 5)));
        tfAlamat.setDisabledTextColor(new java.awt.Color(50, 79, 83));
        tfAlamat.setPreferredSize(new java.awt.Dimension(322, 90));
        tfAlamat.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        tfAlamat.setSelectionColor(view.Secondary);
        tfAlamat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfFocusLost(evt);
            }
        });
        spTfAlamat.setViewportView(tfAlamat);

        lbRole.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        lbRole.setText("Role");
        lbRole.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        cxRole.setBackground(new java.awt.Color(255, 255, 255));
        cxRole.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        cxRole.setForeground(view.Primary);
        cxRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Kasir", "Teknisi" }));
        cxRole.setBorder(null);

        lbPin.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        lbPin.setText("PIN");
        lbPin.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        tfPin.setBackground(new java.awt.Color(255, 255, 255));
        tfPin.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        tfPin.setForeground(new java.awt.Color(50, 79, 83));
        tfPin.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tfPin.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 155, 154)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        tfPin.setDisabledTextColor(new java.awt.Color(50, 79, 83));
        tfPin.setMargin(new java.awt.Insets(0, 0, 0, 0));
        tfPin.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        tfPin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfFocusLost(evt);
            }
        });

        tfHarga.setBackground(new java.awt.Color(255, 255, 255));
        tfHarga.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        tfHarga.setForeground(new java.awt.Color(50, 79, 83));
        tfHarga.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 155, 154)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5)));
        tfHarga.setDisabledTextColor(new java.awt.Color(50, 79, 83));
        tfHarga.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        tfHarga.setSelectionColor(view.Secondary);
        tfHarga.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfFocusLost(evt);
            }
        });

        lbHarga.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        lbHarga.setText("Harga");
        lbHarga.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lbFoto.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        lbFoto.setText("Foto");
        lbFoto.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        btnFoto.setBackground(view.Primary);
        btnFoto.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        btnFoto.setForeground(new java.awt.Color(255, 255, 255));
        btnFoto.setFocusPainted(false);
        btnFoto.setLabel("Pilih");
        btnFoto.setMaximumSize(new java.awt.Dimension(120, 30));
        btnFoto.setMinimumSize(new java.awt.Dimension(120, 30));
        btnFoto.setPreferredSize(new java.awt.Dimension(120, 30));
        btnFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFotoActionPerformed(evt);
            }
        });

        lbFoto2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/default_image.jpg"))); // NOI18N

        tfJK.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        tfJK.setForeground(new java.awt.Color(50, 79, 83));
        tfJK.setText("Laki-Laki");

        tfRole.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        tfRole.setForeground(new java.awt.Color(50, 79, 83));
        tfRole.setText("Laki-Laki");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbFoto2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfJK, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfPin)
                            .addComponent(cxJK, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNama, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfNama, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbJK, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTelepon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfTelepon, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfEmail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbAlm, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spTfAlamat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lbRole, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbPin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cxRole, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbHarga, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfHarga, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbFoto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfRole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(50, 50, 50))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lbFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbFoto2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(lbNama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lbJK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cxJK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tfJK, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lbTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tfTelepon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lbHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tfHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lbAlm, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(spTfAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cxRole, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tfRole, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lbPin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tfPin, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );

        spMain.setViewportView(jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

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

        btnClose.setBackground(view.Danger);
        btnClose.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 14)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setText("Tutup");
        btnClose.setFocusPainted(false);
        btnClose.setMaximumSize(new java.awt.Dimension(120, 30));
        btnClose.setMinimumSize(new java.awt.Dimension(120, 30));
        btnClose.setPreferredSize(new java.awt.Dimension(120, 30));
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(spMain)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(spMain, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void generateId(){
        idPegawai = "";
        idPelanggan = "";
        idBarang = "";
        String sIdPegawai = model.stringResult("Select id_pegawai from tbl_pegawai ORDER BY id_pegawai ASC");
        String sIdPelanggan = model.stringResult("Select id_pelanggan from tbl_pelanggan ORDER BY id_pelanggan ASC");
        String sIdBarang = model.stringResult("Select id_barang from tbl_barang ORDER BY id_barang ASC");
        

        if (sIdPegawai.isEmpty()){
            idPegawai="U001";
        }else if (sIdBarang.isEmpty()){
            idBarang="B001";
        }else if (sIdPelanggan.isEmpty()){
            idPelanggan="P0001";
        }
        
        else{
            for (int i = 1; i < 4; i++) {
                idPegawai += sIdPegawai.charAt(i);
                idBarang += sIdBarang.charAt(i);
            }
            for (int i = 1; i < 5; i++) {
                idPelanggan += sIdPelanggan.charAt(i);
            }

            int iIdBarang = Integer.parseInt(idBarang)+1;
            int iIdPelanggan = Integer.parseInt(idPelanggan)+1;
            int iIdPegawai = Integer.parseInt(idPegawai)+1;

            if(iIdBarang<10){
                idBarang= "B00"+iIdBarang;
                idPegawai= "U00"+iIdPegawai;
            }else if(iIdBarang<100){
                idBarang= "B0"+iIdBarang;
                idPegawai= "U0"+iIdPegawai;
            }else{
                idBarang= "B"+iIdBarang;
                idPegawai= "U"+iIdPegawai;
            }

            if(iIdPelanggan<10){
                idPelanggan= "P000"+iIdPelanggan;
            }else if(iIdPelanggan<100){
                idPelanggan= "P00"+iIdPelanggan;
            }else if(iIdPelanggan<1000){
                idPelanggan= "P0"+iIdPelanggan;
            }else{
                idPelanggan= "P"+iIdPelanggan;
            }
        }
    }
    
    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        isClose=false;
        String nama = tfNama.getText();
        String JK= (String) cxJK.getSelectedItem();
        String jenis = String.valueOf(cxJK.getSelectedIndex()+1);
        String tlp = tfTelepon.getText();
        String email = tfEmail.getText();
        String alm = tfAlamat.getText();
        String role = (String) cxRole.getSelectedItem();
        String pass = tfPin.getText();
        String harga = tfHarga.getText();

        if (idEdit==null) {
            if (menu.equals("Kelola Pegawai")) {
                if (nama.equals("")||JK.equals("")||tlp.equals("")||email.equals("")||alm.equals("")||role.equals("")||pass.equals("")) {
                    DialogInfo.showMsg(this, true, "Isi Semua Data", "Info", true);
                }else{
                    model.updOrIns("Insert into tbl_pegawai values ("
                            + "'"+idPegawai+"',"
                            + "'"+nama+"',"
                            + "'"+JK+"',"
                            + "'"+tlp+"',"
                            + "'"+email+"',"
                            + "'"+alm+"',"
                            + "'"+role+"',"
                            + "'"+pass+"')");
                        dispose();
                }
            }else if(menu.equals("Kelola Pelanggan")||menu.equals("Penyewaan")){
                if (nama.equals("")||tlp.equals("")||alm.equals("")) {
                    DialogInfo.showMsg(this, true, "Isi Semua Data", "Info", true);
                    System.out.println("salah");
                }else{
                    String tempIdPelanggan = model.stringResult("select id_pelanggan from tbl_pelanggan "
                            + "where nm_pelanggan='"+nama+"' and "
                            + "tlp_pelanggan='"+tlp+"' and "
                            + "alm_pelanggan='"+alm+"'");
                    if (!tempIdPelanggan.isEmpty()) {
                        idPelanggan = tempIdPelanggan;
                    }else{
                        model.updOrIns("Insert into tbl_pelanggan values ("
                                + "'"+idPelanggan+"',"
                                + "'"+nama+"',"
                                + "'"+tlp+"',"
                                + "'"+alm+"')");
                    }
                    dispose();
                    if (menu.equals("Penyewaan")) {
                        new DialogOrderBaru(parent, true,idPelanggan,idOrder).show();
                    }
                }
            }else if(menu.equals("Kelola Barang")){
                if (JK.equals("")||tlp.equals("")||email.equals("")||harga.equals("")) {
                    DialogInfo.showMsg(this, true, "Isi Semua Data", "Info", true);
                }else{
                    File baru=null;
                    if (f.getName().equals("default_image.jpg")) {
                        baru=f;
                    }else{
                        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
                        baru = new File("src\\img\\images\\"+sdf.format(new java.util.Date())+".png");
                        try {
                            Files.copy(f.toPath(), baru.toPath());
                        } catch (IOException e) {
                            
                        }
                    }
                    model.updOrIns("Insert into tbl_barang values ("
                            + "'"+idBarang+"',"
                            + ""+jenis+","
                            + "'"+tlp+"',"
                            + "'"+email+"',"
                            + ""+harga+","
                            + "'"+baru.getName()+"',"
                            + "'Tersedia')");   
                    dispose();

                }
                
            }
        }else{
            if (btnSimpan.getText().equals("Edit")) {
                enableAll();
            }else {
                String sql = "";
                switch (menu) {
                    case "Kelola Pegawai":
                        sql = "Update tbl_pegawai set nm_pegawai='"+nama+"',"
                                + "jk_pegawai='"+JK+"',"
                                + "tlp_pegawai='"+tlp+"',"
                                + "email_pegawai='"+email+"',"
                                + "alm_pegawai='"+alm+"',"
                                + "role_pegawai='"+role+"',"
                                + "pin_pegawai='"+pass+"' "
                                + "where id_pegawai ='"+idEdit+"'";
                        break;
                    case "Profile":
                        sql = "Update tbl_pegawai set nm_pegawai='"+nama+"',"
                                + "jk_pegawai='"+JK+"',"
                                + "tlp_pegawai='"+tlp+"',"
                                + "email_pegawai='"+email+"',"
                                + "alm_pegawai='"+alm+"',"
                                + "pin_pegawai='"+pass+"' "
                                + "where id_pegawai ='"+idEdit+"'";
                        break;
                    case "Kelola Barang":
                        File baru=null;
                        if (f.getName().equals("default_image.jpg")) {
                            baru=f;
                        }else{
                            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
                            baru = new File("src\\img\\images\\"+sdf.format(new java.util.Date())+".png");
                            try {
                                Files.copy(f.toPath(), baru.toPath());
                            } catch (IOException ex) {

                            }
                        }
                        sql = "Update tbl_barang set id_jenis="+jenis+","
                                + "merk='"+tlp+"',"
                                + "nm_barang='"+email+"',"
                                + "harga='"+harga+"',"
                                + "image='"+baru.getName()+"',"
                                + "status='"+role+"' "
                                + "where id_barang ='"+idEdit+"'";
                        System.out.println(sql);
                        break;
                    case "Kelola Pelanggan":
                        sql = "Update tbl_pelanggan set nm_pelanggan='"+nama+"',"
                                + "tlp_pelanggan='"+tlp+"',"
                                + "alm_pelanggan='"+alm+"' "
                                + "where id_pelanggan ='"+idEdit+"'";
                        break;
                        
                    default:
                        sql = "Update tbl_pelanggan set nm_pelanggan='"+nama+"',"
                                + "tlp_pelanggan='"+tlp+"',"
                                + "alm_pelanggan='"+alm+"' "
                                + "where id_pelanggan ='"+idEdit+"'";
                        break;
                }
                if (model.updOrIns(sql)&&!menu.equals("Penyewaan")) {
                    DialogInfo.showMsg(this, true, "Data Berhasil Diubah", "Info", true);   
                }
                
                if (menu.equals("Penyewaan")) {
                    dispose();
                    new DialogOrderBaru(parent, true,idEdit,idOrder).show();
                }else if(menu.equals("Profile")){
                    dispose();
                }else{
                    disableAll();
                }
            }

        }

    }//GEN-LAST:event_btnSimpanActionPerformed

    private void tfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfFocusGained
        if (evt.getSource()instanceof JTextField) {
            ((JTextField)evt.getSource()).setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(view.Primary),BorderFactory.createEmptyBorder(0,5,0,5)));
            

        }else{
            ((JTextArea)evt.getSource()).setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(view.Primary),BorderFactory.createEmptyBorder(5,5,5,5)));
            
        }
    }//GEN-LAST:event_tfFocusGained

    private void tfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfFocusLost
        if (evt.getSource()instanceof JTextField) {
            ((JTextField)evt.getSource()).setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(view.Secondary),BorderFactory.createEmptyBorder(0,5,0,5)));
         }else{
            ((JTextArea)evt.getSource()).setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(view.Secondary),BorderFactory.createEmptyBorder(5,5,5,5)));
        }
        
    }//GEN-LAST:event_tfFocusLost

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        System.out.println("id pelanggan = "+idPelanggan);
    }//GEN-LAST:event_formWindowOpened

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        if (btnClose.getText().equals("Tutup")) {
            dispose();
        }else{
            showAll();
            disableAll();
        }
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFotoActionPerformed
        JFileChooser openFileChooser = new JFileChooser();
        openFileChooser.setCurrentDirectory(new File("C:\\Users\\Kamisato Ayaka\\Pictures"));
        openFileChooser.setFileFilter(new FileNameExtensionFilter("Images","png","jpg","PNG","JPG"));
        int val = openFileChooser.showOpenDialog(this);
        if (val==JFileChooser.APPROVE_OPTION) {
            f = openFileChooser.getSelectedFile();
            ImageIcon ico = new ImageIcon(f.getAbsolutePath());
            
            Image img = ico.getImage();
            Image modImg=null;
            if (img.getHeight(null)>img.getWidth(null)) {
                 modImg = img.getScaledInstance(100, img.getHeight(null)/(img.getWidth(null)/100), Image.SCALE_SMOOTH);
            }else if (img.getHeight(null)<img.getWidth(null)) {
                 modImg = img.getScaledInstance(img.getWidth(null)/(img.getHeight(null)/100),100, Image.SCALE_SMOOTH);
            }else{
                 modImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            }
            
            lbFoto2.setIcon(new ImageIcon(modImg));  
        }else{
            f = new File("src\\img\\images\\default_image.jpg");
        }
    }//GEN-LAST:event_btnFotoActionPerformed

    private void formClose(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formClose
        if (isClose) {
            if (menu.equals("Penyewaan")) {
                model.updOrIns("delete from tbl_order where id_order = '"+idOrder+"'");
                model.updOrIns("delete from tbl_pelanggan where id_pelanggan = '"+idEdit+"'");
            }
        }
    }//GEN-LAST:event_formClose

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DialogEditBaru dialog = new DialogEditBaru(new javax.swing.JFrame(), true,menu,idEdit,idOrder);
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
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnFoto;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cxJK;
    private javax.swing.JComboBox<String> cxRole;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbAlm;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbFoto;
    private javax.swing.JLabel lbFoto2;
    private javax.swing.JLabel lbHarga;
    private javax.swing.JLabel lbJK;
    private javax.swing.JLabel lbNama;
    private javax.swing.JLabel lbPin;
    private javax.swing.JLabel lbRole;
    private javax.swing.JLabel lbTelepon;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JScrollPane spMain;
    private javax.swing.JScrollPane spTfAlamat;
    private javax.swing.JTextArea tfAlamat;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfHarga;
    private javax.swing.JLabel tfJK;
    private javax.swing.JTextField tfNama;
    private javax.swing.JPasswordField tfPin;
    private javax.swing.JLabel tfRole;
    private javax.swing.JTextField tfTelepon;
    // End of variables declaration//GEN-END:variables
}
