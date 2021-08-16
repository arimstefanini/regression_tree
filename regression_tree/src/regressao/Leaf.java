package regressao;

import programa.Programa;
import instacia.Dados;

public class Leaf implements Programa {

    private int visitas = 0;
    private Programa expr;

    public Leaf(Programa expr){
        this.expr = expr;
    }

    @Override
    public double processa(int linha, Dados data){
        visitas++;
        return 1/(1 + Math.exp(-this.expr.processa(linha, data)));
    }

    public int getVisitas(){
        return visitas;
    }

    @Override
    public String toString(){
        return "return "+expr.toString();
    }

    @Override
    public Leaf clone(){
        return new Leaf(this.expr.clone());
    }
}
