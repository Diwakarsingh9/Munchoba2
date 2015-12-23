package spinno.com.munchoba.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 10/17/2015.
 */
public class spottingsettergetter {

    @SerializedName("result")
    public List<Innerdataspotting> innerspotting= new ArrayList<Innerdataspotting>();



    @SerializedName("status")
    public String status;
}
