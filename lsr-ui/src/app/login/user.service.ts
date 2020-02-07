import { Injectable } from '@angular/core';
import { environment } from './../../environments/environment';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }


  public login(nickname: string){
    return this.http.post<any>(`${environment.userserviceurl}/user`, {
      "nickname": nickname});
  }

  public getUser(userId: string){
    return this.http.get<any>(`${environment.userserviceurl}/user/` + userId);
  }
}
