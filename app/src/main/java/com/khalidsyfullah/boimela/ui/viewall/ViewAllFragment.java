package com.khalidsyfullah.boimela.ui.viewall;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.PublisherDataModel;
import com.khalidsyfullah.boimela.global.StaticData;
import com.khalidsyfullah.boimela.ui.home.HomeViewModel;
import com.khalidsyfullah.boimela.ui.store.StoreViewModel;

import java.util.ArrayList;

public class ViewAllFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private StoreViewModel storeViewModel;
    private EditText searchView;
    private TextView clearText;
    private RecyclerView viewAllRecycler;
    private ArrayList<BookDataModel> bookDataModels;
    private ArrayList<AuthorDataModel> authorDataModels;
    private ArrayList<PublisherDataModel> publisherDataModels;
    private ViewAllBooksAdapter viewAllBooksAdapter;
    private ViewAllAuthorsAdapter viewAllAuthorsAdapter;
    private ViewAllPublishersAdapter viewAllPublishersAdapter;
    private ConstraintLayout viewAllEmptyConstraintLayout, constraintLayout;
    private ProgressBar viewAllProgressBar;
    private String searchString="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_view_all, container, false);
        searchView = root.findViewById(R.id.view_all_searchview);
        clearText = root.findViewById(R.id.view_all_clear_text);
        viewAllRecycler = root.findViewById(R.id.view_all_recycler);
        viewAllEmptyConstraintLayout = root.findViewById(R.id.view_all_empty_container);
        constraintLayout = root.findViewById(R.id.view_all_constraint_layout);
        viewAllProgressBar = root.findViewById(R.id.view_all_progress_bar);

        homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        storeViewModel = new ViewModelProvider(getActivity()).get(StoreViewModel.class);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bookDataModels = new ArrayList<>();
        authorDataModels = new ArrayList<>();
        publisherDataModels = new ArrayList<>();

        constraintLayout.setVisibility(View.GONE);



        int id = getResources().getIdentifier("android:id/search_src_text", null, null);
        EditText searchEditText = (EditText) searchView.findViewById(id);
        if (searchEditText != null) {
            searchEditText.setGravity(Gravity.CENTER);
            searchEditText.setBackground(null);

        }

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                searchString = editable.toString();

            }
        });




    }

    @Override
    public void onResume() {
        super.onResume();

        Bundle bundle = getArguments();

        String fragment = bundle.getString("fragment");
        String query = bundle.getString("query");
        String type = bundle.getString("type");


        if(fragment != null && query != null && type != null){

            viewAllProgressBar.setVisibility(View.VISIBLE);

            if (type.equals("books")) {

                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                layoutManager.setGapStrategy(2);
                viewAllRecycler.setLayoutManager(layoutManager);
                viewAllRecycler.setHasFixedSize(true);
                viewAllBooksAdapter = new ViewAllBooksAdapter(getActivity(), bookDataModels);
                viewAllRecycler.setAdapter(viewAllBooksAdapter);

                if(fragment.equals("home")) {

//                    bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Novel", "Satyagit Roy", 4, 6, "1984", "https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//                    bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Novel", "Satyagit Roy", 4, 6, "1984", "https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//                    bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Novel", "Satyagit Roy", 4, 6, "1984", "https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//                    bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Novel", "Satyagit Roy", 4, 6, "1984", "https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//                    bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Novel", "Satyagit Roy", 4, 6, "1984", "https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//                    bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Novel", "Satyagit Roy", 4, 6, "1984", "https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//                    bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Novel", "Satyagit Roy", 4, 6, "1984", "https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//                    bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Novel", "Satyagit Roy", 4, 6, "1984", "https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));
//                    bookDataModels.add(new BookDataModel("https://ds.rokomari.store/rokomari110/ProductNew20190903/260X372/975c8e23feb4_42863.gif", "Feluda", "Novel", "Satyagit Roy", 4, 6, "1984", "https://boimelafoundation.com/1984.epub", "https://boimelafoundation.com/1984.mp3"));

                    if(query.equals("best-seller")) {

                        homeViewModel.getBestSellerBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllBooksAdapter.setBookDataModels(bookDataModels);

                                if (bookDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }

                    else if(query.equals("popular-books")) {

                        homeViewModel.getPopularBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllBooksAdapter.setBookDataModels(bookDataModels);

                                if (bookDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }


                    else if(query.equals("series")) {


                        StaticData.CURRENT_BOOK_ID = bundle.getString("book_id");

                        homeViewModel.getBooksOfBookSeries().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllBooksAdapter.setBookDataModels(bookDataModels);

                                if (bookDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }

                    else if(query.equals("audiobooks")) {

                        homeViewModel.getAudioBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllBooksAdapter.setBookDataModels(bookDataModels);

                                if (bookDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }

                    else if(query.equals("top-rated")) {

                        homeViewModel.getTopRatedBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllBooksAdapter.setBookDataModels(bookDataModels);

                                if (bookDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }

                    else if(query.equals("genres")) {

                        StaticData.CURRENT_BOOK_ID = bundle.getString("book_id");

                        homeViewModel.getGenreBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllBooksAdapter.setBookDataModels(bookDataModels);

                                if (bookDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }

                    else if(query.equals("editors-choice")) {

                        homeViewModel.getEditorsChoiceBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllBooksAdapter.setBookDataModels(bookDataModels);

                                if (bookDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }

                    else if(query.equals("new-releases")) {

                        homeViewModel.getNewReleasedBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllBooksAdapter.setBookDataModels(bookDataModels);

                                if (bookDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }

                    else if(query.equals("upcoming")) {

                        homeViewModel.getUpcomingBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllBooksAdapter.setBookDataModels(bookDataModels);

                                if (bookDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }

                }

                else if(fragment.equals("store")) {


                    if(query.equals("non-fiction")) {

                        storeViewModel.getNonFictionBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllBooksAdapter.setBookDataModels(bookDataModels);

                                if (bookDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }

                    else if(query.equals("fiction")) {

                        storeViewModel.getFictionBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllBooksAdapter.setBookDataModels(bookDataModels);

                                if (bookDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }

                    else if(query.equals("series")) {

                        StaticData.CURRENT_BOOK_ID = bundle.getString("book_id");

                        storeViewModel.getBooksOfBookSeries().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllBooksAdapter.setBookDataModels(bookDataModels);

                                if (bookDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }


                    else if(query.equals("short-stories")) {

                        storeViewModel.getShortStoryBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllBooksAdapter.setBookDataModels(bookDataModels);

                                if (bookDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }

                    else if(query.equals("biography")) {

                        storeViewModel.getBiographyBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllBooksAdapter.setBookDataModels(bookDataModels);

                                if (bookDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }

                    else if(query.equals("religous")) {

                        storeViewModel.getReligiousBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllBooksAdapter.setBookDataModels(bookDataModels);

                                if (bookDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }


                    else if(query.equals("new-releases")) {

                        storeViewModel.getNonFictionBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllBooksAdapter.setBookDataModels(bookDataModels);

                                if (bookDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }

                    else if(query.equals("poetry")) {

                        storeViewModel.getNonFictionBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllBooksAdapter.setBookDataModels(bookDataModels);

                                if (bookDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }


                    else if(query.equals("editors-choice")) {

                        storeViewModel.getNonFictionBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllBooksAdapter.setBookDataModels(bookDataModels);

                                if (bookDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }

                    else if(query.equals("category-books")) {

                        StaticData.CURRENT_BOOK_ID = bundle.getString("book_id");

                        storeViewModel.getCategoryBooks().observe(getViewLifecycleOwner(), new Observer<ArrayList<BookDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<BookDataModel> bookDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllBooksAdapter.setBookDataModels(bookDataModels);

                                if (bookDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }

                }
            }
            else if (type.equals("authors")) {

                viewAllRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                viewAllAuthorsAdapter = new ViewAllAuthorsAdapter(getActivity(), authorDataModels);
                viewAllRecycler.setAdapter(viewAllAuthorsAdapter);

                if(fragment.equals("home")) {

//                    authorDataModels.add(new AuthorDataModel("Zafor Iqbal", "https://dailyasianage.com/library/1545504095_2.jpg"));
//                    authorDataModels.add(new AuthorDataModel("Anisul Hoque", "https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
//                    authorDataModels.add(new AuthorDataModel("Rakib Hasan", "https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
//                    authorDataModels.add(new AuthorDataModel("Selina Hossain", "https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
//                    authorDataModels.add(new AuthorDataModel("Zafor Iqbal", "https://dailyasianage.com/library/1545504095_2.jpg"));
//                    authorDataModels.add(new AuthorDataModel("Anisul Hoque", "https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
//                    authorDataModels.add(new AuthorDataModel("Rakib Hasan", "https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
//                    authorDataModels.add(new AuthorDataModel("Selina Hossain", "https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
//                    authorDataModels.add(new AuthorDataModel("Zafor Iqbal", "https://dailyasianage.com/library/1545504095_2.jpg"));
//                    authorDataModels.add(new AuthorDataModel("Anisul Hoque", "https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
//                    authorDataModels.add(new AuthorDataModel("Rakib Hasan", "https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
//                    authorDataModels.add(new AuthorDataModel("Selina Hossain", "https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
//                    authorDataModels.add(new AuthorDataModel("Zafor Iqbal", "https://dailyasianage.com/library/1545504095_2.jpg"));
//                    authorDataModels.add(new AuthorDataModel("Anisul Hoque", "https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
//                    authorDataModels.add(new AuthorDataModel("Rakib Hasan", "https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
//                    authorDataModels.add(new AuthorDataModel("Selina Hossain", "https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
//                    authorDataModels.add(new AuthorDataModel("Zafor Iqbal", "https://dailyasianage.com/library/1545504095_2.jpg"));
//                    authorDataModels.add(new AuthorDataModel("Anisul Hoque", "https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
//                    authorDataModels.add(new AuthorDataModel("Rakib Hasan", "https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
//                    authorDataModels.add(new AuthorDataModel("Selina Hossain", "https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));
//                    authorDataModels.add(new AuthorDataModel("Zafor Iqbal", "https://dailyasianage.com/library/1545504095_2.jpg"));
//                    authorDataModels.add(new AuthorDataModel("Anisul Hoque", "https://upload.wikimedia.org/wikipedia/commons/6/64/Anisul_Hoque_%281%29.jpg"));
//                    authorDataModels.add(new AuthorDataModel("Rakib Hasan", "https://images.gr-assets.com/authors/1354896549p8/6575675.jpg"));
//                    authorDataModels.add(new AuthorDataModel("Selina Hossain", "https://upload.wikimedia.org/wikipedia/commons/1/1b/Selina_Hossain_%282%29_%28cropped%29.JPG"));


                    if(query.equals("all-authors")) {

                        homeViewModel.getAllAuthors().observe(getViewLifecycleOwner(), new Observer<ArrayList<AuthorDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<AuthorDataModel> authorDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllAuthorsAdapter.setAuthorDataModels(authorDataModels);

                                if (authorDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }

                }

                else if(fragment.equals("store")) {

                    if(query.equals("popular-authors")) {

                        storeViewModel.getPopularAuthors().observe(getViewLifecycleOwner(), new Observer<ArrayList<AuthorDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<AuthorDataModel> authorDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllAuthorsAdapter.setAuthorDataModels(authorDataModels);

                                if (authorDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }

                }
            }
            else if (type.equals("publishers")) {

                viewAllRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                viewAllPublishersAdapter = new ViewAllPublishersAdapter(getActivity(), publisherDataModels);
                viewAllRecycler.setAdapter(viewAllPublishersAdapter);

                if(fragment.equals("home")) {

//                    publisherDataModels.add(new PublisherDataModel("চিরকুট", "https://cdn.shopify.com/s/files/1/1296/0049/files/chirkutt_logo_450.png?height=628&pad_color=ffffff&v=1546845363&width=1200"));
//                    publisherDataModels.add(new PublisherDataModel("বিভাস", "https://ds.rokomari.store/rokomari110/company/06df811a0a54_67.jpg"));
//                    publisherDataModels.add(new PublisherDataModel("ছায়াবীথি", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwKEakbr_9Ps9Z6vRnBcUpkMkvla318TaiX_RJ8Y4UNJRfLJYG6R_fBjbqQVJCSvKCcf0&usqp=CAU"));
//                    publisherDataModels.add(new PublisherDataModel("আনন্দ", "https://play-lh.googleusercontent.com/oIpsql26FPp1xqGj5L8N8OoLF7Nx7RsCWyEMv1275NocR-FsupjsCKf0L97xZIvdlA"));
//                    publisherDataModels.add(new PublisherDataModel("চিরকুট", "https://cdn.shopify.com/s/files/1/1296/0049/files/chirkutt_logo_450.png?height=628&pad_color=ffffff&v=1546845363&width=1200"));
//                    publisherDataModels.add(new PublisherDataModel("বিভাস", "https://ds.rokomari.store/rokomari110/company/06df811a0a54_67.jpg"));
//                    publisherDataModels.add(new PublisherDataModel("ছায়াবীথি", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwKEakbr_9Ps9Z6vRnBcUpkMkvla318TaiX_RJ8Y4UNJRfLJYG6R_fBjbqQVJCSvKCcf0&usqp=CAU"));
//                    publisherDataModels.add(new PublisherDataModel("আনন্দ", "https://play-lh.googleusercontent.com/oIpsql26FPp1xqGj5L8N8OoLF7Nx7RsCWyEMv1275NocR-FsupjsCKf0L97xZIvdlA"));
//                    publisherDataModels.add(new PublisherDataModel("চিরকুট", "https://cdn.shopify.com/s/files/1/1296/0049/files/chirkutt_logo_450.png?height=628&pad_color=ffffff&v=1546845363&width=1200"));
//                    publisherDataModels.add(new PublisherDataModel("বিভাস", "https://ds.rokomari.store/rokomari110/company/06df811a0a54_67.jpg"));
//                    publisherDataModels.add(new PublisherDataModel("ছায়াবীথি", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwKEakbr_9Ps9Z6vRnBcUpkMkvla318TaiX_RJ8Y4UNJRfLJYG6R_fBjbqQVJCSvKCcf0&usqp=CAU"));
//                    publisherDataModels.add(new PublisherDataModel("আনন্দ", "https://play-lh.googleusercontent.com/oIpsql26FPp1xqGj5L8N8OoLF7Nx7RsCWyEMv1275NocR-FsupjsCKf0L97xZIvdlA"));
//                    publisherDataModels.add(new PublisherDataModel("চিরকুট", "https://cdn.shopify.com/s/files/1/1296/0049/files/chirkutt_logo_450.png?height=628&pad_color=ffffff&v=1546845363&width=1200"));
//                    publisherDataModels.add(new PublisherDataModel("বিভাস", "https://ds.rokomari.store/rokomari110/company/06df811a0a54_67.jpg"));
//                    publisherDataModels.add(new PublisherDataModel("ছায়াবীথি", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwKEakbr_9Ps9Z6vRnBcUpkMkvla318TaiX_RJ8Y4UNJRfLJYG6R_fBjbqQVJCSvKCcf0&usqp=CAU"));
//                    publisherDataModels.add(new PublisherDataModel("আনন্দ", "https://play-lh.googleusercontent.com/oIpsql26FPp1xqGj5L8N8OoLF7Nx7RsCWyEMv1275NocR-FsupjsCKf0L97xZIvdlA"));

                    if(query.equals("publishers")) {

                        storeViewModel.getAllPublishers().observe(getViewLifecycleOwner(), new Observer<ArrayList<PublisherDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<PublisherDataModel> publisherDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllPublishersAdapter.setPublisherDataModels(publisherDataModels);

                                if (publisherDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }

                }

                else if(fragment.equals("store")) {

                    if(query.equals("publishers")) {

                        storeViewModel.getAllPublishers().observe(getViewLifecycleOwner(), new Observer<ArrayList<PublisherDataModel>>() {
                            @Override
                            public void onChanged(ArrayList<PublisherDataModel> publisherDataModels) {

                                viewAllProgressBar.setVisibility(View.GONE);
                                viewAllPublishersAdapter.setPublisherDataModels(publisherDataModels);

                                if (publisherDataModels.size() == 0) {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.VISIBLE);
                                    viewAllRecycler.setVisibility(View.GONE);
                                } else {
                                    constraintLayout.setVisibility(View.VISIBLE);
                                    viewAllEmptyConstraintLayout.setVisibility(View.GONE);
                                    viewAllRecycler.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }
                }
            }
        }

    }
}
