package histoire;


import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;
import villagegaulois.VillageSansChefException;

public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
	     Etal etal = new Etal();
	        //etal.libererEtal();

	        Village village = new Village("le village des irreductibles", 10, 5);
			Chef abraracourcix = new Chef("Abraracourcix", 10, village);
			village.setChef(abraracourcix);
			Gaulois obelix = new Gaulois("Obelix", 25);
			Gaulois asterix = new Gaulois("Asterix", 8);
			Etal etalMenhir = village.rechercherEtal(asterix);
//		    village.partirVendeur(obelix); //etal non occupe
			etalMenhir.acheterProduit(-10, abraracourcix); //quantite negative
//			etalMenhir.acheterProduit(15, null); //acheteur null
			village.ajouterHabitant(asterix);
			village.ajouterHabitant(obelix);
			village.ajouterHabitant(abraracourcix);

  }

}
