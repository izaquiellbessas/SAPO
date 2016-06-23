/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sapo.controller;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import sapo.util.Audio;
import sapo.view.DlgJogoFigura;
import sapo.view.DlgJogoFruta;

/**
 *
 * @author wsouza
 */
public class ArrastarMouse extends MouseAdapter implements MouseMotionListener {

    private int baseX = -1;
    private int baseY = -1;
    private DlgJogoFruta fruta;
    private DlgJogoFigura figura;
    private JLabel JLblDestino;
    private JLabel JLblOrigem;
    private int contador;

    public ArrastarMouse(DlgJogoFruta frame) {
        this.fruta = frame;
        this.contador = 0;
    }

    public ArrastarMouse(DlgJogoFigura frame) {
        this.figura = frame;
        this.contador = 0;
    }

    @Override
    public void mouseDragged(MouseEvent e) {  //metodo para mover o componente clicado
        Component comp = e.getComponent(); //pega o componente
        if ((this.baseX != -1) && (this.baseY != -1)) { //verifica se as constantes mudaram de valor
            int x = comp.getX() + e.getX() - this.baseX;
            int y = comp.getY() + e.getY() - this.baseY;
            comp.setLocation(x, y); //reposiciona o componente
            comp.getParent().repaint(); //atualiza a tela
            }
        /*fruta.getjTFDestinoX().setText(String.valueOf(fruta.getjPanel1().getX()));
        fruta.getjTFDestinoY().setText(String.valueOf(fruta.getjPanel1().getY()));
        fruta.getjTFMouseX().setText(String.valueOf(fruta.getjLMouse().getX()));
        fruta.getjTFMouseY().setText(String.valueOf(fruta.getjLMouse().getY()));*/
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) { //pega a posicao em que o origem foi clicado
        this.baseX = e.getX();
        this.baseY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) { //evento para qdo o botao do origem é liberado
        this.baseX = -1;
        this.baseY = -1;
        contador++;

        // realiza a validação das duas telas e suas posiçoes
        // ordem de uso (objeto_de_destino,Objeto_que_será_movido_pelo_mouse,sensitive No grid X, sensitivi no gridY)
        if (!pegaPosicaoCerta(JLblDestino, JLblOrigem, 10, 10)) {
            if (contador == 1) {
                Audio a = new Audio();
                a.executa("/src/sons/[erro]confundiu.wav");
            } else if (contador == 2) {
                Audio a = new Audio();
                a.executa("/src/sons/[erro]continuetentando.wav");
            } else if (contador == 3) {
                Audio a = new Audio();
                a.executa("/src/sons/[erro]figuraerrada.wav");
            } else if (contador == 4) {
                Audio a = new Audio();
                a.executa("/src/sons/[erro]quasela.wav");
            } else if ((contador % 2 == 0) && (contador > 4)) {
                Audio a = new Audio();
                a.executa("/src/sons/[erro]tenteoutravez.wav");
            } else if ((contador % 2 != 0) && (contador > 4)) {
                Audio a = new Audio();
                a.executa("/src/sons/[erro]tenteoutravez2.wav");
            }
        }
        Component comp = e.getComponent(); //pega o componente arrastadeo
        Dimension tam = JLblDestino.getSize(); //pega a dimensao do painel de destino
        Point p = JLblDestino.getLocation();
        int posX = p.x; //pega o ponto x do painel
        int posY = p.y; //pega o ponto y do painel
        //com os dados anteriores vai se verificar se o label está na area demarcada
        int height = tam.height; //pega a largura do painel
        int width = tam.width; //pega a algura do painel
        int a = e.getX();
        int b = e.getY();

    }

    public boolean pegaPosicaoCerta(JLabel destino, JLabel origem, int sensibilidadeX, int sensibilidadeY) {
        boolean posicao = false;
        int mouseX, mouseY, destinoX, destinoY;

        // Calculando os valores do centro dos componentes
        mouseX = (origem.getX() + origem.getWidth()) / 2;
        mouseY = (origem.getY() + origem.getHeight()) / 2;
        destinoX = (destino.getX() + destino.getWidth()) / 2;
        destinoY = (destino.getY() + destino.getHeight()) / 2;

        // verificando se as posições estao certas entre o centro dos componentes e a sensibilidade de deteção.
        if ((destinoX == mouseX) && (destinoY == mouseY)) {
            // as posiçoes dos dois componentes coincidem perfeitamente
            JOptionPane.showMessageDialog(null, "Parabéns, voce acertou na mosca! \nVocê errou " + (contador - 1) + " vezes");
            Audio a = new Audio();
            a.executa("/src/sons/[certo]parabens2.wav");
            if (fruta != null) {
                fruta.limpaTela();
                fruta.gravaHistoricoJogo();
                fruta.incrementaFase();
                fruta.carregarNovoJogo();
                contador = 0;

            } else if (figura != null) {

                figura.limpaTela();
                figura.gravaHistoricoJogo();
                figura.incrementaFase();
                figura.carregarNovoJogo();

            }
            return true;
        } else if ((mouseX <= (destinoX + sensibilidadeX)) && (mouseX >= (destinoX - sensibilidadeX))) {
            if ((mouseY <= (destinoY + sensibilidadeY)) && (mouseY >= (destinoY - sensibilidadeY))) {
                // as posiçoes estao dentro so sensitive desejado
                JOptionPane.showMessageDialog(null, "Parabéns, voce acertou! \nVocê errou " + (contador - 1) + " vezes");
                Audio a = new Audio();
                a.executa("/src/sons/[certo]parabens3.wav");
                if (fruta != null) {
                    fruta.limpaTela();
                    fruta.gravaHistoricoJogo();
                    fruta.incrementaFase();
                    fruta.carregarNovoJogo();
                    contador = 0;
                } else if (figura != null) {
                    figura.gravaHistoricoJogo();
                    figura.limpaTela();
                    figura.gravaHistoricoJogo();
                    figura.incrementaFase();
                    figura.carregarNovoJogo();
                    contador = 0;
                }
            }
            return true;
        }
        return posicao;
    }

    //getters e setters
    public JLabel getJLblDestino() {
        return JLblDestino;
    }

    public void setJLblDestino(JLabel JLblDestino) {
        this.JLblDestino = JLblDestino;
    }

    public JLabel getJLblOrigem() {
        return JLblOrigem;
    }

    public void setJLblOrigem(JLabel JLblOrigem) {
        this.JLblOrigem = JLblOrigem;
    }

    public DlgJogoFigura getFigura() {
        return figura;
    }

    public void setFigura(DlgJogoFigura figura) {
        this.figura = figura;
    }

    public DlgJogoFruta getFruta() {
        return fruta;
    }

    public void setFruta(DlgJogoFruta fruta) {
        this.fruta = fruta;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
}

