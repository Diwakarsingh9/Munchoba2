package spinno.com.munchoba.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 8/31/2015.
 */
public class Fooddiarydata {
    @SerializedName("meal")
    public List<Innerdatafoodmeal> foodmeal= new ArrayList<Innerdatafoodmeal>();

    @SerializedName("food")
    public List<Innerdatafooditem> food_items= new ArrayList<Innerdatafooditem>();

    @SerializedName("food_units")
    public List<Innerdatafoodunits> foodunits= new ArrayList<Innerdatafoodunits>();

    @SerializedName("status")
    public String status;
}
