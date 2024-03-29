package avis;

import java.util.LinkedList;

public class ItemFilm extends Item {
	
	protected String realisateur;
	protected String scenariste;
	protected int duree;

	public ItemFilm(String titre, String genre, String realisateur, String scenariste, int duree){
		this.titre = titre;
		this.genre = genre;
		this.realisateur = realisateur;
		this.scenariste = scenariste;
		this.duree = duree;
		reviews = new LinkedList<Review>();
		averageRating = 0.0f;
	}
	
	public String toString(){
		return ("\n Titre : "+titre+" genre : "+genre+" realisateur : "+realisateur+" Scenariste : "+scenariste+
				" duree : "+duree+" note moyenne : "+averageRating+" nombre de commentaires : "+reviews.size());
	}
}
