package br.com.caelum.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;


public class FormularioActivity extends ActionBarActivity {

    private FormularioHelper helper;

    public static final String ALUNO_SELECIONADO = "alunoSelecionado";

    private String localArquivoFoto;
    private static final int TIRA_FOTO = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        this.helper = new FormularioHelper(this);

        Button botaoSalvar = (Button) findViewById(R.id.formulario_botao);
        botaoSalvar.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                Toast.makeText(FormularioActivity.this, "Usuário Adicionado!", Toast.LENGTH_LONG).show();

                finish();
            }

        });

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra(ALUNO_SELECIONADO);
        if(aluno != null){
            helper.preencheFormulario(aluno);
        }

        Button foto = helper.getFotoButton();
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localArquivoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                Uri localFoto = Uri.fromFile(new File(localArquivoFoto));
                Intent irParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);

                startActivityForResult(irParaCamera, TIRA_FOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == TIRA_FOTO){
            if(resultCode == Activity.RESULT_OK) {
                helper.carregaImagem(this.localArquivoFoto);
            }else{
                this.localArquivoFoto = null;
            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.menu_formulario_ok:

                Aluno aluno = helper.pegaAlunoDoFormulario();
                AlunoDAO dao = new AlunoDAO(FormularioActivity.this);

                if (aluno.getId() == null){
                    dao.insere(aluno);
                }else{
                    dao.alterar(aluno);
                }
                dao.close();
                finish();
                return false;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
