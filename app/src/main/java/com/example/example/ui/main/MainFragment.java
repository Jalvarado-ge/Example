package com.example.example.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.example.databinding.FragmentMainBinding;
import com.example.example.models.CharacterRickAndMorty;
import com.example.example.viewModels.MainViewModel;
import com.example.example.R;

import java.util.List;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
//    private RecyclerView recyclerView;
    private FragmentMainBinding binding;

    private List<CharacterRickAndMorty> characterRickAndMortyList;

    private NavController navController;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding = FragmentMainBinding.inflate(inflater,container,false);
        getCharacterServices();
        setValueLiveData();
//        setData();

        return binding.getRoot();
//        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    private void setData(){
//        recyclerView = getView().findViewById(R.id.rvCharacters);
        RecyclerCharacterAdapter adapter = new RecyclerCharacterAdapter(characterRickAndMortyList,navController);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.rvCharacters.setLayoutManager(layoutManager);
        binding.rvCharacters.setHasFixedSize(true);
        binding.rvCharacters.setAdapter(adapter);

    }

    private void setValueLiveData(){
//        mViewModel.setData("Hola mundo");
////        mViewModel.data.observe(requireActivity(), datalive -> {
////            binding.tvLiveData.setText(datalive);
////        });
//        mViewModel.getData().observe(requireActivity(), liveData ->{
//            binding.tvLiveData.setText(liveData);
//        } );
        binding.tvLiveData.setText("Hola Mundo");
    }


    private void getCharacterServices(){
        binding.loader.setVisibility(View.VISIBLE);
        mViewModel.getCharacterRepository();
        mViewModel.getCharacterViewModel().observe(requireActivity(), responseCharacters -> {
            if (responseCharacters != null){
                Log.d("Respuesta de personajes", "No viene vacio: ");
                characterRickAndMortyList = responseCharacters.getResults();
                setData();
            }
            binding.loader.setVisibility(View.GONE);
        });
    }

}