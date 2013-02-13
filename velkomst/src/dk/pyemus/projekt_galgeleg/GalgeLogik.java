package dk.pyemus.projekt_galgeleg;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class GalgeLogik {
	//
	  public ArrayList<String> muligeOrd;
	  public ArrayList<String> brugteBogstaver;
	  public String ordet;
	  public String synligtOrd;
	  public int korrekteBogstaver;
	  public int forkerteBogstaver;
	  public boolean sidsteBogstavVarKorrekt;
	  public boolean spilletErVundet;
	  public boolean spilletErTabt;

	  void nulstil() {
	    muligeOrd = new ArrayList<String>();
	    muligeOrd.add("bil");
	    muligeOrd.add("computer");
	    muligeOrd.add("programmering");
	    muligeOrd.add("motorvej");
	    muligeOrd.add("busrute");
	    muligeOrd.add("gangsti");
	    muligeOrd.add("skovsnegl");
	    muligeOrd.add("solsort");
//	    muligeOrd.add("qwq");

	    Random random = new Random();
	    ordet = muligeOrd.get(random.nextInt(muligeOrd.size()));

	    brugteBogstaver = new ArrayList<String>();
	    forkerteBogstaver = 0;
	    spilletErVundet = false;
	    spilletErTabt = false;

	    opdaterSynligtOrd();
	  }

	  public void opdaterSynligtOrd() {
	    synligtOrd = "";
	    spilletErVundet = true;
	    for (int n=0; n<ordet.length(); n++) {
	      String bogstav = ordet.substring(n, n+1);
	      if (brugteBogstaver.contains(bogstav)) {
	        synligtOrd = synligtOrd + bogstav;
	      } else {
	        synligtOrd = synligtOrd + "*";
	        spilletErVundet = false;
	      }
	    }
	  }

	  void gaetBogstav(String bogstav) {
	    if (bogstav.length()!=1) return;
	    if (brugteBogstaver.contains(bogstav)) return;
	    if (spilletErVundet || spilletErTabt) return;

	    brugteBogstaver.add(bogstav);

	    if (ordet.contains(bogstav)) {
	      sidsteBogstavVarKorrekt = true;
	      System.out.println("Bogstavet var korrekt: "+bogstav);
	    }  else {
	      // Vi gaettede paa et bogstav der ikke var i ordet.
	      sidsteBogstavVarKorrekt = false;
	      System.out.println("Bogstavet var IKKE korrekt: "+bogstav);
	      forkerteBogstaver = forkerteBogstaver + 1;
	      if (forkerteBogstaver>6) {
	        spilletErTabt = true;
	      }
	    }
	    opdaterSynligtOrd();
	  }

	  void logStatus() {
	    System.out.println(" --------- ");
	    if (spilletErTabt) System.out.println("SPILLET ER TABT");
	    if (spilletErVundet) System.out.println("SPILLET ER VUNDET");
	    System.out.println("ordet = "+ordet);
	    System.out.println("synligtOrd = "+synligtOrd);
	    System.out.println("forkerteBogstaver = "+forkerteBogstaver);
	    System.out.println("brugeBogstaver = "+brugteBogstaver);
	    System.out.println(" --------- ");
	  }
	  
		public static String læsInputStreamSomStreng(InputStream is) throws IOException {
			char[] buffer = new char[1024];
			StringBuilder out = new StringBuilder();
			Reader in = new InputStreamReader(is, "UTF-8");
			int read;
			do {
				read = in.read(buffer, 0, buffer.length);
				if (read > 0) {
					out.append(buffer, 0, read);
				}
			} while (read >= 0);
			in.close();
			return out.toString();
		}

		public void vælgOrdFraDr() throws IOException {
			URL url = new URL(velkomst.url);//"http://dr.dk"
			
			
			String data = læsInputStreamSomStreng(url.openStream());
			//
			System.out.println("data = " + data);
			data = data.replaceAll("<.+?>", " ").toLowerCase()
					.replaceAll("[^a-zæøå]", " ");
			System.out.println("data = " + data);
			muligeOrd.addAll(new HashSet<String>(Arrays.asList(data.split(" "))));
			System.out.println("muligeOrd = " + muligeOrd);
			Random random = new Random();
			
			ordet = muligeOrd.get(random.nextInt(muligeOrd.size()));
			opdaterSynligtOrd();
		}
	
	//

}
