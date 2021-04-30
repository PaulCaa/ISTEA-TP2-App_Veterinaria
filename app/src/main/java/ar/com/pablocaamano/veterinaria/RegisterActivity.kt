package ar.com.pablocaamano.veterinaria

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import ar.com.pablocaamano.veterinaria.model.Animal

class RegisterActivity : AppCompatActivity() {

    lateinit var nameText: TextView;
    lateinit var typeOpt: Spinner;
    lateinit var razeText: TextView;
    lateinit var ageNum: TextView;
    lateinit var descripText: TextView;
    lateinit var regBtn: Button;
    lateinit var cancelBtn: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initializeElems();

        regBtn.setOnClickListener(View.OnClickListener {
            registerAction();
        });

        cancelBtn.setOnClickListener(View.OnClickListener {
            this.goToActivity(this, MainActivity::class.java);
        })
    }

    /**
     * Inicializar elementos de la vista
     */
    private fun initializeElems() {
        this.nameText = findViewById(R.id.regNombre);
        this.typeOpt = findViewById(R.id.regTypeOpt);

        this.typeOpt.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.types_animals)
        );

        this.razeText = findViewById(R.id.regRaze);
        this.ageNum = findViewById(R.id.regAge);
        this.descripText = findViewById(R.id.regDescrip);
        this.regBtn = findViewById(R.id.regBtnConfirm);
        this.cancelBtn = findViewById(R.id.regBtnCancel);
    }

    /**
     * Se validan cantidad de mascotas registradas y se leen datos ingresados o se avisa que llego al limite
     */
    private fun registerAction() {
        var typeSelected: String = "";
        this.typeOpt.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                typeSelected = typeOpt.adapter.getItem(position) as String;
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        var name: String = "";
        if(this.nameText.text != null) name = this.nameText.text.toString();
        var raze: String = "";
        if(this.razeText.text != null) raze = this.razeText.text.toString();
        var age: Int = 0;
        if(this.ageNum.text != null) age = this.ageNum.text.toString().toInt();
        var desc: String = "";
        if(this.descripText.text != null) desc = this.descripText.text.toString();

        this.goToActivity(this, MainActivity::class.java,Animal(name,typeSelected,raze,age,desc,""));
    }


    /**
     * Metodo para navegar a otra activity
     */
    private fun <T>goToActivity(context: Context, view: Class<T>) {
        this.goToActivity(context,view,null);
    }

    /**
     * Metodo para navegar a otra activity con params
     */
    private fun <T>goToActivity(context: Context, view: Class<T>, petParam: Animal?) {
        val intent: Intent = Intent(context,view);
        if(petParam != null) {
            intent.putExtra("pet", petParam);
        }
        startActivity(intent);
    }
}