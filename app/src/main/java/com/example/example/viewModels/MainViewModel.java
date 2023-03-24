package com.example.example.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.example.models.ResponseCharacters;
import com.example.example.repository.CharacterRepository;

public class MainViewModel extends ViewModel {
    private MutableLiveData<ResponseCharacters> charactersMutableLiveData;
    public MutableLiveData<String> data;

    public void getCharacterRepository(){
        CharacterRepository characterRepository = CharacterRepository.getInstance();
        CharacterRepository characterRepository1 = CharacterRepository.getInstance();
        CharacterRepository characterRepository2 = CharacterRepository.getInstance();
        characterRepository1.setValue("Esto es una prueba");
        characterRepository2.getPrueba();
        charactersMutableLiveData = characterRepository.getCharacter();
    }

    public LiveData<ResponseCharacters> getCharacterViewModel(){
        return charactersMutableLiveData;
    }

    public void setData(String value){
        data = new MutableLiveData<>();
        data.setValue(value);
    }

    public LiveData<String> getData(){
        return data;
    }
}