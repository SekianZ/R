package com.senati.proyectomontalvoapp.ui.Favoritos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.senati.proyectomontalvoapp.R

class FavoritosAdapter(
    private var favoritos: List<Favorito>,
    private val onItemClick: (Favorito) -> Unit
) : RecyclerView.Adapter<FavoritosAdapter.FavoritoViewHolder>() {

    inner class FavoritoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgFavorito: ImageView = view.findViewById(R.id.imgFavorito)

        fun bind(favorito: Favorito) {
            imgFavorito.setImageResource(favorito.imageRes)
            itemView.setOnClickListener {
                onItemClick(favorito)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorito, parent, false)
        return FavoritoViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritoViewHolder, position: Int) {
        holder.bind(favoritos[position])
    }

    override fun getItemCount() = favoritos.size

    fun updateFavoritos(newFavoritos: List<Favorito>) {
        favoritos = newFavoritos
        notifyDataSetChanged()
    }
}