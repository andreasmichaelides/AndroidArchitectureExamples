package com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class MovesItem{

	@SerializedName("version_group_details")
	public abstract List<VersionGroupDetailsItem> versionGroupDetails();

	@SerializedName("move")
	public abstract Move move();

	public static TypeAdapter<MovesItem> typeAdapter(Gson gson) {
		return new AutoValue_MovesItem.GsonTypeAdapter(gson);
	}
}