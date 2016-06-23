/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgJogoFigura.java
 *
 * Created on 04/11/2009, 15:48:01
 */
package sapo.view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import sapo.controller.ArrastarMouse;
import sapo.controller.SQLFases;
import sapo.controller.SQLHistorico;
import sapo.model.Fase_IS;
import sapo.model.HistoricoModelo;
import sapo.model.OrientadorModelo;
import sapo.util.ImgSapo;

/**
 *
 * @author izaquiel
 */
public class DlgJogoFigura extends javax.swing.JDialog {

    private final int CODIGO_JOGO = 1;
    private ArrastarMouse arr;
    private ImgSapo imagemDestino;
    private OrientadorModelo orientador;
    private Vector<ImgSapo> vetorImagens;
    private SQLFases sqlFases;
    private SQLHistorico sqlHistorico;
    private HistoricoModelo modeloHistorico;
    private SimpleDateFormat fomatoHora = new SimpleDateFormat("hh:mm:ss");
    private int codigoAluno,  codigoFase,  nivelDificuldade;

    public OrientadorModelo getOrientador() {
        return orientador;
    }

    public void setOrientador(OrientadorModelo orientador) {
        this.orientador = orientador;
    }

    public void limpaTela() {
        arr.setJLblOrigem(null);
        jpnlImagens.remove(imagemDestino);
        arr.setJLblDestino(null);
        imagemDestino = null;

        for (int i = vetorImagens.size() - 1; i >= 0; i--) {
            vetorImagens.get(i).setVisible(false);
            jpnlImagens.remove(vetorImagens.get(i));
            vetorImagens.set(i, null);
        }

    }

    /** Creates new form DlgJogoFigura */
    public DlgJogoFigura(java.awt.Frame parent, boolean modal, OrientadorModelo orienta) {
        super(parent, modal);
        initComponents();
        arr = new ArrastarMouse(this);
        this.orientador = orienta;

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(d);
        this.setLocation(0, 0);


        //selecionar a criança e a fase
        DlgSelectAluno select = new DlgSelectAluno(null, true, CODIGO_JOGO);
        select.setVisible(true);


        if ((select.isConfirmado()) && (select.codigoAlunoSelecionado() > -1)) {
            codigoAluno = select.codigoAlunoSelecionado();
            //caso tenha selecionado uma fase o jogo continuara a partit desta
            codigoFase = select.codigoFaseSelecionada();
            if (codigoFase <= 0) {
                codigoFase = 1;
            }

            select = null;
            JOptionPane.showMessageDialog(null, "Aluno selecionado.: " + codigoAluno);

            try {
                sqlHistorico = new SQLHistorico();
            } catch (SQLException ex) {
                Logger.getLogger(DlgJogoFruta.class.getName()).log(Level.SEVERE, null, ex);
            }
            carregarNovoJogo();
            this.setVisible(true);

        }//nenhum aluno selecionado deve sair do jogo
        else {
            this.setVisible(false);
            this.dispose();
        }

    }

    public int verificaNivelDificuldade() {
        int cont = 0;
        for (int i = 0; i < jpnlImagens.getComponentCount(); i++) {
            if (jpnlImagens.getComponent(i) instanceof JLabel) {
                cont++;
            }
        }
        return (arr.getContador() / cont) * 100;
    }

    public void carregarNovoJogo() {



        if (vetorImagens != null) {
            for (int i = 0; i < vetorImagens.size(); i++) {
                vetorImagens.set(i, null);
            }
        }


        vetorImagens = new Vector<ImgSapo>();

        modeloHistorico = new HistoricoModelo();
        modeloHistorico.setAlunoMatricula(codigoAluno);
        modeloHistorico.setCodigoOrientador(orientador.getCodigo());
        modeloHistorico.setCodigoFase(codigoFase);
        modeloHistorico.setFaseJogoCodigo(CODIGO_JOGO);
        java.util.Date dataJava = new java.util.Date();

        modeloHistorico.setData(new Date(new GregorianCalendar().getTimeInMillis()));
        modeloHistorico.setHoraInicial(fomatoHora.format(new java.util.Date()));
        //verificar o que controlalar o nivel
        try {
            modeloHistorico.setNivelDificuldade(verificaNivelDificuldade());
        } catch (Exception e) {
            modeloHistorico.setNivelDificuldade(0);
        }
        ResultSet rs = null;
        //

        try {
            //carregar as imagens
            sqlFases = new SQLFases();
            //JOptionPane.showMessageDialog(null, codigoFase+"  "+CODIGO_JOGO);

            rs = sqlFases.retornaFases_IS(codigoFase, CODIGO_JOGO); //aqui deve pegar a ult.fase registrada no historico do aluno
            if (rs.next()) {

                Fase_IS fase = new Fase_IS(rs.getInt("idFases_IS"), rs.getString("URL_imagem"), rs.getString("URL_sons"), rs.getString("nome_is"));
                //ImgSapo imgSapo = new ImgSapo(fase);
                imagemDestino = new ImgSapo(fase);

                jpnlImagens.add(imagemDestino);
                arr.setJLblDestino(imagemDestino);
                rs.previous();


                while (rs.next()) {
                    fase = new Fase_IS(rs.getInt("idFases_IS"),
                            rs.getString("URL_imagem"), rs.getString("URL_sons"), rs.getString("nome_is"));
                    ImgSapo imgSapo = new ImgSapo(fase);
                    vetorImagens.add(imgSapo);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DlgJogoFruta.class.getName()).log(Level.SEVERE, null, ex);
        }

        organizaLabels();
        if (vetorImagens.size() > 0) {
            setMouse();
            arr.setJLblOrigem(vetorImagens.get(0));
            vetorImagens.get(0).executa();
        } else {
            JOptionPane.showMessageDialog(null, "Jogo Terminado!");
            this.setVisible(false);
            this.dispose();
        }





    }

    public void comeacarDinovo() {
    }

    public void organizaLabels() {
        Random r = new Random();
        Vector<ImgSapo> vetorAux = (Vector<ImgSapo>) vetorImagens.clone();
        for (int i = vetorImagens.size(); i > 0; i--) {
            int j = r.nextInt(i);
            jpnlImagens.add(vetorAux.get(j));
            vetorAux.remove(j);


        }
    }

    public void gravaHistoricoJogo() {

        modeloHistorico.setHoraFinal(fomatoHora.format(new java.util.Date()));
        try {
            modeloHistorico.setQtdeErros(arr.getContador());
        } catch (Exception e) {
            modeloHistorico.setQtdeErros(0);
        }
        sqlHistorico.insRegistros(modeloHistorico);


    }

    public void incrementaFase() {
        codigoFase++;
    }

    public void setMouse() {
        for (int i = 0; i < jpnlImagens.getComponentCount(); i++) {
            if (jpnlImagens.getComponent(i) instanceof JLabel) {
                JLabel l = (JLabel) jpnlImagens.getComponent(i);
                l.addMouseListener(arr);
                l.addMouseMotionListener(arr);
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnlJogo = new javax.swing.JPanel();
        jpnlImagens = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Jogo - SAPO - Aprender Brincando");
        setBackground(new java.awt.Color(0, 102, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jpnlJogo.setLayout(new java.awt.BorderLayout());

        jpnlImagens.setLayout(new java.awt.GridLayout());
        jpnlJogo.add(jpnlImagens, java.awt.BorderLayout.CENTER);

        getContentPane().add(jpnlJogo);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-869)/2, (screenSize.height-451)/2, 869, 451);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jpnlImagens;
    private javax.swing.JPanel jpnlJogo;
    // End of variables declaration//GEN-END:variables
}
