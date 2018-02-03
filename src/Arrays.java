/**
 * @(#)Arrays.java
 *
 * Arrays application
 *
 * @author 
 * @version 1.00 2010/3/23
 */
import java.util.*;
import java.io.*;
 
public class Arrays {
    
    public static void main(String[] args) {
    	
    	String xmlFile   = "<things><thing>cat</thing><thing>bird</thing><thing>plane</thing></things>";
    		//"<person1><name>Chris</name><age>23</age></person1>";
    	
    	Map xmlTree = new HashMap();
        
    	loadXML2(xmlFile, xmlTree);
    	
    	System.out.println(xmlTree.values());

    }
    
    public static void loadXML(String xmlStr, Map mapRef) {
    	if(xmlStr.indexOf('<') != -1) {
    		String aTag 	 = findTag(xmlStr, 0);
    		String aContents = getContents(xmlStr, aTag);
    		mapRef.put(aTag, aContents);
    		loadXML(aContents, mapRef);
    	}
    	
    }
    
    public static void loadXML2(String xmlStr, Map mapRef) {
    	
    	String aContents = "";
    	
    	String aTag 	 = "";
    	
    	int contentStart = 0;
    	
    	int contentEnd	 = 0; 
    	
    	int startIndex = 0;
    	
    	int tagStart   = 0;
    	
    	int tagEnd 	   = 0;
    	
    	
    	if(xmlStr.indexOf('<') != -1) {
    		
    		while(contentEnd > -1) {
    		
	    		// Find the location of first <.
	    		tagStart = xmlStr.indexOf('<', contentEnd == 0 ? contentEnd : contentEnd + 1);
	
				// Does not take attributes into account.    	
	    		tagEnd	 = xmlStr.indexOf('>', tagStart);
	    	
	    		aTag = xmlStr.substring(tagStart + 1, tagEnd);
	    	
	    		System.out.println(contentEnd);
	    	
	    		contentEnd	 = xmlStr.indexOf("</" + aTag + '>', contentEnd + 1);
	    	
	    		if(contentEnd > -1) {
		    		aContents = xmlStr.substring(tagStart + aTag.length() + 2, contentEnd);
		    		
		    		// Do this.
			    	// If the key exist at this level go add value to that map.
			    	// If it doesn't make new map with that key.
		    		
		    		if(!mapRef.containsKey(aTag)) {
		    		
		    			Map subXMLTree = new HashMap();
		    		
		    			subXMLTree.put(aTag, aContents);
		    		
		    			mapRef.put(aTag, subXMLTree);
		    			
		    			loadXML2(aContents, subXMLTree);
		    		}
		    		else {
		    			// Almost done.  May need a tree not a map.
		    			mapRef.get(aTag);
		    		}
		    		
		    		
	    		}
	    		
    		}
    	}
    	
    }
    
    public static String findTag(String xmlStr, int startIndex) {
    	
    	int tagStart = xmlStr.indexOf('<', startIndex);

		// Does not take attributes into account.    	
    	int tagEnd	 = xmlStr.indexOf('>', tagStart);
    	
    	return xmlStr.substring(tagStart + 1, tagEnd);
    	
    }
    
    public static String getContents( String xmlStr, String tag) {
    	
    	int contentStart = xmlStr.indexOf('<' + tag + '>') + tag.length() + 2;
    	
    	int contentEnd	 = xmlStr.indexOf("</" + tag + '>');
    	
    	return xmlStr.substring(contentStart, contentEnd);
    }
}


