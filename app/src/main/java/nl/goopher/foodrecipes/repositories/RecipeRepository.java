package nl.goopher.foodrecipes.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import nl.goopher.foodrecipes.models.Recipe;

public class RecipeRepository {

    private static RecipeRepository instance;
    private MutableLiveData<List<Recipe>> mRecipes;

    public static RecipeRepository getInstance() {
        if(instance == null) {
            instance = new RecipeRepository();
        }

        return instance;
    }

    private RecipeRepository() {
        mRecipes = new MutableLiveData<>();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipes;
    }

}
