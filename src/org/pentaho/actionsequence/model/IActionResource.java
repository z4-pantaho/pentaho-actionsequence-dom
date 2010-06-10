package org.pentaho.actionsequence.model;


public interface IActionResource extends IActionIOElement{

	  /**
	   * The Resource is a solution file
	   */
	  public static final int SOLUTION_FILE_RESOURCE = 1;

	  /**
	   * The resource is a URL
	   */
	  public static final int URL_RESOURCE = 2;

	  /**
	   * The resource is an arbitrary file
	   */
	  public static final int FILE_RESOURCE = 3;

	  /**
	   * The resource type is unknown
	   */
	  public static final int UNKNOWN_RESOURCE = 4;

	  /**
	   * The resource type is an embedded string
	   */
	  public static final int STRING = 5;

	  /**
	   * The resource type is embedded xml
	   */
	  public static final int XML = 6;
	
	
  public String getUri();
  public void setURI(String uri);
  public String getMimeType();
  public void setMimeType(String mimeType);
  
  public void setMapping( String publicName );
  
  public void setName(String resourceName);
  
  public void setType(String ioType);
  
  public String getMapping();
  
  public String getName();
  
  public String getPublicName();
  
  public String getType();
  

}
