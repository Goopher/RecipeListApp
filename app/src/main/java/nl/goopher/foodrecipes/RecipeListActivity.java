package nl.goopher.foodrecipes;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nl.goopher.foodrecipes.models.Recipe;
import nl.goopher.foodrecipes.requests.RecipeApi;
import nl.goopher.foodrecipes.requests.ServiceGenerator;
import nl.goopher.foodrecipes.requests.responses.RecipeResponse;
import nl.goopher.foodrecipes.requests.responses.RecipeSearchResponse;
import nl.goopher.foodrecipes.util.Constants;
import nl.goopher.foodrecipes.util.Testing;
import nl.goopher.foodrecipes.viewmodels.RecipeListViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends BaseActivity {

    private static final String TAG = "RecipeListActivity";

    private RecipeListViewModel mRecipeListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRetrofitRequest();
            }
        });
        subscribeObservers();
    }

    private void subscribeObservers() {
        mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                if(recipes != null) {
                    Testing.printRecipes(recipes, "recipes test");
                }
            }
        });
    }

    private void searchRecipesApi(String query, int pageNumber) {
        mRecipeListViewModel.searchRecipesApi(query, pageNumber);
    }


    private void testRetrofitRequest() {
       searchRecipesApi("chicken breast", 0);
    }
}