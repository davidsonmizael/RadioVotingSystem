package com.radiolossantos.voteservice.dto;


import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class SubmitVotesRequestDTO {
	
	private Long userId;
	private List<String> songIds;

}
