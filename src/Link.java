import java.net.URL;
import java.net.URLConnection;
import java.net.Proxy;
import java.net.InetSocketAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Link{
  public static void main(String[] argv) throws Exception {
      ArrayList<String> link_list  = Link.getTitle(argv[0]);
      for(int i=0; link_list.size() > i ; i++){
          System.out.println(link_list.get(i));    
      }
    }
  public static ArrayList getTitle(String page_url) throws Exception {
    //アクセスしたいページpage_url
    URL url = new URL(page_url);
    URLConnection conn = url.openConnection();

//    String charset = Arrays.asList(conn.getContentType().split(";") ).get(1);
//    String encoding = Arrays.asList(charset.split("=") ).get(1);

//    String encoding = Arrays.asList(Arrays.asList(conn.getContentType().split(";") ).get(1).split("=") ).get(1);

    String encoding = conn.getContentType().split("=")[1];
    
    
    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding )); 
//    StringBuffer response = new StringBuffer();
    String line;
    ArrayList<String> match_list = new ArrayList();
    Pattern link_pattern1 = Pattern.compile("<a [^>]*href=[^>]+>([^<]+)</a>",Pattern.CASE_INSENSITIVE);
//  Pattern link_pattern1 = Pattern.compile("<a.*>([^<]+)</a>",Pattern.CASE_INSENSITIVE);
    while ((line= in.readLine()) != null){
		Matcher matcher1 = link_pattern1.matcher(line);
		while(matcher1.find() ) {
			match_list.add(matcher1.group(1)); 	
    	} 
    }
//	response.append(line+"\n");
	in.close();
	return match_list;
  }
}

  
  
/*    Pattern link_pattern1 = Pattern.compile("<a.*>([^<]+)</a>",Pattern.CASE_INSENSITIVE);
    	Matcher matcher1 = link_pattern1.matcher(response.toString());
    	if(matcher1.find() ) {
    			return matcher1.group(1);
    		} else {
    			return null;
    			}
    		}
  }

*/




    	
/*    	
import java.net.URL;
import java.net.URLConnection;
import java.net.Proxy;
import java.net.InetSocketAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Link{
  public static void main(String[] argv) throws Exception {
      String[] link_list  = Link.getTitle(argv[0]);
      for(int i=0; link_list.length > i ; i++){
          System.out.println(link_list);    
      }
    }
  public static String[] getTitle(String page_url) throws Exception {
    //アクセスしたいページpage_url
    URL url = new URL(page_url);
    URLConnection conn = url.openConnection();

    String charset = Arrays.asList(conn.getContentType().split(";") ).get(1);
    String encoding = Arrays.asList(charset.split("=") ).get(1);

    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding )); 
    StringBuffer response = new StringBuffer();
    String line;
    while ((line= in.readLine()) != null)     	
    	response.append(line+"\n");
    in.close();

    Pattern link_pattern1 = Pattern.compile("<a.*>([^<]+)</a>",Pattern.CASE_INSENSITIVE);
    	Matcher matcher1 = link_pattern1.matcher(response.toString());
    		if(matcher1.find() ) {
    			return matcher1.group(1);
    		} else {
    			return null;
    			}
    		}
  }
*/