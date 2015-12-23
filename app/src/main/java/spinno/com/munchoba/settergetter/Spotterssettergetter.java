package spinno.com.munchoba.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 10/17/2015.
 */
public class Spotterssettergetter {

    @SerializedName("result")
    public List<Innerdataspotters> innerspotters= new ArrayList<Innerdataspotters>();



    @SerializedName("status")
    public String status;
}
