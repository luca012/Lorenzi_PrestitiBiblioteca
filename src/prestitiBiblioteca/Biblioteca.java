package prestitiBiblioteca;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 *  Classe per la gestione di libri, soci e prestiti in una biblioteca
 * 
 * @author Luca Lorenzi
 * @version versione 1.0 del 14/04/2022
 *
 */

public class Biblioteca {
	
	private List<Prestito> prestiti;
	private Map<String, Libro> libri;
	private Map<String, Socio> soci;
	
	public Biblioteca() {
		this.prestiti = new ArrayList<>();
		this.libri = new HashMap<>();;
		this.soci = new TreeMap<>();;
	}
	
	public boolean aggiungiLibro(String isbn, String titolo, String autore) {
		if (cercaLibro(isbn) == null) {
			libri.put(isbn, new Libro(isbn, titolo, autore));
			return true;
		}
		return false;
	}
	
	public boolean aggiungiSocio(String codiceFiscale, String cognome, String nome, String email) {
		if (cercaSocio(codiceFiscale) == null) {
			soci.put(codiceFiscale, new Socio(codiceFiscale, cognome, nome, email));
			return true;
		}
		return false;
	}
	
	private Libro cercaLibro(String isbn) {
		if(libri.containsKey(isbn)) {
			return libri.get(isbn);
		}
		return null;
	}
	
	private Socio cercaSocio(String codiceFiscale) {
		if(soci.containsKey(codiceFiscale)) {
			return soci.get(codiceFiscale);
		}
		return null;
	}
	
	public void stampaDatiLibro(String isbn) {
		if (cercaLibro(isbn) != null) {
			System.out.println(cercaLibro(isbn).toString());
		} else {
			System.out.println("Il codice ISBN inserito è errato");
		}
	}
	
	public void stampaDatiSocio(String codiceFiscale) {
		if (cercaSocio(codiceFiscale) != null) {
			System.out.println(cercaSocio(codiceFiscale).toString());
		} else {
			System.out.println("Il codice fiscale inserito è errato");
		}
	}
	
	public boolean registraPrestito(String isbn, String codiceFiscale) {
		if (cercaLibro(isbn) != null && cercaSocio(codiceFiscale) != null && verificaPrestito(isbn) == null) {
			Prestito p = new Prestito(isbn, codiceFiscale);
			prestiti.add(p);
			return true;
		}
		return false;
	}
	
	public String verificaPrestito(String isbn) {
		if (cercaLibro(isbn) != null) {
			for (Prestito p : prestiti) {
				if (p.getIsbn().equals(isbn)) {
					return soci.get(p.getCodiceFiscale()).toString();
				}
			}
		}
		return null;
	}
	
	public boolean restituisciLibro(String isbn) {
		if (cercaLibro(isbn) != null) {
			for (Prestito p : prestiti) {
				if (p.getIsbn().equals(isbn)) {
					prestiti.remove(p);
					return true;
				}
			}
		}
		return false;
	}
	
 	public void salva() {
		ObjectOutputStream oosPrestiti = null;
		ObjectOutputStream oosLibri = null;
		ObjectOutputStream oosSoci = null;
		try {
			oosPrestiti = new ObjectOutputStream(new FileOutputStream("prestiti.bin"));
			oosLibri = new ObjectOutputStream(new FileOutputStream("libri.bin"));
			oosSoci = new ObjectOutputStream(new FileOutputStream("soci.bin"));
			oosPrestiti.writeObject(this.prestiti);
			oosLibri.writeObject(this.libri);
			oosSoci.writeObject(this.soci);
			oosPrestiti.close();
			oosLibri.close();
			oosSoci.close();
			System.out.println("Dati salvati nei file prestiti.bin, libri.bin e soci.bin");
		} catch (IOException e) {
			System.out.println("Errore nella scrittura dei file");
		}
	}
	
	public void carica() {
		ObjectInputStream oisPrestiti = null;
		ObjectInputStream oisLibri = null;
		ObjectInputStream oisSoci = null;
		try {
			oisPrestiti = new ObjectInputStream(new FileInputStream("prestiti.bin"));
			oisLibri = new ObjectInputStream(new FileInputStream("libri.bin"));
			oisSoci = new ObjectInputStream(new FileInputStream("soci.bin"));
			this.prestiti = (ArrayList<Prestito>) oisPrestiti.readObject();
			this.libri = (Map<String, Libro>) oisLibri.readObject();
			this.soci = (Map<String, Socio>) oisSoci.readObject();
			oisPrestiti.close();
			oisLibri.close();
			oisSoci.close();
			System.out.println("Dati importati dai file prestiti.bin, libri.bin e soci.bin");
		} catch (IOException e) {
			System.out.println("File binari non presenti");
		} catch (ClassNotFoundException e) {
			System.out.println("Errore nella lettura dei file");
		}
	}
}
