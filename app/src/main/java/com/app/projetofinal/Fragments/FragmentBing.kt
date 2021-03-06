package com.app.projetofinal.Fragments

import com.app.projetofinal.modelclass.Ingles.BingResponse



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.projetofinal.R
import com.app.projetofinal.ViewPagerAdapterNarrativas
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.frag_teste1.*
import kotlinx.android.synthetic.main.frag_teste1.view.*


class FragmentBing : androidx.fragment.app.Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.frag_teste1, container, false)

        var jsonarray = arguments?.getString("tudo")
        var query = arguments?.getString("query")


        var gson = Gson()

        val turnsType = object : TypeToken<BingResponse>() {}.type
        var testModel = gson.fromJson<BingResponse>(jsonarray, turnsType)



        val adapter = ViewPagerAdapterNarrativas(childFragmentManager)

        val emptyList = mutableListOf<String>()

        var jsonDomains = gson.toJson(testModel.domains)


        for(item in testModel.results!!){
            var jsonString = gson.toJson(item.keyphrases)
            var fragmento = FragmentVistaNarrativasBing.newInstance(jsonString,item.to,item.from,query!!,jsonDomains)
            emptyList.add(convertedata(item.from))
            adapter.addFragment(fragmento)
        }


//        for(item in testModel){
//            var jsonString = gson.toJson(item.headlines)
//            var fragmento = FragmentVistaNarrativas.newInstance(jsonString,item.date_interval_end,item.date_interval_begin,
//                query!!, jsonarrayDomains!!
//            )
//            emptyList.add(convertedata(item.date_interval_begin))
//            adapter.addFragment(fragmento)
//        }
        view.viewpagernarr.adapter = adapter
        //val a = listOf<String>("03/17","23/18","05/19","06/19","06/19")
        view.step_view.setSteps(emptyList)
        view.step_view.selectedStep(0)

        view.viewpagernarr.addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                var aux = view.viewpagernarr.currentItem
                var tam = view.viewpagernarr.adapter?.count



                if(aux==0){
                    step_view.selectedStep(0)
                }else{
                    step_view.selectedStep(aux+1)
                }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
            }
        })

        return view

    }

    fun convertedata(data: String):String{
        val strs = data.split(" ").toTypedArray()

        val aux = strs.get(0)
        //val aux2= aux.takeLast(2)
        return  aux
    }

    companion object {
        fun newInstance(tudo : String, query : String): FragmentBing {
            val args = Bundle()
            args.putString("tudo",tudo)
            args.putString("query",query)
            val fragment = FragmentBing()
            fragment.arguments = args
            return fragment
        }
    }

}
