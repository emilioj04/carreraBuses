/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Master;
import carrerabuses.*;

/**
 *
 * @author Usuario
 */
public class master extends javax.swing.JFrame {

    private Juego juego;
    
        
    public master() {
        initComponents();
        iniciarJuego();
    }

    private void iniciarJuego() {
        juego = new Juego(this);
        panelJuego.setLayout(new java.awt.BorderLayout());
        panelJuego.add(juego.getPanel(), java.awt.BorderLayout.CENTER);

        juego.iniciar();

        addKeyListener(new manejoTeclas(juego)); 
        setFocusable(true); 
        requestFocusInWindow(); 
    }

    public void establecerResultadoCarrera(String resultado) {
        resultadoCarrera.setText(resultado);
    }
    

    @SuppressWarnings("unchecked")
    private void initComponents() {

        panelJuego = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultadoCarrera = new javax.swing.JTextArea();
        nuevoJuego = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelJuego.setBackground(new java.awt.Color(255, 255, 255));
        panelJuego.setForeground(new java.awt.Color(255, 204, 0));

        javax.swing.GroupLayout panelJuegoLayout = new javax.swing.GroupLayout(panelJuego);
        panelJuego.setLayout(panelJuegoLayout);
        panelJuegoLayout.setHorizontalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1330, Short.MAX_VALUE)
        );
        panelJuegoLayout.setVerticalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 491, Short.MAX_VALUE)
        );

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Goudy Stout", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BUS RACER");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        resultadoCarrera.setColumns(20);
        resultadoCarrera.setFont(new java.awt.Font("Eras Bold ITC", 1, 36)); // NOI18N
        resultadoCarrera.setRows(5);
        jScrollPane1.setViewportView(resultadoCarrera);

        nuevoJuego.setLabel("Nuevo Juego");
        nuevoJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoJuegoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(panelJuego, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nuevoJuego, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(nuevoJuego, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(panelJuego, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoJuegoActionPerformed
         if (juego != null) {
            juego.detenerAudio(); 
        }
        panelJuego.removeAll(); 
        iniciarJuego(); 
    }//GEN-LAST:event_nuevoJuegoActionPerformed

    /**
     * @param args the command line arguments
     */
    

    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton nuevoJuego;
    private javax.swing.JPanel panelJuego;
    private javax.swing.JTextArea resultadoCarrera;
}
