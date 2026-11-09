package service.Funcoes;
import service.Geral.Conversor;

public class Node {
    private String token;
    private Node esquerda, direita;

    public Node(String token,Node esquerda, Node direita){
        this.token=token;
        this.esquerda=esquerda;
        this.direita=direita;
    }

    public Node getEsquerda(){
        return esquerda;
    }

    public Node getDireita(){
        return direita;
    }

    public String getToken(){
        return token;
    }

    public double resolver(double x){
        if(esquerda!=null&&direita!=null){
            switch(token){
                case "+":
                    return esquerda.resolver(x)+direita.resolver(x);
                case "-":
                    return esquerda.resolver(x)-direita.resolver(x);
                case "*":
                    return esquerda.resolver(x)*direita.resolver(x);
                case "/":
                    return esquerda.resolver(x)/direita.resolver(x);
                case "^":
                    return Math.pow(esquerda.resolver(x),direita.resolver(x));
                default:
                    return 0;
            }
        }
        else if(esquerda==null&&direita!=null){
            switch (token){
                case "cos":
                    return Math.cos(direita.resolver(x));
                case "sen":
                    return Math.sin(direita.resolver(x));
                case "tan":
                    return Math.tan(direita.resolver(x));
                case "sec":
                    return 1/Math.cos(direita.resolver(x));
                case "cossec":
                    return 1/Math.sin(direita.resolver(x));
                case "cotg":
                    return 1/Math.tan(direita.resolver(x));
                case "log":
                    return Math.log10(direita.resolver(x));
                case "ln":
                    return Math.log(direita.resolver(x));
                default:
                    return 0;
            }
        }
        else{
            if(token=="e"){
                return Math.E;
            }
            else if(token=="x"){
                return x;
            }
            else{
                Conversor conversor = new Conversor();
                return conversor.StringParaDouble(token);
            }
        }
    }

    public boolean ehNumero(){
        if(token.charAt(0)>='0'&&token.charAt(0)<='9'){
            return true;
        }
        else{
            return false;
        }
    }

    public Node simplificar(){
        if(esquerda!=null){
            esquerda=esquerda.simplificar();
        }
        if(direita!=null){   
            direita=direita.simplificar();
        }

        switch(token){
            case "+":
                if(esquerda.getToken()=="0"){
                    return direita;
                }
                else if(direita.getToken()=="0"){
                    return esquerda;
                }
                break;
            case "-":
                if(direita.getToken()=="0"){
                    return esquerda;
                }
                break;
            case "*":
                if(direita.getToken()=="0"||esquerda.getToken()=="0"){
                    return new Node("0", null,null);
                }
                else if(esquerda.getToken()=="1"){
                    return direita;
                }
                else if(direita.getToken()=="1"){
                    return esquerda;
                }
                break;
            case "/":
                if(esquerda.getToken()=="0"){
                    return new Node("0", null,null);
                }
                else if(direita.getToken()=="1"){
                    return esquerda;
                }
                break;
            case "^":
                if(direita.getToken()=="1"){
                    return esquerda;
                }
                else if(esquerda.getToken()=="0"){
                    return new Node("0", null,null);
                }
                else if(direita.getToken()=="0"){
                    return new Node("1", null,null);
                }
                break;
            case "ln":
                if(direita.getToken()=="e"){
                    return new Node("1", null,null);
                }
                break;               
                
        }
        return this;
    }
}
