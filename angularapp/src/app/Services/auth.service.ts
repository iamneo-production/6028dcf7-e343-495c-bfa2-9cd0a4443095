import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient) { }
  RegisterUserFromRemote (user : User):Observable<any>{
    return this.http.post<any>("https://8080-fcacaebadcfbffafdfceaeedbbedabdaaddbd.examlyiopb.examly.io/user/signup",user)
      }
  LoginUserFromRemote (user : User):Observable<any>{
    return this.http.post<any>("https://8080-fcacaebadcfbffafdfceaeedbbedabdaaddbd.examlyiopb.examly.io/user/login",user)
      }

    // user api    
  getUserList(): Observable<User[]>
    {
      return this.http.get<User[]>("https://8080-cdadfacbcccdbdffafdfceaeedbbedabdaaddbd.examlyiopb.examly.io/user/getUser/");
    }
  getUsersById(id: number) :Observable<User>
    {
      return this.http.get<User> ("https://8080-cdadfacbcccdbdffafdfceaeedbbedabdaaddbd.examlyiopb.examly.io/user/viewUser/"+id);
    }
  deleteuser(id: number): Observable<Object>{
      return this.http.delete("https://8080-cdadfacbcccdbdffafdfceaeedbbedabdaaddbd.examlyiopb.examly.io/user/deleteUser/"+id);
    }
  edituser(id: number, user: User): Observable<Object>{
     return this.http.put("https://8080-cdadfacbcccdbdffafdfceaeedbbedabdaaddbd.examlyiopb.examly.io/user/editUser/"+id, user);
    }

    //theme api
  postTheme(data:any){
    return this.http.post<any>("https://8080-cdadfacbcccdbdffafdfceaeedbbedabdaaddbd.examlyiopb.examly.io/admin/addTheme",data);
    }
  getTheme(){
    return this.http.get<any>("https://8080-cdadfacbcccdbdffafdfceaeedbbedabdaaddbd.examlyiopb.examly.io/admin/getTheme");
    }
  putTheme(data:any,id:number){
    return this.http.put<any>("https://8080-cdadfacbcccdbdffafdfceaeedbbedabdaaddbd.examlyiopb.examly.io/admin/editTheme/"+id ,data);
    }
  deleteTheme(id:number){
    return this.http.delete<any>("https://8080-cdadfacbcccdbdffafdfceaeedbbedabdaaddbd.examlyiopb.examly.io/admin/deleteTheme/"+id);
    }

  //menu api
  postItem(data:any){
    return this.http.post<any>("https://8080-cdadfacbcccdbdffafdfceaeedbbedabdaaddbd.examlyiopb.examly.io/admin/addMenu",data);
    }
  getItem(){
    return this.http.get<any>("https://8080-cdadfacbcccdbdffafdfceaeedbbedabdaaddbd.examlyiopb.examly.io/admin/getMenu");
    }
  putItem(data:any,id:number){
    return this.http.put<any>("https://8080-cdadfacbcccdbdffafdfceaeedbbedabdaaddbd.examlyiopb.examly.io/admin/editMenu/"+id ,data);
    }
  deleteItem(id:number){
    return this.http.delete<any>("https://8080-cdadfacbcccdbdffafdfceaeedbbedabdaaddbd.examlyiopb.examly.io/admin/deleteMenu/"+id);
    }

  //addon api
  postAddon(data:any){
    return this.http.post<any>("https://8080-cdadfacbcccdbdffafdfceaeedbbedabdaaddbd.examlyiopb.examly.io/admin/addAddon",data);
    }
  getAddon(){
    return this.http.get<any>("https://8080-cdadfacbcccdbdffafdfceaeedbbedabdaaddbd.examlyiopb.examly.io/admin/getAddon");
    }
  putAddon(data:any,id:number){
    return this.http.put<any>("https://8080-cdadfacbcccdbdffafdfceaeedbbedabdaaddbd.examlyiopb.examly.io/admin/editAddon/"+id ,data);
    }
  deleteAddon(id:number){
    return this.http.delete<any>("https://8080-cdadfacbcccdbdffafdfceaeedbbedabdaaddbd.examlyiopb.examly.io/admin/deleteAddon/"+id);
    }
}
