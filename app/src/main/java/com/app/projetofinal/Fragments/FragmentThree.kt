package com.app.projetofinal.Fragments


import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.projetofinal.SecondActivity
import com.synnapps.carouselview.ViewListener
import kotlinx.android.synthetic.main.custom_para_carrossel.view.*
import kotlinx.android.synthetic.main.fragment_fragment_one.view.*
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.content.Context
import android.view.Window
import android.widget.Button
import com.app.projetofinal.R


class FragmentThree : androidx.fragment.app.Fragment() {

    val tema = arrayOf("Troika em Portugal", "Taxa Social única", "Crise migrantes","Lesados do BES","Alojamento Local","Crise Financeira em Portugal","Eutanásia","Incendios Florestais","Vítor Gastar","FMI","BCE","Ricardo Salgado","Moody's","BPN","Guerra na Siria","Crise Médio Oriente","Crise Coreia do Norte","Crise Crimeia","II Guerra Mundial")

    val tema2 = arrayOf("Marcelo Rebelo de Sousa", "António Costa", "Cavaco Silva","Mário Soares","José Sócrates","Pedro Passos Coelho","Catarina Martins","Jerónimo de Sousa","Rui Rio","Miguel Relvas","Paulo Portas","Santana Lopes")

    val tema3 = arrayOf("José Saramago", "Acordo Ortográfico", "Carlos Queiroz","Bruno de Carvalho","Cristiano Ronaldo","Jorge Jesus","Neymar e Barcelona","Iker CasilLas","José Mourinho")

    val temaSubtitulo = arrayOf("Economia","Economia","Economia","Justiça e Economia","Economia","Economia","Atualidade","Atualidade","Política e Economia","Economia","Economia","Economia","Economia","Justiça e Economia","Política Internacional","Política Internacional","Política Internacional","Política Internacional","História")

    val tema2Subtitulo = arrayOf("Política","Política","Política","Política","Política","Política","Política","Política","Política","Política","Política","Política")

    val tema3Subtitulo = arrayOf("Cultura","Cultura","Desporto","Desporto","Desporto","Desporto","Desporto","Desporto","Desporto")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(com.app.projetofinal.R.layout.fragment_fragment_one, container, false)


        var carousel_View = view.carouselView
        var carouse2_View = view.carouselView2
        var carouse3_View = view.carouselView3

        carouse2_View.pageCount = tema2.size/2
        carouse2_View.setViewListener(viewListener)

        carousel_View.pageCount = tema.size/2
        carousel_View.setViewListener(viewListenerTema2)


        carouse3_View.pageCount= tema3.size/2
        carouse3_View.setViewListener(viewListenerTema3)

        return view
    }



    val viewListener = object : ViewListener {
        override fun setViewForPosition(position: Int): View {
            var view = layoutInflater.inflate(R.layout.custom_para_carrossel, null)
            view.parte1.setOnClickListener {
                if(checkInternet()){
                    val aux = view.titulo_noticia1.text.toString()
                    val kotlinFragment = FragmentOne.newInstance(aux,1)

                    (activity as SecondActivity).replaceFragment(kotlinFragment)
                }
                else{
                    buttonnointernet(view)
                }


            }
            view.titulo_noticia1.text = tema2.get(position*2)
            view.tema_noticia1.text = tema2Subtitulo.get(position*2)

            view.parte2.setOnClickListener {

                if(checkInternet()){
                    val aux = view.titulo_noticia2.text.toString()
                    val kotlinFragment = FragmentOne.newInstance(aux,1)

                    (activity as SecondActivity).replaceFragment(kotlinFragment)
                }
                else{
                    buttonnointernet(view)
                }



            }

            view.titulo_noticia2.text = tema2.get(position*2+1)
            view.tema_noticia2.text = tema2Subtitulo.get(position*2+1)


            return view
        }
    }


    val viewListenerTema2 = object : ViewListener {
        override fun setViewForPosition(position: Int): View {
            var view = layoutInflater.inflate(R.layout.custom_para_carrossel, null)
            view.parte1.setOnClickListener {
                if(checkInternet()){
                    val aux = view.titulo_noticia1.text.toString()
                    val kotlinFragment = FragmentOne.newInstance(aux,1)

                    (activity as SecondActivity).replaceFragment(kotlinFragment)
                }
                else{
                    buttonnointernet(view)
                }

            }
            view.titulo_noticia1.text = tema.get(position*2)
            view.tema_noticia1.text = temaSubtitulo.get(position*2)

            view.parte2.setOnClickListener {
                if(checkInternet()){
                    val aux = view.titulo_noticia2.text.toString()
                    val kotlinFragment = FragmentOne.newInstance(aux,1)

                    (activity as SecondActivity).replaceFragment(kotlinFragment)
                }
                else{
                    buttonnointernet(view)
                }

            }

            view.titulo_noticia2.text = tema.get(position*2+1)
            view.tema_noticia2.text = temaSubtitulo.get(position*2+1)

            return view
        }
    }


    val viewListenerTema3 = object : ViewListener {
        override fun setViewForPosition(position: Int): View {
            var view = layoutInflater.inflate(R.layout.custom_para_carrossel, null)
            view.parte1.setOnClickListener {
                if(checkInternet()){
                    val aux = view.titulo_noticia1.text.toString()
                    val kotlinFragment = FragmentOne.newInstance(aux,1)

                    (activity as SecondActivity).replaceFragment(kotlinFragment)
                }
                else{
                    buttonnointernet(view)
                }

            }
            view.titulo_noticia1.text = tema3.get(position*2)
            view.tema_noticia1.text = tema3Subtitulo.get(position*2)

            view.parte2.setOnClickListener {
                if(checkInternet()){
                    val aux = view.titulo_noticia2.text.toString()
                    val kotlinFragment = FragmentOne.newInstance(aux,1)

                    (activity as SecondActivity).replaceFragment(kotlinFragment)
                }
                else{
                    buttonnointernet(view)
                }

            }

            view.titulo_noticia2.text = tema3.get(position*2+1)
            view.tema_noticia2.text = tema3Subtitulo.get(position*2+1)

            return view
        }
    }







    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



    }

    fun checkInternet() : Boolean {
        val manager = activity?.applicationContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = manager.activeNetworkInfo
        if (null != activeNetwork) {
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                return true
            }
            if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                return true
            }
            return false
        } else {
            return false
        }
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




