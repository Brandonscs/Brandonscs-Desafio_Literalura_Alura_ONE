package com.brandonscs.desafio.literalura.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespuestaApi {
    private List<DatosBook> results;

    public List<DatosBook> getResults() {
        return results;
    }

    public void setResults(List<DatosBook> results) {
        this.results = results;
    }
}