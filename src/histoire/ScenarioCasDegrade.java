package histoire;


import villagegaulois.Village;
import villagegaulois.VillageSansChefException;

public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
//	     Etal etal = new Etal();
//	     try {
//	         etal.acheterProduit(420, new Gaulois("idefix", 69));
//	     } catch (IllegalArgumentException e) {
//	         System.out.println("La quantite est negative");
//	     } catch (IllegalStateException e) {
//	         System.out.println("L'etal est vide");
//	     }
	    Village village = new Village("aze", 20, 5);
	    try {
	        village.afficherVillageois();    
	    } catch (VillageSansChefException e) {
	        e.printStackTrace();
	    }
	    	
  }

}
