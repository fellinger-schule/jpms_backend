package be.rgen

import be.rgen.dto.track.TrackDTO
import be.rgen.entity.Track
import javax.ws.rs.*
import kotlin.streams.toList

@Path("/track")
class TrackResource {

    @GET
    fun getAll(): List<TrackDTO> {
        return Track.findAll().stream().map{ TrackDTO(it) }.toList<TrackDTO>()
    }

    @GET
    @Path("findByArtistId/{artistId}")
    fun findByArtist(@PathParam("artistId") artistId:Long): List<TrackDTO> {
        var result: List<Track> = listOf()
        result = Track.findByArtistId(artistId)
        return result.map{ TrackDTO(it) }
    }

    @GET
    @Path("findByTitle/{title}")
    fun findByTitle(@PathParam("title") title:String): List<TrackDTO> {
        var result: List<Track> = listOf()
        result = Track.findByTitle(title)
        return result.map{ TrackDTO(it) }
    }

    @GET
    @Path("findByAlbumId/{albumId}")
    fun findByAlbumId(@PathParam("albumId") albumId:Long): List<TrackDTO> {
        var result: List<Track> = listOf()
        result = Track.findByAlbumId(albumId)
        return result.map{ TrackDTO(it) }
    }
}