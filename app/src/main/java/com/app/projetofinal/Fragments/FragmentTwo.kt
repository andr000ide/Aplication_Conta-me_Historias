package com.app.projetofinal.Fragments


import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import com.app.projetofinal.CustomDropDownAdapter
import com.app.projetofinal.SecondActivity
import kotlinx.android.synthetic.main.pesquisar.view.*
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.core.content.ContextCompat.getSystemService
import com.app.projetofinal.R


class FragmentTwo : androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.pesquisar, container, false)

        view.constraintclick.setOnFocusChangeListener { _, _ ->
            run {
                view.constraintclick.hideKeyboard()
                view.imagePesquisa.setImageResource(R.drawable.ic_search_blue_24dp)
            }
        }


        view.constraintclick.setOnClickListener {
            // view.constraintclick.hideKeyboard()
        }

        view.searchbar.setOnFocusChangeListener { _, _ ->  view.imagePesquisa.setImageResource(R.drawable.ic_search_black_24dp) }


        view.searchbar.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val aux = view.spinner1.selectedItem
                view.searchbar.hideKeyboard()
                view.constraintclick.requestFocus()


                //Perform Code
                return@OnKeyListener true
            }
            false
        })


        val listItemsTxt = arrayOf(getString(R.string.five_years), getString(R.string.ten_years), getString(R.string.fifteen_years), getString(
                    R.string.twenty_years))
        val spinnerAdapter = CustomDropDownAdapter(context!!, listItemsTxt)
        view.spinner1.adapter = spinnerAdapter
        //(view.spinner1.adapter as CustomDropDownAdapter).setSelection(1)
        view.spinner1.setSelection(1)


        view.advancedoptions.setOnClickListener {
            if (view.linear_visibility.visibility == View.INVISIBLE) {
                view.advancedoptions.setTextColor(Color.GRAY)
                view.linear_visibility.visibility = View.VISIBLE
            } else {
                view.advancedoptions.setTextColor(Color.WHITE)
                view.linear_visibility.visibility = View.INVISIBLE
            }
        }

        view.imagePesquisa.setOnClickListener {


            val manager = activity?.applicationContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = manager.activeNetworkInfo
            if (null != activeNetwork) {
                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                    var aux = view.searchbar.text.toString()

                    val result = aux.trim()

                    var aux3 = view.spinner1.selectedItemPosition
                    //var aux2 = listItemsTxt.get(view.spinner1.selectedItemPosition)
                    view.imagePesquisa.hideKeyboard()

                    if(result.isNotEmpty()){
                        val kotlinFragment = FragmentOne.newInstance(result,aux3)

                        (activity as SecondActivity).replaceFragment(kotlinFragment)
                    }
                    else{
                        buttonerrorempty(view)
                    }
                }
                if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                    var aux = view.searchbar.text.toString()

                    val result = aux.trim()

                    var aux3 = view.spinner1.selectedItemPosition
                    //var aux2 = listItemsTxt.get(view.spinner1.selectedItemPosition)
                    view.imagePesquisa.hideKeyboard()

                    if(result.isNotEmpty()){
                        val kotlinFragment = FragmentOne.newInstance(result,aux3)

                        (activity as SecondActivity).replaceFragment(kotlinFragment)
                    }
                    else{
                        buttonerrorempty(view)
                    }
                }
            } else {
                buttonnointernet(view)
            }
        }
        return view
    }


    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    fun buttonerrorempty(view: View) {

        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_mensagem_pesquisar_vazio)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)

        val button = dialog.findViewById(R.id.buttonOk) as Button

        button.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    fun buttonnointernet(view: View) {

        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_error_no_internet)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)

        val button = dialog.findViewById(R.id.buttonOk) as Button

        button.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

}
