package metrica;

import programa.Programa;
import instacia.Dados;
import validacao.KFold;

import java.util.ArrayList;

public class RMSE {
    private Dados dados;
    private ArrayList<Double> predicaoTrain = new ArrayList();
    private ArrayList<Double> predicaoTeste = new ArrayList();
    private ArrayList<Double> predicao = new ArrayList();

    public RMSE(Dados dados) {
        this.dados = dados;
    }

    public double measure(Programa decisao) {
        double sumError = 0.0;
        predicao.clear();
        for(int i = 0; i < dados.sizeLinhas; i++){
            predicao.add(decisao.processa(i,dados));
            sumError += Math.pow(predicao.get(i) - dados.getTarget(i), 2);
        }

        double error = Math.sqrt(sumError/dados.sizeLinhas);
        return error;
    }

    public double measureTrain(Programa decisao, KFold kFold) {
        double sumError = 0.0;
        predicaoTrain.clear();

        for(int i = 0; i < kFold.getSizeTrain(); i++){
            predicaoTrain.add(decisao.processa(kFold.getTrainIndex(i),dados));

            sumError += Math.pow(predicaoTrain.get(i) - dados.getTarget(kFold.getTrainIndex(i)), 2);
        }

        return Math.sqrt(sumError/kFold.getSizeTrain());
    }

    public double measureTeste(Programa decisao, KFold kFold) {
        double sumError = 0.0;
        predicaoTeste.clear();

        for (int i = 0; i < kFold.getSizeTeste(); i++) {
            predicaoTeste.add(decisao.processa(kFold.getTesteIndex(i), dados));

            sumError += Math.pow(predicaoTeste.get(i) - dados.getTarget(kFold.getTesteIndex(i)), 2);
        }

        return Math.sqrt(sumError/kFold.getSizeTeste());
    }

    public double measurePred(ArrayList<Double> jaca, KFold kFold) {
        double sumError = 0.0;

        for(int i = 0; i < kFold.getSizeTrain(); i++){
            sumError += Math.pow(jaca.get(i) - dados.getTarget(kFold.getTrainIndex(i)), 2);
        }

        double error = Math.sqrt(sumError/kFold.getSizeTrain());
        return error;
    }

    public ArrayList getP(){

        return predicaoTrain;
    }

    public double getIndexPred(int i){
        return predicaoTrain.get(i);
    }

    public String getName(){

        return "RMSE ";
    }

}
