package com.westplay.androidarchitectureexamples.pokemonsearch.data.repository.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class VersionGroupDetailsItem{

	@SerializedName("level_learned_at")
	public abstract int levelLearnedAt();

	@SerializedName("version_group")
	public abstract VersionGroup versionGroup();

	@SerializedName("move_learn_method")
	public abstract MoveLearnMethod moveLearnMethod();

	public static TypeAdapter<VersionGroupDetailsItem> typeAdapter(Gson gson) {
		return new AutoValue_VersionGroupDetailsItem.GsonTypeAdapter(gson);
	}
}