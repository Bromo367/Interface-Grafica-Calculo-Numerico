package model.integracao.retornos;

import java.util.List;

import model.integracao.subintervalos.SubintervaloTrapezio;

public class RetornoTrapezio {
    private Double areaTotal=0.0;
    private List<SubintervaloTrapezio> subintervalos;

    public RetornoTrapezio(List<SubintervaloTrapezio> subintervalos){
        this.subintervalos=subintervalos;
        for(SubintervaloTrapezio subintervalo:subintervalos){
            areaTotal+=subintervalo.getArea();
        }
    }

    public Double getAreaTotal() {
        return areaTotal;
    }

    public List<SubintervaloTrapezio> getSubintervalos() {
        return subintervalos;
    }
}
