package service.geral;

import service.funcoes.Funcao;

public class Conversor{
    public double StringParaDouble(String str){
        boolean negativo=false;
        int pVirgula=str.indexOf(',');
        if(pVirgula<0){
            pVirgula=str.indexOf('.');
        }
        double numero=0;
        if(str.charAt(0)=='-'){
            negativo=true;
            str=str.substring(1);
        }
        if(pVirgula>=0){
            for(int i=0;i<pVirgula;i++){
                numero+=(str.charAt(pVirgula-i-1)-'0')*Math.pow(10, i);
            }
            for(int i=pVirgula+1;i<str.length();i++){
                numero+=(str.charAt(i)-'0')*Math.pow(10, pVirgula-i);
            }
        }
        else{
            for(int i=0;i<str.length();i++){
                numero+=(str.charAt(str.length()-i-1)-'0')*Math.pow(10, i);
            }
        }
        if(negativo){
            numero*=-1;
        }
        return numero;
    }

    public int StringParaInt(String str){
        boolean negativo=false;
        int numero=0;
        if(str.charAt(0)=='-'){
            negativo=true;
            str=str.substring(1);
        }
        for(int i=0;i<str.length();i++){
            numero+=(str.charAt(str.length()-i-1)-'0')*Math.pow(10, i);
        }
        if(negativo){
            numero*=-1;
        }
        return numero;
    }

    public Funcao StringParaFuncao(String str){
        return new Funcao(str);
    }
}