package terrain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GridReader {

	public static Grid readGrid(String file) throws FileNotFoundException, IOException{

		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		ArrayList<ArrayList<Boolean>> t = new ArrayList<ArrayList<Boolean>>();
		
		String read = br.readLine();
		
		int line = 0;
		int width = 0;
		
		while(read != null){
			t.add(new ArrayList<Boolean>());
			
			String[] tl = read.split(" ");
			for(String p : tl){
				if(p.equals("1")){
					t.get(line).add(true);
				}
				else{
					t.get(line).add(false);
				}
			}
			if(t.get(line).size() > width){
				width = t.get(line).size();
			}
			try {
				read = br.readLine();
			} catch (IOException e) {
				break;
			}
		}
		
		br.close();
		
		boolean[][] tiles = new boolean[t.size()][width];
		
		for(int x = 0; x < tiles[0].length; x ++){
			for(int y = 0; y < tiles.length; y ++){
				try{
					tiles[y][x] = t.get(y).get(x);
				}
				catch(IndexOutOfBoundsException e){
					tiles[y][x] = false;
				}
			}
		}
		
		return new Grid(tiles);
		
		
	}
	
}
