import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
@Injectable({
  providedIn: 'root'
})
export class VoteService {

  constructor(private http: HttpClient) { }

  public getUserVotes(userId: number){
    return this.http.get<any>(`${environment.voteserviceurl}/vote/user/` + userId);
  }

  public submitVote(userId: number, songIds: string[]){
    return this.http.post<any>(`${environment.voteserviceurl}/vote`, {
      "userId": userId,
      "songIds": songIds});
  }

  public getMostVotedByAmount(amount: number){
    return this.http.get<any>(`${environment.voteserviceurl}/vote/top/` + amount);
  }

  public getUsersVoteCountOnMostVoted(){
    return this.http.get<any>(`${environment.voteserviceurl}/vote/user/votes`);
  }

}
