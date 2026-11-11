package service.metodos;

public class CriteriosParadaRaizes {
    public boolean erroFuncao(double fX,int nX, double precisao){
        System.out.print("|f(x"+nX+")| = "+Math.abs(fX));

        if(Math.abs(fX)<precisao){
            System.out.println(" < precisao");
            System.out.println("Parou!");
            return true;
        }
        else{
            System.out.println(" > precisao");
            return false;
        }
    }

    public boolean erroFuncaoVariavel(double fVariavel,String nomeVariavel, double precisao){
        System.out.print("|f("+nomeVariavel+")| = "+Math.abs(fVariavel));

        if(Math.abs(fVariavel)<precisao){
            System.out.println(" < precisao");
            System.out.println("Parou!");
            return true;
        }
        else{
            System.out.println(" > precisao");
            return false;
        }
    }

    public boolean erroAbsoluto(double xAtual,double xAnterior, int nX,double precisao){
        System.out.print("|x"+nX+"-x"+(nX-1)+"| = "+Math.abs(xAtual-xAnterior));
        if(Math.abs(xAtual-xAnterior)<precisao){
            System.out.println(" < precisao");
            System.out.println("Parou!");
            return true;
        }
        else{
            System.out.println(" > precisao");
            return false;
        }
    }

    public boolean diferencaIntervalo(double a,double b,double precisao){
        System.out.print("|b-a| = "+Math.abs(b-a));
        if(Math.abs(b-a)<precisao){
            System.out.println(" < precisao");
            System.out.println("Parou!");
            return true;
        }
        else{
            System.out.println(" > precisao");
            return false;
        }
    }

    public boolean erroRelativo(double xAtual,double xAnterior, int nX,double precisao){
        System.out.print("|b-a|/x"+nX+" = "+Math.abs((xAtual-xAnterior)/xAtual));
        if(Math.abs((xAtual-xAnterior)/xAtual)<precisao){
            System.out.println(" < precisao");
            System.out.println("Parou!");
            return true;
        }
        else{
            System.out.println(" > precisao");
            return false;
        }
    }

}
