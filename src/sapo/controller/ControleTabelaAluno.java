/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sapo.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import sapo.model.AlunoModelo;
import sapo.view.DlgCadAluno;
import sapo.view.DlgSelectAluno;

/**
 *
 * @author wsouza
 */
public class ControleTabelaAluno implements MouseListener {

    private JTable tabela;
    private DlgCadAluno visao;
    private DlgSelectAluno seleciona;

    public ControleTabelaAluno(DlgCadAluno visao) {
        this.visao = visao;
        this.tabela = visao.getPnlPrincipal().getjTable();
    }

    public ControleTabelaAluno(DlgSelectAluno seleciona) {
        this.seleciona = seleciona;
        this.tabela = seleciona.getJtblTabela();
    }

    private void setDadosInterface(AlunoModelo aluno) {
        try {
            if (aluno != null) {
                SQLAluno al = new SQLAluno();
                String data = al.dateToString(aluno.getDataNasc());

                visao.getPnlDados().getJftCEP().setText(aluno.getCep());
                if (data != null) {
                    visao.getPnlDados().getJftDataNasc().setText(data); //converter do mysql para string
                }
                visao.getPnlDados().getJftTelefone().setText(aluno.getTelefone());
                visao.getPnlDados().getJtfBairro().setText(aluno.getBairro());
                visao.getPnlDados().getJtfCidade().setText(aluno.getCidade());
                visao.getPnlDados().getJtfMatricula().setText(String.valueOf(aluno.getMatricula()));
                visao.getPnlDados().getJftNome().setText(aluno.getNome());
                visao.getPnlDados().getJtfNroRua().setText(String.valueOf(aluno.getNumeroRua()));
                visao.getPnlDados().getJtfRua().setText(aluno.getEndereco());
                int usar = 0; //seta qual item do combo deve aparecer
                for(int i = 0; i< visao.getPnlDados().getjComboBox1().getItemCount(); i++){
                    if (visao.getPnlDados().getjComboBox1().getItemAt(i).equals(aluno.getClassifica())){
                        usar = i;
                    }
                }
                visao.getPnlDados().getjComboBox1().setSelectedIndex(usar);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControleAluno.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void mouseClicked(MouseEvent arg0) {
        try {
            AlunoModelo al = new AlunoModelo();
            SQLAluno sql = new SQLAluno();
            Object pos = tabela.getValueAt(tabela.getSelectedRow(), 0);
            al = sql.localizar((Integer) pos);
            if (tabela.getName().equals("jTable")) {
                setDadosInterface(al);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControleTabelaAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mousePressed(MouseEvent arg0) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseReleased(MouseEvent arg0) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseEntered(MouseEvent arg0) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseExited(MouseEvent arg0) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
