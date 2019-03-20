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

        subscribeObservers();
    }

    private void subscribeObservers() {
        mRecipeListViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {

            }
        });
    }


    private void testRetrofitRequest() {
        RecipeApi recipeApi = ServiceGenerator.getRecipeApi();

//        Call<RecipeResponse> responseCall = recipeApi
//                .getRecipe(Constants.API_KEY,
//                        "495802");
//
//        responseCall.enqueue(new Callback<RecipeResponse>() {
//            @Override
//            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
//                Log.d(TAG, "onResponse: server response: " + response.toString());
//                if (response.code() == 200) {
//                    Log.d(TAG, "onResponse: " + response.body().toString());
//                    Recipe recipe = response.body().getRecipe();
//                    Log.d(TAG, "onResponse: RETRIEVED RECIPE: " + recipe);
//                } else {
//                    try {
//                        Log.d(TAG, "onResponse: " + response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//
//            @Override
//            public void onFailure(Call<RecipeResponse> call, Throwable t) {
//
//            }
//        });
    }
}