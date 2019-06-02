package terrain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;

public class GridReader {

	public static Grid readGrid(String file) throws FileNotFoundException, IOException{
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		String[] in = new String[1];
		int maxLength = 0;
		
		while((in[in.length - 1] = br.readLine()) != null){ //Read file into the array until the end of file
			in[in.length - 1] = in[in.length - 1].replaceAll(" ", "");
			if(in[in.length - 1].length() > maxLength){
				maxLength = in[in.length - 1].length();
			}
			in = Arrays.copyOf(in, in.length + 1); //Expand array by one
		}
		in = Arrays.copyOf(in, in.length - 1); //Filter out the last element
		br.close();
		
		boolean[][] tiles = new boolean[in.length][maxLength];
		
		for(int i = 0; i < in.length; i ++){
			char[] ch = Arrays.copyOf(in[i].toCharArray(), maxLength);
			for(int c = 0; c < ch.length; c ++){
				if(ch[c] == '1')
					tiles[i][c] = true;
				else
					tiles[i][c] = false;
			}
		}
		
		return new Grid(tiles);
		
	}
	
}
