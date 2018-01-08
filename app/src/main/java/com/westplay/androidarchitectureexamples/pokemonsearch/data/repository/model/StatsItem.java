package com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class StatsItem{

	@SerializedName("stat")
	public abstract Stat stat();

	@SerializedName("base_stat")
	public abstract int baseStat();

	@SerializedName("effort")
	public abstract int effort();

	public static TypeAdapter<StatsItem> typeAdapter(Gson gson) {
		return new AutoValue_StatsItem.GsonTypeAdapter(gson);
	}
}