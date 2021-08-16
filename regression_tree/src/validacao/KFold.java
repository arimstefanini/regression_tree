package validacao;

public class KFold {
    private int[] trainIndex;
    private int[] testeIndex;
    private int k;
    private int size;
    private int inicioV;
    private int tamanho;
    private int resto;

    public KFold(int k, int size){
        this.k = k;
        this.size = size;
        tamanho = size/k;
        resto = size % k;
        this.size = size;
    }

    public void split(){
        cria();
        int c = 0;
        int j = 0;
        for (int i = 0; i < size; i++) {
            if(i == inicioV && c == 0) {
                while (c != tamanho) {
                    this.testeIndex[c] = i;
                    c++;
                    i++;
                    this.inicioV = i;
                }
                i--;
            } else {
                this.trainIndex[j] = i;
                j++;
            }
        }
    }

    private void cria(){
        if(inicioV == 0){
            this.tamanho = tamanho+resto;
            this.testeIndex = new int[tamanho];
            this.trainIndex = new int[size-tamanho];
        } else {
            this.tamanho = size/k;
            this.testeIndex = new int[tamanho];
            this.trainIndex = new int[size-tamanho];
        }
    }

    public int[] getTrain(){
        return trainIndex;
    }

    public int[] getTeste(){
        return testeIndex;
    }

    public Integer getTrainIndex(int i) {
        return trainIndex[i];
    }

    public Integer getSizeTrain() {
        return trainIndex.length;
    }

    public Integer getTesteIndex(int i) {
        return testeIndex[i];
    }

    public Integer getSizeTeste() {
        return testeIndex.length;
    }
}

