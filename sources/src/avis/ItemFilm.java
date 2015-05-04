package avis;

import java.util.Collection;
import java.util.LinkedList;

public class ItemFilm extends Item {
	private String realisateur;
	private String scenariste;
	private int duree;
	/** 
	 * @uml.property name="review"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" inverse="itemFilm:avis.Review"
	 */
	private LinkedList<Review> review;

}
