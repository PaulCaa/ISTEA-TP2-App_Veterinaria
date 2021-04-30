package ar.com.pablocaamano.veterinaria

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import ar.com.pablocaamano.veterinaria.model.Animal
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var btIngress: Button;
    lateinit var btnResult: Button;

    lateinit var pets: List<Animal>;
    lateinit var p: Animal;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeElems();

        btIngress.setOnClickListener(View.OnClickListener {
            if(this.pets.size < 2) this.goToActivity(this,RegisterActivity::class.java);
            else Toast.makeText(this, "Se alcanzó el máximo de mascotas registradas", Toast.LENGTH_LONG).show();
        });

        if(intent.getSerializableExtra("pet") != null) {
            this.p = intent.getSerializableExtra("pet") as Animal;
            this.pets += p;
        }

        btnResult.setOnClickListener(View.OnClickListener {
            this.goToActivity(this, DiagnosticActivity::class.java, p);
        });
    }


    private fun initializeElems(){
        this.btIngress = findViewById(R.id.btIngress);
        this.btnResult = findViewById(R.id.btnRegDiagnostic);
        this.pets = listOf();
    }

    private fun <T>goToActivity(context: Context, view: Class<T>) {
        this.goToActivity(context,view,null);
    }

    private fun <T>goToActivity(context: Context, view: Class<T>, petParam: Animal?) {
        val intent: Intent = Intent(context,view);
        if(petParam != null) {
            intent.putExtra("pet", petParam);
        }
        startActivity(intent);
    }
}