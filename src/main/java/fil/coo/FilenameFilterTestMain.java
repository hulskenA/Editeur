package fil.coo;

import java.io.File;


public class FilenameFilterTestMain {

	public static void main(String[] args) {

		File racine = new File("./");
		for (String s : racine.list(new FileNameFilterC()))
			System.out.println(s);
		
		System.out.println("------------");
		
		for (String s : racine.list(new FileNameFilterClass())) 
			System.out.println(s);

	}

}
