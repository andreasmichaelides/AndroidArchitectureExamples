package com.westplay.androidarchitectureexamples.pokemonsearch;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.westplay.androidarchitectureexamples.R;
import com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.model.PokemonResponse;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class PokemonSearchActivity extends DaggerAppCompatActivity {

    private static final String POKEMON_QUERY = "POKEMON_QUERY";

    @Inject
    PokemonSearchViewModelFactory viewModelFactory;

    @BindView(R.id.pokemonSearchSearchView)
    SearchView pokemonSearchSearchView;
    @BindView(R.id.pokemonSearchProgressBar)
    ProgressBar pokemonSearchProgressBar;
    @BindView(R.id.pokemonSearchImageView)
    ImageView pokemonSearchImageView;
    @BindView(R.id.pokemonSearchName)
    TextView pokemonSearchName;

    private PokemonSearchViewModel viewModel;
    private String lastSearchQuery = "";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_search);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PokemonSearchViewModel.class);

        pokemonSearchSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                setLastSearchQuery(query);
                viewModel.searchPokemon(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        viewModel.searchResult()
                .observe(this, pokemonResponse -> setPokemonData(pokemonResponse));
        viewModel.searchError()
                .observe(this, message -> Snackbar.make(pokemonSearchSearchView, message, Snackbar.LENGTH_SHORT).show());
        viewModel.isLoading()
                .observe(this, isLoading -> pokemonSearchProgressBar.setVisibility(isLoading ? VISIBLE : INVISIBLE));

        restoreInstance(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!lastSearchQuery.isEmpty()) {
            outState.putString(POKEMON_QUERY, lastSearchQuery);
        }

        super.onSaveInstanceState(outState);
    }

    private void restoreInstance(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            String query = savedInstanceState.getString(POKEMON_QUERY);
            if (query != null) {
                setLastSearchQuery(query);
                viewModel.restoreSearch(query);
            }
        }
    }

    private void setLastSearchQuery(String query) {
        lastSearchQuery = query;
    }

    private void setPokemonData(PokemonResponse pokemonResponse) {
        pokemonSearchName.setText(pokemonResponse.name());
        Glide.with(this)
                .load(pokemonResponse.sprites().frontDefault())
                .into(pokemonSearchImageView);
    }
}
