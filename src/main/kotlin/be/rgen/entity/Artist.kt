package be.rgen.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany

@Entity
class Artist(): PanacheEntity() {
    companion object: PanacheCompanion<Artist> {
        fun findByName(name: String) = list("name like ?1", "%$name%")
    }
    constructor(name: String) : this() { this.name = name }

    lateinit var name: String;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "albumArtist")
    @JsonBackReference
    lateinit var albums: Set<Album>
}