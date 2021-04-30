package ar.com.pablocaamano.veterinaria

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import ar.com.pablocaamano.veterinaria.model.Animal

class DiagnosticActivity : AppCompatActivity() {

    lateinit var name: TextView;
    lateinit var type: TextView;
    lateinit var raze: TextView;
    lateinit var age:TextView;
    lateinit var cause: TextView;
    lateinit var diagnotic: TextView;

    lateinit var confirm: Button;
    lateinit var cancel: Button;

    lateinit var p: Animal;

    private val PET_KEY: String = "pet";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostic);

        initializeElems();

        if(intent.getSerializableExtra(PET_KEY) != null) {
            this.p = intent.getSerializableExtra(PET_KEY) as Animal;
            Toast.makeText(this, p.name.toString(), Toast.LENGTH_LONG).show();
            this.name.text = p.name;
            this.type.text = p.type;
            this.raze.text = p.raze;
            this.age.text = p.age.toString();
            this.cause.text = p.cause;
        }

        confirm.setOnClickListener(View.OnClickListener {
            p.diagnostic = this.diagnotic.text.toString();
            this.goToActivity(this,MainActivity::class.java,p);
        });

        cancel.setOnClickListener(View.OnClickListener {
           this.goToActivity(this,MainActivity::class.java);
        });
    }

    private fun initializeElems() {
        this.name = findViewById(R.id.diagNameVal);
        this.type = findViewById(R.id.diagTypeVal);
        this.raze = findViewById(R.id.diagRazeVal);
        this.age = findViewById(R.id.diagAgeVal);
        this.cause = findViewById(R.id.diagCauseVal);
        this.diagnotic = findViewById(R.id.diagResultVal);

        this.confirm = findViewById(R.id.diagConfirmBtn);
        this.cancel = findViewById(R.id.diagCancelBtn);
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
            intent.putExtra(PET_KEY, petParam);
        }
        startActivity(intent);
    }
}