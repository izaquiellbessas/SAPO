package sapo;

import sapo.conexao.Conectar;
import sapo.conexao.Usuario;
import sapo.view.DlgLogin;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author home
 */
public class Main {

    public static Conectar con = new Conectar();
    public static Usuario us = new Usuario("sapopasswd", "saporoot", "jdbc:mysql://localhost/sapo");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        con.conecte(us);
        DlgLogin log = new DlgLogin(null, true);
        log.setVisible(true);
    }

    public static Conectar getCon() {
        return con;
    }

    public static void setCon(Conectar con) {
        Main.con = con;
    }

    public static Usuario getUs() {
        return us;
    }

    public static void setUs(Usuario us) {
        Main.us = us;
    }
}
