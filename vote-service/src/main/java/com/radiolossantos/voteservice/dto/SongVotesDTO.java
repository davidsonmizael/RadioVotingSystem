package com.radiolossantos.voteservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class SongVotesDTO {

	private String songId;
	private int voteCount;

}
