package quick;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class CountryList {
	
	// countries list scraped from:  https://history.state.gov/countries/all
	static String raw = "Afghanistan;Albania;Algeria;Andorra;Angola;"
			  		  + "Antigua and Barbuda;Argentina;Armenia;Australia;Austria;"
			  		  + "Austrian Empire;Azerbaijan;Baden;Bahamas, The;Bahrain;"
			  		  + "Bangladesh;Barbados;Bavaria;Belarus;Belgium;"
			  		  + "Belize;Benin (Dahomey);Bolivia;Bosnia and Herzegovina;Botswana;"
			  		  + "Brazil;Brunei;Brunswick and Lüneburg;Bulgaria;Burkina Faso (Upper Volta);"
			  		  + "Burma;Burundi;Cabo Verde;Cambodia;Cameroon;"
			  		  + "Canada;Cayman Islands, The;Central African Republic;Central American Federation;Chad;"
			  		  + "Chile;China;Colombia;Comoros;Congo Free State, The;"
			  		  + "Costa Rica;Cote d’Ivoire (Ivory Coast);Croatia;Cuba;Cyprus;"
			  		  + "Czechia;Czechoslovakia;Democratic Republic of the Congo;Denmark;Djibouti;"
			  		  + "Dominica;Dominican Republic;Duchy of Parma, The;East Germany (German Democratic Republic);Ecuador;"
			  		  + "Egypt;El Salvador;Equatorial Guinea;Eritrea;Estonia;"
			  		  + "Eswatini;Ethiopia;Federal Government of Germany (1848-49);Fiji;Finland;"
			  		  + "France;Gabon;Gambia, The;Georgia;Germany;"
			  		  + "Ghana;Grand Duchy of Tuscany, The;Greece;Grenada;Guatemala;"
			  		  + "Guinea;Guinea-Bissau;Guyana;Haiti;Hanover;"
			  		  + "Hanseatic Republics;Hawaii;Hesse;Holy See;Honduras;"
			  		  + "Hungary;Iceland;India;Indonesia;Iran;"
			  		  + "Iraq;Ireland;Israel;Italy;Jamaica;"
			  		  + "Japan;Jordan;Kazakhstan;Kenya;Kingdom of Serbia/Yugoslavia;"
			  		  + "Kiribati;Korea;Kosovo;Kuwait;Kyrgyzstan;"
			  		  + "Laos;Latvia;Lebanon;Lesotho;Lew Chew (Loochoo);"
			  		  + "Liberia;Libya;Liechtenstein;Lithuania;Luxembourg;"
			  		  + "Madagascar;Malawi;Malaysia;Maldives;Mali;"
			  		  + "Malta;Marshall Islands;Mauritania;Mauritius;Mecklenburg-Schwerin;"
			  		  + "Mecklenburg-Strelitz;Mexico;Micronesia;Moldova;Monaco;"
			  		  + "Mongolia;Montenegro;Morocco;Mozambique;Namibia;"
			  		  + "Nassau;Nauru;Nepal;Netherlands, The;New Zealand;"
			  		  + "Nicaragua;Niger;Nigeria;North German Confederation;North German Union;"
			  		  + "North Macedonia;Norway;Oldenburg;Oman;Orange Free State;"
			  		  + "Pakistan;Palau;Panama;Papal States;Papua New Guinea;"
			  		  + "Paraguay;Peru;Philippines;Piedmont-Sardinia;Poland;"
			  		  + "Portugal;Qatar;Republic of Genoa;Republic of Korea (South Korea);Republic of the Congo;"
			  		  + "Romania;Russia;Rwanda;Saint Kitts and Nevis;Saint Lucia;"
			  		  + "Saint Vincent and the Grenadines;Samoa;San Marino;Sao Tome and Principe;Saudi Arabia;"
			  		  + "Schaumburg-Lippe;Senegal;Serbia;Seychelles;Sierra Leone;"
			  		  + "Singapore;Slovakia;Slovenia;Solomon Islands, The;Somalia;"
			  		  + "South Africa;South Sudan;Spain;Sri Lanka;Sudan;"
			  		  + "Suriname;Sweden;Switzerland;Syria;Tajikistan;"
			  		  + "Tanzania;Texas;Thailand;Timor-Leste;Togo;"
			  		  + "Tonga;Trinidad and Tobago;Tunisia;Turkey;Turkmenistan;"
			  		  + "Tuvalu;Two Sicilies;Uganda;Ukraine;Union of Soviet Socialist Republics;"
			  		  + "United Arab Emirates, The;United Kingdom, The;Uruguay;Uzbekistan;Vanuatu;"
			  		  + "Venezuela;Vietnam;Württemberg;Yemen;Zambia;"
			  		  + "Zimbabwe";
	
	public static void main(String[] args) {
		
		// Creating the trie
		TrieNode root = new TrieNode();
		String[] c = raw.split(";");
		for(int i = 0; i < c.length; i++) {
			root.add(c[i]);
		}
		
		//number of tests:
		int tests = 500;
		
		// vars
		Random random = new Random();
		int chosenByUser, now;
		List<String> ans;
		String typedQuery, spaces;
		int start = LocalTime.now().getNano();
		System.out.println("Total country names: " + c.length);
		
		//main loop
		for(int j = 0; j < tests; j++) {
			chosenByUser = random.nextInt(c.length);
			typedQuery = "";
			for(int i = 0; i < c[chosenByUser].length(); i++) {
				typedQuery += c[chosenByUser].charAt(i);
				now = LocalTime.now().getNano();
				ans = root.prefixQuery(typedQuery);
				now = (LocalTime.now().getNano() - now)/1000000;
				spaces = "";
				if(typedQuery.length() < 10) {
					while(typedQuery.length() + spaces.length() < 10) spaces += " ";
				} else spaces = " ";
				System.out.println("q: " + typedQuery + spaces + now + " milliseconds: " + ans);
				if(ans.size() == 1) {
					System.out.println();
					System.out.println("Found a unique match with " + (i+1) + " characters.");
					System.out.println("----------------------------------------------------------");
					i = c[chosenByUser].length();
				}
			}
		}
		System.out.println("Total time: " + (LocalTime.now().getNano() - start)/1000000 + " ms");
	}
}
