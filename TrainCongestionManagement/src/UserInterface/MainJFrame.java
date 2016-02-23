/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;
import Business.DB4OUtil.DB4OUtil;
import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.Organization;
import Business.TrainManagementSystem.SimulationClass;
import Business.TrainManagementSystem.Train;
import Business.TrainManagementSystem.TrainOffered;
import Business.UserAccount.UserAccount;
import java.awt.CardLayout;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Manasi Laddha
 */
public class MainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainJFrame
     */
    private EcoSystem system;
    private DB4OUtil dB4OUtil = DB4OUtil.getInstance();
    
    public MainJFrame() {
        
       initComponents();
       system = dB4OUtil.retrieveSystem();
       
       lblErrorMessageUserName.setVisible(false);
       lblErrorMessagePswrd.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainSplitPanel = new javax.swing.JSplitPane();
        mainLoginPanel = new javax.swing.JPanel();
        lblLogon = new javax.swing.JLabel();
        lblUserName = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblHeading = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        txtPswrd = new javax.swing.JPasswordField();
        btnLogout = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        lblErrorMessageUserName = new javax.swing.JLabel();
        lblErrorMessagePswrd = new javax.swing.JLabel();
        userProcessContainer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        mainSplitPanel.setDividerLocation(200);
        mainSplitPanel.setDividerSize(0);
        mainSplitPanel.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        mainSplitPanel.setEnabled(false);

        mainLoginPanel.setBackground(java.awt.Color.white);
        mainLoginPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        mainLoginPanel.setPreferredSize(new java.awt.Dimension(1014, 252));

        lblLogon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/login.png"))); // NOI18N

        lblUserName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblUserName.setText("UserName :");

        lblPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPassword.setText("Password :");

        lblHeading.setBackground(new java.awt.Color(255, 255, 255));
        lblHeading.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblHeading.setText("<html> <u>Smart Train Congestion Management</u> </html>");

        txtUserName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtUserName.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtUserName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUserNameFocusLost(evt);
            }
        });

        txtPswrd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPswrd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPswrdFocusLost(evt);
            }
        });

        btnLogout.setBackground(java.awt.Color.lightGray);
        btnLogout.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Login_out.png"))); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.setEnabled(false);
        btnLogout.setPreferredSize(new java.awt.Dimension(103, 41));
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Login_in.png"))); // NOI18N
        btnLogin.setText("Login");
        btnLogin.setPreferredSize(new java.awt.Dimension(103, 41));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblErrorMessageUserName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblErrorMessageUserName.setForeground(java.awt.Color.red);
        lblErrorMessageUserName.setText("ErrMsg");

        lblErrorMessagePswrd.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblErrorMessagePswrd.setForeground(java.awt.Color.red);
        lblErrorMessagePswrd.setText("ErrMsg");

        javax.swing.GroupLayout mainLoginPanelLayout = new javax.swing.GroupLayout(mainLoginPanel);
        mainLoginPanel.setLayout(mainLoginPanelLayout);
        mainLoginPanelLayout.setHorizontalGroup(
            mainLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLoginPanelLayout.createSequentialGroup()
                .addGroup(mainLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainLoginPanelLayout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(lblLogon)
                        .addGap(18, 18, 18)
                        .addGroup(mainLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(mainLoginPanelLayout.createSequentialGroup()
                                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainLoginPanelLayout.createSequentialGroup()
                                .addGroup(mainLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPassword)
                                    .addComponent(lblUserName))
                                .addGap(20, 20, 20)
                                .addGroup(mainLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtUserName)
                                    .addComponent(txtPswrd, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))))
                        .addGap(84, 84, 84)
                        .addGroup(mainLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblErrorMessageUserName)
                            .addComponent(lblErrorMessagePswrd)))
                    .addGroup(mainLoginPanelLayout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(lblHeading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainLoginPanelLayout.setVerticalGroup(
            mainLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLoginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHeading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mainLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainLoginPanelLayout.createSequentialGroup()
                        .addGroup(mainLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUserName)
                            .addComponent(lblErrorMessageUserName))
                        .addGap(22, 22, 22)
                        .addGroup(mainLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPassword)
                            .addComponent(txtPswrd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblErrorMessagePswrd)))
                    .addComponent(lblLogon))
                .addGap(13, 13, 13)
                .addGroup(mainLoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        mainSplitPanel.setLeftComponent(mainLoginPanel);

        userProcessContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        userProcessContainer.setPreferredSize(new java.awt.Dimension(1000, 552));
        userProcessContainer.setLayout(new java.awt.CardLayout());
        mainSplitPanel.setRightComponent(userProcessContainer);

        getContentPane().add(mainSplitPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        JPanel blankJP = new JPanel();
        userProcessContainer.add("blank", blankJP);
        CardLayout crdLyt = (CardLayout) userProcessContainer.getLayout();
        crdLyt.next(userProcessContainer);
        
        btnLogout.setEnabled(false);
        txtPswrd.setEnabled(true);
        txtUserName.setEnabled(true);
        btnLogin.setEnabled(true);
        //btnRegister.setEnabled(true);
         
        txtUserName.setText("");
        txtPswrd.setText("");
        userProcessContainer.removeAll();
        
        dB4OUtil.storeSystem(system);
        
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        // Get user name
        String userName = txtUserName.getText().trim();
        // Get Password
        char[] passwordCharArray = txtPswrd.getPassword();
        String password = String.valueOf(passwordCharArray).trim();

        //Step1: Check in the system user account directory if you have the user
        UserAccount userAccount = system.getUserAccountDirectory().authenticateUser(userName, password);
        Enterprise inEnterprise = null;
        Organization inOrganization = null;
        Network inNetwork = null;
        if (userAccount == null) {
            //Step2: Go inside each network to check each enterprise
            for (Network network : system.getNetworkList()) {
                //Step 2-a: Check against each enterprise
                for (Enterprise enterprise : network.getEnterpriseDirectory().getEnterpriseList()) {
                    userAccount = enterprise.getUserAccountDirectory().authenticateUser(userName, password);
                    if (userAccount == null) {
                        //Step3: Check against each organization inside that enterprise
                        for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                            userAccount = organization.getUserAccountDirectory().authenticateUser(userName, password);
                            if (userAccount != null) {
                                inEnterprise = enterprise;
                                inNetwork = network;
                                inOrganization = organization;
                                break;
                            }
                        }
                    } else {
                        inNetwork = network;
                        inEnterprise = enterprise;
                        break;
                    }
                    if (inOrganization != null) {
                        break;
                    }
                }
                if (inEnterprise != null) {
                    inNetwork = network;
                    break;
                }
            }
        }

        if (userAccount == null) {
            JOptionPane.showMessageDialog(null, "Invalid Credentails!");
            return;
        } else {
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            userProcessContainer.add("workArea", userAccount.getRole().createWorkArea(userProcessContainer, userAccount, inOrganization, inEnterprise,inNetwork, system));
            layout.next(userProcessContainer);
        }
        
        btnLogin.setEnabled(false);
        btnLogout.setEnabled(true);
        //btnRegister.setEnabled(false);
        txtUserName.setEnabled(false);
        lblPassword.setEnabled(false);
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtUserNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserNameFocusLost
        // TODO add your handling code here:
        if((txtUserName.getText().trim().length()) == 0){            
            lblErrorMessageUserName.setText("User Name cannot be blank.");
            lblErrorMessageUserName.setVisible(true);            
        }
        else {
            lblErrorMessageUserName.setText("");
            lblErrorMessageUserName.setVisible(false);      
        }
        
    }//GEN-LAST:event_txtUserNameFocusLost

    private void txtPswrdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPswrdFocusLost
        // TODO add your handling code here:
          if(txtPswrd.getPassword().length == 0){            
            lblErrorMessagePswrd.setText("Password cannot be blank.");
            lblErrorMessagePswrd.setVisible(true);            
        }
        else {
            lblErrorMessagePswrd.setText("");
            lblErrorMessagePswrd.setVisible(false);      
        }
    }//GEN-LAST:event_txtPswrdFocusLost

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
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel lblErrorMessagePswrd;
    private javax.swing.JLabel lblErrorMessageUserName;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JLabel lblLogon;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JPanel mainLoginPanel;
    private javax.swing.JSplitPane mainSplitPanel;
    private javax.swing.JPasswordField txtPswrd;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JPanel userProcessContainer;
    // End of variables declaration//GEN-END:variables
}
