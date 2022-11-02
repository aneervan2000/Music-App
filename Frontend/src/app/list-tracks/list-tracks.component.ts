import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { RemoveFavouriteRequest } from '../model/removeFavouriteRequest';
import { Track } from '../model/Track';
import { TracksService } from '../service/tracks.service';
import { TrackUtil } from '../util/track.util';

@Component({
  selector: 'app-list-tracks',
  templateUrl: './list-tracks.component.html',
  styleUrls: ['./list-tracks.component.css']
})
export class ListTracksComponent implements OnInit {

  tracks: Track[] | undefined;

  constructor(private service: TracksService) {
    this.fetchTracks();
  }

  ngOnInit(): void {}
  
  fetchTracks() {
    const observer = {
      next: (result: Track[]) => {
        this.tracks = result;
        console.log(this.tracks);
      },
      error: (error: Error) => {
        console.log(error.message);
      },
    };
    const observable: Observable<Track[]> = this.service.getAllTracks(12);
    observable.subscribe(observer);
  }

  isDurationProvided(duration: number): boolean {
    return duration > 0;
  }

  removeTrack(data: Track) {
    const requestData:RemoveFavouriteRequest=new TrackUtil().convertToRemoveRequest(data,12);
    const observer = {
      next: (result: Track) => {
        
        console.log(result);
        alert("Track is removed from favourite list");
        this.fetchTracks();
      },
      error: (error: Error) => {
        console.log(error.message);
      },
    };
    const observable: Observable<Track> = this.service.deleteTrack(requestData.appUserId,requestData.name,requestData.albumName,requestData.artistName);
    observable.subscribe(observer);
    
  }
}
