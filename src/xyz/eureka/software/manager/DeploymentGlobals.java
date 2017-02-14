package xyz.eureka.software.manager;

/**
 * Contains common deployment variables used by multiple classes.
 * 
 *
 */
public final class DeploymentGlobals
{
	
	private static final String VISITOR_FILE_PATH = "/var/lib/openshift/58977dab89f5cf8dea000118/app-root/runtime/dependencies/jbossews/webapps/Counter.log";
	
	public static String getVisitorFilePath()
	{
		return VISITOR_FILE_PATH;
	}
}
