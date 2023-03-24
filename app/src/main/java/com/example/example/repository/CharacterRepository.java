package com.example.example.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.example.apis.CharacterApi;
import com.example.example.models.ResponseCharacters;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharacterRepository {
    private static CharacterRepository characterRepository;

    private String prueba;
    public static CharacterRepository getInstance() {
        if (characterRepository == null) {
            characterRepository = new CharacterRepository();
        }
        return characterRepository;
    }

    private CharacterApi api;

    public CharacterRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(CharacterApi.class);
    }

    public void setValue (String value){
        prueba = value;
    }

    public String getPrueba(){
        return prueba;
    }

    public MutableLiveData<ResponseCharacters> getCharacter() {
        final MutableLiveData<ResponseCharacters> data = new MutableLiveData<>();

        api.getCharacter().enqueue(new Callback<ResponseCharacters>() {
            @Override
            public void onResponse(Call<ResponseCharacters> call, Response<ResponseCharacters> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(new ResponseCharacters());
                }
            }

            @Override
            public void onFailure(Call<ResponseCharacters> call, Throwable t) {
                data.setValue(new ResponseCharacters());
            }
        });
        return data;
    }
}
