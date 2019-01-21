package vilhelmmuradyan.com.vilhelmmuradyantask.network;

import retrofit2.Call;
import retrofit2.http.GET;
import vilhelmmuradyan.com.vilhelmmuradyantask.model.Results;

public interface ResultServer {

    @GET("api/?page=1&amp;results=10&amp;seed=abc")
    Call<Results> getResults();
}
