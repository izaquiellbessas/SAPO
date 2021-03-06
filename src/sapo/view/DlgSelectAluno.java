/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgSelectAluno.java
 *
 * Created on 10/11/2009, 11:30:49
 */
package sapo.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import sapo.controller.ControleAluno;
import sapo.util.PadraoEditor;
import sapo.util.PadraoZebrado;
import sapo.util.TabelaPadrao;

/**
 *
 * @author izaquiel
 */
public class DlgSelectAluno extends javax.swing.JDialog implements ActionListener {

    // private AlunoModelo modelo = new AlunoModelo();
    private boolean confirmado = false;
    int codigoJogo;

    /** Creates new form DlgSelectAluno */
    public int codigoAlunoSelecionado() {
        if (jtblSelecionador.getSelectedRow() >= 0) {
            return Integer.parseInt(jtblSelecionador.getValueAt(jtblSelecionador.getSelectedRow(), 0).toString());
        } else {
            return -1;
        }

    }


    public int codigoFaseSelecionada() {
        if (jTblFases.getSelectedRow() >= 0) {
            return Integer.parseInt(jTblFases.getValueAt(jTblFases.getSelectedRow(), 0).toString());
        } else {
            return -1;
        }

    }

    public DlgSelectAluno(java.awt.Frame parent, boolean modal,int codigoJogo) {
        super(parent, modal);
        initComponents();
        this.codigoJogo=codigoJogo;

        setTitle("Selecionando o Aluno e a Fase para começar a jogar");
        setMinimumSize(new Dimension(750, 400));

        //centralizar
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2, this.getWidth(), this.getHeight());
        atualizaTabela(jtblSelecionador, null);
        carregaFasesAluno();

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jpnlTabela = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblSelecionador = new javax.swing.JTable();
        jpnlFase = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTblFases = new javax.swing.JTable();
        jpnlPesquisa = new javax.swing.JPanel();
        jlblResponsavel = new javax.swing.JLabel();
        jlblNomeResponsavel = new javax.swing.JLabel();
        jlblAluno = new javax.swing.JLabel();
        jcboFiltro = new javax.swing.JComboBox();
        jtfBusca = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jpnlBotoes = new javax.swing.JPanel();
        jBtnConfirma = new javax.swing.JButton();
        jBtnCancela = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jpnlTabela.setLayout(new java.awt.BorderLayout());

        jtblSelecionador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtblSelecionador.setName("jtblSelecionador"); // NOI18N
        jtblSelecionador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblSelecionadorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblSelecionador);

        jpnlTabela.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jpnlFase.setMaximumSize(new java.awt.Dimension(100, 100));
        jpnlFase.setMinimumSize(new java.awt.Dimension(90, 53));
        jpnlFase.setPreferredSize(new java.awt.Dimension(180, 100));
        jpnlFase.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 3, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/textura_fases.png"))); // NOI18N
        jpnlFase.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jTblFases.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Codigo", "Fase"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTblFases);
        jTblFases.getColumnModel().getColumn(0).setMinWidth(30);
        jTblFases.getColumnModel().getColumn(0).setMaxWidth(33);
        jTblFases.getColumnModel().getColumn(1).setMinWidth(100);
        jTblFases.getColumnModel().getColumn(1).setMaxWidth(110);

        jpnlFase.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jpnlTabela.add(jpnlFase, java.awt.BorderLayout.EAST);

        jpnlPesquisa.setLayout(new java.awt.GridBagLayout());

        jlblResponsavel.setText("Responsável:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 100, 5, 5);
        jpnlPesquisa.add(jlblResponsavel, gridBagConstraints);

        jlblNomeResponsavel.setText("Nome Responsável");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 0);
        jpnlPesquisa.add(jlblNomeResponsavel, gridBagConstraints);

        jlblAluno.setText("Aluno:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 0);
        jpnlPesquisa.add(jlblAluno, gridBagConstraints);

        jcboFiltro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CODIGO", "NOME", "ENDEREÇO", "BAIRRO", "CIDADE", "TELEFONE", "CLASSIFICAÇÃO" }));
        jcboFiltro.setToolTipText("Selecione um campo para filtrar sua pesquisa");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 0);
        jpnlPesquisa.add(jcboFiltro, gridBagConstraints);

        jtfBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfBuscaKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 0);
        jpnlPesquisa.add(jtfBusca, gridBagConstraints);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/textura_selectaluno.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jpnlPesquisa.add(jLabel3, gridBagConstraints);

        jpnlTabela.add(jpnlPesquisa, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jpnlTabela, java.awt.BorderLayout.CENTER);

        jpnlBotoes.setLayout(new java.awt.GridBagLayout());

        jBtnConfirma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/botao_confirma.png"))); // NOI18N
        jBtnConfirma.setContentAreaFilled(false);
        jBtnConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnConfirmaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 250, 0, 200);
        jpnlBotoes.add(jBtnConfirma, gridBagConstraints);

        jBtnCancela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/botao_cancela.png"))); // NOI18N
        jBtnCancela.setContentAreaFilled(false);
        jBtnCancela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, -500, 0, 0);
        jpnlBotoes.add(jBtnCancela, gridBagConstraints);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/textura_selectaluno.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jpnlBotoes.add(jLabel2, gridBagConstraints);

        getContentPane().add(jpnlBotoes, java.awt.BorderLayout.PAGE_END);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-748)/2, (screenSize.height-462)/2, 748, 462);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelaActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jBtnCancelaActionPerformed

    private void jBtnConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConfirmaActionPerformed
        // TODO add your handling code here:
        confirmado = true;
        this.setVisible(false);
    }//GEN-LAST:event_jBtnConfirmaActionPerformed

    public boolean isConfirmado() {
        return confirmado;
    }

    public static void atualizaTabela(JTable tabela, String filtro) { //método para atualizar os registros na tabela
        try {
            //JTable tabela = visao.getPnlPrincipal().getjTable();
            TableModel mod;
            TabelaPadrao padrao = new TabelaPadrao();
            String sql = "SELECT a.`numMatricula`, a.`nome`, a.`rua`, a.`bairro`," +
                    " a.`cidade`, a.`telefone`, a.`classificacao` FROM aluno a";
            if (filtro != null) {
                sql = sql + " where " + filtro;
            }
            mod = padrao.retornaTableModel(sql);
            tabela.setModel(mod);
            TableColumn col;
            for (int i = 0; i < tabela.getModel().getColumnCount(); i++) {
                col = tabela.getColumnModel().getColumn(i);
                if (i == 0) {
                    col.setCellRenderer(new PadraoEditor());
                } else {
                    col.setCellRenderer(new PadraoZebrado());
                }
            }
            for (int i = 0; i < tabela.getModel().getColumnCount(); i++) {
                tabela.setEditingColumn(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControleAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jtfBuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfBuscaKeyPressed
        // TODO add your handling code here:
        // pegado o evento on enter da pesquisa
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (jtfBusca.getText() != null) {
                // 0- CODIGO
                if (jcboFiltro.getSelectedIndex() == 0) {
                    atualizaTabela(jtblSelecionador, "numMatricula = " + jtfBusca.getText() + " ORDER BY numMatricula;");
                }
                // 1 - nome
                if (jcboFiltro.getSelectedIndex() == 1) {
                    atualizaTabela(jtblSelecionador, "nome like '%" + jtfBusca.getText() + "%' ORDER BY nome;");
                }
                // 2 - rua
                if (jcboFiltro.getSelectedIndex() == 2) {
                    atualizaTabela(jtblSelecionador, "rua like '%" + jtfBusca.getText() + "%' ORDER BY rua;");
                }
                // 3 - cidade
                if (jcboFiltro.getSelectedIndex() == 3) {
                    atualizaTabela(jtblSelecionador, " cidade like '%" + jtfBusca.getText() + "%' ORDER BY cidade;");
                }
                // 4 - Bairro
                if (jcboFiltro.getSelectedIndex() == 4) {
                    atualizaTabela(jtblSelecionador, " bairro like '%" + jtfBusca.getText() + "%' ORDER BY bairro;");
                }
                // 5 - telefone
                if (jcboFiltro.getSelectedIndex() == 5) {
                    atualizaTabela(jtblSelecionador, " telefone like '%" + jtfBusca.getText() + "%' ORDER BY telefone;");
                }
                // 6 - classifivação
                if (jcboFiltro.getSelectedIndex() == 6) {
                    atualizaTabela(jtblSelecionador, " classificacao like '%" + jtfBusca.getText() + "%' ORDER BY classificacao;");
                }
            } else {
                atualizaTabela(jtblSelecionador, null);
            }
        }
    }//GEN-LAST:event_jtfBuscaKeyPressed

    private void jtblSelecionadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblSelecionadorMouseClicked

        // TODO add your handling code here:
        carregaFasesAluno();

    }//GEN-LAST:event_jtblSelecionadorMouseClicked

    private void carregaFasesAluno(){

        try {
            //JTable tabela = visao.getPnlPrincipal().getjTable();
            TableModel mod;
            TabelaPadrao padrao = new TabelaPadrao();
            String sql = "SELECT  h.`Fases_codigoFase` 'Código Fase' ,Data " +
                    " FROM historico h where  Aluno_numMatricula="+codigoAlunoSelecionado()+
                    " and h.`Fases_Jogos_codigo`= "+codigoJogo;
            mod = padrao.retornaTableModel(sql);
            jTblFases.setModel(mod);

            mod = padrao.retornaTableModel(sql);
            TableColumn col;
            for (int i = 0; i < jTblFases.getModel().getColumnCount(); i++) {
                col = jTblFases.getColumnModel().getColumn(i);
                if (i == 0) {
                    col.setCellRenderer(new PadraoEditor());
                } else {
                    col.setCellRenderer(new PadraoZebrado());
                }
            }
            for (int i = 0; i < jTblFases.getModel().getColumnCount(); i++) {
                jTblFases.setEditingColumn(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControleAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int getCodigoJogo() {
        return codigoJogo;
    }

    public void setCodigoJogo(int codigoJogo) {
        this.codigoJogo = codigoJogo;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCancela;
    private javax.swing.JButton jBtnConfirma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTblFases;
    private javax.swing.JComboBox jcboFiltro;
    private javax.swing.JLabel jlblAluno;
    private javax.swing.JLabel jlblNomeResponsavel;
    private javax.swing.JLabel jlblResponsavel;
    private javax.swing.JPanel jpnlBotoes;
    private javax.swing.JPanel jpnlFase;
    private javax.swing.JPanel jpnlPesquisa;
    private javax.swing.JPanel jpnlTabela;
    private javax.swing.JTable jtblSelecionador;
    private javax.swing.JTextField jtfBusca;
    // End of variables declaration//GEN-END:variables

    public JComboBox getJcboFiltro() {
        return jcboFiltro;
    }

    public void setJcboFiltro(JComboBox jcboFiltro) {
        this.jcboFiltro = jcboFiltro;
    }

    public JLabel getJlblNomeResponsavel() {
        return jlblNomeResponsavel;
    }

    public void setJlblNomeResponsavel(JLabel jlblNomeResponsavel) {
        this.jlblNomeResponsavel = jlblNomeResponsavel;
    }

    public JTable getJtblTabela() {
        return jtblSelecionador;
    }

    public void setJtblTabela(JTable jtblTabela) {
        this.jtblSelecionador = jtblTabela;
    }

    public JTextField getJtfBusca() {
        return jtfBusca;
    }

    public void setJtfBusca(JTextField jtfBusca) {
        this.jtfBusca = jtfBusca;
    }

    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
