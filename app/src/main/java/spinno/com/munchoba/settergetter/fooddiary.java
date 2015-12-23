package spinno.com.munchoba.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 8/6/2015.
 */
public class fooddiary {

    @SerializedName("result")
    public List<Innerdata2> innerdataafooddiary= new ArrayList<Innerdata2>();



    @SerializedName("status")
    public String message;
}
