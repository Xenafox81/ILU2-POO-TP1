package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	
	private Marche marche;

	private static class Marche {
		private Etal[] etals;

		private Marche(int nbEtals) {
			this.etals = new Etal[nbEtals];
			for (int i = 0; i < nbEtals; i++) {
				etals[i] = new Etal();
			}
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		private int trouverEtalLibre() {
			for (int i = 0; i < etals.length; i++) {
				if (!etals[i].isEtalOccupe()) {
					return i;
				}
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			int nbEtals = 0;
			
			/* 
			 * for-each : ici on a juste besoin de parcourir et non modif les éléments du tableau
			 * en ayant besoin d'accéder aux indices 
			 * */
			for (Etal etal : etals) {
				if (etal.contientProduit(produit))
				{
					nbEtals++;
				}
			}
			
			Etal[] etals_produits = new Etal[nbEtals];
			
			/* for-each*/
			for (int i = 0,  j = 0; i < etals.length; i++) {
				Etal etal = etals[i];
				if (etal.contientProduit(produit)) {
					etals_produits[j]= etal;
					j++;
				}
			}
			
			return etals_produits;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			return null;
		}
		
		private String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int etalsVide = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				} else {
					etalsVide++;
				}
			}
			if (etalsVide != 0) {
				chaine.append("Il reste " + (etalsVide) +" etals non utilises dans le marche.\n");
			}
			return chaine.toString();
		}
	}
	
	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		if (chef == null){
			throw new VillageSansChefException("Il n'y a pas de chef");
		}

		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		int etal = marche.trouverEtalLibre();
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");
		marche.utiliserEtal(etal, vendeur, produit, nbProduit);
		chaine.append("Le vendeur " + vendeur.getNom() + "vend des " + produit + " a l'etal n " + (etal+1) + ".\n");
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal [] lstEtals = marche.trouverEtals(produit);
		switch (lstEtals.length) {
		case 0: {
			chaine.append("Il n'y a pas de vendeur qui propose des " + produit + " au marche.\n");
			break;
		} case 1: {
			chaine.append("Seul le vendeur " + lstEtals[0].getVendeur().getNom() + " propose des " + produit + " au marche.\n");
			break;
		}
		default:
			chaine.append("Les vendeurs qui proposent des fleurs sont :\n");
			for (int i = 0; i < lstEtals.length; i++) {
				chaine.append("- " + lstEtals[i].getVendeur().getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	 public Etal rechercherEtal(Gaulois vendeur) {
		 return marche.trouverVendeur(vendeur);
	 }
	 
	 public String partirVendeur(Gaulois vendeur) {
		 return rechercherEtal(vendeur).libererEtal();
	 }
	 
	 public String afficherMarche() { 
		 return "Le marche du village : \"" + nom + "\"" + " possede plusieurs etals :\n" +  marche.afficherMarche();
	 }

}