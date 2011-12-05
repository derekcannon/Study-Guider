package studyGuider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class UpdateChecker {
	
	private static final double CURRENT_VERSION = 3.0; // The current version of the application
	private static final String DOWNLOAD_URL = "http://www.derekcannon.com/apps/studyguider/StudyGuider";
	private double latestVersion; // The latest version of the application
	private String updateURL;
	private String updateJARPath = null;
	private UpdateController controller;
	public boolean error = false;
	
	public UpdateChecker(String updateURL, UpdateController controller)
	{
		this.updateURL = updateURL;
		this.controller = controller;
		parseUpdateURL(); // Sets "latestVersion" and "updateJARPath", based on the update text file
	}
	
	public String lastVersion()
	{
		return  String.valueOf(latestVersion);
	}
	
	/**
	 * Verifies with the contents of the URL -- a text file -- whether the current version of StudyGuider is the latest
	 * available version.
	 * @param updateURL The URL of the text file containing latest version information.
	 * @return Returns true if the current version is not the latest version as listed in the online text file.
	 */
	private void parseUpdateURL()
	{
		ArrayList<String> lines; // The Array that holds the newest version and URL of newest version download
		lines = new ArrayList<String>(); // [Version, Link to Newest Version]
		
		BufferedReader reader; // Define a BufferedReader "reader"
		
		try
		{
			controller.view.setStatus("Connecting to the update server...");
			
			// Set "reader" to read data from the URL "updateURL"
			reader = new BufferedReader(new InputStreamReader(new URL(updateURL).openStream()));
			String line = reader.readLine(); // Read the first line

			while (line != null) // While there are more lines
			{
				lines.add(line); // add the line to the "lines" Array
				line = reader.readLine(); // get the next line; if it's null (file is at the end), the loop will exit
			}
			
			controller.view.setStatus("Connection successful!");
			
			latestVersion = Double.valueOf(lines.get(0)); // Get the double value of the latest version number
			
			updateJARPath = DOWNLOAD_URL + lines.get(0) + ".jar";
		}
		catch (Exception e) // If any errors happen (e.g. no internet connection, derekcannon.com is down, etc)
		{
			error = true;
			latestVersion = 1000.0; // Since there was an error and the data wasn't received,
										   // set the latest version to a high number, so the updater won't update.
			
		}

	}
	
	public boolean needsUpdate() // Determines if the current version is the latest version
	{
		return CURRENT_VERSION < latestVersion; // Return true if the latest version is newer than the current version.
	}
	
	public String updateJARPath()
	{
		return updateJARPath;
	}
}
