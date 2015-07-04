package scraping;

import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;

public class LinkNum {
    public static void main(String[] argv) throws Exception {

      String input_url = "";
      try {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
              System.out.print("URLを入力してください。>");
              input_url = new String(in.readLine());
          } catch (IOException err) {
            System.out.println(err);
          }

      int num0 = viewNum(argv[0]);
      int num1 = viewNum(input_url);

      if (num0 < num1) {
        System.out.println(argv[1] + "の方がリンク数が多いよ！");
      } else if(num1 < num0) {
        System.out.println(argv[0] + "の方がリンク数が多いよ！");
      } else {
        System.out.println("リンクの数は一緒だよ！");
      }
    }

    public static int viewNum(String page_url) throws Exception {
          System.out.println(page_url + "にあるリンクの数を調べるよ。");
      System.out.println("...");
      int link_num = getLinkNum(page_url);
      System.out.println("リンクの数は" + link_num + "件です！");
      return link_num;
    }

    public static int getLinkNum(String page_url) throws Exception {
      URL url = new URL(page_url);
      URLConnection conn = url.openConnection();
      String encoding = "";
      try {
        encoding = conn.getContentType().split(";")[1].split("=")[1];
      } catch(ArrayIndexOutOfBoundsException e) {
        System.out.println("encoding情報が取得できませんでした。utf-8を採用します。");
        encoding = "utf-8";
      }

      BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding ));
      Pattern link_pattern = Pattern.compile("<a [^>]*href=[^>]+>([^<]+)</a>",Pattern.CASE_INSENSITIVE );
      String line;

      int ret_num = 0;

      while ((line= in.readLine()) != null) {
        Matcher matcher = link_pattern.matcher(line);
        while(matcher.find() ) {
          ret_num += 1;
        }
      }

      in.close();

      return ret_num;
    }


    public static ArrayList getLinkList(String page_url) throws Exception {
      //アクセスしたいページpage_url
      URL url = new URL(page_url);
      URLConnection conn = url.openConnection();

      String encoding = conn.getContentType().split("=")[1];

      BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding ));
      String line;
      ArrayList link_list = new ArrayList();
      Pattern link_pattern = Pattern.compile("<a [^>]*href=[^>]+>([^<]+)</a>",Pattern.CASE_INSENSITIVE );

      while ((line= in.readLine()) != null) {
          Matcher matcher = link_pattern.matcher(line);
          while(matcher.find() ) {
            System.out.println("find");
            link_list.add(matcher.group(1));
          }
      }
      in.close();

      return link_list;
    }


    public static String getLinks(String page_url) throws Exception {
      URL url = new URL(page_url);
      URLConnection conn = url.openConnection();

      String encoding = conn.getContentType().split(";")[1].split("=")[1];

      BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding ));
      String line;
      String links = "";
      Pattern link_pattern = Pattern.compile("<a [^>]*href=[^>]+>([^<]+)</a>",Pattern.CASE_INSENSITIVE );

      while ((line= in.readLine()) != null) {
          Matcher matcher = link_pattern.matcher(line);
          while(matcher.find() ) {
            links += matcher.group(1);
            links += "NNN";
          }
      }
      in.close();

      return links;
    }
}

