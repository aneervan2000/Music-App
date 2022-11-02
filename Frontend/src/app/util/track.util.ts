import { AddFavouriteRequest } from '../model/addFavouriteRequest';
import { RemoveFavouriteRequest } from '../model/removeFavouriteRequest';
import { Track } from '../model/Track';

export class TrackUtil {
  convertToTrack(trackData: any): Track {
    const track: Track = new Track();
    track.name = trackData.name;
    track.duration = -1;
    if (trackData.duration) {
      track.duration = trackData.duration;
    }

    track.artistName = trackData.artist.name;
    track.trackUrl = trackData.url;
    return track;
  }
  convertToTracks(data: any): Track[] {
    debugger;
    const tracksData = data.album.tracks.track;
    const albumName = data.album.name;
    const albumImgUrl = data.album.image[4]['#text'];
    const desired: Track[] = [];
    for (let iterated of tracksData) {
      const track = this.convertToTrack(iterated);
      track.albumImageUrl = albumImgUrl;
      track.albumName = albumName;
      desired.push(track);
    }
    return desired;
  }

  convertRecommendationToTrack(data: any): Track[] {
    debugger;
    const tracksData = data.tracks.track;
    const desired: Track[] = [];
    for (let iterated of tracksData) {
      const trackName = iterated.name;
      const albumImgUrl = iterated.image[3]['#text'];
      const track = this.convertToTrack(iterated);
      track.albumImageUrl = albumImgUrl;
      track.albumName = trackName;
      desired.push(track);
    }
    return desired;
  }

  convertToAddRequest(data: Track, userId:number): AddFavouriteRequest {
    const requestData = new AddFavouriteRequest();
    requestData.name = data.name;
    requestData.artistName = data.artistName;
    requestData.albumName = data.albumName;
    requestData.trackUrl = data.trackUrl;
    requestData.duration = data.duration;
    requestData.albumImageUrl = data.albumImageUrl;
    requestData.appUserId = userId;
    return requestData;
  }
  convertToRemoveRequest(data: Track, userId:number): RemoveFavouriteRequest {
    const requestData = new RemoveFavouriteRequest();
    requestData.name = data.name;
    requestData.artistName = data.artistName;
    requestData.albumName = data.albumName;
    requestData.appUserId = userId;
    return requestData;
  }
}
