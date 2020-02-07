package com.radiolossantos.voteservice.service;

import java.util.List;

import com.radiolossantos.voteservice.dto.TopVotedSongsResponseDTO;
import com.radiolossantos.voteservice.dto.UsersWhoVotedOnTheTopSongsResponseDTO;
import com.radiolossantos.voteservice.entity.Vote;

public interface VoteService {

	public List<Vote> submitVotes(Long userId, List<String> songIds);

	public List<Vote> findAllByUser(Long userId);
	
	public TopVotedSongsResponseDTO findTopVotedSongs(int amount);
	
	public UsersWhoVotedOnTheTopSongsResponseDTO findUsersThatVotedOnTheMostVotedSongs();
}
