package studyGuider;

public class UpdateController {
	
	public UpdateView view;
	
	public UpdateController()
	{
		view = new UpdateView();
		
		// Updates should only be checked manually, to avoid taxing my dear server!
		UpdateChecker checker = new UpdateChecker("http://derekcannon.com/apps/studyguider/update.txt", this);
		
		if(checker.needsUpdate() && !checker.error)
		{
			System.out.println("A more recent version exists!\n");
			
			String filename = System.getProperty("user.dir");
			
			if(UpdateDownloader.downloadAndSaveUpdate(checker.updateJARPath(), filename + "/", checker.lastVersion()))
			{
				view.setStatus("<html>Update successful! You may now close this version of Study Guider " +
						"and open the latest version, StudyGuider" + checker.lastVersion() + ".jar, located in the " +
								"same directory as this version.<html>");
				
				view.showButton();
			}
			else
			{
				view.setStatus("<html>There was an error in installing the latest version of Study Guider." +
						"Please ensure that Study Guider has adequate file-writing permissions.</html>");
				view.showButton();
			}
		}
		else if (checker.error)
		{
			view.setStatus("<html>There was an error connecting to the update server. Ensure you are connected to the internet, or try the update server later.</html>");
			view.showButton();
		}
		else
		{
			view.setStatus("You have the latest version of Study Guider!");
			view.showButton();
			//System.out.println("Doesn't need update.");
		}
		
	}
	
	public static void main(String[] args)
	{
		new UpdateController();
	}

}
