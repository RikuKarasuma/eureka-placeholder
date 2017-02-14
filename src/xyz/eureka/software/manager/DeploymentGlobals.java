package xyz.eureka.software.manager;

/**
 * Contains common deployment variables used by multiple classes.
 * 
 *
 */
public final class DeploymentGlobals
{
	private static final String VISITOR_FILE_PATH = "/var/lib/openshift/58a2c63a7628e1c4ab00014f/app-root/runtime/dependencies/jbossews/webapps/Counter.log";
	
	public static String getVisitorFilePath()
	{
		return VISITOR_FILE_PATH;
	}
}
