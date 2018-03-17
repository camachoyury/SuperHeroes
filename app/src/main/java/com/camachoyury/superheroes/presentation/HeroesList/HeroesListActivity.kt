package com.camachoyury.superheroes.presentation.HeroesList

import android.app.Application
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.camachoyury.superheroes.HeroesApp
import com.camachoyury.superheroes.R
import com.camachoyury.superheroes.data.model.character.CharacterDataWrapper
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.*


class HeroesListActivity : AppCompatActivity(),HeroesView {

    @Inject
    lateinit var presenter : HeroesListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as HeroesApp).appComponent.inject(this)
        presenter.setView(this)


    }


    fun search(view: View){

        var name = this.et_name.text.toString()
        presenter.searchHero(name)
    }
    override fun displayHeroes(characterDataWrapper: CharacterDataWrapper) {
        tv_result.visibility = View.VISIBLE
        tv_result.text = characterDataWrapper.data.results.toString()
    }


    override fun displayNoHeroes() {
        tv_result.visibility = View.VISIBLE
        tv_result.text = "No heroes"
    }

    override fun displayLoading() {
        this.progressBar.visibility = View.VISIBLE
    }

    override fun displayError() {
        this.tv_result.text = "Ocurrio un error"

    }

    override fun hideLoading() {
        this.progressBar.visibility = View.INVISIBLE
    }

}
