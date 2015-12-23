package spinno.com.munchoba.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 9/2/2015.
 */
public class Videosmy {
    @SerializedName("status")
    public String status;


    @SerializedName("result")
    public List<myvideossettergetter> result= new ArrayList<myvideossettergetter>();

}
