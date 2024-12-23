package Lexico;

import javax.swing.*;

public class Principal {
    public static void main(String[] args) {
        String caminho = "";
        JFileChooser fc = new JFileChooser();
        int retorno = fc.showOpenDialog(null);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            caminho = fc.getSelectedFile().getAbsolutePath();
            System.out.println(caminho);
        }

        Analisador ent = new Analisador(caminho);
        Token token = null;

        while ((token = ent.proximoToken()) != null){
            System.out.println(token);
        }
    }
}
