package ayds.spotisong.aurora.otherdetails.model.repository.external;

import ayds.spotisong.aurora.otherdetails.model.Artist;
import retrofit2.Response;

public interface ArtistInfoFormatter {

    Artist formatArtistInfo(Response<String> callResponse,Artist artist);
}