package com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class AbilitiesItem{

	@SerializedName("is_hidden")
	public abstract boolean isHidden();

	@SerializedName("slot")
	public abstract int slot();

	@SerializedName("ability")
	public abstract Ability ability();

	public static TypeAdapter<AbilitiesItem> typeAdapter(Gson gson) {
		return new AutoValue_AbilitiesItem.GsonTypeAdapter(gson);
	}
}