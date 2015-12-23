package spinno.com.munchoba.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 10/8/2015.
 */
public class myactivitystart {

    @SerializedName("status")
    public String status;

    @SerializedName("result")
    public List<myactivitysettergetter> myact = new ArrayList<>();
}
