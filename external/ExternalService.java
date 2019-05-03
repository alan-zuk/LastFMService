package ayds.spotisong.aurora.otherdetails.model.repository.external;

import ayds.spotisong.aurora.otherdetails.model.Artist;

public interface ExternalService {

    Artist getArtistInfo(String artistName);
}
