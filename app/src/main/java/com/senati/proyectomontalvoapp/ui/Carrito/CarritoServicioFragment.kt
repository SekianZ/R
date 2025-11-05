package com.senati.proyectomontalvoapp.ui.Carrito

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.senati.proyectomontalvoapp.R

class CarritoServicioFragment : Fragment() {

    // DrawerLayout
    private lateinit var drawerLayout: DrawerLayout

    // Header views
    private lateinit var menuIcon: ImageView
    private lateinit var headerLogoImage: ImageView

    // Tabs
    private lateinit var tabProducto: TextView
    private lateinit var tabServicio: TextView

    // Service buttons
    private lateinit var btnComoLlegar1: Button
    private lateinit var btnCancelarReserva1: Button
    private lateinit var btnComoLlegar2: Button
    private lateinit var btnCancelarReserva2: Button

    // Menu items
    private lateinit var menuServicios: LinearLayout
    private lateinit var menuNotificaciones: LinearLayout
    private lateinit var menuPuntos: LinearLayout
    private lateinit var menuChat: LinearLayout
    private lateinit var menuTendencias: LinearLayout
    private lateinit var menuHairColor: LinearLayout
    private lateinit var menuTarjetas: LinearLayout
    private lateinit var menuContacto: LinearLayout
    private lateinit var menuAyuda: LinearLayout
    private lateinit var menuCerrarSesion: LinearLayout

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
        return inflater.inflate(R.layout.fragment_carrito_servicio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews(view)
        setupListeners()
        setupMenuListeners()
        setupBackPressHandler()

        // Marcar Carrito como seleccionado
        updateBottomNavSelection(3)
    }

    private fun initializeViews(view: View) {
        // DrawerLayout
        drawerLayout = view.findViewById(R.id.drawerLayout)

        // Header
        menuIcon = view.findViewById(R.id.menuIcon)
        headerLogoImage = view.findViewById(R.id.headerLogoImage)

        // Tabs
        tabProducto = view.findViewById(R.id.tabProducto)
        tabServicio = view.findViewById(R.id.tabServicio)

        // Service buttons
        btnComoLlegar1 = view.findViewById(R.id.btnComoLlegar1)
        btnCancelarReserva1 = view.findViewById(R.id.btnCancelarReserva1)
        btnComoLlegar2 = view.findViewById(R.id.btnComoLlegar2)
        btnCancelarReserva2 = view.findViewById(R.id.btnCancelarReserva2)

        // Menu items
        menuServicios = view.findViewById(R.id.menuServicios)
        menuNotificaciones = view.findViewById(R.id.menuNotificaciones)
        menuPuntos = view.findViewById(R.id.menuPuntos)
        menuChat = view.findViewById(R.id.menuChat)
        menuTendencias = view.findViewById(R.id.menuTendencias)
        menuHairColor = view.findViewById(R.id.menuHairColor)
        menuTarjetas = view.findViewById(R.id.menuTarjetas)
        menuContacto = view.findViewById(R.id.menuContacto)
        menuAyuda = view.findViewById(R.id.menuAyuda)
        menuCerrarSesion = view.findViewById(R.id.menuCerrarSesion)

        // Bottom navigation
        navOffers = view.findViewById(R.id.navOffers)
        navFavorites = view.findViewById(R.id.navFavorites)
        navSearch = view.findViewById(R.id.navSearch)
        navCart = view.findViewById(R.id.navCart)
        navLocation = view.findViewById(R.id.navLocation)
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

    private fun setupListeners() {
        // Menu icon para abrir drawer
        menuIcon.setOnClickListener {
            openNavigationDrawer()
        }

        // Tabs
        tabProducto.setOnClickListener {
            navigateToProducto()
        }

        tabServicio.setOnClickListener {
            // Ya estamos en servicios
        }

        // Service buttons
        btnComoLlegar1.setOnClickListener {
            // TODO: Implementar navegación/mapa
        }

        btnCancelarReserva1.setOnClickListener {
            // TODO: Implementar cancelación de reserva
        }

        btnComoLlegar2.setOnClickListener {
            // TODO: Implementar navegación/mapa
        }

        btnCancelarReserva2.setOnClickListener {
            // TODO: Implementar cancelación de reserva
        }

        // Bottom navigation
        navOffers.setOnClickListener {
            navigateToOffers()
        }

        navFavorites.setOnClickListener {
            navigateToFavorites()
        }

        navSearch.setOnClickListener {
            navigateToSearch()
        }

        navCart.setOnClickListener {
            navigateToProducto()
        }

        navLocation.setOnClickListener {
            navigateToLocation()
        }
    }

    private fun setupMenuListeners() {
        menuServicios.setOnClickListener {
            closeNavigationDrawer()
            // TODO: Implementar navegación a servicios
        }

        menuNotificaciones.setOnClickListener {
            closeNavigationDrawer()
            // TODO: Implementar navegación a notificaciones
        }

        menuPuntos.setOnClickListener {
            closeNavigationDrawer()
            // TODO: Implementar navegación a puntos
        }

        menuChat.setOnClickListener {
            closeNavigationDrawer()
            // TODO: Implementar navegación a chat
        }

        menuTendencias.setOnClickListener {
            closeNavigationDrawer()
            // TODO: Implementar navegación a tendencias
        }

        menuHairColor.setOnClickListener {
            closeNavigationDrawer()
            // TODO: Implementar navegación a hair color cam
        }

        menuTarjetas.setOnClickListener {
            closeNavigationDrawer()
            // TODO: Implementar navegación a tarjetas
        }

        menuContacto.setOnClickListener {
            closeNavigationDrawer()
            // TODO: Implementar navegación a contacto
        }

        menuAyuda.setOnClickListener {
            closeNavigationDrawer()
            // TODO: Implementar navegación a ayuda
        }

        menuCerrarSesion.setOnClickListener {
            closeNavigationDrawer()
            // TODO: Implementar lógica de cierre de sesión
        }
    }

    private fun openNavigationDrawer() {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun closeNavigationDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    private fun navigateToProducto() {
        try {
            findNavController().navigate(R.id.action_carritoServicioFragment_to_carritoFragment)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun navigateToOffers() {
        try {
            findNavController().navigate(R.id.action_carritoServicioFragment_to_inicioFragment)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun navigateToFavorites() {
        try {
            findNavController().navigate(R.id.action_carritoServicioFragment_to_favoritosFragment)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun navigateToSearch() {
        try {
            findNavController().navigate(R.id.action_carritoServicioFragment_to_busquedaFragment)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun navigateToLocation() {
        // TODO: Implementar navegación a ubicación
        updateBottomNavSelection(4)
    }

    private fun updateBottomNavSelection(position: Int) {
        val allNavLayouts = listOf(navOffers, navFavorites, navSearch, navCart, navLocation)
        val allIndicators = listOf(
            R.id.navOffersIndicator,
            R.id.navFavoritesIndicator,
            R.id.navSearchIndicator,
            R.id.navCartIndicator,
            R.id.navLocationIndicator
        )

        allNavLayouts.forEachIndexed { index, layout ->
            val indicator = layout.findViewById<View>(allIndicators[index])

            if (index == position) {
                indicator?.visibility = View.VISIBLE
            } else {
                indicator?.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        backPressCallback.remove()
    }

    companion object {
        fun newInstance() = CarritoServicioFragment()
    }
}