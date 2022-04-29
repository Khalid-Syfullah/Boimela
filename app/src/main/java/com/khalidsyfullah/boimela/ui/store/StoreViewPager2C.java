package com.khalidsyfullah.boimela.ui.store;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.khalidsyfullah.boimela.R;
import com.khalidsyfullah.boimela.datamodel.AuthorDataModel;

import java.util.ArrayList;

public class StoreViewPager2C extends Fragment {


    private RecyclerView topPublishersRecycler;
    private ArrayList<AuthorDataModel> publishersDataModels;
    private TopPublishersAdapter topPublishersAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.viewpager_store_2c, container, false);

        topPublishersRecycler = root.findViewById(R.id.store_2c_recycler_view);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        publishersDataModels = new ArrayList<>();

        publishersDataModels.add(new AuthorDataModel("Chirkut","https://scontent.fdac27-2.fna.fbcdn.net/v/t1.6435-9/80420814_592450154887156_1258394017222098944_n.jpg?_nc_cat=106&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=hKijhGKu-QIAX-BfsUF&_nc_ht=scontent.fdac27-2.fna&oh=00_AT8oYMCYB3v2jVSyrmncbbYOFWF1OCLwT67hlali_ax8Rg&oe=628F34C7"));
        publishersDataModels.add(new AuthorDataModel("Bibhash","https://ds.rokomari.store/rokomari110/company/06df811a0a54_67.jpg"));
        publishersDataModels.add(new AuthorDataModel("Chayabithi","https://scontent.fdac27-2.fna.fbcdn.net/v/t1.18169-9/10698532_476810742457147_8509215480382108082_n.jpg?_nc_cat=102&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=lKlnLasqenwAX_lz_b6&_nc_ht=scontent.fdac27-2.fna&oh=00_AT8t2fVLy2nJxbOHDZHg__VbTN3Vs9X1yRaxaLOBb-xtmQ&oe=6290F570"));
        publishersDataModels.add(new AuthorDataModel("Babui Prokashoni","https://scontent.fdac27-2.fna.fbcdn.net/v/t1.18169-9/26239922_2056603064355910_3579298233949067054_n.jpg?_nc_cat=101&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=ec1jJ3ZY9wEAX_VvPAz&_nc_ht=scontent.fdac27-2.fna&oh=00_AT_9-twly0L4fn6iffVtYDRsZQFM7EcfRe-Tb011eCFcqg&oe=629009FE"));
        publishersDataModels.add(new AuthorDataModel("Chirkut","https://scontent.fdac27-2.fna.fbcdn.net/v/t1.6435-9/80420814_592450154887156_1258394017222098944_n.jpg?_nc_cat=106&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=hKijhGKu-QIAX-BfsUF&_nc_ht=scontent.fdac27-2.fna&oh=00_AT8oYMCYB3v2jVSyrmncbbYOFWF1OCLwT67hlali_ax8Rg&oe=628F34C7"));
        publishersDataModels.add(new AuthorDataModel("Bibhash","https://ds.rokomari.store/rokomari110/company/06df811a0a54_67.jpg"));
        publishersDataModels.add(new AuthorDataModel("Chayabithi","https://scontent.fdac27-2.fna.fbcdn.net/v/t1.18169-9/10698532_476810742457147_8509215480382108082_n.jpg?_nc_cat=102&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=lKlnLasqenwAX_lz_b6&_nc_ht=scontent.fdac27-2.fna&oh=00_AT8t2fVLy2nJxbOHDZHg__VbTN3Vs9X1yRaxaLOBb-xtmQ&oe=6290F570"));
        publishersDataModels.add(new AuthorDataModel("Babui Prokashoni","https://scontent.fdac27-2.fna.fbcdn.net/v/t1.18169-9/26239922_2056603064355910_3579298233949067054_n.jpg?_nc_cat=101&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=ec1jJ3ZY9wEAX_VvPAz&_nc_ht=scontent.fdac27-2.fna&oh=00_AT_9-twly0L4fn6iffVtYDRsZQFM7EcfRe-Tb011eCFcqg&oe=629009FE"));


        topPublishersRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false));
        topPublishersAdapter = new TopPublishersAdapter(getActivity(), publishersDataModels);
        topPublishersRecycler.setAdapter(topPublishersAdapter);
    }
}
