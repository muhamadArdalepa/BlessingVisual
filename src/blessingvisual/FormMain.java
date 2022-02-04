package blessingvisual;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


/**
 *
 * @author Kamisato Ayaka
 */
public class FormMain extends javax.swing.JFrame {
    public static String username;
    static DefaultTableModel dtmPenyewaan;
    static DefaultTableModel dtmKelolaPegawai;
    static DefaultTableModel dtmKelolaPelanggan;
    TableColumn tcrPenyewaan,tchPenyewaan;
    TableColumn tcrKelolaPelanggan,tchKelolaPelanggan;
    TableColumn tcrKelolaPegawai,tchKelolaPegawai;
    static String[][] arrDataPenyewaan;
    static String[][] arrDataKelolaBarang;
    static String[][] arrDataKelolaPegawai;
    static String[][] arrDataKelolaPelanggan;
    static Object[][] arrObjectKelolaBarang;
    private JButton btnSelectedSidebar;
    static String[] arrUser = {"U000","Admin","Laki-laki","080000000000","Admin@localhost","Localhost","Admin","Admin"};
    
    public FormMain(String[] arrUser) {
        initComponents();
        this.arrUser=arrUser;
        if(!arrUser[6].equals("Admin")){
            btnKelolaPegawai.setVisible(false);
            btnKelolaPelanggan.setVisible(false);
            jLabel4.setVisible(false);
            btnKelolaBarang.setVisible(false);
        }else{
            btnKelolaPegawai.setVisible(true);
            btnKelolaPelanggan.setVisible(true);
            jLabel4.setVisible(true);
            btnKelolaBarang.setVisible(true);
        }
        dtmPenyewaan = (DefaultTableModel)tblPenyewaan.getModel();
        dtmKelolaPegawai = (DefaultTableModel)tblKelolaPegawai.getModel();
        dtmKelolaPelanggan = (DefaultTableModel)tblKelolaPelanggan.getModel();
        
        tcrPenyewaan=tblPenyewaan.getColumnModel().getColumn(6);
        tcrPenyewaan.setCellRenderer(new ColumnColorRenderer(view.Tertiary));
        tchPenyewaan=tblPenyewaan.getColumnModel().getColumn(7);
        tchPenyewaan.setCellRenderer(new ColumnColorRenderer(view.Danger));
        
        tcrKelolaPegawai=tblKelolaPegawai.getColumnModel().getColumn(5);
        tcrKelolaPegawai.setCellRenderer(new ColumnColorRenderer(view.Tertiary));
        tchKelolaPegawai=tblKelolaPegawai.getColumnModel().getColumn(6);
        tchKelolaPegawai.setCellRenderer(new ColumnColorRenderer(view.Danger));
        
        tcrKelolaPelanggan=tblKelolaPelanggan.getColumnModel().getColumn(4);
        tcrKelolaPelanggan.setCellRenderer(new ColumnColorRenderer(view.Tertiary));
        tchKelolaPelanggan=tblKelolaPelanggan.getColumnModel().getColumn(5);
        tchKelolaPelanggan.setCellRenderer(new ColumnColorRenderer(view.Danger));
        refresh();
    }
    public void refresh(){
        showPenyewaan(tfSearch.getText());
        showProfile();
        showKelolaBarang(tfSearch.getText());
        showKelolaPegawai(tfSearch.getText());
        showKelolaPelanggan(tfSearch.getText());
        showDashboard();
    }
    public static void showPenyewaan(String search){
        if (search.equals("Cari")) {
            search="";
        }
        String sql = "select id_order,nm_pelanggan,tgl_order,tlp_pelanggan, "
                + "status,total_bayar,'Return','Hapus' from tbl_order join "
                + "tbl_pelanggan using(id_pelanggan) where nm_pelanggan like'%"+search+"%'";

        arrDataPenyewaan = model.arr2Result(sql);
        dtmPenyewaan.setRowCount(0);
        dtmPenyewaan.fireTableDataChanged();
        for (int i = 0; i < arrDataPenyewaan.length; i++) {
                arrDataPenyewaan[i][5] = NumberFormat.getCurrencyInstance().format(Float.parseFloat(arrDataPenyewaan[i][5]));
            try {
                arrDataPenyewaan[i][2] = view.viewFormat.format(view.dbFormat.parse(arrDataPenyewaan[i][2]));
            } catch (ParseException e) {
                System.out.println(e);
            }
            dtmPenyewaan.addRow(arrDataPenyewaan[i]);
        }
    }
    
    public static void showKelolaPegawai(String search){
        if (search.equals("Cari")) {
            search="";
        }
        String sql = "select id_pegawai,nm_pegawai,jk_pegawai,tlp_pegawai,role_pegawai,'Rincian','Hapus' "
                + "from tbl_pegawai where nm_pegawai like'%"+search+"%'";
        arrDataKelolaPegawai = model.arr2Result(sql);
        dtmKelolaPegawai.setRowCount(0);
        dtmKelolaPegawai.fireTableDataChanged();
        for (int i = 0; i < arrDataKelolaPegawai.length; i++) {
            dtmKelolaPegawai.addRow(arrDataKelolaPegawai[i]);
        }
    }
    public static void showKelolaPelanggan(String search){
        if (search.equals("Cari")) {
            search="";
        }
        String sql = "select id_Pelanggan,nm_pelanggan,tlp_pelanggan,alm_pelanggan,'Rincian','Hapus' "
                + "from tbl_pelanggan  where nm_pelanggan like'%"+search+"%' order by nm_pelanggan";
        arrDataKelolaPelanggan = model.arr2Result(sql);
        dtmKelolaPelanggan.setRowCount(0);
        dtmKelolaPelanggan.fireTableDataChanged();
        for (int i = 0; i < arrDataKelolaPelanggan.length; i++) {
            dtmKelolaPelanggan.addRow(arrDataKelolaPelanggan[i]);
        }
    }
    
    public void showProfile(){
        arrUser=model.arrResult("select * from tbl_pegawai where id_pegawai='"+arrUser[0]+"'");
        lbRoleUserProfile.setText(arrUser[6]);
        tfNamaUserProfile.setText(arrUser[1]);
        lbJKUserProfile.setText(arrUser[2]);
        tfTlpUserProfile.setText(arrUser[3]);
        tfEmailUserProfile.setText(arrUser[4]);
        taAlmUserProfile.setText(arrUser[5]);
        lbSessUser.setText(arrUser[1]);
        String nPenyewaan = model.stringResult("Select count(*) from tbl_order where id_pegawai_order = '"+arrUser[0]+"'");
        String nPengembalian = model.stringResult("Select count(*) from tbl_order where id_pegawai_kembali = '"+arrUser[0]+"'");
        
        lbNPenyewaan.setText(nPenyewaan);
        lbNPengembalian.setText(nPengembalian);
    }
    void rincianBarangActionPerformed(ActionEvent e){
        new DialogEditBaru(this, true, "Kelola Barang", ((JButton)e.getSource()).getName(), null).show();
    }
    void hapusBarangActionPerformed(ActionEvent e){
        int conf = DialogInfo.showPrompt(this, true,"Hapus "+(model.stringResult("select nm_barang from tbl_barang where id_barang='"+((JButton)e.getSource()).getName()+"'")) , "info", false);
        if (conf==1) {
            if(model.updOrIns("delete from tbl_barang where id_barang='"+((JButton)e.getSource()).getName()+"'")){
                DialogInfo.showMsg(this, true, "Hapus Barang Berhasil", "Info", true);
            }
        }
    }
    public void showKelolaBarang(String search){
        if (search.equals("Cari")) {
            search="";
        }
        panMainKelolaBarang.removeAll();
        panMainKelolaBarang.repaint();
        panMainKelolaBarang.revalidate();
        String query = "select id_barang,merk, nm_barang, jenis, harga, image, status "
                + "from tbl_barang join tbl_jenis using(id_jenis) where nm_barang like'%"+search+"%' or merk like'%"+search+"%'";
        arrDataKelolaBarang = model.arr2Result(query);
        arrObjectKelolaBarang = new Object[arrDataKelolaBarang.length][7];
        for (int i = 0; i < arrDataKelolaBarang.length; i++) {
            arrObjectKelolaBarang[i][0] = new JPanel();
            ((JPanel)arrObjectKelolaBarang[i][0]).setLayout(null);
            ((JPanel)arrObjectKelolaBarang[i][0]).setBackground(view.Body);
            
            arrObjectKelolaBarang[i][1]= new JLabel();
            ((JLabel)arrObjectKelolaBarang[i][1]).setIcon(new ImageIcon(view.showIcon(arrDataKelolaBarang[i][5])));
            for (int j = 0; j < 2; j++) {
                arrObjectKelolaBarang[i][j+2] = new JLabel();
                ((JLabel)arrObjectKelolaBarang[i][j+2]).setFont(view.segoe(0, 12));
                ((JLabel)arrObjectKelolaBarang[i][j+2]).setHorizontalAlignment(SwingConstants.CENTER);
                ((JLabel)arrObjectKelolaBarang[i][j+2]).setText((String) arrDataKelolaBarang[i][j+1]);
            }
            arrObjectKelolaBarang[i][4] = new JButton();
            ((JButton)arrObjectKelolaBarang[i][4]).setBackground(view.Primary);
            ((JButton)arrObjectKelolaBarang[i][4]).setFont(view.segoe(0, 12));
            ((JButton)arrObjectKelolaBarang[i][4]).setForeground(Color.white);
            ((JButton)arrObjectKelolaBarang[i][4]).setText("Rincian");
            ((JButton)arrObjectKelolaBarang[i][4]).setBorder(null);
            ((JButton)arrObjectKelolaBarang[i][4]).setName(arrDataKelolaBarang[i][0]);
            ((JButton)arrObjectKelolaBarang[i][4]).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    rincianBarangActionPerformed(e);
                }
            });
            ((JButton)arrObjectKelolaBarang[i][4]).setBorderPainted(false);
            ((JButton)arrObjectKelolaBarang[i][4]).setFocusPainted(false);
            
            arrObjectKelolaBarang[i][5] = new JButton();
            ((JButton)arrObjectKelolaBarang[i][5]).setBackground(view.Danger);
            ((JButton)arrObjectKelolaBarang[i][5]).setFont(view.segoe(0, 12));
            ((JButton)arrObjectKelolaBarang[i][5]).setForeground(Color.white);
            ((JButton)arrObjectKelolaBarang[i][5]).setText("");
            ((JButton)arrObjectKelolaBarang[i][5]).setIcon(new ImageIcon(getClass().getResource("/img/icons/delete.png")));
            ((JButton)arrObjectKelolaBarang[i][5]).setBorder(null);
            ((JButton)arrObjectKelolaBarang[i][5]).setContentAreaFilled(false);
            
            ((JButton)arrObjectKelolaBarang[i][5]).setName(arrDataKelolaBarang[i][0]);
            ((JButton)arrObjectKelolaBarang[i][5]).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hapusBarangActionPerformed(e);
                }
            });
            ((JButton)arrObjectKelolaBarang[i][5]).setBorderPainted(false);
            ((JButton)arrObjectKelolaBarang[i][5]).setFocusPainted(false);
            
            for (int j = 1; j < 4; j++) {
                ((JPanel)arrObjectKelolaBarang[i][0]).add(((JLabel)arrObjectKelolaBarang[i][j]));
            }
            ((JPanel)arrObjectKelolaBarang[i][0]).add(((JButton)arrObjectKelolaBarang[i][4]));
            ((JPanel)arrObjectKelolaBarang[i][0]).add(((JButton)arrObjectKelolaBarang[i][5]));
            
            ((JPanel)arrObjectKelolaBarang[i][0]).add(((JLabel)arrObjectKelolaBarang[i][1]));
            
            panMainKelolaBarang.add((JPanel)arrObjectKelolaBarang[i][0]);
        }
        boundingKelolaBarang();
        panMainKelolaBarang.setPreferredSize(new Dimension(panMainKelolaBarang.getWidth(), Math.round((arrDataKelolaBarang.length+6)/6)*220));
    }
    
    public void showDashboard(){
        lbTotalBarang.setText(model.stringResult("select count(id_barang) from tbl_barang"));
        lbTotalPelanggan.setText(model.stringResult("select count(id_pelanggan) from tbl_pelanggan"));
        lbTotalPegawai.setText(model.stringResult("select count(id_pegawai) from tbl_pegawai"));
        lbTotalPenyewaan.setText(model.stringResult("select count(id_order) from tbl_order"));
        
        lbNmTopRank1.setText("");
        lbNmTopRank2.setText("");
        lbNmTopRank3.setText("");
        lbNOrderRank1.setText("");
        lbNOrderRank2.setText("");
        lbNOrderRank3.setText("");
        rank.setVisible(true);
        rank2.setVisible(true);
        rank3.setVisible(true);
        String[][] dataTopPelanggan = model.arr2Result("SELECT nm_pelanggan, abs(COUNT(id_pelanggan)) "
                +" as total FROM tbl_order JOIN tbl_pelanggan USING(id_pelanggan)"
                +" GROUP BY id_pelanggan ORDER BY total DESC LIMIT 3");
        if (dataTopPelanggan.length==3) {
            lbNmTopRank1.setText(dataTopPelanggan[0][0]);
            lbNmTopRank2.setText(dataTopPelanggan[1][0]);
            lbNmTopRank3.setText(dataTopPelanggan[2][0]);
            lbNOrderRank1.setText(dataTopPelanggan[0][1]+"x");
            lbNOrderRank2.setText(dataTopPelanggan[1][1]+"x");
            lbNOrderRank3.setText(dataTopPelanggan[2][1]+"x");
        }else if(dataTopPelanggan.length==2){
            lbNmTopRank1.setText(dataTopPelanggan[0][0]);
            lbNmTopRank2.setText(dataTopPelanggan[1][0]);
            lbNOrderRank1.setText(dataTopPelanggan[0][1]+"x");
            lbNOrderRank2.setText(dataTopPelanggan[1][1]+"x");
            rank3.setVisible(false);
        }else if(dataTopPelanggan.length==1){
            lbNmTopRank1.setText(dataTopPelanggan[0][0]);
            lbNOrderRank1.setText(dataTopPelanggan[0][1]+"x");           
            rank2.setVisible(false);
            rank3.setVisible(false);
        }else{
            rank.setVisible(false);
        }
        
        
        lbImageTopBarang1.setIcon(new ImageIcon(getClass().getResource("/img/images/default_image.jpg")));
        lbImageTopBarang2.setIcon(new ImageIcon(getClass().getResource("/img/images/default_image.jpg")));
        lbMerkTopBarang1.setText("");
        lbMerkTopBarang2.setText("");
        lbNmTopBarang1.setText("");
        lbNmTopBarang2.setText("");
        lbNOrderTopBarang1.setText("");
        lbNOrderTopBarang1.setText("");
        panTopBarang.setVisible(true);
        panTopBarangItem2.setVisible(true);
        String[][] dataTopBarang = model.arr2Result("SELECT image,merk,nm_barang, abs(COUNT(id_barang)) "
                +" as total FROM tbl_detail_order JOIN tbl_barang USING(id_barang)"
                +" GROUP BY id_barang ORDER BY total DESC LIMIT 2");
        if (dataTopBarang.length==2) {            
            lbImageTopBarang1.setIcon(new ImageIcon(view.showIcon(dataTopBarang[0][0])));
            lbImageTopBarang2.setIcon(new ImageIcon(view.showIcon(dataTopBarang[1][0])));
            lbMerkTopBarang1.setText(dataTopBarang[0][2]);
            lbMerkTopBarang2.setText(dataTopBarang[1][2]);
            lbNmTopBarang1.setText(dataTopBarang[0][1]);
            lbNmTopBarang2.setText(dataTopBarang[1][1]);
            lbNOrderTopBarang1.setText(dataTopBarang[0][3]+"x Diorder");
            lbNOrderTopBarang2.setText(dataTopBarang[1][3]+"x Diorder");
        }else if(dataTopBarang.length==1){
            lbImageTopBarang1.setIcon(new ImageIcon(view.showIcon(dataTopBarang[0][0])));
            lbMerkTopBarang1.setText(dataTopBarang[0][2]);
            lbNmTopBarang1.setText(dataTopBarang[0][1]);
            lbNOrderTopBarang1.setText(dataTopBarang[0][3]);
            panTopBarangItem2.setVisible(false);
        }else{
            panTopBarang.setVisible(false);
        }
        
    }
    
    public void boundingKelolaBarang(){
        int x=0,y=0;
        for (int i = 0; i < arrObjectKelolaBarang.length; i++) {
            if (x==0 && y==0) {
                ((JPanel)arrObjectKelolaBarang[i][0]).setBounds(x,y,150,200);
            }else{
                if(y==0){
                    ((JPanel)arrObjectKelolaBarang[i][0]).setBounds(x*150+x*(panMainKelolaBarang.getWidth()-150*6)/5,y*200,150,200);
                }else{
                    ((JPanel)arrObjectKelolaBarang[i][0]).setBounds(x*150+x*(panMainKelolaBarang.getWidth()-150*6)/5,y*240,150,200);
                }
                
            }    
            if((i+1)%6==0){
                y++;
                x=0;
            }else{
                x++;
            }
            ((JLabel)arrObjectKelolaBarang[i][1]).setBounds(25,10,100,100);
            for (int j = 0; j < 2; j++) {
                ((JLabel)arrObjectKelolaBarang[i][j+2]).setBounds(10,120+(15*j),130,15);
            }
            ((JButton)arrObjectKelolaBarang[i][4]).setBounds(25,170,75,20);
            ((JButton)arrObjectKelolaBarang[i][5]).setBounds(150-25-20,170,20,20);
        }
    }
    
    public void deselectAllMenu(){
        btnDashboard.setBorder(BorderFactory.createCompoundBorder(null, BorderFactory.createEmptyBorder(0, 50, 0, 0)));
        btnPenyewaan.setBorder(BorderFactory.createCompoundBorder(null, BorderFactory.createEmptyBorder(0, 50, 0, 0)));
        btnProfile.setBorder(BorderFactory.createCompoundBorder(null, BorderFactory.createEmptyBorder(0, 50, 0, 0)));
        btnKelolaPegawai.setBorder(BorderFactory.createCompoundBorder(null, BorderFactory.createEmptyBorder(0, 50, 0, 0)));
        btnKelolaPelanggan.setBorder(BorderFactory.createCompoundBorder(null, BorderFactory.createEmptyBorder(0, 50, 0, 0)));
        btnKelolaBarang.setBorder(BorderFactory.createCompoundBorder(null, BorderFactory.createEmptyBorder(0, 50, 0, 0)));
        btnLogout.setBorder(BorderFactory.createCompoundBorder(null, BorderFactory.createEmptyBorder(0, 50, 0, 0)));
        
        panDashboard.setVisible(false);
        panPenyewaan.setVisible(false);
        panProfile.setVisible(false);
        panKelolaBarang.setVisible(false);
        panKelolaPelanggan.setVisible(false);
        panKelolaPegawai.setVisible(false);
    }
    
    public void unHoverAllSidebarBtn(){
        ArrayList<JButton> arrLiSidebarBtn = new ArrayList<>();
        arrLiSidebarBtn.add(btnDashboard);
        arrLiSidebarBtn.add(btnPenyewaan);
        arrLiSidebarBtn.add(btnProfile);
        arrLiSidebarBtn.add(btnKelolaBarang);
        arrLiSidebarBtn.add(btnKelolaPegawai);
        arrLiSidebarBtn.add(btnKelolaPelanggan);
        arrLiSidebarBtn.add(btnLogout);
        arrLiSidebarBtn.remove(btnSelectedSidebar);
        for (int i = 0; i < arrLiSidebarBtn.size(); i++) {
            arrLiSidebarBtn.get(i).setBackground(view.Primary);
        }
    }

    public void selectMenu(JButton btn, JPanel pan){
        deselectAllMenu();
        btn.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.white), BorderFactory.createEmptyBorder(0, 45, 0, 0)));
        btn.setBackground(view.MenuRollover);
        pan.setVisible(true);
        btnSelectedSidebar=btn;
        unHoverAllSidebarBtn();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbSessUser = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnLogout = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        btnPenyewaan = new javax.swing.JButton();
        btnProfile = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnKelolaPegawai = new javax.swing.JButton();
        btnKelolaPelanggan = new javax.swing.JButton();
        btnKelolaBarang = new javax.swing.JButton();
        lbTitle = new javax.swing.JLabel();
        panTopTool = new javax.swing.JPanel();
        tfSearch = new javax.swing.JTextField();
        lbSearch = new javax.swing.JLabel();
        btnNew = new javax.swing.JButton();
        panMain = new javax.swing.JPanel();
        panPenyewaan = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPenyewaan = new javax.swing.JTable();
        panDashboard = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbTotalPenyewaan = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lbTotalPelanggan = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lbTotalPegawai = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lbTotalBarang = new javax.swing.JLabel();
        rank = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        rank1 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        lbNmTopRank1 = new javax.swing.JLabel();
        lbNOrderRank1 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        rank2 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        lbNmTopRank2 = new javax.swing.JLabel();
        lbNOrderRank2 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        rank3 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        lbNmTopRank3 = new javax.swing.JLabel();
        lbNOrderRank3 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        panTopBarang = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        lbImageTopBarang1 = new javax.swing.JLabel();
        lbNmTopBarang1 = new javax.swing.JLabel();
        lbMerkTopBarang1 = new javax.swing.JLabel();
        lbNOrderTopBarang1 = new javax.swing.JLabel();
        panTopBarangItem2 = new javax.swing.JPanel();
        lbImageTopBarang2 = new javax.swing.JLabel();
        lbNmTopBarang2 = new javax.swing.JLabel();
        lbMerkTopBarang2 = new javax.swing.JLabel();
        lbNOrderTopBarang2 = new javax.swing.JLabel();
        panProfile = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lbRoleUserProfile = new javax.swing.JLabel();
        tfNamaUserProfile = new javax.swing.JTextField();
        btnEditProfile = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        lbJKUserProfile = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tfTlpUserProfile = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tfEmailUserProfile = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        taAlmUserProfile = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        lbNPenyewaan = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbNPengembalian = new javax.swing.JLabel();
        panKelolaPegawai = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblKelolaPegawai = new javax.swing.JTable();
        panKelolaPelanggan = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblKelolaPelanggan = new javax.swing.JTable();
        panKelolaBarang = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        panMainKelolaBarang = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setName("LoginForm"); // NOI18N
        setSize(new java.awt.Dimension(1280, 720));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(231, 230, 225));

        jPanel3.setBackground(new java.awt.Color(242, 161, 85));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/Blessing VISUAL.png"))); // NOI18N

        lbSessUser.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        lbSessUser.setForeground(new java.awt.Color(255, 255, 255));
        lbSessUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbSessUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/Profile.png"))); // NOI18N
        lbSessUser.setText("Admin");
        lbSessUser.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lbSessUser.setIconTextGap(10);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbSessUser)
                .addGap(50, 50, 50))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbSessUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(50, 79, 83));
        jPanel2.setPreferredSize(new java.awt.Dimension(260, 100));

        btnLogout.setBackground(new java.awt.Color(50, 79, 83));
        btnLogout.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/logout.png"))); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEmptyBorder(0, 50, 0, 0)));
        btnLogout.setDefaultCapable(false);
        btnLogout.setFocusPainted(false);
        btnLogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLogout.setIconTextGap(10);
        btnLogout.setRequestFocusEnabled(false);
        btnLogout.setRolloverEnabled(false);
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSidebarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSidebarMouseExited(evt);
            }
        });
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Menu");

        btnDashboard.setBackground(new java.awt.Color(50, 79, 83));
        btnDashboard.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/dashboard.png"))); // NOI18N
        btnDashboard.setText("Dashboard");
        btnDashboard.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEmptyBorder(0, 50, 0, 0)));
        btnDashboard.setDefaultCapable(false);
        btnDashboard.setFocusPainted(false);
        btnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDashboard.setIconTextGap(10);
        btnDashboard.setRequestFocusEnabled(false);
        btnDashboard.setRolloverEnabled(false);
        btnDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSidebarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSidebarMouseExited(evt);
            }
        });
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
                btnSideAP(evt);
            }
        });

        btnPenyewaan.setBackground(new java.awt.Color(50, 79, 83));
        btnPenyewaan.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        btnPenyewaan.setForeground(new java.awt.Color(255, 255, 255));
        btnPenyewaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/Penyewaan.png"))); // NOI18N
        btnPenyewaan.setText("Penyewaan");
        btnPenyewaan.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEmptyBorder(0, 50, 0, 0)));
        btnPenyewaan.setDefaultCapable(false);
        btnPenyewaan.setFocusPainted(false);
        btnPenyewaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPenyewaan.setIconTextGap(10);
        btnPenyewaan.setRequestFocusEnabled(false);
        btnPenyewaan.setRolloverEnabled(false);
        btnPenyewaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSidebarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSidebarMouseExited(evt);
            }
        });
        btnPenyewaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenyewaanActionPerformed(evt);
                btnSideAP(evt);
            }
        });

        btnProfile.setBackground(new java.awt.Color(50, 79, 83));
        btnProfile.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        btnProfile.setForeground(new java.awt.Color(255, 255, 255));
        btnProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/Profile.png"))); // NOI18N
        btnProfile.setText("Profile");
        btnProfile.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEmptyBorder(0, 50, 0, 0)));
        btnProfile.setDefaultCapable(false);
        btnProfile.setFocusPainted(false);
        btnProfile.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnProfile.setIconTextGap(10);
        btnProfile.setRequestFocusEnabled(false);
        btnProfile.setRolloverEnabled(false);
        btnProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSidebarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSidebarMouseExited(evt);
            }
        });
        btnProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfileActionPerformed(evt);
                btnSideAP(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Montserrat Medium", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Admin");

        btnKelolaPegawai.setBackground(new java.awt.Color(50, 79, 83));
        btnKelolaPegawai.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        btnKelolaPegawai.setForeground(new java.awt.Color(255, 255, 255));
        btnKelolaPegawai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/kelola pegawai.png"))); // NOI18N
        btnKelolaPegawai.setText("Kelola Pegawai");
        btnKelolaPegawai.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEmptyBorder(0, 50, 0, 0)));
        btnKelolaPegawai.setDefaultCapable(false);
        btnKelolaPegawai.setFocusPainted(false);
        btnKelolaPegawai.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKelolaPegawai.setIconTextGap(10);
        btnKelolaPegawai.setRequestFocusEnabled(false);
        btnKelolaPegawai.setRolloverEnabled(false);
        btnKelolaPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSidebarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSidebarMouseExited(evt);
            }
        });
        btnKelolaPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKelolaPegawaiActionPerformed(evt);
                btnSideAP(evt);
            }
        });

        btnKelolaPelanggan.setBackground(new java.awt.Color(50, 79, 83));
        btnKelolaPelanggan.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        btnKelolaPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        btnKelolaPelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/kelola pelanggan.png"))); // NOI18N
        btnKelolaPelanggan.setText("Kelola Pelanggan");
        btnKelolaPelanggan.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEmptyBorder(0, 50, 0, 0)));
        btnKelolaPelanggan.setDefaultCapable(false);
        btnKelolaPelanggan.setFocusPainted(false);
        btnKelolaPelanggan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKelolaPelanggan.setIconTextGap(10);
        btnKelolaPelanggan.setRequestFocusEnabled(false);
        btnKelolaPelanggan.setRolloverEnabled(false);
        btnKelolaPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSidebarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSidebarMouseExited(evt);
            }
        });
        btnKelolaPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKelolaPelangganActionPerformed(evt);
                btnSideAP(evt);
            }
        });

        btnKelolaBarang.setBackground(new java.awt.Color(50, 79, 83));
        btnKelolaBarang.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        btnKelolaBarang.setForeground(new java.awt.Color(255, 255, 255));
        btnKelolaBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/Daftar Barang.png"))); // NOI18N
        btnKelolaBarang.setText("Kelola Barang");
        btnKelolaBarang.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEmptyBorder(0, 50, 0, 0)));
        btnKelolaBarang.setDefaultCapable(false);
        btnKelolaBarang.setFocusPainted(false);
        btnKelolaBarang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnKelolaBarang.setIconTextGap(10);
        btnKelolaBarang.setRequestFocusEnabled(false);
        btnKelolaBarang.setRolloverEnabled(false);
        btnKelolaBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSidebarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSidebarMouseExited(evt);
            }
        });
        btnKelolaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKelolaBarangActionPerformed(evt);
                btnSideAP(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPenyewaan, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
            .addComponent(btnKelolaBarang, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
            .addComponent(btnProfile, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
            .addComponent(btnKelolaPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnKelolaPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addGap(0, 0, 0)
                .addComponent(btnDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnPenyewaan, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addGap(0, 0, 0)
                .addComponent(btnKelolaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnKelolaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnKelolaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(300, 300, 300)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbTitle.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(140, 155, 154));
        lbTitle.setText("Dashboard");

        panTopTool.setBackground(new java.awt.Color(255, 255, 255));

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
                tfSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfSearchFocusLost(evt);
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
        lbSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbSearchMouseClicked(evt);
            }
        });

        btnNew.setBackground(new java.awt.Color(50, 79, 83));
        btnNew.setFont(new java.awt.Font("Montserrat Medium", 1, 12)); // NOI18N
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setText("Baru");
        btnNew.setBorder(null);
        btnNew.setFocusPainted(false);
        btnNew.setMaximumSize(new java.awt.Dimension(30, 120));
        btnNew.setMinimumSize(new java.awt.Dimension(30, 120));
        btnNew.setPreferredSize(new java.awt.Dimension(30, 120));
        btnNew.setRolloverEnabled(false);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panTopToolLayout = new javax.swing.GroupLayout(panTopTool);
        panTopTool.setLayout(panTopToolLayout);
        panTopToolLayout.setHorizontalGroup(
            panTopToolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panTopToolLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        panTopToolLayout.setVerticalGroup(
            panTopToolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panTopToolLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panTopToolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        panMain.setLayout(new java.awt.CardLayout());

        panPenyewaan.setBackground(new java.awt.Color(255, 255, 255));

        tblPenyewaan.setAutoCreateRowSorter(true);
        tblPenyewaan.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        tblPenyewaan.setForeground(new java.awt.Color(50, 79, 83));
        tblPenyewaan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "OrderId", "Pelanggan", "Tanggal Order", "Telepon", "Status", "Total Bayar", "Rincian", "Hapus"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPenyewaan.setColumnSelectionAllowed(true);
        tblPenyewaan.setFocusable(false);
        tblPenyewaan.setGridColor(new java.awt.Color(140, 155, 154));
        tblPenyewaan.setIntercellSpacing(new java.awt.Dimension(30, 0));
        tblPenyewaan.setRowHeight(50);
        tblPenyewaan.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tblPenyewaan.setSelectionForeground(view.Primary);
        tblPenyewaan.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPenyewaan.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPenyewaan.setShowGrid(true);
        tblPenyewaan.getTableHeader().setResizingAllowed(false);
        tblPenyewaan.getTableHeader().setReorderingAllowed(false);
        tblPenyewaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPenyewaanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPenyewaan);
        tblPenyewaan.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tblPenyewaan.getColumnModel().getColumnCount() > 0) {
            tblPenyewaan.getColumnModel().getColumn(0).setMinWidth(0);
            tblPenyewaan.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblPenyewaan.getColumnModel().getColumn(0).setMaxWidth(0);
            tblPenyewaan.getColumnModel().getColumn(1).setResizable(false);
            tblPenyewaan.getColumnModel().getColumn(2).setMinWidth(150);
            tblPenyewaan.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblPenyewaan.getColumnModel().getColumn(2).setMaxWidth(150);
            tblPenyewaan.getColumnModel().getColumn(3).setMinWidth(150);
            tblPenyewaan.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblPenyewaan.getColumnModel().getColumn(3).setMaxWidth(150);
            tblPenyewaan.getColumnModel().getColumn(4).setMinWidth(120);
            tblPenyewaan.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblPenyewaan.getColumnModel().getColumn(4).setMaxWidth(120);
            tblPenyewaan.getColumnModel().getColumn(5).setMinWidth(200);
            tblPenyewaan.getColumnModel().getColumn(5).setPreferredWidth(200);
            tblPenyewaan.getColumnModel().getColumn(5).setMaxWidth(200);
            tblPenyewaan.getColumnModel().getColumn(6).setMinWidth(100);
            tblPenyewaan.getColumnModel().getColumn(6).setPreferredWidth(100);
            tblPenyewaan.getColumnModel().getColumn(6).setMaxWidth(100);
            tblPenyewaan.getColumnModel().getColumn(7).setMinWidth(100);
            tblPenyewaan.getColumnModel().getColumn(7).setPreferredWidth(100);
            tblPenyewaan.getColumnModel().getColumn(7).setMaxWidth(100);
        }

        javax.swing.GroupLayout panPenyewaanLayout = new javax.swing.GroupLayout(panPenyewaan);
        panPenyewaan.setLayout(panPenyewaanLayout);
        panPenyewaanLayout.setHorizontalGroup(
            panPenyewaanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panPenyewaanLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1082, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        panPenyewaanLayout.setVerticalGroup(
            panPenyewaanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panPenyewaanLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1118, Short.MAX_VALUE))
        );

        panMain.add(panPenyewaan, "card2");

        panDashboard.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setBorder(null);
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(50, 79, 83));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/Penyewaan2.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total Penyewaan");

        lbTotalPenyewaan.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 48)); // NOI18N
        lbTotalPenyewaan.setForeground(new java.awt.Color(255, 255, 255));
        lbTotalPenyewaan.setText("123");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTotalPenyewaan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTotalPenyewaan)
                        .addGap(38, 38, 38))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(30, 30, 30))))
        );

        jPanel8.setBackground(new java.awt.Color(50, 79, 83));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/kelola pelanggan1.png"))); // NOI18N

        jLabel19.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Total Pelanggan");

        lbTotalPelanggan.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 48)); // NOI18N
        lbTotalPelanggan.setForeground(new java.awt.Color(255, 255, 255));
        lbTotalPelanggan.setText("123");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel18)
                .addGap(30, 30, 30)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(lbTotalPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTotalPelanggan)
                        .addGap(38, 38, 38))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(30, 30, 30))))
        );

        jPanel9.setBackground(new java.awt.Color(50, 79, 83));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/kelola pegawai1.png"))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Total Pegawai");

        lbTotalPegawai.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 48)); // NOI18N
        lbTotalPegawai.setForeground(new java.awt.Color(255, 255, 255));
        lbTotalPegawai.setText("123");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel21)
                .addGap(30, 30, 30)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTotalPegawai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTotalPegawai)
                        .addGap(38, 38, 38))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(30, 30, 30))))
        );

        jPanel7.setBackground(new java.awt.Color(50, 79, 83));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/Daftar Barang1.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Total Barang");

        lbTotalBarang.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 48)); // NOI18N
        lbTotalBarang.setForeground(new java.awt.Color(255, 255, 255));
        lbTotalBarang.setText("123");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel11)
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTotalBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTotalBarang)
                        .addGap(38, 38, 38))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(30, 30, 30))))
        );

        rank.setBackground(new java.awt.Color(255, 255, 255));
        rank.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 155, 154)));

        jLabel24.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(50, 79, 83));
        jLabel24.setText("Pelanggan Teratas");

        rank1.setBackground(new java.awt.Color(255, 255, 255));
        rank1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(140, 155, 154)));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/rank 1.png"))); // NOI18N

        lbNmTopRank1.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        lbNmTopRank1.setForeground(new java.awt.Color(50, 79, 83));
        lbNmTopRank1.setText("Muhamad Ardalepa");

        lbNOrderRank1.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 14)); // NOI18N
        lbNOrderRank1.setForeground(new java.awt.Color(50, 79, 83));
        lbNOrderRank1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNOrderRank1.setText("30x");

        jLabel28.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(50, 79, 83));
        jLabel28.setText("Order");
        jLabel28.setToolTipText("");

        javax.swing.GroupLayout rank1Layout = new javax.swing.GroupLayout(rank1);
        rank1.setLayout(rank1Layout);
        rank1Layout.setHorizontalGroup(
            rank1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rank1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel25)
                .addGap(10, 10, 10)
                .addComponent(lbNmTopRank1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNOrderRank1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel28)
                .addGap(10, 10, 10))
        );
        rank1Layout.setVerticalGroup(
            rank1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbNOrderRank1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbNmTopRank1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        rank2.setBackground(new java.awt.Color(255, 255, 255));
        rank2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(140, 155, 154)));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/rank 2.png"))); // NOI18N

        lbNmTopRank2.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        lbNmTopRank2.setForeground(new java.awt.Color(50, 79, 83));
        lbNmTopRank2.setText("Muhamad Ardalepa");

        lbNOrderRank2.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 14)); // NOI18N
        lbNOrderRank2.setForeground(new java.awt.Color(50, 79, 83));
        lbNOrderRank2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNOrderRank2.setText("30x");

        jLabel32.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(50, 79, 83));
        jLabel32.setText("Order");
        jLabel32.setToolTipText("");

        javax.swing.GroupLayout rank2Layout = new javax.swing.GroupLayout(rank2);
        rank2.setLayout(rank2Layout);
        rank2Layout.setHorizontalGroup(
            rank2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rank2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel29)
                .addGap(10, 10, 10)
                .addComponent(lbNmTopRank2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNOrderRank2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel32)
                .addGap(10, 10, 10))
        );
        rank2Layout.setVerticalGroup(
            rank2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbNOrderRank2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbNmTopRank2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        rank3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/rank 3.png"))); // NOI18N

        lbNmTopRank3.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        lbNmTopRank3.setForeground(new java.awt.Color(50, 79, 83));
        lbNmTopRank3.setText("Muhamad Ardalepa");

        lbNOrderRank3.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 14)); // NOI18N
        lbNOrderRank3.setForeground(new java.awt.Color(50, 79, 83));
        lbNOrderRank3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNOrderRank3.setText("30x");

        jLabel36.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(50, 79, 83));
        jLabel36.setText("Order");
        jLabel36.setToolTipText("");

        javax.swing.GroupLayout rank3Layout = new javax.swing.GroupLayout(rank3);
        rank3.setLayout(rank3Layout);
        rank3Layout.setHorizontalGroup(
            rank3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rank3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel33)
                .addGap(10, 10, 10)
                .addComponent(lbNmTopRank3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNOrderRank3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel36)
                .addGap(10, 10, 10))
        );
        rank3Layout.setVerticalGroup(
            rank3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbNOrderRank3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbNmTopRank3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout rankLayout = new javax.swing.GroupLayout(rank);
        rank.setLayout(rankLayout);
        rankLayout.setHorizontalGroup(
            rankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rankLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel24)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(rank1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rank2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rank3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        rankLayout.setVerticalGroup(
            rankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rankLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel24)
                .addGap(30, 30, 30)
                .addComponent(rank1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(rank2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(rank3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panTopBarang.setBackground(new java.awt.Color(255, 255, 255));
        panTopBarang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(140, 155, 154)));

        jLabel37.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(50, 79, 83));
        jLabel37.setText("Barang Terpopuler");

        jPanel16.setBackground(new java.awt.Color(231, 230, 225));
        jPanel16.setPreferredSize(new java.awt.Dimension(250, 150));

        lbImageTopBarang1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/Nikon D700.png"))); // NOI18N

        lbNmTopBarang1.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 14)); // NOI18N
        lbNmTopBarang1.setForeground(new java.awt.Color(50, 79, 83));
        lbNmTopBarang1.setText("Canon");

        lbMerkTopBarang1.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        lbMerkTopBarang1.setForeground(new java.awt.Color(50, 79, 83));
        lbMerkTopBarang1.setText("EOS R");

        lbNOrderTopBarang1.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        lbNOrderTopBarang1.setForeground(new java.awt.Color(50, 79, 83));
        lbNOrderTopBarang1.setText("17x Diorder");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lbImageTopBarang1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNmTopBarang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbMerkTopBarang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNOrderTopBarang1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbImageTopBarang1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lbNmTopBarang1)
                .addGap(0, 0, 0)
                .addComponent(lbMerkTopBarang1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbNOrderTopBarang1)
                .addGap(40, 40, 40))
        );

        panTopBarangItem2.setBackground(new java.awt.Color(231, 230, 225));
        panTopBarangItem2.setPreferredSize(new java.awt.Dimension(250, 150));

        lbImageTopBarang2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/images/Nikon D700.png"))); // NOI18N

        lbNmTopBarang2.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 14)); // NOI18N
        lbNmTopBarang2.setForeground(new java.awt.Color(50, 79, 83));
        lbNmTopBarang2.setText("Canon");

        lbMerkTopBarang2.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        lbMerkTopBarang2.setForeground(new java.awt.Color(50, 79, 83));
        lbMerkTopBarang2.setText("EOS R");

        lbNOrderTopBarang2.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        lbNOrderTopBarang2.setForeground(new java.awt.Color(50, 79, 83));
        lbNOrderTopBarang2.setText("17x Diorder");

        javax.swing.GroupLayout panTopBarangItem2Layout = new javax.swing.GroupLayout(panTopBarangItem2);
        panTopBarangItem2.setLayout(panTopBarangItem2Layout);
        panTopBarangItem2Layout.setHorizontalGroup(
            panTopBarangItem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panTopBarangItem2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lbImageTopBarang2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panTopBarangItem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNmTopBarang2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbMerkTopBarang2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNOrderTopBarang2, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        panTopBarangItem2Layout.setVerticalGroup(
            panTopBarangItem2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbImageTopBarang2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panTopBarangItem2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lbNmTopBarang2)
                .addGap(0, 0, 0)
                .addComponent(lbMerkTopBarang2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(lbNOrderTopBarang2)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout panTopBarangLayout = new javax.swing.GroupLayout(panTopBarang);
        panTopBarang.setLayout(panTopBarangLayout);
        panTopBarangLayout.setHorizontalGroup(
            panTopBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panTopBarangLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panTopBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addGroup(panTopBarangLayout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 58, Short.MAX_VALUE)
                        .addComponent(panTopBarangItem2, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        panTopBarangLayout.setVerticalGroup(
            panTopBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panTopBarangLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel37)
                .addGap(30, 30, 30)
                .addGroup(panTopBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panTopBarangItem2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(panTopBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 252, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rank, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addComponent(panTopBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(463, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel10);

        javax.swing.GroupLayout panDashboardLayout = new javax.swing.GroupLayout(panDashboard);
        panDashboard.setLayout(panDashboardLayout);
        panDashboardLayout.setHorizontalGroup(
            panDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panDashboardLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane4)
                .addGap(30, 30, 30))
        );
        panDashboardLayout.setVerticalGroup(
            panDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panDashboardLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1058, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );

        panMain.add(panDashboard, "card2");

        panProfile.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setMinimumSize(new java.awt.Dimension(250, 250));
        jPanel4.setName(""); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(651, 250));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setMinimumSize(new java.awt.Dimension(300, 320));
        jPanel5.setPreferredSize(new java.awt.Dimension(300, 250));
        jPanel5.setRequestFocusEnabled(false);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons/account.png"))); // NOI18N

        lbRoleUserProfile.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        lbRoleUserProfile.setForeground(new java.awt.Color(50, 79, 83));
        lbRoleUserProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbRoleUserProfile.setText("Admin");
        lbRoleUserProfile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbRoleUserProfile.setMaximumSize(new java.awt.Dimension(100, 30));
        lbRoleUserProfile.setMinimumSize(new java.awt.Dimension(100, 30));
        lbRoleUserProfile.setPreferredSize(new java.awt.Dimension(100, 30));

        tfNamaUserProfile.setEditable(false);
        tfNamaUserProfile.setBackground(new java.awt.Color(255, 255, 255));
        tfNamaUserProfile.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 18)); // NOI18N
        tfNamaUserProfile.setForeground(new java.awt.Color(50, 79, 83));
        tfNamaUserProfile.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfNamaUserProfile.setText("Muhamad Ardalepa");
        tfNamaUserProfile.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 10)));
        tfNamaUserProfile.setCaretColor(new java.awt.Color(50, 79, 83));
        tfNamaUserProfile.setDisabledTextColor(new java.awt.Color(50, 79, 83));
        tfNamaUserProfile.setEnabled(false);
        tfNamaUserProfile.setMinimumSize(new java.awt.Dimension(30, 200));
        tfNamaUserProfile.setPreferredSize(new java.awt.Dimension(200, 30));
        tfNamaUserProfile.setSelectionColor(new java.awt.Color(140, 155, 154));
        tfNamaUserProfile.setSelectionStart(0);

        btnEditProfile.setBackground(view.Primary);
        btnEditProfile.setForeground(new java.awt.Color(255, 255, 255));
        btnEditProfile.setText("Edit");
        btnEditProfile.setFocusPainted(false);
        btnEditProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProfileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lbRoleUserProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(50, 50, 50))
            .addComponent(tfNamaUserProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(75, 75, 75))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEditProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lbRoleUserProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tfNamaUserProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 84, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel5, new java.awt.GridBagConstraints());

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        lbJKUserProfile.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        lbJKUserProfile.setForeground(new java.awt.Color(50, 79, 83));
        lbJKUserProfile.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbJKUserProfile.setText("Laki-Laki");
        lbJKUserProfile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbJKUserProfile.setMaximumSize(new java.awt.Dimension(100, 30));
        lbJKUserProfile.setMinimumSize(new java.awt.Dimension(100, 30));
        lbJKUserProfile.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel13.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(50, 79, 83));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Jenis Kelamin");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel13.setMaximumSize(new java.awt.Dimension(100, 30));
        jLabel13.setMinimumSize(new java.awt.Dimension(100, 30));
        jLabel13.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel12.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(50, 79, 83));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Telepon");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel12.setMaximumSize(new java.awt.Dimension(100, 30));
        jLabel12.setMinimumSize(new java.awt.Dimension(100, 30));
        jLabel12.setPreferredSize(new java.awt.Dimension(100, 30));

        tfTlpUserProfile.setEditable(false);
        tfTlpUserProfile.setBackground(new java.awt.Color(255, 255, 255));
        tfTlpUserProfile.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        tfTlpUserProfile.setForeground(new java.awt.Color(50, 79, 83));
        tfTlpUserProfile.setText("081521544674");
        tfTlpUserProfile.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 10)));
        tfTlpUserProfile.setCaretColor(new java.awt.Color(50, 79, 83));
        tfTlpUserProfile.setDisabledTextColor(new java.awt.Color(50, 79, 83));
        tfTlpUserProfile.setEnabled(false);
        tfTlpUserProfile.setMinimumSize(new java.awt.Dimension(30, 200));
        tfTlpUserProfile.setPreferredSize(new java.awt.Dimension(200, 30));
        tfTlpUserProfile.setSelectionColor(new java.awt.Color(140, 155, 154));

        jLabel15.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(50, 79, 83));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Email");
        jLabel15.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel15.setMaximumSize(new java.awt.Dimension(100, 30));
        jLabel15.setMinimumSize(new java.awt.Dimension(100, 30));
        jLabel15.setPreferredSize(new java.awt.Dimension(100, 30));

        tfEmailUserProfile.setEditable(false);
        tfEmailUserProfile.setBackground(new java.awt.Color(255, 255, 255));
        tfEmailUserProfile.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        tfEmailUserProfile.setForeground(new java.awt.Color(50, 79, 83));
        tfEmailUserProfile.setText("nanangardalepa1@gmail.com");
        tfEmailUserProfile.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 10)));
        tfEmailUserProfile.setCaretColor(new java.awt.Color(50, 79, 83));
        tfEmailUserProfile.setDisabledTextColor(new java.awt.Color(50, 79, 83));
        tfEmailUserProfile.setEnabled(false);
        tfEmailUserProfile.setMinimumSize(new java.awt.Dimension(200, 30));
        tfEmailUserProfile.setPreferredSize(new java.awt.Dimension(200, 30));
        tfEmailUserProfile.setSelectionColor(new java.awt.Color(140, 155, 154));

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setToolTipText("");
        jScrollPane2.setEnabled(false);
        jScrollPane2.setFocusable(false);
        jScrollPane2.setOpaque(false);
        jScrollPane2.setRequestFocusEnabled(false);
        jScrollPane2.setVerifyInputWhenFocusTarget(false);

        taAlmUserProfile.setEditable(false);
        taAlmUserProfile.setBackground(java.awt.Color.white);
        taAlmUserProfile.setColumns(15);
        taAlmUserProfile.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        taAlmUserProfile.setForeground(new java.awt.Color(50, 79, 83));
        taAlmUserProfile.setLineWrap(true);
        taAlmUserProfile.setText("Jl. Tanggok Yunus No 2 Sintang Kalimantan Barat");
        taAlmUserProfile.setWrapStyleWord(true);
        taAlmUserProfile.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 0, 0));
        taAlmUserProfile.setCaretColor(new java.awt.Color(50, 79, 83));
        taAlmUserProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        taAlmUserProfile.setDisabledTextColor(new java.awt.Color(50, 79, 83));
        taAlmUserProfile.setEnabled(false);
        taAlmUserProfile.setMinimumSize(new java.awt.Dimension(101, 90));
        taAlmUserProfile.setPreferredSize(new java.awt.Dimension(225, 90));
        taAlmUserProfile.setSelectionColor(new java.awt.Color(140, 155, 154));
        jScrollPane2.setViewportView(taAlmUserProfile);

        jLabel16.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(50, 79, 83));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Alamat");
        jLabel16.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel16.setMaximumSize(new java.awt.Dimension(100, 30));
        jLabel16.setMinimumSize(new java.awt.Dimension(100, 30));
        jLabel16.setPreferredSize(new java.awt.Dimension(100, 30));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbJKUserProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfTlpUserProfile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfEmailUserProfile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(100, 100, 100))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lbJKUserProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tfTlpUserProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tfEmailUserProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(418, 418, 418))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        lbNPenyewaan.setFont(new java.awt.Font("Montserrat Medium", 0, 18)); // NOI18N
        lbNPenyewaan.setForeground(new java.awt.Color(50, 79, 83));
        lbNPenyewaan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNPenyewaan.setText("1");
        lbNPenyewaan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbNPenyewaan.setMaximumSize(new java.awt.Dimension(100, 30));
        lbNPenyewaan.setMinimumSize(new java.awt.Dimension(100, 30));
        lbNPenyewaan.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel9.setFont(new java.awt.Font("Montserrat Medium", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(50, 79, 83));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Penyewaan");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel9.setMaximumSize(new java.awt.Dimension(100, 30));
        jLabel9.setMinimumSize(new java.awt.Dimension(100, 30));
        jLabel9.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel10.setFont(new java.awt.Font("Montserrat Medium", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(50, 79, 83));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Pengembalian");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel10.setMaximumSize(new java.awt.Dimension(100, 30));
        jLabel10.setMinimumSize(new java.awt.Dimension(100, 30));
        jLabel10.setPreferredSize(new java.awt.Dimension(100, 30));

        lbNPengembalian.setFont(new java.awt.Font("Montserrat Medium", 0, 18)); // NOI18N
        lbNPengembalian.setForeground(new java.awt.Color(50, 79, 83));
        lbNPengembalian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNPengembalian.setText("1");
        lbNPengembalian.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbNPengembalian.setMaximumSize(new java.awt.Dimension(100, 30));
        lbNPengembalian.setMinimumSize(new java.awt.Dimension(100, 30));
        lbNPengembalian.setPreferredSize(new java.awt.Dimension(100, 30));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(lbNPenyewaan, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNPengembalian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
                .addGap(100, 100, 100))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNPenyewaan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNPengembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(275, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panProfileLayout = new javax.swing.GroupLayout(panProfile);
        panProfile.setLayout(panProfileLayout);
        panProfileLayout.setHorizontalGroup(
            panProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panProfileLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(panProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE)
                    .addGroup(panProfileLayout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(50, 50, 50))
        );
        panProfileLayout.setVerticalGroup(
            panProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panProfileLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(panProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );

        panMain.add(panProfile, "card2");

        panKelolaPegawai.setBackground(new java.awt.Color(255, 255, 255));

        tblKelolaPegawai.setAutoCreateRowSorter(true);
        tblKelolaPegawai.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        tblKelolaPegawai.setForeground(new java.awt.Color(50, 79, 83));
        tblKelolaPegawai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Pegawai", "Nama", "Jenis Kelamin", "Telepon", "Role", "Rincian", "Hapus"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKelolaPegawai.setCellSelectionEnabled(true);
        tblKelolaPegawai.setFocusable(false);
        tblKelolaPegawai.setGridColor(new java.awt.Color(140, 155, 154));
        tblKelolaPegawai.setIntercellSpacing(new java.awt.Dimension(30, 0));
        tblKelolaPegawai.setRowHeight(50);
        tblKelolaPegawai.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tblKelolaPegawai.setSelectionForeground(view.Primary);
        tblKelolaPegawai.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblKelolaPegawai.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblKelolaPegawai.setShowGrid(true);
        tblKelolaPegawai.getTableHeader().setResizingAllowed(false);
        tblKelolaPegawai.getTableHeader().setReorderingAllowed(false);
        tblKelolaPegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKelolaPegawaiMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblKelolaPegawai);
        tblKelolaPegawai.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tblKelolaPegawai.getColumnModel().getColumnCount() > 0) {
            tblKelolaPegawai.getColumnModel().getColumn(0).setMinWidth(100);
            tblKelolaPegawai.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblKelolaPegawai.getColumnModel().getColumn(0).setMaxWidth(100);
            tblKelolaPegawai.getColumnModel().getColumn(1).setResizable(false);
            tblKelolaPegawai.getColumnModel().getColumn(2).setMinWidth(150);
            tblKelolaPegawai.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblKelolaPegawai.getColumnModel().getColumn(2).setMaxWidth(150);
            tblKelolaPegawai.getColumnModel().getColumn(3).setMinWidth(150);
            tblKelolaPegawai.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblKelolaPegawai.getColumnModel().getColumn(3).setMaxWidth(150);
            tblKelolaPegawai.getColumnModel().getColumn(4).setMinWidth(120);
            tblKelolaPegawai.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblKelolaPegawai.getColumnModel().getColumn(4).setMaxWidth(120);
            tblKelolaPegawai.getColumnModel().getColumn(5).setMinWidth(100);
            tblKelolaPegawai.getColumnModel().getColumn(5).setPreferredWidth(100);
            tblKelolaPegawai.getColumnModel().getColumn(5).setMaxWidth(100);
            tblKelolaPegawai.getColumnModel().getColumn(6).setMinWidth(100);
            tblKelolaPegawai.getColumnModel().getColumn(6).setPreferredWidth(100);
            tblKelolaPegawai.getColumnModel().getColumn(6).setMaxWidth(100);
        }

        javax.swing.GroupLayout panKelolaPegawaiLayout = new javax.swing.GroupLayout(panKelolaPegawai);
        panKelolaPegawai.setLayout(panKelolaPegawaiLayout);
        panKelolaPegawaiLayout.setHorizontalGroup(
            panKelolaPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panKelolaPegawaiLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1082, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        panKelolaPegawaiLayout.setVerticalGroup(
            panKelolaPegawaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panKelolaPegawaiLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1118, Short.MAX_VALUE))
        );

        panMain.add(panKelolaPegawai, "card2");

        panKelolaPelanggan.setBackground(new java.awt.Color(255, 255, 255));

        tblKelolaPelanggan.setAutoCreateRowSorter(true);
        tblKelolaPelanggan.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        tblKelolaPelanggan.setForeground(new java.awt.Color(50, 79, 83));
        tblKelolaPelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Pelanggan", "Nama", "Telepon", "Alamat", "Rincian", "Hapus"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKelolaPelanggan.setCellSelectionEnabled(true);
        tblKelolaPelanggan.setFocusable(false);
        tblKelolaPelanggan.setGridColor(new java.awt.Color(140, 155, 154));
        tblKelolaPelanggan.setIntercellSpacing(new java.awt.Dimension(30, 0));
        tblKelolaPelanggan.setRowHeight(50);
        tblKelolaPelanggan.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tblKelolaPelanggan.setSelectionForeground(view.Primary);
        tblKelolaPelanggan.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblKelolaPelanggan.setShowGrid(true);
        tblKelolaPelanggan.getTableHeader().setResizingAllowed(false);
        tblKelolaPelanggan.getTableHeader().setReorderingAllowed(false);
        tblKelolaPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKelolaPelangganMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblKelolaPelanggan);
        tblKelolaPelanggan.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tblKelolaPelanggan.getColumnModel().getColumnCount() > 0) {
            tblKelolaPelanggan.getColumnModel().getColumn(0).setMinWidth(120);
            tblKelolaPelanggan.getColumnModel().getColumn(0).setPreferredWidth(120);
            tblKelolaPelanggan.getColumnModel().getColumn(0).setMaxWidth(120);
            tblKelolaPelanggan.getColumnModel().getColumn(1).setMinWidth(275);
            tblKelolaPelanggan.getColumnModel().getColumn(1).setPreferredWidth(275);
            tblKelolaPelanggan.getColumnModel().getColumn(1).setMaxWidth(275);
            tblKelolaPelanggan.getColumnModel().getColumn(2).setMinWidth(150);
            tblKelolaPelanggan.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblKelolaPelanggan.getColumnModel().getColumn(2).setMaxWidth(150);
            tblKelolaPelanggan.getColumnModel().getColumn(3).setResizable(false);
            tblKelolaPelanggan.getColumnModel().getColumn(3).setPreferredWidth(200);
            tblKelolaPelanggan.getColumnModel().getColumn(4).setMinWidth(100);
            tblKelolaPelanggan.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblKelolaPelanggan.getColumnModel().getColumn(4).setMaxWidth(100);
            tblKelolaPelanggan.getColumnModel().getColumn(5).setMinWidth(100);
            tblKelolaPelanggan.getColumnModel().getColumn(5).setPreferredWidth(100);
            tblKelolaPelanggan.getColumnModel().getColumn(5).setMaxWidth(100);
        }

        javax.swing.GroupLayout panKelolaPelangganLayout = new javax.swing.GroupLayout(panKelolaPelanggan);
        panKelolaPelanggan.setLayout(panKelolaPelangganLayout);
        panKelolaPelangganLayout.setHorizontalGroup(
            panKelolaPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panKelolaPelangganLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1082, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        panKelolaPelangganLayout.setVerticalGroup(
            panKelolaPelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panKelolaPelangganLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1118, Short.MAX_VALUE))
        );

        panMain.add(panKelolaPelanggan, "card2");

        panKelolaBarang.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panMainKelolaBarang.setBackground(new java.awt.Color(255, 255, 255));
        panMainKelolaBarang.setPreferredSize(new java.awt.Dimension(932, 2000));
        panMainKelolaBarang.setLayout(null);
        jScrollPane3.setViewportView(panMainKelolaBarang);

        javax.swing.GroupLayout panKelolaBarangLayout = new javax.swing.GroupLayout(panKelolaBarang);
        panKelolaBarang.setLayout(panKelolaBarangLayout);
        panKelolaBarangLayout.setHorizontalGroup(
            panKelolaBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panKelolaBarangLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1082, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );
        panKelolaBarangLayout.setVerticalGroup(
            panKelolaBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panKelolaBarangLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1088, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );

        panMain.add(panKelolaBarang, "card2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panTopTool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1256, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lbTitle)
                        .addGap(0, 0, 0)
                        .addComponent(panTopTool, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(panMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setExtendedState(MAXIMIZED_BOTH);
        deselectAllMenu();        
        selectMenu(btnDashboard, panDashboard);
        panTopTool.setVisible(false);
        
    }//GEN-LAST:event_formWindowOpened

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        deselectAllMenu();
        selectMenu(btnDashboard, panDashboard);
        panTopTool.setVisible(false);
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnPenyewaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenyewaanActionPerformed
        // TODO add your handling code here:
        deselectAllMenu();
        selectMenu(((JButton)evt.getSource()), panPenyewaan);
        panTopTool.setVisible(true);
        btnNew.requestFocus();
    }//GEN-LAST:event_btnPenyewaanActionPerformed

    private void btnKelolaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKelolaBarangActionPerformed
        deselectAllMenu();
        selectMenu(((JButton)evt.getSource()), panKelolaBarang);
        panTopTool.setVisible(true);
        btnNew.requestFocus();
    }//GEN-LAST:event_btnKelolaBarangActionPerformed

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed
        // TODO add your handling code here:
        deselectAllMenu();
        selectMenu(((JButton)evt.getSource()), panProfile);
        panTopTool.setVisible(false);
    }//GEN-LAST:event_btnProfileActionPerformed

    private void btnKelolaPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKelolaPegawaiActionPerformed
        // TODO add your handling code here:
        deselectAllMenu();
        selectMenu(((JButton)evt.getSource()), panKelolaPegawai);
        panTopTool.setVisible(true);
        btnNew.requestFocus();
    }//GEN-LAST:event_btnKelolaPegawaiActionPerformed

    private void btnKelolaPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKelolaPelangganActionPerformed
        // TODO add your handling code here:
        deselectAllMenu();
        selectMenu(((JButton)evt.getSource()), panKelolaPelanggan);
        panTopTool.setVisible(true);
        btnNew.requestFocus();
    }//GEN-LAST:event_btnKelolaPelangganActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        new DialogEditBaru(this, true,btnSelectedSidebar.getText(),null,null).show();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSidebarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSidebarMouseEntered
        unHoverAllSidebarBtn();
        ((JButton)evt.getSource()).setBackground(view.MenuRollover);
    }//GEN-LAST:event_btnSidebarMouseEntered

    private void btnSidebarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSidebarMouseExited
        unHoverAllSidebarBtn();
    }//GEN-LAST:event_btnSidebarMouseExited
    
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        boundingKelolaBarang();
    }//GEN-LAST:event_formComponentResized

    private void tblPenyewaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPenyewaanMouseClicked
        if (tblPenyewaan.getSelectedColumn()==6) {
            new DialogRincianOrder(this, true, (String) tblPenyewaan.getValueAt(tblPenyewaan.getSelectedRow(),0) ,arrUser[0]).show();
        }else if(tblPenyewaan.getSelectedColumn()==7){
            if (((String) tblPenyewaan.getValueAt(tblPenyewaan.getSelectedRow(),4)).equals("Ongoing")) {
                DialogInfo.showMsg(this, true, "Ongoing order tidak dapat dihapus", "Info", true);
            }else{
                int conf = DialogInfo.showPrompt(this, true, "Hapus Order "+((String) tblPenyewaan.getValueAt(tblPenyewaan.getSelectedRow(),0)), "Info", false);
                if(conf==1){
                    model.updOrIns("delete from tbl_order where id_order = '"+(String) tblPenyewaan.getValueAt(tblPenyewaan.getSelectedRow(),0)+"'");
                    DialogInfo.showMsg(this, true, "Order Berhasil Dihapus", "Info", true);
                }
            }
        }
    }//GEN-LAST:event_tblPenyewaanMouseClicked

    private void tblKelolaPegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKelolaPegawaiMouseClicked
        if (tblKelolaPegawai.getSelectedColumn()==5) {
            new DialogEditBaru(this, true, "Kelola Pegawai", (String) tblKelolaPegawai.getValueAt(tblKelolaPegawai.getSelectedRow(),0), null).show();
        }else if(tblKelolaPegawai.getSelectedColumn()==6){
            if(model.updOrIns("delete from tbl_pegawai where id_pegawai = '"+(String) tblKelolaPegawai.getValueAt(tblKelolaPegawai.getSelectedRow(),0)+"'")){
                DialogInfo.showMsg(this, true, "Data Pegawai Berhasil Dihapus", "Info", true);
            }
        }
    }//GEN-LAST:event_tblKelolaPegawaiMouseClicked

    private void tblKelolaPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKelolaPelangganMouseClicked
        if (tblKelolaPelanggan.getSelectedColumn()==4) {
            new DialogEditBaru(this, true, "Kelola Pelanggan", (String) tblKelolaPelanggan.getValueAt(tblKelolaPelanggan.getSelectedRow(),0), null).show();
        }else if(tblKelolaPelanggan.getSelectedColumn()==5){
            if(model.updOrIns("delete from tbl_pelanggan where id_pelanggan = '"+(String) tblKelolaPelanggan.getValueAt(tblKelolaPelanggan.getSelectedRow(),0)+"'")){
                DialogInfo.showMsg(this, true, "Data Pegawai Berhasil Dihapus", "Info", true);
            }
        }
    }//GEN-LAST:event_tblKelolaPelangganMouseClicked

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int x = DialogInfo.showPrompt(this, true, "Logout?", "Info", false);
        if (x==1) {
            dispose();
            new FormLogin().show();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        showDashboard();
        showPenyewaan(tfSearch.getText());
        showProfile();
        showKelolaPegawai(tfSearch.getText());
        showKelolaPelanggan(tfSearch.getText());
        showKelolaBarang(tfSearch.getText());
    }//GEN-LAST:event_formWindowActivated

    private void btnEditProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProfileActionPerformed
        new DialogEditBaru(this, true, "Profile", arrUser[0], null).show();
    }//GEN-LAST:event_btnEditProfileActionPerformed

    private void tfSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfSearchFocusGained
        tfSearch.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, view.Primary));
        tfSearch.setForeground(view.Primary);
        lbSearch.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, view.Primary));
        if (tfSearch.getText().equals("Cari")) {
            tfSearch.setText("");
        }
    }//GEN-LAST:event_tfSearchFocusGained

    private void tfSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfSearchFocusLost
        tfSearch.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, view.Secondary));
        tfSearch.setForeground(view.Secondary);
        lbSearch.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, view.Secondary));
        if (tfSearch.getText().equals("")) {
            tfSearch.setText("Cari");
        }
    }//GEN-LAST:event_tfSearchFocusLost

    private void lbSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSearchMouseClicked
        tfSearch.requestFocus();
    }//GEN-LAST:event_lbSearchMouseClicked

    private void btnSideAP(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSideAP
        tfSearch.setText("Cari");
    }//GEN-LAST:event_btnSideAP

    private void tfSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchKeyReleased
        switch (btnSelectedSidebar.getText()) {
            case "Penyewaan":
                showPenyewaan(tfSearch.getText());
                break;
            case "Kelola Barang":
                showKelolaBarang(tfSearch.getText());
                break;
            case "Kelola Pegawai":
                showKelolaPegawai(tfSearch.getText());
                break;
            case "Kelola Pelanggan":
                showKelolaPelanggan(tfSearch.getText());
                break;
            default:
                break;
        }

    }//GEN-LAST:event_tfSearchKeyReleased

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMain(arrUser).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnEditProfile;
    private javax.swing.JButton btnKelolaBarang;
    private javax.swing.JButton btnKelolaPegawai;
    private javax.swing.JButton btnKelolaPelanggan;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnPenyewaan;
    private javax.swing.JButton btnProfile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel16;
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
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lbImageTopBarang1;
    private javax.swing.JLabel lbImageTopBarang2;
    private javax.swing.JLabel lbJKUserProfile;
    private javax.swing.JLabel lbMerkTopBarang1;
    private javax.swing.JLabel lbMerkTopBarang2;
    private javax.swing.JLabel lbNOrderRank1;
    private javax.swing.JLabel lbNOrderRank2;
    private javax.swing.JLabel lbNOrderRank3;
    private javax.swing.JLabel lbNOrderTopBarang1;
    private javax.swing.JLabel lbNOrderTopBarang2;
    private javax.swing.JLabel lbNPengembalian;
    private javax.swing.JLabel lbNPenyewaan;
    private javax.swing.JLabel lbNmTopBarang1;
    private javax.swing.JLabel lbNmTopBarang2;
    private javax.swing.JLabel lbNmTopRank1;
    private javax.swing.JLabel lbNmTopRank2;
    private javax.swing.JLabel lbNmTopRank3;
    private javax.swing.JLabel lbRoleUserProfile;
    private javax.swing.JLabel lbSearch;
    private javax.swing.JLabel lbSessUser;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbTotalBarang;
    private javax.swing.JLabel lbTotalPegawai;
    private javax.swing.JLabel lbTotalPelanggan;
    private javax.swing.JLabel lbTotalPenyewaan;
    private javax.swing.JPanel panDashboard;
    private javax.swing.JPanel panKelolaBarang;
    private javax.swing.JPanel panKelolaPegawai;
    private javax.swing.JPanel panKelolaPelanggan;
    private javax.swing.JPanel panMain;
    private javax.swing.JPanel panMainKelolaBarang;
    private javax.swing.JPanel panPenyewaan;
    private javax.swing.JPanel panProfile;
    private javax.swing.JPanel panTopBarang;
    private javax.swing.JPanel panTopBarangItem2;
    private javax.swing.JPanel panTopTool;
    private javax.swing.JPanel rank;
    private javax.swing.JPanel rank1;
    private javax.swing.JPanel rank2;
    private javax.swing.JPanel rank3;
    private javax.swing.JTextArea taAlmUserProfile;
    private javax.swing.JTable tblKelolaPegawai;
    private javax.swing.JTable tblKelolaPelanggan;
    private javax.swing.JTable tblPenyewaan;
    private javax.swing.JTextField tfEmailUserProfile;
    private javax.swing.JTextField tfNamaUserProfile;
    private javax.swing.JTextField tfSearch;
    private javax.swing.JTextField tfTlpUserProfile;
    // End of variables declaration//GEN-END:variables
}
class ColumnColorRenderer extends DefaultTableCellRenderer {
   Color foregroundColor;
   public ColumnColorRenderer(Color foregroundColor) {
      super();
      this.foregroundColor = foregroundColor;
   }
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) {
      Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      cell.setForeground(foregroundColor);
      return cell;
   }
}