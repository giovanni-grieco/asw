package asw.goodmusic.recensioniseguite.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ConnessioneRepository extends CrudRepository<Connessione, Long> {
    Collection<Connessione> findAllByUtente(String utente);
}
