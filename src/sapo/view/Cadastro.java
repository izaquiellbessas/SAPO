/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Cadastro.java
 *
 * Created on 22/10/2009, 13:57:13
 */
package sapo.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import sapo.util.CadControls;

/**
 *
 * @author home
 */
public class Cadastro extends javax.swing.JPanel {

    private CadControls ctrl;

    /** Creates new form Cadastro */
    public Cadastro() {
        initComponents();
        ctrl = new CadControls();
        ctrl.HabDesabObjects(jbtnSair, jbtnEditar, jbtnExcluir, jbtnIncluir, jbtnCancelar, jbtnSalvar, jPanelTable, jPanelAtribs, true, false);
        ctrl.limparTextos(jPanelAtribs);
        ctrl.existRegistro(jbtnEditar, jbtnExcluir, jPanelPesquisa, jTable);

    }

    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public CadControls getCtrl() {
        return ctrl;
    }
    
    public void setCtrl(CadControls ctrl) {
        this.ctrl = ctrl;
    }
    
    public JPanel getjPanelAtribs() {
        return jPanelAtribs;
    }
    
    public void setjPanelAtribs(JPanel jPanelAtribs) {
        this.jPanelAtribs = jPanelAtribs;
    }
    
    public JPanel getjPanelBotoes() {
        return jPanelBotoes;
    }
    
    public void setjPanelBotoes(JPanel jPanelBotoes) {
        this.jPanelBotoes = jPanelBotoes;
    }
    
    public JPanel getjPanelPesquisa() {
        return jPanelPesquisa;
    }
    
    public void setjPanelPesquisa(JPanel jPanelPesquisa) {
        this.jPanelPesquisa = jPanelPesquisa;
    }
    
    public JPanel getjPanelTable() {
        return jPanelTable;
    }
    
    public void setjPanelTable(JPanel jPanelTable) {
        this.jPanelTable = jPanelTable;
    }
    
    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }
    
    public void setjScrollPane(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }
    
    public JTabbedPane getjTabbedPane() {
        return jTabbedPane;
    }
    
    public void setjTabbedPane(JTabbedPane jTabbedPane) {
        this.jTabbedPane = jTabbedPane;
    }
    
    public JTable getjTable() {
        return jTable;
    }
    
    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }
    
    public JButton getJbtnCancelar() {
        return jbtnCancelar;
    }
    
    public void setJbtnCancelar(JButton jbtnCancelar) {
        this.jbtnCancelar = jbtnCancelar;
    }
    
    public JButton getJbtnEditar() {
        return jbtnEditar;
    }
    
    public void setJbtnEditar(JButton jbtnEditar) {
        this.jbtnEditar = jbtnEditar;
    }
    
    public JButton getJbtnExcluir() {
        return jbtnExcluir;
    }
    
    public void setJbtnExcluir(JButton jbtnExcluir) {
        this.jbtnExcluir = jbtnExcluir;
    }
    
    public JButton getJbtnIncluir() {
        return jbtnIncluir;
    }
    
    public void setJbtnIncluir(JButton jbtnIncluir) {
        this.jbtnIncluir = jbtnIncluir;
    }
    
    public JButton getJbtnSair() {
        return jbtnSair;
    }
    
    public void setJbtnSair(JButton jbtnSair) {
        this.jbtnSair = jbtnSair;
    }
    
    public JButton getJbtnSalvar() {
        return jbtnSalvar;
    }
    
    public void setJbtnSalvar(JButton jbtnSalvar) {
        this.jbtnSalvar = jbtnSalvar;
    }
    
    public JComboBox getJcmbCategoria() {
        return jcmbCategoria;
    }
    
    public void setJcmbCategoria(JComboBox jcmbCategoria) {
        this.jcmbCategoria = jcmbCategoria;
    }
    
    public JLabel getJlblPesquisa() {
        return jlblPesquisa;
    }
    
    public void setJlblPesquisa(JLabel jlblPesquisa) {
        this.jlblPesquisa = jlblPesquisa;
    }
    
    public JTextField getJtxtPesquisar() {
        return jtxtPesquisar;
    }
    
    public void setJtxtPesquisar(JTextField jtxtPesquisar) {
        this.jtxtPesquisar = jtxtPesquisar;
    }
//</editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelBotoes = new javax.swing.JPanel();
        jbtnIncluir = new javax.swing.JButton();
        jbtnExcluir = new javax.swing.JButton();
        jbtnEditar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jbtnSalvar = new javax.swing.JButton();
        jbtnSair = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanelAtribs = new javax.swing.JPanel();
        jPanelTable = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanelPesquisa = new javax.swing.JPanel();
        jlblPesquisa = new javax.swing.JLabel();
        jtxtPesquisar = new javax.swing.JTextField();
        jcmbCategoria = new javax.swing.JComboBox();

        setLayout(new java.awt.BorderLayout());

        jPanelBotoes.setLayout(new java.awt.GridBagLayout());

        jbtnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/botao_incluir.png"))); // NOI18N
        jbtnIncluir.setMnemonic('i');
        jbtnIncluir.setToolTipText("Inicia a inclusão de um novo registro");
        jbtnIncluir.setActionCommand("INCLUIR");
        jbtnIncluir.setBorder(null);
        jbtnIncluir.setBorderPainted(false);
        jbtnIncluir.setContentAreaFilled(false);
        jbtnIncluir.setFocusPainted(false);
        jbtnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIncluirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(70, 2, 10, 2);
        jPanelBotoes.add(jbtnIncluir, gridBagConstraints);

        jbtnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/botao_excluir.png"))); // NOI18N
        jbtnExcluir.setMnemonic('x');
        jbtnExcluir.setToolTipText("Exclua um dado registro");
        jbtnExcluir.setActionCommand("EXCLUIR");
        jbtnExcluir.setBorder(null);
        jbtnExcluir.setBorderPainted(false);
        jbtnExcluir.setContentAreaFilled(false);
        jbtnExcluir.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 2, 10, 2);
        jPanelBotoes.add(jbtnExcluir, gridBagConstraints);

        jbtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/botao_editar.png"))); // NOI18N
        jbtnEditar.setMnemonic('e');
        jbtnEditar.setToolTipText("Edite o registro selecionado");
        jbtnEditar.setActionCommand("EDITAR");
        jbtnEditar.setBorder(null);
        jbtnEditar.setBorderPainted(false);
        jbtnEditar.setContentAreaFilled(false);
        jbtnEditar.setEnabled(false);
        jbtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 2, 10, 2);
        jPanelBotoes.add(jbtnEditar, gridBagConstraints);

        jbtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/botao_cancelar.png"))); // NOI18N
        jbtnCancelar.setMnemonic('c');
        jbtnCancelar.setToolTipText("Cancele a edição ou inclusão");
        jbtnCancelar.setActionCommand("CANCELAR");
        jbtnCancelar.setBorder(null);
        jbtnCancelar.setBorderPainted(false);
        jbtnCancelar.setContentAreaFilled(false);
        jbtnCancelar.setEnabled(false);
        jbtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 2, 10, 2);
        jPanelBotoes.add(jbtnCancelar, gridBagConstraints);

        jbtnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/botao_salvar.png"))); // NOI18N
        jbtnSalvar.setMnemonic('s');
        jbtnSalvar.setToolTipText("Salve um novo registro ou as alterações feitas em um registro antigo");
        jbtnSalvar.setActionCommand("SALVAR");
        jbtnSalvar.setBorder(null);
        jbtnSalvar.setBorderPainted(false);
        jbtnSalvar.setContentAreaFilled(false);
        jbtnSalvar.setEnabled(false);
        jbtnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSalvarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 2, 10, 2);
        jPanelBotoes.add(jbtnSalvar, gridBagConstraints);

        jbtnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/botao_sair.png"))); // NOI18N
        jbtnSair.setMnemonic('r');
        jbtnSair.setToolTipText("Sair deste formulário");
        jbtnSair.setActionCommand("SAIR");
        jbtnSair.setBorder(null);
        jbtnSair.setBorderPainted(false);
        jbtnSair.setContentAreaFilled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(20, 2, 10, 2);
        jPanelBotoes.add(jbtnSair, gridBagConstraints);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/textura_tela_cadastro.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanelBotoes.add(jLabel1, gridBagConstraints);

        add(jPanelBotoes, java.awt.BorderLayout.LINE_START);

        jTabbedPane.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTabbedPaneComponentShown(evt);
            }
        });

        jPanelAtribs.setLayout(new java.awt.GridLayout(1, 0));
        jTabbedPane.addTab("Cadastro", jPanelAtribs);

        jPanelTable.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelTableComponentShown(evt);
            }
        });
        jPanelTable.setLayout(new java.awt.BorderLayout());

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4", "Título 5"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable.setToolTipText("Registros cadastrados, clique na linha para selecionar o registro desejado");
        jTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable.setName("jTable"); // NOI18N
        jScrollPane.setViewportView(jTable);

        jPanelTable.add(jScrollPane, java.awt.BorderLayout.CENTER);

        jPanelPesquisa.setLayout(new java.awt.GridBagLayout());

        jlblPesquisa.setDisplayedMnemonic(':');
        jlblPesquisa.setText("Pesquisar:");
        jlblPesquisa.setToolTipText("Selecione uma categoria para pesquisar um registro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelPesquisa.add(jlblPesquisa, gridBagConstraints);

        jtxtPesquisar.setText("Pesquisar...");
        jtxtPesquisar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtPesquisarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtxtPesquisarFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelPesquisa.add(jtxtPesquisar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelPesquisa.add(jcmbCategoria, gridBagConstraints);

        jPanelTable.add(jPanelPesquisa, java.awt.BorderLayout.PAGE_START);

        jTabbedPane.addTab("Fichas Cadastradas", jPanelTable);

        add(jTabbedPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPaneComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTabbedPaneComponentShown
    }//GEN-LAST:event_jTabbedPaneComponentShown

    private void jPanelTableComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelTableComponentShown
        // Verificação nessesaria para correção de bug
        ctrl.existRegistro(jbtnEditar, jbtnExcluir, jPanelPesquisa, jTable);
    }//GEN-LAST:event_jPanelTableComponentShown

    private void jbtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditarActionPerformed
        if (jTabbedPane.getSelectedIndex() != 0) {
            jTabbedPane.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jbtnEditarActionPerformed

    private void jbtnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIncluirActionPerformed
        if (jTabbedPane.getSelectedIndex() != 0) {
            jTabbedPane.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jbtnIncluirActionPerformed

    private void jbtnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSalvarActionPerformed
        if (jTabbedPane.getSelectedIndex() != 1) {
            jTabbedPane.setSelectedIndex(1);
        }
    }//GEN-LAST:event_jbtnSalvarActionPerformed

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarActionPerformed
        if (jTabbedPane.getSelectedIndex() != 1) {
            jTabbedPane.setSelectedIndex(1);
        }
    }//GEN-LAST:event_jbtnCancelarActionPerformed

    private void jtxtPesquisarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtPesquisarFocusGained
        jtxtPesquisar.selectAll();
    }//GEN-LAST:event_jtxtPesquisarFocusGained

    private void jtxtPesquisarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtPesquisarFocusLost
        if (jtxtPesquisar.getText().equals("")) {
            jtxtPesquisar.setText("Pesquisar");
        }
    }//GEN-LAST:event_jtxtPesquisarFocusLost
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelAtribs;
    private javax.swing.JPanel jPanelBotoes;
    private javax.swing.JPanel jPanelPesquisa;
    private javax.swing.JPanel jPanelTable;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable jTable;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnEditar;
    private javax.swing.JButton jbtnExcluir;
    private javax.swing.JButton jbtnIncluir;
    private javax.swing.JButton jbtnSair;
    private javax.swing.JButton jbtnSalvar;
    private javax.swing.JComboBox jcmbCategoria;
    private javax.swing.JLabel jlblPesquisa;
    private javax.swing.JTextField jtxtPesquisar;
    // End of variables declaration//GEN-END:variables
}
