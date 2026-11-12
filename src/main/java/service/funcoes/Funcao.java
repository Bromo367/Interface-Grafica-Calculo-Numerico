package service.funcoes;
import java.util.ArrayList;

public class Funcao {
    private Node raizArvore;
    private ArrayList<String> tokens = new ArrayList<>();
    public Funcao(String strFuncao){
        MontadorArvore ma = new MontadorArvore();
        tokens=ma.tokenizar(strFuncao);
        raizArvore=ma.criarArvore(tokens,0,tokens.size()-1);
    }

    public Funcao(Node raizArvore){
        this.raizArvore=raizArvore;
    }

    private String imprimeNo(Node no){
        String retorno="";
        if(no==null){
            return "";
        }
        if(no.getEsquerda()!=null||no.getDireita()!=null){
            retorno+="(";
        }
        retorno+=imprimeNo(no.getEsquerda());
        retorno+=no.getToken();
        retorno+=imprimeNo(no.getDireita());
        if(no.getEsquerda()!=null||no.getDireita()!=null){
            retorno+=")";
        }
        return retorno;
    }

    public String arvoreEmOrdem(){
        return imprimeNo(raizArvore);
    }

    public double aplicar(double x){
        return raizArvore.resolver(x);
    }

    public Funcao derivar(){
        Derivador derivador = new Derivador();
        Funcao derivada =  new Funcao(derivador.derivar(raizArvore));
        derivada.simplificar();
        return derivada;
    }

    public void simplificar(){
        raizArvore=raizArvore.simplificar();
    }

}
