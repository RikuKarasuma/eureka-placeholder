package xyz.eureka.software.manager;

/**
 * Contains common deployment variables used by multiple classes.
 * 
 *
 */
public final class DeploymentGlobals
{
	private static final String VISITOR_FILE_PATH = System.getenv("OPENSHIFT_HOMEDIR") + "/" + "Counter.log"; 
			// Windows test path.
			//System.getenv("SystemDrive") + System.getenv("HOMEPATH") + "\\" + "Counter.log";

	public static String getVisitorFilePath() 
	{
		return VISITOR_FILE_PATH;
	}
}
