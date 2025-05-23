package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;
import villagegaulois.VillageSansChefException;

public class Scenario {

	public static void main(String[] args) {
		Village village = new Village("le village des irréductibles", 10, 5);
		Chef abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		Druide druide = new Druide("Panoramix", 2, 5, 10);
		Gaulois obelix = new Gaulois("Obélix", 25);
		Gaulois asterix = new Gaulois("Astérix", 8);
		Gaulois assurancetourix = new Gaulois("Assurancetourix", 2);
		Gaulois bonemine = new Gaulois("Bonemine", 7);
		
		village.ajouterHabitant(bonemine);
		village.ajouterHabitant(assurancetourix);
		village.ajouterHabitant(asterix);
		village.ajouterHabitant(obelix);
		village.ajouterHabitant(druide);
		village.ajouterHabitant(abraracourcix);
		try {
			village.afficherVillageois();
		} catch (VillageSansChefException e) {
			System.out.println(e);
		}

		System.out.println(village.rechercherVendeursProduit("fleurs"));
		System.out.println(village.installerVendeur(bonemine, "fleurs", 20));
		System.out.println(village.rechercherVendeursProduit("fleurs"));
		System.out
				.println(village.installerVendeur(assurancetourix, "lyres", 5));
		System.out.println(village.installerVendeur(obelix, "menhirs", 2));
		System.out.println(village.installerVendeur(druide, "fleurs", 10));

		System.out.println(village.rechercherVendeursProduit("fleurs"));
		Etal etalFleur = village.rechercherEtal(bonemine);
		try {
		System.out.println(etalFleur.acheterProduit(10, abraracourcix));
		System.out.println(etalFleur.acheterProduit(15, obelix));
		System.out.println(etalFleur.acheterProduit(15, assurancetourix));
		}catch (IllegalArgumentException e) {
		    // Traite l'exception IllegalArgumentException
		    System.out.println("Erreur : La quantit� d'achat est invalide (doit �tre >= 1).");
		    e.printStackTrace();
		} catch (IllegalStateException e) {
		    // Traite l'exception IllegalStateException
		    System.out.println("Erreur : L'�tal n'est pas occup�.");
		    e.printStackTrace();
		} catch (NullPointerException e) {
		    // Traite l'exception NullPointerException
		    System.out.println("Erreur : Une des valeurs pass�es est nulle.");
		    e.printStackTrace();
		}
		}
	}

