package decisao;

import instacia.Dados;
import programa.Programa;

public class Constante implements Programa {
    private final double constante;

    public Constante(double constante){

        this.constante = constante;
    }

    public double getConstante(){
        return constante;
    }

    @Override
    public double processa(int linha, Dados data) {

        return constante;
    }

    public Constante clone(){

        return new Constante(this.constante);
    }

    @Override
    public String toString(){

        return String.valueOf(constante);
    }

}
