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
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 
 *  Classe per la gestione di libri, soci e prestiti in una biblioteca
 * 
 * @author Luca Lorenzi
 * @version versione 2.0 del 21/04/2022
 *
 */

public class Biblioteca {
	
	private List<Prestito> prestiti;
	private Map<String, Libro> libri;
	private Map<String, Socio> soci;
	
	/**
	 * Costruttore
	 * 
	 * Inizializza le strutture dati {@code ArrayList} <b>prestiti</b> {@code HashMap} <b>libri</b> e {@code TreeMap} <b>soci</b>
	 */
	
	public Biblioteca() {
		this.prestiti = new ArrayList<>();
		this.libri = new HashMap<>();
		this.soci = new TreeMap<>();
	}
	
	/**
	 * 
	 * Aggiungi un libro alla {@code HashMap} <b>libri</b>
	 * 
	 * @param isbn		codice ISBN del libro da aggiungere
	 * @param titolo	titolo del libro da aggiungere
	 * @param autore	autore del libro da aggiungere
	 * @return			true se il libro viene aggiunto, altrimenti false
	 */
	
	public boolean aggiungiLibro(String isbn, String titolo, String autore) {
		if (cercaLibro(isbn) == null) {
			libri.put(isbn, new Libro(isbn, titolo, autore));
			return true;
		}
		return false;
	}
	
	/**
	 * Aggiunge un socio alla {@code TreeMap} <b>soci</b>
	 * 
	 * @param codiceFiscale		codice fiscale del socio da aggiungere
	 * @param cognome			cognome del socio da aggiungere
	 * @param nome				nome del socio da aggiungere
	 * @param email				email del socio da aggiungere
	 * @return					true se il socio viene aggiunto, altrimenti false
	 */
	
	public boolean aggiungiSocio(String codiceFiscale, String cognome, String nome, String email) {
		if (cercaSocio(codiceFiscale) == null) {
			soci.put(codiceFiscale, new Socio(codiceFiscale, cognome, nome, email));
			return true;
		}
		return false;
	}
	
	/**
	 * METODO PRIVATO, non compare nella java doc
	 * 
	 * Cerca un libro nella {@code HashMap} <b>libri</b>
	 * 
	 * @param isbn	codice ISBN del libro da cercare 
	 * @return		l'oggetto {@link Libro}. Se non esiste ritorna null
	 */
	
	private Libro cercaLibro(String isbn) {
			return libri.get(isbn);
	}
	
	/**
	 * METODO PRIVATO, non compare nella java doc
	 * 
	 * Cerca un socio nella {@code TreeMap} <b>soci</b>
	 * 
	 * @param codiceFiscale		codice fiscale del socio da cercare 
	 * @return					l'oggetto {@link Socio}. Se non esiste ritorna null
	 */
	
	private Socio cercaSocio(String codiceFiscale) {
			return soci.get(codiceFiscale);
	}
	
	/**
	 * Stampa i dati di un libro
	 * 
	 * @param isbn	codice ISBN del libro di cui stampare i dati
	 */
	
	public void stampaDatiLibro(String isbn) {
		if (cercaLibro(isbn) != null) {
			System.out.println(cercaLibro(isbn).toString());
		} else {
			System.out.println("Il codice ISBN inserito è errato");
		}
	}
	
	/**
	 * Stampa i dati di un socio
	 * 
	 * @param codiceFiscale codice fiscale del socio di cui stampare i dati
	 */
	
	public void stampaDatiSocio(String codiceFiscale) {
		if (cercaSocio(codiceFiscale) != null) {
			System.out.println(cercaSocio(codiceFiscale).toString());
		} else {
			System.out.println("Il codice fiscale inserito è errato");
		}
	}
	
	/**
	 * Registra il prestito di un libro ad un socio
	 * 
	 * @param isbn				isbn del libro richiesto in prestito
	 * @param codiceFiscale		codice fiscale del socio che richiede il prestito
	 * @return					true se il libro viene prestato al socio, altrimenti false
	 */
	
	public boolean registraPrestito(String isbn, String codiceFiscale) {
		if (cercaLibro(isbn) != null && cercaSocio(codiceFiscale) != null && verificaPrestito(isbn) == null) {
			Prestito p = new Prestito(isbn, codiceFiscale);
			prestiti.add(p);
			return true;
		}
		return false;
	}
	
	/**
	 * Verifica se un libro è in prestito
	 * 
	 * @param isbn	isbn del libro di cui verificare il prestito
	 * @return		i dati del socio che ha preso in prestito il libro, altrimenti null
	 */
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
	
	/**
	 * Restituisce un libro preso in prestito
	 * 
	 * @param isbn	isbn del libro da restituire
	 * @return		true se avviene la restituzione, altrimenti false
	 */
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
	
	/**
	 * Stampa i dati di tutti i soci presenti nella {@code HashMap} <b>libri</b> 
	 */
		
	public void stampaLibri() {
		System.out.println("\n** Elenco libri **");
		for(Entry<String, Libro> elemento : libri.entrySet()) {
			System.out.println(elemento.getValue());
		}
	}
	
	/**
	 * Stampa i dati di tutti i soci presenti nella {@code TreeMap} <b>soci</b> 
	 */
	
	public void stampaSoci() {
		System.out.println("\n** Elenco soci **");
		for(Entry<String, Socio> elemento : soci.entrySet()) {
			System.out.println(elemento.getValue());
		}
	}
	
	/**
	 * Salva su file binari i dati inerenti a prestiti, libri e soci
	 */

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
			System.out.println("\nDati salvati nei file prestiti.bin, libri.bin e soci.bin");
		} catch (IOException e) {
			System.out.println("\nErrore nella scrittura dei file");
		}
	}
	
 	/**
 	 * Carica da file binari i dati inerenti a prestiti, libri e soci
 	 */
 	
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
			System.out.println("\nDati importati dai file prestiti.bin, libri.bin e soci.bin");
		} catch (IOException e) {
			System.out.println("\nFile binari non presenti");
		} catch (ClassNotFoundException e) {
			System.out.println("\nErrore nella lettura dei file");
		}
	}
}
