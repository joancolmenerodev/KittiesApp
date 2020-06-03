package com.joancolmenerodev.kitties.feature.kitties_list.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.joancolmenerodev.kitties.R
import com.joancolmenerodev.kitties.feature.kitties_list.domain.models.Kittie
import com.joancolmenerodev.kitties.feature.kitties_detail.KittyDetailActivity
import com.joancolmenerodev.kitties.feature.kitties_list.presentation.adapter.KittiesListAdapter
import com.ybs.countrypicker.CountryPicker
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_kitties_list.*
import javax.inject.Inject


class KittiesListActivity : AppCompatActivity(), KittiesListContract.View {

    private lateinit var adapter: KittiesListAdapter

    @Inject
    lateinit var presenter: KittiesListContract.Presenter

    private var filter: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kitties_list)
        setupRecyclerView()
        initOnClickListeners()
        presenter.onViewReady(this)
    }

    private fun inject() {
        AndroidInjection.inject(this)
    }

    private fun setupRecyclerView() {
        adapter = KittiesListAdapter {
            presenter.onKittieClicked(it)
        }
        rv_breed_kitties.layoutManager = LinearLayoutManager(this)
        rv_breed_kitties.adapter = adapter
    }

    private fun initOnClickListeners() {
        fab_filter_by_country.setOnClickListener {
            if (fab_filter_by_country.drawable.constantState == ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_filter_list_white_24dp
                )?.constantState
            ) {
                val picker =
                    CountryPicker.newInstance(getString(R.string.select_country_dialog_title))

                picker.setListener { _, code, _, _ ->
                    filter = false
                    updateFabIcon()
                    presenter.filterList(code)
                    picker.dialog?.dismiss()
                }
                picker.show(supportFragmentManager, getString(R.string.country_picker_dialog_text))
            } else {
                filter = true
                updateFabIcon()
                presenter.showRealList()
            }

        }

    }

    private fun updateFabIcon() {
        if (!filter) {
            fab_filter_by_country.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_clear_white_24dp
                )
            )

        } else {
            fab_filter_by_country.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_filter_list_white_24dp
                )
            )
        }
    }

    override fun noKittiesWithThatCountryCode() {
        Toast.makeText(this, getString(R.string.no_cat_found_by_country_code), Toast.LENGTH_SHORT)
            .show()
    }

    override fun showProgress(visible: Boolean) {
        progress_kitties_list.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun fillList(kittiesList: List<Kittie>) {
        fab_filter_by_country.visibility = View.VISIBLE
        adapter.addItems(kittiesList)
    }

    override fun showKittiesNotFound() {
        Snackbar.make(
            findViewById(android.R.id.content),
            getString(R.string.no_kitties_found),
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun showServiceUnavailable() {
        Snackbar.make(
            findViewById(android.R.id.content),
            getString(R.string.service_unavailable_kitties_list),
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun navigateToDetail(kittie: Kittie) {
        startActivity(
            KittyDetailActivity.getIntent(
                this,
                kittie
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}
