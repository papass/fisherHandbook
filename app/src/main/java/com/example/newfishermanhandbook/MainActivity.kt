package com.example.newfishermanhandbook

import MyAdapter
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    var adapter: MyAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nav_view = findViewById<NavigationView>(R.id.nav_view)
        val rcView = findViewById<RecyclerView>(R.id.rcView)
        nav_view.setNavigationItemSelectedListener (this)

        val list = ArrayList<ListItem>()

        list.addAll(fillArras(resources.getStringArray(R.array.fish),
            resources.getStringArray(R.array.fish_content),getImageId(R.array.fish_image_array)))
        rcView.hasFixedSize()
        rcView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(list,this)
        rcView.adapter = adapter


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.id_fish ->
            {
                Toast.makeText(this,"Id fish",Toast.LENGTH_SHORT).show()
                adapter?.updateAdapter(fillArras(resources.getStringArray(R.array.fish),
                    resources.getStringArray(R.array.fish_content),getImageId(R.array.fish_image_array)))

            }
            R.id.id_na ->
            {
                Toast.makeText(this,"Id na",Toast.LENGTH_SHORT).show()
                adapter?.updateAdapter(fillArras(resources.getStringArray(R.array.na),
                    resources.getStringArray(R.array.na_content),getImageId(R.array.na_image_array)))

            }
            R.id.id_sna -> Toast.makeText(this,"Id sna",Toast.LENGTH_SHORT).show()
            R.id.id_history -> Toast.makeText(this,"Id history",Toast.LENGTH_SHORT).show()
        }
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerlayout)
        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }
    private fun fillArras(titleArray:Array<String>, contentArray:Array<String>, imageArray:IntArray):List<ListItem>
    {
        val listItemArray = ArrayList<ListItem>()
        for(n in titleArray.indices)
        {
            val listItem = ListItem(imageArray[n],titleArray[n],contentArray[n])
            listItemArray.add(listItem)
        }
        return listItemArray
    }

    private fun getImageId(imageArrayId:Int):IntArray
    {
        val tArray: TypedArray = resources.obtainTypedArray(imageArrayId)
        val count = tArray.length()
        val ids = IntArray(count)
        for(i in ids.indices)
        {
            ids[i] = tArray.getResourceId(i,0)
        }
        tArray.recycle()
        return ids
    }

}