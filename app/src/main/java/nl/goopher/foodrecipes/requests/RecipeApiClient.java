package nl.goopher.foodrecipes.requests;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import nl.goopher.foodrecipes.AppExecutors;
import nl.goopher.foodrecipes.models.Recipe;

import static nl.goopher.foodrecipes.util.Constants.NETWORK_TIMEOUT;

public class RecipeApiClient {

    private static RecipeApiClient instance;

    private MutableLiveData<List<Recipe>> mRecipes;


    public static RecipeApiClient getInstance() {
        if (instance == null) {
            instance = new RecipeApiClient();
        }

        return instance;
    }

    private RecipeApiClient() {
        mRecipes = new MutableLiveData<>();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipes;
    }

    public void searchRecipesApi() {
        final Future handler = AppExecutors.getInstance().networkIO().submit(new Runnable() {
            @Override
            public void run() {
                // retrieve data from rest api
                // mRecipes.postValue();
            }
        });

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // let the user know its timed out
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
    }
}
