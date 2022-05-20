package com.google.codelabs.mdc.java.shrine;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.codelabs.mdc.java.shrine.network.ProductEntry;
import com.google.codelabs.mdc.java.shrine.staggeredgridlayout.StaggeredProductCardRecyclerViewAdapter;

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

        // Set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 3 == 2 ? 2 : 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        StaggeredProductCardRecyclerViewAdapter adapter = new StaggeredProductCardRecyclerViewAdapter(
                ProductEntry.initProductEntryList(getResources()));
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_large);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_staggered_product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));

        // Set cut corner background for API 23+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.findViewById(R.id.product_grid).setBackgroundResource(R.drawable.shr_product_grid_background_shape);
        }

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

        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(
                getContext(),
                view.findViewById(R.id.product_grid),
                new AccelerateDecelerateInterpolator()));
    }
    // to inflate the contents of shr_toolbar_menu.xml into the toolbar:
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.shr_toolbar_menu,menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }


}
