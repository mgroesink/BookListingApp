package nl.rocvantwente.rsk01.booklistingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BooksAdapter extends ArrayAdapter<Book> {

    ViewHolder viewHolder = new ViewHolder();
    private class ViewHolder {
        TextView title;
        TextView realeaseyear;
        TextView isbn;
        TextView author;
        TextView publisher;
        TextView language;
        ImageView cover;
    }

    private  class ImageLoadTask extends AsyncTask<String , Void , Bitmap> {

        ImageView bmImage;

        public ImageLoadTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public BooksAdapter(@NonNull Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Book book = getItem(position);
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.book_list_item , parent , false);
            viewHolder.title = convertView.findViewById(R.id.text_view_title);
            viewHolder.isbn = convertView.findViewById(R.id.text_view_isbn);
            viewHolder.author = convertView.findViewById(R.id.text_view_author);
            viewHolder.publisher = convertView.findViewById(R.id.text_view_publisher);
            viewHolder.realeaseyear = convertView.findViewById(R.id.text_view_releaseyear);
            viewHolder.language = convertView.findViewById(R.id.text_view_language);
            viewHolder.cover = convertView.findViewById(R.id.image_view_cover);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        if(book.get_title() != null) viewHolder.title.setText(book.get_title());
        if(book.get_isbn() != null) viewHolder.isbn.setText(book.get_isbn());
        //viewHolder.author.setText(book.get_author());
        if(book.get_publisher() != null) viewHolder.publisher.setText(book.get_publisher());
        if(book.get_language() != null) viewHolder.language.setText(book.get_language());
        if(book.get_releaseYear() != null) viewHolder.realeaseyear.setText(book.get_releaseYear());

        //if(book.get_coverUrl() != null) viewHolder.cover.setImageBitmap(myBitmap);
        //if(book.get_coverUrl().trim() != "")
            ImageLoadTask task = new ImageLoadTask(viewHolder.cover);
            task.execute(book.get_coverUrl());
        return convertView;

    }
}
