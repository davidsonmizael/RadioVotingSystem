package com.radiolossantos.voteservice.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import javax.persistence.Tuple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.radiolossantos.voteservice.dao.VoteDAO;
import com.radiolossantos.voteservice.dto.TopVotedSongsResponseDTO;
import com.radiolossantos.voteservice.dto.UsersWhoVotedOnTheTopSongsResponseDTO;
import com.radiolossantos.voteservice.entity.Vote;
import com.radiolossantos.voteservice.service.impl.VoteServiceImpl;

@ExtendWith(MockitoExtension.class)
public class VoteServiceImplTest {

	@Mock
	private VoteDAO voteDAO;

	@InjectMocks
	@Spy
	private VoteServiceImpl voteService;
	
	@BeforeEach
	public void setup() {

	
	}
	
	@Test
	public void testFindAllByUser() {
		List<Vote> testVotes = new ArrayList<>();
		LongStream.range(1, 10).forEachOrdered(l ->{
			testVotes.add(new Vote("aaaaaaaa", l));
			testVotes.add(new Vote("bbbbbbbb", l));
			testVotes.add(new Vote("cccccccc", l));
			
		});

		when(voteDAO.findAllByUserId(1L)).thenReturn(testVotes.stream().filter(v -> v.getUserId().equals(1L)).collect(Collectors.toList()));
		assertEquals(voteService.findAllByUser(1L).size(), 3);

	}
	
	@Test
	public void testSubmitVotes() {
		List<Vote> testVotes = new ArrayList<>();
		testVotes.add(new Vote(1L, "aaaaaaaa", 1L));
		testVotes.add(new Vote(2L, "bbbbbbbb", 1L));
		testVotes.add(new Vote(3L, "cccccccc", 1L));

		
		List<Vote> userVotes = new ArrayList<>();
		userVotes.add(new Vote("aaaaaaaa", 1L));
		userVotes.add(new Vote("bbbbbbbb", 1L));
		userVotes.add(new Vote("cccccccc", 1L));
		
		when(voteDAO.saveAll(userVotes)).thenReturn(testVotes);
		
		List<String> lines = Arrays.asList("aaaaaaaa", "bbbbbbbb", "cccccccc");
		
		assertEquals(voteService.submitVotes(1L, lines), testVotes);
		
	}
	
	@Test
	public void testFindTopVotedSongs() {
		
		List<Tuple> tupleResultTest = new ArrayList<>();
		List<String> songIds = Arrays.asList("aaaaaaaa", "bbbbbbbb", "cccccccc");
		
		IntStream.range(1, 3).forEachOrdered(i ->{
			Tuple line = Mockito.mock(Tuple.class);
			
			when(line.get(0)).thenReturn(String.valueOf(songIds.get(i - 1)));
			when(line.get(1)).thenReturn(BigInteger.valueOf(30/i));
			
			tupleResultTest.add(line);
		});
		
		when(voteDAO.findMostVotedSongs(3)).thenReturn(tupleResultTest);
		TopVotedSongsResponseDTO serviceResult = voteService.findTopVotedSongs(3);
		
		assertNotNull(serviceResult);
		IntStream.range(0, 2).forEachOrdered(i ->{
			assertEquals(serviceResult.getSongs().get(i).getSongId(), songIds.get(i));
		});
	}
	
	@Test
	public void testFindUsersThatVotedOnTheMostVotedSongs() {
		List<Tuple> tupleResultTest = new ArrayList<>();
		List<Tuple> tupleSongsResultTest = new ArrayList<>();
		List<String> songIds = Arrays.asList("aaaaaaaa", "bbbbbbbb", "cccccccc", "dddddddd", "eeeeeeee");
		
		IntStream.range(1, 6).forEachOrdered(i ->{
			Tuple line = Mockito.mock(Tuple.class);
			
			when(line.get(0)).thenReturn(String.valueOf(songIds.get(i - 1)));
			
			tupleSongsResultTest.add(line);
		});
		
		IntStream.range(1, 3).forEachOrdered(i ->{
			Tuple line = Mockito.mock(Tuple.class);
			
			when(line.get(0)).thenReturn(BigInteger.valueOf(i));
			when(line.get(1)).thenReturn(BigInteger.valueOf(i*2));
			
			tupleResultTest.add(line);
		});
		when(voteDAO.findMostVotedSongs(5)).thenReturn(tupleSongsResultTest);
		when(voteDAO.findUsersWhoVotedOnMostVoted(songIds)).thenReturn(tupleResultTest);
		UsersWhoVotedOnTheTopSongsResponseDTO serviceResult = voteService.findUsersThatVotedOnTheMostVotedSongs();
		assertNotNull(serviceResult);
	}
	


}