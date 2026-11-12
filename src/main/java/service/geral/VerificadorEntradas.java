package service.geral;

import java.util.ArrayList;
import java.util.Collections;
import service.funcoes.Funcao;

public class VerificadorEntradas {
    private Conversor conversor = new Conversor();
    public int avaliarInt(String entrada, String nome) throws EntradaException{
        if(entrada.length()==0){
            throw new EntradaException(nome + " deve ser um valor inteiro!");
        }
        int i=0;
        while(i<entrada.length()&&entrada.charAt(i)>='0'&&entrada.charAt(i)<='9'){
            i++;
        }
        if(i!=entrada.length()){
            throw new EntradaException(nome + " deve ser um valor inteiro!");
        }
        return conversor.StringParaInt(entrada);
    }
    public double avaliarDouble(String entrada, String nome) throws EntradaException{
        if(entrada.length()==0){
            throw new EntradaException(nome + " deve ser um valor racional!");
        }
        int i=0;
        boolean sucesso;
        while(i<entrada.length()&&entrada.charAt(i)>='0'&&entrada.charAt(i)<='9'){
            i++;
        }
        if(i==entrada.length()){
            sucesso=true;
        }
        else if(entrada.charAt(i)==','||entrada.charAt(i)=='.'){
            i++;
            while(i<entrada.length()&&entrada.charAt(i)>='0'&&entrada.charAt(i)<='9'){
                i++;
            }
            if(i==entrada.length()){
                sucesso=true;
            }
            else{
                sucesso=false;
            }
        }
        else{
            sucesso=false;
        }

        if(!sucesso){
            throw new EntradaException(nome + " deve ser um valor racional!");
        }
        return conversor.StringParaDouble(entrada);
    }

    public Funcao avaliarFuncao(String entrada, String nome) throws EntradaException{
        if(entrada.length()==0){
            throw new EntradaException(nome + " deve ser uma função válida");
        }
        boolean valido=true;

        int i=0;
        int parenteses=0;
        while(i<entrada.length()&&valido){
            if(entrada.charAt(i)=='('){
                parenteses++;
            }
            else if(entrada.charAt(i)==')'){
                if(parenteses<=0){
                    valido=false;
                }
                else{
                    parenteses--;
                }
            }
            i++;
        }
        if(parenteses!=0){
            valido=false;
        }
        
        boolean esperandoOperador=false;
        ArrayList<String> operadoresBinarios = new ArrayList<>();
        Collections.addAll(operadoresBinarios,"+","-","*","/","^");
        ArrayList<String> operadoresUnarios = new ArrayList<>();
        Collections.addAll(operadoresUnarios,"cos","sen","tan","sec","cossec","cotg","ln","log");
        i=0;
        while(i<entrada.length()&&valido){
            if(entrada.charAt(i)==' '){
                i++;
            }
            else{
                if(entrada.charAt(i)!='('&&entrada.charAt(i)!=')'){
                    if(esperandoOperador){
                        boolean ehOperadorBinario=false;
                        for(String operador:operadoresBinarios){
                            if(!ehOperadorBinario&&i+operador.length()<entrada.length()){
                                if(entrada.substring(i,i+operador.length()).equalsIgnoreCase(operador)){
                                    i+=operador.length();
                                    ehOperadorBinario=true;
                                }
                            }
                        }
                        if(ehOperadorBinario){
                            esperandoOperador=false;
                        }
                        else{
                            valido=false;
                        }
                    }
                    else{
                        if(entrada.charAt(i)=='-'){
                            i++;
                        }
                        boolean ehOperadorUnario=false;
                        for(String operador:operadoresUnarios){
                            if(!ehOperadorUnario&&i+operador.length()<entrada.length()){
                                if(entrada.substring(i,i+operador.length()).equalsIgnoreCase(operador)){
                                    i+=operador.length();
                                    ehOperadorUnario=true;
                                }
                            }
                        }
                        if(!ehOperadorUnario){
                            if(entrada.charAt(i)=='x'||entrada.charAt(i)=='e'){
                                esperandoOperador=true;
                                i++;
                            }
                            else if(entrada.charAt(i)>='0'&&entrada.charAt(i)<='9'){
                                while(i<entrada.length()&&entrada.charAt(i)>='0'&&entrada.charAt(i)<='9'){
                                    i++;
                                }
                                if(i<entrada.length()&&(entrada.charAt(i)==','||entrada.charAt(i)=='.')){
                                    i++;
                                    if(i>=entrada.length()||entrada.charAt(i)<='0'||entrada.charAt(i)>='9'){
                                        valido=false;
                                    }
                                    while(i<entrada.length()&&entrada.charAt(i)>='0'&&entrada.charAt(i)<='9'){
                                        i++;
                                    }
                                }
                                esperandoOperador=true;
                            }
                            else{
                                valido=false;
                            }
                        }
                    }
                }
                else{
                    i++;
                } 
            }
        }
        if(!esperandoOperador){
            valido=false;
        }
        if(!valido){
            throw new EntradaException(nome + " deve ser uma função válida");
        }
        return conversor.StringParaFuncao(entrada);
    }
}
