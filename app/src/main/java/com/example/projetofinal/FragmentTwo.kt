package com.example.projetofinal


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.Spinner
import kotlinx.android.synthetic.main.pesquisar.view.*


class FragmentTwo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.pesquisar, container, false)

        view.constraintclick.setOnFocusChangeListener { v, hasFocus -> view.constraintclick.hideKeyboard() }

        view.constraintclick.setOnClickListener {
            // view.constraintclick.hideKeyboard()
        }
        view.searchbar.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                println("iedije")
                val aux = view.spinner1.selectedItem
                println(aux)
                view.searchbar.hideKeyboard()
                view.constraintclick.requestFocus()
                //Perform Code
                return@OnKeyListener true
            }
            false
        })


        val listItemsTxt = arrayOf("Last 5 years", "Last 10 years", "Last 15 years", "Last 20 years")

        var spinnerAdapter: CustomDropDownAdapter = CustomDropDownAdapter(context!!, listItemsTxt)
        var spinner: Spinner = view.findViewById(R.id.spinner1) as Spinner
        spinner?.adapter = spinnerAdapter
        spinner.setSelection(1)


        spinner?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                println("oedodeo")
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                //parent.getChildAt(position).setBackgroundColor(Color.BLACK)
                //parent.getChildAt(position).setBackgroundColor(0x00000000)
                println("ee")
                //((TextView) parent.getChildAt(0)).setTextColor(0x00000000);
            }
        })


        /*
        //get the spinner from the xml.
        val dropdown = view.spinner1
        //create a list of items for the spinner.
        //val items = arrayListOf<String>("5","10","15","20")
        val items = arrayOf("5", "10", "15","20")
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        //val adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item, items)
    //set the spinners adapter to the previously created one.
        //dropdown.setAdapter(adapter)
        dropdown.setSelection(1)

    */


        view.advancedoptions.setOnClickListener {
            if (view.linear_visibility.visibility == View.INVISIBLE) {
                view.advancedoptions.setTextColor(Color.GRAY)
                view.linear_visibility.visibility = View.VISIBLE
            } else {
                view.advancedoptions.setTextColor(Color.WHITE)
                view.linear_visibility.visibility = View.INVISIBLE
            }
        }


        return view
    }


    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }


}
