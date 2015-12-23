package spinno.com.munchoba.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 8/12/2015.
 */
public class bodygraph {
    @SerializedName("result")
    public List<Innerdata4> innerdatabodyfatgraph= new ArrayList<Innerdata4>();



    @SerializedName("status")
    public String message;
}
