package be.rgen

import be.rgen.dto.album.AlbumDTO
import be.rgen.dto.artist.*
import be.rgen.entity.Album
import be.rgen.entity.Artist
import java.util.*
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import kotlin.streams.toList

@Path("/artist")
class ArtistResource {
    @GET
    fun getAll(): List<ArtistDTO> {
        return Artist.findAll().stream().map{ ArtistDTO(it) }.toList<ArtistDTO>()
    }

    @POST
    @Path("add")
    @Transactional
    fun addArtist(artist: ArtistAddDTO): ArtistDTO {
        val newArtist = Artist(artist.name);
        newArtist.persist();
        return ArtistDTO(newArtist);
    }

    @GET
    @Path("{id}")
    fun getById(@PathParam("id") artistId: Long): Response {
        val artist = Artist.findById(artistId)
        if(artist != null)
            return Response.ok(ArtistDTO(artist)).build()

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("findByName/{name}")
    fun findByName(@PathParam("name") name: String): List<ArtistDTO> {
        var result: List<Artist> = listOf()
        result = Artist.findByName(name);
        return result.map { ArtistDTO(it) }
    }
}