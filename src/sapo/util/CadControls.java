/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sapo.util;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author home
 */
public class CadControls {

    public CadControls() {
    }

    public void conteudoJPanelHabDesab(JPanel p, boolean b) {
        for (int i = 0; i < p.getComponentCount(); i++) {
            p.getComponent(i).setEnabled(b);
        }
    }

    /*public void conteudoJPanelHabDesab(JPanel p, boolean b, int cont) {
    while (cont < p.getComponentCount()) {
    if (p.getComponent(cont) instanceof JPanel) {
    conteudoJPanelHabDesab((JPanel) p.getComponent(cont), b);
    conteudoJPanelHabDesab(p, b, cont++);
    } else {
    p.getComponent(cont).setEnabled(b);
    conteudoJPanelHabDesab(p, b, cont++);
    }
    }
    }*/
    
    public boolean HabDesabObjects(JButton b1, JButton b2, JButton b3, JButton b4, JButton b5, JButton b6, JPanel p1_4, JPanel p5_6, boolean b1_b4, boolean b5_b6) {
        /*
         * Este método irá setar o atributo enable dos botões passados por argumento;
         * os argumentos de b1 até b4 terão o atributo enable setado de acordo com o primeiro boolean, o mesmo ocorrerá com o panel p1_4
         * os argumentos de b5 e b6 terão o atributo enable setado de acordo com o segundo boolean, o mesmo ocorrerá com o panle p5_6
         */
        try {
            b1.setEnabled(b1_b4);
            b2.setEnabled(b1_b4);
            b3.setEnabled(b1_b4);
            b4.setEnabled(b1_b4);
            conteudoJPanelHabDesab(p1_4, b1_b4);
            b5.setEnabled(b5_b6);
            b6.setEnabled(b5_b6);
            conteudoJPanelHabDesab(p5_6, b5_b6);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public void existRegistro(JButton excluir, JButton editar, JPanel p, JTable t) {
        if (t.getRowCount() > 0) {
            excluir.setEnabled(true);
            editar.setEnabled(true);
            conteudoJPanelHabDesab(p, true);
            t.setEnabled(true);
        } else {
            editar.setEnabled(false);
            excluir.setEnabled(false);
            conteudoJPanelHabDesab(p, false);
            t.setEnabled(false);
        }
    }

    public void limparTextos(JPanel p) {
        for (int i = 0; i <
                p.getComponentCount(); i++) {
            if (p.getComponent(i) instanceof JFormattedTextField) {
                JFormattedTextField f = (JFormattedTextField) p.getComponent(i);
                f.setText("");
            } else if (p.getComponent(i) instanceof JTextField) {
                JTextField t = (JTextField) p.getComponent(i);
                t.setText("");
            } else if (p.getComponent(i) instanceof JTextArea) {
                JTextArea a = (JTextArea) p.getComponent(i);
                a.setText("");
            }
        }
    }
}
