package ayds.spotisong.aurora.otherdetails.model.repository.external;

import ayds.spotisong.aurora.otherdetails.model.Artist;
import ayds.spotisong.aurora.otherdetails.model.repository.external.lastfm.LastFMAPI;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ExternalModule {

    private static ExternalModule instance;
    private ExternalService externalService;

    private ExternalModule() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ws.audioscrobbler.com/2.0/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        externalService = new ExternalServiceImp(
                retrofit.create(LastFMAPI.class),
                new Artist(),
                new ArtistInfoFormatterImp(new DocumentManager())

        );


    }

    public static ExternalModule getInstance() {
        if (instance == null) {
            instance = new ExternalModule();
        }
        return instance;
    }

    public ExternalService getExternalService(){return externalService;}
}
