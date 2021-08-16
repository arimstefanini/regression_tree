package regressao;

import programa.Programa;
import instacia.Dados;

public class Variable implements Programa {
    private int index;

    public Variable(int index){
        this.index = index;
    }

    public double processa(int linha, Dados data) {
        return data.getLabel(linha, index);
    }

    @Override
    public Variable clone() {
        return new Variable(index);
    }

    @Override
    public String toString(){
        return "data.getByIndex("+index+", linha)";
    }

}
