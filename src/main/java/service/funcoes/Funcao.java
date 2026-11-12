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

    private void imprimeNo(Node no){
        if(no==null){
            return;
        }
        if(no.getEsquerda()!=null||no.getDireita()!=null){
            System.out.print("(");
        }
        imprimeNo(no.getEsquerda());
        System.out.print(no.getToken());
        imprimeNo(no.getDireita());
        if(no.getEsquerda()!=null||no.getDireita()!=null){
            System.out.print(")");
        }
    }

    public void arvoreEmOrdem(){
        imprimeNo(raizArvore);
        System.out.println();
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
