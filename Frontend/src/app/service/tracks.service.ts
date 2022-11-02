import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AddFavouriteRequest } from '../model/addFavouriteRequest';
import { Track } from '../model/Track';

@Injectable({
  providedIn: 'root'
})
export class TracksService {

  constructor(private client : HttpClient) { }

  addTrack(requestData: AddFavouriteRequest): Observable<Track> {
    const url = 'http://localhost:8585/favouritetracks/add';
    const observable: Observable<Track> = this.client.post<AddFavouriteRequest>(url,requestData);
    return observable;
  }

  getAllTracks(appUserId:any): Observable<Track[]> {
    const url = 'http://localhost:8585/favouritetracks/byid/'+appUserId;
    const observable: Observable<Track[]> = this.client.get<Track[]>(url);
    return observable;
  }

  getAllRecommendation(): Observable<Track[]> {
    const url = 'https://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&country=INDIA&api_key=22e0b07f98e3ef117bfab7087178b3cf&format=json';
    const observable: Observable<any[]> = this.client.get<any[]>(url);
    return observable;
  }
  
  deleteTrack(appUserId:any, name:any,  albumName:any, artistName:any ): Observable<Track> {
    const url ="http://localhost:8585/favouritetracks/delete/"+appUserId+"/"+name+"/"+albumName+"/"+artistName;
    const observable: Observable<Track> = this.client.delete<Track>(url);
    return observable;
  }

  getTrackFromExternalServer(album:string,artist:string):Observable<any>{
    const url=`https://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key=22e0b07f98e3ef117bfab7087178b3cf&artist=${artist}&album=${album}&format=json`;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Length': '0'
      })
    };

    
    
    const observable: Observable<any> = this.client.post(url,httpOptions);
    return observable;
  }
}
