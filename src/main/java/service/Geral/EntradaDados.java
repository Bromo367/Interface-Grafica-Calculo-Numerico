package service.Geral;

import java.util.Scanner;

import service.Funcoes.Funcao;

public class EntradaDados {
    private Scanner scanner;
    private Conversor conversor = new Conversor();
    private VerificadorStrings verificador = new VerificadorStrings();
    public EntradaDados(Scanner scanner){
        this.scanner=scanner;
    }

    public int leituraInt(){
        int entrada=0;
        boolean passou=false;
        while(!passou){
            try{
                entrada=scanner.nextInt();
                scanner.nextLine();
                passou=true;
            }
            catch(Exception E){
                System.out.println("Insira um valor valido");
            }
        }

        return entrada;
    }

    public double leituraDouble(){
        String entrada;
        entrada=scanner.nextLine();
        while(!verificador.ehDouble(entrada)){
            System.out.println("Insira uma entrada valida");
            entrada=scanner.nextLine();
        }
        return conversor.StringParaDouble(entrada);
    }

    public Funcao leituraFuncao(){
        String entrada=scanner.nextLine();
        while(!verificador.ehFuncao(entrada)){
            System.out.println("Insira uma funcao valida");
            entrada=scanner.nextLine();
        }
        return new Funcao(entrada);
    }


}
