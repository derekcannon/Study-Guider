package studyGuider;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class UpdateDownloader {
	
	/**
	 * Attempts to fetch an internet file and save it to the specified output path.
	 * 
	 * @param updateTextPath A String of the URL of the latest version of StudyGuider
	 * @param fileOutputPath A String of the file path indicating where to save the latest version of StudyGuider.
	 * @return Returns true if the download was successfully saved.
	 */
	public static boolean downloadAndSaveUpdate(String updatePath, String fileOutputPath, String versionNumber)
	{
		System.out.println("Output path: " + fileOutputPath);
		System.out.println("Complete path: " + fileOutputPath + "upatedJAR.jar");
		
		URL updateURL;
		try {
			updateURL = new URL(updatePath);
			ReadableByteChannel rbc = Channels.newChannel(updateURL.openStream());
		    FileOutputStream fos = new FileOutputStream(fileOutputPath + "StudyGuider" + versionNumber + ".jar");
		    fos.getChannel().transferFrom(rbc, 0, 1 << 24);
		}
		/*catch (MalformedURLException e) {
			// invalid URL
		}
		catch (IOException e) {
			// write errors?
			e.printStackTrace();
		}*/
		catch (Exception e) {
			// all other errors
			System.out.println(e);
			return false; // Something went wrong, so return False, indicating the update failed.
		}
		
		return true; // The operation completed successfully. Return true
	    
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Tester
		
	}

}
