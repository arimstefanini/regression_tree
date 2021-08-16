package validacao;

import programa.Arvore;
import programa.Programa;
import instacia.Dados;
import metrica.RMSE;
import metrica.Sig;

import java.util.Random;

public class Otimizador {

    private Dados dados;
    private Arvore arvore;
    private RMSE metrica;
    private double melhorScore = Double.MAX_VALUE;
    private double melhorTest = Double.MAX_VALUE;
    private Programa arvClone;
    private Programa regressaoClone;
    private Random random;
    private int interacao;

    public Otimizador(Dados dados, Arvore arvore, RMSE metrica, Random random, int iteracao){
        this.arvore = arvore;
        this.metrica = metrica;
        this.random = random;
        this.dados = dados;
        this.interacao = iteracao;
    }

    public void otimizacao() {
        double scoreAtual;
        for(int i = 0; i < interacao; i++ ) { //100000000
            Programa arv = arvore.geraNo(random.nextInt((8 - 4) + 1) + 4);
            scoreAtual = metrica.measure(arv);

            if (melhorScore > scoreAtual) {
                melhorScore = scoreAtual;
                arvClone = arv.clone();
            }
        }
    }

    public void otimizacaoTrain(KFold kFold) {
        double scoreAtual;
        Programa arv;
        for(int i = 0; i < interacao; i++ ) { //100_000_000

            if(random.nextDouble() < 0.5) {
                arv = arvore.geraNo(random.nextInt((10 - 4) + 1) + 4);

            } else {
                arv = arvore.mutacao(arvore.geraNo(random.nextInt((10 - 6) + 1) + 6), random.nextInt((6 - 4) + 1) + 4);
            }
            scoreAtual = metrica.measureTrain(arv, kFold);

            if (melhorScore > scoreAtual) {
                melhorScore = scoreAtual;
                arvClone = arv.clone();

            }
        }
    }

    public void otimizacaoRegressao() {
        double scoreAtual;
        for(int i = 0; i < interacao; i++ ) {

            Programa regressao = arvore.geraNo(random.nextInt((6 - 4) + 1) + 4);

            scoreAtual = Sig.getSig(regressao, dados);

            if (melhorScore > scoreAtual) {
                melhorScore = scoreAtual;
                regressaoClone = regressao.clone();
            }
        }
    }

    public void otimizacaoRegressaoTrain(KFold kFold) {
        double scoreAtual, testeAtual;
        for(int i = 0; i < interacao; i++ ) {

            Programa regressao = arvore.geraNo(random.nextInt((10 - 8) + 1) + 8);

            //TODO SEMAFARO
            scoreAtual = Sig.getSigTrain(regressao, dados, kFold);
            testeAtual = Sig.getSigTest(regressao, dados, kFold);

            if (melhorScore >= scoreAtual &&
                    melhorTest >= testeAtual &&
                    testeAtual > 0.1 ) {
                melhorScore = scoreAtual;
                melhorTest = testeAtual;
                regressaoClone = regressao.clone();


            }
        }
    }

    public double getMelhorScore() {

        return melhorScore;
    }

    public Programa getArvClone(){
        return arvClone;
    }

    public Programa getRegressaoClone(){
        return regressaoClone;
    }

}

