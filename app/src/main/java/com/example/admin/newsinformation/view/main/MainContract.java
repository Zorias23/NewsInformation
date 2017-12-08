package com.example.admin.newsinformation.view.main;

/**
 * Created by Admin on 12/4/2017.
 */




import com.example.admin.newsinformation.util.BasePresenter;
import com.example.admin.newsinformation.util.BaseView;
import com.example.admin.newsinformation.model.NewsResponse;


/**
 * Created by Admin on 11/29/2017.
 */

public interface MainContract
{
    //methods for main activity
    interface View extends BaseView
    {

        void setCurrentNews(NewsResponse newsResponse);
        void showProgress(String progress);

    }

    interface Presenter extends BasePresenter<View>
    {

        void getCurrentNews();

    }
}
