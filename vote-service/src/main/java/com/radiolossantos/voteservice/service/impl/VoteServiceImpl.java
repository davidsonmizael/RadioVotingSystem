package com.radiolossantos.voteservice.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radiolossantos.voteservice.dao.VoteDAO;
import com.radiolossantos.voteservice.dto.SongVotesDTO;
import com.radiolossantos.voteservice.dto.TopVotedSongsResponseDTO;
import com.radiolossantos.voteservice.dto.UserVoteCountOnMostVotedDTO;
import com.radiolossantos.voteservice.dto.UsersWhoVotedOnTheTopSongsResponseDTO;
import com.radiolossantos.voteservice.entity.Vote;
import com.radiolossantos.voteservice.service.VoteService;

@Service
public class VoteServiceImpl implements VoteService {
	
	@Autowired
	private VoteDAO voteDAO;

	@Override
	public List<Vote> submitVotes(Long userId, List<String> songIds) {
		List<Vote> votes = new ArrayList<>();
		for(String songId: songIds) {
			votes.add(new Vote(songId, userId));
		}
		return voteDAO.saveAll(votes);
	}

	@Override
	public List<Vote> findAllByUser(Long userId) {
		return voteDAO.findAllByUserId(userId);
	}

	@Override
	public TopVotedSongsResponseDTO findTopVotedSongs(int amount) {
		TopVotedSongsResponseDTO response = null;
		List<Tuple> votes = voteDAO.findMostVotedSongs(amount);
		
		if(null != votes && !votes.isEmpty()) {
			response = new TopVotedSongsResponseDTO();
			List<SongVotesDTO> songVotes = new ArrayList<>();
			for(Tuple songVote: votes) {
				songVotes.add(new SongVotesDTO((String) songVote.get(0), ((BigInteger) songVote.get(1)).intValue()));
			}
			response.setSongs(songVotes);
		}
		return response;
	}

	@Override
	public UsersWhoVotedOnTheTopSongsResponseDTO findUsersThatVotedOnTheMostVotedSongs() {
		UsersWhoVotedOnTheTopSongsResponseDTO response = null;
		List<String> songIds = new ArrayList<>();
		List<Tuple> votes = voteDAO.findMostVotedSongs(5);
		if(null != votes && !votes.isEmpty()) {
			for(Tuple songVote: votes) {
				songIds.add((String) songVote.get(0));
			}
			List<Tuple> userVotes = voteDAO.findUsersWhoVotedOnMostVoted(songIds);
			if (null != userVotes && !userVotes.isEmpty()) {
				List<UserVoteCountOnMostVotedDTO> userVotesDTO = new ArrayList<>();
				response = new UsersWhoVotedOnTheTopSongsResponseDTO();
				for(Tuple userVote: userVotes) {
					userVotesDTO.add(new UserVoteCountOnMostVotedDTO(((BigInteger) userVote.get(0)).intValue(),
							((BigInteger) userVote.get(1)).intValue()));
				}
				response.setUserVotes(userVotesDTO);
			}
		}
		return response;
	}

}
