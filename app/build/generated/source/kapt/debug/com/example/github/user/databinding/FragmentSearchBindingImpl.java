package com.example.github.user.databinding;
import com.example.github.user.R;
import com.example.github.user.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentSearchBindingImpl extends FragmentSearchBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.rlSearch, 2);
        sViewsWithIds.put(R.id.edSearchUser, 3);
        sViewsWithIds.put(R.id.imgSearchUser, 4);
        sViewsWithIds.put(R.id.divider, 5);
        sViewsWithIds.put(R.id.rlUserMain, 6);
        sViewsWithIds.put(R.id.imgUserPic, 7);
        sViewsWithIds.put(R.id.userFullName, 8);
        sViewsWithIds.put(R.id.userName, 9);
        sViewsWithIds.put(R.id.userDesc, 10);
        sViewsWithIds.put(R.id.userFollowers, 11);
        sViewsWithIds.put(R.id.userFollowing, 12);
        sViewsWithIds.put(R.id.userRepositories, 13);
        sViewsWithIds.put(R.id.lUserError, 14);
        sViewsWithIds.put(R.id.userPicError, 15);
        sViewsWithIds.put(R.id.divider2, 16);
        sViewsWithIds.put(R.id.lFollowView, 17);
        sViewsWithIds.put(R.id.lFollowersNoData, 18);
        sViewsWithIds.put(R.id.lFollowingNoData, 19);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentSearchBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 20, sIncludes, sViewsWithIds));
    }
    private FragmentSearchBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.view.View) bindings[5]
            , (android.view.View) bindings[16]
            , (android.widget.EditText) bindings[3]
            , (android.widget.ImageView) bindings[4]
            , (android.widget.ImageView) bindings[7]
            , (android.widget.LinearLayout) bindings[1]
            , (android.widget.LinearLayout) bindings[17]
            , (android.widget.LinearLayout) bindings[18]
            , (android.widget.LinearLayout) bindings[19]
            , (android.widget.LinearLayout) bindings[14]
            , (android.widget.RelativeLayout) bindings[2]
            , (android.widget.RelativeLayout) bindings[6]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[9]
            , (android.widget.ImageView) bindings[15]
            , (android.widget.TextView) bindings[13]
            );
        this.lFollowMain.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.searchViewModel == variableId) {
            setSearchViewModel((com.example.github.user.viewmodels.SearchViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setSearchViewModel(@Nullable com.example.github.user.viewmodels.SearchViewModel SearchViewModel) {
        this.mSearchViewModel = SearchViewModel;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): searchViewModel
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}