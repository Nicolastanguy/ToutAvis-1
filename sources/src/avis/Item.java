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
	
	public float addReviewToItem(String commentaire, float note){
		return 0;
	}
	
	public float averageRating(){
		return 0;
	}
	
}