package be.rgen

import be.rgen.dto.album.AlbumDTO
import be.rgen.dto.artist.ArtistDTO
import be.rgen.entity.Album
import be.rgen.entity.Artist
import java.util.*
import javax.ws.rs.*
import javax.ws.rs.core.Response
import kotlin.streams.toList

@Path("/album")
class AlbumResource {

    @GET
    fun getAll(): List<AlbumDTO> {
        return Album.findAll().stream().map{ AlbumDTO(it) }.toList<AlbumDTO>()
    }

    @GET
    @Path("{id}")
    fun getById(@PathParam("id") albumId: Long): Response {
        val album = Album.findById(albumId)
        if(album != null) {
            return Response.ok(AlbumDTO(album)).build()
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("findByArtistId/{artistId}")
    fun findByArtist(@PathParam("artistId") artistId:Long): List<AlbumDTO> {
        var result: List<Album> = listOf()
        result = Album.findByArtistId(artistId)
        return result.map{ AlbumDTO(it) }
    }

    @GET
    @Path("findByTitle/{title}")
    fun findByTitle(@PathParam("title") title:String): List<AlbumDTO> {
        println("FindByTitle in Album called")
        var result: List<Album> = listOf()
        result = Album.findByTitle(title)
        return result.map{ AlbumDTO(it) }
    }
}