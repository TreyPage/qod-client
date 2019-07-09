package edu.cnm.deepdive.qodclient.controller;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import edu.cnm.deepdive.qodclient.R;
import edu.cnm.deepdive.qodclient.model.Quote;
import edu.cnm.deepdive.qodclient.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

  private LiveData<Quote> random;
  private MainViewModel viewModel;
  private ListView searchResults;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setupToolbar();
    setupFab();
    setupViewModel();
    setupSearch();
  }

  private void setupSearch() {
    EditText searchText = findViewById(R.id.search_box);
    ImageButton submitSearch = findViewById(R.id.submit_search);
    searchResults = findViewById(R.id.search_results);

    submitSearch.setOnClickListener(v ->
        viewModel.searchQuote(searchText.getText().toString().trim()));
  }

  private void setupViewModel() {
    View rootView = findViewById(R.id.root_view);
    viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    getLifecycle().addObserver(viewModel);
    viewModel.getRandomQuote()
        .observe(this, (quote) -> {
          AlertDialog builder = new AlertDialog.Builder(this)
              .setTitle("Random Quote")
              .setMessage(quote.getText() + quote.getCombinedSources())
              .setPositiveButton("Dismiss", (dialogInterface, i) -> {
              })
              .create();
          builder.show();
        });
    viewModel.searchQuote(null).observe(this, (quotes) -> {
      ArrayAdapter<Quote> adapter = new ArrayAdapter<>(
          this, android.R.layout.simple_list_item_1, quotes);
      searchResults.setAdapter(adapter);
    });
  }

  private void setupFab() {
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(
        view -> viewModel.getRandomQuote());
  }

  private void setupToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
