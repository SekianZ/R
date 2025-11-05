package com.senati.proyectomontalvoapp.ui.Busqueda

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.senati.proyectomontalvoapp.R

class BusquedaFragment : Fragment() {

    // DrawerLayout
    private lateinit var drawerLayout: DrawerLayout

    // Header views
    private lateinit var menuIcon: ImageView

    // Search
    private lateinit var etBuscar: EditText
    private lateinit var btnLimpiarHistorial: TextView

    // RecyclerView
    private lateinit var recyclerBusquedasRecientes: RecyclerView
    private lateinit var busquedasAdapter: BusquedasRecientesAdapter

    // Bottom navigation
    private lateinit var navOffers: LinearLayout
    private lateinit var navFavorites: LinearLayout
    private lateinit var navSearch: LinearLayout
    private lateinit var navCart: LinearLayout
    private lateinit var navLocation: LinearLayout

    // Back press callback
    private lateinit var backPressCallback: OnBackPressedCallback

    // Lista de búsquedas
    private val busquedasRecientes = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_busqueda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews(view)
        setupRecyclerView()
        setupListeners()
        setupBackPressHandler()
        loadBusquedasRecientes()
    }

    private fun initializeViews(view: View) {
        // DrawerLayout
        drawerLayout = view.findViewById(R.id.drawerLayout)

        // Header
        menuIcon = view.findViewById(R.id.menuIcon)

        // Search
        etBuscar = view.findViewById(R.id.etBuscar)
        btnLimpiarHistorial = view.findViewById(R.id.btnLimpiarHistorial)

        // RecyclerView
        recyclerBusquedasRecientes = view.findViewById(R.id.recyclerBusquedasRecientes)

        // Bottom navigation
        navOffers = view.findViewById(R.id.navOffers)
        navFavorites = view.findViewById(R.id.navFavorites)
        navSearch = view.findViewById(R.id.navSearch)
        navCart = view.findViewById(R.id.navCart)
        navLocation = view.findViewById(R.id.navLocation)

        // Marcar Búsqueda como seleccionado
        view.findViewById<View>(R.id.navSearchIndicator).visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        recyclerBusquedasRecientes.layoutManager = LinearLayoutManager(requireContext())
        busquedasAdapter = BusquedasRecientesAdapter(
            busquedasRecientes,
            onItemClick = { busqueda ->
                // Realizar búsqueda
                realizarBusqueda(busqueda)
            },
            onDeleteClick = { busqueda ->
                // Eliminar búsqueda
                eliminarBusqueda(busqueda)
            }
        )
        recyclerBusquedasRecientes.adapter = busquedasAdapter
    }

    private fun setupListeners() {
        // Menu icon
        menuIcon.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Search input
        etBuscar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Filtrar búsquedas mientras escribe
                filtrarBusquedas(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        etBuscar.setOnEditorActionListener { _, _, _ ->
            val query = etBuscar.text.toString().trim()
            if (query.isNotEmpty()) {
                agregarBusqueda(query)
                realizarBusqueda(query)
            }
            true
        }

        // Limpiar historial
        btnLimpiarHistorial.setOnClickListener {
            limpiarHistorial()
        }

        // Bottom navigation
        navOffers.setOnClickListener {
            try {
                findNavController().navigate(R.id.action_busquedaFragment_to_inicioFragment)
            } catch (e: Exception) {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }

        navFavorites.setOnClickListener {
            try {
                findNavController().navigate(R.id.action_busquedaFragment_to_favoritosFragment)
            } catch (e: Exception) {
                // Handle error
            }
        }

        navSearch.setOnClickListener {
            // Ya estamos aquí
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

    private fun loadBusquedasRecientes() {
        // Cargar búsquedas de ejemplo
        busquedasRecientes.clear()
        busquedasRecientes.addAll(
            listOf(
                "Laceado brasilero",
                "Tendencias",
                "Balayage",
                "Manicure",
                "Masajes"
            )
        )
        busquedasAdapter.notifyDataSetChanged()
    }

    private fun agregarBusqueda(query: String) {
        if (!busquedasRecientes.contains(query)) {
            busquedasRecientes.add(0, query)
            if (busquedasRecientes.size > 10) {
                busquedasRecientes.removeAt(busquedasRecientes.size - 1)
            }
            busquedasAdapter.notifyDataSetChanged()
        }
    }

    private fun eliminarBusqueda(busqueda: String) {
        val position = busquedasRecientes.indexOf(busqueda)
        if (position != -1) {
            busquedasRecientes.removeAt(position)
            busquedasAdapter.notifyItemRemoved(position)
        }
    }

    private fun limpiarHistorial() {
        busquedasRecientes.clear()
        busquedasAdapter.notifyDataSetChanged()
    }

    private fun filtrarBusquedas(query: String) {
        // TODO: Implementar filtrado de búsquedas
    }

    private fun realizarBusqueda(query: String) {
        // TODO: Implementar lógica de búsqueda
        etBuscar.setText(query)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        backPressCallback.remove()
    }

    companion object {
        fun newInstance() = BusquedaFragment()
    }
}