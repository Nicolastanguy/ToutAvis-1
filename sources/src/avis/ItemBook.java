package avis;

import java.util.Collection;


public class ItemBook extends Item {
	private String auteur;
	private int nbPages;
	/** 
	 * @uml.property name="review"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="itemBook:avis.Review"
	 */
	private Collection<Review> review;
	
	

}
