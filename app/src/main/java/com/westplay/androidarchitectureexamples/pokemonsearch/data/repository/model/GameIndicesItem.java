package com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class GameIndicesItem{

	@SerializedName("game_index")
	public abstract int gameIndex();

	@SerializedName("version")
	public abstract Version version();

	public static TypeAdapter<GameIndicesItem> typeAdapter(Gson gson) {
		return new AutoValue_GameIndicesItem.GsonTypeAdapter(gson);
	}
}