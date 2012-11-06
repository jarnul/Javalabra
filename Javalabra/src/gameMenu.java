/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * gameMenu.java
 *
 * Created on Oct 30, 2012, 7:40:19 PM
 */
/**
 *
 * @author jarno
 */

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class gameMenu extends javax.swing.JFrame implements ActionListener {
    private Javalabra menu;
    private int squareWidth;
    private gameLogic currentGame;
    
    /** Creates new form gameMenu */
    public gameMenu() {
        initComponents();
    }
    
    /** Creates new form gameMenu and fills the game-status table status and rotations-table*/
    
    public gameMenu(Javalabra handle) {
        initComponents();
        menu = handle;
        this.squareWidth=20;
        this.currentGame=new gameLogic();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jButton1.setText("Tauko");
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Lopeta");
        jButton2.setFocusPainted(false);
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(327, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(310, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*
     * Action Performed for button "Lopeta"
     */
    
private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    this.setVisible(false);
    (this.menu).setVisible(true);
}//GEN-LAST:event_jButton2ActionPerformed

/*
 * Method for drawing game-status
 */

@Override
public void paint(Graphics g) {
   
   super.paint(g);
   //Two loops to go trough status-table, and draw all the blocks
   int[][] tempStatus=this.currentGame.getGameStatus();
   for (int i=0;i<tempStatus.length;++i){
       for(int j=0;j<tempStatus[i].length;++j){
           if (tempStatus[i][j]>0) {
                g.setColor(Color.black);
                g.fillRect (20*i+50, 20*j+50, this.squareWidth, this.squareWidth);
           }
           else{
               g.clearRect (20*i+50, 20*j+50, this.squareWidth, this.squareWidth);
           }
       }
   }

}

/*
 * Method for keypress
 */

private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
    Timer timer = new Timer(1000, this);
    timer.start(); 
}//GEN-LAST:event_formKeyPressed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    this.currentGame.rotatePiece();// TODO add your handling code here:
    repaint();
}//GEN-LAST:event_jButton1ActionPerformed

/*
 * Method for implementing abstract class ActionListener, updates the game-status
 * after set times.
 */

@Override
public void actionPerformed(ActionEvent e) {
    this.currentGame.updateGame(); 
    repaint();
    
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    // End of variables declaration//GEN-END:variables
}