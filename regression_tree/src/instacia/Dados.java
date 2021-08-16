package instacia;

public class Dados {

    private final double[][] dados;
    public int sizeLinhas;
    public int sizeColunas;
    public int indexTarget;

    public final double[] max;
    public final double[] min;
    public final double[] media;
    public final double[] std;
    public final double[][] correlacao;



    public Dados(String[] colunas,  double[][] dados, int indexTarget) {
        this.dados = dados;
        this.indexTarget = indexTarget;
        sizeLinhas = dados.length;
        sizeColunas = colunas.length;
        max = new double[sizeColunas];
        min = new double[sizeColunas];
        media = new double[sizeColunas];
        std = new double[sizeColunas];
        correlacao = new double[sizeColunas][sizeColunas];

        for (int i = 0; i < sizeColunas; i++) {
            getDesvioPadrao(i);
        }
        matrizCorrelacao();
    }

    public void getMedia(int indice) {

        media[indice] = getSoma(dados, indice) / sizeLinhas;
    }

    public double getSoma(double[][] dados, int indice) {
        double soma = 0;
        min[indice] = Double.MAX_VALUE;
        max[indice] = 0;
        for (int i = 0; i < sizeLinhas; i++) {
            soma += dados[i][indice];
            if (dados[i][indice] < min[indice])
                min[indice] = dados[i][indice];
            if (dados[i][indice] > max[indice])
                max[indice] = dados[i][indice];
        }
        return soma;
    }

    public void getDesvioPadrao(int indice) {
        getMedia(indice);
        double desvPadrao = 0.0;
        for (int i = 0; i < sizeLinhas; i++) {
            double aux = dados[i][indice] - media[indice];
            desvPadrao += aux * aux;
        }
        std[indice] = Math.sqrt(desvPadrao / (sizeLinhas - 1));
    }

    public void matrizCorrelacao(){
        for (int i = 0; i < sizeColunas; i++) {
            for (int j = 0; j < sizeColunas; j++) {
                if(i < j) {
                    correlacao[i][j] = calcularCorrelacao(i, j);
                    correlacao[j][i] = correlacao[i][j];
                }
                if(i == j){
                    correlacao[i][j] = 1.0;
                }
            }
        }
    }

    public double calcularCorrelacao(int x, int y){
        double somatorio = 0.0;
        for (int i = 0; i < sizeLinhas; i++) {
            double aux = (dados[i][x]- media[x]) * (dados[i][y]- media[y]);
            somatorio +=  aux;
        }
        return (1.0/(sizeLinhas-1)) * (somatorio/(std[x]*std[y]));
    }

    public double getLabel(int i, int j){
        return dados[i][j];
    }

    public double getTarget(int row){
        return dados[row][indexTarget];
    }
}
