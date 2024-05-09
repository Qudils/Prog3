package application;

public interface PersonaFactory {
	AbstractStudente creaStudente(String nome, String cognome, String matricola, String dataDiNascita, String residenza, String pianoDiStudi);
	AbstractDocente creaDocente(String nome, String cognome, String materia);
}
