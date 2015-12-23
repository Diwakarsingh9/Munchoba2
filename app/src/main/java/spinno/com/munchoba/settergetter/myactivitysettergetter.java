package spinno.com.munchoba.settergetter;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 10/8/2015.
 */
public class myactivitysettergetter {

    @SerializedName("id")
    public String id;

    @SerializedName("comment_type")
    public String comment_type;

    @SerializedName("title")
    public String title1;

    @SerializedName("food_title")
    public String food_title;

    @SerializedName("workout_time")
    public String workout_time;

    @SerializedName("workout_calories")
    public String workout_calories;

    @SerializedName("mood")
    @Expose
    public String mood;

    @SerializedName("created")
    public String created;

    @SerializedName("graph_image")
    public String graph_image;

    @SerializedName("type")
    public String type;

    @SerializedName("description")
    public String description;

    @SerializedName("thumb")
    public String thumb;

    @SerializedName("path")
    public String path;

    @SerializedName("photos")
    public List<photosactivitysettergetter> photos11 = new ArrayList<>();


    @SerializedName("calories")
    public String calories;

    @SerializedName("volume")
    public String volume;

    @SerializedName("volume_unit")
    public String volume_unit;

    @SerializedName("food_id")
    public String food_id;

    @SerializedName("total_cal")
    public String total_cal;


}
