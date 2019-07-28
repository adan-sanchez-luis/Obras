/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bayer
 */
//import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CampoDato extends JTextField implements KeyListener {
    
    private char tipo;
    private int longitud;
    private String dato;
    public CampoDato() {
        
        this.addKeyListener(this);
       
    }
     public CampoDato(String dato) {
        this.dato=dato;
        this.addKeyListener(this);
        setText(dato);
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
    
    public void setLongitud(int longitud) {
        this.longitud = longitud;
        this.setColumns(longitud);//explica cuantas columnas  estan escitas
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char k = e.getKeyChar();
        switch (Character.toUpperCase(tipo)) { //para omitir entre mayusculas y minusculas
            case 'T':
                if (!Character.isLetter(k) && k != ' ') {
                    e.consume();
                } else {
                    if ((this.getText().length() > longitud)) {
                        e.consume();
                    }
                }
                break;
            case 'E':
                if ((!(Character.isDigit(k) || k == KeyEvent.VK_BACK_SPACE))) {
                    
                        e.consume();
                    

                } else {
                    if (this.getText().length() > longitud) {
                        e.consume();
                    }
                }
                break;
            case 'D':
                if (!(Character.isDigit(k) || k == '.' || k=='-') ) {
                    e.consume();
                }
                if(k=='.' && getText().contains(".")){
                    e.consume();
                }else {
                    if (this.getText().length() > longitud) {
                        e.consume();
                    }
                }

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
