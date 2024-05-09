package application;

//caretaker
public class AppelloFactoryConcrete implements AppelloFactory {

	public Appello creaAppello(String nome, String cognome, String data, String materia) {
		return new Appello(nome, cognome, data, materia);
	}
}
