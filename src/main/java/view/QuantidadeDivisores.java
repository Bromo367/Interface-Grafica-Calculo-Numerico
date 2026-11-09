package view;

public class QuantidadeDivisores {
    private int eixoX;
    private int eixoY;

    public QuantidadeDivisores(int eixoX){
        this.eixoX=eixoX;
    }

    public void recalcularY(double w, double h){
        eixoY=(int)((h*(eixoX+1))/w);
        if(eixoY%2==0){
            eixoY++;
        }
    }

    public int getEixoX(){
        return eixoX;
    }

    public int getEixoY(){
        return eixoY;
    }
}
