package regressao;

import decisao.Condicional;
import programa.Arvore;
import programa.Programa;
import instacia.Dados;

import java.util.Random;

public class Regressao implements Arvore {
    Random random;
    Dados data;

    public Regressao(Random random, Dados data){

        this.random = random;
        this.data = data;
    }

    public Programa geraNo(int depth){
        if(depth == 1){
            if(random.nextDouble() > 0.5)
                return new Variable(random.nextInt(30)+2);
            else
                return new Constant(random.nextDouble()*2-1);
        }else{

            return new Operation(this.geraNo(depth-1),
                                 this.geraNo(depth-1),
                                 Sign.byInteger(random.nextInt(3)));
        }
    }

    public Programa crossOver(Programa crossA, Programa crossB, int prof){
        if(random.nextDouble() > 1.0/(prof*prof)){
            Condicional auxA = (Condicional) crossA;
            Condicional auxB = (Condicional) crossB;
            if(random.nextDouble() < 0.5) {
                auxA.setEsquerda(crossOver(auxA.getEsquerda(), auxB.getEsquerda(), prof - 1));
                auxB.setEsquerda(crossOver(auxA.getEsquerda(), auxB.getEsquerda(), prof - 1));
            }
            else {
                auxA.setDireita(crossOver(auxA.getDireita(),auxB.getDireita(), prof - 1));
                auxB.setDireita(crossOver(auxA.getDireita(), auxB.getDireita(), prof - 1));
            }
        } else {
            crossA = crossB;
        }
        return crossA;
    }

    public Programa mutacao(Programa muta, int prof){
        if(random.nextDouble() > 1.0/(prof*prof)){
            Condicional aux = (Condicional) muta;
            if(random.nextDouble() < 0.5) {
                aux.setEsquerda(mutacao(aux.getEsquerda(), prof - 1));
            }
            else {
                aux.setDireita(mutacao(aux.getDireita(), prof - 1));
            }
        } else {
            muta = geraNo(random.nextInt(4) + 1);
        }

        return muta;
    }

}
