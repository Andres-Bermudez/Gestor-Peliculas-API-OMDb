package buscadorpeliculas;

import com.google.gson.annotations.SerializedName;

public record Pelicula(
        @SerializedName("Title") String titulo,
        @SerializedName("Year") String año,
        @SerializedName("Released") String fechaPublicacion,
        @SerializedName("Runtime") String duracion,
        @SerializedName("Genre") String genero,
        @SerializedName("Director") String director,
        @SerializedName("Writer") String escritor,
        @SerializedName("Actors") String actores,
        @SerializedName("Country") String pais,
        @SerializedName("Poster") String imagen
) {
    @Override
    public String toString() {
        return "\n:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::" +
               "\nTitulo: " + titulo +
               "\nAño: " + año +
               "\nFecha publicacion: " + fechaPublicacion +
               "\nDuracion: " + fechaPublicacion +
               "\nGenero: " + genero +
               "\nDirector: " + director +
               "\nEscritor: " + escritor +
               "\nActores: " + actores +
               "\nPais: " + pais +
               "\nImagen: " + imagen +
               "\n";
    }
}
