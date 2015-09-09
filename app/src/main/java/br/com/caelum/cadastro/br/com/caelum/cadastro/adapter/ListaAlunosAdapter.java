package br.com.caelum.cadastro.br.com.caelum.cadastro.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.caelum.cadastro.FormularioHelper;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android5497 on 08/09/15.
 */
public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos;
    private final Activity activity;

    public ListaAlunosAdapter(Activity activity, List<Aluno> alunos) {
        this.activity = activity;
        this.alunos = alunos;
    }


    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = activity.getLayoutInflater().inflate(R.layout.item, parent, false);

        if(position % 2 == 0){
            view.setBackgroundColor(activity.getResources().getColor(R.color.linha_par));
        }else{
            view.setBackgroundColor(activity.getResources().getColor(R.color.linha_impar));
        }

        TextView nome = (TextView) view.findViewById(R.id.item_nome);

        Aluno aluno = alunos.get(position);
        nome.setText(aluno.getNome());

        Bitmap bm;

        if (aluno.getCaminhoFoto() != null){
            bm = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
        } else {
            bm = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image);
        }

        bm = Bitmap.createScaledBitmap(bm, 100, 100, true);

        ImageView imageView = (ImageView) view.findViewById(R.id.item_foto);
        imageView.setImageBitmap(bm);

        return view;

    }
}
