package com.radiolossantos.songsservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Song {

	@Id
	private String id;
	@NonNull
	private String name;
	@NonNull
	private String genre;
	@NonNull
	private String artists;
	private double danceability;
	private double energy;
	private int key;
	private double loudness;
	private int mode;
	private double speechiness;
	private double acousticness;
	private double instrumentalness;
	private double liveness;
	private double valence;
	private double tempo;
	@JsonProperty("duration_ms")
	private double durationMs;
	@JsonProperty("time_signature")
	private double timeSignature;

}
