package wordPuzzle;

import java.util.Set;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
	
	public class Writer {
		public static void writeToCSV(String filePath, Set<String> words) {
			try (FileWriter fileWriter = new FileWriter(filePath);
				 BufferedWriter writer = new BufferedWriter(fileWriter)) {
				for (String word : words) {
					writer.write(word + ",");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
