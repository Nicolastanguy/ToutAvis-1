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
	}

	//Accesseur du titre 
	public String getTitre(){
		return titre;
	}
}
