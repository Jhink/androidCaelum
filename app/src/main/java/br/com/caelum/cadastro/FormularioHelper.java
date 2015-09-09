package br.com.caelum.cadastro;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RatingBar;

import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android5497 on 02/09/15.
 */
public class FormularioHelper {
    private Aluno aluno;

    private EditText nome;
    private EditText telefone;
    private EditText site;
    private RatingBar nota;
    private EditText endereco;
    private ImageView foto;

    private String caminhoFoto;
    private Button fotoButton;

    public FormularioHelper(FormularioActivity activity){
       this.aluno = new Aluno();

       foto = (ImageView) activity.findViewById(R.id.formulario_foto);
       fotoButton = (Button) activity.findViewById(R.id.formulario_foto_button);

       this.nome = (EditText) activity.findViewById(R.id.formulario_name);
       this.telefone = (EditText) activity.findViewById(R.id.formulario_telephone);
       this.site = (EditText) activity.findViewById(R.id.formulario_site);
       this.nota = (RatingBar) activity.findViewById(R.id.formulario_nota);
       this.endereco = (EditText) activity.findViewById(R.id.formulario_address);
    }

    public Button getFotoButton(){
        return fotoButton;
    }

    public void carregaImagem(String localArquivoFoto){
        Bitmap imagemFoto = BitmapFactory.decodeFile(localArquivoFoto);
        Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap(imagemFoto, imagemFoto.getWidth(), 300, true);

        foto.setImageBitmap(imagemFotoReduzida);
        foto.setTag(localArquivoFoto);
        foto.setScaleType(ScaleType.FIT_XY);
    }


    public Aluno pegaAlunoDoFormulario() {

        aluno.setNome(nome.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setNota(Double.valueOf(nota.getProgress()));
        aluno.setCaminhoFoto( (String) foto.getTag());

        return aluno;
    }

    public boolean temNome(){
        return !nome.getText().toString().isEmpty();
    }

    public void mostraErro(){
        nome.setError("Campo nome náo pode ser vazio!");
    }

    public void preencheFormulario(Aluno aluno){

        nome.setText(aluno.getNome());
        endereco.setText(aluno.getEndereco());
        site.setText(aluno.getSite());
        telefone.setText(aluno.getTelefone());
        nota.setProgress(aluno.getNota().intValue());
        carregaImagem(aluno.getCaminhoFoto());

        this.aluno = aluno;

    }
}
