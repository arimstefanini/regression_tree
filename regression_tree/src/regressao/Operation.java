package regressao;

import decisao.ArvoreBinaria;
import programa.Programa;
import instacia.Dados;

public class Operation extends ArvoreBinaria {
    private Sign op;

    public Operation(Programa left, Programa right, Sign op){
        super(left, right);
        this.op = op;
    }

    @Override
    public Operation clone() {

        return new Operation(super.getEsquerda().clone(), super.getDireita().clone(), this.op);
    }

    @Override
    public double processa(int linha, Dados data) {
        return op.apply(super.getEsquerda().processa(linha, data),super.getDireita().processa(linha, data));
    }

    @Override
    public String toString(){
        return "("+ super.getEsquerda().toString() + " " + op.toString() + " " + super.getDireita().toString()+")";
    }
}
