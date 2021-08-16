package decisao;

import programa.Programa;

public abstract class ArvoreBinaria implements Programa {
    private Programa esquerda;
    private Programa direita;

    public ArvoreBinaria(Programa esquerda, Programa direita){
        this.esquerda = esquerda;
        this.direita = direita;
    }

    public Programa getEsquerda() {
        return esquerda;
    }

    public Programa getDireita() {
        return direita;
    }

    public void setEsquerda(Programa esquerda) {
        this.esquerda = esquerda;
    }

    public void setDireita(Programa direita) {
        this.direita = direita;
    }

    @Override
    public abstract ArvoreBinaria clone();
}
