/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sapo.util;

import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author wsouza
 */
public class Mascara extends MaskFormatter {

    public Mascara() {
    }

    public MaskFormatter getMaskCep() throws ParseException {
        return new MaskFormatter("##.###-###");
    }

    public MaskFormatter getMaskData() throws ParseException{
        return new MaskFormatter("##/##/####");
    }

    public MaskFormatter getMaskTel() throws ParseException{
        return new MaskFormatter("(##)####-####");
    }

    public MaskFormatter getMaskTexto(int tam) throws ParseException{
        String masc = "";
        for(int i =0; i<tam;i++){
            masc += "*";
        }
        return new MaskFormatter(masc);
    }
    
    public MaskFormatter getMaskNumero(int tam) throws ParseException{
        String masc = "";
        for(int i =0; i< tam; i++){
            masc += "#";
        }
        return new MaskFormatter(masc);
    }

    public void trim(JFormattedTextField campo){
        campo.getText().trim();
    }
}
