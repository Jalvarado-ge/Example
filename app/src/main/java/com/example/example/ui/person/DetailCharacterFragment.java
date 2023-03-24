package com.example.example.ui.person;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.example.R;
import com.example.example.databinding.FragmentDetailCharacterBinding;
import com.example.example.models.CharacterRickAndMorty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class DetailCharacterFragment extends Fragment {

    DetailCharacterFragmentArgs args;
    private FragmentDetailCharacterBinding binding;

    private CharacterRickAndMorty character;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetailCharacterBinding.inflate(inflater,container,false);
        args = DetailCharacterFragmentArgs.fromBundle(getArguments());
        Gson gson;
        gson = new GsonBuilder().create();
        character = gson.fromJson(args.getCharacter(),CharacterRickAndMorty.class);
        setData();
        return binding.getRoot();
    }

    private void setData(){
        Glide.with(requireContext())
                .load(character.getImage())
                .into(binding.imgCharacter);

        binding.tvName.setText(character.getName());
        binding.tvState.setText(character.getStatus()+" - ");
        Drawable liveCircleDrawable;
        if (character.getStatus().equals("Alive")){
            liveCircleDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.live_circle);
        }else {
            liveCircleDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.dead_circle);
        }
        binding.ivStateLive.setBackground(liveCircleDrawable);
        binding.tvTypeCharacter.setText(character.getGender());
        binding.tvLocation.setText(character.getLocation().getName());
    }
}