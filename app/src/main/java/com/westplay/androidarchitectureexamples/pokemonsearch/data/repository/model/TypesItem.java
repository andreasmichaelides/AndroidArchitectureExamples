package com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class TypesItem{

	@SerializedName("slot")
	public abstract int slot();

	@SerializedName("type")
	public abstract Type type();

	public static TypeAdapter<TypesItem> typeAdapter(Gson gson) {
		return new AutoValue_TypesItem.GsonTypeAdapter(gson);
	}
}