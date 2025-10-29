package com.senati.proyectomontalvoapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.senati.proyectomontalvoapp.R

class LoginFragment : Fragment() {

    private lateinit var usernameEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var btnEntrar: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar vistas
        usernameEditText = view.findViewById(R.id.usernameEditText)
        passwordEditText = view.findViewById(R.id.passwordEditText)
        btnEntrar = view.findViewById(R.id.btnEntrar)

        // Configurar el click del botón
        btnEntrar.setOnClickListener {
            validarYNavegar()
        }
    }

    private fun validarYNavegar() {
        val username = usernameEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        // Validar que los campos no estén vacíos
        if (username.isEmpty()) {
            Toast.makeText(requireContext(), "Ingrese su usuario", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.isEmpty()) {
            Toast.makeText(requireContext(), "Ingrese su contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        // Validación simple (puedes cambiarla por tu lógica de autenticación)
        if (validarCredenciales(username, password)) {
            // Navegar a la vista de inicio
            findNavController().navigate(R.id.action_loginFragment_to_inicioFragment)
        } else {
            Toast.makeText(
                requireContext(),
                "Usuario o contraseña incorrectos",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun validarCredenciales(username: String, password: String): Boolean {
        // Por ahora, validación simple
        // TODO: Implementar validación real con API o base de datos
        return username.isNotEmpty() && password.length >= 6
    }
}