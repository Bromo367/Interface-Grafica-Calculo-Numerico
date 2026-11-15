package model.integracao.retornos;

import java.util.List;

import model.integracao.subintervalos.SubintervaloSimpson;

public class RetornoSimpson {
    private Double areaTotal=0.0;
    private List<SubintervaloSimpson> subintervalos;

    public RetornoSimpson(List<SubintervaloSimpson> subintervalos){
        this.subintervalos=subintervalos;
        for(SubintervaloSimpson subintervalo:subintervalos){
            areaTotal+=subintervalo.getArea();
        }
    }

    public Double getAreaTotal() {
        return areaTotal;
    }

    public List<SubintervaloSimpson> getSubintervalos() {
        return subintervalos;
    }
}
