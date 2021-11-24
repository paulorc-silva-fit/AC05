package source;

import java.io.IOException;
import java.util.Scanner;

import interfaces.Entry;

/**
 * @author Geovane Donizete Laera  - RA: 1902679 
 * @author Isaque Ribeiro dos Santos Junior - RA: 1903978
 * @author Marcelo Martinez Mesa Campos - RA: 1905076 
 * @author Paulo Ricardo Costa da Silva - RA: 1905013 
 * @author Vinícius da Cruz Pera - RA: 1903144
 * Data: 27/10/2021
 *
 */
public class WordCount {
	public static void main(String[] args) throws IOException {
		String texto = "O Brasil, um vasto pais sul-americano, estende-se da Bacia Amazonica, no norte, ate os vinhedos “"
		+ "e as gigantescas Cataratas do Iguacu, no sul. O Rio de Janeiro, simbolizado pela sua estatua de 38 metros de altura "
		+ "do Cristo Redentor, situada no topo do Corcovado, e famoso pelas movimentadas praias de Copacabana e Ipanema, "
		+ "bem como pelo imenso e animado Carnaval, com desfiles de carros alegoricos, fantasias extravagantes e samba.\r\n"
		+ "O Brasil e um pais localizado no subcontinente da America do Sul. O territorio brasileiro e banhado pelo oceano "
		+ "Atlantico, limitando-se ao norte, com a Guiana Francesa, Suriname, Guiana, Venezuela e Colombia; a noroeste, "
		+ "com o Peru; a oeste, com a Bolivia, Paraguai e Argentina; e ao sul, com o Uruguai. O Brasil é lindo";

		Scanner doc = new Scanner(texto);
		doc.useDelimiter("[^a-zA-Z]"); // ignora caracteres que não são letras

		HashTableMap<String,Integer> h = new HashTableMap<String,Integer>();

		String word;
		Integer count;

		while (doc.hasNext()) {
			word = doc.next();

			if (word.equals("")) {
				continue; // ignora strings nulas entre delimitadores
			}

			if (word.length()<=3) {
				continue; // ignora letras com 3 ou menos caracteres
			}

			word = word.toLowerCase(); // ignora maiúscula e minúscula
			count = h.get(word); // pega o contador anterior e conta com esta palavra

			if (count == null) {
				h.put(word, 1); // autoboxing permite isso
			}
			else {
				h.put(word, ++count); // autoboxing/unboxing permite isso
			}
		}

		int maxCount = 0;
		String maxWord = "sem palavras";

		for (Entry<String,Integer> ent : h.entrySet()) { // procura o número máximo de palavras
			if (ent.getValue() > maxCount) {
				maxWord = ent.getKey();
				maxCount = ent.getValue();
			}
		}

		System.out.print("A palavra mais frequente é \"" + maxWord);
		System.out.println(",\" com um total de ocorrências = " + maxCount + ".");
		doc.close();
	}
}