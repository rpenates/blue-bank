package com.rpenates.bluebank.ui.transaction

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rpenates.bluebank.R

class TransactionActivity : AppCompatActivity() {

    private val viewModel: TransactionActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContentView(R.layout.activity_transaction)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val addTransactionBtn = findViewById<Button>(R.id.add_transaction)
        val inputDescription = findViewById<EditText>(R.id.input_description)
        val inputAmount = findViewById<EditText>(R.id.input_amount)

        addTransactionBtn.setOnClickListener {
            val description = inputDescription.text.toString()
            val amount = inputAmount.text.toString().toDouble()
            viewModel.addTransaction(description, amount)
            finish()
        }
    }
}