package metrica;

import instacia.Dados;
import validacao.KFold;

import java.util.ArrayList;

/**
Considero o  falso positivo como o pior cenário,  o qual alegará que um câncer é
benigno onde na verdade é maligno,  traria desperdício de custo médico desnecessário e ao paciente.
 **/
public class MatrizDeConfusao {

    private Dados dados;
    private ArrayList<Double> predicao;
    private KFold kFold;
    private int tp, tn, fp, fn;
    private double f1;

    public MatrizDeConfusao(Dados dados, ArrayList<Double> predicao, KFold kFold) {
        this.dados = dados;
        this.predicao = predicao;
        this.kFold = kFold;
    }

    public MatrizDeConfusao(Dados dados, ArrayList<Double> predicao) {
        this.dados = dados;
        this.predicao = predicao;
    }

    public void matriz(){
        tp = 0; tn = 0; fp = 0; fn = 0;
        for (int i = 0; i < dados.sizeLinhas; i++) {
            if (dados.getTarget(i) == 1.0 && predicao.get(i) >= 0.5) {
                tp++;
            } else if (dados.getTarget(i) == 0.0 && predicao.get(i) < 0.5) {
                tn++;
            } else if (dados.getTarget(i) == 1.0 && predicao.get(i) < 0.5) {
                fp++;
            } else if (dados.getTarget(i) == 0.0 && predicao.get(i) >= 0.5) {
                fn++;
            }
        }

        double precision = 1.0 * tp / (tp + fp);
        double recall = 1.0 * tp / (tp + fn);

        f1 = 2 * precision * recall / (precision + recall);

    }

    public void matrizTrain(){
        tp = 0; tn = 0; fp = 0; fn = 0;
        for (int i = 0; i < kFold.getSizeTrain(); i++) {
            if (dados.getTarget(kFold.getTrainIndex(i)) == 1.0 && predicao.get(i) == 1.0) {
                tp++;
            } else if (dados.getTarget(kFold.getTrainIndex(i)) == 0.0 && predicao.get(i) == 0.0) {
                tn++;
            } else if (dados.getTarget(kFold.getTrainIndex(i)) == 0.0 && predicao.get(i) == 1.0) {
                fp++;
            } else if (dados.getTarget(kFold.getTrainIndex(i)) == 1.0 && predicao.get(i) == 0.0) {
                fn++;
            }
        }

        double precision = 1.0 * tp / (tp + fp);
        double recall = 1.0 * tp / (tp + fn);

        f1 = 2 * precision * recall / (precision + recall);

    }

    public void matrizTrainRegressao(){
        tp = 0; tn = 0; fp = 0; fn = 0;
        for (int i = 0; i < kFold.getSizeTrain(); i++) {
            if (dados.getTarget(kFold.getTrainIndex(i)) == 1.0 && predicao.get(i) >= 0.5) {
                tp++;
            } else if (dados.getTarget(kFold.getTrainIndex(i)) == 0.0 && predicao.get(i) < 0.5) {
                tn++;
            } else if (dados.getTarget(kFold.getTrainIndex(i)) == 1.0 && predicao.get(i) < 0.5) {
                fp++;
            } else if (dados.getTarget(kFold.getTrainIndex(i)) == 0.0 && predicao.get(i) >= 0.5) {
                fn++;
            }
        }

        double precision = 1.0 * tp / (tp + fp);
        double recall = 1.0 * tp / (tp + fn);

        f1 = 2 * precision * recall / (precision + recall);

    }

    public String toString(){
        return "Positivo |Negativo\n" +
                "TP "+tp + "\t |"+ "FP "+fp +"\n"+
                "FN "+fn + "\t |"+ "TN "+tn + "\n"+
                "F1: "+ f1 +"\n";
    }

}

