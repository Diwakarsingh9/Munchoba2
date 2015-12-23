package spinno.com.munchoba.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 8/19/2015.
 */
public class Mygoalssettergetter {

    @SerializedName("result")
    public List<Innerdatamygoals> result = new ArrayList<Innerdatamygoals>();

    @SerializedName("status")
    public String status;
}
