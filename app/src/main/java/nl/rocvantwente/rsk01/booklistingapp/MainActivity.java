package nl.rocvantwente.rsk01.booklistingapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Original call to the Google API
    private static String GOOGLE_BOOKS_API_REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes?q=";
    SearchView svBook;

    private BooksAdapter mAdapter;

    private class DownloadBooksTask extends AsyncTask<String , Void , List<Book>>
    {

        @Override
        protected List<Book> doInBackground(String... urls) {
            QueryUtils utils = new QueryUtils(getApplicationContext());
            List<Book> books = new ArrayList<Book>();
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<Book> result = utils.fetchBookData(urls[0]);
            return result;
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            mAdapter.clear();

            if(books != null && !books.isEmpty()){
                mAdapter.addAll(books);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        svBook = findViewById(R.id.search_view_book);
        svBook.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                doSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        ListView booksListView = (ListView)findViewById(R.id.list_view_books);

        mAdapter = new BooksAdapter(this , new ArrayList<Book>());

        booksListView.setAdapter(mAdapter);
        booksListView.setEmptyView(findViewById(R.id.empty));


    }

    private void doSearch(String s) {
        String search = GOOGLE_BOOKS_API_REQUEST_URL + s;
        DownloadBooksTask task = new DownloadBooksTask();
        task.execute(search);
    }

    @Override
    protected void onResume() {
        super.onResume();
        doSearch(svBook.getQuery().toString());
    }
}
