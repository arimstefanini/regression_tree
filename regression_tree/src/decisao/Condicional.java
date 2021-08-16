package decisao;

import instacia.Dados;
import programa.Programa;

public class Condicional extends ArvoreBinaria{

    private int feature;
    private double constante;
    private OperadorInequacao operador;

    public Condicional(Programa direita, Programa esquerda, int feature, OperadorInequacao operador, double constante){
        super(direita, esquerda);
        this.feature = feature;
        this.constante = constante;
        this.operador = operador;
    }

    @Override
    public double processa(int linha, Dados dados){
        if(operador.executa(dados.getLabel(linha,feature),constante))
            return super.getEsquerda().processa(linha,dados);
        else
            return super.getDireita().processa(linha,dados);
    }

    public String toString(){
        String c1 = "if ( " +  feature + " " + (operador == OperadorInequacao.MAIOR_OU_IGUAL? ">=" : "<=") + " " +
                constante +  " )";
        String c2 = ("\n" + this.getEsquerda().toString()).replaceAll("\n", "\n\t");

        String c3 = "\n } else {";

        String c4 = ("\n" + this.getDireita().toString()).replaceAll("\n", "\n\t");

        String c5 = "\n }";

        return c1 + c2 + c3 + c4 + c5;
    }

    @Override
    public Condicional clone(){
        return new Condicional(super.getEsquerda().clone(), super.getDireita().clone(), this.feature, this.operador, this.constante);
    }
}
