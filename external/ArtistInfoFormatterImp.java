package ayds.spotisong.aurora.otherdetails.model.repository.external;

import ayds.spotisong.aurora.otherdetails.model.Artist;
import retrofit2.Response;

class ArtistInfoFormatterImp implements ArtistInfoFormatter {

    private DocumentManager documentManager;

    public ArtistInfoFormatterImp(DocumentManager documentManager) {
        this.documentManager = documentManager;
    }

    public Artist formatArtistInfo(Response<String> callResponse, Artist artist) {
        String imageUrl;
        String bio;
        try {
            documentManager.setResponse(callResponse);
            bio = documentManager.extractBio();
            imageUrl = documentManager.extractImageUrl();
            if(bio!=null && imageUrl!=null){
                artist.setBio(armarBio(bio));
                artist.setImageUrl(imageUrl);
                return artist;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }

    private String armarBio(String bio){
        bio = planeTextForDB(bio);
        return bio;
    }

    private String planeTextForDB(String text) {
        StringBuilder builder = new StringBuilder();
        String textWithoutQuotationMarks = text.replace("'", "`");
        builder.append(textWithoutQuotationMarks);
        return builder.toString();
    }
}

