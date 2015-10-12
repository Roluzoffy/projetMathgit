import java.util.Arrays;
import java.util.Scanner;


public class ImproCeasar {
	private int y;
	private int x;
	private static char[] TabAlphabet = {'@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'}  ;
	private static float[] Frequences = {0.00F, 7.68F, 0.80F, 3.32F, 3.60F, 17.76F, 1.06F, 1.10F, 0.64F, 7.23F, 0.19F, 0.00F, 5.89F, 2.72F, 7.61F, 5.34F, 
		3.24F, 1.34F, 6.81F, 8.23F, 7.30F, 6.05F, 1.27F, 0.00F, 0.54F, 0.21F, 0.07F};
	private float[] frequencesTexte;
	private char[] tabSelec ;
	private char[] tabTraduit ;
	private char[] Cle;
	private float[] tabTaMer;
	
	public ImproCeasar(){
		this.x = 50;
		this.tabSelec = new char[x];
		this.tabTraduit = new char[x];
		this.Cle = new char[y];
		this.frequencesTexte = new float[27];
		this.tabTaMer = new float [27];
	}

	public void setY(int a){
		this.y = a;
		this.Cle = new char[y];
	}

	public int getY(){
		return this.y;
	}

	public char[] getCle() {
		return Cle;
	}

	public void setCle(char[] cle) {
		Cle = cle;
	}

	public String toString() {
		return "ImproCeasar [tabAlphabet=" + Arrays.toString(TabAlphabet) + "]";
	}


	public String toString2() {
		return "ImproCeasar [tabSelec=" + Arrays.toString(tabSelec) + "]";
	}

	public String toString3() {
		return "ImproCeasar [tabTraduit=" + Arrays.toString(tabTraduit) + "]";
	}

	public String toString4() {
		return "ImproCeasar [Cle=" + Arrays.toString(Cle) + "]";
	}

	public void setX(int a){
		this.x = a;
		this.tabSelec = new char[x];
		this.tabTraduit = new char[x];
	}

	public void setTabSelec(char[] b){
		this.tabSelec = b;
	}

	public void insert(){
		Scanner sc = new Scanner(System.in);

		System.out.println("Que voulez-vous coder/decoder ?");
		String selection = sc.nextLine();
		String selectionMaj = selection.toUpperCase();
		setX(selectionMaj.length());
		System.out.println(getX());
		setTabSelec(selectionMaj.toCharArray());
		System.out.println("Vous avez saisi : "+toString2());
		System.out.println("Voulez-vous coder/decoder ce texte ? (oui/non)");
		String confirmation = sc.nextLine();
		if(confirmation.equals("oui")){
			System.out.println("voulez vous coder ou decoder ?");
			String codeDecode = sc.nextLine();
			if(codeDecode.equals("coder")){
				System.out.println("quelle clef voulez vous utiliser ?");
				String c = sc.nextLine();
				setY(c.length());
				setCle(c.toUpperCase().toCharArray());
				System.out.println("Voici votre clef: "+toString4());
				System.out.println("Voulez vous coder en 'ceasar' ou en 'vigenere' ?");
				String couv = sc.nextLine();
				if(couv.equals("ceasar")){
					coderTexte();
					System.out.println("Le texte code est: "+toString3());
					insert();
				}else if(couv.equals("vigenere")){
					coderVigenere();
					System.out.println("Le texte code est: "+toString3());
					insert();
				}else{
					insert();
				}
			}else if(codeDecode.equals("decoder")){
				System.out.println("Voulez vous decoder en 'ceasar' ou en 'vigenere' ?");
				String couv = sc.nextLine();
				if(couv.equals("ceasar")){
					decoderTexte();
					System.out.println("Le texte code est: "+toString3());
					insert();
				}else if(couv.equals("vigenere")){
					System.out.println("fais pas chier batar !!!!");
					insert();
				}else{
					insert();
				}
			}else{
				insert();
			}
		}else{
			insert();
		}
	}

	public void coderVigenere(){
		for(int i=0; i < getX(); i++){
			int i2 = i;
			if(i2>(getY()-1)){
				i2 = (i2-(i2/(getY()))*(getY()));
			}
			int b = getCharTabAlphabet(tabSelec[i]) + getCharTabAlphabet(Cle[i2]);
			if(b > 26){
				b = ((b-(b/26)*26));
			}
			setTabTraduit(TabAlphabet[b], i);

		}



	}
	//SPYDER ma geullee !!!!!
	
	public float calcultg(int decallage){
		float blabla = 0.00F;
		for (int i=1; i<27 ;i++){
			blabla = blabla + (Frequences[ ( (i+decallage) - ( ( (i+decallage)/26 )*26) ) ] * frequencesTexte[i] ) ;
		}
		return blabla;
	}
	
	public void setFrequencesTexte(){
		for(int i=1; i< 27; i++){
			frequencesTexte[i] = (getNbCharTabSelec(TabAlphabet[i]));
			}
	}
	
	public void setTabTaMer(){
		for (int i = 1; i<27; i++){
			tabTaMer[i] = calcultg(i-1);
		}
		
	}
	
	public int plusGrosElement(){
		float blabla = 0.00F;
		int oups = 0;
		for(int i=1; i<27; i++){
			if(blabla < tabTaMer[i]){
				blabla = tabTaMer[i];
				oups = i;
			}
		}
		return oups;
	}
	
	
	public void decoderTexte(){
		System.out.println("test1");
		setFrequencesTexte();
		setTabTaMer();
		
		
		
		System.out.println("test2");
		int chepaskoi = (plusGrosElement()-getCharTabAlphabet('E'));
		if (chepaskoi<= 0){
			chepaskoi = chepaskoi + 26;
		}
		int x1 = getCharTabAlphabet(TabAlphabet[chepaskoi]);
	    //	if(x1<=0){
		//	x1 = x1+26;
		//}
		System.out.println("test3");
		System.out.println("a"+TabAlphabet[x1]);
		setCle(Character.toString(TabAlphabet[x1]).toCharArray());
		
		for(int i=0; i < getX(); i++){
			int b = getCharTabAlphabet(tabSelec[i]) - getCharTabAlphabet(Cle[0]);
			System.out.println(b);
			System.out.println(x1);
			if(b <= 0){
				b = b+26;
			}
			System.out.println(b+"  yolo  "+i);
			setTabTraduit(TabAlphabet[b], i);
		}
		System.out.println("test4");
		//XXXXXXX
		//pareil que l'autre, batar
		//jy sui presk tamer
	}

	public int getNbCharTabSelec(char c){
		int v = 0;
		int i = 0;
		while(i < getX()){
			if(tabSelec[i] == c){
				v = v+1;
			}
			i++;
		}
		return v;
	}


	public int getCharTabAlphabet(char c){
		for(int i=1; i < 27; i++){
			if(c==TabAlphabet[i]){
				return i;
			}
		}
		return 42; //La reponse a la vie d'apres google
	}

	public void setTabTraduit(char c, int i){
		tabTraduit[i] = c;
	}


	public void coderTexte(){
		for(int i=0; i < getX(); i++){
			int b = getCharTabAlphabet(tabSelec[i]) + getCharTabAlphabet(Cle[0]);
			if(b > 26){
				b = ((b-(b/26)*26));
			}
			setTabTraduit(TabAlphabet[b], i);

		}
	}

	public Integer getX() {
		return x;
	}

	public static char[] getTabAlphabet() {
		return TabAlphabet;
	}

	public char[] getTabSelec() {
		return tabSelec;
	}

}
