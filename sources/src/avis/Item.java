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
	
	public void addReviewToItem(float note, String commentaire){
		Review newReview = new Review(note, commentaire);
		reviews.add(newReview);
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
	
	//Accesseur du titre 
	public String getTitre(){
		return titre;
	}
}