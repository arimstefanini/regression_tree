package decisao;

public enum OperadorInequacao {
    MAIOR_OU_IGUAL(">=") {
        @Override
        public boolean executa(double feature, double constante) {
            return feature >= constante;
        }
    },
    MENOR_OU_IGUAL("<="){
        @Override
        public boolean executa(double feature, double constante){
            return feature <= constante;
        }
    };

    private final String text;
    private static final OperadorInequacao[] asInteger = OperadorInequacao.values();

    OperadorInequacao(String text){

        this.text = text;
    }

    public static OperadorInequacao byInt(int i){

        return asInteger[i];
    }

    public abstract boolean executa(double feature, double constante);

    @Override public String toString(){

        return text;
    }

}
