package asw.goodmusic.recensioniseguite.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecensioniBreviService {

    @Autowired
    private RecensioneBreveRepository recensioneBreveRepository;

    public void createRecensioneBreve(Long id, String recensore, String album, String artista, String genere, String sunto){
        RecensioneBreve recensione = new RecensioneBreve(id, recensore, album, artista, genere, sunto);
        recensioneBreveRepository.save(recensione);
    }

}
