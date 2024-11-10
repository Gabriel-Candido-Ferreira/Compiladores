package Lexico;

import java.util.Stack;

public class Analisador {
    Entrada ent;
    Stack<Token> pilha;

    public Analisador(String arquivo) {
        ent = new Entrada(arquivo);
        pilha = new Stack<>();
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
                    case "repeticao":
                        return new Token(TipoToken.PCRepeticao, palavra);
                    case "completada":
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

    public boolean analisarExpressao() {
        Token token;
        while ((token = proximoToken()) != null) {
            if (token.nome == TipoToken.AbreChave) {
                pilha.push(token);
            } else if (token.nome == TipoToken.FechaChave) {
                if (pilha.isEmpty() || pilha.pop().nome != TipoToken.AbreChave) {
                    System.out.println("Erro de sintaxe: Chave fechada sem uma abertura correspondente.");
                    return false;
                }
            }
            // Outros casos de análise podem ser adicionados aqui
        }

        if (!pilha.isEmpty()) {
            System.out.println("Erro de sintaxe: Chave aberta sem fechamento correspondente.");
            return false;
        }

        return true;
    }

    public void interpretar() {
        Token token;
        while ((token = proximoToken()) != null) {
            switch (token.nome) {
                case PCExercicio:
                    System.out.println("Iniciando exercício: " + token.lexema);
                    break;
                case PCSerie:
                    System.out.println("Iniciando série: " + token.lexema);
                    break;
                case PCRepeticao:
                    System.out.println("Repetição: " + token.lexema);
                    break;
                case PCSe:
                    System.out.println("Condição SE encontrada");
                    break;
                case PCEntao:
                    System.out.println("Então executando...");
                    break;
                case PCSenao:
                    System.out.println("Executando SENÃO...");
                    break;
                case PCEnquanto:
                    System.out.println("Executando ENQUANTO...");
                    break;
                default:
                    System.out.println("Token não reconhecido: " + token);
                    break;
            }
        }
    }
}