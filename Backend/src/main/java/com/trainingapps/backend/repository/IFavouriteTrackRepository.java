package com.trainingapps.backend.repository;

import com.trainingapps.backend.entity.FavouriteTrack;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFavouriteTrackRepository extends MongoRepository<FavouriteTrack, String> {

    List<FavouriteTrack> findByAppUserId(long appUserId);

    Optional<FavouriteTrack> findByAppUserIdAndAlbumNameAndArtistNameAndName(long userId, String album, String artist, String track);
}
