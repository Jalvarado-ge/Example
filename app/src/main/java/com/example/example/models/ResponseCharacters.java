package com.example.example.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseCharacters {
    @SerializedName("results")
    @Expose
    private List<CharacterRickAndMorty> resultados;

    public List<CharacterRickAndMorty> getResults() {
        return resultados;
    }

    public void setResults(List<CharacterRickAndMorty> results) {
        this.resultados = results;
    }
}
