package blessingvisual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class view {
    public static final Color MenuRollover = new Color(71, 97, 101);
    public static final Color Primary = new Color(50, 79, 83);
    public static final Color Secondary = new Color(140, 155, 154);
    public static final Color Tertiary = new Color(242, 161, 85);
    public static final Color Body = new Color(231, 230, 225);
    public static final Color Danger = new Color(220, 53, 69);
    public static final Color Warning = new Color(255, 193, 7);
    public static final Color Success = new Color(25, 134, 84);
    public static final Color White = new Color(254,254,254);
    public static final SimpleDateFormat viewFormat = new SimpleDateFormat("HH:mm | dd/MM/yy");
    public static final SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat idFormat = new SimpleDateFormat("ddMMYY");
    public static Dimension minWin(){
        return new Dimension(700,500);
    }
    
    public static Dimension prefWin(){
        return new Dimension(1366,768);
    }
    
    public static Font segoe(int weight,int size){
        Font segoe = new Font("Montserrat Medium", weight, size);
        return segoe;
    }


    public static JLabel lblStatus(String status) {
        JLabel lbl = new JLabel();
        switch(status){
             case "Ordered":
                 lbl.setBorder(new javax.swing.border.LineBorder(view.Primary, 1));
                 break;
             case "Ongoing":
                 lbl.setForeground(view.Warning);
                 lbl.setBorder(new javax.swing.border.LineBorder(view.Warning, 1));
                 break;
             case "Finished":
                 lbl.setForeground(view.Success);
                 lbl.setBorder(new javax.swing.border.LineBorder(view.Success, 1));
                 break;
             case "Canceled":
                 lbl.setForeground(view.Danger);
                 lbl.setBorder(new javax.swing.border.LineBorder(view.Danger, 1));
                 break;
             default:
                 lbl.setForeground(view.Primary);
                 lbl.setBorder(new javax.swing.border.LineBorder(view.Primary, 1));
                 break;
         }

         lbl.setHorizontalAlignment(SwingConstants.CENTER);
         lbl.setText(status);
         lbl.setFont(view.segoe(0, 12));

         return lbl;
    }
    
    public static final void tfFocusGained(JTextField tf){
        tf.setBorder(BorderFactory.createLineBorder(Primary));
    }
    public static final void tfFocusLost(JTextField tf){
        tf.setBorder(BorderFactory.createLineBorder(Secondary));
    }
    
    public static final Image showIcon(String fileName){
        Image img = new ImageIcon("src\\img\\images\\"+fileName).getImage();
        Image modImg=null;
        if (img.getHeight(null)>img.getWidth(null)) {
             modImg = img.getScaledInstance(100, (int) (img.getHeight(null)/(img.getWidth(null)/100f)), Image.SCALE_SMOOTH);
        }else if (img.getHeight(null)<img.getWidth(null)) {
             modImg = img.getScaledInstance((int) (img.getWidth(null)/(img.getHeight(null)/100f)),100, Image.SCALE_SMOOTH);
        }else{
             modImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        }
        
        return modImg;    
    }
}
