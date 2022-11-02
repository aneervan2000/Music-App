package com.trainingapps.backend.controller;

import com.trainingapps.backend.dto.FavouriteTrackDetails;
import com.trainingapps.backend.dto.AddFavouriteRequest;
import com.trainingapps.backend.dto.RemoveFavouriteRequest;
import com.trainingapps.backend.exception.NoTrackFoundException;
import com.trainingapps.backend.exception.TrackAlreadyExistsException;
import com.trainingapps.backend.service.IFavouriteTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favouritetracks")
public class FavouriteTrackRestApi {
    @Autowired
    private IFavouriteTrackService service;

    @CrossOrigin(origins = "http://localhost:55101")
    @GetMapping("/byid/{id}")
    public List<FavouriteTrackDetails> findAll(@PathVariable long id) throws NoTrackFoundException {
        List<FavouriteTrackDetails> response = service.listFavouriteTracksByUserId(id);
        return response;
    }

    @CrossOrigin(origins = "http://localhost:55101")
    @PostMapping("/add")
    public FavouriteTrackDetails add(@RequestBody AddFavouriteRequest requestData) throws TrackAlreadyExistsException {
        FavouriteTrackDetails response = service.addToFavourite(requestData);
        return response;
    }

    @CrossOrigin(origins = "http://localhost:55101")
    @DeleteMapping("/delete/{appUserId}/{name}/{albumName}/{artistName}")
    public FavouriteTrackDetails remove(@PathVariable long appUserId, @PathVariable String name, @PathVariable String albumName, @PathVariable String artistName) throws NoTrackFoundException {
        RemoveFavouriteRequest requestData=new RemoveFavouriteRequest();
        requestData.setAppUserId(appUserId);
        requestData.setAlbumName(albumName);
        requestData.setName(name);
        requestData.setArtistName(artistName);
        FavouriteTrackDetails response = service.removeFavourite(requestData);
        return response;
    }
}
