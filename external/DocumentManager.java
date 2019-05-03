package ayds.spotisong.aurora.otherdetails.model.repository.external;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import retrofit2.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

class DocumentManager {
    private Document document;

    public DocumentManager(){}

    public void setResponse(Response<String> callResponse) {
        armarDocumento(callResponse);
    }

    private Document armarDocumento(Response<String> callResponse){
        document = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.parse(new InputSource(new StringReader(callResponse.body())));
        }catch(Exception e) {e.printStackTrace();}
        return document;
    }

    public String extractBio(){
        NodeList nodes = document.getDocumentElement().getElementsByTagName("content");
        String bio = nodes.item(0).getTextContent().replace("\\n", "\n");
        if(bio.equals(""))
            return null;
        return bio;
    }

    public String extractImageUrl(){
        NodeList images = document.getDocumentElement().getElementsByTagName("image");
        for (int i = 0; i < images.getLength(); i++) {
            Node image = images.item(i);
            NamedNodeMap atts = image.getAttributes();
            Node size = atts.getNamedItem("size");
            if (size.getTextContent().equals("large")) {
                return  image.getTextContent();
            }
        }
        return null;
    }
}
