package service.geral;

import java.util.ArrayList;
import java.util.Collections;

public class VerificadorStrings {
    public boolean ehDouble(String entrada){
        int i=0;
        while(i<entrada.length()&&entrada.charAt(i)>='0'&&entrada.charAt(i)<='9'){
            i++;
        }
        if(i==entrada.length()){
            return true;
        }
        if(entrada.charAt(i)==','||entrada.charAt(i)=='.'){
            i++;
            while(i<entrada.length()&&entrada.charAt(i)>='0'&&entrada.charAt(i)<='9'){
                i++;
            }
            if(i==entrada.length()){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
        
    }

    public boolean ehFuncao(String entrada){
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
        return valido&&esperandoOperador;
    }
}
