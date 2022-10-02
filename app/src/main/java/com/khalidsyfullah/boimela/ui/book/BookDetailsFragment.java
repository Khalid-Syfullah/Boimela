package com.khalidsyfullah.boimela.ui.book;

import static com.khalidsyfullah.boimela.global.StaticData.CURRENT_BOOK_ID;
import static com.khalidsyfullah.boimela.global.StaticData.audioUrl;
import static com.khalidsyfullah.boimela.global.StaticData.bookDetails;
import static com.khalidsyfullah.boimela.global.StaticData.bookUrl;
import static com.khalidsyfullah.boimela.global.StaticData.fileDir;
import static com.khalidsyfullah.boimela.global.StaticData.fileName;
import static com.khalidsyfullah.boimela.global.StaticData.imageDirBig;
import static com.khalidsyfullah.boimela.global.StaticData.imageDirSmall;
import static com.khalidsyfullah.boimela.global.StaticData.mediaUrl;
import static com.khalidsyfullah.boimela.ui.epub.ReaderActivity.bookLanguage;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.api.RestAPI;
import com.khalidsyfullah.boimela.api.RetrofitClient;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDetailsDataModel;
import com.khalidsyfullah.boimela.datamodel.ReviewDataModel;
import com.khalidsyfullah.boimela.ui.epub.ReaderActivity;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailsFragment extends Fragment {


    private BookDetailsViewModel bookDetailsViewModel;
    private TextView bookName, authorName, awardDescription, numberOfReader, likes, pages, numberOfRating, award, bookDescription,
            category, subcategory, publisher, language, year, edition, openReaderBtn;
    private ImageView bookImage, backBtn;
    private RatingBar rating;
    private ProgressDialog mProgressDialog;
    private File file;
    private RecyclerView reviewRecycler;
    private ArrayList<ReviewDataModel> reviewDataModels;
    private ConstraintLayout bookDetailsConstraintLayout;
    private ProgressBar bookDetailsProgressBar;
    private String book_id = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_book_details,container,false);

        bookName = root.findViewById(R.id.book_details_title);
        bookImage = root.findViewById(R.id.book_details_image);
        authorName = root.findViewById(R.id.book_details_author);
        award = root.findViewById(R.id.book_details_award_title);
        awardDescription = root.findViewById(R.id.book_details_award_description);
        numberOfReader = root.findViewById(R.id.book_details_readers_number);
        likes = root.findViewById(R.id.book_details_likes_number);
        pages = root.findViewById(R.id.book_details_pages_number);
        rating = root.findViewById(R.id.book_details_ratingbar);
        numberOfRating = root.findViewById(R.id.book_details_review_amount);
        bookDescription = root.findViewById(R.id.book_details_full_details_text);
        publisher = root.findViewById(R.id.book_details_publisher_text);
        language = root.findViewById(R.id.book_details_language_text);
        category = root.findViewById(R.id.book_details_category_text);
        year = root.findViewById(R.id.book_details_year_text);
        edition = root.findViewById(R.id.book_details_edition_text);
        subcategory = root.findViewById(R.id.book_details_subcategory_text);
        reviewRecycler = root.findViewById(R.id.book_details_reviews_recycler_view);
        openReaderBtn = root.findViewById(R.id.book_details_open);
        backBtn = root.findViewById(R.id.book_details_back);
        bookDetailsConstraintLayout = root.findViewById(R.id.book_details_constraint_layout);
        bookDetailsProgressBar = root.findViewById(R.id.book_details_progress_bar);

        bookDetailsViewModel = new ViewModelProvider(this).get(BookDetailsViewModel.class);

        return root;
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bookDetails = new MutableLiveData<>();

        bookDetailsConstraintLayout.setVisibility(View.GONE);
        openReaderBtn.setVisibility(View.GONE);
        bookDetailsProgressBar.setVisibility(View.VISIBLE);



        reviewDataModels = new ArrayList<>();
        ReviewAdapter reviewAdapter = new ReviewAdapter(reviewDataModels);
        reviewRecycler.setAdapter(reviewAdapter);

        openReaderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkForPermissions();
                bookLanguage = "en";

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_main).navigateUp();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();

        Bundle bundle = getArguments();

        if(bundle.getString("book_id") != null) {
            CURRENT_BOOK_ID = bundle.getString("book_id");

            Log.d("BookDetailsFragment","CURRENT BOOK ID: "+CURRENT_BOOK_ID);

            bookDetailsViewModel.getBookDetails().observe(getViewLifecycleOwner(), new Observer<BookDetailsDataModel>() {

                @Override
                public void onChanged(BookDetailsDataModel bookDetailsDataModel) {

                    bookName.setText(bookDetailsDataModel.getName());
                    Picasso.get().load(imageDirBig + bookDetailsDataModel.getImage()).placeholder(R.drawable.book_not_found).into(bookImage);
                    authorName.setText(bookDetailsDataModel.getWriter().getName());
                    numberOfReader.setText(String.valueOf(bookDetailsDataModel.getNumberOfReader()));
                    likes.setText(String.valueOf(bookDetailsDataModel.getLike()));
                    pages.setText(String.valueOf(bookDetailsDataModel.getPage()));
                    rating.setRating(Float.parseFloat(String.valueOf(bookDetailsDataModel.getRating())));
                    numberOfRating.setText(getResources().getString(R.string.reviews)+" ("+String.valueOf(bookDetailsDataModel.getNumberOfRating())+")");
                    bookDescription.setText(String.valueOf(bookDetailsDataModel.getDescription()));
                    publisher.setText(String.valueOf(bookDetailsDataModel.getPublisher().getName()));
                    language.setText(String.valueOf(bookDetailsDataModel.getLanguage()));
                    category.setText(String.valueOf(bookDetailsDataModel.getCategoryDataModel().getCategoryName()));
                    year.setText(String.valueOf(bookDetailsDataModel.getYear()));
                    edition.setText(String.valueOf(bookDetailsDataModel.getEdition()));
                    subcategory.setText(String.valueOf(bookDetailsDataModel.getSubCategoryDataModel().getCategoryName()));

                    fileName = bookDetailsDataModel.getName();
                    bookUrl = fileDir + bookDetailsDataModel.getPdfFile();
                    audioUrl = fileDir + bookDetailsDataModel.getAudioFile();
                    mediaUrl = fileDir + bookDetailsDataModel.getAudioFile();

                    openReaderBtn.setVisibility(View.VISIBLE);
                    bookDetailsConstraintLayout.setVisibility(View.VISIBLE);
                    bookDetailsProgressBar.setVisibility(View.GONE);
                }

            });

        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }

    private void checkForPermissions() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                Log.i("BookDetailsFragment","Unexpected flow");
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

            }
        } else {

            checkStatus();


        }
    }

    private void checkStatus(){


        if(Build.VERSION.SDK_INT > 30) {
            file = new File(Environment.getDataDirectory().getAbsolutePath() + "/data/com.khalidsyfullah.boimela" + File.separator + fileName + ".epub");
        }
        else{
            file = new File(Environment.getDownloadCacheDirectory().getAbsolutePath() + "/data/com.khalidsyfullah.boimela" + File.separator + fileName + ".epub");

        }
        if(!file.exists()){
            loadData();
        }
        else{
            Intent intent = new Intent(getActivity(), ReaderActivity.class);
            getActivity().startActivity(intent);
        }

    }



    private void loadData(){


        mProgressDialog = new ProgressDialog(getActivity(), R.style.CustomProgressDialog);
        mProgressDialog.setMessage("Downloading E-Book");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(true);


        RestAPI restAPI = RetrofitClient.createRetrofitClient();
        restAPI.downloadFileWithDynamicUrlAsync(bookUrl).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("BookDetailsFragment", "Connected to CDN server: "+response.code());

                    mProgressDialog.show();


                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            boolean writtenToDisk = writeResponseBodyToDisk(response.body());

                            Log.d("BookDetailsFragment", "E-book Download was a success? " + writtenToDisk);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void unused) {
                            super.onPostExecute(unused);

                            Toast.makeText(getActivity(), "Download successful!", Toast.LENGTH_SHORT).show();

                            mProgressDialog.dismiss();

                            Intent intent = new Intent(getActivity(), ReaderActivity.class);
                            getActivity().startActivity(intent);


                        }
                    }.execute();
                }
                else {
                    Log.d("BookDetailsFragment", "Server Response error: "+response.code());
                    Toast.makeText(getActivity(), "Download Unsuccessful: "+response.code(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("BookDetailsFragment", "No response from Server: "+t.getMessage());
                Toast.makeText(getActivity(), "No response from Server: "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            if(Build.VERSION.SDK_INT > 30) {
                //file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + File.separator + fileName + ".epub");
                file = new File(Environment.getDataDirectory().getAbsolutePath()+"/data/com.khalidsyfullah.boimela" + File.separator + fileName + ".epub");
            }
            else{
                //file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + File.separator + fileName + ".epub");
                file = new File(Environment.getDownloadCacheDirectory().getAbsolutePath()+"/data/com.khalidsyfullah.boimela" + File.separator + fileName + ".epub");
            }


            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(file);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;
                    mProgressDialog.setIndeterminate(false);
                    mProgressDialog.setMax(100);
                    mProgressDialog.setProgress((int) (fileSizeDownloaded*100/fileSize));
                    Log.d("BookDetailsFragment", "File download: " + fileSizeDownloaded + " of " + fileSize+ " "+fileSizeDownloaded/fileSize*100+ "%");
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

}