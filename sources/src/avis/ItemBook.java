package avis;

import java.util.LinkedList;

public class ItemBook extends Item {
	
	private String auteur;
	private int nbPages;

	public ItemBook(String titre, String genre, String auteur, int nbPages){
		this.titre = titre;
		this.genre = genre;
		this.auteur = auteur;
		this.nbPages = nbPages;
		reviews = new LinkedList<Review>();
	}
	
}