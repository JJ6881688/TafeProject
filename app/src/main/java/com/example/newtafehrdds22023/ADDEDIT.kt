package com.example.newtafehrdds22023

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast

import com.example.newtafehrdds22023.databinding.AddeditBinding

class ADDEDIT :Activity(), View.OnClickListener {
    private lateinit var binding: AddeditBinding
    val dbh = Dbhandler(this,null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddeditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSave.setOnClickListener(this)
        binding.btnCancel.setOnClickListener(this)
        val extras = intent.extras
        if(extras!=null) {
            val id: Int = extras.getInt("ID")
            val person = dbh.getPerson(id)
            binding.etID.setText(person.id.toString())
            binding.etName.setText(person.name)
            binding.etImageFile.setText(person.imageFile)
            binding.etAddress.setText(person.address)
            binding.etMobile.setText(person.mobile)
            binding.etEmail.setText(person.email)
            binding.ivImageFile.setImageResource(
                this.resources.getIdentifier(person.imageFile, "drawable", "com.example.newtafehrdds22023"))
        }
    }


    override fun onClick(btn:View) {
        when(btn.id){
            R.id.btnSave->{
                val cid:Int = binding.etID.text.toString().toIntOrNull()?:0
                if(cid==0) {
                    addPerson()
                }else{
                    editPerson(cid)
                }
            }
            R.id.btnCancel->{
                goBack()

            }
        }
    }

    private fun goBack() {
        val mainAct = Intent(this,MainActivity::class.java)
        startActivity(mainAct)
    }

    private fun editPerson(cid: Int) {
        val person = dbh.getPerson(cid)
        person.name = binding.etName.text.toString()
        person.address = binding.etAddress.text.toString()
        person.email = binding.etEmail.text.toString()
        person.imageFile = binding.etImageFile.text.toString()
        person.mobile = binding.etMobile.text.toString()
        dbh.updatePerson(person)
        Toast.makeText(this,"Person has been updated", Toast.LENGTH_LONG).show()
        goBack()

    }

    private fun addPerson() {
        val person = Person()
        person.name = binding.etName.text.toString()
        person.address = binding.etAddress.text.toString()
        person.email = binding.etEmail.text.toString()
        person.imageFile = binding.etImageFile.text.toString()
        person.mobile = binding.etMobile.text.toString()



    }

}
