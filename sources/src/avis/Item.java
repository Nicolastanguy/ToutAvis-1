package avis;

import java.util.LinkedList;

/**
 * Classe abstraite d'item, cette classe est implémentée par ItemBook et ItemFilm
 * 
 */
public abstract class Item {
	
	/**
	 * @uml.property  name="reviews"
	 * @uml.associationEnd  multiplicity="(0 -1)" ordering="true" inverse="item:avis.Review"
	 */
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
	 * 
	 * 
	 */	
	public void addReviewToItem(float note, String commentaire){
		Review newReview = new Review(note, commentaire);
		reviews.add(newReview);
	}
	
	/**
	 * Accesseur du titre de l'item
	 * @return
	 */
	public String getTitre(){
		return titre;
	}
	
	/**
	 * Accesseur de la note moyenne
	 * @return
	 */
	public float getAverageRating(){
		return averageRating;
	}
		
	/**
	 */
	public abstract String toString();
	
}