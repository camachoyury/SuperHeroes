package com.camachoyury.superheroes.presentation.HeroesList

import android.util.Log
import com.camachoyury.superheroes.data.model.character.CharacterDataWrapper
import com.camachoyury.superheroes.data.model.character.Data
import com.camachoyury.superheroes.data.model.character.Result
import com.camachoyury.superheroes.data.repository.HeroesRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.net.URLEncoder
import javax.inject.Inject

/**
 * Created by Yury Camacho on 14/02/2018.
 */
class HeroesListPresenter @Inject constructor( val repository: HeroesRepository){

     lateinit var heroesView:HeroesView

    fun setView(heroesView: HeroesView){
        this.heroesView = heroesView
    }

    internal val disposables = CompositeDisposable()

    fun dispose() = disposables.dispose()

    fun searchHero(name:String) {
        heroesView.displayLoading()


        var result = repository.searchCharacter(URLEncoder.encode("ultron", "UTF-8"))

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse,this::handleError,this::completed)

        disposables.add(result)

        }


    fun handleResponse(list: CharacterDataWrapper){

        list.let {

            heroesView.displayHeroes(list)}
        if (list == null){
            heroesView.displayNoHeroes()
        }



    }
    fun handleError(throwable: Throwable){
        throwable.printStackTrace()
        heroesView.displayError()
        Log.d("Error",throwable.message)
    }
    fun completed(){
        Log.d("Completed","Completed")
        heroesView.hideLoading()
    }

    fun onDestroy(){
        disposables.clear()
    }
}
