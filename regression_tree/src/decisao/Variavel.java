package decisao;

import instacia.Dados;
import programa.Programa;

public class Variavel implements Programa {
    private int index;

    public Variavel(int index){
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public double processa(int linha, Dados data) {
        return index;
    }

    @Override
    public Variavel clone(){
        return new Variavel(index);
    }

    @Override
    public String toString(){
        return String.valueOf(index);
    }
}
