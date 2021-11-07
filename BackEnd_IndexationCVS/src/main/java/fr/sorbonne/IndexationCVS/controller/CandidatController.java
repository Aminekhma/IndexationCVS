package fr.sorbonne.IndexationCVS.controller;

import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.sorbonne.IndexationCVS.IndexationCvsApplication;
import fr.sorbonne.IndexationCVS.documents.Candidat;
import fr.sorbonne.IndexationCVS.service.CandidatService;

@RestController
@RequestMapping("api/candidat")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Access-Control-Allow-Origin")
public class CandidatController {

    private final CandidatService service;
    Logger logger = LoggerFactory.getLogger(IndexationCvsApplication.class);

    @Autowired
    public CandidatController(CandidatService service) {
        this.service = service;
    }

    @PostMapping
    public void index(@RequestBody final Candidat candidat) {
        service.index(candidat);
    }

    @PostMapping("/addAll")
    public void indexAll() {
        System.out.println("ajout des CVs");
        service.indexAll();
    }

    @GetMapping("/all")
    public List<Candidat> getAll() {
        return service.getAll();
    }

    @GetMapping("/hello")
    public String hello() {
        return "Salut";
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    public Candidat findById(@PathVariable final String id) {
        Candidat cv = service.getById(id);
        if (cv != null) {
            logger.info("\n cv found : "/* , cv.getId() */);
        } else {
            logger.info("\n Error");
        }
        return service.getById(id);
    }

    @GetMapping("/search/{content}")
    public List<Candidat> getAllCandidats(@PathVariable final String content) {
        List<Candidat> cv = service.searchContent(content);
        for (Candidat c : cv) {
            if (c != null) {
                logger.info("\n cv found : " + c.getContent().replace("\n", " "));
            } else {
                logger.info("\n Error");

            }
        }
        return cv;
    }

    @PostMapping("/add")
    public void addCandidat(@RequestBody final String p) {

        JSONObject jsonObject = new JSONObject(p);

        service.addCandidat(jsonObject.getString("chemin"));
    }

    // @DeleteMapping("/deleteAll")
    // public boolean deleteAllCandidats() {
    // return service.deleteByIds();
    // }

}
