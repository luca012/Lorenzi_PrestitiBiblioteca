package prestitiBiblioteca;

public class Test {
	
	public static void menu() {
		System.out.println("\n *** Gestione di una biblioteca *** ");
		System.out.println("1. Carica dati");
		System.out.println("2. Salva dati");
		System.out.println("3. Aggiungi libro");
		System.out.println("4. Aggiungi socio");
		System.out.println("5. Cerca libro");
		System.out.println("6. Cerca socio");
		System.out.println("7. Verifica prestito");
		System.out.println("8. Registra prestito");
		System.out.println("9. Restituisci libro");
		System.out.println("0. Fine programma");
		System.out.print("Opzione scelta:");
	}
	
	public static void main(String[] args) {
		Biblioteca b = new Biblioteca();
		Input input = new Input();
		int scelta;
		do {
			menu();
			scelta = input.inputInt("");
			switch (scelta) {
			case 1:
				b.carica();
				break;
			case 2:
				b.salva();
				break;
			case 3:
				String isbn = input.inputString("\nInserire il codice ISBN:");
				String titolo = input.inputString("Inserire il titolo:");
				String autore = input.inputString("Inserire l'autore:");
				if (b.aggiungiLibro(isbn, titolo, autore)) {
					System.out.println("Nuovo libro aggiunto correttamente");
				} else {
					System.out.println("Esiste già un libro con questo ISBN");
				}
				break;
			case 4:
				String codiceFiscale = input.inputString("\nInserire il codice fiscale:");
				String nome = input.inputString("Inserire il nome:");
				String cognome = input.inputString("Inserire il cognome:");
				String email = input.inputString("Inserire l'indirizzo email:");
				if (b.aggiungiSocio(codiceFiscale, cognome, nome, email)) {
					System.out.println("Nuovo socio aggiunto correttamente");
				} else {
					System.out.println("Esiste già un socio con questo codice fiscale");
				}				
				break;
			case 5:
				String isbn2 = input.inputString("\nInserire il codice ISBN:");
				b.stampaDatiLibro(isbn2);
				break;
			case 6:
				String fisc = input.inputString("\nInserire il codice fiscale:");
				b.stampaDatiSocio(fisc);
				break;
			case 7:
				String isbn3 = input.inputString("\nInserire il codice ISBN:");
				if (b.verificaPrestito(isbn3) == null) {
					System.out.println("Questo libro non è in prestito o non esiste");
				} else {
					System.out.println(b.verificaPrestito(isbn3));
				}
				break;
			case 8:
				String isbn4 = input.inputString("\nInserire il codice ISBN:");
				String fisc2 = input.inputString("Inserire il codice fiscale:");
				if (!b.registraPrestito(isbn4, fisc2)) {
					System.out.println("Codice ISBN e/o codice fiscale errato/i o libro già in prestito");
				} else {
					System.out.println("Prestito registrato correttamente");
				}
				break;
			case 9:
				String isbn5 = input.inputString("\nInserire il codice ISBN:");
				if (!b.restituisciLibro(isbn5)) {
					System.out.println("Codice ISBN errato");
				} else {
					System.out.println("Libro restituito correttamente");
				}
				break;
			}
		} while (scelta != 0);
	}

}
