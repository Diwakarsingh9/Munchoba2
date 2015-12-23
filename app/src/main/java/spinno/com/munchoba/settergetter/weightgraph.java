package spinno.com.munchoba.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 8/7/2015.
 */
public class weightgraph {
    @SerializedName("result")
    public List<Innerdata3> innerdataweightgraph= new ArrayList<Innerdata3>();



    @SerializedName("status")
    public String message;
}
