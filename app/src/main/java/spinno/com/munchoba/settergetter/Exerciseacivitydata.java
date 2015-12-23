package spinno.com.munchoba.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 8/13/2015.
 */
public class Exerciseacivitydata {

    @SerializedName("workout_type")
    public List<Innerdataworktype> workout_type= new ArrayList<Innerdataworktype>();

    @SerializedName("food_units")
    public List<Innerdatafoodunits> food_units= new ArrayList<Innerdatafoodunits>();

    @SerializedName("status")
    public String status;
}
