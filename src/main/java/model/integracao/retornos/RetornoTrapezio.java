package model.integracao.retornos;

import java.text.DecimalFormat;
import java.util.List;

import model.integracao.subintervalos.SubintervaloTrapezio;

public class RetornoTrapezio {
    private List<SubintervaloTrapezio> subintervalos;
    private Double areaTotal=0.0;
    private String strAreaTotal;
    private int numCasasDecimais;
    private DecimalFormat df;

    public RetornoTrapezio(List<SubintervaloTrapezio> subintervalos){
        this.subintervalos=subintervalos;
        for(SubintervaloTrapezio subintervalo:subintervalos){
            areaTotal+=subintervalo.getArea();
        }

        String formato = "0.";
        for(int i=0;i<numCasasDecimais;i++){
            formato+="#";
        }
        df = new DecimalFormat(formato);

        strAreaTotal = df.format(areaTotal);
    }

    public Double getAreaTotal() {
        return areaTotal;
    }

    public List<SubintervaloTrapezio> getSubintervalos() {
        return subintervalos;
    }

    public String getStrAreaTotal() {
        return strAreaTotal;
    }
}
