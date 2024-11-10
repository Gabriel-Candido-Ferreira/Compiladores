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
        if (ent.analisarExpressao()) {
            System.out.println("Expressão válida.");
            ent.interpretar();
        } else {
            System.out.println("Expressão inválida.");
        }
    }
}