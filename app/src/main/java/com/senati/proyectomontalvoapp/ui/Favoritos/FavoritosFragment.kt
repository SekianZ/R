package com.senati.proyectomontalvoapp.ui.Favoritos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.senati.proyectomontalvoapp.R

class FavoritosFragment : Fragment() {

    // DrawerLayout
    private lateinit var drawerLayout: DrawerLayout

    // Header views
    private lateinit var menuIcon: ImageView
    private lateinit var btnFilterCategory: ImageView
    private lateinit var tvFilterCategory: TextView

    // RecyclerView
    private lateinit var recyclerFavoritos: RecyclerView
    private lateinit var favoritosAdapter: FavoritosAdapter

    // Bottom navigation
    private lateinit var navOffers: LinearLayout
    private lateinit var navFavorites: LinearLayout
    private lateinit var navSearch: LinearLayout
    private lateinit var navCart: LinearLayout
    private lateinit var navLocation: LinearLayout

    // Back press callback
    private lateinit var backPressCallback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favoritos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews(view)
        setupRecyclerView()
        setupListeners()
        setupBackPressHandler()
        loadFavoritos()
    }

    private fun initializeViews(view: View) {
        // DrawerLayout
        drawerLayout = view.findViewById(R.id.drawerLayout)

        // Header
        menuIcon = view.findViewById(R.id.menuIcon)
        btnFilterCategory = view.findViewById(R.id.btnFilterCategory)
        tvFilterCategory = view.findViewById(R.id.tvFilterCategory)

        // RecyclerView
        recyclerFavoritos = view.findViewById(R.id.recyclerFavoritos)

        // Bottom navigation
        navOffers = view.findViewById(R.id.navOffers)
        navFavorites = view.findViewById(R.id.navFavorites)
        navSearch = view.findViewById(R.id.navSearch)
        navCart = view.findViewById(R.id.navCart)
        navLocation = view.findViewById(R.id.navLocation)

        // Marcar Favoritos como seleccionado
        view.findViewById<View>(R.id.navFavoritesIndicator).visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        // Grid de 2 columnas
        recyclerFavoritos.layoutManager = GridLayoutManager(requireContext(), 2)
        favoritosAdapter = FavoritosAdapter(emptyList()) { favorito ->
            // Click en favorito
            openFavoritoDetail(favorito)
        }
        recyclerFavoritos.adapter = favoritosAdapter
    }

    private fun setupListeners() {
        menuIcon.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        btnFilterCategory.setOnClickListener {
            showFilterDialog()
        }

        tvFilterCategory.setOnClickListener {
            showFilterDialog()
        }

        // Bottom navigation
        navOffers.setOnClickListener {
            // Navegar de regreso a Inicio
            try {
                findNavController().navigate(R.id.action_favoritosFragment_to_inicioFragment)
            } catch (e: Exception) {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }

        navFavorites.setOnClickListener {
            // Ya estamos aquí
        }

        navSearch.setOnClickListener {
            // TODO: Navegar a Búsqueda
        }

        navCart.setOnClickListener {
            // TODO: Navegar a Carrito
        }

        navLocation.setOnClickListener {
            // TODO: Navegar a Ubicación
        }
    }
    private fun setupBackPressHandler() {
        backPressCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    isEnabled = false
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            backPressCallback
        )
    }

    private fun loadFavoritos() {
        // Datos de ejemplo - reemplazar con datos reales
        val favoritos = listOf(
            Favorito(1, R.drawable.image1, "Manicure Negro"),
            Favorito(2, R.drawable.image2, "Manicure Rosa"),
            Favorito(3, R.drawable.image3, "Manicure Morado"),
            Favorito(4, R.drawable.image1, "Manicure Azul"),
            Favorito(5, R.drawable.image2, "Manicure Nude"),
            Favorito(6, R.drawable.image3, "Manicure Decorado")
        )
        favoritosAdapter.updateFavoritos(favoritos)
    }

    private fun showFilterDialog() {
        // TODO: Mostrar diálogo de filtros por categoría
        // Categorías: Manicure y Pedicure, Peluquería, Depilación, etc.
    }

    private fun openFavoritoDetail(favorito: Favorito) {
        // TODO: Navegar al detalle del favorito
    }

    override fun onDestroyView() {
        super.onDestroyView()
        backPressCallback.remove()
    }

    companion object {
        fun newInstance() = FavoritosFragment()
    }
}

// Data class para Favorito
data class Favorito(
    val id: Int,
    val imageRes: Int,
    val nombre: String
)