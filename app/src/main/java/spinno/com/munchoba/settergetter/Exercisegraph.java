package spinno.com.munchoba.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 8/12/2015.
 */
public class Exercisegraph {
    @SerializedName("result")
    public List<Innerdata5> innerexrcise= new ArrayList<Innerdata5>();



    @SerializedName("status")
    public String message;
}
