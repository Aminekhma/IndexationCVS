package fr.sorbonne.IndexationCVS.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import fr.sorbonne.IndexationCVS.documents.Candidat;

public interface CandidatRepository extends ElasticsearchRepository<Candidat,String>{
    
}
