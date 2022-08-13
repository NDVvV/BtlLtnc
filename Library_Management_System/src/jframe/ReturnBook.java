/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DucViet
 */
public class ReturnBook extends javax.swing.JFrame {

    /**
     * Creates new form IssueBook
     */
    int userId = 0;
    DefaultTableModel model;
    public ReturnBook(int userId) {
        initComponents();
        this.userId = userId;
    }

    
    //returned book after pay down
    public boolean returnBookAfterPayDown(){
        boolean isPayDown = false;
        int studentId = Integer.parseInt(txt_studentId.getText());

        try {
            Connection con = DBConnection.getConnection();
            String sql = "update issue_book_details set status = ? where student_id = ? and status = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "returned");
            pst.setInt(2, studentId);
            pst.setString(3, "beforeReturned");

            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                isPayDown = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        return isPayDown;
    }
    

    //return book
    public boolean returnBook() {
        boolean isReturned = false;
        int bookId = Integer.parseInt(txt_bookId.getText());
        int studentId = Integer.parseInt(txt_studentId.getText());

        try {
            Connection con = DBConnection.getConnection();
            String sql = "update issue_book_details set status = ? where book_id = ? and student_id = ? and status = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "beforeReturned");
            pst.setInt(2, bookId);
            pst.setInt(3, studentId);
            pst.setString(4, "pending");

            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                isReturned = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isReturned;
    }

    //update book count
    public void updateBookCount() {
        int bookId = Integer.parseInt(txt_bookId.getText());
        try {
            Connection con = DBConnection.getConnection();
            String sql = "update book_details set quantity = quantity + 1 where book_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            int rowCount = pst.executeUpdate();

            if (rowCount > 0) {
                JOptionPane.showMessageDialog(this, "Book count update");
            } else {
                JOptionPane.showMessageDialog(this, "Canot update book count");
            }
            showBookNameAndQuantity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //update due date
    public void updateDueDate() {
        int bookId = Integer.parseInt(txt_bookId.getText());
        int studentId = Integer.parseInt(txt_studentId.getText());
        java.util.Date date = new java.util.Date();
        long timeDue = date.getTime();
        java.sql.Date sDueDate = new java.sql.Date(timeDue);
        try {
            Connection con = DBConnection.getConnection();
            String sql = "update issue_book_details set due_date = ? where book_id = ? and student_id = ? and status = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDate(1, sDueDate);
            pst.setInt(2, bookId);
            pst.setInt(3, studentId);
            pst.setString(4, "returned");
            int rowCount = pst.executeUpdate();

            if (rowCount > 0) {
                JOptionPane.showMessageDialog(this, "Due date update");
            } else {
                JOptionPane.showMessageDialog(this, "Canot update due date");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //show issue of student 
    public void showIssueOfStudent(){
        int studentId = Integer.parseInt(txt_studentId.getText());
        
        try {
            Connection con = DBConnection.getConnection();
            String sql = "select * from issue_book_details where student_id = ? and status = 'pending'";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, studentId);
            ResultSet rs = pst.executeQuery();
            model = (DefaultTableModel) tbl_issueOfStudent.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                String issueId = rs.getString("issue_details_id");
                String bookId = rs.getString("book_id");
                String studentName = rs.getString("student_name");
                String bookName = rs.getString("book_name");
                String status = rs.getString("status");
                
                Object[] obj = {issueId, bookId, studentName, bookName, status};
                
                model.addRow(obj);
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //show pay down
    public void checkPayDown() {
        double payDown = 0;
        int studentId = Integer.parseInt(txt_studentId.getText());

        try {
            Connection con = DBConnection.getConnection();
            String sql = "select * from issue_book_details, book_details where issue_book_details.book_id = book_details.book_id and status = ? and student_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "beforeReturned");
            pst.setInt(2, studentId);

            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                payDown += Double.parseDouble(rs.getString("money_of_book"));
            }
            
            lbl_payDown.setText(Double.toString(payDown));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    //show book name and quantity
    public void showBookNameAndQuantity() {
        int bookId = Integer.parseInt(txt_bookId.getText());
        
        try {
            Connection con = DBConnection.getConnection();
            String sql = "select * from book_details where book_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                lbl_bookName.setText(rs.getString("book_name"));
                lbl_quantity.setText(rs.getString("quantity"));
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
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

        panel_main = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txt_bookId = new app.bolivia.swing.JCTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_studentId = new app.bolivia.swing.JCTextField();
        jLabel20 = new javax.swing.JLabel();
        rSMaterialButtonCircle2 = new necesario.RSMaterialButtonCircle();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_issueOfStudent = new rojeru_san.complementos.RSTableMetro();
        lbl_bookName = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lbl_payDown = new javax.swing.JLabel();
        rSMaterialButtonCircle4 = new necesario.RSMaterialButtonCircle();
        rSMaterialButtonCircle5 = new necesario.RSMaterialButtonCircle();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_main.setBackground(new java.awt.Color(255, 255, 255));
        panel_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 51, 51));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel13.setText(" Return Book");
        panel_main.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 80, 230, -1));

        jPanel3.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        panel_main.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 150, -1, -1));

        txt_bookId.setPlaceholder("Book id");
        txt_bookId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookIdFocusLost(evt);
            }
        });
        panel_main.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 380, 340, 50));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 51, 51));
        jLabel10.setText("Book Id");
        panel_main.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 340, 210, 32));

        txt_studentId.setPlaceholder("Student id");
        txt_studentId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentIdFocusLost(evt);
            }
        });
        panel_main.add(txt_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 240, 340, 50));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 51, 51));
        jLabel20.setText("Student id");
        panel_main.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 190, 210, 32));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(255, 1, 11));
        rSMaterialButtonCircle2.setText("Return book");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        panel_main.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 470, 400, 60));

        jPanel5.setBackground(new java.awt.Color(0, 51, 153));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel12.setText("Back");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        panel_main.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/library-2.png"))); // NOI18N
        panel_main.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 980, 360));

        tbl_issueOfStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Issue Id", "Book Id", "Student Name", "Book Name", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_issueOfStudent.setColorBackgoundHead(new java.awt.Color(0, 6, 250));
        tbl_issueOfStudent.setColorBordeFilas(new java.awt.Color(102, 102, 255));
        tbl_issueOfStudent.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_issueOfStudent.setColorSelBackgound(new java.awt.Color(255, 51, 51));
        tbl_issueOfStudent.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        tbl_issueOfStudent.setFuenteFilas(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tbl_issueOfStudent.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tbl_issueOfStudent.setFuenteHead(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tbl_issueOfStudent.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tbl_issueOfStudent.setRowHeight(40);
        jScrollPane2.setViewportView(tbl_issueOfStudent);

        panel_main.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 950, 320));

        lbl_bookName.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbl_bookName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_main.add(lbl_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 210, 40));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(147, 8, 250));
        jLabel16.setText("Book name: ");
        panel_main.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, 20));

        lbl_quantity.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbl_quantity.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_main.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 380, 190, 40));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(147, 8, 250));
        jLabel23.setText("Quantity:");
        panel_main.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 390, -1, 20));

        lbl_payDown.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lbl_payDown.setForeground(new java.awt.Color(153, 0, 153));
        lbl_payDown.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_main.add(lbl_payDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 700, 190, 40));

        rSMaterialButtonCircle4.setBackground(new java.awt.Color(255, 1, 11));
        rSMaterialButtonCircle4.setText("search pay down");
        rSMaterialButtonCircle4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle4ActionPerformed(evt);
            }
        });
        panel_main.add(rSMaterialButtonCircle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 620, 180, 60));

        rSMaterialButtonCircle5.setBackground(new java.awt.Color(255, 51, 51));
        rSMaterialButtonCircle5.setText("pay down");
        rSMaterialButtonCircle5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle5ActionPerformed(evt);
            }
        });
        panel_main.add(rSMaterialButtonCircle5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 690, 190, 60));

        jLabel22.setBackground(new java.awt.Color(204, 204, 204));
        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 0, 51));
        jLabel22.setText("  X");
        jLabel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });
        panel_main.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 0, 60, 70));

        getContentPane().add(panel_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 780));

        setSize(new java.awt.Dimension(1411, 778));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        HomePage home = new HomePage(this.userId);
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void txt_bookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIdFocusLost
        if (!txt_bookId.getText().equals("")) {
            showBookNameAndQuantity();
        }

    }//GEN-LAST:event_txt_bookIdFocusLost

    private void txt_studentIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentIdFocusLost
        if (!txt_studentId.getText().equals("")) {
            showIssueOfStudent();
        }
    }//GEN-LAST:event_txt_studentIdFocusLost

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        if (returnBook() == true) {
            JOptionPane.showMessageDialog(this, "Book returned successfully!");
            updateBookCount();
            updateDueDate();
            showIssueOfStudent();
        } else {
            JOptionPane.showMessageDialog(this, "Book returned failed!");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void rSMaterialButtonCircle4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle4ActionPerformed
        if (!txt_studentId.getText().equals("")) {
            checkPayDown();
        }
        
    }//GEN-LAST:event_rSMaterialButtonCircle4ActionPerformed

    private void rSMaterialButtonCircle5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle5ActionPerformed
        if (!txt_studentId.getText().equals("")) {
            if(returnBookAfterPayDown() == true) {
            checkPayDown();
            showIssueOfStudent();
            JOptionPane.showMessageDialog(this, "Pay Down successfull!");
            }else {
                JOptionPane.showMessageDialog(this, "Pay Down failed!");
            }
        }
        
        
        
    }//GEN-LAST:event_rSMaterialButtonCircle5ActionPerformed

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel22MouseClicked

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReturnBook(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_payDown;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JPanel panel_main;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle4;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle5;
    private rojeru_san.complementos.RSTableMetro tbl_issueOfStudent;
    private app.bolivia.swing.JCTextField txt_bookId;
    private app.bolivia.swing.JCTextField txt_studentId;
    // End of variables declaration//GEN-END:variables
}
