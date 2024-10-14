package Lexico;

public class Analisador {
    Entrada ent;
    public Analisador(String arquivo) {
        ent = new Entrada(arquivo);
    }

    public Token proximoToken(){
        int caractereLido = -1;
        while ((caractereLido = ent.LerProximoCaractere()) != -1) {
            char c = (char) caractereLido;
            if (c == ' ' || c == '\n') continue;
            if (c == ':'){
                return new Token(TipoToken.Delim, ":");
            }else if(c == '+'){
                return new Token(TipoToken.OprAritSoma, "+");
            } else if (c == '<') {
                c = (char)ent.LerProximoCaractere();
                if (c == '>'){
                    return new Token(TipoToken.OprRelDif, "<>");
                } else if (c == '=') {
                    return new Token(TipoToken.OprRelMenorIgual, "<=");
                }else{
                    return new Token(TipoToken.OprRelMenor, "<");
                }

            }
        }

        return null;
    }


}
