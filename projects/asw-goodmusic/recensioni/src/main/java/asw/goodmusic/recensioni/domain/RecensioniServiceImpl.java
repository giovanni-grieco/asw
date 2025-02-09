package asw.goodmusic.recensioni.domain;

import asw.goodmusic.common.api.messaging.DomainEvent;
import asw.goodmusic.recensioni.api.messaging.RecensioneCreatedEvent;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.logging.Logger;

@Service
public class RecensioniServiceImpl implements RecensioniService {


	private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired
	private RecensioniEventPublisherPort messagePublisher;

	@Autowired
	private RecensioniRepository recensioniRepository;

	/* Crea una nuova recensione, a partire dai suoi dati. */ 
 	public Recensione createRecensione(String recensore, String album, String artista, String genere, String testo, String sunto) {
		Recensione recensione = new Recensione(recensore, album, artista, genere, testo, sunto); 
		recensione = recensioniRepository.save(recensione);

		// Creiamo l'evento CreatedRecensioneEvent e lo pubblichiamo
		DomainEvent event = new RecensioneCreatedEvent(
				recensione.getId(),
				recensione.getRecensore(),
				recensione.getAlbum(),
				recensione.getArtista(),
				recensione.getGenere(),
				recensione.getTesto(),
				recensione.getSunto()
		);
		messagePublisher.publish(event);
		return recensione;
	}

	/* Trova una recensione, dato l'id. */ 
 	public Recensione getRecensione(Long id) {
		Recensione recensione = recensioniRepository.findById(id).orElse(null);
		return recensione;
	}

	/* Trova tutte le recensioni. */ 
	public Collection<Recensione> getRecensioni() {
		Collection<Recensione> recensioni = recensioniRepository.findAll();
		return recensioni;
	}

	/* Trova tutte le recensioni di un album. */ 
	public Collection<Recensione> getRecensioniByAlbum(String album) {
		Collection<Recensione> recensioni = recensioniRepository.findByAlbum(album);
		return recensioni;
	}

	/* Trova tutte le recensioni scritte da un recensore. */ 
	public Collection<Recensione> getRecensioniByRecensore(String recensore) {
		Collection<Recensione> recensioni = recensioniRepository.findByRecensore(recensore);
		return recensioni;
	}

	/* Trova tutte le recensioni scritte da un insieme di recensori. */ 
	public Collection<Recensione> getRecensioniByRecensori(Collection<String> recensori) {
		Collection<Recensione> recensioni = recensioniRepository.findByRecensoreIn(recensori);
		return recensioni;
	}

	/* Trova tutte le recensioni degli album di un artista. */ 
	public Collection<Recensione> getRecensioniByArtista(String artista) {
		Collection<Recensione> recensioni = recensioniRepository.findByArtista(artista);
		return recensioni;
	}

	/* Trova tutte le recensioni degli album di un insieme di artisti. */ 
	public Collection<Recensione> getRecensioniByArtisti(Collection<String> artisti) {
		Collection<Recensione> recensioni = recensioniRepository.findByArtistaIn(artisti);
		return recensioni;
	}

	/* Trova tutte le recensioni degli album di un certo genere. */ 
	public Collection<Recensione> getRecensioniByGenere(String genere) {
		Collection<Recensione> recensioni = recensioniRepository.findByGenere(genere);
		return recensioni;
	}

	/* Trova tutte le recensioni degli album di un insieme di generi. */ 
	public Collection<Recensione> getRecensioniByGeneri(Collection<String> generi) {
		Collection<Recensione> recensioni = recensioniRepository.findByGenereIn(generi);
		return recensioni;
	}

}
