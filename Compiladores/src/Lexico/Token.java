package Lexico;

public class Token {
    public TipoToken nome;
    public String lexema;

    public Token(TipoToken nome, String lexema) {
        this.nome = nome;
        this.lexema = lexema;
    }
    //metodo toString para apresentar de forma facil a representação da classe Token

    public String toString(){
        return "-----" + nome + "," + lexema + "-----";
    }
}
