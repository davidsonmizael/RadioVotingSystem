package com.radiolossantos.voteservice.dao;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.radiolossantos.voteservice.entity.Vote;

public interface VoteDAO extends JpaRepository<Vote, Long>{
	
	List<Vote> findAllByUserId(Long id);
	
	@Query(value="select song_id, count(song_id)\r\n" + 
			"from Vote\r\n" + 
			"group by song_id\r\n" + 
			"order by 2 desc\r\n" +
			"limit ?1",nativeQuery=true)
	List<Tuple> findMostVotedSongs(int count);
	
	@Query(value="select user_id, count(song_id)\r\n"
			+ "from Vote\r\n"
			+ "where song_id in ?1\r\n" 
			+ "group by user_id",nativeQuery=true)
	List<Tuple> findUsersWhoVotedOnMostVoted(List<String> songIds);

}
