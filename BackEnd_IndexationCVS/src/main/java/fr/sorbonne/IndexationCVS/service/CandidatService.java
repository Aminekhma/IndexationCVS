package fr.sorbonne.IndexationCVS.service;

import fr.utils.pdf_tools.FileManager;
import fr.utils.pdf_tools.PDFManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.sorbonne.IndexationCVS.documents.Candidat;
import fr.sorbonne.IndexationCVS.helper.Indices;
import fr.sorbonne.IndexationCVS.search.util.SearchUtil;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CandidatService {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOG = LoggerFactory.getLogger(CandidatService.class);
    PDFManager pdfManager = new PDFManager();

    String directory_path = FileManager.getProjectRoot()
            + "/BackEnd_IndexationCVS/src/main/java/fr/sorbonne/IndexationCVS/service/assets/";

    String directory_path2 = FileManager.getProjectRoot()
            + "/BackEnd_IndexationCVS/src/main/java/fr/sorbonne/IndexationCVS/service/assets/";

    private final RestHighLevelClient client;

    @Autowired
    public CandidatService(RestHighLevelClient client) {
        this.client = client;
    }

    public List<Candidat> searchContent(final String content) {

        final SearchRequest request = SearchUtil.buildSearchRequest(Indices.CANDIDAT_INDEX, "content", content);
        return searchInternal(request);
    }

    public List<Candidat> getAll() {
        final SearchRequest request = SearchUtil.buildSearchRequest(Indices.CANDIDAT_INDEX, "id");
        return searchInternal(request);
    }

    /**
     * @param candidats : a candidat with a id, content and a name
     * @return true if the true if the element has been indexed correctly,  false otherwise
     */
    public Boolean index(final Candidat candidats) {
        try {
            final String candidatsAsString = MAPPER.writeValueAsString(candidats);

            final IndexRequest request = new IndexRequest(Indices.CANDIDAT_INDEX);
            request.id(candidats.getId());
            request.source(candidatsAsString, XContentType.JSON);
            final IndexResponse response = client.index(request, RequestOptions.DEFAULT);

            return response != null && response.status().equals(RestStatus.OK);
        } catch (final Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    public void assetsCVFolder() {
        ArrayList<String> list = FileManager.listPdfFilesFromFolder(directory_path);

        // System.out.println(list);

        // ouvrire le fichier
        FileReader file;
        try {
            file = new FileReader(directory_path + "utils/index.txt");

            BufferedReader buffer = new BufferedReader(file);
            // lire la 1er ligne
            String indexFile;
            indexFile = buffer.readLine();

            String newName;

            for (int i = 0; i < list.size(); i++) {

                if (list.get(i).matches("^CV[1-9].*")) {
                    // System.out.println(list.get(i)+" : True");

                } else {
                    int index = Integer.parseInt(indexFile) + 1;
                    FileOutputStream fileOut = new FileOutputStream(directory_path + "utils/index.txt");
                    fileOut.write(Integer.toString(index).getBytes());
                    fileOut.close();

                    // System.out.println(list.get(i)+" : False");
                    newName = "CV" + Integer.toString(index) + ".pdf";
                    // System.out.println(newName);
                    FileManager.changeFileName(directory_path2 + list.get(i), newName);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addCandidat(String path) {

        FileManager.movePDF(path, directory_path + "a.pdf");
        assetsCVFolder();
    }

    public void indexAll() {

        try {
            // ajoute tous les CV de la CVTheques

            assetsCVFolder();

            ArrayList<JSONObject> json_list = pdfManager.dir2Json(directory_path);

            for (JSONObject obj : json_list) {

                String file_name = obj.getString("name");

                String jpgpath = FileManager.getProjectRoot() + "/FrontEnd_IndexationCVS/src/assets/cvs/" + file_name;
                String filepath = directory_path + file_name;

                Candidat c = buildCandidat(obj.getString("id"), obj.getString("content"),
                        file_name.replaceAll("(\\w*)pdf", "png"));

                // System.out.println(c);
                PDFManager.PDFtoJPG(filepath, jpgpath);

                index(c);
            }

        } catch (final Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private List<Candidat> searchInternal(final SearchRequest request) {
        if (request == null) {
            LOG.error("Failed to build search request");
            return Collections.emptyList();
        }

        try {
            final SearchResponse response = client.search(request, RequestOptions.DEFAULT);

            final SearchHit[] searchHits = response.getHits().getHits();
            final List<Candidat> candidats = new ArrayList<>(searchHits.length);
            for (SearchHit hit : searchHits) {
                candidats.add(MAPPER.readValue(hit.getSourceAsString(), Candidat.class));

            }
            return candidats;

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public Candidat getById(final String candidatId) {
        try {
            final GetResponse documentFields = client.get(new GetRequest(Indices.CANDIDAT_INDEX, candidatId),
                    RequestOptions.DEFAULT);
            if (documentFields == null || documentFields.isSourceEmpty()) {
                return null;
            }
            return MAPPER.readValue(documentFields.getSourceAsString(), Candidat.class);

        } catch (final Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

    private static Candidat buildCandidat(final String id, final String content, final String name) {
        Candidat candidat = new Candidat();
        candidat.setId(id);
        candidat.setContent(content);
        candidat.setName(name);
        return candidat;
    }

}
