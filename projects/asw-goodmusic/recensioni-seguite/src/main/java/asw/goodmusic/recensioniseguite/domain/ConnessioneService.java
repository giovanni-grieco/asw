package asw.goodmusic.recensioniseguite.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnessioneService {

    @Autowired
    private ConnessioneRepository connessioneRepository;

    public void createConnessione(Long id, String utente, String seguito, String ruolo){
        Connessione connessione = new Connessione(id, utente, seguito, ruolo);
        connessioneRepository.save(connessione);
    }

    public void deleteConnessione(Long id){
        connessioneRepository.deleteById(id);
    }

}
