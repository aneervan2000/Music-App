import { Component, OnInit } from '@angular/core';
import { TrackUtil } from '../util/track.util';
import { Observable } from 'rxjs';
import { Track } from '../model/Track';
import { TracksService } from '../service/tracks.service';
import { AddFavouriteRequest } from '../model/addFavouriteRequest';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  tracks: Track[] | undefined;

  constructor(private service: TracksService) {
      this.fetch_recommendations();
   }

  ngOnInit(): void {
  }


  fetch_recommendations() : void {
    console.log("tara");

    const observer = {
      next: (result: any) => {
        debugger;
        this.tracks = new TrackUtil().convertRecommendationToTrack(result);
        const tracksText = JSON.stringify(this.tracks);

        console.log(this.tracks);
      },
      error: (error: Error) => {
        console.log(error.message);
      },
    };
    //const observable: Observable<Track[]> = this.service.getAllTracks(12);

    const observable: Observable<Track[]> = this.service.getAllRecommendation();
    observable.subscribe(observer);
  }

  addToFavouritList(data: Track) {
    const requestData: AddFavouriteRequest =
      new TrackUtil().convertToAddRequest(data, 12);
    const observer = {
      next: (result: Track) => {
        alert('Added to favourite list');
        console.log(result);
      },
      error: (error: Error) => {
        console.log(error.message);
      },
    };
    const observable: Observable<Track> = this.service.addTrack(requestData);
    observable.subscribe(observer);
  }

}
