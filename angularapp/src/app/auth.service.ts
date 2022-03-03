import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient) { }
  public RegisterUserFromRemote (user : User):Observable<any>{
    return this.http.post<any>("https://8080-cdadfacbcccdbdffafdfceaeedbbedabdaaddbd.examlyiopb.examly.io/user/login",user)
      }
}
