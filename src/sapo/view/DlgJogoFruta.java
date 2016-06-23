/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgJogoFruta.java
 *
 * Created on 28/10/2009, 23:46:05
 */
package sapo.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Date;
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
 * @author christino
 */
public class DlgJogoFruta extends javax.swing.JDialog {
    //a intenção deste vetor é facilitar o acesso aos "Labels"

    private final int CODIGO_JOGO = 2;
    private Vector<ImgSapo> vetorImagens;
    private ArrastarMouse arrasta;
    private SQLFases sqlFases;
    private SQLHistorico sqlHistorico;
    private HistoricoModelo modeloHistorico;
    private OrientadorModelo modeloOrientador;
    private SimpleDateFormat fomatoHora = new SimpleDateFormat("hh:mm:ss");
    private int codigoAluno, codigoFase, nivelDificuldade;

    public void incrementaFase() {
        codigoFase++;
    }

    /** Creates new form DlgJogoFruta */
    public DlgJogoFruta(java.awt.Frame parent, boolean modal, OrientadorModelo orientador) {
        super(parent, modal);
        modeloOrientador = orientador;

        initComponents();
        setTitle("Jogo Frutas no Cesto");
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(d);
        this.setLocation(0, 0);


        //selecionar a criança e a fase
        DlgSelectAluno select = new DlgSelectAluno(null, true, CODIGO_JOGO);
        select.setVisible(true);

        arrasta = new ArrastarMouse(this);
        arrasta.setJLblDestino(jLblCesto);

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

    public void carregarNovoJogo() {

        vetorImagens = new Vector<ImgSapo>();

        modeloHistorico = new HistoricoModelo();
        modeloHistorico.setAlunoMatricula(codigoAluno);
        modeloHistorico.setCodigoOrientador(modeloOrientador.getCodigo());
        modeloHistorico.setCodigoFase(codigoFase);
        modeloHistorico.setFaseJogoCodigo(CODIGO_JOGO);
        modeloHistorico.setQtdeErros(0);
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
            //     JOptionPane.showMessageDialog(null, codigoFase);
            rs = sqlFases.retornaFases_IS(codigoFase, CODIGO_JOGO); //aqui deve pegar a ult.fase registrada no historico do aluno
            while (rs.next()) {
                Fase_IS fase = new Fase_IS(rs.getInt("idFases_IS"),
                        rs.getString("URL_imagem"), rs.getString("URL_sons"), rs.getString("nome_is"));
                ImgSapo imgSapo = new ImgSapo(fase);
                vetorImagens.add(imgSapo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DlgJogoFruta.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "erro" + ex);
        }

        organizaLabels();
        setMouse();
        if (vetorImagens.size() > 0) {
            int numero = new Random().nextInt(vetorImagens.size() - 1);

            arrasta.setJLblOrigem(vetorImagens.get(numero));

            vetorImagens.get(numero).executa();

        } else {
            JOptionPane.showMessageDialog(null, "Jogo Terminado!");
            this.setVisible(false);
            this.dispose();
        }


    }

    public void gravaHistoricoJogo() {
        modeloHistorico.setHoraFinal(fomatoHora.format(new java.util.Date()));
        try {
            modeloHistorico.setQtdeErros(arrasta.getContador());
        } catch (Exception e) {
            modeloHistorico.setQtdeErros(0);
        }
        sqlHistorico.insRegistros(modeloHistorico);
    }

    public int verificaNivelDificuldade() {
        int cont = 0;
        for (int i = 0; i < jPnlJogo.getComponentCount(); i++) {
            if (jPnlJogo.getComponent(i) instanceof JLabel) {
                cont++;
            }
        }
        return (arrasta.getContador() / cont) * 100;
    }

    public void organizaLabels() {
        //para adicionar as frutas em ordem aleatoria
        //primeiramente duplica-se o vetor de frutas e vai-se adicinadando em ordem aletaoria as frutas ao formulario
        Random r = new Random();
        Vector<ImgSapo> vetorAux = (Vector<ImgSapo>) vetorImagens.clone();
        for (int i = vetorImagens.size(); i > 0; i--) {
            int j = r.nextInt(i);
            jPnlJogo.add(vetorAux.get(j));
            vetorAux.remove(j);
        }
    }

    public void limpaTela() {
        arrasta.setJLblOrigem(null);

        for (int i = vetorImagens.size() - 1; i >= 0; i--) {
            vetorImagens.get(i).setVisible(false);
            jPnlJogo.remove(vetorImagens.get(i));
            vetorImagens.set(i, null);
        }
    }

    public void setMouse() {
        for (int i = 0; i < vetorImagens.size(); i++) {
            vetorImagens.get(i).addMouseListener(arrasta);
            vetorImagens.get(i).addMouseMotionListener(arrasta);
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

        jPnlJogo = new javax.swing.JPanel();
        jLblCesto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPnlJogo.setLayout(new java.awt.GridLayout(4, 8));

        jLblCesto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/cesto3.png"))); // NOI18N
        jPnlJogo.add(jLblCesto);

        getContentPane().add(jPnlJogo);

        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Dimension dialogSize = getSize();
        setLocation((screenSize.width-dialogSize.width)/2,(screenSize.height-dialogSize.height)/2);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLblCesto;
    private javax.swing.JPanel jPnlJogo;
    // End of variables declaration//GEN-END:variables
}
