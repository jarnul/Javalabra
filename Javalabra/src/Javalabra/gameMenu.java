package Javalabra;

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
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class gameMenu extends javax.swing.JFrame implements ActionListener {

    private Javalabra menu;
    private int squareWidth;
    private gameLogic.gameLogic currentGame;
    private Timer timer;
    private int[][] lastStatus;
    private int score;
    private boolean gameOver;
    private gameFiles files;

    /**
     * Creates new form gameMenu
     */
    public gameMenu() {
        initComponents();
    }

    /**
     * Creates new form gameMenu and fills the game-status table status and
     * rotations-table
     */
    public gameMenu(Javalabra handle) {
        initComponents();
        menu = handle;
        this.squareWidth = 20;
        int gameWidth=12;
        int gameHeight=15;
        this.score=0;
        this.gameOver=false;
        this.files = new gameFiles("Player", 0);
        this.currentGame = new gameLogic.gameLogic(gameWidth,gameHeight);
        this.lastStatus = new int[gameWidth][gameHeight];
        for (int i=0;i<this.lastStatus.length;++i){
            for (int j=0;j<this.lastStatus[i].length;++j){
                this.lastStatus[i][j]=0;
            }
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

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jButton1.setText("Pause");
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Stop");
        jButton2.setFocusPainted(false);
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Start");
        jButton3.setFocusPainted(false);
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextPane1.setEditable(false);
        jTextPane1.setFocusable(false);
        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(309, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
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
        //Two loops to go through status-table, and draw all the blocks
        int[][] tempStatus = this.currentGame.getGameStatus();
        for (int i = 0; i < tempStatus.length; ++i) {
            for (int j = 0; j < tempStatus[i].length; ++j) {
                //If so that only blocks that change are drawn
                if (tempStatus[i][j]!=this.lastStatus[i][j]){
                    if (tempStatus[i][j] > 0) {
                        g.setColor(Color.black);
                        g.fillRect(20 * i + 50, 20 * j + 50, this.squareWidth, this.squareWidth);
                    } else {
                        g.clearRect(20 * i + 50, 20 * j + 50, this.squareWidth, this.squareWidth);
                    }
                }
            }
        }

    }

    /*
     * Method for keypress
     */
private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
    if (!this.gameOver){
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            this.currentGame.movePiece(2);
        }
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.currentGame.movePiece(1);
        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            this.currentGame.movePiece(0);
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN){
            updateGameStatus();
        }
        repaint();
    }

}//GEN-LAST:event_formKeyPressed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    this.timer.stop();
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.timer = new Timer(1000, this);
        timer.start();
    }//GEN-LAST:event_jButton3ActionPerformed

    /*
     * Method for implementing abstract class ActionListener, updates the
     * game-status after set times.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        updateGameStatus();
        repaint();

    }
    
    private void updateGameStatus(){
        int tempScore=this.currentGame.updateGame();
        if (tempScore >-1) {
            this.score=this.score + 10*tempScore;
            jTextPane1.setText(Integer.toString(this.score));
        }
        else {
            this.timer.stop();
            this.gameOver=true;
            files.setScore(this.score);
            files.saveScore();
            JOptionPane.showMessageDialog(this,
            "Game over!",
            "GG",
            JOptionPane.INFORMATION_MESSAGE);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
