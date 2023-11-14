package com.example.newtafehrdds22023

import android.app.Activity
import android.content.Intent

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.Toast
import com.example.newtafehrdds22023.databinding.ActivityMainBinding

class MainActivity : Activity() {
    private lateinit var binding:ActivityMainBinding
    private val menuAdd = Menu.FIRST+1
    private val menuEdit = Menu.FIRST+2
    private val menuDelete= Menu.FIRST+3
    private var personList:MutableList<Person> = arrayListOf()
    private var idList:MutableList<Int> = arrayListOf()
    private val dbHandler = Dbhandler(this,null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerForContextMenu(binding.lstView)
        initAdapter()
    }

    private fun initAdapter() {
        try{
            personList.clear()
            idList.clear()
            for (person:Person in dbHandler.getAllPersons()){
                personList.add(person)
                idList.add(person.id)
            }
            val adp = CustomAdaptor(this, personList as ArrayList<Person>)
            binding.lstView.adapter = adp
        }catch(e:Exception){
            Toast.makeText(this, "Problem:${e.message.toString()}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(Menu.NONE,menuAdd,Menu.NONE,"ADD")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val goAddEdit = Intent(this, ADDEDIT::class.java)
        startActivity(goAddEdit)
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View?,
        menuInfo:ContextMenu.ContextMenuInfo?
    ){
        menu.add(Menu.NONE,menuEdit,Menu.NONE,"EDIT")
        menu.add(Menu.NONE,menuDelete,Menu.NONE,"DELETE")
        super.onCreateContextMenu(menu, v, menuInfo)

    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info:AdapterContextMenuInfo = item.menuInfo as AdapterContextMenuInfo
                when(item.itemId){
                    menuEdit-> {


                        val addEdit = Intent(this, ADDEDIT::class.java)
                        addEdit.putExtra("ID", idList[info.position])
                        startActivity(addEdit)
                    }
                    menuDelete->{
                        dbHandler.deletePerson(idList[info.position])
                        initAdapter()
                    }
                }
        return super.onContextItemSelected(item)
    }

}