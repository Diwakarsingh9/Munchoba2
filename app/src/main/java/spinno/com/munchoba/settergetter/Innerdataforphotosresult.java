package spinno.com.munchoba.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 8/31/2015.
 */
public class Innerdataforphotosresult {

    @SerializedName("id")
    public String id;

    @SerializedName("user_id")
    public String user_id;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("location")
    public String location;

    @SerializedName("created")
    public String created;

    @SerializedName("photos")
    public List<Innerdataphotos> photos= new ArrayList<Innerdataphotos>();
}
