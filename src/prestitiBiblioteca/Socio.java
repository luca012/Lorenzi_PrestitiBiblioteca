package prestitiBiblioteca;

import java.io.Serializable;

public class Socio implements Serializable {
	
	private String codiceFiscale;
	private String cognome;
	private String nome;
	private String email;
	
	public Socio(String codiceFiscale, String cognome, String nome, String email) {
		this.codiceFiscale = codiceFiscale;
		this.cognome = cognome;
		this.nome = nome;
		this.email = email;
	}
	
	public Socio(Socio s) {
		this.codiceFiscale = s.codiceFiscale;
		this.cognome = s.cognome;
		this.nome = s.nome;
		this.email = s.email;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "\n-- Dati socio --\nCodice fiscale: " + codiceFiscale + "\nCognome: " + 
				cognome + "\nNome: " + nome + "\nEmail: " + email;
	}
	
	
}
