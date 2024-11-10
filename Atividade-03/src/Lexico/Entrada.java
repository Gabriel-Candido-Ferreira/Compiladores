package Lexico;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Entrada {
    InputStream is;
    public Entrada(String arquivo) {
        try{
            is = new FileInputStream(new File(arquivo));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int LerProximoCaractere(){
        try{
            int i = is.read();
            System.out.print((char) i);

            return i;
        }catch (Exception e){
            return -1;
        }
    }
}