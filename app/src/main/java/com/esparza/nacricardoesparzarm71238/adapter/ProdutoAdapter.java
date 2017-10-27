package com.esparza.nacricardoesparzarm71238.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esparza.nacricardoesparzarm71238.R;
import com.esparza.nacricardoesparzarm71238.model.Produto;

import java.util.List;

/**
 * Created by Esparza on 10/27/2017.
 */

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

private List<Produto> produtos;
private Activity activity;
private int lastPositionSelected;

public class ProdutoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener{

    public TextView tvNomeProduto;

    public ProdutoViewHolder(View view) {
        super(view);
        tvNomeProduto = (TextView) view.findViewById(R.id.tvNomeProduto);
        view.setOnClickListener(this);
        view.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onClick(View view) {
        activity.openContextMenu(view);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        lastPositionSelected = getLayoutPosition();
        menu.setHeaderIcon(R.mipmap.ic_launcher);
        menu.setHeaderTitle(activity.getString(R.string.app_name));
    }
}

    public ProdutoAdapter(Activity activity, List<Produto> produtos) {
        this.activity = activity;
        this.produtos = produtos;
    }

    @Override
    public ProdutoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.produto_list_row, parent, false);
        return new ProdutoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProdutoViewHolder holder, int position) {
        Produto produto = produtos.get(position);
        holder.tvNomeProduto.setText(produto.getName());
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public void add(Produto produto) {
        produtos.add(produto);
        notifyDataSetChanged();
    }

    public void addAll(List<Produto> produtosList) {
        produtos.addAll(produtosList);
        notifyDataSetChanged();
    }

    public Produto getProdutoSelected() {
        return produtos.get(lastPositionSelected);
    }

    public void removeProdutoSelected() {
        produtos.remove(produtos.get(lastPositionSelected));
        notifyItemRemoved(lastPositionSelected);
    }
}