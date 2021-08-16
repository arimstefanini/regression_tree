package metrica;

import programa.Programa;
import instacia.Dados;
import validacao.KFold;

import java.util.ArrayList;

public class Accuracy {

    ArrayList<Double> predicao = new ArrayList();


    public double measure(Dados dados, Programa decisao) {
        int correct = 0;
        predicao.clear();

        for (int i = 0; i <dados.sizeLinhas; i++) {
            predicao.add(decisao.processa(i,dados));
            if (((predicao.get(i) > .5) && (dados.getTarget(i) == 1)) ||
                    ((predicao.get(i) < .5) && (dados.getTarget(i) == 0))) {
                correct++;
            }
        }
        return correct / ((double)dados.sizeLinhas);
    }


    public double measureTrain(Dados dados, Programa decisao, KFold kFold) {
        int correct = 0;
        predicao.clear();

        for (int i = 0; i < kFold.getSizeTrain(); i++) {
            predicao.add(decisao.processa(kFold.getTrainIndex(i),dados));
            if (((predicao.get(i) > .5) && (dados.getTarget(kFold.getTrainIndex(i)) == 1)) ||
                    ((predicao.get(i) < .5) && (dados.getTarget(kFold.getTrainIndex(i)) == 0))) {
                correct++;
            }
        }
        return correct / ((double)dados.sizeLinhas);
    }


    public double measureTeste(Dados dados, Programa decisao, KFold kFold) {
        int correct = 0;
        predicao.clear();

        for (int i = 0; i < kFold.getSizeTeste(); i++) {
            predicao.add(decisao.processa(kFold.getTesteIndex(i),dados));
            if (((predicao.get(i) > .5) && (dados.getTarget(kFold.getTesteIndex(i)) == 1)) ||
                    ((predicao.get(i) < .5) && (dados.getTarget(kFold.getTesteIndex(i)) == 0))) {
                correct++;
            }
        }
        return correct / ((double)dados.sizeLinhas);
    }


    public double measurePred(Dados dados, ArrayList<Double> predicao, KFold kFold) {
        int correct = 0;

        for (int i = 0; i < kFold.getSizeTrain(); i++) {
            if (((predicao.get(i) > .5) && (dados.getTarget(kFold.getTrainIndex(i)) == 1)) ||
                    ((predicao.get(i) < .5) && (dados.getTarget(kFold.getTrainIndex(i)) == 0))) {
                correct++;
            }
        }
        return correct / ((double)dados.sizeLinhas);
    }

    public ArrayList getPred(){
        return predicao;
    }


    public String getName() {
        return "Accuracy";
    }
}

