package decisao;

import instacia.Dados;
import programa.Arvore;
import programa.Programa;

import java.util.Random;

public class Decisao implements Arvore {

    private Dados dados;
    private Random random;

    public Decisao(Dados dados, Random random){
        this.dados = dados;
        this.random = random;
    }

    public Programa geraNo(int prof){
        Variavel variavel = new Variavel(random.nextInt(30)+2);
        double vMax = dados.max[variavel.getIndex()];
        double vMin = dados.min[variavel.getIndex()];
        Constante constante = new Constante((random.nextDouble()*(vMax - vMin)) + vMin);

        if(prof == 1)
            return new Folha(random.nextInt(2));
        else
        if(random.nextDouble() < 0.5)
            return new Condicional(geraNo(prof-1),
                                   geraNo(prof-1),
                                   variavel.getIndex(),
                                   OperadorInequacao.MAIOR_OU_IGUAL,
                                   constante.getConstante());
        else
            return new Condicional(geraNo(prof-1),
                                   geraNo(prof-1),
                                   variavel.getIndex(),
                                   OperadorInequacao.MENOR_OU_IGUAL,
                                   constante.getConstante());
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
