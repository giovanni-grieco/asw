package asw.goodmusic.connessioni.domain;

import asw.goodmusic.common.api.messaging.DomainEvent;
import asw.goodmusic.connessioni.api.messaging.ConnessioneCreatedEvent;
import asw.goodmusic.connessioni.api.messaging.ConnessioneDeletedEvent;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 

@Service
public class ConnessioniServiceImpl implements ConnessioniService {

	@Autowired
	private ConnessioniRepository connessioniRepository;

	@Autowired
	private ConnessioniEventPublisherPort messagePublisher;

	/* Crea una nuova connessione, dati utente, seguito e ruolo. */ 
 	public Connessione createConnessione(String utente, String seguito, String ruolo) {
		Connessione connessione = new Connessione(utente, seguito, ruolo); 
		try {
			connessione = connessioniRepository.save(connessione);

			DomainEvent event= new ConnessioneCreatedEvent(
					connessione.getId(),
					connessione.getUtente(),
					connessione.getSeguito(),
					connessione.getRuolo()
			);
			messagePublisher.publish(event);

			return connessione;
		} catch(Exception e) {
			/* si potrebbe verificare un'eccezione se è violato il vincolo di unicità della connessione */ 
			return null; 
		}
	}

	/* Trova una connessione, dato l'id. */ 
 	public Connessione getConnessione(Long id) {
		Connessione connessione = connessioniRepository.findById(id).orElse(null);
		return connessione;
	}

	/* Trova una connessione, dati utente, seguito e ruolo. */ 
	public Connessione getConnessione(String utente, String seguito, String ruolo) {
		Connessione connessione = connessioniRepository.findByUtenteAndSeguitoAndRuolo(utente, seguito, ruolo);
		return connessione;
	}

	/* Trova tutte le connessioni. */ 
 	public Collection<Connessione> getConnessioni() {
		Collection<Connessione> connessioni = connessioniRepository.findAll();
		return connessioni;
	}

	/* Trova tutte le connessioni di un utente. */ 
	public Collection<Connessione> getConnessioniByUtente(String utente) {
		Collection<Connessione> connessioni = connessioniRepository.findByUtente(utente);
		return connessioni;
	}

	/* Trova tutte le connessioni con un certo ruolo. */ 
	public Collection<Connessione> getConnessioniByRuolo(String ruolo) {
		Collection<Connessione> connessioni = connessioniRepository.findByRuolo(ruolo);
		return connessioni;
	}

	/* Trova tutte le connessioni di un utente con un certo ruolo. */ 
	public Collection<Connessione> getConnessioniByUtenteAndRuolo(String utente, String ruolo) {
		Collection<Connessione> connessioni = connessioniRepository.findByUtenteAndRuolo(utente, ruolo);
		return connessioni;
	}

	/* Cancella una connessione, dati utente, seguito e ruolo. */ 
 	public Connessione deleteConnessione(String utente, String seguito, String ruolo) {
		Connessione connessione = getConnessione(utente, seguito, ruolo); 
		if (connessione!=null) {

			connessioniRepository.delete(connessione);
			DomainEvent event = new ConnessioneDeletedEvent(
					connessione.getId(),
					connessione.getUtente(),
					connessione.getSeguito(),
					connessione.getRuolo()
			);
			messagePublisher.publish(event);
		}
		return connessione; 
	}

}
