package application;

public class ConcretePersonaFactory implements PersonaFactory {
	
	AbstractStudente studente;
	AbstractDocente docente;
	
	// Metodo per creare uno docente da input
	public AbstractDocente creaDocente(String nome, String cognome, String materia) {
		this.docente = new Docente(nome, cognome, materia);
		return docente;
	}
	
    // Metodo per creare uno studente da input
    public AbstractStudente creaStudente(String nome, String cognome, String matricola, String dataDiNascita, String residenza, String pianoDiStudi) {
        this.studente = new Studente(nome, cognome, matricola, dataDiNascita, residenza, pianoDiStudi);
        return studente;
    }
}