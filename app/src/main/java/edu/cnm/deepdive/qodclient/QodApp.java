package edu.cnm.deepdive.qodclient;

import android.app.Application;

public class QodApp extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    // This is where Stetho is initialized
    // This is where auto-populate DB operations go
  }
}