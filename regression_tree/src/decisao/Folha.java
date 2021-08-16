package decisao;

import instacia.Dados;
import programa.Programa;

public class Folha implements Programa {

    private int folha;

    public Folha(int folha){
        this.folha = folha;
    }

    @Override
    public double processa(int linha, Dados dados) {
        return this.folha;
    }

    public String toString(){
        return " " + this.folha;
    }

    @Override
    public Folha clone(){

        return new Folha(this.folha);
    }
}

