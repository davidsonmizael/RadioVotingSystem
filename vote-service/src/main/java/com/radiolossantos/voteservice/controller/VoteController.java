package com.radiolossantos.voteservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.radiolossantos.voteservice.dto.SubmitVotesRequestDTO;
import com.radiolossantos.voteservice.dto.TopVotedSongsResponseDTO;
import com.radiolossantos.voteservice.dto.UsersWhoVotedOnTheTopSongsResponseDTO;
import com.radiolossantos.voteservice.entity.Vote;
import com.radiolossantos.voteservice.service.VoteService;

@Controller
@RequestMapping("/vote")
public class VoteController {
	
	@Autowired
	private VoteService voteService;
	
	@GetMapping("/user/{id}")
	public @ResponseBody List<Vote> getVote(@PathVariable Long id) {
		return voteService.findAllByUser(id);
	}
	
	@GetMapping("/user/votes")
	public @ResponseBody UsersWhoVotedOnTheTopSongsResponseDTO getUsersWhoVotedOnTheTopSongs() {
		return voteService.findUsersThatVotedOnTheMostVotedSongs();
	}
	
	
	@PostMapping
	public @ResponseBody List<Vote> submitVotes(@RequestBody SubmitVotesRequestDTO submitVotesRequest) {
		return voteService.submitVotes(submitVotesRequest.getUserId(), submitVotesRequest.getSongIds());
	}
	
	
	@GetMapping("/top/{amount}")
	public @ResponseBody TopVotedSongsResponseDTO getTopMostVoted(@PathVariable int amount) {		
		return voteService.findTopVotedSongs(amount);
	}

}
