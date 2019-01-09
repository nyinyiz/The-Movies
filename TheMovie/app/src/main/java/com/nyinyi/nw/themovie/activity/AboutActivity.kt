package com.nyinyi.nw.themovie.activity

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.nyinyi.nw.themovie.R
import com.vansuita.materialabout.builder.AboutBuilder

class AboutActivity : AppCompatActivity() {

    @BindView(R.id.about)
    internal var fr_about: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setTheme(R.style.AboutTheme)

        ButterKnife.bind(this, this)

        val view = AboutBuilder.with(this)
                .setPhoto(R.drawable.profile)
                .setCover(R.mipmap.profile_cover)
                .setName("Nyi Nyi Zaw")
                .setSubTitle("Android Application Developer")
                .addEmailLink("nyinyizaw199617@gmail.com")
                .setBrief("I'm warmed of mobile technologies. Ideas maker, curious and nature lover.\n" + "When you don't give up\n You cannot fail")
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
                .build()

        fr_about!!.addView(view)
    }
}
