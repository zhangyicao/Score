package com.yicao.score;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private static final String A_SCORE = "aScore";
    private static final String B_SCORE = "bScore";

    private SavedStateHandle handle;

    private Integer aUndo, bUndo;

    public MyViewModel(SavedStateHandle handle) {
        if (! handle.contains(A_SCORE)) {
            handle.set(A_SCORE, 0);
        }
        if (! handle.contains(B_SCORE)) {
            handle.set(B_SCORE, 0);
        }
        this.handle = handle;
    }

    public LiveData<Integer> getAScore() {
        return handle.getLiveData(A_SCORE);
    }

    public LiveData<Integer> getBScore() {
        return handle.getLiveData(B_SCORE);
    }

    public void addAScore(int p) {
        setUndo();
        handle.set(A_SCORE, (int)handle.get(A_SCORE) + p);
    }

    public void addBScore(int p) {
        setUndo();
        handle.set(B_SCORE, (int)handle.get(B_SCORE) + p);
    }

    public void reset(){
        setUndo();
        handle.set(A_SCORE, 0);
        handle.set(B_SCORE, 0);
    }

    private void setUndo(){
        aUndo = getAScore().getValue();
        bUndo = getBScore().getValue();
    }

    public void undo(){
        handle.set(A_SCORE, aUndo);
        handle.set(B_SCORE, bUndo);
    }
}
