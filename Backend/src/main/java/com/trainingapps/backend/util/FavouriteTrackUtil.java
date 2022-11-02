package com.trainingapps.backend.util;

import com.trainingapps.backend.dto.FavouriteTrackDetails;
import com.trainingapps.backend.dto.AddFavouriteRequest;
import com.trainingapps.backend.entity.FavouriteTrack;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class FavouriteTrackUtil {

    public FavouriteTrackDetails toFavouriteTrackDetails(FavouriteTrack track){
        FavouriteTrackDetails desired=new FavouriteTrackDetails();
        desired.setName(track.getName());
        desired.setArtistName(track.getArtistName());
        desired.setTrackUrl(track.getTrackUrl());
        desired.setDuration(track.getDuration());
        desired.setAlbumName(track.getAlbumName());
        desired.setAlbumImageUrl(track.getAlbumImageUrl());
        return desired;

    }
    public FavouriteTrack toFavouriteTrack(AddFavouriteRequest track){
        FavouriteTrack desired=new FavouriteTrack();
        desired.setAppUserId(track.getAppUserId());
        desired.setName(track.getName());
        desired.setArtistName(track.getArtistName());
        desired.setTrackUrl(track.getTrackUrl());
        desired.setDuration(track.getDuration());
        desired.setAlbumName(track.getAlbumName());
        desired.setAlbumImageUrl(track.getAlbumImageUrl());
        return desired;

    }

    public List<FavouriteTrackDetails> toFavouriteTrackDetails(List<FavouriteTrack> tracks){
        List<FavouriteTrackDetails> desired=new ArrayList<>();
        for(FavouriteTrack track:tracks){
            FavouriteTrackDetails details=toFavouriteTrackDetails(track);
            desired.add(details);

        }
        return desired;
    }
}
