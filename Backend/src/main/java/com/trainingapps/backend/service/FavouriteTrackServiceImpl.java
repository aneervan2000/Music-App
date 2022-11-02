package com.trainingapps.backend.service;

import com.trainingapps.backend.dto.FavouriteTrackDetails;
import com.trainingapps.backend.dto.AddFavouriteRequest;
import com.trainingapps.backend.dto.RemoveFavouriteRequest;
import com.trainingapps.backend.entity.FavouriteTrack;
import com.trainingapps.backend.exception.NoTrackFoundException;
import com.trainingapps.backend.exception.TrackAlreadyExistsException;
import com.trainingapps.backend.repository.IFavouriteTrackRepository;
import com.trainingapps.backend.util.FavouriteTrackUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;

@Service
public class FavouriteTrackServiceImpl implements IFavouriteTrackService {
    @Autowired
    private IFavouriteTrackRepository repository;

    @Autowired
    private FavouriteTrackUtil util;

    private Random random = new Random();


    public String generateId(String album, String track,long userId, String artist) {
        String id=album.toLowerCase()+"-"+track.toLowerCase()+"-"+artist.toLowerCase()+"-u-"+userId;
        return id;
    }

    @Override
    public FavouriteTrackDetails addToFavourite(AddFavouriteRequest requestData) throws TrackAlreadyExistsException {
        Optional<FavouriteTrack> optional = repository.findByAppUserIdAndAlbumNameAndArtistNameAndName(requestData.getAppUserId(), requestData.getAlbumName(), requestData.getArtistName(), requestData.getName());
        if (optional.isPresent()) {
            throw new TrackAlreadyExistsException("Track is already in the favourite list");
        }
        FavouriteTrack track = new FavouriteTrack();
        track = util.toFavouriteTrack(requestData);
        String id=generateId(requestData.getAlbumName(), requestData.getName(), requestData.getAppUserId(), requestData.getArtistName());
        track.setId(id);
        track=repository.save(track);
        FavouriteTrackDetails details = util.toFavouriteTrackDetails(track);
        return details;
    }

    @Override
    public FavouriteTrackDetails removeFavourite(RemoveFavouriteRequest requestData) throws NoTrackFoundException {
        Optional<FavouriteTrack> optional = repository.findByAppUserIdAndAlbumNameAndArtistNameAndName(requestData.getAppUserId(), requestData.getAlbumName(), requestData.getArtistName(), requestData.getName());
        if (!optional.isPresent()) {
            throw new NoTrackFoundException("No track found");
        }
        FavouriteTrack track = optional.get();
        repository.delete(track);
        FavouriteTrackDetails details = util.toFavouriteTrackDetails(track);
        return details;
    }

    @Override
    public List<FavouriteTrackDetails> listFavouriteTracksByUserId(long userId) throws NoTrackFoundException {
        List<FavouriteTrack> tracks = repository.findByAppUserId(userId);
        if (tracks.isEmpty()) {
            throw new NoTrackFoundException("No track found");
        }
        List<FavouriteTrackDetails> desired = util.toFavouriteTrackDetails(tracks);
        return desired;
    }
}
