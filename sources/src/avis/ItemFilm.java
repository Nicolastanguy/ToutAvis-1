package avis;

<<<<<<< HEAD
=======
import java.util.Collection;
>>>>>>> 06973c892b0ed8968a7e989f15cfb85de4cfde01
import java.util.LinkedList;

public class ItemFilm extends Item {
	
	private String realisateur;
	private String scenariste;
	private int duree;
<<<<<<< HEAD
=======
	/** 
	 * @uml.property name="review"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" inverse="itemFilm:avis.Review"
	 */
	private LinkedList<Review> review;
>>>>>>> 06973c892b0ed8968a7e989f15cfb85de4cfde01

	public ItemFilm(String titre, String genre, String realisateur, String scenariste, int duree){
		this.titre = titre;
		this.genre = genre;
		this.realisateur = realisateur;
		this.scenariste = scenariste;
		this.duree = duree;
		reviews = new LinkedList<Review>();
	}
	
	
}
