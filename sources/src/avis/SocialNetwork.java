package avis;

import java.util.LinkedList;
import java.lang.String;


import exception.BadEntry;
import exception.ItemFilmAlreadyExists;
import exception.ItemBookAlreadyExists;
import exception.MemberAlreadyExists;
import exception.MemberAlreadyOpinion;
import exception.NotItem;
import exception.NotMember;
import exception.NotReview;
import exception.SameMember;

/** 
 * @author A. Beugnard, 
 * @author G. Ouvradou
 * @author B. Prou
 * @date février - mars 2011
 * @version V0.6
 */


/** 
 * <p>
 * <b>Système de mutualisation d'opinions portant sur des domaines
 * variés (littérature, cinéma, art, gastronomie, etc.) et non limités.</b>
 * </p>
 * <p>
 * L'accès aux items et aux opinions qui leurs sont associées
 * est public. La création d'item et le dépôt d'opinion nécessite en revanche 
 * que l'utilisateur crée son profil au préalable.
 * </p>
 * <p>
 * Lorsqu'une méthode peut lever deux types d'exception, et que les conditions sont réunies 
 * pour lever l'une et l'autre, rien ne permet de dire laquelle des deux sera effectivement levée.
 * </p>
 * <p>
 * Dans une version avancée (version 2), une opinion peut également
 * être évaluée. Chaque membre se voit dans cette version décerner un "karma" qui mesure
 * la moyenne des notes portant sur les opinions qu'il a émises.
 * L'impact des opinions entrant dans le calcul de la note moyenne attribuée à un item
 * est pondéré par le karma des membres qui les émettent.
 * </p>
 */

public class SocialNetwork {
	
	private int nbMembers;
	private int nbBooks;
	private int nbFilms;
	
	/** 
	 * @uml.property name="members"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" inverse="socialNetwork:avis.Member"
	 */
	private LinkedList<Member> members = new LinkedList<Member>();
	
	/**
	 * @uml.property  name="items"
	 * @uml.associationEnd  multiplicity="(0 -1)" ordering="true" inverse="socialNetwork:avis.Item"
	 */
	private LinkedList<Item> items = new LinkedList<Item>();
	
	
	
	
	/**
	 * constructeur de <i>SocialNetwok</i> 
	 */
	public SocialNetwork() {
		nbMembers = 0;
		nbBooks = 0;
		nbFilms = 0;
	}

	/**
	 * Obtenir le nombre de membres du <i>SocialNetwork</i>
	 * 
	 * @return le nombre de membres
	 */
	public int nbMembers() {
		return nbMembers;
	}

	/**
	 * Obtenir le nombre de films du <i>SocialNetwork</i>
	 * 
	 * @return le nombre de films
	 */
	public int nbFilms() {
		return nbFilms;
	}

	/**
	 * Obtenir le nombre de livres du <i>SocialNetwork</i>
	 * 
	 * @return le nombre de livres
	 */
	public int nbBooks() {
		return nbBooks;
	}


	/**
	 * Ajouter un nouveau membre au <i>SocialNetwork</i>
	 * 
	 * @param pseudo son pseudo
	 * @param password son mot de passe 
	 * @param profil un slogan choisi par le membre pour se définir
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le profil n'est pas instancié.  </li>
	 * </ul><br>       
	 * 
	 * @throws MemberAlreadyExists membre de même pseudo déjà présent dans le <i>SocialNetwork</i> (même pseudo : indifférent à  la casse  et aux leadings et trailings blanks)
	 * 
	 */
	public void addMember(String pseudo, String password, String profil) throws BadEntry, MemberAlreadyExists  {
		
		//__BadEntry__\\
		// - pseudo : doit être différent de null ou avec au moins 1 caractère autre que des espaces
		if (pseudo==null) throw new BadEntry("Le pseudo n'est pas instancié");
		//On retire les blanks du pseudo avec trim() et on met en miniscule avec toLowerCase
		pseudo = pseudo.trim().toLowerCase();
		if (pseudo.length()<1) throw new BadEntry("Le pseudo doit contenir au moins un caractère autre que des espaces");
		// - password : doit être différent de null, contenir au moins 4 caractères autre que des leadings ou trailing blanks
		if (password==null) throw new BadEntry("Le mot de passe n'est pas instancié");
		if (password.contains(" ")) throw new BadEntry ("Le password ne doit pas contenir d'espace");
		if (password.length()<4) throw new BadEntry ("Le password doit contenir au moins 4 caractères");
		// - profil : doit être différent de null
		if (profil==null) throw new BadEntry("Le profil du membre n'est pas instancié");		
		
		//__MemberAlreadyExists__\\
		if (members.size()!=0){	
			if (isMember(pseudo)) throw new MemberAlreadyExists("Le pseudo est déjà utilisé");
		}
				
		Member newMember = new Member(pseudo,password,profil); //création du nouveau membre
		members.add(newMember); //Ajour de ce membre à la linkedList des membres
		nbMembers++;
	}


	/**
	 * Ajouter un nouvel item de film au <i>SocialNetwork</i> 
	 * 
	 * @param pseudo le pseudo du membre
	 * @param password le password du membre 
	 * @param titre le titre du film
	 * @param genre son genre (aventure, policier, etc.)
	 * @param realisateur le réalisateur
	 * @param scenariste le scénariste
	 * @param duree sa durée en minutes
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si le genre n'est pas instancié. </li>
	 *  <li>  si le réalisateur n'est pas instancié. </li>
	 *  <li>  si le scénariste n'est pas instancié. </li>
	 *  <li>  si la durée n'est pas positive.  </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws ItemFilmAlreadyExists : item film de même titre  déjà présent (même titre : indifférent à  la casse  et aux leadings et trailings blanks)
	 * 
	 */
	public void addItemFilm(String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree) throws BadEntry, NotMember, ItemFilmAlreadyExists {
		
		//___Bad Entry___\\
		// - pseudo : doit être différent de null ou avec au moins 1 caractère autre que des espaces
		if (pseudo==null) throw new BadEntry("Le pseudo n'est pas instancié");
		//On retire les blanks du pseudo avec trim() et on met en miniscule avec toLowerCase
		pseudo = pseudo.trim().toLowerCase();
		if (pseudo.length()<1) throw new BadEntry("Le pseudo doit contenir au moins un caractère autre que des espaces");
		// - password : doit être différent de null, contenir au moins 4 caractères autre que des leadings ou trailing blanks
		if (password==null) throw new BadEntry("Le mot de passe n'est pas instancié");
		if (password.contains(" ")) throw new BadEntry ("Le password ne doit pas contenir d'espace");
		if (password.length()<4) throw new BadEntry ("Le password doit contenir au moins 4 caractères");
		// - titre : doit être différent de null et contenir au moins 1 caractère autre que des espaces
		if (titre==null) throw new BadEntry ("Le titre du film n'est pas instancié");
		//On retire les blanks du titre avec trim()
		titre = titre.trim().toLowerCase();
		if (titre.length()<1) throw new BadEntry("Le titre doit contenir au moins un caractère autre que des espaces");
		// - genre : doit être différent de null
		if (genre==null) throw new BadEntry ("Le genre du film n'est pas instancié");
		// - réalisateur : doit être différent de null
		if (realisateur==null) throw new BadEntry ("Le realisateur du film n'est pas instancié");
		// - scenariste : doit être différent de null
		if (scenariste==null) throw new BadEntry ("Le scenariste du film n'est pas instancié");
		// - durée : doit être positive
		if (duree<=0) throw new BadEntry ("La duree du film doit être positive");		
		
		//__NotMember__\\
		if (!isMember(pseudo)) throw new NotMember ("Le pseudo entré n'est pas celui d'un membre enregistré");
		if (!isPswCorrespondToPseudo(pseudo,password)) throw new NotMember ("Le couple pseudo/password est incorrect");
		
		//__ItemFilmAlreadyExists__\\
		if (isItemFilm(titre)) throw new ItemFilmAlreadyExists ("Un film avec un titre identique existe déjà");
		
		//__Ajout du film__\\
		Item newFilm = new ItemFilm(titre, genre, realisateur, scenariste, duree);
		items.add(newFilm);
		nbFilms++;
	}

	/**
	 * Ajouter un nouvel item de livre au <i>SocialNetwork</i> 
	 * 
	 * @param pseudo le pseudo du membre
	 * @param password le password du membre 
	 * @param titre le titre du livre
	 * @param genre son genre (roman, essai, etc.)
	 * @param auteur l'auteur
	 * @param nbPages le nombre de pages
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si le genre n'est pas instancié. </li>
	 *  <li>  si l'auteur n'est pas instancié. </li>
	 *  <li>  si le nombre de pages n'est pas positif.  </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws ItemBookAlreadyExists item livre de même titre  déjà présent (même titre : indifférent à la casse  et aux leadings et trailings blanks)
	 * 
	 * 
	 */
	public void addItemBook(String pseudo, String password, String titre, String genre, String auteur, int nbPages) throws  BadEntry, NotMember, ItemBookAlreadyExists{

		//___Bad Entry___\\
		// - pseudo : doit être différent de null ou avec au moins 1 caractère autre que des espaces
		if (pseudo==null) throw new BadEntry("Le pseudo n'est pas instancié");
		//On retire les blanks du pseudo avec trim() et on met en miniscule avec toLowerCase
		pseudo = pseudo.trim().toLowerCase();
		if (pseudo.length()<1) throw new BadEntry("Le pseudo doit contenir au moins un caractère autre que des espaces");
		// - password : doit être différent de null, contenir au moins 4 caractères autre que des leadings ou trailing blanks
		if (password==null) throw new BadEntry("Le mot de passe n'est pas instancié");
		if (password.contains(" ")) throw new BadEntry ("Le password ne doit pas contenir d'espace");
		if (password.length()<4) throw new BadEntry ("Le password doit contenir au moins 4 caractères");
		// - titre : doit être différent de null et contenir au moins 1 caractère autre que des espaces
		if (titre==null) throw new BadEntry ("Le titre du livre n'est pas instancié");
		//On retire les blanks du titre avec trim()
		titre = titre.trim().toLowerCase();
		if (titre.length()<1) throw new BadEntry("Le titre doit contenir au moins un caractère autre que des espaces");
		// - genre : doit être différent de null
		if (genre==null) throw new BadEntry ("Le genre du livre n'est pas instancié");
		// - auteur : doit être différent de null
		if (auteur==null) throw new BadEntry ("L'auteur du livre n'est pas instancié");
		// - nbPages : doit être positif
		if (nbPages<=0) throw new BadEntry ("Le nombre de page du livre doit être positif");		
		
		//__NotMember__\\
		if (!isMember(pseudo)) throw new NotMember ("Le pseudo entré n'est pas celui d'un membre enregistré");
		if (!isPswCorrespondToPseudo(pseudo,password)) throw new NotMember ("Le couple pseudo/password est incorrect");
		
		//__ItemFilmAlreadyExists__\\
		if (isItemBook(titre)) throw new ItemBookAlreadyExists ("Un livre avec un titre identique existe déjà");
		
		//__Ajout du livre__\\
		ItemBook newBook = new ItemBook(titre, genre, auteur, nbPages);
		items.add(newBook);
		nbBooks++;
	}

	/**
	 * Consulter les items du <i>SocialNetwork</i> par nom
	 * 
	 * @param nom son nom (eg. titre d'un film, d'un livre, etc.)
	 * 
	 * @throws BadEntry : si le nom n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 * 
	 * @return LinkedList <String> : la liste des représentations de tous les items ayant ce nom 
	 * Cette représentation contiendra la note de l'item s'il a été noté.
	 * (une liste vide si aucun item ne correspond) 
	 */
	public LinkedList <String> consultItems(String nom) throws BadEntry, NotItem {
		
		//___Bad Entry___\\
		// - nom : doit être différent de null ou avec au moins un caractère autre que des espaces
		if (nom==null) throw new BadEntry("Le nom n'est pas instancié");
		//On retire les blanks du pseudo avec trim() et on met en miniscule avec toLowerCase
		nom = nom.trim().toLowerCase();
		if(nom.length()<1) throw new BadEntry("Le nom doit contenir au moins un caractère autre que des espaces");
		if((!isItemBook(nom))&&(!isItemFilm(nom))) throw new NotItem ("Aucun item ne correspond à la recherche");
		LinkedList<String> itemsFindList = new LinkedList<String>();		
		for(Item item : items){  //recherche d'un titre correspondant à la recherche dans la liste d'items
			if (item.getTitre().trim().toLowerCase().equals(nom.trim().toLowerCase())) itemsFindList.add(item.titre + " - note moyenne : " + item.averageRating + item.reviews.toString());
		}
		return itemsFindList;
	}
	
	/**
	 * Donner son opinion sur un item film.
	 * Ajoute l'opinion de ce membre sur ce film au <i>SocialNetwork</i> 
	 * Si une opinion de ce membre sur ce film  préexiste, elle est mise à jour avec ces nouvelles valeurs.
	 * 
	 * @param pseudo pseudo du membre émettant l'opinion
	 * @param password son mot de passe
	 * @param titre titre du film  concerné
	 * @param note la note qu'il donne au film 
	 * @param commentaire ses commentaires
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si la note n'est pas comprise entre 0.0 et 5.0. </li>
	 *  <li>  si le commentaire n'est pas instancié. </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws NotItem : si le titre n'est pas le titre d'un film.
	 * 
	 * @return la note moyenne des notes sur ce film  
	 */
	public float reviewItemFilm(String pseudo, String password, String titre, float note, String commentaire) throws BadEntry, NotMember, NotItem {
		
		//___Bad Entry___\\
		// - pseudo : doit être différent de null ou avec au moins 1 caractère autre que des espaces
		if (pseudo==null) throw new BadEntry("Le pseudo n'est pas instancié");
		//On retire les blanks du pseudo avec trim() et on met en miniscule avec toLowerCase
		pseudo = pseudo.trim().toLowerCase();
		if (pseudo.length()<1) throw new BadEntry("Le pseudo doit contenir au moins un caractère autre que des espaces");
		// - password : doit être différent de null, contenir au moins 4 caractères autre que des leadings ou trailing blanks
		if (password==null) throw new BadEntry("Le mot de passe n'est pas instancié");
		if (password.contains(" ")) throw new BadEntry ("Le password ne doit pas contenir d'espace");
		if (password.length()<4) throw new BadEntry ("Le password doit contenir au moins 4 caractères");
		// - titre : doit être différent de null et contenir au moins 1 caractère autre que des espaces.
		if (titre==null) throw new BadEntry ("Le titre du film n'est pas instancié");		
		//On retire les blanks du titre avec trim()
		titre = titre.trim().toLowerCase();
		if (titre.length()<1) throw new BadEntry("Le titre doit contenir au moins un caractère autre que des espaces");
		// - note : doit être comprise entre 0.0 et 5.0
		if (note<0.0f || note>5.0f) throw new BadEntry("La note doit être comprise entre 0.0 et 5.0");
		// - commentaire : doit être différent de null
		if (commentaire==null) throw new BadEntry("Le commentaire n'est pas instancié");
		
		//__NotMember__\\
		if (!isMember(pseudo)) throw new NotMember ("Le pseudo entré n'est pas celui d'un membre enregistré");
		if (!isPswCorrespondToPseudo(pseudo,password)) throw new NotMember ("Le couple pseudo/password est incorrect");
		
		//__NotItem__\\
		if (!isItemFilm(titre)) throw new NotItem ("Le titre entré n'est pas celui d'un film existant");
		
		//__Ajout du review__\\
		
		float tempAverageRating = 0.0f;
		float coef = 0.0f;
		
		//Récupération du karma du membre
		for (Member member : members){
			if (member.getPseudo().trim().toLowerCase().equals(pseudo.trim().toLowerCase())){
				coef=member.getKarma();
			}
		}
		
		//Comparaison des titres de film pour trouver le film à évaluer
		for (Item itemfilm : items){
			if (itemfilm.getTitre().trim().toLowerCase().equals(titre.trim().toLowerCase()) && itemfilm instanceof ItemFilm){
				itemfilm.deletePreviousMemberReview(pseudo);
				itemfilm.addReviewToItem(note, coef, commentaire, pseudo);
				itemfilm.averageRating();
				tempAverageRating = itemfilm.getAverageRating();
			}
		}
		return tempAverageRating;
	}



	/**
	 * Donner son opinion sur un item livre.
	 * Ajoute l'opinion de ce membre sur ce livre au <i>SocialNetwork</i> 
	 * Si une opinion de ce membre sur ce livre  préexiste, elle est mise à jour avec ces nouvelles valeurs.
	 * 
	 * @param pseudo pseudo du membre émettant l'opinion
	 * @param password son mot de passe
	 * @param titre titre du livre  concerné
	 * @param note la note qu'il donne au livre 
	 * @param commentaire ses commentaires
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si la note n'est pas comprise entre 0.0 et 5.0. </li>
	 *  <li>  si le commentaire n'est pas instancié. </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws NotItem : si le titre n'est pas le titre d'un livre.
	 * 
	 * @return la note moyenne des notes sur ce livre
	 */
	public float reviewItemBook(String pseudo, String password, String titre, float note, String commentaire) throws BadEntry, NotMember, NotItem {
		
		//___Bad Entry___\\
		// - pseudo : doit être différent de null ou avec au moins 1 caractère autre que des espaces
		if (pseudo==null) throw new BadEntry("Le pseudo n'est pas instancié");
		//On retire les blanks du pseudo avec trim() et on met en miniscule avec toLowerCase
		pseudo = pseudo.trim().toLowerCase();
		if (pseudo.length()<1) throw new BadEntry("Le pseudo doit contenir au moins un caractère autre que des espaces");
		// - password : doit être différent de null, contenir au moins 4 caractères autre que des leadings ou trailing blanks
		if (password==null) throw new BadEntry("Le mot de passe n'est pas instancié");
		if (password.contains(" ")) throw new BadEntry ("Le password ne doit pas contenir d'espace");
		if (password.length()<4) throw new BadEntry ("Le password doit contenir au moins 4 caractères");
		// - titre : doit être différent de null et contenir au moins 1 caractère autre que des espaces.
		if (titre==null) throw new BadEntry ("Le titre du livre n'est pas instancié");		
		//On retire les blanks du titre avec trim()
		titre = titre.trim().toLowerCase();
		if (titre.length()<1) throw new BadEntry("Le titre doit contenir au moins un caractère autre que des espaces");
		// - note : doit être comprise entre 0.0 et 5.0
		if (note<0.0f || note>5.0f) throw new BadEntry("La note doit être comprise entre 0.0 et 5.0");
		// - commentaire : doit être différent de null
		if (commentaire==null) throw new BadEntry("Le commentaire n'est pas instancié");
		
		//__NotMember__\\
		if (!isMember(pseudo)) throw new NotMember ("Le pseudo entré n'est pas celui d'un membre enregistré");
		if (!isPswCorrespondToPseudo(pseudo,password)) throw new NotMember ("Le couple pseudo/password est incorrect");
		
		//__NotItem__\\
		if (!isItemBook(titre)) throw new NotItem ("Le titre entré n'est pas celui d'un livre existant");
		
		//__Ajout du review__\\

		float tempAverageRating = 0.0f;
		float coef = 0.0f;
		
		//Récupération du karma du membre
		for (Member member : members){
			if (member.getPseudo().trim().toLowerCase().equals(pseudo.trim().toLowerCase())){
				coef=member.getKarma();
			}
		}
		
		//Comparaison des titres de livre pour trouver le livre à évaluer
		for (Item itembook : items){
			if (itembook.getTitre().trim().toLowerCase().equals(titre.trim().toLowerCase()) && itembook instanceof ItemBook){
				itembook.deletePreviousMemberReview(pseudo);
				itembook.addReviewToItem(note, coef, commentaire, pseudo);
				itembook.averageRating();
				tempAverageRating = itembook.getAverageRating();
			}
		}
		return tempAverageRating;
	}
	
	/**
	 * 
	 * @param pseudo1  pseudo du membre voulant noter un avis
	 * @param password	password du membre voulant noter un avis
	 * @param titre titre de l'item ou l'avis à été posté
	 * @param type	type "film" ou "book"
	 * @param pseudo2 pseudo du membre ayant posté l'avis
	 * @param note note donnée à l'avis
	 */
	public float reviewOpinions(String pseudo1,String password,String titre,String type,String pseudo2,float note) throws BadEntry, NotMember, NotItem, NotReview, MemberAlreadyOpinion, SameMember{
		//___Bad Entry___\\
			// - pseudo : doit être différent de null ou avec au moins 1 caractère autre que des espaces
			if (pseudo1==null) throw new BadEntry("Le pseudo1 n'est pas instancié");
			if (pseudo2==null) throw new BadEntry("Le pseudo2 n'est pas instancié");
			//On retire les blanks du pseudo avec trim() et on met en miniscule avec toLowerCase
			pseudo1 = pseudo1.trim().toLowerCase();
			pseudo2 = pseudo2.trim().toLowerCase();
			if (pseudo1.length()<1) throw new BadEntry("Le pseudo1 doit contenir au moins un caractère autre que des espaces");
			if (pseudo2.length()<1) throw new BadEntry("Le pseudo2 doit contenir au moins un caractère autre que des espaces");
			// - password : doit être différent de null, contenir au moins 4 caractères autre que des leadings ou trailing blanks
			if (password==null) throw new BadEntry("Le mot de passe n'est pas instancié");
			if (password.contains(" ")) throw new BadEntry ("Le password ne doit pas contenir d'espace");
			if (password.length()<4) throw new BadEntry ("Le password doit contenir au moins 4 caractères");
			if (type==null) throw new BadEntry("Le type n'est pas instancié");
			// - titre : doit être différent de null et contenir au moins 1 caractère autre que des espaces.
			if (titre==null) throw new BadEntry ("Le titre du livre n'est pas instancié");		
			//On retire les blanks du titre avec trim()
			titre = titre.trim().toLowerCase();
			if (titre.length()<1) throw new BadEntry("Le titre doit contenir au moins un caractère autre que des espaces");
			// - note : doit être comprise entre 0.0 et 5.0
			if (note<0.0f || note>5.0f) throw new BadEntry("La note doit être comprise entre 0.0 et 5.0");
		
		//__NotMember__\\
			if (!isMember(pseudo1)) throw new NotMember ("Le pseudo utilisé en identifiant entré n'est pas celui d'un membre enregistré");
			if (!isMember(pseudo2)) throw new NotMember ("Le pseudo du membre ayant posté l'avis n'est pas celui d'un membre enregistré");
			if (!isPswCorrespondToPseudo(pseudo1,password)) throw new NotMember ("Le couple pseudo/password est incorrect");
		
			
		//__SameMember__\\
			if (pseudo1.trim().toLowerCase()==pseudo2.trim().toLowerCase()) throw new SameMember("Un membre n'a pas le droit de noter un de ses propres avis");
			
			float tempNoteReview = 0.0f;
			
			if (type=="livre"){
				//__NotItem__\\
				if (!isItemBook(titre)) throw new NotItem ("Le titre entré n'est pas celui d'un livre existant");
				
				for (Item itembook : items){
					if (itembook.getTitre().trim().toLowerCase().equals(titre.trim().toLowerCase()) && itembook instanceof ItemBook){
						//Est-ce que le pseudo 2 à bien commenté cet item?\\
						if (!itembook.isMemberReview(pseudo2)) throw new NotReview("Aucun avis n'a été posté par ce membre sur ce livre.");
						//Est-ce que le membre n'a pas déjà noté cet avis?\\
						if (itembook.memberAlreadyReviewOpinion(pseudo1, pseudo2)) throw new MemberAlreadyOpinion("Le membre à déjà noté cet avis.");
						//Mise à jour de la note du review (par rapport au nombre de personne ayant déjà noté l'avis)
						itembook.addNoteToReview(pseudo1, pseudo2, note);
						//Récupération de la moyenne obtenue
						tempNoteReview=itembook.getNoteReview(pseudo1, pseudo2);
					}
				}
				//Mise à jour du Karma de l'utilisateur dont l'avis a été noté
				for (Member member : members){
					if (member.getPseudo().trim().toLowerCase().equals(pseudo2.trim().toLowerCase())){
						member.karmaUpdate(note);
					}
				}
				return tempNoteReview;
			}
			if (type=="film"){
				//__NotItem__\\
				if (!isItemFilm(titre)) throw new NotItem ("Le titre entré n'est pas celui d'un film existant");
				
				for (Item itemfilm : items){
					if (itemfilm.getTitre().trim().toLowerCase().equals(titre.trim().toLowerCase()) && itemfilm instanceof ItemFilm){
						//Est-ce que le pseudo 2 à bien commenté cet item?\\
						if (!itemfilm.isMemberReview(pseudo2)) throw new NotReview("Aucun avis n'a été posté par ce membre sur ce film.");
						//Est-ce que le membre n'a pas déjà noté cet avis?\\
						if (itemfilm.memberAlreadyReviewOpinion(pseudo1, pseudo2)) throw new MemberAlreadyOpinion("Le membre à déjà noté cet avis.");
						//Mise à jour de la note du review (par rapport au nombre de personne ayant déjà noté l'avis)
						itemfilm.addNoteToReview(pseudo1, pseudo2, note);
						//Récupération de la moyenne obtenue
						tempNoteReview=itemfilm.getNoteReview(pseudo1, pseudo2);
					}
				}
				//Mise à jour du Karma de l'utilisateur dont l'avis a été noté
				for (Member member : members){
					if (member.getPseudo().trim().toLowerCase().equals(pseudo2.trim().toLowerCase())){
						member.karmaUpdate(note);
					}
				}
				return tempNoteReview;
			}
			else {
				throw new BadEntry("le type est différent de film ou de book");
			}
			
			
	}

	/**
	 * Obtenir une représentation textuelle du <i>SocialNetwork</i>.
	 * 
	 * @return la chaîne de caractères représentation textuelle du <i>SocialNetwork</i> 
	 */
	public String toString() {
		//Liste des films :
		LinkedList <Item> filmList = new LinkedList<Item>();
		for (Item item : items){
			if (item instanceof ItemFilm) filmList.add(item);
		}
		//Liste des livres :
		LinkedList <Item> bookList = new LinkedList<Item>();
		for (Item item : items){
			if (item instanceof ItemBook) bookList.add(item);
		}
				
		return ("Nombre de membre : "+nbMembers+ "\n" 
				+ "___Liste des membres enregistrés___  " + members.toString() + "\n"
				+ "___Nombre de livres enregistrés : "+nbBooks+ "\n"
				+ "___Liste des livres enregistrés___ "+bookList.toString()+ "\n"
				+ "___Nombre de films enregistrés : "+nbFilms+ "\n"
				+ "___Liste des films enregistrés___ "+filmList.toString() + "\n"
				);
	}

	/**
	 * Permet de savoir si le pseudo est déjà utilisé dans la liste des membres enregistrés
	 * @param pseudo
	 * @return True si le pseudo est déjà présent dans la liste des membres / False sinon
	 */
	private boolean isMember(String pseudo){
		//Comparaison des pseudos de la liste de membres avec le pseudo passé en paramètre
		for (Member member : members){
			if (member.getPseudo().trim().toLowerCase().equals(pseudo.trim().toLowerCase())) return true;
		}
		return false;
	}
	
	/**
	 * Permet de savoir si l'item film est déjà présent dans la liste des items films du réseau social
	 * @param titre
	 * @return True si l'item est déjà enregistré sur le réseau social / False sinon
	 */
	private boolean isItemFilm(String titre){
		//Comparaison des titres de film pour voir si l'item est déjà présent dans la liste
		for (Item itemfilm : items){
			if (itemfilm.getTitre().trim().toLowerCase().equals(titre.trim().toLowerCase()) && itemfilm instanceof ItemFilm) return true;
		}
		return false;
	}
	
	/**
	 * Permet de savoir si l'item book est déjà présent dans la liste des items books du réseau social
	 * @param titre
	 * @return True si l'item est déjà enregistré sur le réseau social / False sinon
	 */
	private boolean isItemBook(String titre){
		//Comparaison des titres de book pour voir si l'item est déjà présent dans la liste
		for (Item itembook : items){
			if (itembook.getTitre().trim().toLowerCase().equals(titre.trim().toLowerCase()) && itembook instanceof ItemBook) return true;
		}
		return false;
	}

	/**
	 * Permet de savoir si le mot de passe correspond bien au pseudo passé en paramètre
	 * @param pseudo, password
	 * @return True si le mot de passe est bien celui du membre enregistré passé en paramètre / False sinon
	 */
	private boolean isPswCorrespondToPseudo(String pseudo, String password){
		//Comparaison des pseudos de la liste de membres avec le pseudo passé en paramètre
		for (Member member : members){
			if (member.getPseudo().trim().toLowerCase().equals(pseudo.trim().toLowerCase())){
				if(member.getPassword().trim().toLowerCase().equals(password)) return true;
			}	
		}
		return false;	
	}

				
	public float getKarma(String pseudo){
		
		for (Member member : members){
			if (member.getPseudo().trim().toLowerCase().equals(pseudo.trim().toLowerCase())) return member.getKarma();
		}
		return 0;
	}
}
