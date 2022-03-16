
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entropy {

	public static void main(String[] args) {

		if (args.length != 1) {
			throw new IllegalArgumentException("Please enter file name");
		}

		String fileName = args[0];

		try {

			String text = Files.readString(Paths.get(fileName));

			Map<Character, Integer> letterCount = new HashMap<>();

			for (int i = 0; i < text.length(); i++) {
				Character ch = text.charAt(i);

				if (letterCount.get(ch) == null) {
					letterCount.put(ch, 0);
				}

				letterCount.put(ch, letterCount.get(ch) + 1);
			}
			
			double entropy = 0.00;
			
			for(Character c : letterCount.keySet()) {
				
				int amount = letterCount.get(c);
				double prob = (double) amount/text.length();
				
				entropy += prob * Math.log(prob) / Math.log(2.0);
				
			}
			
			entropy = -entropy;
			System.out.println("Entropy: " + entropy);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
