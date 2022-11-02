package com.trainingapps.backend.service;

import com.trainingapps.backend.dto.FavouriteTrackDetails;
import com.trainingapps.backend.dto.AddFavouriteRequest;
import com.trainingapps.backend.dto.RemoveFavouriteRequest;
import com.trainingapps.backend.exception.NoTrackFoundException;
import com.trainingapps.backend.exception.TrackAlreadyExistsException;
import org.springframework.validation.annotation.Validated;


import java.util.List;
@Validated
public interface IFavouriteTrackService {

    FavouriteTrackDetails addToFavourite(AddFavouriteRequest requestData) throws TrackAlreadyExistsException;

    FavouriteTrackDetails removeFavourite(RemoveFavouriteRequest requestData) throws NoTrackFoundException;

    List<FavouriteTrackDetails> listFavouriteTracksByUserId(long userId) throws NoTrackFoundException;


}
