package spinno.com.munchoba.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 10/26/2015.
 */
public class communitysettergetter {

    @SerializedName("result")
    public List<Innerdatacommu> innerdatacommunity= new ArrayList<Innerdatacommu>();



    @SerializedName("status")
    public String status;
}
