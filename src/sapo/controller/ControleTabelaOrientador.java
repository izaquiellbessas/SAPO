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
import sapo.model.OrientadorModelo;
import sapo.view.DlgCadOrientador;

/**
 *
 * @author wsouza
 */
public class ControleTabelaOrientador implements MouseListener {

    private JTable tabela;
    private DlgCadOrientador visao;

    public ControleTabelaOrientador(DlgCadOrientador visao) {
        this.tabela = visao.getPnlPrincipal().getjTable();
        this.visao = visao;
    }

    private void setDadosInterface(OrientadorModelo ori) {
        if (ori != null) {
            visao.getPnlDados().getJftCEP().setText(ori.getCep());
            visao.getPnlDados().getJftTelefone().setText(ori.getTelefone());
            visao.getPnlDados().getJtfBairro().setText(ori.getBairro());
            visao.getPnlDados().getJtfCidade().setText(ori.getCidade());
            visao.getPnlDados().getJtfLogin().setText(ori.getLogin());
            visao.getPnlDados().getJtfMatricula().setText(String.valueOf(ori.getCodigo()));
            visao.getPnlDados().getJtfNome().setText(ori.getNome());
            visao.getPnlDados().getJtfNroRua().setText(String.valueOf(ori.getNumRua()));
            visao.getPnlDados().getJtfProfissao().setText(ori.getProfissao());
            visao.getPnlDados().getJtfRua().setText(ori.getEndereco());
        }
    }

    public void mouseClicked(MouseEvent arg0) {
        try {
            OrientadorModelo al = new OrientadorModelo();
            SQLOrientador sql = new SQLOrientador();
            Object pos = tabela.getValueAt(tabela.getSelectedRow(), 0);
            al = sql.localizar((Integer) pos);
            setDadosInterface(al);
        } catch (SQLException ex) {
            Logger.getLogger(ControleTabelaAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mousePressed(MouseEvent arg0) {
        //  throw new UnsupportedOperationException("Not supported yet.");
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
