package programa;

import instacia.Dados;

public interface Programa extends Cloneable {
    double processa(int linha, Dados dados);

    String toString();

    Programa clone();

}
