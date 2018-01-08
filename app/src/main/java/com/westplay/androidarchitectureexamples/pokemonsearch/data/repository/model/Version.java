package com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class Version{

	@SerializedName("name")
	public abstract String name();

	@SerializedName("url")
	public abstract String url();

	public static TypeAdapter<Version> typeAdapter(Gson gson) {
		return new AutoValue_Version.GsonTypeAdapter(gson);
	}
}