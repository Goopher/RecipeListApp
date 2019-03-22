package nl.goopher.foodrecipes.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

import nl.goopher.foodrecipes.models.Recipe;
import nl.goopher.foodrecipes.repositories.RecipeRepository;

public class RecipeListViewModel extends ViewModel {

    private RecipeRepository mRecipeRepository;
    private boolean mIsViewingRecipes;
    private boolean mIsPerformingQuery = false;

    public RecipeListViewModel() {
        mRecipeRepository = RecipeRepository.getInstance();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipeRepository.getRecipes();
    }

    public void searchRecipesApi(String query, int pageNumber) {
        mIsViewingRecipes = true;
        mIsPerformingQuery = true;
        mRecipeRepository.searchRecipesApi(query, pageNumber);
    }

    public void searchNextPage() {
        if (!mIsPerformingQuery && mIsViewingRecipes) {
            mRecipeRepository.searchNextPage();
        }
    }

    public boolean isViewingRecipes() {
        return mIsViewingRecipes;
    }

    public void setIsViewingRecipes(boolean isViewingRecipes) {
        mIsViewingRecipes = isViewingRecipes;
    }

    public void setIsPerformingQuery (Boolean isPerformingQuery) {
        mIsPerformingQuery = isPerformingQuery;
    }

    public boolean isPerformingQuery() {
        return mIsPerformingQuery;
    }

    public boolean onBackPressed() {
        if(mIsPerformingQuery) {
            // cancel the query
            mRecipeRepository.cancelRequest();
            mIsPerformingQuery = false;
        }
        if(mIsViewingRecipes) {
            mIsViewingRecipes = false;
            return false;
        }
        return true;
    }

}
