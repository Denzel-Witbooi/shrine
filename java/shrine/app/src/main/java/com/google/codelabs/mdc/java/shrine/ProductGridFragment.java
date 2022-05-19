package com.google.codelabs.mdc.java.shrine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class ProductGridFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        /**
         * The above code sets the app bar from
         * our XML layout to be the Action Bar for this activity.
         * The callback onCreateOptionsMenu tells the activity
         * what to use as our menu. In this case,
         * it will put the menu items
         * from R.menu.shr_toolbar_menu into the app bar.
         */
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment with the ProductGrid theme
        View view = inflater.inflate(R.layout.shr_product_grid_fragment,container, false);

        // Set up the toolbar
        setUpToolbar(view);

        return view;
    }

    private void setUpToolbar(View view) {
//        reference to toolbar
        Toolbar toolbar = view.findViewById(R.id.app_bar);
//        reference to activity
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            //  If the activity isn't null,
            //  set the Toolbar to be used as an ActionBar using setSupportActionBar
            activity.setSupportActionBar(toolbar);
        }
    }
    // to inflate the contents of shr_toolbar_menu.xml into the toolbar:
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.shr_toolbar_menu,menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }


}
