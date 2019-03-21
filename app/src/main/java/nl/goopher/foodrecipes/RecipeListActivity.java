package nl.goopher.foodrecipes;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import java.util.List;

import nl.goopher.foodrecipes.adapters.OnRecipeListener;
import nl.goopher.foodrecipes.adapters.RecipeRecyclerAdapter;
import nl.goopher.foodrecipes.models.Recipe;
import nl.goopher.foodrecipes.util.Testing;
import nl.goopher.foodrecipes.viewmodels.RecipeListViewModel;

public class RecipeListActivity extends BaseActivity implements OnRecipeListener {

    private static final String TAG = "RecipeListActivity";

    private RecipeListViewModel mRecipeListViewModel;
    private RecyclerView mRecyclerView;
    private RecipeRecyclerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        mRecyclerView = findViewById(R.id.recipe_list);

        mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);
        initRecyclerView();
        subscribeObservers();
        initSearchView();
        if(!mRecipeListViewModel.isViewingRecipes()) {
            // display search categories
            displaySearchCategories();
        }
    }

    private void subscribeObservers() {
        mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                if(recipes != null) {
                    Testing.printRecipes(recipes, "recipes test");
                    mAdapter.setRecipes(recipes);
                }
            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new RecipeRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initSearchView() {
        final SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                mAdapter.displayLoading();
                mRecipeListViewModel.searchRecipesApi(s, 1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    public void onRecipeClick(int position) {

    }

    @Override
    public void onCategoryClick(String category) {
        mAdapter.displayLoading();
        mRecipeListViewModel.searchRecipesApi(category, 0);
    }

    private void displaySearchCategories() {
        mRecipeListViewModel.setIsViewingRecipes(false);
        mAdapter.displaySearchCategories();
    }
}