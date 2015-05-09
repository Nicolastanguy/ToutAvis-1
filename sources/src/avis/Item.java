package avis;

import java.util.LinkedList;

/**
 * Classe abstraite d'item, cette classe est implémentée par ItemBook et ItemFilm
 * 
 */
public abstract class Item {
	
	protected LinkedList<Review> reviews;
	protected String titre;
	protected String genre;
	protected float averageRating;
		
	/**
	 * Met a jour la note de l'item (calcul de la moyenne des différents commentaires)
	 * @return
	 */	
	public void averageRating(){
		
		float rateSum = 0; 
		int nombreNotes = reviews.size();
		for (Review review : reviews){
			rateSum += review.getNote(); //somme des notes données à un item
		}
		averageRating = rateSum/nombreNotes; //Calcul de la moyenne
	}
	
	/**
	 * Accesseur du titre de l'item
	 * @return
	 */
	public String getTitre(){
		return titre;
	}

		
	/**
	 */
	public abstract String toString();
		
}