// Copyright 2003 Resplendent Technology Ltd.
// See objectlessons.com for details of the java3d course.

public class FindUrl {

public static java.net.URL get(String filename, java.applet.Applet applet) {
  String codeBaseString;
  // initialize the code base
	try {
	    java.net.URL codeBase = applet.getCodeBase();
	    codeBaseString = codeBase.toString();
	} catch (Exception e) {
	    // probably running as an application, try the application
	    // code base
	    codeBaseString = "file:./";
  }
  java.net.URL url = null;
	try {
	    url = new java.net.URL(codeBaseString + filename);
  } catch (java.net.MalformedURLException ex) {
	    System.out.println(ex.getMessage()+"\n"+codeBaseString+ filename);
	}
	if (url == null) { // application, try file URL
     try {
		     url = new java.net.URL("file:./"+filename);
     } catch (java.net.MalformedURLException ex) {
         System.out.println(ex.getMessage());
     }
	}
  return url;
}
} // end of class