package asw.goodmusic.recensioniseguite.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface RecensioneBreveRepository extends CrudRepository<RecensioneBreve, Long> {

    Collection<RecensioneBreve> findAllByGenere(String genere);
    Collection<RecensioneBreve> findAllByArtista(String artista);
    Collection<RecensioneBreve> findAllByRecensore(String recensore);

    Collection<RecensioneBreve> findAllByArtistaOrRecensoreOrGenere(String artista, String recensore, String genere);
}
