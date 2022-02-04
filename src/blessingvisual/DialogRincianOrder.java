/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package blessingvisual;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kamisato Ayaka
 */
public class DialogRincianOrder extends javax.swing.JDialog {

    /**
     * Creates new form RincianDialog
     */
    static String orderId;
    static String userId = "";
    static String[][] arrDetailOrder;
    static String[] arrOrder1 = null;
    static String nmPegawaiKembali = "";
//    static String[] arrOrder1 = null;
    DefaultTableModel dtmDetailBarang;
    Date tglOrder = null;
    Date tglKembali = null;
    Date tglNow = new Date();
    int nDenda,nDurasi;
    float total=0;
    float totalDenda=0;
    public DialogRincianOrder(java.awt.Frame parent, boolean modal,String orderId,String userId) {
        super(parent, modal);
        this.orderId = orderId;
        this.userId = userId;
        System.out.println(userId);
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        dtmDetailBarang = (DefaultTableModel)tblDetailBarang.getModel();

        showDialog();
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
        lbIdOrder = new javax.swing.JLabel();
        lbTglOrder = new javax.swing.JLabel();
        lbDurasi = new javax.swing.JLabel();
        lbNmPelanggan = new javax.swing.JLabel();
        lbTlpPelanggan = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetailBarang = new javax.swing.JTable();
        lbPegawai = new javax.swing.JLabel();
        lbDendaLabel = new javax.swing.JLabel();
        lbDenda = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnFinish = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        lbTglKembaliLabel = new javax.swing.JLabel();
        lbTglKembali = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        lbPenerima = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(50, 79, 83));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Rincian");

        lbIdOrder.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbIdOrder.setForeground(new java.awt.Color(50, 79, 83));
        lbIdOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbIdOrder.setText("O00000000");

        lbTglOrder.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        lbTglOrder.setForeground(new java.awt.Color(50, 79, 83));
        lbTglOrder.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTglOrder.setText("20:29 | 01/01/22");

        lbDurasi.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        lbDurasi.setForeground(new java.awt.Color(50, 79, 83));
        lbDurasi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbDurasi.setText("10 Hari");

        lbNmPelanggan.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        lbNmPelanggan.setForeground(new java.awt.Color(50, 79, 83));
        lbNmPelanggan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbNmPelanggan.setText("Nama Peminjam");

        lbTlpPelanggan.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        lbTlpPelanggan.setForeground(new java.awt.Color(50, 79, 83));
        lbTlpPelanggan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbTlpPelanggan.setText("Telepon");

        tblDetailBarang.setAutoCreateRowSorter(true);
        tblDetailBarang.setBackground(new java.awt.Color(255, 255, 255));
        tblDetailBarang.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        tblDetailBarang.setForeground(new java.awt.Color(50, 79, 83));
        tblDetailBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Barang", "Harga", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDetailBarang.setFocusable(false);
        tblDetailBarang.setGridColor(new java.awt.Color(140, 155, 154));
        tblDetailBarang.setIntercellSpacing(new java.awt.Dimension(30, 0));
        tblDetailBarang.setRowHeight(50);
        tblDetailBarang.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tblDetailBarang.setSelectionForeground(new java.awt.Color(50, 79, 83));
        tblDetailBarang.setShowGrid(true);
        tblDetailBarang.getTableHeader().setResizingAllowed(false);
        tblDetailBarang.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblDetailBarang);
        if (tblDetailBarang.getColumnModel().getColumnCount() > 0) {
            tblDetailBarang.getColumnModel().getColumn(0).setResizable(false);
            tblDetailBarang.getColumnModel().getColumn(1).setResizable(false);
            tblDetailBarang.getColumnModel().getColumn(2).setResizable(false);
        }

        lbPegawai.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        lbPegawai.setForeground(new java.awt.Color(50, 79, 83));
        lbPegawai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbPegawai.setText("Nama Kasir Peminjam");

        lbDendaLabel.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        lbDendaLabel.setForeground(new java.awt.Color(50, 79, 83));
        lbDendaLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbDendaLabel.setText("Denda Terlambat n Jam ");

        lbDenda.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        lbDenda.setForeground(new java.awt.Color(50, 79, 83));
        lbDenda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbDenda.setText(": Rp. 50.000");

        lbTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTotal.setForeground(new java.awt.Color(50, 79, 83));
        lbTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTotal.setText("Rp. 50.000");

        jLabel11.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(50, 79, 83));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Total :");

        btnFinish.setBackground(new java.awt.Color(50, 79, 83));
        btnFinish.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 14)); // NOI18N
        btnFinish.setForeground(new java.awt.Color(255, 255, 255));
        btnFinish.setText("Order Selesai");
        btnFinish.setBorder(null);
        btnFinish.setFocusPainted(false);
        btnFinish.setMaximumSize(new java.awt.Dimension(30, 120));
        btnFinish.setMinimumSize(new java.awt.Dimension(30, 120));
        btnFinish.setPreferredSize(new java.awt.Dimension(30, 120));
        btnFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinishActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(220, 53, 69));
        btnCancel.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Order Batal");
        btnCancel.setBorder(null);
        btnCancel.setFocusPainted(false);
        btnCancel.setMaximumSize(new java.awt.Dimension(30, 120));
        btnCancel.setMinimumSize(new java.awt.Dimension(30, 120));
        btnCancel.setPreferredSize(new java.awt.Dimension(30, 120));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnClose.setBackground(new java.awt.Color(255, 255, 255));
        btnClose.setFont(new java.awt.Font("Montserrat ExtraBold", 0, 14)); // NOI18N
        btnClose.setForeground(new java.awt.Color(50, 79, 83));
        btnClose.setText("Kembali");
        btnClose.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(50, 79, 83)));
        btnClose.setFocusPainted(false);
        btnClose.setMaximumSize(new java.awt.Dimension(30, 120));
        btnClose.setMinimumSize(new java.awt.Dimension(30, 120));
        btnClose.setPreferredSize(new java.awt.Dimension(30, 120));
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        lbTglKembaliLabel.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        lbTglKembaliLabel.setForeground(new java.awt.Color(50, 79, 83));
        lbTglKembaliLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbTglKembaliLabel.setText("Tanggal Kembali");

        lbTglKembali.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        lbTglKembali.setForeground(new java.awt.Color(50, 79, 83));
        lbTglKembali.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbTglKembali.setText(": 20:29 | 01/01/22");

        lbStatus.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        lbStatus.setForeground(new java.awt.Color(50, 79, 83));
        lbStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbStatus.setText("Status");

        lbPenerima.setFont(new java.awt.Font("Montserrat Medium", 0, 14)); // NOI18N
        lbPenerima.setForeground(new java.awt.Color(50, 79, 83));
        lbPenerima.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbPenerima.setText("Diterima oleh Admin");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbIdOrder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNmPelanggan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTlpPelanggan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbPegawai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(lbDurasi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTglOrder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDendaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                            .addComponent(lbTglKembaliLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTglKembali, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbPenerima, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(44, 44, 44))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(lbIdOrder)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbNmPelanggan)
                        .addGap(0, 0, 0)
                        .addComponent(lbTlpPelanggan)
                        .addGap(0, 0, 0)
                        .addComponent(lbStatus))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbTglOrder)
                        .addGap(0, 0, 0)
                        .addComponent(lbDurasi)
                        .addGap(0, 0, 0)
                        .addComponent(lbPegawai)))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTglKembaliLabel)
                    .addComponent(lbTglKembali)
                    .addComponent(jLabel11)
                    .addComponent(lbTotal))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPenerima)
                    .addComponent(lbDenda)
                    .addComponent(lbDendaLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    public void showDialog(){
        if (orderId!=null) {
            arrOrder1 = model.arrResult(
                "select nm_pelanggan, tlp_pelanggan, status, tgl_order,  nm_pegawai, durasi, tgl_kembali, total_bayar "
                + "from tbl_order join tbl_pelanggan using(id_pelanggan) "
                + "join tbl_pegawai on  tbl_order.id_pegawai_order  =  tbl_pegawai.id_pegawai "
                + "where id_order='"+orderId+"'");
            nmPegawaiKembali = model.stringResult(
                "Select nm_pegawai "
                + "from tbl_order "
                + "join tbl_pegawai on tbl_pegawai.id_pegawai = tbl_order.id_pegawai_order "
                + "where id_order='"+orderId+"'");
            if (nmPegawaiKembali==null) {
                nmPegawaiKembali="";
            }
            
            try {
                tglOrder = view.dbFormat.parse(arrOrder1[3]);
                            
                if (arrOrder1[6]!=null) {
                    tglKembali = view.dbFormat.parse(arrOrder1[6]);
                    lbTglKembali.setText(": "+view.viewFormat.format(tglKembali));
                }
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this,e);
            }
            nDurasi = Integer.parseInt(arrOrder1[5]);
            
            Calendar c = Calendar.getInstance();
            c.setTime(tglOrder);
            c.add(Calendar.DATE, nDurasi);
            Date tglTenggat = c.getTime();
            
            if (!arrOrder1[2].equals("Ongoing")) {
                if (!arrOrder1[2].equals("Canceled")) {
                    lbDenda.setVisible(true);
                    lbDendaLabel.setVisible(true);
                }
                lbTglKembali.setVisible(true);
                lbTglKembaliLabel.setVisible(true);
                lbPenerima.setVisible(true);
                btnCancel.setVisible(false);
                btnFinish.setVisible(false);
                nDenda = ((int) TimeUnit.MILLISECONDS.toHours(tglKembali.getTime()-tglOrder.getTime()))-(nDurasi*24);
            }else{
                lbDenda.setVisible(false);
                lbDendaLabel.setVisible(false);
                lbTglKembali.setVisible(false);
                lbTglKembaliLabel.setVisible(false);
                lbPenerima.setVisible(false);
                nDenda = (int) TimeUnit.MILLISECONDS.toHours(tglNow.getTime()-tglTenggat.getTime());
            }
            
            if (nDenda<=1) {
                nDenda=0;
                lbDenda.setVisible(false);
                lbDendaLabel.setVisible(false);
            }
            
            
            totalDenda=nDenda*20000;
            
            lbIdOrder.setText(orderId);
            lbNmPelanggan.setText(arrOrder1[0]);
            lbTlpPelanggan.setText(arrOrder1[1]);
            lbStatus.setText(arrOrder1[2]);
            
            lbDendaLabel.setText("Denda Terlambat "+nDenda+" Jam");
            lbDenda.setText(": "+NumberFormat.getCurrencyInstance().format(totalDenda));
            lbTglOrder.setText(view.viewFormat.format(tglOrder));
            lbDurasi.setText(arrOrder1[5]+" Hari");
            lbPegawai.setText(arrOrder1[4]);
            lbPenerima.setText("Diterima oleh : "+nmPegawaiKembali);

            
            dtmDetailBarang.setRowCount(0);
            dtmDetailBarang.fireTableDataChanged();
            arrDetailOrder = model.arr2Result("select merk, nm_barang, harga,total_harga, id_barang from tbl_detail_order "
                + "join tbl_barang using(id_barang) "
                + "where id_order = '"+orderId+"'");
            for (int i = 0; i < arrDetailOrder.length; i++) {
                String[] data = {
                    arrDetailOrder[i][0]+" "+arrDetailOrder[i][1],
                    NumberFormat.getCurrencyInstance().format(Float.parseFloat(arrDetailOrder[i][2])),
                    NumberFormat.getCurrencyInstance().format(Float.parseFloat(arrDetailOrder[i][3])),
                };
                
                dtmDetailBarang.addRow(data);
            }
            total=Float.parseFloat(arrOrder1[7]);
            lbTotal.setText(NumberFormat.getCurrencyInstance().format(total));
        }
    }
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinishActionPerformed
        int x = DialogInfo.showPrompt(this, true,"Barang Telah Dikembalikan?","Info",false);
        if (x==1) {
            model.updOrIns("update tbl_order set total_bayar='"+(total+totalDenda)+"' , status = 'Finished' ,tgl_kembali='"+view.dbFormat.format(tglNow)+"',id_pegawai_kembali='"+userId+"' where id_order='"+orderId+"'");
            for (String[] arrDetailOrder1 : arrDetailOrder) {
                model.updOrIns("update tbl_barang set status='Tersedia' where id_barang='" + arrDetailOrder1[4] + "'");
            }
            showDialog();
        }
    }//GEN-LAST:event_btnFinishActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        int x = DialogInfo.showPrompt(this, true,"Batalkan Order?","Info",false);
        if (x==1) {
            model.updOrIns("update tbl_order set total_bayar=0 , status = 'Canceled' ,tgl_kembali='"+view.dbFormat.format(tglNow)+"',id_pegawai_kembali='"+userId+"' where id_order='"+orderId+"'");
            for (String[] arrDetailOrder1 : arrDetailOrder) {
                model.updOrIns("update tbl_barang set status='Tersedia' where id_barang='" + arrDetailOrder1[4] + "'");
            }
            showDialog();
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogRincianOrder dialog = new DialogRincianOrder(new javax.swing.JFrame(), true,orderId,userId);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnFinish;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDenda;
    private javax.swing.JLabel lbDendaLabel;
    private javax.swing.JLabel lbDurasi;
    private javax.swing.JLabel lbIdOrder;
    private javax.swing.JLabel lbNmPelanggan;
    private javax.swing.JLabel lbPegawai;
    private javax.swing.JLabel lbPenerima;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbTglKembali;
    private javax.swing.JLabel lbTglKembaliLabel;
    private javax.swing.JLabel lbTglOrder;
    private javax.swing.JLabel lbTlpPelanggan;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JTable tblDetailBarang;
    // End of variables declaration//GEN-END:variables
}
