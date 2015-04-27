package avis;

import java.util.Collection;


public class ItemFilm extends Item {
	private String realisateur;
	private String scenariste;
	private int duree;
	/** 
	 * @uml.property name="review"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="itemFilm:avis.Review"
	 */
	private Collection<Review> review;

}
