import decisao.Decisao;
import instacia.Arquivo;
import instacia.Dados;
import metrica.RMSE;
import programa.Arvore;
import regressao.Regressao;
import validacao.Floresta;
import validacao.Otimizador;

import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {

        //Parametros e inicializações
        int seed = 1;

        String path = "src/instacia/train.csv";
        String del = ",";
        int indexTarget = 1;
        Arquivo arq = new Arquivo();
        Dados dados = arq.Ler(path, del, indexTarget);

        String path2 = "src/instacia/teste.csv";
        String del2 = ",";
        int indexTarget2 = 1;
        Arquivo arq2 = new Arquivo();
        Dados teste = arq2.Ler(path2, del2, indexTarget2);

        RMSE metrica = new RMSE(dados);
        int interation = 100_000;

        Boolean floresta = true;
        int qtdArvores = 128;
        int kfold = 10;
        Random random = new Random(seed);

        if (floresta) {

            Floresta flo = new Floresta(dados, teste, kfold, metrica, interation);
            flo.geraRegressao(qtdArvores);

        } else {

            Arvore arvore = new Regressao(random, dados);
            Otimizador oti = new Otimizador(dados, arvore, metrica, random, interation);
            oti.otimizacaoRegressao();

            //System.out.println("train " + Sig.getName() + " " + oti.getMelhorScore());
        }

    }
}
