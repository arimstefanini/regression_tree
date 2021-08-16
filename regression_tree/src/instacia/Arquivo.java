package instacia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Arquivo {

    private Dados dados;


    public Dados Ler(String filePath, String del, int label) throws IOException {

        String linha;

        BufferedReader buffer = new BufferedReader(new FileReader(new File(filePath)));

        String[] nomesCols = buffer.readLine().split(del);
        int colSize = nomesCols.length;
        int fileSize = 0;

        while (buffer.readLine() != null)
            fileSize++;

        buffer = new BufferedReader(new FileReader(new File(filePath)));
        buffer.readLine();
        double[][] data = new double[fileSize][colSize];

        int count = 0;
        while( (linha = buffer.readLine()) != null ){
            String[] values = linha.split(del);
            for(int i=0; i < nomesCols.length; i++) {
                data[count][i] = Double.valueOf(values[i]);
            }
            count++;
        }

        dados = new Dados(nomesCols, data, label);
        return dados;
    }
}