package be.rgen.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.*

@Entity
class Track: PanacheEntity() {

    companion object: PanacheCompanion<Track> {
        fun findByTitle(title: String) = list("title like ?1", "%$title%")
        fun findByArtistId(artistId: Long) = list("artist_id", artistId)
        fun findByAlbumId(albumId: Long) = list("album_id", albumId);
    }

    @Column(length = 32)
    lateinit var mimeType: String
    @ManyToOne
    @JoinColumn(name="artist_id")
    @JsonManagedReference
    lateinit var artist: Artist
    @ManyToOne
    @JoinColumn(name="album_id")
    @JsonManagedReference
    lateinit var album: Album

    lateinit var title: String
    @Column(length = 255)
    lateinit var path: String
    var length: UInt = 0u
    var trackno: Int = 1
}