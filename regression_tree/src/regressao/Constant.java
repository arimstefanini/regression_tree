package regressao;

import programa.Programa;
import instacia.Dados;

public class Constant implements Programa {
    private final double constante;

    public Constant(double constante){
        this.constante = constante;
    }

    @Override
    public double processa(int linha, Dados data) {
        return constante;
    }

    @Override
    public Constant clone() {
        return new Constant(this.constante);
    }

    @Override
    public String toString(){
        return String.valueOf(constante);
    }
}
