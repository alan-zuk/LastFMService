package ayds.spotisong.aurora.otherdetails.model.repository.external;

import ayds.spotisong.aurora.otherdetails.model.Artist;
import ayds.spotisong.aurora.otherdetails.model.repository.external.lastfm.LastFMAPI;
import retrofit2.Response;

import java.io.IOException;

class ExternalServiceImp implements  ExternalService{

    private LastFMAPI lastFMAPI;
    private Artist artist;
    private ArtistInfoFormatter artistInfoFormatter;

    public ExternalServiceImp(LastFMAPI lastFMAPI,Artist artist, ArtistInfoFormatter artistInfoFormatter){
        this.artist=artist;
        this.artistInfoFormatter=artistInfoFormatter;
        this.lastFMAPI = lastFMAPI;
    }

    public Artist getArtistInfo(String artistName){
        Artist artistFromExternalService = null;
        try {
            artist.setName(artistName);
            Response<String> callResponse = lastFMAPI.getArtistInfo(artistName).execute();
            artistFromExternalService = artistInfoFormatter.formatArtistInfo(callResponse,artist);
        }
        catch(IOException e){e.getMessage();}

        return artistFromExternalService;

    }


}
