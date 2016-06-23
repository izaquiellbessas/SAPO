/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sapo.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import sapo.model.AlunoModelo;
import sapo.util.PadraoEditor;
import sapo.util.PadraoZebrado;
import sapo.util.TabelaPadrao;
import sapo.view.DlgCadAluno;

/**
 *
 * @author Administrador
 */
public class ControleAluno implements ActionListener {

    private DlgCadAluno visao;
    //  private DlgSelectAluno seleciona;
    private AlunoModelo aluno;
    private String acao; //variável para verificar se ação é uma inserção ou alteração.

    public ControleAluno(DlgCadAluno visao, AlunoModelo aluno) {
        this.visao = visao;
        this.aluno = aluno;
        //instancia a classe que terá os eventos de clique para a tabela
        visao.getPnlPrincipal().getjTable().addMouseListener(new ControleTabelaAluno(visao));

        acao = "";

        // adicionando os action listener dos frames de cadastro
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

        try {  //preenche a interface com o primeiro registro e a tabela com todos
            SQLAluno al = new SQLAluno();
            setDadosInterface(al.first());
            atualizaTabela(visao.getPnlPrincipal().getjTable());
        } catch (SQLException ex) {
            Logger.getLogger(ControleAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pesquisaPor(String pesquisa) {
        try {
            JTable tabela = visao.getPnlPrincipal().getjTable();
            TableModel mod;
            TabelaPadrao padrao = new TabelaPadrao();
            mod = padrao.retornaTableModel("SELECT * FROM aluno where " + pesquisa);
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
        // pegado o evento on enter da pesquisa
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // 0- CODIGO
            if (visao.getPnlPrincipal().getJcmbCategoria().getSelectedIndex() == 0) {
                // JOptionPane.showMessageDialog(null, "pesquisa por matricula");
                pesquisaPor("numMatricula = '" + visao.getPnlPrincipal().getJtxtPesquisar().getText() + "' ORDER BY numMatricula;");
            }
            // 1 - nome
            if (visao.getPnlPrincipal().getJcmbCategoria().getSelectedIndex() == 1) {
                //JOptionPane.showMessageDialog(null, "pesquisa por nome");
                pesquisaPor("nome like '%" + visao.getPnlPrincipal().getJtxtPesquisar().getText() + "%' ORDER BY nome;");
            }
            // 2 - endereço
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
            // 6 - classifivação
            if (visao.getPnlPrincipal().getJcmbCategoria().getSelectedIndex() == 6) {
                //JOptionPane.showMessageDialog(null, "pesquisa por classificacao");
                pesquisaPor("classificacao like '%" + visao.getPnlPrincipal().getJtxtPesquisar().getText() + "%' ORDER BY classificacao;");
            }
            if (visao.getPnlPrincipal().getJtxtPesquisar().getText().equals("")) {
                try {
                    JTable tabela = visao.getPnlPrincipal().getjTable();
                    TableModel mod;
                    TabelaPadrao padrao = new TabelaPadrao();
                    //System.out.println("SELECT * FROM orientador where " + pesquisa + " = " + visao.getPnlPrincipal().getJtxtPesquisar().getText() + ";");
                    mod = padrao.retornaTableModel("SELECT * FROM aluno;");
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

    private void atualizaTabela(JTable tabela) { //método para atualizar os registros na tabela
        try {
            //JTable tabela = visao.getPnlPrincipal().getjTable();
            TableModel mod;
            TabelaPadrao padrao = new TabelaPadrao();
            mod = padrao.retornaTableModel("SELECT numMatricula, nome, classificacao, telefone FROM aluno");
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

    private void setDadosInterface(AlunoModelo aluno) { //preenche a interface com os dados do banco de dados
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
                for (int i = 0; i < visao.getPnlDados().getjComboBox1().getItemCount(); i++) {
                    if (visao.getPnlDados().getjComboBox1().getItemAt(i).equals(aluno.getClassifica())) {
                        usar = i;
                    }
                }
                visao.getPnlDados().getjComboBox1().setSelectedIndex(usar);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControleAluno.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String[] getDadosInterface() { //retorna os dados na interface para um vetor de strings
        String dados[] = {visao.getPnlDados().getJtfMatricula().getText(),
            visao.getPnlDados().getJftNome().getText(),
            visao.getPnlDados().getJftDataNasc().getText(),
            String.valueOf(visao.getPnlDados().getjComboBox1().getSelectedIndex()),
            visao.getPnlDados().getJtfRua().getText(),
            visao.getPnlDados().getJtfNroRua().getText(),
            visao.getPnlDados().getJtfBairro().getText(),
            visao.getPnlDados().getJtfCidade().getText(),
            visao.getPnlDados().getJftCEP().getText(),
            visao.getPnlDados().getJftTelefone().getText()};
        return dados;
    }

    public void visRegistro(String value) {
        String SQL = "SELECT * FROM aluno WHERE numMatricula= " + value;
        ResultSet res;
        try {
            {
                SQLAluno SqlAluno = new SQLAluno();
                res = SqlAluno.getStmt().executeQuery(SQL);
                res.next();
                // Tem que organizar a ordem de visao dos registros.

                //joga-se os valores nos jtextfields
                visao.getPnlDados().getJftNome().setText(res.getString(1));
                visao.getPnlDados().getJftDataNasc().setText(res.getString(2));
                visao.getPnlDados().getJtfRua().setText(res.getString(3));
                visao.getPnlDados().getjComboBox1().setSelectedIndex(res.getInt(4));
                visao.getPnlDados().getJtfNroRua().setText(res.getString(5));
                visao.getPnlDados().getJtfBairro().setText(res.getString(6));
                visao.getPnlDados().getJtfCidade().setText(res.getString(7));
                visao.getPnlDados().getJftCEP().setText(res.getString(8));
                visao.getPnlDados().getJftTelefone().setText(res.getString(9));
            }

            //Esta variável é para ter o controle na alteração
            // nomeA = this.jTFNomeAutor.getText();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean validaCampos(JPanel p) {
        for (int i = 0; i < p.getComponentCount(); i++) {
            if (p.getComponent(i) instanceof JFormattedTextField) {
                JFormattedTextField f = (JFormattedTextField) p.getComponent(i);
                if (validaCampos(f) == false) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validaCampos(JFormattedTextField f) {
        if (f.getText().trim().equals("")) {
            f.requestFocus();
            f.setBackground(Color.green);
            return false;
        } else {
            f.setBackground(Color.white);
            f.setText(f.getText().toUpperCase());
            return true;
        }
    }

    public void actionPerformed(ActionEvent evt) {
        // Montando o vetor com os dados a serem salvos
        //    String[] valor = null;/*= new String();*/
        if (evt.getActionCommand().equals("INCLUIR")) {
            acao = "inserir";
            try {
                SQLAluno sqlA = new SQLAluno();
                AlunoModelo alunos = sqlA.last();
                if (alunos == null) {
                    visao.getPnlDados().getJtfMatricula().setText("1");
                } else {
                    visao.getPnlDados().getJtfMatricula().setText(String.valueOf(alunos.getMatricula() + 1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControleAluno.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getActionCommand().equals("CANCELAR")) {
            try {
                SQLAluno a = new SQLAluno();
                atualizaInterface(true, false);
                setDadosInterface(a.first());
            } catch (SQLException ex) {
                Logger.getLogger(ControleAluno.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getActionCommand().equals("SALVAR")) {
            //if (validaCampos(visao.getPnlDados())) {
                try {
                    // Criando o vetor que sera salvo na base de dados.
                    //Ordem dos valores: numMatricula, nome, dataNasc, classificacao, rua, numRua, bairro ,cep, telefone
                    String[] val = getDadosInterface();
                    SQLAluno sql = new SQLAluno();
                    AlunoModelo al = new AlunoModelo();
                    if (acao.equals("inserir")) {
                        sql.insRegistros(val);
                    } else if (acao.equals("editar")) {
                        sql.atRegistros(val, val[0]);
                    }
                    al = sql.first();
                    atualizaInterface(true, false);
                    atualizaTabela(visao.getPnlPrincipal().getjTable());
                    setDadosInterface(al);//coloca os dados do primeiro aluno na tela
                } catch (SQLException ex) {
                    Logger.getLogger(ControleAluno.class.getName()).log(Level.SEVERE, null, ex);
                }

            //} else {
             //   JOptionPane.showMessageDialog(null, "É necessário que termine de preencher todos os campos do cadastro antes que você possa salva-lo");
            //}
        } else if (evt.getActionCommand().equals("EDITAR")) {
            try {
                acao = "editar";
                SQLAluno a = new SQLAluno();
                AlunoModelo model = a.localizar(Integer.parseInt(visao.getPnlDados().getJtfMatricula().getText()));
                if (model != null) {
                    atualizaInterface(false, true);
                    setDadosInterface(model);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControleAluno.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (evt.getActionCommand().equals("EXCLUIR")) {
            if (!visao.getPnlDados().getJtfMatricula().getText().equals("")) {
                if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Confirma", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    try {
                        SQLAluno al = new SQLAluno();
                        al.excluir(visao.getPnlDados().getJtfMatricula().getText());
                        aluno = null;
                        aluno = al.first();
                    } catch (SQLException ex) {
                        Logger.getLogger(ControleAluno.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    atualizaInterface(true, false);
                    atualizaTabela(visao.getPnlPrincipal().getjTable());
                    setDadosInterface(aluno);
                }
            }
        } else if (evt.getActionCommand().equals("SAIR")) {
        }
    }
}
