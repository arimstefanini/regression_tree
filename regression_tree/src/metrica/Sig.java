package metrica;

import programa.Programa;
import instacia.Dados;
import validacao.KFold;


import java.util.ArrayList;

public class Sig {

    private static ArrayList<Double> predicaoTrain = new ArrayList();
    private static ArrayList<Double> predicaoTeste = new ArrayList();

    public static double getSig(Programa expr, Dados data){
        double rmse = 0;
        predicaoTrain.clear();
        for (int i = 0; i < data.sizeLinhas; i++) {
            predicaoTrain.add((1.0/(1.0 + Math.exp(-expr.processa(i, data)))));

            rmse += Math.pow(predicaoTrain.get(i) - data.getLabel(i, 1), 2);
        }
        return Math.sqrt(rmse / data.sizeLinhas);
    }

    public static double getSigTrain(Programa expr, Dados data, KFold kFold){
        double rmse = 0;
        predicaoTrain.clear();
        for (int i = 0; i < kFold.getSizeTrain(); i++) {
            predicaoTrain.add((1.0/(1.0 + Math.exp(-expr.processa(kFold.getTrainIndex(i), data)))));

            rmse += Math.pow(predicaoTrain.get(i) - data.getLabel(kFold.getTrainIndex(i), 1), 2);
        }
        return Math.sqrt(rmse / kFold.getSizeTrain());
    }

    public static double getSigTest(Programa expr, Dados data, KFold kFold){
        double rmse = 0;
        predicaoTeste.clear();

        for (int i = 0; i < kFold.getSizeTeste(); i++) {
            predicaoTeste.add((1.0/(1.0 + Math.exp(-expr.processa(i, data)))));

            rmse += Math.pow(predicaoTeste.get(i) - data.getLabel(kFold.getTesteIndex(i), 1), 2);
        }
        return Math.sqrt(rmse / kFold.getSizeTeste());
    }

    public static double getSigPred(ArrayList<Double> jaca, Dados data, KFold kFold){
        double rmse = 0;

        for (int i = 0; i < jaca.size(); i++) {
            rmse += Math.pow(jaca.get(i) - data.getLabel(kFold.getTrainIndex(i), 1), 2);
        }
        return Math.sqrt(rmse / jaca.size());
    }

    public static double getSigPred(ArrayList<Double> jaca, Dados data){
        double rmse = 0;

        for (int i = 0; i < jaca.size(); i++) {
            rmse += Math.pow(jaca.get(i) - data.getLabel(i, 1), 2);
        }
        return Math.sqrt(rmse / jaca.size());
    }

    public static ArrayList getP(){

        return predicaoTrain;
    }

    public static double getIndexPred(int i){
        return predicaoTrain.get(i);
    }

    public static String getName(){

        return "RMSE da regressao";
    }



}
