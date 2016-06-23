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
import sapo.controller.ControleAluno;
import sapo.model.AlunoModelo;

/**
 *
 * @author christino
 */
public class DlgCadAluno extends JDialog implements ActionListener {

    private Cadastro pnlPrincipal = new Cadastro();
    private PnlAluno pnlDados = new PnlAluno();
    private AlunoModelo modelo = new AlunoModelo();
    private ControleAluno al = new ControleAluno(this, modelo);

    public DlgCadAluno(Frame owner) {
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

        setTitle("Cadastro de Alunos");
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
        pnlPrincipal.getJcmbCategoria().addItem("CLASSIFICAÇÃO");

        this.setModal(true);
        this.setVisible(true);
        this.setResizable(false);
    }

    public void actionPerformed(ActionEvent evt) { //retirado pois os eventos são tratados na clase controleAluno
        //como os eventos dos botoes são "constantes" todos estão em caixa auto

        if (evt.getActionCommand().equals("INCLUIR")) {
            pnlPrincipal.getCtrl().HabDesabObjects(pnlPrincipal.getJbtnSair(), pnlPrincipal.getJbtnEditar(), pnlPrincipal.getJbtnExcluir(), pnlPrincipal.getJbtnIncluir(), pnlPrincipal.getJbtnCancelar(), pnlPrincipal.getJbtnSalvar(), pnlPrincipal.getjPanelTable(), pnlDados, false, true);
            pnlPrincipal.getCtrl().limparTextos(pnlDados);
        } else if (evt.getActionCommand().equals("CANCELAR")) {
            pnlPrincipal.getCtrl().HabDesabObjects(pnlPrincipal.getJbtnSair(), pnlPrincipal.getJbtnEditar(), pnlPrincipal.getJbtnExcluir(), pnlPrincipal.getJbtnIncluir(), pnlPrincipal.getJbtnCancelar(), pnlPrincipal.getJbtnSalvar(), pnlPrincipal.getjPanelTable(), pnlDados, true, false);
            pnlPrincipal.getCtrl().existRegistro(pnlPrincipal.getJbtnEditar(), pnlPrincipal.getJbtnExcluir(), pnlPrincipal.getjPanelPesquisa(), pnlPrincipal.getjTable());
        } else if (evt.getActionCommand().equals("SALVAR")) {
           
        } else if (evt.getActionCommand().equals("EDITAR")) {
            //pnlPrincipal.getCtrl().HabDesabObjects(pnlPrincipal.getJbtnSair(), pnlPrincipal.getJbtnEditar(), pnlPrincipal.getJbtnExcluir(), pnlPrincipal.getJbtnIncluir(), pnlPrincipal.getJbtnCancelar(), pnlPrincipal.getJbtnSalvar(), pnlPrincipal.getjPanelTable(), pnlDados, false, true);
        } else if (evt.getActionCommand().equals("EXCLUIR")) {
            //JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
            pnlPrincipal.getCtrl().existRegistro(pnlPrincipal.getJbtnEditar(), pnlPrincipal.getJbtnExcluir(), pnlPrincipal.getjPanelPesquisa(), pnlPrincipal.getjTable());
        } else if (evt.getActionCommand().equals("SAIR")) {
            this.setVisible(false);
            this.dispose();
        }
    }

    public AlunoModelo getModelo() {
        return modelo;
    }

    public void setModelo(AlunoModelo modelo) {
        this.modelo = modelo;
    }

    public PnlAluno getPnlDados() {
        return pnlDados;
    }

    public void setPnlDados(PnlAluno pnlDados) {
        this.pnlDados = pnlDados;
    }

    public Cadastro getPnlPrincipal() {
        return pnlPrincipal;
    }

    public void setPnlPrincipal(Cadastro pnlPrincipal) {
        this.pnlPrincipal = pnlPrincipal;
    }
}
