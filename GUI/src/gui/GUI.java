/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
//import gui.Generator;

/**
 *
 * @author mateo
 */
public class GUI extends javax.swing.JFrame {

    private String selectedMethod = "";
    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
    }
    
    public void execute(){
        // TODO add your handling code here:
        String file = "";
        String args = "";
        if(selectedMethod.equals("jacobi")){
            file = "jacobi.py";
            String tolerance = txtTolerance.getText();
            String maxIters = txtMaxIterations.getText();
            String initial = initialValuesText.getText();

            args = tolerance + " " + maxIters + " "+initial;
            
        }else if(selectedMethod.equals("seidel")){
            file = "gaussSeidel.py";
            String tolerance = txtTolerance.getText();
            String maxIters = txtMaxIterations.getText();
            String initial = initialValuesText.getText();

            args = tolerance + " " + maxIters + " "+ initial;
            
        }else if(selectedMethod.equals("jacobiRela")){
            file = "jacobiRelajado.py";
            String tolerance = txtTolerance.getText();
            String maxIters = txtMaxIterations.getText();
            String initial = initialValuesText.getText();
            String landa = landaText.getText();

            args = tolerance + " " + maxIters + " "+initial+" "+landa;
            
        }else if(selectedMethod.equals("seidelRela")){
            file = "gaussSeidelRelajado.py";
            String tolerance = txtTolerance.getText();
            String maxIters = txtMaxIterations.getText();
            String initial = initialValuesText.getText();
            String landa = landaText.getText();

            args = tolerance + " " + maxIters + " "+initial+" "+landa;
            
        }else if(selectedMethod.equals("crout")){
            file = "luCrout.py";
        }else if(selectedMethod.equals("lucho")){
            file = "luCholesky.py";
        }else if(selectedMethod.equals("doolittle")){
            file = "luDoolittle.py";
        }else if(selectedMethod.equals("gauss")){
            file = "luGauss.py";
        }else if(selectedMethod.equals("gaussPartial")){
            file = "gaussPivoteoParcial.py";
        }else if(selectedMethod.equals("gaussTotal")){
            file = "gaussPivoteoTotal.py";
        }else if(selectedMethod.equals("gaussSimple")){
            file = "elimGaussNormal.py";
            
        }
        else if(selectedMethod.equals("help")){
            file = "helpReader.py";
            
        }
        BufferedReader [] std = executeCommand(file, args);  
        String s = null;
        try{
            boolean error=false;
            // read any errors from the attempted command
            while ((s = std[1].readLine()) != null) {
                JOptionPane.showMessageDialog(this,s,"Error",JOptionPane.ERROR_MESSAGE);
                error=true;
            }  
            // read the output from the command
            if(!error){
                //System.out.println("Here is the standard output of the command:\n");
                output.setText("");
                output.append("Here is the standard output of the command:\n");
                while ((s = std[0].readLine()) != null) {
                    //System.out.println(s);
                    output.append(s + "\n");
                }
            }
        }
        catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    public BufferedReader[] executeCommand(String file, String args){

        BufferedReader stdOutput = null;
        BufferedReader stdError = null;
        try {
            
	    // run the Unix "ps -ef" command
            // using the Runtime exec method:
            String ruta = "/Users/marcossierra/Dropbox/Eafit/Semestre6/"
                    + "AnalisisNumerico/Proyecto/parallelGames/InterfaceMethodsPython/";
            String ruta_args = "python " + ruta+file+" " + ruta + "matrix.txt "+args;
            Process p = Runtime.getRuntime().exec(ruta_args);
            
            stdOutput = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));

            stdError = new BufferedReader(new 
                 InputStreamReader(p.getErrorStream()));
            
            //System.exit(0);
        }
        catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
        
        BufferedReader[] std = {stdOutput, stdError};
        
        return std;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        btnExecCholesky = new javax.swing.JButton();
        btnExecute = new javax.swing.JButton();
        btnJacobi = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTolerance = new javax.swing.JTextField();
        txtMaxIterations = new javax.swing.JTextField();
        btnExecCrout = new javax.swing.JButton();
        btnExecDooLittle = new javax.swing.JButton();
        btnExecGauss1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        output = new javax.swing.JTextArea();
        btnSeidel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        initialValuesText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnJacobiRel = new javax.swing.JButton();
        btnSeidelRela = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        landaText = new javax.swing.JTextField();
        gaussPP = new javax.swing.JButton();
        gaussPT = new javax.swing.JButton();
        gaussSimple = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        matrixOrderText = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnExecCholesky.setText("Execute Lu Cholesky");
        btnExecCholesky.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecCholeskyActionPerformed(evt);
            }
        });

        btnExecute.setText("Execute");
        btnExecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecuteActionPerformed(evt);
            }
        });

        btnJacobi.setText("Select Jacobi");
        btnJacobi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJacobiActionPerformed(evt);
            }
        });

        jLabel1.setText("Tolerance");

        jLabel2.setText("Max Iterations");

        txtTolerance.setEnabled(false);

        txtMaxIterations.setEnabled(false);

        btnExecCrout.setText("Execute Lu Crout");
        btnExecCrout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecCroutActionPerformed(evt);
            }
        });

        btnExecDooLittle.setText("Execute Lu DooLittle");
        btnExecDooLittle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecDooLittleActionPerformed(evt);
            }
        });

        btnExecGauss1.setText("Execute Lu Gauss");
        btnExecGauss1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecGauss1ActionPerformed(evt);
            }
        });

        output.setColumns(20);
        output.setRows(5);
        jScrollPane1.setViewportView(output);

        btnSeidel.setText("Select Seidel");
        btnSeidel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeidelActionPerformed(evt);
            }
        });

        jLabel3.setText("Initial Values");

        initialValuesText.setEnabled(false);

        jLabel4.setText("i.e [1,2,3]");

        btnJacobiRel.setText("Select Relaxed Jacobi");
        btnJacobiRel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJacobiRelActionPerformed(evt);
            }
        });

        btnSeidelRela.setText("Select Relaxed Seidel");
        btnSeidelRela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeidelRelaActionPerformed(evt);
            }
        });

        jLabel5.setText("Lambda");

        landaText.setEnabled(false);

        gaussPP.setText("Execute Gauss Partial");
        gaussPP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gaussPPActionPerformed(evt);
            }
        });

        gaussPT.setText("Execute Gauss Total");
        gaussPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gaussPTActionPerformed(evt);
            }
        });

        gaussSimple.setText("Execute Gauss");
        gaussSimple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gaussSimpleActionPerformed(evt);
            }
        });

        helpButton.setText("Help");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        jButton2.setText("Generate Matrix");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Matrix Order");

        jLabel7.setText("i.e 100");

        jLabel8.setText("i.e 0.001");

        jLabel9.setText("1.0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(matrixOrderText, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSeidel)
                    .addComponent(btnJacobiRel, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeidelRela, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel7))
                                    .addComponent(txtMaxIterations, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(27, 27, 27)
                                .addComponent(txtTolerance, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4)
                                    .addComponent(initialValuesText)
                                    .addComponent(landaText, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btnExecute))
                    .addComponent(gaussPP)
                    .addComponent(gaussPT)
                    .addComponent(btnExecCrout)
                    .addComponent(btnExecDooLittle)
                    .addComponent(btnExecCholesky)
                    .addComponent(gaussSimple)
                    .addComponent(btnExecGauss1)
                    .addComponent(btnJacobi))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(helpButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(helpButton)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnJacobi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSeidel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnJacobiRel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSeidelRela)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtTolerance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtMaxIterations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(initialValuesText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(landaText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExecute)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gaussPP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gaussPT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gaussSimple)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExecCholesky)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExecCrout)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnExecDooLittle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExecGauss1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(matrixOrderText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))))
                        .addGap(0, 17, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExecCholeskyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecCholeskyActionPerformed
        selectedMethod = "lucho";
        txtTolerance.setEnabled(false);
        txtMaxIterations.setEnabled(false);
        landaText.setEditable(false);
        initialValuesText.setEditable(false);
        execute();
    }//GEN-LAST:event_btnExecCholeskyActionPerformed

    private void btnExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecuteActionPerformed
        execute();
    }//GEN-LAST:event_btnExecuteActionPerformed

    private void btnJacobiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJacobiActionPerformed
        selectedMethod = "jacobi";
        txtTolerance.setEnabled(true);
        txtMaxIterations.setEnabled(true);
        landaText.setEnabled(false);
        initialValuesText.setEnabled(true);
        
    }//GEN-LAST:event_btnJacobiActionPerformed

    private void btnExecCroutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecCroutActionPerformed
        selectedMethod = "crout";
        txtTolerance.setEnabled(false);
        txtMaxIterations.setEnabled(false);
        landaText.setEnabled(false);
        initialValuesText.setEnabled(false);
        execute();
    }//GEN-LAST:event_btnExecCroutActionPerformed

    private void btnExecDooLittleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecDooLittleActionPerformed
        selectedMethod = "doolittle";
        txtTolerance.setEnabled(false);
        txtMaxIterations.setEnabled(false);
        landaText.setEnabled(false);
        initialValuesText.setEnabled(false);
        execute();
    }//GEN-LAST:event_btnExecDooLittleActionPerformed

    private void btnExecGauss1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecGauss1ActionPerformed
        selectedMethod = "gauss";
        txtTolerance.setEnabled(false);
        txtMaxIterations.setEnabled(false);
        landaText.setEnabled(false);
        initialValuesText.setEnabled(false);
        execute();
    }//GEN-LAST:event_btnExecGauss1ActionPerformed

    private void btnSeidelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeidelActionPerformed
        selectedMethod = "seidel";
        txtTolerance.setEnabled(true);
        txtMaxIterations.setEnabled(true);
        initialValuesText.setEditable(true);
        landaText.setEnabled(false);
        initialValuesText.setEnabled(true);
    }//GEN-LAST:event_btnSeidelActionPerformed

    private void btnJacobiRelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJacobiRelActionPerformed
        // TODO add your handling code here:
        selectedMethod = "jacobiRela";
        txtTolerance.setEnabled(true);
        txtMaxIterations.setEnabled(true);
        landaText.setEnabled(true);
        initialValuesText.setEnabled(true);
    }//GEN-LAST:event_btnJacobiRelActionPerformed

    private void btnSeidelRelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeidelRelaActionPerformed
        // TODO add your handling code here:
        selectedMethod = "seidelRela";
        txtTolerance.setEnabled(true);
        txtMaxIterations.setEnabled(true);
        landaText.setEnabled(true);
        initialValuesText.setEnabled(true);
    }//GEN-LAST:event_btnSeidelRelaActionPerformed

    private void gaussPPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gaussPPActionPerformed
        // TODO add your handling code here:
        selectedMethod = "gaussPartial";
        txtTolerance.setEnabled(false);
        txtMaxIterations.setEnabled(false);
        landaText.setEnabled(false);
        initialValuesText.setEnabled(false);
        execute();
    }//GEN-LAST:event_gaussPPActionPerformed

    private void gaussPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gaussPTActionPerformed
        // TODO add your handling code here:
        selectedMethod = "gaussTotal";
        txtTolerance.setEnabled(false);
        txtMaxIterations.setEnabled(false);
        landaText.setEnabled(false);
        initialValuesText.setEnabled(false);
        execute();
    }//GEN-LAST:event_gaussPTActionPerformed

    private void gaussSimpleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gaussSimpleActionPerformed
        // TODO add your handling code here:
        selectedMethod = "gaussSimple";
        txtTolerance.setEnabled(false);
        txtMaxIterations.setEnabled(false);
        landaText.setEnabled(false);
        initialValuesText.setEnabled(false);
        execute();
    }//GEN-LAST:event_gaussSimpleActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        // TODO add your handling code here:
        selectedMethod = "help";
        txtTolerance.setEnabled(false);
        txtMaxIterations.setEnabled(false);
        landaText.setEnabled(false);
        initialValuesText.setEnabled(false);
        execute();
    }//GEN-LAST:event_helpButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String order = matrixOrderText.getText();
        Generator generator = new Generator();
        int order1 = Integer.parseInt(order);
        generator.matrixGenerator(order1, 10);
        JOptionPane.showMessageDialog(this, "The matrix has been generated");
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExecCholesky;
    private javax.swing.JButton btnExecCrout;
    private javax.swing.JButton btnExecDooLittle;
    private javax.swing.JButton btnExecGauss1;
    private javax.swing.JButton btnExecute;
    private javax.swing.JButton btnJacobi;
    private javax.swing.JButton btnJacobiRel;
    private javax.swing.JButton btnSeidel;
    private javax.swing.JButton btnSeidelRela;
    private javax.swing.JButton gaussPP;
    private javax.swing.JButton gaussPT;
    private javax.swing.JButton gaussSimple;
    private javax.swing.JButton helpButton;
    private javax.swing.JTextField initialValuesText;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField landaText;
    private javax.swing.JTextField matrixOrderText;
    private javax.swing.JTextArea output;
    private javax.swing.JTextField txtMaxIterations;
    private javax.swing.JTextField txtTolerance;
    // End of variables declaration//GEN-END:variables
}
