package application;

//concrete
public class Appello implements AbstractAppello {
	private String data;
	private String materia;
	private String nome;
	private String cognome;
	
	
	public Appello (String nome, String cognome, String materia, String data) {
		this.data = data;
		this.materia = materia;
		this.nome = nome;
		this.cognome = cognome;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

}
