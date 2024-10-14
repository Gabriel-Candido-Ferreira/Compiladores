package Lexico;

public class Analisador {
    Entrada ent;

    public Analisador(String arquivo) {
        ent = new Entrada(arquivo);
    }

    public Token proximoToken() {
        int caractereLido;
        while ((caractereLido = ent.LerProximoCaractere()) != -1) {
            char c = (char) caractereLido;
            
            if (c == ' ' || c == '\n') continue;

            if (c == '{') {
                return new Token(TipoToken.AbreChave, "{");
            } else if (c == '}') {
                return new Token(TipoToken.FechaChave, "}");
            } else if (c == '(') {
                return new Token(TipoToken.AbrePar, "(");
            } else if (c == ')') {
                return new Token(TipoToken.FechaPar, ")");
            } else if (c == ',') {
                return new Token(TipoToken.Virgula, ",");
            } else if (Character.isDigit(c)) {
                StringBuilder num = new StringBuilder();
                num.append(c);
                while (Character.isDigit(c = (char) ent.LerProximoCaractere())) {
                    num.append(c);
                }
                return new Token(TipoToken.NumInt, num.toString());
            } else if (Character.isAlphabetic(c)) {
                StringBuilder ident = new StringBuilder();
                ident.append(c);
                while (Character.isAlphabetic(c = (char) ent.LerProximoCaractere()) || Character.isDigit(c)) {
                    ident.append(c);
                }
                String palavra = ident.toString();

                switch (palavra) {
                    case "exercicio":
                        return new Token(TipoToken.PCExercicio, palavra);
                    case "serie":
                        return new Token(TipoToken.PCSerie, palavra);
                    case "se":
                        return new Token(TipoToken.PCSe, palavra);
                    case "então":
                        return new Token(TipoToken.PCEntao, palavra);
                    case "senao":
                        return new Token(TipoToken.PCSenao, palavra);
                    case "enquanto":
                        return new Token(TipoToken.PCEnquanto, palavra);
                    case "imprimir":
                        return new Token(TipoToken.Identificador, palavra);
                    default:
                        return new Token(TipoToken.Identificador, palavra);
                }
            } else if (c == '<') {
                c = (char) ent.LerProximoCaractere();
                if (c == '>') {
                    return new Token(TipoToken.OprRelDif, "<>");
                } else if (c == '=') {
                    return new Token(TipoToken.OprRelMenorIgual, "<=");
                } else {
                    return new Token(TipoToken.OprRelMenor, "<");
                }
            } else if (c == '=') {
                return new Token(TipoToken.OprRelIgual, "=");
            }
        }
        return null;
    }
}