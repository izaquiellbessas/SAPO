/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sapo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import sapo.model.OrientadorModelo;
import sapo.util.Audio;
import sapo.util.PadraoEditor;
import sapo.util.PadraoZebrado;
import sapo.util.TabelaPadrao;
import sapo.view.DlgCadOrientador;
import sapo.view.DlgLogin;
import sapo.view.Principal;

/**
 *
 * @author Administrador
 */
public class ControleOrientador implements ActionListener {

    private DlgCadOrientador visao;
    private OrientadorModelo orientador;
    private DlgLogin visaoLogin;
    private String acao;
    private Principal principal;
    private int cont; //usado para contar o numero de tentativas de login, após 3 ele fecha tudo

    public ControleOrientador(OrientadorModelo orientador, DlgLogin visaoLogin) {
        principal = new Principal();
        principal.setVisible(true);
        cont = 0;
        this.orientador = orientador;
        this.visaoLogin = visaoLogin;
        acao = "";
        visaoLogin.getJbtnLogin().addActionListener(this);
        visaoLogin.getJbtnCancelar().addActionListener(this);
    }

    public ControleOrientador(DlgCadOrientador visao, OrientadorModelo orientador) {
        this.visao = visao;
        this.orientador = orientador;
        acao = "";
        visao.getPnlPrincipal().getjTable().addMouseListener(new ControleTabelaOrientador(visao));
        // adicionando os action listener do frame de cadastro
        visao.getPnlPrincipal().getJbtnCancelar().addActionListener(this);
        visao.getPnlPrincipal().getJbtnEditar().addActionListener(this);
        visao.getPnlPrincipal().getJbtnExcluir().addActionListener(this);
        visao.getPnlPrincipal().getJbtnIncluir().addActionListener(this);
        visao.getPnlPrincipal().getJbtnSair().addActionListener(this);
        visao.getPnlPrincipal().getJbtnSalvar().addActionListener(this);

        visao.getPnlPrincipal().getJtxtPesquisar().addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtxtPesquisarKeyPressed(evt);
            }
        });

        try {
            SQLOrientador sql = new SQLOrientador();
            setDadosInterface(sql.first());
            atualizaTabela();
        } catch (SQLException ex) {
            Logger.getLogger(ControleOrientador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void atualizaTabela() {
        try {
            JTable tabela = visao.getPnlPrincipal().getjTable();
            TableModel mod;
            TabelaPadrao padrao = new TabelaPadrao();
            mod = padrao.retornaTableModel("SELECT codigo, nome, profissao, telefone FROM orientador");
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
        } catch (SQLException ex) {
            Logger.getLogger(ControleOrientador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String[] getDadosInterface() {
        char caracteres[] = visao.getPnlDados().getjPFSenha().getPassword();
        String senha = "";
        for (int i = 0; i < caracteres.length; i++) {
            senha += caracteres[i];
        }
        String dados[] = {visao.getPnlDados().getJtfMatricula().getText(),
            visao.getPnlDados().getJtfNome().getText(),
            visao.getPnlDados().getJtfRua().getText(),
            visao.getPnlDados().getJtfNroRua().getText(),
            visao.getPnlDados().getJtfBairro().getText(),
            visao.getPnlDados().getJtfCidade().getText(),
            visao.getPnlDados().getJftCEP().getText(),
            visao.getPnlDados().getJftTelefone().getText(),
            visao.getPnlDados().getJtfProfissao().getText(),
            visao.getPnlDados().getJtfLogin().getText(),
            senha};
        return dados;
    }

    private void atualizaInterface(boolean prim, boolean seg) { //metodo para limpar os text field e inverter os botoes
        visao.getPnlPrincipal().getCtrl().HabDesabObjects(visao.getPnlPrincipal().getJbtnSair(),
                visao.getPnlPrincipal().getJbtnEditar(), visao.getPnlPrincipal().getJbtnExcluir(),
                visao.getPnlPrincipal().getJbtnIncluir(), visao.getPnlPrincipal().getJbtnCancelar(),
                visao.getPnlPrincipal().getJbtnSalvar(), visao.getPnlPrincipal().getjPanelTable(),
                visao.getPnlDados(), prim, seg);

        /* visao.getPnlPrincipal().getCtrl().existRegistro(visao.getPnlPrincipal().getJbtnEditar(),
        visao.getPnlPrincipal().getJbtnExcluir(), visao.getPnlPrincipal().getjPanelPesquisa(),
        visao.getPnlPrincipal().getjTable());*/

        visao.getPnlPrincipal().getCtrl().limparTextos(visao.getPnlDados());
    }

    private void pesquisaPor(String pesquisa) {
        try {
            JTable tabela = visao.getPnlPrincipal().getjTable();
            TableModel mod;
            TabelaPadrao padrao = new TabelaPadrao();
            //System.out.println("SELECT * FROM orientador where " + pesquisa + " = " + visao.getPnlPrincipal().getJtxtPesquisar().getText() + ";");
            mod = padrao.retornaTableModel("SELECT * FROM orientador where " + pesquisa);
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

    private void jtxtPesquisarKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // 0- CODIGO
            if (visao.getPnlPrincipal().getJcmbCategoria().getSelectedIndex() == 0) {
                // JOptionPane.showMessageDialog(null, "pesquisa por matricula");
                pesquisaPor("codigo = '" + visao.getPnlPrincipal().getJtxtPesquisar().getText() + "' ORDER BY codigo;");
            }
            // 1 - nome
            if (visao.getPnlPrincipal().getJcmbCategoria().getSelectedIndex() == 1) {
                //JOptionPane.showMessageDialog(null, "pesquisa por nome");
                pesquisaPor("nome like '%" + visao.getPnlPrincipal().getJtxtPesquisar().getText() + "%' ORDER BY nome;");
            }
            // 2 - rua
            if (visao.getPnlPrincipal().getJcmbCategoria().getSelectedIndex() == 2) {
                //JOptionPane.showMessageDialog(null, "pesquisa por rua");
                pesquisaPor("rua like '%" + visao.getPnlPrincipal().getJtxtPesquisar().getText() + "%' ORDER BY rua;");
            }
            // 3 - cidade
            if (visao.getPnlPrincipal().getJcmbCategoria().getSelectedIndex() == 3) {
                //JOptionPane.showMessageDialog(null, "pesquisa por cidade");
                pesquisaPor("cidade like '%" + visao.getPnlPrincipal().getJtxtPesquisar().getText() + "%' ORDER BY cidade;");
            }
            // 4 - Bairro
            if (visao.getPnlPrincipal().getJcmbCategoria().getSelectedIndex() == 4) {
                //JOptionPane.showMessageDialog(null, "pesquisa rua");
                pesquisaPor("bairro like '%" + visao.getPnlPrincipal().getJtxtPesquisar().getText() + "%' ORDER BY bairro;");
            }
            // 5 - telefone
            if (visao.getPnlPrincipal().getJcmbCategoria().getSelectedIndex() == 5) {
                //JOptionPane.showMessageDialog(null, "pesquisa por telefone");
                pesquisaPor("telefone like '%" + visao.getPnlPrincipal().getJtxtPesquisar().getText() + "%' ORDER BY telefone;");
            }
            // 6 - profissao
            if (visao.getPnlPrincipal().getJcmbCategoria().getSelectedIndex() == 6) {
                //JOptionPane.showMessageDialog(null, "pesquisa por profissao");
                pesquisaPor("profissao like '%" + visao.getPnlPrincipal().getJtxtPesquisar().getText() + "%' ORDER BY profissao;");
            }
            if (visao.getPnlPrincipal().getJtxtPesquisar().getText().equals("")) {
                try {
                    JTable tabela = visao.getPnlPrincipal().getjTable();
                    TableModel mod;
                    TabelaPadrao padrao = new TabelaPadrao();
                    //System.out.println("SELECT * FROM orientador where " + pesquisa + " = " + visao.getPnlPrincipal().getJtxtPesquisar().getText() + ";");
                    mod = padrao.retornaTableModel("SELECT * FROM orientador;");
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
        }
    }

    private void setDadosInterface(OrientadorModelo dados) {
        if (dados != null) {
            visao.getPnlDados().getJftCEP().setText(dados.getCep());
            visao.getPnlDados().getJftTelefone().setText(dados.getTelefone());
            visao.getPnlDados().getJtfBairro().setText(dados.getBairro());
            visao.getPnlDados().getJtfCidade().setText(dados.getCidade());
            visao.getPnlDados().getJtfLogin().setText(dados.getLogin());
            visao.getPnlDados().getJtfMatricula().setText(String.valueOf(dados.getCodigo()));
            visao.getPnlDados().getJtfNome().setText(dados.getNome());
            visao.getPnlDados().getJtfNroRua().setText(String.valueOf(dados.getNumRua()));
            visao.getPnlDados().getJtfProfissao().setText(dados.getProfissao());
            visao.getPnlDados().getJtfRua().setText(dados.getEndereco());
            visao.getPnlDados().getjPFSenha().setText(dados.getSenha());
        }
    }

    public boolean validaCampos(JPanel p) {
        for (int i = 0; i < p.getComponentCount(); i++) {
            if (p.getComponent(i) instanceof JFormattedTextField) {
                JFormattedTextField f = (JFormattedTextField) p.getComponent(i);
                f.setText(f.getText().trim());
                if (f.getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("INCLUIR")) {
            acao = "inserir";
            try {
                SQLOrientador sqlA = new SQLOrientador();
                OrientadorModelo ori = sqlA.last();
                if (ori == null) {
                    visao.getPnlDados().getJtfMatricula().setText("1");
                } else {
                    visao.getPnlDados().getJtfMatricula().setText(String.valueOf(ori.getCodigo() + 1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControleOrientador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getActionCommand().equals("CANCELAR")) {
            try {
                SQLOrientador ori = new SQLOrientador();
                atualizaInterface(true, false);
                setDadosInterface(ori.first());
            } catch (SQLException ex) {
                Logger.getLogger(ControleOrientador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getActionCommand().equals("SALVAR")) {
            if (validaCampos(visao.getPnlDados())) {
                try {
                    String[] orientadores = getDadosInterface();
                    /* nao usa mais operador
                    String[] operador = {visao.getPnlDados().getJtfLogin().getText(),
                    visao.getPnlDados().getjPFSenha().getText()};
                    JOptionPane.showMessageDialog(null, "Os dados do orientador " + orientador);


                    // Conexao do SQL para salvar na tabela do operador
                    SQLOperador sqlOperador = new SQLOperador();
                    sqlOperador.insRegistros(operador);
                     */
                    // Conexao SQL para salvar na tabela Orientador.
                    SQLOrientador sqlOrientador = new SQLOrientador();
                    OrientadorModelo ori;
                    //System.out.println(orientador);
                    if (acao.equals("inserir")) {
                        sqlOrientador.insRegistros(orientadores);
                        JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
                    } else if (acao.equals("editar")) {
                        if (JOptionPane.showConfirmDialog(null, "Deseja realmente alterar este orientador?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            sqlOrientador.atRegistros(orientadores, orientadores[0]);
                            //JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
                        }
                    }
                    ori = sqlOrientador.first();
                    atualizaInterface(true, false);
                    atualizaTabela();
                    setDadosInterface(ori);//coloca os dados do primeiro orientador na tela
                } catch (SQLException ex) {
                    Logger.getLogger(ControleOrientador.class.getName()).log(Level.SEVERE, null, ex);
                }

                visao.getPnlPrincipal().getCtrl().limparTextos(visao.getPnlPrincipal());
            } else {
                JOptionPane.showMessageDialog(null, "É necessário que termine de preencher todos os campos do cadastro antes que você possa salva-lo");
            }
        } else if (evt.getActionCommand().equals("EDITAR")) {
            try {
                acao = "editar";
                SQLOrientador sql = new SQLOrientador();
                OrientadorModelo model = sql.localizar(Integer.parseInt(visao.getPnlDados().getJtfMatricula().getText()));
                visao.getPnlDados().getJtfLogin().setVisible(false);
                visao.getPnlDados().getjPFSenha().setVisible(false);
                visao.getPnlDados().getjLblUsuario().setVisible(false);
                visao.getPnlDados().getjLblSenha().setVisible(false);
                if (model != null) {
                    atualizaInterface(false, true);
                    setDadosInterface(model);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControleOrientador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getActionCommand().equals("EXCLUIR")) {
            if (!visao.getPnlDados().getJtfMatricula().getText().equals("")) {
                if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Confirma", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    OrientadorModelo ori = null;
                    try {
                        SQLOrientador al = new SQLOrientador();

                        al.excluir(visao.getPnlDados().getJtfMatricula().getText());
                        //JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");

                        ori = al.first();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControleOrientador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    atualizaInterface(true, false);
                    atualizaTabela();
                    setDadosInterface(ori);
                }
            }
        } else if (evt.getActionCommand().equals("SAIR")) {
        } else if (evt.getActionCommand().equals("LOGIN")) {
            try {
                SQLOrientador sql = new SQLOrientador();
                String usuario = visaoLogin.getJftfUsuario().getText();
                String senha = "";
                char[] lerSenha = visaoLogin.getJpsfSenha().getPassword();
                for (int i = 0; i < lerSenha.length; i++) {
                    senha += lerSenha[i];
                }
                boolean logado = sql.validaOrientador(usuario, senha);
                if (logado) {
                    orientador = sql.getOrientador();
                    principal.getJlblUser().setText(usuario.toUpperCase());
                    principal.setOrientador(orientador); //a interface principal agora armazena qual o orientador que logou no sistema
                    Audio audio = new Audio();
                    audio.executa("/src/sons/[msg]bemvindo.wav");
                    visaoLogin.dispose();
                } else {
                    cont++;
                    if (cont < 3) {
                        JOptionPane.showMessageDialog(null, "Tentativa número " + (cont + 1));
                        visaoLogin.getJpsfSenha().setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Suas tentativas terminaram, o programa será encerrado.");
                        visaoLogin.dispose();
                        principal.dispose();
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(ControleOrientador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getActionCommand().equals("CANCEL_LOGIN")) {
            //JOptionPane.showMessageDialog(null, "funciona cancelar");
        }
    }
}
