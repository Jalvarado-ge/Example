package com.example.example.ui.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.example.R;
import com.example.example.databinding.ItemCardViewCharacterBinding;
import com.example.example.models.CharacterRickAndMorty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class RecyclerCharacterAdapter extends RecyclerView.Adapter<RecyclerCharacterAdapter.ViewHolder> {

    private ItemCardViewCharacterBinding binding;
    private List<CharacterRickAndMorty> list;

    private NavController navController;

    public RecyclerCharacterAdapter(List<CharacterRickAndMorty> list, NavController navController) {
        this.list = list;
        this.navController = navController;
    }

    @NonNull
    @Override
    public RecyclerCharacterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCardViewCharacterBinding view = ItemCardViewCharacterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        binding = view;
        return new ViewHolder(view.getRoot(), view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerCharacterAdapter.ViewHolder holder, int position) {
//        binding.tvName.setText(list.get(position).getName());
//        binding.tvState.setText(list.get(position).getStatus());
//        binding.tvTypeCharacter.setText(list.get(position).getSpecies());
//        binding.tvFirsTSeenIn.setText(list.get(position).getGender());
//        Glide.with(holder.itemView.getContext())
//                .load(list.get(position).getImage())
//                .into(binding.ivCharacter);
        holder.setItem(list.get(position));
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemCardViewCharacterBinding characterBinding;
        Context context;

        public ViewHolder(@NonNull View itemView, ItemCardViewCharacterBinding itemCardViewCharacterBinding, Context context) {
            super(itemView);
            characterBinding = itemCardViewCharacterBinding;
            this.context = context;
        }

        public void setItem(CharacterRickAndMorty rickAndMorty) {

            Glide.with(context)
                    .load(rickAndMorty.getImage())
                    .into(characterBinding.ivCharacter);
            characterBinding.tvName.setText(rickAndMorty.getName());
            characterBinding.tvState.setText(rickAndMorty.getStatus()+" - ");
            Drawable liveCircleDrawable;
            if (rickAndMorty.getStatus().equals("Alive")){
                liveCircleDrawable = ContextCompat.getDrawable(context, R.drawable.live_circle);
            }else {
                liveCircleDrawable = ContextCompat.getDrawable(context, R.drawable.dead_circle);
            }
            characterBinding.ivStateLive.setBackground(liveCircleDrawable);
            characterBinding.tvTypeCharacter.setText(rickAndMorty.getSpecies());
            characterBinding.tvFirsTSeenIn.setText(rickAndMorty.getGender());

            itemView.setOnClickListener(view -> {
                Gson gson;
                gson = new GsonBuilder().create();
                MainFragmentDirections.ActionMainFragmentToDetailCharacterFragment action = MainFragmentDirections.actionMainFragmentToDetailCharacterFragment();
                action.setCharacter(gson.toJson(rickAndMorty));
                navController.navigate(action);
//                Navigation.findNavController(itemView.getRootView()).navigate(action);
            });
        }
    }
}
