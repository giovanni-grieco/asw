package asw.goodmusic.recensioniseguite.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Primary
public class RecensioniSeguiteServiceLocalBasedImpl implements RecensioniSeguiteService{

    @Autowired
    private RecensioneBreveRepository recensioneBreveRepository;
    @Autowired
    private ConnessioneRepository connessioneRepository;


    @Override
    public Collection<RecensioneBreve> getRecensioniSeguite(String utente) {
        List<Connessione> connessioni = new ArrayList<>(connessioneRepository.findAllByUtente(utente));
        List<RecensioneBreve> output = new ArrayList<>();
        for(Connessione c: connessioni){
            output.addAll(recensioneBreveRepository.findAllByArtistaOrRecensoreOrGenere(c.getSeguito(), c.getSeguito(), c.getSeguito()));
        }
        return output;
    }
}
