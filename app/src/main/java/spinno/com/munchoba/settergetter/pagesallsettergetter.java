package spinno.com.munchoba.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 8/13/2015.
 */
public class pagesallsettergetter {
    @SerializedName("result")
    public List<Innerdataallpages> innerdatabodyfatgraph= new ArrayList<Innerdataallpages>();



    @SerializedName("status")
    public String message;
}
