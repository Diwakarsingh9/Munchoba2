package spinno.com.munchoba.settergetter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saifi45 on 10/16/2015.
 */
public class termpolicysettergetter {

    @SerializedName("result")
    public List<Innerdataterm> result= new ArrayList<Innerdataterm>();

    @SerializedName("status")
    public String status;
}
