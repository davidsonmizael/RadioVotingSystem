package com.radiolossantos.songsservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SongDTO {
	
	private String id;
	private String name;
	private String artistName;
	private String genre;

}
