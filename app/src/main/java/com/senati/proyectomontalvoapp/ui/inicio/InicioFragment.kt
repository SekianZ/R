package com.senati.proyectomontalvoapp.ui.inicio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.senati.proyectomontalvoapp.R

class InicioFragment : Fragment() {

    // Header views
    private lateinit var menuIcon: ImageView
    private lateinit var headerLogoImage: ImageView

    // Promotion cards
    private lateinit var promotionCard1: CardView
    private lateinit var promotionCard2: CardView
    private lateinit var promotionCard3: CardView
    private lateinit var imgPromotion1: ImageView
    private lateinit var imgPromotion2: ImageView
    private lateinit var imgPromotion3: ImageView

    // Filter/Search
    private lateinit var btnSearchFilter: ImageView
    private lateinit var tvSearchFilter: TextView

    // Pagination indicators
    private lateinit var indicator1: View
    private lateinit var indicator2: View
    private lateinit var indicator3: View
    private lateinit var indicator4: View
    private lateinit var indicator5: View

    // Bottom navigation
    private lateinit var navOffers: ImageView
    private lateinit var navFavorites: ImageView
    private lateinit var navSearch: ImageView
    private lateinit var navCart: ImageView
    private lateinit var navLocation: ImageView

    private var currentPage = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inicio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews(view)
        setupListeners()
        setupPagination()
    }

    private fun initializeViews(view: View) {
        // Header
        menuIcon = view.findViewById(R.id.menuIcon)
        headerLogoImage = view.findViewById(R.id.headerLogoImage)

        // Promotion cards
        promotionCard1 = view.findViewById(R.id.promotionCard1)
        promotionCard2 = view.findViewById(R.id.promotionCard2)
        promotionCard3 = view.findViewById(R.id.promotionCard3)
        imgPromotion1 = view.findViewById(R.id.imgPromotion1)
        imgPromotion2 = view.findViewById(R.id.imgPromotion2)
        imgPromotion3 = view.findViewById(R.id.imgPromotion3)

        // Filter/Search
        btnSearchFilter = view.findViewById(R.id.btnSearchFilter)
        tvSearchFilter = view.findViewById(R.id.tvSearchFilter)

        // Pagination indicators
        indicator1 = view.findViewById(R.id.indicator1)
        indicator2 = view.findViewById(R.id.indicator2)
        indicator3 = view.findViewById(R.id.indicator3)
        indicator4 = view.findViewById(R.id.indicator4)
        indicator5 = view.findViewById(R.id.indicator5)

        // Bottom navigation
        navOffers = view.findViewById(R.id.navOffers)
        navFavorites = view.findViewById(R.id.navFavorites)
        navSearch = view.findViewById(R.id.navSearch)
        navCart = view.findViewById(R.id.navCart)
        navLocation = view.findViewById(R.id.navLocation)
    }

    private fun setupListeners() {
        // Menu icon
        menuIcon.setOnClickListener {
            // Abrir drawer o menú lateral
            openNavigationDrawer()
        }

        // Filter/Search
        btnSearchFilter.setOnClickListener {
            showFilterDialog()
        }

        tvSearchFilter.setOnClickListener {
            showFilterDialog()
        }

        // Promotion cards
        promotionCard1.setOnClickListener {
            openPromotionDetail(1, "Manicure", 20)
        }

        promotionCard2.setOnClickListener {
            openPromotionDetail(2, "Depilación", 30)
        }

        promotionCard3.setOnClickListener {
            openPromotionDetail(3, "Peluquería", 50)
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
            navigateToCart()
        }

        navLocation.setOnClickListener {
            navigateToLocation()
        }
    }

    private fun setupPagination() {
        // Configurar los indicadores de paginación
        // Por defecto, el primero está activo
        updatePaginationIndicator(0)
    }

    private fun updatePaginationIndicator(position: Int) {
        currentPage = position
        val indicators = listOf(indicator1, indicator2, indicator3, indicator4, indicator5)

        indicators.forEachIndexed { index, indicator ->
            if (index == position) {
                // Indicador activo - color más oscuro
                indicator.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.montalvo_pink
                )
            } else {
                // Indicador inactivo - color claro
                indicator.backgroundTintList = ContextCompat.getColorStateList(
                    requireContext(),
                    R.color.indicator_bg
                )
            }
        }
    }

    private fun openNavigationDrawer() {
        // Implementar apertura del drawer
        // Ejemplo: (activity as? MainActivity)?.openDrawer()
    }

    private fun showFilterDialog() {
        // Mostrar diálogo o bottom sheet con opciones de filtrado
        // - De menor a mayor precio
        // - De mayor a menor precio
        // - Más recientes
        // - Más populares
    }

    private fun openPromotionDetail(promotionId: Int, promotionName: String, discount: Int) {
        // Navegar al detalle de la promoción
        // Ejemplo usando Navigation Component:
        // val action = InicioFragmentDirections.actionInicioFragmentToPromotionDetailFragment(
        //     promotionId, promotionName, discount
        // )
        // findNavController().navigate(action)
    }

    private fun navigateToOffers() {
        // Navegar a la sección de ofertas
        updateBottomNavSelection(navOffers)
    }

    private fun navigateToFavorites() {
        // Navegar a favoritos
        updateBottomNavSelection(navFavorites)
    }

    private fun navigateToSearch() {
        // Navegar a búsqueda
        updateBottomNavSelection(navSearch)
    }

    private fun navigateToCart() {
        // Navegar al carrito
        updateBottomNavSelection(navCart)
    }

    private fun navigateToLocation() {
        // Navegar a ubicaciones/sucursales
        updateBottomNavSelection(navLocation)
    }

    private fun updateBottomNavSelection(selectedView: ImageView) {
        // Reset all icons to default color
        val allNavIcons = listOf(navOffers, navFavorites, navSearch, navCart, navLocation)
        allNavIcons.forEach { icon ->
            icon.imageTintList = ContextCompat.getColorStateList(
                requireContext(),
                R.color.ButtonPrimary
            )
        }

        // Highlight selected icon
        selectedView.imageTintList = ContextCompat.getColorStateList(
            requireContext(),
            R.color.montalvo_pink
        )
    }

    companion object {
        fun newInstance() = InicioFragment()
    }
}
