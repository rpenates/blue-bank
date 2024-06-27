package com.rpenates.bluebank

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.rpenates.bluebank.ui.adapters.TransactionItemAdapter
import com.rpenates.bluebank.ui.transaction.TransactionActivity

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private val transactionAdapter = TransactionItemAdapter()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val transactionBtn = findViewById<ExtendedFloatingActionButton>(R.id.add_transaction)
        val userBalanceText = findViewById<TextView>(R.id.user_balance_text)
        val userNameText = findViewById<TextView>(R.id.user_name_text)
        val transactionList = findViewById<RecyclerView>(R.id.transaction_list)

        viewModel.loadUser()

        viewModel.currentUser.observe(this) { user ->
            if (user != null) {
                userNameText.text = user!!.fullName
                userBalanceText.text = "$" + user.account?.balance.toString()
                transactionList.layoutManager = LinearLayoutManager(this)
                transactionAdapter.transactions = user.account?.transactions ?: emptyList()
                transactionList.adapter = transactionAdapter
            }
            transactionAdapter.notifyDataSetChanged()

        }

        transactionBtn.setOnClickListener {
            val intent = Intent(this, TransactionActivity::class.java)
            startActivity(intent)
        }
    }

}