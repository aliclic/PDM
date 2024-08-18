package com.example.navegacao1.model.dados

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects

//classe que pega dados do Firestore
class UsuarioDAO {

    val db = FirebaseFirestore.getInstance()

    fun buscar(callback: (List<Usuario>) -> Unit) {
        db.collection("usuarios").get()
            .addOnSuccessListener { document ->
                val usuarios = document.toObjects<Usuario>()
                callback(usuarios)
            }
            .addOnFailureListener {
                callback(emptyList())
            }
    }

    fun buscarPorNome(nome: String, callback: (Usuario?) -> Unit) {
        db.collection("usuarios").whereEqualTo("nome", nome).get()
            .addOnSuccessListener { document ->
                if (!document.isEmpty) {
                    val usuario = document.documents[0].toObject<Usuario>()
                    Log.d("UsuarioDAO", "Usuário encontrado: $usuario")
                    callback(usuario)
                } else {
                    Log.d("UsuarioDAO", "Nenhum usuário encontrado com o nome: $nome")
                    callback(null)
                }
            }
            .addOnFailureListener { exception ->
                Log.e("UsuarioDAO", "Erro ao buscar usuário: ${exception.message}")
                callback(null)
            }
    }


    fun buscarPorId(id: String, callback: (Usuario?) -> Unit) {
        db.collection("usuarios").document(id).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val usuario = document.toObject<Usuario>()
                    Log.d("UsuarioDAO", "Usuário encontrado: $usuario")
                    callback(usuario)
                } else {
                    Log.d("UsuarioDAO", "Nenhum usuário encontrado com o ID: $id")
                    callback(null)
                }
            }
            .addOnFailureListener { exception ->
                Log.e("UsuarioDAO", "Erro ao buscar usuário por ID: ${exception.message}")
                callback(null)
            }
    }

    fun adicionar(usuario: Usuario, callback: (Usuario?) -> Unit) {
        db.collection("usuarios").add(usuario)
            .addOnSuccessListener { documentReference ->
                Log.d("UsuarioDAO", "Usuário adicionado com sucesso: ${documentReference.id}")
                usuario.id = documentReference.id
                callback(usuario)
            }
            .addOnFailureListener { exception ->
                Log.e("UsuarioDAO", "Erro ao adicionar usuário: ${exception.message}")
                callback(null)
            }
    }

}