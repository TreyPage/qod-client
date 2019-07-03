package edu.cnm.deepdive.qodclient.service;

import edu.cnm.deepdive.qodclient.BuildConfig;
import edu.cnm.deepdive.qodclient.model.Quote;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface QodService {

  @GET("quotes/random")
  Single<Quote> random();

  static QodService getInstance() {
    return instanceHolder.INSTANCE;
  }

  class instanceHolder {

    private static final QodService INSTANCE;

    static {
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(Level.BODY);
      OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl(BuildConfig.BASE_URL)
          .client(client) //Can be removed
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create())
          .build();
      INSTANCE = retrofit.create(QodService.class);
//      Retrofit retrofit = new Retrofit.Builder()
//          .baseUrl(BuildConfig.BASE_URL)
//          .addConverterFactory(GsonConverterFactory.create())
//          .build();
//      INSTANCE = retrofit.create(QodService.class);
    }
  }

}
