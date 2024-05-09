package application;

import java.io.IOException;
import java.sql.SQLException;

public interface ValutazioneStrategy {
	    void mostraValutazione(String input) throws IOException, SQLException;
} 	