package com.radiolossantos.voteservice.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TopVotedSongsResponseDTO {

	List<SongVotesDTO> songs;

}
