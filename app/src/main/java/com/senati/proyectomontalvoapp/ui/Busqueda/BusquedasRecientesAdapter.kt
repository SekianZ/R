package com.senati.proyectomontalvoapp.ui.Busqueda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.senati.proyectomontalvoapp.R

class BusquedasRecientesAdapter(
    private val busquedas: List<String>,
    private val onItemClick: (String) -> Unit,
    private val onDeleteClick: (String) -> Unit
) : RecyclerView.Adapter<BusquedasRecientesAdapter.BusquedaViewHolder>() {

    inner class BusquedaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvBusqueda: TextView = view.findViewById(R.id.tvBusqueda)
        val btnEliminar: ImageView = view.findViewById(R.id.btnEliminarBusqueda)

        fun bind(busqueda: String) {
            tvBusqueda.text = busqueda

            itemView.setOnClickListener {
                onItemClick(busqueda)
            }

            btnEliminar.setOnClickListener {
                onDeleteClick(busqueda)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusquedaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_busqueda_reciente, parent, false)
        return BusquedaViewHolder(view)
    }

    override fun onBindViewHolder(holder: BusquedaViewHolder, position: Int) {
        holder.bind(busquedas[position])
    }

    override fun getItemCount() = busquedas.size
}