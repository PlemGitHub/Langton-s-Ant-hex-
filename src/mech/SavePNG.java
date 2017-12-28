package mech;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;

public class SavePNG {
	private File f;
	
	public void save(BufferedImage buffImg, String directionChars, int stepNumber) {
        try{
        	String home = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
        	File myFolder = new File(home+"/LangtonAntPNG");
        	if (!myFolder.exists())
        		myFolder.mkdir();
        	File directionCharsFolder = new File(myFolder+"/"+directionChars);
        	if (!directionCharsFolder.exists())
        		directionCharsFolder.mkdir();
        	f = new File(directionCharsFolder+"/"+stepNumber+".png");
            ImageIO.write(buffImg, "PNG", f);
        }
        catch(Exception e){
            e.printStackTrace();
        }
	}
}

