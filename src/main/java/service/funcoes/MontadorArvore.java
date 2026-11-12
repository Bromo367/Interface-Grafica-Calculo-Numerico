package service.funcoes;
import java.util.ArrayList;
import java.util.Collections;

public class MontadorArvore {
    public ArrayList<String> tokenizar(String strFuncao){
        ArrayList<String> tokens = new ArrayList<>();
        ArrayList<String> opcoesTokens = new ArrayList<>();
        Collections.addAll(opcoesTokens,"e","x","+","-","*","/","^","cos","sen","tan","sec","cossec","cotg","ln","log","(",")");
        int i=0;
        while(i<strFuncao.length()){
            if(strFuncao.charAt(i)==' '){
                i++;
            }
            else{
                int j=0;
                boolean encontrouToken=false;
                while(j<opcoesTokens.size()&&!encontrouToken){
                    String tokenAtual = opcoesTokens.get(j);
                    if(i+tokenAtual.length()<=strFuncao.length()&&strFuncao.substring(i,i+tokenAtual.length()).equalsIgnoreCase(tokenAtual)){
                        i+=tokenAtual.length();
                        encontrouToken=true;
                    }
                    j++;
                }
                if(encontrouToken){

                    tokens.add(opcoesTokens.get(j-1));
                }
                else{
                    int idxInicialNum=i;
                    while(i<strFuncao.length()&&strFuncao.charAt(i)>='0'&&strFuncao.charAt(i)<='9'){
                        i++;
                    }
                    if(i<strFuncao.length()&&(strFuncao.charAt(i)==','||strFuncao.charAt(i)=='.')){
                        i++;
                        while(i<strFuncao.length()&&strFuncao.charAt(i)>='0'&&strFuncao.charAt(i)<='9'){
                            i++;
                        }
                    }
                    tokens.add(strFuncao.substring(idxInicialNum,i));
                }
            }
        }
        int n=tokens.size();
        ArrayList<String> operadores = new ArrayList<>();
        Collections.addAll(operadores,"+","-","*","/","^","cos","sen","tan","sec","cossec","cotg","ln","log","(");
        for(int j=0;j<n;j++){
            if(tokens.get(j)=="-"){
                if(j==0){
                    tokens.remove(0);
                    tokens.add(0,"*");
                    tokens.add(0,"-1");
                    n=tokens.size();
                }
                else{
                    int k=0;
                    boolean encontrouOperador=false;
                    while(k<operadores.size()&&!encontrouOperador){
                        if(tokens.get(j-1).equalsIgnoreCase(operadores.get(k))){
                            encontrouOperador=true;
                        }
                        k++;
                    }
                    if(encontrouOperador){
                        tokens.remove(j);
                        tokens.add(j,"*");
                        tokens.add(j,"-1");
                        n=tokens.size();
                    }
                }
            }
        }
        return tokens;
    }

    public Node criarArvore(ArrayList<String> tokens,int inicio,int fim){
        if(inicio>fim){
            return null;
        }
        if(tokens.get(inicio)=="("&&tokens.get(fim)==")"){
            boolean cercadoParenteses=true;
            int parenteses=0;
            for(int i=inicio;i<fim;i++){
                if(tokens.get(i)=="("){
                    parenteses++;
                }
                else if(tokens.get(i)==")"){
                    parenteses--;
                    if(parenteses==0){
                        cercadoParenteses=false;
                    }
                }
            }
            if(cercadoParenteses){
                inicio++;
                fim--;
            }
            
        }
        

        String token;
        int pToken=0;
        int prioridade=5;
        int i=fim;
        int parenteses=0;

        
        ArrayList<String> operadoresPrioridade0 = new ArrayList<>();
        Collections.addAll(operadoresPrioridade0, "+","-");

        ArrayList<String> operadoresPrioridade1 = new ArrayList<>();
        Collections.addAll(operadoresPrioridade1, "*","/");

        ArrayList<String> operadoresPrioridade2 = new ArrayList<>();
        Collections.addAll(operadoresPrioridade2, "^");

        ArrayList<String> operadoresPrioridade3 = new ArrayList<>();
        Collections.addAll(operadoresPrioridade3, "cos","sen","tan","cossec","sec","cotg","ln","log");
        while(i>=inicio){
            if(tokens.get(i)=="("){
                parenteses--;
            }
            else if(tokens.get(i)==")"){
                parenteses++;
            }
            else if(parenteses==0){
                if(prioridade>0&&verificarLista(tokens.get(i), operadoresPrioridade0)){
                    prioridade=0;
                    pToken=i;
                }
                else if(prioridade>1&&verificarLista(tokens.get(i), operadoresPrioridade1)){
                    prioridade=1;
                    pToken=i;
                }
                else if(prioridade>2&&verificarLista(tokens.get(i), operadoresPrioridade2)){
                    prioridade=2;
                    pToken=i;
                }
                else if(prioridade>3&&verificarLista(tokens.get(i), operadoresPrioridade3)){
                    prioridade=3;
                    pToken=i;
                }
                else if(prioridade>4){
                    prioridade=4;
                    pToken=i;
                }
            }
            i--;
        }
        token=tokens.get(pToken);
        return new Node(token,criarArvore(tokens, inicio, pToken-1),criarArvore(tokens,pToken+1, fim));
    }

    public boolean verificarLista(String entrada,ArrayList<String> lista){
        for(String item:lista){
            if(entrada.equalsIgnoreCase(item)){
                return true;
            }
        }
        return false;
    }
}
