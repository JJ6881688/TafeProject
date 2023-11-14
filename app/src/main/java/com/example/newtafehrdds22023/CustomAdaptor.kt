package com.example.newtafehrdds22023

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdaptor(private val context:Context,  arrayList:ArrayList<Person>)
    :BaseAdapter() {
    private val personList = arrayList
    private lateinit var tvName:TextView
    private lateinit var tvAddress:TextView
    private lateinit var tvMobile:TextView
    private lateinit var tvEmail:TextView
    private lateinit var ivImageFile:ImageView
    override fun getCount(): Int {
        return personList.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position:Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, p1: View?, parent: ViewGroup?): View {
        val cView = LayoutInflater.from(context).inflate(R.layout.rowfile,parent, false)
        tvName = cView.findViewById(R.id.tvName)
        tvAddress = cView.findViewById(R.id.tvAddress)
        tvEmail = cView.findViewById(R.id.tvEmail)
        tvMobile = cView.findViewById(R.id.tvMobile)
        ivImageFile = cView.findViewById(R.id.ivImageFile)
        tvName.text = personList[position].name
        tvAddress.text = personList[position].address
        tvMobile.text = personList[position].mobile
        tvEmail.text = personList[position].email
        ivImageFile.setImageResource(context.resources.getIdentifier(personList[position].imageFile,"drawable" ,"com.example.newtafehrdds22023"))
        return cView
    }
}











