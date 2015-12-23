package spinno.com.munchoba.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 8/31/2015.
 */
public class photoalbum {

    @SerializedName("status")
    public String status;


    @SerializedName("result")
    public List<Innerdataforphotosresult> result= new ArrayList<Innerdataforphotosresult>();


}
