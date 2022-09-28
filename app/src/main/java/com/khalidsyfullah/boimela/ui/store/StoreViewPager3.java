package com.khalidsyfullah.boimela.ui.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;
import com.khalidsyfullah.boimela.datamodel.BookDataModel;
import com.khalidsyfullah.boimela.datamodel.CategoryDataModel;
import com.khalidsyfullah.boimela.ui.home.AuthorAdapter;

import java.util.ArrayList;

public class StoreViewPager3 extends Fragment {


    private RecyclerView categoryRecycler;
    private ArrayList<CategoryDataModel> categoryDataModels;
    private CategoryAdapter categoryAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.viewpager_store_3, container, false);

        categoryRecycler = root.findViewById(R.id.store_3_recycler_view);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        categoryDataModels = new ArrayList<>();

        categoryDataModels.add(new CategoryDataModel("Fiction","https://d1csarkz8obe9u.cloudfront.net/posterpreviews/science-fiction-book-cover-design-template-b3ebf6385a80a0f8a08d5e813d2bdf44_screen.jpg?ts=1637019532",1));
        categoryDataModels.add(new CategoryDataModel("Non-Fiction","https://i.pinimg.com/originals/67/84/f2/6784f23ad1d7a20fd1fa07621e5232c6.jpg",2));
        categoryDataModels.add(new CategoryDataModel("Poetry","https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/0ac96e79227357.5cbd5178ceaa7.jpg",3));
        categoryDataModels.add(new CategoryDataModel("Biography","https://www.adazing.com/wp-content/uploads/2012/09/einstein.jpg",4));
        categoryDataModels.add(new CategoryDataModel("Fiction","https://d1csarkz8obe9u.cloudfront.net/posterpreviews/science-fiction-book-cover-design-template-b3ebf6385a80a0f8a08d5e813d2bdf44_screen.jpg?ts=1637019532",1));
        categoryDataModels.add(new CategoryDataModel("Non-Fiction","https://i.pinimg.com/originals/67/84/f2/6784f23ad1d7a20fd1fa07621e5232c6.jpg",2));
        categoryDataModels.add(new CategoryDataModel("Poetry","https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/0ac96e79227357.5cbd5178ceaa7.jpg",3));
        categoryDataModels.add(new CategoryDataModel("Biography","https://www.adazing.com/wp-content/uploads/2012/09/einstein.jpg",4));
        categoryDataModels.add(new CategoryDataModel("Fiction","https://d1csarkz8obe9u.cloudfront.net/posterpreviews/science-fiction-book-cover-design-template-b3ebf6385a80a0f8a08d5e813d2bdf44_screen.jpg?ts=1637019532",1));
        categoryDataModels.add(new CategoryDataModel("Non-Fiction","https://i.pinimg.com/originals/67/84/f2/6784f23ad1d7a20fd1fa07621e5232c6.jpg",2));
        categoryDataModels.add(new CategoryDataModel("Poetry","https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/0ac96e79227357.5cbd5178ceaa7.jpg",3));
        categoryDataModels.add(new CategoryDataModel("Biography","https://www.adazing.com/wp-content/uploads/2012/09/einstein.jpg",4));
        categoryDataModels.add(new CategoryDataModel("Fiction","https://d1csarkz8obe9u.cloudfront.net/posterpreviews/science-fiction-book-cover-design-template-b3ebf6385a80a0f8a08d5e813d2bdf44_screen.jpg?ts=1637019532",1));
        categoryDataModels.add(new CategoryDataModel("Non-Fiction","https://i.pinimg.com/originals/67/84/f2/6784f23ad1d7a20fd1fa07621e5232c6.jpg",2));
        categoryDataModels.add(new CategoryDataModel("Poetry","https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/0ac96e79227357.5cbd5178ceaa7.jpg",3));
        categoryDataModels.add(new CategoryDataModel("Biography","https://www.adazing.com/wp-content/uploads/2012/09/einstein.jpg",4));
        categoryDataModels.add(new CategoryDataModel("Fiction","https://d1csarkz8obe9u.cloudfront.net/posterpreviews/science-fiction-book-cover-design-template-b3ebf6385a80a0f8a08d5e813d2bdf44_screen.jpg?ts=1637019532",1));
        categoryDataModels.add(new CategoryDataModel("Non-Fiction","https://i.pinimg.com/originals/67/84/f2/6784f23ad1d7a20fd1fa07621e5232c6.jpg",2));
        categoryDataModels.add(new CategoryDataModel("Poetry","https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/0ac96e79227357.5cbd5178ceaa7.jpg",3));
        categoryDataModels.add(new CategoryDataModel("Biography","https://www.adazing.com/wp-content/uploads/2012/09/einstein.jpg",4));

        categoryRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        categoryAdapter = new CategoryAdapter(getActivity(),categoryDataModels);
        categoryRecycler.setAdapter(categoryAdapter);

    }
}
