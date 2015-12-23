package spinno.com.munchoba.trytt;

import java.util.ArrayList;

/**
 * Created by saifi45 on 10/26/2015.
 */
public class URL_MAKER {
public  static ArrayList<String> parameters1 = new ArrayList<String>();
    public  static ArrayList<String> parameters2 = new ArrayList<String>();
    public  static ArrayList<String> values1 = new ArrayList<String>();
    public  static ArrayList<String> values2 = new ArrayList<String>();
      public URL_MAKER(String s){
        for(int i=0;i<12;i++) {
            URL_MAKER.parameters1.add(""+i);
        }
          for(int i=0;i<1;i++) {
              URL_MAKER.parameters2.add(""+i);
          }
          for(int i=0;i<12;i++) {
              URL_MAKER.values1.add(""+i);
          }
          for(int i=0;i<1;i++) {
              URL_MAKER.values2.add(""+i);
          }
    }
public static String furl  = "http://munchoba.com/API/community_search.php?";
}
