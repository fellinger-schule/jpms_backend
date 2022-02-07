package be.rgen.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class Album: PanacheEntity() {
    companion object: PanacheCompanion<Album> {
        fun findByTitle(title: String) = list("title like ?1", "%$title%")
        fun findByArtistId(artistId: Long) = list("artist_id", artistId)
    }

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name="artist_id")
    lateinit var albumArtist: Artist
    @OneToMany(mappedBy = "album")
    @JsonBackReference
    lateinit var tracks: Set<Track>
    lateinit var title: String
}