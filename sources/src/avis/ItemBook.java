package avis;

import java.util.LinkedList;

public class ItemBook extends Item {
	
	private String auteur;
	private int nbPages;
<<<<<<< HEAD
=======
	/** 
	 * @uml.property name="review"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="itemBook:avis.Review"
	 */
	private Collection<Review> review;
	
	
>>>>>>> 06973c892b0ed8968a7e989f15cfb85de4cfde01

	public ItemBook(String titre, String genre, String auteur, int nbPages){
		this.titre = titre;
		this.genre = genre;
		this.auteur = auteur;
		this.nbPages = nbPages;
		reviews = new LinkedList<Review>();
	}
	
}
