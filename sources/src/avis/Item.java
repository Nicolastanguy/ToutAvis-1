package avis;

import java.util.LinkedList;

public abstract class Item {
	
	/**
	 * @uml.property  name="reviews"
	 * @uml.associationEnd  multiplicity="(0 -1)" ordering="true" inverse="item:avis.Review"
	 */
	protected LinkedList<Review> reviews;
	protected String titre;
	protected String genre;
	protected float averageRating;
	
	public float addReviewToItem(String commentaire, float note){
		return 0;
	}
	
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
	
}