package avis;

import java.util.LinkedList;

public class ItemBook extends Item {
	
	protected String auteur;
	protected int nbPages;
	
	public ItemBook(String titre, String genre, String auteur, int nbPages){
		this.titre = titre;
		this.genre = genre;
		this.auteur = auteur;
		this.nbPages = nbPages;
		reviews = new LinkedList<Review>();
	}
	
	public String toString(){
		return ("\n Titre : "+titre+" genre : "+genre+" auteur : "+auteur+
				" nombre de pages : "+nbPages+" note moyenne : "+averageRating+" nombre de commentaires : "+reviews.size());
	}
}
