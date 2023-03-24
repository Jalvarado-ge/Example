package com.example.example.apis;

import com.example.example.models.ResponseCharacters;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CharacterApi {
    @GET("character")
    Call<ResponseCharacters> getCharacter();
}
