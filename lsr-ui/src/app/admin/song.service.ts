import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class SongService {

  constructor(private http: HttpClient) { }

  public loadSongs(){
    return this.http.get<any>(`${environment.songserviceurl}/song`);
  }

  public getSong(songId: string){ 
    return this.http.get<any>(`${environment.songserviceurl}/song/` + songId);
  }

}
