package com.app.projetofinal.Fragments



import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.projetofinal.*
import com.app.projetofinal.modelclass.Example_Yake
import com.app.projetofinal.modelclass.Ingles.BingResponse
import com.app.projetofinal.modelclass.Wordcloud
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_layout_wordcloud.*
import kotlinx.android.synthetic.main.fragment_layout_wordcloud.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread


class FragmentWCBing : androidx.fragment.app.Fragment() {

    lateinit var call2: Call<Example_Yake>
    lateinit var call3: Call<Wordcloud>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(com.app.projetofinal.R.layout.fragment_layout_wordcloud, container, false)
        //view.textowordcloud.visibility=View.VISIBLE
        view.wordCloud.visibility=View.VISIBLE

        var jsonarray = arguments?.getString("timeline")
        var gson = Gson()
        val turnsType = object : TypeToken<BingResponse>() {}.type
        var testModel = gson.fromJson<BingResponse>(jsonarray, turnsType)

        var pesquisa = arguments?.getString("pesquisa")
        var queryPesquisa = pesquisa?.replace("\\s".toRegex(),"+")






        thread(start = true) {
            var teste : String = ""
            for(item in testModel.results!!){
                for(item2 in item.keyphrases){
                    teste+=item2.docs!!.get(0)+" "
                }
            }


            val array = queryPesquisa?.split("+")?.toTypedArray()

            array?.let {
                for (item in it){
                    teste=teste.toLowerCase()
                    teste =teste.replace(item.toLowerCase(),"")
                }
            }


            val service2 = RetrofitClientInstance_Keywords.retrofitInstance?.create(ServiceAPI::class.java)
            call2 = service2!!.search_words(teste,"1","20")
            call2?.enqueue(object : Callback<Example_Yake> {

                override fun onResponse(call: Call<Example_Yake>, response: Response<Example_Yake>) {
                    val gson = Gson()
                    val service3 = RetrofitWordCloudInstance.retrofitInstance?.create(ServiceAPI::class.java)

                    var width = resources.displayMetrics.widthPixels
                    var height = resources.displayMetrics.heightPixels
                    height=height-300
//                    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
//                    if (resourceId > 0) {
//                        val navigationBarHeight = resources.getDimensionPixelSize(resourceId)
//                    }



                    call3 = service3!!.search_cloud(width.toString()  ,height.toString(),gson.toJson(response.body()))
                    //println(call3.toString())
                    call3!!.enqueue(object : Callback<Wordcloud> {


                        override fun onResponse(call: Call<Wordcloud>, response: Response<Wordcloud>) {
                            val outronome = response.body()

                            //val decodedstring = Base64.getDecoder().decode(outronome?.wordcloudb64)
                            val decodedstring = android.util.Base64.decode(outronome?.wordcloudb64, android.util.Base64.DEFAULT)
                            val decodedByte = BitmapFactory.decodeByteArray(decodedstring,0,decodedstring.size)
                            val atividade = activity as SecondActivity
                            //atividade.imagemtestar.setImageBitmap(decodedByte)
                            view.wordCloud.setImageBitmap(decodedByte)
                            //view.textowordcloud.visibility=View.GONE
                            view.wordCloud.visibility=View.VISIBLE

                        }
                        override fun onFailure(call: Call<Wordcloud>, t: Throwable) {
                            println("onerror")
                        }
                    })
                }

                override fun onFailure(call: Call<Example_Yake>, t: Throwable) {
                }
            })
        }



        //val activity = activity as SecondActivity



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val activity = activity as SecondActivity
        wordCloud.setImageBitmap(activity.getImagem())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if(::call2.isInitialized){

            call2.cancel()
        }

        if(::call3.isInitialized){
            call3.cancel()
        }

    }

    fun outroServico(algo2 : String){

        //println(algo2)

    }

    companion object {
        fun newInstance(jsonString: String,pesquisa : String ): FragmentWCBing {
            val args = Bundle()
            args.putString("timeline",jsonString)
            args.putString("pesquisa",pesquisa)
            val fragment = FragmentWCBing()
            fragment.arguments = args
            return fragment
        }
    }
}
