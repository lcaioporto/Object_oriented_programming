package Arquivos;
import java.util.ArrayList;
import Administrador.Seguradora;

public interface I_Arquivo {
    boolean gravarArquivo(Seguradora seguradora); //Recebe a seguradora como parâmetro; chama os construtores de objetos
    ArrayList<Object> lerArquivo(ArrayList<Object> lista); //retorna uma lista com todos os objetos do arquivo instanciados
}