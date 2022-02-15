package com.example.starwarsdk

import android.app.Activity
import android.content.Intent
import com.example.starwarsdk.ui.IntroActivity
import com.example.starwarsdk.ui.film.FilmActivity
import com.example.starwarsdk.ui.people.CharacterActivity
import com.example.starwarsdk.ui.planet.PlanetActivity

internal object SdkCoordinator {
    fun startIntroActivity(activity: Activity){
        val intent = Intent(activity.applicationContext, IntroActivity::class.java)
        activity.startActivity(intent);
    }

    fun startCharacterActivity(activity: Activity){
        val intent = Intent(activity.applicationContext, CharacterActivity::class.java)
        activity.startActivity(intent);
    }

    fun startFilmActivity(activity: Activity){
        val intent = Intent(activity.applicationContext, FilmActivity::class.java)
        activity.startActivity(intent);
    }

    fun startPlanetActivity(activity: Activity){
        val intent = Intent(activity.applicationContext, PlanetActivity::class.java)
        activity.startActivity(intent);
    }
}