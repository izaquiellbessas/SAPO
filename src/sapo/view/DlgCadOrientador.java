/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sapo.view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sapo.controller.ControleOrientador;
import sapo.model.OrientadorModelo;

/**
 *
 * @author christino
 */
public class DlgCadOrientador extends JDialog implements ActionListener {

    private Cadastro pnlPrincipal = new Cadastro();
    private PnlOrientador pnlDados = new PnlOrientador();
    // private PnlOperador pnlLogin=new PnlOperador();
    private OrientadorModelo modelo = new OrientadorModelo();
    private ControleOrientador oritentador = new ControleOrientador(this, modelo);

    public DlgCadOrientador(Frame owner) {
        super(owner);

        pnlPrincipal.getJbtnCancelar().addActionListener(this);
        pnlPrincipal.getJbtnEditar().addActionListener(this);
        pnlPrincipal.getJbtnExcluir().addActionListener(this);
        pnlPrincipal.getJbtnIncluir().addActionListener(this);
        pnlPrincipal.getJbtnSair().addActionListener(this);
        pnlPrincipal.getJbtnSalvar().addActionListener(this);

        pnlPrincipal.getCtrl().HabDesabObjects(pnlPrincipal.getJbtnSair(), pnlPrincipal.getJbtnEditar(), pnlPrincipal.getJbtnExcluir(), pnlPrincipal.getJbtnIncluir(), pnlPrincipal.getJbtnCancelar(), pnlPrincipal.getJbtnSalvar(), pnlPrincipal.getjPanelTable(), pnlDados, true, false);
        //pnlPrincipal.getCtrl().existRegistro(pnlPrincipal.getJbtnEditar(), pnlPrincipal.getJbtnExcluir(), pnlPrincipal.getjPanelPesquisa(), pnlPrincipal.getjTable());
        //pnlPrincipal.getCtrl().limparTextos(pnlDados);

        setTitle("Cadastro de Orientador");
        setMinimumSize(new Dimension(750, 400));

        //centralizar
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2, this.getWidth(), this.getHeight());

        add(pnlPrincipal);
        pnlPrincipal.getjPanelAtribs().add(pnlDados);

        pnlPrincipal.getJcmbCategoria().addItem("CODIGO");
        pnlPrincipal.getJcmbCategoria().addItem("NOME");
        pnlPrincipal.getJcmbCategoria().addItem("ENDEREÇO");
        pnlPrincipal.getJcmbCategoria().addItem("CIDADE");
        pnlPrincipal.getJcmbCategoria().addItem("BAIRRO");
        pnlPrincipal.getJcmbCategoria().addItem("TELEFONE");
        pnlPrincipal.getJcmbCategoria().addItem("PROFISSÃO");

        setModal(true);
        setVisible(true);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent evt) { //retirado pois os eventos são tratados na classe ControleOrientador
        //como os eventos dos botoes são "constantes" todos estão em caixa auto

        if (evt.getActionCommand().equals("INCLUIR")) {
            pnlPrincipal.getCtrl().HabDesabObjects(pnlPrincipal.getJbtnSair(), pnlPrincipal.getJbtnEditar(), pnlPrincipal.getJbtnExcluir(), pnlPrincipal.getJbtnIncluir(), pnlPrincipal.getJbtnCancelar(), pnlPrincipal.getJbtnSalvar(), pnlPrincipal.getjPanelTable(), pnlDados, false, true);
            pnlPrincipal.getCtrl().limparTextos(pnlDados);
        } else if (evt.getActionCommand().equals("CANCELAR")) {
            pnlPrincipal.getCtrl().HabDesabObjects(pnlPrincipal.getJbtnSair(), pnlPrincipal.getJbtnEditar(), pnlPrincipal.getJbtnExcluir(), pnlPrincipal.getJbtnIncluir(), pnlPrincipal.getJbtnCancelar(), pnlPrincipal.getJbtnSalvar(), pnlPrincipal.getjPanelTable(), pnlDados, true, false);
            pnlPrincipal.getCtrl().existRegistro(pnlPrincipal.getJbtnEditar(), pnlPrincipal.getJbtnExcluir(), pnlPrincipal.getjPanelPesquisa(), pnlPrincipal.getjTable());
            //pnlPrincipal.getCtrl().limparTextos(pnlDados);
            //JOptionPane.showMessageDialog(null, "Evento Cancelado com Sucesso");
        } else if (evt.getActionCommand().equals("SALVAR")) {
            pnlPrincipal.getCtrl().HabDesabObjects(pnlPrincipal.getJbtnSair(), pnlPrincipal.getJbtnEditar(), pnlPrincipal.getJbtnExcluir(), pnlPrincipal.getJbtnIncluir(), pnlPrincipal.getJbtnCancelar(), pnlPrincipal.getJbtnSalvar(), pnlPrincipal.getjPanelTable(), pnlDados, true, false);
            pnlPrincipal.getCtrl().existRegistro(pnlPrincipal.getJbtnEditar(), pnlPrincipal.getJbtnExcluir(), pnlPrincipal.getjPanelPesquisa(), pnlPrincipal.getjTable());
            //pnlPrincipal.getCtrl().limparTextos(pnlDados);
            //JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
        } else if (evt.getActionCommand().equals("EDITAR")) {
            //pnlPrincipal.getCtrl().HabDesabObjects(pnlPrincipal.getJbtnSair(), pnlPrincipal.getJbtnEditar(), pnlPrincipal.getJbtnExcluir(), pnlPrincipal.getJbtnIncluir(), pnlPrincipal.getJbtnCancelar(), pnlPrincipal.getJbtnSalvar(), pnlPrincipal.getjPanelTable(), pnlDados, false, true);
        } else if (evt.getActionCommand().equals("EXCLUIR")) {
            //JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
            //pnlPrincipal.getCtrl().existRegistro(pnlPrincipal.getJbtnEditar(), pnlPrincipal.getJbtnExcluir(), pnlPrincipal.getjPanelPesquisa(), pnlPrincipal.getjTable());
        } else if (evt.getActionCommand().equals("SAIR")) {            
                this.setVisible(false);
                this.dispose();
            
        }
    }

    public OrientadorModelo getModelo() {
        return modelo;
    }

    public void setModelo(OrientadorModelo modelo) {
        this.modelo = modelo;
    }

    public PnlOrientador getPnlDados() {
        return pnlDados;
    }

    public void setPnlDados(PnlOrientador pnlDados) {
        this.pnlDados = pnlDados;
    }

    public Cadastro getPnlPrincipal() {
        return pnlPrincipal;
    }

    public void setPnlPrincipal(Cadastro pnlPrincipal) {
        this.pnlPrincipal = pnlPrincipal;
    }
}
