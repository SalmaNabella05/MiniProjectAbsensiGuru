package id.ac.polinema.absensiguruprivate_salma.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import id.ac.polinema.absensiguruprivate_salma.model.AbsenGuru;
import id.ac.polinema.absensiguruprivate_salma.model.Detail;
import id.ac.polinema.absensiguruprivate_salma.model.GuruItem;
import id.ac.polinema.absensiguruprivate_salma.model.Rekap;
import id.ac.polinema.absensiguruprivate_salma.model.Siswa;
import id.ac.polinema.absensiguruprivate_salma.model.SiswaAdmin;
import id.ac.polinema.absensiguruprivate_salma.model.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiInterface {
//    @POST("loginAdmin")
//    Call<ResponseBody> loginAdmin(@Body User user);

    @POST("loginGuru")
    Call<ResponseBody> loginGuru(@Body User user);

    @GET("guru")
    Call<List<GuruItem>> getGuru();

    @GET("guru")
    Call<List<GuruItem>> getGuruByUsername(
            @Query("username") String username
    );

    @GET("absenGuru")
    Call<List<AbsenGuru>> getAbsenByUsername(
            @Query("username") String username
    );

    @POST("absenGuru")
    Call<ResponseBody> absenGuru(@Body AbsenGuru absenGuru);

    @Multipart
    @POST("guru")
    Call<ResponseBody> tambahGuru(
            @Part MultipartBody.Part photo,
            @PartMap Map<String, RequestBody> text);

    @GET("siswa")
    Call<List<Siswa>> getSiswa();

    @GET("siswa")
    Call<List<SiswaAdmin>> getAllSiswa();

    @POST("siswa")
    Call<ResponseBody> tambahSiswa(@Body HashMap<String, RequestBody> siswa);

    @GET("rekap")
    Call<List<Rekap>> getRekap();

    @GET("detailRekap")
    Call<List<Detail>> getAbsenByUsernameForRekap(@Query("username") String username);

}
