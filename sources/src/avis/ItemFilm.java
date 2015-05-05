package avis;

import java.util.LinkedList;

public class ItemFilm extends Item {
	
	private String realisateur;
	private String scenariste;
	private int duree;

	public ItemFilm(String titre, String genre, String realisateur, String scenariste, int duree){
		this.titre = titre;
		this.genre = genre;
		this.realisateur = realisateur;
		this.scenariste = scenariste;
		this.duree = duree;
		reviews = new LinkedList<Review>();
	}
	
}
