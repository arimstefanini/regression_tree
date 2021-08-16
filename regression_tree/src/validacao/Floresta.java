package validacao;

import decisao.Decisao;
import instacia.Dados;
import metrica.MatrizDeConfusao;
import metrica.RMSE;
import metrica.Sig;
import programa.Programa;
import regressao.Regressao;

import java.util.ArrayList;
import java.util.Random;

public class Floresta  {

    private Dados dados;
    private Dados teste;
    private int k;
    private RMSE metrica;
    private ArrayList<Double> prediction;
    private double[][] bag;
    private Programa[] modelos;
    private int interation;
    private int qtdArvores;

    public Floresta(Dados dados, Dados teste, int kFold, RMSE metrica, int interation){
        this.dados = dados;
        this.k = kFold;
        this.metrica = metrica;
        this.interation = interation;
        this.teste = teste;
    }


    public void geraRegressao(int qtdArvore) {

        this.qtdArvores = qtdArvore;

        System.out.println("Modelo Arvore de Regressao");

        KFold kFold = new KFold(this.k, dados.sizeLinhas);

        for (int i = 0; i < 1; i++) {
            kFold.split();
            prediction = new ArrayList<>();
            bag = new double[kFold.getSizeTrain()][qtdArvore];
            modelos = new Programa[qtdArvore];

            System.out.println("k: " + i);

            //TODO paralelizar esse metodo
            florestaRegressao(kFold);

            System.out.println("TREINO");

            ArrayList preds = meanReg(bag);
            double rmse = Sig.getSigPred(preds,dados, kFold);
            System.out.println("rt train "+rmse);

            MatrizDeConfusao mdc = new MatrizDeConfusao(dados, preds, kFold);
            mdc.matrizTrainRegressao();
            System.out.println(mdc.toString());


            System.out.println("TESTE");

            double[][] bagTeste = new double[teste.sizeLinhas][qtdArvore];

            for (int f = 0; f < modelos.length; f++) {

                Sig.getSig(modelos[f],teste);

                for (int j = 0; j < teste.sizeLinhas ; j++) {
                    bagTeste[j][f] = Sig.getIndexPred(j);
                }
            }

            ArrayList predsTest = meanReg(bagTeste);
            double rmseTeste = Sig.getSigPred(predsTest,teste);
            System.out.println("TESTE "+ rmseTeste);

            MatrizDeConfusao mdcTeste = new MatrizDeConfusao(teste, predsTest);
            mdcTeste.matriz();
            System.out.println(mdcTeste.toString());
        }
    }

    public void florestaRegressao(KFold kFold){

        for (int f = 0; f < qtdArvores; f++) {

            Random random = new Random(f);
            Regressao modelo = new Regressao(random, dados);
            Otimizador oti = new Otimizador(dados, modelo, metrica, random, interation);

            System.out.println("Modelo: "+ f);

            oti.otimizacaoRegressaoTrain(kFold);
            System.out.println("train " + Sig.getName() + " " + oti.getMelhorScore());

            Sig.getSigTrain(oti.getRegressaoClone(),dados,kFold);

            for (int j = 0; j < kFold.getSizeTrain() ; j++) {
                bag[j][f] = Sig.getIndexPred(j);
            }

            modelos[f] = oti.getRegressaoClone();

            double teste = Sig.getSigTest(oti.getRegressaoClone(), dados, kFold);
            System.out.println("teste " + Sig.getName() + " " + teste);

        }
    }

    //Para dados continuos é realizado a media da predição
    public ArrayList<Double> meanReg(double[][] b) {
        prediction.clear();
        for (int r = 0; r < b.length; r++) {
            double soma = 0;
            for (int c = 0; c < b[0].length; c++) {
                double prediction = b[r][c];
                soma += prediction;
            }
            double mean = soma/bag[0].length;
            prediction.add(mean);
        }
        return prediction;
    }
}

