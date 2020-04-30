package com.nebiyu.nutrition

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    var list = ArrayList<DataModel>()
    var listSearch = ArrayList<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView_main.layoutManager = LinearLayoutManager(this)

        val name = "https://wger.de/api/v2/ingredient/?format=json"


        getJson(name)



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.search_bar, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search_bar)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("",false)
                searchItem.collapseActionView()
                Toast.makeText(this@MainActivity,"looking for $query", Toast.LENGTH_SHORT).show()
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText!!.isNotEmpty()){
                    search(newText)
                    recyclerView_main.adapter = MainAdapter(listSearch)
                }
                else{
                    reset()
                    recyclerView_main.adapter = MainAdapter(listSearch)
                }
                return true
            }
        })

        return true
    }

    fun search(newText:String){
        listSearch.clear()
        val search = newText.toLowerCase()
        list.forEach {
            if (it.name?.toLowerCase()!!.contains(search)){
                listSearch.add(it)

            }
        }
    }

    fun reset(){
        recyclerView_main.adapter = MainAdapter(list)
    }

    fun getJson(name:String){


        val url = name

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()



        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("fail")
            }

            override fun onResponse(call: Call, response: Response) {

                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()


                val homeFeed = gson.fromJson(body, HomeFeed :: class.java)





                for (elm in homeFeed.results) {

                    list.add(DataModel(elm.id, elm.name, "Energy: " + elm.energy + " J",
                        "Protein: "+ elm.protein + " g",
                        "Carbohydrates: " + elm.carbohydrates + " g",
                        "Sugar: "+ elm.carbohydrates_sugar + " g",
                        "Fat: " + elm.fat + "g", "Saturated Fat: " + elm.fat_saturated + " g",
                        "Dietary Fiber: " + elm.fibres + " g", "Sodium: " + elm.sodium + " g"))

                }


                if (homeFeed.next != "https://wger.de/api/v2/ingredient/?format=json&page=100"){
                    getJson(homeFeed.next)
                }



                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(list)
                }





            }


        })


    }
}
