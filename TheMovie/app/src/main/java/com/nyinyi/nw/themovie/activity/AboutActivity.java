package com.nyinyi.nw.themovie.activity;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.nyinyi.nw.themovie.R;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.about)
    FrameLayout fr_about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTheme(R.style.AboutTheme);

        ButterKnife.bind(this,this);

        AboutView view = AboutBuilder.with(this)
                .setPhoto(R.drawable.profile)
                .setCover(R.mipmap.profile_cover)
                .setName("Nyi Nyi Zaw")
                .setSubTitle("Android Application Developer")
                .addEmailLink("nyinyizaw199617@gmail.com")
               .setBrief("I'm warmed of mobile technologies. Ideas maker, curious and nature lover.\n" +
                        "When you don't give up\n You cannot fail")
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .addGooglePlayStoreLink("8002078663318221363")
                .addGitHubLink("nyinyiz")
                .addFacebookLink("nyinyi.zaw.7927")
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();

        fr_about.addView(view);
    }
}
