package application;

public class Docente implements AbstractDocente{
	private String nome;
	private String cognome;
	protected String materia;

	
	public Docente() { //costruttore vuoto
		this.materia = "";
	}
	
	public Docente(String nome, String cognome, String materia) { //costruttore parametrizzato
		this.nome = nome;
		this.cognome = cognome;
		this.materia = materia;
	
	}
	
	public String getNome() {
        return nome;
    }
    
    public String getCognome() {
        return cognome;
    }
	
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    
    //metodi set e get delle variabili di Docente
    public String getMateria() {	
    	return materia;
    }
    
    public void setMateria(String materia) {
    	this.materia = materia;
    }
    
}